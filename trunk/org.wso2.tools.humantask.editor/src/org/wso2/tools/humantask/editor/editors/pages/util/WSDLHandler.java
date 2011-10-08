package org.wso2.tools.humantask.editor.editors.pages.util;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;

public class WSDLHandler {
	
	private static WSDLHandler wsdl_handler;
	protected EditingDomain domain;
	private String filename = "";
	private String txt_name;
	
	private WSDLHandler(HTMultiPageEditor editor){
		
		this.domain = editor.getEditingDomain();
	}

	public static WSDLHandler getInstance(HTMultiPageEditor editor){
		if(wsdl_handler == null){
			wsdl_handler = new WSDLHandler(editor);
		}
		
		return wsdl_handler;
	}
	
	public String createWSDLLocationTxt(String flag){
		
		//This ladder should be updated if any WSDL importing feature will add in the future.
		
		if(flag.compareTo("hi_impwiz") == 0){
			txt_name = "/HIImportWSDLLocations_";
		}else if(flag.compareTo("task_page") == 0){
			txt_name = "/TWSDLLocations_";
		}else if(flag.compareTo("notifi_page") == 0){
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
