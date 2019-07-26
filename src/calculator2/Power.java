package calculator2;
/**
 * This class is a child of the operator class, it extends this class with the addition of a precedence constant 
 * and acts as it would as a mathematical operator. 
 * @author Aaron Morris
 *	
 */
public class Power extends Operator{
	
	
	public Power(String str){
		this.setOperator(str);
	}

	@Override
	public double calculation(double in1, double in2){
		setVals(in1,in2);
		double result = 1;
		while(mVal2 != 0){
			result *= mVal1;
			mVal2--;
		}
		return result;
	}

	public void setOperator(String str){
		setOp(str);
	}


	
}