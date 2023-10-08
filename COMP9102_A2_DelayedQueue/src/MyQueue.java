public class MyQueue<E> implements  DelayedQueue<E>{
    private E[] queueArr;
    private int maxDelay = 0;
    private int newDelay = 0;
    private int actualSize = 0;
    private boolean containsNull = false;
    private int enNum = 0;
    private int deNum = 0;

    public MyQueue(int mdValue){
        queueArr = (E[]) new Object[1000];
        maxDelay = mdValue;
        newDelay = mdValue;
    }
    /**
     * Returns the number of elements currently in the queue.
     * @return The size of the queue.
     */
    public int size(){
        return actualSize;
    }

    /**
     * Add an element to this queue.
     * @param element The element to be added.
     */
    public void enqueue(E element){
        E[] newArr;
        if (deNum > 0){
            if (maxDelay != newDelay){
                maxDelay = newDelay;
            }
            enNum = 0;
            deNum = 0;
        }
        if (actualSize == queueArr.length){
            newArr = (E[]) new Object[actualSize*2];
            for (int i = 0; i < actualSize; i ++){
                newArr[i] = queueArr [i];
            }
            queueArr = newArr;
        }
        if (element == null) containsNull = true;
        queueArr [actualSize] = element;
        actualSize ++;
        enNum ++;
        //System.out.println("pushNum_af " + pushNum);
        //System.out.println("popNum_af " + popNum);
    }

    /**
     * Attempt to remove an element from this queue.
     * The first element to remove is the most recent one that was added.
     * If the enqueue is empty, throw an IllegalStateException.
     * If an element cannot be removed due to the delay condition, return null.
     *
     * @return The element that was removed.
     * @throws IllegalStateException if the queue is empty.
     */
    public E dequeue() throws IllegalStateException{
        E deEle = null;

        if (actualSize != 0){
            if (enNum >= maxDelay){
                deEle = queueArr[0];
                for (int i = 0; i < actualSize - 1; i ++){
                    queueArr[i] = queueArr[i + 1];
                }
                queueArr[actualSize - 1] = null;
                actualSize --;
                deNum ++;
                //System.out.println(popNum);
            }
            return deEle;
        }else throw new IllegalStateException("IllegalStateException is thrown, the stack is empty.");
    }

    /**
     * Return the element at the front of this queue, without removing it.
     *
     * @return The element at the front of the queue.
     * @throws IllegalStateException if the queue is empty.
     */
    public E peek() throws IllegalStateException{
        if (actualSize != 0){
            return (E) queueArr[0];
        }else throw new IllegalStateException ("IllegalStateException is thrown, the stack is empty.");
    }

    /**
     * Returns how many more elements must be added before the queue will allow removals to commence.
     * @return
     */
    public int getDelay(){
        int delayNum = 0;
        if (enNum <= maxDelay) delayNum = maxDelay - enNum;
        return delayNum;
    }

    /**
     * Sets the maximum delay, which is the number of elements that must be added to the queue before any can be removed.
     * Once a removal is allowed to occur, there is no limit on the number of removals.
     * Modifying this value while the queue is in use does not change the current delay value. It only changes the next time the delay is reset.
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
     * Remove all elements in this queue. Similarly to the pop operation, it is constrained by the delay condition.
     * @return Whether the clear succeeded or failed due to the delay condition.
     */
    public boolean clear(){
        boolean success = false;
        if (this.getDelay() == 0) {
            success = true;
            actualSize = 0;
            deNum ++;
        }
        return success;
    }

    /**
     * Check whether the queue contains this element, according to the .equals() method of the element.
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
                    if (elem.equals(queueArr[i])){
                        contains = true;
                        break;
                    }
                }
            }
        }
        return contains;
    }
}
