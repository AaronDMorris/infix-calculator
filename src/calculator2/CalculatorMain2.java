package calculator2;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;




/**
 * This class is the main program class to initalise The Calculator program 
 * @author Aaron Morris
 * @version 0.9
 * @since October 2015
 * 
 */
public class CalculatorMain2 {
	public Deque<String> mInputStack = new ArrayDeque<String>();
	public Deque<Operator> mInputStack2 = new ArrayDeque<Operator>();
	public Scanner ui = new Scanner(System.in);
	public double result = 0;
	public Operator op1 = new Operator();
	

	/**
	 * Initialisation message to welcome the user
	 */
	public void initMessage(){
		System.out.printf("Welcome to The Calculator program.\n"
				+ "Please type EXIT on the command line to close.\n"
				+ "Enter your calculation with a space to separate each input, eg. 123 + 9 * ( 4 / 2.5 ) + 100 / ( 2 ^ 4 )"
				+ " + 100 / ( 1 / 2 )\n"
				+ ">>>"
				);
		
	}
	
	/**
	 * Message to display on the program exiting
	 */
	public void exitMessage(){
		System.out.println("Thank you for using this program, we hope to see you again soon\n" +
				"BYE BYE");
		
	}
	
	
	/**
	 * This method compares two operator objects and returns a true boolean if the first passed operator has
	 * a precedence value less than the second, or equal to the second
	 * @param op1 the first operator to be compared
	 * @param op2 the second operator to be compared against
	 * @return boolean true if operator 1 precedence is less than or equal to operator 2, otherwise return false
	 */
	public boolean isOP1LessThanOrEqualOP2(Operator op1, Operator op2){
			
		if(op1.getPrecedence() <= op2.getPrecedence()){
			return true;
		}else{
		return false;}
	}
	
	/**
	 * This method compares two operator objects and returns a true boolean if the first passed operator has
	 * a precedence value less than the second
	 * @param op1 the first operator to be compared
	 * @param op2 the second operator to be compared against
	 * @return boolean true if operator 1 precedence is less than operator 2, otherwise return false
	 */
	public boolean isOP1LessThanOP2(Operator op1, Operator op2){
		
		if(op1.getPrecedence() < op2.getPrecedence()){
			return true;
		}else{
		return false;}
	}
	
	/**
	 * This method will return a boolean of true; if the string parameter is that of a number, otherwise it will return false
	 * @param str a single string that is to be checked if it is a number
	 * @return true boolean if the string passed is a number
	 */
	public boolean isANumber(String str){
		try{
			@SuppressWarnings("unused")
			double d = Double.parseDouble(str);
		}catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}
	
