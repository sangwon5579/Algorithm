import logic.DoublyLinkedList;

public class Main {
    public static void main(String[] args) {
        DoublyLinkedList<Student> list = new DoublyLinkedList<>();

        list.add(new Student("name1", 100));
        list.add(new Student("name2", 70));
        list.add(new Student("name3", 85));
        list.add(new Student("name4", 95));
        System.out.println();

        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i) );
        }

        System.out.println(list.size());
        System.out.println(list.isEmpty());
        list.add(0,new Student("NEWname1", 10));
        System.out.println(list.get(0));
        list.remove(0);
        System.out.println(list.get(0));
        list.clear();
        System.out.println(list.isEmpty());

    }
}

class Student implements Comparable<Student>{
    String name;
    int score;

    Student(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String toString(){
        return "name: " + name + ", score: " + score;
    }

    @Override
    public int compareTo(Student o) {
        return o.score - this.score;
    }
}