package sorting;

import java.io.FileReader;
import java.io.BufferedReader;
import sorting.util.*;
import java.util.*;

public class Main2 {

	/*
	 * Sorting an array of integer numbers by a desired sorting algorithm.
	 * 
	 * Input: Both ARRAY and SORTING METHOD are input values from a specified
	 * TEXT FILE. The full-path filename of the text file is passed as argument
	 * to the program.
	 * 
	 * Output: The initial array sorted using the selected sorting option will
	 * be added to the file, if valid input file. Error message, otherwise.
	 * 
	 * ADDITIONAL!
	 * 
	 * This project is designed to work with an input text file containing at
	 * least the 4 #MARKER_LINES described by the following CONTENT DESIGN:
	 * 
	 * (interface, irrelevant content, prompt to input array, multiple lines
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
	 * (interface, irrelevant content, prompt available options to select,
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
	 * (interface, irrelevant content, multiple lines allowed)
	 * 
	 * (maybe previous outputs, irrelevant content)
	 */

	public static void main(String[] args) throws Exception {
		// TODO get input file and display it.
		// TODO ValidateInput.isGoodInputFile ?, then HandleArray.getItFromFile.

		// if no arguments
		if (args.length == 0) {
			System.out.println("MISSING ARGUMENT! Specify .txt file to use.");
		} else {
			// check if valid filename argument
			if (ValidateInput.isGoodFilename(args[0])) {
				//System.out.println(args[0] + " exista.");
				int[] numbers = new int[HandleArray.getItFromFile(args[0]).length];
				numbers = HandleArray.getItFromFile(args[0]);
				HandleArray.screenIt(numbers);
			} else {
				System.out.println(args[0] + " nu exista.");
			}
		}

	}

}
