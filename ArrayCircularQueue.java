public class ArrayCircularQueue {
    private String[] array;
    private int front, rear, size, capacity;

    public ArrayCircularQueue(int capacity) {
        this.capacity = capacity;
        array = new String[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(String item) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % capacity;
        array[rear] = item;
        size++;
    }

    public String dequeue() {
        if (size == 0) {
            return null;
        }
        String item = array[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
