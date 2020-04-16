/*
 * NetData API
 * Real-time performance and health monitoring.
 *
 * The version of the OpenAPI document: 1.11.1_rolling
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package eu.binjr.sources.netdata.api;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Chart
 */
public class Chart {
    public static final String SERIALIZED_NAME_ID = "id";
    @SerializedName(SERIALIZED_NAME_ID)
    private String id;

    public static final String SERIALIZED_NAME_NAME = "name";
    @SerializedName(SERIALIZED_NAME_NAME)
    private String name;

    public static final String SERIALIZED_NAME_TYPE = "type";
    @SerializedName(SERIALIZED_NAME_TYPE)
    private String type;

    public static final String SERIALIZED_NAME_FAMILY = "family";
    @SerializedName(SERIALIZED_NAME_FAMILY)
    private String family;

    public static final String SERIALIZED_NAME_TITLE = "title";
    @SerializedName(SERIALIZED_NAME_TITLE)
    private String title;

    public static final String SERIALIZED_NAME_PRIORITY = "priority";
    @SerializedName(SERIALIZED_NAME_PRIORITY)
    private BigDecimal priority;

    public static final String SERIALIZED_NAME_ENABLED = "enabled";
    @SerializedName(SERIALIZED_NAME_ENABLED)
    private Boolean enabled;

    public static final String SERIALIZED_NAME_UNITS = "units";
    @SerializedName(SERIALIZED_NAME_UNITS)
    private String units;

    public static final String SERIALIZED_NAME_DATA_URL = "data_url";
    @SerializedName(SERIALIZED_NAME_DATA_URL)
    private String dataUrl;

    /**
     * The chart type.
     */
    @JsonAdapter(ChartTypeEnum.Adapter.class)
    public enum ChartTypeEnum {
        LINE("line"),

        AREA("area"),

        STACKED("stacked");

        private String value;

        ChartTypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static ChartTypeEnum fromValue(String value) {
            for (ChartTypeEnum b : ChartTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        public static class Adapter extends TypeAdapter<ChartTypeEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final ChartTypeEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public ChartTypeEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return ChartTypeEnum.fromValue(value);
            }
        }
    }

    public static final String SERIALIZED_NAME_CHART_TYPE = "chart_type";
    @SerializedName(SERIALIZED_NAME_CHART_TYPE)
    private ChartTypeEnum chartType;

    public static final String SERIALIZED_NAME_DURATION = "duration";
    @SerializedName(SERIALIZED_NAME_DURATION)
    private BigDecimal duration;

    public static final String SERIALIZED_NAME_FIRST_ENTRY = "first_entry";
    @SerializedName(SERIALIZED_NAME_FIRST_ENTRY)
    private BigDecimal firstEntry;

    public static final String SERIALIZED_NAME_LAST_ENTRY = "last_entry";
    @SerializedName(SERIALIZED_NAME_LAST_ENTRY)
    private BigDecimal lastEntry;

    public static final String SERIALIZED_NAME_UPDATE_EVERY = "update_every";
    @SerializedName(SERIALIZED_NAME_UPDATE_EVERY)
    private BigDecimal updateEvery;

    public static final String SERIALIZED_NAME_DIMENSIONS = "dimensions";
    @SerializedName(SERIALIZED_NAME_DIMENSIONS)
    private Map<String, ChartDimensions> dimensions = null;

//    public static final String SERIALIZED_NAME_CHART_VARIABLES = "chart_variables";
//    @SerializedName(SERIALIZED_NAME_CHART_VARIABLES)
//    private Map<String, ChartVariables> chartVariables = null;

    public static final String SERIALIZED_NAME_GREEN = "green";
    @SerializedName(SERIALIZED_NAME_GREEN)
    private BigDecimal green;

    public static final String SERIALIZED_NAME_RED = "red";
    @SerializedName(SERIALIZED_NAME_RED)
    private BigDecimal red;


    public Chart id(String id) {

        this.id = id;
        return this;
    }

    /**
     * The unique id of the chart.
     *
     * @return id
     **/
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public Chart name(String name) {

        this.name = name;
        return this;
    }

