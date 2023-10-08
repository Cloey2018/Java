
public class MyStack <E> implements DelayedStack <E> {
    private Object[] stackArr;
    private int maxDelay = 0;
    private int newDelay = 0;
    private int actualSize = 0;
    private boolean containsNull = false;
    private int pushNum = 0;
    private int popNum = 0;

    public MyStack (int mdValue){
        stackArr = new Object[1000];
        maxDelay = mdValue;
        newDelay = mdValue;
    }


    /**
     * Returns the number of elements currently in the stack.
     * @return The size of the stack.
     */
    public int size(){
        return actualSize;
    }

    /**
     * Add an element to this stack.
     * @param element The element to be added.
     */
    public void push(E element){
        Object[] newArr;
        if (popNum > 0){
            if (maxDelay != newDelay){
                maxDelay = newDelay;
            }
            pushNum = 0;
            popNum = 0;
        }
        if (actualSize == stackArr.length){
            newArr = new Object[actualSize*2];
            for (int i = 0; i < actualSize; i ++){
                newArr[i] = stackArr [i];
            }
            stackArr = newArr;
        }
        if (element == null) containsNull = true;
        stackArr [actualSize] = element;
        actualSize ++;
        pushNum ++;
        //System.out.println("pushNum_af " + pushNum);
        //System.out.println("popNum_af " + popNum);
    }

    /**
     * Attempt to remove an element from this stack.
     * The first element to remove is the most recent one that was added.
     * If the stack is empty, throw an IllegalStateException.
     * If an element cannot be removed due to the delay condition, return null.
     *
     * @return The element that was removed.
     * @throws IllegalStateException if the stack is empty.
     */
    public E pop() throws IllegalStateException{
        E popEle = null;
        if (actualSize != 0){
            if (pushNum >= maxDelay){
                popEle = (E) stackArr[actualSize - 1];
                stackArr[actualSize - 1] = null;
                actualSize --;
                popNum ++;
                //System.out.println(popNum);
            }
            return popEle;
        }else throw new IllegalStateException("IllegalStateException is thrown, the stack is empty.");
    }

    /**
     * Return the element at the top of this stack, without removing it.
     *
     * @return The element at the top of the stack.
     * @throws IllegalStateException if the stack is empty.
     */
    public E peek() throws IllegalStateException{
        if (actualSize != 0){
            return (E) stackArr[actualSize - 1];
        }else throw new IllegalStateException ("IllegalStateException is thrown, the stack is empty.");
    }

    /**
     * Returns how many more elements must be added before the stack will allow removals to commence.
     * @return
     */
    public int getDelay(){
        int delayNum = 0;
        //System.out.println("pushNum_af " + pushNum);
        if (pushNum <= maxDelay) delayNum = maxDelay - pushNum;
        return delayNum;
    }

    /**
     * Sets the maximum delay, which is the number of elements that must be added to the stack before any can be removed.
     * Once a removal is allowed to occur, there is no limit on the number of removals.
     * Modifying this value while the stack is in use does not change the current delay value. It only changes the next time the delay is reset.
     *
     * @param d The number of elements that must be added before any can be removed.
     */
    public void setMaximumDelay(int d){
        newDelay = d;
    }

    /**
     * Get the maximum possible delay, which is the number of push operations that must occur before pop operations can occur, given that the first push operation has not yet occurred (ie. a pop operation just occurred).
     * @return The maximum number of push operations before pop operations can occur.
     */
    public int getMaximumDelay(){
        return newDelay;
    }

    /**
     * Remove all elements in this stack. Similarly to the pop operation, it is constrained by the delay condition.
     * @return Whether the clear succeeded or failed due to the delay condition.
     */
    public boolean clear(){
        boolean success = false;
        if (this.getDelay() == 0) {
            success = true;
            actualSize = 0;
            popNum ++;
        }
        return success;
    }

    /**
     * Check whether the stack contains this element, according to the .equals() method of the element.
     * @param elem The element to search for.
     * @return Whether the element was found or not.
     */
    public boolean contains(E elem){
        boolean contains = false;
        if (actualSize != 0){
            if (elem == null){
                contains = containsNull;
            }else{
                for (int i = 0; i < actualSize; i ++){
                    if (elem.equals(stackArr[i])){
                        contains = true;
                        break;
                    }
                }
            }
        }
        return contains;
    }
}
