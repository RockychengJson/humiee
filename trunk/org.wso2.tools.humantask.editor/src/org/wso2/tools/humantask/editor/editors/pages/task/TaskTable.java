package org.wso2.tools.humantask.editor.editors.pages.task;

import org.eclipse.emf.edit.domain.EditingDomain;
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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTasks;

public class TaskTable {
	
	private TableViewer viewer;
	protected FormToolkit toolkit;
	private CTabFolder tabFolder;
	private ScrolledForm form;
	protected TTasks tasks;	
	TaskPage taskPage;
	private THumanInteractions humanInteractions;
	private  EditingDomain domain;
	private TTask selectedTask;
	
	TaskTable(THumanInteractions humanInteractions,EditingDomain domain, TaskPage taskPage ,FormToolkit toolkit,CTabFolder tabFolder,ScrolledForm form,TTasks tasks,TTask selectedTask)
	{
	this.toolkit=toolkit;
	this.tabFolder=tabFolder;
	this.form=form;
	this.tasks=tasks;
	this.taskPage=taskPage;
	this.humanInteractions=humanInteractions;
	this.domain=domain;
	this.selectedTask=selectedTask;
	
	}

    //Create TaskTable Section

	 Section createTaskTableSection(final IManagedForm managedForm) {

		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		section.setText("Create Human Task");
		section.setDescription("Description here");
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
		viewer = new TableViewer(sectionClient, SWT.MULTI
				| SWT.BORDER);
		createColumns(sectionClient, viewer);

		final Table table = viewer.getTable();

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan =10;
		gd.heightHint = 20;
		gd.widthHint = 100;

		table.setLayoutData(gd);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//if(table.getSelectionIndex()==-1)
		//{System.out.println(table.getSelectionIndex());}
		//else{System.out.println(table.getSelectionIndex());}

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				taskPage.itemSelecter_TaskTable(event.getSelection(),viewer);

			}
		});

		viewer.setContentProvider(new TaskContentProvider());
		viewer.setInput(createModle());
		table.setSelection(0);
		
		Button add_btn = toolkit.createButton(sectionClient, "Add", SWT.PUSH); //$NON-NLS-1$
		GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(btn_gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				taskPage.checkEnableStateWSDLSection();
				TaskCreatWizard wizard = new TaskCreatWizard(humanInteractions,domain,viewer );
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
				taskPage.deleteTask(viewer,tasks);
				
			

			}
		});
		
		section.setClient(sectionClient);
		return section;

	}

	public Object createModle() {
		return tasks;		
	}
	
	
	private void createColumns(Composite parent, TableViewer viewer) {
		String[] titles = { "Task" };
		int[] bounds = { 100 };

		// first column for the first name
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				
				 TTask task=(TTask)element;
				 return task.getName();
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
	
	
	
	

}
