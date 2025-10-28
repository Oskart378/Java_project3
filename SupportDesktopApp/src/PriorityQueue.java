import java.util.Comparator;
import java.util.LinkedList;

// PriorityQueue where smaller values mean higher priority.
// Lower numbers come out first. Uses a Comparator if provided.
public class PriorityQueue<E> {

    private final LinkedList<E> list;
    private final Comparator<E> comparator;

    public PriorityQueue() {
        this(null);
    }

    public PriorityQueue(Comparator<E> comparator){
        this.comparator = comparator;
        this.list = new LinkedList<>();
    }


    public void enqueue(E newItem) {

        if (isEmpty()) {
            list.addLast(newItem);
            return;
        }

        int i = 0;

        for (E item : list) {
            int compareValue = compare(newItem, item);

            if (compareValue < 0) {
                list.add(i, newItem);
                return;
            }
            i++;
        }

        list.addLast(newItem);
    }

    public E dequeue() {
        if (isEmpty())
            return null;

        return list.removeFirst();
    }

    public int getSize() {
        return list.size();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    private int compare(E firstItem, E secondItem) {
        if (comparator != null)
            return comparator.compare(firstItem, secondItem);
        else
            return
                    ((Comparable<E>) firstItem).compareTo(secondItem);
    }

    @Override
    public String toString() {

        StringBuilder listItems = new StringBuilder();

        for (E item : list)
            listItems.append(item).append("\n");

        return listItems.toString();
    }
}
