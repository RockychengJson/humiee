package org.wso2.tools.humantask.editor.editors.pages.task;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationParameter;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

public class AddPElemNameWIzard extends Wizard {

	private AddPElemNameWizardPage page;
	private EditingDomain domain;
	private TableViewer tableviewer;
	private TaskPage taskPage;
	
	
	public AddPElemNameWIzard(TaskPage taskPage,EditingDomain domain,
			TableViewer tableviewer){
		this.domain = domain;
		this.tableviewer = tableviewer;
		this.taskPage = taskPage;
	}
	
	public void addPages(){
		page = new AddPElemNameWizardPage();
		addPage(page);
	}
	
	
	@Override
	public boolean performFinish() {
		//TODO 
		
		return false;
	}
	
	

}
