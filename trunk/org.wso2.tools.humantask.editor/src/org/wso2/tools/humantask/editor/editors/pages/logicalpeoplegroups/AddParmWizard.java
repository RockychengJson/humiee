package org.wso2.tools.humantask.editor.editors.pages.logicalpeoplegroups;

import org.eclipse.jface.wizard.Wizard;

public class AddParmWizard extends Wizard{

	private AddParmWizardPage page;
	
	
	
	public void addPages(){
		page = new AddParmWizardPage();
		addPage(page);
	}
	
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean performCancel()  {
		
		page.dispose();
	    return true;
	}


}
