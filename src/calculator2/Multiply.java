package calculator2;
/**
 * This class is a child of the operator class, it extends this class with the addition of a precedence constant 
 * and acts as it would as a mathematical operator. 
 * @author Aaron Morris
 *	
 */
public class Multiply extends Operator{
	
	private final int PRECEDENCE = 4;
	
	public Multiply(String str){
		this.setOp(str);
	}
	
	@Override
	public double calculation(double in1, double in2){
		setVals(in1,in2);
		result = mVal1 * mVal2;
		return result;
	}

	public int getPrecedence(){
		return this.PRECEDENCE;
	}

}