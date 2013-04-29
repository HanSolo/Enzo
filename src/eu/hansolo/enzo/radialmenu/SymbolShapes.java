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

package eu.hansolo.enzo.radialmenu;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 24.09.12
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
public class SymbolShapes extends Symbol {

    // ******************** Methods *******************************************
    public static final Group getSymbol(final Type TYPE, final double SIZE, final Color COLOR) {
        final Group SYMBOL_GROUP = new Group();

        final Shape IBOUNDS = new Rectangle(0, 0, SIZE, SIZE);
        IBOUNDS.setOpacity(0.0);
        SYMBOL_GROUP.getChildren().add(IBOUNDS);

        final Path SYMBOL;
        switch(TYPE) {
            case REFRESH:
                SYMBOL = getRefresh(SIZE);
                break;
            case STAR:
                SYMBOL = getStar(SIZE);
                break;
            case HEART:
                SYMBOL = getHeart(SIZE);
                break;
            case BLUE_TOOTH:
                SYMBOL = getBlueTooth(SIZE);
                break;
            case SEARCH:
                SYMBOL = getSearch(SIZE);
                break;
            case ZOOM_IN:
                SYMBOL = getZoomIn(SIZE);
                break;
            case ZOOM_OUT:
                SYMBOL = getZoomOut(SIZE);
                break;
            case EYE:
                SYMBOL = getEye(SIZE);
                break;
            case COMPOSE:
                SYMBOL = getCompose(SIZE);
                break;
            case ROCKET:
                SYMBOL = getRocket(SIZE);
                break;
            case CART:
                SYMBOL = getCart(SIZE);
                break;
            case TAG:
                SYMBOL = getTag(SIZE);
                break;
            case REPEAT:
                SYMBOL = getRepeat(SIZE);
                break;
            case SHUFFLE:
                SYMBOL = getShuffle(SIZE);
                break;
            case UNDO:
                SYMBOL = getUndo(SIZE);
                break;
            case SETTINGS:
                SYMBOL = getSettings(SIZE);
                break;
            case TOOL:
                SYMBOL = getTool(SIZE);
                break;
            case CALENDAR:
                SYMBOL = getCalendar(SIZE);
                break;
            case CLOCK:
                SYMBOL = getClock(SIZE);
                break;
            case ALARM:
                SYMBOL = getAlarm(SIZE);
                break;
            case SPEECH_BUBBLE:
                SYMBOL = getSpeechBubble(SIZE);
                break;
            case WEB:
                SYMBOL = getWeb(SIZE);
                break;
            case MAIL:
                SYMBOL = getMail(SIZE);
                break;
            case PHONE:
                SYMBOL = getPhone(SIZE);
                break;
            case USER:
                SYMBOL = getUser(SIZE);
                break;
            case ATTACHMENT:
                SYMBOL = getAttachment(SIZE);
                break;
            case LINK:
                SYMBOL = getLink(SIZE);
                break;
            case CLOUD:
                SYMBOL = getCloud(SIZE);
                break;
            case GRAPH:
                SYMBOL = getGraph(SIZE);
                break;
            case TRASH:
                SYMBOL = getTrash(SIZE);
                break;
            case HEAD_PHONES:
                SYMBOL = getHeadPhones(SIZE);
                break;
            case MUSIC:
                SYMBOL = getMusic(SIZE);
                break;
            case VOLUME:
                SYMBOL = getVolume(SIZE);
                break;
            case CAMERA:
                SYMBOL = getCamera(SIZE);
                break;
            case PHOTO:
                SYMBOL = getPhoto(SIZE);
                break;
            case BACK:
                SYMBOL = getBack(SIZE);
                break;
            case REWIND:
                SYMBOL = getRewind(SIZE);
                break;
            case PAUSE:
                SYMBOL = getPause(SIZE);
                break;
            case PLAY:
                SYMBOL = getPlay(SIZE);
                break;
            case FORWARD:
                SYMBOL = getForward(SIZE);
                break;
            case NEXT:
                SYMBOL = getNext(SIZE);
                break;
            case EJECT:
                SYMBOL = getEject(SIZE);
                break;
            case COMPASS:
                SYMBOL = getCompass(SIZE);
                break;
            case GLOBE:
                SYMBOL = getGlobe(SIZE);
                break;
            case LOCATION:
                SYMBOL = getLocation(SIZE);
                break;
            case TWITTER:
                SYMBOL = getTwitter(SIZE);
                break;
            case FACEBOOK:
                SYMBOL = getFacebook(SIZE);
                break;
            case GOOGLE:
                SYMBOL = getGoogle(SIZE);
                break;
            case LOCK:
                SYMBOL = getLock(SIZE);
                break;
            case CURSOR:
                SYMBOL = getCursor(SIZE);
                break;
            case CURSOR1:
                SYMBOL = getCursor1(SIZE);
                break;
            case CURSOR2:
                SYMBOL = getCursor2(SIZE);
                break;
            case CROP:
                SYMBOL = getCrop(SIZE);
                break;
            case SELECTION:
                SYMBOL = getSelection(SIZE);
                break;
            case SELECTION1:
                SYMBOL = getSelection1(SIZE);
                break;
            case SELECTION2:
                SYMBOL = getSelection2(SIZE);
                break;
            case SELECTION3:
                SYMBOL = getSelection3(SIZE);
                break;
            case PENCIL:
                SYMBOL = getPencil(SIZE);
                break;
            case BRUSH:
                SYMBOL = getBrush(SIZE);
                break;
            case BIG_BRUSH:
                SYMBOL = getBigBrush(SIZE);
                break;
            case PEN:
                SYMBOL = getPen(SIZE);
                break;
            case PEN1:
                SYMBOL = getPen1(SIZE);
                break;
            case LINE:
                SYMBOL = getLine(SIZE);
                break;
            case EYEDROPPER:
                SYMBOL = getEyeDropper(SIZE);
                break;
            case ERASER:
                SYMBOL = getEraser(SIZE);
                break;
            case SCISSORS:
                SYMBOL = getScissors(SIZE);
                break;
            case SMUDGE:
                SYMBOL = getSmudge(SIZE);
                break;
            case CONTRAST:
                SYMBOL = getContrast(SIZE);
                break;
            case LAYERS:
                SYMBOL = getLayers(SIZE);
                break;
            case BOOLEAN:
                SYMBOL = getBoolean(SIZE);
                break;
            case TEXT:
                SYMBOL = getText(SIZE);
                break;
            case ALIGN_LEFT:
                SYMBOL = getAlignLeft(SIZE);
                break;
            case CENTER:
                SYMBOL = getCenter(SIZE);
                break;
            case ALIGN_RIGHT:
                SYMBOL = getAlignRight(SIZE);
                break;
            case JUSTIFIED:
                SYMBOL = getJustified(SIZE);
                break;
            case BULB:
                SYMBOL = getBulb(SIZE);
                break;
            case BULB_ON:
                SYMBOL = getBulbOn(SIZE);
                break;
            case GAUGE:
                SYMBOL = getGauge(SIZE);
                break;
            case UNLOCK:
                SYMBOL = getUnlock(SIZE);
                break;
            case TAGS:
                SYMBOL = getTags(SIZE);
                break;
            case MONITOR:
                SYMBOL = getMonitor(SIZE);
                break;
            case PLANE:
                SYMBOL = getPlane(SIZE);
                break;
            case TRAIN:
                SYMBOL = getTrain(SIZE);
                break;
            case CAR:
                SYMBOL = getCar(SIZE);
                break;
            case LUGGAGE:
                SYMBOL = getLuggage(SIZE);
                break;
            case BRIGHTNESS:
                SYMBOL = getBrightness(SIZE);
                break;
            case DELETE:
                SYMBOL = getDelete(SIZE);
                break;
            default:
                SYMBOL = new Path();
                break;
        }

        SYMBOL.setFill(COLOR);

        SYMBOL_GROUP.getChildren().addAll(IBOUNDS, SYMBOL);
        SYMBOL_GROUP.setCache(true);

        return SYMBOL_GROUP;
    }


