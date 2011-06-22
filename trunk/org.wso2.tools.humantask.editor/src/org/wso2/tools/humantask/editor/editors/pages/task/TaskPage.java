package org.wso2.tools.humantask.editor.editors.pages.task;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
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
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationParameter;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTasks;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TText;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;
import org.wso2.tools.humantask.editor.editors.base.util.EMFObjectHandleUtil;
import org.wso2.tools.humantask.editor.editors.pages.util.Messages;

public class TaskPage extends FormPage implements IResourceChangeListener,
		Listener {

	private CTabFolder tabFolder;

	protected HTMultiPageEditor editor;
	protected EditingDomain domain;
	protected TTasks tasks;
	protected FormToolkit toolkit;
	protected ComposedAdapterFactory adaptorFactory;
	protected String pageTitle;
	private boolean isResourceChanged;
	private ScrolledForm form;
	private TabContent[] temp;
	private int tempindex;
	private boolean isFirst = true;

	private Section via_logical_ppl;
	private Section via_exp;
	private Section via_literal;

	protected THumanInteractions humanInteractions;

	private Text portTextBox;
	private Text OportTextBox;
	private Text OresponseTextBox;
	private Text operationTextBox;

	private TTask input;
	HumanRole selectedHumanRole;

	private Section taskTableSection;
	private TaskTable taskTable;
	private TableViewer viewer_peopleAssignment;
	private Table table_peopleAssingment;
	private Text exp_lang_text;

	// private TPresentationselectedPreElement;
	private TableViewer presentationElemNameViewer;
	private Table table_presentationElemName;
	
	private TableViewer PresentationParameterViewer;
	private Table table_presentationParm;
	
	private TableViewer presentationElemSubjectViewer;
	private Table table_presentationElemSub;
	
	private TableViewer presentationElemDescViewer;
	private Table table_PresentationElemDesc;
	
	private TText selectedElemName;
	
	private TPresentationParameter selectedParam;
	
	// presentation element subject
	private TText selectedElemSubject;

	// presentation element description
	private TDescription selectedElemDesc;

	// Presentation parameterSection fields
	private Text preElem_GenInfo_name_txt;
	private Text preElem_GenInfo_lang_txt;
	
	private Text preParm_nametext;
	private Text preParm_typetext;
	private Text preParm_exptext;
	
	private Text preElemSubInfo_subject_txt;
	private Text preElemSubInfo_lang_txt;
	
	private Text preElemDescInfo_lang_txt;
	private Combo preElemDescInfo_context_type;
	private Text preElemDescInfo_desc_txt;

	
	
	
	public TaskPage(HTMultiPageEditor editor, TTasks tasks,
			THumanInteractions humanInteractions) {
		super(editor, "HTHT", "Human Task");

		this.pageTitle = super.getTitle();
		this.editor = editor;
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this,
				IResourceChangeEvent.POST_CHANGE);

		this.domain = editor.getEditingDomain();
		this.adaptorFactory = editor.getAdapterFactory();
		this.tasks = tasks;
		this.humanInteractions = humanInteractions;

	}

	public String getTitle() {
		return this.pageTitle;
	}

	// Create form content

	protected void createFormContent(IManagedForm managedform) {

		if (!domain.getResourceSet().getResources().isEmpty()) {

			toolkit = managedform.getToolkit();
			form = managedform.getForm();
			form.setText("Human Task");
			GridLayout layout = new GridLayout(2, true); // layout for the form
															// body
			layout.marginWidth = 10;
			form.getBody().setLayout(layout);

			tabFolder = new CTabFolder(form.getBody(), SWT.FLAT | SWT.TOP);
			toolkit.adapt(tabFolder, true, true);

			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.heightHint = 0;
			tabFolder.setLayoutData(gd);

			@SuppressWarnings("deprecation")
			Color selectedColor = toolkit.getColors().getColor(
					FormColors.SEPARATOR);
			tabFolder.setSelectionBackground(new Color[] { selectedColor,
					toolkit.getColors().getBackground() }, new int[] { 50 });
			toolkit.paintBordersFor(tabFolder);

			checkAvailability_Tasks();
			chechAvailability_PElemName();
			checkAvailability_HumanRole();
			checkAvailability_Pparm();
			checkAvailabilityPElemSubTable();
			checkAvailabilityPElemDescTable();

			taskTable = new TaskTable(humanInteractions, domain, this, toolkit,
					tabFolder, form, tasks);
			taskTableSection = taskTable.createTaskTableSection(managedform);

			createGenarelInfoTab(toolkit, form, managedform);
			createPeaopleAssTab(toolkit, form, managedform);
			createPresentationElementTab(toolkit, form, managedform);

			tabFolder.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					updateSelection();
				}
			});

			tabFolder.setSelection(0);
			updateSelection();
		}
	}

	private void createGenarelInfoTab(FormToolkit toolkit, ScrolledForm form,
			IManagedForm managedform) {
		CTabItem item = new CTabItem(tabFolder, SWT.NULL);

		TabContent section2 = new TabContent(createGenaralInfoSection(toolkit,
				form, managedform));
		TabContent section1 = new TabContent(taskTableSection);
		TabContent[] sectionArray = new TabContent[2];
		sectionArray[0] = section1;
		sectionArray[1] = section2;
		item.setText(Messages.getString("TaskPage.interfaceTab.title"));
		item.setData(sectionArray);

	}

	private void createPeaopleAssTab(FormToolkit toolkit, ScrolledForm form,
			IManagedForm managedForm) {
		CTabItem item = new CTabItem(tabFolder, SWT.NULL);

		TabContent section2 = new TabContent(createPeopelAssiTableSection(
				toolkit, form));
		TabContent section3 = new TabContent(createPeopleAssiSection(toolkit,
				form));
		TabContent section1 = new TabContent(taskTableSection);

		via_logical_ppl = createLogicalPplSection(toolkit, form);
		TabContent section4 = new TabContent(via_logical_ppl);

		via_exp = createExpSection(toolkit, form);
		TabContent section5 = new TabContent(via_exp);

		via_literal = createLiteralsSection(toolkit, form);
		TabContent section6 = new TabContent(via_literal);

		TabContent[] sectionArray = new TabContent[6];
		sectionArray[0] = section1;
		sectionArray[1] = section2;
		sectionArray[2] = section3;
		sectionArray[3] = section4;
		sectionArray[4] = section5;
		sectionArray[5] = section6;
		item.setText(Messages.getString("TaskPage.peopleassingmentTab.title"));
		item.setData(sectionArray);
	}

	private void createPresentationElementTab(FormToolkit toolkit,
			ScrolledForm form, IManagedForm managedForm) {
		CTabItem item = new CTabItem(tabFolder, SWT.NULL);

		TabContent section1 = new TabContent(taskTableSection);
		TabContent section2 = new TabContent(createPElemGenralTableSection(toolkit));
		
		TabContent section3 = new TabContent(createPElemGeneralInfoSection(toolkit, form));
		
		TabContent section4 = new TabContent(createPParamTableSection(toolkit, form));
		
		TabContent section5 = new TabContent(createPParmDetailSection(toolkit,form));
		
		TabContent section6 = new TabContent(createPElemSubjectTableSection(toolkit));
		
		TabContent section7 = new TabContent(createPElemSubjectInfoSection(toolkit, form));
		
		TabContent section8 = new TabContent(createPElemDescTableSection(toolkit));
		
		TabContent section9 = new TabContent(createPElemDesInfoSection(toolkit,form));

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
		
		item.setText(Messages
				.getString("TaskPage.presentationElementTab.title"));
		item.setData(sectionArray);
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

	

	// Create general Info

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
		configGeneralInfoSection_portType(portTextBox);

		// Operation label and Text box
		Label operationLabel = new Label(sectionClient, SWT.WRAP);
		operationLabel.setText(Messages
				.getString("TaskPage.interfaceTab.Section.operationlable"));
		operationLabel.setLayoutData(data);
		operationTextBox = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		operationTextBox.setSize(100, 20);
		operationTextBox.setLayoutData(data);
		configGeneralInfoSection_operation(operationTextBox);

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
		configGeneralInfoSection_OportType(OportTextBox);

		// Oresponse label and Text box
		final Label OresponseLabel = new Label(sectionClient, SWT.WRAP);
		OresponseLabel
				.setText(Messages
						.getString("TaskPage.interfaceTab.Section.onewayradio.responselable"));
		OresponseLabel.setLayoutData(data);

		OresponseTextBox = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		OresponseTextBox.setSize(100, 20);
		OresponseTextBox.setLayoutData(data);
		configGeneralInfoSection_Oresponse(OresponseTextBox);

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

		section.setClient(sectionClient);

		return section;

	}

	private void configGeneralInfoSection_portType(final Text portTextBox) {
		if (tasks != null) {
			if (tasks.getTask().get(0).getInterface().getPortType() != null) {
				if ((tasks.getTask().get(0).getInterface().getPortType()
						.toString() != null)) {
					portTextBox.setText((tasks.getTask().get(0).getInterface()
							.getPortType().toString()));
				} else {
					portTextBox
							.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
				}
			}
		}
		portTextBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				setAttribute_Qname(
						htdPackage.eINSTANCE.getTTaskInterface_PortType(),
						new QName(portTextBox.getText()));
			}
		});

	}

	private void configGeneralInfoSection_operation(final Text operationTextBox) {
		if (tasks != null) {
			if ((tasks.getTask().get(0).getInterface().getOperation() != null)) {
				operationTextBox.setText((tasks.getTask().get(0).getInterface()
						.getOperation()));
			} else {
				operationTextBox
						.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			}
		}

		operationTextBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				setAttribute(
						htdPackage.eINSTANCE.getTTaskInterface_Operation(),
						operationTextBox.getText());
			}
		});

	}

	private void configGeneralInfoSection_OportType(final Text OportTypeTextBox) {
		if (tasks != null) {
			if (tasks.getTask().get(0).getInterface().getResponsePortType() != null) {
				if ((tasks.getTask().get(0).getInterface()
						.getResponsePortType().toString() != null)) {
					OportTypeTextBox.setText((tasks.getTask().get(0)
							.getInterface().getResponsePortType().toString()));
				} else {
					OportTypeTextBox
							.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
				}
			}
		}
		OportTypeTextBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				setAttribute_Qname(htdPackage.eINSTANCE
						.getTTaskInterface_ResponsePortType(), new QName(
						OportTypeTextBox.getText()));
			}
		});

	}

	private void configGeneralInfoSection_Oresponse(final Text OresponseTextBox) {
		if (tasks != null) {
			if ((tasks.getTask().get(0).getInterface().getResponseOperation() != null)) {
				OresponseTextBox.setText((tasks.getTask().get(0).getInterface()
						.getOperation()));
			} else {
				OresponseTextBox
						.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			}
		}
		OresponseTextBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				setAttribute(htdPackage.eINSTANCE
						.getTTaskInterface_ResponseOperation(),
						OresponseTextBox.getText());
			}
		});

	}

	private void setAttribute(EAttribute tTaskInterface_Attribute, String text) {
		Command setAttribCommand = SetCommand.create(domain,
				input.getInterface(), tTaskInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
		} else {
			System.out.println("can't modify Attribute: "
					+ tTaskInterface_Attribute.getName());
		}
	}

	private void setAttribute_Qname(EAttribute tTaskInterface_Attribute,
			QName text) {
		Command setAttribCommand = SetCommand.create(domain,
				input.getInterface(), tTaskInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
		} else {
			System.out.println("can't modify Attribute: "
					+ tTaskInterface_Attribute.getName());
		}
	}

	private void updateInterfaceTab() {
		if (input.getInterface() != null) {

			if (input.getInterface().getPortType() != null) {
				portTextBox.setText(input.getInterface().getPortType()
						.toString());
			} else {
				portTextBox.setText("");
			}
			if (input.getInterface().getOperation() != null) {
				operationTextBox.setText(input.getInterface().getOperation());
			} else {

				operationTextBox.setText("");
			}
			if (input.getInterface().getResponseOperation() != null) {
				OresponseTextBox.setText(input.getInterface()
						.getResponseOperation());
			} else {
				OresponseTextBox.setText("");
			}
			if (input.getInterface().getResponsePortType() != null) {
				OportTextBox.setText(input.getInterface().getResponsePortType()
						.toString());
			} else {
				OportTextBox.setText("");
			}
		} else {
			portTextBox.setText("");
			operationTextBox.setText("");
			OresponseTextBox.setText("");
			OportTextBox.setText("");
		}

	}

	// Create people assignments

	private Section createPeopleAssiSection(final FormToolkit toolkit,
			final ScrolledForm form) {

		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);

		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText(Messages
				.getString("TaskPage.peopleassingmentTab.Section.title"));
		section.setDescription(Messages
				.getString("TaskPage.peopleassingmentTab.Section.description"));
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);

		final Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, true);
		layout.marginWidth = 2;
		layout.marginHeight = 5;
		// layout.makeColumnsEqualWidth = true;
		sectionClient.setLayout(layout);

		final Composite combo = toolkit.createComposite(sectionClient);
		GridData rgd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		rgd.horizontalSpan = 1;
		// rgd.verticalSpan =1;
		combo.setLayoutData(rgd);
		FillLayout fl = new FillLayout(SWT.VERTICAL);
		combo.setLayout(fl);

		Label radiolabel = new Label(combo, SWT.WRAP);
		radiolabel
				.setText(Messages
						.getString("TaskPage.peopleassingmentTab.Section.radiobtnsetlabel"));

		Combo roal_type = new Combo(combo, SWT.READ_ONLY);
		roal_type
				.add(Messages
						.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.potentialownerslable"),
						0);
		roal_type
				.add(Messages
						.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.excludedowners"),
						1);
		roal_type
				.add(Messages
						.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.taskinitiator"),
						2);
		roal_type
				.add(Messages
						.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.taskstakeholders"),
						3);
		roal_type
				.add(Messages
						.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.businessadministrators"),
						4);
		roal_type
				.add(Messages
						.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.notificationrecipients"),
						5);

		roal_type.select(0);
		section.setClient(sectionClient);

		Composite dynamic = toolkit.createComposite(sectionClient);
		GridData dydata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		dydata.horizontalSpan = 1;
		dynamic.setLayoutData(dydata);

		FillLayout fld = new FillLayout(SWT.VERTICAL);
		dynamic.setLayout(fld);

		Label combolabel = new Label(dynamic, SWT.WRAP);
		combolabel
				.setText(Messages
						.getString("TaskPage.peopleassingmentTab.Section.dynamic.combolabel"));
		final Combo vialist = new Combo(dynamic, SWT.READ_ONLY);
		vialist.add(
				Messages.getString("TaskPage.peopleassingmentTab.Section.dynamic.vialist.methodone"),
				0);
		vialist.add(
				Messages.getString("TaskPage.peopleassingmentTab.Section.dynamic.vialist.methodtwo"),
				1);
		vialist.add(
				Messages.getString("TaskPage.peopleassingmentTab.Section.dynamic.vialist.methodthree"),
				2);
		vialist.select(0);

		// TODO FOCUs Problem.

		vialist.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// System.out.println(vialist.getSelectionIndex());
				int index = vialist.getSelectionIndex();
				if (index == 0) {
					if (via_exp.isEnabled()) {

						via_exp.setEnabled(false);
					}
					if (via_literal.isEnabled()) {

						via_literal.setEnabled(false);
					}
					via_logical_ppl.setEnabled(true);

				} else if (index == 1) {
					if (via_logical_ppl.isEnabled()) {

						via_logical_ppl.setEnabled(false);
					}
					if (via_literal.isEnabled()) {

						via_literal.setEnabled(false);
					}

					via_exp.setEnabled(true);

				} else if (index == 2) {
					if (via_logical_ppl.isEnabled()) {

						via_logical_ppl.setEnabled(false);
					}
					if (via_exp.isEnabled()) {

						via_exp.setEnabled(false);
					}

					via_literal.setEnabled(true);
				}

			}

		});

		return section;
	}

	private Section createPeopelAssiTableSection(final FormToolkit toolkit,
			final ScrolledForm form) {
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText("Existing People Assingments");
		section.setDescription("THIS SHOULD BE ADDED LATER");
		GridData sectiondata = new GridData(GridData.FILL_BOTH);
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

		GridData gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
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
				.setContentProvider(new PeopleAssignmentContentProvider());
		viewer_peopleAssignment.setInput(createModleForPeopleAssinment());
		table_peopleAssingment.setSelection(0);

		section.setClient(sectionClient);
		return section;
	}

	public Object createModleForPeopleAssinment() {
		return input;
	}

	private void createColumnsPeopleAssiTable(Composite parent,
			TableViewer viewer) {
		String[] titles = { "People Assignment", "Detail" };
		int[] bounds = { 100, 100 };

		// first column
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {

				HumanRole role = (HumanRole) element;
				return role.getType();
			}

		});

		// second column
		TableViewerColumn col2 = createTableViewerColumn(viewer, titles[1],
				bounds[1]);
		col2.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {

				HumanRole role = (HumanRole) element;
				return role.getPeopleAssignMethodology();
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
		section.setDescription("THIS SHOULD BE ADDED LATER");
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, true);
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);

		Label selectppllabel = new Label(sectionClient, SWT.WRAP);
		selectppllabel.setText("Select Logical group");

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL
				| GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 1;
		// gd.verticalSpan =2;
		selectppllabel.setLayoutData(gd);

		// Should be reviewed
		Combo combo = new Combo(sectionClient, SWT.READ_ONLY);
		combo.setLayoutData(gd);
		combo.add("This shoud provide via ", 0);
		combo.add("This shoud provide via ", 1);
		combo.add("This shoud provide via ", 2);
		combo.select(0);
		GridData cgd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		// cgd.verticalIndent = 5;
		// cgd.verticalSpan =2;
		combo.setLayoutData(cgd);

		Label namelabel = new Label(sectionClient, SWT.WRAP);
		namelabel.setText("Name");
		namelabel.setLayoutData(gd);

		Text nametextbox = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		nametextbox.setLayoutData(gd);

		Label explabel = new Label(sectionClient, SWT.WRAP);
		explabel.setText("Expression");
		explabel.setLayoutData(gd);

		Text exptextbox = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		exptextbox.setLayoutData(gd);

		// section.setExpanded(true);
		section.setEnabled(true);
		section.setClient(sectionClient);

		return section;
	}

	private Section createExpSection(final FormToolkit toolkit,
			final ScrolledForm form) {
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText(Messages
				.getString("TaskPage.peopleassingmentTab.Section.dynamic.vialist.methodtwo"));
		section.setDescription("THIS SHOULD BE ADDED LATER");
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, true);
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		Label exp_lang_label = new Label(sectionClient, SWT.WRAP);
		exp_lang_label
				.setText(Messages
						.getString("TaskPage.peopleassingmentTab.expSection.explang_label"));
		GridData explbgd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL
				| GridData.GRAB_VERTICAL);
		explbgd.horizontalSpan = 1;
		exp_lang_label.setLayoutData(explbgd);

		exp_lang_text = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		exp_lang_text.setLayoutData(explbgd);
		configPeopleAssignSection_exp_lang(exp_lang_text);

		Label exp_label = new Label(sectionClient, SWT.WRAP);
		exp_label
				.setText(Messages
						.getString("TaskPage.peopleassingmentTab.expSection.exp_label"));
		exp_label.setLayoutData(explbgd);

		// TODO get the <>internals<>
		Text exp_text = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		exp_text.setLayoutData(explbgd);

		sectionClient.setLayout(layout);

		// section.setExpanded(false);
		section.setEnabled(false);
		section.setClient(sectionClient);

		return section;

	}

	private Section createLiteralsSection(final FormToolkit toolkit,
			final ScrolledForm form) {
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText(Messages
				.getString("TaskPage.peopleassingmentTab.Section.dynamic.vialist.methodthree"));
		section.setDescription("THIS SHOULD BE ADDED LATER");
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		sectiondata.horizontalSpan = 2;
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, true);
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);

		Label user_label = new Label(sectionClient, SWT.WRAP);
		user_label
				.setText(Messages
						.getString("TaskPage.peopleassingmentTab.literalSection.user_label"));
		GridData ulbdata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL
				| GridData.GRAB_VERTICAL);
		ulbdata.horizontalSpan = 1;

		user_label.setLayoutData(ulbdata);

		Text user_text_box = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		user_text_box.setLayoutData(ulbdata);

		Label group_label = new Label(sectionClient, SWT.WRAP);
		group_label
				.setText(Messages
						.getString("TaskPage.peopleassingmentTab.literalSection.group_label"));
		group_label.setLayoutData(ulbdata);

		Text group_text_box = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		group_text_box.setLayoutData(ulbdata);

		section.setEnabled(false);
		section.setClient(sectionClient);

		return section;
	}

	// /////////////////////////////////////////////////////////////////////////////////
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
				setAttribute_poepleAssignViaExpression(htdPackage.eINSTANCE
						.getTTaskInterface_ResponseOperation(), exp_langTextBox
						.getText());
			}
		});

	}

	private void setAttribute_poepleAssignViaExpression(
			EAttribute tTaskInterface_Attribute, String text) {
		Command setAttribCommand = SetCommand.create(domain,
				input.getInterface(), tTaskInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
		} else {
			System.out.println("can't modify Attribute: "
					+ tTaskInterface_Attribute.getName());
		}
	}

	void itemSelecter_peopleAssignment(ISelection selection) {
		IStructuredSelection ssel = (IStructuredSelection) selection;

		if (ssel.size() == 1) {
			selectedHumanRole = (HumanRole) ssel.getFirstElement();
			System.out.println(selectedHumanRole.getPeopleAssignMethodology());
		} else {
			selectedHumanRole = null;
		}

		update_peopleAssingment();
	}

	private void update_peopleAssingment() {

		if (selectedHumanRole.getPeopleAssignMethodology() != null) {

			if ((selectedHumanRole.getPeopleAssignMethodology())
					.equals("By LogicalPeopleGroup")) {

			}
			if ((selectedHumanRole.getPeopleAssignMethodology())
					.equals("By Literal")) {

			}
			if ((selectedHumanRole.getPeopleAssignMethodology())
					.equals("By ExpressionLanguage")) {
				exp_lang_text.setText(selectedHumanRole.getGenericHumanRole()
						.getFrom().getExpressionLanguage());
			}

		}

		if (input.getInterface().getPortType() != null) {
			portTextBox.setText(input.getInterface().getPortType().toString());
		}

		operationTextBox.setText(input.getInterface().getOperation());
		OresponseTextBox.setText(input.getInterface().getResponseOperation());

		if (input.getInterface().getResponsePortType() != null) {
			OportTextBox.setText(input.getInterface().getResponsePortType()
					.toString());
		}

	}

	void taskTableItemSelecter(ISelection selection) {

		IStructuredSelection ssel = (IStructuredSelection) selection;

		if (ssel.size() == 1) {
			input = (TTask) ssel.getFirstElement();
			// System.out.println(input.getName());
		} else {
			input = null;
		}

		table_peopleAssingment.removeAll();
		ArrayList<HumanRole> list = (ArrayList<HumanRole>) getPeopleAssignmentList(input);
		for (int i = 0; i < list.size(); i++) {

			TableItem item = new TableItem(table_peopleAssingment, SWT.NONE);
			item.setText(0, list.get(i).getType());
			item.setText(1, list.get(i).getPeopleAssignMethodology());

		}

		
		updateInterfaceTab();
		updatePreElemNameTable();
		updatePresentationPramTable();
		updatePreElemSubjectTable();
		updatePreElemDescTable();
	}

	private List getPeopleAssignmentList(TTask inputElement) {
		TTask t = inputElement;
		List<HumanRole> list = new ArrayList<HumanRole>();
		// list.add(t);*/

		if (t.getPeopleAssignments() != null) {

			if (t.getPeopleAssignments().getBusinessAdministrators() != null) {
				for (int i = 0; i < t.getPeopleAssignments()
						.getBusinessAdministrators().size(); ++i) {
					list.add(new HumanRole(t.getPeopleAssignments()
							.getBusinessAdministrators().get(i),
							"BusinessAdministrators"));
				}
			}
			if (t.getPeopleAssignments().getExcludedOwners() != null) {
				for (int i = 0; i < t.getPeopleAssignments()
						.getExcludedOwners().size(); ++i) {
					list.add(new HumanRole(t.getPeopleAssignments()
							.getExcludedOwners().get(i), "ExcludedOwners"));
				}
			}
			if (t.getPeopleAssignments().getPotentialOwners() != null) {
				for (int i = 0; i < t.getPeopleAssignments()
						.getPotentialOwners().size(); ++i) {
					list.add(new HumanRole(t.getPeopleAssignments()
							.getPotentialOwners().get(i), "PotentialOwners"));
				}
			}
			if (t.getPeopleAssignments().getRecipients() != null) {
				for (int i = 0; i < t.getPeopleAssignments().getRecipients()
						.size(); ++i) {
					list.add(new HumanRole(t.getPeopleAssignments()
							.getRecipients().get(i), "Recipients"));
				}
			}
			if (t.getPeopleAssignments().getTaskInitiator() != null) {
				for (int i = 0; i < t.getPeopleAssignments().getTaskInitiator()
						.size(); ++i) {
					list.add(new HumanRole(t.getPeopleAssignments()
							.getTaskInitiator().get(i), "TaskInitiator"));
				}
			}

			if (t.getPeopleAssignments().getTaskStakeholders() != null) {
				for (int i = 0; i < t.getPeopleAssignments()
						.getTaskStakeholders().size(); ++i) {
					list.add(new HumanRole(t.getPeopleAssignments()
							.getTaskStakeholders().get(i), "TaskStakeholders"));
				}
			}
		}
		return list;

	}

	// ///////////////////////////////////////////////////////////////////////////////////

	
	
	
	// Create Presentations
	// TODO
	private Section createPElemGenralTableSection(FormToolkit toolkit){
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR);

		section.setText("Create Name");
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
		//		PresentationParameterViewer);
		
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
		
		presentationElemNameViewer.setContentProvider(new PresentationNameElemContentProvider());
		presentationElemNameViewer.setInput(input);
		
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
		section.setClient(sectionClient);
		return section;
		
	}
	private Section createPElemGeneralInfoSection(FormToolkit toolkit,
			ScrolledForm form) {

		Section info_section = toolkit.createSection(tabFolder,
				Section.DESCRIPTION | Section.TITLE_BAR);
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

		info_section.setClient(sectionClient);

		return info_section;
	}

	private Section createPParamTableSection(
			final FormToolkit toolkit, ScrolledForm form) {
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR);

		section.setText("Create Presntation Parameter");
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
				.setContentProvider(new PresentationParamContentProvider());
		PresentationParameterViewer.setInput(input);
		table_presentationParm.setSelection(0);

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
		section.setClient(sectionClient);
		return section;
	}

	private Section createPParmDetailSection(FormToolkit toolkit, ScrolledForm form) {

		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR);

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
		toolkit.paintBordersFor(sectionClient);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL
				| GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 1;

		Label namelabel = new Label(sectionClient, SWT.WRAP);
		namelabel.setText("Name");
		namelabel.setLayoutData(gd);

		preParm_nametext = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		preParm_nametext.setLayoutData(gd);

		Label typelabel = new Label(sectionClient, SWT.WRAP);
		typelabel.setText("Type");
		typelabel.setLayoutData(gd);

		preParm_typetext = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		preParm_typetext.setLayoutData(gd);

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
				| Section.TITLE_BAR);

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
				.setContentProvider(new PresentationElemeSubContentProvider());

		presentationElemSubjectViewer.setInput(input);
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
		section.setClient(sectionClient);
		return section;

	}

	private Section createPElemSubjectInfoSection(FormToolkit toolkit,
			ScrolledForm form) {
		Section sub_section = toolkit.createSection(tabFolder,
				Section.DESCRIPTION | Section.TITLE_BAR);
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

		sub_section.setClient(sectionClient);

		return sub_section;
	}

	private Section createPElemDescTableSection(FormToolkit toolkit) {
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR);

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

		presentationElemDescViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						
						preElemDescViewerItemSelecter(event.getSelection());

					}
				});

		presentationElemDescViewer
				.setContentProvider(new PresentationElemDescContentProvider());
		presentationElemDescViewer.setInput(input);

		table_PresentationElemDesc.setSelection(0);

		section.setClient(sectionClient);
		return section;

	}

	private Section createPElemDesInfoSection(FormToolkit toolkit,
			ScrolledForm form) {
		Section desc_section = toolkit.createSection(tabFolder,
				Section.DESCRIPTION | Section.TITLE_BAR);
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
	
	
	// creating columns
	
	
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
				return parm.getType().toString();
			}

		});

		
		//third column
		TableViewerColumn col3 = createTableViewerColumn(viewer, titles[2], bounds[2]);
		col3.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				TPresentationParameter parm = (TPresentationParameter) element;
				return parm.getMixed().get(0).toString();
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

	
	//functional area of presentation Elements
	
	
	private void updatePreElemNameTable(){
		
		if(input.getPresentationElements() != null){
			if(input.getPresentationElements().getName() != null){
				
			 presentationElemNameViewer.setInput(input);
			 table_presentationElemName.setSelection(0);
			 
			 if(input.getPresentationElements().getName().size() != 0){
				 
				 selectedElemName = input.getPresentationElements().getName().get(0);
				 
			 }else{
				 
			 }
				
			 	updatePElemNameDetails();
			}
		}
	}

	private void updatePresentationPramTable(){
	
	if (input.getPresentationElements() != null) {
		if (input.getPresentationElements().getPresentationParameters() != null) {
			if (input.getPresentationElements().getPresentationParameters()
					.getPresentationParameter() != null) {

				PresentationParameterViewer.setInput(input);
				table_presentationParm.setSelection(0);

				if (input.getPresentationElements().getPresentationParameters().getPresentationParameter().size() != 0) {
					
					selectedParam = input.getPresentationElements().getPresentationParameters().getPresentationParameter().get(0);

				} else {
				//	clearText();
				}

				updatePparmDetails();
			}
		}
	}
}

	private void updatePreElemSubjectTable(){
	
	if(input.getPresentationElements() != null){
		if(input.getPresentationElements().getSubject() != null){
			
			presentationElemSubjectViewer.setInput(input);
			table_presentationElemSub.setSelection(0);
			
			if(input.getPresentationElements().getSubject().size() != 0){
				
				selectedElemSubject = input.getPresentationElements().getSubject().get(0);
			}else{
				
			}
			
			updatePElemSubDetail();
		}
	}
}

	private void updatePreElemDescTable(){
	
	if(input.getPresentationElements() != null){
		if(input.getPresentationElements().getDescription() != null){
			
			presentationElemDescViewer.setInput(input);
			table_PresentationElemDesc.setSelection(0);
			
			if(input.getPresentationElements().getDescription().size() != 0){
				
				selectedElemDesc = input.getPresentationElements().getDescription().get(0);
			}
			else{
				
			}
			
			updatePElemDescDetail();
			}
		}
	}

	// item selecters
	
