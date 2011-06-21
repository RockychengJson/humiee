package org.wso2.tools.humantask.editor.editors.pages.humanInteractions;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class HIImportWizardPage extends WizardPage {

	 public static final String PAGE_NAME = "Import page";
	 
	 private Text nsp_txt;
	 private Text location_txt;
	 private Text im_type_txt;
	 
	protected HIImportWizardPage() {
		super(PAGE_NAME, "Add New Import", null);
		
	}

	
	@Override
	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		comp.setLayout(layout);
		
		Label name_space_label = new Label(comp, SWT.WRAP);
		name_space_label.setText("Namespace");
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);
		gd.horizontalSpan =1;
		name_space_label.setLayoutData(gd);
		
		nsp_txt= new Text(comp, SWT.SINGLE|SWT.BORDER);
		nsp_txt.setLayoutData(gd);
		
		Label location_lb = new Label(comp, SWT.WRAP);
		location_lb.setText("Location");
		location_lb.setLayoutData(gd);
		
		location_txt = new Text(comp, SWT.SINGLE | SWT.BORDER);
		location_txt.setLayoutData(gd);
		
		Label im_type_lb = new Label(comp, SWT.WRAP);
		im_type_lb.setText("Import Type");
		im_type_lb.setLayoutData(gd);
		
		im_type_txt = new Text(comp, SWT.SINGLE| SWT.BORDER);
		im_type_txt.setLayoutData(gd);
		
		setControl(comp);  
		setPageComplete(true);
	}
	
	public String getNameSpace(){
		return nsp_txt.getText();
	}

	public String getLocation(){
		return location_txt.getText();
	}
	
	public String getImportType(){
		return im_type_txt.getText();
	}
}
