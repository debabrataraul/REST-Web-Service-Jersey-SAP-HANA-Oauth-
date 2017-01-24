package com.incture.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Data {
	public  String [][]db= new String[6][4];
	public  String failure=new String();
	public  String success=new String();
	
	private Data() {
		InputStream is = this.getClass().getResourceAsStream("/db.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8")))
		{
			String sCurrentLine;
			int line=0;
			while ((sCurrentLine = br.readLine()) != null) {
				db[line++]=sCurrentLine.split(" ");
				//System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
			if(is!=null)
				try {
					is.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		InputStream is2 = this.getClass().getResourceAsStream("/failure.txt");
	try (BufferedReader br = new BufferedReader(new InputStreamReader(is2, "UTF-8")))
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				failure+=sCurrentLine;
			}

		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
			if(is2!=null)
				try {
					is.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		InputStream is3 = this.getClass().getResourceAsStream("/success.txt");
	try (BufferedReader br = new BufferedReader(new InputStreamReader(is3, "UTF-8")))
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				success+=sCurrentLine;
			}

		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
			if(is3!=null)
				try {
					is.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}
	public static Data singleInstance= new Data(); 
	
	public static Data getInstance(){
		return singleInstance;
	}
	public static void main(String []args){
		//Data d=Data.getInstance();
		
	}
}
