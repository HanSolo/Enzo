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

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javafx.geometry.Bounds;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import static eu.langhammer.enzo2.common.ShapeConverter.Command.*;

/**
 * A utils class to transform all kinds of {@link javafx.scene.shape.Shape} to
 * {@link String}'s and pure SVG data to {@link javafx.scene.shape.Path}
 * objects.
 * <p>
 * ~~ HANSOLO ~~ Created by User: hansolo Date: 02.04.13 Time: 12:49 ~~ END ~~
 *
 * @author Gerrit Grunwald (creator)
 * @author Tim Langhammer (editor)
 */
public final class ShapeConverter {

    private static final double KAPPA = 0.5522847498307935D;

    /**
     * Utilities class have a private constructor.
     */
    private ShapeConverter() {
        throw new AssertionError();
    }

    enum Command {

        MOVE_TO( "M" ),
        LINE_TO( "L" ),
        CUBIC_CURVE_TO( "C" ),
        QUAD_CURVE_TO( "Q" ),
        ARC_TO( "A" ),
        H_LINE_TO( "H" ),
        V_LINE_TO( "V" ),
        FINISH( "Z" );

        private final String cmd;

        private Command( final String commandStr ) {
            this.cmd = commandStr;
        }

        @Override
        public String toString() {
            return cmd;
        }

        static Command parse( final String tokenStr ) {
            Objects.requireNonNull( tokenStr, "The token is null!" );
            for ( final Command c : values() ) {

                if ( c.cmd.equalsIgnoreCase( tokenStr.trim() ) ) {
                    return c;
                }
            }
            return null;
        }
    }

    /**
     *
     * @param shape
     *
     * @return
     */
    public static String shapeToSvgString( final Shape shape ) {
        Objects.requireNonNull( shape, "The shape must non null" );

        final StringBuilder fxPathSB = new StringBuilder();

        final Class<?> cls = shape.getClass();

        if ( Line.class.equals( cls ) ) {
            fxPathSB.append( convertLine( ( Line ) shape ) );
        }
        else if ( Arc.class.equals( cls ) ) {
            fxPathSB.append( convertArc( ( Arc ) shape ) );
        }
        else if ( QuadCurve.class.equals( cls ) ) {
            fxPathSB.append( convertQuadCurve( ( QuadCurve ) shape ) );
        }
        else if ( CubicCurve.class.equals( cls ) ) {
            fxPathSB.append( convertCubicCurve( ( CubicCurve ) shape ) );
        }
        else if ( Rectangle.class.equals( cls ) ) {
            fxPathSB.append( convertRectangle( ( Rectangle ) shape ) );
        }
        else if ( Circle.class.equals( cls ) ) {
            fxPathSB.append( convertCircle( ( Circle ) shape ) );
        }
        else if ( Ellipse.class.equals( cls ) ) {
            fxPathSB.append( convertEllipse( ( Ellipse ) shape ) );
        }
        else if ( Text.class.equals( cls ) ) {
            Path path = ( Path ) ( Shape.subtract( shape, new Rectangle( 0, 0 ) ) );
            fxPathSB.append( convertPath( path ) );
        }
        else if ( Path.class.equals( cls ) ) {
            fxPathSB.append( convertPath( ( Path ) shape ) );
        }
        else if ( Polygon.class.equals( cls ) ) {
            fxPathSB.append( convertPolygon( ( Polygon ) shape ) );
        }
        else if ( Polyline.class.equals( cls ) ) {
            fxPathSB.append( convertPolyline( ( Polyline ) shape ) );
        }
        else if ( SVGPath.class.equals( cls ) ) {
            fxPathSB.append( ( ( SVGPath ) shape ).getContent() );
        }
        // unsupported class
        else {
            throw new IllegalArgumentException( "The class '" + cls + "' is not supported!" );
        }
        return fxPathSB.toString();
    }

    /**
     *
     * @param shape
     *
     * @return
     */
    public static SVGPath shapeToSvgPath( final Shape shape ) {
        Objects.requireNonNull( shape, "The shape must be non null" );

        SVGPath svgPath = new SVGPath();
        svgPath.setContent( shapeToSvgString( shape ) );
        return svgPath;
    }

