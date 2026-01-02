/**
 * Heap
 *
 * An implementation of Fibonacci heap over positive integers 
 * with the possibility of not performing lazy melds and 
 * the possibility of not performing lazy decrease keys.
 *
 */
public class Heap
{
    public final boolean lazyMelds;
    public final boolean lazyDecreaseKeys;
    public HeapNode min;
    private int totalLinks;
    private int totalCuts;
    private int totalHeapifyUp;
    private int numMarkedModes;
    private int size;
    private int numOfTrees;

    
    /**
     *
     * Constructor to initialize an empty heap.
     *
     */
    public Heap(boolean lazyMelds, boolean lazyDecreaseKeys)
    {
        this.lazyMelds = lazyMelds;
        this.lazyDecreaseKeys = lazyDecreaseKeys;
        // student code can be added here
    }


    /**
     * 
     * pre: key > 0
     *
     * Insert (key,info) into the heap and return the newly generated HeapNode.
     *
     */
    public HeapNode insert(int key, String info) 
    {
        HeapNode newNode = new HeapNode(key, info);
        Heap newHeap = new Heap(this.lazyMelds, this.lazyDecreaseKeys);
        newHeap.min = newNode;
        newHeap.size = 1;
        this.meld(newHeap);
        return newNode;
    }

    /**
     * 
     * Return the minimal HeapNode, null if empty.
     *
     */
    public HeapNode findMin()
    {
        return this.min; // should be replaced by student code
    }

    /**
     * 
     * Delete the minimal item.
     *
     */
    public void deleteMin()
    {
        return; // should be replaced by student code
    }

    /**
     * 
     * pre: 0<=diff<=x.key
     * 
     * Decrease the key of x by diff and fix the heap.
     * 
     */
    public void decreaseKey(HeapNode x, int diff) 
    {   
        // if x is root
        if(x.parent == null){
            x.key = x.key - diff;
            return;
        // if x is not root
        } else {
            // if heap order doesn't break decrease key and return
            if (x.key - diff > x.parent.key) {
                x.key = x.key - diff;
                return;
            }
            /// not lazy decrease keys
            if (!lazyDecreaseKeys){
                // heapify up
                while (x.parent != null && x.key < x.parent.key){
                    x.changeParentChild();
                    this.totalHeapifyUp++;
                }
            }

        }
        return; 
    }

    /**
     * 
     * Delete the x from the heap.
     *
     */
    public void delete(HeapNode x) 
    {    
        return; // should be replaced by student code
    }


    /**
     * 
     * Meld the heap with heap2
     * pre: heap2.lazyMelds = this.lazyMelds AND heap2.lazyDecreaseKeys = this.lazyDecreaseKeys
     *
     */
    public void meld(Heap heap2)
    {
        // update the fields
        this.size = this.size + heap2.size;
        this.totalCuts = this.totalCuts + heap2.totalCuts;
        this.totalHeapifyUp = this.totalHeapifyUp + heap2.totalHeapifyUp;
        this.totalLinks = this.totalLinks + heap2.totalLinks;

        /// lazy meld
        // connect the heaps through minimums
        HeapNode m1 = this.min;
        HeapNode m2 = heap2.min;
        m1.prev.next = m2.next;
        m2.next.prev = m1.prev;
        m1.prev = m2;
        m2.next = m1;

        // update min to min between 2 heaps
        this.min = Integer.compare(this.min.key, heap2.min.key) < 0 ? this.min : heap2.min;

        /// not lazy meld
        if(!this.lazyMelds){
            this.successiveLinking();
        }
        

        
        return;
    }
    
    
    /**
     * 
     * Return the number of elements in the heap
     *   
     */
    public int size()
    {
        return this.size; // should be replaced by student code
    }


