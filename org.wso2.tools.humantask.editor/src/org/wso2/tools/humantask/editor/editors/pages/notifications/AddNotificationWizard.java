package org.wso2.tools.humantask.editor.editors.pages.notifications;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TNotification;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

public class AddNotificationWizard extends Wizard {

	
	
	private AddNotificationWizardPage page;
	private EditingDomain domain;
	private TableViewer tableviewer;
	private THumanInteractions humanInteractions;
	
	public AddNotificationWizard(THumanInteractions humanInteractions,NotificationPage notificationPage,EditingDomain domain,
			TableViewer tableviewer){
		this.domain = domain;
		this.tableviewer = tableviewer;
		this.humanInteractions =humanInteractions;
	}
	
	public void addPages(){
		page = new AddNotificationWizardPage();
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
		TNotification notification = factory.createTNotification();
		
		if(humanInteractions.getNotifications()==null)
		{
			
			humanInteractions.setNotifications(factory.createTNotifications());
			
			humanInteractions.getNotifications().getNotification().add(notification);
			
			humanInteractions.getNotifications().getNotification().get(0).setInterface(factory.createTNotificationInterface());
			
		}
		
		
		humanInteractions.getNotifications().getNotification().add(notification);
		
		int size = humanInteractions.getNotifications().getNotification().size();
	
		humanInteractions.getNotifications().getNotification().get(size -1).setInterface(factory.createTNotificationInterface());
		
		setAttribute(htdPackage.eINSTANCE.getTNotification_Name(),page.getNameTextBoxContent(),size);
	
		tableviewer.add(notification);
		return true;
		
	}

	private void setAttribute(EAttribute tExtension_Attribute, String text,int size) {
		Command setAttribCommand = SetCommand.create(domain, humanInteractions.getNotifications().getNotification().get(size-1), tExtension_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tExtension_Attribute.getName());
		}
	}


}
