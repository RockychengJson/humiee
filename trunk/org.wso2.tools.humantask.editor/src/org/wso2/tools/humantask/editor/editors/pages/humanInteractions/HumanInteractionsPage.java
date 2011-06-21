package org.wso2.tools.humantask.editor.editors.pages.humanInteractions;

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
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TBoolean;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExtension;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExtensions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TImport;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;
import org.wso2.tools.humantask.editor.editors.base.util.EMFObjectHandleUtil;

public class HumanInteractionsPage extends FormPage implements
		IResourceChangeListener {

	protected HTMultiPageEditor editor;
	protected EditingDomain domain;
	protected THumanInteractions humanInteractions;
	protected FormToolkit toolkit;
	protected  ComposedAdapterFactory adaptorFactory;
	protected String pageTitle;
	private HumanInteractionsPage hi_page;
	private boolean isResourceChanged;
	private CTabFolder tabFolder;
	private TabContent[] temp;
	private ScrolledForm form;
	private TExtensions extensions;
	private TExtension selectedItem_extension;
	private TImport selectedItem_import;
	private Text nsp_txt_extension;
	private Combo must_und_extension;
	private Text nsp_txt_import;
	private Text location_txt;
	Text im_type_txt;
	
	protected TableViewer viewer_extension;
	private TableViewer viewer_import;

	private int tempindex;
	private boolean isFirst = true;

	public HumanInteractionsPage(HTMultiPageEditor editor,
			THumanInteractions humanInteractions) {
		super(editor, "HTHI", "Human Interactions");

		this.pageTitle = super.getTitle();
		this.editor = editor;
		this.domain = this.editor.getEditingDomain();
		this.adaptorFactory = editor.getAdapterFactory();
		this.humanInteractions = humanInteractions;

	}

	public String getTitle() {
		return this.pageTitle;
	}

	protected void createFormContent(IManagedForm managedform) {

		if (!domain.getResourceSet().getResources().isEmpty()) {

			toolkit = managedform.getToolkit();
			form = managedform.getForm();
			form.setText("Human Task");

			GridLayout layout = new GridLayout(); // layout for the form body
			layout.numColumns = 1;
			layout.marginWidth = 10;
			form.getBody().setLayout(layout);

			tabFolder = new CTabFolder(form.getBody(), SWT.FLAT | SWT.TOP);
			toolkit.adapt(tabFolder, true, true);

			GridData gd = new GridData(GridData.FILL_HORIZONTAL); // grid data
																	// info for
																	// the
																	// tabFolder
			gd.heightHint = 0;
			tabFolder.setLayoutData(gd);

			Color selectedColor = toolkit.getColors().getColor(
					FormColors.SEPARATOR);
			tabFolder.setSelectionBackground(new Color[] { selectedColor,
					toolkit.getColors().getBackground() }, new int[] { 50 });
			toolkit.paintBordersFor(tabFolder);
			
			checkAvailability_extensions();
			checkAvailability_import();

			createGenarelInfoTab(toolkit, form);
			createExtentionsTab(toolkit, form);
			createImportsTab(toolkit, form);

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
		TabContent section1 = new TabContent(createGenaralInfoSection(toolkit,
				form));

		TabContent[] sectionArray = new TabContent[1];
		sectionArray[0] = section1;
		item.setText("Human Interaction");
		item.setData(sectionArray);

	}

	private void createExtentionsTab(FormToolkit toolkit, ScrolledForm form) {
		CTabItem item = new CTabItem(tabFolder, SWT.NULL);
		TabContent section1 = new TabContent(createExtentionInfoSection(
				toolkit, form));
		TabContent section2 = new TabContent(createExtentionDetailSection(toolkit, form));
		
		TabContent[] sectionArray = new TabContent[2];
		sectionArray[0] = section1;
		sectionArray[1] = section2;
		
		item.setText("Extentions");
		item.setData(sectionArray);
	}

	private void createImportsTab(FormToolkit toolkit, ScrolledForm form) {
		CTabItem item = new CTabItem(tabFolder, SWT.NULL);
		TabContent section1 = new TabContent(createImportInfoSection(toolkit,
				form));
		TabContent section2 = new TabContent(createImportDetailSection(toolkit, form));
		
		TabContent[] sectionArray = new TabContent[2];
		
		sectionArray[0] = section1;
		sectionArray[1] = section2;
		
		item.setText("Imports");
		item.setData(sectionArray);
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
		section.setText("Globale Properties"); 
		section.setDescription("Globle configataions going here");
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);

		GridData gd= new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL
				| GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 1;
		
		Label tnslb = new Label(sectionClient, SWT.WRAP);
		tnslb.setText("Target Name Space");
		tnslb.setLayoutData(gd);

		Text tns_txt = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		tns_txt.setLayoutData(gd);
		configGeneralInfoSection_targetNamespace(tns_txt);

		Label exp_lang_lb = new Label(sectionClient, SWT.WRAP);
		exp_lang_lb.setText("Expression Language");
		exp_lang_lb.setLayoutData(gd);

		Text exp_txt = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		exp_txt.setLayoutData(gd);
		configGeneralInfoSection_expressionLanguage(exp_txt);

		Label qry_lang_lb = new Label(sectionClient, SWT.WRAP);
		qry_lang_lb.setText("Query Language");
		qry_lang_lb.setLayoutData(gd);

		Text qry_txt = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		qry_txt.setLayoutData(gd);
		configGeneralInfoSection_queryLanguage(qry_txt);

		section.setClient(sectionClient);

		return section;

	}

	private Section createExtentionInfoSection(FormToolkit toolkit,
			final ScrolledForm form) {

		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText("Extention Properties"); 
		section.setDescription("Extention configataions going here");
		GridData sectiondata = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(sectiondata);
		
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		
		client.setLayout(layout);
		
		toolkit.paintBordersFor(client);
		
		 viewer_extension = new TableViewer(client, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(client, viewer_extension);
		final Table table = viewer_extension.getTable();
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 10;
		gd.heightHint = 20;
		gd.widthHint = 100;
		
		table.setLayoutData(gd);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		// configure the selection changed listener to this section part for notify other sections
		
		viewer_extension.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				itemSelecter(event.getSelection());
				
			}
		});
		
		viewer_extension.setContentProvider(new ExtensionContentProvider());
		viewer_extension.setInput(createModle());
	    
		table.setSelection(0);
		Button add_btn = toolkit.createButton(client, "Add", SWT.PUSH); //$NON-NLS-1$
		GridData btn_gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(btn_gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				HIExtentionWizard wizard = new HIExtentionWizard(humanInteractions, domain,viewer_extension);
				WizardDialog wizardDialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
				wizardDialog.create();
				wizardDialog.open();

			}
		});

		section.setClient(client);

		return section;
	}
	
	private Section createExtentionDetailSection(FormToolkit toolkit,
			final ScrolledForm form){
		
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText("Extention Details"); 
		section.setDescription("Extention releven details here");
		GridData sectiondata = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;
		sectionClient.setLayout(layout);
		
		Label name_space_label = new Label(sectionClient, SWT.WRAP);
		name_space_label.setText("Namespace");
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);
		gd.horizontalSpan =1;
		name_space_label.setLayoutData(gd);
		
		nsp_txt_extension= new Text(sectionClient, SWT.SINGLE|SWT.BORDER);
		nsp_txt_extension.setLayoutData(gd);
		configExtensionDetailSection_namespace(nsp_txt_extension);
		
		Label must_und_lb = new Label(sectionClient, SWT.WRAP);
		must_und_lb.setText("Must Understand");
		
		must_und_extension= new Combo(sectionClient, SWT.READ_ONLY);
		must_und_extension.add("No",0);
		must_und_extension.add("Yes", 1);
		must_und_extension.setLayoutData(gd);
		must_und_extension.select(1);
		configExtensionDetailSection_mustUnderstand(must_und_extension);

		section.setClient(sectionClient);

		return section;
	}

	private Section createImportInfoSection(FormToolkit toolkit,
			final ScrolledForm form) {

		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText("Import Properties"); 
		section.setDescription("Import configataions going here");
		GridData sectiondata = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		sectionClient.setLayout(layout);		
		toolkit.paintBordersFor(sectionClient);

		 viewer_import = new TableViewer(sectionClient, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns_import(sectionClient, viewer_import);
		final Table table = viewer_import.getTable();
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 10;
		gd.heightHint = 20;
		gd.widthHint = 100;
		table.setLayoutData(gd);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		viewer_import.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				itemSelecter_import(event.getSelection());
				
			}
		});

		viewer_import.setContentProvider(new ImportContentProvider());
		
		viewer_import.setInput(createModle_import()); 
		
		table.setSelection(0);
		
		Button add_btn = toolkit.createButton(sectionClient, "Add", SWT.PUSH); 
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		add_btn.setLayoutData(gd);

		add_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

				HIImportWizard wizard = new HIImportWizard( humanInteractions, domain,viewer_import);

				WizardDialog wizardDialog = new WizardDialog(Display
						.getCurrent().getActiveShell(), wizard);
				wizardDialog.create();
				wizardDialog.open();

			}
		});

		section.setClient(sectionClient);

		return section;
	}
	
	private Section createImportDetailSection(FormToolkit toolkit,
			final ScrolledForm form){
		Section section = toolkit.createSection(tabFolder, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText("Import Details"); 
		section.setDescription("Import relevent details here");
		GridData sectiondata = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;
		sectionClient.setLayout(layout);
		
		Label name_space_label = new Label(sectionClient, SWT.WRAP);
		name_space_label.setText("Namespace");
		
		GridData gd= new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL
				| GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 1;
		name_space_label.setLayoutData(gd);
		
		nsp_txt_import= new Text(sectionClient, SWT.SINGLE|SWT.BORDER);
		nsp_txt_import.setLayoutData(gd);
		configImportDetailSection_namespace(nsp_txt_import);
		
		Label location_lb = new Label(sectionClient, SWT.WRAP);
		location_lb.setText("Location");
		location_lb.setLayoutData(gd);
		
		location_txt = new Text(sectionClient, SWT.SINGLE | SWT.BORDER);
		location_txt.setLayoutData(gd);
		configImportDetailSection_location(location_txt);
		
		Label im_type_lb = new Label(sectionClient, SWT.WRAP);
		im_type_lb.setText("Import Type");
		im_type_lb.setLayoutData(gd);
		
		im_type_txt = new Text(sectionClient, SWT.SINGLE| SWT.BORDER);
		im_type_txt.setLayoutData(gd);
		configImportDetailSection_importType(im_type_txt);
		
		section.setClient(sectionClient);
		return section;
	}
	
	private void configGeneralInfoSection_expressionLanguage(final Text expressionLanguageTextBox)
	{
		if(humanInteractions.getExpressionLanguage()!=null)
		{
			expressionLanguageTextBox.setText(humanInteractions.getExpressionLanguage());
			} else {
				expressionLanguageTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			}
		
		expressionLanguageTextBox.addModifyListener(new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			//validateInput();
			setAttribute(htdPackage.eINSTANCE.getTHumanInteractions_ExpressionLanguage(), expressionLanguageTextBox.getText());
		}
	});
	
		
	}
	
	private void configGeneralInfoSection_targetNamespace(final Text targetNamespaceTextBox)
	{
		if(humanInteractions.getTargetNamespace()!=null)
		{
			targetNamespaceTextBox.setText(humanInteractions.getTargetNamespace());
			} else {
				targetNamespaceTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			}
		
		targetNamespaceTextBox.addModifyListener(new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			//validateInput();
			setAttribute(htdPackage.eINSTANCE.getTHumanInteractions_TargetNamespace(), targetNamespaceTextBox.getText());
		}
	});
	
		
	}
	
	private void configGeneralInfoSection_queryLanguage(final Text queryLanguageTextBox)
	{
		if(humanInteractions.getQueryLanguage()!=null)
		{
			queryLanguageTextBox.setText(humanInteractions.getQueryLanguage());
			} else {
				queryLanguageTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
			}
		
		queryLanguageTextBox.addModifyListener(new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			//validateInput();
			setAttribute(htdPackage.eINSTANCE.getTHumanInteractions_QueryLanguage(), queryLanguageTextBox.getText());
		}
	});
	
		
	}

	
	private void setAttribute(EAttribute tTaskInterface_Attribute, String text) {
		Command setAttribCommand = SetCommand.create(domain, humanInteractions, tTaskInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			System.out.println(humanInteractions.getAnyAttribute().size());
		} else {
			System.out.println("can't modify Attribute: " + tTaskInterface_Attribute.getName());
		}
	}

	public boolean isResourceChanged() {
		return this.isResourceChanged;
	}

	public void setResourceChanged(boolean value) {
		this.isResourceChanged = value;
	}

	class TabContent {

		Section content;

		public TabContent(Section content) {
			this.content = content;
		}

	}
	private void createColumns(Composite parent, TableViewer viewer) {
		String[] titles = { "Extension" };
		int[] bounds = { 100};

		// first column 
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TExtension p = (TExtension) element;				
				return p.getNamespace();
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

	public Object createModle() {		
		return humanInteractions.getExtensions();
	}
	@Override
	public void resourceChanged(IResourceChangeEvent arg0) {
		// TODO Auto-generated method stub

	}
	 void itemSelecter(ISelection selection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			
			if (ssel.size() == 1) {
				selectedItem_extension = (TExtension) ssel.getFirstElement();
				//System.out.println(selectedItem.getName());
			} else {
				selectedItem_extension = null;
			}
			update();
		}
		
		private void update() {
			if(selectedItem_extension.getNamespace()!=null)
			{
				nsp_txt_extension.setText(selectedItem_extension.getNamespace());
				}
			
			 if(selectedItem_extension.getMustUnderstand()!=null)
			 {
				 if(selectedItem_extension.getMustUnderstand().getValue()==1)
				 {					 
					 must_und_extension.select(0);
				 }
				 else
				 {
					 must_und_extension.select(1);
				 }
				
			 }
			 
			 
		}
		private void checkAvailability_extensions()
		{
			if(humanInteractions.getExtensions()==null)
			{
				//Error message
				
			}
			else
			{
				selectedItem_extension=humanInteractions.getExtensions().getExtension().get(0);
			}
			
		}

		
	
			
			private void configExtensionDetailSection_namespace(final Text namespaceTextBox)
			{
				if(humanInteractions.getExtensions()!=null)
				{
					namespaceTextBox.setText(humanInteractions.getExtensions().getExtension().get(0).getNamespace());
					} else {
						namespaceTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
					}
				
				namespaceTextBox.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					//validateInput();
					setAttribute_extension(htdPackage.eINSTANCE.getTExtension_Namespace(), namespaceTextBox.getText());
				}
			});
			
				
			}
			
			private void configExtensionDetailSection_mustUnderstand(final Combo mustUnderstansComboBox)
			{
				if(humanInteractions.getExtensions()!=null)
				{				
					mustUnderstansComboBox.select((humanInteractions.getExtensions().getExtension().get(0).getMustUnderstand().getValue()==0)?1:0);
					} else {
						//mustUnderstansComboBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
					}
				
				mustUnderstansComboBox.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					//validateInput();
					setAttribute_extensionMustUnderstand(htdPackage.eINSTANCE.getTExtension_MustUnderstand(), (mustUnderstansComboBox.getSelectionIndex()==0)?TBoolean.get(1):TBoolean.get(0));
				}
			});
			
				
			}
			
			private void setAttribute_extension(EAttribute tExtension_Attribute, String text) {
				Command setAttribCommand = SetCommand.create(domain, selectedItem_extension, tExtension_Attribute, text);

				if (setAttribCommand.canExecute()) {
					domain.getCommandStack().execute(setAttribCommand);
					//System.out.println(humanInteractions.getAnyAttribute().size());
				} else {
					System.out.println("can't modify Attribute: " + tExtension_Attribute.getName());
				}
			}
			
			private void setAttribute_extensionMustUnderstand(EAttribute tExtension_Attribute, TBoolean selection) {
				Command setAttribCommand = SetCommand.create(domain, selectedItem_extension, tExtension_Attribute, selection);

				if (setAttribCommand.canExecute()) {
					domain.getCommandStack().execute(setAttribCommand);
					//System.out.println(humanInteractions.getAnyAttribute().size());
				} else {
					System.out.println("can't modify Attribute: " + tExtension_Attribute.getName());
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//import
			
			private void createColumns_import(Composite parent, TableViewer viewer) {
				
				String[] titles = { "Import" };
				int[] bounds = { 100};

				// first column for the first name
				TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
						bounds[0]);
				col.setLabelProvider(new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						TImport p = (TImport) element;						
						return p.getNamespace();
					}
					
				});
			

			}
			

			private void checkAvailability_import()
			{
				if(humanInteractions.getImport()==null)
				{
					//Error message
					
				}
				else
				{
					selectedItem_import=humanInteractions.getImport().get(0);
				}
				
			}
			
			 void itemSelecter_import(ISelection selection) {
					IStructuredSelection ssel = (IStructuredSelection) selection;
					
					if (ssel.size() == 1) {
						selectedItem_import = (TImport) ssel.getFirstElement();
						//System.out.println(selectedItem.getName());
					} else {
						selectedItem_import = null;
					}
					update_import();
				}
				
				private void update_import() {			
						nsp_txt_import.setText(selectedItem_import.getNamespace());						
						location_txt.setText(selectedItem_import.getLocation());
						im_type_txt.setText(selectedItem_import.getImportType());	
						
					 
				}
				public Object createModle_import() {		
					return humanInteractions.getImport();
				}
			
			
			private void configImportDetailSection_namespace(final Text namespaceTextBox)
			{
				if(humanInteractions.getImport()!=null)
				{
					namespaceTextBox.setText(humanInteractions.getImport().get(0).getNamespace());
					} else {
						namespaceTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
					}
				
				namespaceTextBox.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					//validateInput();
					setAttribute_import(htdPackage.eINSTANCE.getTImport_Namespace(), namespaceTextBox.getText());
				}
			});
			
				
			}		
		
			
			private void configImportDetailSection_location(final Text locationTextBox)
			{
				if(humanInteractions.getImport()!=null)
				{
					locationTextBox.setText(humanInteractions.getImport().get(0).getLocation());
					} else {
						locationTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
					}
				
				locationTextBox.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					//validateInput();
					setAttribute_import(htdPackage.eINSTANCE.getTImport_Location(), locationTextBox.getText());
				}
			});
			
				
			}
			
	
			
			private void configImportDetailSection_importType(final Text importTypeTextBox)
			{
				if(humanInteractions.getImport()!=null)
				{
					importTypeTextBox.setText(humanInteractions.getImport().get(0).getImportType());
					} else {
						importTypeTextBox.setText(EMFObjectHandleUtil.RESOURCE_NOT_AVAILABLE);
					}
				
				importTypeTextBox.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					//validateInput();
					setAttribute_import(htdPackage.eINSTANCE.getTImport_ImportType(), importTypeTextBox.getText());
				}
			});
			
				
			}
			
			private void setAttribute_import(EAttribute tImport_Attribute, String text) {
				Command setAttribCommand = SetCommand.create(domain, selectedItem_import, tImport_Attribute, text);

				if (setAttribCommand.canExecute()) {
					domain.getCommandStack().execute(setAttribCommand);
					//System.out.println(humanInteractions.getAnyAttribute().size());
				} else {
					System.out.println("can't modify Attribute: " + tImport_Attribute.getName());
				}
			}
	
		
}