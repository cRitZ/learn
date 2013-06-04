
package sorting.util;

import java.io.File;

public class ValidateInput {

	public static boolean isGoodFilename(String s){
		/*
		 * check if a given string is a valid FULLPATH filename  
		 */
		File f=new File(s);
		if (f.exists()) {
			return true;
		}else{
			return false;
		}				
	}
	
	public static boolean isGoodNumber(String s) {
		/*
		 *  check if a given string is a valid integer value,
		 *  <numeric> or <-numeric>
		 *  
		 */  
		s = s.trim();
		int l = s.length();
		if ((l == 0) || (s.equals("-"))) {
			return false;
		}

		int j = 0;
		if (s.charAt(0) == '-') {
			j = 1; // if charAt(0) is minus sign, start checking from charAt(1)
		}

		boolean ok = true; // checking if chars are numeric
		for (int i = j; i < s.length(); i++) {
			switch (s.charAt(i)){
			case '0' : {break;}
			case '1' : {break;}
			case '2' : {break;}
			case '3' : {break;}
			case '4' : {break;}			// Ctrl+Shift+F it your self 
			case '5' : {break;}			// if you think it looks better (in this case)
			case '6' : {break;}
			case '7' : {break;}
			case '8' : {break;}
			case '9' : {break;}
			default :{return false;}
			}
		}
		return ok;
	}

}
