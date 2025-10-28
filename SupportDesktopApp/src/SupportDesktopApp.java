import java.util.Scanner;

public class SupportDesktopApp {

    private static final Scanner input = new Scanner(System.in);
    private final SupportDesktop supportDesktop;

    public SupportDesktopApp() {
        this.supportDesktop = new SupportDesktop();
    }

    public static void main(String[] args) {

        SupportDesktop supportDesktop = new SupportDesktop();
        new SupportDesktopApp().run();
    }

    private static void pauseConsole() {
        System.out.println("\nPress enter to go back to main menu...");

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isEmpty()) break; // stop only when the user really just hits enter
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
                case 6-> {
                    System.out.println("Thank you for using MDC Tech Support Ticket System!");
                    running = false;}
            }
        }
    }

    private void processNextTicket() {
        supportDesktop.processNextTicket();
        pauseConsole();
    }

    private void viewAllActiveTickets() {
        supportDesktop.viewAllActiveTickets();
        pauseConsole();
    }

    private void viewRecentlyResolved() {
        supportDesktop.viewRecentResolved();
        pauseConsole();
    }

    private void reopenLastResolved() {
        supportDesktop.reopenLastResolved();
        pauseConsole();
    }

    private void addNewTicket() {

        try {
            int id;
            String name, issue, priority;

            System.out.println("Enter ticket ID:");
            id = Integer.parseInt(input.nextLine());

            System.out.println("Enter requester name:");
            name = input.nextLine();

            System.out.println("Enter issue:");
            issue = input.nextLine();

            System.out.println("Enter priority (Low/Medium/High):");
            priority = input.nextLine();

            supportDesktop.addTicket(new Ticket(id, name, issue, priority));
            System.out.println("Ticket added successfully");

            pauseConsole();
        }

        catch (IllegalArgumentException e) {
            System.out.println("Try to enter data in the correct format: " + e.getMessage());
        }

        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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
        System.out.print("Enter your choice: ");
    }

    private int promptMenuChoice() {
        while (true){
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
}