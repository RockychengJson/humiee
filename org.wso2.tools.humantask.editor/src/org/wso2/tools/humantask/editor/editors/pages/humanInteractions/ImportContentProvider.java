package org.wso2.tools.humantask.editor.editors.pages.humanInteractions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TExtensions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TImport;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTasks;
//import org.wso2.tools.humantask.editor.editors.task.pages.presentationelements.TPresentationParameters;

public class ImportContentProvider implements IStructuredContentProvider {

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
		return ((EList)inputElement).toArray();
		/*List<TTasks> list=new ArrayList<TTasks>();
		list.add(t);*/
		//return ((EList<TImport>)inputElement).toArray();
		
/*		if(parent != null){
			TPresentationParameters params = (TPresentationParameters) parent;
			List<TPresentationParameters> paramsList = new ArrayList<TPresentationParameters>();
			return params.getPresentationParameter().toArray();
		}
		return null;*/
	}

}