    /**
     *
     * @param svgPath
     *
     * @return
     */
    public static Path svgPathToPath( final SVGPath svgPath ) {
        Objects.requireNonNull( svgPath, "The SVG path must be non null" );

        return SVGPathReader.instance( svgPath, null ).buildPath();
    }

    /**
     * Transform a {@link Line} to a {@code String}.
     *
     * @param line The line to transform
     * @return
     */
    public static String convertLine( final Line line ) {
        Objects.requireNonNull( line, "The line must be non null!" );

        final StringBuilder fxPath = new StringBuilder();
        fxPath.append( MOVE_TO )
                .append( " " )
                .append( line.getStartX() )
                .append( " " )
                .append( line.getStartY() )
                .append( " " )
                .append( LINE_TO )
                .append( " " )
                .append( line.getEndX() )
                .append( " " )
                .append( line.getEndY() );
        return fxPath.toString();
    }

    public static String convertArc( final Arc arc ) {
        Objects.requireNonNull( arc, "The arc must be non null!" );

        StringBuilder fxPath = new StringBuilder();

        double centerX = arc.getCenterX();
        double centerY = arc.getCenterY();
        double radiusX = arc.getRadiusX();
        double radiusY = arc.getRadiusY();
        double startAngle = Math.toRadians( -arc.getStartAngle() );
        double endAngle = Math.toRadians( -arc.getStartAngle() - arc.getLength() );
        double length = arc.getLength();

        double startX = radiusX * Math.cos( startAngle );
        double startY = radiusY * Math.sin( startAngle );

        double endX = centerX + radiusX * Math.cos( endAngle );
        double endY = centerY + radiusY * Math.sin( endAngle );

        int xAxisRot = 0;
        int largeArc = ( length > 180 ) ? 1 : 0;
        int sweep = ( length < 0 ) ? 1 : 0;

        fxPath.append( "M " ).append( centerX ).append( " " ).append( centerY ).append( " " );

        if ( ArcType.ROUND == arc.getType() ) {
            fxPath.append( "l " ).append( startX ).append( " " ).append( startY ).append( " " );
        }

        fxPath.append( "A " ).append( radiusX ).append( " " ).append( radiusY ).append( " " )
                .append( xAxisRot ).append( " " ).append( largeArc ).append( " " ).append( sweep ).append( " " )
                .append( endX ).append( " " ).append( endY ).append( " " );
        if ( ArcType.CHORD == arc.getType() || ArcType.ROUND == arc.getType() ) {
            fxPath.append( "Z" );
        }
        return fxPath.toString();
    }

    public static String convertQuadCurve( final QuadCurve quadCurve ) {
        Objects.requireNonNull( quadCurve, "The quad curve path must be non null" );

        final StringBuilder fxPath = new StringBuilder();

        fxPath.append( "M " ).append( quadCurve.getStartX() ).append( " " ).append( quadCurve.getStartY() ).append( " " )
                .append( "Q " ).append( quadCurve.getControlX() ).append( " " ).append( quadCurve.getControlY() )
                .append( quadCurve.getEndX() ).append( " " ).append( quadCurve.getEndY() );
        return fxPath.toString();
    }

    public static String convertCubicCurve( final CubicCurve cubicCurve ) {
        Objects.requireNonNull( cubicCurve, "The cubic curve path must be non null" );

        final StringBuilder fxPath = new StringBuilder();

        fxPath.append( "M " ).append( cubicCurve.getStartX() ).append( " " ).append( cubicCurve.getStartY() ).append( " " )
                .append( "C " ).append( cubicCurve.getControlX1() ).append( " " ).append( cubicCurve.getControlY1() ).append( " " )
                .append( cubicCurve.getControlX2() ).append( " " ).append( cubicCurve.getControlY2() ).append( " " )
                .append( cubicCurve.getEndX() ).append( " " ).append( cubicCurve.getEndY() );
        return fxPath.toString();
    }

