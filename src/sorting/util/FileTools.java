package sorting.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileTools {

	public static void initIOFile(File f) throws Exception{
		/*
		 * delete and then re-initialise content of a file according to design
		 * specifications
		 */
		
		f.delete();
		f.createNewFile();
				
		FileWriter fw= new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.newLine();bw.write(MarkerSet.inHeaderMarker);
		bw.newLine();
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.arrayStartMarker);
		bw.newLine();
		bw.newLine();//bw.write(" integers separated by space and/or newline ");
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.arrayEndMarker);
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.separatorMarker);
		bw.newLine();bw.write(MarkerSet.availableOptionsMarker);
		bw.newLine();bw.write(MarkerSet.separatorMarker);
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.optionStartMarker);
		bw.newLine();
		bw.newLine();//bw.write(" integer of the above options ");
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.optionEndMarker);
		bw.newLine();
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.outHeaderMarker);
		bw.newLine();
		bw.newLine();
		
		bw.close();	//cleanup
		
	}

	public static void resetIOFile(File f) throws Exception {

		/*
		 * prompt Y/N message first.
		 * 
		 * resets the content of the file referred by f to the appropriate I/O
		 * design, ready to then be populated with input data.
		 */

		String input = "";
		Scanner scnr = new Scanner(System.in);

		do {
			System.out.print("\n" + f
					+ "\n RESET CONTENT TO NEW INPUT FILE? Y/N ");
			input = scnr.nextLine();
		} while (!(input.equals("y")) && !(input.equals("Y"))
				&& !(input.equals("n")) && !(input.equals("N")));

		scnr.close(); // cleanup

		if ((input.equals("y")) || (input.equals("Y"))) {
			// proceed with input file re-initialisation
			//File filename= new File(arg);
			initIOFile(f);
			System.out.println(" DONE. JUST ADD INPUT DATA.");
		} else {
			System.out.println(" OK. HAVE IT YOUR WAY THEN.");
		}

	}
	
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
	
	public static boolean isGoodIOFile(File file) throws Exception{
		/*
		 * check if the file has a valid design
		 * content, marker lines in place,
		 */
		String mark[] = new String[] { 
				MarkerSet.arrayStartMarker,
				MarkerSet.arrayEndMarker,
				MarkerSet.optionStartMarker,
				MarkerSet.optionEndMarker };
	
		// basically, just check if the file contains the mark lines in the
		// appropriate order
	
		Scanner scnr= new Scanner(file);
		Boolean ok=false;
		for (int i=0;i<mark.length;i++){
			ok=false;
			while(scnr.hasNextLine()){
				String line=scnr.nextLine();
				if (line.equals(mark[i])) {
					ok=true;
					break;
				}
			}
			if (!ok){
				scnr.close();
				return false;
			}
		}
		scnr.close();
		return true;
	}

	public static InputDataType getDataFromFile(File filename) throws Exception {
		/*
		 * by the time this gets called, <filename> already checked ok for valid
		 * input file (has the right markers in place)
		 *
		 * read data from the INPUT section from "filename"
		 * 
		 * array input data (hopefully some integer numbers) is located between
		 * the following two marker lines:, all integers separated by space or
		 * newline will be considered in the array.
		 * 
		 * #ARRAY_INPUT_STARTS_BELOW_THIS_LINE# <MarkerSet.arrayStartMarker>
		 * 
		 * and
		 * 
		 * #ARRAY_INPUT_ENDS_ABOVE_THIS_LINE#	<MarkerSet.arrayEndMarker>
		 * 
		 * sorting option input data is located between the following two marker
		 * lines:, only the first integer corresponding to available sorting
		 * options will be considered.
		 * 
		 * #OPTION_INPUT_STARTS_BELOW_THIS_LINE#	<optionStartMarker>
		 * 
		 * and
		 * 
		 * #OPTION_INPUT_ENDS_ABOVE_THIS_LINE#	<optionEndMarker>
		 * 
		 * returns an InputDataType element, 2 fields, (int[] .array) & (int
		 * .option)
		 * 
		 * returns (<array>,<option>) if valid input
		 * 
		 * returns (<empty array>,<0>), otherwise
		 */
		
		String startMark=MarkerSet.arrayStartMarker;
		String endMark=MarkerSet.arrayEndMarker;
		
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String line;
		
		// getting the array
		
		// skip file content until the line containing <arrayStartMarker>		
		do {
			line = br.readLine();
		} while (!(line.trim()).equals(startMark));
		// get input data, until the line containing <arrayEndMarker>
		List<Integer> lst = new ArrayList<Integer>();
		
		do {
			line = br.readLine();
			/*
			 * for every string line within the markers, a scanner is used to go
			 * through all the values on that line and add the integer ones to
			 * the list
			 */
			Scanner scnr = new Scanner(line);
			while (scnr.hasNext()) {
				String value = scnr.next();
				if (ValidateInput.isGoodNumber(value)) { 
					lst.add(Integer.parseInt(value.trim()));
				}else{
					//TODO maybe add to output: "value" ignored
				}
			}
			scnr.close();		//cleanup
		} while (!(line.trim()).equals(endMark));
		
		//at this point, the last br.readLine() was done on <arrayEndMarker>
		
		//continue with getting the sorting option
		startMark=MarkerSet.optionStartMarker;
		endMark=MarkerSet.optionEndMarker;
		// skip file content until the line containing <optionStartMarker>		
		do {
			line = br.readLine();
		} while (!(line.trim()).equals(startMark));
		
		/* getting the option
		 * starting from this point in the file
		 * meaning getting the first valid integer value corresponding to
		 * an available option from the specified list of sorting options
		 */ 
		int opt=0;	//if no valid option input, 0 is fine
		Boolean notfound=true;
		do {
			line = br.readLine();
			Scanner scnr = new Scanner(line);
			// Boolean notfound=true;
			while (scnr.hasNext() && notfound) {
				String value = scnr.next();
				if (ValidateInput.isGoodNumber(value)) {
					// TODO option validation maybe should be treated in
					// outputToFile(inputData)
					opt = Integer.parseInt(value.trim());
					notfound = false;
					break;
				}
			}
			scnr.close();		//cleanup
		} while (!(line.trim()).equals(endMark)&&notfound);
		
		
		br.close();				//cleanup
		
		
		
		// just clone the list to the an array
	    int[] x = new int[lst.size()];
		for (int i = 0; i < x.length; i++) {
			x[i] = lst.get(i);
		}
		
		lst.clear();			//cleanup
		
		//return data
		InputDataType data= new InputDataType();
		data.option=opt;
		data.array=x;
		return data;
	}

	public static void outputToFile(File f, InputDataType data) throws Exception{
		Scanner scnr=new Scanner(f);
		
		
		//for now just output the data extracted from the input section
		//no validation/modification done
		
		
		while(scnr.hasNextLine()){
			System.out.println("citit "+scnr.nextLine());
		}
		scnr.close();
		FileWriter fw=new FileWriter(f);
		BufferedWriter bw= new BufferedWriter(fw);
		bw.close();	//cleanup
	}
}
