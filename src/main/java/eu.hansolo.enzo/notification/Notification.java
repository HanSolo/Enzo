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

package eu.hansolo.enzo.notification;

import javafx.scene.image.Image;


/**
 * Created by
 * User: hansolo
 * Date: 01.07.13
 * Time: 07:10
 */
public class Notification {
    public static final Image INFO_ICON    = new Image(Notifier.class.getResourceAsStream("info.png"));
    public static final Image WARNING_ICON = new Image(Notifier.class.getResourceAsStream("warning.png"));
    public static final Image SUCCESS_ICON = new Image(Notifier.class.getResourceAsStream("success.png"));
    public static final Image ERROR_ICON   = new Image(Notifier.class.getResourceAsStream("error.png"));
    public final String       TITLE;
    public final String       MESSAGE;
    public final Image        IMAGE;


    // ******************** Constructors **************************************
    public Notification(final String TITLE, final String MESSAGE) {
        this(TITLE, MESSAGE, null);
    }
    public Notification(final String MESSAGE, final Image IMAGE) {
        this("", MESSAGE, IMAGE);
    }
    public Notification(final String TITLE, final String MESSAGE, final Image IMAGE) {
        this.TITLE   = TITLE;
        this.MESSAGE = MESSAGE;
        this.IMAGE   = IMAGE;
    }
}
