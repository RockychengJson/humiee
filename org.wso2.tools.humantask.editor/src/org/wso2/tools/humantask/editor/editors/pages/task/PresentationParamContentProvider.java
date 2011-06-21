package org.wso2.tools.humantask.editor.editors.pages.task;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;

public class PresentationParamContentProvider implements IStructuredContentProvider {

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
		
		TTask t = (TTask) inputElement;
		return t.getPresentationElements().getPresentationParameters().getPresentationParameter().toArray();
	}

}
