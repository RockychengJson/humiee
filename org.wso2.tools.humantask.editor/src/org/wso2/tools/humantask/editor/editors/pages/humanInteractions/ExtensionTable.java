package org.wso2.tools.humantask.editor.editors.pages.humanInteractions;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ArrayContentProvider;
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
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExtension;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExtensions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTasks;
import org.wso2.tools.humantask.editor.editors.pages.task.TaskPage;

public class ExtensionTable {
	
	
	protected FormToolkit toolkit;
	private CTabFolder tabFolder;
	private ScrolledForm form;
	private TExtensions extensions;
	HumanInteractionsPage humanInteractionPage;
	EditingDomain domain;
	THumanInteractions humanInteractions;
	TableViewer viewer;
	//protected TTasks tasks;	
	//TaskPage taskPage;
	
	ExtensionTable(HumanInteractionsPage humanInteractionPage ,FormToolkit toolkit,CTabFolder tabFolder,ScrolledForm form,TExtensions extensions,EditingDomain domain,THumanInteractions humanInteractions)
	{
	this.toolkit=toolkit;
	this.tabFolder=tabFolder;
	this.form=form;
	this.extensions=extensions;
	this.humanInteractionPage=humanInteractionPage;
	
	}

	 Section createExtentionInfoSection(final IManagedForm managedForm) {

		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText("Extention Properties"); // Properties
		section.setDescription("Extention configataions going here");
		GridData sectiondata = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(sectiondata);
		
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		
		client.setLayout(layout);

		// Table t = toolkit.createTable(client, SWT.NULL);
		// t.setLayoutData(gd);
		
		toolkit.paintBordersFor(client);
		section.setClient(client);


		 viewer = new TableViewer(client, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(client, viewer);
		final Table table = viewer.getTable();
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 10;
		gd.heightHint = 20;
		gd.widthHint = 100;
		
		table.setLayoutData(gd);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		
		// configure the selection changed listener to this section part for notify other sections
		
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				//managedForm.fireSelectionChanged(spart, event.getSelection());
				//System.out.println(event.getSelection().toString());
			}
		});
		
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(createModle());
		Button add_btn = toolkit.createButton(client, "Add", SWT.PUSH); //$NON-NLS-1$
		GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(btn_gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				HIExtentionWizard wizard = new HIExtentionWizard(humanInteractions, domain,viewer);
				WizardDialog wizardDialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
				wizardDialog.create();
				wizardDialog.open();

			}
		});

		section.setClient(client);

		return section;
	}

	public Object createModle() {
		return extensions;		
	}
	
	
	private void createColumns(Composite parent, TableViewer viewer) {
		String[] titles = { "Extension" };
		int[] bounds = { 100 };

		// first column for the first name
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				
				TExtension extension=(TExtension)element;
				 return extension.getNamespace();
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
