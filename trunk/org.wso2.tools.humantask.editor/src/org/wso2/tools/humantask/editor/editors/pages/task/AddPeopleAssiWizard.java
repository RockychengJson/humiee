package org.wso2.tools.humantask.editor.editors.pages.task;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TArgument;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TBoolean;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExtension;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TFrom;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TGenericHumanRole;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPeopleAssignments;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;
import org.wso2.tools.humantask.editor.editors.pages.util.Messages;

public class AddPeopleAssiWizard extends Wizard {

	private AddPeopleAssiWizardPage page;
	
	THumanInteractions humanInteractions;
	EditingDomain domain;
	TableViewer viewer_peopleAssignment;
	TaskPage taskPage;
	
	
	public AddPeopleAssiWizard(THumanInteractions humanInteractions, EditingDomain domain, TableViewer viewer_peopleAssignment, TaskPage taskPage){
		this.humanInteractions=humanInteractions;
		this.domain=domain;
		this.viewer_peopleAssignment=viewer_peopleAssignment;
		this.taskPage=taskPage;
	}
	
	
	public void addPages(){
		page = new AddPeopleAssiWizardPage();
		addPage(page);
		
	}
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		htdFactory factory= htdFactory.eINSTANCE;
		TArgument tArgument=factory.createTArgument();
		TFrom tFrom= factory.createTFrom();
		tFrom.setArgument(tArgument);
		
		TGenericHumanRole tGenericHumanRole= factory.createTGenericHumanRole();	
		tGenericHumanRole.setFrom(tFrom);
		
		
		if(taskPage.input.getPeopleAssignments()==null){
			
			TPeopleAssignments tPeopleAssignments= factory.createTPeopleAssignments();
			taskPage.input.setPeopleAssignments(tPeopleAssignments);
		
		}
		
		String s=page.getRoleType();
		System.out.println(s);
		
		if((Messages.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.potentialownerslable")).equals(page.getRoleType())){
			taskPage.input.getPeopleAssignments().getPotentialOwners().add(tGenericHumanRole);
			
			int size=taskPage.input.getPeopleAssignments().getPotentialOwners().size();
			setAttribute_potentialOwners(htdPackage.eINSTANCE.getTArgument_Name(),page.getNameTextBoxContent(),size);
			viewer_peopleAssignment.add(new HumanRole(tGenericHumanRole, "PotentialOwners", size-1));
		}
		if((Messages.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.excludedowners")).equals(page.getRoleType())){
			taskPage.input.getPeopleAssignments().getExcludedOwners().add(tGenericHumanRole);
			
			int size=taskPage.input.getPeopleAssignments().getExcludedOwners().size();
			setAttribute_excludeOwners(htdPackage.eINSTANCE.getTArgument_Name(),page.getNameTextBoxContent(),size);
			viewer_peopleAssignment.add(new HumanRole(tGenericHumanRole, "ExcludedOwners", size-1));
		}
		if(Messages.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.taskinitiator").equals(page.getRoleType())){
			taskPage.input.getPeopleAssignments().getTaskInitiator().add(tGenericHumanRole);
			
			int size=taskPage.input.getPeopleAssignments().getTaskInitiator().size();
			setAttribute_taskInitiator(htdPackage.eINSTANCE.getTArgument_Name(),page.getNameTextBoxContent(),size);
			viewer_peopleAssignment.add(new HumanRole(tGenericHumanRole, "TaskInitiator", size-1));
			
		}
		if(Messages.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.taskstakeholders").equals(page.getRoleType())){
			taskPage.input.getPeopleAssignments().getTaskStakeholders().add(tGenericHumanRole);
			
			int size=taskPage.input.getPeopleAssignments().getTaskStakeholders().size();
			setAttribute_taskStakeholders(htdPackage.eINSTANCE.getTArgument_Name(),page.getNameTextBoxContent(),size);
			viewer_peopleAssignment.add(new HumanRole(tGenericHumanRole, "TaskStakeholders", size-1));
		}
		if(Messages.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.businessadministrators").equals(page.getRoleType())){
			taskPage.input.getPeopleAssignments().getBusinessAdministrators().add(tGenericHumanRole);
			
			int size=taskPage.input.getPeopleAssignments().getBusinessAdministrators().size();
			setAttribute_businessAdministrators(htdPackage.eINSTANCE.getTArgument_Name(),page.getNameTextBoxContent(),size);
			viewer_peopleAssignment.add(new HumanRole(tGenericHumanRole, "BusinessAdministrators", size-1));
		}
		if(Messages.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.notificationrecipients").equals(page.getRoleType())){
			taskPage.input.getPeopleAssignments().getRecipients().add(tGenericHumanRole);
			
			int size=taskPage.input.getPeopleAssignments().getRecipients().size();
			setAttribute_recipients(htdPackage.eINSTANCE.getTArgument_Name(),page.getNameTextBoxContent(),size);
			viewer_peopleAssignment.add(new HumanRole(tGenericHumanRole, "Recipients", size-1));
		}
		
		
	/*	
		taskPage.input.getPeopleAssignments().get .selectedLogicalPplGroup.getParameter().add(tParameter);
		
		
		int size=logicalPeopleGroupPage.selectedLogicalPplGroup.getParameter().size();
		setAttribute(htdPackage.eINSTANCE.getTParameter_Name(),page.getNameTextBoxContent(),size);
		
	
		tableviewer.add(tParameter);*/
		
		return true;
	}
	
	private void setAttribute_potentialOwners(EAttribute tArgument_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain,taskPage.input.getPeopleAssignments().getPotentialOwners().get(size-1).getFrom().getArgument(), tArgument_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tArgument_Attribute.getName());
		}
	}
	private void setAttribute_excludeOwners(EAttribute tArgument_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain,taskPage.input.getPeopleAssignments().getExcludedOwners().get(size-1).getFrom().getArgument(), tArgument_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tArgument_Attribute.getName());
		}
	}
	private void setAttribute_taskInitiator(EAttribute tArgument_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain,taskPage.input.getPeopleAssignments().getTaskInitiator().get(size-1).getFrom().getArgument(), tArgument_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tArgument_Attribute.getName());
		}
	}
	private void setAttribute_taskStakeholders(EAttribute tArgument_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain,taskPage.input.getPeopleAssignments().getTaskStakeholders().get(size-1).getFrom().getArgument(), tArgument_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tArgument_Attribute.getName());
		}
	}
	
	private void setAttribute_businessAdministrators(EAttribute tArgument_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain,taskPage.input.getPeopleAssignments().getBusinessAdministrators().get(size-1).getFrom().getArgument(), tArgument_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tArgument_Attribute.getName());
		}
	}
	private void setAttribute_recipients(EAttribute tArgument_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain,taskPage.input.getPeopleAssignments().getRecipients().get(size-1).getFrom().getArgument(), tArgument_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tArgument_Attribute.getName());
		}
	}

}
