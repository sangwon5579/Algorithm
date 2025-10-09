# Algorithm
---
## 1. Branch and Bound
  
- 최적화 문제에서 상한/하한(bound)을 이용해 해공간을 효율적으로 탐색하는 기법이다. 문제를 여러 부분문제로 분기(branch)해 트리 구조로 보고, 현재까지 구한 최적값을 기준으로 더 이상 유망하지 않은 분기를 버려(prune) 계산량을 줄인다. 예를 들어 여행하는 세일즈맨 문제에서 현재까지 구한 최단 거리보다 더 길어질 것이 확실한 경로는 더 이상 확장하지 않는다.
  
- **정의 및 원리:**
- 연속적으로 해의 후보 집합을 나누어(rooted tree) 검색하며, 각 분기마다 목적함수의 상/하한 값을 계산하여 최적해가 될 가능성이 없는 경우 탐색하지 않는 방식이다. 모든 후보를 전수조사하되, 가지치기(bound pruning)로 불필요한 분기를 제거함으로써 효율성을 높인다.
- **설계 방법:**
1. 문제를 분기하여 상태공간트리의 노드를 생성한다.    
2. 각 노드에서 효율적인 상한/하한을 구한다.    
3. 현재 best solution이 있고 새로운 분기에서 구한 bound가 이보다 더 나쁨이 확인되면 해당 분기를 탐색하지 않는다. 보통 우선순위큐를 이용해 현재 가장 유망한 상태를 우선 처리한다.  
 예: 0-1 KnapSack문제에서는 예상 최대 이익을 계산해 이것이 기존 최적보다 작으면 해당 분기를 버린다.
- **시간복잡도:**
- 본질적으로 지수적이다. 모든 분기가 최적화하여 자르더라도 최악의 경우 여전히 전수탐색 수준이 될 수 있다. 하지만 효과적인 bound를 사용하면 일반적 완전 탐색보다 훨씬 적은 양을 탐색해 시간 성능이 좋아질 수 있다.
- **예시 문제:**
- NP-hard 최적화 문제에 자주 쓰인다. 대표적으로 TSP, 0-1 Knapsack, job 스케줄링, 그래프 색칠 등이 있다. GFG 튜토리얼에 따르면 0-1 KS(Branch & Bound), 8-퍼즐, N-Queen, TSP 등이 예시이다.
- **장점:**
- 완전탐색을 하면서도 유망하지 않은 경로를 배제하기 때문에 단순 백트래킹보다 실질 탐색량을 크게 줄일 수 있다. 최적해를 구할 수 있고, 메모리 허용범위 내에서 해답을 찾는다.
- **단점:**
- 구현이 복잡하고, 상/하한 계산(휴리스틱)이 문제마다 달라 설계가 어렵다. 또한 잘못된 bound를 쓰면 백트래킹과 큰 차이가 없으며, 여전히 계산량이 매우 클 수 있다. 최악의 경우 시간복잡도는 여전히 지수적이다.
--- 
## 2. Doubly LinkedList  
- 각 노드가 이전 노드와 다음 노드의 포인터(링크)를 모두 가지고 있는 연결 리스트  
- **구조:**
- [ prev | data | next ]  
- prev: 이전 노드를 가리키는 포인터  
- data: 저장할 데이터  
- next: 다음 노드를 가리키는 포인터
- **장점:**
1. 양방향 이동 가능 : 이전 노드와 다음 노드 참조 가능
2. 삭제/삽입 유리 : 적절한 포인터 사용시 O(1)에 삽입/삭제 가능
3. 끝에서부터 순회 가능 : 역방향 탐색이 쉬움
- **단점:**
1. 메모리 사용 증가(prev)
2. 구현 복잡도 증가
- **주요 연산:**
- 삽입 (맨 앞/뒤) : head 또는 tail에 노드 추가	- O(1)
- 삽입 (중간) :	특정 노드 뒤에 삽입	- O(1) (노드 위치 알고 있는 경우)
- 삭제 (특정 노드)	: 노드 포인터만 수정하면 된다 -	O(1)
- 탐색 : 특정 데이터 탐색	 - O(n)
---   
## 3. Divide and Conquer  
#### Design  
- step1 : Divide into 2개 이상의 smaller problems(instances)  
- step2 : Solve each instance - Conquer  
- step3 : Combine the subsolutions(Optional)  
  SubSolution의 상세 디자인은 어디에 있는가? => 없음 => 즉 위의 3개의 step이 상세한 디자인을 포함한 전체 Solution임  
#### Analysis
- Step2 해결  
 1. Recursion : concise, natural, clear한 기계적 해법  
 2. Iteration : faster, save memory space(system stack)  
    -> recursion depth, stack depth : log(n) + 1  
- 시간복잡도  
1. Bast-case : O(1)  
2. Worst-case : 1/2 size가 매번 감소 -> O(log n)  
   -> Mathmatical analysis is required  
- Time Complexity = # of basic operations  
D&C의 Basic Opeation은? => 위의 step1~3의 합  
T(n) =Divide + Conquer + Combine 하는 시간들의 합  
= ? + ? + ?  
- Divide TIme = 1   => WHY? Step1에서 divide 1회 이후 step2에서는 다시 recursive를 할 수도 있고, Iteratively하게 Solve할 수도 있음. 즉 Divide는 Step1에서 1회  
- Conquer Time = T(n/2) => WHY? 나누어진 문제의 왼쪽 or 오른쪽 중 하나만 해결하면 됨. 각각 T(n/2)이므로 T(n/2)  
- Combine Time = 0 => WHY? 계산 후 L과 R의 값을 합칠 필요가 없음. Step3는 Optional임<br>
==> T(n) = 1 + T(n/2) + 0 = 1 + log n <= O(log n)  
### 3.1 Merge Sort
- 정렬되지 않은 영역을 쪼개서 각각의 영역을 정렬하고 이를 합치며 정렬
- 리스트를 두 개의 균등한 크기로 분할하고 분할된 부분리스트를 정렬
- 정렬된 두 개의 부분 리스트를 합하여 전체 리스트를 정렬
- 단점 : 분할한 자료를 저장할 별도의 저장 공간 필요
#### Design 
- step1 : Divide into 2개 이상의 subarrays
- step2 : Solve(Conquer) each subarray - Sort Left, Right
- step3 : Combine the SubSolutions - merge Left, Right
#### Analysis  
n개의 element  
log(n)의 defth -> Complexity = nlog(n)  
- T(n) = divide complexity + conquer complexity + combine complexity<br>
= ? + ? + ?<br>
- Divide Time : 0 => WHY? 코드를 잘 짜면 Divide 시간을 0으로 만들 수 있음. 즉 Copy를 안해도됨. 반드시 필요한 Operation은 아님
- Conquer Time : 2*T(n/2) => WHY? Solve Left + Solve Right = T(n/2) + T(n/2)
- Combine Time : n-1 => WHY? n/2 + n/2 + 1<br>
= 2*T(n/2) + n - 1 <br>
<= 2*T(n/2) + n<br>
...<br>
= n*T(1) + nlog(n)<br>
= nlog(n)<br>
=> O(nlog(n))
#### 성능 향상
- 재귀 호출 감소
  - 크기 1까지 재귀호출하지 말고 소수의 크기(10 이내)일 때는 삽입 정렬
  - sorted를 list로 복사하는 대신 두 개의 배열을 번갈아 사용
  - O(n log n)
- 반복 Merge Sort
  - D&C가 아니라 2개-4개-8개를 바로 합병하는 방식
