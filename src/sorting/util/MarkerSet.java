package sorting.util;

public interface MarkerSet {
	
	String SEPARATOR="-------------------------------------------";
	
	String IN_HEADER= "################   INPUT   ################";
	
	//array input markers
	String ARRAY_START="#ARRAY_INPUT_STARTS_BELOW_THIS_LINE#";
	String ARRAY_END="#ARRAY_INPUT_ENDS_ABOVE_THIS_LINE#";
	
	//
	String AVAILABLE_OPTIONS="1.Brute  2.Bubble  3.Insertion  4.Selection";
	
	//option input markers
	String OPTION_START="#OPTION_INPUT_STARTS_BELOW_THIS_LINE#";
	String OPTION_END="#OPTION_INPUT_ENDS_ABOVE_THIS_LINE#";
	
	String OUT_HEADER="###############   OUTPUT   ################";
	
}
