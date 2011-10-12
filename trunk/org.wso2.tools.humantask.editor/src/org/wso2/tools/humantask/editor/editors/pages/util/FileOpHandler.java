package org.wso2.tools.humantask.editor.editors.pages.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;

public class FileOpHandler {

	public FileOpHandler(){
		
	}
	
	public static void copyFile(String in, String out) throws IOException {
		FileChannel inChannel = new FileInputStream(in).getChannel();
		FileChannel outChannel = new FileOutputStream(out).getChannel();
		try {
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} catch (IOException e) {
			throw e;
		} finally {
			if (inChannel != null)
				inChannel.close();
			if (outChannel != null)
				outChannel.close();
		}
	}
	
	public static boolean isListed(String locationfile,String selectedfile){
		ArrayList<String> fileList = new ArrayList<String>();
		
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new DataInputStream(
					new FileInputStream(locationfile))));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				fileList.add(strLine);
			}
			br.close();

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		Iterator<String> it = fileList.iterator();
		
		while(it.hasNext()){
			
		 String inlist	= it.next();
		 if(inlist.compareTo(selectedfile) == 0){
			 return true;
		 }
			 
		 
		}
		
		return false;
		
	}
}
