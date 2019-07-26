package calculator2;
/**
 * The left parenthesis class acts the same as the mathematical operator, it holds position in the stack until a right parenthesis
 * is found by the program. Once this occurs; the program works backwards until the left parenthesis is found, as an indicator
 * to when the highest precedence sum contained is complete. 
 * @author Aaron Morris
 *	
 */
public class LeftParenthesis extends Operator {
		
	public LeftParenthesis(String str){
		this.setOp(str);
		
	}
}
