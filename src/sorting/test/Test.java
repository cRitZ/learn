package sorting.test;


import sorting.util.*;
import  java.io.*;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{

		InputDataType data=new InputDataType();
		data.array= new int[0];
		data.option=0;
		
		
		String filename="D:\\rtz\\java\\workspace\\learn\\src\\sorting\\sortinggIO.txt";
		File f=new File(filename);
		
		data=FileTools.getDataFromFile(f);
		FileTools.outputToFile(f,data);
		
	}

}
