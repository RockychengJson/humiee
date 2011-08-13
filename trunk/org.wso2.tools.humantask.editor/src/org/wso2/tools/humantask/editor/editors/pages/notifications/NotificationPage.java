package org.wso2.tools.humantask.editor.editors.pages.notifications;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.xml.namespace.QName;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import org.eclipse.swt.widgets.Group;
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
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExtension;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TNotification;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TNotifications;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationParameter;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TText;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;
import org.wso2.tools.humantask.editor.editors.base.util.EMFObjectHandleUtil;
import org.wso2.tools.humantask.editor.editors.pages.task.HumanRole;
import org.wso2.tools.humantask.editor.editors.pages.util.Messages;

import com.ibm.wsdl.OperationImpl;
import com.ibm.wsdl.xml.WSDLReaderImpl;



public class NotificationPage extends FormPage implements IResourceChangeListener,Listener  {

	protected HTMultiPageEditor editor;
	protected EditingDomain domain;
	protected TNotifications notifications;
	protected FormToolkit toolkit;
	protected ComposedAdapterFactory adaptorFactory;
	private THumanInteractions humanInteractions;
	protected String pageTitle;
	private TNotification selectedNotification;
	
	private NotificationPage notificationPage;
	
	private Definition definition;
	private WSDLReaderImpl reader;
	private String filename= "WSDLLocations.txt";
	private String selectedWsdlComboBoxItem;
	private Combo comboDropDown;
	private Object portTypes[];
	
	private CTabFolder tabFolder;
	private ScrolledForm form;
	private TabContent[] temp;
	private int tempindex;
	private boolean isFirst = true;
	
	private Combo portComboBox;
	//private Text OportTextBox;
	//private Text OresponseTextBox;
	private Combo operationComboBox;
	
	private HumanRole selectedHumanRole;
	private HumanRole pastSelectedHumanRole;
	
	
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
	private TText selectedElemName;
	
	private Text preElem_GenInfo_name_txt;
	private Text preElem_GenInfo_lang_txt;
	
	private TableViewer PresentationParameterViewer;
	private Table table_presentationParm;
	private TPresentationParameter selectedParam;
	
	private Text preParm_nametext;
	private Text preParm_typetext;
	private Text preParm_exptext;
	
	private TableViewer presentationElemSubjectViewer;
	private Table table_presentationElemSub;
	private TText selectedElemSubject;
	
	private TableViewer presentationElemDescViewer;
	private Table table_PresentationElemDesc;
	private TDescription selectedElemDesc;
	
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
		this.humanInteractions = humanInteractions;
		this.notifications = this.humanInteractions.getNotifications();
		this.notificationPage = this;
		
		
		reader=new WSDLReaderImpl();

		String name= domain.getResourceSet().getResources().get(0).getURI().segment(domain.getResourceSet().getResources().get(0).getURI().segmentCount()-1);
		
