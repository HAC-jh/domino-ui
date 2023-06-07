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

 import static java.util.Objects.nonNull;

 import elemental2.dom.HTMLDivElement;
 import elemental2.dom.HTMLElement;
 import elemental2.dom.HTMLTableCellElement;
 import elemental2.dom.HTMLTableRowElement;
 import java.util.Collections;
 import java.util.List;
 import java.util.Optional;
 import org.dominokit.domino.ui.datatable.*;
 import org.dominokit.domino.ui.datatable.events.ExpandRecordEvent;
 import org.dominokit.domino.ui.datatable.events.TableEvent;
 import org.dominokit.domino.ui.elements.DivElement;
 import org.dominokit.domino.ui.elements.TDElement;
 import org.dominokit.domino.ui.elements.TableRowElement;
 import org.dominokit.domino.ui.grid.flex.FlexItem;
 import org.dominokit.domino.ui.icons.Icon;
 import org.dominokit.domino.ui.icons.Icons;
 import org.dominokit.domino.ui.utils.ComponentMeta;
 import org.dominokit.domino.ui.utils.ElementUtil;
 import org.dominokit.domino.ui.IsElement;

/**
 * This plugin add the capability to expand a row in the table to display more information about
 its
 * record beneath the row itself
 *
 * @param <T> the type of the data table records
 */
 public class RecordDetailsPlugin<T> implements DataTablePlugin<T> {

  private final Icon<?> collapseIcon;
  private final Icon<?> expandIcon;
  private DivElement element = elements.div();
  private TDElement td =
      elements.td().css(DataTableStyles.DETAILS_TD).appendChild(element);
  private TableRowElement tr =
      elements.tr().css(DataTableStyles.DETAILS_TR).appendChild(td);

  private final CellRenderer<T> cellRenderer;
  private DetailsButtonElement buttonElement;
  private DataTable<T> dataTable;

  /**
   * Creates an instance with custom renderer and default expand/collapse icons
   *
   * @param cellRenderer the {@link CellRenderer}
   */
  public RecordDetailsPlugin(CellRenderer<T> cellRenderer) {
    this(
        cellRenderer,
        Icons.fullscreen_exit().clickable(),
        Icons.fullscreen().clickable());
  }

  /**
   * Creates an instance with custom renderer and expand/collapse icons
   *
   * @param cellRenderer the {@link CellRenderer}
   * @param collapseIcon {@link Icon}
   * @param expandIcon {@link Icon}
   */
  public RecordDetailsPlugin(
      CellRenderer<T> cellRenderer, Icon<?> collapseIcon, Icon<?> expandIcon) {
    this.cellRenderer = cellRenderer;
    this.collapseIcon = collapseIcon;
    this.expandIcon = expandIcon;
  }

  @Override
  public boolean requiresUtilityColumn() {
    return true;
  }

  @Override
  public Optional<List<HTMLElement>> getUtilityElements(
      DataTable<T> dataTable, CellRenderer.CellInfo<T> cell) {
    applyStyles(cell);
    DetailsButtonElement<T> detailsButtonElement =
        new DetailsButtonElement<>(expandIcon, collapseIcon, RecordDetailsPlugin.this, cell);
    cell.getTableRow().applyMeta(detailsButtonElement);
    cell.getTableRow()
        .addCollapseListener(
            () -> {
              if (nonNull(buttonElement)
                  && cell.getTableRow().equals(buttonElement.getCellInfo().getTableRow())) {
                buttonElement.collapse();
              }
            });
    applyStyles(cell);
    detailsButtonElement.element.setAttribute("order", "30");
    return Optional.of(Collections.singletonList(detailsButtonElement.element()));
  }

  @Override
  public void onHeaderAdded(DataTable<T> dataTable, ColumnConfig<T> column) {
    if (column.isUtilityColumn()) {
      column
          .getHeaderLayout()
          .appendChild(FlexItem.create().setOrder(30).appendChild(expandIcon.copy().clickable()));
    }
  }

  @Override
  public void onBeforeAddHeaders(DataTable<T> dataTable) {
    this.dataTable = dataTable;
  }

  /** {@inheritDoc} */
  @Override
  public void handleEvent(TableEvent event) {
    if (ExpandRecordEvent.EXPAND_RECORD.equals(event.getType())) {
      expandRow((ExpandRecordEvent<T>) event);
    }
  }

  private void expandRow(ExpandRecordEvent<T> event) {
    Optional<DetailsButtonElement<T>> detailsButtonElement =
        event.getTableRow().getMeta(DataTableStyles.RECORD_DETAILS_BUTTON);
      detailsButtonElement.ifPresent(this::setExpanded);
  }

  /** @return the root {@link HTMLDivElement} of this component */
  public HTMLDivElement getElement() {
    return element.element();
  }

  /** @return the {@link HTMLTableCellElement} that contains the records details */
  public HTMLTableCellElement getTd() {
    return td.element();
  }

  /** @return the {@link HTMLTableRowElement} that contains the records details */
  public HTMLTableRowElement getTr() {
    return tr.element();
  }

  public void applyStyles(CellRenderer.CellInfo<T> cellInfo) {}

  /**
   * A hook method to customize the column of the expand/collapse icons
   *
   * @param column {@link ColumnConfig}
   */
  public void setupColumn(ColumnConfig<T> column) {}

  private static class DetailsButtonElement<T>
      implements IsElement<HTMLElement>, ComponentMeta {
    private final DivElement element;
    private final CellRenderer.CellInfo<T> cellInfo;
    private final Icon<?> expandIcon;
    private final Icon<?> collapseIcon;
    private RecordDetailsPlugin<?> recordDetailsPlugin;

    public DetailsButtonElement(
        Icon<?> expandIcon,
        Icon<?> collapseIcon,
        RecordDetailsPlugin<?> recordDetailsPlugin,
        CellRenderer.CellInfo<T> cellInfo) {
      this.expandIcon = expandIcon.copy();
      this.collapseIcon = collapseIcon.copy();
      this.recordDetailsPlugin = recordDetailsPlugin;
      this.cellInfo = cellInfo;
      this.element = elements.div();
      this.element
          .appendChild(this.expandIcon.clickable())
          .appendChild(this.collapseIcon.clickable().hide());

      this.expandIcon.addClickListener(evt -> expand());
      this.collapseIcon.addClickListener(evt -> collapse());
    }

    public CellRenderer.CellInfo<T> getCellInfo() {
      return cellInfo;
    }

    public void expand() {
      expandIcon.hide();
      collapseIcon.show();
      recordDetailsPlugin.setExpanded(this);
    }

    public void collapse() {
      expandIcon.show();
      collapseIcon.hide();
      recordDetailsPlugin.clear();
    }

    @Override
    public HTMLElement element() {
      return element.element();
    }

    @Override
    public String getKey() {
      return DataTableStyles.RECORD_DETAILS_BUTTON;
    }
  }

  private void clear() {
    tr.remove();
    ElementUtil.clear(element);
    this.buttonElement = null;
  }

  private void setExpanded(DetailsButtonElement buttonElement) {
    if (nonNull(this.buttonElement)) {
      this.buttonElement.collapse();
      clear();
    }
    this.buttonElement = buttonElement;
    td
        .setAttribute("colspan", dataTable.getTableConfig().getColumns().size() + "");
    element.appendChild(cellRenderer.asElement(buttonElement.getCellInfo()));
    dataTable
        .bodyElement()
        .element()
        .insertBefore(tr.element(), buttonElement.getCellInfo().getTableRow().element().nextSibling);
  }
 }
