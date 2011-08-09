package org.wso2.tools.humantask.editor.editors.pages.notifications;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardDialog;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
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
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TDescription;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TNotification;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TNotifications;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationParameter;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TText;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;
import org.wso2.tools.humantask.editor.editors.base.util.EMFObjectHandleUtil;
import org.wso2.tools.humantask.editor.editors.pages.util.Messages;



public class NotificationPage extends FormPage implements IResourceChangeListener,Listener  {

	protected HTMultiPageEditor editor;
	protected EditingDomain domain;
	protected TNotifications notifications;
	protected FormToolkit toolkit;
	protected ComposedAdapterFactory adaptorFactory;
	private THumanInteractions humaninteractions;
	protected String pageTitle;
	private TNotification selectedNotification;
	
	private NotificationPage notificationPage;
	
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
	
	private TableViewer notficationViewer;
	private Section notificationTableSection;
	
	private TableViewer viewer_peopleAssignment;
	private Table table_peopleAssingment;
	
	private Combo roal_type;
	private Combo selectLogicalPeopleGroup_combo;
	private Text peopleAssignmentNametextbox;
	private Text peopleAssignmentExptextbox;
	
	//presentation element tab variables
	
	private TableViewer presentationElemNameViewer;
	private Table table_presentationElemName;
	
	private Text preElem_GenInfo_name_txt;
	private Text preElem_GenInfo_lang_txt;
	
	private TableViewer PresentationParameterViewer;
	private Table table_presentationParm;
	
	private Text preParm_nametext;
	private Text preParm_typetext;
	private Text preParm_exptext;
	
	private TableViewer presentationElemSubjectViewer;
	private Table table_presentationElemSub;
	
	private TableViewer presentationElemDescViewer;
	private Table table_PresentationElemDesc;
	
	private Text preElemSubInfo_subject_txt;
	private Text preElemSubInfo_lang_txt;
	
