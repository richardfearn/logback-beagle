/**
 * Logback-beagle: The logback Console Plugin for Eclipse 
 * Copyright (C) 2006-2012, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
package ch.qos.logback.beagle.vista;

import static ch.qos.logback.beagle.util.ResourceUtil.JUMP_IMG_KEY;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolItem;

import ch.qos.logback.beagle.util.ResourceUtil;
import ch.qos.logback.beagle.visual.ClassicTISBuffer;
import ch.qos.logback.beagle.visual.ITableItemStub;

public class TableItemSelectionListener implements SelectionListener {

  Table table;
  ClassicTISBuffer classicTISBuffer;
  TableItem lastSelection;
  ToolItem unfreezeButton;

  TableItemSelectionListener(Table table,
      ClassicTISBuffer visualElementBuffer, ToolItem unfreezeButton,
      UnfreezeToolItemListener unfreezeButtonListener) {
    this.table = table;
    this.classicTISBuffer = visualElementBuffer;
    this.unfreezeButton = unfreezeButton;
  }

  @Override
  public void widgetDefaultSelected(SelectionEvent e) {
    System.out.println("MySelectionListener.widgetDefaultSelected called with "
	+ e);
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    TableItem currentlySelectedTI = (TableItem) e.item;
    final int indexOfCurrentSel = table.indexOf(currentlySelectedTI);

    classicTISBuffer.setActive(false);
    unfreezeButton.setEnabled(true);
    lastSelection = currentlySelectedTI;

    classicTISBuffer.clearCues();
    ITableItemStub visualElem = classicTISBuffer.get(indexOfCurrentSel);
    if (visualElem.supportsJump() && table.getSelectionCount() == 1) {
      classicTISBuffer.jumpCue.setImage(ResourceUtil.getImage(JUMP_IMG_KEY));
    }
  }
}
