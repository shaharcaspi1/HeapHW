public class Tester {

    public static void printHeap(Heap heap) {
        System.out.println("========== Heap Structure ==========");
        System.out.println("Lazy Melds: " + heap.lazyMelds);
        System.out.println("Lazy Decrease: " + heap.lazyDecreaseKeys);
        System.out.println("Min Node: " + (heap.min != null ? heap.min.key : "null"));
        System.out.println("------------------------------------");

        if (heap.min == null) {
            System.out.println("(Empty Heap)");
            return;
        }

        System.out.println("Root List:");
        Heap.HeapNode current = heap.min;
        
        // Traverse the circular root list
        if (current != null) {
            do {
                printNodeRecursive(current, "", true);
                current = current.next;
            } while (current != null && current != heap.min);
        }
        System.out.println("====================================");
    }

    private static void printNodeRecursive(Heap.HeapNode node, String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + nodeToString(node));

        if (node.child != null) {
            Heap.HeapNode child = node.child;
            String childPrefix = prefix + (isTail ? "    " : "│   ");
            
            Heap.HeapNode firstChild = child;
            do {
                boolean isLastChild = (child.next == firstChild || child.next == null);
                printNodeRecursive(child, childPrefix, isLastChild);
                child = child.next;
            } while (child != null && child != firstChild);
        }
    }

    private static String nodeToString(Heap.HeapNode node) {
        return String.format("[Key: %d | Rank: %d | Info: %s | Marked: %s]", 
                             node.key, node.rank, node.info, node.isMarked ? "YES" : "NO");
    }

    // --- UPDATED MAIN METHOD ---
    public static void main(String[] args) {
        // 1. Initialize Heap (Lazy flags set to true as example)
        Heap heap = new Heap(true, true); 
        Heap heap2 = new Heap(true, true); 

        // 2. Insert nodes using the heap's public method ONLY
        System.out.println("Inserting 20, 10, 30, 5, 25...");
        Heap.HeapNode[] lst = new Heap.HeapNode[20];
        for(int i = 1; i<20; i++){
            lst[i-1] = heap.insert(i, "Node_"+i);
        }
        Tester.printHeap(heap);
       heap.delete(lst[4]);
       Tester.printHeap(heap);
       heap.decreaseKey(lst[13], 9);
       heap.decreaseKey(lst[11], 11);
        Tester.printHeap(heap);
    }
}