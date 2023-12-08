public class CalendarTester {
	
	/**
	* Checks whether CalendarPrinter.isLeapYear() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
  public static boolean isLeapYearTest() {
    Year year1 = new Year(2000);
    Year year2 = new Year(1999);
    Year year3 = new Year(3001);

    if (CalendarPrinter.isLeapYear(year1)) {
      System.out.println("Leap Year Test 1 Passed.");
    } else {
      System.out.println("Leap Year Test 1 Failed.");
      return false;
    }
    if (CalendarPrinter.isLeapYear(year2)) {
      System.out.println("Leap Year Test 2 Failed.");
      return false;
    } else {
      System.out.println("Leap Year Test 2 Passed.");
    }
    if (CalendarPrinter.isLeapYear(year3)) {
      System.out.println("Leap Year Test 3 Failed.");
      return false;
    } else {
      System.out.println("Leap Year Test 3 Passed.");
    }
    return true;
  }

	/**
	* Checks whether CalendarPrinter.numberOfDaysInMonth() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
  public static boolean numberOfDaysInMonthTest() {
    Month month1 = new Month(MonthOfYear.January, new Year(2017));
    Month month2 = new Month(MonthOfYear.February, new Year(2020));
    Month month3 = new Month(MonthOfYear.February, new Year(3001));

    if (!(CalendarPrinter.numberOfDaysInMonth(month1) == 31)) {
      System.out.println("numberOfDaysInMonth Test 1 Failed.");
      return false;
    } else {
      System.out.println("numberOfDaysInMonth Test 1 Passed.");
    }
    if (!(CalendarPrinter.numberOfDaysInMonth(month2) == 29)) {
      System.out.println("numberOfDaysInMonth Test 2 Failed.");
      return false;
    } else {
      System.out.println("numberOfDaysInMonth Test 2 Passed.");
    }
    if (!(CalendarPrinter.numberOfDaysInMonth(month3) == 28)) {
      System.out.println("numberOfDaysInMonth Test 3 Failed.");
      return false;
    } else {
      System.out.println("numberOfDaysInMonth Test 3 Passed.");
    }
    return true;
  }

	/**
	* Checks whether CalendarPrinter.dayOfWeekAfter() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
  public static boolean dayOfWeekAfterTest() {
    if (!(CalendarPrinter.monthOfYearAfter(DayOfWeek.Monday) == DayOfWeek.Tuesday)) {
      System.out.println("dayOfWeekAfter Test 1 Failed.");
      return false;
    } else {
      System.out.println("dayOfWeekAfter Test 1 Passed.");
    }
    if (!(CalendarPrinter.monthOfYearAfter(DayOfWeek.Wednesday) == DayOfWeek.Thursday)) {
      System.out.println("dayOfWeekAfter Test 2 Failed.");
      return false;
    } else {
      System.out.println("dayOfWeekAfter Test 2 Passed.");
    }
    if (!(CalendarPrinter.monthOfYearAfter(DayOfWeek.Friday) == DayOfWeek.Saturday)) {
      System.out.println("dayOfWeekAfter Test 3 Failed.");
      return false;
    } else {
      System.out.println("dayOfWeekAfter Test 3 Passed.");
    }

    return true;
  }

	/**
	* Checks whether CalendarPrinter.monthOfYearAfter() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
  public static boolean monthOfYearAfterTest() {
    if (!(CalendarPrinter.monthOfYearAfter(MonthOfYear.January) == MonthOfYear.February)) {
      System.out.println("monthOfYearAfter Test 1 Failed.");
      return false;
    } else {
      System.out.println("monthOfYearAfter Test 1 Passed.");
    }
    if (!(CalendarPrinter.monthOfYearAfter(MonthOfYear.March) == MonthOfYear.April)) {
      System.out.println("monthOfYearAfter Test 2 Failed.");
      return false;
    } else {
      System.out.println("monthOfYearAfter Test 2 Passed.");
    }
    if (!(CalendarPrinter.monthOfYearAfter(MonthOfYear.May) == MonthOfYear.June)) {
      System.out.println("monthOfYearAfter Test 3 Failed.");
      return false;
    } else {
      System.out.println("monthOfYearAfter Test 3 Passed.");
    }

    return true;
  }

	/**
	* Checks whether CalendarPrinter.createNewMonth() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
  public static boolean createNewMonthTest() {
    Month testMonth1 = CalendarPrinter.createNewMonth(MonthOfYear.February, new Year(2020));
    Month testMonth2 = CalendarPrinter.createNewMonth(MonthOfYear.February, new Year(2021));
    Month testMonth3 = CalendarPrinter.createNewMonth(MonthOfYear.January, new Year(2020));

    if (!(testMonth1.getDayCount() == 29)) {
      System.out.println("createNewMonth Test 1 Failed.");
      return false;
    } else {
      System.out.println("createNewMonth Test 1 Passed.");
    }
    if (!(testMonth2.getDayCount() == 28)) {
      System.out.println("createNewMonth Test 2 Failed.");
      return false;
    } else {
      System.out.println("createNewMonth Test 2 Passed.");
    }
    if (!(testMonth3.getDayCount() == 31)) {
      System.out.println("createNewMonth Test 3 Failed.");
      return false;
    } else {
      System.out.println("createNewMonth Test 3 Passed.");
    }
    return true;
  }

	/**
	* Checks whether CalendarPrinter.fullCenturiesContained() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
  public static boolean testFullCenturiesContained() {
    if (CalendarPrinter.fullCenturiesContained(new Year(2)) != 0)
      return false;
    if (CalendarPrinter.fullCenturiesContained(new Year(2020)) != 20)
      return false;
    if (CalendarPrinter.fullCenturiesContained(new Year(44444)) != 444)
      return false;
    return true;
  }

	/**
	* Checks whether CalendarPrinter.yearOffsetWithingCentury() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
  public static boolean testYearOffsetWithinCentury() {
    if (CalendarPrinter.yearOffsetWithinCentury(new Year(540)) != 40)
      return false;
    if (CalendarPrinter.yearOffsetWithinCentury(new Year(530)) != 30)
      return false;
    if (CalendarPrinter.yearOffsetWithinCentury(new Year(520)) != 20)
      return false;
    return true;
  }

	/**
	* Checks whether CalendarPrinter.calcFirstDayOfWeekInMonth() method works as expected.
	* @return true when this test verifies a correct functionality, and false otherwise
	*/
  public static boolean testCalcFirstDayOfWeekInMonth() {
    Month month = new Month(MonthOfYear.February, new Year(2020));
    Month month2 = new Month(MonthOfYear.January, new Year(2020));
    Month month3 = new Month(MonthOfYear.December, new Year(2019));
    if (CalendarPrinter.calcFirstDayOfWeekInMonth(month) != DayOfWeek.Saturday)
      return false;
    if (CalendarPrinter.calcFirstDayOfWeekInMonth(month2) != DayOfWeek.Wednesday)
      return false;
    if (CalendarPrinter.calcFirstDayOfWeekInMonth(month3) != DayOfWeek.Sunday)
      return false;
    return true;
  }

  public static void main(String[] args) {
    System.out.println("isLeapYearTest(): " + isLeapYearTest());
    System.out.println("numberOfDaysInMonthTest(): " + numberOfDaysInMonthTest());
    System.out.println("dayOfWeekAfterTest(): " + dayOfWeekAfterTest());
    System.out.println("monthOfYearAfterTest(): " + monthOfYearAfterTest());
    System.out.println("createNewMonthTest(): " + createNewMonthTest());
    System.out.println("testFullCenturiesContained(): " + testFullCenturiesContained());
    System.out.println("testYearOffsetWithinCentury(): " + testYearOffsetWithinCentury());
    System.out.println("testCalcFirstDayOfWeekInMonth(): " + testCalcFirstDayOfWeekInMonth());
  }
}