package org.wso2.tools.humantask.editor.editors.pages.notifications;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TNotifications;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;

import org.wso2.tools.humantask.editor.editors.pages.util.Messages;



public class NotificationPage extends FormPage implements IResourceChangeListener,
Listener  {

	protected HTMultiPageEditor editor;
	protected EditingDomain domain;
	protected TNotifications notifications;
	protected FormToolkit toolkit;
	protected ComposedAdapterFactory adaptorFactory;
	private THumanInteractions humaninteractions;
	protected String pageTitle;
	
	private CTabFolder tabFolder;
	private ScrolledForm form;
	private TabContent[] temp;
	private int tempindex;
	private boolean isFirst = true;
	
	private Text portTextBox;
	private Text OportTextBox;
	private Text OresponseTextBox;
	private Text operationTextBox;
	
	private static final String[] FILTER_EXTS = { "*.wsdl","*.*" };
	
	public NotificationPage(HTMultiPageEditor editor,THumanInteractions humanInteractions){
		super(editor, "HTN", "Notifications");
		this.pageTitle = super.getTitle();
		this.editor = editor;
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this,
				IResourceChangeEvent.POST_CHANGE);
		this.domain = editor.getEditingDomain();
		this.adaptorFactory = editor.getAdapterFactory();
		this.humaninteractions = humanInteractions;
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
			gd.heightHint = 0;
			tabFolder.setLayoutData(gd);

			Color selectedColor = toolkit.getColors().getColor(FormColors.SEPARATOR);
			tabFolder.setSelectionBackground(new Color[] { selectedColor,
					toolkit.getColors().getBackground() }, new int[] { 50 });
			toolkit.paintBordersFor(tabFolder);

			createGenarelInfoTab(toolkit, form, managedForm);
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
	
	
	private void createGenarelInfoTab(FormToolkit toolkit, ScrolledForm form,
			IManagedForm managedform) {
		CTabItem item = new CTabItem(tabFolder, SWT.NULL);

		TabContent section1 = new TabContent(createGenaralInfoSection(toolkit,
				form, managedform));
		//TabContent section1 = new TabContent(taskTableSection);
		TabContent[] sectionArray = new TabContent[1];
		sectionArray[0] = section1;
		//sectionArray[1] = section2;
		item.setText(Messages.getString("TaskPage.interfaceTab.title"));
		item.setData(sectionArray);

	}
	
	private Section createGenaralInfoSection(FormToolkit toolkit,
			final ScrolledForm form, IManagedForm managedForm) {

		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText(Messages
				.getString("TaskPage.interfaceTab.Section.title"));
		section.setDescription(Messages
				.getString("TaskPage.interfaceTab.Section.description"));
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);

		
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);
		
		final Composite wsdl_import_comp = toolkit.createComposite(sectionClient);
		GridData wsdl_import_comp_gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		wsdl_import_comp_gd.horizontalSpan = 2;
		wsdl_import_comp.setLayoutData(wsdl_import_comp_gd);
		GridLayout wsdlComp_layout = new GridLayout(3 ,false);
		wsdl_import_comp.setLayout(wsdlComp_layout);
		
		Label import_label = new Label(wsdl_import_comp, SWT.WRAP);
		import_label.setText("Imported WSDLs");
		GridData import_lb_gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		import_lb_gd.horizontalSpan =1;
		import_label.setLayoutData(import_lb_gd);
		
		Combo comboDropDown = new Combo(wsdl_import_comp, SWT.DROP_DOWN | SWT.BORDER);
		comboDropDown.add("test 1");
		comboDropDown.add("test 2");
		comboDropDown.add("test 3");
		GridData combo_lb_gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		combo_lb_gd.horizontalSpan = 2;
		comboDropDown.setLayoutData(combo_lb_gd);
		
		Label select_wsdl_label = new Label(wsdl_import_comp, SWT.WRAP);
		select_wsdl_label.setText("Select the WSDL");
		//import_lb_gd.horizontalSpan =  1;
		select_wsdl_label.setLayoutData(import_lb_gd);
		
		final Text filename = new Text(wsdl_import_comp, SWT.SINGLE | SWT.BORDER);
		filename.setLayoutData(import_lb_gd);
		
		Button browse_btn = new Button(wsdl_import_comp, SWT.PUSH);
		browse_btn.setText("Browse");
		browse_btn.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent event) {
				
				FileDialog dlg = new FileDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), SWT.OPEN);
			  //dlg.setFilterNames(FILTER_NAMES);
				dlg.setFilterExtensions(FILTER_EXTS);
				String fn = dlg.open();
				if (fn != null) {
					filename.setText(fn);
				}
			}
			
		});
		browse_btn.setLayoutData(import_lb_gd);
		
		
		// Port Type label and Text box
		Label portTypeLabel = new Label(sectionClient, SWT.WRAP);
		portTypeLabel.setText(Messages
				.getString("TaskPage.interfaceTab.Section.porttypelable"));
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		portTypeLabel.setLayoutData(data);
		portTextBox = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		portTextBox.setSize(100, 20);
		portTextBox.setLayoutData(data);
		//configGeneralInfoSection_portType(portTextBox);

		// Operation label and Text box
		Label operationLabel = new Label(sectionClient, SWT.WRAP);
		operationLabel.setText(Messages
				.getString("TaskPage.interfaceTab.Section.operationlable"));
		operationLabel.setLayoutData(data);
		operationTextBox = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		operationTextBox.setSize(100, 20);
		operationTextBox.setLayoutData(data);
		//configGeneralInfoSection_operation(operationTextBox);

		// Radio button
		Label radioSectionLabel = new Label(sectionClient, SWT.WRAP);
		radioSectionLabel.setText(Messages
				.getString("TaskPage.interfaceTab.Section.radiosectionlable"));
		radioSectionLabel.setLayoutData(data);
		Composite radiocomposite = toolkit.createComposite(sectionClient);
		FillLayout fl = new FillLayout(SWT.HORIZONTAL);
		radiocomposite.setLayout(fl);
		Button oneway = new Button(radiocomposite, SWT.RADIO);
		oneway.setText(Messages
				.getString("TaskPage.interfaceTab.Section.onewayradiolable"));
		oneway.setSelection(true);

		Button requestres = new Button(radiocomposite, SWT.RADIO);
		requestres.setText(Messages
				.getString("TaskPage.interfaceTab.Section.reqresradiolable"));
		requestres.setSelection(false);

		// OportType label and Text box
		final Label OportTypeLabel = new Label(sectionClient, SWT.WRAP);
		OportTypeLabel
				.setText(Messages
						.getString("TaskPage.interfaceTab.Section.onewayradio.portlable"));
		OportTypeLabel.setLayoutData(data);

		OportTextBox = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		OportTextBox.setSize(100, 20);
		OportTextBox.setLayoutData(data);
		//configGeneralInfoSection_OportType(OportTextBox);

		// Oresponse label and Text box
		final Label OresponseLabel = new Label(sectionClient, SWT.WRAP);
		OresponseLabel
				.setText(Messages
						.getString("TaskPage.interfaceTab.Section.onewayradio.responselable"));
		OresponseLabel.setLayoutData(data);

		OresponseTextBox = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		OresponseTextBox.setSize(100, 20);
		OresponseTextBox.setLayoutData(data);
		//configGeneralInfoSection_Oresponse(OresponseTextBox);

		oneway.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {

				OportTextBox.setEnabled(true);

				OresponseTextBox.setEnabled(true);
			}

		});

		requestres.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {

				OportTextBox.setEnabled(false);

				OresponseTextBox.setEnabled(false);
			}
		});
		//section.setClient(wsdl_import_comp);
		section.setClient(sectionClient);

		return section;

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
