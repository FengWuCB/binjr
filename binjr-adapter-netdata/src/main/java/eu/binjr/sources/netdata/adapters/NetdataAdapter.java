/*
 *    Copyright 2020 Frederic Thevenet
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.binjr.sources.netdata.adapters;

import com.google.gson.Gson;
import eu.binjr.core.data.adapters.*;
import eu.binjr.core.data.codec.Decoder;
import eu.binjr.core.data.codec.csv.CsvDecoder;
import eu.binjr.core.data.exceptions.CannotInitializeDataAdapterException;
import eu.binjr.core.data.exceptions.DataAdapterException;
import eu.binjr.core.data.timeseries.DoubleTimeSeriesProcessor;
import eu.binjr.core.data.workspace.ChartType;
import eu.binjr.core.preferences.UserPreferences;
import eu.binjr.sources.netdata.api.ChartDimensions;
import eu.binjr.sources.netdata.api.ChartSummary;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.fx.ui.controls.tree.FilterableTreeItem;

import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Frederic Thevenet
 */
public class NetdataAdapter extends HttpDataAdapter {
    private static final Logger logger = LogManager.getLogger(NetdataAdapter.class);
    private static final char DELIMITER = ',';
    private ZoneId zoneId;
    private final Gson jsonParser;
    private Decoder decoder;
    private UserPreferences userPrefs = UserPreferences.getInstance();
    private NetdataAdapterPreferences adapterPrefs = (NetdataAdapterPreferences) getAdapterInfo().getPreferences();

    public NetdataAdapter() throws CannotInitializeDataAdapterException {
        this(null, ZoneId.systemDefault());
    }

    private NetdataAdapter(URL baseAddress, ZoneId zoneId) throws CannotInitializeDataAdapterException {
        super(baseAddress);
        this.zoneId = zoneId;
        this.decoder = buildDecoder(zoneId);
        jsonParser = new Gson();
    }

    public static DataAdapter fromUrl(String address, ZoneId zoneId) throws CannotInitializeDataAdapterException {
        return new NetdataAdapter(urlFromString(address), zoneId);
    }

    @Override
    protected URI craftFetchUri(String path, Instant begin, Instant end) throws DataAdapterException {

        return craftRequestUri(path,
                makeParamList(
                        UriParameter.of("after", begin.getEpochSecond()),
                        UriParameter.of("before", end.getEpochSecond())
                )
        );
    }

    private List<NameValuePair> makeParamList(NameValuePair... parameters) {
        var params = new ArrayList<NameValuePair>();
        params.add(UriParameter.of("points",
                (userPrefs.downSamplingEnabled.get() && !adapterPrefs.disableServerSideDownsampling.get()
                        ? userPrefs.downSamplingThreshold.get() : 0)));
        params.add(UriParameter.of("group", adapterPrefs.groupingMethod.get()));
        params.add(UriParameter.of("gtime", adapterPrefs.groupingTime.get()));
        if (adapterPrefs.disableTimeFrameAlignment.get()) {
            params.add(UriParameter.of("options", "unaligned"));
        }
        params.add(UriParameter.of("format", "csv"));
        params.add(UriParameter.of("options", "seconds"));
        params.addAll(Arrays.asList(parameters));
        return params;
    }

    @Override
    public Decoder getDecoder() {
        return this.decoder;
    }

    @Override
    public FilterableTreeItem<TimeSeriesBinding> getBindingTree() throws DataAdapterException {
        //TODO make async
        String entityString = doHttpGet(craftRequestUri(ChartSummary.ENDPOINT), new BasicResponseHandler());
        logger.trace(entityString);
        var chartSummary = jsonParser.fromJson(entityString, ChartSummary.class);
        this.zoneId = ZoneId.of(chartSummary.getTimezone());
        FilterableTreeItem<TimeSeriesBinding> tree = new FilterableTreeItem<>(new TimeSeriesBindingBuilder(this)
                .setLabel(getSourceName())
                .setParent("")
                .setPath("/").build());
        chartSummary.getCharts().forEach((s, chart) -> {
            var branch = new FilterableTreeItem<>(
                    new TimeSeriesBindingBuilder(this)
                            .setPath(chart.getDataUrl())
                            .setLabel(chart.getName())
                            .setGraphType(ChartType.valueOrDefault(chart.getChartType().name(), ChartType.STACKED))
                            .setLegend(chart.getTitle())
                            .setUnitName(chart.getUnits())
                            .setParent(tree.getValue().getTreeHierarchy())
                            .build());
            chart.getDimensions().forEach((s1, chartDimensions) -> {
                branch.getInternalChildren().add(new FilterableTreeItem<>(
                        new TimeSeriesBindingBuilder(this)
                                .setLabel(chartDimensions.getName())
                                .setParent(branch.getValue().getTreeHierarchy())
                                .setUnitName(chart.getUnits())
                                .setGraphType(ChartType.valueOrDefault(chart.getChartType().name(), ChartType.STACKED))
                                .setPath(chart.getDataUrl())
                                .build()));
            });
            tree.getInternalChildren().add(branch);
        });
        return tree;
    }

    @Override
    public String getEncoding() {
        return StandardCharsets.UTF_8.name();
    }

    @Override
    public ZoneId getTimeZoneId() {
        return zoneId;
    }


    @Override
    public String getSourceName() {
        return new StringBuilder("[Netdata] ")
                .append(getBaseAddress() != null ? getBaseAddress().getHost() : "???")
                .append((getBaseAddress() != null && getBaseAddress().getPort() > 0) ? ":" + getBaseAddress().getPort() : "")
                .append(" - ")
                .append(" (")
                .append(zoneId != null ? zoneId : "???")
                .append(")").toString();
    }

    private CsvDecoder buildDecoder(ZoneId zoneId) {
        return new CsvDecoder(getEncoding(), DELIMITER,
                DoubleTimeSeriesProcessor::new,
                s -> Instant.ofEpochSecond(Long.parseLong(s)).atZone(zoneId));
    }

}
