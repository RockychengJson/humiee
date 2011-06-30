package org.wso2.tools.humantask.editor.editors.pages.task;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationParameter;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;


public class AddPresentationParmWizard extends Wizard{

	private AddPParmWizardPage page;
	private EditingDomain domain;
	private TableViewer tableviewer;
	private TaskPage taskPage;
	private TTask selectedTask;
	
	public AddPresentationParmWizard(TaskPage taskPage,EditingDomain domain,
			TableViewer tableviewer){
	
		this.domain = domain;
		this.tableviewer = tableviewer;
		this.taskPage = taskPage;
	}
	
	
	public void addPages(){
		page = new AddPParmWizardPage();
		addPage(page);
	}
	
	
	
	@Override
	public boolean performFinish() {
		htdFactory factory= htdFactory.eINSTANCE;
		TPresentationParameter tPparm = factory.createTPresentationParameter();
		
		taskPage.input.getPresentationElements().getPresentationParameters().getPresentationParameter().add(tPparm);
		
		int size = taskPage.input.getPresentationElements().getPresentationParameters().getPresentationParameter().size();
		setAttribute(htdPackage.eINSTANCE.getTParameter_Name(),page.getNameTextBoxContent(),size);
		
		tableviewer.add(tPparm);
		
		return true;
	}
	
	private void setAttribute(EAttribute tParameter_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain, taskPage.input.getPresentationElements().getPresentationParameters().getPresentationParameter().get(size-1), tParameter_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tParameter_Attribute.getName());
		}
	}

}
