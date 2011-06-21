package org.wso2.tools.humantask.editor.editors.pages.task;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTasks;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;
import org.wso2.tools.humantask.editor.editors.base.util.EMFObjectHandleUtil;

public class PeopleAssignmentTab {
	
	protected EditingDomain domain;
	protected TTasks tasks;
	protected TTask input;
	
	
	PeopleAssignmentTab(EditingDomain domain,TTasks tasks)
	{
		this.domain=domain;
		this.tasks=tasks;
		
	}

	 void configPeopleAssignSection_exp_lang(final Text exp_langTextBox) {
		if (tasks != null) {
			if ((tasks.getTask().get(0).getInterface().getResponseOperation() != null)) {
				exp_langTextBox.setText((tasks.getTask().get(0).getInterface()
						.getOperation()));
			} else {
				exp_langTextBox
						.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			}
		}
		exp_langTextBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				setAttribute(htdPackage.eINSTANCE
						.getTTaskInterface_ResponseOperation(),
						exp_langTextBox.getText());
			}
		});

	}
	

	private void setAttribute(EAttribute tTaskInterface_Attribute, String text) {
		Command setAttribCommand = SetCommand.create(domain, input.getInterface(), tTaskInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
		} else {
			System.out.println("can't modify Attribute: "
					+ tTaskInterface_Attribute.getName());
		}
	}
	
	
	
}
