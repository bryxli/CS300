public class PiggyBankTester {
	
	/**
	* Checks whether PiggyBank.removeCoin() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
	public static boolean removeCoinTestMethod() {
		int[] testBank= {25,25,10,5,5,1,1,1,0,0};
		int size=8;
		return PiggyBank.removeCoin(testBank, size)==7;
	}
	
	/**
	* Checks whether PiggyBank.emptyPiggyBank() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
	public static boolean emptyPiggyBankTestMethod() {
		int[] testBank= {25,25,10,5,5,1,1,1,0,0};
		int size=8;
		return PiggyBank.emptyPiggyBank(testBank, size)==0;
	}
	
	/**
	* Checks whether PiggyBank.withdraw() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
	public static boolean withdrawTestMethod() {
		int[] testBank={1,1,5,5,5,10,10,25,25,0};
		int size=9;
		int[] arrayTest=PiggyBank.withdraw(40, testBank, size);
		int[] answer= {6,1,1,1,0};
		for(int i=0;i<4;i++)
			if(arrayTest[i]!=answer[i])
				return false;
		return true;
	}
	
	/**
	* Checks whether PiggyBank.getSpecificCoinCount() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
    public static boolean getSpecificCoinCountTestMethod() {
        int[] coins = new int[10]; // array storing the coins held in a piggy bank
        int size = 0; // number of coins held in coins array
        if(PiggyBank.getSpecificCoinCount(1, coins, size) != 0) {
            System.out.println("Problem detected. Method is counting with a size of 0");
            return false;
        }
       
        coins = new int[] {10, 1, 5, 25, 1, 0, 0};
        size = 5;
        if(PiggyBank.getSpecificCoinCount(1, coins, size) != 2) {
            System.out.println("Problem detected. Miscounted the coins");
            return false;
        }
       
        coins = new int[] {10, 1, 5, 25, 1, 0};
        size = 3;
        if(PiggyBank.getSpecificCoinCount(1, coins, size) != 1) {
            System.out.println("Problem detected. Counting past the size.");
            return false;
        }
       
        return true;
    }
	
	public static void main(String[] args) {
		System.out.println(removeCoinTestMethod());
		System.out.println(emptyPiggyBankTestMethod());
		System.out.println(withdrawTestMethod());
	}

}
