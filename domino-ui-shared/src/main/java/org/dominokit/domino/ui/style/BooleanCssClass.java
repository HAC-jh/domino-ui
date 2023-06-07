/*
 * Copyright © 2019 Dominokit
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
package org.dominokit.domino.ui.style;

import elemental2.dom.Element;
import org.dominokit.domino.ui.IsElement;

public class BooleanCssClass implements CssClass {

  private CssClass cssClass;
  private boolean addRemove;

  public static BooleanCssClass of(CssClass cssClass, boolean addRemove) {
    return new BooleanCssClass(cssClass, addRemove);
  }

  public static BooleanCssClass of(HasCssClass cssClass, boolean addRemove) {
    return new BooleanCssClass(cssClass.getCssClass(), addRemove);
  }

  public static BooleanCssClass of(String cssClass, boolean addRemove) {
    return new BooleanCssClass(()-> cssClass, addRemove);
  }

  public static BooleanCssClass of(CssClass cssClass) {
    return new BooleanCssClass(cssClass);
  }

  public static BooleanCssClass of(HasCssClass cssClass) {
    return new BooleanCssClass(cssClass.getCssClass());
  }

  public static BooleanCssClass of(String cssClass) {
    return new BooleanCssClass(()-> cssClass);
  }

  public BooleanCssClass(CssClass cssClass, boolean addRemove) {
    this.cssClass = cssClass;
    this.addRemove = addRemove;
  }

  public BooleanCssClass(CssClass cssClass) {
    this(cssClass, true);
  }

  @Override
  public void apply(Element element) {
    apply(element, addRemove);
  }

  public void apply(Element element, boolean addRemove) {
    if (addRemove) {
      cssClass.apply(element);
    } else {
      remove(element);
    }
  }

  public void apply(IsElement<?> element, boolean addRemove) {
    apply(element.element(), addRemove);
  }

  @Override
  public boolean isAppliedTo(Element element) {
    return cssClass.isAppliedTo(element);
  }

  @Override
  public boolean isAppliedTo(IsElement<?> element) {
    return cssClass.isAppliedTo(element);
  }

  @Override
  public void remove(Element element) {
    cssClass.remove(element);
  }

  @Override
  public void remove(IsElement<?> element) {
    cssClass.remove(element);
  }

  @Override
  public String getCssClass() {
    return cssClass.getCssClass();
  }
}
