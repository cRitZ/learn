package sorting;

import sorting.util.*;
import java.io.File;
import sorting.util.InputDataType;
public class Main2 {

	/*
	 * Sorting an array of integer numbers by a desired sorting algorithm.
	 * 
	 * Input: Both ARRAY and SORTING METHOD are input values from a specified
	 * TEXT FILE. The full-path filename of the text file is passed as argument
	 * to the program.
	 * 
	 * Output: The initial array sorted using the selected sorting option will
	 * be added to the file, if valid input file. Error messages, otherwise.
	 * 
	 * ADDITIONAL!
	 * 
	 * This project is designed to work with an input text file containing at
	 * least the 4 #MARKER_LINES# described by the following CONTENT DESIGN:
	 * 
	 * (irrelevant content, prompt to input array, multiple lines
	 * allowed)
	 * 
	 * #ARRAY_INPUT_STARTS_BELOW_THIS_LINE#
	 * 
	 * <input data, only integers will be taken in consideration>
	 * 
	 * (multiple lines allowed)
	 * 
	 * #ARRAY_INPUT_ENDS_ABOVE_THIS_LINE#
	 * 
	 * (irrelevant content, prompt available options to select,
	 * multiple lines allowed)
	 * 
	 * #OPTION_INPUT_STARTS_BELOW_THIS_LINE#
	 * 
	 * <input data, only first integer will be taken into consideration>
	 * 
	 * (multiple lines allowed)
	 * 
	 * #OPTION_INPUT_ENDS_ABOVE_THIS_LINE#
	 * 
	 * (irrelevant content, multiple lines allowed)
	 * 
	 * (maybe previous outputs, irrelevant content)
	 */

	public static InputDataType getDATA(File f) throws Exception {
		/*
		 * At this point f is a valid filename
		 * 
		 * If content design checks out, proceed to FileTools.getDataFromFile
		 * and extract data. Otherwise, option to re-initialise file content to
		 * correct design.  
		 * 
		 */
		InputDataType data= new InputDataType();
		data.option=0;
		data.array= new int[0];
		
		if (FileTools.isGoodIOFile(f)){
			data =FileTools.getDataFromFile(f);
		}else{
			System.out.println("\""+f + "\" NOT A VALID INPUT FILE.");
			//option (Y/N) to re-initialise the given 
			//file to correct input/output design
			FileTools.resetIOFile(f);
		}
		
		return data;
	}
	
	public static void main(String[] args) throws Exception {
		//TODO polish initIOFile
		
		// if no arguments
		if (args.length == 0) {
			System.out.println("MISSING ARGUMENT! Specify file to use.");
		} else {
			// check if valid filename argument
			if (FileTools.isGoodFilename(args[0])) {
				File f= new File(args[0]);
				
				FileTools.outputToFile(f,getDATA(f));
											
			} else {
				System.out.println("\""+args[0] + "\" DOES NOT EXIST.");
				//TODO option (Y/N) to create new IO file maybe ?!
			}
		}
	}

}
