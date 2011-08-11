package org.wso2.tools.humantask.editor.editors;



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.DocumentRoot;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.THumanInteractions;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTasks;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.util.htdAdapterFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.util.htdResourceFactoryImpl;
import org.wso2.tools.humantask.editor.editors.pages.humanInteractions.HumanInteractionsPage;
import org.wso2.tools.humantask.editor.editors.pages.logicalpeoplegroups.LogicalPeopleGroupPage;
import org.wso2.tools.humantask.editor.editors.pages.notifications.NotificationPage;
import org.wso2.tools.humantask.editor.editors.pages.task.TaskPage;
import org.wso2.tools.humantask.editor.editors.xmleditor.HTEditor;



public class HTMultiPageEditor extends HTMultiPageEditorBase{

	private HumanInteractionsPage humanInteractionPage;
	private LogicalPeopleGroupPage logicalPeopleGroupPage;
	private TaskPage taskPage;
	private NotificationPage notificationPage;
	
	private HTEditor sourceViewer;
	
	private THumanInteractions humanInteractions;
	private TTasks tasks;
	
	
	public HTMultiPageEditor(){
		super();
		initializeEditingDomain();
	}

	protected void initializeEditingDomain() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new htdAdapterFactory());
		//adapterFactory.addAdapterFactory(new NamespaceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		
		super.initializeEditingDomain();
	}
	
	/**
	 * This is called during startup.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		setSite(site);
		setInputWithNotify(editorInput);
		setPartName(editorInput.getName());
		//site.setSelectionProvider(this);
		
		if (!(editorInput instanceof IFileEditorInput)) {
			throw new PartInitException(
					"Invalid Input: Must be IFileEditorInput"); //$NON-NLS-1$
		}
		
		createModel();
		
		//TODO: Check for these two below lines to add outline view and resourceChangeListener
		//site.getPage().addPartListener(partListener);
		//ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener, IResourceChangeEvent.POST_CHANGE);
	}

	/**
	 * Create the Human Interactions EMF generated model from the input file.
	 * @throws IOException 
	 * @throws PartInitException
	 */
	private void createModel() throws PartInitException {
		URI resourceURI = EditUIUtil.getURI(getEditorInput());
		Resource resource = null;
		
		htdResourceFactoryImpl fac = new htdResourceFactoryImpl();
		resource = fac.createResource(resourceURI);
		editingDomain.getResourceSet().getResources().add(resource);
		try {
			// Load the resource through the editing domain.
			//
			resource = editingDomain.getResourceSet().getResource(resourceURI, true);
		}
		catch (Exception e) {
			resource = editingDomain.getResourceSet().getResource(resourceURI, false);
		}
		
		try {
			resource.load(Collections.EMPTY_MAP);
			EList<EObject> contents = resource.getContents();
			
			if (!contents.isEmpty() && contents.get(0) instanceof DocumentRoot ) {
				humanInteractions = ((DocumentRoot) contents.get(0)).getHumanInteractions();
				
				tasks =  humanInteractions.getTasks(); //
			
				
			}
		} catch (IOException e) {
			throw new PartInitException(e.getMessage(), e);
		}
		
	
		
		//TODO: next 5 lines related to diagnosing resources, need to un-comment them later 
		/*Diagnostic diagnostic = analyzeResourceProblems(resource, exception);
		if (diagnostic.getSeverity() != Diagnostic.OK) {
			resourceToDiagnosticMap.put(resource,  analyzeResourceProblems(resource, exception));
		}
		editingDomain.getResourceSet().eAdapters().add(problemIndicationAdapter);*/
	}
	
	/*
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	protected void addPages() {
		
		try {
			
			
			humanInteractionPage = new HumanInteractionsPage(this, humanInteractions);
			int index = addPage(humanInteractionPage);
			setPageText(index, humanInteractionPage.getTitle());
			
			logicalPeopleGroupPage = new LogicalPeopleGroupPage(this, humanInteractions);
			index = addPage(logicalPeopleGroupPage);
			setPageText(index,logicalPeopleGroupPage.getTitle() );
				
			taskPage = new TaskPage(this,tasks,humanInteractions);
			index = addPage(taskPage);
			setPageText(index, taskPage.getTitle());
			
			notificationPage = new NotificationPage(this,humanInteractions);
			index = addPage(notificationPage);
			setPageText(index, notificationPage.getTitle());
		
			
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		try {

			sourceViewer = new HTEditor();
			int index = addPage(sourceViewer, getEditorInput());
			setPageText(index, sourceViewer.getTitle());
			
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		
		// Save only resources that have actually changed.
		final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		
		// Do the work within an Operation because this is a long running activity that modifies the workbench.
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			// This is the method that gets invoked when the Operation runs.
			@Override
			protected void execute(IProgressMonitor arg0) /*throws CoreException, InvocationTargetException, InterruptedException*/ {
				// Save the resources to the file system.
				
				boolean first = true;
				for (Resource resource : editingDomain.getResourceSet().getResources()) {
					if ((first || !resource.getContents().isEmpty() || isPersisted(resource)) && !editingDomain.isReadOnly(resource)) {
						
						try {
							resource.getTimeStamp();
							resource.save(saveOptions);
						} catch (IOException e) {
							//TODO: Correct exception handling
							e.printStackTrace();
						}
						first = false;
					}
				}
				
			}
		};
		
		try {
			// This runs the options, and shows progress.
			new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);
			
			// Refresh the necessary state.
			((BasicCommandStack)editingDomain.getCommandStack()).saveIsDone();
			//TODO: Need to come up with a interface represent all the pages, so page specific setResourceCHanged() can be avoided
			
			sourceViewer.doSave(progressMonitor);
			
			firePropertyChange(IEditorPart.PROP_DIRTY);
		} catch (InvocationTargetException e) {
			//TODO: Correct exception handling
			e.printStackTrace();
		} catch (InterruptedException e) {
			//TODO: Correct exception handling
			e.printStackTrace();
		}
			
	}
	
	@Override
	public boolean isDirty() {
		
		if (((BasicCommandStack) editingDomain.getCommandStack()).isSaveNeeded()) {
			return true;

		}else if (sourceViewer.isDirty()) {
			return true;
		
		} else {
			return false;
		}
		
	}


}