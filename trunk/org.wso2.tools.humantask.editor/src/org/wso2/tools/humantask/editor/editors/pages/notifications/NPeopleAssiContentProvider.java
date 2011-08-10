package org.wso2.tools.humantask.editor.editors.pages.notifications;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TNotification;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.wso2.tools.humantask.editor.editors.pages.task.HumanRole;

public class NPeopleAssiContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		
		TNotification t=(TNotification)inputElement;
		List<HumanRole> list=new ArrayList<HumanRole>();
		//list.add(t);*/
		
		if(t.getPeopleAssignments()!=null)
		{
		if(t.getPeopleAssignments().getBusinessAdministrators()!=null)
		{
			for(int i=0;i<t.getPeopleAssignments().getBusinessAdministrators().size();++i)
			{
				list.add(new HumanRole(t.getPeopleAssignments().getBusinessAdministrators().get(i),"BusinessAdministrators",i));
			}
		}
		
		
		if(t.getPeopleAssignments().getRecipients()!=null)
		{
			for(int i=0;i<t.getPeopleAssignments().getRecipients().size();++i)
			{
				list.add(new HumanRole(t.getPeopleAssignments().getRecipients().get(i), "Recipients",i));
			}
		}
		
		}
		
		return list.toArray();
		
		
		
	/*	TNotification notification = (TNotification)inputElement;
		//TODO 
		return notification.getPeopleAssignments().getBusinessAdministrators().toArray();*/
	


}
}
