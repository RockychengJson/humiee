package org.wso2.tools.humantask.editor.editors.pages.humanInteractions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TBoolean;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExtension;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TImport;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

public class HIImportWizard extends Wizard{

	private EditingDomain domain;
	private THumanInteractions humanInteractions;
	private  TableViewer tableviewer;
	
	private HIImportWizardPage page;
	
	
	public HIImportWizard(THumanInteractions humanInteractions,EditingDomain domain,TableViewer tableviewer ){
		super();
		this.humanInteractions=humanInteractions;
		this.domain=domain;
		this.tableviewer=tableviewer;
	}
	
	
	public void addPages(){
		page = new HIImportWizardPage();
		addPage(page);
	}
	
	
public boolean performCancel()  {
		
		page.dispose();
		return true;
	  }


	@Override
	public boolean performFinish() {
		// TODO viraj check out pou in the console you will understand how it works
		
		htdFactory factory= htdFactory.eINSTANCE;
		TImport tImport= factory.createTImport();					
		humanInteractions.getImport().add(tImport);		
		
		int size=humanInteractions.getImport().size();
		setAttribute(htdPackage.eINSTANCE.getTImport_Namespace(),page.getNameSpace(),size);
		setAttribute(htdPackage.eINSTANCE.getTImport_Location(), page.getLocation(), size);
		setAttribute(htdPackage.eINSTANCE.getTImport_ImportType(), page.getImportType(), size);
		//setAttribute(htdPackage.eINSTANCE.getTExtension_MustUnderstand(),TBoolean.get(page.getMustUnd()),size);
		
		//System.out.println(page.getNameSpace()+""+page.getLocation() +" "+page.getImportType());
		tableviewer.add(tImport);
		return true;
	}
	
	private void setAttribute(EAttribute tImport_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain, humanInteractions.getImport().get(size-1), tImport_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tImport_Attribute.getName());
		}
	}

}
