package com.datacenter.integ;

public enum Complexity {

	one(10),
	two(20),
	three(40),
	four(60),
	five(80);
	private int value;


	// CONSTRUCTOR
	private Complexity(int value) {
		this.value = value;
	}

	// GET COMPLEXITY LEVEL
	public int getComplexityID(int complexity){
		int complexityValue = 0;
		int i = 0;
		for (Complexity c: Complexity.values()){
			i++;
			if (i == complexity){
				complexityValue = c.value;
				System.out.println("Complexity value returned is "+complexityValue);
			} 
		} // END OF LOOP
		return complexityValue;
	} // END OF METHOD


	@Override
	public String toString() {

		switch (this) {

		case one:
			System.out.println("Penny: " + value);
			break;

		case two:
			System.out.println("Nickle: " + value);
			break;

		case three:

			System.out.println("Dime: " + value);
			break;

		case four:
			System.out.println("Quarter: " + value);

		}

		return super.toString();

	}
}

