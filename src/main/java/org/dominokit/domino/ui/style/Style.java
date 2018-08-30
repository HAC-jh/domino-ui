package org.dominokit.domino.ui.style;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;

import static java.util.Objects.nonNull;
import static org.dominokit.domino.ui.style.Styles.*;

public class Style<E extends HTMLElement, T extends IsElement<E>> implements IsElement<E> {

    private E element;
    private T wrapperElement;

    public Style(T element) {
        this.element = element.asElement();
        this.wrapperElement = element;

    }

    public static <E extends HTMLElement, T extends IsElement<E>> Style<E, T> of(E htmlElement) {
        return new Style<>((T) (IsElement<E>) () -> htmlElement);
    }

    public static <E extends HTMLElement, T extends IsElement<E>> Style<E, T> of(T htmlElement) {
        return new Style<>(htmlElement);
    }

    public Style<E, T> setProperty(String name, String value) {
        element.style.setProperty(name, value);
        return this;
    }

    public Style<E, T> removeProperty(String name) {
        element.style.removeProperty(name);
        return this;
    }

    public Style<E, T> setImportantProperty(String name, String value) {
        element.style.setProperty(name, value, "important");
        return this;
    }

    public Style<E, T> css(String cssClass) {
        if (nonNull(cssClass) && !cssClass.isEmpty())
            element.classList.add(cssClass);
        return this;
    }

    public Style<E, T> css(String... cssClasses) {
        if (nonNull(cssClasses) && cssClasses.length > 0)
            element.classList.add(cssClasses);
        return this;
    }

    /**
     * @param cssClass
     * @return
     * @deprecated use {@link #removeCss(String)}
     */
    @Deprecated
    public Style<E, T> removeClass(String cssClass) {
        if (nonNull(cssClass) && !cssClass.isEmpty())
            element.classList.remove(cssClass);
        return this;
    }

    /**
     *
     * @param cssClasses
     * @return
     *
     * @deprecated  use {@link #removeCss(String...)}
     */
    @Deprecated
    public Style<E, T> removeClass(String... cssClasses) {
        if (nonNull(cssClasses) && cssClasses.length > 0)
            element.classList.remove(cssClasses);
        return this;
    }

    /**
     *
     * @param cssClass
     * @param replacementClass
     * @return
     *
     * @deprecated use {@link #replaceCss(String, String)}
     */
    @Deprecated
    public Style<E, T> replaceClass(String cssClass, String replacementClass) {
        if (hasClass(cssClass)) {
            removeClass(cssClass);
            css(replacementClass);
        }
        return this;
    }

    public Style<E, T> removeCss(String cssClass) {
        if (nonNull(cssClass) && !cssClass.isEmpty())
            element.classList.remove(cssClass);
        return this;
    }

    public Style<E, T> removeCss(String... cssClasses) {
        if (nonNull(cssClasses) && cssClasses.length > 0)
            element.classList.remove(cssClasses);
        return this;
    }

    public Style<E, T> replaceCss(String cssClass, String replacementClass) {
        if (hasClass(cssClass)) {
            removeCss(cssClass);
            css(replacementClass);
        }
        return this;
    }

    public Style<E, T> setBorder(String border) {
        setProperty("border", border);
        return this;
    }

    public Style<E, T> setBorderColor(String borderColor) {
        setProperty("border-color", borderColor);
        return this;
    }

    public Style<E, T> setWidth(String width) {
        setProperty("width", width);
        return this;
    }

    public Style<E, T> setWidth(String width, boolean important) {
        if (important) {
            setImportantProperty("width", width);
        } else {
            setProperty("width", width);
        }
        return this;
    }

    public Style<E, T> setMinWidth(String width) {
        setProperty("min-width", width);
        return this;
    }

    public Style<E, T> setMinWidth(String width, boolean important) {
        if (important) {
            setImportantProperty("min-width", width);
        } else {
            setProperty("min-width", width);
        }
        return this;
    }

    public Style<E, T> setMaxWidth(String width) {
        setProperty("max-width", width);
        return this;
    }

