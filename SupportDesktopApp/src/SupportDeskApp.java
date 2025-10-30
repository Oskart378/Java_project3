import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class SupportDeskApp {

    private static final Scanner input = new Scanner(System.in);
    private final SupportDesk supportDesk;

    public SupportDeskApp() {
        this.supportDesk = new SupportDesk();
    }

    public static void main(String[] args) {
        new SupportDeskApp().run();
    }

    private static void pauseConsole() {
        System.out.println("\nPress enter to go back to main menu...");

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isEmpty()) break;
        }
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = promptMenuChoice();

            switch (choice) {
                case 1 -> addNewTicket();
                case 2 -> processNextTicket();
                case 3 -> viewAllActiveTickets();
                case 4 -> viewRecentlyResolved();
                case 5 -> reopenLastResolved();
                case 6 -> {
                    System.out.println("Thank you for using MDC Tech Support Ticket System!");
                    running = false;
                }
            }

            if (running)
                clearScreen();
        }
    }

    private void processNextTicket() {
        supportDesk.processNextTicket();
        pauseConsole();
    }

    private void viewAllActiveTickets() {
        supportDesk.viewAllActiveTickets();
        pauseConsole();
    }

    private void viewRecentlyResolved() {
        supportDesk.viewRecentResolved();
        pauseConsole();
    }

    private void reopenLastResolved() {
        supportDesk.reopenLastResolved();
        pauseConsole();
    }

    private void addNewTicket() {
        try {
            supportDesk.addTicket(createTicket());
            System.out.println("Ticket added successfully");
            pauseConsole();
        } catch (IllegalArgumentException e) {
            System.out.println("Try to enter data in the correct format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private Ticket createTicket() throws Exception {
        int id = promptInput(
                "Enter ticket ID: ",
                Integer::parseInt,             // converter from String to Integer
                n -> n > 0 && Ticket.isIdAvailable(n),  // predicate for validation
                "Invalid ID or ID already used. Please enter a positive, unused number."
        );

        String name = promptInput(
                "Enter requester name: ",
                s -> s,                       // converter is identity for String
                s -> !s.isBlank(),
                "Name can't be blank or null."
        );

        String issue = promptInput(
                "Enter issue: ",
                s -> s,
                s -> !s.isBlank(),
                "Issue can't be blank or null."
        );

        String priority = promptInput(
                "Enter priority (Low/Medium/High): ",
                s -> s,
                Ticket::isPriorityValid,
                "Priority must be one of the following: Low, Medium, High."
        );
        return new Ticket(id, name, issue, priority);
    }

    // Generic readInput method
    private <T> T promptInput(String prompt, Function<String, T> converter, Predicate<T> checker, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine();

            try {
                T value = converter.apply(line);// convert String to T

                if (checker.test(value))
                    return value;  // check validity
                else
                    System.out.println(errorMsg);

            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== Welcome to MDC Tech Support Ticket System =====");
        System.out.println("1. Add new support ticket");
        System.out.println("2. Process next ticket");
        System.out.println("3. View all active tickets");
        System.out.println("4. View recently resolved tickets");
        System.out.println("5. Reopen last resolved tickets");
        System.out.println("6. Exit\n");
    }

    private int promptMenuChoice() {
        while (true) {
            System.out.print("Enter your choice: ");
            String inputStr = input.nextLine().trim();
            if (inputStr.isEmpty()) {
                System.out.println("Please enter a valid choice between 1 and 6.");
                continue;
            }
            try {
                int choice = Integer.parseInt(inputStr);
                if (choice >= 1 && choice <= 6)
                    return choice;
                else
                    System.out.println("Please enter a valid choice between 1 and 6.");
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid choice between 1 and 6.");
            }
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
