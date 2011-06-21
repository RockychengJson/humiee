package org.wso2.tools.humantask.editor.editors.pages.presentationelements;

import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.wizard.Wizard;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationElements;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationParameter;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;


public class PresentationParameterWizard  extends Wizard {
	private EditingDomain domain;
	private ComposedAdapterFactory adapterFactory;
	private TPresentationElements presentationElements;
	
	PresentationParameterWizardPage page;
	
	
	public PresentationParameterWizard(){
		
	}
	
	
	public void addPages(){
		page = new PresentationParameterWizardPage();
		addPage(page);
	}
	
	
	
	@Override
	public boolean performFinish() {
		PresentationParameterWizardPage ppwp = (PresentationParameterWizardPage)
		getPage(PresentationParameterWizardPage.PAGE_NAME);
		
		
		return true;
	}
	
	public boolean performCancel()  
	  {
	    page.dispose();
	    return true;
	  }
	
	private void createPresentaionParameter(String name ,String type,String exp){
		/*//presentationElements.getPresentationParameters();
		//TPresentationParameter parameter = _200803Factory.eINSTANCE.createTPresentationParameter();
		TPresentationParameter parameter = htdFactory.eINSTANCE.createTPresentationParameter();
		parameter.setName(name);
		parameter.setType(QName.valueOf(type));
		
		List<TPresentationParameter> list = new BasicEList<TPresentationParameter>();
		list.add(parameter);
		Command addNewPresentationParameter = AddCommand.create(domain, presentationElements.getPresentationParameters(), _200803Package.eINSTANCE.getTPresentationParameters_PresentationParameter(), list);
		if(addNewPresentationParameter.canExecute()) {
			domain.getCommandStack().execute(addNewPresentationParameter);
			parametersTableViewer.refresh();
		} else {
			System.out.println("Cannot add new presentation parameter to Presentation Parameters");
		}*/
	}

}
