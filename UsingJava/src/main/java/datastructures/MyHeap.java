package datastructures;

import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * 제네릭 힙(Heap) 구현체입니다.
 * 생성자 옵션에 따라 최소 힙(Min Heap) 또는 최대 힙(Max Heap)으로 동작합니다.
 * MyArrayList를 사용하여 완전 이진 트리를 표현합니다.
 *
 * @param <T> 비교 가능한(Comparable) 데이터 타입
 */
public class MyHeap<T extends Comparable<T>> {

    private final MyArrayList<T> heap;
    private final Comparator<T> comparator;

    /**
     * 기본 생성자. 최소 힙(Min Heap)으로 생성됩니다.
     */
    public MyHeap() {
        this(false);
    }

    /**
     * 힙의 종류를 지정하여 생성합니다.
     * 
     * @param isMaxHeap true면 최대 힙, false면 최소 힙으로 동작합니다.
     */
    public MyHeap(boolean isMaxHeap) {
        this.heap = new MyArrayList<>();
        if (isMaxHeap) {
            // 최대 힙: 내림차순 정렬 (큰 값이 위로)
            this.comparator = Collections.reverseOrder();
        } else {
            // 최소 힙: 오름차순 정렬 (작은 값이 위로) - 기본
            this.comparator = Comparator.naturalOrder();
        }
    }

    /**
     * 힙에 데이터를 추가합니다.
     * 가장 마지막 위치에 추가한 뒤, 부모와 비교하며 위로 올라갑니다 (Heapify Up).
     * 
     * @param element 추가할 데이터
     */
    public void insert(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    /**
     * 힙에서 최상위 노드(Min Heap: 최솟값, Max Heap: 최댓값)를 제거하고 반환합니다.
     * 루트를 제거하고 가장 마지막 노드를 루트로 옮긴 뒤, 자식들과 비교하며 내려갑니다 (Heapify Down).
     * 
     * @return 최상위 값
     * @throws NoSuchElementException 힙이 비어있는 경우 발생
     */
    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T root = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }

        return root;
    }

    /**
     * 힙의 최상위 노드를 제거하지 않고 반환합니다.
     * 
     * @return 최상위 값
     * @throws NoSuchElementException 힙이 비어있는 경우 발생
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    /**
     * 힙이 비어있는지 확인합니다.
     * 
     * @return 비어있으면 true, 아니면 false
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * 힙에 저장된 데이터의 개수를 반환합니다.
     * 
     * @return 데이터 개수
     */
    public int size() {
        return heap.size();
    }

    /**
     * 새로운 노드가 추가된 후, 힙의 성질을 만족하도록 위로 이동시킵니다.
     * 
     * @param index 추가된 노드의 인덱스
     */
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;

        // comparator.compare(child, parent) < 0 이면 자식이 부모보다 "우선순위가 높다"는 뜻 (MinHeap: 더
        // 작음, MaxHeap: 더 큼)
        while (index > 0 && comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    /**
     * 루트 노드가 변경된 후, 힙의 성질을 만족하도록 아래로 이동시킵니다.
     * 
     * @param index 변경된 노드의 인덱스 (보통 0)
     */
    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int targetIndex = index;

        // 왼쪽 자식과 비교
        if (leftChildIndex < heap.size() && comparator.compare(heap.get(leftChildIndex), heap.get(targetIndex)) < 0) {
            targetIndex = leftChildIndex;
        }

        // 오른쪽 자식과 비교
        if (rightChildIndex < heap.size() && comparator.compare(heap.get(rightChildIndex), heap.get(targetIndex)) < 0) {
            targetIndex = rightChildIndex;
        }

        // 우선순위가 더 높은(Comparator 기준 더 작은) 자식이 있다면 교환하고 계속 진행
        if (targetIndex != index) {
            swap(index, targetIndex);
            heapifyDown(targetIndex);
        }
    }

    /**
     * 두 인덱스의 데이터를 서로 교환합니다.
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
