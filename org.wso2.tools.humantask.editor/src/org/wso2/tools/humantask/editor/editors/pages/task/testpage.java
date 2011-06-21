package org.wso2.tools.humantask.editor.editors.pages.task;

import javax.tools.Tool;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.wso2.tools.humantask.editor.editors.pages.util.Messages;

public class testpage extends FormPage {

	private FormToolkit toolkit;

	public testpage(FormEditor editor, String title) {
		super(editor, "test", title);
		// TODO Auto-generated constructor stub
	}

	protected void createFormContent(IManagedForm managedform) {
		 toolkit = managedform.getToolkit();
		 ScrolledForm form =managedform.getForm();
		/* TableWrapLayout layout = new TableWrapLayout();
		    layout.numColumns = 2;
		    form.getBody().setLayout(layout);

		    Color color = form.getDisplay().getSystemColor(SWT.COLOR_YELLOW);

		    Label label = toolkit.createLabel(form.getBody(), "Some text spans over two columns in the first row. ", SWT.WRAP);
		    TableWrapData data = new TableWrapData();
		    data.colspan = 2;
		    label.setLayoutData(data);
		    label.setBackground(color);

		    label = toolkit.createLabel(form.getBody(), "Some text in the first column of the"
		 +"second row. and here it goes on and on ... ... ... ... ", SWT.WRAP);
		    label.setBackground(color);
		    label = toolkit.createLabel(form.getBody(), "Some text in the second column of the second row. ", SWT.WRAP);
		 */
		 GridLayout layout = new GridLayout(2, true);
			form.getBody().setLayout(layout);
			
		/*	for (int i = 0; i < 16; ++i) {
				Button button = new Button(form.getBody(), SWT.NONE);
				GridData gd = new GridData();
				button.setLayoutData(gd);
				button.setText("Cell " + i);
			}
			*/
			
			/*Button btn = new Button(form.getBody(),SWT.PUSH);
			GridData data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);
			data.horizontalSpan = 1;
			btn.setLayoutData(data);
			
			Button btn1 = new Button(form.getBody(),SWT.PUSH);
			GridData data1 = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);
			data.horizontalSpan = 1;
			btn1.setLayoutData(data1);
			
			Composite c1 = toolkit.createComposite(form.getBody());
			GridData c1gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);
			c1gd.horizontalSpan =2;
			c1.setLayoutData(c1gd);
			
			FillLayout fl = new FillLayout(SWT.VERTICAL);
			c1.setLayout(fl);
			
			
			Composite comp = toolkit.createComposite(c1);
			//GridData data2 = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);
			//data2.horizontalSpan=2;
			//comp.setLayoutData(data2);
			
			GridLayout gl = new GridLayout(2,true);
			comp.setLayout(gl);
			
			for (int i = 0; i < 6; ++i) {
				Button button = new Button(comp, SWT.NONE);
				GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING| GridData.FILL_HORIZONTAL);
				button.setLayoutData(gd);
				button.setText("Cell " + i);
			}*/
			 final TableViewer viewer = new TableViewer(form.getBody(), 
                     SWT.BORDER | SWT.FULL_SELECTION);
			GridData data2 = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);
				data2.horizontalSpan=2;
				
//configure thevi table for display
TableLayout layout1 = new TableLayout();
layout1.addColumnData(new ColumnWeightData(33, true));
layout1.addColumnData(new ColumnWeightData(33, true));
layout1.addColumnData(new ColumnWeightData(33, true));
viewer.getTable().setLayout(layout1);


viewer.getTable().setLinesVisible(true);
viewer.getTable().setHeaderVisible(true);
			
			//form.getBody().setLayout(layout);
				
	}
}