		File file=new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString()+"/WSDLLocations_"+name+".txt");
		try{	
		filename=file.getCanonicalPath();
			if (!file.exists()) {
				file.createNewFile();
			}
		}
		catch(IOException e)
		{
			System.out.println("Error creating file WSDLLocations_...txt !");
		}
		
		
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
			chechAvailability_PElemName();
			checkAvailability_Pparm();
			checkAvailabilityPElemSub();
			checkAvailabilityPElemDesc();
			
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
				AddNotificationWizard wizard = new AddNotificationWizard(humanInteractions,notificationPage,domain,notficationViewer);
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
				boolean done= humanInteractions.getNotifications().getNotification().remove(selectedNotification);
				notficationViewer.setInput(createNotificationModle());

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
		
		/*final Composite wsdl_import_comp = toolkit.createComposite(sectionClient);
		GridData wsdl_import_comp_gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		wsdl_import_comp_gd.horizontalSpan = 2;
		wsdl_import_comp.setLayoutData(wsdl_import_comp_gd);
		GridLayout wsdlComp_layout = new GridLayout(3 ,false);
		wsdl_import_comp.setLayout(wsdlComp_layout);*/
	
		Group wsdlInfo = new Group(sectionClient, SWT.NONE);
		wsdlInfo.setText("Import WSDLs");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		wsdlInfo.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData.horizontalSpan = 2;
		wsdlInfo.setLayoutData(gridData);
		
		Label import_label = new Label(wsdlInfo, SWT.WRAP);
		import_label.setText("Imported WSDLs");
		GridData import_lb_gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		import_lb_gd.horizontalSpan =1;
		import_label.setLayoutData(import_lb_gd);
		
		Combo comboDropDown = new Combo(wsdlInfo, SWT.DROP_DOWN | SWT.BORDER);
		comboDropDown.add("test 1");
		comboDropDown.add("test 2");
		comboDropDown.add("test 3");
		
		GridData combo_lb_gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		combo_lb_gd.horizontalSpan = 2;
		comboDropDown.setLayoutData(combo_lb_gd);
		try {
			configImportedWsdl(comboDropDown);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		Label select_wsdl_label = new Label(wsdlInfo, SWT.WRAP);
		select_wsdl_label.setText("Select the WSDL");
		//import_lb_gd.horizontalSpan =  1;
		select_wsdl_label.setLayoutData(import_lb_gd);
		
		final Text filename = new Text(wsdlInfo, SWT.SINGLE | SWT.BORDER);
		filename.setLayoutData(import_lb_gd);
		
		Button browse_btn = new Button(wsdlInfo, SWT.PUSH);
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
				try{
					saveToFile(filename.getText()); //url is saved to a text file
					}
					catch(IOException e)
					{
						System.out.println(e);
					}
					updateDetailsAccordingToWSDL();
			}
			
		});
		browse_btn.setLayoutData(import_lb_gd);
		
		creatSpacer(sectionClient, 2);
		Label portTypeLabel = new Label(sectionClient, SWT.WRAP);
		portTypeLabel.setText(Messages
				.getString("TaskPage.interfaceTab.Section.porttypelable"));
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		portTypeLabel.setLayoutData(data);
	
		portComboBox = new Combo(sectionClient,SWT.DROP_DOWN | SWT.BORDER);
		portComboBox.setSize(100, 20);
		portComboBox.setLayoutData(data);
		configGeneralInfoSection_portType(portComboBox);
		
		creatSpacer(sectionClient, 2);
		// Operation label and Text box
		Label operationLabel = new Label(sectionClient, SWT.WRAP);
		operationLabel.setText(Messages
				.getString("TaskPage.interfaceTab.Section.operationlable"));
		operationLabel.setLayoutData(data);
	
		operationComboBox = new Combo(sectionClient,SWT.DROP_DOWN | SWT.BORDER);
		operationComboBox.setSize(100, 20);
		operationComboBox.setLayoutData(data);
		configGeneralInfoSection_operation(operationComboBox);
		
		section.setClient(sectionClient);

		return section;

	}
	
	private void configGeneralInfoSection_portType(final Combo portComboBox) {
		if (humanInteractions.getNotifications() != null) {
			if (humanInteractions.getNotifications().getNotification().get(0).getInterface().getPortType()!= null) {
			/*	if ((tasks.getTask().get(0).getInterface().getPortType()
						.toString() != null)){*/ 
				portComboBox.setText(humanInteractions.getNotifications().getNotification().get(0).getInterface().getPortType().toString()) ;
				
				
			}else {
				portComboBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
				}
			}
		
		portComboBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				setAttribute_Qname(
						htdPackage.eINSTANCE.getTNotificationInterface_PortType(),
						new QName(portComboBox.getText()));
				
				if(portComboBox.getSelectionIndex()!=-1){
				
				List operations=definition.getPortType((QName) portTypes[portComboBox.getSelectionIndex()]).getOperations();
				operationComboBox.removeAll();
				for(int i=0;i<operations.size();++i){
					operationComboBox.add(((OperationImpl)operations.get(i)).getName());
				}
				operationComboBox.select(0);
				}
			}
		});

	}
	
	
	
	
	private void setAttribute_Qname(EAttribute tNotificationInterface_Attribute,
			QName text) {
		Command setAttribCommand = SetCommand.create(domain,
				selectedNotification.getInterface(), tNotificationInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
		} else {
			System.out.println("can't modify Attribute: "
					+ tNotificationInterface_Attribute.getName());
		}
	}
	
	
	private void configGeneralInfoSection_operation(final Combo operationComboBox) {
		if (humanInteractions.getNotifications() != null) {
			if (humanInteractions.getNotifications().getNotification().get(0).getInterface().getOperation()!= null) {
			/*	if ((tasks.getTask().get(0).getInterface().getPortType()
						.toString() != null)){*/ 
				operationComboBox.setText(humanInteractions.getNotifications().getNotification().get(0).getInterface().getOperation()) ;
				
				
			}else {
				operationComboBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
				}
			}
		
		operationComboBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				setAttribute(
						htdPackage.eINSTANCE.getTNotificationInterface_Operation(),
						operationComboBox.getText());
							
			}
		});

	}
	
	private void setAttribute(EAttribute tNotificationInterface_Attribute, String text) {
		Command setAttribCommand = SetCommand.create(domain,
				selectedNotification.getInterface(), tNotificationInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
		} else {
			System.out.println("can't modify Attribute: "
					+ tNotificationInterface_Attribute.getName());
		}
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
						itemSelecter_peopleAssignment(event.getSelection());

					}
				});

		viewer_peopleAssignment
				.setContentProvider(new NPeopleAssiContentProvider());
		viewer_peopleAssignment.setInput(createModleForPeopleAssinment());
		table_peopleAssingment.setSelection(0);
		checkAvailability_peopleAssignment();
		
		
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
		configPeopleAssignmentSection_roleType(roal_type);
		
		
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
		
		int size= humanInteractions.getLogicalPeopleGroups().getLogicalPeopleGroup().size();
		for(int i=0;i<size;++i)
		{
			selectLogicalPeopleGroup_combo.add(humanInteractions.getLogicalPeopleGroups().getLogicalPeopleGroup().get(i).getName(),i);
		}
		
		selectLogicalPeopleGroup_combo.select(0);
		GridData cgd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);
		cgd.horizontalSpan =1;
		selectLogicalPeopleGroup_combo.setLayoutData(cgd);
		configPeopleAssignmentSection_logicalPeopleGroup(selectLogicalPeopleGroup_combo);

		Label namelabel = new Label(sectionClient, SWT.WRAP);
		namelabel.setText("Name");
		namelabel.setLayoutData(gd);

		peopleAssignmentNametextbox = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		peopleAssignmentNametextbox.setLayoutData(gd);
		configPeopleAssignmentSection_name(peopleAssignmentNametextbox);

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

	
	void itemSelecter_peopleAssignment(ISelection selection) {
		IStructuredSelection ssel = (IStructuredSelection) selection;

		if (ssel.size() == 1) {
			selectedHumanRole = (HumanRole) ssel.getFirstElement();
			//System.out.println(selectedHumanRole.getPeopleAssignMethodology());
		} else {
			selectedHumanRole = null;
		}

		update_peopleAssingment();
	}
	
	private void checkAvailability_peopleAssignment() {

		if (viewer_peopleAssignment.getElementAt(0) == null) {
			// Error message
		} else {
			selectedHumanRole=(HumanRole)viewer_peopleAssignment.getElementAt(0);
		}
	}
	
	private void configPeopleAssignmentSection_roleType(final Combo roleCombo) {
		if (selectedHumanRole != null) {
			if (selectedHumanRole.getType()!= null) {
				roleCombo.select(selectedHumanRole.getTypeByIndex());
			} else {
				roleCombo.select(-1);
						

			}
		}

		roleCombo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {				
				
				editRoleType_roleCombo();
				
				firePropertyChange(PROP_DIRTY);
				System.out.println( isDirty());

				
			}
		});
		
		pastSelectedHumanRole=selectedHumanRole;
	}
	
	private void editRoleType_roleCombo() {

		if (roal_type.getSelectionIndex() == 0) {

			
			if (selectedHumanRole.getTypeByIndex() == 5) {
				selectedNotification.getPeopleAssignments().getRecipients()
						.remove(selectedHumanRole.getGenericHumanRole());
				selectedNotification.getPeopleAssignments().getBusinessAdministrators()
						.add(selectedHumanRole.getGenericHumanRole());
			}
			

		}
		if (roal_type.getSelectionIndex() == 1) {
			if (selectedHumanRole.getTypeByIndex() == 4) {
				selectedNotification.getPeopleAssignments().getBusinessAdministrators()
						.remove(selectedHumanRole.getGenericHumanRole());
				selectedNotification.getPeopleAssignments().getRecipients()
						.add(selectedHumanRole.getGenericHumanRole());
			}
		
			
		}
		
		}


	private void configPeopleAssignmentSection_logicalPeopleGroup(final Combo logicalPeopleGroupComboBox)
	{
		if (selectedHumanRole != null) {
			if (selectedHumanRole.getGenericHumanRole().getFrom().getLogicalPeopleGroup() != null) {
				int itemCount = logicalPeopleGroupComboBox.getItemCount();
				for (int j = 0; j < itemCount; ++j) {
					if ((logicalPeopleGroupComboBox.getItem(j))
							.equals(selectedHumanRole.getLogicalPeopleGroup())) {
						logicalPeopleGroupComboBox.select(j);
					}

				}
				
			} else {
				
			}
		}

		logicalPeopleGroupComboBox.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				
				setAttribute_logicalPeopleGroup(htdPackage.eINSTANCE.getTFrom_LogicalPeopleGroup(),logicalPeopleGroupComboBox.getItem(logicalPeopleGroupComboBox.getSelectionIndex()) );
			}
		});
	
		
	}
	
	private void configPeopleAssignmentSection_name(final Text nameTextBox) {
		if (selectedHumanRole != null) {
			if (selectedHumanRole.getGenericHumanRole().getFrom().getArgument().getName() != null) {
				nameTextBox.setText(selectedHumanRole.getGenericHumanRole().getFrom().getArgument().getName());
			} else {
				nameTextBox
						.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);

			}
		}

		nameTextBox.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {

				setAttribute_parmName(
						htdPackage.eINSTANCE.getTArgument_Name(),
						nameTextBox.getText());
			}
		});
	}
	
	
	private void setAttribute_parmName(EAttribute tArgument_Attribute,
			String text) {
		if(selectedHumanRole.getGenericHumanRole().getFrom()!=null)
		{
		Command setAttribCommand = SetCommand.create(domain, selectedHumanRole.getGenericHumanRole().getFrom().getArgument(),
				tArgument_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);

		} else {
			System.out.println("can't modify Attribute: "
					+ tArgument_Attribute.getName());
		}
		}

	}
	
	private void setAttribute_logicalPeopleGroup(EAttribute tArgument_Attribute,
			String text) {
		
		if(selectedHumanRole.getGenericHumanRole().getFrom()!=null)
		{
		Command setAttribCommand = SetCommand.create(domain, selectedHumanRole.getGenericHumanRole().getFrom(),
				tArgument_Attribute, new QName(text) );

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);

		} else {
			System.out.println("can't modify Attribute: "
					+ tArgument_Attribute.getName());
		}
		}

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
				
				preElemNameViewerItemSelecter(event.getSelection());
				
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
		
		configPElemGeneralInfoSection_name(preElem_GenInfo_name_txt);
		
		Label language_lb = new Label(sectionClient, SWT.WRAP);
		language_lb.setText("Language");
		language_lb.setLayoutData(gd);

		preElem_GenInfo_lang_txt = new Text(sectionClient, SWT.SINGLE
				| SWT.BORDER);
		preElem_GenInfo_lang_txt.setLayoutData(gd);
		
		configPElemGeneralInfoSection_language(preElem_GenInfo_lang_txt);

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
						
						preParamViewerItemSelecter(event.getSelection());

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
		configPParm_name(preParm_nametext);

		Label typelabel = new Label(sectionClient, SWT.WRAP);
		typelabel.setText("Type");
		typelabel.setLayoutData(gd);

		preParm_typetext = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		preParm_typetext.setLayoutData(gd);
		cinfigPparm_type(preParm_typetext);
		
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
						
						preElemSubViewerItemSelecter(event.getSelection());

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
		configPElem_SubInfo_lang(preElemSubInfo_lang_txt);

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
						
						preElemDescViewerItemSelecter(event.getSelection());

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
		configPElem_DescInfo_lang(preElemDescInfo_lang_txt);
		
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
		configPElemDescInfo_contextType(preElemDescInfo_context_type);
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
	
	//Presentation Element Tab configurations
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void configPElemGeneralInfoSection_name(final Text nameTextBox)
	{
		 if (selectedNotification != null)
		  {
			  if(selectedElemName!= null)
			  { 
				  nameTextBox.setText(notifications.getNotification().get(0).getPresentationElements()
						  .getName().get(0).getMixed().get(0).getValue().toString());
				
			  } 
			  else 
			  {
				nameTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE); 
				
			  } 
			  
		  }
		  		
		  nameTextBox.addModifyListener(new ModifyListener() {
		  			public void modifyText(ModifyEvent e) 
		  			{
		  			// validateInput(); 
		  			   setAttribute_tParameter(htdPackage.eINSTANCE.getTExpression_Mixed(), nameTextBox.getText()); 
		  				
		  			}
		  		});
	 }
	
	private void setAttribute_tParameter(EAttribute tParameter_attribute,String text)
	{
		Command setAttribCommand = SetCommand.create(domain, selectedElemName.getMixed().getValue(0), tParameter_attribute, text);

		if (setAttribCommand.canExecute()) 
		{
			domain.getCommandStack().execute(setAttribCommand);
		} else
		{
			System.out.println("can't modify Attribute:from configPElemGeneralInfoSection_name@NotificationPage" + tParameter_attribute.getName());
		}
	}
	
	
	private void configPElemGeneralInfoSection_language(final Text languageTextBox) {
		if (notifications != null) {
			if (notifications.getNotification().get(0).getPresentationElements() != null) {
				if ((notifications.getNotification().get(0).getPresentationElements().getName().get(0).getLang() != null)) {
					languageTextBox.setText((notifications.getNotification().get(0).getPresentationElements().getName().get(0).getLang()));
				} else {
					languageTextBox
							.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
				}
			}
		}
		languageTextBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				setAttribute_presentationElement(
						htdPackage.eINSTANCE.getTText_Lang(),languageTextBox.getText());
			}
		});

	}
	
	private void setAttribute_presentationElement(EAttribute tText_attribute, String text) 
	{
		Command setAttribCommand = SetCommand.create(domain,selectedElemName, tText_attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
		} else {
			System.out.println("can't modify Attribute: "
					+ tText_attribute.getName());
		}
	}
	
	
	private void configPParm_name(final Text nameTextBox) 
	{
	  if (notifications != null)
	  {
		  if((notifications.getNotification().get(0).getPresentationElements().getPresentationParameters()
				  .getPresentationParameter() .get(0).getName()!= null))
		  { 
			  nameTextBox.setText((notifications.getNotification().get(0).getPresentationElements().
					  getPresentationParameters().getPresentationParameter().get(0).getName())); 
		  } 
		  else 
		  {
			nameTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE); 
			
		  } 
		  
	  }
	  		
	  nameTextBox.addModifyListener(new ModifyListener() {
	  			public void modifyText(ModifyEvent e) 
	  			{
	  			// validateInput(); 
	  				setAttribute_PpramName(htdPackage.eINSTANCE.getTParameter_Name(), nameTextBox.getText()); 
	  				
	  			}
	  		});
	  
	  }

	private void setAttribute_PpramName(EAttribute tParameter_attribute,String text)
	{
		Command setAttribCommand = SetCommand.create(domain, selectedParam, tParameter_attribute, text);

		if (setAttribCommand.canExecute()) 
		{
			domain.getCommandStack().execute(setAttribCommand);
		} else
		{
			System.out.println("can't modify Attribute: " + tParameter_attribute.getName());
		}
	}
	
	
	private void cinfigPparm_type(final Text typeTextBox){
		
		if (notifications != null)
		  {
			  if((notifications.getNotification().get(0).getPresentationElements().getPresentationParameters()
					  .getPresentationParameter() .get(0).getType() != null))
			  { 
				  typeTextBox.setText((notifications.getNotification().get(0).getPresentationElements().
						  getPresentationParameters().getPresentationParameter().get(0).getType().toString())); 
			  } 
			  else 
			  {
				  typeTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE); 
				
			  } 
			  
		  }
		  		
		typeTextBox.addModifyListener(new ModifyListener() {
		  			public void modifyText(ModifyEvent e) 
		  			{
		  			// validateInput(); 
		  				setAttribute_PparmType(htdPackage.eINSTANCE.getTParameter_Type(), new QName(typeTextBox.getText())); 
		  				
		  			}
		  		});
	}
	
	private void setAttribute_PparmType(EAttribute tTaskInterface_Attribute,
			QName text) {
		Command setAttribCommand = SetCommand.create(domain, selectedParam,
				tTaskInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);

		} else {
			System.out.println("can't modify Attribute: "
					+ tTaskInterface_Attribute.getName());
		}
	}
	 
	private void configPElem_SubInfo_lang(final Text languageTextBox){
		if (notifications != null) {
			if (notifications.getNotification().get(0).getPresentationElements() != null) {
				if ((notifications.getNotification().get(0).getPresentationElements().getSubject().get(0) != null)) {
					languageTextBox.setText((notifications.getNotification().get(0).getPresentationElements().getSubject().get(0).getLang()));
				} else {
					languageTextBox
							.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
				}
			}
		}
		languageTextBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				setAttribute_pElemSub_lang(
						htdPackage.eINSTANCE.getTText_Lang(),languageTextBox.getText());
			}
		});
	}
	
	private void setAttribute_pElemSub_lang(EAttribute tText_attribute, String text){
		Command setAttribCommand = SetCommand.create(domain,selectedElemSubject, tText_attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
		} else {
			System.out.println("can't modify Attribute: "
					+ tText_attribute.getName());
		}
		
	}
	
	
	private void configPElem_DescInfo_lang(final Text languageTextBox){
		
		if (notifications != null) {
			if (notifications.getNotification().get(0).getPresentationElements().getDescription().size() !=0) {
				if ((notifications.getNotification().get(0).getPresentationElements().getDescription().get(0) != null)) {
					languageTextBox.setText((notifications.getNotification().get(0).getPresentationElements().getDescription().get(0).getLang()));
				} else {
					languageTextBox
							.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
				}
			}
		}
		languageTextBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				setAttribute_pElemDesc_lang(
						htdPackage.eINSTANCE.getTDescription_Lang(),languageTextBox.getText());
			}
		});
		
	}
	
	
	private void setAttribute_pElemDesc_lang(EAttribute tText_attribute, String text){
		
		Command setAttribCommand = SetCommand.create(domain,selectedElemDesc, tText_attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
		} else {
			System.out.println("can't modify Attribute: "
					+ tText_attribute.getName());
		}
	}
	
	
	private void configPElemDescInfo_contextType(final Combo contextTypeComboBox)
 {
		if (notifications != null) {
			if (notifications.getNotification().get(0).getPresentationElements().getDescription().size() != 0) {
				if ((notifications.getNotification().get(0).getPresentationElements()
						.getDescription().get(0) != null)) {
					contextTypeComboBox.select(notifications.getNotification().get(0)
							.getPresentationElements().getDescription().get(0)
							.getContentType().compareTo("text/plain") == 0 ? 0
							: 1);

				} else {

					contextTypeComboBox
							.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
				}
			}
		}

		contextTypeComboBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				setAttribute_PElemDescInfo_contextType(htdPackage.eINSTANCE
						.getTDescription_ContentType(), (contextTypeComboBox
						.getSelectionIndex() == 0) ? "text/plain" : "text/html");
			}
		});
	
		
	}
	
	
	private void setAttribute_PElemDescInfo_contextType(EAttribute tExtension_Attribute, String text) {
		Command setAttribCommand = SetCommand.create(domain, selectedElemDesc, tExtension_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
		
		} else {
			System.out.println("can't modify Attribute: " + tExtension_Attribute.getName());
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////
	
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
				
				HumanRole role = (HumanRole) element;
				return role.getType();
				
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
			
			selectedNotification = (TNotification)  notficationViewer.getElementAt(0);
			notficationViewer.getTable().setSelection(0);
		}
		
		
		updateInterfaceTab();
		updatePeopleAssignmentTable();
		updatePreElemNameTable();
		updatePParmTable();
		updatePElemSubTable();
		updatePElemDescTable();
	}
	
	private void preElemNameViewerItemSelecter(ISelection selection) {

		IStructuredSelection ssel = (IStructuredSelection) selection;

		if (ssel.size() == 1) {
			selectedElemName = (TText) ssel.getFirstElement();
		} else {

			selectedElemName = (TText) presentationElemNameViewer.getElementAt(0);
			presentationElemNameViewer.getTable().setSelection(0);

		}
		
		 updatePElemNameDetails();
	}

	private void preParamViewerItemSelecter(ISelection selection) {

		IStructuredSelection ssel = (IStructuredSelection) selection;

		if (ssel.size() == 1) {

			selectedParam = (TPresentationParameter) ssel.getFirstElement();

		} else {

			selectedParam = (TPresentationParameter) PresentationParameterViewer
					.getElementAt(0);
			PresentationParameterViewer.getTable().setSelection(0);

		}
		updatePparmDetails();
	}
	
	private void preElemSubViewerItemSelecter(ISelection selection) {

		IStructuredSelection ssel = (IStructuredSelection) selection;

		if (ssel.size() == 1) {

			selectedElemSubject = (TText) ssel.getFirstElement();
		} else {
			selectedElemSubject = null;
		}

		
		updatePElemSubDetail();

	}
	
	private void preElemDescViewerItemSelecter(ISelection selection) {
		IStructuredSelection ssel = (IStructuredSelection) selection;

		if (ssel.size() == 1) {

			selectedElemDesc = (TDescription) ssel.getFirstElement();
		} else {
			selectedElemDesc = null;
		}

		 updatePElemDescDetail();
	}

		//updatePparmDetails();

	
	//updaters
	
	
	private void updatePeopleAssignmentTable()
	{
		viewer_peopleAssignment.setInput(createModleForPeopleAssinment());
		table_peopleAssingment.setSelection(0);
		checkAvailability_peopleAssignment();
		
		update_peopleAssingment();
		
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

	private void updatePElemNameDetails() {

		if (selectedElemName != null) {
			if (selectedElemName.getMixed() != null
					&& selectedElemName.getMixed().size() != 0) {

				preElem_GenInfo_name_txt.setText(selectedElemName.getMixed()
						.getValue(0).toString());

			} else {
				preElem_GenInfo_name_txt
						.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);

			}
			if (selectedElemName.getLang() != null) {

				preElem_GenInfo_lang_txt.setText(selectedElemName.getLang());
			} else {

				preElem_GenInfo_lang_txt
						.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			}
		}
	}
	
	private void updatePparmDetails() {
		
		if(selectedParam != null){
		if(selectedParam.getName() != null )
		{
			preParm_nametext.setText(selectedParam.getName());
			
		}
		else
		{
			
			preParm_nametext.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			
		}
		if(selectedParam.getType() != null)
		{
			preParm_typetext.setText(selectedParam.getType().toString());
			
		}else
		{
			
			preParm_typetext.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
		}
		if(selectedParam.getMixed() != null && selectedParam.getMixed().size() != 0)
		{
			preParm_exptext.setText(selectedParam.getMixed().getValue(0).toString());
			
		}else
		{
			preParm_exptext.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
		}
		}
	 
	}

	private void updatePElemSubDetail()
	{
		if(selectedElemSubject != null)
		{
		if(selectedElemSubject.getMixed() != null && selectedElemSubject.getMixed().size() != 0)
		{
			
			preElemSubInfo_subject_txt.setText(selectedElemSubject.getMixed().getValue(0).toString());
			
		}
		else
		{
			preElemSubInfo_subject_txt.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			
		}
		
		if(selectedElemSubject.getLang() != null)
		{
			
			preElemSubInfo_lang_txt.setText(selectedElemSubject.getLang());
			
		}
		else
		{
			
			preElemSubInfo_lang_txt.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
		}
		}
	}
	
	private void updatePElemDescDetail()
	{
		if(selectedElemDesc != null)
		{
		if(selectedElemDesc.getLang() != null)
		{
			
			preElemDescInfo_lang_txt.setText(selectedElemDesc.getLang());
		}
		else
		{
			
			preElemDescInfo_lang_txt.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
		}
		
		if(selectedElemDesc.getContentType() != null)
		{
			if(selectedElemDesc.getContentType().compareTo("text/plain") == 0)
			{
				preElemDescInfo_context_type.select(0);
			}
			else
			{
				preElemDescInfo_context_type.select(1);
			}
			
		}
		
		if(selectedElemDesc.getMixed() != null && selectedElemDesc.getMixed().size() != 0)
		{
			
			preElemDescInfo_desc_txt.setText(selectedElemDesc.getMixed().getValue(0).toString());
			
		}
		else
		{
			
			preElemDescInfo_desc_txt.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			
		}
		
		}
	}
	
	// Availability checks
	
	private void checkAvailability_Notifications() {
		if (notifications == null) {
			System.out.println("checkAvailability_Notifications + Error message");
			// Error message

		} else {
			selectedNotification = humanInteractions.getNotifications().getNotification().get(0);
		}

	}
	
	private void chechAvailability_PElemName(){
		
		if (selectedNotification == null){
			System.out.println("chechAvailability_PElemName()+ Error message");
			//Error message
		}
		else{
			if(selectedNotification.getPresentationElements() !=  null){
				selectedElemName = selectedNotification.getPresentationElements().getName().get(0);
			}
		}
	}
	
	private void checkAvailability_Pparm() {

		if (selectedNotification == null) {
			System.out.println("checkAvailability_Pparm() +Error message");
			// Error message
		} else {
			if(selectedNotification.getPresentationElements().getPresentationParameters().getPresentationParameter() != null){
			selectedParam = selectedNotification.getPresentationElements()
					.getPresentationParameters().getPresentationParameter()
					.get(0);
			}
		}
	}

	private void checkAvailabilityPElemSub() {
		if (selectedNotification == null) {
			System.out.println("checkAvailabilityPElemSubTable() + Error message");
			//Error message
		} else {
			if(selectedNotification.getPresentationElements().getSubject() != null){
			selectedElemSubject = selectedNotification.getPresentationElements().getSubject()
					.get(0);
			}
		}
	}

	private void checkAvailabilityPElemDesc() {
		if (selectedNotification == null) {
			System.out.println("checkAvailabilityPElemDescTable() +Error message");
		} else {
			if(selectedNotification.getPresentationElements().getDescription().size()!= 0){
			selectedElemDesc = selectedNotification.getPresentationElements().getDescription()
					.get(0);
			}
		}
	}
	
	
	private void updateDetailsAccordingToWSDL()
	{
		try {
			definition= reader.readWSDL(selectedWsdlComboBoxItem);
		} catch (WSDLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 portTypes= definition.getPortTypes().keySet().toArray();
		
		 
		// portComboBox.setText(definition.getPortType((QName) portTypes[0]).getQName().toString());
		 portComboBox.removeAll();
		 for(int i=0;i<portTypes.length;++i){
		 portComboBox.add(definition.getPortType((QName) portTypes[i]).getQName().toString());
		 }
		 portComboBox.select(0);
		OperationImpl operation= (OperationImpl)definition.getPortType((QName) portTypes[0]).getOperations().get(0);
		List operations=definition.getPortType((QName) portTypes[0]).getOperations();
		operationComboBox.removeAll();
		for(int i=0;i<operations.size();++i){
			operationComboBox.add(((OperationImpl)operations.get(i)).getName());
		}
		operationComboBox.select(0);
		
		//operationComboBox.setText(operation.getName());
		//System.out.println(operation.getStyle());
		/*if(operation.getStyle().toString().equals("REQUEST_RESPONSE"))
		{
			
			oneway.setSelection(false);
			requestres.setSelection(true);
			OportComboBox.setEnabled(true);
			
			OportComboBox.removeAll();
			for(int i=0;i<portTypes.length;++i){
				OportComboBox.add(definition.getPortType((QName) portTypes[i]).getQName().toString());
			}
			OportComboBox.select(0);
			OperationImpl responseOperation= (OperationImpl)definition.getPortType((QName) portTypes[0]).getOperations().get(0);
			List responseOperations=definition.getPortType((QName) portTypes[0]).getOperations();
			
			OresponseComboBox.setEnabled(true);
			OresponseComboBox.removeAll();
			for(int i=0;i<responseOperations.size();++i){
				OresponseComboBox.add(((OperationImpl)responseOperations.get(i)).getName());
			}
			OresponseComboBox.select(0);
			
			//OportTextBox.setText("")
			
		}*/
		 /*if(operation.getStyle().toString().equals("ONE_WAY"))
		{
			
			oneway.setSelection(true);
			requestres.setSelection(false);			
			OresponseComboBox.setText("");
			OresponseComboBox.setEnabled(false);
			OportComboBox.setText("");
			OportComboBox.setEnabled(false);
		}
		*/
		//oneway.setSelection(selected);
		
	}
	
	private void configImportedWsdl(final Combo WsdlComboBox)
	throws IOException {

BufferedReader br;
try {
	br= new BufferedReader(new InputStreamReader(
			new DataInputStream(new FileInputStream(filename))));
	String strLine;
	while ((strLine = br.readLine()) != null) {
		WsdlComboBox.add(strLine);
	}
	br.close();
	
} catch (IOException e) {
	System.out.println("Error: " + e.getMessage());
}
System.out.println(WsdlComboBox.getItemCount());
if(WsdlComboBox.getItemCount()!=0)
{
	selectedWsdlComboBoxItem = WsdlComboBox.getItem(0);
}
	  
	
	WsdlComboBox.addModifyListener(new ModifyListener() {
public void modifyText(ModifyEvent e) {
	//validateInput();
	selectedWsdlComboBoxItem= WsdlComboBox.getItem(WsdlComboBox.getSelectionIndex());
	//WSDLReaderImpl reader =new WSDLReaderImpl();
	
	updateDetailsAccordingToWSDL();
	
	
}
});


}



private void saveToFile(String location) throws IOException {
FileWriter fw = null;
	try {		

	fw = new FileWriter(filename, true); // the true will append the new
											// data

} catch (FileNotFoundException ioe) {
	System.out.println("File not found !");
}
fw.write(location + "\n");// appends the string to the file
fw.close();



comboDropDown.add(location);
int count = comboDropDown.getItemCount();
comboDropDown.select(count - 1);
selectedWsdlComboBoxItem = comboDropDown.getItem(comboDropDown
		.getSelectionIndex());

}
	
	

private void updateInterfaceTab() {
	
	if (selectedNotification.getInterface() != null) {

		if (selectedNotification.getInterface().getPortType() != null) {				
			portComboBox.setText(selectedNotification.getInterface().getPortType()
					.toString());
		} else {
			portComboBox.setText("");
		}
		if (selectedNotification.getInterface().getOperation() != null) {				
			operationComboBox.setText(selectedNotification.getInterface().getOperation());
		} else {

			operationComboBox.setText("");
		}
		
		}
		
		
	 else {
		portComboBox.setText("");
		operationComboBox.setText("");
	
	}

}

private void update_peopleAssingment() {

	if (selectedHumanRole != null) {		
		
		if(selectedHumanRole.getTypeByIndex()==4){
			roal_type.select(0);
		}
		if(selectedHumanRole.getTypeByIndex()==5){
			roal_type.select(1);
		}
		
		
		int itemCount = selectLogicalPeopleGroup_combo.getItemCount();
		for (int j = 0; j < itemCount; ++j) {
			if ((selectLogicalPeopleGroup_combo.getItem(j))
					.equals(selectedHumanRole.getLogicalPeopleGroup())) {
				selectLogicalPeopleGroup_combo.select(j);
			}

		}
		if (selectedHumanRole.getGenericHumanRole().getFrom() != null) {
			if (selectedHumanRole.getGenericHumanRole().getFrom().getArgument()!=null){
			if (selectedHumanRole.getGenericHumanRole().getFrom().getArgument().getName() != null) {
			peopleAssignmentNametextbox.setText(selectedHumanRole
					.getGenericHumanRole().getFrom().getArgument()
					.getName());
			}
			

			try
			{
			peopleAssignmentExptextbox.setText(selectedHumanRole
					.getGenericHumanRole().getFrom().getArgument()
					.getMixed().getValue(0).toString());
			}
			catch(Exception e)
			{
				System.out.println(e+"  update people assignment table");
				peopleAssignmentExptextbox.setText("");
			}
			}
		}
		else
		{
			peopleAssignmentNametextbox.setText("");
			peopleAssignmentExptextbox.setText("");
		}
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
