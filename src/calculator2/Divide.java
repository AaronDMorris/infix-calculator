package calculator2;
/**
 * The divide class is a child of the operator class, it extends this class with the addition of a precedence constant 
 * and acts as it would as a mathematical operator. 
 * @author Aaron Morris
 *	
 */
public class Divide extends Operator{
	
	private final int PRECEDENCE = 4;
	
	public Divide(String str){
		this.setOp(str);
	}
	
	@Override
	public double calculation(double in1, double in2){
		setVals(in1,in2);
		if(mVal1 == 0 || mVal2 == 0){
			error = true;
			System.out.println("ERROR: You cannot divide by zero");
			return 0;
			
		}
		result = mVal1 / mVal2;
		return result;
	}

	public int getPrecedence(){
		return this.PRECEDENCE;
	}

}