    public static String convertRectangle( final Rectangle rectangle ) {
        Objects.requireNonNull( rectangle, "The rectangle must be non null" );

        final StringBuilder fxPath = new StringBuilder();

        final Bounds bounds = rectangle.getBoundsInLocal();

        if ( Double.compare( rectangle.getArcWidth(), 0.0D ) == 0 && Double.compare( rectangle.getArcHeight(), 0.0D ) == 0 ) {
            fxPath.append( "M " ).append( bounds.getMinX() ).append( " " ).append( bounds.getMinY() ).append( " " )
                    .append( "H " ).append( bounds.getMaxX() ).append( " " )
                    .append( "V " ).append( bounds.getMaxY() ).append( " " )
                    .append( "H " ).append( bounds.getMinX() ).append( " " )
                    .append( "V " ).append( bounds.getMinY() ).append( " " )
                    .append( "Z" );
        }
        else {
            double x = bounds.getMinX();
            double y = bounds.getMinY();
            double width = bounds.getWidth();
            double height = bounds.getHeight();
            double arcWidth = rectangle.getArcWidth();
            double arcHeight = rectangle.getArcHeight();
            double r = x + width;
            double b = y + height;
            fxPath.append( "M " ).append( x + arcWidth ).append( " " ).append( y ).append( " " )
                    .append( "L " ).append( r - arcWidth ).append( " " ).append( y ).append( " " )
                    .append( "Q " ).append( r ).append( " " ).append( y ).append( " " ).append( r ).append( " " ).append( y + arcHeight ).append( " " )
                    .append( "L " ).append( r ).append( " " ).append( y + height - arcHeight ).append( " " )
                    .append( "Q " ).append( r ).append( " " ).append( b ).append( " " ).append( r - arcWidth ).append( " " ).append( b ).append( " " )
                    .append( "L " ).append( x + arcWidth ).append( " " ).append( b ).append( " " )
                    .append( "Q " ).append( x ).append( " " ).append( b ).append( " " ).append( x ).append( " " ).append( b - arcHeight ).append( " " )
                    .append( "L " ).append( x ).append( " " ).append( y + arcHeight ).append( " " )
                    .append( "Q " ).append( x ).append( " " ).append( y ).append( " " ).append( x + arcWidth ).append( " " ).append( y ).append( " " )
                    .append( "Z" );
        }
        return fxPath.toString();
    }

    public static String convertCircle( final Circle CIRCLE ) {
        final StringBuilder fxPath = new StringBuilder();
        final double CENTER_X = CIRCLE.getCenterX() == 0 ? CIRCLE.getRadius() : CIRCLE.getCenterX();
        final double CENTER_Y = CIRCLE.getCenterY() == 0 ? CIRCLE.getRadius() : CIRCLE.getCenterY();
        final double RADIUS = CIRCLE.getRadius();
        final double CONTROL_DISTANCE = RADIUS * KAPPA;
        // Move to first point
        fxPath.append( "M " ).append( CENTER_X ).append( " " ).append( CENTER_Y - RADIUS ).append( " " );
        // 1. quadrant
        fxPath.append( "C " ).append( CENTER_X + CONTROL_DISTANCE ).append( " " ).append( CENTER_Y - RADIUS ).append( " " )
                .append( CENTER_X + RADIUS ).append( " " ).append( CENTER_Y - CONTROL_DISTANCE ).append( " " )
                .append( CENTER_X + RADIUS ).append( " " ).append( CENTER_Y ).append( " " );
        // 2. quadrant
        fxPath.append( "C " ).append( CENTER_X + RADIUS ).append( " " ).append( CENTER_Y + CONTROL_DISTANCE ).append( " " )
                .append( CENTER_X + CONTROL_DISTANCE ).append( " " ).append( CENTER_Y + RADIUS ).append( " " )
                .append( CENTER_X ).append( " " ).append( CENTER_Y + RADIUS ).append( " " );
        // 3. quadrant
        fxPath.append( "C " ).append( CENTER_X - CONTROL_DISTANCE ).append( " " ).append( CENTER_Y + RADIUS ).append( " " )
                .append( CENTER_X - RADIUS ).append( " " ).append( CENTER_Y + CONTROL_DISTANCE ).append( " " )
                .append( CENTER_X - RADIUS ).append( " " ).append( CENTER_Y ).append( " " );
        // 4. quadrant
        fxPath.append( "C " ).append( CENTER_X - RADIUS ).append( " " ).append( CENTER_Y - CONTROL_DISTANCE ).append( " " )
                .append( CENTER_X - CONTROL_DISTANCE ).append( " " ).append( CENTER_Y - RADIUS ).append( " " )
                .append( CENTER_X ).append( " " ).append( CENTER_Y - RADIUS ).append( " " );
        // Close path
        fxPath.append( "Z" );
        return fxPath.toString();
    }

