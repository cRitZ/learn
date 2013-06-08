package sorting.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import sorting.algorithms.*;

public class FileTools {

	public static void initIOFile(File f) throws Exception{
		/*
		 * delete and then re-initialise content of a file according to design
		 * specifications
		 */
		
		/*
		 * just finishing last touches, and it hit me that since the time i
		 * started this task maybe i gathered enough information on the issue to
		 * know (at least how to find out) how to avoid hand-writing this fucker
		 * instead of cloning an actual maybe read-only backup txt file and be
		 * done with it. hm..
		 */
		
		
		f.delete();
		f.createNewFile();
				
		FileWriter fw= new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.newLine();bw.write("      ----------------------------");
		bw.newLine();bw.write("      | SORTING NUMBERS I/O FILE |");
		bw.newLine();bw.write("      ----------------------------");
		bw.newLine();
		bw.newLine();bw.write("    ! EXCEPT THE VALUES TO BE TESTED  !");
		bw.newLine();bw.write("    ! BETTER NOT MODIFY ANYTHING ELSE !");
		bw.newLine(); // TODO can be replaced with "\n" -> or even better String separator = System.getProperty("line.separator");
		
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.IN_HEADER);
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.ARRAY_START);
		bw.newLine();
		bw.newLine();bw.write("    <  array goes here, may delete this");
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.ARRAY_END);
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.SEPARATOR);
		bw.newLine();bw.write(MarkerSet.AVAILABLE_OPTIONS);
		bw.newLine();bw.write(MarkerSet.SEPARATOR);
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.OPTION_START);
		bw.newLine();
		bw.newLine();bw.write("    <  option goes here, may delete this");
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.OPTION_END);
		bw.newLine();
		bw.newLine();
		bw.newLine();bw.write(MarkerSet.OUT_HEADER);
		bw.newLine();bw.write("! starting with THIS_LINE ,you may delete !");
		bw.newLine();bw.write("! anything. outputs will be ADDED !       !");
		//bw.newLine();
		bw.flush();
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
			// TODO for this kind of comparison: http://docs.oracle.com/javase/7/docs/api/java/lang/String.html#equalsIgnoreCase(java.lang.String)
		} while (!(input.equals("y")) && !(input.equals("Y"))
				&& !(input.equals("n")) && !(input.equals("N")));

		scnr.close(); // cleanup

		if ((input.equals("y")) || (input.equals("Y"))) {
			// proceed with input file re-initialisation
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
				MarkerSet.ARRAY_START,
				MarkerSet.ARRAY_END,
				MarkerSet.OPTION_START,
				MarkerSet.OPTION_END };
	
		// basically, just check if the file contains ALL the mark lines in the
		// appropriate ORDER
	
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
		 * #ARRAY_INPUT_STARTS_BELOW_THIS_LINE# 	<MarkerSet.ARRAY_START>
		 * 
		 * and
		 * 
		 * #ARRAY_INPUT_ENDS_ABOVE_THIS_LINE# 		<MarkerSet.ARRAY_END>
		 * 
		 * sorting option input data is located between the following two marker
		 * lines:, only the first integer corresponding to available sorting
		 * options will be considered.
		 * 
		 * #OPTION_INPUT_STARTS_BELOW_THIS_LINE# 	<MarkerSet.OPTION_START>
		 * 
		 * and
		 * 
		 * #OPTION_INPUT_ENDS_ABOVE_THIS_LINE# 		<MarkerSet.OPTION_END>
		 * 
		 * returns an InputDataType element, 2 fields, (int[] .array) & (int
		 * .option)
		 * 
		 * returns (<array>,<option>) if valid input
		 * 
		 * returns (<empty array>,<0>), otherwise
		 */
		
		String startMark=MarkerSet.ARRAY_START;
		String endMark=MarkerSet.ARRAY_END;
		
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String line;
		
		// getting the array
		
		// skip file content until the line containing <ARRAY_START>		
		do {
			line = br.readLine();
		} while (!(line.trim()).equals(startMark));
		// get input data, until the line containing <ARRAY_END>
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
		
		//at this point, the last br.readLine() was done on <ARRAY_END>
		
		//continue with getting the sorting option
		startMark=MarkerSet.OPTION_START;
		endMark=MarkerSet.OPTION_END;
		// skip file content until the line containing <OPTION_START>		
		do {
			line = br.readLine();
		} while (!(line.trim()).equals(startMark));
		
		/*
		 * getting the option starting from this point in the file, meaning
		 * getting the first valid integer value in this section
		 */ 
		int opt=0;	//if no valid option input, 0 is fine
		Boolean notfound=true;
		
		do {
			line = br.readLine();
			Scanner scnr = new Scanner(line);
			
			while (scnr.hasNext() && notfound) {
				String value = scnr.next();
				if (ValidateInput.isGoodNumber(value)) {
					// option validation maybe should be treated in
					// outputToFile(inputData)
					opt = Integer.parseInt(value.trim());
					notfound = false;
					break;
				}
			}
			
			scnr.close();		//cleanup
		} while (!(line.trim()).equals(endMark)&&notfound);
		//no need looking after <OPTION_END> line
		
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

	public static void writeArrayToFile(File f,int[] x) throws Exception{
		/*
		 * just append the content of an array at THE END of the file, NOT on
		 * new line by default, "( EMPTY ARRAY )" if the case
		 */

		FileWriter fw= new FileWriter(f,true);
		BufferedWriter bw= new BufferedWriter(fw);
		//bw.newLine();
		bw.append("(");
		if (x.length>0){
			for (int i=0;i<x.length;i++){
			bw.append(" "+x[i]);
			}
		}else {
			bw.append(" EMPTY ARRAY.");
		}
		
		bw.append(" )");
		//bw.newLine();
		bw.flush();
		bw.close();
		fw.close();
	}
	
	
	private static boolean isValidSortingOption(int opt){
		/*
		 * validation of the value input for the sorting option
		 * this scenario just checks if it is 1,2,3 or 4.
		 */
		if((opt==1)||(opt==2)||(opt==3)||(opt==4)){
			return true;
		}
		return false;
		}
	
	public static void outputToFile(File f, InputDataType data) throws Exception{
		/*
		 * if (data.option = 1 or 2 or 3 or 4) and (data.array not empty) sort
		 * array accordingly to 1.Brute 2.Bubble 3.Insertion 4.Selection and
		 * then output results to file.
		 * 
		 * otherwise, output ISSUES to file as well.
		 */
		
		FileWriter fw= new FileWriter(f,true);
		BufferedWriter bw= new BufferedWriter(fw);

		bw.newLine();bw.newLine();
		bw.write(MarkerSet.SEPARATOR);
		bw.newLine();bw.newLine();
		bw.flush();
		
		if (data.array.length>0){
			if (isValidSortingOption(data.option)){
				//array ok, option ok
				switch (data.option){
				case 1: {
					bw.write(" BRUTE ");
					bw.flush();
					writeArrayToFile(f,data.array);	//
					bw.write(" -> ");
					bw.flush();
					writeArrayToFile(f, (new BruteSorter()).sortIt(data.array));
					// nailed it first try. starting to love java. :)
					break;
				}
				case 2: {
					bw.write(" BUBBLE ");
					bw.flush();
					writeArrayToFile(f,data.array);	//
					bw.write(" -> ");
					bw.flush();
					writeArrayToFile(f, (new BubbleSorter()).sortIt(data.array));
					break;
				}
				case 3: {
					bw.write(" INSERTION ");
					bw.flush();
					writeArrayToFile(f,data.array);	//
					bw.write(" -> ");
					bw.flush();
					writeArrayToFile(f, (new InsertionSorter()).sortIt(data.array));
					break;
				}
				case 4: {
					bw.write(" SELECTION ");
					bw.flush();
					writeArrayToFile(f,data.array);	//
					bw.write(" -> ");
					bw.flush();
					writeArrayToFile(f, (new SelectionSorter()).sortIt(data.array));
					break;
				}
				}
			}else{
				//array ok, option not ok
				bw.write(" ARRAY OK. ");
				bw.flush();
				writeArrayToFile(f, data.array);
				bw.newLine();bw.newLine();
				bw.write(" OPTION NOT OK.  1, 2, 3 OR 4 AVAILABLE ONLY!");
				bw.flush();
			}
		}else{
			//array not ok
			bw.write(" ARRAY NOT OK. INPUT SOME INTEGERS! ");
			bw.flush();
			bw.newLine();bw.newLine();
			if (isValidSortingOption(data.option)){
				// array not ok, option ok
				bw.write(" OPTION OK.");
				bw.flush();
			}else{
				//array not ok, option not ok
				bw.write(" OPTION NOT OK.  1, 2, 3 OR 4 AVAILABLE ONLY!");
				bw.flush();
			}
		}
		
		bw.flush();
		bw.close();
		fw.close();
	}
}