    /**
     * The name of the chart.
     *
     * @return name
     **/
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Chart type(String type) {

        this.type = type;
        return this;
    }

    /**
     * The type of the chart. Types are not handled by netdata. You can use this field for anything you like.
     *
     * @return type
     **/
    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Chart family(String family) {

        this.family = family;
        return this;
    }

    /**
     * The family of the chart. Families are not handled by netdata. You can use this field for anything you like.
     *
     * @return family
     **/
    public String getFamily() {
        return family;
    }


    public void setFamily(String family) {
        this.family = family;
    }


    public Chart title(String title) {

        this.title = title;
        return this;
    }

    /**
     * The title of the chart.
     *
     * @return title
     **/
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public Chart priority(BigDecimal priority) {

        this.priority = priority;
        return this;
    }

    /**
     * The relative priority of the chart. NetData does not care about priorities. This is just an indication of importance for the chart viewers to sort charts of higher priority (lower number) closer to the top. Priority sorting should only be used among charts of the same type or family.
     *
     * @return priority
     **/
    public BigDecimal getPriority() {
        return priority;
    }


    public void setPriority(BigDecimal priority) {
        this.priority = priority;
    }


    public Chart enabled(Boolean enabled) {

        this.enabled = enabled;
        return this;
    }

    /**
     * True when the chart is enabled. Disabled charts do not currently collect values, but they may have historical values available.
     *
     * @return enabled
     **/
    public Boolean getEnabled() {
        return enabled;
    }


    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public Chart units(String units) {

        this.units = units;
        return this;
    }

    /**
     * The unit of measurement for the values of all dimensions of the chart.
     *
     * @return units
     **/
    public String getUnits() {
        return units;
    }


    public void setUnits(String units) {
        this.units = units;
    }


    public Chart dataUrl(String dataUrl) {

        this.dataUrl = dataUrl;
        return this;
    }

    /**
     * The absolute path to get data values for this chart. You are expected to use this path as the base when constructing the URL to fetch data values for this chart.
     *
     * @return dataUrl
     **/
    public String getDataUrl() {
        return dataUrl;
    }


    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }


    public Chart chartType(ChartTypeEnum chartType) {

        this.chartType = chartType;
        return this;
    }

    /**
     * The chart type.
     *
     * @return chartType
     **/
    public ChartTypeEnum getChartType() {
        return chartType;
    }


    public void setChartType(ChartTypeEnum chartType) {
        this.chartType = chartType;
    }


    public Chart duration(BigDecimal duration) {

        this.duration = duration;
        return this;
    }

    /**
     * The duration, in seconds, of the round robin database maintained by netdata.
     *
     * @return duration
     **/
    public BigDecimal getDuration() {
        return duration;
    }


    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }


    public Chart firstEntry(BigDecimal firstEntry) {

        this.firstEntry = firstEntry;
        return this;
    }

    /**
     * The UNIX timestamp of the first entry (the oldest) in the round robin database.
     *
     * @return firstEntry
     **/
    public BigDecimal getFirstEntry() {
        return firstEntry;
    }


    public void setFirstEntry(BigDecimal firstEntry) {
        this.firstEntry = firstEntry;
    }


    public Chart lastEntry(BigDecimal lastEntry) {

        this.lastEntry = lastEntry;
        return this;
    }

    /**
     * The UNIX timestamp of the latest entry in the round robin database.
     *
     * @return lastEntry
     **/
    public BigDecimal getLastEntry() {
        return lastEntry;
    }

    public void setLastEntry(BigDecimal lastEntry) {
        this.lastEntry = lastEntry;
    }

    public Chart updateEvery(BigDecimal updateEvery) {

        this.updateEvery = updateEvery;
        return this;
    }

    /**
     * The update frequency of this chart, in seconds. One value every this amount of time is kept in the round robin database.
     *
     * @return updateEvery
     **/
    public BigDecimal getUpdateEvery() {
        return updateEvery;
    }


    public void setUpdateEvery(BigDecimal updateEvery) {
        this.updateEvery = updateEvery;
    }


    public Chart dimensions(Map<String, ChartDimensions> dimensions) {

        this.dimensions = dimensions;
        return this;
    }

    public Chart putDimensionsItem(String key, ChartDimensions dimensionsItem) {
        if (this.dimensions == null) {
            this.dimensions = new HashMap<>();
        }
        this.dimensions.put(key, dimensionsItem);
        return this;
    }

    /**
     * An object containing all the chart dimensions available for the chart. This is used as an indexed array. For each pair in the dictionary: the key is the id of the dimension and the value is a dictionary containing the name.
     *
     * @return dimensions
     **/


    public Map<String, ChartDimensions> getDimensions() {
        return dimensions;
    }


    public void setDimensions(Map<String, ChartDimensions> dimensions) {
        this.dimensions = dimensions;
    }