	private Text preElemDescInfo_lang_txt;
	private Combo preElemDescInfo_context_type;
	private Text preElemDescInfo_desc_txt;
	
	
	public NotificationPage(HTMultiPageEditor editor,THumanInteractions humanInteractions){
		super(editor, "HTN", "Notifications");
		this.pageTitle = super.getTitle();
		this.editor = editor;
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this,
				IResourceChangeEvent.POST_CHANGE);
		this.domain = editor.getEditingDomain();
		this.adaptorFactory = editor.getAdapterFactory();
		this.humaninteractions = humanInteractions;
		this.notifications = this.humaninteractions.getNotifications();
		this.notificationPage = this;
	}
	
	
	public String getTitle() {
		return this.pageTitle;
	}
	
	protected void createFormContent(IManagedForm managedForm){
		if (!domain.getResourceSet().getResources().isEmpty()) {

			toolkit = managedForm.getToolkit();
			form = managedForm.getForm();
			form.setText("Notifications");

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

			checkAvailability_Notifications();
			
			notificationTableSection = createNotificationTableSection(toolkit, form);
			
			createGenarelInfoTab(toolkit, form);
			createPeaopleAssTab(toolkit, form);
			createPresentationElementTab(toolkit, form);
			

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
	
	
	private void createGenarelInfoTab(FormToolkit toolkit, ScrolledForm form) {
		CTabItem item = new CTabItem(tabFolder, SWT.NULL);

		TabContent section1 = new TabContent(notificationTableSection);
		TabContent section2 = new TabContent(createGenaralInfoSection(toolkit,form));
		
		TabContent[] sectionArray = new TabContent[2];
		sectionArray[0] = section1;
		sectionArray[1] = section2;
		
		item.setText(Messages.getString("TaskPage.interfaceTab.title"));
		item.setData(sectionArray);
	}
	
	private void createPeaopleAssTab(FormToolkit toolkit,ScrolledForm form){
		CTabItem item = new CTabItem(tabFolder, SWT.NULL);
		TabContent section1 = new TabContent(notificationTableSection);
		TabContent section2 = new TabContent(createPeopleAssiSection(toolkit, form));
		TabContent section3 = new TabContent(createLogicalPplSection(toolkit, form));
		
		TabContent[] sectionArray = new TabContent[3];
		sectionArray[0] = section1;
		sectionArray[1] = section2;
		sectionArray[2] = section3;
		
		item.setText(Messages.getString("TaskPage.peopleassingmentTab.title"));
		item.setData(sectionArray);
	}
		
	private void createPresentationElementTab(FormToolkit toolkit,ScrolledForm form) {
		CTabItem item = new CTabItem(tabFolder, SWT.NULL);
		
		TabContent section1 = new TabContent(notificationTableSection);
		TabContent section2 = new TabContent(createPElemGenralTableSection(toolkit));
		TabContent section3 = new TabContent(createPElemGeneralInfoSection(toolkit));
		TabContent section4 = new TabContent(createPParamTableSection(toolkit));
		TabContent section5 = new TabContent(createPParmDetailSection(toolkit));
		TabContent section6 = new TabContent(createPElemSubjectTableSection(toolkit));
		TabContent section7 = new TabContent(createPElemSubjectInfoSection(toolkit));
		TabContent section8 = new TabContent(createPElemDescTableSection(toolkit));
		TabContent section9 = new TabContent(createPElemDesInfoSection(toolkit));
		
		TabContent[] sectionArray = new TabContent[9];
		sectionArray[0] = section1;
		sectionArray[1] = section2;
		sectionArray[2] = section3;
		sectionArray[3] = section4;
		sectionArray[4] = section5;
		sectionArray[5] = section6;
		sectionArray[6] = section7;
		sectionArray[7] = section8;
		sectionArray[8] = section9;
		
		item.setText(Messages.getString("TaskPage.presentationElementTab.title"));
		item.setData(sectionArray);
		
	}
	
	//creating sections
	private Section createNotificationTableSection(FormToolkit toolkit,final ScrolledForm form){
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		
		section.setText("Create Notification");
		section.setDescription("Add Later");
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);
		
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);
		
		notficationViewer = new TableViewer(sectionClient, SWT.MULTI
				| SWT.BORDER);
		createColumnsforNotificationTable(sectionClient, notficationViewer);
		
		final Table table = notficationViewer.getTable();

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan =10;
		gd.heightHint = 20;
		gd.widthHint = 100;

		table.setLayoutData(gd);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		notficationViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				notificationTableItemSelecter(event.getSelection());

			}
		});

		notficationViewer.setContentProvider(new NotificationContentProvider());
		notficationViewer.setInput(createNotificationModle());
		table.setSelection(0);
		
		Button add_btn = toolkit.createButton(sectionClient, "Add", SWT.PUSH); //$NON-NLS-1$
		GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(btn_gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				//TODO create New notification wizard
				AddNotificationWizard wizard = new AddNotificationWizard(humaninteractions,notificationPage,domain,notficationViewer);
				WizardDialog wizardDialog = new WizardDialog(Display .getCurrent().getActiveShell(),wizard);
				wizardDialog.create(); 
				wizardDialog.open();
			}
		});
		
		Button del_btn = toolkit.createButton(sectionClient, "Delete", SWT.PUSH); 
		del_btn.setLayoutData(btn_gd);

		del_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

			//TODO viraj :handle the action

			}
		});
		
		section.setClient(sectionClient);
		return section;
		
		
	}
	
	private Section createGenaralInfoSection(FormToolkit toolkit,
			final ScrolledForm form) {

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

		//Oresponse label and Text box
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
	
	private Section createPeopleAssiSection(FormToolkit toolkit,
			final ScrolledForm form){
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		
		section.setText("Existing People Assingments ");
		section.setDescription("Add later");
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);
		
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);
		
		viewer_peopleAssignment = new TableViewer(sectionClient, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumnsPeopleAssiTable(sectionClient, viewer_peopleAssignment);

		table_peopleAssingment = viewer_peopleAssignment.getTable();

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 10;
		gd.heightHint = 20;
		gd.widthHint = 100;

		table_peopleAssingment.setLayoutData(gd);

		table_peopleAssingment.setHeaderVisible(true);
		table_peopleAssingment.setLinesVisible(true);

		viewer_peopleAssignment
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						//itemSelecter_peopleAssignment(event.getSelection());

					}
				});

		viewer_peopleAssignment
				.setContentProvider(new NPeopleAssiContentProvider());
		viewer_peopleAssignment.setInput(createModleForPeopleAssinment());
		table_peopleAssingment.setSelection(0);
		
		
		Button add_btn = toolkit.createButton(sectionClient, "Add", SWT.PUSH);
		GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(btn_gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				
				//AddPeopleAssiWizard wizard = new AddPeopleAssiWizard( humanInteractions, domain, viewer_peopleAssignment,taskPage);
				//WizardDialog wizardDialog = new WizardDialog(Display .getCurrent().getActiveShell(),wizard);
				//wizardDialog.create();
				//wizardDialog.open();
				 
			}
		});
		
		Button del_btn = toolkit.createButton(sectionClient, "Delete", SWT.PUSH); 
		del_btn.setLayoutData(btn_gd);

		del_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

			//TODO viraj :handle the action

			}
		});
		
		section.setClient(sectionClient);

		return section;
	}
	
	private Section createLogicalPplSection(final FormToolkit toolkit,
			final ScrolledForm form) {
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText(Messages
				.getString("TaskPage.peopleassingmentTab.Section.dynamic.vialist.methodone"));
		section.setDescription("Add later");
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, true);
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);
		
		Label selectRoleLabel = new Label(sectionClient, SWT.WRAP);
		selectRoleLabel.setText("Choose Role Type");

		GridData gd1 = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL
				| GridData.GRAB_VERTICAL);
		gd1.horizontalSpan = 1;
	
		selectRoleLabel.setLayoutData(gd1);
		
		
		roal_type = new Combo(sectionClient, SWT.READ_ONLY);
		roal_type.setLayoutData(gd1);
		
		roal_type
				.add(Messages
						.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.businessadministrators"),
						0);
		roal_type
				.add(Messages
						.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.notificationrecipients"),
						1);

		roal_type.select(0);
		
		
		GridData cgd1 = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		
		roal_type.setLayoutData(cgd1);
		//configPeopleAssignmentSection_roleType(roal_type);
		
		
		Label selectppllabel = new Label(sectionClient, SWT.WRAP);
		selectppllabel.setText("Select Logical group");

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL
				| GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 1;
		
		selectppllabel.setLayoutData(gd);
		

		// Should be reviewed
		selectLogicalPeopleGroup_combo = new Combo(sectionClient, SWT.READ_ONLY);
		selectLogicalPeopleGroup_combo.setLayoutData(gd);
		
		int size= humaninteractions.getLogicalPeopleGroups().getLogicalPeopleGroup().size();
		for(int i=0;i<size;++i)
		{
			selectLogicalPeopleGroup_combo.add(humaninteractions.getLogicalPeopleGroups().getLogicalPeopleGroup().get(i).getName(),i);
		}
		
		selectLogicalPeopleGroup_combo.select(0);
		GridData cgd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);
		cgd.horizontalSpan =1;
		selectLogicalPeopleGroup_combo.setLayoutData(cgd);
		//configPeopleAssignmentSection_logicalPeopleGroup(selectLogicalPeopleGroup_combo);

		Label namelabel = new Label(sectionClient, SWT.WRAP);
		namelabel.setText("Name");
		namelabel.setLayoutData(gd);

		peopleAssignmentNametextbox = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		peopleAssignmentNametextbox.setLayoutData(gd);
		//configPeopleAssignmentSection_name(peopleAssignmentNametextbox);

		Label explabel = new Label(sectionClient, SWT.WRAP);
		explabel.setText("Expression");
		explabel.setLayoutData(gd);

		peopleAssignmentExptextbox = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		peopleAssignmentExptextbox.setLayoutData(gd);
		//configPeopleAssignmentSection_expression(peopleAssignmentExptextbox);

		
		section.setEnabled(true);
		section.setClient(sectionClient);

		return section;
	}

	
	
	
	
	//presentation elem tab
	
	private Section createPElemGenralTableSection(FormToolkit toolkit){
	Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
			| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
	section.addExpansionListener(new ExpansionAdapter() {
		public void expansionStateChanged(ExpansionEvent e) {
			form.reflow(true);
		}
	});
		section.setText("Create Presentaion Element Name");
		section.setDescription("ADD LATER");
		GridData sectiondata = new GridData(GridData.FILL_BOTH);

		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);
		toolkit.paintBordersFor(sectionClient);
		
		presentationElemNameViewer = new TableViewer(sectionClient, SWT.MULTI
				| SWT.BORDER);
		createColumnPresantationElemNameTable(sectionClient,presentationElemNameViewer);
	
		
		table_presentationElemName= presentationElemNameViewer.getTable();

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 10;
		gd.heightHint = 20;
		gd.widthHint = 100;

		table_presentationElemName.setLayoutData(gd);

		table_presentationElemName.setHeaderVisible(true);
		table_presentationElemName.setLinesVisible(true);
		
		presentationElemNameViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
			//	preElemNameViewerItemSelecter(event.getSelection());
				
			}
		});
		
		presentationElemNameViewer.setContentProvider(new NPresentationElemContentProvider());
		presentationElemNameViewer.setInput(selectedNotification);
		
		table_presentationElemName.setSelection(0);
		
		
		Button add_btn = toolkit.createButton(sectionClient, "Add", SWT.PUSH);
		GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(btn_gd);
		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				/*
				 * TaskCreatWizard wizard = new
				 * TaskCreatWizard(humanInteractions,domain,viewer );
				 * WizardDialog wizardDialog = new WizardDialog(Display
				 * .getCurrent().getActiveShell(),wizard);
				 * wizardDialog.create(); wizardDialog.open();
				 */
			}
		});
		
		Button del_btn = toolkit.createButton(sectionClient, "Delete", SWT.PUSH); 
		del_btn.setLayoutData(btn_gd);

		del_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

			//TODO viraj :handle the action

			}
		});
		
		section.setClient(sectionClient);
		return section;
		
	}
	
	private Section createPElemGeneralInfoSection(FormToolkit toolkit) {

		Section info_section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		info_section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		
		info_section.setText("Genaral Information");
		info_section.setDescription("add later");

		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL); // configure the section expanded
												// over the form fully
		sectiondata.horizontalSpan = 2;
		info_section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(info_section);
		GridLayout layout = new GridLayout(); // layout for the sectionClient
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		gd.horizontalSpan = 1;

		Label name_lb = new Label(sectionClient, SWT.WRAP);
		name_lb.setText("Name");
		name_lb.setLayoutData(gd);

		preElem_GenInfo_name_txt = new Text(sectionClient, SWT.SINGLE
				| SWT.BORDER);
		preElem_GenInfo_name_txt.setLayoutData(gd);

		//configPElemGeneralInfoSection_name(preElem_GenInfo_name_txt);
		
		Label language_lb = new Label(sectionClient, SWT.WRAP);
		language_lb.setText("Language");
		language_lb.setLayoutData(gd);

		preElem_GenInfo_lang_txt = new Text(sectionClient, SWT.SINGLE
				| SWT.BORDER);
		preElem_GenInfo_lang_txt.setLayoutData(gd);
		
		//configPElemGeneralInfoSection_language(preElem_GenInfo_lang_txt);

		info_section.setClient(sectionClient);

		return info_section;
	}

	private Section createPParamTableSection(
			final FormToolkit toolkit) {
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		
		section.setText("Create Presentation Parameter");
		section.setDescription("ADD LATER");
		GridData sectiondata = new GridData(GridData.FILL_BOTH);

		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);
		toolkit.paintBordersFor(sectionClient);

		PresentationParameterViewer = new TableViewer(sectionClient, SWT.MULTI
				| SWT.BORDER);
		createColumnPresentationParaTable(sectionClient,
				PresentationParameterViewer);

		table_presentationParm = PresentationParameterViewer.getTable();

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 10;
		gd.heightHint = 20;
		gd.widthHint = 100;

		table_presentationParm.setLayoutData(gd);

		table_presentationParm.setHeaderVisible(true);
		table_presentationParm.setLinesVisible(true);

		PresentationParameterViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						
						//preParamViewerItemSelecter(event.getSelection());

					}
				});

		PresentationParameterViewer
				.setContentProvider(new NPresentationParamContentProvider());
		PresentationParameterViewer.setInput(selectedNotification);
		table_presentationParm.setSelection(0);

		Button add_btn = toolkit.createButton(sectionClient, "Add", SWT.PUSH);
		GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(btn_gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				
				/*AddPresentationParmWizard wizard = new AddPresentationParmWizard(taskPage, domain, PresentationParameterViewer);
				 WizardDialog wizardDialog = new WizardDialog(Display
				  .getCurrent().getActiveShell(),wizard);
				  wizardDialog.create(); wizardDialog.open();*/
				 
			}
		});
		
		Button del_btn = toolkit.createButton(sectionClient, "Delete", SWT.PUSH); 
		del_btn.setLayoutData(btn_gd);

		del_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

			//TODO viraj :handle the action

			}
		});
		
		section.setClient(sectionClient);
		return section;
	}

	private Section createPParmDetailSection(FormToolkit toolkit) {

		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		section.setText("Presentation Prameter Details");
		section.setDescription("ADD LATER");
		GridData sectiondata = new GridData(GridData.FILL_BOTH);

		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);
		//toolkit.paintBordersFor(sectionClient);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL
				| GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 1;

		Label namelabel = new Label(sectionClient, SWT.WRAP);
		namelabel.setText("Name");
		namelabel.setLayoutData(gd);

		preParm_nametext = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		preParm_nametext.setLayoutData(gd);
		//configPParm_name(preParm_nametext);

		Label typelabel = new Label(sectionClient, SWT.WRAP);
		typelabel.setText("Type");
		typelabel.setLayoutData(gd);

		preParm_typetext = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		preParm_typetext.setLayoutData(gd);
		//cinfigPparm_type(preParm_typetext);
		
		Label explabel = new Label(sectionClient, SWT.WRAP);
		explabel.setText("Expression");
		explabel.setLayoutData(gd);

		preParm_exptext = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		preParm_exptext.setLayoutData(gd);

		section.setClient(sectionClient);

		return section;

	}
	
	private Section createPElemSubjectTableSection(FormToolkit toolkit) {
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		section.setText("Create Subject");
		section.setDescription("ADD LATER");
		GridData sectiondata = new GridData(GridData.FILL_BOTH);

		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);
		toolkit.paintBordersFor(sectionClient);

		presentationElemSubjectViewer = new TableViewer(
				sectionClient, SWT.MULTI | SWT.BORDER);
		
		createColumnPresentationElemSubTable(presentationElemSubjectViewer);

		table_presentationElemSub = presentationElemSubjectViewer
				.getTable();
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 10;
		gd.heightHint = 20;
		gd.widthHint = 100;

		table_presentationElemSub.setLayoutData(gd);

		table_presentationElemSub.setHeaderVisible(true);
		table_presentationElemSub.setLinesVisible(true);

		presentationElemSubjectViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						
						//preElemSubViewerItemSelecter(event.getSelection());

					}
				});

		presentationElemSubjectViewer
				.setContentProvider(new NPresentationElemeSubContentProvider());

		presentationElemSubjectViewer.setInput(selectedNotification);
		table_presentationElemSub.setSelection(0);

		Button add_btn = toolkit.createButton(sectionClient, "Add", SWT.PUSH);
		GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(btn_gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				/*
				 * TaskCreatWizard wizard = new
				 * TaskCreatWizard(humanInteractions,domain,viewer );
				 * WizardDialog wizardDialog = new WizardDialog(Display
				 * .getCurrent().getActiveShell(),wizard);
				 * wizardDialog.create(); wizardDialog.open();
				 */
			}
		});
		
		Button del_btn = toolkit.createButton(sectionClient, "Delete", SWT.PUSH); 
		del_btn.setLayoutData(btn_gd);

		del_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

			//TODO viraj :handle the action

			}
		});
		
		section.setClient(sectionClient);
		return section;

	}
	
	private Section createPElemSubjectInfoSection(FormToolkit toolkit) {
		Section sub_section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		sub_section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		//Section sub_section = toolkit.createSection(tabFolder,Section.DESCRIPTION | Section.TITLE_BAR);
		sub_section.setText("Subject Information");
		sub_section.setDescription("add later");

		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL); // configure the section expanded
												// over the form fully
		sectiondata.horizontalSpan = 2;
		sub_section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(sub_section);
		GridLayout layout = new GridLayout(); // layout for the sectionClient
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL
				| GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 1;

		Label subject_lb = new Label(sectionClient, SWT.WRAP);
		subject_lb.setText("Subject");
		subject_lb.setLayoutData(gd);

		preElemSubInfo_subject_txt = new Text(sectionClient, SWT.SINGLE
				| SWT.BORDER);
		preElemSubInfo_subject_txt.setLayoutData(gd);

		Label language_lb = new Label(sectionClient, SWT.WRAP);
		language_lb.setText("Language");
		language_lb.setLayoutData(gd);

		preElemSubInfo_lang_txt = new Text(sectionClient, SWT.SINGLE
				| SWT.BORDER);
		preElemSubInfo_lang_txt.setLayoutData(gd);
		//configPElem_SubInfo_lang(preElemSubInfo_lang_txt);

		sub_section.setClient(sectionClient);

		return sub_section;
	}

	private Section createPElemDescTableSection(FormToolkit toolkit) {
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		section.setText("Create Description");
		section.setDescription("ADD LATER");
		GridData sectiondata = new GridData(GridData.FILL_BOTH);

		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);
		toolkit.paintBordersFor(sectionClient);

		presentationElemDescViewer = new TableViewer(sectionClient,
				SWT.MULTI | SWT.BORDER);
		createColumnForPresentationElemDescTable(presentationElemDescViewer);

		table_PresentationElemDesc = presentationElemDescViewer.getTable();
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 10;
		gd.heightHint = 20;
		gd.widthHint = 100;

		table_PresentationElemDesc.setLayoutData(gd);

		table_PresentationElemDesc.setHeaderVisible(true);
		table_PresentationElemDesc.setLinesVisible(true);

		Button add_btn = toolkit.createButton(sectionClient, "Add", SWT.PUSH);
		GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(btn_gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				/*
				 * TaskCreatWizard wizard = new
				 * TaskCreatWizard(humanInteractions,domain,viewer );
				 * WizardDialog wizardDialog = new WizardDialog(Display
				 * .getCurrent().getActiveShell(),wizard);
				 * wizardDialog.create(); wizardDialog.open();
				 */
			}
		});

		Button del_btn = toolkit.createButton(sectionClient, "Delete", SWT.PUSH); 
		del_btn.setLayoutData(btn_gd);

		del_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

			//TODO viraj :handle the action

			}
		});
		
		presentationElemDescViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						
						//preElemDescViewerItemSelecter(event.getSelection());

					}
				});

		presentationElemDescViewer
				.setContentProvider(new NPresentationElemDescContentProvider());
		presentationElemDescViewer.setInput(selectedNotification);

		table_PresentationElemDesc.setSelection(0);

		section.setClient(sectionClient);
		return section;

	}
	
	private Section createPElemDesInfoSection(FormToolkit toolkit) {
		Section desc_section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		desc_section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		
		
		desc_section.setText("Description Information");
		desc_section.setDescription("add later");

		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL); // configure the section expanded
												// over the form fully
		sectiondata.horizontalSpan = 2;
		desc_section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(desc_section);
		GridLayout layout = new GridLayout(2, true); // layout for the
														// sectionClient
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL
				| GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 1;

		Label language_lb = new Label(sectionClient, SWT.WRAP);
		language_lb.setText("Language");

		language_lb.setLayoutData(gd);

		preElemDescInfo_lang_txt = new Text(sectionClient, SWT.SINGLE
				| SWT.BORDER);
		preElemDescInfo_lang_txt.setLayoutData(gd);
		//configPElem_DescInfo_lang(preElemDescInfo_lang_txt);
		
		GridData combo_gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		combo_gd.verticalSpan = 2;

		Label context_lb = new Label(sectionClient, SWT.WRAP);
		context_lb.setText("Context Type");
		context_lb.setLayoutData(combo_gd);

		preElemDescInfo_context_type = new Combo(sectionClient, SWT.READ_ONLY);
		preElemDescInfo_context_type.add("text/plain", 0);
		preElemDescInfo_context_type.add("text/html", 1);
		preElemDescInfo_context_type.select(0);
		preElemDescInfo_context_type.setLayoutData(combo_gd);
		//configPElemDescInfo_contextType(preElemDescInfo_context_type);
		creatSpacer(sectionClient, 2);

		Label dec_lb = new Label(sectionClient, SWT.WRAP);
		dec_lb.setText("Description");
		dec_lb.setLayoutData(gd);

		preElemDescInfo_desc_txt = new Text(sectionClient, SWT.WRAP | SWT.MULTI
				| SWT.BORDER | SWT.V_SCROLL);
		preElemDescInfo_desc_txt.setLayoutData(gd);

		desc_section.setClient(sectionClient);

		return desc_section;
	}
	
	
	//creating Modles
	public Object createNotificationModle(){
		return notifications;
	}
	
	private Object createModleForPeopleAssinment(){
		
		return selectedNotification;
	}
	//creating columns
	private void createColumnsforNotificationTable(Composite parent, TableViewer viewer) {
		String[] titles = { "Notifications" };
		int[] bounds = { 100 };

		// first column for the first name
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				
				TNotification notification = (TNotification)element;
				return notification.getName();
				
			}

		});

	}
	
	private void createColumnsPeopleAssiTable(Composite parent, TableViewer viewer) {
		String[] titles = { "People Assingments" };
		int[] bounds = { 100 };

		// first column for the first name
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				
				TNotification notification = (TNotification)element;
				return notification.getPeopleAssignments().getBusinessAdministrators().get(0).toString();
				
			}

		});

	}
	
	private void createColumnPresantationElemNameTable(Composite parent,
			TableViewer viewer){
		String[] titles = { "Language", "Name"};
		int[] bounds = { 100, 100};
		
		// first column
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {

				TText txt = (TText)element;
				return txt.getLang();
			}

		});

		// second column
		TableViewerColumn col2 = createTableViewerColumn(viewer, titles[1],
				bounds[1]);
		col2.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				
				TText txt = (TText)element;
				return txt.getMixed().getValue(0).toString();
			}

		});
		
		
	}
	
	private void createColumnPresentationParaTable(Composite parent,
			TableViewer viewer) {
		String[] titles = { "Name", "Type" ,"Expression" };
		int[] bounds = { 100, 100, 100 };

		// first column
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				
				TPresentationParameter parm = (TPresentationParameter) element;
				return parm.getName();
			}

		});

		// second column
		TableViewerColumn col2 = createTableViewerColumn(viewer, titles[1],
				bounds[1]);
		col2.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TPresentationParameter parm = (TPresentationParameter) element;
				if(parm.getType() != null){
				return parm.getType().toString();
				}
				else{
					return EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE;
				}
			}

		});

		
		//third column
		TableViewerColumn col3 = createTableViewerColumn(viewer, titles[2], bounds[2]);
		col3.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				TPresentationParameter parm = (TPresentationParameter) element;
				if (parm.getMixed() != null) {
					
					if (parm.getMixed().size() != 0) {
						
						return parm.getMixed().get(0).toString();
					}
					else{
						return EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE;
					}
				} else {
					return EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE;
				}
			}
		});
	}

	private void createColumnPresentationElemSubTable(TableViewer viewer) {
		String[] titles = { "Language", "Content" };
		int[] bounds = { 100, 100 };

		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {

				TText text = (TText) element;
				return text.getLang();
			}

		});

		TableViewerColumn col2 = createTableViewerColumn(viewer, titles[1],
				bounds[1]);
		col2.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {

				TText text = (TText) element;
				return text.getMixed().getValue(0).toString();
			}

		});

	}
	
	private void createColumnForPresentationElemDescTable(TableViewer viewer) {
		String[] titles = { "Language", "Content Type" ,"Content"};
		int[] bounds = { 100, 100,100 };

		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TDescription desc = (TDescription) element;
				return desc.getLang();
			}

		});
		
		// second column
		TableViewerColumn col2 = createTableViewerColumn(viewer, titles[1],
				bounds[1]);
		col2.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TDescription desc = (TDescription) element;
				return desc.getContentType();
			}

		});

		
		//third column
		TableViewerColumn col3 = createTableViewerColumn(viewer, titles[2], bounds[2]);
		col3.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				TDescription desc = (TDescription) element;
				return desc.getMixed().getValue(0).toString();
			}
		});
	}
	
	private TableViewerColumn createTableViewerColumn(TableViewer viewer,
			String title, int bound) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;

	}
	
	//item selectors
	
	private void notificationTableItemSelecter(ISelection selection) {

		IStructuredSelection ssel = (IStructuredSelection) selection;

		if (ssel.size() == 1) {
			selectedNotification = (TNotification) ssel.getFirstElement();
			 System.out.println(selectedNotification.getName());
		} else {
			selectedNotification = null;
		}
	
		
		//updatePeopleAssignmentTable();
		updatePreElemNameTable();
		updatePParmTable();
		updatePElemSubTable();
		updatePElemDescTable();
	}
	
	
	//updaters
	
	
	private void updatePeopleAssignmentTable()
	{
		viewer_peopleAssignment.setInput(createModleForPeopleAssinment());
		table_peopleAssingment.setSelection(0);
		//checkAvailability_peopleAssignment();
		
		//update_peopleAssingment();
		
	}
	
	private void updatePreElemNameTable(){
		
		if(selectedNotification.getPresentationElements() != null){
			if(selectedNotification.getPresentationElements().getName() != null){
				
			 presentationElemNameViewer.setInput(selectedNotification);
			 table_presentationElemName.setSelection(0);
			// FormColors frmcolores = toolkit.getColors();
			// table_presentationElemName.setBackground(frmcolores.createColor(FormColors.SEPARATOR, 50, 20, 70));
				
			 //	updatePElemNameDetails();
			}
		}
	}

	private void updatePParmTable(){
		
		if(selectedNotification.getPresentationElements() != null){
			if(selectedNotification.getPresentationElements().getPresentationParameters() != null){
				
				PresentationParameterViewer.setInput(selectedNotification);
				table_presentationParm.setSelection(0);
			}
		}
		else{
			//TODO handle the no element case
		}
		
	}
	
	private void updatePElemSubTable(){
		
		if(selectedNotification.getPresentationElements() != null){
			if(selectedNotification.getPresentationElements().getSubject() != null){
				
				presentationElemSubjectViewer.setInput(selectedNotification);
				table_presentationParm.setSelection(0);
			}
		}
		else{
			//TODO handle the no element case
		}
	}
	
	private void updatePElemDescTable(){
		
		if(selectedNotification.getPresentationElements() != null){
			if(selectedNotification.getPresentationElements().getDescription() != null){
				
				presentationElemDescViewer.setInput(selectedNotification);
				table_presentationParm.setSelection(0);
			}
		}
		else{
			//TODO handle the no element case
		}
	}

	
	// Availability checks
	
	private void checkAvailability_Notifications() {
		if (notifications == null) {
			System.out.println("checkAvailability_Notifications + Error message");
			// Error message

		} else {
			selectedNotification = notifications.getNotification().get(0);
		}

	}
	
	class TabContent {

		Section content;

		public TabContent(Section content) {
			this.content = content;
		}

	}
	private void creatSpacer(Composite parent, int horizontalSpan) {

		Label space = new Label(parent, SWT.WRAP);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = horizontalSpan;
		space.setLayoutData(gd);
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
