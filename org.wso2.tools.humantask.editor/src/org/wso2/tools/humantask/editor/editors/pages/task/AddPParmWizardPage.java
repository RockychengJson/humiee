package org.wso2.tools.humantask.editor.editors.pages.task;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationParameter;

public class AddPParmWizardPage extends WizardPage{

	
	public static final String PAGE_NAME ="Add Presentation Parameter";
	private Text nametextbox;
	 
	
	public AddPParmWizardPage(){
		 super(PAGE_NAME ,"Add Presentation Parameter" , null);
	}
	
	
	@Override
	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		comp.setLayout(layout);
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER
				 | GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 1;
		
		Label namelabel = new Label(comp, SWT.WRAP);
		namelabel.setText("Name");
		namelabel.setLayoutData(gd);

		nametextbox = new Text(comp, SWT.SINGLE | SWT.BORDER);
		nametextbox.setLayoutData(gd);
		
	    setControl(comp);  
	    setPageComplete(true);
		
	}
	

	public String getNameTextBoxContent(){
		return nametextbox.getText();
	}

}
