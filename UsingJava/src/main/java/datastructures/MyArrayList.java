package datastructures;

import java.util.Arrays;

/**
 * 동적 배열(Dynamic Array) 리스트 구현체입니다.
 * 내부적으로 배열을 사용하며, 데이터가 가득 차면 자동으로 크기를 늘립니다.
 * 인덱스를 통한 빠른 접근(O(1))이 가능합니다.
 *
 * @param <T> 저장할 요소의 타입
 */
public class MyArrayList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * 기본 생성자. 초기 용량은 10입니다.
     */
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * 리스트의 가장 끝에 요소를 추가합니다.
     * 배열이 가득 찼다면 용량을 2배로 늘립니다.
     * 
     * @param element 추가할 요소
     */
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * 특정 인덱스에 요소를 삽입합니다.
     * 해당 인덱스 이후의 요소들은 한 칸씩 뒤로 밀려납니다.
     * 
     * @param index   삽입할 위치 (0 ~ size)
     * @param element 추가할 요소
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();

        // index부터 끝까지의 요소들을 한 칸씩 뒤로 이동
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * 특정 인덱스의 요소를 반환합니다.
     * 
     * @param index 가져올 위치 (0 ~ size-1)
     * @return 해당 위치의 요소
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    /**
     * 특정 인덱스의 요소를 교체합니다.
     * 
     * @param index   교체할 위치 (0 ~ size-1)
     * @param element 새로운 요소
     * @return 이전에 저장되어 있던 요소
     */
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T oldElement = (T) elements[index];
        elements[index] = element;
        return oldElement;
    }

    /**
     * 특정 인덱스의 요소를 삭제합니다.
     * 삭제된 위치 이후의 요소들은 한 칸씩 앞으로 당겨집니다.
     * 
     * @param index 삭제할 위치 (0 ~ size-1)
     * @return 삭제된 요소
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        T removedElement = (T) elements[index];

        // index + 1부터 끝까지의 요소들을 한 칸씩 앞으로 이동
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }

        elements[--size] = null; // 마지막 요소 참조 해제 (GC 유도)
        return removedElement;
    }

    /**
     * 리스트에 특정 요소가 포함되어 있는지 확인합니다.
     * 
     * @param element 찾을 요소
     * @return 포함되어 있으면 true, 아니면 false
     */
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    /**
     * 특정 요소가 처음으로 나타나는 인덱스를 반환합니다.
     * 
     * @param element 찾을 요소
     * @return 인덱스 (없으면 -1)
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[i] == null)
                    return i;
            } else {
                if (element.equals(elements[i]))
                    return i;
            }
        }
        return -1;
    }

    /**
     * 리스트의 현재 요소 개수를 반환합니다.
     * 
     * @return 요소 개수
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
     * 리스트의 모든 요소를 삭제하고 초기화합니다.
     */
    public void clear() {
        // 모든 참조를 null로 설정하여 GC가 동작하도록 함
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 배열의 용량이 가득 찼는지 확인하고, 찼다면 2배로 늘립니다.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }
}
