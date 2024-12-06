public class TriageSimulator {
    // Priority queues using different data structures
    private LinkedListQueue priority1Queue;       // LinkedList
    private NodeQueue priority2Queue; // Linked chain of nodes
    private ArrayCircularQueue priority3Queue;    // Circular array

    public TriageSimulator() {
        // Initialize the queues
        priority1Queue = new LinkedListQueue();
        priority2Queue = new NodeQueue();
        priority3Queue = new ArrayCircularQueue(10); // Arbitrary size
    }



    // Map triage codes to their priorities
    private int getPriority(String triageCode) {
        switch (triageCode) {
            case "AL":
            case "HA":
            case "ST":
                return 1;
            case "BL":
            case "SF":
            case "IW":
            case "KS":
            case "OT":
                return 2;
            case "HN":
                return 3;
            default:
                throw new IllegalArgumentException("Invalid triage code: " + triageCode);
        }
    }

    public void add(String lineFromFile) {
        // Parse the input line
        String[] parts = lineFromFile.trim().split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid input format. Expected: 'FName LName TriageCode'.");
        }
        String firstName = parts[0];
        String lastName = parts[1];
        String triageCode = parts[2];
        String fullName = firstName + " " + lastName;

        // Determine the priority
        int priority = getPriority(triageCode);

        // Add to the appropriate queue
        switch (priority) {
            case 1:
                priority1Queue.enqueue(fullName);
                break;
            case 2:
                priority2Queue.enqueue(fullName);
                break;
            case 3:
                priority3Queue.enqueue(fullName);
                break;
        }
    }

    public String remove() {
        // Serve patients from the highest priority queue that is not empty
        if (!priority1Queue.isEmpty()) {
            return priority1Queue.dequeue();
        } else if (!priority2Queue.isEmpty()) {
            return priority2Queue.dequeue();
        } else if (!priority3Queue.isEmpty()) {
            return priority3Queue.dequeue();
        }
        return null; // All queues are empty
    }

    public boolean isEmpty() {
        // Check if all queues are empty
        return priority1Queue.isEmpty() && priority2Queue.isEmpty() && priority3Queue.isEmpty();
    }
}
