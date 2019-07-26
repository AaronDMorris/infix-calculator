package calculator2;

/**
 * This class is the parent class of which all sub-operator classes will be derived. 
 * @author Aaron Morris
 *	
 */
public class Operator{
	public double mVal1;
	public double mVal2;
	public double result;
	public boolean error = false;
	/**
	 * Certain operators hold a level of precedence over each other, these are represented by an integer value named
	 * precedence, this is for easily comparable situations
	 */
	private int PRECEDENCE;
	private String op;
	
	public Operator(){
		
	}
	
	/**
	 * This constructor is used to set a string in the instantiated object, the string will contain what the sub-operator class is
	 * @param op the string that will contain the character equivalent to what the object is
	 */
	public Operator(String op){
		this.setOp(op);
	}

	public void setVals(double val1,double val2){
		mVal1 = val1;
		mVal2 = val2;
	}
	

	/**
	 * The calculation method is a method that takes in 2 double values and performs a calculation on them, 
	 * depending on which operator class is called. The result is returned in a singular double variable
	 * @param in1  a double that holds the first value of the requested calculation
	 * @param in2 a double that holds the second value that is required to form a calculation 
	 * @return a double containing the result of the prior performed calculation
	 */
	public double calculation(double in1, double in2){
		setVals(in1,in2);
		return result;
	}
	
	/**
	 * A method to return the currently stored result, as a double
	 * @return a double containing the stored result in the operator
	 */
	public double getResult(){
		return result;
	}
	
	/**
	 * This method returns which operator the method has been called upon, as a string
	 * @return a string containing the operator of which it was called upon
	 */
	public String getOp() {
		return op;
	}
	
	/**
	 * This method is called upon during construction when a string is passed into the instantiation, 
	 * it sets the string that is passed in; into a private member variable
	 * @param op a string that represents what operator has been instantiated
	 */
	public void setOp(String op) {
		this.op = op;
	}
	
	/**
	 * A method that allows public access to the retrieval of the privately set member variable;
	 * precedence. This disables the user from changing the variable, yet allowing them readable access
	 * @return integer value of the operator's precedence 
	 */
	public int getPrecedence(){
		return this.PRECEDENCE;
	}
}

