/*
 * Copyright 2016 Animesh Shaw ( a.k.a. Psycho_Coder).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.letshackit.chromeforensics.core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ChromeForensics {

    /**
     * Essential System environment variables.
     */
    private final String USER_HOME;
    private final String OS;
    private final String WINDOWS_VISTAUP_CHROME_DATAPATH;
    private final String WINDOWS_XP_CHROME_DATAPATH;

    /**
     * Paths to Forensically important SQLite DB files.
     *
     * --To be Updated--
     */
    private final String HISTORY_DB_PATH;

    public ChromeForensics() {
        USER_HOME = System.getProperty("user.home");
        OS = System.getProperty("os.name");
        WINDOWS_VISTAUP_CHROME_DATAPATH = "AppData/Local/Google/Chrome/User Data/Default";
        WINDOWS_XP_CHROME_DATAPATH = "/Local Settings/Application Data/Google/Chrome/User Data/Default";
        assert getChromeDataPath() != null;
        HISTORY_DB_PATH = Paths.get(getChromeDataPath().toString(), "History").toString();
    }

    /**
     *
     * @return
     */
    public String getUserHome() {
        return USER_HOME;
    }

    /**
     *
     * @return
     */
    public OSType getOSType(){
        switch (OS) {
            case "Windows 8.1":
                return OSType.WINDOWS_81;
            case "Windows 8":
                return OSType.WINDOWS_8;
            case "Windows 7":
                return OSType.WINDOWS_7;
            case "Windows Vista":
                return OSType.WINDOWS_VISTA;
            case "Windows XP":
                return OSType.WINDOWS_XP;
            case "Linux":
                return OSType.LINUX;
            default:
                return OSType.NOT_SUPPORTED;
        }
    }

    /**
     * Needs update
     * @return
     */
    public Path getChromeDataPath() {
        Path path;
        switch (getOSType()){
            case WINDOWS_81:
            case WINDOWS_8:
            case WINDOWS_7:
            case WINDOWS_VISTA:
                path = Paths.get(USER_HOME, WINDOWS_VISTAUP_CHROME_DATAPATH);
                assert Files.exists(path) && Files.isDirectory(path);
                return path;
            case WINDOWS_XP:
                path = Paths.get(USER_HOME, WINDOWS_XP_CHROME_DATAPATH);
                assert Files.exists(path) && Files.isDirectory(path);
                return path;
            case LINUX:
                break;
            case NOT_SUPPORTED:
                break;
        }
        return null;
    }

    public String getHistoryDbPath() {
        return HISTORY_DB_PATH;
    }

}