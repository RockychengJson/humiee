package org.wso2.tools.humantask.editor.editors.pages.task;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.wso2.tools.humantask.editor.editors.pages.util.Messages;

public class AddPeopleAssiWizardPage extends WizardPage{

	
	public static final String PAGE_NAME ="Add new People Assingment";
	private Text nametextbox;
	private Combo roal_type;
	
	public AddPeopleAssiWizardPage(){
		
		 super(PAGE_NAME ,"Add new People Assingment" , null);
	}
	
	
	@Override
	public void createControl(Composite parent) {

		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		comp.setLayout(layout);
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER
				 | GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 1;
		
		Label namelabel = new Label(comp, SWT.WRAP);
		namelabel.setText("Name");
		namelabel.setLayoutData(gd);

		nametextbox = new Text(comp, SWT.SINGLE | SWT.BORDER);
		nametextbox.setLayoutData(gd);
		
	    setControl(comp);  
	    setPageComplete(true);
	    
	    Label crt = new Label(comp, SWT.WRAP);
	    crt.setText("Choose Roal Type");
	    crt.setLayoutData(gd);
		
	    roal_type = new Combo(comp, SWT.READ_ONLY);
		roal_type.setLayoutData(gd);
		
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
		
	}
	

	public String getNameTextBoxContent(){
		return nametextbox.getText();
	}
	
	public String getRoalType(){
		
	
		int index = roal_type.getSelectionIndex();
		if(index == 0){
			return Messages
			.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.potentialownerslable");
		}else if(index == 1){
			return Messages
			.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.excludedowners");
		}else if(index == 2){
			return Messages
			.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.taskinitiator");
		}else if(index == 3){
			return Messages
			.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.taskstakeholders");
		}else if(index == 4){
			return Messages
			.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.businessadministrators");
		}else if(index == 5){
			return Messages
			.getString("TaskPage.peopleassingmentTab.Section.radiobtnset.notificationrecipients");
		}else{
			return "N/A";
		}
			
	}		
		
	

}
