import java.util.Comparator;
import java.util.HashSet;

public class Ticket implements Comparable<Ticket> {

    private static final HashSet<Integer> usedIds = new HashSet<>();
    private final int id;
    private final String name;
    private final String issue;
    private final String priority;

    public Ticket(int id, String name, String issue, String priority) {

        if (id < 0)
            throw new IllegalArgumentException("Id can't have negative values");
        if (!Ticket.isIdAvailable(id))
            throw new IllegalArgumentException("Id is already used, try a different Id number");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name can't be blank or null");
        if(issue == null || issue.isBlank())
            throw new IllegalArgumentException("Issue can't be blank or null");
        if(priority == null || !isValid(priority))
            throw new IllegalArgumentException("Priority has to be one of the following values (low, medium or high)");

        this.id = id;
        this.name = name;
        this.issue = issue;
        this.priority = priority.toLowerCase();
        usedIds.add(id);
    }

    private static boolean isIdAvailable(int id) {
        return !usedIds.contains(id);
    }

    private boolean isValid(String priority) {
        priority = priority.toLowerCase();
        return (priority.equals("low") || priority.equals("medium") ||
                priority.equals("high"));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIssue() {
        return issue;
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("[#%d] %s - %s (%s)", id, name, issue, priority);
    }

    @Override
    public int compareTo(Ticket t) {
        int thisItem = this.getPriorityNumericalValue();
        int secondItem = t.getPriorityNumericalValue();

        return thisItem - secondItem;

    }

    private int getPriorityNumericalValue () {

        return switch (priority) {
            case "high" -> 1;
            case "medium" -> 2;
            case "low" -> 3;
            default -> throw new IllegalStateException("Unexpected value: " + priority);
        };
    }
}
