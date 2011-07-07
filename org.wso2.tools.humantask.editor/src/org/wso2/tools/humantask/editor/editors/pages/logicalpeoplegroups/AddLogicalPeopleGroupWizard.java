package org.wso2.tools.humantask.editor.editors.pages.logicalpeoplegroups;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLogicalPeopleGroup;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

public class AddLogicalPeopleGroupWizard extends Wizard {

	private AddLogicalPeopleGroupWizardPage page;
	private THumanInteractions humanInteractions;
	private EditingDomain domain;
	private TableViewer tableviewer;
	
	public AddLogicalPeopleGroupWizard(THumanInteractions humanInteractions,EditingDomain domain,TableViewer tableviewer){
		this.humanInteractions=humanInteractions;
		this.domain=domain;
		this.tableviewer=tableviewer;
	}
	
	public void addPages(){
		page = new AddLogicalPeopleGroupWizardPage();
		addPage(page);
	}
	
	
	@Override
	public boolean performFinish() {
		htdFactory factory= htdFactory.eINSTANCE;
		TLogicalPeopleGroup tLogicalPeopleGroup= factory.createTLogicalPeopleGroup();
		
		if(humanInteractions.getLogicalPeopleGroups()==null)
		{
			humanInteractions.setLogicalPeopleGroups(factory.createTLogicalPeopleGroups());
			
		}
		humanInteractions.getLogicalPeopleGroups().getLogicalPeopleGroup().add(tLogicalPeopleGroup);
		
		int size=humanInteractions .getLogicalPeopleGroups().getLogicalPeopleGroup().size();
		setAttribute(htdPackage.eINSTANCE.getTLogicalPeopleGroup_Name(),page.getNameTextBoxContent(),size);
		
	
		tableviewer.add(tLogicalPeopleGroup);
		//tableviewer.setInput(hi_page.createModle());
		return true;
	}
	
	private void setAttribute(EAttribute tLogicalPeopleGroup_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain, humanInteractions.getLogicalPeopleGroups().getLogicalPeopleGroup().get(size-1), tLogicalPeopleGroup_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tLogicalPeopleGroup_Attribute.getName());
		}
	}
	
	public boolean performCancel()  {
		
		page.dispose();
	    return true;
	}

}
