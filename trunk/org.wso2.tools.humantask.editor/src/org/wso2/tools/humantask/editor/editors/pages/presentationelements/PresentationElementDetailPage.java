package org.wso2.tools.humantask.editor.editors.pages.presentationelements;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.TextActionHandler;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.wso2.tools.humantask.editor.editors.pages.presentationelements.PresentationElementMasterBlock.Person;

public class PresentationElementDetailPage implements IDetailsPage {

	private IManagedForm mform;
	private FormToolkit toolkit;
	private Person input;
	private Text fname;
	private Text lname;
	private Text gname;

	public PresentationElementDetailPage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(IManagedForm mform) {
		this.mform = mform;
		this.toolkit = mform.getToolkit();
	}

	@Override
	public void createContents(Composite parent) {
		GridLayout gl = new GridLayout(1, false);  //layout for the parent
		parent.setLayout(gl);

		Section testsection = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);

		testsection.setText("Parameter Details");
		testsection.setDescription("add later");
		
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);      //layout data for the section
		testsection.setLayoutData(sectiondata);

		Composite sectionClient = toolkit.createComposite(testsection);
		GridLayout layout = new GridLayout(); // layout for the sectionClient
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL); //layout data for the sectionClient members
		
		fname = new Text(sectionClient, SWT.WRAP);
		fname.setLayoutData(gd);

		lname = new Text(sectionClient, SWT.WRAP);
		lname.setLayoutData(gd);

		gname = new Text(sectionClient, SWT.WRAP);
		gname.setLayoutData(gd);
		
		sectionClient.setLayout(layout);

		testsection.setClient(sectionClient);
		//TODO viraj please remove this testsection from the GUI when u gor the idea.
		createGeneralInfoSection(parent);
		
		createSubjectIfoSection(parent);
		
		createDescriptionSection(parent);
		
	}

	private void update() {
		if (input != null) {
			fname.setText(input.getFname());
			lname.setText(input.getLname());
			gname.setText(input.getGender());
		} else {
			System.out.println("INPUT NULL");
		}
	}
	
	private void createGeneralInfoSection(Composite parent){
		
		final  Section info_section = toolkit.createSection(parent, Section.DESCRIPTION |
				Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		info_section.setText("Genaral Information");
		info_section.setDescription("add later");
		
		info_section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				if(info_section.getParent() instanceof ScrolledForm){
					ScrolledForm form =(ScrolledForm)info_section.getParent();
					form.reflow(true);
				}
			}
		});
		
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL); 
		info_section.setLayoutData(sectiondata);
		
		Composite sectionClient = toolkit.createComposite(info_section);
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
		
		Text name_txt = new Text(sectionClient, SWT.SINGLE|SWT.BORDER);
		name_txt.setLayoutData(gd);
		
		Label language_lb = new Label(sectionClient, SWT.WRAP);
		language_lb.setText("Language");
		language_lb.setLayoutData(gd);
		
		Text lang_txt = new Text(sectionClient, SWT.SINGLE |SWT.BORDER);
		lang_txt.setLayoutData(gd);
		
		
		info_section.setClient(sectionClient);
	}

	
	
	private void createSubjectIfoSection(Composite parent){
		final  Section sub_section = toolkit.createSection(parent, Section.DESCRIPTION |
				Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		sub_section.setText("Subject Information");
		sub_section.setDescription("add later");
		
		sub_section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				if(sub_section.getParent() instanceof ScrolledForm){
					ScrolledForm form =(ScrolledForm)sub_section.getParent();
					form.reflow(true);
				}
			}
		});
		
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL); 
		sub_section.setLayoutData(sectiondata);
		
		Composite sectionClient = toolkit.createComposite(sub_section);
		GridLayout layout = new GridLayout(); // layout for the sectionClient
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;
		
		sectionClient.setLayout(layout);
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		gd.horizontalSpan =1;
		
		Label subject_lb = new Label(sectionClient, SWT.WRAP);
		subject_lb.setText("Subject");
		subject_lb.setLayoutData(gd);
		
		Text subject_txt = new Text(sectionClient, SWT.SINGLE|SWT.BORDER);
		 subject_txt.setLayoutData(gd);
		
		Label language_lb = new Label(sectionClient, SWT.WRAP);
		language_lb.setText("Language");
		language_lb.setLayoutData(gd);
		
		Text lang_txt = new Text(sectionClient, SWT.SINGLE |SWT.BORDER);
		lang_txt.setLayoutData(gd);
		
		
		sub_section.setClient(sectionClient);
	}
	
	private void createDescriptionSection(Composite parent){
		final  Section desc_section = toolkit.createSection(parent, Section.DESCRIPTION |
				Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		desc_section.setText("Subject Information");
		desc_section.setDescription("add later");
		
		desc_section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				if(desc_section.getParent() instanceof ScrolledForm){
					ScrolledForm form =(ScrolledForm)desc_section.getParent();
					form.reflow(true);
				}
			}
		});
		
		GridData sectiondata = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL); 
		desc_section.setLayoutData(sectiondata);
		
		Composite sectionClient = toolkit.createComposite(desc_section);
		GridLayout layout = new GridLayout(); // layout for the sectionClient
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 5;
		
		sectionClient.setLayout(layout);
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		gd.horizontalSpan =1;
		
		Label language_lb = new Label(sectionClient, SWT.WRAP);
		language_lb.setText("Language");
		language_lb.setLayoutData(gd);
		
		Text lang_txt = new Text(sectionClient, SWT.SINGLE |SWT.BORDER);
		lang_txt.setLayoutData(gd);
		
		GridData combo_gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		combo_gd.verticalSpan =2;
		Label context_lb = new Label(sectionClient, SWT.WRAP);
		context_lb.setText("Context Type");
		context_lb.setLayoutData(combo_gd);
		
		Combo context_type = new Combo(sectionClient, SWT.READ_ONLY);
		context_type.add("text/plain" , 0);
		context_type.add("text/html", 1);
		context_type.select(0);
		context_type.setLayoutData(combo_gd);
		
		Label dec_lb = new Label(sectionClient, SWT.WRAP);
		dec_lb.setText("Description");
		dec_lb.setLayoutData(combo_gd);
		
		Text desc_txt = new Text(sectionClient, SWT.WRAP|SWT.MULTI|SWT.BORDER|SWT.V_SCROLL);
		desc_txt.setLayoutData(combo_gd);
		
		desc_section.setClient(sectionClient);
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void commit(boolean onSave) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean setFormInput(Object input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isStale() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void refresh() {
		update();

	}

	@Override
	public void selectionChanged(IFormPart part, ISelection selection) {
		// System.out.println("selction chaged fired Ifrom,Iselction");
		IStructuredSelection ssel = (IStructuredSelection) selection;
		if (ssel.size() == 1) {
			input = (Person) ssel.getFirstElement();
		} else {
			input = null;
		}
		update();
	}

}
