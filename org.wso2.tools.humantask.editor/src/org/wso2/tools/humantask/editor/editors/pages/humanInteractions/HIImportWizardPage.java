package org.wso2.tools.humantask.editor.editors.pages.humanInteractions;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.xml.namespace.QName;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.wso2.tools.humantask.editor.editors.HTMultiPageEditor;
import org.wso2.tools.humantask.editor.editors.pages.util.WSDLHandler;

import com.ibm.wsdl.xml.WSDLReaderImpl;

public class HIImportWizardPage extends WizardPage {

	 public static final String PAGE_NAME = "Import page";
	 
	
	 private Combo nsp_combo;
	 private Text location_txt;
	 private Text im_type_txt;
	 private Combo comboDropDown;
	 private static final String[] FILTER_EXTS = { "*.wsdl","*.*" };
	 private String selectedWsdlComboBoxItem;
	 private String filename = "ImportWSDLLocations.txt";
	 private File wsdl_txt_file;
	 private Definition definition;
	 private WSDLReaderImpl reader;
	 private WSDLHandler wsdl_handler;
	
	 
	protected HIImportWizardPage(HTMultiPageEditor editor) {
		super(PAGE_NAME, "Add New Import", null);
		reader=new WSDLReaderImpl();
		wsdl_handler = WSDLHandler.getInstance(editor);
		filename = wsdl_handler.createWSDLLocationTxt(WSDLHandler.HI_IMPORT_WIZ);
		
	}

	
	@Override
	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		comp.setLayout(layout);
		
		/////////////////////////////////////////////////////////////
		Group wsdlInfo = new Group(comp, SWT.NONE);
		wsdlInfo.setText("Import WSDLs");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		wsdlInfo.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData.horizontalSpan = 2;
		wsdlInfo.setLayoutData(gridData);
		
		Label import_label = new Label(wsdlInfo, SWT.WRAP);
		import_label.setText("Imported WSDLs");
		GridData import_lb_gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		import_lb_gd.horizontalSpan =1;
		import_label.setLayoutData(import_lb_gd);
		
		 comboDropDown = new Combo(wsdlInfo, SWT.DROP_DOWN | SWT.BORDER);
		GridData combo_lb_gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL);
		combo_lb_gd.horizontalSpan = 2;
		comboDropDown.setLayoutData(combo_lb_gd);
		try {
			configImportedWsdl(comboDropDown);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Label select_wsdl_label = new Label(wsdlInfo, SWT.WRAP);
		select_wsdl_label.setText("Select the WSDL");
		//import_lb_gd.horizontalSpan =  1;
		select_wsdl_label.setLayoutData(import_lb_gd);
		
		final Text filename = new Text(wsdlInfo, SWT.SINGLE | SWT.BORDER);
		filename.setLayoutData(import_lb_gd);
		
		Button browse_btn = new Button(wsdlInfo, SWT.PUSH);
		browse_btn.setText("Browse");
		browse_btn.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent event) {
				
			
				FileDialog dlg = new FileDialog(Display
						.getCurrent().getActiveShell(), SWT.OPEN);
			 
				dlg.setFilterExtensions(FILTER_EXTS);
				String fn = dlg.open();
				if (fn != null) {
					filename.setText(fn);
				}
				try{
				saveToFile(filename.getText()); //url is saved to a text file
				}
				catch(IOException e)
				{
					System.out.println(e);
				}
				updateDetailsAccordingToWSDL();
			}
			
		});
		browse_btn.setLayoutData(import_lb_gd);
		
		//////////////////////////////////////////////////////////////
		
		Label name_space_label = new Label(comp, SWT.WRAP);
		name_space_label.setText("Namespace");
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);
		gd.horizontalSpan =1;
		name_space_label.setLayoutData(gd);
		
		//nsp_txt= new Text(comp, SWT.SINGLE|SWT.BORDER);
		//nsp_txt.setLayoutData(gd);
		nsp_combo = new Combo(comp,SWT.DROP_DOWN | SWT.BORDER );
		nsp_combo.setLayoutData(gd);
		
		Label location_lb = new Label(comp, SWT.WRAP);
		location_lb.setText("Location");
		location_lb.setLayoutData(gd);
		
		location_txt = new Text(comp, SWT.SINGLE | SWT.BORDER);
		location_txt.setLayoutData(gd);
		
		Label im_type_lb = new Label(comp, SWT.WRAP);
		im_type_lb.setText("Import Type");
		im_type_lb.setLayoutData(gd);
		
		im_type_txt = new Text(comp, SWT.SINGLE| SWT.BORDER);
		im_type_txt.setLayoutData(gd);
		
		setControl(comp);  
		setPageComplete(true);
	}
	
	private void saveToFile(String location) throws IOException {
		FileWriter fw = null;
		try {

			fw = new FileWriter(filename, true);

		} catch (FileNotFoundException ioe) {
			System.out.println("File not found !");
		}
		fw.write(location + "\n");// appends the string to the file
		fw.close();

		comboDropDown.add(location);
		int count = comboDropDown.getItemCount();
		comboDropDown.select(count - 1);
		selectedWsdlComboBoxItem = comboDropDown.getItem(comboDropDown
				.getSelectionIndex());
		
	}
	
	private void configImportedWsdl(final Combo WsdlComboBox)
			throws IOException {

		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new DataInputStream(
					new FileInputStream(filename))));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				WsdlComboBox.add(strLine);
			}
			br.close();

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		System.out.println(WsdlComboBox.getItemCount());
		if (WsdlComboBox.getItemCount() != 0) {
			selectedWsdlComboBoxItem = WsdlComboBox.getItem(0);
		}

		WsdlComboBox.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// validateInput();
				selectedWsdlComboBoxItem = WsdlComboBox.getItem(WsdlComboBox
						.getSelectionIndex());
				// WSDLReaderImpl reader =new WSDLReaderImpl();

				 updateDetailsAccordingToWSDL();

			}
		});

	}
	
	private void updateDetailsAccordingToWSDL(){
		
		try {
			definition= reader.readWSDL(selectedWsdlComboBoxItem);
		} catch (WSDLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Object [] namesapces = definition.getNamespaces().keySet().toArray();
		
		nsp_combo.removeAll();
		
		for(int i = 0 ;i<namesapces.length ; i++){
			nsp_combo.add(definition.getNamespace(namesapces[i].toString()));
		}
		nsp_combo.select(0);
		
		
	}
	
	public String getNameSpace(){
		return nsp_combo.getItem(nsp_combo.getSelectionIndex());
	}

	public String getLocation(){
		return location_txt.getText();
	}
	
	public String getImportType(){
		return im_type_txt.getText();
	}
	
}
