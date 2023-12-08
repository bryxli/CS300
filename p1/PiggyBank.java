import java.util.Random;
public class PiggyBank {
	public final static int[] COINS_VALUES = {1, 5, 10, 25}; // coins values in cents
	public final static String[] COINS_NAMES = {"PENNY", "NICKEL", "DIME", "QUARTER"};
	public final static String NO_SUCH_COIN = "N/A"; // N/A string
	private final static Random RAND_GEN = new Random(100); // generator of random integers
	
	  /**
	    * Returns the balance of a piggy bank in cents
	    * @param coins an oversize array which contains all the coins held in a piggy bank
	    * @param size the total number of coins stored in coins array
	    * @return the total value of the coins held in a piggy bank in cents
	    */
	    public static int getBalance(int[] coins, int size) {
	        int balance = 0;
	        for (int i = 0; i < size; i++) {
	            balance += coins[i];
	        }
	        return balance;
	    }
	   
	    /**
	    * Returns the total number of coins of a specific coin value held in a piggy bank
	    *
	    * @param coinValue the value of a specific coin
	    * @param coins an oversize array which contains all the coins held in a piggy
	    * bank
	    * @param size the total number of coins stored in coins array
	    * @return the number of coins of value coinValue stored in the array coins
	    */
	    public static int getSpecificCoinCount(int coinValue, int[] coins, int size) {
	        int count = 0;
	        for (int i = 0; i < size; i++) {
	            if (coins[i] == coinValue) {
	                count++;
	            }
	        }
	        return count;
	    }
	   
	    /**
	    * Displays information about the content of a piggy bank
	    *
	    * @param coins an oversize array storing the coins held in a piggy bank
	    * @param size number of coin held array coins
	    */
	    public static void printPiggyBank(int[] coins, int size) {
	        System.out.println("QUARTERS: " + getSpecificCoinCount(25, coins, size));
	        System.out.println("DIMES: " + getSpecificCoinCount(10, coins, size));
	        System.out.println("NICKELS: " + getSpecificCoinCount(5, coins, size));
	        System.out.println("PENNIES: "+getSpecificCoinCount(1, coins, size));
	        System.out.println("Balance: $" + getBalance(coins, size)*0.01);
	    }
	   
	    /**
	    * Adds a given coin to a piggy bank. This operation can terminate
	    * successfully or unsuccessfully. For either cases, this method
	    * displays a descriptive message for either cases.
	    *
	    * @param coin the coin value in cents to be added to the array coins
	    * @param coins an oversize array storing the coins held in a piggy bank
	    * @param size the total number of coins contained in the array coins
	    * before this addCoin method is called.
	    * @return the new size of the coins array after trying to add coin.
	    */
	    public static int addCoin(int coin, int[] coins, int size) {
	        for (int i = 0; i <= 3; i++) {
	            if (coin == COINS_VALUES[i]) {
	                if (size >= coins.length) {
	                    System.out.println("\"Tried to add a \"" + COINS_NAMES[i] + " \", but could not because the piggy bank is full.\"");
	                    return size;
	                } else {
	                    coins[size] = coin;
	                    System.out.println("Added a " + COINS_NAMES[i] + ".");
	                    return (size + 1);
	                }
	            }
	        }
	        System.out.println(coin + " is a not a valid US currency coin.");
	        return size;
	    }
	   
	    /**
	    * Returns the name of a specified coin value
	    * @param coin represents a coin value in cents.
	    * @return the String name of a specified coin value if it is valid and N/A if the
	    * coin value is not valid
	    */
	    public static String getCoinName(int coin) {
	        for (int i = 0; i < COINS_VALUES.length; i++) {
	            if (coin == COINS_VALUES[i]) {
	                return COINS_NAMES[i];
	            }
	        }
	        return NO_SUCH_COIN;
	    }
	