    // ******************** SymbolShapes methods ************************************
    private static final Path getRefresh(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path REFRESH = new Path();
        REFRESH.setFillRule(FillRule.EVEN_ODD);
        REFRESH.getElements().add(new MoveTo(0.21428571428571427 * WIDTH, 0.39285714285714285 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.25 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        REFRESH.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT));
        REFRESH.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.39285714285714285 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.42857142857142855 * HEIGHT));
        REFRESH.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.42857142857142855 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.39285714285714285 * HEIGHT));
        REFRESH.getElements().add(new ClosePath());
        REFRESH.getElements().add(new MoveTo(0.7857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.75 * HEIGHT,
            0.5 * WIDTH, 0.75 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.75 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT));
        REFRESH.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT));
        REFRESH.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.7857142857142857 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5 * WIDTH, 0.8571428571428571 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5714285714285714 * HEIGHT));
        REFRESH.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT));
        REFRESH.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        REFRESH.getElements().add(new ClosePath());
        REFRESH.setStroke(null);
        return REFRESH;
    }

    private static final Path getStar(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path STAR = new Path();
        STAR.setFillRule(FillRule.NON_ZERO);
        STAR.getElements().add(new MoveTo(0.4642857142857143 * WIDTH, 0.10714285714285714 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.10714285714285714 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.39285714285714285 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5357142857142857 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.6428571428571429 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.75 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.75 * WIDTH, 0.8571428571428571 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.75 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.75 * WIDTH, 0.8928571428571429 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.75 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.75 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.75 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.75 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.25 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.25 * WIDTH, 0.8928571428571429 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.25 * WIDTH, 0.8571428571428571 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.39285714285714285 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT));
        STAR.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.10714285714285714 * HEIGHT));
        STAR.getElements().add(new ClosePath());
        STAR.setStroke(null);
        return STAR;
    }

    private static final Path getHeart(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path HEART = new Path();
        HEART.setFillRule(FillRule.EVEN_ODD);
        HEART.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.10714285714285714 * HEIGHT));
        HEART.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        HEART.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT));
        HEART.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        HEART.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.5 * WIDTH, 0.8928571428571429 * HEIGHT));
        HEART.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.32142857142857145 * HEIGHT));
        HEART.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.10714285714285714 * HEIGHT));
        HEART.getElements().add(new ClosePath());
        HEART.setStroke(null);

        return HEART;
    }

    private static final Path getBlueTooth(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path BLUETOOTH = new Path();
        BLUETOOTH.setFillRule(FillRule.EVEN_ODD);
        BLUETOOTH.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.5 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.25 * WIDTH, 0.32142857142857145 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.2857142857142857 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.07142857142857142 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.5 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.6785714285714286 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.9285714285714286 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.7142857142857143 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.25 * WIDTH, 0.6785714285714286 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.5 * HEIGHT));
        BLUETOOTH.getElements().add(new ClosePath());
        BLUETOOTH.getElements().add(new MoveTo(0.5357142857142857 * WIDTH, 0.25 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.42857142857142855 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.25 * HEIGHT));
        BLUETOOTH.getElements().add(new ClosePath());
        BLUETOOTH.getElements().add(new MoveTo(0.5357142857142857 * WIDTH, 0.75 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        BLUETOOTH.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.75 * HEIGHT));
        BLUETOOTH.getElements().add(new ClosePath());
        BLUETOOTH.setStroke(null);

        return BLUETOOTH;
    }

    private static final Path getSearch(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path SEARCH = new Path();
        SEARCH.setFillRule(FillRule.EVEN_ODD);
        SEARCH.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.42857142857142855 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6071428571428571 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.42857142857142855 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        SEARCH.getElements().add(new ClosePath());
        SEARCH.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.25 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.42857142857142855 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.75 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8928571428571429 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8928571428571429 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6071428571428571 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.42857142857142855 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.25 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT));
        SEARCH.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT));
        SEARCH.getElements().add(new ClosePath());
        SEARCH.setStroke(null);

        return SEARCH;
    }

    private static final Path getZoomIn(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path ZOOMIN = new Path();
        ZOOMIN.setFillRule(FillRule.EVEN_ODD);
        ZOOMIN.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.42857142857142855 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6071428571428571 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.42857142857142855 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        ZOOMIN.getElements().add(new ClosePath());
        ZOOMIN.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.25 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.42857142857142855 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.75 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8928571428571429 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8928571428571429 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6071428571428571 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.42857142857142855 * HEIGHT));
        ZOOMIN.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.25 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT));
        ZOOMIN.getElements().add(new ClosePath());
        ZOOMIN.getElements().add(new MoveTo(0.35714285714285715 * WIDTH, 0.32142857142857145 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.39285714285714285 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.4642857142857143 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.5357142857142857 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.5357142857142857 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.4642857142857143 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.5 * WIDTH, 0.4642857142857143 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.5 * WIDTH, 0.39285714285714285 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.39285714285714285 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.32142857142857145 * HEIGHT));
        ZOOMIN.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.32142857142857145 * HEIGHT));
        ZOOMIN.getElements().add(new ClosePath());
        ZOOMIN.setStroke(null);

        return ZOOMIN;
    }

    private static final Path getZoomOut(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path ZOOMOUT = new Path();
        ZOOMOUT.setFillRule(FillRule.EVEN_ODD);
        ZOOMOUT.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.42857142857142855 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6071428571428571 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.42857142857142855 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        ZOOMOUT.getElements().add(new ClosePath());
        ZOOMOUT.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.25 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.42857142857142855 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.75 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8928571428571429 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8928571428571429 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6071428571428571 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.42857142857142855 * HEIGHT));
        ZOOMOUT.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.25 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT));
        ZOOMOUT.getElements().add(new ClosePath());
        ZOOMOUT.getElements().add(new MoveTo(0.5 * WIDTH, 0.39285714285714285 * HEIGHT));
        ZOOMOUT.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT));
        ZOOMOUT.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        ZOOMOUT.getElements().add(new LineTo(0.5 * WIDTH, 0.4642857142857143 * HEIGHT));
        ZOOMOUT.getElements().add(new LineTo(0.5 * WIDTH, 0.39285714285714285 * HEIGHT));
        ZOOMOUT.getElements().add(new ClosePath());
        ZOOMOUT.setStroke(null);

        return ZOOMOUT;
    }

    private static final Path getEye(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path EYE = new Path();
        EYE.setFillRule(FillRule.EVEN_ODD);
        EYE.getElements().add(new MoveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.5 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.5 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.5 * HEIGHT,
            0.25 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.5 * WIDTH, 0.7857142857142857 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.5 * HEIGHT,
            0.75 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        EYE.getElements().add(new ClosePath());
        EYE.getElements().add(new MoveTo(0.5 * WIDTH, 0.6785714285714286 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5 * WIDTH, 0.32142857142857145 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5 * WIDTH, 0.6785714285714286 * HEIGHT));
        EYE.getElements().add(new ClosePath());
        EYE.getElements().add(new MoveTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.5 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.5714285714285714 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5 * HEIGHT));
        EYE.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        EYE.getElements().add(new ClosePath());
        EYE.setStroke(null);

        return EYE;
    }

    private static final Path getCompose(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path COMPOSE = new Path();
        COMPOSE.setFillRule(FillRule.EVEN_ODD);
        COMPOSE.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.25 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.25 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.75 * WIDTH, 0.14285714285714285 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.75 * WIDTH, 0.10714285714285714 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.10714285714285714 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        COMPOSE.getElements().add(new ClosePath());
        COMPOSE.getElements().add(new MoveTo(0.4642857142857143 * WIDTH, 0.6428571428571429 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.5357142857142857 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.2857142857142857 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.6428571428571429 * HEIGHT));
        COMPOSE.getElements().add(new ClosePath());
        COMPOSE.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT));
        COMPOSE.getElements().add(new ClosePath());
        COMPOSE.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.7857142857142857 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.75 * WIDTH, 0.7857142857142857 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.75 * WIDTH, 0.4642857142857143 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.5 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.25 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8928571428571429 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.8214285714285714 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.17857142857142858 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.10714285714285714 * HEIGHT));
        COMPOSE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.21428571428571427 * HEIGHT));
        COMPOSE.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT));
        COMPOSE.getElements().add(new ClosePath());
        COMPOSE.setStroke(null);

        return COMPOSE;
    }

    private static final Path getRocket(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path ROCKET = new Path();
        ROCKET.setFillRule(FillRule.EVEN_ODD);
        ROCKET.getElements().add(new MoveTo(0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.25 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.21428571428571427 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.25 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.75 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.35714285714285715 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT));
        ROCKET.getElements().add(new ClosePath());
        ROCKET.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.10714285714285714 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.39285714285714285 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.25 * WIDTH, 0.4642857142857143 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.25 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT));
        ROCKET.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.7142857142857143 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.7142857142857143 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8928571428571429 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.75 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5714285714285714 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.5 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.10714285714285714 * HEIGHT));
        ROCKET.getElements().add(new ClosePath());
        ROCKET.getElements().add(new MoveTo(0.25 * WIDTH, 0.6785714285714286 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.75 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.75 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT));
        ROCKET.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.75 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT));
        ROCKET.getElements().add(new ClosePath());
        ROCKET.setStroke(null);

        return ROCKET;
    }

    private static final Path getCart(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path CART = new Path();
        CART.setFillRule(FillRule.EVEN_ODD);
        CART.getElements().add(new MoveTo(0.2857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT));
        CART.getElements().add(new ClosePath());
        CART.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.75 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.75 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        CART.getElements().add(new ClosePath());
        CART.getElements().add(new MoveTo(0.2857142857142857 * WIDTH, 0.7857142857142857 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8571428571428571 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.7857142857142857 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.75 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7857142857142857 * HEIGHT));
        CART.getElements().add(new ClosePath());
        CART.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        CART.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.2857142857142857 * HEIGHT));
        CART.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.25 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.25 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.14285714285714285 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.17857142857142858 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.25 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.25 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.25 * HEIGHT));
        CART.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT));
        CART.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT));
        CART.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        CART.getElements().add(new ClosePath());
        CART.setStroke(null);

        return CART;
    }

    private static final Path getTag(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path TAG = new Path();
        TAG.setFillRule(FillRule.EVEN_ODD);
        TAG.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.10714285714285714 * HEIGHT));
        TAG.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.10714285714285714 * HEIGHT));
        TAG.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.42857142857142855 * HEIGHT));
        TAG.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.9285714285714286 * HEIGHT));
        TAG.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.5714285714285714 * HEIGHT));
        TAG.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.10714285714285714 * HEIGHT));
        TAG.getElements().add(new ClosePath());
        TAG.getElements().add(new MoveTo(0.25 * WIDTH, 0.35714285714285715 * HEIGHT));
        TAG.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT));
        TAG.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.25 * WIDTH, 0.21428571428571427 * HEIGHT));
        TAG.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.25 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.2857142857142857 * HEIGHT));
        TAG.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.25 * WIDTH, 0.35714285714285715 * HEIGHT));
        TAG.getElements().add(new ClosePath());
        TAG.setStroke(null);

        return TAG;
    }

    private static final Path getRepeat(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path REPEAT = new Path();
        REPEAT.setFillRule(FillRule.EVEN_ODD);
        REPEAT.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.6785714285714286 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.6785714285714286 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.5 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.5 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.6785714285714286 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.8214285714285714 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.8214285714285714 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.9285714285714286 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.5357142857142857 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.6785714285714286 * HEIGHT));
        REPEAT.getElements().add(new ClosePath());
        REPEAT.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.17857142857142858 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.07142857142857142 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.25 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.5 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.5 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.32142857142857145 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.17857142857142858 * HEIGHT));
        REPEAT.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        REPEAT.getElements().add(new ClosePath());
        REPEAT.setStroke(null);

        return REPEAT;
    }

    private static final Path getShuffle(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path SHUFFLE = new Path();
        SHUFFLE.setFillRule(FillRule.EVEN_ODD);
        SHUFFLE.getElements().add(new MoveTo(0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.25 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.25 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.25 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.25 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.25 * WIDTH, 0.42857142857142855 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.25 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.42857142857142855 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT));
        SHUFFLE.getElements().add(new ClosePath());
        SHUFFLE.getElements().add(new MoveTo(0.4642857142857143 * WIDTH, 0.6785714285714286 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.75 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.75 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.75 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.75 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(WIDTH, 0.7142857142857143 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6428571428571429 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.5 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.6785714285714286 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.6785714285714286 * HEIGHT));
        SHUFFLE.getElements().add(new ClosePath());
        SHUFFLE.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.5 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(WIDTH, 0.32142857142857145 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5 * WIDTH, 0.35714285714285715 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6071428571428571 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT));
        SHUFFLE.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.5 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT));
        SHUFFLE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT));
        SHUFFLE.getElements().add(new ClosePath());
        SHUFFLE.setStroke(null);

        return SHUFFLE;
    }

    private static final Path getUndo(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path UNDO = new Path();
        UNDO.setFillRule(FillRule.NON_ZERO);
        UNDO.getElements().add(new MoveTo(0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        UNDO.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.21428571428571427 * HEIGHT));
        UNDO.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT));
        UNDO.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.42857142857142855 * HEIGHT));
        UNDO.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT));
        UNDO.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        UNDO.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.25 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        UNDO.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5 * HEIGHT));
        UNDO.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.5 * WIDTH, 0.7857142857142857 * HEIGHT));
        UNDO.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.25 * WIDTH, 0.6428571428571429 * HEIGHT));
        UNDO.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT));
        UNDO.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.5 * WIDTH, 0.8928571428571429 * HEIGHT));
        UNDO.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5 * HEIGHT));
        UNDO.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        UNDO.getElements().add(new ClosePath());
        UNDO.setStroke(null);

        return UNDO;
    }

    private static final Path getSettings(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path SETTINGS = new Path();
        SETTINGS.setFillRule(FillRule.EVEN_ODD);
        SETTINGS.getElements().add(new MoveTo(0.9642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT));
        SETTINGS.getElements().add(new LineTo(0.9642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.42857142857142855 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.2857142857142857 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT));
        SETTINGS.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.03571428571428571 * HEIGHT));
        SETTINGS.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.03571428571428571 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.10714285714285714 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.10714285714285714 * HEIGHT));
        SETTINGS.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.42857142857142855 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT));
        SETTINGS.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.7142857142857143 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT));
        SETTINGS.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.8571428571428571 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.9642857142857143 * HEIGHT));
        SETTINGS.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.9642857142857143 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT));
        SETTINGS.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5714285714285714 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT));
        SETTINGS.getElements().add(new ClosePath());
        SETTINGS.getElements().add(new MoveTo(0.5 * WIDTH, 0.6785714285714286 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5 * WIDTH, 0.32142857142857145 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5 * HEIGHT));
        SETTINGS.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5 * WIDTH, 0.6785714285714286 * HEIGHT));
        SETTINGS.getElements().add(new ClosePath());
        SETTINGS.setStroke(null);

        return SETTINGS;
    }

    private static final Path getTool(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path TOOL = new Path();
        TOOL.setFillRule(FillRule.EVEN_ODD);
        TOOL.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.75 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.75 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.75 * WIDTH, 0.75 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.75 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.75 * WIDTH, 0.8214285714285714 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.75 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.75 * HEIGHT));
        TOOL.getElements().add(new ClosePath());
        TOOL.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.4642857142857143 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.2857142857142857 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.25 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.25 * WIDTH, 0.39285714285714285 * HEIGHT));
        TOOL.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT));
        TOOL.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.21428571428571427 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.07142857142857142 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.35714285714285715 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8571428571428571 * HEIGHT));
        TOOL.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.75 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT));
        TOOL.getElements().add(new ClosePath());
        TOOL.setStroke(null);

        return TOOL;
    }

    private static final Path getCalendar(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path CALENDAR = new Path();
        CALENDAR.setFillRule(FillRule.EVEN_ODD);
        CALENDAR.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.07142857142857142 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.14285714285714285 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.75 * WIDTH, 0.17857142857142858 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.07142857142857142 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.75 * WIDTH, 0.03571428571428571 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.07142857142857142 * HEIGHT));
        CALENDAR.getElements().add(new ClosePath());
        CALENDAR.getElements().add(new MoveTo(0.10714285714285714 * WIDTH, 0.32142857142857145 * HEIGHT));
        CALENDAR.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8928571428571429 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.32142857142857145 * HEIGHT));
        CALENDAR.getElements().add(new ClosePath());
        CALENDAR.getElements().add(new MoveTo(0.03571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.8571428571428571 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.9642857142857143 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.9642857142857143 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.8571428571428571 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.21428571428571427 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.10714285714285714 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.21428571428571427 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.17857142857142858 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.10714285714285714 * HEIGHT));
        CALENDAR.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.25 * WIDTH, 0.21428571428571427 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        CALENDAR.getElements().add(new ClosePath());
        CALENDAR.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.07142857142857142 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.14285714285714285 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.17857142857142858 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.14285714285714285 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.25 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.25 * WIDTH, 0.07142857142857142 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.25 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.03571428571428571 * HEIGHT));
        CALENDAR.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.07142857142857142 * HEIGHT));
        CALENDAR.getElements().add(new ClosePath());
        CALENDAR.setStroke(null);

        return CALENDAR;
    }

    private static final Path getClock(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path CLOCK = new Path();
        CLOCK.setFillRule(FillRule.EVEN_ODD);
        CLOCK.getElements().add(new MoveTo(0.5 * WIDTH, 0.8928571428571429 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.5 * WIDTH, 0.8928571428571429 * HEIGHT));
        CLOCK.getElements().add(new ClosePath());
        CLOCK.getElements().add(new MoveTo(0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.5 * WIDTH, 0.8214285714285714 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        CLOCK.getElements().add(new ClosePath());
        CLOCK.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.5714285714285714 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.5714285714285714 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.5357142857142857 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.2857142857142857 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.5 * WIDTH, 0.25 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.25 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.25 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.5 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.5 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.5 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.5 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5357142857142857 * HEIGHT));
        CLOCK.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5714285714285714 * HEIGHT));
        CLOCK.getElements().add(new ClosePath());
        CLOCK.setStroke(null);

        return CLOCK;
    }

    private static final Path getAlarm(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path ALARM = new Path();
        ALARM.setFillRule(FillRule.EVEN_ODD);
        ALARM.getElements().add(new MoveTo(0.5357142857142857 * WIDTH, 0.21428571428571427 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.21428571428571427 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.6071428571428571 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.21428571428571427 * HEIGHT));
        ALARM.getElements().add(new ClosePath());
        ALARM.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.25 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.25 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6071428571428571 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.75 * HEIGHT));
        ALARM.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.75 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        ALARM.getElements().add(new ClosePath());
        ALARM.getElements().add(new MoveTo(0.5 * WIDTH, 0.9285714285714286 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        ALARM.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.5 * WIDTH, 0.9285714285714286 * HEIGHT));
        ALARM.getElements().add(new ClosePath());
        ALARM.setStroke(null);

        return ALARM;
    }

    private static final Path getSpeechBubble(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path BUBBLE = new Path();
        BUBBLE.setFillRule(FillRule.EVEN_ODD);
        BUBBLE.getElements().add(new MoveTo(0.07142857142857142 * WIDTH, 0.42857142857142855 * HEIGHT));
        BUBBLE.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.25 * WIDTH, 0.6428571428571429 * HEIGHT));
        BUBBLE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT));
        BUBBLE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT));
        BUBBLE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.5 * WIDTH, 0.7142857142857143 * HEIGHT));
        BUBBLE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.42857142857142855 * HEIGHT));
        BUBBLE.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.75 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT));
        BUBBLE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.42857142857142855 * HEIGHT));
        BUBBLE.getElements().add(new ClosePath());
        BUBBLE.setStroke(null);

        return BUBBLE;
    }

    private static final Path getWeb(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path WEB = new Path();
        WEB.setFillRule(FillRule.EVEN_ODD);
        WEB.getElements().add(new MoveTo(0.6071428571428571 * WIDTH, 0.5357142857142857 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.2857142857142857 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.5 * WIDTH, 0.2857142857142857 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6071428571428571 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.25 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5 * WIDTH, 0.8571428571428571 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.75 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.75 * WIDTH, 0.7142857142857143 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.75 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.5 * WIDTH, 0.7857142857142857 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.42857142857142855 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6071428571428571 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5357142857142857 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5357142857142857 * HEIGHT));
        WEB.getElements().add(new ClosePath());
        WEB.getElements().add(new MoveTo(0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6071428571428571 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.5 * WIDTH, 0.35714285714285715 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT));
        WEB.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT));
        WEB.getElements().add(new ClosePath());
        WEB.setStroke(null);

        return WEB;
    }

    private static final Path getMail(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path MAIL = new Path();
        MAIL.setFillRule(FillRule.EVEN_ODD);
        MAIL.getElements().add(new MoveTo(0.6071428571428571 * WIDTH, 0.5357142857142857 * HEIGHT));
        MAIL.getElements().add(new LineTo(0.5 * WIDTH, 0.6428571428571429 * HEIGHT));
        MAIL.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.75 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.75 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.75 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.75 * HEIGHT));
        MAIL.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.5357142857142857 * HEIGHT));
        MAIL.getElements().add(new ClosePath());
        MAIL.getElements().add(new MoveTo(0.07142857142857142 * WIDTH, 0.25 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.25 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.25 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.2857142857142857 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.7142857142857143 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.7142857142857143 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.5 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.5 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.5 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.25 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.25 * HEIGHT));
        MAIL.getElements().add(new ClosePath());
        MAIL.getElements().add(new MoveTo(0.9285714285714286 * WIDTH, 0.2857142857142857 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.25 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.25 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.25 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.25 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.5 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.7142857142857143 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.7142857142857143 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.2857142857142857 * HEIGHT));
        MAIL.getElements().add(new ClosePath());
        MAIL.getElements().add(new MoveTo(0.5 * WIDTH, 0.5357142857142857 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.21428571428571427 * HEIGHT));
        MAIL.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5 * WIDTH, 0.5357142857142857 * HEIGHT));
        MAIL.getElements().add(new ClosePath());
        MAIL.setStroke(null);

        return MAIL;
    }

    private static final Path getPhone(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path PHONE = new Path();
        PHONE.setFillRule(FillRule.EVEN_ODD);
        PHONE.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.75 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.75 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.75 * WIDTH, 0.5714285714285714 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5714285714285714 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6428571428571429 * HEIGHT));
        PHONE.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.75 * HEIGHT));
        PHONE.getElements().add(new ClosePath());
        PHONE.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.25 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.25 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.10714285714285714 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT));
        PHONE.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT));
        PHONE.getElements().add(new ClosePath());
        PHONE.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.25 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.75 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.6428571428571429 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.5 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT));
        PHONE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        PHONE.getElements().add(new ClosePath());
        PHONE.setStroke(null);

        return PHONE;
    }

    private static final Path getUser(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path USER = new Path();
        USER.setFillRule(FillRule.EVEN_ODD);
        USER.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8928571428571429 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.7142857142857143 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6071428571428571 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.4642857142857143 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.25 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.25 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.4642857142857143 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.75 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT));
        USER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT));
        USER.getElements().add(new ClosePath());
        USER.setStroke(null);

        return USER;
    }

    private static final Path getAttachment(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path ATTACHMENT = new Path();
        ATTACHMENT.setFillRule(FillRule.NON_ZERO);
        ATTACHMENT.getElements().add(new MoveTo(0.07142857142857142 * WIDTH, 0.5714285714285714 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.5714285714285714 * HEIGHT));
        ATTACHMENT.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.75 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        ATTACHMENT.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.5 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.5 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.5357142857142857 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        ATTACHMENT.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.14285714285714285 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.8571428571428571 * HEIGHT));
        ATTACHMENT.getElements().add(new CubicCurveTo(0.0, 0.7857142857142857 * HEIGHT,
            0.0, 0.6428571428571429 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.5714285714285714 * HEIGHT));
        ATTACHMENT.getElements().add(new ClosePath());
        ATTACHMENT.setStroke(null);

        return ATTACHMENT;
    }

    private static final Path getLink(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path LINK = new Path();
        LINK.setFillRule(FillRule.EVEN_ODD);
        LINK.getElements().add(new MoveTo(0.5714285714285714 * WIDTH, 0.5 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.5357142857142857 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.5357142857142857 * HEIGHT));
        LINK.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.25 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.25 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.25 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.25 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.35714285714285715 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.42857142857142855 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.5 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.25 * WIDTH, 0.6071428571428571 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT));
        LINK.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.6071428571428571 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.5 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5 * HEIGHT));
        LINK.getElements().add(new ClosePath());
        LINK.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT));
        LINK.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.5 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5 * WIDTH, 0.4642857142857143 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        LINK.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.4642857142857143 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.75 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.75 * HEIGHT,
            0.5 * WIDTH, 0.75 * HEIGHT,
            0.5 * WIDTH, 0.75 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.75 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6428571428571429 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.75 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.75 * WIDTH, 0.8214285714285714 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.6428571428571429 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5714285714285714 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.5 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.39285714285714285 * HEIGHT));
        LINK.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT));
        LINK.getElements().add(new ClosePath());
        LINK.setStroke(null);

        return LINK;
    }

    private static final Path getCloud(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path CLOUD = new Path();
        CLOUD.setFillRule(FillRule.EVEN_ODD);
        CLOUD.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.25 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.25 * WIDTH, 0.32142857142857145 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.42857142857142855 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.5 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT));
        CLOUD.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.6428571428571429 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        CLOUD.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        CLOUD.getElements().add(new ClosePath());
        CLOUD.setStroke(null);

        return CLOUD;
    }

    private static final Path getGraph(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path GRAPH = new Path();
        GRAPH.setFillRule(FillRule.EVEN_ODD);
        GRAPH.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.39285714285714285 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.32142857142857145 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.21428571428571427 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.21428571428571427 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.5357142857142857 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.75 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7857142857142857 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.75 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.75 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.75 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7857142857142857 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.8571428571428571 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7857142857142857 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.75 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.75 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT));
        GRAPH.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.75 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.75 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.75 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.75 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        GRAPH.getElements().add(new ClosePath());
        GRAPH.setStroke(null);

        return GRAPH;
    }

    private static final Path getTrash(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path TRASH = new Path();
        TRASH.setFillRule(FillRule.EVEN_ODD);
        TRASH.getElements().add(new MoveTo(0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new ClosePath());
        TRASH.getElements().add(new MoveTo(0.5357142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new ClosePath());
        TRASH.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new ClosePath());
        TRASH.getElements().add(new MoveTo(0.32142857142857145 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRASH.getElements().add(new ClosePath());
        TRASH.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT));
        TRASH.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.75 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.75 * HEIGHT));
        TRASH.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT));
        TRASH.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT));
        TRASH.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.75 * HEIGHT));
        TRASH.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.75 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.25 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.25 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.75 * WIDTH, 0.25 * HEIGHT));
        TRASH.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.25 * HEIGHT,
            0.75 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.75 * HEIGHT));
        TRASH.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.75 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        TRASH.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7857142857142857 * HEIGHT));
        TRASH.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.25 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.25 * WIDTH, 0.75 * HEIGHT));
        TRASH.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.75 * HEIGHT,
            0.25 * WIDTH, 0.25 * HEIGHT,
            0.25 * WIDTH, 0.25 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT));
        TRASH.getElements().add(new ClosePath());
        TRASH.getElements().add(new MoveTo(0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.10714285714285714 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.10714285714285714 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.14285714285714285 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        TRASH.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT));
        TRASH.getElements().add(new ClosePath());
        TRASH.setStroke(null);

        return TRASH;
    }

    private static final Path getHeadPhones(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path HEADPHONES = new Path();
        HEADPHONES.setFillRule(FillRule.EVEN_ODD);
        HEADPHONES.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.8214285714285714 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.75 * WIDTH, 0.8928571428571429 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.75 * WIDTH, 0.5357142857142857 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT));
        HEADPHONES.getElements().add(new ClosePath());
        HEADPHONES.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.25 * WIDTH, 0.8928571428571429 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8214285714285714 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.25 * WIDTH, 0.5357142857142857 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT));
        HEADPHONES.getElements().add(new ClosePath());
        HEADPHONES.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.6785714285714286 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5714285714285714 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.75 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.5714285714285714 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.6785714285714286 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        HEADPHONES.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        HEADPHONES.getElements().add(new ClosePath());
        HEADPHONES.setStroke(null);

        return HEADPHONES;
    }

    private static final Path getMusic(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path MUSIC = new Path();
        MUSIC.setFillRule(FillRule.NON_ZERO);
        MUSIC.getElements().add(new MoveTo(0.32142857142857145 * WIDTH, 0.17857142857142858 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.25 * WIDTH, 0.6071428571428571 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.25 * WIDTH, 0.8571428571428571 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT));
        MUSIC.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.75 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.6071428571428571 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT));
        MUSIC.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.10714285714285714 * HEIGHT));
        MUSIC.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.17857142857142858 * HEIGHT));
        MUSIC.getElements().add(new ClosePath());
        MUSIC.setStroke(null);

        return MUSIC;
    }

    private static final Path getVolume(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        final Path VOLUME = new Path();
        VOLUME.setFillRule(FillRule.EVEN_ODD);
        VOLUME.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.5 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.8571428571428571 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.75 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.14285714285714285 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.25 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5 * HEIGHT));
        VOLUME.getElements().add(new ClosePath());
        VOLUME.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.5 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.7142857142857143 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.75 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.75 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.75 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.75 * WIDTH, 0.5 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.25 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.25 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5 * HEIGHT));
        VOLUME.getElements().add(new ClosePath());
        VOLUME.getElements().add(new MoveTo(0.5 * WIDTH, 0.5 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5 * WIDTH, 0.39285714285714285 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5 * WIDTH, 0.5 * HEIGHT));
        VOLUME.getElements().add(new ClosePath());
        VOLUME.getElements().add(new MoveTo(0.07142857142857142 * WIDTH, 0.6071428571428571 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT));
        VOLUME.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.39285714285714285 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.6071428571428571 * HEIGHT));
        VOLUME.getElements().add(new ClosePath());
        VOLUME.getElements().add(new MoveTo(0.35714285714285715 * WIDTH, 0.7857142857142857 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.75 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.75 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.25 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.21428571428571427 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.32142857142857145 * HEIGHT));
        VOLUME.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT));
        VOLUME.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7857142857142857 * HEIGHT));
        VOLUME.getElements().add(new ClosePath());
        VOLUME.setStroke(null);

        return VOLUME;
    }

    private static final Path getCamera(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path CAMERA = new Path();
        CAMERA.setFillRule(FillRule.EVEN_ODD);
        CAMERA.getElements().add(new MoveTo(0.25 * WIDTH, 0.5 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.25 * HEIGHT,
            0.5 * WIDTH, 0.25 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.25 * HEIGHT,
            0.75 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.75 * WIDTH, 0.5 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.75 * HEIGHT,
            0.5 * WIDTH, 0.75 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.75 * HEIGHT,
            0.25 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.25 * WIDTH, 0.5 * HEIGHT));
        CAMERA.getElements().add(new ClosePath());
        CAMERA.getElements().add(new MoveTo(0.03571428571428571 * WIDTH, 0.2857142857142857 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.2857142857142857 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.17857142857142858 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.75 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.75 * WIDTH, 0.17857142857142858 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.10714285714285714 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.10714285714285714 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.2857142857142857 * HEIGHT));
        CAMERA.getElements().add(new ClosePath());
        CAMERA.getElements().add(new MoveTo(0.35714285714285715 * WIDTH, 0.5 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.5 * WIDTH, 0.6428571428571429 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.5 * WIDTH, 0.35714285714285715 * HEIGHT));
        CAMERA.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.5 * HEIGHT));
        CAMERA.getElements().add(new ClosePath());
        CAMERA.setStroke(null);

        return CAMERA;
    }

    private static final Path getPhoto(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path PHOTO = new Path();
        PHOTO.setFillRule(FillRule.EVEN_ODD);
        PHOTO.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.25 * HEIGHT));
        PHOTO.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.25 * HEIGHT));
        PHOTO.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.75 * HEIGHT));
        PHOTO.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.75 * HEIGHT));
        PHOTO.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.25 * HEIGHT));
        PHOTO.getElements().add(new ClosePath());
        PHOTO.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.2857142857142857 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.7142857142857143 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.7142857142857143 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.2857142857142857 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT));
        PHOTO.getElements().add(new ClosePath());
        PHOTO.getElements().add(new MoveTo(0.25 * WIDTH, 0.39285714285714285 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.25 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.25 * WIDTH, 0.39285714285714285 * HEIGHT));
        PHOTO.getElements().add(new ClosePath());
        PHOTO.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.7142857142857143 * HEIGHT));
        PHOTO.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        PHOTO.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.5 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT));
        PHOTO.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.5 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT));
        PHOTO.getElements().add(new ClosePath());
        PHOTO.setStroke(null);

        return PHOTO;
    }

    private static final Path getBack(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path BACK = new Path();
        BACK.setFillRule(FillRule.EVEN_ODD);
        BACK.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.75 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.75 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.7142857142857143 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.75 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.75 * WIDTH, 0.2857142857142857 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.25 * HEIGHT,
            0.75 * WIDTH, 0.25 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.25 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.25 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.25 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.25 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.25 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.25 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.25 * HEIGHT,
            0.25 * WIDTH, 0.25 * HEIGHT,
            0.25 * WIDTH, 0.2857142857142857 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.25 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT));
        BACK.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT));
        BACK.getElements().add(new ClosePath());
        BACK.setStroke(null);

        return BACK;
    }

    private static final Path getRewind(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path REWIND = new Path();
        REWIND.setFillRule(FillRule.NON_ZERO);
        REWIND.getElements().add(new MoveTo(0.75 * WIDTH, 0.25 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.25 * HEIGHT,
            0.5 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.39285714285714285 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.5 * WIDTH, 0.2857142857142857 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.25 * HEIGHT,
            0.5 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.5 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5357142857142857 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.75 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.75 * HEIGHT,
            0.5 * WIDTH, 0.75 * HEIGHT,
            0.5 * WIDTH, 0.7142857142857143 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.75 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.75 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.75 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.2857142857142857 * HEIGHT));
        REWIND.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.25 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.25 * HEIGHT,
            0.75 * WIDTH, 0.25 * HEIGHT));
        REWIND.getElements().add(new ClosePath());
        REWIND.setStroke(null);

        return REWIND;
    }

    private static final Path getPause(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path PAUSE = new Path();
        PAUSE.setFillRule(FillRule.EVEN_ODD);
        PAUSE.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.6785714285714286 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.25 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.25 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.25 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.25 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.25 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.25 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.32142857142857145 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.75 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.75 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.75 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.75 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.75 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.75 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.6785714285714286 * HEIGHT));
        PAUSE.getElements().add(new ClosePath());
        PAUSE.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.32142857142857145 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.25 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.25 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.25 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.25 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.75 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.75 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.75 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.75 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.75 * HEIGHT));
        PAUSE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.75 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT));
        PAUSE.getElements().add(new ClosePath());
        PAUSE.setStroke(null);

        return PAUSE;
    }

    private static final Path getPlay(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path PLAY = new Path();
        PLAY.setFillRule(FillRule.NON_ZERO);
        PLAY.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT));
        PLAY.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.25 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.25 * HEIGHT));
        PLAY.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.25 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.25 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        PLAY.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        PLAY.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.75 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.75 * HEIGHT));
        PLAY.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.75 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5357142857142857 * HEIGHT));
        PLAY.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.5 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT));
        PLAY.getElements().add(new ClosePath());
        PLAY.setStroke(null);

        return PLAY;
    }

    private static final Path getForward(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path FORWARD = new Path();
        FORWARD.setFillRule(FillRule.NON_ZERO);
        FORWARD.getElements().add(new MoveTo(0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5 * WIDTH, 0.25 * HEIGHT,
            0.5 * WIDTH, 0.25 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.2857142857142857 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.25 * WIDTH, 0.25 * HEIGHT,
            0.25 * WIDTH, 0.25 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.25 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.25 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.7142857142857143 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.75 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT,
            0.25 * WIDTH, 0.75 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.75 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.6071428571428571 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.7142857142857143 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.5 * WIDTH, 0.75 * HEIGHT,
            0.5 * WIDTH, 0.75 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.75 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        FORWARD.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.5 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        FORWARD.getElements().add(new ClosePath());
        FORWARD.setStroke(null);

        return FORWARD;
    }

    private static final Path getNext(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path NEXT = new Path();
        NEXT.setFillRule(FillRule.NON_ZERO);
        NEXT.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.25 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.25 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.25 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.25 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.25 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.25 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.25 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.25 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.25 * HEIGHT,
            0.25 * WIDTH, 0.25 * HEIGHT,
            0.25 * WIDTH, 0.2857142857142857 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.25 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.25 * WIDTH, 0.7142857142857143 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.75 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.75 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.75 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5714285714285714 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.75 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.75 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.75 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.75 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.75 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.75 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.7142857142857143 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.75 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.75 * WIDTH, 0.2857142857142857 * HEIGHT));
        NEXT.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.25 * HEIGHT,
            0.75 * WIDTH, 0.25 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.25 * HEIGHT));
        NEXT.getElements().add(new ClosePath());
        NEXT.setStroke(null);

        return NEXT;
    }

    private static final Path getEject(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path EJECT = new Path();
        EJECT.setFillRule(FillRule.EVEN_ODD);
        EJECT.getElements().add(new MoveTo(0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.75 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.75 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT));
        EJECT.getElements().add(new ClosePath());
        EJECT.getElements().add(new MoveTo(0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.21428571428571427 * HEIGHT));
        EJECT.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT));
        EJECT.getElements().add(new ClosePath());
        EJECT.setStroke(null);

        return EJECT;
    }

    private static final Path getCompass(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path COMPASS = new Path();
        COMPASS.setFillRule(FillRule.EVEN_ODD);
        COMPASS.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT));
        COMPASS.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        COMPASS.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT));
        COMPASS.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.5 * WIDTH, 0.9285714285714286 * HEIGHT));
        COMPASS.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT));
        COMPASS.getElements().add(new ClosePath());
        COMPASS.getElements().add(new MoveTo(0.5 * WIDTH, 0.2857142857142857 * HEIGHT));
        COMPASS.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        COMPASS.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5 * WIDTH, 0.8571428571428571 * HEIGHT));
        COMPASS.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5714285714285714 * HEIGHT));
        COMPASS.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.5 * WIDTH, 0.2857142857142857 * HEIGHT));
        COMPASS.getElements().add(new ClosePath());
        COMPASS.getElements().add(new MoveTo(0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.5357142857142857 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.6428571428571429 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT));
        COMPASS.getElements().add(new ClosePath());
        COMPASS.getElements().add(new MoveTo(0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.21428571428571427 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.07142857142857142 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.03571428571428571 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.03571428571428571 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.03571428571428571 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.03571428571428571 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.07142857142857142 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.17857142857142858 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT));
        COMPASS.getElements().add(new ClosePath());
        COMPASS.getElements().add(new MoveTo(0.5357142857142857 * WIDTH, 0.14285714285714285 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.14285714285714285 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.07142857142857142 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.07142857142857142 * HEIGHT));
        COMPASS.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.14285714285714285 * HEIGHT));
        COMPASS.getElements().add(new ClosePath());
        COMPASS.setStroke(null);

        return COMPASS;
    }

    private static final Path getGlobe(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path GLOBE = new Path();
        GLOBE.setFillRule(FillRule.EVEN_ODD);
        GLOBE.getElements().add(new MoveTo(0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.5 * WIDTH, 0.8928571428571429 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        GLOBE.getElements().add(new ClosePath());
        GLOBE.getElements().add(new MoveTo(0.5 * WIDTH, 0.14285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.42857142857142855 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.75 * WIDTH, 0.6428571428571429 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6428571428571429 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5357142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.42857142857142855 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.75 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.75 * WIDTH, 0.35714285714285715 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.75 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.32142857142857145 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.25 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.25 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.25 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.25 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.14285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.14285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.14285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT));
        GLOBE.getElements().add(new ClosePath());
        GLOBE.getElements().add(new MoveTo(0.35714285714285715 * WIDTH, 0.17857142857142858 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.25 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.2857142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.2857142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.32142857142857145 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5 * HEIGHT,
            0.25 * WIDTH, 0.5 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5357142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.25 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.5714285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6428571428571429 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.75 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.75 * HEIGHT,
            0.5 * WIDTH, 0.7857142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.7857142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.7857142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.25 * WIDTH, 0.7142857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.25 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.25 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.25 * WIDTH, 0.6428571428571429 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.25 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.17857142857142858 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.17857142857142858 * HEIGHT));
        GLOBE.getElements().add(new ClosePath());
        GLOBE.getElements().add(new MoveTo(0.4642857142857143 * WIDTH, 0.25 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT));
        GLOBE.getElements().add(new ClosePath());
        GLOBE.getElements().add(new MoveTo(0.5 * WIDTH, 0.25 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.25 * HEIGHT,
            0.5 * WIDTH, 0.25 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.5 * WIDTH, 0.25 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.25 * HEIGHT,
            0.5 * WIDTH, 0.25 * HEIGHT,
            0.5 * WIDTH, 0.25 * HEIGHT));
        GLOBE.getElements().add(new ClosePath());
        GLOBE.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.35714285714285715 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT));
        GLOBE.getElements().add(new ClosePath());
        GLOBE.getElements().add(new MoveTo(0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        GLOBE.getElements().add(new ClosePath());
        GLOBE.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT));
        GLOBE.getElements().add(new ClosePath());
        GLOBE.getElements().add(new MoveTo(0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT));
        GLOBE.getElements().add(new ClosePath());
        GLOBE.getElements().add(new MoveTo(0.25 * WIDTH, 0.5714285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.5714285714285714 * HEIGHT));
        GLOBE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.5714285714285714 * HEIGHT));
        GLOBE.getElements().add(new ClosePath());
        GLOBE.setStroke(null);

        return GLOBE;
    }

    private static final Path getLocation(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path LOCATION = new Path();
        LOCATION.setFillRule(FillRule.EVEN_ODD);
        LOCATION.getElements().add(new MoveTo(0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        LOCATION.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.35714285714285715 * HEIGHT));
        LOCATION.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5 * HEIGHT,
            0.5 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.5 * WIDTH, 0.9285714285714286 * HEIGHT));
        LOCATION.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.75 * WIDTH, 0.5 * HEIGHT,
            0.75 * WIDTH, 0.35714285714285715 * HEIGHT));
        LOCATION.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        LOCATION.getElements().add(new ClosePath());
        LOCATION.getElements().add(new MoveTo(0.5 * WIDTH, 0.5 * HEIGHT));
        LOCATION.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.5 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT));
        LOCATION.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.25 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        LOCATION.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.25 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        LOCATION.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5 * HEIGHT,
            0.5 * WIDTH, 0.5 * HEIGHT));
        LOCATION.getElements().add(new ClosePath());
        LOCATION.setStroke(null);

        return LOCATION;
    }

    private static final Path getTwitter(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path TWITTER = new Path();
        TWITTER.setFillRule(FillRule.NON_ZERO);
        TWITTER.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.25 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.25 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.25 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.25 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.75 * WIDTH, 0.21428571428571427 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.35714285714285715 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.25 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.25 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.42857142857142855 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.39285714285714285 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.39285714285714285 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.5 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.5714285714285714 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.25 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.7142857142857143 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.75 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.7857142857142857 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.25 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8571428571428571 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT));
        TWITTER.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.25 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT));
        TWITTER.setStroke(null);

        return TWITTER;
    }

    private static final Path getFacebook(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path FACEBOOK = new Path();
        FACEBOOK.setFillRule(FillRule.EVEN_ODD);
        FACEBOOK.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.07142857142857142 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.14285714285714285 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.8571428571428571 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.9285714285714286 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.5 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.5 * WIDTH, 0.9285714285714286 * HEIGHT));
        FACEBOOK.getElements().add(new LineTo(0.5 * WIDTH, 0.6071428571428571 * HEIGHT));
        FACEBOOK.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT));
        FACEBOOK.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT));
        FACEBOOK.getElements().add(new LineTo(0.5 * WIDTH, 0.4642857142857143 * HEIGHT));
        FACEBOOK.getElements().add(new LineTo(0.5 * WIDTH, 0.35714285714285715 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.21428571428571427 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.75 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.75 * WIDTH, 0.21428571428571427 * HEIGHT));
        FACEBOOK.getElements().add(new LineTo(0.75 * WIDTH, 0.35714285714285715 * HEIGHT));
        FACEBOOK.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.4642857142857143 * HEIGHT));
        FACEBOOK.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        FACEBOOK.getElements().add(new LineTo(0.75 * WIDTH, 0.6071428571428571 * HEIGHT));
        FACEBOOK.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.6071428571428571 * HEIGHT));
        FACEBOOK.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.9285714285714286 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.9285714285714286 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.8571428571428571 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.14285714285714285 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.07142857142857142 * HEIGHT));
        FACEBOOK.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.07142857142857142 * HEIGHT));
        FACEBOOK.getElements().add(new ClosePath());
        FACEBOOK.setStroke(null);

        return FACEBOOK;
    }

    private static final Path getGoogle(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path GOOGLE = new Path();
        GOOGLE.setFillRule(FillRule.NON_ZERO);
        GOOGLE.getElements().add(new MoveTo(0.5 * WIDTH, 0.8214285714285714 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.5 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.5 * WIDTH, 0.8214285714285714 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.5714285714285714 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.5 * WIDTH, 0.8214285714285714 * HEIGHT));
        GOOGLE.getElements().add(new MoveTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.25 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        GOOGLE.getElements().add(new ClosePath());
        GOOGLE.getElements().add(new MoveTo(0.6428571428571429 * WIDTH, 0.5714285714285714 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.42857142857142855 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.32142857142857145 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.25 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.17857142857142858 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.17857142857142858 * HEIGHT));
        GOOGLE.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.14285714285714285 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5 * WIDTH, 0.4642857142857143 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5 * WIDTH, 0.4642857142857143 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5 * WIDTH, 0.5 * HEIGHT,
            0.5 * WIDTH, 0.5 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.5714285714285714 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5 * WIDTH, 0.8571428571428571 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7142857142857143 * HEIGHT));
        GOOGLE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.5714285714285714 * HEIGHT));
        GOOGLE.getElements().add(new ClosePath());
        GOOGLE.setStroke(null);

        return GOOGLE;
    }

    private static final Path getLock(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path LOCK = new Path();
        LOCK.setFillRule(FillRule.EVEN_ODD);
        LOCK.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.6071428571428571 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5 * WIDTH, 0.5357142857142857 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6785714285714286 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        LOCK.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.6785714285714286 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6071428571428571 * HEIGHT));
        LOCK.getElements().add(new ClosePath());
        LOCK.getElements().add(new MoveTo(0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.32142857142857145 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.32142857142857145 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.39285714285714285 * HEIGHT));
        LOCK.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT));
        LOCK.getElements().add(new ClosePath());
        LOCK.getElements().add(new MoveTo(0.75 * WIDTH, 0.39285714285714285 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.75 * WIDTH, 0.32142857142857145 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.32142857142857145 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.25 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.25 * WIDTH, 0.39285714285714285 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.5 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.25 * WIDTH, 0.9285714285714286 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.75 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.75 * WIDTH, 0.9285714285714286 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5 * HEIGHT));
        LOCK.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.39285714285714285 * HEIGHT));
        LOCK.getElements().add(new ClosePath());
        LOCK.setStroke(null);

        return LOCK;
    }

    private static final Path getCursor(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path CURSOR = new Path();
        CURSOR.setFillRule(FillRule.EVEN_ODD);
        CURSOR.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.5357142857142857 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.25 * WIDTH, 0.0,
            0.25 * WIDTH, 0.0));
        CURSOR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.0,
            0.21428571428571427 * WIDTH, 0.0,
            0.21428571428571427 * WIDTH, 0.0));
        CURSOR.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.0,
            0.21428571428571427 * WIDTH, 0.0,
            0.21428571428571427 * WIDTH, 0.0));
        CURSOR.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.0,
            0.17857142857142858 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.03571428571428571 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.25 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.25 * WIDTH, 0.8571428571428571 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.9642857142857143 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, HEIGHT,
            0.5714285714285714 * WIDTH, HEIGHT,
            0.6071428571428571 * WIDTH, HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, HEIGHT,
            0.6071428571428571 * WIDTH, HEIGHT,
            0.6071428571428571 * WIDTH, HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, HEIGHT,
            0.6428571428571429 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.9642857142857143 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.9285714285714286 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6428571428571429 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        CURSOR.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5357142857142857 * HEIGHT));
        CURSOR.getElements().add(new ClosePath());
        CURSOR.setStroke(null);

        return CURSOR;
    }

    private static final Path getCursor1(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path CURSOR1 = new Path();
        CURSOR1.setFillRule(FillRule.EVEN_ODD);
        CURSOR1.getElements().add(new MoveTo(0.75 * WIDTH, 0.6071428571428571 * HEIGHT));
        CURSOR1.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.6071428571428571 * HEIGHT));
        CURSOR1.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT));
        CURSOR1.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.14285714285714285 * HEIGHT));
        CURSOR1.getElements().add(new LineTo(0.75 * WIDTH, 0.6071428571428571 * HEIGHT));
        CURSOR1.getElements().add(new ClosePath());
        CURSOR1.setStroke(null);

        return CURSOR1;
    }

    private static final Path getCursor2(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path CURSOR2 = new Path();
        CURSOR2.setFillRule(FillRule.EVEN_ODD);
        CURSOR2.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.75 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.7142857142857143 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.75 * WIDTH, 0.8214285714285714 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.8928571428571429 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.8214285714285714 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.8214285714285714 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.7142857142857143 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.75 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.5 * WIDTH, 0.6785714285714286 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.6785714285714286 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.5714285714285714 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.5714285714285714 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.5 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.75 * WIDTH, 0.5714285714285714 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.5714285714285714 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.6785714285714286 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.6785714285714286 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT));
        CURSOR2.getElements().add(new ClosePath());
        CURSOR2.getElements().add(new MoveTo(0.10714285714285714 * WIDTH, 0.75 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        CURSOR2.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.75 * HEIGHT));
        CURSOR2.getElements().add(new ClosePath());
        CURSOR2.setStroke(null);

        return CURSOR2;
    }

    private static final Path getCrop(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path CROP = new Path();
        CROP.setFillRule(FillRule.EVEN_ODD);
        CROP.getElements().add(new MoveTo(0.25 * WIDTH, 0.75 * HEIGHT));
        CROP.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.75 * HEIGHT));
        CROP.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.6428571428571429 * HEIGHT));
        CROP.getElements().add(new LineTo(0.75 * WIDTH, 0.6428571428571429 * HEIGHT));
        CROP.getElements().add(new LineTo(0.75 * WIDTH, 0.25 * HEIGHT));
        CROP.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        CROP.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.14285714285714285 * HEIGHT));
        CROP.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.25 * HEIGHT));
        CROP.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.25 * HEIGHT));
        CROP.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.10714285714285714 * HEIGHT));
        CROP.getElements().add(new LineTo(0.25 * WIDTH, 0.10714285714285714 * HEIGHT));
        CROP.getElements().add(new LineTo(0.25 * WIDTH, 0.25 * HEIGHT));
        CROP.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.25 * HEIGHT));
        CROP.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        CROP.getElements().add(new LineTo(0.25 * WIDTH, 0.35714285714285715 * HEIGHT));
        CROP.getElements().add(new LineTo(0.25 * WIDTH, 0.75 * HEIGHT));
        CROP.getElements().add(new ClosePath());
        CROP.getElements().add(new MoveTo(0.35714285714285715 * WIDTH, 0.6071428571428571 * HEIGHT));
        CROP.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT));
        CROP.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT));
        CROP.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.6071428571428571 * HEIGHT));
        CROP.getElements().add(new ClosePath());
        CROP.getElements().add(new MoveTo(0.6428571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        CROP.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.6428571428571429 * HEIGHT));
        CROP.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.6428571428571429 * HEIGHT));
        CROP.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        CROP.getElements().add(new ClosePath());
        CROP.getElements().add(new MoveTo(0.75 * WIDTH, 0.7857142857142857 * HEIGHT));
        CROP.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.7857142857142857 * HEIGHT));
        CROP.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.8928571428571429 * HEIGHT));
        CROP.getElements().add(new LineTo(0.75 * WIDTH, 0.8928571428571429 * HEIGHT));
        CROP.getElements().add(new LineTo(0.75 * WIDTH, 0.7857142857142857 * HEIGHT));
        CROP.getElements().add(new ClosePath());
        CROP.setStroke(null);

        return CROP;
    }

    private static final Path getSelection(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path SELECTION = new Path();
        SELECTION.setFillRule(FillRule.EVEN_ODD);
        SELECTION.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.6785714285714286 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION.getElements().add(new ClosePath());
        SELECTION.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT));
        SELECTION.getElements().add(new ClosePath());
        SELECTION.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION.getElements().add(new ClosePath());
        SELECTION.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION.getElements().add(new ClosePath());
        SELECTION.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION.getElements().add(new ClosePath());
        SELECTION.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.6785714285714286 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION.getElements().add(new ClosePath());
        SELECTION.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION.getElements().add(new ClosePath());
        SELECTION.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.39285714285714285 * HEIGHT));
        SELECTION.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT));
        SELECTION.getElements().add(new ClosePath());
        SELECTION.setStroke(null);

        return SELECTION;
    }

    private static final Path getSelection1(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path SELECTION1 = new Path();
        SELECTION1.setFillRule(FillRule.EVEN_ODD);
        SELECTION1.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.75 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.6785714285714286 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.75 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.75 * WIDTH, 0.75 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.75 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.75 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.75 * WIDTH, 0.9285714285714286 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.9285714285714286 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.75 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.75 * HEIGHT));
        SELECTION1.getElements().add(new ClosePath());
        SELECTION1.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new ClosePath());
        SELECTION1.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.25 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.25 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.25 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.07142857142857142 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.75 * WIDTH, 0.07142857142857142 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.75 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.75 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.75 * WIDTH, 0.25 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.25 * HEIGHT));
        SELECTION1.getElements().add(new ClosePath());
        SELECTION1.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION1.getElements().add(new ClosePath());
        SELECTION1.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new ClosePath());
        SELECTION1.getElements().add(new MoveTo(0.25 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.25 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.25 * WIDTH, 0.75 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.75 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.6785714285714286 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.75 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.75 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.9285714285714286 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.25 * WIDTH, 0.9285714285714286 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.25 * WIDTH, 0.8571428571428571 * HEIGHT));
        SELECTION1.getElements().add(new ClosePath());
        SELECTION1.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.25 * WIDTH, 0.25 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.25 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.25 * WIDTH, 0.14285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.25 * WIDTH, 0.07142857142857142 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.07142857142857142 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.25 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.25 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION1.getElements().add(new ClosePath());
        SELECTION1.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.39285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT));
        SELECTION1.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT));
        SELECTION1.getElements().add(new ClosePath());
        SELECTION1.setStroke(null);

        return SELECTION1;
    }

    private static final Path getSelection2(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path SELECTION2 = new Path();
        SELECTION2.setFillRule(FillRule.EVEN_ODD);
        SELECTION2.getElements().add(new MoveTo(0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.7857142857142857 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION2.getElements().add(new ClosePath());
        SELECTION2.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.6428571428571429 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION2.getElements().add(new ClosePath());
        SELECTION2.getElements().add(new MoveTo(0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION2.getElements().add(new ClosePath());
        SELECTION2.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.21428571428571427 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION2.getElements().add(new ClosePath());
        SELECTION2.getElements().add(new MoveTo(0.25 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.75 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.75 * WIDTH, 0.6785714285714286 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.25 * WIDTH, 0.6785714285714286 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.25 * WIDTH, 0.32142857142857145 * HEIGHT));
        SELECTION2.getElements().add(new ClosePath());
        SELECTION2.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.75 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.75 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.25 * HEIGHT));
        SELECTION2.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT));
        SELECTION2.getElements().add(new ClosePath());
        SELECTION2.setStroke(null);

        return SELECTION2;
    }

    private static final Path getSelection3(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path SELECTION3 = new Path();
        SELECTION3.setFillRule(FillRule.EVEN_ODD);
        SELECTION3.getElements().add(new MoveTo(0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.7857142857142857 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION3.getElements().add(new ClosePath());
        SELECTION3.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.6428571428571429 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        SELECTION3.getElements().add(new ClosePath());
        SELECTION3.getElements().add(new MoveTo(0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION3.getElements().add(new ClosePath());
        SELECTION3.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.21428571428571427 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        SELECTION3.getElements().add(new ClosePath());
        SELECTION3.getElements().add(new MoveTo(0.21428571428571427 * WIDTH, 0.2857142857142857 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.7142857142857143 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        SELECTION3.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.2857142857142857 * HEIGHT));
        SELECTION3.getElements().add(new ClosePath());
        SELECTION3.setStroke(null);

        return SELECTION3;
    }

    private static final Path getPencil(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path PENCIL = new Path();
        PENCIL.setFillRule(FillRule.EVEN_ODD);
        PENCIL.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.07142857142857142 * HEIGHT));
        PENCIL.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.17857142857142858 * HEIGHT));
        PENCIL.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT));
        PENCIL.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.2857142857142857 * HEIGHT));
        PENCIL.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.25 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.21428571428571427 * HEIGHT));
        PENCIL.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.07142857142857142 * HEIGHT));
        PENCIL.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.75 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.07142857142857142 * HEIGHT));
        PENCIL.getElements().add(new ClosePath());
        PENCIL.getElements().add(new MoveTo(0.5714285714285714 * WIDTH, 0.25 * HEIGHT));
        PENCIL.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.6428571428571429 * HEIGHT));
        PENCIL.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.8571428571428571 * HEIGHT));
        PENCIL.getElements().add(new LineTo(0.75 * WIDTH, 0.42857142857142855 * HEIGHT));
        PENCIL.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.25 * HEIGHT));
        PENCIL.getElements().add(new ClosePath());
        PENCIL.getElements().add(new MoveTo(0.03571428571428571 * WIDTH, 0.9642857142857143 * HEIGHT));
        PENCIL.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT));
        PENCIL.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        PENCIL.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.9642857142857143 * HEIGHT));
        PENCIL.getElements().add(new ClosePath());
        PENCIL.setStroke(null);

        return PENCIL;
    }

    private static final Path getBrush(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path BRUSH = new Path();
        BRUSH.setFillRule(FillRule.EVEN_ODD);
        BRUSH.getElements().add(new MoveTo(0.07142857142857142 * WIDTH, 0.9285714285714286 * HEIGHT));
        BRUSH.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.7142857142857143 * HEIGHT));
        BRUSH.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT));
        BRUSH.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.25 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT));
        BRUSH.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.9285714285714286 * HEIGHT));
        BRUSH.getElements().add(new ClosePath());
        BRUSH.getElements().add(new MoveTo(0.4642857142857143 * WIDTH, 0.7142857142857143 * HEIGHT));
        BRUSH.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.7142857142857143 * HEIGHT,
            WIDTH, 0.07142857142857142 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.03571428571428571 * HEIGHT));
        BRUSH.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.0,
            0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT));
        BRUSH.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.7142857142857143 * HEIGHT));
        BRUSH.getElements().add(new ClosePath());
        BRUSH.setStroke(null);

        return BRUSH;
    }

    private static final Path getBigBrush(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path BIG_BRUSH = new Path();
        BIG_BRUSH.setFillRule(FillRule.EVEN_ODD);
        BIG_BRUSH.getElements().add(new MoveTo(0.03571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.9642857142857143 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.25 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.25 * WIDTH, 0.39285714285714285 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT));
        BIG_BRUSH.getElements().add(new ClosePath());
        BIG_BRUSH.getElements().add(new MoveTo(0.9642857142857143 * WIDTH, 0.03571428571428571 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.0,
            0.75 * WIDTH, 0.10714285714285714 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.5 * WIDTH, 0.35714285714285715 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT));
        BIG_BRUSH.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.5 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            WIDTH, 0.2857142857142857 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.03571428571428571 * HEIGHT));
        BIG_BRUSH.getElements().add(new ClosePath());
        BIG_BRUSH.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.07142857142857142 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.10714285714285714 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.14285714285714285 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.10714285714285714 * HEIGHT));
        BIG_BRUSH.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.07142857142857142 * HEIGHT));
        BIG_BRUSH.getElements().add(new ClosePath());
        BIG_BRUSH.setStroke(null);

        return BIG_BRUSH;
    }

    private static final Path getPen(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path PEN = new Path();
        PEN.setFillRule(FillRule.EVEN_ODD);
        PEN.getElements().add(new MoveTo(0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8571428571428571 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8928571428571429 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.9285714285714286 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.9285714285714286 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8928571428571429 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8571428571428571 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT));
        PEN.getElements().add(new ClosePath());
        PEN.getElements().add(new MoveTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5 * WIDTH, 0.6071428571428571 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT));
        PEN.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.07142857142857142 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT));
        PEN.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.5357142857142857 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT));
        PEN.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        PEN.getElements().add(new LineTo(0.5 * WIDTH, 0.7857142857142857 * HEIGHT));
        PEN.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.7142857142857143 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.75 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.75 * WIDTH, 0.5357142857142857 * HEIGHT));
        PEN.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.21428571428571427 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.14285714285714285 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        PEN.getElements().add(new LineTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        PEN.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        PEN.getElements().add(new ClosePath());
        PEN.getElements().add(new MoveTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        PEN.getElements().add(new LineTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        PEN.getElements().add(new LineTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        PEN.getElements().add(new LineTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        PEN.getElements().add(new ClosePath());
        PEN.setStroke(null);

        return PEN;
    }

    private static final Path getPen1(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path PEN1 = new Path();
        PEN1.setFillRule(FillRule.EVEN_ODD);
        PEN1.getElements().add(new MoveTo(0.10714285714285714 * WIDTH, HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, HEIGHT,
            0.17857142857142858 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.9642857142857143 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.9642857142857143 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.8571428571428571 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6428571428571429 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.75 * WIDTH, 0.6071428571428571 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.6428571428571429 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.5 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.6071428571428571 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.9285714285714286 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.9285714285714286 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.9285714285714286 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.07142857142857142 * WIDTH, HEIGHT,
            0.10714285714285714 * WIDTH, HEIGHT));
        PEN1.getElements().add(new ClosePath());
        PEN1.getElements().add(new MoveTo(0.5714285714285714 * WIDTH, 0.25 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.25 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.25 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.21428571428571427 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.17857142857142858 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.03571428571428571 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.03571428571428571 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.07142857142857142 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.10714285714285714 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.25 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.25 * HEIGHT));
        PEN1.getElements().add(new ClosePath());
        PEN1.getElements().add(new MoveTo(0.25 * WIDTH, 0.5 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.39285714285714285 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.35714285714285715 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.35714285714285715 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT));
        PEN1.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT));
        PEN1.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.32142857142857145 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.25 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.25 * HEIGHT));
        PEN1.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT));
        PEN1.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.17857142857142858 * HEIGHT));
        PEN1.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.14285714285714285 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.25 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.2857142857142857 * HEIGHT));
        PEN1.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.6428571428571429 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.8214285714285714 * HEIGHT));
        PEN1.getElements().add(new LineTo(0.25 * WIDTH, 0.5 * HEIGHT));
        PEN1.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.5 * HEIGHT,
            0.25 * WIDTH, 0.5 * HEIGHT,
            0.25 * WIDTH, 0.5 * HEIGHT));
        PEN1.getElements().add(new ClosePath());
        PEN1.getElements().add(new MoveTo(0.25 * WIDTH, 0.5 * HEIGHT));
        PEN1.getElements().add(new LineTo(0.25 * WIDTH, 0.5 * HEIGHT));
        PEN1.getElements().add(new LineTo(0.25 * WIDTH, 0.5 * HEIGHT));
        PEN1.getElements().add(new LineTo(0.25 * WIDTH, 0.5 * HEIGHT));
        PEN1.getElements().add(new ClosePath());
        PEN1.setStroke(null);

        return PEN1;
    }

    private static final Path getLine(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path LINE = new Path();
        LINE.setFillRule(FillRule.EVEN_ODD);
        LINE.getElements().add(new MoveTo(0.7857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT));
        LINE.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT));
        LINE.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT));
        LINE.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT));
        LINE.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT));
        LINE.getElements().add(new ClosePath());
        LINE.setStroke(null);

        return LINE;
    }

    private static final Path getEyeDropper(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path EYEDROPPER = new Path();
        EYEDROPPER.setFillRule(FillRule.EVEN_ODD);
        EYEDROPPER.getElements().add(new MoveTo(0.5 * WIDTH, 0.39285714285714285 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.5 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.5 * WIDTH, 0.39285714285714285 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.39285714285714285 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.25 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.25 * WIDTH, 0.6428571428571429 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.25 * WIDTH, 0.6428571428571429 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.25 * WIDTH, 0.6428571428571429 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.5 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.39285714285714285 * HEIGHT));
        EYEDROPPER.getElements().add(new ClosePath());
        EYEDROPPER.getElements().add(new MoveTo(0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        EYEDROPPER.getElements().add(new LineTo(0.25 * WIDTH, 0.5714285714285714 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.25 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6785714285714286 * HEIGHT));
        EYEDROPPER.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        EYEDROPPER.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        EYEDROPPER.getElements().add(new ClosePath());
        EYEDROPPER.getElements().add(new MoveTo(0.21428571428571427 * WIDTH, 0.75 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.75 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.9285714285714286 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.25 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.25 * WIDTH, 0.8571428571428571 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT));
        EYEDROPPER.getElements().add(new ClosePath());
        EYEDROPPER.getElements().add(new MoveTo(0.7857142857142857 * WIDTH, 0.07142857142857142 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.10714285714285714 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.10714285714285714 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.17857142857142858 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT));
        EYEDROPPER.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.5 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5 * HEIGHT,
            0.75 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.39285714285714285 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.75 * WIDTH, 0.2857142857142857 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.21428571428571427 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.21428571428571427 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.17857142857142858 * HEIGHT));
        EYEDROPPER.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.07142857142857142 * HEIGHT));
        EYEDROPPER.getElements().add(new ClosePath());
        EYEDROPPER.setStroke(null);

        return EYEDROPPER;
    }

    private static final Path getEraser(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path ERASER = new Path();
        ERASER.setFillRule(FillRule.EVEN_ODD);
        ERASER.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.6785714285714286 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.6785714285714286 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.6428571428571429 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT));
        ERASER.getElements().add(new ClosePath());
        ERASER.getElements().add(new MoveTo(0.5357142857142857 * WIDTH, 0.8928571428571429 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8928571428571429 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.8928571428571429 * HEIGHT));
        ERASER.getElements().add(new ClosePath());
        ERASER.getElements().add(new MoveTo(0.6071428571428571 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8214285714285714 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new ClosePath());
        ERASER.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.7857142857142857 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.7857142857142857 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.75 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.75 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.75 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT));
        ERASER.getElements().add(new ClosePath());
        ERASER.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.8214285714285714 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new ClosePath());
        ERASER.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.75 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.75 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.75 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.75 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.75 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.75 * HEIGHT));
        ERASER.getElements().add(new ClosePath());
        ERASER.getElements().add(new MoveTo(0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new ClosePath());
        ERASER.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new ClosePath());
        ERASER.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.75 * WIDTH, 0.7857142857142857 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.75 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.75 * WIDTH, 0.7857142857142857 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.75 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.75 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.75 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        ERASER.getElements().add(new ClosePath());
        ERASER.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.10714285714285714 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.25 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8928571428571429 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.4642857142857143 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        ERASER.getElements().add(new ClosePath());
        ERASER.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.25 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.25 * WIDTH, 0.8214285714285714 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.6428571428571429 * HEIGHT));
        ERASER.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.39285714285714285 * HEIGHT));
        ERASER.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        ERASER.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT));
        ERASER.getElements().add(new ClosePath());
        ERASER.setStroke(null);

        return ERASER;
    }

    private static final Path getScissors(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path SCISSORS = new Path();
        SCISSORS.setFillRule(FillRule.EVEN_ODD);
        SCISSORS.getElements().add(new MoveTo(0.4642857142857143 * WIDTH, 0.10714285714285714 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.25 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.25 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.10714285714285714 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.10714285714285714 * HEIGHT));
        SCISSORS.getElements().add(new ClosePath());
        SCISSORS.getElements().add(new MoveTo(0.75 * WIDTH, 0.39285714285714285 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5 * HEIGHT,
            0.75 * WIDTH, 0.5357142857142857 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.5 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.75 * WIDTH, 0.39285714285714285 * HEIGHT));
        SCISSORS.getElements().add(new ClosePath());
        SCISSORS.getElements().add(new MoveTo(0.9285714285714286 * WIDTH, 0.5714285714285714 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5714285714285714 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.5 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.5 * HEIGHT,
            0.5 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5 * WIDTH, 0.8571428571428571 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.9642857142857143 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.5 * WIDTH, 0.5 * HEIGHT,
            0.5 * WIDTH, 0.5 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.5 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.6428571428571429 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.5 * HEIGHT,
            0.5 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.32142857142857145 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.2857142857142857 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.07142857142857142 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.07142857142857142 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.35714285714285715 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.35714285714285715 * HEIGHT));
        SCISSORS.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.5 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5714285714285714 * HEIGHT));
        SCISSORS.getElements().add(new ClosePath());
        SCISSORS.setStroke(null);

        return SCISSORS;
    }

    private static final Path getSmudge(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path SMUDGE = new Path();
        SMUDGE.setFillRule(FillRule.EVEN_ODD);
        SMUDGE.getElements().add(new MoveTo(0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT));
        SMUDGE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.6785714285714286 * HEIGHT));
        SMUDGE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.5 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5 * WIDTH, 0.8571428571428571 * HEIGHT));
        SMUDGE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.42857142857142855 * HEIGHT));
        SMUDGE.getElements().add(new ClosePath());
        SMUDGE.getElements().add(new MoveTo(0.21428571428571427 * WIDTH, 0.6428571428571429 * HEIGHT));
        SMUDGE.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.5 * WIDTH, 0.9285714285714286 * HEIGHT));
        SMUDGE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT));
        SMUDGE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.5 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        SMUDGE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6428571428571429 * HEIGHT));
        SMUDGE.getElements().add(new ClosePath());        
        SMUDGE.setStroke(null);
        
        return SMUDGE;
    }

    private static final Path getContrast(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path CONTRAST = new Path();
        CONTRAST.setFillRule(FillRule.EVEN_ODD);
        CONTRAST.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.5 * HEIGHT));
        CONTRAST.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.14285714285714285 * HEIGHT));
        CONTRAST.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5 * WIDTH, 0.8571428571428571 * HEIGHT));
        CONTRAST.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5 * HEIGHT));
        CONTRAST.getElements().add(new ClosePath());
        CONTRAST.getElements().add(new MoveTo(0.07142857142857142 * WIDTH, 0.5 * HEIGHT));
        CONTRAST.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.75 * HEIGHT,
            0.25 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.5 * WIDTH, 0.9285714285714286 * HEIGHT));
        CONTRAST.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.75 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5 * HEIGHT));
        CONTRAST.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.25 * HEIGHT,
            0.75 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        CONTRAST.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.25 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.5 * HEIGHT));
        CONTRAST.getElements().add(new ClosePath());        
        CONTRAST.setStroke(null);
        
        return CONTRAST;
    }

    private static final Path getLayers(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path LAYERS = new Path();
        LAYERS.setFillRule(FillRule.EVEN_ODD);
        LAYERS.getElements().add(new MoveTo(0.25 * WIDTH, 0.7142857142857143 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.75 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.75 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.75 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.75 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.75 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.75 * WIDTH, 0.6785714285714286 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.75 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.75 * WIDTH, 0.32142857142857145 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.75 * WIDTH, 0.25 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.25 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.25 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.2857142857142857 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.6428571428571429 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.25 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.25 * WIDTH, 0.7142857142857143 * HEIGHT));
        LAYERS.getElements().add(new ClosePath());
        LAYERS.getElements().add(new MoveTo(0.25 * WIDTH, 0.17857142857142858 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.25 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.6071428571428571 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.25 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.25 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.17857142857142858 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT));
        LAYERS.getElements().add(new ClosePath());
        LAYERS.getElements().add(new MoveTo(0.32142857142857145 * WIDTH, 0.7857142857142857 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.75 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.75 * WIDTH, 0.8214285714285714 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.75 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.75 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7857142857142857 * HEIGHT));
        LAYERS.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.7857142857142857 * HEIGHT));
        LAYERS.getElements().add(new ClosePath());        
        LAYERS.setStroke(null);
        
        return LAYERS;
    }

    private static final Path getBoolean(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path BOOLEAN = new Path();
        BOOLEAN.setFillRule(FillRule.EVEN_ODD);
        BOOLEAN.getElements().add(new MoveTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.42857142857142855 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.5 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.7142857142857143 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.5 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.5 * WIDTH, 0.7857142857142857 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.75 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.7142857142857143 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.5 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.5 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.5 * WIDTH, 0.42857142857142855 * HEIGHT));
        BOOLEAN.getElements().add(new ClosePath());
        BOOLEAN.getElements().add(new MoveTo(0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.75 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.75 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.75 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.75 * WIDTH, 0.8214285714285714 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.75 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.75 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.4642857142857143 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.39285714285714285 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT));
        BOOLEAN.getElements().add(new ClosePath());
        BOOLEAN.getElements().add(new MoveTo(0.25 * WIDTH, 0.17857142857142858 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.25 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5357142857142857 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.25 * WIDTH, 0.6071428571428571 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6071428571428571 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.42857142857142855 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.35714285714285715 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.25 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.25 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        BOOLEAN.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT));
        BOOLEAN.getElements().add(new ClosePath());        
        BOOLEAN.setStroke(null);
        
        return BOOLEAN;
    }

    private static final Path getText(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path TEXT = new Path();
        TEXT.setFillRule(FillRule.EVEN_ODD);
        TEXT.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.07142857142857142 * HEIGHT));
        TEXT.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.07142857142857142 * HEIGHT));
        TEXT.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.25 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.14285714285714285 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.8928571428571429 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.8928571428571429 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.9285714285714286 * HEIGHT));
        TEXT.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.9285714285714286 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8928571428571429 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.8928571428571429 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.14285714285714285 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.17857142857142858 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.25 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.32142857142857145 * HEIGHT));
        TEXT.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT));
        TEXT.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.07142857142857142 * HEIGHT));
        TEXT.getElements().add(new ClosePath());        
        TEXT.setStroke(null);
        
        return TEXT;
    }

    private static final Path getAlignLeft(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        final Path ALIGNLEFT = new Path();
        ALIGNLEFT.setFillRule(FillRule.EVEN_ODD);
        ALIGNLEFT.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.75 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.7857142857142857 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.75 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.75 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.75 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.75 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.75 * HEIGHT));
        ALIGNLEFT.getElements().add(new ClosePath());
        ALIGNLEFT.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6428571428571429 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6071428571428571 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT));
        ALIGNLEFT.getElements().add(new ClosePath());
        ALIGNLEFT.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT));
        ALIGNLEFT.getElements().add(new ClosePath());
        ALIGNLEFT.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.35714285714285715 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT));
        ALIGNLEFT.getElements().add(new ClosePath());
        ALIGNLEFT.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.21428571428571427 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.25 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.25 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.25 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.25 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.25 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.25 * HEIGHT,
            0.75 * WIDTH, 0.25 * HEIGHT,
            0.75 * WIDTH, 0.21428571428571427 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.75 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        ALIGNLEFT.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT));
        ALIGNLEFT.getElements().add(new ClosePath());        
        ALIGNLEFT.setStroke(null);
        
        return ALIGNLEFT;
    }

    private static final Path getCenter(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path CENTER = new Path();
        CENTER.setFillRule(FillRule.EVEN_ODD);
        CENTER.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.75 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.75 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.75 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.7857142857142857 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8214285714285714 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.7857142857142857 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.75 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.75 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.75 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.75 * HEIGHT));
        CENTER.getElements().add(new ClosePath());
        CENTER.getElements().add(new MoveTo(0.25 * WIDTH, 0.6071428571428571 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6428571428571429 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.75 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.75 * WIDTH, 0.6785714285714286 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.75 * WIDTH, 0.6071428571428571 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.25 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.25 * WIDTH, 0.6071428571428571 * HEIGHT));
        CENTER.getElements().add(new ClosePath());
        CENTER.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.39285714285714285 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.39285714285714285 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.35714285714285715 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT));
        CENTER.getElements().add(new ClosePath());
        CENTER.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT));
        CENTER.getElements().add(new ClosePath());
        CENTER.getElements().add(new MoveTo(0.21428571428571427 * WIDTH, 0.17857142857142858 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.25 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.25 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.25 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.25 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.25 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.25 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.25 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.21428571428571427 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        CENTER.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.17857142857142858 * HEIGHT));
        CENTER.getElements().add(new ClosePath());        
        CENTER.setStroke(null);
        
        return CENTER;
    }

    private static final Path getAlignRight(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path ALIGNRIGHT = new Path();
        ALIGNRIGHT.setFillRule(FillRule.EVEN_ODD);
        ALIGNRIGHT.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.75 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.75 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.75 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.75 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.7857142857142857 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.75 * HEIGHT));
        ALIGNRIGHT.getElements().add(new ClosePath());
        ALIGNRIGHT.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6071428571428571 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6785714285714286 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.6428571428571429 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        ALIGNRIGHT.getElements().add(new ClosePath());
        ALIGNRIGHT.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        ALIGNRIGHT.getElements().add(new ClosePath());
        ALIGNRIGHT.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.39285714285714285 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        ALIGNRIGHT.getElements().add(new ClosePath());
        ALIGNRIGHT.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.17857142857142858 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.21428571428571427 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.25 * HEIGHT,
            0.25 * WIDTH, 0.25 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.25 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.25 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.25 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.25 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.25 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.25 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT));
        ALIGNRIGHT.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.17857142857142858 * HEIGHT));
        ALIGNRIGHT.getElements().add(new ClosePath());        
        ALIGNRIGHT.setStroke(null);
        
        return ALIGNRIGHT;
    }

    private static final Path getJustified(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path JUSTIFIED = new Path();
        JUSTIFIED.setFillRule(FillRule.EVEN_ODD);
        JUSTIFIED.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.75 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.75 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.75 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.75 * HEIGHT));
        JUSTIFIED.getElements().add(new ClosePath());
        JUSTIFIED.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6071428571428571 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6428571428571429 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.6428571428571429 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        JUSTIFIED.getElements().add(new ClosePath());
        JUSTIFIED.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.4642857142857143 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.5357142857142857 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        JUSTIFIED.getElements().add(new ClosePath());
        JUSTIFIED.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.39285714285714285 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        JUSTIFIED.getElements().add(new ClosePath());
        JUSTIFIED.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.17857142857142858 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.21428571428571427 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.25 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.25 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.25 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.25 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.25 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.25 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.25 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT));
        JUSTIFIED.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.17857142857142858 * HEIGHT));
        JUSTIFIED.getElements().add(new ClosePath());        
        JUSTIFIED.setStroke(null);
        
        return JUSTIFIED;
    }

    private static final Path getBulb(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path BULB = new Path();
        BULB.setFillRule(FillRule.EVEN_ODD);
        BULB.getElements().add(new MoveTo(0.5 * WIDTH, 0.03571428571428571 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.5 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.8928571428571429 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.9285714285714286 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.5 * WIDTH, 0.9642857142857143 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.9285714285714286 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8928571428571429 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6428571428571429 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.5 * WIDTH, 0.03571428571428571 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.5 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.5 * WIDTH, 0.03571428571428571 * HEIGHT));
        BULB.getElements().add(new ClosePath());
        BULB.getElements().add(new MoveTo(0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.75 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.75 * WIDTH, 0.35714285714285715 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.5 * WIDTH, 0.7142857142857143 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6071428571428571 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.35714285714285715 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        BULB.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT));
        BULB.getElements().add(new ClosePath());        
        BULB.setStroke(null);
        
        return BULB;
    }

    private static final Path getBulbOn(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path BULB_ON = new Path();
        BULB_ON.setFillRule(FillRule.EVEN_ODD);
        BULB_ON.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.10714285714285714 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.75 * WIDTH, 0.17857142857142858 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.21428571428571427 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.10714285714285714 * HEIGHT));
        BULB_ON.getElements().add(new ClosePath());
        BULB_ON.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.7142857142857143 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.75 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.7142857142857143 * HEIGHT));
        BULB_ON.getElements().add(new ClosePath());
        BULB_ON.getElements().add(new MoveTo(0.14285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.21428571428571427 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.25 * WIDTH, 0.17857142857142858 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.10714285714285714 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        BULB_ON.getElements().add(new ClosePath());
        BULB_ON.getElements().add(new MoveTo(0.7857142857142857 * WIDTH, 0.75 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.75 * HEIGHT));
        BULB_ON.getElements().add(new ClosePath());
        BULB_ON.getElements().add(new MoveTo(0.03571428571428571 * WIDTH, 0.5 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.5 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.14285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.5 * HEIGHT));
        BULB_ON.getElements().add(new ClosePath());
        BULB_ON.getElements().add(new MoveTo(0.8571428571428571 * WIDTH, 0.5 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.9642857142857143 * WIDTH, 0.5 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.9642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.5 * HEIGHT));
        BULB_ON.getElements().add(new ClosePath());
        BULB_ON.getElements().add(new MoveTo(0.5357142857142857 * WIDTH, 0.14285714285714285 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.03571428571428571 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.03571428571428571 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.14285714285714285 * HEIGHT));
        BULB_ON.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.14285714285714285 * HEIGHT));
        BULB_ON.getElements().add(new ClosePath());
        BULB_ON.getElements().add(new MoveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.8928571428571429 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.9285714285714286 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.5 * WIDTH, 0.9642857142857143 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.9285714285714286 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8928571428571429 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        BULB_ON.getElements().add(new ClosePath());
        BULB_ON.getElements().add(new MoveTo(0.5 * WIDTH, 0.25 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.25 * HEIGHT,
            0.75 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.75 * WIDTH, 0.4642857142857143 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.75 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.75 * HEIGHT,
            0.5 * WIDTH, 0.75 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.75 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.75 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.75 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.6785714285714286 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.25 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.4642857142857143 * HEIGHT));
        BULB_ON.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.25 * HEIGHT,
            0.5 * WIDTH, 0.25 * HEIGHT));
        BULB_ON.getElements().add(new ClosePath());        
        BULB_ON.setStroke(null);
        
        return BULB_ON;
    }

    private static final Path getGauge(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path GAUGE = new Path();
        GAUGE.setFillRule(FillRule.EVEN_ODD);
        GAUGE.getElements().add(new MoveTo(0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.75 * WIDTH, 0.6785714285714286 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6428571428571429 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.7857142857142857 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.5 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.5 * WIDTH, 0.8214285714285714 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.7857142857142857 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.7857142857142857 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.25 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.6428571428571429 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        GAUGE.getElements().add(new ClosePath());
        GAUGE.getElements().add(new MoveTo(0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.25 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.75 * HEIGHT,
            0.25 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.5 * WIDTH, 0.9285714285714286 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.75 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.25 * HEIGHT,
            0.75 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        GAUGE.getElements().add(new ClosePath());
        GAUGE.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.2857142857142857 * HEIGHT));
        GAUGE.getElements().add(new ClosePath());
        GAUGE.getElements().add(new MoveTo(0.2857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.39285714285714285 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.39285714285714285 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.2857142857142857 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT));
        GAUGE.getElements().add(new ClosePath());
        GAUGE.getElements().add(new MoveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5 * WIDTH, 0.32142857142857145 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5 * WIDTH, 0.32142857142857145 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5 * WIDTH, 0.32142857142857145 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.5 * WIDTH, 0.21428571428571427 * HEIGHT));
        GAUGE.getElements().add(new ClosePath());
        GAUGE.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.5 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.5 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.5 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.4642857142857143 * HEIGHT));
        GAUGE.getElements().add(new ClosePath());
        GAUGE.getElements().add(new MoveTo(0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.5 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.5 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.4642857142857143 * HEIGHT));
        GAUGE.getElements().add(new ClosePath());
        GAUGE.getElements().add(new MoveTo(0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.6071428571428571 * HEIGHT));
        GAUGE.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.5714285714285714 * HEIGHT));
        GAUGE.getElements().add(new ClosePath());        
        GAUGE.setStroke(null);
        
        return GAUGE;
    }

    private static final Path getUnlock(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path UNLOCK = new Path();
        UNLOCK.setFillRule(FillRule.EVEN_ODD);
        UNLOCK.getElements().add(new MoveTo(0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.5357142857142857 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.6071428571428571 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        UNLOCK.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.8214285714285714 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6785714285714286 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6071428571428571 * HEIGHT));
        UNLOCK.getElements().add(new ClosePath());
        UNLOCK.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT));
        UNLOCK.getElements().add(new LineTo(0.9642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.07142857142857142 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.39285714285714285 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.5 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.5 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.9285714285714286 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.9285714285714286 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.75 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.75 * WIDTH, 0.8214285714285714 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.75 * WIDTH, 0.5 * HEIGHT,
            0.75 * WIDTH, 0.5 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.39285714285714285 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.32142857142857145 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.14285714285714285 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.32142857142857145 * HEIGHT));
        UNLOCK.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.39285714285714285 * HEIGHT));
        UNLOCK.getElements().add(new ClosePath());        
        UNLOCK.setStroke(null);
        
        return UNLOCK;
    }

    private static final Path getTags(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path TAGS = new Path();
        TAGS.setFillRule(FillRule.EVEN_ODD);
        TAGS.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.10714285714285714 * HEIGHT));
        TAGS.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT));
        TAGS.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.5 * HEIGHT));
        TAGS.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.03571428571428571 * HEIGHT));
        TAGS.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.03571428571428571 * HEIGHT));
        TAGS.getElements().add(new LineTo(0.10714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT));
        TAGS.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.10714285714285714 * HEIGHT));
        TAGS.getElements().add(new ClosePath());
        TAGS.getElements().add(new MoveTo(0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        TAGS.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        TAGS.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        TAGS.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.9642857142857143 * HEIGHT));
        TAGS.getElements().add(new LineTo(0.8571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        TAGS.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT));
        TAGS.getElements().add(new ClosePath());
        TAGS.getElements().add(new MoveTo(0.21428571428571427 * WIDTH, 0.39285714285714285 * HEIGHT));
        TAGS.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.32142857142857145 * HEIGHT));
        TAGS.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.25 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.25 * HEIGHT));
        TAGS.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.25 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT));
        TAGS.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.25 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.39285714285714285 * HEIGHT));
        TAGS.getElements().add(new ClosePath());        
        TAGS.setStroke(null);
        
        return TAGS;
    }

    private static final Path getMonitor(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;
        
        final Path MONITOR = new Path();
        MONITOR.setFillRule(FillRule.EVEN_ODD);
        MONITOR.getElements().add(new MoveTo(0.9285714285714286 * WIDTH, 0.75 * HEIGHT));
        MONITOR.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.75 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.75 * HEIGHT));
        MONITOR.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.75 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.8928571428571429 * HEIGHT));
        MONITOR.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.8928571428571429 * HEIGHT));
        MONITOR.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.9285714285714286 * HEIGHT));
        MONITOR.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.9285714285714286 * HEIGHT));
        MONITOR.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.9285714285714286 * HEIGHT));
        MONITOR.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.9285714285714286 * HEIGHT));
        MONITOR.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.8928571428571429 * HEIGHT));
        MONITOR.getElements().add(new LineTo(0.35714285714285715 * WIDTH, 0.8928571428571429 * HEIGHT));
        MONITOR.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.75 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.75 * HEIGHT));
        MONITOR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.75 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.75 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.75 * HEIGHT));
        MONITOR.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.0, 0.7142857142857143 * HEIGHT,
            0.0, 0.6785714285714286 * HEIGHT));
        MONITOR.getElements().add(new CubicCurveTo(0.0, 0.6785714285714286 * HEIGHT,
            0.0, 0.2857142857142857 * HEIGHT,
            0.0, 0.10714285714285714 * HEIGHT));
        MONITOR.getElements().add(new CubicCurveTo(0.0, 0.07142857142857142 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.03571428571428571 * HEIGHT));
        MONITOR.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.03571428571428571 * HEIGHT));
        MONITOR.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.03571428571428571 * HEIGHT,
            WIDTH, 0.07142857142857142 * HEIGHT,
            WIDTH, 0.10714285714285714 * HEIGHT));
        MONITOR.getElements().add(new CubicCurveTo(WIDTH, 0.10714285714285714 * HEIGHT,
            WIDTH, 0.5 * HEIGHT,
            WIDTH, 0.6785714285714286 * HEIGHT));
        MONITOR.getElements().add(new CubicCurveTo(WIDTH, 0.7142857142857143 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.75 * HEIGHT));
        MONITOR.getElements().add(new ClosePath());
        MONITOR.getElements().add(new MoveTo(0.9285714285714286 * WIDTH, 0.10714285714285714 * HEIGHT));
        MONITOR.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.10714285714285714 * HEIGHT));
        MONITOR.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.6785714285714286 * HEIGHT));
        MONITOR.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.6785714285714286 * HEIGHT));
        MONITOR.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.10714285714285714 * HEIGHT));
        MONITOR.getElements().add(new ClosePath());
        MONITOR.setStroke(null);

        return MONITOR;
    }

    private static final Path getPlane(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path PLANE = new Path();
        PLANE.setFillRule(FillRule.NON_ZERO);
        PLANE.getElements().add(new MoveTo(0.42857142857142855 * WIDTH, 0.07142857142857142 * HEIGHT));
        PLANE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.07142857142857142 * HEIGHT));
        PLANE.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.9642857142857143 * WIDTH, 0.6071428571428571 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.9642857142857143 * WIDTH, 0.7142857142857143 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.5714285714285714 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.5714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.8571428571428571 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.6785714285714286 * WIDTH, 0.9285714285714286 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.5 * WIDTH, 0.8928571428571429 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.9285714285714286 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.32142857142857145 * WIDTH, 0.8571428571428571 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.7857142857142857 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.5714285714285714 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.6071428571428571 * HEIGHT));
        PLANE.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.35714285714285715 * HEIGHT));
        PLANE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.07142857142857142 * HEIGHT));
        PLANE.getElements().add(new ClosePath());
        PLANE.setStroke(null);

        return PLANE;
    }

    private static final Path getTrain(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path TRAIN = new Path();
        TRAIN.setFillRule(FillRule.EVEN_ODD);
        TRAIN.getElements().add(new MoveTo(0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.5 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.03571428571428571 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.07142857142857142 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.4642857142857143 * WIDTH, 0.10714285714285714 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.4642857142857143 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        TRAIN.getElements().add(new ClosePath());
        TRAIN.getElements().add(new MoveTo(0.6071428571428571 * WIDTH, 0.07142857142857142 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.03571428571428571 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.03571428571428571 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.07142857142857142 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.5357142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5357142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.5714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.07142857142857142 * HEIGHT));
        TRAIN.getElements().add(new ClosePath());
        TRAIN.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.14285714285714285 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.9642857142857143 * HEIGHT));
        TRAIN.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.9642857142857143 * HEIGHT));
        TRAIN.getElements().add(new LineTo(0.39285714285714285 * WIDTH, 0.8214285714285714 * HEIGHT));
        TRAIN.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.8214285714285714 * HEIGHT));
        TRAIN.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.9642857142857143 * HEIGHT));
        TRAIN.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.9642857142857143 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.9642857142857143 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.25 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.25 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.75 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.14285714285714285 * HEIGHT));
        TRAIN.getElements().add(new ClosePath());
        TRAIN.getElements().add(new MoveTo(0.32142857142857145 * WIDTH, 0.75 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.75 * HEIGHT,
            0.25 * WIDTH, 0.75 * HEIGHT,
            0.25 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.35714285714285715 * WIDTH, 0.75 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.75 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.75 * HEIGHT));
        TRAIN.getElements().add(new ClosePath());
        TRAIN.getElements().add(new MoveTo(0.6785714285714286 * WIDTH, 0.75 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.75 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.75 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.6428571428571429 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.75 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.75 * WIDTH, 0.7142857142857143 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.75 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.75 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.75 * HEIGHT));
        TRAIN.getElements().add(new ClosePath());
        TRAIN.getElements().add(new MoveTo(0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.5 * HEIGHT,
            0.75 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5357142857142857 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.5357142857142857 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.25 * WIDTH, 0.5 * HEIGHT,
            0.25 * WIDTH, 0.4642857142857143 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.25 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.25 * WIDTH, 0.35714285714285715 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.2857142857142857 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT));
        TRAIN.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.4642857142857143 * HEIGHT));
        TRAIN.getElements().add(new ClosePath());
        TRAIN.setStroke(null);

        return TRAIN;
    }

    private static final Path getCar(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path CAR = new Path();
        CAR.setFillRule(FillRule.EVEN_ODD);
        CAR.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.75 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.75 * WIDTH, 0.17857142857142858 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.25 * WIDTH, 0.17857142857142858 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.35714285714285715 * HEIGHT));
        CAR.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        CAR.getElements().add(new ClosePath());
        CAR.getElements().add(new MoveTo(0.8928571428571429 * WIDTH, 0.5 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.42857142857142855 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.75 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.75 * WIDTH, 0.5 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.75 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5714285714285714 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.8571428571428571 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.5 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.5 * HEIGHT));
        CAR.getElements().add(new ClosePath());
        CAR.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.25 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.25 * WIDTH, 0.5 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.42857142857142855 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.5 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.5357142857142857 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT));
        CAR.getElements().add(new ClosePath());
        CAR.getElements().add(new MoveTo(0.25 * WIDTH, 0.7142857142857143 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.25 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.25 * WIDTH, 0.7857142857142857 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8571428571428571 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.14285714285714285 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.7142857142857143 * HEIGHT));
        CAR.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.7142857142857143 * HEIGHT));
        CAR.getElements().add(new LineTo(0.03571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.4642857142857143 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.21428571428571427 * WIDTH, 0.14285714285714285 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.25 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.10714285714285714 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.7857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.35714285714285715 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.4642857142857143 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.4642857142857143 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.4642857142857143 * HEIGHT));
        CAR.getElements().add(new LineTo(0.9642857142857143 * WIDTH, 0.7142857142857143 * HEIGHT));
        CAR.getElements().add(new LineTo(0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.7857142857142857 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.75 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.75 * WIDTH, 0.7857142857142857 * HEIGHT));
        CAR.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.75 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.75 * WIDTH, 0.7142857142857143 * HEIGHT));
        CAR.getElements().add(new LineTo(0.25 * WIDTH, 0.7142857142857143 * HEIGHT));
        CAR.getElements().add(new ClosePath());
        CAR.setStroke(null);

        return CAR;
    }

    private static final Path getLuggage(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path LUGGAGE = new Path();
        LUGGAGE.setFillRule(FillRule.EVEN_ODD);
        LUGGAGE.getElements().add(new MoveTo(0.6071428571428571 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new LineTo(0.42857142857142855 * WIDTH, 0.17857142857142858 * HEIGHT));
        LUGGAGE.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.17857142857142858 * HEIGHT));
        LUGGAGE.getElements().add(new LineTo(0.6071428571428571 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new ClosePath());
        LUGGAGE.getElements().add(new MoveTo(0.6428571428571429 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.25 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.17857142857142858 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.42857142857142855 * WIDTH, 0.14285714285714285 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.25 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new LineTo(0.25 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new LineTo(0.25 * WIDTH, 0.8571428571428571 * HEIGHT));
        LUGGAGE.getElements().add(new LineTo(0.75 * WIDTH, 0.8571428571428571 * HEIGHT));
        LUGGAGE.getElements().add(new LineTo(0.75 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new LineTo(0.6428571428571429 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new ClosePath());
        LUGGAGE.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.8571428571428571 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.75 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.75 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.9642857142857143 * WIDTH, 0.32142857142857145 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.9642857142857143 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.25 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.8928571428571429 * WIDTH, 0.25 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.25 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        LUGGAGE.getElements().add(new ClosePath());
        LUGGAGE.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.8571428571428571 * HEIGHT));
        LUGGAGE.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.25 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.25 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.25 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.25 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.75 * HEIGHT,
            0.03571428571428571 * WIDTH, 0.75 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.03571428571428571 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT));
        LUGGAGE.getElements().add(new CubicCurveTo(0.10714285714285714 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.8571428571428571 * HEIGHT));
        LUGGAGE.getElements().add(new ClosePath());
        LUGGAGE.setStroke(null);

        return LUGGAGE;
    }

    private static final Path getBrightness(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path BRIGHTNESS = new Path();
        BRIGHTNESS.setFillRule(FillRule.EVEN_ODD);
        BRIGHTNESS.getElements().add(new MoveTo(0.7857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.25 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.75 * WIDTH, 0.2857142857142857 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.21428571428571427 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        BRIGHTNESS.getElements().add(new ClosePath());
        BRIGHTNESS.getElements().add(new MoveTo(0.25 * WIDTH, 0.7142857142857143 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.7857142857142857 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.8214285714285714 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.75 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.25 * WIDTH, 0.7142857142857143 * HEIGHT));
        BRIGHTNESS.getElements().add(new ClosePath());
        BRIGHTNESS.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.25 * WIDTH, 0.2857142857142857 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.2857142857142857 * WIDTH, 0.25 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.21428571428571427 * WIDTH, 0.17857142857142858 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.21428571428571427 * HEIGHT));
        BRIGHTNESS.getElements().add(new ClosePath());
        BRIGHTNESS.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.75 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.7857142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.7857142857142857 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.75 * WIDTH, 0.7142857142857143 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.7142857142857143 * WIDTH, 0.75 * HEIGHT));
        BRIGHTNESS.getElements().add(new ClosePath());
        BRIGHTNESS.getElements().add(new MoveTo(0.07142857142857142 * WIDTH, 0.5357142857142857 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.5357142857142857 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.17857142857142858 * WIDTH, 0.4642857142857143 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.4642857142857143 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.07142857142857142 * WIDTH, 0.5357142857142857 * HEIGHT));
        BRIGHTNESS.getElements().add(new ClosePath());
        BRIGHTNESS.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.5357142857142857 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.5357142857142857 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.9285714285714286 * WIDTH, 0.4642857142857143 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.4642857142857143 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.8214285714285714 * WIDTH, 0.5357142857142857 * HEIGHT));
        BRIGHTNESS.getElements().add(new ClosePath());
        BRIGHTNESS.getElements().add(new MoveTo(0.5357142857142857 * WIDTH, 0.9285714285714286 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.8214285714285714 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.8214285714285714 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.9285714285714286 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.9285714285714286 * HEIGHT));
        BRIGHTNESS.getElements().add(new ClosePath());
        BRIGHTNESS.getElements().add(new MoveTo(0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.07142857142857142 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.07142857142857142 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.4642857142857143 * WIDTH, 0.17857142857142858 * HEIGHT));
        BRIGHTNESS.getElements().add(new LineTo(0.5357142857142857 * WIDTH, 0.17857142857142858 * HEIGHT));
        BRIGHTNESS.getElements().add(new ClosePath());
        BRIGHTNESS.getElements().add(new MoveTo(0.7142857142857143 * WIDTH, 0.5 * HEIGHT));
        BRIGHTNESS.getElements().add(new CubicCurveTo(0.7142857142857143 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.5 * WIDTH, 0.2857142857142857 * HEIGHT));
        BRIGHTNESS.getElements().add(new CubicCurveTo(0.39285714285714285 * WIDTH, 0.2857142857142857 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.2857142857142857 * WIDTH, 0.5 * HEIGHT));
        BRIGHTNESS.getElements().add(new CubicCurveTo(0.2857142857142857 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.5 * WIDTH, 0.7142857142857143 * HEIGHT));
        BRIGHTNESS.getElements().add(new CubicCurveTo(0.6071428571428571 * WIDTH, 0.7142857142857143 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.7142857142857143 * WIDTH, 0.5 * HEIGHT));
        BRIGHTNESS.getElements().add(new ClosePath());
        BRIGHTNESS.setStroke(null);

        return BRIGHTNESS;
    }

    private static final Path getDelete(final double SIZE) {
        final double WIDTH  = SIZE;
        final double HEIGHT = SIZE;

        final Path DELETE = new Path();
        DELETE.setFillRule(FillRule.EVEN_ODD);
        DELETE.getElements().add(new MoveTo(0.8214285714285714 * WIDTH, 0.25 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.25 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.25 * HEIGHT,
            0.8571428571428571 * WIDTH, 0.25 * HEIGHT));
        DELETE.getElements().add(new LineTo(0.75 * WIDTH, 0.14285714285714285 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.14285714285714285 * HEIGHT,
            0.75 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.75 * WIDTH, 0.17857142857142858 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.10714285714285714 * HEIGHT,
            0.6071428571428571 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.5 * WIDTH, 0.07142857142857142 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.07142857142857142 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.25 * HEIGHT,
            0.07142857142857142 * WIDTH, 0.5 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.07142857142857142 * WIDTH, 0.6071428571428571 * HEIGHT,
            0.10714285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.75 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.75 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.75 * HEIGHT,
            0.14285714285714285 * WIDTH, 0.75 * HEIGHT));
        DELETE.getElements().add(new LineTo(0.25 * WIDTH, 0.8571428571428571 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.25 * WIDTH, 0.8571428571428571 * HEIGHT,
            0.25 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.25 * WIDTH, 0.8214285714285714 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.8928571428571429 * HEIGHT,
            0.39285714285714285 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.5 * WIDTH, 0.9285714285714286 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.75 * WIDTH, 0.9285714285714286 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.75 * HEIGHT,
            0.9285714285714286 * WIDTH, 0.5 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.9285714285714286 * WIDTH, 0.39285714285714285 * HEIGHT,
            0.8928571428571429 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.25 * HEIGHT));
        DELETE.getElements().add(new ClosePath());
        DELETE.getElements().add(new MoveTo(0.17857142857142858 * WIDTH, 0.5 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.17857142857142858 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.5 * WIDTH, 0.17857142857142858 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.5714285714285714 * WIDTH, 0.17857142857142858 * HEIGHT,
            0.6428571428571429 * WIDTH, 0.21428571428571427 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.25 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.6785714285714286 * WIDTH, 0.25 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.25 * WIDTH, 0.6785714285714286 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.21428571428571427 * WIDTH, 0.6428571428571429 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5714285714285714 * HEIGHT,
            0.17857142857142858 * WIDTH, 0.5 * HEIGHT));
        DELETE.getElements().add(new ClosePath());
        DELETE.getElements().add(new MoveTo(0.5 * WIDTH, 0.8214285714285714 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.42857142857142855 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.35714285714285715 * WIDTH, 0.7857142857142857 * HEIGHT,
            0.32142857142857145 * WIDTH, 0.75 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.32142857142857145 * WIDTH, 0.75 * HEIGHT,
            0.75 * WIDTH, 0.32142857142857145 * HEIGHT,
            0.75 * WIDTH, 0.32142857142857145 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.7857142857142857 * WIDTH, 0.35714285714285715 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.42857142857142855 * HEIGHT,
            0.8214285714285714 * WIDTH, 0.5 * HEIGHT));
        DELETE.getElements().add(new CubicCurveTo(0.8214285714285714 * WIDTH, 0.6785714285714286 * HEIGHT,
            0.6785714285714286 * WIDTH, 0.8214285714285714 * HEIGHT,
            0.5 * WIDTH, 0.8214285714285714 * HEIGHT));
        DELETE.getElements().add(new ClosePath());
        DELETE.setStroke(null);

        return DELETE;
    }
}
