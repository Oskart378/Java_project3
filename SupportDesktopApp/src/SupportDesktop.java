import java.util.LinkedList;
import java.util.Stack;

public class SupportDesktop {

    private final LinkedList<Ticket> activeTickets;
    private final Stack<Ticket> resolvedTickets;

    public SupportDesktop() {
        activeTickets = new LinkedList<>();
        resolvedTickets = new Stack<>();
    }

    public void addTicket(Ticket t) {
        activeTickets.addLast(t);
    }

    public void processNextTicket() {

        if (activeTickets.isEmpty()) {
            System.out.println("There are no more tickets to process.");
            return;
        }

        Ticket removedTicket = activeTickets.removeFirst();
        resolvedTickets.push(removedTicket);
        System.out.println("Processing next ticket...");
        System.out.println("Ticket resolved: " + removedTicket);
    }


    public void viewRecentResolved() {

        int size = resolvedTickets.size();

        if (size == 0) {
            System.out.println("There is no resolved tickets at the moment");
            return;
        }


        int start = Math.max(0, size - 3);

        System.out.println("Recently resolved tickets: ");
        for (int i = size - 1; i >= start; i--) {

            System.out.print(resolvedTickets.get(i));
            if (i == size - 1) {
                System.out.print("  <-- Most recently resolved");
            }
            System.out.println();
        }

    }

    public void viewAllActiveTickets() {
        if (activeTickets.isEmpty()) {
            System.out.println("There is no active tickets at the moment");
            return;
        }
        System.out.println(activeTickets);
    }

    public void reopenLastResolved() {

        if(resolvedTickets.isEmpty()) {
            System.out.println("There is no tickets at this moment");
            return;
        }

        Ticket ticket = resolvedTickets.pop();
        activeTickets.addLast(ticket);
        System.out.println("Reopened ticket: " + ticket);
    }
}
