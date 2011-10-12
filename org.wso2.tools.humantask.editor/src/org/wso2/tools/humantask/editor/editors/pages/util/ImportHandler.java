package org.wso2.tools.humantask.editor.editors.pages.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;
import org.wso2.tools.humantask.editor.editors.pages.humanInteractions.HIImportWizardPage;

public class ImportHandler {
	
	private static ImportHandler wsdl_handler;
	protected EditingDomain domain;
	private String filename = "";
	private String txt_name;
	
	public static final String HI_IMPORT_WIZ = "hi_impwiz";
	public static final String TASK_PAGE_IMPORT= "task_page_import";
	public static final String NOTIFICATION_PAGE_IMPORT ="notifi_page_import" ;
	
	
	private ImportHandler(HTMultiPageEditor editor){
		
		this.domain = editor.getEditingDomain();
	}

	public static ImportHandler getInstance(HTMultiPageEditor editor){
		if(wsdl_handler == null){
			wsdl_handler = new ImportHandler(editor);
		}
		
		return wsdl_handler;
	}
	
	public String createWSDLLocationTxt(String flag){
		
		//This ladder should be updated if any WSDL importing feature will add in the future.
		
		if(flag.compareTo(ImportHandler.HI_IMPORT_WIZ) == 0){
			txt_name = "/HIImportWSDLLocations_";
		}else if(flag.compareTo(ImportHandler.TASK_PAGE_IMPORT) == 0){
			txt_name = "/TWSDLLocations_";
		}else if(flag.compareTo(ImportHandler.NOTIFICATION_PAGE_IMPORT) == 0){
			txt_name = "/NWSDLLocations_";
		}

		String name = domain
				.getResourceSet()
				.getResources()
				.get(0)
				.getURI()
				.segment(
						domain.getResourceSet().getResources().get(0).getURI()
								.segmentCount() - 1);

		File file = new File(ResourcesPlugin.getWorkspace().getRoot()
				.getLocation().toString()
				+ txt_name + name + ".txt");
		try {
			filename = file.getCanonicalPath();
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			System.out.println("Error creating file WSDLLocations_...txt !");
		}

		System.out.println(ResourcesPlugin.getWorkspace().getRoot()
				.getLocation().toFile().pathSeparator);
		return filename;
	}
	
	
}
