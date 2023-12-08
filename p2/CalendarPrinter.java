import java.util.Scanner;
import java.util.ArrayList;

public class CalendarPrinter {

  /**
   * Calculates the number of centuries (rounded down) between year 0 and the specified year. For
   * example, the year 2034 has the century index of 20 (as do the other years 2000-2099).
   * 
   * @param year to compute the century offset for
   * @return number of centuries between year 0 and the specified year
   */
  public static int fullCenturiesContained(Year year) {
    return year.intValue() / 100;
  }

  /**
   * This method computes whether the specified year is a leap year or not.
   * 
   * @param year is the year is being checked for leap-year-ness
   * @return true when the specified year is a leap year, and false otherwise
   */
  public static boolean isLeapYear(Year year) {
    if ((year.intValue() % 4 == 0)
        && ((year.intValue() % 100 != 0) || (year.intValue() % 400 == 0))) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Calculates the number of years between the specified year and the first year of its century.
   * For example, the year 2034 has the offset within its century of 34 (as do 1234 and 9999934).
   * 
   * @param year to compute the offset within century for
   * @return number of years between the specified year and the first year of century
   */
  public static int yearOffsetWithinCentury(Year year) {
    int century = Integer.parseInt(
        year.toString().substring(year.toString().length() - 3, year.toString().length() - 2))
        * 100;
    return (year.intValue() - century);
  }

  /**
   * Calculates the number of days in the specified month, while taking into consideration whether
   * or not the specified month is in a leap year. Note: that this is calculated based on the
   * month's monthOfYear and year, and is NOT retrieved from the month's getDayCount() method. This
   * is because this method is used to generate the day objects that are stored within each month.
   * 
   * @param month to determine the number of days within (within its year)
   * @return the number of days in the specified month (between 28-31)
   */
  public static int numberOfDaysInMonth(Month month) {
    int days = 0;
    if ((isLeapYear(month.getYear()) && month.getMonthOfYear().equals(MonthOfYear.February))) {
      days = 29;
    } else if (month.getMonthOfYear().equals(MonthOfYear.February)) {
      days = 28;
    } else if (month.getMonthOfYear().equals(MonthOfYear.January)
        || month.getMonthOfYear().equals(MonthOfYear.March)
        || month.getMonthOfYear().equals(MonthOfYear.May)
        || month.getMonthOfYear().equals(MonthOfYear.July)
        || month.getMonthOfYear().equals(MonthOfYear.August)
        || month.getMonthOfYear().equals(MonthOfYear.October)
        || month.getMonthOfYear().equals(MonthOfYear.December)) {
      days = 31;
    } else {
      days = 30;
    }
    return days;
  }

  /**
   * Calculates which day of the week the first day of the specified month falls on. Note: that this
   * is calculated based on the month's monthOfYear and year, and is NOT retrieved from the month's
   * getDayByDate(1) day. This is because this method is used to generate the day objects that are
   * stored within each month.
   * 
   * @param month within which we are calculate the day of week for the first day
   * @return DayOfWeek corresponding to the first day within the specified month
   */
  public static DayOfWeek calcFirstDayOfWeekInMonth(Month month) {
    MonthOfYear currentMonth = month.getMonthOfYear();
    Year year = month.getYear();
    if (currentMonth == MonthOfYear.January || currentMonth == MonthOfYear.February)
      year = new Year(year.intValue() - 1);
    int lastTwo = Integer.parseInt(year.toString().substring(year.toString().length() - 2));
    int monthNumber = 0;
    int date = 1;
    switch (currentMonth) {
      case January:
        monthNumber = 11;
        break;
      case February:
        monthNumber = 12;
        break;
      case March:
        monthNumber = 1;
        break;
      case April:
        monthNumber = 2;
        break;
      case May:
        monthNumber = 3;
        break;
      case June:
        monthNumber = 4;
        break;
      case July:
        monthNumber = 5;
        break;
      case August:
        monthNumber = 6;
        break;
      case September:
        monthNumber = 7;
        break;
      case October:
        monthNumber = 8;
        break;
      case November:
        monthNumber = 9;
        break;
      case December:
        monthNumber = 10;
        break;
      default:
        break;
    };
    int century = Integer.parseInt(year.toString().substring(0, year.toString().length() - 2));
    int answer =
        date + ((13 * monthNumber - 1) / 5) + lastTwo + (lastTwo / 4) + (century / 4) - 2 * century;
    answer %= 7;
    if(answer<0)
    	answer=Math.abs(answer)+1;
    switch (answer) {
      case 0:
        return DayOfWeek.Sunday;
      case 1:
        return DayOfWeek.Monday;
      case 2:
        return DayOfWeek.Tuesday;
      case 3:
        return DayOfWeek.Wednesday;
      case 4:
        return DayOfWeek.Thursday;
      case 5:
        return DayOfWeek.Friday;
      case 6:
        return DayOfWeek.Saturday;
      case 7:
        return DayOfWeek.Sunday;
      default:
        break;
    };
    return null;
  }

  /**
   * Calculates the day of the week that follows the specified day of week. For example, Thursday
   * comes after Wednesday, and Monday comes after Sunday.
   * 
   * @param dayBefore is the day of week that comes before the day of week returned
   * @return day of week that comes after dayBefore
   */
  public static DayOfWeek monthOfYearAfter(DayOfWeek dayBefore) {
    if (dayBefore == DayOfWeek.Monday) {
      return DayOfWeek.Tuesday;
    } else if (dayBefore == DayOfWeek.Tuesday) {
      return DayOfWeek.Wednesday;
    } else if (dayBefore == DayOfWeek.Wednesday) {
      return DayOfWeek.Thursday;
    } else if (dayBefore == DayOfWeek.Thursday) {
      return DayOfWeek.Friday;
    } else if (dayBefore == DayOfWeek.Friday) {
      return DayOfWeek.Saturday;
    } else if (dayBefore == DayOfWeek.Saturday) {
      return DayOfWeek.Sunday;
    } else {
      return DayOfWeek.Monday;
    }
  }

  /**
   * Calculates the month of the year that follows the specified month. For example, July comes
   * after June, and January comes after December.
   * 
   * @param monthBefore is the month that comes before the month that is returned
   * @return month of year that comes after monthBefore
   */
  public static MonthOfYear monthOfYearAfter(MonthOfYear monthBefore) {
    if (monthBefore == MonthOfYear.January) {
      return MonthOfYear.February;
    } else if (monthBefore == MonthOfYear.February) {
      return MonthOfYear.March;
    } else if (monthBefore == MonthOfYear.March) {
      return MonthOfYear.April;
    } else if (monthBefore == MonthOfYear.April) {
      return MonthOfYear.May;
    } else if (monthBefore == MonthOfYear.May) {
      return MonthOfYear.June;
    } else if (monthBefore == MonthOfYear.June) {
      return MonthOfYear.July;
    } else if (monthBefore == MonthOfYear.July) {
      return MonthOfYear.August;
    } else if (monthBefore == MonthOfYear.August) {
      return MonthOfYear.September;
    } else if (monthBefore == MonthOfYear.September) {
      return MonthOfYear.October;
    } else if (monthBefore == MonthOfYear.October) {
      return MonthOfYear.November;
    } else if (monthBefore == MonthOfYear.November) {
      return MonthOfYear.December;
    } else {
      return MonthOfYear.January;
    }
  }

  /**
   * Creates a new month object and fully initializes with its corresponding days.
   * 
   * @param monthOfYear which month of the year this new month represents
   * @param year        in which this month is a part
   * @return reference to the newly created month object
   */
  public static Month createNewMonth(MonthOfYear monthOfYear, Year year) {
    Month newMonth = new Month(monthOfYear, year);
    int days = 0;
    Day currentDay = new Day(calcFirstDayOfWeekInMonth(newMonth), 1, newMonth);
    newMonth.addDay(currentDay);
    days = numberOfDaysInMonth(newMonth);

    for (int i = 2; i <= days; i++) {
      Day nextDay = new Day(monthOfYearAfter(currentDay.getDayOfWeek()), i, newMonth);
      newMonth.addDay(nextDay);
      currentDay = nextDay;
    }
    return newMonth;
  }

  /**
   * Prints the contents of the specified month object in calendar form. This printout should begin
   * with the Month an year of the month. The next line should contain the three letter
   * abbreviations for the seven days of the week. And then the dates of each day of that month
   * should follow: one week per line, with periods in positions of days that are a part of the
   * month before or after. For example, January 2020 should be printed as follows:
   *
   * January 2020 MON TUE WED THU FRI SAT SUN . . 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
   * 21 22 23 24 25 26 27 28 29 30 31 . .
   *
   * @param month which is to be printed by this method
   */

  public static void printMonth(Month month) {
    MonthOfYear currentMonth = month.getMonthOfYear();
    Year year = month.getYear();
    System.out.println(currentMonth.toString() + " " + year.toString());
    System.out.println(
        "MON" + " " + "TUE" + " " + "WED" + " " + "THU" + " " + "FRI" + " " + "SAT" + " " + "SUN");
    DayOfWeek firstDay = calcFirstDayOfWeekInMonth(month);
    int numberOfDays = numberOfDaysInMonth(month);
    int last;
    switch (firstDay) {
      case Monday:
        System.out.println(" 1   2   3   4   5   6   7");
        last = 8;
        break;
      case Tuesday:
        System.out.println(" .   1   2   3   4   5   6");
        last = 7;
        break;
      case Wednesday:
        System.out.println(" .   .   1   2   3   4   5");
        last = 6;
        break;
      case Thursday:
        System.out.println(" .   .   .   1   2   3   4");
        last = 5;
        break;
      case Friday:
        System.out.println(" .   .   .   .   1   2   3");
        last = 4;
        break;
      case Saturday:
        System.out.println(" .   .   .   .   .   1   2");
        last = 3;
        break;
      case Sunday:
        System.out.println(" .   .   .   .   .   .   1");
        last = 2;
        break;
      default:
        last = 0;
        break;
    };
    int counter = 0;
    for (int i = last; i <= numberOfDays; i++) {
      if (counter == 7) {
        System.out.println();
        counter = 0;
      }
      if (i < 10) {
        System.out.print(" " + i + "  ");
        counter++;
      } else {
        System.out.print(" " + i + " ");
        counter++;
      }
    }
  }

  /**
   * Creates an array list of months that are initialized with their full complement of days. Prints
   * out each of these months in calendar form, and then returns the resulting ArrayList.
   * 
   * @param month of the year for the first month that is created and printed
   * @param year  that the first month created and printed is a part of
   * @param count is the total number of sequential months created and printed
   */
  public static ArrayList<Month> createAndPrintMonths(MonthOfYear month, Year year, int count) {
    ArrayList<Month> answer = new ArrayList<Month>();
    for (int i = 0; i < count; i++) {
      Month currentMonth = createNewMonth(month, year);
      answer.add(currentMonth);
      printMonth(currentMonth);
      System.out.println();
      month = monthOfYearAfter(month);
      if (month == MonthOfYear.January)
        year = new Year(year.intValue() + 1);
    }

    return answer;
  }

  /**
   * Driver for the CalendarPrinter Application. This program asks the user to enter an initial
   * month, an initial year, and the total number of months to create and display in calendar form.
   * 
   * @param args is not used by this program
   */
  public static void main(String[] args) {
    // print welcome message
    Scanner in = new Scanner(System.in);
    System.out.println("Welcome to the Calendar Printer.");
    System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
    // read input from the user
    System.out.print("Enter the month to begin calendar: ");
    String monthString = in.nextLine().trim();
    System.out.print("Enter the year to begin calendar: ");
    String yearString = in.nextLine().trim();
    System.out.print("Enter the number of months to include in this calendar: ");
    String countString = in.nextLine().trim();
    // convert user input into usable form
    monthString = monthString.substring(0, 3).toUpperCase();
    MonthOfYear month = null;
    for (int i = 0; i < MonthOfYear.values().length; i++)
      if (monthString.equals(MonthOfYear.values()[i].name().substring(0, 3).toUpperCase()))
        month = MonthOfYear.values()[i];
    Year year = new Year(Integer.parseInt(yearString.trim()));
    int count = Integer.parseInt(countString.trim());
    // create months and display them in calendar form
    System.out.println();
    createAndPrintMonths(month, year, count);
    // display thank you message
    System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
    System.out.println("Thanks, and have a nice day.");
    in.close();
  }
}
