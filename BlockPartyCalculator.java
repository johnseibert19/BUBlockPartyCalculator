import java.util.Scanner;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 * Identifies the Saturday closest to April 20th of a given year. Copy/paste this into an online Java compiler like this (https://www.programiz.com/java-programming/online-compiler/) or 
 * set up the JDK environment if you truly wanna learn Java for yourself.
 * @param args - command line arguments (unused)
 */
public class BlockPartyCalculator {

public static void main(String[] args) 
{
    System.out.println("Welcome to the Block Party Calculator!");
    System.out.print("What year do you want to calculate? ");

    Scanner scan = new Scanner(System.in);
    int year = scan.nextInt();
    System.out.print("\n");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
    LocalDate blockParty = calculateBlock(year); 
    System.out.println("Block Party for " + year + " is: " + blockParty.format(formatter));
    findNextBlock();
}


/**
 * Identifies the date of the next iteration of Block Party, BU's annual bash on Fetterman Ave.
 * @param year user-supplied year
 * @return Date of next block party
 */
public static LocalDate calculateBlock(int year) {
  LocalDate targetDate = LocalDate.of(year, 4, 20);
  DayOfWeek targetDay = targetDate.getDayOfWeek();

  int offsetDays = 0;
  switch (targetDay) 
  {
    case SATURDAY:
      break;
    case SUNDAY:
      offsetDays = -1;
      break;
    case MONDAY:
      offsetDays = -2;
      break;
    case TUESDAY:
      offsetDays = -3;
      break;
    case WEDNESDAY:
      offsetDays = 3;
      break;
    case THURSDAY:
      offsetDays = 2;
      break;
    case FRIDAY:
      offsetDays = 1;
      break;
  }
  return targetDate.plusDays(offsetDays);
}

/**
 * Finds the days between the next Block Party!
 */
public static void findNextBlock() 
{
    LocalDate today = LocalDate.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd yyyy");
    if (today.isBefore(calculateBlock(today.getYear()))) {
      System.out.println("The next Block Party is on: " + dtf.format(calculateBlock(today.getYear())));
      LocalDate blockParty = calculateBlock(today.getYear() + 1);
      long daysBetween = ChronoUnit.DAYS.between(today, blockParty);
      System.out.println(daysBetween + " days until Block!");
    } else if (today.isAfter(calculateBlock(today.getYear()))) {
      System.out.println("The next Block Party is on: " + dtf.format(calculateBlock(today.getYear() + 1)));
      LocalDate blockParty = calculateBlock(today.getYear() + 1);
      long daysBetween = ChronoUnit.DAYS.between(today, blockParty);
      System.out.println(daysBetween + " days until Block!");
    } else {
      System.out.println("Happy Block! There are 0 days until Block!");
    }
}
}