	/**
	* Removes an arbitrary coin from a piggy bank. This method may terminate
	* successfully or unsuccessfully. In either cases, a descriptive message
	* is displayed.
	*
	* @param coins an oversize array which contains the coins held in a piggy bank
	* @param size the number of coins held in the coins array before this method
	* is called
	* @return the size of coins array after this method returns successfully
	*/
	public static int removeCoin(int[] coins, int size) {//shift
		boolean empty=true;
		for(int i=0;i<size;i++) {
			if(coins[i]!=0)
				empty=false;
		}
		if(empty)
			System.out.println("Tried to remove a coin, but could not because the piggy bank is empty.");
		else {
			boolean loop=true;
			while(loop) {
				int number=RAND_GEN.nextInt(size);
				if(number<size&&coins[number]!=0) {
					int looper=number;
					do {
						looper++;
					}
					while(coins[looper]!=0);
					coins[number]=coins[looper];
					coins[looper]=0;
					loop=false;
				}
			}
			return --size;
		}
		return size;
	}
	
	/**
	* Removes all the coins in a piggy bank. It also displays the total value
	* of the removed coins
	*
	* @param coins an oversize array storing the coins held in a piggy bank application
	* @param size number of coins held in coins array before this method is called
	* @return the new size of the piggy bank after removing all its coins.
	*/
	public static int emptyPiggyBank(int[] coins, int size) {
		boolean empty=true;
		int counter=0;
		for(int i=0;i<size;i++) {
			if(coins[i]!=0)
				empty=false;
		}
		if(empty)
			System.out.println("Zero coin removed. The piggy bank is already empty.");
		else {
			for(int i=0;i<size;i++) {
				counter+=coins[i];
				coins[i]=0;
			}
			System.out.println("All done. " + counter + " cents removed.");
			return 0;
		}
		return size;
	}
	
	/**
	* Removes the least number of coins needed to fulfill a withdrawal request
	*
	* @param amount amount to withdraw given in cents
	* @param coins an oversize array storing the coins held in a piggy bank
	* @param size number of coins stored in coins array before this method is called
	* @return perfect size array of 5 elements, index 0 stores the new size of
	* the piggy bank after this method returns. Indexes 1, 2, 3, and 4
	* store respectively the number of removed quarters, dimes,
	* nickels, and pennies.
	*/
	public static int[] withdraw(int amount, int[] coins, int size) {
		int[] answer=new int[5];
		int withdrawn=0;
		int[] coinAmount=new int[4];
		int removed=0;
		coinAmount[3]=getSpecificCoinCount(25,coins,size);
		coinAmount[2]=getSpecificCoinCount(10,coins,size);
		coinAmount[1]=getSpecificCoinCount(5,coins,size);
		coinAmount[0]=getSpecificCoinCount(1,coins,size);
		int current=3;
		if(getBalance(coins,size)<amount)
			System.out.println("Unable to withdraw " + amount + " cents. NOT enough money in the piggy bank.");
		else {
			while(withdrawn<amount) {
				int amountLeft=0;
				int needed=amount-withdrawn;
				for(int j=current-1;j>=0;j--) {
					amountLeft+=(coinAmount[j]*COINS_VALUES[j]);
				}
				if(coinAmount[current]==0)
					current--;
				else if(COINS_VALUES[current]>needed&&amountLeft>needed) {
					current--;
				}
				else {
					for(int i=0;i<size;i++) {
						if(coins[i]==COINS_VALUES[current]) {
							withdrawn+=coins[i];
							int element=0;
							try {
								while(coins[element]!=0)
									element++;
							}
							catch (ArrayIndexOutOfBoundsException e) {
							}
							coins[i]=coins[--element];
							coins[element]=0;
							removed++;
							break;
						}
					}
				}
			}

		}
		answer[0]=size-removed;
		coinAmount[3]-=getSpecificCoinCount(25,coins,size);
		coinAmount[2]-=getSpecificCoinCount(10,coins,size);
		coinAmount[1]-=getSpecificCoinCount(5,coins,size);
		coinAmount[0]-=getSpecificCoinCount(1,coins,size);
		answer[1]=coinAmount[3];
		answer[2]=coinAmount[2];
		answer[3]=coinAmount[1];
		answer[4]=coinAmount[0];
		return answer;
	}
	

}
