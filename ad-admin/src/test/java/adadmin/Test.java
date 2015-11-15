package adadmin;

import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) {
		
	BigDecimal d	= new BigDecimal((1234+0.0f)/100);
	d.setScale(2, BigDecimal.ROUND_DOWN);
	System.out.println(d.setScale(2, BigDecimal.ROUND_DOWN));
	}
}
