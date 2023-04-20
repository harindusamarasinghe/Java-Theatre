import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Theatre {
    //declaring and initialising three separate arrays to store row information
    //declaring them as public in-order to access them through the code snit
    public static int[] firstRow = new int[12];
    public static int[] secondRow = new int[16];
    public static int[] thirdRow = new int[20];

    //declaring an arraylist of tickets as ticket-list
    public static ArrayList<Ticket> ticketList = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Welcome to the New Theatre");
        while (true) {
            System.out.println("------------------------------------------------\n" +
                    "Please select an option:\n" +
                    "1) Buy a ticket\n" +
                    "2) Print seating area\n" +
                    "3) Cancel ticket\n" +
                    "4) List available seats\n" +
                    "5) Save\n" +
                    "6) Load\n" +
                    "7) Print ticket information and total price\n" +
                    "8) Sort tickets by price\n" +
                    "0) Quit\n" +
                    "------------------------------------------------");
            int option;
            System.out.print("Enter option :");
            try {
                Scanner input = new Scanner(System.in);
                option = input.nextInt();
            } catch (Exception e) {
                //throwing an error if the user inputs anything other than a number
                System.out.println("Invalid input !");
                continue;
            }
            //using a switch case to run the methods according to the input of the user
            switch (option) {
                case 1:
                    buy_ticket();
                    break;
                case 2:
                    print_seating_area(firstRow, secondRow, thirdRow);

                    break;
                case 3:
                    cancel_ticket();
                    break;
                case 4:
                    show_available("Seats available in row 1:", firstRow);
                    show_available("Seats available in row 2:", secondRow);
                    show_available("Seats available in row 3:", thirdRow);
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    show_tickets_info();
                    break;
                case 8:
                    sort_tickets();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option !");
            }
        }
    }


    public static void buy_ticket() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your name :");
        //validating the user's input for the name
        //it should only contain alphabetical letters
        while (!input.hasNext("[a-zA-Z]+")) {
            System.out.println("Invalid input.");
            System.out.print("Please enter alphabetical characters as your name :");
            input.next();
        }
        String name = input.next();
        //validating the user's input for the Surname
        //it should only contain alphabetical letters
        System.out.print("Please enter your surname :");
        while (!input.hasNext("[a-zA-Z]+")) {
            System.out.println("Invalid input.");
            System.out.print("Please enter alphabetical characters as your Surname : ");
            input.next();
        }
        String surname = input.next();
        System.out.print("Please enter your Email :");
        String email = input.next();
        //validating the email address using Regex
        //reference-https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
        while (!email.matches("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.\\p{L}{2,})$")) {
            System.out.print("Invalid email,Please retype your email:");
            email = input.next();
        }
        //creating an object called "user" with Person class
        //using the attributes given by the user
        Person user = new Person(name, surname, email);

        double price;
        while (true) {
            //stating a ticket price range for users
            System.out.print("Please enter the prize of your ticket (>50$) :");
            String x = input.next();
            try {
                //returns the double value represented by the string argument
                price = Double.parseDouble(x);
                if (price >= 50) {
                    break;
                } else {
                    //if the price inputted is less than 50 , printing an error message
                    System.out.println("Sorry the Price you entered is not allowed!\n");
                }
            } catch (NumberFormatException e) {
                //if the price entered is not a number , print error message
                System.out.println("Invalid input for a ticket price, please re-enter!!\n");
            }
        }

        while (true) {
            int row, seat;
            try {
                System.out.print("Please enter your desired Row number :");
                row = input.nextInt();
            } catch (Exception e) {
                //throwing an error if the row number inputted is not a number
                System.out.println("Invalid input!Please enter a number as row");
                return;
            }
            if (row == 1 || row == 2 || row == 3) {
                boolean isBooked = false;
                try {
                    System.out.print("Please enter your desired Seat number :");
                    seat = input.nextInt();
                } catch (Exception e) {
                    //throwing an error if the seat number inputted is not a number
                    System.out.println("Invalid input!Please enter a number as seat !");
                    return;
                }
                if (row == 1 && 0 < seat && seat < 13) {
                    if (firstRow[seat - 1] != 1) {
                        firstRow[seat - 1] = 1;
                        isBooked = true;

                    } else {
                        System.out.println("Sorry, The seat is already booked!");
                    }
                } else if (row == 2 && 0 < seat && seat < 16) {
                    if (secondRow[seat - 1] != 1) {
                        secondRow[seat - 1] = 1;
                        isBooked = true;
                    } else {
                        System.out.println("Sorry, The seat is already booked!");
                    }
                } else if (row == 3 && 0 < seat && seat < 21) {

                    if (thirdRow[seat - 1] != 1) {
                        thirdRow[seat - 1] = 1;
                        isBooked = true;
                    } else {
                        System.out.println("Sorry, The seat is already booked!");
                    }
                } else {
                    System.out.println("Invalid Seat number!");
                }
                if (isBooked) {
                    System.out.println("Booking is completed !");
                    //creating an object called ticket
                    Ticket ticket = new Ticket(row, seat, price, user);
                    //adding the ticket object to the arraylist
                    ticketList.add(ticket);
                    break;
                }
            } else {
                System.out.println("Error: The row number you entered does not exist. Please select 1-3.");
            }
        }
    }

    public static void print_seating_area(int[] row1, int[] row2, int[] row3) {
        System.out.printf("%10s%-10s\n", "******", "*******");
        System.out.printf("%10sA%-10s\n", "*   ST", "GE   *");
        System.out.printf("%10s%-10s\n", "******", "*******");
        //formatting the seating area with a user build method getSeatArea
        System.out.printf("    %6s %6s\n", getSeatArea(row1, 0, 6), getSeatArea(row1, 6, 12));
        System.out.printf("  %8s %8s\n", getSeatArea(row2, 0, 8), getSeatArea(row2, 8, 16));
        System.out.printf("%10s %10s\n", getSeatArea(row3, 0, 10), getSeatArea(row3, 10, 20));
    }

    private static String getSeatArea(int[] row, int start, int end) {
        //creating an empty stringBuilder for the range of seats in a row
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            //using a ternary to check the array element is 0 or not
            sb.append(row[i] == 0 ? "O" : "X");
        }
        return sb.toString();
    }

    public static void cancel_ticket() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the Row number :");
        int rowNum;
        int seat;
        try{
             rowNum = input.nextInt();
        }catch (Exception e){
            //catching the exception if the user input anything other than a number as a row number
            System.out.println("Invalid input , please enter a valid row number!");
            return;
        }

        if (rowNum == 1 || rowNum == 2 || rowNum == 3) {
            System.out.println("Please enter your desired Seat number :");
            try{
                seat = input.nextInt();
            }catch (Exception e){
                //catching the exception if the user input anything other than a number as a seat number
                System.out.println("Invalid input , please enter a valid seat number!");
                return;
            }
            //if the inputted seat is within the range of the particular row
            //cancelling the booking and removing the ticket object from the list
            //using a user-defined method called updateList
            if (rowNum == 1 && 0 < seat && seat < 13) {
                updateList(seat, firstRow);
            } else if (rowNum == 2 && 0 < seat && seat < 16) {
                updateList(seat, secondRow);
            } else if (rowNum == 3 && 0 < seat && seat < 21) {
                updateList(seat, thirdRow);
            } else {
                //if the seat is not within the valid range printing the error message
                System.out.println("Invalid Seat number!");
            }
        } else {
            //if the row is not within the valid range printing the error message
            System.out.println("Invalid Row number!");
        }
    }

    //creating a user build method called update-list
    private static void updateList(int seat, int[] Row) {
        //checking the seat of the row array is already booked or not
        //if booked making it available again
        if (Row[seat - 1] == 1) {
            Row[seat - 1] = 0;
            System.out.println("Okay,Your booking has Cancelled!");
            //iterating over the ticket-list
            for (int i = 0; i < ticketList.size(); i++) {
                //if the ticket element's seat attribute
                //equals to the seat user gave
                //that Ticket object will be removed from the list
                if (ticketList.get(i).getSeat() == seat) {
                    ticketList.remove(i);
                    i--;
                }
            }
        } else {
            //if the seat is not yet booked , printing an error message
            System.out.println("The seat is not yet booked!Please check the Row Number and Seat Number correctly" +
                    " and try again !");
        }
    }

    public static void show_available(String message, int[] row) {
        System.out.print(message);
        //iterating over the array
        for (int i = 0; i < row.length; i++) {
            //if the seat is not booked,printing the seat
            if (row[i] == 0) {
                System.out.print(i + 1 + " ");
            }
        }
        System.out.println(" ");
    }

    public static void save() {
        try {
            //using printWriter object to write the data to the file
            //using buffer writer to improve performance
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("SeatingInfo.txt")));
            //converting arrays to string and writing that data to the file
            printWriter.println(Arrays.toString(firstRow));
            printWriter.println(Arrays.toString(secondRow));
            printWriter.println(Arrays.toString(thirdRow));
            printWriter.close();
            System.out.println("The row information have saved !");

        } catch (IOException e) {
            System.out.println("An error occurred while saving data !");
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            //using the bufferReader to read the text file
            BufferedReader reader = new BufferedReader(new FileReader("SeatingInfo.txt"));
            //extracting the data in the file to a String variable line by line
            String Line = reader.readLine();
            while (Line != null) {
                System.out.println(Line);
                Line = reader.readLine();
            }
            reader.close();
            System.out.println("The row information have been loaded..!");
        } catch (IOException ex) {
            System.out.println("An error occurred while loading data..!");
        }
    }

    public static void show_tickets_info() {
        double total = 0;
        //ticket info
        System.out.println("\n------------------Tickets Information-----------");
        //enhanced for loop to iterate over the list
        for (Ticket displayTicket : ticketList) {
            //invoking the print method in ticket class using Ticket object "displayTicket"
            displayTicket.print();
            System.out.println(" ");
        }
        //using the getter method getPrice to get the price of the Ticket object and
        //to store it in a variable named total
        for (Ticket ticket : ticketList) {
            total += ticket.getPrice();
        }
        if (ticketList.size() == 1) {
            System.out.println("Total price of the ticket is $" + total);
        } else {
            System.out.println("Total price of tickets are $" + total);
        }
    }

    public static void sort_tickets() {
        System.out.println("-------------Sorted Ticket Info-----------------");
        //converting ticket-list into an array and assigning it to ticket array named sortedTickets
        Ticket[] sortedTickets = ticketList.toArray(new Ticket[0]);
        int len = sortedTickets.length;
        //implementing the bubble sort algorithm
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i; j++) {
                if (sortedTickets[i].getPrice() > sortedTickets[i + 1].getPrice()) {
                    //swapping the ticket objects if first object's price
                    //is greater than the second object's price using a temp variable
                    Ticket temp = sortedTickets[i];
                    sortedTickets[i] = sortedTickets[i + 1];
                    sortedTickets[i + 1] = temp;
                }
            }
        }
        //printing the sorted ticket array to the console
        for (Ticket displayTicket : ticketList) {
            displayTicket.print();
            System.out.println(" ");
        }
    }
}