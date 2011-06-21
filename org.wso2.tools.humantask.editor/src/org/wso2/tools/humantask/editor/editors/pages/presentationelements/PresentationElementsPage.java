package org.wso2.tools.humantask.editor.editors.pages.presentationelements;



import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationElements;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;

public class PresentationElementsPage extends FormPage {

	TPresentationElements presentationElements;
	protected String pageTitle;
	
	private PresentationElementMasterBlock block;
	
	public PresentationElementsPage(HTMultiPageEditor editor, String title) {
		super(editor, "HTPE", title);
		this.pageTitle = super.getTitle();
		//this.presentationElements = presentationElements;
		block = new PresentationElementMasterBlock(this,editor);
	}
	/*public PresentationElementsPage(HTMultiPageEditor editor, String title,TPresentationElements presentationElements) {
		super(editor, "HTPE", title);
		this.pageTitle = super.getTitle();
		this.presentationElements = presentationElements;
		block = new PresentationElementMasterBlock(this,editor);
	}*/
	
	protected void createFormContent(final IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		//FormToolkit toolkit = managedForm.getToolkit();
		form.setText("Presentaion Element"); 
		block.createContent(managedForm);
	}
	
	public String getTitle() {
		return this.pageTitle;
	}
	
}
