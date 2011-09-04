package org.wso2.tools.humantask.editor.editors.pages.logicalpeoplegroups;

import java.awt.Image;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExtension;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLogicalPeopleGroup;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TParameter;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;
import org.wso2.tools.humantask.editor.editors.base.util.EMFObjectHandleUtil;

public class LogicalPeopleGroupPage extends FormPage implements IResourceChangeListener{

	private LogicalPeopleGroupPage logicalPeopleGroupPage;
	protected HTMultiPageEditor editor;
	protected EditingDomain domain;
	protected THumanInteractions humanInteractions;

	protected FormToolkit toolkit;
	protected ComposedAdapterFactory adapterFactory;
	protected String pageTitle;

	private ScrolledForm form;
	private TableViewer logicalPplviewer;
	private TableViewer parameterViewer;
	private Table para_table;

	TLogicalPeopleGroup selectedLogicalPplGroup;
	private TParameter selectedParameter;

	private Text name_txt;
	private Text para_name_txt;
	private Text para_type_txt;
	
	private Table grp_table;

	private boolean isResourceChanged ;

	public LogicalPeopleGroupPage(HTMultiPageEditor editor,
			THumanInteractions humanInteractions) {
		super(editor, "HTLP", "Logical People Groups");
		this.pageTitle = super.getTitle();
		this.editor = editor;
		this.domain = this.editor.getEditingDomain();
		this.adapterFactory = editor.getAdapterFactory();
		this.humanInteractions = humanInteractions;
		this.logicalPeopleGroupPage=this;

	}

	public String getTitle() {
		return this.pageTitle;
	}

