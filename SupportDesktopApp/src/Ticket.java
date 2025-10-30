import java.util.HashSet;

public class Ticket {

    private static final HashSet<Integer> usedIds = new HashSet<>();
    private final int id;
    private final String name;
    private final String issue;
    private final String priority;

    public Ticket(int id, String name, String issue, String priority) {

        if (id < 0)
            throw new IllegalArgumentException("id can't have negative values");
        if (!Ticket.isIdAvailable(id))
            throw new IllegalArgumentException("id is already used, try a different Id number");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name can't be blank or null");
        if(issue == null || issue.isBlank())
            throw new IllegalArgumentException("Issue can't be blank or null");
        if(priority == null || !isPriorityValid(priority))
            throw new IllegalArgumentException("Priority has to be one of the following values (low, medium or high)");

        this.id = id;
        this.name = name.trim();
        this.issue = issue.trim();
        this.priority = priority.toLowerCase().trim();
        usedIds.add(id);
    }

    public static boolean isIdAvailable(int id) {
        return !usedIds.contains(id);
    }

    public static boolean isPriorityValid(String priority) {
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
}
