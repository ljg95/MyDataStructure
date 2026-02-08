---
marp: true
theme: default
paginate: true
backgroundColor: #fff
style: |
  section { font-family: 'Arial', sans-serif; }
  h1 { color: #2c3e50; }
  h2 { color: #34495e; }
  table { width: 100%; font-size: 24px; }
  th { background-color: #ecf0f1; }
---

# 자바 자료구조 구현 프로젝트
## Java Data Structures Implementation

직접 구현하며 배우는 핵심 자료구조
(`UsingJava` Project)

---

# 1. 프로젝트 개요

- **목표**: 자바의 핵심 자료구조를 직접 구현하여 원리 이해
- **범위**: List, Stack, Queue, Map, Set, Heap, Red-Black Tree
- **특징**:
    - 모든 자료구조를 제네릭(Generic)으로 구현
    - `java.util` 패키지와 유사한 API 설계
    - 최적화된 알고리즘 적용 (예: Red-Black Tree)

---

# 2. 구현 목록 (Overview)

| 자료구조 | 클래스명 | 내부 구현 | 시간 복잡도 (Search) |
| :--- | :--- | :--- | :--- |
| **List** | `MyArrayList` | 동적 배열 | O(1) (Index) |
| **Stack** | `MyStack` | 동적 배열 (Adapter) | - |
| **Queue** | `MyQueue` | 연결 리스트 | - |
| **Map** | `MyHashMap` | 해시 테이블 (Chaining) | O(1) (Avg) |
| **Map** | `MyTreeMap` | **Red-Black Tree** | **O(log n)** |
| **Set** | `MyHashSet` | 해시 맵 | O(1) (Avg) |
| **Heap** | `MyHeap` | 배열 (Complete Binary Tree) | O(1) (Peek) |

---

# 3. List: Array vs LinkedList

<div class="mermaid">
graph LR
    subgraph MyArrayList [Array List]
        A[0: Data A] --- B[1: Data B] --- C[2: Data C]
        style A fill:#e1f5fe
        style B fill:#e1f5fe
        style C fill:#e1f5fe
    end

    subgraph LinkedList [Linked List]
        N1((Data A)) --> N2((Data B)) --> N3((Data C))
        style N1 fill:#f3e5f5
        style N2 fill:#f3e5f5
        style N3 fill:#f3e5f5
    end
</div>

- **ArrayList**: 인덱스 접근 빠름, 중간 삽입/삭제 느림 (이동 필요)
- **LinkedList**: 중간 삽입/삭제 빠름 (참조 변경), 접근 느림 (순회 필요)

---

# 4. 구조: Stack & Queue

<div class="mermaid">
chat
    participant User
    participant Stack
    participant Queue

    User->>Stack: Push 'A' (1)
    User->>Stack: Push 'B' (2)
    Stack->>User: Pop 'B' (Last In, First Out)
    
    User->>Queue: Offer 'A' (1)
    User->>Queue: Offer 'B' (2)
    Queue->>User: Poll 'A' (First In, First Out)
</div>

- **Stack (LIFO)**: 나중에 들어온 것이 먼저 나감 (뒤로 가기, 실행 취소)
- **Queue (FIFO)**: 먼저 들어온 것이 먼저 나감 (대기열, 프린터)

---

# 5. Map: HashMap 구조

**Separate Chaining** 방식을 그림으로 표현:

<div class="mermaid">
graph TD
    Key1[Key: "Apple"] -->|Hash Function| Idx1[Index 1]
    Key2[Key: "Banana"] -->|Hash Function| Idx2[Index 2]
    Key3[Key: "Melon"] -->|Hash Function| Idx1

    Idx1 --> Node1["(Apple, 100)"]
    Node1 --> Node3["(Melon, 200)"]
    Idx2 --> Node2["(Banana, 150)"]
    
    style Idx1 fill:#fff9c4
    style Idx2 fill:#fff9c4
    style Node1 fill:#c8e6c9
    style Node3 fill:#ffcdd2
</div>

- **충돌(Collision)** 발생 시, 같은 인덱스에 **연결 리스트**로 데이터를 연결하여 저장합니다.

---

# 6. 핵심: Red-Black Tree (TreeMap)

자가 균형 이진 탐색 트리 (Self-Balancing BST)

<div class="mermaid">
graph TD
    R((ROOT: Black))
    R --- A((A: Red))
    R --- B((B: Red))
    A --- A1((1: Black))
    A --- A2((2: Black))
    
    style R fill:#000,color:#fff
    style A fill:#f00,color:#fff
    style B fill:#f00,color:#fff
    style A1 fill:#000,color:#fff
    style A2 fill:#000,color:#fff
</div>

- **규칙**:
    1. 노드는 Red 혹은 Black
    2. 루트는 항상 Black
    3. Red 노드의 자식은 Black (No Double Red)
- **장점**: 어떤 데이터가 들어와도 트리의 높이를 **O(log n)**으로 유지하여 최악의 경우를 방지함.

---

# 7. 결론

## 프로젝트 성과
1. **깊은 이해**: 라이브러리 내부 동작 원리 파악
2. **최적화 경험**: 이진 탐색 트리 -> 레드-블랙 트리로 성능 개선
3. **코드 품질**: 제네릭, 인터페이스 활용, 문서화

---

# 감사합니다
## Q & A
