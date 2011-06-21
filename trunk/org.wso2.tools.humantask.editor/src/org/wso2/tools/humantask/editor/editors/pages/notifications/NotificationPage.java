package org.wso2.tools.humantask.editor.editors.pages.notifications;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TNotifications;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;



public class NotificationPage extends FormPage implements IResourceChangeListener,
Listener  {

	protected HTMultiPageEditor editor;
	protected EditingDomain domain;
	protected TNotifications notifications;
	protected FormToolkit toolkit;
	protected ComposedAdapterFactory adaptorFactory;
	protected String pageTitle;
	
	private CTabFolder tabFolder;
	private ScrolledForm form;
	private TabContent[] temp;
	private int tempindex;
	private boolean isFirst = true;
	
	
	public NotificationPage(HTMultiPageEditor editor,TNotifications notifications){
		super(editor, "HTN", "Notifications");
		this.pageTitle = super.getTitle();
		this.editor = editor;
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this,
				IResourceChangeEvent.POST_CHANGE);
		this.domain = editor.getEditingDomain();
		this.adaptorFactory = editor.getAdapterFactory();
		this.notifications = notifications;
	}
	
	
	public String getTitle() {
		return this.pageTitle;
	}
	
	protected void createFormContent(IManagedForm managedForm){
		if (!domain.getResourceSet().getResources().isEmpty()) {

			toolkit = managedForm.getToolkit();
			form = managedForm.getForm();
			form.setText("Human Task");

			GridLayout layout = new GridLayout(); // layout for the form body
			layout.numColumns = 2;
			layout.marginWidth = 10;
			form.getBody().setLayout(layout);

			tabFolder = new CTabFolder(form.getBody(), SWT.FLAT | SWT.TOP);
			toolkit.adapt(tabFolder, true, true);

			GridData gd = new GridData(GridData.FILL_HORIZONTAL); 
			tabFolder.setLayoutData(gd);

			Color selectedColor = toolkit.getColors().getColor(FormColors.SEPARATOR);
			tabFolder.setSelectionBackground(new Color[] { selectedColor,
					toolkit.getColors().getBackground() }, new int[] { 50 });
			toolkit.paintBordersFor(tabFolder);

			//createGenarelInfoTab(toolkit, form, managedForm);
			//createPeaopleAssTab(toolkit, form, managedForm);
			//createPresentationElementTab(toolkit, form, managedForm);
			

			tabFolder.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					updateSelection();
				}
			});
			tabFolder.setSelection(0);
			updateSelection();
		}
	}

	private void updateSelection() {

		CTabItem item = tabFolder.getSelection();

		TabContent[] asoData = (TabContent[]) item.getData();

		if (isFirst) {
			isFirst = false;
			temp = asoData;
			tempindex = tabFolder.getSelectionIndex();
			for (int i = 0; i < asoData.length; ++i) {
				((Section) asoData[i].content).setParent(form.getBody());
			}
		} else {

			if (tabFolder.getSelectionIndex() == tempindex) {

				// preparedSection.setParent(form.getBody());

			} else {

				for (int i = 0; i < temp.length; ++i) {
					temp[i].content.setParent(tabFolder);
				}
				for (int i = 0; i < asoData.length; ++i) {
					((Section) asoData[i].content).setParent(form.getBody());
				}

				temp = asoData;
				tempindex = tabFolder.getSelectionIndex();
			}
		}

		form.reflow(true);

	}
	
	
	
	
	class TabContent {

		Section content;

		public TabContent(Section content) {
			this.content = content;
		}

	}
	@Override
	public void handleEvent(Event arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resourceChanged(IResourceChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
