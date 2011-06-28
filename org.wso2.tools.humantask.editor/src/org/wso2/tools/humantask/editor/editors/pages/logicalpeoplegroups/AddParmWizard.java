package org.wso2.tools.humantask.editor.editors.pages.logicalpeoplegroups;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Listener;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLogicalPeopleGroup;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TParameter;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

public class AddParmWizard extends Wizard{

	private AddParmWizardPage page;
	private THumanInteractions humanInteractions;
	private EditingDomain domain;
	private TableViewer tableviewer;
	private LogicalPeopleGroupPage logicalPeopleGroupPage;
	
	public AddParmWizard(THumanInteractions humanInteractions,EditingDomain domain,TableViewer tableviewer,LogicalPeopleGroupPage logicalPeopleGroupPage)
	{
		this.humanInteractions=humanInteractions;
		this.domain=domain;
		this.tableviewer=tableviewer;
		this.logicalPeopleGroupPage=logicalPeopleGroupPage;
	}
	
	public void addPages(){
		page = new AddParmWizardPage();
		addPage(page);
	}
	
	
	@Override
	public boolean performFinish() {
		htdFactory factory= htdFactory.eINSTANCE;
		TParameter tParameter= factory.createTParameter();
	
		logicalPeopleGroupPage.selectedLogicalPplGroup.getParameter().add(tParameter);
		
		
		int size=logicalPeopleGroupPage.selectedLogicalPplGroup.getParameter().size();
		setAttribute(htdPackage.eINSTANCE.getTParameter_Name(),page.getNameTextBoxContent(),size);
		
	
		tableviewer.add(tParameter);
		//tableviewer.setInput(hi_page.createModle());
		return true;
	}
	
	private void setAttribute(EAttribute tParameter_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain, logicalPeopleGroupPage.selectedLogicalPplGroup.getParameter().get(size-1), tParameter_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tParameter_Attribute.getName());
		}
	}
	
	public boolean performCancel()  {
		
		page.dispose();
	    return true;
	}


}
