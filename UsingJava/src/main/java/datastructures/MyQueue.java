package datastructures;

import java.util.NoSuchElementException;

/**
 * 제네릭 큐(Queue) 구현체입니다.
 * 연결 리스트(Linked List) 구조를 기반으로 하여 삽입/삭제 성능이 우수합니다.
 * 
 * @param <T> 큐에 저장할 데이터의 타입
 */
public class MyQueue<T> {

    /**
     * 내부 노드 클래스입니다.
     */
    private static class Node<E> {
        E data; // 데이터
        Node<E> next; // 다음 노드 참조

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front; // 큐의 앞부분 (삭제 위치)
    private Node<T> rear; // 큐의 뒷부분 (삽입 위치)
    private int size; // 큐의 크기

    public MyQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * 큐의 뒤쪽에 데이터를 추가합니다 (Offer/Enqueue).
     * 
     * @param data 추가할 데이터
     */
    public void offer(T data) {
        Node<T> newNode = new Node<>(data);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
        size++;
    }

    /**
     * 큐의 앞쪽에 있는 데이터를 제거하고 반환합니다 (Poll/Dequeue).
     * 
     * @return 제거된 데이터
     * @throws NoSuchElementException 큐가 비어있는 경우 발생
     */
    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    /**
     * 큐의 앞쪽에 있는 데이터를 제거하지 않고 반환합니다 (Peek).
     * 
     * @return 앞쪽 데이터
     * @throws NoSuchElementException 큐가 비어있는 경우 발생
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.data;
    }

    /**
     * 큐가 비어있는지 확인합니다.
     * 
     * @return 비어있으면 true, 아니면 false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 큐에 저장된 데이터의 개수를 반환합니다.
     * 
     * @return 데이터 개수
     */
    public int size() {
        return size;
    }
}
