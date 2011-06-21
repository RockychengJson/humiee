package org.wso2.tools.humantask.editor.editors.base.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;


public class EMFObjectHandleUtil {
	public static String RESOURCE_NOT_AVAILABLE = "N/A";
	public static String getTextContent(EObject object) {
		//String value = object.getMixed().getValue(0).toString();
		return null;
		
	}
	
	public static TableViewerColumn createTableViewerColumn(TableViewer viewer,	String title, int bound) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
}
