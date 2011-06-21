package org.wso2.tools.humantask.editor.editors.pages.humanInteractions;


import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class HIExtentionWizardpage extends WizardPage{

	 public static final String PAGE_NAME = "Extention Page";
	
	 private Combo must_und ;
	 private Text nsp_txt;
	
	 
	protected HIExtentionWizardpage() {
		super(PAGE_NAME, "Create New Extention", null);
		
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
		
		Label must_und_lb = new Label(comp, SWT.WRAP);
		must_und_lb.setText("Must Understand");
		
		must_und = new Combo(comp, SWT.READ_ONLY);
		must_und.add("No",0);
		must_und.add("Yes", 1);
		must_und.setLayoutData(gd);
		must_und.select(1);
		
		 setControl(comp);  
		 setPageComplete(true);
		
	}
	
	public String getMustUnd(){
		if(must_und.getSelectionIndex() ==  1){
			return "yes";
		}else{
			return "no";
		}
	}
	
	public String getNameSpace(){
		return nsp_txt.getText();
	}

}
