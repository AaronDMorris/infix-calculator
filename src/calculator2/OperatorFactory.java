package calculator2;

/**
 * This class is a stand-alone "factory" class, thats sole purpose is to instantiate operator objects required by the user
 * @author Aaron Morris
 * @since December 2015
 *	
 */
public class OperatorFactory {
	/**
	 * This method checks a string to see if it is of a supported operator type
	 * , if it is of a type; an instantiated Operator object is returned accordingly. Otherwise it returns as null
	 * @param op a string that should contain the operator of which type of Operator object is required to instantiate 
	 * @return the required operator class that is associated with the input string
	 */
	public static Operator getOperator(String op){
		
		if(op.equals("+")){
			return new Add(op);
		}else if(op.equals("-")){
			return new Minus(op);
		}else if(op.equals("*")){
			return new Multiply(op);
		}else if(op.equals("/")){
			return new Divide(op);
		}else if(op.equals("(")){
			return new LeftParenthesis(op);
		}else if(op.equals("^")){
			return new Power(op);
		}else{
			return null;
		}
	}
}
