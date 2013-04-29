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

package eu.hansolo.enzo.gauge;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;

import java.text.DecimalFormat;
import java.util.Locale;


/**
 * Created by
 * User: hansolo
 * Date: 15.11.12
 * Time: 00:53
 */

public class Radial extends Control {
    public static enum NeedleType {
        STANDARD,
        SCIENTIFIC,
        ARROW,
        BIG
    }
    public static enum TickLabelOrientation {
        NORMAL,
        HORIZONTAL,
        TANGENT
    }
    public static enum NumberFormat {
        AUTO("0"),
        STANDARD("0"),
        FRACTIONAL("0.0#"),
        SCIENTIFIC("0.##E0"),
        PERCENTAGE("##0.0%");

        private final DecimalFormat DF;

        private NumberFormat(final String FORMAT_STRING) {
            Locale.setDefault(new Locale("en", "US"));

            DF = new DecimalFormat(FORMAT_STRING);
        }

        public String format(final Number NUMBER) {
            return DF.format(NUMBER);
        }
    }
    public static final String STYLE_CLASS_NEEDLE_STANDARD   = "needle-standard";
    public static final String STYLE_CLASS_NEEDLE_ARROW      = "needle-arrow";
    public static final String STYLE_CLASS_NEEDLE_SCIENTIFIC = "needle-scientific";
    public static final String STYLE_CLASS_NEEDLE_BIG        = "needle-big";

    private IntegerProperty                      angleRange;
    private IntegerProperty                      rotationOffset;
    private DoubleProperty                       value;
    private DoubleProperty                       minValue;
    private DoubleProperty                       maxValue;
    private StringProperty                       title;
    private ObjectProperty<NeedleType>           needleType;
    private BooleanProperty                      animated;
    private ObjectProperty<TickLabelOrientation> tickLabelOrientation;
    private ObjectProperty<NumberFormat>         numberFormat;
    private BooleanProperty                      tickLabelsVisible;


    // ******************** Constructors **************************************
    public Radial() {
        getStyleClass().setAll("radial");
        angleRange           = new SimpleIntegerProperty(300);
        rotationOffset       = new SimpleIntegerProperty(150);
        value                = new SimpleDoubleProperty(0);
        minValue             = new SimpleDoubleProperty(0);
        maxValue             = new SimpleDoubleProperty(100);
        title                = new SimpleStringProperty("");
        needleType           = new SimpleObjectProperty<>(NeedleType.STANDARD);
        animated             = new SimpleBooleanProperty(false);
        tickLabelOrientation = new SimpleObjectProperty<>(TickLabelOrientation.NORMAL);
        numberFormat         = new SimpleObjectProperty<>(NumberFormat.STANDARD);
        tickLabelsVisible    = new SimpleBooleanProperty(true);
    }


    // ******************** Methods *******************************************
    public final int getAngleRange() {
        return angleRange.get();
    }
    public final void setAngleRange(final int ANGLE_RANGE) {
        angleRange.set(ANGLE_RANGE);
    }
    public final ReadOnlyIntegerProperty angleRangeProperty() {
        return angleRange;
    }

    public final int getRotationOffset() {
        return rotationOffset.get();
    }
    public final void setRotationOffset(final int ROTATION_OFFSET) {
        rotationOffset.set((int) clamp(0, 180, ROTATION_OFFSET));
    }
    public final ReadOnlyIntegerProperty rotationOffsetProperty() {
        return rotationOffset;
    }

    public final double getValue() {
        return value.get();
    }
    public final void setValue(final double VALUE) {
        value.set(clamp(getMinValue(), getMaxValue(), VALUE));
    }
    public final DoubleProperty valueProperty() {
        return value;
    }

    public final double getMinValue() {
        return minValue.get();
    }
    public final void setMinValue(final double MIN_VALUE) {
        minValue.set(MIN_VALUE);
    }
    public final DoubleProperty minValueProperty() {
        return minValue;
    }

    public final double getMaxValue() {
        return maxValue.get();
    }
    public final void setMaxValue(final double MAX_VALUE) {
        maxValue.set(MAX_VALUE);
    }
    public final DoubleProperty maxValueProperty() {
        return maxValue;
    }

    public final String getTitle() {
        return title.get();
    }
    public final void setTitle(final String TITLE) {
        title.set(TITLE);
    }
    public final StringProperty titleProperty() {
        return title;
    }

    public final NeedleType getNeedleType() {
        return needleType.get();
    }
    public final void setNeedleType(final NeedleType NEEDLE_TYPE) {
        needleType.set(NEEDLE_TYPE);
    }
    public final ObjectProperty<NeedleType> needleTypeProperty() {
        return needleType;
    }

    public final boolean isAnimated() {
        return animated.get();
    }
    public final void setAnimated(final boolean ANIMATED) {
        animated.set(ANIMATED);
    }
    public final BooleanProperty animatedProperty() {
        return animated;
    }

    public final TickLabelOrientation getTickLabelOrientation() {
        return tickLabelOrientation.get();
    }
    public final void setTickLabelOrientation(final TickLabelOrientation TICK_LABEL_ORIENATION) {
        tickLabelOrientation.set(TICK_LABEL_ORIENATION);
    }
    public final ReadOnlyObjectProperty<TickLabelOrientation> tickLabelOrientationProperty() {
        return tickLabelOrientation;
    }

    public final NumberFormat getNumberFormat() {
        return numberFormat.get();
    }
    public final void setNumberFormat(final NumberFormat NUMBER_FORMAT) {
        numberFormat.set(NUMBER_FORMAT);
    }
    public final ReadOnlyObjectProperty<NumberFormat> numberFormatProperty() {
        return numberFormat;
    }

    public final boolean isTickLabelsVisible() {
        return tickLabelsVisible.get();
    }
    public final void setTickLabelsVisible(final boolean TICK_LABELS_VISIBLE) {
        tickLabelsVisible.set(TICK_LABELS_VISIBLE);
    }
    public final ReadOnlyBooleanProperty tickLabelsVisibleProperty() {
        return tickLabelsVisible;
    }


    // ******************** Utility methods ***********************************
    private static double clamp(final double MIN, final double MAX, final double VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }


    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource("radial.css").toExternalForm();
    }
}

