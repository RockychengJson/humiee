package org.wso2.tools.humantask.editor.editors.pages.task;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

public class TaskCreatWizard extends Wizard{

	TaskCreateWizardPage page;
	THumanInteractions humanInteractions;
	EditingDomain domain;
	TableViewer tableviewer;
	
	
	public TaskCreatWizard (THumanInteractions humanInteractions,EditingDomain domain,TableViewer tableviewer ){
		super();
		this.domain=domain;
		this.humanInteractions=humanInteractions;
		this.tableviewer=tableviewer;
	}
	
	public void addPages(){
		page = new TaskCreateWizardPage();
		addPage(page);
	}
	
	
	public boolean performCancel()  
	  {
		page.dispose();
	    return true;
		
	  }
	
	@Override
	public boolean performFinish() {
		
		htdFactory factory= htdFactory.eINSTANCE;
		TTask tTask= factory.createTTask();
		
		if(humanInteractions.getTasks()==null)
		{
			humanInteractions.setTasks(factory.createTTasks());
			humanInteractions.getTasks().getTask().add(tTask);
			humanInteractions.getTasks().getTask().get(0).setInterface(factory.createTTaskInterface());
			
		}
		
		humanInteractions.getTasks().getTask().add(tTask);
		
		int size=humanInteractions.getTasks().getTask().size();
		humanInteractions.getTasks().getTask().get(size-1).setInterface(factory.createTTaskInterface());
		setAttribute(htdPackage.eINSTANCE.getTTask_Name(),page.getNameTextBoxContent(),size);
		
	
		tableviewer.add(tTask);
		return true;
		
	}

	private void setAttribute(EAttribute tExtension_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain, humanInteractions.getTasks().getTask().get(size-1), tExtension_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tExtension_Attribute.getName());
		}
	}

}
