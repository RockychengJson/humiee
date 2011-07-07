package org.wso2.tools.humantask.editor.editors.pages.task;

import org.eclipse.jface.wizard.Wizard;

public class AddPeopleAssiWizard extends Wizard {

	private AddPeopleAssiWizardPage page;
	
	
	
	public AddPeopleAssiWizard(){
		
	}
	
	
	public void addPages(){
		page = new AddPeopleAssiWizardPage();
		addPage(page);
		
	}
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

}
