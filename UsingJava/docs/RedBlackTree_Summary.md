# Red-Black Tree (레드-블랙 트리)

## 개념
**Red-Black Tree**는 **자가 균형 이진 탐색 트리(Self-Balancing Binary Search Tree)**의 일종입니다.
일반적인 이진 탐색 트리(BST)는 최악의 경우(예: 정렬된 데이터 삽입) 높이가 $O(n)$이 되어 성능이 저하되지만, Red-Black Tree는 트리의 높이를 항상 $O(\log n)$으로 유지합니다.

Java의 `TreeMap`, `TreeSet` (그리고 C++의 `std::map`) 등이 내부적으로 Red-Black Tree를 사용하여 구현되어 있습니다.

## 핵심 속성 (Red-Black Properties)
Red-Black Tree는 다음 5가지 규칙을 만족해야 합니다.

1.  **모든 노드는 Red 또는 Black이다.**
2.  **루트 노드(Root)는 항상 Black이다.**
3.  **모든 리프 노드(NIL)는 Black이다.** (여기서 리프는 데이터를 담지 않는 Sentinel Node를 의미함)
4.  **Red 노드의 자식은 항상 Black이다.** (즉, **Red 노드가 연속해서 나타날 수 없다.** 이를 "Double Red" 불가라고 함)
5.  **어떤 노드에서 리프 노드(NIL)까지 가는 모든 경로에는 동일한 개수의 Black 노드가 있다.** (이를 "Black Height"가 같다고 함)

이 규칙들 덕분에, 루트에서 가장 먼 리프까지의 경로가 가장 가까운 리프까지의 경로보다 2배 이상 길지 않음이 보장됩니다. (대략적으로 균형이 잡힘)

## 주요 동작 원리

### 1. 회전 (Rotation)
트리의 균형을 맞추기 위해 노드의 위치를 바꾸는 연산입니다. $O(1)$ 시간이 소요됩니다.
-   **Left Rotation**: 오른쪽 자식을 부모로 올리고, 부모를 왼쪽 자식으로 내림.
-   **Right Rotation**: 왼쪽 자식을 부모로 올리고, 부모를 오른쪽 자식으로 내림.

### 2. 삽입 (Insertion)
1.  일반적인 BST처럼 삽입합니다.
2.  새로 삽입된 노드는 항상 **Red**로 칠합니다. (Black Height 규칙을 깨지 않기 위해)
3.  만약 부모가 **Red**라면 규칙 4(Double Red)를 위반하므로, **재조정(Rebalancing)**이 필요합니다.
    -   **Case 1 (Uncle is Red)**: 부모와 삼촌(부모의 형제)을 Black으로, 조부모를 Red로 칠함. (조부모에서 다시 문제 발생 가능 -> 재귀적 해결)
    -   **Case 2 (Uncle is Black, Triangle)**: 회전을 통해 Case 3 형태로 만듦.
    -   **Case 3 (Uncle is Black, Line)**: 부모를 Black, 조부모를 Red로 칠하고 회전하여 균형 회복.

### 3. 삭제 (Deletion)
1.  일반적인 BST 삭제를 수행합니다.
2.  삭제된 노드(또는 대체된 노드)가 **Black**이었다면, Black Height가 감소하여 규칙 5가 깨지므로 **재조정**이 필요합니다.
3.  삭제된 노드의 위치를 대체하는 노드(x)를 기준으로 형제(w)의 색상과 자녀들의 색상에 따라 4가지 Case로 나누어 처리합니다.

## 시간 복잡도

| 연산 | 평균 | 최악 |
| :--- | :--- | :--- |
| **Search (검색)** | $O(\log n)$ | $O(\log n)$ |
| **Insert (삽입)** | $O(\log n)$ | $O(\log n)$ |
| **Delete (삭제)** | $O(\log n)$ | $O(\log n)$ |

> [!NOTE]
> **AVL Tree vs Red-Black Tree**
> - **AVL Tree**: 더 엄격하게 균형을 맞춤 (높이 차이 1 이하). **조회(Search)** 성능이 더 좋음.
> - **Red-Black Tree**: 균형을 조금 덜 엄격하게 맞춤. 회전 수가 적어 **삽입/삭제(Insert/Delete)** 성능이 더 좋음. Java의 표준 라이브러리에서는 주로 RB Tree를 사용함.
