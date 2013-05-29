package eu.hansolo.enzo.touchables.pbutton;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.css.PseudoClass;
import javafx.scene.control.Control;


public class PushButton extends Control {
    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
    private boolean                  keepAspect;
    private BooleanProperty          selected;


    // ******************** Constructors **************************************
    public PushButton() {
        getStyleClass().add("pushbutton");
        keepAspect    = true;
    }


    // ******************** Methods *******************************************
    public final boolean isKeepAspect() {
        return keepAspect;
    }
    public final void setKeepAspect(final boolean KEEP_ASPECT) {
        keepAspect = KEEP_ASPECT;
    }

    public final boolean isSelected() {
        return null == selected ? false : selected.get();
    }
    public final void setSelected(final boolean SELECTED) {
        selectedProperty().set(SELECTED);
    }
    public final BooleanProperty selectedProperty() {
        if (null == selected) {
            selected = new BooleanPropertyBase(false) {
                @Override protected void invalidated() { pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, get()); }
                @Override public Object getBean() { return this; }
                @Override public String getName() { return "selected"; }
            };
        }
        return selected;
    }

    @Override public boolean isResizable() {
        return true;
    }
    

    // ******************** Style related *************************************
    @Override protected String getUserAgentStylesheet() {
        return getClass().getResource(getClass().getSimpleName().toLowerCase() + ".css").toExternalForm();
    }
}

