package datastructures;

import java.util.NoSuchElementException;

/**
 * 단방향 연결 리스트(Singly Linked List) 구현체입니다.
 * 
 * @param <T> 리스트에 저장할 데이터의 타입
 */
public class MyLinkedList<T> {

    /**
     * 리스트를 구성하는 노드 클래스입니다.
     * 데이터와 다음 노드에 대한 참조를 가집니다.
     */
    private static class Node<E> {
        E data; // 노드에 저장된 데이터
        Node<E> next; // 다음 노드를 가리키는 참조

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head; // 리스트의 첫 번째 노드
    private int size; // 리스트의 크기

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * 리스트의 끝에 데이터를 추가합니다.
     * 
     * @param data 추가할 데이터
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * 특정 인덱스에 있는 데이터를 반환합니다.
     * 
     * @param index 가져올 데이터의 인덱스 (0부터 시작)
     * @return 해당 위치의 데이터
     * @throws IndexOutOfBoundsException 인덱스가 범위를 벗어난 경우
     */
    public T get(int index) {
        checkBounds(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * 특정 인덱스에 있는 노드를 삭제합니다.
     * 
     * @param index 삭제할 노드의 인덱스 (0부터 시작)
     * @throws IndexOutOfBoundsException 인덱스가 범위를 벗어난 경우
     */
    public void remove(int index) {
        checkBounds(index);
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            // current는 삭제할 노드의 바로 앞 노드입니다.
            // current.next를 삭제할 노드의 다음 노드로 연결하여 중간 노드를 제거합니다.
            current.next = current.next.next;
        }
        size--;
    }

    /**
     * 리스트의 크기(노드 개수)를 반환합니다.
     * 
     * @return 노드 개수
     */
    public int size() {
        return size;
    }

    /**
     * 리스트가 비어있는지 확인합니다.
     * 
     * @return 비어있으면 true, 아니면 false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 인덱스 범위를 검사합니다.
     * 
     * @param index 검사할 인덱스
     */
    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
