package datastructures;

import java.util.EmptyStackException;

public class VerifyImplementation {
    public static void main(String[] args) {
        System.out.println("Starting Verification...");

        runTest(VerifyImplementation::verifyArrayList, "MyArrayList");
        runTest(VerifyImplementation::verifyStack, "MyStack");
        runTest(VerifyImplementation::verifyQueue, "MyQueue");
        runTest(VerifyImplementation::verifyHeap, "MyHeap");
        runTest(VerifyImplementation::verifyHashMap, "MyHashMap");
        runTest(VerifyImplementation::verifyLinkedHashMap, "MyLinkedHashMap");
        runTest(VerifyImplementation::verifyTreeMap, "MyTreeMap");
        runTest(VerifyImplementation::verifyHashSet, "MyHashSet");
        runTest(VerifyImplementation::verifyTreeSet, "MyTreeSet");

        System.out.println("\nAll verifications finished.");
    }

    private static void runTest(Runnable test, String name) {
        try {
            System.out.println("\n--- Verifying " + name + " ---");
            test.run();
            System.out.println(">>> [SUCCESS] " + name + " passed.");
        } catch (Exception e) {
            System.out.println(">>> [FAILED] " + name + " failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void verifyArrayList() {
        MyArrayList<String> list = new MyArrayList<>();

        System.out.println("In: Add 'A'");
        list.add("A");
        System.out.println("In: Add 'B'");
        list.add("B");
        System.out.println("In: Add 'C'");
        list.add("C");

        System.out.println("Check Size: " + list.size());
        if (list.size() != 3)
            throw new RuntimeException("Size check failed");

        String get1 = list.get(1);
        System.out.println("Out: Get(1) -> " + get1);
        if (!get1.equals("B"))
            throw new RuntimeException("Get failed");

        System.out.println("In: Remove index 1");
        list.remove(1);
        System.out.println("Check Size after remove: " + list.size());

        String get1After = list.get(1);
        System.out.println("Out: Get(1) after shift -> " + get1After);
        if (!get1After.equals("C"))
            throw new RuntimeException("Shift after remove failed");
    }

    private static void verifyStack() {
        MyStack<Integer> stack = new MyStack<>();

        System.out.println("In: Push 1");
        stack.push(1);
        System.out.println("In: Push 2");
        stack.push(2);
        System.out.println("In: Push 3");
        stack.push(3);

        int peekVal = stack.peek();
        System.out.println("Out: Peek -> " + peekVal);
        if (peekVal != 3)
            throw new RuntimeException("Peek failed");

        int pop1 = stack.pop();
        System.out.println("Out: Pop -> " + pop1);
        if (pop1 != 3)
            throw new RuntimeException("Pop failed. Expected 3");

        int pop2 = stack.pop();
        System.out.println("Out: Pop -> " + pop2);
        if (pop2 != 2)
            throw new RuntimeException("Pop failed. Expected 2");

        int pop3 = stack.pop();
        System.out.println("Out: Pop -> " + pop3);
        if (pop3 != 1)
            throw new RuntimeException("Pop failed. Expected 1");

        System.out.println("Check IsEmpty: " + stack.isEmpty());
        if (!stack.isEmpty())
            throw new RuntimeException("isEmpty failed");
    }

    private static void verifyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();

        System.out.println("In: Offer 1");
        queue.offer(1);
        System.out.println("In: Offer 2");
        queue.offer(2);

        int peekVal = queue.peek();
        System.out.println("Out: Peek -> " + peekVal);
        if (peekVal != 1)
            throw new RuntimeException("Peek failed");

        int poll1 = queue.poll();
        System.out.println("Out: Poll -> " + poll1);
        if (poll1 != 1)
            throw new RuntimeException("Poll failed");

        int poll2 = queue.poll();
        System.out.println("Out: Poll -> " + poll2);
        if (poll2 != 2)
            throw new RuntimeException("Poll failed");

        System.out.println("Check IsEmpty: " + queue.isEmpty());
        if (!queue.isEmpty())
            throw new RuntimeException("Queue should be empty");
    }

    private static void verifyHeap() {
        // Min Heap
        System.out.println("[Min Heap Test]");
        MyHeap<Integer> minHeap = new MyHeap<>();
        System.out.println("In: Insert 3");
        minHeap.insert(3);
        System.out.println("In: Insert 1");
        minHeap.insert(1);
        System.out.println("In: Insert 5");
        minHeap.insert(5);

        int peekMin = minHeap.peek();
        System.out.println("Out: Peek -> " + peekMin);
        if (peekMin != 1)
            throw new RuntimeException("MinHeap Peek failed");

        System.out.println("Out: Poll -> " + minHeap.poll());
        System.out.println("Out: Poll -> " + minHeap.poll());
        System.out.println("Out: Poll -> " + minHeap.poll());

        // Max Heap
        System.out.println("[Max Heap Test]");
        MyHeap<Integer> maxHeap = new MyHeap<>(true);
        System.out.println("In: Insert 3");
        maxHeap.insert(3);
        System.out.println("In: Insert 1");
        maxHeap.insert(1);
        System.out.println("In: Insert 5");
        maxHeap.insert(5);

        int peekMax = maxHeap.peek();
        System.out.println("Out: Peek -> " + peekMax);
        if (peekMax != 5)
            throw new RuntimeException("MaxHeap Peek failed");

        System.out.println("Out: Poll -> " + maxHeap.poll());
        System.out.println("Out: Poll -> " + maxHeap.poll());
        System.out.println("Out: Poll -> " + maxHeap.poll());
    }

    private static void verifyHashMap() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        System.out.println("In: Put 'One' -> 1");
        map.put("One", 1);
        System.out.println("In: Put 'Two' -> 2");
        map.put("Two", 2);

        System.out.println("Out: Get 'One' -> " + map.get("One"));
        if (map.get("One") != 1)
            throw new RuntimeException("Get failed");

        System.out.println("In: Update 'One' -> 100");
        map.put("One", 100);
        System.out.println("Out: Get 'One' -> " + map.get("One"));
        if (map.get("One") != 100)
            throw new RuntimeException("Update failed");

        System.out.println("In: Remove 'One'");
        map.remove("One");
        System.out.println("Out: Get 'One' -> " + map.get("One"));
        if (map.get("One") != null)
            throw new RuntimeException("Remove failed");
    }

