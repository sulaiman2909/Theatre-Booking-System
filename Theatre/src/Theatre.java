import java.util.*;
import java.io.*;

public class Theatre {
    public static void main(String[] args) {
        int[] row1 = new int[12];
        int[] row2 = new int[16];
        int[] row3 = new int[20];
        ArrayList<Ticket> tickets = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to the new theatre!");
        // While loop used here to iterate the menu until the user inserts 0
        while (true) {
            try {
                System.out.println("""
                        ---------------------------------------------------Please select an option:-----------------------------------------------------
                        1) Buy a ticket
                        2) Print seating area
                        3) Cancel ticket
                        4) List available seats
                        5) Save
                        6) Load
                        7) Print ticket information and total price
                        8) Sort tickets by price
                        0) Quit
                        ---------------------------------------------------------------------------------------------------------------------------------
                        Enter Option:""");
                int option = s.nextInt();

                switch (option) {            //This switch case is used to run the representing method when the number is entered
                    case 0 -> System.exit(0);
                    case 1 -> buy_ticket(row1, row2, row3, tickets);
                    case 2 -> print_seating_area(row1, row2, row3);
                    case 3 -> cancel_ticket(row1, row2, row3, tickets);
                    case 4 -> show_available(row1, row2, row3);
                    case 5 -> save(row1, row2, row3);
                    case 6 -> load(row1, row2, row3);
                    case 7 -> show_tickets_info(tickets);
                    case 8 -> sort_tickets(tickets);
                    default -> System.out.println("Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, Please enter an integer."); // Try-catch used here to eliminate any errors caused when the user inputs a non integer
            }
        }

    }

    public static void buy_ticket(int[] row1, int[] row2, int[] row3, ArrayList<Ticket> tickets) {
        int[][] allRows = {row1, row2, row3}; // A 2D array 'allRows' is created which includes all the three arrays for each row
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String name = s.nextLine();
        System.out.println("Please enter your Surname: ");
        String surname = s.nextLine();
        System.out.println("Please enter your Email Address");
        String email = s.nextLine();
        System.out.println("Please enter the number of tickets you prefer to buy: \n" + "You can purchase 1 ticket for $20, 2 tickets at $15 and any number more than that for $10. ");
        int num_of_tickets = s.nextInt(); // Get the number of tickets the user prefers to buy. Based on his preference the price category is automatically fixed to a specific offer
        int price = 0;
        if (num_of_tickets == 1)
            price = 20;
        else if (num_of_tickets == 2)
            price = 15;
        else if (num_of_tickets >= 3)
            price = 10;
        if (num_of_tickets > 0 && num_of_tickets<(48-tickets.size())) {
            for (int i = 0; i < num_of_tickets; i++) { //Getting user input for row number by validating if it's within the available range
                int row_num;
                while(true) { //Using a do-while loop to iterate until the user enters a valid row number
                    System.out.println("Please enter your desired ROW number (1-3): ");
                    row_num = s.nextInt();
                    if (row_num>3 || row_num<1){
                        System.out.println("Entered row doesn't exist, Please try again.");
                        continue;
                    }
                    break;
                }

                int seat_num;
                while(true) {         //Using a do-while loop to iterate until the user enters a valid seat number
                    System.out.println("Please enter your desired SEAT number for the row " + row_num + " Seats Available (1-" + allRows[row_num - 1].length + ")" + " :");
                    seat_num = s.nextInt();
                    if(seat_num < 1 || seat_num > allRows[row_num - 1].length){
                        System.out.println("The row you entered is not within the range for the selected row.");
                        continue;
                    }
                    break;
                }
                Person person = new Person(name, surname, email); //Creates an object person and passing in the following arguments.
                int[] temp_arr = null;
                switch (row_num) { // In order to book, I have used a temporary array where it equals to the array of the row selected by the user and does the job.
                    case 1 -> temp_arr = row1;
                    case 2 -> temp_arr = row2;
                    case 3 -> temp_arr = row3;
                }
                if (seat_num <= temp_arr.length) {
                    if (temp_arr[seat_num - 1] == 1) {    //If condition used to check if seat is booked or available
                        System.out.println("Please try with a different seat, The seat you entered is already booked.");
                    } else if (temp_arr[seat_num - 1] == 0) {
                        temp_arr[seat_num - 1] = 1;
                        Ticket ticket = new Ticket(row_num, seat_num, price, person); //Creates an object named ticket and gets all the information
                        tickets.add(ticket); //The created ticket is added to the arraylist named "tickets".
                        System.out.println("Your Seat Booked Successfully at row " + row_num + " seat " + seat_num);
                    }
                }
            }
        } else {
            System.out.println("The number of tickets you preferred to buy is not available, Only " + (48-tickets.size()) + " seats left.\n");
        }

    }

    public static void print_seating_area(int[] row1, int[] row2, int[] row3) {
        System.out.println("""
                ***********
                *  STAGE  *
                ***********""".indent(6));
        System.out.print("    ");
        for (int i = 0; i < row1.length; i++) {
            if (i == 6) //If condition used to print a space on the first row to separate from the middle
                System.out.print(" \t");
            if (row1[i] == 1) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }
        System.out.print("\n");
        System.out.print("  ");
        for (int i = 0; i < row2.length; i++) {
            if (i == 8) //If condition used to print a space on the second row to separate from the middle
                System.out.print(" \t");
            if (row2[i] == 1) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }
        System.out.print("\n");
        for (int i = 0; i < row3.length; i++) {
            if (i == 10) //If condition used to print a space on the second row to separate from the middle
                System.out.print(" \t");
            if (row3[i] == 1) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }
        System.out.print("\n");
    }

    public static void cancel_ticket(int[] row1, int[] row2, int[] row3, ArrayList<Ticket> tickets) {
        int[][] allRows = {row1, row2, row3};
        Scanner s = new Scanner(System.in);
        int row_num_cancel;
        while (true) {                //Until a valid row number is entered it'll keep on prompting to enter the row again
            System.out.println("Please enter the ROW number of your booked seat: ");
            row_num_cancel = s.nextInt();
            if (row_num_cancel > 3 || row_num_cancel <= 0) {
                System.out.println("Invalid Row Entered, Please enter a row 1-3.");
            } else {
                break;
            }
        }
        int seat_num_cancel;
        while (true) {               //Until a valid row number is entered it'll keep on prompting to enter the row again
            System.out.println("Please enter the SEAT number of your booked seat: ");
            seat_num_cancel = s.nextInt();
            if (seat_num_cancel < 1 || seat_num_cancel > allRows[row_num_cancel - 1].length) {
                System.out.println("Invalid Seat Entered, Please enter valid seat.");
            } else {
                break;
            }
        }
        int[] temp_arr = switch (row_num_cancel) { //Temporary array used here to role-play as the selected row array
            case 1 -> row1;
            case 2 -> row2;
            case 3 -> row3;
            default -> null;
        };
        if (temp_arr[seat_num_cancel - 1] == 1) {
            temp_arr[seat_num_cancel - 1] = 0;
            for (int i = 0; i < tickets.size(); i++) {
                if (row_num_cancel == tickets.get(i).getRow() && seat_num_cancel == tickets.get(i).getSeat()) { //Searches for ticket with the entered row and seat number to remove it from the array list
                    tickets.remove(i); //Removes the ticket of the selected seat from the ArrayList
                    System.out.println("Your seat was cancelled successfully.");
                }
            }
        } else {
            System.out.println("The chosen seat is not booked inorder to cancel.");

        }
    }

    public static void show_available(int[] row1, int[] row2, int[] row3) {
        int[][] all_rows = {row1, row2, row3}; //Creates a 2D array to avoid repetition
        for (int i = 0; i < all_rows.length; i++) {
            System.out.println("Seats available in row " + (i + 1) + ": ");
            for (int j = 0; j < all_rows[i].length; j++) { //For loop used to iterarate and check if the seat is available or not.
                if (all_rows[i][j] == 0) {
                    System.out.print((j + 1) + ", "); //Print the available seat numbers
                }
            }
            System.out.println("\b\b.\n");
        }
    }

    public static void save(int[] row1, int[] row2, int[] row3) {
        try { //Saves the data in arrays into a file
            int[][] all_rows = {row1, row2, row3}; //Creates a 2D array all_rows
            File seat_availability = new File("Seat Availability.txt");
            FileWriter fw = new FileWriter(seat_availability);
            for (int[] row : all_rows) { //using a for each loop iterate over the 2D array
                for (int i : row) {   //Using a for each loop iterate over the row array
                    fw.write(i + " ");
                }
                fw.write("\n");
            }
            fw.close();
            System.out.println("File Saved Successfully.");
        } catch (IOException e) {
            System.out.println("File was not created, An error occurred.");
            e.printStackTrace();
        }
    }

    public static void load(int[] row1, int[] row2, int[] row3) {
        try { //Loads back the data into the arrays
            int[][] all_rows = {row1, row2, row3};
            File file = new File("Seat Availability.txt");
            Scanner file_reader = new Scanner(file);
            for (int i = 0; i < all_rows.length; i++) {
                for (int j = 0; j < all_rows[i].length; j++) {
                    all_rows[i][j] = file_reader.nextInt();
                }
            }
            System.out.println("Successfully Loaded from file.");
            file_reader.close();
        } catch (IOException e) {
            System.out.println("Error occurred while loading the file.");
            e.printStackTrace();
        }
    }

    public static void show_tickets_info(ArrayList<Ticket> tickets) {
        double total_price = 0;
        for (int i = 0; i < tickets.size(); i++) {
            System.out.print("==============================");
            System.out.println("\nTicket " + (i + 1));
            tickets.get(i).print(); //Prints the ticket
            total_price += tickets.get(i).getPrice(); //Calculates the total price
        }
        System.out.println("==============================" + "\nYour purchase completed successfully." + "\nYour total bill is " + total_price);

    }

    public static void sort_tickets(ArrayList<Ticket> tickets) {
        int bottom = tickets.size() - 2; //Bubbles sort algorithm used here
        Ticket temp_ticket;
        boolean exchanged = true;
        while (exchanged) {
            exchanged = false;
            for (int i = 0; i <= bottom; i++) {
                if (tickets.get(i).getPrice() > tickets.get(i + 1).getPrice()) {
                    temp_ticket = tickets.get(i);
                    tickets.set(i, tickets.get(i + 1));
                    tickets.set(i + 1, temp_ticket);
                    exchanged = true;
                }
            }
            bottom--;
        }
        show_tickets_info(tickets);
    }
}










