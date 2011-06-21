package org.wso2.tools.humantask.editor.editors.pages.presentationelements;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationElements;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;

public class PresentationElementMasterBlock extends MasterDetailsBlock {

	// resource changing mechanism should be here..

	private FormPage page;
	private ComposedAdapterFactory adaptorFactory;
	private EditingDomain domain;
	private TPresentationElements presentationElements;

	public PresentationElementMasterBlock(FormPage page,
			HTMultiPageEditor editor) {
		this.page = page;
		this.adaptorFactory = editor.getAdapterFactory();
		this.domain = editor.getEditingDomain();
		// this.presentationElements =
	}

	@Override
	protected void createMasterPart(final IManagedForm managedForm,
			Composite parent) {
		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setText("Presentation Paramenters"); //$NON-NLS-1$
		section.setDescription("add later"); //$NON-NLS-1$
		section.marginWidth = 10;
		section.marginHeight = 5;

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

		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);

		TableViewer viewer = new TableViewer(client, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(client, viewer);
		final Table table = viewer.getTable();
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		
		table.setLayoutData(gd);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		
		// configure the selection changed listener to this section part for notify other sections
		
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(spart, event.getSelection());
				//System.out.println(event.getSelection().toString());
			}
		});
		
		// viewer.setContentProvider(new
		// AdapterFactoryContentProvider(adaptorFactory));
		// viewer.setLabelProvider(new
		// AdapterFactoryLabelProvider(adaptorFactory));
		
		//TODO viraj this content provider and the input are dummy to understand what is going on you should provide
		//actual one as required. above commented lines will help you
		
		viewer.setContentProvider(new ArrayContentProvider());
		
		viewer.setInput(createModle());
		// viewer.setInput(page.getEditor().getEditorInput());
		
		Button add_btn = toolkit.createButton(client, "Add", SWT.PUSH); //$NON-NLS-1$
		GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(btn_gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				PresentationParameterWizard wizard = new PresentationParameterWizard();
				WizardDialog wizardDialog = new WizardDialog(Display
						.getCurrent().getActiveShell(), wizard);
				wizardDialog.create();
				wizardDialog.open();

			}
		});

	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		detailsPart.registerPage(Person.class,
				new PresentationElementDetailPage());

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
		
		/*haction.setImageDescriptor(new ImageDescriptor() {
			
			@Override
			public ImageData getImageData() {
				return PlatformUI.getWorkbench().getSharedImages().getImage(
		                 ISharedImages.IMG_OBJ_ELEMENT).getImageData();
			}
		});*/
		
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

	class Person {
		private String fname;
		private String lname;
		private String gender;

		Person(String fname, String lname, String gender) {
			this.fname = fname;
			this.lname = lname;
			this.gender = gender;
		}

		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}
	}

	private void createColumns(Composite parent, TableViewer viewer) {
		String[] titles = { "Name", "Type", "Expression" };
		int[] bounds = { 100, 100, 100 };

		// first column for the first name
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return p.getFname();
			}
			
		});
		

		col = createTableViewerColumn(viewer, titles[1], bounds[1]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return p.getLname();
			}
		});

		col = createTableViewerColumn(viewer, titles[2], bounds[2]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				return p.getGender();
			}
		});

	}

	public Object createModle() {
		Person[] parry = { new Person("kalpa", "senananyake", "male"),
				new Person("amith", "shiv", "male"),
				new Person("viraj", "patti", "male") };
		return parry;
	}

}
