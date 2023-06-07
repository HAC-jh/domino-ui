package org.dominokit.domino.ui.style;

import elemental2.dom.Element;
import org.dominokit.domino.ui.IsElement;
import org.dominokit.domino.ui.mediaquery.MediaQuery;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class XSmallCss implements CssClass {

    private Set<CssClass> cssClasses = new HashSet<>();

    public static XSmallCss of(Collection<CssClass> cssClasses) {
        return new XSmallCss(cssClasses);
    }

    public static XSmallCss of(CssClass... cssClasses) {
        return new XSmallCss(cssClasses);
    }

    public static XSmallCss of(Element element){
        return of(element.classList.asList()
                .stream()
                .map(s -> (CssClass) () -> s)
                .collect(Collectors.toList()));
    }

    public static XSmallCss of(IsElement<?> element){
        return of(element.element());
    }

    public XSmallCss(Collection<CssClass> cssClasses) {
        this.cssClasses.addAll(cssClasses);
    }

    public XSmallCss(CssClass... cssClasses) {
        this(Arrays.asList(cssClasses));
    }

    @Override
    public String getCssClass() {
        return cssClasses.stream().map(CssClass::getCssClass).collect(Collectors.joining(" "));
    }

    @Override
    public void apply(Element element) {
        MediaQuery.addOnXSmallAndDownListener(() -> {

        });
        cssClasses.forEach(cssClass -> cssClass.apply(element));
    }

    @Override
    public boolean isAppliedTo(Element element) {
        return cssClasses.stream().allMatch(cssClass -> cssClass.isAppliedTo(element));
    }

    @Override
    public boolean isAppliedTo(IsElement<?> element) {
        return isAppliedTo(element.element());
    }

    @Override
    public void remove(Element element) {
        cssClasses.forEach(cssClass -> cssClass.remove(element));
    }

    @Override
    public void remove(IsElement<?> element) {
        remove(element.element());
    }
}
