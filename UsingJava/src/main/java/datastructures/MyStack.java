package datastructures;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * 제네릭 스택(Stack) 구현체입니다.
 * 내부적으로 ArrayList를 사용하여 데이터를 저장합니다.
 * 
 * @param <T> 스택에 저장할 데이터의 타입
 */
public class MyStack<T> {
    private final List<T> elements = new ArrayList<>();

    /**
     * 스택의 맨 위에 데이터를 추가합니다 (Push).
     * 
     * @param element 추가할 데이터
     */
    public void push(T element) {
        elements.add(element);
    }

    /**
     * 스택의 맨 위에 있는 데이터를 제거하고 반환합니다 (Pop).
     * 
     * @return 제거된 데이터
     * @throws EmptyStackException 스택이 비어있는 경우 발생
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.remove(elements.size() - 1);
    }

    /**
     * 스택의 맨 위에 있는 데이터를 제거하지 않고 반환합니다 (Peek).
     * 
     * @return 맨 위의 데이터
     * @throws EmptyStackException 스택이 비어있는 경우 발생
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.get(elements.size() - 1);
    }

    /**
     * 스택이 비어있는지 확인합니다.
     * 
     * @return 비어있으면 true, 아니면 false
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * 스택에 저장된 데이터의 개수를 반환합니다.
     * 
     * @return 데이터 개수
     */
    public int size() {
        return elements.size();
    }
}
