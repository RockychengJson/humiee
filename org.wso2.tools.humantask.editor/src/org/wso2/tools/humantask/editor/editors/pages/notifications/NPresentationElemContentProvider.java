package org.wso2.tools.humantask.editor.editors.pages.notifications;

import java.util.concurrent.ArrayBlockingQueue;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TNotification;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationElements;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TText;

public class NPresentationElemContentProvider implements IStructuredContentProvider{

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
		
		Object o[]={};
		TNotification notification = (TNotification)inputElement;
		if(notification != null){
			if(notification.getPresentationElements() != null){
				if(notification.getPresentationElements().getName() != null){
					if(notification.getPresentationElements().getName().size() != 0){
						return notification.getPresentationElements().getName().toArray();
					}else{
						return o;	
					}
				}else{
					return o;	
				}
			}else{
				return o;	
			}
		}else{
			return o;	
		}
		
		
	}

}
