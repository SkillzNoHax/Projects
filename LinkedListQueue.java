import java.util.LinkedList;
import java.util.Queue;

public class LinkedListQueue {
    private Queue<String> queue;

    public LinkedListQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue(String item) {
        queue.add(item);
    }

    public String dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
