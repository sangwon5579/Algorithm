#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

using namespace std;

// 아이템 구조체: 무게(weight)와 가치(value)를 저장
struct Item {
    float weight;  // 무게
    int   value;   // 가치
};

// 결정 트리의 노드 구조체
struct Node {
    int   level;   // 현재 고려 중인 아이템 인덱스
    int   profit;  // 지금까지 얻은 가치 합
    float weight;  // 지금까지 사용한 무게 합
    int   bound;   // 이 노드 하위에서 얻을 수 있는 최대 가치 상한
};

// 가치/무게 비율이 높은 순으로 정렬하기 위한 비교 함수
bool cmp(const Item &a, const Item &b) {
    double r1 = (double)a.value / a.weight;
    double r2 = (double)b.value / b.weight;
    return r1 > r2;
}

// 노드 u로부터 시작할 때 이 하위 트리에서 얻을 수 있는 최대 가치(상한)를 계산
int bound(const Node &u, int n, int W, const vector<Item> &arr) {
    // 이미 허용 용량을 초과했다면 더 이상의 가치 추가 불가능
    if (u.weight >= W) return 0;

    int profit_bound = u.profit;
    float totweight  = u.weight;
    int j = u.level + 1;

    // 남은 아이템들을 가치/무게 비율이 높은 순으로(이미 정렬됨) 전부 담아보기
    while (j < n && totweight + arr[j].weight <= W) {
        totweight  += arr[j].weight;
        profit_bound += arr[j].value;
        j++;
    }

    // 마지막 아이템은 부분적으로 담아서 상한값 계산
    if (j < n) {
        profit_bound += (int)((W - totweight) * (arr[j].value / arr[j].weight));
    }
    return profit_bound;
}

// 최대 이익(profit)을 구하는 함수
int knapsack(int W, vector<Item> &arr) {
    int n = arr.size();
    // 가치/무게 비율 기준 내림차순 정렬
    sort(arr.begin(), arr.end(), cmp);

    queue<Node> Q;
    Node u, v;
    // 루트(더미) 노드 초기화
    u.level = -1;
    u.profit = 0;
    u.weight = 0;
    Q.push(u);

    int maxProfit = 0;

    // BFS 방식으로 결정 트리 탐색
    while (!Q.empty()) {
        u = Q.front(); Q.pop();

        // 마지막 레벨이면 더 이상 내려가지 않음
        if (u.level == n - 1) continue;

        // 다음 아이템을 '담는' 경우
        v.level  = u.level + 1;
        v.weight = u.weight + arr[v.level].weight;
        v.profit = u.profit  + arr[v.level].value;

        // 유효한 해라면 최대값 갱신
        if (v.weight <= W && v.profit > maxProfit)
            maxProfit = v.profit;

        // 상한 계산 후, 더 가능성 있으면 큐에 추가
        v.bound = bound(v, n, W, arr);
        if (v.bound > maxProfit)
            Q.push(v);

        // 다음 아이템을 '담지 않는' 경우
        v.weight = u.weight;
        v.profit = u.profit;
        v.bound  = bound(v, n, W, arr);
        if (v.bound > maxProfit)
            Q.push(v);
    }

    return maxProfit;
}

int main() {
    int W = 10;  // 배낭 용량
    vector<Item> arr = {
        {2.0f,   40},
        {3.14f,  50},
        {1.98f, 100},
        {5.0f,   95},
        {3.0f,   30}
    };

    cout << "최대 이익 = " << knapsack(W, arr) << '\n';
    return 0;
}