	/*
	 * @see
	 * org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui
	 * .forms.IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {

		form = managedForm.getForm();
		form.setText("Logical People Group");
		toolkit = managedForm.getToolkit();

		GridLayout layout = new GridLayout(); // layout for the form body
		layout.numColumns = 1;
		layout.marginWidth = 10;
		form.getBody().setLayout(layout);

		checkAvailability_LogicalPeopleGroup();
		checkAvailability_Parameter();

		createLogicalPeopleTableSection(form.getBody());
		createPrameterTableSection(form.getBody());
		createOverallDetailSection(form.getBody());

	}

	private void createLogicalPeopleTableSection(Composite parent) {

		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setText("Logical People Groups");
		section.setDescription("add later");
		section.marginWidth = 10;
		section.marginHeight = 5;
		GridData sectiondata = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(sectiondata);

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		toolkit.paintBordersFor(client);
		section.setClient(client);

		logicalPplviewer = new TableViewer(client, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		createLogicalPplColumns(client, logicalPplviewer);

		grp_table = logicalPplviewer.getTable();

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;

		grp_table.setLayoutData(gd);
		grp_table.setHeaderVisible(true);
		grp_table.setLinesVisible(true);

		logicalPplviewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						logiclPplTableitemSelecter(event.getSelection());

					}
				});

		logicalPplviewer
				.setContentProvider(new LogicalPeopleGroupTableContentProvider());
		logicalPplviewer.setInput(createLogicalPplModle());

		grp_table.setSelection(0);

		Composite fillcomp = toolkit.createComposite(client,SWT.WRAP);
		fillcomp.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING));
		FillLayout fl = new FillLayout(SWT.VERTICAL);
		fl.spacing = 5;
		fillcomp.setLayout(fl);
		
		Button add_btn = toolkit.createButton(fillcomp, "Add", SWT.PUSH);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

				AddLogicalPeopleGroupWizard wizard = new AddLogicalPeopleGroupWizard(humanInteractions,domain,logicalPplviewer);
				WizardDialog wizardDialog = new WizardDialog(Display
						.getCurrent().getActiveShell(), wizard);
				wizardDialog.create();
				wizardDialog.open();

			}
		});
		
		Button del_btn = toolkit.createButton(fillcomp, "Delete", SWT.PUSH); 
		//del_btn.setLayoutData(btn_gd);

		del_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

			
				boolean done=humanInteractions.getLogicalPeopleGroups().getLogicalPeopleGroup().remove(selectedLogicalPplGroup);
				logicalPplviewer.setInput(createLogicalPplModle());

			}
		});
		

	}

	private void createPrameterTableSection(Composite parent) {

		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setText("Logical People Groups");
		section.setDescription("add later");
		section.marginWidth = 10;
		section.marginHeight = 5;
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.FILL_BOTH);
		section.setLayoutData(sectiondata);

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		toolkit.paintBordersFor(client);
		section.setClient(client);

		parameterViewer = new TableViewer(client, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createPrameterColumns(client, parameterViewer);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;

		para_table = parameterViewer.getTable();
		para_table.setLayoutData(gd);

		para_table.setHeaderVisible(true);
		para_table.setLinesVisible(true);

		parameterViewer.setContentProvider(new ParameterTableContentProvider());
		parameterViewer.setInput(createPramModle());

		para_table.setSelection(0);
		parameterViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {

						parameterViewerItemSelecter(event.getSelection());
					}
				});
		
		Composite fillcomp = toolkit.createComposite(client,SWT.WRAP);
		fillcomp.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING));
		FillLayout fl = new FillLayout(SWT.VERTICAL);
		fl.spacing = 5;
		fillcomp.setLayout(fl);
		
		Button add_btn = toolkit.createButton(fillcomp, "Add", SWT.PUSH);
		//GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		//add_btn.setLayoutData(btn_gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

				AddParmWizard wizard = new AddParmWizard(domain,parameterViewer,logicalPeopleGroupPage);
				WizardDialog wizardDialog = new WizardDialog(Display
						.getCurrent().getActiveShell(), wizard);
				wizardDialog.create();
				wizardDialog.open();

			}
		});
		
		Button del_btn = toolkit.createButton(fillcomp, "Delete", SWT.PUSH);
		//del_btn.setLayoutData(btn_gd);

		del_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

				//TODO viraj : handle the action
				boolean done=selectedLogicalPplGroup.getParameter().remove(selectedParameter);
				parameterViewer.setInput(createPramModle());
				
			}
		});

	}

	private void createOverallDetailSection(Composite parent) {
		final Section detail_section = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE
						| Section.EXPANDED);
		detail_section.setText("Genaral Information");
		detail_section.setDescription("add later");

		detail_section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				if (detail_section.getParent() instanceof ScrolledForm) {
					ScrolledForm form = (ScrolledForm) detail_section
							.getParent();
					form.reflow(true);
				}
			}
		});

		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_BOTH);
		detail_section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(detail_section);
		GridLayout layout = new GridLayout(); // layout for the sectionClient
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;

		Label name_lb = new Label(sectionClient, SWT.WRAP);
		name_lb.setText("Name");
		name_lb.setLayoutData(gd);

		name_txt = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		name_txt.setLayoutData(gd);
		config_logicalPplGroupNameField(name_txt);

		Label para_name = new Label(sectionClient, SWT.WRAP);
		para_name.setText("Parameter Name");
		para_name.setLayoutData(gd);

		para_name_txt = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		para_name_txt.setLayoutData(gd);
		config_parmNameField(para_name_txt);

		Label para_type_label = new Label(sectionClient, SWT.WRAP);
		para_type_label.setText("Parameter Type");
		para_type_label.setLayoutData(gd);

		para_type_txt = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		para_type_txt.setLayoutData(gd);
		config_parmTypeField(para_type_txt);

		
		detail_section.setClient(sectionClient);

	}

	private void createLogicalPplColumns(Composite parent, TableViewer viewer) {
		String[] titles = { "Logical People Group" };
		int[] bounds = { 100 };

		// first column for the first name
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TLogicalPeopleGroup lpg = (TLogicalPeopleGroup) element;
				return lpg.getName();
			}

		});

	}

	private void createPrameterColumns(Composite parent, TableViewer viewer) {

		String[] titles = { "Parameters" };
		int[] bounds = { 100 };

		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TParameter parm = (TParameter) element;
				return parm.getName();
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

	private void logiclPplTableitemSelecter(ISelection selection) {

		IStructuredSelection ssel = (IStructuredSelection) selection;

		if (ssel.size() == 1) {
			selectedLogicalPplGroup = (TLogicalPeopleGroup) ssel
					.getFirstElement();

		} else {
			selectedLogicalPplGroup = (TLogicalPeopleGroup) logicalPplviewer.getElementAt(0);
			logicalPplviewer.getTable().setSelection(0);
			
		}

		parameterViewer.setInput(createPramModle());
		para_table.setSelection(0);

		if (selectedLogicalPplGroup.getParameter()!=null) {
			selectedParameter = selectedLogicalPplGroup.getParameter().get(0);
		} else {
			clearTextBox();
		}

		updateParameters(selectedParameter);

	}

	private void parameterViewerItemSelecter(ISelection selection) {
		IStructuredSelection ssel = (IStructuredSelection) selection;
		// System.out.println("ssel.size()  "+ssel.size());

		if (ssel.size() == 1) {
			selectedParameter = (TParameter) ssel.getFirstElement();

		} else {
			selectedParameter = (TParameter) parameterViewer.getElementAt(0);
			parameterViewer.getTable().setSelection(0);
		}
		// System.out.println(selectedParameter.getName());
		updateParameters(selectedParameter);
	}

	public Object createLogicalPplModle() {
		return humanInteractions.getLogicalPeopleGroups();

	}

	public Object createPramModle() {

		return selectedLogicalPplGroup;

	}

	public void updateParameters(TParameter selectedParameter) {
		if (selectedLogicalPplGroup != null) {

			if(selectedLogicalPplGroup.getName()!=null)
			{
			name_txt.setText(selectedLogicalPplGroup.getName());
			}

		}
		if (selectedParameter != null) {
			if (selectedParameter.getName() != null) {
				para_name_txt.setText(selectedParameter.getName());
			}
			else
			{
				para_name_txt.setText("");
			}
			if (selectedParameter.getType() != null) {
				para_type_txt.setText(selectedParameter.getType().toString());
			}
			else
			{
				para_type_txt.setText("");
			}
		}

	}

	public void clearTextBox() {
		para_name_txt.setText("");
		para_type_txt.setText("");
	}

	// functional area

	private void config_logicalPplGroupNameField(final Text nameTextBox) {

		if (selectedLogicalPplGroup != null) {
			if (selectedLogicalPplGroup.getName() != null) {
				nameTextBox.setText(selectedLogicalPplGroup.getName());
			} else {
				nameTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			}
		} else {
			nameTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
		}

		nameTextBox.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {

				setAttribute_logicalPplGroup(
						htdPackage.eINSTANCE.getTLogicalPeopleGroup_Name(),
						nameTextBox.getText());
			}
		});
	}

	private void config_parmNameField(final Text parmNameTextBox) {
		if (selectedParameter != null) {
			if (selectedParameter.getName() != null) {
				parmNameTextBox.setText(selectedParameter.getName());
			} else {
				parmNameTextBox
						.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);

			}
		} else {
			parmNameTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);

		}

		parmNameTextBox.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {

				setAttribute_parmName(
						htdPackage.eINSTANCE.getTParameter_Name(),
						parmNameTextBox.getText());
			}
		});
	}

	private void config_parmTypeField(final Text parmTypeTextBox) {
		if (selectedParameter != null) {
			if (selectedParameter.getType() != null) {
				parmTypeTextBox.setText(selectedParameter.getType().toString());
			} else {
				parmTypeTextBox
						.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			}
		} else {
			parmTypeTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
		}
		

		parmTypeTextBox.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {

				setAttribute_parmType(htdPackage.eINSTANCE.getTParameter_Type(), new QName(parmTypeTextBox.getText()));

			}
		});
	}

	private void setAttribute_logicalPplGroup(
			EAttribute tTaskInterface_Attribute, String text) {

		Command setAttribCommand = SetCommand.create(domain,
				selectedLogicalPplGroup, tTaskInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);

		} else {
			System.out.println("can't modify Attribute: "
					+ tTaskInterface_Attribute.getName());
		}
	}

	private void setAttribute_parmName(EAttribute tTaskInterface_Attribute,
			String text) {
		Command setAttribCommand = SetCommand.create(domain, selectedParameter,
				tTaskInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);

		} else {
			System.out.println("can't modify Attribute: "
					+ tTaskInterface_Attribute.getName());
		}

	}

	private void setAttribute_parmType(EAttribute tTaskInterface_Attribute,
			QName text) {
		Command setAttribCommand = SetCommand.create(domain, selectedParameter,
				tTaskInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);

		} else {
			System.out.println("can't modify Attribute: "
					+ tTaskInterface_Attribute.getName());
		}
	}

	private void checkAvailability_LogicalPeopleGroup() {

		if (humanInteractions.getLogicalPeopleGroups() == null) {
			// Error message

		} else {
			selectedLogicalPplGroup = humanInteractions
					.getLogicalPeopleGroups().getLogicalPeopleGroup().get(0);
		}

	}

	private void checkAvailability_Parameter() {
		if(humanInteractions.getLogicalPeopleGroups() == null){
			//Error Message
		
		} else {
			selectedParameter = humanInteractions.getLogicalPeopleGroups()
					.getLogicalPeopleGroup().get(0).getParameter().get(0);
		}
	}
	
	public void updatePeopleAssignmentTable()
	{
		if(logicalPplviewer!=null)
		{
		logicalPplviewer.setInput(createLogicalPplModle());
		}
	}
	
	public void updateParameterTable()
	{
		if(parameterViewer!=null)
		{
		parameterViewer.setInput(createPramModle());
		}
	}
	

	public void setResourceChanged(boolean value) {
		this.isResourceChanged = value;
	}

	public boolean isResourceChanged() {
		return this.isResourceChanged;
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		//isResourceChanged = true;
		
	}

}
