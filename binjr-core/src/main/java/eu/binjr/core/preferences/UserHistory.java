/*
 *    Copyright 2019 Frederic Thevenet
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

package eu.binjr.core.preferences;

import eu.binjr.common.preferences.MostRecentlyUsedList;
import eu.binjr.common.preferences.MruFactory;
import eu.binjr.common.preferences.PreferenceFactory;

import java.net.URI;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class UserHistory extends MruFactory {

    private final Map<String, MostRecentlyUsedList<?>> mrulMap;

    public final MostRecentlyUsedList<Path> mostRecentWorkspaces =
            pathMostRecentlyUsedList("mostRecentWorkspaces", 20, false);

    public final MostRecentlyUsedList<Path> mostRecentSaveFolders =
            pathMostRecentlyUsedList("mostRecentSaveFolders", 20, true);

    private UserHistory() {
        super(Preferences.userRoot().node("binjr/history"));
        mrulMap = new HashMap<>();
        mrulMap.put("mostRecentWorkspaces", mostRecentWorkspaces);
    }

    public static UserHistory getInstance() {
        return UserHistoryHolder.instance;
    }

    private static class UserHistoryHolder {
        private final static UserHistory instance = new UserHistory();
    }


}