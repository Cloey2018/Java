public class Example {

    public static void main(String[] args) {
        try{
            // Example 1
//            DelayedStack<String> s = new MyStack<String>(4); //delay condition of 4
//            s.push("first element");
//            s.push("something else");
//            System.out.println(s.pop()); //return value is null, because so far only 2 elements have been pushed
//            s.push("third");
//            s.push("fourth");
//            System.out.println(s.pop()); //return value is “fourth”
//            s.push("another one");
//            System.out.println(s.pop()); //return value is null again, because the delay condition has been reset
//            s.push("2");
//            s.push("3");
//            s.push("4");
//            System.out.println(s.pop()); // return value is “4”
//            System.out.println(s.pop()); // return value is “3”
//            System.out.println(s.pop());
//            System.out.println(s.pop());
//            System.out.println(s.pop());
//            System.out.println(s.pop());//return values are “2”, “another one”, “third”, “something else”


            // Example 2
            DelayedStack<String> s = new MyStack<String>(0); //delay condition of 0 means that there is never a restriction. Same with negative values, or 1.
            s.push("hello");
            System.out.println(s.pop()); //returns “hello”
            s.setMaximumDelay(2);
            System.out.println(s.getMaximumDelay()); //return value is 2
            // System.out.println(s.pop()); //IllegalStateException is thrown, the stack is empty
            s.push("X");
            //System.out.println(s.getDelay());
            s.push("a");
            //System.out.println(s.getDelay());
            s.push("b");
            //System.out.println(s.getDelay());
            s.push("c");
            //System.out.println(s.getDelay());
            System.out.println(s.pop()); //return value is "c"
            //System.out.println(s.getDelay());
            //System.out.println(s.getMaximumDelay());
            System.out.println(s.pop()); //return value is "b"
            s.setMaximumDelay(4);
            //System.out.println(s.getMaximumDelay());
            System.out.println(s.getDelay()); //return value is 0.
            System.out.println(s.pop()); //return value is “a” – delay is not set until the next push
            //System.out.println(s.getDelay()); //0
            s.push("Y");
            //System.out.println(s.getDelay()); //3
            s.push("Z");
            //System.out.println(s.getDelay()); //2
            s.setMaximumDelay(-1);
            System.out.println(s.getDelay()); //return value is 2 – delay is not set yet
            s.push("An");
            System.out.println(s.getDelay()); //return value is 1
            System.out.println(s.pop()); //return value is null
            s.push("AX");
            System.out.println(s.getDelay()); //return value is 0
            System.out.println(s.pop()); //return value is “AX”
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }
    }
}