    public Style<E, T> setMaxWidth(String width, boolean important) {
        if (important) {
            setImportantProperty("max-width", width);
        } else {
            setProperty("max-width", width);
        }
        return this;
    }


    public Style<E, T> setHeight(String height) {
        setProperty("height", height);
        return this;
    }

    public Style<E, T> setHeight(String height, boolean important) {
        if (important) {
            setImportantProperty("height", height);
        } else {
            setProperty("height", height);
        }
        return this;
    }

    public Style<E, T> setMinHeight(String height) {
        setProperty("min-height", height);
        return this;
    }

    public Style<E, T> setMinHeight(String height, boolean important) {
        if (important) {
            setImportantProperty("min-height", height);
        } else {
            setProperty("min-height", height);
        }
        return this;
    }

    public Style<E, T> setMaxHeight(String height) {
        setProperty("max-height", height);
        return this;
    }

    public Style<E, T> setMaxHeight(String height, boolean important) {
        if (important) {
            setImportantProperty("max-height", height);
        } else {
            setProperty("max-height", height);
        }
        return this;
    }

    public Style<E, T> setTextAlign(String textAlign) {
        setProperty("text-align", textAlign);
        return this;
    }

    public Style<E, T> setTextAlign(String textAlign, boolean important) {
        if (important) {
            setImportantProperty("text-align", textAlign);
        } else {
            setProperty("text-align", textAlign);
        }
        return this;
    }

    public Style<E, T> setColor(String color) {
        setProperty("color", color);
        return this;
    }

    public Style<E, T> setColor(String color, boolean important) {
        if (important) {
            setImportantProperty("color", color);
        } else {
            setProperty("color", color);
        }
        return this;
    }


    public Style<E, T> setBackgroundColor(String color) {
        setProperty("background-color", color);
        return this;
    }

    public Style<E, T> setBackgroundColor(String color, boolean important) {
        if (important) {
            setImportantProperty("background-color", color);
        } else {
            setProperty("background-color", color);
        }
        return this;
    }

    public Style<E, T> setMargin(String margin) {
        setProperty("margin", margin);
        return this;
    }

    public Style<E, T> setMargin(String margin, boolean important) {
        if (important) {
            setImportantProperty("margin", margin);
        } else {
            setProperty("margin", margin);
        }
        return this;
    }

    public Style<E, T> setMarginTop(String margin) {
        setProperty("margin-top", margin);
        return this;
    }

    public Style<E, T> setMarginTop(String margin, boolean important) {
        if (important) {
            setImportantProperty("margin-top", margin);
        } else {
            setProperty("margin-top", margin);
        }
        return this;
    }

    public Style<E, T> setMarginBottom(String margin) {
        setProperty("margin-bottom", margin);
        return this;
    }

    public Style<E, T> setMarginBottom(String margin, boolean important) {
        if (important) {
            setImportantProperty("margin-bottom", margin);
        } else {
            setProperty("margin-bottom", margin);
        }
        return this;
    }

    public Style<E, T> setMarginLeft(String margin) {
        setProperty("margin-left", margin);
        return this;
    }

    public Style<E, T> setMarginLeft(String margin, boolean important) {
        if (important) {
            setImportantProperty("margin-left", margin);
        } else {
            setProperty("margin-left", margin);
        }
        return this;
    }

    public Style<E, T> setMarginRight(String margin) {
        setProperty("margin-right", margin);
        return this;
    }

    public Style<E, T> setMarginRight(String margin, boolean important) {
        if (important) {
            setImportantProperty("margin-right", margin);
        } else {
            setProperty("margin-right", margin);
        }
        return this;
    }

    public Style<E, T> setPaddingRight(String padding) {
        setProperty("padding-right", padding);
        return this;
    }

    public Style<E, T> setPaddingRight(String padding, boolean important) {
        if (important) {
            setImportantProperty("padding-right", padding);
        } else {
            setProperty("padding-right", padding);
        }
        return this;
    }

