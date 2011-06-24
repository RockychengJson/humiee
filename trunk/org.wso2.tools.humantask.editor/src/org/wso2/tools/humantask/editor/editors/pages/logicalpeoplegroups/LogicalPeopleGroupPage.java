package org.wso2.tools.humantask.editor.editors.pages.logicalpeoplegroups;

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
import org.eclipse.ui.forms.SectionPart;

import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLogicalPeopleGroup;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLogicalPeopleGroups;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TParameter;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;
import org.wso2.tools.humantask.editor.editors.base.util.EMFObjectHandleUtil;



public class LogicalPeopleGroupPage extends FormPage {

	protected HTMultiPageEditor editor;
	protected EditingDomain domain;
	protected THumanInteractions  humanInteractions;
	//protected TLogicalPeopleGroups logicalPeopleGroups;
	protected FormToolkit toolkit;
	protected ComposedAdapterFactory adapterFactory;
	protected String pageTitle;
	
	private ScrolledForm form;
	private TableViewer logicalPplviewer;
	private TableViewer parameterViewer;
	private Table para_table;
	
	private TLogicalPeopleGroup selectedLogicalPplGroup;
	private TParameter selectedParameter;
	
	
	private Text name_txt;
	private Text para_name_txt;
	private Text para_type_txt;
	
	private boolean isResourceChanged = false;
	
	
	
	
	public LogicalPeopleGroupPage(HTMultiPageEditor editor,THumanInteractions  humanInteractions){
		super(editor, "HTLP", "Logical People Groups");
		this.pageTitle = super.getTitle();
		this.editor = editor;
		this.domain = this.editor.getEditingDomain();
		this.adapterFactory = editor.getAdapterFactory();
		this.humanInteractions = humanInteractions;
		//this.logicalPeopleGroups = logicalPeopleGroups;
		
		//mBlock = new LogicalPeopleGroupMasterBlock(this, editor, this.humanInteractions);
	}
	
	
	public String getTitle() {
		return this.pageTitle;
	}
	
