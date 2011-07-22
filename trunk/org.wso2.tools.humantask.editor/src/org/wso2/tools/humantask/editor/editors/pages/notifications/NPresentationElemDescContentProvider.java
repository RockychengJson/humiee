package org.wso2.tools.humantask.editor.editors.pages.notifications;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TNotification;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;

public class NPresentationElemDescContentProvider implements IStructuredContentProvider {

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
		
		TNotification notification = (TNotification)inputElement;
		return notification.getPresentationElements().getDescription().toArray();
	}

}
