package org.wso2.tools.humantask.editor.editors.pages.logicalpeoplegroups;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.ui.forms.IManagedForm;

import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLogicalPeopleGroups;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;



public class LogicalPeopleGroupPage extends FormPage {

	protected HTMultiPageEditor editor;
	protected EditingDomain domain;
	protected THumanInteractions  humanInteractions;
	//protected TLogicalPeopleGroups logicalPeopleGroups;
	protected FormToolkit toolkit;
	protected ComposedAdapterFactory adapterFactory;
	protected String pageTitle;
	
		
	private boolean isResourceChanged = false;
	
	private LogicalPeopleGroupMasterBlock mBlock;
	
	
	public LogicalPeopleGroupPage(HTMultiPageEditor editor,THumanInteractions  humanInteractions){
		super(editor, "HTLP", "Logical People Groups");
		this.pageTitle = super.getTitle();
		this.editor = editor;
		this.domain = this.editor.getEditingDomain();
		this.adapterFactory = editor.getAdapterFactory();
		this.humanInteractions = humanInteractions;
		//this.logicalPeopleGroups = logicalPeopleGroups;
		
		mBlock = new LogicalPeopleGroupMasterBlock(this, editor, this.humanInteractions);
	}
	
	
	public String getTitle() {
		return this.pageTitle;
	}
	
	/*
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {
		
		final ScrolledForm form = managedForm.getForm();
		form.setText("Logical People Group");
		mBlock.createContent(managedForm);
		
	}

	public void setResourceChanged(boolean value) {
		this.isResourceChanged = value;
	}

	public boolean isResourceChanged() {
		return this.isResourceChanged;
	}

	
}
