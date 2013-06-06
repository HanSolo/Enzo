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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 24.09.12
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */
public class RadialMenuBuilder implements Builder<RadialMenu> {
    private HashMap<String, Property> properties = new HashMap<String, Property>();


    // ******************** Constructors **************************************
    protected RadialMenuBuilder() {}


    // ******************** Methods *******************************************
    public static final RadialMenuBuilder create() {
        return new RadialMenuBuilder();
    }

    public final RadialMenuBuilder options(final Options OPTIONS) {
        properties.put("OPTIONS", new SimpleObjectProperty<Options>(OPTIONS));
        return this;
    }

    public final RadialMenuBuilder items(final MenuItem... MENU_ITEMS) {
        properties.put("ITEMS_ARRAY", new SimpleObjectProperty<MenuItem[]>(MENU_ITEMS));
        return this;
    }

    public final RadialMenuBuilder items(final List<MenuItem> MENU_ITEMS) {
        properties.put("ITEMS_LIST", new SimpleObjectProperty<List<MenuItem>>(MENU_ITEMS));
        return this;
    }

    @Override public final RadialMenu build() {
        Options        options = properties.keySet().contains("OPTIONS") ? ((ObjectProperty<Options>) properties.get("OPTIONS")).get() : new Options();
        List<MenuItem> items;
        if (properties.keySet().contains("ITEMS_ARRAY")) {
            items = Arrays.asList(((ObjectProperty<MenuItem[]>) properties.get("ITEMS_ARRAY")).get());
        } else if (properties.keySet().contains("ITEMS_LIST")) {
            items = ((ObjectProperty<List<MenuItem>>) properties.get("ITEMS_LIST")).get();
        } else {
            items = new ArrayList<MenuItem>();
            items.add(new MenuItem());
        }
        return new RadialMenu(options, items);
    }
}
