package logic;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index){
        //범위밖이면 예외
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        //양방향이니깐 앞/뒤 중 더 효율적인거 사용하기
        //뒤에서부터 검색
        if(index +1 > size/2){
            Node<E> x= tail;
            for(int i = size - 1; i > index; i--){
                x = x.prev;
            }
            return x;
        }
        //앞에서부터
        else{
            Node<E> x= head;
            for(int i = 0; i < index; i++){
                x = x.next;
            }
            return x;
        }

    }

    public void addFirst(E value){
        Node<E> newNode = new Node<E>(value);
        newNode.next = head;    //새 노드의 next를 head로 연결

        if(head != null){
            head.prev = newNode;
        }
        head = newNode;
        size++;
        if(head.next == null){
            tail = head;
        }
    }

    public boolean add(E value){
        addLast(value);
        return true;
    }

    public void addLast(E value){
        Node<E> newNode = new Node<E>(value);
        if(size == 0){
            addFirst(value);
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    public void add(int index, E value){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0){
            addFirst(value);
            return;
        }
        if(index == size){
            addLast(value);
            return;
        }
        Node<E> prev_Node = search(index-1);
        Node<E> next_Node = prev_Node.next;
        Node<E> newNode = new Node<E>(value);

        //링크 끊기
        prev_Node.next = null;
        next_Node.prev = null;

        //연결
        prev_Node.next = newNode;
        newNode.prev = prev_Node;
        newNode.next = next_Node;
        next_Node.prev = newNode;

        size++;
    }

    public E remove(){
        Node<E> headNode = head;
        if(headNode == null){
            throw new NoSuchElementException();
        }

        //삭제된 노드 리턴해야되니깐 담을 변수선언
        E element = headNode.data;

        //head 다음노드
        Node<E> nextNode = headNode.next;

        //head 노드의 데이터들을 제거
        headNode.next = null;
        headNode.prev = null;

        if(nextNode != null){
            nextNode.prev = null;
        }

        head = nextNode;
        size--;

        if(size == 0){
            tail = null;
        }
        return element;
    }

    @Override
    public E remove(int index){
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        //첫번쨰 노드 삭제의 경우
        if(index == 0){
            E element = head.data;
            remove();
            return element;
        }

        Node<E> prevNode = search(index-1);
        Node<E> removedNode = prevNode.next;
        Node<E> nextNode = removedNode.next;

        E element = removedNode.data;

        prevNode.next = null;
        removedNode.next = null;
        removedNode.prev = null;
        removedNode.data = null;

        if(nextNode != null){
            nextNode.prev = null;

            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        }
        else{
            tail = prevNode;
        }
        size--;
        return element;
    }

    @Override
    public boolean remove(Object value){
        Node<E> prevNode = head;
        Node<E> x= head;

        for(;x != null; x = x.next){
            if(value.equals(x.data)){
                break;
            }
            prevNode = x;
        }

        //일치하는 요소 없는 경우
        if(x == null){
            return false;
        }

        //삭제 노드 == head인 경우
        if(x.equals(head)){
            remove();
            return true;
        }

        else{
            Node<E> nextNode = x.next;

            prevNode.next = null;
            x.data = null;
            x.prev = null;
            x.next = null;

            if(nextNode != null){
                nextNode.prev = prevNode;
                prevNode.next = nextNode;
            }
            else{
                tail = prevNode;
            }
            size--;
            return true;
        }
    }

    @Override
    public E get(int index){
        return search(index).data;
    }

    @Override
    public void set(int index, E value){
        Node<E> replaceNode = search(index);
        replaceNode.data = value;
    }

    @Override
    //정방향 탐색
    public int indexOf(Object o){
        int index = 0;

        for(Node<E> x = head; x != null; x = x.next){
            if(o.equals(x.data)){
                return index;
            }
            index++;
        }
        return -1;
    }

    public boolean contains(Object item){
        return indexOf(item) >= 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void clear(){
        for (Node<E> x = head; x != null;){
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = nextNode;
        }
        head = null;
        tail = null;
        size = 0;
    }

}
