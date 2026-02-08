package datastructures;

import java.util.EmptyStackException;

public class VerifyImplementation {
    public static void main(String[] args) {
        System.out.println("Starting Verification...");

        try {
            verifyStack();
            System.out.println("[SUCCESS] MyStack verification passed.");
        } catch (Exception e) {
            System.out.println("[FAILED] MyStack verification failed: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            verifyHeap();
            System.out.println("[SUCCESS] MyHeap verification passed.");
        } catch (Exception e) {
            System.out.println("[FAILED] MyHeap verification failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void verifyStack() {
        System.out.println("Verifying MyStack...");
        MyStack<Integer> stack = new MyStack<>();

        // Push
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Peek
        if (stack.peek() != 3)
            throw new RuntimeException("Peek failed. Expected 3, got " + stack.peek());

        // Pop
        if (stack.pop() != 3)
            throw new RuntimeException("Pop failed. Expected 3");
        if (stack.pop() != 2)
            throw new RuntimeException("Pop failed. Expected 2");
        if (stack.pop() != 1)
            throw new RuntimeException("Pop failed. Expected 1");

        // IsEmpty
        if (!stack.isEmpty())
            throw new RuntimeException("isEmpty failed. Stack should be empty.");

        // Exception
        try {
            stack.pop();
            throw new RuntimeException("EmptyStackException not thrown");
        } catch (EmptyStackException e) {
            // Expected
        }
    }

    private static void verifyHeap() {
        System.out.println("Verifying MyHeap...");

        // Min Heap
        MyHeap<Integer> minHeap = new MyHeap<>();
        minHeap.insert(3);
        minHeap.insert(1);
        minHeap.insert(5);

        if (minHeap.peek() != 1)
            throw new RuntimeException("MinHeap Peek failed. Expected 1, got " + minHeap.peek());
        if (minHeap.poll() != 1)
            throw new RuntimeException("MinHeap Poll failed. Expected 1");
        if (minHeap.poll() != 3)
            throw new RuntimeException("MinHeap Poll failed. Expected 3");
        if (minHeap.poll() != 5)
            throw new RuntimeException("MinHeap Poll failed. Expected 5");

        // Max Heap
        MyHeap<Integer> maxHeap = new MyHeap<>(true);
        maxHeap.insert(3);
        maxHeap.insert(1);
        maxHeap.insert(5);

        if (maxHeap.peek() != 5)
            throw new RuntimeException("MaxHeap Peek failed. Expected 5, got " + maxHeap.peek());
        if (maxHeap.poll() != 5)
            throw new RuntimeException("MaxHeap Poll failed. Expected 5");
        if (maxHeap.poll() != 3)
            throw new RuntimeException("MaxHeap Poll failed. Expected 3");
        if (maxHeap.poll() != 1)
            throw new RuntimeException("MaxHeap Poll failed. Expected 1");
    }
}
