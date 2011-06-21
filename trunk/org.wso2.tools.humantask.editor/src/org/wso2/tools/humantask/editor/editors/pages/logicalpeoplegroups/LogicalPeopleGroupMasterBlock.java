package org.wso2.tools.humantask.editor.editors.pages.logicalpeoplegroups;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLogicalPeopleGroup;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TParameter;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TLogicalPeopleGroupImpl;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;
import org.wso2.tools.humantask.editor.editors.pages.task.HumanRole;

public class LogicalPeopleGroupMasterBlock extends MasterDetailsBlock {

	private EditingDomain domain;
	private THumanInteractions humanInteractions;
	private TLogicalPeopleGroup selectedLogicalPplGroup;
	private TParameter selectedParameter;
	private LogicalPeopleGroupDetailPage detailPage;
	private Table para_table;
	private TableViewer parameterViewer;
	private TableViewer logicalPplviewer;
	
	public LogicalPeopleGroupMasterBlock(FormPage page,
			HTMultiPageEditor editor, THumanInteractions humanInteractions) {
		
		this.domain = editor.getEditingDomain();
		this.humanInteractions = humanInteractions;
		detailPage = new LogicalPeopleGroupDetailPage(this.domain,this.humanInteractions);

	}

	@Override
	protected void createMasterPart(final IManagedForm managedForm,
			Composite parent) {
		FormToolkit toolkit = managedForm.getToolkit();
		checkAvailability_LogicalPeopleGroup();
		checkAvailability_Parameter();
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setText("Logical People Groups");
		section.setDescription("add later");
		section.marginWidth = 10;
		section.marginHeight = 5;

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		toolkit.paintBordersFor(client);
		section.setClient(client);

		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);

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

		logicalPplviewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						managedForm.fireSelectionChanged(spart,
								event.getSelection());
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

		parameterViewer = new TableViewer(client, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createPrameterColumns(client, parameterViewer);

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
		detailPage.updateParameter(selectedParameter);
	}
	

	
	@Override
	protected void registerPages(DetailsPart detailsPart) {
		
		detailsPart.registerPage(TLogicalPeopleGroupImpl.class,
				detailPage);

	}

	
	
	@Override
	protected void createToolBarActions(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		Action haction = new Action("hor", Action.AS_RADIO_BUTTON) { //$NON-NLS-1$
			public void run() {
				sashForm.setOrientation(SWT.HORIZONTAL);
				form.reflow(true);
			}
		};

		haction.setChecked(true);
		haction.setToolTipText("Horizontal"); //$NON-NLS-1$
		haction.setText("Horizontal");
		// haction.setImageDescriptor(Activator.getImageDescriptor("icons/th_horizontal.gif"));
		Action vaction = new Action("ver", Action.AS_RADIO_BUTTON) { //$NON-NLS-1$
			public void run() {
				sashForm.setOrientation(SWT.VERTICAL);
				form.reflow(true);
			}
		};

		vaction.setChecked(false);
		vaction.setToolTipText("Vertical"); //$NON-NLS-1$
		vaction.setText("Vertical");
		// vaction.setImageDescriptor(Activator.getImageDescriptor("icons/th_vertical.gif"));
		form.getToolBarManager().add(haction);
		form.getToolBarManager().add(vaction);

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

	public Object createLogicalPplModle() {
		return humanInteractions.getLogicalPeopleGroups();

	}

	public Object createPramModle() {

		return selectedLogicalPplGroup;

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
			detailPage.clearTextBox();
		}
		
		detailPage.updateParameter(selectedParameter);
	
		
		
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
	
	

}
