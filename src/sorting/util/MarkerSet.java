package sorting.util;

public interface MarkerSet {
	
	final String separatorMarker="-------------------------------------------";
	
	final String inHeaderMarker= "################   INPUT   ################";
	
	//array input markers
	final String arrayStartMarker="#ARRAY_INPUT_STARTS_BELOW_THIS_LINE#";
	final String arrayEndMarker="#ARRAY_INPUT_ENDS_ABOVE_THIS_LINE#";
	
	//
	final String availableOptionsMarker="1.Brute  2.Bubble  3.Insertion  4.Selection";
	
	//option input markers
	final String optionStartMarker="#OPTION_INPUT_STARTS_BELOW_THIS_LINE#";
	final String optionEndMarker="#OPTION_INPUT_ENDS_ABOVE_THIS_LINE#";
	
	final String outHeaderMarker="###############   OUTPUT   ################";
	
}