    public static String convertEllipse( final Ellipse ELLIPSE ) {
        final StringBuilder fxPath = new StringBuilder();
        final double CENTER_X = ELLIPSE.getCenterX() == 0 ? ELLIPSE.getRadiusX() : ELLIPSE.getCenterX();
        final double CENTER_Y = ELLIPSE.getCenterY() == 0 ? ELLIPSE.getRadiusY() : ELLIPSE.getCenterY();
        final double RADIUS_X = ELLIPSE.getRadiusX();
        final double RADIUS_Y = ELLIPSE.getRadiusY();
        final double CONTROL_DISTANCE_X = RADIUS_X * KAPPA;
        final double CONTROL_DISTANCE_Y = RADIUS_Y * KAPPA;
        // Move to first point
        fxPath.append( "M " ).append( CENTER_X ).append( " " ).append( CENTER_Y - RADIUS_Y ).append( " " );
        // 1. quadrant
        fxPath.append( "C " ).append( CENTER_X + CONTROL_DISTANCE_X ).append( " " ).append( CENTER_Y - RADIUS_Y ).append( " " )
                .append( CENTER_X + RADIUS_X ).append( " " ).append( CENTER_Y - CONTROL_DISTANCE_Y ).append( " " )
                .append( CENTER_X + RADIUS_X ).append( " " ).append( CENTER_Y ).append( " " );
        // 2. quadrant
        fxPath.append( "C " ).append( CENTER_X + RADIUS_X ).append( " " ).append( CENTER_Y + CONTROL_DISTANCE_Y ).append( " " )
                .append( CENTER_X + CONTROL_DISTANCE_X ).append( " " ).append( CENTER_Y + RADIUS_Y ).append( " " )
                .append( CENTER_X ).append( " " ).append( CENTER_Y + RADIUS_Y ).append( " " );
        // 3. quadrant
        fxPath.append( "C " ).append( CENTER_X - CONTROL_DISTANCE_X ).append( " " ).append( CENTER_Y + RADIUS_Y ).append( " " )
                .append( CENTER_X - RADIUS_X ).append( " " ).append( CENTER_Y + CONTROL_DISTANCE_Y ).append( " " )
                .append( CENTER_X - RADIUS_X ).append( " " ).append( CENTER_Y ).append( " " );
        // 4. quadrant
        fxPath.append( "C " ).append( CENTER_X - RADIUS_X ).append( " " ).append( CENTER_Y - CONTROL_DISTANCE_Y ).append( " " )
                .append( CENTER_X - CONTROL_DISTANCE_X ).append( " " ).append( CENTER_Y - RADIUS_Y ).append( " " )
                .append( CENTER_X ).append( " " ).append( CENTER_Y - RADIUS_Y ).append( " " );
        // Close path
        fxPath.append( "Z" );
        return fxPath.toString();
    }