    private static void verifyLinkedHashMap() {
        MyLinkedHashMap<String, Integer> map = new MyLinkedHashMap<>();

        System.out.println("In: Put 'First' -> 1");
        map.put("First", 1);
        System.out.println("In: Put 'Second' -> 2");
        map.put("Second", 2);
        System.out.println("In: Put 'Third' -> 3");
        map.put("Third", 3);

        MyArrayList<String> keys = map.keys();
        System.out.println("Out: Keys (Insertion Order) -> " + keys.get(0) + ", " + keys.get(1) + ", " + keys.get(2));

        if (!keys.get(0).equals("First") || !keys.get(1).equals("Second") || !keys.get(2).equals("Third")) {
            throw new RuntimeException("Insertion order check failed");
        }
    }

    private static void verifyTreeMap() {
        MyTreeMap<Integer, String> tree = new MyTreeMap<>();

        System.out.println("In: Put 5 -> 'Five'");
        tree.put(5, "Five");
        System.out.println("In: Put 1 -> 'One'");
        tree.put(1, "One");
        System.out.println("In: Put 10 -> 'Ten'");
        tree.put(10, "Ten");

        System.out.println("Out: Get 5 -> " + tree.get(5));
        if (!tree.get(5).equals("Five"))
            throw new RuntimeException("Get failed");

        MyArrayList<Integer> keys = tree.keys();
        System.out.println("Out: Keys (Sorted Order) -> " + keys.get(0) + ", " + keys.get(1) + ", " + keys.get(2));

        if (keys.get(0) != 1 || keys.get(1) != 5 || keys.get(2) != 10) {
            throw new RuntimeException("Sorted order check failed");
        }
    }

    private static void verifyHashSet() {
        MyHashSet<String> set = new MyHashSet<>();

        System.out.println("In: Add 'A'");
        set.add("A");
        System.out.println("In: Add 'B'");
        set.add("B");
        System.out.println("In: Add 'A' (Duplicate)");
        set.add("A");

        System.out.println("Check Size: " + set.size());
        if (set.size() != 2)
            throw new RuntimeException("Duplicate check failed");

        System.out.println("Out: Contains 'B' -> " + set.contains("B"));
        if (!set.contains("B"))
            throw new RuntimeException("Contains failed");
    }

    private static void verifyTreeSet() {
        MyTreeSet<Integer> set = new MyTreeSet<>();

        System.out.println("In: Add 10");
        set.add(10);
        System.out.println("In: Add 5");
        set.add(5);
        System.out.println("In: Add 20");
        set.add(20);

        MyArrayList<Integer> list = set.toList();
        System.out.println("Out: Elements (Sorted Order) -> " + list.get(0) + ", " + list.get(1) + ", " + list.get(2));

        if (list.get(0) != 5 || list.get(1) != 10 || list.get(2) != 20) {
            throw new RuntimeException("TreeSet sort check failed");
        }
    }
}
