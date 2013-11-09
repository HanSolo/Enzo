/*
 * Copyright (c) 2013 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.enzo.common;

import javafx.scene.text.Font;


/**
 * User: hansolo
 * Date: 22.10.13
 * Time: 10:27
 */
public final class Fonts {
    private static final String BEBAS_NEUE_NAME;
    private static final String DIGITAL_NAME;
    private static final String DIGITAL_READOUT_NAME;
    private static final String DIGITAL_READOUT_BOLD_NAME;
    private static final String DIN_FUN_NAME;
    private static final String DROID_SANS_MONO_NAME;
    private static final String ELEKTRA_NAME;
    private static final String OPENSANS_LIGHT_NAME;
    private static final String OPENSANS_SEMI_BOLD_NAME;
    private static final String OPENSANS_BOLD_NAME;
        
    private static String bebasNeueName;
    private static String digitalName;
    private static String digitalReadoutName;
    private static String digitalReadoutBoldName;
    private static String dinFunName;
    private static String droidSansMonoName;
    private static String elektraName;
    private static String openSansLightName;
    private static String openSansSemiBoldName;
    private static String openSansBoldName;

    static {
        try {
            bebasNeueName          = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/enzo/fonts/bebasneue.otf"), 10).getName();
            digitalName            = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/enzo/fonts/digital.ttf"), 10).getName();
            digitalReadoutName     = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/enzo/fonts/digitalreadout.ttf"), 10).getName();
            digitalReadoutBoldName = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/enzo/fonts/digitalreadoutb.ttf"), 10).getName();
            dinFunName             = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/enzo/fonts/din.otf"), 10).getName();
            droidSansMonoName      = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/enzo/fonts/droidsansmono.ttf"), 10).getName();
            elektraName            = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/enzo/fonts/elektra.ttf"), 10).getName();
            openSansLightName      = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/enzo/fonts/opensans-light.ttf"), 10).getName();
            openSansSemiBoldName   = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/enzo/fonts/opensans-semibold.ttf"), 10).getName();
            openSansBoldName       = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/enzo/fonts/opensans-bold.ttf"), 10).getName();
        } catch (Exception exception) { }
        BEBAS_NEUE_NAME           = bebasNeueName;
        DIGITAL_NAME              = digitalName;
        DIGITAL_READOUT_NAME      = digitalReadoutName;
        DIGITAL_READOUT_BOLD_NAME = digitalReadoutBoldName;
        DIN_FUN_NAME              = dinFunName;
        DROID_SANS_MONO_NAME      = droidSansMonoName;
        ELEKTRA_NAME              = elektraName;
        OPENSANS_LIGHT_NAME       = openSansLightName;
        OPENSANS_SEMI_BOLD_NAME   = openSansSemiBoldName;
        OPENSANS_BOLD_NAME        = openSansBoldName;
    }

    
    // ******************** Methods *******************************************
    public static Font bebasNeue(final double SIZE) {
        return new Font(BEBAS_NEUE_NAME, SIZE);
    }

    public static Font digital(final double SIZE) {
        return new Font(DIGITAL_NAME, SIZE);
    }

    public static Font digitalReadout(final double SIZE) {
        return new Font(DIGITAL_READOUT_NAME, SIZE);
    }

    public static Font digitalReadoutBold(final double SIZE) {
        return new Font(DIGITAL_READOUT_BOLD_NAME, SIZE);
    }

    public static Font dinFun(final double SIZE) {
        return new Font(DIN_FUN_NAME, SIZE);
    }

    public static Font droidSansMono(final double SIZE) {
        return new Font(DROID_SANS_MONO_NAME, SIZE);
    }

    public static Font elektra(final double SIZE) {
        return new Font(ELEKTRA_NAME, SIZE);
    }

    public static Font opensansLight(final double SIZE) {
        return new Font(OPENSANS_LIGHT_NAME, SIZE);
    }
    
    public static Font opensansSemiBold(final double SIZE) {
        return new Font(OPENSANS_SEMI_BOLD_NAME, SIZE);
    }

    public static Font opensansBold(final double SIZE) {
        return new Font(OPENSANS_BOLD_NAME, SIZE);
    }
}
