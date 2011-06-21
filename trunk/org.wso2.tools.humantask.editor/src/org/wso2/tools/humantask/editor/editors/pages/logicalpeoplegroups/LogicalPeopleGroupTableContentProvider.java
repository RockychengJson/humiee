package org.wso2.tools.humantask.editor.editors.pages.logicalpeoplegroups;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLogicalPeopleGroups;

public class LogicalPeopleGroupTableContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		TLogicalPeopleGroups logicalPeopleGroups = (TLogicalPeopleGroups)inputElement;
		return logicalPeopleGroups.getLogicalPeopleGroup().toArray();
	}

}
