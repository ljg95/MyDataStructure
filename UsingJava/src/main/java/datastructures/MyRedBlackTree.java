package datastructures;

/**
 * 레드-블랙 트리(Red-Black Tree) 구현체입니다.
 * 자가 균형 이진 탐색 트리로, 삽입과 삭제 시 트리의 높이를 O(log n)으로 유지합니다.
 *
 * @param <K> Key의 타입 (Comparable)
 * @param <V> Value의 타입
 */
public class MyRedBlackTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        K key;
        V value;
        Node left, right, parent;
        boolean color;

        Node(K key, V value, boolean color, Node parent, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private Node T_NIL; // 리프 노드를 나타내는 Sentinel Node (항상 Black)
    private int size;

    public MyRedBlackTree() {
        T_NIL = new Node(null, null, BLACK, null, null, null);
        root = T_NIL;
        size = 0;
    }

    /**
     * 값을 저장합니다. (Insert)
     */
    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");

        Node node = root;
        Node parent = T_NIL;

        // 1. 일반적인 BST 삽입 위치 찾기
        while (node != T_NIL) {
            parent = node;
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node.value = value; // Update
                return;
            }
        }

        // 2. 새 노드 삽입 (항상 RED)
        Node newNode = new Node(key, value, RED, parent, T_NIL, T_NIL);

        if (parent == T_NIL) {
            root = newNode;
        } else {
            int cmp = key.compareTo(parent.key);
            if (cmp < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }

        size++;

        // 3. 재조정 (Fix Violation)
        fixInsert(newNode);
    }

    /**
     * 값을 조회합니다. (Search)
     */
    public V get(K key) {
        Node node = search(key);
        return node == T_NIL ? null : node.value;
    }

    private Node search(K key) {
        Node node = root;
        while (node != T_NIL) {
            int cmp = key.compareTo(node.key);
            if (cmp == 0)
                return node;
            if (cmp < 0)
                node = node.left;
            else
                node = node.right;
        }
        return T_NIL;
    }

    /**
     * 값을 삭제합니다. (Delete)
     * 현재는 기본적인 BST 삭제 로직만 구현되어 있으며, 삭제 후 RB Tree 속성 복구(Rebalancing)는 미구현 상태입니다.
     */
    public void remove(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");

        Node z = search(key);
        if (z == T_NIL)
            return; // Key not found

        Node y = z;
        // boolean yOriginalColor = y.color; // For fixup later
        Node x;

        if (z.left == T_NIL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == T_NIL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            // yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        size--;
        // if (yOriginalColor == BLACK) fixDelete(x); // Rebalancing pending
    }

    private void transplant(Node u, Node v) {
        if (u.parent == T_NIL) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private Node minimum(Node node) {
        while (node.left != T_NIL) {
            node = node.left;
        }
        return node;
    }

    /**
     * 삽입 후 레드-블랙 트리의 속성을 유지하기 위해 재조정합니다.
     */
    private void fixInsert(Node k) {
        Node u; // uncle
        while (k.parent.color == RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left; // uncle
                if (u.color == RED) {
                    // Case 1: 부모와 삼촌이 모두 RED -> 부모/삼촌 BLACK, 조부모 RED
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        // Case 2: 삼촌 BLACK, 현재 노드가 안쪽 자손(Triangle) -> 회전하여 Case 3으로 만듦
                        k = k.parent;
                        rotateRight(k);
                    }
                    // Case 3: 삼촌 BLACK, 현재 노드가 바깥쪽 자손(Line) -> 색상 변경 후 회전
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rotateLeft(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right; // uncle
                if (u.color == RED) {
                    // Case 1
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        // Case 2
                        k = k.parent;
                        rotateLeft(k);
                    }
                    // Case 3
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rotateRight(k.parent.parent);
                }
            }
            if (k == root)
                break;
        }
        root.color = BLACK; // 루트는 항상 BLACK
    }

    /**
     * 왼쪽으로 회전합니다.
     */
    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != T_NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == T_NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    /**
     * 오른쪽으로 회전합니다.
     */
    private void rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != T_NIL) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == T_NIL) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 키 목록 반환(In-Order)
    public MyArrayList<K> keys() {
        MyArrayList<K> list = new MyArrayList<>();
        inOrderTraversal(root, list);
        return list;
    }

    private void inOrderTraversal(Node node, MyArrayList<K> list) {
        if (node != T_NIL) {
            inOrderTraversal(node.left, list);
            list.add(node.key);
            inOrderTraversal(node.right, list);
        }
    }
}
