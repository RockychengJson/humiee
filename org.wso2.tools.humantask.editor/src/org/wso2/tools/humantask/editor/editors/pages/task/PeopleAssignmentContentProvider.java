package org.wso2.tools.humantask.editor.editors.pages.task;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TGenericHumanRole;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPeopleAssignments;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTasks;
//import org.wso2.tools.humantask.editor.editors.task.pages.presentationelements.TPresentationParameters;

public class PeopleAssignmentContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub 
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		TTask t=(TTask)inputElement;
		List<HumanRole> list=new ArrayList<HumanRole>();
		//list.add(t);*/
		
		if(t.getPeopleAssignments()!=null)
		{
		if(t.getPeopleAssignments().getBusinessAdministrators()!=null)
		{
			for(int i=0;i<t.getPeopleAssignments().getBusinessAdministrators().size();++i)
			{
				list.add(new HumanRole(t.getPeopleAssignments().getBusinessAdministrators().get(i),"BusinessAdministrators"));
			}
		}
		if(t.getPeopleAssignments().getExcludedOwners()!=null)
		{
			for(int i=0;i<t.getPeopleAssignments().getExcludedOwners().size();++i)
			{
				list.add(new HumanRole(t.getPeopleAssignments().getExcludedOwners().get(i),"ExcludedOwners"));
			}
		}
		if(t.getPeopleAssignments().getPotentialOwners()!=null)
		{
			for(int i=0;i<t.getPeopleAssignments().getPotentialOwners().size();++i)
			{
				list.add(new HumanRole(t.getPeopleAssignments().getPotentialOwners().get(i), "PotentialOwners"));
			}
		}
		if(t.getPeopleAssignments().getRecipients()!=null)
		{
			for(int i=0;i<t.getPeopleAssignments().getRecipients().size();++i)
			{
				list.add(new HumanRole(t.getPeopleAssignments().getRecipients().get(i), "Recipients"));
			}
		}
		if(t.getPeopleAssignments().getTaskInitiator()!=null)
		{
			for(int i=0;i<t.getPeopleAssignments().getTaskInitiator().size();++i)
			{
				list.add(new HumanRole(t.getPeopleAssignments().getTaskInitiator().get(i), "TaskInitiator"));
			}
		}
		
		if(t.getPeopleAssignments().getTaskStakeholders()!=null)
		{
			for(int i=0;i<t.getPeopleAssignments().getTaskStakeholders().size();++i)
			{
				list.add(new HumanRole(t.getPeopleAssignments().getTaskStakeholders().get(i), "TaskStakeholders"));
			}
		}
		}
	
		return list.toArray();
		
/*		if(parent != null){
			TPresentationParameters params = (TPresentationParameters) parent;
			List<TPresentationParameters> paramsList = new ArrayList<TPresentationParameters>();
			return params.getPresentationParameter().toArray();
		}
		return null;*/
	}

}
