public class Example {

    public static void main(String[] args) {
        DelayedQueue<String> s = new MyQueue<String>(4); //delay condition of 4
        s.enqueue("first element");
        s.enqueue("something else");
        System.out.println(s.dequeue()); //return value is null, because so far only 2 elements have been pushed
        s.enqueue("third");
        s.enqueue("fourth");
        System.out.println(s.dequeue()); //return value is “first element”
        s.enqueue("another one");
        System.out.println(s.dequeue()); //return value is null again, because the delay condition has been reset
        s.enqueue("2");
        s.enqueue("3");
        s.enqueue("4");
        System.out.println(s.dequeue()); // return value is “something else”
        System.out.println(s.dequeue()); // return value is “third”
        System.out.println(s.dequeue()); // return values is “fourth”
        System.out.println(s.dequeue()); // return values is “another one”
        System.out.println(s.dequeue()); // return values is “2”
        System.out.println(s.dequeue());//return values is “3”

    }
}
