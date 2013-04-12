/*
 * Copyright (c) 2013, Gerrit Grunwald
 * All right reserved
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * The names of its contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
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