	/**
	 * This method will receive a calculation; that is held in a string and is written in infix form, then convert
	 * the infix form into an RPN postfix form and return that in a string.
	 * The method utilises the "shunting-yard algorithm", written by Edsger Dijkstra
	 * @param infixNotation the string to be passed into the method that contains a calculation; written in infix notation
	 * @return a string that is the same calculation as the passed in calculation; written in postfix notation (RPN)
	 */
	public  String convertInfixToRPN(String infixNotation){
		/**
		* boolean to aid in error handling, to detect if user input contains 2 successive operators
		*/
		boolean doubleOp = false;
		/**
		 * boolean to aid in error handling, to detect if user input contains 2 successive operands
		 */
		boolean doubleNum = false;
		String RPNString = new String();
		Deque<Operator> theStack = new ArrayDeque<Operator>();
		StringTokenizer infixTokens = new StringTokenizer(infixNotation, " ");
		
		while(infixTokens.hasMoreTokens()){
			String token = infixTokens.nextToken();
			
			if (isANumber(token)){
				RPNString = RPNString.concat(token);
				RPNString = RPNString.concat(" ");
				doubleOp = false;
				if(doubleNum){
					System.out.println("ERROR: Sum contains succeeding operands");
					System.exit(0);
				}
				doubleNum = true;
			}
			else{
				doubleNum = false;
				if(!(token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*") 
						|| token.equals("(") || token.equals(")") || token.equals("^"))){
					System.out.printf("ERROR: Invalid input argument in sum - <%s>", token);
					System.exit(0);
				}
				if (token.equals("(")){
					Operator bracket = OperatorFactory.getOperator(token);
					theStack.push(bracket);
					continue;
				}
				
				if (token.equals(")")){
					Operator stackValue = theStack.pop();
					
					while (!stackValue.getOp().equals("(")){
						RPNString = RPNString.concat(stackValue.getOp());
						RPNString = RPNString.concat(" ");
						
						if(theStack.isEmpty()){
							System.out.println("ERROR: Missmatched parenthesis, opening parenthesis not found");
							System.exit(0);
						}
						stackValue = theStack.pop(); // get next value on the stack. Stack will always have value here
					}
					continue;
				}
				if(doubleOp){
					System.out.println("ERROR: Sum contains succeeding operators");
					System.exit(0);
				}
				doubleOp = true;
				op1 = OperatorFactory.getOperator(token);
				
				if (theStack.isEmpty()){
					theStack.push(op1);
				}
				else{
					boolean isLeftAssociative = true;
					Operator op2 = theStack.pop();
					
					if(op1.getOp().equals("^")){
						isLeftAssociative = false;
					}
						
					if(isLeftAssociative){
						if (isOP1LessThanOrEqualOP2(op1, op2)){
							RPNString = RPNString.concat(op2.getOp());
							RPNString = RPNString.concat(" ");
						
						}
						else{
							theStack.push(op2);
						}
					
					}
					else{
						if (isOP1LessThanOP2(op1, op2))
						{
							RPNString = RPNString.concat(op2.getOp());
							RPNString = RPNString.concat(" ");
						
						}
						else{
							theStack.push(op2);
						}
						
					}
					
					theStack.push(op1);
				}
			}
			
		}
		while(!theStack.isEmpty()){
			Operator tempOp = theStack.pop();
			if(tempOp.getOp().equals("(")){
				System.out.println("ERROR: Missmatched parenthesis, closing parenthesis not found");
				System.exit(0);
			}
		RPNString = RPNString.concat(" ");	
		RPNString = RPNString.concat(tempOp.getOp());
		}
		return RPNString;
	}
	
	public void boot(){
		boolean running = true;
		while(running){
			initMessage();
			/**
			 * receive user input in infix notation
			 */
			String infixNotation = ui.nextLine();
			if(infixNotation.equalsIgnoreCase("Exit")){ //detect if the user would like to exit the program
				System.out.println("Thank you, we look forward to welcoming you back");
				System.exit(0);
			}
			
			String RPNString = convertInfixToRPN(infixNotation);
			/**
			 * tokenize the postfix calculation, delimiting via a single whitespace character
			 */
			StringTokenizer st = new StringTokenizer(RPNString, " ");
			
			while(st.hasMoreTokens()){ 
				String holdStr = st.nextToken(); //a string to hold the token that is to be processed
					
					if(isANumber(holdStr)){
						mInputStack.push(holdStr);
					}
					if(!isANumber(holdStr)){
						Operator op = OperatorFactory.getOperator(holdStr);
						double val1 = Double.parseDouble(mInputStack.pop());
						double val2 = Double.parseDouble(mInputStack.pop());
						double result = op.calculation(val2, val1);
						if(op.error == true){
							System.exit(0);
						}
						mInputStack.push(Double.toString(result));
					}
				
	
			}			
			/**
			 * a string that undergoes a formatting operation to present a readable result to the user
			 */
			String resultStr = String.format(mInputStack.element());
			System.out.println("Your calculation result is: " + resultStr + "\n");
		}
	}
	
	public static void main(String[] args) {
		CalculatorMain2 main = new CalculatorMain2();
		main.boot();
	}
}