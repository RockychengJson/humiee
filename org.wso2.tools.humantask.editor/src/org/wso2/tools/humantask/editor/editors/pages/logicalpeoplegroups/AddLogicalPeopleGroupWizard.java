package org.wso2.tools.humantask.editor.editors.pages.logicalpeoplegroups;

import org.eclipse.jface.wizard.Wizard;

public class AddLogicalPeopleGroupWizard extends Wizard {

	private AddLogicalPeopleGroupWizardPage page;
	
	public AddLogicalPeopleGroupWizard(){
		
	}
	
	public void addPages(){
		page = new AddLogicalPeopleGroupWizardPage();
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