    /**
     * 
     * Return the number of trees in the heap.
     * 
     */
    public int numTrees()
    {
        return this.numOfTrees; // should be replaced by student code
    }
    
    
    /**
     * 
     * Return the number of marked nodes in the heap.
     * 
     */
    public int numMarkedNodes()
    {
        return this.numMarkedModes; // should be replaced by student code
    }
    
    
    /**
     * 
     * Return the total number of links.
     * 
     */
    public int totalLinks()
    {
        return this.totalLinks; // should be replaced by student code
    }
    
    
    /**
     * 
     * Return the total number of cuts.
     * 
     */
    public int totalCuts()
    {
        return this.totalCuts; // should be replaced by student code
    }
    

    /**
     * 
     * Return the total heapify costs.
     * 
     */
    public int totalHeapifyCosts()
    {
        return this.totalHeapifyUp; // should be replaced by student code
    }   
    

    /**
     * 
     * successive link the roots of the heap
     * 
     */
    private void successiveLinking(){
        if(this.size == 0){
            return;
        }
        // get the size of the array and build array size of log n (size)
        int logN = 2 * (31 - Integer.numberOfLeadingZeros(this.size) + 1);
        HeapNode[] arr = new HeapNode[logN];

        // init vars for looping on roots
        HeapNode startNode = this.min;
        HeapNode currNode = startNode;
        HeapNode nextNode = startNode.next;

        /// successive linking
        do {
            nextNode = currNode.next;
            int currRank = currNode.rank;
            if(arr[currRank] == null){
                arr[currRank] = currNode;
            } else {
                while(arr[currRank] != null){
                    // find smaller and bigger
                    HeapNode smaller = Integer.compare(arr[currRank].key, currNode.key) <= 0 ? arr[currRank] : currNode;
                    HeapNode bigger = Integer.compare(arr[currRank].key, currNode.key) > 0 ? arr[currRank] : currNode;
    
                    arr[currRank] = null;                
                    // disconnecting from roots list
                    bigger.next.prev = bigger.prev;
                    bigger.prev.next = bigger.next;
                    // adding to smaller childs list
                    if(smaller.child != null){
                        bigger.prev = smaller.child.prev;
                        bigger.next = smaller.child;
                        smaller.child.prev.next = bigger;
                        smaller.child.prev = bigger;
                    }
                    // connect to smaller as parent
                    bigger.parent = smaller;
                    smaller.child = bigger;
                    smaller.rank++;

                    currNode = smaller;
                    currRank = smaller.rank;
                } 
                arr[currRank] = currNode;   
            }
            currNode = nextNode;
        } while (currNode != startNode);
        
    }

    /**
     * Class implementing a node in a ExtendedFibonacci Heap.
     *  
     */
    public static class HeapNode{
        public int key;
        public String info;
        public HeapNode child;
        public HeapNode next;
        public HeapNode prev;
        public HeapNode parent;
        public int rank;
        public boolean isMarked;

        public HeapNode(int key, String info){
            this.key = key;
            this.info = info;
            this.child = null;
            this.next = this;
            this.prev = this;
            this.parent = null;
            this.rank = 0;
        }

        /**
         * change node (this) and this.parent positions
         */
        private void changeParentChild(){

            // new pointers for this.parents
            HeapNode thisOldParent = this.parent;
            HeapNode thisOldChild = this.child;
            HeapNode thisOldPrev = (this.prev != this) ? this.prev : this.parent;
            HeapNode thisOldNext = (this.next != this) ? this.next : this.parent;

            // new pointers for this
            HeapNode thisNewParent = this.parent.parent;
            HeapNode thisNewPrev = (this.parent.prev != this.parent) ? this.parent.prev : this;
            HeapNode thisNewNext = (this.parent.next != this.parent) ? this.parent.next : this;

            // save ranks for exchange
            int thisNewRank = this.rank;
            int thisOldRank = thisOldParent.rank;

            // change pointers
            this.child = this.parent;
            this.parent = thisNewParent;
            this.next = thisNewNext;
            this.prev = thisNewPrev;
            thisOldParent.child = thisOldChild;
            thisOldParent.parent = this;
            thisOldParent.next = thisOldNext;
            thisOldParent.prev = thisOldPrev;

            // change ranks
            this.rank = thisNewRank;
            thisOldParent.rank = thisOldRank;
        }
    }
}