private void preElemNameViewerItemSelecter(ISelection selection){
		
		IStructuredSelection ssel = (IStructuredSelection) selection;
		
		if(ssel.size() == 1)
		{
			selectedElemName = (TText)ssel.getFirstElement();
		}
		else
		{
			selectedElemName = null;
			
		}
		
			updatePElemNameDetails();
	}
	
	private void preParamViewerItemSelecter(ISelection selection) {
		
		IStructuredSelection ssel = (IStructuredSelection) selection;
		
		if (ssel.size() == 1) {
			
			selectedParam = (TPresentationParameter) ssel.getFirstElement();

		} else 
		{
			
			selectedParam = null;
			
		}

		
			updatePparmDetails();
	}
	
	
	
	private void preElemSubViewerItemSelecter(ISelection selection)
	{
		IStructuredSelection ssel = (IStructuredSelection) selection;
		
		if(ssel.size() ==  1)
		{
			
			selectedElemSubject = (TText)ssel.getFirstElement();
		}
		else
		{
			selectedElemSubject = null;
		}
		
		
			updatePElemSubDetail();
		
	}
	
	
	private void preElemDescViewerItemSelecter(ISelection selection)
	{
		IStructuredSelection ssel = (IStructuredSelection)selection;
		
		if(ssel.size() == 1)
		{
			
			selectedElemDesc = (TDescription)ssel.getFirstElement();
		}
		else
		{
			selectedElemDesc = null;
		}
		
		
			updatePElemDescDetail();
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
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//hjakk
	
	private void configPElemGeneralInfoSection_name(final Text nameTextBox)
	{
		/* if (input != null)
		  {
			  if(selectedElemName.ge!= null))
			  { 
				  nameTextBox.setText((tasks.getTask().get(0).getPresentationElements().
						  getPresentationParameters().getPresentationParameter().get(0).getName())); 
			  } 
			  else 
			  {
				nameTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE); 
				
			  } 
			  
		  }*/
		  		
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
			System.out.println("can't modify Attribute: " + tParameter_attribute.getName());
		}
	}
	
	/*private void configPresentationInfoSection_name(final Text nameTextBox) 
	{
	  if (tasks != null)
	  {
		  if((tasks.getTask().get(0).getPresentationElements().getPresentationParameters()
				  .getPresentationParameter() .get(0).getName()!= null))
		  { 
			  nameTextBox.setText((tasks.getTask().get(0).getPresentationElements().
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
	  				setAttribute_tParameter(htdPackage.eINSTANCE.getTParameter_Name(), nameTextBox.getText()); 
	  				
	  			}
	  		});
	  
	  }

	private void setAttribute_tParameter(EAttribute tParameter_attribute,String text)
	{
		Command setAttribCommand = SetCommand.create(domain, tasks.getTask().get(0).
				getPresentationElements().getPresentationParameters()
				.getPresentationParameter(), tParameter_attribute, text);

		if (setAttribCommand.canExecute()) 
		{
			domain.getCommandStack().execute(setAttribCommand);
		} else
		{
			System.out.println("can't modify Attribute: " + tParameter_attribute.getName());
		}
	}
	 */
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	// Availability checks

	private void checkAvailability_Tasks() {
		if (tasks == null) {
			System.out.println("checkAvailability_Tasks() + Error message");
			// Error message

		} else {
			input = tasks.getTask().get(0);
		}

	}
	
	private void chechAvailability_PElemName(){
		
		if (input == null){
			System.out.println("chechAvailability_PElemName()+ Error message");
			//Error message
		}
		else{
			if(input.getPresentationElements() !=  null){
				selectedElemName = input.getPresentationElements().getName().get(0);
			}
		}
	}

	private void checkAvailability_HumanRole() {
		List peopleassignment = getPeopleAssignmentList(tasks.getTask().get(0));
		if (peopleassignment.size() == 0) {
			// Error message
			System.out.println("checkAvailability_HumanRole() + Error message");
		} else {
			selectedHumanRole = (HumanRole) peopleassignment.get(0);

		}

	}

	private void checkAvailability_Pparm() {

		if (input == null) {
			System.out.println("checkAvailability_Pparm() +Error message");
			// Error message
		} else {
			if(input.getPresentationElements().getPresentationParameters().getPresentationParameter() != null){
			selectedParam = input.getPresentationElements()
					.getPresentationParameters().getPresentationParameter()
					.get(0);
			}
		}
	}

	private void checkAvailabilityPElemSubTable() {
		if (input == null) {
			System.out.println("checkAvailabilityPElemSubTable() + Error message");
			//Error message
		} else {
			if(input.getPresentationElements().getSubject() != null){
			selectedElemSubject = input.getPresentationElements().getSubject()
					.get(0);
			}
		}
	}

	private void checkAvailabilityPElemDescTable() {
		if (input == null) {
			System.out.println("checkAvailabilityPElemDescTable() +Error message");
		} else {
			if(input.getPresentationElements().getDescription() != null){
			selectedElemDesc = input.getPresentationElements().getDescription()
					.get(0);
			}
		}
	}

	
	private void clearText(Text text)
	{
		
		text.setText("");
	}

	private void creatSpacer(Composite parent, int horizontalSpan) {

		Label space = new Label(parent, SWT.WRAP);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = horizontalSpan;
		space.setLayoutData(gd);
	}

	
	
	class TabContent {

		Section content;

		public TabContent(Section content) {
			this.content = content;
		}

	}

	
	
	@Override
	public void handleEvent(Event event) {

	}

	public boolean isResourceChanged() {
		return this.isResourceChanged;
	}

	public void setResourceChanged(boolean value) {
		this.isResourceChanged = value;
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		// TODO Auto-generated method stub

	}
}
