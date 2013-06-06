/*
 * Copyright (c) 2013. Gerrit Grunwald
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.hansolo.enzo.common;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Random;


/**
 * Created by
 * User: hansolo
 * Date: 29.04.13
 * Time: 12:59
 */
public class Util {

    public static Image createGrayNoise(final double WIDTH, final double HEIGHT, final Color COLOR, final int VARIATION) {
        int red   = (int) (255 * COLOR.getRed());
        int green = (int) (255 * COLOR.getRed());
        int blue  = (int) (255 * COLOR.getRed());
        int variation = clamp(0, 255, VARIATION) / 2;
        Color darkColor   = Color.rgb(clamp(0, 255, red - variation), clamp(0, 255, green - variation), clamp(0, 255, blue - variation));
        Color brightColor = Color.rgb(clamp(0, 255, red + variation), clamp(0, 255, green + variation), clamp(0, 255, blue + variation));
        return createGrayNoise(WIDTH, HEIGHT, darkColor, brightColor);
    }

    public static Image createGrayNoise(final double WIDTH, final double HEIGHT, final Color DARK_COLOR, final Color BRIGHT_COLOR) {
        if (WIDTH <= 0 || HEIGHT <= 0) {
            return null;
        }
        final WritableImage IMAGE      = new WritableImage((int) WIDTH, (int) HEIGHT);
        final PixelWriter PIXEL_WRITER = IMAGE.getPixelWriter();
        final Random RND = new Random();

        double redDark   = DARK_COLOR.getRed();
        double greenDark = DARK_COLOR.getGreen();
        double blueDark  = DARK_COLOR.getBlue();

        double redBright   = DARK_COLOR.getRed();
        double greenBright = DARK_COLOR.getGreen();
        double blueBright  = DARK_COLOR.getBlue();

        int startRed   = (int) (Math.min(redDark, redBright) * 255);
        int startGreen = (int) (Math.min(greenDark, greenBright) * 255);
        int startBlue  = (int) (Math.min(blueDark, blueBright) * 255);
        int start = returnLargest(startRed, startGreen, startBlue);

        int deltaRed   = Math.abs((int) ((BRIGHT_COLOR.getRed() - DARK_COLOR.getRed()) * 255));
        int deltaGreen = Math.abs((int) ((BRIGHT_COLOR.getGreen() - DARK_COLOR.getGreen()) * 255));
        int deltaBlue  = Math.abs((int) ((BRIGHT_COLOR.getBlue() - DARK_COLOR.getBlue()) * 255));
        int delta = returnLargest(deltaRed, deltaGreen, deltaBlue);

        int gray;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                gray = delta > 0 ? start + RND.nextInt(delta) : start;
                PIXEL_WRITER.setColor(x, y, Color.rgb(clamp(0, 255, gray), clamp(0, 255, gray), clamp(0, 255, gray)));
            }
        }
        return IMAGE;
    }

    public static String colorToCss(final Color COLOR) {
        StringBuilder cssColor = new StringBuilder();
        cssColor.append("rgba(")
                .append((int) (COLOR.getRed() * 255)).append(", ")
                .append((int) (COLOR.getGreen() * 255)).append(", ")
                .append((int) (COLOR.getBlue() * 255)).append(", ")
                .append(COLOR.getOpacity()).append(");");
        return cssColor.toString();
    }

    private static int clamp(final int MIN, final int MAX, final int VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }

    private static int returnLargest(final int A, final int B, final int C) {
        if (A == B && A == C) {
            return A;
        } else if (A > B && A > C) {
            return A;
        } else if (B > A && B > C) {
            return B;
        } else if (C > A && C > B) {
            return C;
        }
        return 0;
    }
}
