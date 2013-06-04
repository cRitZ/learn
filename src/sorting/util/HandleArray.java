package sorting.util;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class HandleArray {

	public static int[] getItFromFile(String filename) throws Exception {

		/*
		 * read data from the INPUT section from "filename"
		 * 
		 * input data (hopefully some integer numbers) is located between the following
		 * two lines:
		 * 
		 * #ARRAY_INPUT_STARTS_BELOW_THIS_LINE#
		 * 
		 * and
		 * 
		 * #ARRAY_INPUT_ENDS_ABOVE_THIS_LINE#
		 * 
		 * returns an array of integers, if any valid data.
		 * 
		 * returns an empty zero length array of integers, otherwise
		 */

		String line;
		String startMark = "#ARRAY_INPUT_STARTS_BELOW_THIS_LINE#";
		String endMark = "#ARRAY_INPUT_ENDS_ABOVE_THIS_LINE#";

		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);

		// skip file content until the line containing <startMark>
		do {
			line = br.readLine();
		} while (!(line.trim()).equals(startMark));

		// get input data, until the line containing <endMark>
		List<Integer> lst = new ArrayList<Integer>();
		int[] x;
		
		do {
			line = br.readLine();
			/*
			 * for every line within the markers, a scanner is used to go
			 * through all the values on that line and add the integer ones to
			 * the list
			 */
			Scanner scnr = new Scanner(line);
			while (scnr.hasNext()) {
				String value = scnr.next();
				if (ValidateInput.isGoodNumber(value)) { 
					lst.add(Integer.parseInt(value.trim()));
				}else{
					//maybe add to output: "value" ignored
				}
			}
			scnr.close();		//cleanup
		} while (!(line.trim()).equals(endMark));
		
		br.close();				//cleanup
		
		// if any given
	    x = new int[lst.size()];
		for (int i = 0; i < x.length; i++) {
			x[i] = lst.get(i);
		}
		lst.clear();			//cleanup
		return x;
	}

	public static int[] getIt(int len) {
		/*
		 * getting an array of 'len' integers, values are input from keyboard
		 * one at a line, if not a valid integer value, message(#) , continue.
		 */
		Scanner in = new Scanner(System.in);
		int[] x = new int[len];
		int i = 0;
		String input;
		do {
			input = in.nextLine().trim();
			if (ValidateInput.isGoodNumber(input)) {
				x[i] = Integer.parseInt(input);
				i++;
			} else {
				System.out.println("'" + input + "' NOT INTEGER! " + (len - i)
						+ " MORE. CONTINUE."); // (#)
			}
		} while (i < len);
		// in.close();
		return x;
	}

	public static void screenIt(int[] x) {
		/*
		 * display an array
		 */
		if (x.length!=0){
			for (int i = 0; i < x.length; i++){
				System.out.print(" " + x[i]);
				}
			System.out.println();
		}else{
			System.out.println("EMPTY ARRAY.");
		}
	}

}
