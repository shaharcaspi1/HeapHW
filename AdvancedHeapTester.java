/**
 * AdvancedHeapTester
 * 
 * Comprehensive STRICT tester for Heap implementation with all 4 combinations of
 * lazyMelds and lazyDecreaseKeys flags.
 * 
 * This tester is designed to EXPOSE BUGS, not work around them.
 */
public class AdvancedHeapTester {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║       ADVANCED HEAP TESTER - STRICT MODE (ALL 4 MODES)      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // Test all 4 modes
        testMode(false, false, "Binomial Heap (strict meld, heapify up)");
        testMode(true, false, "Lazy Binomial Heap (lazy meld, heapify up)");
        testMode(true, true, "Fibonacci Heap (lazy meld, cascading cuts)");
        testMode(false, true, "Binomial with Cut-offs (strict meld, cascading cuts)");

        // Print summary
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                        SUMMARY                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Total Tests:  " + (testsPassed + testsFailed));
        
        if (testsFailed == 0) {
            System.out.println("\n✓ ALL TESTS PASSED!");
        } else {
            System.out.println("\n✗ SOME TESTS FAILED - SEE ABOVE FOR DETAILS");
        }
    }

    private static void testMode(boolean lazyMelds, boolean lazyDecreaseKeys, String modeName) {
        System.out.println("\n════════════════════════════════════════════════════════════════");
        System.out.println("Testing Mode: " + modeName);
        System.out.println("lazyMelds=" + lazyMelds + ", lazyDecreaseKeys=" + lazyDecreaseKeys);
        System.out.println("════════════════════════════════════════════════════════════════\n");

        // ========== BASIC TESTS ==========
        System.out.println("  --- BASIC TESTS ---");
        testEmptyHeap(lazyMelds, lazyDecreaseKeys);
        testSingleElement(lazyMelds, lazyDecreaseKeys);
        testBasicInsertAndFindMin(lazyMelds, lazyDecreaseKeys);
        testDeleteMinExactOrder(lazyMelds, lazyDecreaseKeys);
        testDuplicateKeysExact(lazyMelds, lazyDecreaseKeys);
        testMeldExactOrder(lazyMelds, lazyDecreaseKeys);
        testMeldHistoryInheritance(lazyMelds, lazyDecreaseKeys);
        testMeldWithEmptyHeap(lazyMelds, lazyDecreaseKeys);
        testDecreaseKeyNoAction(lazyMelds, lazyDecreaseKeys);
        testDecreaseKeyBasic(lazyMelds, lazyDecreaseKeys);
        testDecreaseKeyToNewMin(lazyMelds, lazyDecreaseKeys);
        testDeleteArbitraryNode(lazyMelds, lazyDecreaseKeys);
        testDeleteOverflowBug(lazyMelds, lazyDecreaseKeys);

        // ========== INSERT TESTS (10) ==========
        System.out.println("  --- INSERT TESTS ---");
        testInsert_SingleElement(lazyMelds, lazyDecreaseKeys);
        testInsert_MultipleElements(lazyMelds, lazyDecreaseKeys);
        testInsert_IncreasingOrder(lazyMelds, lazyDecreaseKeys);
        testInsert_DecreasingOrder(lazyMelds, lazyDecreaseKeys);
        testInsert_DuplicateKeys(lazyMelds, lazyDecreaseKeys);
        testInsert_ReturnsCorrectNode(lazyMelds, lazyDecreaseKeys);
        testInsert_UpdatesSize(lazyMelds, lazyDecreaseKeys);
        testInsert_UpdatesNumTrees(lazyMelds, lazyDecreaseKeys);
        testInsert_UpdatesMin(lazyMelds, lazyDecreaseKeys);
        testInsert_LargeNumber(lazyMelds, lazyDecreaseKeys);

        // ========== FINDMIN TESTS (10) ==========
        System.out.println("  --- FINDMIN TESTS ---");
        testFindMin_EmptyHeap(lazyMelds, lazyDecreaseKeys);
        testFindMin_SingleElement(lazyMelds, lazyDecreaseKeys);
        testFindMin_MultipleElements(lazyMelds, lazyDecreaseKeys);
        testFindMin_AfterInsertSmaller(lazyMelds, lazyDecreaseKeys);
        testFindMin_AfterInsertLarger(lazyMelds, lazyDecreaseKeys);
        testFindMin_AfterDeleteMin(lazyMelds, lazyDecreaseKeys);
        testFindMin_AfterMeld(lazyMelds, lazyDecreaseKeys);
        testFindMin_WithDuplicates(lazyMelds, lazyDecreaseKeys);
        testFindMin_DoesNotModifyHeap(lazyMelds, lazyDecreaseKeys);
        testFindMin_ConsistentReturns(lazyMelds, lazyDecreaseKeys);

        // ========== DELETEMIN TESTS (10) ==========
        System.out.println("  --- DELETEMIN TESTS ---");
        testDeleteMin_SingleElement(lazyMelds, lazyDecreaseKeys);
        testDeleteMin_TwoElements(lazyMelds, lazyDecreaseKeys);
        testDeleteMin_MultipleElements(lazyMelds, lazyDecreaseKeys);
        testDeleteMin_UpdatesSize(lazyMelds, lazyDecreaseKeys);
        testDeleteMin_UpdatesNumTrees(lazyMelds, lazyDecreaseKeys);
        testDeleteMin_UpdatesMin(lazyMelds, lazyDecreaseKeys);
        testDeleteMin_WithDuplicateMinKeys(lazyMelds, lazyDecreaseKeys);
        testDeleteMin_AllElements(lazyMelds, lazyDecreaseKeys);
        testDeleteMin_TriggersConsolidation(lazyMelds, lazyDecreaseKeys);
        testDeleteMin_TotalLinksIncreases(lazyMelds, lazyDecreaseKeys);

        // ========== MELD TESTS (10) ==========
        System.out.println("  --- MELD TESTS ---");
        testMeld_TwoNonEmptyHeaps(lazyMelds, lazyDecreaseKeys);
        testMeld_EmptyIntoNonEmpty(lazyMelds, lazyDecreaseKeys);
        testMeld_NonEmptyIntoEmpty(lazyMelds, lazyDecreaseKeys);
        testMeld_TwoEmptyHeaps(lazyMelds, lazyDecreaseKeys);
        testMeld_UpdatesSize(lazyMelds, lazyDecreaseKeys);
        testMeld_UpdatesMin(lazyMelds, lazyDecreaseKeys);
        testMeld_InheritsTotalLinks(lazyMelds, lazyDecreaseKeys);
        testMeld_InheritsTotalCuts(lazyMelds, lazyDecreaseKeys);
        testMeld_InheritsHeapifyCosts(lazyMelds, lazyDecreaseKeys);
        testMeld_NumTreesCorrect(lazyMelds, lazyDecreaseKeys);

        // ========== DELETE TESTS (10) ==========
        System.out.println("  --- DELETE TESTS ---");
        testDelete_SingleElement(lazyMelds, lazyDecreaseKeys);
        testDelete_MinElement(lazyMelds, lazyDecreaseKeys);
        testDelete_MaxElement(lazyMelds, lazyDecreaseKeys);
        testDelete_MiddleElement(lazyMelds, lazyDecreaseKeys);
        testDelete_UpdatesSize(lazyMelds, lazyDecreaseKeys);
        testDelete_UpdatesMin(lazyMelds, lazyDecreaseKeys);
        testDelete_LeafNode(lazyMelds, lazyDecreaseKeys);
        testDelete_InternalNode(lazyMelds, lazyDecreaseKeys);
        testDelete_MultipleDeletes(lazyMelds, lazyDecreaseKeys);
        testDelete_AllElements(lazyMelds, lazyDecreaseKeys);

        // ========== DECREASEKEY TESTS (10) ==========
        System.out.println("  --- DECREASEKEY TESTS ---");
        testDecreaseKey_ByZero(lazyMelds, lazyDecreaseKeys);
        testDecreaseKey_ByOne(lazyMelds, lazyDecreaseKeys);
        testDecreaseKey_ToNewMin(lazyMelds, lazyDecreaseKeys);
        testDecreaseKey_StillGreaterThanParent(lazyMelds, lazyDecreaseKeys);
        testDecreaseKey_OnRoot(lazyMelds, lazyDecreaseKeys);
        testDecreaseKey_OnLeaf(lazyMelds, lazyDecreaseKeys);
        testDecreaseKey_MultipleOnSameNode(lazyMelds, lazyDecreaseKeys);
        testDecreaseKey_MultipleOnDifferentNodes(lazyMelds, lazyDecreaseKeys);
        testDecreaseKey_UpdatesMinPointer(lazyMelds, lazyDecreaseKeys);
        testDecreaseKey_CounterUpdates(lazyMelds, lazyDecreaseKeys);

        // ========== INTEGRATION TESTS (10) ==========
        System.out.println("  --- INTEGRATION TESTS ---");
        testIntegration_InsertDeleteMinSequence(lazyMelds, lazyDecreaseKeys);
        testIntegration_InsertMeldDeleteMin(lazyMelds, lazyDecreaseKeys);
        testIntegration_DecreaseKeyThenDeleteMin(lazyMelds, lazyDecreaseKeys);
        testIntegration_DeleteThenMeld(lazyMelds, lazyDecreaseKeys);
        testIntegration_MeldThenDecreaseKey(lazyMelds, lazyDecreaseKeys);
        testIntegration_ComplexSequence1(lazyMelds, lazyDecreaseKeys);
        testIntegration_ComplexSequence2(lazyMelds, lazyDecreaseKeys);
        testIntegration_StressTest(lazyMelds, lazyDecreaseKeys);
        testIntegration_AllCountersConsistent(lazyMelds, lazyDecreaseKeys);
        testIntegration_HeapPropertyMaintained(lazyMelds, lazyDecreaseKeys);

        // ========== COUNTER TESTS ==========
        System.out.println("  --- COUNTER TESTS ---");
        testNumTreesAfterOperations(lazyMelds, lazyDecreaseKeys);
        testTotalLinksCounter(lazyMelds, lazyDecreaseKeys);
        testSuccessiveLinkingArrayBounds(lazyMelds, lazyDecreaseKeys);

        // ========== HEAPIFY UP TESTS (lazyDecreaseKeys=false only) ==========
        System.out.println("  --- HEAPIFY UP TESTS ---");
        testHeapifyUpCosts(lazyMelds, lazyDecreaseKeys);
        testHeapifyUpExact(lazyMelds, lazyDecreaseKeys);
        testMarkedNodesAlwaysZero(lazyMelds, lazyDecreaseKeys);
        testTotalCutsAlwaysZero(lazyMelds, lazyDecreaseKeys);

        // ========== CASCADING CUTS TESTS (lazyDecreaseKeys=true only) ==========
        System.out.println("  --- CASCADING CUTS TESTS ---");
        testCascadingCuts(lazyMelds, lazyDecreaseKeys);
        testMarkedNodesCounter(lazyMelds, lazyDecreaseKeys);
        testCascadingCutSequence(lazyMelds, lazyDecreaseKeys);
        testTotalCutsCounter(lazyMelds, lazyDecreaseKeys);

        // ========== COMPLEX SCENARIO ==========
        System.out.println("  --- COMPLEX SCENARIO ---");
        testComplexScenario(lazyMelds, lazyDecreaseKeys);
    }

    // ==================== BASIC TESTS ====================

    private static void testEmptyHeap(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Empty Heap";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        boolean pass = true;
        String reason = "";

        if (heap.size() != 0) {
            pass = false;
            reason = "size() should be 0, got " + heap.size();
        } else if (heap.findMin() != null) {
            pass = false;
            reason = "findMin() should be null";
        } else if (heap.numTrees() != 0) {
            pass = false;
            reason = "numTrees() should be 0, got " + heap.numTrees();
        } else if (heap.totalLinks() != 0) {
            pass = false;
            reason = "totalLinks() should be 0, got " + heap.totalLinks();
        } else if (heap.totalCuts() != 0) {
            pass = false;
            reason = "totalCuts() should be 0, got " + heap.totalCuts();
        } else if (heap.numMarkedNodes() != 0) {
            pass = false;
            reason = "numMarkedNodes() should be 0, got " + heap.numMarkedNodes();
        } else if (heap.totalHeapifyCosts() != 0) {
            pass = false;
            reason = "totalHeapifyCosts() should be 0, got " + heap.totalHeapifyCosts();
        }

        reportResult(testName, pass, reason);
    }

    private static void testSingleElement(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Single Element";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem item = heap.insert(42, "only");

        boolean pass = true;
        String reason = "";

        if (heap.size() != 1) {
            pass = false;
            reason = "size() should be 1, got " + heap.size();
        } else if (heap.findMin() != item) {
            pass = false;
            reason = "findMin() should return inserted item";
        } else if (heap.findMin().key != 42) {
            pass = false;
            reason = "findMin().key should be 42, got " + heap.findMin().key;
        } else if (heap.numTrees() != 1) {
            pass = false;
            reason = "numTrees() should be 1, got " + heap.numTrees();
        }

        // Delete the only element
        heap.deleteMin();
        if (heap.size() != 0) {
            pass = false;
            reason = "After deleteMin, size() should be 0, got " + heap.size();
        } else if (heap.findMin() != null) {
            pass = false;
            reason = "After deleteMin, findMin() should be null";
        } else if (heap.numTrees() != 0) {
            pass = false;
            reason = "After deleteMin, numTrees() should be 0, got " + heap.numTrees();
        }

        reportResult(testName, pass, reason);
    }

    private static void testBasicInsertAndFindMin(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Basic Insert and FindMin";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        heap.insert(10, "ten");
        heap.insert(5, "five");
        heap.insert(15, "fifteen");
        heap.insert(3, "three");

        boolean pass = true;
        String reason = "";

        if (heap.size() != 4) {
            pass = false;
            reason = "size() should be 4, got " + heap.size();
        } else if (heap.findMin().key != 3) {
            pass = false;
            reason = "findMin().key should be 3, got " + heap.findMin().key;
        }

        reportResult(testName, pass, reason);
    }

    private static void testDeleteMinExactOrder(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DeleteMin EXACT Ordering";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        heap.insert(5, "five");
        heap.insert(3, "three");
        heap.insert(7, "seven");
        heap.insert(1, "one");
        heap.insert(9, "nine");

        boolean pass = true;
        String reason = "";

        int[] expected = {1, 3, 5, 7, 9};
        for (int i = 0; i < expected.length; i++) {
            if (heap.findMin() == null) {
                pass = false;
                reason = "findMin() returned null at step " + i;
                break;
            }
            int minKey = heap.findMin().key;
            if (minKey != expected[i]) {
                pass = false;
                reason = "Expected min " + expected[i] + " at step " + i + ", got " + minKey;
                break;
            }
            heap.deleteMin();
        }

        if (pass && heap.size() != 0) {
            pass = false;
            reason = "After all deletions, size should be 0, got " + heap.size();
        }

        reportResult(testName, pass, reason);
    }

    private static void testDuplicateKeysExact(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Duplicate Keys EXACT Order";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        heap.insert(5, "five-a");
        heap.insert(5, "five-b");
        heap.insert(5, "five-c");
        heap.insert(3, "three-a");
        heap.insert(3, "three-b");

        boolean pass = true;
        String reason = "";

        if (heap.size() != 5) {
            pass = false;
            reason = "size() should be 5, got " + heap.size();
        } else if (heap.findMin().key != 3) {
            pass = false;
            reason = "findMin().key should be 3, got " + heap.findMin().key;
        }

        // First two should be 3, next three should be 5
        int[] expected = {3, 3, 5, 5, 5};
        for (int i = 0; i < expected.length && pass; i++) {
            int minKey = heap.findMin().key;
            if (minKey != expected[i]) {
                pass = false;
                reason = "Expected " + expected[i] + " at step " + i + ", got " + minKey;
            }
            heap.deleteMin();
        }

        reportResult(testName, pass, reason);
    }

    // ==================== MELD TESTS ====================

    private static void testMeldExactOrder(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld EXACT Order";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);

        heap1.insert(10, "h1-10");
        heap1.insert(5, "h1-5");
        heap2.insert(3, "h2-3");
        heap2.insert(8, "h2-8");

        heap1.meld(heap2);

        boolean pass = true;
        String reason = "";

        if (heap1.size() != 4) {
            pass = false;
            reason = "After meld, size should be 4, got " + heap1.size();
        } else if (heap1.findMin().key != 3) {
            pass = false;
            reason = "After meld, min should be 3, got " + heap1.findMin().key;
        }

        int[] expected = {3, 5, 8, 10};
        for (int i = 0; i < expected.length && pass; i++) {
            int minKey = heap1.findMin().key;
            if (minKey != expected[i]) {
                pass = false;
                reason = "Expected " + expected[i] + " at step " + i + ", got " + minKey;
            }
            heap1.deleteMin();
        }

        reportResult(testName, pass, reason);
    }

    private static void testMeldHistoryInheritance(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld History Inheritance";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);

        for (int i = 1; i <= 4; i++) {
            heap1.insert(i, "h1-" + i);
        }
        for (int i = 10; i <= 13; i++) {
            heap2.insert(i, "h2-" + i);
        }

        int links1 = heap1.totalLinks();
        int links2 = heap2.totalLinks();
        int cuts1 = heap1.totalCuts();
        int cuts2 = heap2.totalCuts();
        int heapify1 = heap1.totalHeapifyCosts();
        int heapify2 = heap2.totalHeapifyCosts();

        heap1.meld(heap2);

        boolean pass = true;
        String reason = "";

        // totalCuts and totalHeapifyCosts should be summed
        if (heap1.totalCuts() < cuts1 + cuts2) {
            pass = false;
            reason = "totalCuts should be at least " + (cuts1 + cuts2) + ", got " + heap1.totalCuts();
        }
        if (heap1.totalHeapifyCosts() != heapify1 + heapify2) {
            pass = false;
            reason = "totalHeapifyCosts should be " + (heapify1 + heapify2) + ", got " + heap1.totalHeapifyCosts();
        }
        // totalLinks should be at least the sum (may be more if successive linking occurred)
        if (heap1.totalLinks() < links1 + links2) {
            pass = false;
            reason = "totalLinks should be at least " + (links1 + links2) + ", got " + heap1.totalLinks();
        }

        reportResult(testName, pass, reason);
    }

    private static void testMeldWithEmptyHeap(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld With Empty Heap";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);

        heap1.insert(5, "five");
        heap1.insert(3, "three");
        // heap2 is empty

        heap1.meld(heap2);

        boolean pass = true;
        String reason = "";

        if (heap1.size() != 2) {
            pass = false;
            reason = "After meld with empty, size should be 2, got " + heap1.size();
        } else if (heap1.findMin().key != 3) {
            pass = false;
            reason = "After meld with empty, min should be 3, got " + heap1.findMin().key;
        }

        // Now meld empty into non-empty (other direction)
        Heap heap3 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap4 = new Heap(lazyMelds, lazyDecreaseKeys);
        heap4.insert(7, "seven");
        
        heap3.meld(heap4); // empty.meld(non-empty)
        
        if (heap3.size() != 1) {
            pass = false;
            reason = "After empty.meld(non-empty), size should be 1, got " + heap3.size();
        } else if (heap3.findMin().key != 7) {
            pass = false;
            reason = "After empty.meld(non-empty), min should be 7, got " + heap3.findMin().key;
        }

        reportResult(testName, pass, reason);
    }

    // ==================== DECREASE KEY TESTS ====================

    private static void testDecreaseKeyNoAction(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey No Action (diff=0)";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        heap.insert(10, "ten");
        Heap.HeapItem item = heap.insert(20, "twenty");
        heap.insert(5, "five");

        int cutsBefore = heap.totalCuts();
        int heapifyBefore = heap.totalHeapifyCosts();

        heap.decreaseKey(item, 0); // No change

        boolean pass = true;
        String reason = "";

        if (item.key != 20) {
            pass = false;
            reason = "Key should remain 20, got " + item.key;
        }
        if (heap.totalCuts() != cutsBefore) {
            pass = false;
            reason = "totalCuts should remain " + cutsBefore + ", got " + heap.totalCuts();
        }
        if (heap.totalHeapifyCosts() != heapifyBefore) {
            pass = false;
            reason = "totalHeapifyCosts should remain " + heapifyBefore + ", got " + heap.totalHeapifyCosts();
        }

        reportResult(testName, pass, reason);
    }

    private static void testDecreaseKeyBasic(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey Basic";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        heap.insert(10, "ten");
        Heap.HeapItem item = heap.insert(20, "twenty");
        heap.insert(5, "five");

        heap.decreaseKey(item, 18); // 20 - 18 = 2

        boolean pass = true;
        String reason = "";

        if (item.key != 2) {
            pass = false;
            reason = "After decreaseKey, item.key should be 2, got " + item.key;
        }
        if (heap.findMin().key != 2) {
            pass = false;
            reason = "After decreaseKey, findMin().key should be 2, got " + heap.findMin().key;
        }

        reportResult(testName, pass, reason);
    }

    private static void testDecreaseKeyToNewMin(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey To New Min";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        // Build a larger heap to force tree structure
        for (int i = 1; i <= 8; i++) {
            heap.insert(i * 10, "node-" + i);
        }
        // Force consolidation
        heap.deleteMin(); // removes 10
        
        // Insert a large value
        Heap.HeapItem item = heap.insert(100, "hundred");
        
        // Decrease to become new min
        heap.decreaseKey(item, 99); // 100 - 99 = 1

        boolean pass = true;
        String reason = "";

        if (item.key != 1) {
            pass = false;
            reason = "After decreaseKey, item.key should be 1, got " + item.key;
        }
        if (heap.findMin().key != 1) {
            pass = false;
            reason = "After decreaseKey, findMin().key should be 1, got " + heap.findMin().key;
        }
        if (heap.findMin() != item) {
            pass = false;
            reason = "After decreaseKey, findMin() should return the decreased item";
        }

        reportResult(testName, pass, reason);
    }

    // ==================== DELETE TESTS ====================

    private static void testDeleteArbitraryNode(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete Arbitrary Node EXACT";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        heap.insert(10, "ten");
        Heap.HeapItem toDelete = heap.insert(5, "five");
        heap.insert(15, "fifteen");
        heap.insert(3, "three");

        heap.delete(toDelete);

        boolean pass = true;
        String reason = "";

        if (heap.size() != 3) {
            pass = false;
            reason = "After delete, size should be 3, got " + heap.size();
        } else if (heap.findMin().key != 3) {
            pass = false;
            reason = "After delete, min should be 3, got " + heap.findMin().key;
        }

        // Exact order check
        int[] expected = {3, 10, 15};
        for (int i = 0; i < expected.length && pass; i++) {
            int minKey = heap.findMin().key;
            if (minKey != expected[i]) {
                pass = false;
                reason = "Expected " + expected[i] + " at step " + i + ", got " + minKey;
            }
            heap.deleteMin();
        }

        reportResult(testName, pass, reason);
    }

    private static void testDeleteOverflowBug(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete Overflow Bug (Integer.MIN_VALUE)";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        // BUG: delete() calls decreaseKey(x, Integer.MIN_VALUE)
        // This computes x.key - Integer.MIN_VALUE which OVERFLOWS!
        // Example: 5 - (-2147483648) = 5 + 2147483648 = -2147483643 (overflow!)

        heap.insert(10, "ten");
        Heap.HeapItem toDelete = heap.insert(5, "five");
        heap.insert(15, "fifteen");

        // The delete should work correctly
        heap.delete(toDelete);

        boolean pass = true;
        String reason = "";

        if (heap.size() != 2) {
            pass = false;
            reason = "After delete, size should be 2, got " + heap.size();
        }
        
        // The key check - if overflow happened, min will be a large negative number
        int minKey = heap.findMin().key;
        if (minKey < 0) {
            pass = false;
            reason = "BUG: delete() caused overflow! Min is " + minKey + 
                     ". Fix: change delete() to use decreaseKey(x, x.key - Integer.MIN_VALUE) " +
                     "or set x.key = Integer.MIN_VALUE directly";
        } else if (minKey != 10) {
            pass = false;
            reason = "After delete, min should be 10, got " + minKey;
        }

        reportResult(testName, pass, reason);
    }

    // ==================== COUNTER TESTS ====================

    private static void testNumTreesAfterOperations(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "NumTrees After Operations";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        boolean pass = true;
        String reason = "";

        heap.insert(10, "ten");
        if (heap.numTrees() != 1) {
            pass = false;
            reason = "After 1 insert, numTrees should be 1, got " + heap.numTrees();
        }

        heap.insert(5, "five");
        if (!lazyMelds) {
            if (heap.numTrees() != 1) {
                pass = false;
                reason = "After 2 inserts (strict), numTrees should be 1, got " + heap.numTrees();
            }
        } else {
            if (heap.numTrees() != 2) {
                pass = false;
                reason = "After 2 inserts (lazy), numTrees should be 2, got " + heap.numTrees();
            }
        }

        heap.insert(15, "fifteen");
        if (!lazyMelds) {
            if (heap.numTrees() != 2) {
                pass = false;
                reason = "After 3 inserts (strict), numTrees should be 2, got " + heap.numTrees();
            }
        } else {
            if (heap.numTrees() != 3) {
                pass = false;
                reason = "After 3 inserts (lazy), numTrees should be 3, got " + heap.numTrees();
            }
        }

        heap.insert(20, "twenty");
        if (!lazyMelds) {
            if (heap.numTrees() != 1) {
                pass = false;
                reason = "After 4 inserts (strict), numTrees should be 1, got " + heap.numTrees();
            }
        } else {
            if (heap.numTrees() != 4) {
                pass = false;
                reason = "After 4 inserts (lazy), numTrees should be 4, got " + heap.numTrees();
            }
        }

        reportResult(testName, pass, reason);
    }

    private static void testTotalLinksCounter(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "TotalLinks Counter EXACT";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        for (int i = 1; i <= 4; i++) {
            heap.insert(i, "node-" + i);
        }

        boolean pass = true;
        String reason = "";

        if (!lazyMelds) {
            // Strict melds: Insert 1 (0), Insert 2 (1 link), Insert 3 (0), Insert 4 (2 links) = 3 total
            if (heap.totalLinks() != 3) {
                pass = false;
                reason = "After 4 inserts (strict), totalLinks should be 3, got " + heap.totalLinks();
            }
        } else {
            if (heap.totalLinks() != 0) {
                pass = false;
                reason = "After 4 inserts (lazy), totalLinks should be 0, got " + heap.totalLinks();
            }
        }

        reportResult(testName, pass, reason);
    }

    private static void testSuccessiveLinkingArrayBounds(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "SuccessiveLinking Array Bounds";
        
        boolean pass = true;
        String reason = "";

        try {
            Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
            
            // Insert elements and delete until size is very small
            for (int i = 1; i <= 8; i++) {
                heap.insert(i * 10, "node-" + i);
            }
            
            // Delete most elements - this triggers successive linking with small size
            for (int i = 0; i < 6; i++) {
                heap.deleteMin();
            }
            
            // Should have 2 elements left
            if (heap.size() != 2) {
                pass = false;
                reason = "Expected size 2, got " + heap.size();
            }
            
            // Delete one more
            heap.deleteMin();
            
            if (heap.size() != 1) {
                pass = false;
                reason = "Expected size 1, got " + heap.size();
            }
            
            // Final delete
            heap.deleteMin();
            
            if (heap.size() != 0) {
                pass = false;
                reason = "Expected size 0, got " + heap.size();
            }
            
        } catch (ArrayIndexOutOfBoundsException e) {
            pass = false;
            reason = "BUG: ArrayIndexOutOfBoundsException in successiveLinking! " +
                     "The array size calculation '2 * (31 - Integer.numberOfLeadingZeros(size) + 1)' " +
                     "is too small. Fix: use a larger constant or handle edge cases.";
        } catch (Exception e) {
            pass = false;
            reason = "Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage();
        }

        reportResult(testName, pass, reason);
    }

    // ==================== HEAPIFY UP TESTS (lazyDecreaseKeys=false) ====================

    private static void testHeapifyUpCosts(boolean lazyMelds, boolean lazyDecreaseKeys) {
        if (lazyDecreaseKeys) {
            return; // Skip for cascading cuts mode
        }
        
        String testName = "HeapifyUp Costs Counter";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        // Build a heap
        for (int i = 1; i <= 8; i++) {
            heap.insert(i * 10, "node-" + i);
        }
        heap.deleteMin(); // consolidate

        int heapifyBefore = heap.totalHeapifyCosts();

        // Insert and decrease to trigger heapify-up
        Heap.HeapItem item = heap.insert(100, "hundred");
        heap.decreaseKey(item, 99); // Should bubble up

        boolean pass = true;
        String reason = "";

        if (heap.totalHeapifyCosts() < heapifyBefore) {
            pass = false;
            reason = "totalHeapifyCosts should not decrease";
        }

        reportResult(testName, pass, reason);
    }

    private static void testHeapifyUpExact(boolean lazyMelds, boolean lazyDecreaseKeys) {
        if (lazyDecreaseKeys) {
            return;
        }
        
        String testName = "HeapifyUp Preserves Structure";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        boolean pass = true;
        String reason = "";

        try {
            // Build heap and do operations
            for (int i = 1; i <= 8; i++) {
                heap.insert(i * 10, "node-" + i);
            }
            heap.deleteMin(); // removes 10, consolidates
            
            Heap.HeapItem item = heap.insert(100, "hundred");
            heap.decreaseKey(item, 95); // 100 -> 5, should become min
            
            // Verify heap still works correctly after heapify-up
            int[] expected = {5, 20, 30, 40, 50, 60, 70, 80};
            int maxIterations = 100; // Safety limit to prevent infinite loops
            int iterations = 0;
            
            for (int i = 0; i < expected.length && pass; i++) {
                if (++iterations > maxIterations) {
                    pass = false;
                    reason = "BUG: Possible infinite loop detected! Heap structure corrupted by heapify-up.";
                    break;
                }
                if (heap.findMin() == null) {
                    pass = false;
                    reason = "findMin() returned null at step " + i;
                    break;
                }
                int minKey = heap.findMin().key;
                if (minKey != expected[i]) {
                    pass = false;
                    reason = "Expected " + expected[i] + " at step " + i + ", got " + minKey + 
                             ". BUG: Heapify-up corrupted heap structure!";
                    break;
                }
                heap.deleteMin();
            }
        } catch (Exception e) {
            pass = false;
            reason = "Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage();
        }

        reportResult(testName, pass, reason);
    }

    private static void testMarkedNodesAlwaysZero(boolean lazyMelds, boolean lazyDecreaseKeys) {
        if (lazyDecreaseKeys) {
            return;
        }
        
        String testName = "MarkedNodes Always Zero (heapify mode)";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        for (int i = 1; i <= 10; i++) {
            heap.insert(i * 10, "node-" + i);
        }

        boolean pass = true;
        String reason = "";

        if (heap.numMarkedNodes() != 0) {
            pass = false;
            reason = "With lazyDecreaseKeys=false, numMarkedNodes should always be 0, got " + heap.numMarkedNodes();
        }

        heap.deleteMin();
        heap.deleteMin();

        if (heap.numMarkedNodes() != 0) {
            pass = false;
            reason = "After deleteMins, numMarkedNodes should still be 0, got " + heap.numMarkedNodes();
        }

        reportResult(testName, pass, reason);
    }

    private static void testTotalCutsAlwaysZero(boolean lazyMelds, boolean lazyDecreaseKeys) {
        if (lazyDecreaseKeys) {
            return;
        }
        
        String testName = "TotalCuts Always Zero (heapify mode)";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        for (int i = 1; i <= 8; i++) {
            heap.insert(i * 10, "node-" + i);
        }
        
        heap.deleteMin();
        
        Heap.HeapItem item = heap.insert(100, "hundred");
        heap.decreaseKey(item, 90);

        boolean pass = true;
        String reason = "";

        if (heap.totalCuts() != 0) {
            pass = false;
            reason = "With lazyDecreaseKeys=false, totalCuts should always be 0, got " + heap.totalCuts();
        }

        reportResult(testName, pass, reason);
    }

    // ==================== CASCADING CUTS TESTS (lazyDecreaseKeys=true) ====================

    private static void testCascadingCuts(boolean lazyMelds, boolean lazyDecreaseKeys) {
        if (!lazyDecreaseKeys) {
            return;
        }
        
        String testName = "Cascading Cuts Basic";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        Heap.HeapItem[] items = new Heap.HeapItem[8];
        for (int i = 0; i < 8; i++) {
            items[i] = heap.insert((i + 1) * 10, "node-" + (i + 1));
        }
        
        heap.deleteMin(); // consolidate, removes 10

        int cutsBefore = heap.totalCuts();
        
        // Decrease a key to trigger a cut
        heap.decreaseKey(items[6], 65); // 70 -> 5

        boolean pass = true;
        String reason = "";

        if (heap.findMin().key != 5) {
            pass = false;
            reason = "After decreaseKey, min should be 5, got " + heap.findMin().key;
        }

        reportResult(testName, pass, reason);
    }

    private static void testMarkedNodesCounter(boolean lazyMelds, boolean lazyDecreaseKeys) {
        if (!lazyDecreaseKeys) {
            return;
        }
        
        String testName = "MarkedNodes Counter";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        Heap.HeapItem[] items = new Heap.HeapItem[16];
        for (int i = 0; i < 16; i++) {
            items[i] = heap.insert((i + 1) * 10, "node-" + (i + 1));
        }
        
        heap.deleteMin(); // consolidate

        boolean pass = true;
        String reason = "";

        if (heap.numMarkedNodes() < 0) {
            pass = false;
            reason = "numMarkedNodes should never be negative, got " + heap.numMarkedNodes();
        }

        // After cuts, marked count should increase appropriately
        heap.decreaseKey(items[14], 145); // Should cut and mark parent

        if (heap.numMarkedNodes() < 0) {
            pass = false;
            reason = "After cut, numMarkedNodes should not be negative, got " + heap.numMarkedNodes();
        }

        reportResult(testName, pass, reason);
    }

    private static void testCascadingCutSequence(boolean lazyMelds, boolean lazyDecreaseKeys) {
        if (!lazyDecreaseKeys) {
            return;
        }
        
        String testName = "Cascading Cut Sequence";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        // Build a larger heap to ensure proper tree structure
        Heap.HeapItem[] items = new Heap.HeapItem[32];
        for (int i = 0; i < 32; i++) {
            items[i] = heap.insert((i + 1) * 10, "node-" + (i + 1));
        }
        
        heap.deleteMin(); // consolidate

        boolean pass = true;
        String reason = "";

        int cutsBefore = heap.totalCuts();
        
        // Multiple decreaseKeys to trigger cascading
        heap.decreaseKey(items[30], 295); // 310 -> 15
        heap.decreaseKey(items[28], 275); // 290 -> 15
        
        int cutsAfter = heap.totalCuts();
        
        if (cutsAfter < cutsBefore) {
            pass = false;
            reason = "totalCuts should not decrease after decreaseKey operations";
        }

        reportResult(testName, pass, reason);
    }

    private static void testTotalCutsCounter(boolean lazyMelds, boolean lazyDecreaseKeys) {
        if (!lazyDecreaseKeys) {
            return;
        }
        
        String testName = "TotalCuts Counter";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        for (int i = 1; i <= 8; i++) {
            heap.insert(i * 10, "node-" + i);
        }

        boolean pass = true;
        String reason = "";

        // Before any decreaseKey, totalCuts should be 0
        if (heap.totalCuts() != 0) {
            pass = false;
            reason = "Before any cuts, totalCuts should be 0, got " + heap.totalCuts();
        }

        reportResult(testName, pass, reason);
    }

    // ==================== COMPLEX SCENARIO ====================

    private static void testComplexScenario(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Complex Scenario";
        
        boolean pass = true;
        String reason = "";

        try {
            Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

            // Phase 1: Build heap
            Heap.HeapItem[] items = new Heap.HeapItem[16];
            for (int i = 0; i < 16; i++) {
                items[i] = heap.insert((i + 1) * 5, "node-" + (i + 1));
            }

            if (heap.size() != 16) {
                pass = false;
                reason = "After 16 inserts, size should be 16, got " + heap.size();
            }

            // Phase 2: Some deleteMins
            for (int i = 0; i < 4; i++) {
                heap.deleteMin();
            }

            if (pass && heap.size() != 12) {
                pass = false;
                reason = "After 4 deleteMins, size should be 12, got " + heap.size();
            }

            // Phase 3: Meld
            Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
            for (int i = 0; i < 4; i++) {
                heap2.insert((i + 100) * 5, "h2-" + (i + 1));
            }

            int sizeBefore = heap.size();
            heap.meld(heap2);

            if (pass && heap.size() != sizeBefore + 4) {
                pass = false;
                reason = "After meld, size should be " + (sizeBefore + 4) + ", got " + heap.size();
            }

            // Phase 4: Extract all - verify EXACT order
            int prevMin = Integer.MIN_VALUE;
            int count = 0;
            while (heap.size() > 0 && pass) {
                int currentMin = heap.findMin().key;
                if (currentMin < prevMin) {
                    pass = false;
                    reason = "Heap property violated: got " + currentMin + " after " + prevMin;
                    break;
                }
                prevMin = currentMin;
                heap.deleteMin();
                count++;
            }

            if (pass && heap.size() != 0) {
                pass = false;
                reason = "After extracting all, size should be 0, got " + heap.size();
            }
            
            if (pass && count != 16) {
                pass = false;
                reason = "Should have extracted 16 elements, extracted " + count;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            pass = false;
            reason = "BUG: ArrayIndexOutOfBoundsException - successiveLinking array too small!";
        } catch (Exception e) {
            pass = false;
            reason = "Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage();
        }

        reportResult(testName, pass, reason);
    }

    // ==================== INSERT TESTS (10) ====================

    private static void testInsert_SingleElement(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Insert Single Element";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem item = heap.insert(42, "forty-two");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 1) {
            pass = false;
            reason = "After single insert, size should be 1, got " + heap.size();
        } else if (heap.findMin() != item) {
            pass = false;
            reason = "After single insert, findMin() should return the inserted item";
        } else if (item.key != 42) {
            pass = false;
            reason = "Inserted item key should be 42, got " + item.key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testInsert_MultipleElements(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Insert Multiple Elements";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(10, "ten");
        heap.insert(20, "twenty");
        heap.insert(30, "thirty");
        heap.insert(40, "forty");
        heap.insert(50, "fifty");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 5) {
            pass = false;
            reason = "After 5 inserts, size should be 5, got " + heap.size();
        } else if (heap.findMin().key != 10) {
            pass = false;
            reason = "Min should be 10, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testInsert_IncreasingOrder(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Insert Increasing Order";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        for (int i = 1; i <= 10; i++) {
            heap.insert(i, "val-" + i);
        }
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 10) {
            pass = false;
            reason = "After 10 inserts, size should be 10, got " + heap.size();
        } else if (heap.findMin().key != 1) {
            pass = false;
            reason = "Min should be 1, got " + heap.findMin().key;
        }
        
        // Verify extraction order
        for (int i = 1; i <= 10 && pass; i++) {
            int minKey = heap.findMin().key;
            if (minKey != i) {
                pass = false;
                reason = "Expected " + i + " at step " + i + ", got " + minKey;
            }
            heap.deleteMin();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testInsert_DecreasingOrder(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Insert Decreasing Order";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        for (int i = 10; i >= 1; i--) {
            heap.insert(i, "val-" + i);
            if (heap.findMin().key != i) {
                reportResult(testName, false, "After inserting " + i + ", min should be " + i + ", got " + heap.findMin().key);
                return;
            }
        }
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 10) {
            pass = false;
            reason = "After 10 inserts, size should be 10, got " + heap.size();
        } else if (heap.findMin().key != 1) {
            pass = false;
            reason = "Final min should be 1, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testInsert_DuplicateKeys(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Insert Duplicate Keys";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(5, "five-a");
        heap.insert(5, "five-b");
        heap.insert(5, "five-c");
        heap.insert(5, "five-d");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 4) {
            pass = false;
            reason = "After 4 inserts of same key, size should be 4, got " + heap.size();
        } else if (heap.findMin().key != 5) {
            pass = false;
            reason = "Min should be 5, got " + heap.findMin().key;
        }
        
        // All 4 should be extractable with key 5
        for (int i = 0; i < 4 && pass; i++) {
            if (heap.findMin().key != 5) {
                pass = false;
                reason = "All extracted elements should have key 5, got " + heap.findMin().key + " at step " + i;
            }
            heap.deleteMin();
        }
        
        if (pass && heap.size() != 0) {
            pass = false;
            reason = "After extracting all, size should be 0, got " + heap.size();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testInsert_ReturnsCorrectNode(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Insert Returns Correct Node";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem item1 = heap.insert(10, "ten");
        Heap.HeapItem item2 = heap.insert(20, "twenty");
        Heap.HeapItem item3 = heap.insert(5, "five");
        
        boolean pass = true;
        String reason = "";
        
        if (item1 == null) {
            pass = false;
            reason = "insert() should not return null";
        } else if (item1.key != 10) {
            pass = false;
            reason = "item1.key should be 10, got " + item1.key;
        } else if (item2.key != 20) {
            pass = false;
            reason = "item2.key should be 20, got " + item2.key;
        } else if (item3.key != 5) {
            pass = false;
            reason = "item3.key should be 5, got " + item3.key;
        } else if (item1 == item2 || item2 == item3 || item1 == item3) {
            pass = false;
            reason = "Each insert should return a distinct item";
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testInsert_UpdatesSize(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Insert Updates Size";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        boolean pass = true;
        String reason = "";
        
        for (int i = 1; i <= 20; i++) {
            heap.insert(i * 10, "val-" + i);
            if (heap.size() != i) {
                pass = false;
                reason = "After " + i + " inserts, size should be " + i + ", got " + heap.size();
                break;
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testInsert_UpdatesNumTrees(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Insert Updates NumTrees";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        boolean pass = true;
        String reason = "";
        
        heap.insert(10, "ten");
        if (heap.numTrees() != 1) {
            pass = false;
            reason = "After 1 insert, numTrees should be 1, got " + heap.numTrees();
        }
        
        if (pass) {
            heap.insert(20, "twenty");
            if (lazyMelds) {
                // Lazy mode: trees not consolidated
                if (heap.numTrees() != 2) {
                    pass = false;
                    reason = "After 2 inserts (lazy), numTrees should be 2, got " + heap.numTrees();
                }
            } else {
                // Strict mode: trees consolidated
                if (heap.numTrees() != 1) {
                    pass = false;
                    reason = "After 2 inserts (strict), numTrees should be 1, got " + heap.numTrees();
                }
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testInsert_UpdatesMin(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Insert Updates Min";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        boolean pass = true;
        String reason = "";
        
        Heap.HeapItem n1 = heap.insert(50, "fifty");
        if (heap.findMin() != n1) {
            pass = false;
            reason = "After inserting 50, findMin should return that item";
        }
        
        if (pass) {
            Heap.HeapItem n2 = heap.insert(30, "thirty");
            if (heap.findMin() != n2 || heap.findMin().key != 30) {
                pass = false;
                reason = "After inserting smaller 30, findMin should return new min";
            }
        }
        
        if (pass) {
            heap.insert(70, "seventy");
            if (heap.findMin().key != 30) {
                pass = false;
                reason = "After inserting larger 70, min should still be 30, got " + heap.findMin().key;
            }
        }
        
        if (pass) {
            Heap.HeapItem n4 = heap.insert(10, "ten");
            if (heap.findMin() != n4 || heap.findMin().key != 10) {
                pass = false;
                reason = "After inserting smallest 10, findMin should return new min";
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testInsert_LargeNumber(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Insert Large Number of Elements";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        boolean pass = true;
        String reason = "";
        
        int n = 1000;
        for (int i = n; i >= 1; i--) {
            heap.insert(i, "val-" + i);
        }
        
        if (heap.size() != n) {
            pass = false;
            reason = "After " + n + " inserts, size should be " + n + ", got " + heap.size();
        } else if (heap.findMin().key != 1) {
            pass = false;
            reason = "Min should be 1, got " + heap.findMin().key;
        }
        
        // Verify first 10 extractions
        if (pass) {
            for (int i = 1; i <= 10 && pass; i++) {
                int minKey = heap.findMin().key;
                if (minKey != i) {
                    pass = false;
                    reason = "Expected " + i + " at step " + i + ", got " + minKey;
                }
                heap.deleteMin();
            }
        }
        
        reportResult(testName, pass, reason);
    }

    // ==================== FINDMIN TESTS (10) ====================

    private static void testFindMin_EmptyHeap(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "FindMin Empty Heap";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin() != null) {
            pass = false;
            reason = "findMin() on empty heap should return null, got node with key " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testFindMin_SingleElement(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "FindMin Single Element";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem item = heap.insert(42, "forty-two");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin() != item) {
            pass = false;
            reason = "findMin() should return the only inserted item";
        } else if (heap.findMin().key != 42) {
            pass = false;
            reason = "findMin().key should be 42, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testFindMin_MultipleElements(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "FindMin Multiple Elements";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(30, "thirty");
        heap.insert(10, "ten");
        heap.insert(50, "fifty");
        heap.insert(20, "twenty");
        heap.insert(40, "forty");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin().key != 10) {
            pass = false;
            reason = "findMin().key should be 10, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testFindMin_AfterInsertSmaller(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "FindMin After Insert Smaller";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(50, "fifty");
        heap.insert(30, "thirty");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin().key != 30) {
            pass = false;
            reason = "After inserting 50 then 30, min should be 30, got " + heap.findMin().key;
        }
        
        Heap.HeapItem smallest = heap.insert(10, "ten");
        if (heap.findMin() != smallest || heap.findMin().key != 10) {
            pass = false;
            reason = "After inserting 10, min should be 10, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testFindMin_AfterInsertLarger(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "FindMin After Insert Larger";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem first = heap.insert(10, "ten");
        heap.insert(20, "twenty");
        heap.insert(30, "thirty");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin() != first) {
            pass = false;
            reason = "After inserting larger elements, min should still be the first item";
        } else if (heap.findMin().key != 10) {
            pass = false;
            reason = "Min should be 10, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testFindMin_AfterDeleteMin(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "FindMin After DeleteMin";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(10, "ten");
        heap.insert(20, "twenty");
        heap.insert(30, "thirty");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin().key != 10) {
            pass = false;
            reason = "Initial min should be 10, got " + heap.findMin().key;
        }
        
        if (pass) {
            heap.deleteMin();
            if (heap.findMin().key != 20) {
                pass = false;
                reason = "After deleteMin, new min should be 20, got " + heap.findMin().key;
            }
        }
        
        if (pass) {
            heap.deleteMin();
            if (heap.findMin().key != 30) {
                pass = false;
                reason = "After second deleteMin, new min should be 30, got " + heap.findMin().key;
            }
        }
        
        if (pass) {
            heap.deleteMin();
            if (heap.findMin() != null) {
                pass = false;
                reason = "After deleting all, findMin should be null";
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testFindMin_AfterMeld(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "FindMin After Meld";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap1.insert(20, "twenty");
        heap1.insert(40, "forty");
        heap2.insert(10, "ten");
        heap2.insert(30, "thirty");
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.findMin().key != 20) {
            pass = false;
            reason = "heap1 min should be 20, got " + heap1.findMin().key;
        } else if (heap2.findMin().key != 10) {
            pass = false;
            reason = "heap2 min should be 10, got " + heap2.findMin().key;
        }
        
        if (pass) {
            heap1.meld(heap2);
            if (heap1.findMin().key != 10) {
                pass = false;
                reason = "After meld, min should be 10, got " + heap1.findMin().key;
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testFindMin_WithDuplicates(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "FindMin With Duplicates";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(20, "twenty");
        heap.insert(10, "ten-a");
        heap.insert(10, "ten-b");
        heap.insert(30, "thirty");
        heap.insert(10, "ten-c");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin().key != 10) {
            pass = false;
            reason = "Min should be 10, got " + heap.findMin().key;
        }
        
        // After deleting one 10, min should still be 10
        if (pass) {
            heap.deleteMin();
            if (heap.findMin().key != 10) {
                pass = false;
                reason = "After first deleteMin, min should still be 10, got " + heap.findMin().key;
            }
        }
        
        // After deleting second 10, min should still be 10
        if (pass) {
            heap.deleteMin();
            if (heap.findMin().key != 10) {
                pass = false;
                reason = "After second deleteMin, min should still be 10, got " + heap.findMin().key;
            }
        }
        
        // After deleting third 10, min should be 20
        if (pass) {
            heap.deleteMin();
            if (heap.findMin().key != 20) {
                pass = false;
                reason = "After third deleteMin, min should be 20, got " + heap.findMin().key;
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testFindMin_DoesNotModifyHeap(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "FindMin Does Not Modify Heap";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(30, "thirty");
        heap.insert(10, "ten");
        heap.insert(20, "twenty");
        
        int sizeBefore = heap.size();
        int numTreesBefore = heap.numTrees();
        int totalLinksBefore = heap.totalLinks();
        
        // Call findMin multiple times
        Heap.HeapItem min1 = heap.findMin();
        Heap.HeapItem min2 = heap.findMin();
        Heap.HeapItem min3 = heap.findMin();
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != sizeBefore) {
            pass = false;
            reason = "findMin() should not change size";
        } else if (heap.numTrees() != numTreesBefore) {
            pass = false;
            reason = "findMin() should not change numTrees";
        } else if (heap.totalLinks() != totalLinksBefore) {
            pass = false;
            reason = "findMin() should not change totalLinks";
        } else if (min1 != min2 || min2 != min3) {
            pass = false;
            reason = "findMin() should return the same node each time";
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testFindMin_ConsistentReturns(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "FindMin Consistent Returns";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem minItem = heap.insert(5, "five");
        heap.insert(15, "fifteen");
        heap.insert(25, "twenty-five");
        heap.insert(10, "ten");
        
        boolean pass = true;
        String reason = "";
        
        // Call findMin 100 times - should always return same item
        for (int i = 0; i < 100 && pass; i++) {
            Heap.HeapItem current = heap.findMin();
            if (current != minItem) {
                pass = false;
                reason = "findMin() returned different item on call " + i;
            }
            if (current.key != 5) {
                pass = false;
                reason = "findMin().key changed to " + current.key + " on call " + i;
            }
        }
        
        reportResult(testName, pass, reason);
    }

    // ==================== DELETEMIN TESTS (10) ====================

    private static void testDeleteMin_SingleElement(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DeleteMin Single Element";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(42, "forty-two");
        heap.deleteMin();
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 0) {
            pass = false;
            reason = "After deleteMin on single element, size should be 0, got " + heap.size();
        } else if (heap.findMin() != null) {
            pass = false;
            reason = "After deleteMin on single element, findMin should be null";
        } else if (heap.numTrees() != 0) {
            pass = false;
            reason = "After deleteMin on single element, numTrees should be 0, got " + heap.numTrees();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDeleteMin_TwoElements(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DeleteMin Two Elements";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(10, "ten");
        heap.insert(20, "twenty");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin().key != 10) {
            pass = false;
            reason = "Before deleteMin, min should be 10, got " + heap.findMin().key;
        }
        
        if (pass) {
            heap.deleteMin();
            if (heap.size() != 1) {
                pass = false;
                reason = "After deleteMin, size should be 1, got " + heap.size();
            } else if (heap.findMin().key != 20) {
                pass = false;
                reason = "After deleteMin, min should be 20, got " + heap.findMin().key;
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDeleteMin_MultipleElements(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DeleteMin Multiple Elements";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(30, "thirty");
        heap.insert(10, "ten");
        heap.insert(50, "fifty");
        heap.insert(20, "twenty");
        heap.insert(40, "forty");
        
        boolean pass = true;
        String reason = "";
        
        int[] expected = {10, 20, 30, 40, 50};
        for (int i = 0; i < expected.length && pass; i++) {
            if (heap.findMin().key != expected[i]) {
                pass = false;
                reason = "Expected " + expected[i] + " at step " + i + ", got " + heap.findMin().key;
            }
            heap.deleteMin();
        }
        
        if (pass && heap.size() != 0) {
            pass = false;
            reason = "After all deleteMins, size should be 0, got " + heap.size();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDeleteMin_UpdatesSize(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DeleteMin Updates Size";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        for (int i = 1; i <= 10; i++) {
            heap.insert(i * 10, "val-" + i);
        }
        
        boolean pass = true;
        String reason = "";
        
        for (int i = 10; i >= 1; i--) {
            if (heap.size() != i) {
                pass = false;
                reason = "Before deleteMin, size should be " + i + ", got " + heap.size();
                break;
            }
            heap.deleteMin();
        }
        
        if (pass && heap.size() != 0) {
            pass = false;
            reason = "After all deleteMins, size should be 0, got " + heap.size();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDeleteMin_UpdatesNumTrees(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DeleteMin Updates NumTrees";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        for (int i = 1; i <= 4; i++) {
            heap.insert(i * 10, "val-" + i);
        }
        
        boolean pass = true;
        String reason = "";
        
        // After deleteMin, children become roots but consolidation happens
        heap.deleteMin();
        
        if (heap.numTrees() < 1) {
            pass = false;
            reason = "After deleteMin, numTrees should be >= 1, got " + heap.numTrees();
        }
        
        // Delete all remaining
        while (heap.size() > 0) {
            heap.deleteMin();
        }
        
        if (heap.numTrees() != 0) {
            pass = false;
            reason = "After deleting all, numTrees should be 0, got " + heap.numTrees();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDeleteMin_UpdatesMin(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DeleteMin Updates Min";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(5, "five");
        heap.insert(15, "fifteen");
        heap.insert(10, "ten");
        heap.insert(20, "twenty");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin().key != 5) {
            pass = false;
            reason = "Initial min should be 5, got " + heap.findMin().key;
        }
        
        if (pass) {
            heap.deleteMin(); // removes 5
            if (heap.findMin().key != 10) {
                pass = false;
                reason = "After removing 5, min should be 10, got " + heap.findMin().key;
            }
        }
        
        if (pass) {
            heap.deleteMin(); // removes 10
            if (heap.findMin().key != 15) {
                pass = false;
                reason = "After removing 10, min should be 15, got " + heap.findMin().key;
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDeleteMin_WithDuplicateMinKeys(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DeleteMin With Duplicate Min Keys";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(5, "five-a");
        heap.insert(5, "five-b");
        heap.insert(5, "five-c");
        heap.insert(10, "ten");
        
        boolean pass = true;
        String reason = "";
        
        // First three deleteMins should all return 5
        for (int i = 0; i < 3 && pass; i++) {
            if (heap.findMin().key != 5) {
                pass = false;
                reason = "At step " + i + ", min should be 5, got " + heap.findMin().key;
            }
            heap.deleteMin();
        }
        
        // Fourth should be 10
        if (pass && heap.findMin().key != 10) {
            pass = false;
            reason = "After 3 deleteMins, min should be 10, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDeleteMin_AllElements(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DeleteMin All Elements";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        int n = 100;
        for (int i = n; i >= 1; i--) {
            heap.insert(i, "val-" + i);
        }
        
        boolean pass = true;
        String reason = "";
        
        for (int expected = 1; expected <= n && pass; expected++) {
            if (heap.findMin().key != expected) {
                pass = false;
                reason = "Expected " + expected + ", got " + heap.findMin().key;
            }
            heap.deleteMin();
        }
        
        if (pass && heap.size() != 0) {
            pass = false;
            reason = "After all deleteMins, size should be 0, got " + heap.size();
        }
        
        if (pass && heap.findMin() != null) {
            pass = false;
            reason = "After all deleteMins, findMin should be null";
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDeleteMin_TriggersConsolidation(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DeleteMin Triggers Consolidation";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        // Insert 8 elements (lazy mode will have 8 trees)
        for (int i = 1; i <= 8; i++) {
            heap.insert(i * 10, "val-" + i);
        }
        
        int treesBefore = heap.numTrees();
        int linksBefore = heap.totalLinks();
        
        heap.deleteMin(); // Should trigger consolidation
        
        boolean pass = true;
        String reason = "";
        
        if (lazyMelds) {
            // In lazy mode, deleteMin triggers successive linking
            if (heap.totalLinks() <= linksBefore) {
                pass = false;
                reason = "In lazy mode, deleteMin should trigger linking. Links before: " + linksBefore + ", after: " + heap.totalLinks();
            }
        }
        
        // In any mode, numTrees should be reasonable after consolidation
        if (heap.numTrees() > 7) {
            pass = false;
            reason = "After deleteMin and consolidation, numTrees should be <= 7, got " + heap.numTrees();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDeleteMin_TotalLinksIncreases(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DeleteMin TotalLinks Increases";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        for (int i = 1; i <= 16; i++) {
            heap.insert(i * 10, "val-" + i);
        }
        
        int linksBefore = heap.totalLinks();
        heap.deleteMin();
        int linksAfter = heap.totalLinks();
        
        boolean pass = true;
        String reason = "";
        
        if (linksAfter < linksBefore) {
            pass = false;
            reason = "totalLinks should not decrease after deleteMin. Before: " + linksBefore + ", after: " + linksAfter;
        }
        
        reportResult(testName, pass, reason);
    }

    // ==================== MELD TESTS (10) ====================

    private static void testMeld_TwoNonEmptyHeaps(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld Two Non-Empty Heaps";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap1.insert(10, "h1-10");
        heap1.insert(30, "h1-30");
        heap2.insert(20, "h2-20");
        heap2.insert(40, "h2-40");
        
        heap1.meld(heap2);
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.size() != 4) {
            pass = false;
            reason = "After meld, size should be 4, got " + heap1.size();
        }
        
        int[] expected = {10, 20, 30, 40};
        for (int i = 0; i < expected.length && pass; i++) {
            if (heap1.findMin().key != expected[i]) {
                pass = false;
                reason = "Expected " + expected[i] + " at step " + i + ", got " + heap1.findMin().key;
            }
            heap1.deleteMin();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testMeld_EmptyIntoNonEmpty(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld Empty Into Non-Empty";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap1.insert(10, "ten");
        heap1.insert(20, "twenty");
        // heap2 is empty
        
        int sizeBefore = heap1.size();
        int minBefore = heap1.findMin().key;
        
        heap1.meld(heap2);
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.size() != sizeBefore) {
            pass = false;
            reason = "After meld with empty, size should be " + sizeBefore + ", got " + heap1.size();
        } else if (heap1.findMin().key != minBefore) {
            pass = false;
            reason = "After meld with empty, min should be " + minBefore + ", got " + heap1.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testMeld_NonEmptyIntoEmpty(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld Non-Empty Into Empty";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        // heap1 is empty
        heap2.insert(10, "ten");
        heap2.insert(20, "twenty");
        
        heap1.meld(heap2);
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.size() != 2) {
            pass = false;
            reason = "After empty.meld(non-empty), size should be 2, got " + heap1.size();
        } else if (heap1.findMin().key != 10) {
            pass = false;
            reason = "After empty.meld(non-empty), min should be 10, got " + heap1.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testMeld_TwoEmptyHeaps(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld Two Empty Heaps";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap1.meld(heap2);
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.size() != 0) {
            pass = false;
            reason = "After meld of two empty heaps, size should be 0, got " + heap1.size();
        } else if (heap1.findMin() != null) {
            pass = false;
            reason = "After meld of two empty heaps, findMin should be null";
        } else if (heap1.numTrees() != 0) {
            pass = false;
            reason = "After meld of two empty heaps, numTrees should be 0, got " + heap1.numTrees();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testMeld_UpdatesSize(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld Updates Size";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        for (int i = 1; i <= 5; i++) {
            heap1.insert(i * 10, "h1-" + i);
        }
        for (int i = 1; i <= 7; i++) {
            heap2.insert(i * 100, "h2-" + i);
        }
        
        heap1.meld(heap2);
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.size() != 12) {
            pass = false;
            reason = "After meld, size should be 5+7=12, got " + heap1.size();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testMeld_UpdatesMin(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld Updates Min";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap1.insert(50, "fifty");
        heap1.insert(60, "sixty");
        heap2.insert(10, "ten");
        heap2.insert(20, "twenty");
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.findMin().key != 50) {
            pass = false;
            reason = "Before meld, heap1 min should be 50, got " + heap1.findMin().key;
        }
        
        if (pass) {
            heap1.meld(heap2);
            if (heap1.findMin().key != 10) {
                pass = false;
                reason = "After meld, min should be 10, got " + heap1.findMin().key;
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testMeld_InheritsTotalLinks(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld Inherits TotalLinks";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        for (int i = 1; i <= 4; i++) {
            heap1.insert(i * 10, "h1-" + i);
        }
        for (int i = 1; i <= 4; i++) {
            heap2.insert(i * 100, "h2-" + i);
        }
        
        int links1 = heap1.totalLinks();
        int links2 = heap2.totalLinks();
        
        heap1.meld(heap2);
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.totalLinks() < links1 + links2) {
            pass = false;
            reason = "After meld, totalLinks should be >= " + (links1 + links2) + ", got " + heap1.totalLinks();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testMeld_InheritsTotalCuts(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld Inherits TotalCuts";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        for (int i = 1; i <= 4; i++) {
            heap1.insert(i * 10, "h1-" + i);
            heap2.insert(i * 100, "h2-" + i);
        }
        
        int cuts1 = heap1.totalCuts();
        int cuts2 = heap2.totalCuts();
        
        heap1.meld(heap2);
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.totalCuts() < cuts1 + cuts2) {
            pass = false;
            reason = "After meld, totalCuts should be >= " + (cuts1 + cuts2) + ", got " + heap1.totalCuts();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testMeld_InheritsHeapifyCosts(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld Inherits HeapifyCosts";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        for (int i = 1; i <= 4; i++) {
            heap1.insert(i * 10, "h1-" + i);
            heap2.insert(i * 100, "h2-" + i);
        }
        
        int heapify1 = heap1.totalHeapifyCosts();
        int heapify2 = heap2.totalHeapifyCosts();
        
        heap1.meld(heap2);
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.totalHeapifyCosts() != heapify1 + heapify2) {
            pass = false;
            reason = "After meld, totalHeapifyCosts should be " + (heapify1 + heapify2) + ", got " + heap1.totalHeapifyCosts();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testMeld_NumTreesCorrect(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Meld NumTrees Correct";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap1.insert(10, "ten");
        heap1.insert(20, "twenty");
        heap2.insert(30, "thirty");
        heap2.insert(40, "forty");
        
        int trees1 = heap1.numTrees();
        int trees2 = heap2.numTrees();
        
        heap1.meld(heap2);
        
        boolean pass = true;
        String reason = "";
        
        if (lazyMelds) {
            // In lazy mode, meld just concatenates root lists
            if (heap1.numTrees() != trees1 + trees2) {
                pass = false;
                reason = "In lazy mode, after meld numTrees should be " + (trees1 + trees2) + ", got " + heap1.numTrees();
            }
        } else {
            // In strict mode, successive linking occurs
            if (heap1.numTrees() > trees1 + trees2) {
                pass = false;
                reason = "In strict mode, after meld numTrees should be <= " + (trees1 + trees2) + ", got " + heap1.numTrees();
            }
        }
        
        reportResult(testName, pass, reason);
    }

    // ==================== DELETE TESTS (10) ====================

    private static void testDelete_SingleElement(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete Single Element";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem item = heap.insert(42, "forty-two");
        heap.delete(item);
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 0) {
            pass = false;
            reason = "After deleting only element, size should be 0, got " + heap.size();
        } else if (heap.findMin() != null) {
            pass = false;
            reason = "After deleting only element, findMin should be null";
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDelete_MinElement(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete Min Element";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem minItem = heap.insert(10, "ten");
        heap.insert(20, "twenty");
        heap.insert(30, "thirty");
        
        heap.delete(minItem);
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 2) {
            pass = false;
            reason = "After delete, size should be 2, got " + heap.size();
        } else if (heap.findMin().key != 20) {
            pass = false;
            reason = "After deleting min, new min should be 20, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDelete_MaxElement(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete Max Element";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(10, "ten");
        heap.insert(20, "twenty");
        Heap.HeapItem maxItem = heap.insert(30, "thirty");
        
        heap.delete(maxItem);
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 2) {
            pass = false;
            reason = "After delete, size should be 2, got " + heap.size();
        } else if (heap.findMin().key != 10) {
            pass = false;
            reason = "After deleting max, min should still be 10, got " + heap.findMin().key;
        }
        
        // Verify both remaining elements
        heap.deleteMin();
        if (heap.findMin().key != 20) {
            pass = false;
            reason = "After deleteMin, should have 20 left, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDelete_MiddleElement(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete Middle Element";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(10, "ten");
        Heap.HeapItem middleItem = heap.insert(20, "twenty");
        heap.insert(30, "thirty");
        
        heap.delete(middleItem);
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 2) {
            pass = false;
            reason = "After delete, size should be 2, got " + heap.size();
        }
        
        // Verify extraction order
        if (pass && heap.findMin().key != 10) {
            pass = false;
            reason = "Min should be 10, got " + heap.findMin().key;
        }
        if (pass) {
            heap.deleteMin();
            if (heap.findMin().key != 30) {
                pass = false;
                reason = "After deleteMin, should have 30, got " + heap.findMin().key;
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDelete_UpdatesSize(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete Updates Size";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem[] items = new Heap.HeapItem[5];
        for (int i = 0; i < 5; i++) {
            items[i] = heap.insert((i + 1) * 10, "val-" + (i + 1));
        }
        
        boolean pass = true;
        String reason = "";
        
        heap.delete(items[2]); // delete 30
        if (heap.size() != 4) {
            pass = false;
            reason = "After first delete, size should be 4, got " + heap.size();
        }
        
        if (pass) {
            heap.delete(items[4]); // delete 50
            if (heap.size() != 3) {
                pass = false;
                reason = "After second delete, size should be 3, got " + heap.size();
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDelete_UpdatesMin(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete Updates Min";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem n1 = heap.insert(10, "ten");
        Heap.HeapItem n2 = heap.insert(20, "twenty");
        heap.insert(30, "thirty");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin() != n1) {
            pass = false;
            reason = "Initial min should be item with key 10";
        }
        
        if (pass) {
            heap.delete(n1);
            if (heap.findMin() != n2 || heap.findMin().key != 20) {
                pass = false;
                reason = "After deleting min, new min should be 20, got " + heap.findMin().key;
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDelete_LeafNode(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete Leaf Node";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        // Build a tree structure
        for (int i = 1; i <= 8; i++) {
            heap.insert(i * 10, "val-" + i);
        }
        heap.deleteMin(); // consolidate, removes 10
        
        // Insert a large value that will likely be a leaf
        Heap.HeapItem leaf = heap.insert(100, "hundred");
        
        heap.delete(leaf);
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 7) {
            pass = false;
            reason = "After delete, size should be 7, got " + heap.size();
        }
        
        // Verify heap still works
        int prev = -1;
        while (heap.size() > 0 && pass) {
            int current = heap.findMin().key;
            if (current < prev) {
                pass = false;
                reason = "Heap order violated: got " + current + " after " + prev;
            }
            prev = current;
            heap.deleteMin();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDelete_InternalNode(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete Internal Node";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem[] items = new Heap.HeapItem[8];
        for (int i = 0; i < 8; i++) {
            items[i] = heap.insert((i + 1) * 10, "val-" + (i + 1));
        }
        heap.deleteMin(); // consolidate, removes 10 (items[0])
        
        // Delete an item that might be internal
        heap.delete(items[3]); // delete 40
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 6) {
            pass = false;
            reason = "After delete, size should be 6, got " + heap.size();
        }
        
        // Verify remaining elements in order
        int[] expected = {20, 30, 50, 60, 70, 80};
        for (int i = 0; i < expected.length && pass; i++) {
            if (heap.findMin().key != expected[i]) {
                pass = false;
                reason = "Expected " + expected[i] + " at step " + i + ", got " + heap.findMin().key;
            }
            heap.deleteMin();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDelete_MultipleDeletes(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete Multiple Deletes";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem[] items = new Heap.HeapItem[10];
        for (int i = 0; i < 10; i++) {
            items[i] = heap.insert((i + 1) * 10, "val-" + (i + 1));
        }
        
        // Delete even-indexed items (10, 30, 50, 70, 90)
        heap.delete(items[0]);
        heap.delete(items[2]);
        heap.delete(items[4]);
        heap.delete(items[6]);
        heap.delete(items[8]);
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 5) {
            pass = false;
            reason = "After 5 deletes, size should be 5, got " + heap.size();
        }
        
        // Verify remaining: 20, 40, 60, 80, 100
        int[] expected = {20, 40, 60, 80, 100};
        for (int i = 0; i < expected.length && pass; i++) {
            if (heap.findMin().key != expected[i]) {
                pass = false;
                reason = "Expected " + expected[i] + " at step " + i + ", got " + heap.findMin().key;
            }
            heap.deleteMin();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDelete_AllElements(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Delete All Elements (using delete)";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem[] items = new Heap.HeapItem[10];
        for (int i = 0; i < 10; i++) {
            items[i] = heap.insert((i + 1) * 10, "val-" + (i + 1));
        }
        
        // Delete all using delete() in reverse order
        for (int i = 9; i >= 0; i--) {
            heap.delete(items[i]);
        }
        
        boolean pass = true;
        String reason = "";
        
        if (heap.size() != 0) {
            pass = false;
            reason = "After deleting all, size should be 0, got " + heap.size();
        } else if (heap.findMin() != null) {
            pass = false;
            reason = "After deleting all, findMin should be null";
        } else if (heap.numTrees() != 0) {
            pass = false;
            reason = "After deleting all, numTrees should be 0, got " + heap.numTrees();
        }
        
        reportResult(testName, pass, reason);
    }

    // ==================== DECREASEKEY TESTS (10) ====================

    private static void testDecreaseKey_ByZero(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey By Zero";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(10, "ten");
        Heap.HeapItem item = heap.insert(20, "twenty");
        heap.insert(30, "thirty");
        
        int keyBefore = item.key;
        heap.decreaseKey(item, 0);
        
        boolean pass = true;
        String reason = "";
        
        if (item.key != keyBefore) {
            pass = false;
            reason = "After decreaseKey by 0, key should remain " + keyBefore + ", got " + item.key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDecreaseKey_ByOne(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey By One";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(10, "ten");
        Heap.HeapItem item = heap.insert(20, "twenty");
        heap.insert(30, "thirty");
        
        heap.decreaseKey(item, 1); // 20 -> 19
        
        boolean pass = true;
        String reason = "";
        
        if (item.key != 19) {
            pass = false;
            reason = "After decreaseKey by 1, key should be 19, got " + item.key;
        } else if (heap.findMin().key != 10) {
            pass = false;
            reason = "Min should still be 10, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDecreaseKey_ToNewMin(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey To New Min";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(20, "twenty");
        Heap.HeapItem item = heap.insert(30, "thirty");
        heap.insert(40, "forty");
        
        heap.decreaseKey(item, 25); // 30 -> 5
        
        boolean pass = true;
        String reason = "";
        
        if (item.key != 5) {
            pass = false;
            reason = "After decreaseKey, key should be 5, got " + item.key;
        } else if (heap.findMin() != item) {
            pass = false;
            reason = "After decreaseKey to smallest, findMin should return this item";
        } else if (heap.findMin().key != 5) {
            pass = false;
            reason = "Min should be 5, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDecreaseKey_StillGreaterThanParent(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey Still Greater Than Parent";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        // Build a tree structure
        for (int i = 1; i <= 8; i++) {
            heap.insert(i * 10, "val-" + i);
        }
        heap.deleteMin(); // consolidate, removes 10
        
        // Insert a large value that becomes a child
        Heap.HeapItem item = heap.insert(100, "hundred");
        
        // Decrease but still larger than potential parent
        heap.decreaseKey(item, 5); // 100 -> 95, still large
        
        boolean pass = true;
        String reason = "";
        
        if (item.key != 95) {
            pass = false;
            reason = "After decreaseKey, key should be 95, got " + item.key;
        }
        
        // Verify heap property maintained
        int prev = -1;
        while (heap.size() > 0 && pass) {
            int curr = heap.findMin().key;
            if (curr < prev) {
                pass = false;
                reason = "Heap order violated: got " + curr + " after " + prev;
            }
            prev = curr;
            heap.deleteMin();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDecreaseKey_OnRoot(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey On Root";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem root = heap.insert(20, "twenty");
        heap.insert(30, "thirty");
        heap.insert(40, "forty");
        
        // root is the min, decrease it further
        heap.decreaseKey(root, 10); // 20 -> 10
        
        boolean pass = true;
        String reason = "";
        
        if (root.key != 10) {
            pass = false;
            reason = "After decreaseKey on root, key should be 10, got " + root.key;
        } else if (heap.findMin() != root) {
            pass = false;
            reason = "Min should still be the same item";
        } else if (heap.findMin().key != 10) {
            pass = false;
            reason = "Min should be 10, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDecreaseKey_OnLeaf(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey On Leaf";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        // Build structure
        for (int i = 1; i <= 8; i++) {
            heap.insert(i * 10, "val-" + i);
        }
        heap.deleteMin(); // consolidate
        
        // Insert a leaf
        Heap.HeapItem leaf = heap.insert(200, "twohundred");
        
        // Decrease to become min
        heap.decreaseKey(leaf, 195); // 200 -> 5
        
        boolean pass = true;
        String reason = "";
        
        if (leaf.key != 5) {
            pass = false;
            reason = "After decreaseKey, key should be 5, got " + leaf.key;
        } else if (heap.findMin().key != 5) {
            pass = false;
            reason = "Min should be 5, got " + heap.findMin().key;
        }
        
        // Verify all elements still extractable
        int count = 0;
        while (heap.size() > 0) {
            heap.deleteMin();
            count++;
        }
        if (count != 8) {
            pass = false;
            reason = "Expected 8 elements, extracted " + count;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDecreaseKey_MultipleOnSameNode(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey Multiple On Same Node";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(10, "ten");
        Heap.HeapItem item = heap.insert(100, "hundred");
        heap.insert(20, "twenty");
        
        // Multiple decreases on same item
        heap.decreaseKey(item, 10); // 100 -> 90
        if (item.key != 90) {
            reportResult(testName, false, "After first decreaseKey, key should be 90, got " + item.key);
            return;
        }
        
        heap.decreaseKey(item, 30); // 90 -> 60
        if (item.key != 60) {
            reportResult(testName, false, "After second decreaseKey, key should be 60, got " + item.key);
            return;
        }
        
        heap.decreaseKey(item, 55); // 60 -> 5
        
        boolean pass = true;
        String reason = "";
        
        if (item.key != 5) {
            pass = false;
            reason = "After third decreaseKey, key should be 5, got " + item.key;
        } else if (heap.findMin().key != 5) {
            pass = false;
            reason = "Min should be 5, got " + heap.findMin().key;
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDecreaseKey_MultipleOnDifferentNodes(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey Multiple On Different Nodes";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem[] items = new Heap.HeapItem[5];
        for (int i = 0; i < 5; i++) {
            items[i] = heap.insert((i + 1) * 100, "val-" + (i + 1));
        }
        
        // Decrease multiple different items
        heap.decreaseKey(items[4], 450); // 500 -> 50
        heap.decreaseKey(items[3], 360); // 400 -> 40
        heap.decreaseKey(items[2], 270); // 300 -> 30
        
        boolean pass = true;
        String reason = "";
        
        if (items[4].key != 50) {
            pass = false;
            reason = "Item 4 key should be 50, got " + items[4].key;
        } else if (items[3].key != 40) {
            pass = false;
            reason = "Item 3 key should be 40, got " + items[3].key;
        } else if (items[2].key != 30) {
            pass = false;
            reason = "Item 2 key should be 30, got " + items[2].key;
        }
        
        // Verify extraction order: 30, 40, 50, 100, 200
        int[] expected = {30, 40, 50, 100, 200};
        for (int i = 0; i < expected.length && pass; i++) {
            if (heap.findMin().key != expected[i]) {
                pass = false;
                reason = "Expected " + expected[i] + " at step " + i + ", got " + heap.findMin().key;
            }
            heap.deleteMin();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDecreaseKey_UpdatesMinPointer(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey Updates Min Pointer";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        Heap.HeapItem first = heap.insert(50, "fifty");
        Heap.HeapItem second = heap.insert(100, "hundred");
        heap.insert(75, "seventy-five");
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin() != first) {
            pass = false;
            reason = "Initial min should be first item";
        }
        
        if (pass) {
            heap.decreaseKey(second, 90); // 100 -> 10
            if (heap.findMin() != second) {
                pass = false;
                reason = "After decreaseKey, min should be second item";
            } else if (heap.findMin().key != 10) {
                pass = false;
                reason = "Min key should be 10, got " + heap.findMin().key;
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testDecreaseKey_CounterUpdates(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "DecreaseKey Counter Updates";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        // Build a tree
        for (int i = 1; i <= 8; i++) {
            heap.insert(i * 10, "val-" + i);
        }
        heap.deleteMin(); // consolidate
        
        Heap.HeapItem item = heap.insert(100, "hundred");
        
        int cutsBefore = heap.totalCuts();
        int heapifyBefore = heap.totalHeapifyCosts();
        
        heap.decreaseKey(item, 95); // 100 -> 5, should trigger cut or heapify
        
        int cutsAfter = heap.totalCuts();
        int heapifyAfter = heap.totalHeapifyCosts();
        
        boolean pass = true;
        String reason = "";
        
        if (lazyDecreaseKeys) {
            // Cascading cuts mode - should have cuts
            if (cutsAfter < cutsBefore) {
                pass = false;
                reason = "totalCuts should not decrease";
            }
        } else {
            // Heapify mode - should have heapify costs
            if (heapifyAfter < heapifyBefore) {
                pass = false;
                reason = "totalHeapifyCosts should not decrease";
            }
        }
        
        reportResult(testName, pass, reason);
    }

    // ==================== INTEGRATION TESTS (10) ====================

    private static void testIntegration_InsertDeleteMinSequence(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Integration: Insert-DeleteMin Sequence";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        boolean pass = true;
        String reason = "";
        
        // Interleave inserts and deleteMins
        heap.insert(50, "fifty");
        heap.insert(30, "thirty");
        heap.insert(70, "seventy");
        
        if (heap.findMin().key != 30) {
            pass = false;
            reason = "Min should be 30";
        }
        
        if (pass) {
            heap.deleteMin(); // removes 30
            heap.insert(20, "twenty");
            heap.insert(60, "sixty");
            
            if (heap.findMin().key != 20) {
                pass = false;
                reason = "After operations, min should be 20, got " + heap.findMin().key;
            }
        }
        
        if (pass) {
            // Verify final order: 20, 50, 60, 70
            int[] expected = {20, 50, 60, 70};
            for (int i = 0; i < expected.length && pass; i++) {
                if (heap.findMin().key != expected[i]) {
                    pass = false;
                    reason = "Expected " + expected[i] + " at step " + i + ", got " + heap.findMin().key;
                }
                heap.deleteMin();
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testIntegration_InsertMeldDeleteMin(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Integration: Insert-Meld-DeleteMin";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap1.insert(30, "h1-30");
        heap1.insert(10, "h1-10");
        heap2.insert(20, "h2-20");
        heap2.insert(40, "h2-40");
        
        heap1.meld(heap2);
        heap1.insert(5, "five");
        heap1.deleteMin(); // removes 5
        heap1.insert(15, "fifteen");
        
        boolean pass = true;
        String reason = "";
        
        // Expected order: 10, 15, 20, 30, 40
        int[] expected = {10, 15, 20, 30, 40};
        for (int i = 0; i < expected.length && pass; i++) {
            if (heap1.findMin().key != expected[i]) {
                pass = false;
                reason = "Expected " + expected[i] + " at step " + i + ", got " + heap1.findMin().key;
            }
            heap1.deleteMin();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testIntegration_DecreaseKeyThenDeleteMin(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Integration: DecreaseKey-DeleteMin";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap.insert(20, "twenty");
        Heap.HeapItem item = heap.insert(50, "fifty");
        heap.insert(30, "thirty");
        heap.insert(40, "forty");
        
        heap.decreaseKey(item, 45); // 50 -> 5
        
        boolean pass = true;
        String reason = "";
        
        if (heap.findMin().key != 5) {
            pass = false;
            reason = "After decreaseKey, min should be 5, got " + heap.findMin().key;
        }
        
        if (pass) {
            heap.deleteMin(); // removes 5
            if (heap.findMin().key != 20) {
                pass = false;
                reason = "After deleteMin, min should be 20, got " + heap.findMin().key;
            }
        }
        
        // Verify remaining: 20, 30, 40
        if (pass) {
            int[] expected = {20, 30, 40};
            for (int i = 0; i < expected.length && pass; i++) {
                if (heap.findMin().key != expected[i]) {
                    pass = false;
                    reason = "Expected " + expected[i] + " at step " + i + ", got " + heap.findMin().key;
                }
                heap.deleteMin();
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testIntegration_DeleteThenMeld(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Integration: Delete-Meld";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap1.insert(10, "h1-10");
        Heap.HeapItem toDelete = heap1.insert(20, "h1-20");
        heap1.insert(30, "h1-30");
        
        heap2.insert(15, "h2-15");
        heap2.insert(25, "h2-25");
        
        heap1.delete(toDelete);
        heap1.meld(heap2);
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.size() != 4) {
            pass = false;
            reason = "After delete and meld, size should be 4, got " + heap1.size();
        }
        
        // Verify order: 10, 15, 25, 30
        int[] expected = {10, 15, 25, 30};
        for (int i = 0; i < expected.length && pass; i++) {
            if (heap1.findMin().key != expected[i]) {
                pass = false;
                reason = "Expected " + expected[i] + " at step " + i + ", got " + heap1.findMin().key;
            }
            heap1.deleteMin();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testIntegration_MeldThenDecreaseKey(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Integration: Meld-DecreaseKey";
        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        
        heap1.insert(30, "h1-30");
        heap1.insert(50, "h1-50");
        
        Heap.HeapItem item = heap2.insert(100, "h2-100");
        heap2.insert(40, "h2-40");
        
        heap1.meld(heap2);
        heap1.decreaseKey(item, 95); // 100 -> 5
        
        boolean pass = true;
        String reason = "";
        
        if (heap1.findMin().key != 5) {
            pass = false;
            reason = "After meld and decreaseKey, min should be 5, got " + heap1.findMin().key;
        }
        
        // Verify order: 5, 30, 40, 50
        int[] expected = {5, 30, 40, 50};
        for (int i = 0; i < expected.length && pass; i++) {
            if (heap1.findMin().key != expected[i]) {
                pass = false;
                reason = "Expected " + expected[i] + " at step " + i + ", got " + heap1.findMin().key;
            }
            heap1.deleteMin();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testIntegration_ComplexSequence1(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Integration: Complex Sequence 1";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        boolean pass = true;
        String reason = "";
        
        try {
            // Build initial heap
            Heap.HeapItem[] items = new Heap.HeapItem[10];
            for (int i = 0; i < 10; i++) {
                items[i] = heap.insert((i + 1) * 10, "val-" + (i + 1));
            }
            
            // Delete some
            heap.delete(items[4]); // delete 50
            heap.delete(items[7]); // delete 80
            
            // DecreaseKey
            heap.decreaseKey(items[9], 95); // 100 -> 5
            
            // DeleteMins
            heap.deleteMin(); // removes 5
            heap.deleteMin(); // removes 10
            
            // Insert more
            heap.insert(15, "fifteen");
            heap.insert(35, "thirty-five");
            
            // Meld with another heap
            Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
            heap2.insert(25, "h2-25");
            heap2.insert(45, "h2-45");
            heap.meld(heap2);
            
            // Verify order: 15, 20, 25, 30, 35, 40, 45, 60, 70, 90
            int[] expected = {15, 20, 25, 30, 35, 40, 45, 60, 70, 90};
            for (int i = 0; i < expected.length && pass; i++) {
                if (heap.findMin() == null) {
                    pass = false;
                    reason = "findMin returned null at step " + i;
                    break;
                }
                if (heap.findMin().key != expected[i]) {
                    pass = false;
                    reason = "Expected " + expected[i] + " at step " + i + ", got " + heap.findMin().key;
                }
                heap.deleteMin();
            }
            
        } catch (Exception e) {
            pass = false;
            reason = "Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testIntegration_ComplexSequence2(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Integration: Complex Sequence 2";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        boolean pass = true;
        String reason = "";
        
        try {
            // Insert, deleteMin, meld pattern
            for (int round = 0; round < 3; round++) {
                Heap tempHeap = new Heap(lazyMelds, lazyDecreaseKeys);
                for (int i = 0; i < 5; i++) {
                    tempHeap.insert(round * 100 + i * 10, "r" + round + "-" + i);
                }
                heap.meld(tempHeap);
                heap.deleteMin();
            }
            
            // Should have 12 elements (15 - 3 deleteMins)
            if (heap.size() != 12) {
                pass = false;
                reason = "Expected size 12, got " + heap.size();
            }
            
            // Verify heap property
            int prev = Integer.MIN_VALUE;
            while (heap.size() > 0 && pass) {
                int curr = heap.findMin().key;
                if (curr < prev) {
                    pass = false;
                    reason = "Heap order violated: got " + curr + " after " + prev;
                }
                prev = curr;
                heap.deleteMin();
            }
            
        } catch (Exception e) {
            pass = false;
            reason = "Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testIntegration_StressTest(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Integration: Stress Test";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        boolean pass = true;
        String reason = "";
        
        try {
            int n = 500;
            Heap.HeapItem[] items = new Heap.HeapItem[n];
            
            // Insert all
            for (int i = 0; i < n; i++) {
                items[i] = heap.insert(i * 2, "val-" + i);
            }
            
            // Delete every 5th element
            for (int i = 4; i < n; i += 5) {
                heap.delete(items[i]);
            }
            
            // DecreaseKey on some
            for (int i = 0; i < n; i += 7) {
                if (i % 5 != 4) { // Skip deleted ones
                    heap.decreaseKey(items[i], 1);
                }
            }
            
            // Meld with another heap
            Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
            for (int i = 0; i < 50; i++) {
                heap2.insert(1000 + i, "h2-" + i);
            }
            heap.meld(heap2);
            
            // Extract all and verify order
            int prev = Integer.MIN_VALUE;
            int count = 0;
            while (heap.size() > 0) {
                int curr = heap.findMin().key;
                if (curr < prev) {
                    pass = false;
                    reason = "Heap order violated at extraction " + count + ": got " + curr + " after " + prev;
                    break;
                }
                prev = curr;
                heap.deleteMin();
                count++;
            }
            
            // Expected: 500 - 100 (deleted) + 50 (melded) = 450
            if (pass && count != 450) {
                pass = false;
                reason = "Expected 450 extractions, got " + count;
            }
            
        } catch (Exception e) {
            pass = false;
            reason = "Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage();
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testIntegration_AllCountersConsistent(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Integration: All Counters Consistent";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        boolean pass = true;
        String reason = "";
        
        // Initial state
        if (heap.size() != 0 || heap.numTrees() != 0 || heap.totalLinks() != 0 || 
            heap.totalCuts() != 0 || heap.numMarkedNodes() != 0 || heap.totalHeapifyCosts() != 0) {
            pass = false;
            reason = "Empty heap should have all counters at 0";
        }
        
        if (pass) {
            // After operations
            for (int i = 1; i <= 16; i++) {
                heap.insert(i * 10, "val-" + i);
            }
            
            if (heap.size() != 16) {
                pass = false;
                reason = "After 16 inserts, size should be 16, got " + heap.size();
            }
        }
        
        if (pass) {
            heap.deleteMin();
            if (heap.size() != 15) {
                pass = false;
                reason = "After deleteMin, size should be 15, got " + heap.size();
            }
        }
        
        if (pass) {
            // Counters should be non-negative
            if (heap.numTrees() < 0) {
                pass = false;
                reason = "numTrees should be >= 0, got " + heap.numTrees();
            }
            if (heap.totalLinks() < 0) {
                pass = false;
                reason = "totalLinks should be >= 0, got " + heap.totalLinks();
            }
            if (heap.totalCuts() < 0) {
                pass = false;
                reason = "totalCuts should be >= 0, got " + heap.totalCuts();
            }
            if (heap.numMarkedNodes() < 0) {
                pass = false;
                reason = "numMarkedNodes should be >= 0, got " + heap.numMarkedNodes();
            }
            if (heap.totalHeapifyCosts() < 0) {
                pass = false;
                reason = "totalHeapifyCosts should be >= 0, got " + heap.totalHeapifyCosts();
            }
        }
        
        // After extracting all
        if (pass) {
            while (heap.size() > 0) {
                heap.deleteMin();
            }
            
            if (heap.size() != 0) {
                pass = false;
                reason = "After extracting all, size should be 0, got " + heap.size();
            }
            if (heap.numTrees() != 0) {
                pass = false;
                reason = "After extracting all, numTrees should be 0, got " + heap.numTrees();
            }
        }
        
        reportResult(testName, pass, reason);
    }

    private static void testIntegration_HeapPropertyMaintained(boolean lazyMelds, boolean lazyDecreaseKeys) {
        String testName = "Integration: Heap Property Maintained";
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);
        
        boolean pass = true;
        String reason = "";
        
        try {
            // Random-ish operations
            Heap.HeapItem[] items = new Heap.HeapItem[20];
            
            // Insert in various order
            int[] insertOrder = {15, 3, 18, 7, 12, 1, 9, 20, 5, 14, 2, 17, 8, 11, 4, 19, 6, 13, 10, 16};
            for (int i = 0; i < 20; i++) {
                items[i] = heap.insert(insertOrder[i], "val-" + insertOrder[i]);
            }
            
            // Delete some specific items
            heap.delete(items[5]);  // delete key 1
            heap.delete(items[10]); // delete key 2
            
            // DecreaseKey on item with key 20 to become new min
            heap.decreaseKey(items[7], 20); // 20 -> 0
            
            // Meld
            Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
            heap2.insert(21, "h2-21");
            heap2.insert(22, "h2-22");
            heap.meld(heap2);
            
            // Verify complete extraction is in sorted order
            int prev = Integer.MIN_VALUE;
            int count = 0;
            while (heap.size() > 0 && pass) {
                int curr = heap.findMin().key;
                if (curr < prev) {
                    pass = false;
                    reason = "Heap property violated: extracted " + curr + " after " + prev + " at step " + count;
                    break;
                }
                prev = curr;
                heap.deleteMin();
                count++;
            }
            
            // Expected: 20 - 2 (deleted) + 2 (melded) = 20
            if (pass && count != 20) {
                pass = false;
                reason = "Expected 20 extractions, got " + count;
            }
            
        } catch (Exception e) {
            pass = false;
            reason = "Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage();
        }
        
        reportResult(testName, pass, reason);
    }

    // ==================== HELPER ====================

    private static void reportResult(String testName, boolean pass, String reason) {
        if (pass) {
            System.out.println("  ✓ PASS: " + testName);
            testsPassed++;
        } else {
            System.out.println("  ✗ FAIL: " + testName);
            System.out.println("         " + reason);
            testsFailed++;
        }
    }
}
