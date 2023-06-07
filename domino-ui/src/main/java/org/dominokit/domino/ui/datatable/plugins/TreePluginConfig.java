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
package org.dominokit.domino.ui.datatable.plugins;

import elemental2.dom.Node;
import org.dominokit.domino.ui.datatable.TableRow;
import org.dominokit.domino.ui.icons.Icon;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.icons.ToggleIcon;
import org.dominokit.domino.ui.icons.ToggleMdiIcon;

import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.isNull;
import static org.dominokit.domino.ui.style.SpacingCss.dui_font_size_4;
import static org.dominokit.domino.ui.utils.ElementsFactory.elements;

public class TreePluginConfig<T> implements PluginConfig {

  public static final int DEFAULT_INDENT = 20;

  private boolean lazy = false;
  private final TreeGridPlugin.SubItemsProvider<T> subItemsProvider;
  private TreeGridPlugin.ParentRowCellsSupplier<T> parentRowCellsSupplier;
  private Supplier<ToggleIcon<?,?>> expandCollapseIconSupplier = ()-> ToggleMdiIcon.create(Icons.menu_right(), Icons.menu_down());
  private Supplier<ToggleIcon<?,?>> leafIconSupplier = ()->ToggleMdiIcon.create(Icons.circle_medium(), Icons.circle_medium());
  private Function<TableRow<T>, Node> indentColumnElementSupplier = tableRow -> elements.text();
  private int indent = DEFAULT_INDENT;

  public TreePluginConfig(TreeGridPlugin.SubItemsProvider<T> subItemsProvider) {
    this.subItemsProvider = subItemsProvider;
  }

  public boolean isLazy() {
    return lazy;
  }

  public TreePluginConfig setLazy(boolean lazy) {
    this.lazy = lazy;
    return this;
  }

  public TreeGridPlugin.SubItemsProvider<T> getSubItemsProvider() {
    return subItemsProvider;
  }

  public TreeGridPlugin.ParentRowCellsSupplier<T> getParentRowCellsSupplier() {
    return parentRowCellsSupplier;
  }

  /**
   * Set a supplier that provides cells to be rendered in a parent row cells, this can be used to
   * provide a custom UI for parent rows
   *
   * @param parentRowCellsSupplier {@link TreeGridPlugin.ParentRowCellsSupplier}
   * @return Same config instance
   */
  public TreePluginConfig<T> setParentRowCellsSupplier(
      TreeGridPlugin.ParentRowCellsSupplier<T> parentRowCellsSupplier) {
    this.parentRowCellsSupplier = parentRowCellsSupplier;
    return this;
  }

  public Supplier<ToggleIcon<?,?>> getExpandColapseIconSupplier() {
    return expandCollapseIconSupplier;
  }

  /**
   * Sets a supplier for a custom expand icon instead of the default one
   *
   * @param expandIconSupplier {@link Supplier} of {@link Icon}
   * @return Same config instance
   */
  public TreePluginConfig<T> setExpandCollapseIconSupplier(Supplier<ToggleIcon<?,?>> expandIconSupplier) {
    if (isNull(expandIconSupplier)) {
      this.expandCollapseIconSupplier = () -> ToggleMdiIcon.create(Icons.plus().addCss(dui_font_size_4), Icons.minus().addCss(dui_font_size_4));
    } else {
      this.expandCollapseIconSupplier = expandIconSupplier;
    }
    return this;
  }

  public Supplier<ToggleIcon<?,?>> getLeafIconSupplier() {
    return leafIconSupplier;
  }

  /**
   * Sets a supplier for a custom leaf row icon instead of the default one
   *
   * @param leafIconSupplier {@link Supplier} of {@link Icon}
   * @return Same config instance
   */
  public TreePluginConfig<T> setLeafIconSupplier(Supplier<ToggleIcon<?,?>> leafIconSupplier) {
    if (isNull(leafIconSupplier)) {
      this.leafIconSupplier = () -> ToggleMdiIcon.create(Icons.circle_medium().addCss(dui_font_size_4),
              Icons.circle_medium().addCss(dui_font_size_4));
    } else {
      this.leafIconSupplier = leafIconSupplier;
    }
    return this;
  }

  public Function<TableRow<T>, Node> getIndentColumnElementSupplier() {
    return indentColumnElementSupplier;
  }

  /**
   * Sets a supplier of elements to be appended to the tree grid indent column as part of the
   * utility columns cells
   *
   * @param indentColumnElementSupplier {@link Function} that takes a {@link TableRow} and return a
   *     {@link Node}
   * @return same config instance
   */
  public TreePluginConfig<T> setIndentColumnElementSupplier(
      Function<TableRow<T>, Node> indentColumnElementSupplier) {
    this.indentColumnElementSupplier = indentColumnElementSupplier;
    return this;
  }

  public int getIndent() {
    return indent;
  }

  /**
   * Sets indent value to be added for each tree gird level
   *
   * @param indent int
   * @return Same config instance
   */
  public TreePluginConfig<T> setIndent(int indent) {
    if (indent < 0) {
      this.indent = DEFAULT_INDENT;
    } else {
      this.indent = indent;
    }
    return this;
  }
}