//    public Chart chartVariables(Map<String, ChartVariables> chartVariables) {
//
//        this.chartVariables = chartVariables;
//        return this;
//    }

//    public Chart putChartVariablesItem(String key, ChartVariables chartVariablesItem) {
//        if (this.chartVariables == null) {
//            this.chartVariables = new HashMap<>();
//        }
//        this.chartVariables.put(key, chartVariablesItem);
//        return this;
//    }
//
//    /**
//     * Get chartVariables
//     *
//     * @return chartVariables
//     **/
//
//
//    public Map<String, ChartVariables> getChartVariables() {
//        return chartVariables;
//    }
//
//
//    public void setChartVariables(Map<String, ChartVariables> chartVariables) {
//        this.chartVariables = chartVariables;
//    }


    public Chart green(BigDecimal green) {

        this.green = green;
        return this;
    }

    /**
     * Chart health green threshold.
     *
     * @return green
     **/


    public BigDecimal getGreen() {
        return green;
    }


    public void setGreen(BigDecimal green) {
        this.green = green;
    }


    public Chart red(BigDecimal red) {

        this.red = red;
        return this;
    }

    /**
     * Chart health red threshold.
     *
     * @return red
     **/


    public BigDecimal getRed() {
        return red;
    }


    public void setRed(BigDecimal red) {
        this.red = red;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chart chart = (Chart) o;
        return Objects.equals(this.id, chart.id) &&
                Objects.equals(this.name, chart.name) &&
                Objects.equals(this.type, chart.type) &&
                Objects.equals(this.family, chart.family) &&
                Objects.equals(this.title, chart.title) &&
                Objects.equals(this.priority, chart.priority) &&
                Objects.equals(this.enabled, chart.enabled) &&
                Objects.equals(this.units, chart.units) &&
                Objects.equals(this.dataUrl, chart.dataUrl) &&
                Objects.equals(this.chartType, chart.chartType) &&
                Objects.equals(this.duration, chart.duration) &&
                Objects.equals(this.firstEntry, chart.firstEntry) &&
                Objects.equals(this.lastEntry, chart.lastEntry) &&
                Objects.equals(this.updateEvery, chart.updateEvery) &&
                Objects.equals(this.dimensions, chart.dimensions) &&
//                Objects.equals(this.chartVariables, chart.chartVariables) &&
                Objects.equals(this.green, chart.green) &&
                Objects.equals(this.red, chart.red);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, family, title, priority, enabled, units, dataUrl, chartType, duration, firstEntry, lastEntry, updateEvery, dimensions, /*chartVariables,*/ green, red);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Chart {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    family: ").append(toIndentedString(family)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
        sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
        sb.append("    units: ").append(toIndentedString(units)).append("\n");
        sb.append("    dataUrl: ").append(toIndentedString(dataUrl)).append("\n");
        sb.append("    chartType: ").append(toIndentedString(chartType)).append("\n");
        sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
        sb.append("    firstEntry: ").append(toIndentedString(firstEntry)).append("\n");
        sb.append("    lastEntry: ").append(toIndentedString(lastEntry)).append("\n");
        sb.append("    updateEvery: ").append(toIndentedString(updateEvery)).append("\n");
        sb.append("    dimensions: ").append(toIndentedString(dimensions)).append("\n");
//        sb.append("    chartVariables: ").append(toIndentedString(chartVariables)).append("\n");
        sb.append("    green: ").append(toIndentedString(green)).append("\n");
        sb.append("    red: ").append(toIndentedString(red)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