    public static String convertPath( final Path PATH ) {

        final StringBuilder fxPath = new StringBuilder();

        for ( PathElement element : PATH.getElements() ) {
            if ( MoveTo.class.equals( element.getClass() ) ) {
                fxPath.append( "M " )
                        .append( ( ( MoveTo ) element ).getX() ).append( " " )
                        .append( ( ( MoveTo ) element ).getY() ).append( " " );
            }
            else if ( LineTo.class.equals( element.getClass() ) ) {
                fxPath.append( "L " )
                        .append( ( ( LineTo ) element ).getX() ).append( " " )
                        .append( ( ( LineTo ) element ).getY() ).append( " " );
            }
            else if ( CubicCurveTo.class.equals( element.getClass() ) ) {
                fxPath.append( "C " )
                        .append( ( ( CubicCurveTo ) element ).getControlX1() ).append( " " )
                        .append( ( ( CubicCurveTo ) element ).getControlY1() ).append( " " )
                        .append( ( ( CubicCurveTo ) element ).getControlX2() ).append( " " )
                        .append( ( ( CubicCurveTo ) element ).getControlY2() ).append( " " )
                        .append( ( ( CubicCurveTo ) element ).getX() ).append( " " )
                        .append( ( ( CubicCurveTo ) element ).getY() ).append( " " );
            }
            else if ( QuadCurveTo.class.equals( element.getClass() ) ) {
                fxPath.append( "Q " )
                        .append( ( ( QuadCurveTo ) element ).getControlX() ).append( " " )
                        .append( ( ( QuadCurveTo ) element ).getControlY() ).append( " " )
                        .append( ( ( QuadCurveTo ) element ).getX() ).append( " " )
                        .append( ( ( QuadCurveTo ) element ).getY() ).append( " " );
            }
            else if ( ArcTo.class.equals( element.getClass() ) ) {
                fxPath.append( "A " )
                        .append( ( ( ArcTo ) element ).getX() ).append( " " )
                        .append( ( ( ArcTo ) element ).getY() ).append( " " )
                        .append( ( ( ArcTo ) element ).getRadiusX() ).append( " " )
                        .append( ( ( ArcTo ) element ).getRadiusY() ).append( " " );
            }
            else if ( HLineTo.class.equals( element.getClass() ) ) {
                fxPath.append( "H " )
                        .append( ( ( HLineTo ) element ).getX() ).append( " " );
            }
            else if ( VLineTo.class.equals( element.getClass() ) ) {
                fxPath.append( "V " )
                        .append( ( ( VLineTo ) element ).getY() ).append( " " );
            }
            else if ( ClosePath.class.equals( element.getClass() ) ) {
                fxPath.append( "Z" );
            }
        }
        return fxPath.toString();
    }

    public static String convertPolygon( final Polygon POLYGON ) {
        final StringBuilder fxPath = new StringBuilder();
        final int size = POLYGON.getPoints().size();
        if ( size % 2 == 0 ) {
            List<Double> coordinates = POLYGON.getPoints();
            for ( int i = 0; i < size; i += 2 ) {
                fxPath.append( i == 0 ? "M " : "L " )
                        .append( coordinates.get( i ) ).append( " " ).append( coordinates.get( i + 1 ) ).append( " " );
            }
            fxPath.append( "Z" );
        }
        return fxPath.toString();
    }

    public static String convertPolyline( final Polyline POLYLINE ) {
        final StringBuilder fxPath = new StringBuilder();
        final int size = POLYLINE.getPoints().size();
        if ( size % 2 == 0 ) {
            List<Double> coordinates = POLYLINE.getPoints();
            for ( int i = 0; i < size; i += 2 ) {
                fxPath.append( i == 0 ? "M " : "L " )
                        .append( coordinates.get( i ) ).append( " " ).append( coordinates.get( i + 1 ) ).append( " " );
            }
        }
        return fxPath.toString();
    }

    /**
     * A read only list filled with tokens to create a {@link Path}. TODO: teste
     * wenn mehrere th. einen reader anfordern.
     */
    private static final class SVGPathReader {

        private String svgData;
        private Scanner scan;
        private FillRule fillRule;

        private SVGPathReader() {
            //
        }

        static SVGPathReader instance( final SVGPath svgPath, final FillRule fillRule ) {
            Objects.requireNonNull( svgPath, "The svg data is null!" );

            SVGPathReader svgPR = new SVGPathReader();
            svgPR.init( svgPath );
            svgPR.fillRule = null == fillRule ? FillRule.EVEN_ODD : fillRule;

            return svgPR;
        }