    public Style<E, T> setPaddingLeft(String padding) {
        setProperty("padding-left", padding);
        return this;
    }

    public Style<E, T> setPaddingLeft(String padding, boolean important) {
        if (important) {
            setImportantProperty("padding-left", padding);
        } else {
            setProperty("padding-left", padding);
        }
        return this;
    }


    public Style<E, T> setPaddingBottom(String padding) {
        setProperty("padding-bottom", padding);
        return this;
    }

    public Style<E, T> setPaddingBottom(String padding, boolean important) {
        if (important) {
            setImportantProperty("padding-bottom", padding);
        } else {
            setProperty("padding-bottom", padding);
        }
        return this;
    }

    public Style<E, T> setPaddingTop(String padding) {
        setProperty("padding-top", padding);
        return this;
    }

    public Style<E, T> setPaddingTop(String padding, boolean important) {
        if (important) {
            setImportantProperty("padding-top", padding);
        } else {
            setProperty("padding-top", padding);
        }
        return this;
    }

    public Style<E, T> setPadding(String padding) {
        setProperty("padding", padding);
        return this;
    }

    public Style<E, T> setPadding(String padding, boolean important) {
        if (important) {
            setImportantProperty("padding", padding);
        } else {
            setProperty("padding", padding);
        }
        return this;
    }

    public Style<E, T> setDisplay(String display) {
        setProperty("display", display);
        return this;
    }

    public Style<E, T> setDisplay(String display, boolean important) {
        if (important) {
            setImportantProperty("display", display);
        } else {
            setProperty("display", display);
        }
        return this;
    }

    public Style<E, T> setFontSize(String fontSize) {
        setProperty("font-size", fontSize);
        return this;
    }

    public Style<E, T> setFontSize(String fontSize, boolean important) {
        if (important) {
            setImportantProperty("font-size", fontSize);
        } else {
            setProperty("font-size", fontSize);
        }
        return this;
    }

    public Style<E, T> setFloat(String cssFloat) {
        setProperty("float", cssFloat);
        return this;
    }

    public Style<E, T> setFloat(String cssFloat, boolean important) {
        if (important) {
            setImportantProperty("float", cssFloat);
        } else {
            setProperty("float", cssFloat);
        }
        return this;
    }

    public Style<E, T> setCursor(String cursor) {
        setProperty("cursor", cursor);
        return this;
    }

    public Style<E, T> setCursor(String cursor, boolean important) {
        if (important) {
            setImportantProperty("cursor", cursor);
        } else {
            setProperty("cursor", cursor);
        }
        return this;
    }

    public boolean hasClass(String cssClass) {
        if (nonNull(cssClass) && !cssClass.isEmpty()) {
            return element.classList.contains(cssClass);
        }
        return false;
    }

    public Style<E, T> removeShadow(boolean important) {
        if (important) {
            setImportantProperty("box-shadow", "none");
            setImportantProperty("-webkit-box-shadow", "none");
            removeCss(default_shadow);
        } else {
            removeShadow();
        }
        return this;
    }

    public Style<E, T> removeShadow() {
        setProperty("box-shadow", "none");
        setProperty("-webkit-box-shadow", "none");
        removeCss(default_shadow);
        return this;
    }

    public Style<E, T> addDefaultShadow() {
        css(default_shadow);
        return this;
    }


    public Style<E,T> pullRight(){
        if(!hasClass(pull_right)){
            css(pull_right);
        }

        return this;
    }

    public Style<E,T> pullLeft(){
        if(!hasClass(pull_left)){
            css(pull_left);
        }

        return this;
    }

    public Style<E, T> alignCenter(){
        if(hasClass(align_center)){
            removeCss(align_center);
        }
        css(align_center);
        return this;
    }

    public Style<E, T> alignRight(){
        if(hasClass(align_right)){
            removeCss(align_right);
        }
        css(align_right);
        return this;
    }

    @Override
    public E asElement() {
        return element;
    }

    public T get() {
        return wrapperElement;
    }
}
