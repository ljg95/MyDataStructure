# Heap (힙)

## 개념
**Heap**은 최댓값이나 최솟값을 빠르게 찾아내기 위해 고안된 **완전 이진 트리(Complete Binary Tree)** 기반의 자료구조입니다.
`MyHeap` 클래스는 생성자에 따라 **Min Heap** 또는 **Max Heap**으로 동작하도록 구현되었습니다.

### 종류
1.  **Min Heap (최소 힙)**: 부모 노드의 값이 자식 노드의 값보다 작거나 같습니다. (루트 = 최솟값)
2.  **Max Heap (최대 힙)**: 부모 노드의 값이 자식 노드의 값보다 크거나 같습니다. (루트 = 최댓값)

## 구조 (ArrayList 기반 구현)
힙은 완전 이진 트리이므로 배열(`ArrayList`)을 사용하여 효율적으로 구현할 수 있습니다.
노드들 간의 관계는 **인덱스(Index)**를 통해 계산됩니다.

### 인덱스 공식 (0-based Indexing)
특정 노드의 인덱스를 `i`라고 할 때:
-   **왼쪽 자식**: `2 * i + 1`
-   **오른쪽 자식**: `2 * i + 2`
-   **부모 노드**: `(i - 1) / 2`

## 주요 연산 및 시간 복잡도

| 연산 | 설명 | 시간 복잡도 |
| :--- | :--- | :--- |
| **Insert (삽입)** | 힙의 가장 끝에 데이터를 추가한 후, 부모와 비교하며 위로 올라갑니다. (**Heapify Up**) | **O(log n)** |
| **Poll (추출)** | 루트 노드(최소/최대)를 제거하고 반환합니다. 가장 마지막 노드를 루트로 옮긴 뒤, 자식과 비교하며 내려갑니다. (**Heapify Down**) | **O(log n)** |
| **Peek (조회)** | 루트 노드의 값을 제거하지 않고 반환합니다. | **O(1)** |

## 구현 특징 (MyHeap)
-   `Comparator`를 사용하여 비교 로직을 추상화했습니다.
    -   Min Heap: `Comparator.naturalOrder()` (오름차순)
    -   Max Heap: `Collections.reverseOrder()` (내림차순)
-   이를 통해 하나의 `heapifyUp`, `heapifyDown` 메소드로 Min/Max Heap 로직을 모두 처리할 수 있습니다.

---
> [!TIP]
> **PriorityQueue 활용**
> 자바의 `java.util.PriorityQueue`도 내부적으로는 이와 같은 Heap 구조를 사용합니다.
> - 기본 생성: `new PriorityQueue<>();` (Min Heap)
> - 최대 힙 생성: `new PriorityQueue<>(Collections.reverseOrder());` (Max Heap)