	/*
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
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

	
	
	private void createLogicalPeopleTableSection(Composite parent){
		
		
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setText("Logical People Groups");
		section.setDescription("add later");
		section.marginWidth = 10;
		section.marginHeight = 5;
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.FILL_BOTH);
		section.setLayoutData(sectiondata);
		
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		toolkit.paintBordersFor(client);
		section.setClient(client);

		
		logicalPplviewer = new TableViewer(client, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		
		createLogicalPplColumns(client, logicalPplviewer);
		
		Table grp_table = logicalPplviewer.getTable();

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;

		grp_table.setLayoutData(gd);
		grp_table.setHeaderVisible(true);
		grp_table.setLinesVisible(true);

		// configure the selection changed listener to this section part for
		// notify other sections

		logicalPplviewer.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						logiclPplTableitemSelecter(event.getSelection());

					}
				});

		logicalPplviewer
				.setContentProvider(new LogicalPeopleGroupTableContentProvider());
		logicalPplviewer.setInput(createLogicalPplModle());
		
		grp_table.setSelection(0);

		Button add_btn = toolkit.createButton(client, "Add", SWT.PUSH); 
		GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(btn_gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

				AddLogicalPeopleGroupWizard wizard = new AddLogicalPeopleGroupWizard();
				WizardDialog wizardDialog = new WizardDialog(Display
						.getCurrent().getActiveShell(), wizard);
				wizardDialog.create();
				wizardDialog.open();

			}
		});
		//createPramTableSection(managedForm, parent);

		
	}
	
	
	private void createPrameterTableSection(Composite parent){
		

		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setText("Logical People Groups");
		section.setDescription("add later");
		section.marginWidth = 10;
		section.marginHeight = 5;
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.FILL_BOTH);
		section.setLayoutData(sectiondata);
		
		
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		toolkit.paintBordersFor(client);
		section.setClient(client);
		
		parameterViewer = new TableViewer(client, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
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
		parameterViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				//ISelection is=event.getSelection();
				//System.out.println(is.toString());
				parameterViewerItemSelecter(event.getSelection());
			}
		});

	}
	
	
	private void createOverallDetailSection(Composite parent){
		final  Section detail_section = toolkit.createSection(parent, Section.DESCRIPTION |
				Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		 detail_section.setText("Genaral Information");
		 detail_section.setDescription("add later");
		
		 detail_section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				if( detail_section.getParent() instanceof ScrolledForm){
					ScrolledForm form =(ScrolledForm) detail_section.getParent();
					form.reflow(true);
				}
			}
		});
		
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_BOTH); 
		 detail_section.setLayoutData(sectiondata);
		
		Composite sectionClient = toolkit.createComposite( detail_section);
		GridLayout layout = new GridLayout(); // layout for the sectionClient
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;
		
		sectionClient.setLayout(layout);
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		gd.horizontalSpan =1;
		
		Label name_lb = new Label(sectionClient, SWT.WRAP);
		name_lb.setText("Name");
		name_lb.setLayoutData(gd);
		
		name_txt = new Text(sectionClient, SWT.SINGLE|SWT.BORDER);
		name_txt.setLayoutData(gd);
		//config_logicalPplGroupNameField(name_txt);
		//Label doc_label = new Label(sectionClient, SWT.WRAP);
		//doc_label.setText("Documentation");
		//doc_label.setLayoutData(gd);
		
	   // doc_txt = new Text(sectionClient,SWT.WRAP|SWT.MULTI|SWT.BORDER);
	   // doc_txt.setLayoutData(gd);
		
	   //Label lang_label = new Label(sectionClient, SWT.WRAP);
	   //lang_label.setText("Language");
	   //lang_label.setLayoutData(gd);
		
	   // lang_txt = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
	   // lang_txt.setLayoutData(gd);
		
		Label para_name = new Label(sectionClient, SWT.WRAP);
		para_name.setText("Parameter Name");
		para_name.setLayoutData(gd);
		
		para_name_txt = new Text(sectionClient, SWT.SINGLE|SWT.BORDER);
		para_name_txt.setLayoutData(gd);
		
		Label para_type_label = new Label(sectionClient, SWT.WRAP);
		para_type_label.setText("Parameter Type");
		para_type_label.setLayoutData(gd);
		
		para_type_txt = new Text(sectionClient, SWT.SINGLE|SWT.BORDER);
		para_type_txt.setLayoutData(gd);
		
		
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
			selectedLogicalPplGroup = (TLogicalPeopleGroup) ssel.getFirstElement();

		} else {
			selectedLogicalPplGroup = null;
		}
	
		parameterViewer.setInput(createPramModle());
		para_table.setSelection(0);
		
		if(selectedLogicalPplGroup.getParameter().size()!=0)
		{
			selectedParameter=selectedLogicalPplGroup.getParameter().get(0);
		}
		else
		{
			clearTextBox();
		}
		
		updateParameter(selectedParameter);
	
	}
	
	
	private void parameterViewerItemSelecter(ISelection selection)
	{
		IStructuredSelection ssel = (IStructuredSelection) selection;
		//System.out.println("ssel.size()  "+ssel.size());
		
		if (ssel.size() == 1) {
			selectedParameter = (TParameter) ssel.getFirstElement();
			
		} else {
			selectedParameter = null;
		}
		//System.out.println(selectedParameter.getName());
		updateParameter(selectedParameter);
	}
	
	public Object createLogicalPplModle() {
		return humanInteractions.getLogicalPeopleGroups();

	}
	
	public Object createPramModle() {

		return selectedLogicalPplGroup;

	}
	
	
	
	
private void config_logicalPplGroupNameField(final Text nameTextBox){
		
		if(selectedLogicalPplGroup.getName() != null)
		{
			nameTextBox.setText(selectedLogicalPplGroup.getName());
		}
		else
		{
			nameTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
		}
		
		nameTextBox.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				
				setAttribute_logicalPplGroup(htdPackage.eINSTANCE.getTLogicalPeopleGroup_Name(), nameTextBox.getText());
			}
		});
	}
private void setAttribute_logicalPplGroup(EAttribute tTaskInterface_Attribute, String text) {
	Command setAttribCommand = SetCommand.create(domain,selectedLogicalPplGroup, tTaskInterface_Attribute, text);

	if (setAttribCommand.canExecute()) {
		domain.getCommandStack().execute(setAttribCommand);
		
	} else {
		System.out.println("can't modify Attribute: " + tTaskInterface_Attribute.getName());
	}
}

public void updateParameter(TParameter selectedParameter) {
	if (selectedLogicalPplGroup != null) {

		name_txt.setText(selectedLogicalPplGroup.getName());

	}
	if (selectedParameter != null) {
		para_name_txt.setText(selectedParameter.getName());
		para_type_txt.setText(selectedParameter.getType().toString());
	}
	
}

private void update() {
	if (selectedLogicalPplGroup!= null) {
		
		name_txt.setText(selectedLogicalPplGroup.getName());
	
	} else {
		System.out.println("INPUT NULL");
	}
}

public void clearTextBox()
{
	para_name_txt.setText("");
	para_type_txt.setText("");
}

	
	
	
	private void checkAvailability_LogicalPeopleGroup() {
		if (humanInteractions.getLogicalPeopleGroups() == null) {
			// Error message

		} else {
			selectedLogicalPplGroup = humanInteractions
					.getLogicalPeopleGroups().getLogicalPeopleGroup().get(0);
		}

	}
	
	
	
	private void checkAvailability_Parameter(){
		
		if (humanInteractions.getLogicalPeopleGroups().getLogicalPeopleGroup().get(0).getParameter() != null){
			// Error message
		}
		else{
			selectedParameter = humanInteractions.getLogicalPeopleGroups().
									getLogicalPeopleGroup().get(0).getParameter().get(0);
		}
	}
	
	public void setResourceChanged(boolean value) {
		this.isResourceChanged = value;
	}

	public boolean isResourceChanged() {
		return this.isResourceChanged;
	}

	
}
