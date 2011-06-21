package org.wso2.tools.humantask.editor.editors.pages.logicalpeoplegroups;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLogicalPeopleGroup;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TParameter;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;
import org.wso2.tools.humantask.editor.editors.base.util.EMFObjectHandleUtil;



public class LogicalPeopleGroupDetailPage implements IDetailsPage {
	private FormToolkit toolkit;
	private EditingDomain domain;
	private TLogicalPeopleGroup slectedLogicalPeopleGroup;
	private THumanInteractions humanInteractions;
	private Text name_txt;
	private Text para_name_txt;
	private Text para_type_txt;
	
	
	public LogicalPeopleGroupDetailPage(EditingDomain domain,THumanInteractions humanInteractions){
		this.domain = domain;
		this.humanInteractions = humanInteractions;
	}
	
	@Override
	public void initialize(IManagedForm mform) {
		this.toolkit = mform.getToolkit();
		
	}
	
	@Override
	public void createContents(Composite parent) {
		GridLayout gl = new GridLayout(1, false);  //layout for the parent
		parent.setLayout(gl);
		checkAvailability_LogicalPeopleGroup();
		createDetailSection(parent);
		
	}
	
	private void createDetailSection(Composite parent){

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
				| GridData.FILL_HORIZONTAL); 
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
		config_logicalPplGroupNameField(name_txt);
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
	
	public void updateParameter(TParameter selectedParameter) {
		if (slectedLogicalPeopleGroup != null) {

			name_txt.setText(slectedLogicalPeopleGroup.getName());

		}
		if (selectedParameter != null) {
			para_name_txt.setText(selectedParameter.getName());
			para_type_txt.setText(selectedParameter.getType().toString());
		}
		
	}

	private void update() {
		if (slectedLogicalPeopleGroup!= null) {
			
			name_txt.setText(slectedLogicalPeopleGroup.getName());
		
		} else {
			System.out.println("INPUT NULL");
		}
	}
	
	public void clearTextBox()
	{
		para_name_txt.setText("");
		para_type_txt.setText("");
	}
	
	
	
	public Text getPara_name_txt() {
		return para_name_txt;
	}

	public void setPara_name_txt(Text para_name_txt) {
		this.para_name_txt = para_name_txt;
	}

	public Text getPara_type_txt() {
		return para_type_txt;
	}

	public void setPara_type_txt(Text para_type_txt) {
		this.para_type_txt = para_type_txt;
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
		// TODO Auto-generated method stub
		
	}

	//call by master part fireSelectionChanged()
	@Override
	public void selectionChanged(IFormPart part, ISelection selection) {
	
		IStructuredSelection ssel = (IStructuredSelection) selection;
		
		if (ssel.size() == 1) {
			slectedLogicalPeopleGroup = (TLogicalPeopleGroup) ssel.getFirstElement();
		} else {
			slectedLogicalPeopleGroup = null;
		}
		update();
		
	}
	
	
	private void config_logicalPplGroupNameField(final Text nameTextBox){
		
		if(slectedLogicalPeopleGroup.getName() != null)
		{
			nameTextBox.setText(slectedLogicalPeopleGroup.getName());
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
	
	private void config_parmNameField(final Text paremNameTxt){
	//	if() //TODO
	}
	
	private void setAttribute_logicalPplGroup(EAttribute tTaskInterface_Attribute, String text) {
		Command setAttribCommand = SetCommand.create(domain,slectedLogicalPeopleGroup, tTaskInterface_Attribute, text);

		if (setAttribCommand.canExecute()) {
			domain.getCommandStack().execute(setAttribCommand);
			
		} else {
			System.out.println("can't modify Attribute: " + tTaskInterface_Attribute.getName());
		}
	}
	
	private void checkAvailability_LogicalPeopleGroup() {
		if (humanInteractions.getLogicalPeopleGroups() == null) {
			// Error message

		} else {
			slectedLogicalPeopleGroup = humanInteractions
					.getLogicalPeopleGroups().getLogicalPeopleGroup().get(0);
		}

	}


}
