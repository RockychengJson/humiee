package org.wso2.tools.humantask.editor.editors.pages.task;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTasks;
//import org.wso2.tools.humantask.editor.editors.task.pages.presentationelements.TPresentationParameters;

public class TaskContentProvider implements IStructuredContentProvider {

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
		TTasks t=(TTasks)inputElement;
		/*List<TTasks> list=new ArrayList<TTasks>();
		list.add(t);*/
		return t.getTask().toArray();
		
/*		if(parent != null){
			TPresentationParameters params = (TPresentationParameters) parent;
			List<TPresentationParameters> paramsList = new ArrayList<TPresentationParameters>();
			return params.getPresentationParameter().toArray();
		}
		return null;*/
	}

}