        private void init( final SVGPath svgPath ) {

            String svgPathData = svgPath.getContent();
            // remove commas
            this.svgData = svgPathData.replaceAll( "/(,)/", "/ /" );
            // wrap single characters in blanks
            this.svgData = svgPathData.replaceAll( "/([A-Za-z])/", "/ $1 /" );
            this.scan = new Scanner( svgData );
            this.scan.useDelimiter( " " );
        }

        /**
         * Parse the SVG data, building the {@code Path}.
         *
         * @return
         */
        javafx.scene.shape.Path buildPath() {
            final javafx.scene.shape.Path path = new Path();
            path.setFillRule( fillRule );
            // temp token
            String token = null;
            // temp path element
            PathElement pathElement = null;

            while ( scan.hasNext() ) {
                token = scan.next();
                System.out.println( "Token bei SVG Reader : " + token );
                // try to parse the token to a command
                Command cmd = Command.parse( token );
                // the command is not known or malformed
                if ( cmd == null ) {
                    path.getElements().add( new ClosePath() );
                    System.err.println( "Unknown token '" + token + "' close Path" );
                    scan.close();
                    break;
                }

                switch ( cmd ) {
                    // Move 
                    case MOVE_TO:
                        // advance the next two tokens and try to read them
                        // as double
                        double mX = scan.nextDouble();
                        double mY = scan.nextDouble();

                        pathElement = new MoveTo( mX, mY );
                        break;
                    // Line 
                    case LINE_TO:
                        double lX = scan.nextDouble();
                        double lY = scan.nextDouble();
                        pathElement = new LineTo( lX, lY );

                        break;
                    // Cubic Curve
                    case CUBIC_CURVE_TO:
                        // parse the four control points and the x and y coordinates
                        double control1 = scan.nextDouble();
                        double control2 = scan.nextDouble();
                        double control3 = scan.nextDouble();
                        double control4 = scan.nextDouble();
                        double cX = scan.nextDouble();
                        double cY = scan.nextDouble();

                        pathElement = new CubicCurveTo( control1, control2, control3, control4, cX, cY );
                        break;
                    // Quad Curve
                    case QUAD_CURVE_TO:
                        // parse the two control points and the x and y coordinates
                        double ctrX = scan.nextDouble();
                        double ctrY = scan.nextDouble();
                        double qX = scan.nextDouble();
                        double qY = scan.nextDouble();
                        pathElement = new QuadCurveTo( ctrX, ctrY, qX, qY );
                        break;
                    // Horizontal line
                    case H_LINE_TO:
                        double x = scan.nextDouble();
                        pathElement = new HLineTo( x );
                        break;
                    // 
                    case V_LINE_TO:

                        double y = scan.nextDouble();
                        pathElement = new VLineTo( y );

                        break;
                    // Arc
                    case ARC_TO:
                        // 
                        double radX = scan.nextDouble();
                        double radY = scan.nextDouble();
                        double rotationX = scan.nextDouble();
                        double arcX = scan.nextDouble();
                        double arcY = scan.nextDouble();
                        // optional not contained in SVG path data
                        boolean largeArc = false;
                        boolean sweepFlag = false;
                        pathElement = new ArcTo( radX, radY, rotationX, arcX, arcY, largeArc, sweepFlag );
                        break;
                    // Close Path
                    case FINISH:
                        //
                        pathElement = new ClosePath();
                        break;
                    default:
                        throw new IllegalArgumentException( "Unknown SVG token '" + token + "'" );
                }
                // add the actual path element to the path
                path.getElements().add( pathElement );
            }

            return path;
        }

        /**
         * Secure way to cast a String to a double.
         *
         * @param tokenStr The token to cast to a double
         * @return the {@code double} value of {@code tokenStr} if parseable
         * @throws
         */
        private double readDouble( final String tokenStr ) {
            double x = Double.NaN;

            try {
                x = Double.parseDouble( tokenStr );
            }
            catch ( final NumberFormatException nfE ) {

                throw new IllegalStateException( "The token '" + tokenStr + "' is not a number!" );
            }

            return x;
        }

    }
}
