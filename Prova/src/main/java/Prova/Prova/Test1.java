package Prova.Prova;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Test1 {
	
	public static void main(String[] args) {
		
		XWPFDocument docx1 = null;
		String mainString = "The process CSR is a pool.\r\n" + 
				"The process customer is a pool.\r\n" + 
				"The process Admin is a black pool.\r\n" + 
				"The process CSR starts by propose mortgage offer before perform a send message send offer.\r\n" +
				"The process customer is a pool.\r\n" + 
				" ";
		String string2 = "The process customer is a pool";
		String string3 = "The process customer starts";
		String string4 = "The process customer ends";
		String string5 = "menghi";
		
		boolean trovato = false;
		int count = 0;
		
		int max = mainString.length() - string2.length();
		test:
		for(int i =0; i <= max; i++) {
			
			int n = string2.length();
			int j = i;
			int k = 0;
			while (n-- != 0) {
			if (mainString.charAt(j++) != string2.charAt(k++)) {
			continue test;
			}
		
		}
			trovato = true;
			System.out.println("Parola: " + string2);
			break test;
			}
		
		System.out.println(trovato ? "Tovata" : "Non Trovata");
	//	 StringTokenizer st = new StringTokenizer("the process customer is a pool and it is good in this way");
	//	 String word = st.nextToken();
	//     while (st.hasMoreTokens()) {
	    	 
	//    	 if(st.equals(word))
	    	 
	    	 
	//    	 System.out.println("Pool" );
	//    	 else 	    		 break;
	    	 
	//    	 }
	    
	     }
	}

		
