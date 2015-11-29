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
package eu.langhammer.enzo2.common;

import java.util.Objects;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Utilities for manipulating colors etc.
 * <p>
 * ~~ HAN SOLO ~~ Created by User: hansolo Date: 29.04.13 Time: 12:59 ~~ END ~~
 *
 *
 * @author Gerrit Grunwald (creator)
 * @author Tim Langhammer (editor)
 */
public final class Util {

    private Util() {
        throw new AssertionError();
    }

    /**
     *
     * @param width
     * @param HEIGHT
     * @param color
     * @param noise
     * @return
     */
    public static Image createGrayNoise( final double width,
                                         final double HEIGHT,
                                         final Color color,
                                         final int noise ) {

        Objects.requireNonNull( color, "You have to specify a color" );

        int red = ( int ) ( 255 * color.getRed() );
        int green = ( int ) ( 255 * color.getRed() );
        int blue = ( int ) ( 255 * color.getRed() );
        int variation = clamp( 0, 255, noise ) / 2;
        Color darkColor = Color.rgb( clamp( 0, 255, red - variation ),
                                     clamp( 0, 255, green - variation ),
                                     clamp( 0, 255, blue - variation ) );
        Color brightColor = Color.rgb( clamp( 0, 255, red + variation ),
                                       clamp( 0, 255, green + variation ),
                                       clamp( 0, 255, blue + variation ) );
        return createGrayNoise( width, HEIGHT, darkColor, brightColor );
    }

    /**
     *
     * @param width
     * @param height
     * @param darkColor
     * @param brightColor
     * @return
     */
    public static Image createGrayNoise( final double width,
                                         final double height,
                                         final Color darkColor,
                                         final Color brightColor ) {
        //
        if ( width <= 0 || height <= 0 ) {
            throw new IllegalArgumentException( "The width and height have to be > 0;" );
        }
        //
        Objects.requireNonNull( darkColor, "The dark color is null" );
        Objects.requireNonNull( brightColor, "The bright color is null" );

        final WritableImage IMAGE = new WritableImage( ( int ) width, ( int ) height );
        final PixelWriter PIXEL_WRITER = IMAGE.getPixelWriter();
        final Random RND = new Random();

        double redDark = darkColor.getRed();
        double greenDark = darkColor.getGreen();
        double blueDark = darkColor.getBlue();

        double redBright = darkColor.getRed();
        double greenBright = darkColor.getGreen();
        double blueBright = darkColor.getBlue();

        int startRed = ( int ) ( Math.min( redDark, redBright ) * 255 );
        int startGreen = ( int ) ( Math.min( greenDark, greenBright ) * 255 );
        int startBlue = ( int ) ( Math.min( blueDark, blueBright ) * 255 );
        int start = returnLargest( startRed, startGreen, startBlue );

        int deltaRed = Math.abs( ( int ) ( ( brightColor.getRed() - darkColor.getRed() ) * 255 ) );
        int deltaGreen = Math.abs( ( int ) ( ( brightColor.getGreen() - darkColor.getGreen() ) * 255 ) );
        int deltaBlue = Math.abs( ( int ) ( ( brightColor.getBlue() - darkColor.getBlue() ) * 255 ) );
        int delta = returnLargest( deltaRed, deltaGreen, deltaBlue );

        int gray;

        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {
                gray = delta > 0 ? start + RND.nextInt( delta ) : start;
                PIXEL_WRITER.setColor( x, y, Color.rgb(
                                       clamp( 0, 255, gray ),
                                       clamp( 0, 255, gray ),
                                       clamp( 0, 255, gray )
                               ) );
            }
        }

        return IMAGE;
    }

    public static String colorToCss( final Color COLOR ) {
        Objects.requireNonNull( COLOR, "You have to specify a color" );
        return COLOR.toString().replace( "0x", "#" );
    }

    /**
     * Clam {@code value} between {@code min} and {@code max}.
     *
     * @param min
     * @param max
     * @param value
     * @return
     */
    public static int clamp( final int min,
                             final int max,
                             final int value ) {
        int ret = value < min ? min
                : value > max ? max
                        : value;
        return ret;
    }

    /**
     *
     * @param min
     * @param max
     * @param value
     * @return
     */
    public static long clamp( final long min,
                              final long max,
                              final long value ) {
        long ret = value < min ? min
                : value > max ? max
                        : value;

        return ret;
    }

    public static float clamp( final float min,
                               final float max,
                               final float value ) {
        float ret = value < min ? min
                : value > max ? max
                        : value;

        return ret;
    }

    /**
     *
     * @param min
     * @param max
     * @param value
     * @return
     */
    public static double clamp( final double min,
                                final double max,
                                final double value ) {
        double ret = value > max ? max
                : value < min ? min
                        : value;
        System.out.println( "Clamp von '" + value + "' ergibt : " + ret );
        return ret;
    }

    /**
     * Return the largest value from the three.
     *
     * @param A
     * @param B
     * @param C
     * @return
     */
    private static int returnLargest( final int A,
                                      final int B,
                                      final int C ) {

        return Math.max( Math.max( A, B ), C );

    }

    /**
     * Calculates the number of Nodes contained in a hierarchy of nodes.
     *
     * @param rootNode The root node
     * @return
     */
    public static int calcNoOfNodes( final Node rootNode ) {
        Objects.requireNonNull( rootNode, "You have to specify a non null root node" );

        int ret = 0;
        // Parent anchestor
        if ( rootNode instanceof Parent ) {
            // with childs > 0
            if ( !( ( Parent ) rootNode ).getChildrenUnmodifiable().isEmpty() ) {
                ObservableList<Node> tempChildren = ( ( Parent ) rootNode ).getChildrenUnmodifiable();
                ret += tempChildren.size();
                for ( Node n : tempChildren ) {
                    calcNoOfNodes( n );
                    //System.out.println(n.getStyleClass().toString());
                }
            }
        }

        return ret;
    }
}
