package org.wso2.tools.humantask.editor.editors.pages.notifications;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TNotification;


public class NPresentationElemeSubContentProvider implements IStructuredContentProvider {

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
		TNotification notification = (TNotification) inputElement;
		Object[] o = {};
		if (notification != null) {
			if (notification.getPresentationElements() != null) {
				if (notification.getPresentationElements().getSubject() != null) {
					if (notification.getPresentationElements().getSubject()
							.size() != 0) {
						return notification.getPresentationElements()
								.getSubject().toArray();
					} else {
						return o;
					}
				} else {
					return o;
				}
			} else {
				return o;
			}
		} else {
			return o;
		}

	}

}
