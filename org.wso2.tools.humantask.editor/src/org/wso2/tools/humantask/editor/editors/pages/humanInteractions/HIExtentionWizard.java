package org.wso2.tools.humantask.editor.editors.pages.humanInteractions;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.impl.BasicNotifierImpl.EObservableAdapterList.Listener;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TBoolean;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExtension;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExtensions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TExtensionImpl;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.provider.TExtensionItemProvider;

public class HIExtentionWizard extends Wizard{

	private EditingDomain domain;
	//private ComposedAdapterFactory adapterFactory;
	private THumanInteractions humanInteractions;
	private  TableViewer tableviewer;
	private HumanInteractionsPage hi_page;
	private HIExtentionWizardpage page; 
	
	public HIExtentionWizard (THumanInteractions humanInteractions,EditingDomain domain,TableViewer tableviewer){
		super();
		this.humanInteractions=humanInteractions;
		this.domain=domain;
		this.tableviewer=tableviewer;
		//this.hi_page = page;
		//Infrastructure assignments goes here
	}
	
	
	public void addPages(){
		page = new HIExtentionWizardpage();
		addPage(page);
	}
	
	
	public boolean performCancel()  {
		
		page.dispose();
		return true;
	  }
	
	@Override
	public boolean performFinish() {
		// TODO viraj : implement the methods for do the wizard stuff
		htdFactory factory= htdFactory.eINSTANCE;
		TExtension tExtension= factory.createTExtension();
		
		if(humanInteractions.getExtensions()==null)
		{
			humanInteractions.setExtensions(factory.createTExtensions());
			humanInteractions.getExtensions().getExtension().add(tExtension);
		}
		humanInteractions.getExtensions().getExtension().add(tExtension);
		
		int size=humanInteractions.getExtensions().getExtension().size();
		setAttribute(htdPackage.eINSTANCE.getTExtension_Namespace(),page.getNameSpace(),size);
		setAttribute(htdPackage.eINSTANCE.getTExtension_MustUnderstand(),TBoolean.get(page.getMustUnd()),size);
	
		tableviewer.add(tExtension);
		//tableviewer.setInput(hi_page.createModle());
		return true;
	}
	
	private void setAttribute(EAttribute tExtension_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain, humanInteractions.getExtensions().getExtension().get(size-1), tExtension_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tExtension_Attribute.getName());
		}
	}
	private void setAttribute(EAttribute tExtension_Attribute, TBoolean text,int size) {
		Command setAttribCommand = SetCommand.create(domain, humanInteractions.getExtensions().getExtension().get(size-1), tExtension_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tExtension_Attribute.getName());
		}
	}

}
