package org.wso2.tools.humantask.editor.editors.pages.task;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;

public class AddPeopleAssiWizard extends Wizard {

	private AddPeopleAssiWizardPage page;
	
	
	
	public AddPeopleAssiWizard(THumanInteractions humanInteractions, EditingDomain domain, TableViewer viewer_peopleAssignment, TaskPage taskPage){
		
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
