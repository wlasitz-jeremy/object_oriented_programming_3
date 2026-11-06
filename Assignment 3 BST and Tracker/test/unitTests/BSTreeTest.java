package unitTests;

import org.junit.Before;
import org.junit.Test;

import implementations.BSTree;
import implementations.BSTreeNode;
import utilities.Iterator;

import static org.junit.Assert.*;

/**
 * JUnit 4 test suite for the BSTree class.
 * Tests all methods defined in the BSTreeADT interface.
 *
 * <p>Standard test tree used across most tests (populated via {@link #populateTree()}):</p>
 * <pre>
 *       M
 *      / \
 *     F   T
 *    / \ / \
 *   C  I P  W
 * </pre>
 * <ul>
 *   <li>In-order:   C, F, I, M, P, T, W</li>
 *   <li>Pre-order:  M, F, C, I, T, P, W</li>
 *   <li>Post-order: C, I, F, P, W, T, M</li>
 *   <li>Min: C &nbsp;|&nbsp; Max: W &nbsp;|&nbsp; Height: 3 &nbsp;|&nbsp; Size: 7</li>
 * </ul>
 */
public class BSTreeTest
{
    // -----------------------------------------------------------------------
    // Fields & Setup
    // -----------------------------------------------------------------------

    /** The BSTree instance under test – re-created fresh before every test. */
    private BSTree tree;

    @Before
    public void setUp()
    {
        tree = new BSTree();
    }

    /**
     * Populates the tree with 7 elements that produce a balanced 3-level structure.
     * Insertion order: M, F, T, C, I, P, W
     */
    private void populateTree()
    {
        tree.add( "M" );
        tree.add( "F" );
        tree.add( "T" );
        tree.add( "C" );
        tree.add( "I" );
        tree.add( "P" );
        tree.add( "W" );
    }


    // -----------------------------------------------------------------------
    // size()
    // -----------------------------------------------------------------------

    /** A newly created tree must report size 0. */
    @Test
    public void testSize_emptyTree_returnsZero()
    {
        assertEquals( 0, tree.size() );
    }

    /** Size increments by 1 after each successful add. */
    @Test
    public void testSize_afterAdd_incrementsByOne()
    {
        tree.add( "M" );
        assertEquals( 1, tree.size() );

        tree.add( "F" );
        assertEquals( 2, tree.size() );
    }

    /** Size reflects all 7 added elements. */
    @Test
    public void testSize_afterMultipleAdds_returnsCorrectCount()
    {
        populateTree();
        assertEquals( 7, tree.size() );
    }

    /** Size decrements by 1 after removeMin. */
    @Test
    public void testSize_afterRemoveMin_decrementsByOne()
    {
        populateTree();
        tree.removeMin();
        assertEquals( 6, tree.size() );
    }

    /** Size decrements by 1 after removeMax. */
    @Test
    public void testSize_afterRemoveMax_decrementsByOne()
    {
        populateTree();
        tree.removeMax();
        assertEquals( 6, tree.size() );
    }

    /** Size becomes 0 after clear. */
    @Test
    public void testSize_afterClear_returnsZero()
    {
        populateTree();
        tree.clear();
        assertEquals( 0, tree.size() );
    }


    // -----------------------------------------------------------------------
    // isEmpty()
    // -----------------------------------------------------------------------

    /** A new tree is empty. */
    @Test
    public void testIsEmpty_newTree_returnsTrue()
    {
        assertTrue( tree.isEmpty() );
    }

    /** A tree with one element is not empty. */
    @Test
    public void testIsEmpty_afterAdd_returnsFalse()
    {
        tree.add( "M" );
        assertFalse( tree.isEmpty() );
    }

    /** After clear, the tree is empty again. */
    @Test
    public void testIsEmpty_afterClear_returnsTrue()
    {
        populateTree();
        tree.clear();
        assertTrue( tree.isEmpty() );
    }

    /** After removing the only element via removeMin, tree is empty. */
    @Test
    public void testIsEmpty_afterRemovingOnlyElementViaRemoveMin_returnsTrue()
    {
        tree.add( "M" );
        tree.removeMin();
        assertTrue( tree.isEmpty() );
    }

    /** After removing the only element via removeMax, tree is empty. */
    @Test
    public void testIsEmpty_afterRemovingOnlyElementViaRemoveMax_returnsTrue()
    {
        tree.add( "M" );
        tree.removeMax();
        assertTrue( tree.isEmpty() );
    }


    // -----------------------------------------------------------------------
    // clear()
    // -----------------------------------------------------------------------

    /** Clearing an already-empty tree must not throw. */
    @Test
    public void testClear_emptyTree_noException()
    {
        tree.clear();
        assertEquals( 0, tree.size() );
    }

    /** Clearing a populated tree resets size to 0. */
    @Test
    public void testClear_populatedTree_sizeBecomesZero()
    {
        populateTree();
        tree.clear();
        assertEquals( 0, tree.size() );
    }

    /** The tree can be used normally after a clear. */
    @Test
    public void testClear_thenAdd_treeFunctionsNormally()
    {
        populateTree();
        tree.clear();
        tree.add( "Z" );
        assertEquals( 1, tree.size() );
        assertTrue( tree.contains( "Z" ) );
    }

    /** Repeated clear calls leave the tree in a consistent state. */
    @Test
    public void testClear_calledTwice_noException()
    {
        populateTree();
        tree.clear();
        tree.clear();
        assertTrue( tree.isEmpty() );
    }


    // -----------------------------------------------------------------------
    // add(String)
    // -----------------------------------------------------------------------

    /** Adding a valid element returns true. */
    @Test
    public void testAdd_validElement_returnsTrue()
    {
        assertTrue( tree.add( "M" ) );
    }

    /** Adding multiple distinct elements all return true. */
    @Test
    public void testAdd_multipleDistinctElements_allReturnTrue()
    {
        assertTrue( tree.add( "M" ) );
        assertTrue( tree.add( "F" ) );
        assertTrue( tree.add( "T" ) );
    }

    /** Adding a duplicate element returns false. */
    @Test
    public void testAdd_duplicateElement_returnsFalse()
    {
        tree.add( "M" );
        assertFalse( tree.add( "M" ) );
    }

    /** Adding a duplicate does not increase the size. */
    @Test
    public void testAdd_duplicateElement_sizeUnchanged()
    {
        tree.add( "M" );
        tree.add( "M" );
        assertEquals( 1, tree.size() );
    }

    /** A smaller element is placed as the left child of the root. */
    @Test
    public void testAdd_smallerElement_placedLeftOfRoot()
    {
        tree.add( "M" );
        tree.add( "F" );
        assertNotNull( tree.getRoot().getLeft() );
        assertEquals( "F", tree.getRoot().getLeft().getElement() );
    }

    /** A larger element is placed as the right child of the root. */
    @Test
    public void testAdd_largerElement_placedRightOfRoot()
    {
        tree.add( "M" );
        tree.add( "T" );
        assertNotNull( tree.getRoot().getRight() );
        assertEquals( "T", tree.getRoot().getRight().getElement() );
    }

    /** After 7 adds, all elements are contained in the tree. */
    @Test
    public void testAdd_sevenElements_allContained()
    {
        populateTree();
        assertTrue( tree.contains( "M" ) );
        assertTrue( tree.contains( "F" ) );
        assertTrue( tree.contains( "T" ) );
        assertTrue( tree.contains( "C" ) );
        assertTrue( tree.contains( "I" ) );
        assertTrue( tree.contains( "P" ) );
        assertTrue( tree.contains( "W" ) );
    }

    /** Adding null throws NullPointerException. */
    @Test( expected = NullPointerException.class )
    public void testAdd_null_throwsNullPointerException()
    {
        tree.add( null );
    }


    // -----------------------------------------------------------------------
    // getRoot()
    // -----------------------------------------------------------------------

    /** Getting root of an empty tree throws NullPointerException. */
    @Test( expected = NullPointerException.class )
    public void testGetRoot_emptyTree_throwsNullPointerException()
    {
        tree.getRoot();
    }

    /** Root node is non-null after adding an element. */
    @Test
    public void testGetRoot_singleElement_returnsNonNull()
    {
        tree.add( "M" );
        assertNotNull( tree.getRoot() );
    }

    /** Root element matches the first element added. */
    @Test
    public void testGetRoot_singleElement_rootHasCorrectElement()
    {
        tree.add( "M" );
        assertEquals( "M", tree.getRoot().getElement() );
    }

    /** Root element remains the first-added element after subsequent adds. */
    @Test
    public void testGetRoot_afterMultipleAdds_rootElementUnchanged()
    {
        populateTree();
        assertEquals( "M", tree.getRoot().getElement() );
    }

    /** After clear, root throws NullPointerException again. */
    @Test( expected = NullPointerException.class )
    public void testGetRoot_afterClear_throwsNullPointerException()
    {
        populateTree();
        tree.clear();
        tree.getRoot();
    }


    // -----------------------------------------------------------------------
    // getHeight()
    // -----------------------------------------------------------------------

    /** An empty tree has height 0. */
    @Test
    public void testGetHeight_emptyTree_returnsZero()
    {
        assertEquals( 0, tree.getHeight() );
    }

    /** A tree with only a root node has height 1. */
    @Test
    public void testGetHeight_singleElement_returnsOne()
    {
        tree.add( "M" );
        assertEquals( 1, tree.getHeight() );
    }

    /** A tree with two levels has height 2. */
    @Test
    public void testGetHeight_twoLevels_returnsTwo()
    {
        tree.add( "M" );
        tree.add( "F" );
        assertEquals( 2, tree.getHeight() );
    }

    /** A balanced 7-element tree has height 3. */
    @Test
    public void testGetHeight_threeLevels_returnsThree()
    {
        populateTree();
        assertEquals( 3, tree.getHeight() );
    }

    /** Height returns 0 after clear. */
    @Test
    public void testGetHeight_afterClear_returnsZero()
    {
        populateTree();
        tree.clear();
        assertEquals( 0, tree.getHeight() );
    }


    // -----------------------------------------------------------------------
    // contains(String)
    // -----------------------------------------------------------------------

    /** Returns true when the element is the root. */
    @Test
    public void testContains_rootElement_returnsTrue()
    {
        populateTree();
        assertTrue( tree.contains( "M" ) );
    }

    /** Returns true when the element is in the left subtree. */
    @Test
    public void testContains_leftSubtreeElement_returnsTrue()
    {
        populateTree();
        assertTrue( tree.contains( "C" ) );
    }

    /** Returns true when the element is in the right subtree. */
    @Test
    public void testContains_rightSubtreeElement_returnsTrue()
    {
        populateTree();
        assertTrue( tree.contains( "W" ) );
    }

    /** Returns false when the element is not in the tree. */
    @Test
    public void testContains_missingElement_returnsFalse()
    {
        populateTree();
        assertFalse( tree.contains( "Z" ) );
    }

    /** Returns false on an empty tree. */
    @Test
    public void testContains_emptyTree_returnsFalse()
    {
        assertFalse( tree.contains( "M" ) );
    }

    /** Returns false for the element removed by removeMin. */
    @Test
    public void testContains_afterRemoveMin_removedElementNotFound()
    {
        populateTree();
        tree.removeMin();                       // removes "C"
        assertFalse( tree.contains( "C" ) );
    }

    /** Returns false for the element removed by removeMax. */
    @Test
    public void testContains_afterRemoveMax_removedElementNotFound()
    {
        populateTree();
        tree.removeMax();                       // removes "W"
        assertFalse( tree.contains( "W" ) );
    }

    /** Returns false for all elements after clear. */
    @Test
    public void testContains_afterClear_returnsFalse()
    {
        populateTree();
        tree.clear();
        assertFalse( tree.contains( "M" ) );
    }

    /** Passing null throws NullPointerException. */
    @Test( expected = NullPointerException.class )
    public void testContains_null_throwsNullPointerException()
    {
        tree.contains( null );
    }


    // -----------------------------------------------------------------------
    // search(String)
    // -----------------------------------------------------------------------

    /** Searching for an existing element returns a non-null node. */
    @Test
    public void testSearch_existingElement_returnsNonNullNode()
    {
        populateTree();
        assertNotNull( tree.search( "M" ) );
    }

    /** Returned node contains the correct element. */
    @Test
    public void testSearch_existingElement_nodeHasCorrectElement()
    {
        populateTree();
        BSTreeNode result = tree.search( "F" );
        assertNotNull( result );
        assertEquals( "F", result.getElement() );
    }

    /** Searching for a left-subtree element returns the correct node. */
    @Test
    public void testSearch_leftSubtreeElement_returnsCorrectNode()
    {
        populateTree();
        BSTreeNode result = tree.search( "C" );
        assertNotNull( result );
        assertEquals( "C", result.getElement() );
    }

    /** Searching for a right-subtree element returns the correct node. */
    @Test
    public void testSearch_rightSubtreeElement_returnsCorrectNode()
    {
        populateTree();
        BSTreeNode result = tree.search( "W" );
        assertNotNull( result );
        assertEquals( "W", result.getElement() );
    }

    /** Searching for a missing element returns null. */
    @Test
    public void testSearch_missingElement_returnsNull()
    {
        populateTree();
        assertNull( tree.search( "Z" ) );
    }

    /** Searching in an empty tree returns null. */
    @Test
    public void testSearch_emptyTree_returnsNull()
    {
        assertNull( tree.search( "M" ) );
    }

    /** Searching for a removed element returns null. */
    @Test
    public void testSearch_afterRemoveMin_removedElementReturnsNull()
    {
        populateTree();
        tree.removeMin();                       // removes "C"
        assertNull( tree.search( "C" ) );
    }

    /** Searching after clear returns null. */
    @Test
    public void testSearch_afterClear_returnsNull()
    {
        populateTree();
        tree.clear();
        assertNull( tree.search( "M" ) );
    }

    /** Passing null throws NullPointerException. */
    @Test( expected = NullPointerException.class )
    public void testSearch_null_throwsNullPointerException()
    {
        tree.search( null );
    }


    // -----------------------------------------------------------------------
    // findMin()
    // -----------------------------------------------------------------------

    /** findMin() on an empty tree returns null. */
    @Test
    public void testFindMin_emptyTree_returnsNull()
    {
        assertNull( tree.findMin() );
    }

    /** findMin() on a single-element tree returns that element's node. */
    @Test
    public void testFindMin_singleElement_returnsRootNode()
    {
        tree.add( "M" );
        BSTreeNode result = tree.findMin();
        assertNotNull( result );
        assertEquals( "M", result.getElement() );
    }

    /** findMin() returns the lexicographically smallest element. */
    @Test
    public void testFindMin_populatedTree_returnsSmallestElement()
    {
        populateTree();
        BSTreeNode result = tree.findMin();
        assertNotNull( result );
        assertEquals( "C", result.getElement() );
    }

    /** findMin() does not remove the minimum element from the tree. */
    @Test
    public void testFindMin_populatedTree_doesNotRemoveElement()
    {
        populateTree();
        tree.findMin();
        assertEquals( 7, tree.size() );
        assertTrue( tree.contains( "C" ) );
    }

    /** findMin() does not change the size of the tree. */
    @Test
    public void testFindMin_populatedTree_sizeUnchanged()
    {
        populateTree();
        int sizeBefore = tree.size();
        tree.findMin();
        assertEquals( sizeBefore, tree.size() );
    }

    /** findMin() returns the correct new minimum after the previous minimum is removed. */
    @Test
    public void testFindMin_afterRemoveMin_returnsNewMinimum()
    {
        populateTree();
        tree.removeMin();                       // removes "C"; new min is "F"
        BSTreeNode result = tree.findMin();
        assertNotNull( result );
        assertEquals( "F", result.getElement() );
    }

    /** findMin() called twice on the same tree returns the same node each time. */
    @Test
    public void testFindMin_calledTwice_returnsSameElement()
    {
        populateTree();
        BSTreeNode first = tree.findMin();
        BSTreeNode second = tree.findMin();
        assertEquals( first.getElement(), second.getElement() );
    }

    /** findMin() result matches the first element of the in-order iterator. */
    @Test
    public void testFindMin_matchesFirstInorderElement()
    {
        populateTree();
        String minElement = tree.findMin().getElement();
        Iterator it = tree.inorderIterator();
        assertEquals( minElement, it.next() );
    }

    /** findMin() on a two-element tree returns the smaller element. */
    @Test
    public void testFindMin_twoElements_returnsSmallerElement()
    {
        tree.add( "M" );
        tree.add( "F" );
        BSTreeNode result = tree.findMin();
        assertNotNull( result );
        assertEquals( "F", result.getElement() );
    }

    /** findMin() returns null after all elements are removed via removeMin. */
    @Test
    public void testFindMin_afterAllElementsRemoved_returnsNull()
    {
        tree.add( "M" );
        tree.removeMin();
        assertNull( tree.findMin() );
    }


    // -----------------------------------------------------------------------
    // findMax()
    // -----------------------------------------------------------------------

    /** findMax() on an empty tree returns null. */
    @Test
    public void testFindMax_emptyTree_returnsNull()
    {
        assertNull( tree.findMax() );
    }

    /** findMax() on a single-element tree returns that element's node. */
    @Test
    public void testFindMax_singleElement_returnsRootNode()
    {
        tree.add( "M" );
        BSTreeNode result = tree.findMax();
        assertNotNull( result );
        assertEquals( "M", result.getElement() );
    }

    /** findMax() returns the lexicographically largest element. */
    @Test
    public void testFindMax_populatedTree_returnsLargestElement()
    {
        populateTree();
        BSTreeNode result = tree.findMax();
        assertNotNull( result );
        assertEquals( "W", result.getElement() );
    }

    /** findMax() does not remove the maximum element from the tree. */
    @Test
    public void testFindMax_populatedTree_doesNotRemoveElement()
    {
        populateTree();
        tree.findMax();
        assertEquals( 7, tree.size() );
        assertTrue( tree.contains( "W" ) );
    }

    /** findMax() does not change the size of the tree. */
    @Test
    public void testFindMax_populatedTree_sizeUnchanged()
    {
        populateTree();
        int sizeBefore = tree.size();
        tree.findMax();
        assertEquals( sizeBefore, tree.size() );
    }

    /** findMax() returns the correct new maximum after the previous maximum is removed. */
    @Test
    public void testFindMax_afterRemoveMax_returnsNewMaximum()
    {
        populateTree();
        tree.removeMax();                       // removes "W"; new max is "T"
        BSTreeNode result = tree.findMax();
        assertNotNull( result );
        assertEquals( "T", result.getElement() );
    }

    /** findMax() called twice on the same tree returns the same node each time. */
    @Test
    public void testFindMax_calledTwice_returnsSameElement()
    {
        populateTree();
        BSTreeNode first = tree.findMax();
        BSTreeNode second = tree.findMax();
        assertEquals( first.getElement(), second.getElement() );
    }

    /** findMax() result matches the last element of the in-order iterator. */
    @Test
    public void testFindMax_matchesLastInorderElement()
    {
        populateTree();
        String maxElement = tree.findMax().getElement();
        Iterator it = tree.inorderIterator();
        String last = null;
        while( it.hasNext() )
        {
            last = it.next();
        }
        assertEquals( maxElement, last );
    }

    /** findMax() on a two-element tree returns the larger element. */
    @Test
    public void testFindMax_twoElements_returnsLargerElement()
    {
        tree.add( "M" );
        tree.add( "T" );
        BSTreeNode result = tree.findMax();
        assertNotNull( result );
        assertEquals( "T", result.getElement() );
    }

    /** findMax() returns null after all elements are removed via removeMax. */
    @Test
    public void testFindMax_afterAllElementsRemoved_returnsNull()
    {
        tree.add( "M" );
        tree.removeMax();
        assertNull( tree.findMax() );
    }


    // -----------------------------------------------------------------------
    // removeMin()
    // -----------------------------------------------------------------------

    /** Removing min from an empty tree returns null. */
    @Test
    public void testRemoveMin_emptyTree_returnsNull()
    {
        assertNull( tree.removeMin() );
    }

    /** Removing min from a single-element tree returns the root node. */
    @Test
    public void testRemoveMin_singleElement_returnsRootNode()
    {
        tree.add( "M" );
        BSTreeNode removed = tree.removeMin();
        assertNotNull( removed );
        assertEquals( "M", removed.getElement() );
    }

    /** Removing min from a single-element tree leaves the tree empty. */
    @Test
    public void testRemoveMin_singleElement_treeBecomesEmpty()
    {
        tree.add( "M" );
        tree.removeMin();
        assertTrue( tree.isEmpty() );
    }

    /** Removing min returns the lexicographically smallest element. */
    @Test
    public void testRemoveMin_populatedTree_returnsSmallestElement()
    {
        populateTree();
        BSTreeNode removed = tree.removeMin();
        assertNotNull( removed );
        assertEquals( "C", removed.getElement() );
    }

    /** Removing min decrements the size by 1. */
    @Test
    public void testRemoveMin_populatedTree_sizeDecrementsBy1()
    {
        populateTree();
        tree.removeMin();
        assertEquals( 6, tree.size() );
    }

    /** Removed min element is no longer contained in the tree. */
    @Test
    public void testRemoveMin_populatedTree_removedElementNotInTree()
    {
        populateTree();
        BSTreeNode removed = tree.removeMin();
        assertFalse( tree.contains( removed.getElement() ) );
    }

    /** Calling removeMin twice removes the two smallest elements in order. */
    @Test
    public void testRemoveMin_calledTwice_removesTwoSmallestElements()
    {
        populateTree();
        BSTreeNode first = tree.removeMin();    // removes "C"
        BSTreeNode second = tree.removeMin();   // removes "F"
        assertEquals( "C", first.getElement() );
        assertEquals( "F", second.getElement() );
        assertEquals( 5, tree.size() );
    }

    /** Removing min from a two-element tree leaves only the larger element. */
    @Test
    public void testRemoveMin_twoElements_leavesLargerElement()
    {
        tree.add( "M" );
        tree.add( "F" );
        tree.removeMin();                       // removes "F"
        assertTrue( tree.contains( "M" ) );
        assertFalse( tree.contains( "F" ) );
    }

    /** Removing min on an empty tree does not throw. */
    @Test
    public void testRemoveMin_emptyTree_noException()
    {
        assertNull( tree.removeMin() );
        assertEquals( 0, tree.size() );
    }


    // -----------------------------------------------------------------------
    // removeMax()
    // -----------------------------------------------------------------------

    /** Removing max from an empty tree returns null. */
    @Test
    public void testRemoveMax_emptyTree_returnsNull()
    {
        assertNull( tree.removeMax() );
    }

    /** Removing max from a single-element tree returns the root node. */
    @Test
    public void testRemoveMax_singleElement_returnsRootNode()
    {
        tree.add( "M" );
        BSTreeNode removed = tree.removeMax();
        assertNotNull( removed );
        assertEquals( "M", removed.getElement() );
    }

    /** Removing max from a single-element tree leaves the tree empty. */
    @Test
    public void testRemoveMax_singleElement_treeBecomesEmpty()
    {
        tree.add( "M" );
        tree.removeMax();
        assertTrue( tree.isEmpty() );
    }

    /** Removing max returns the lexicographically largest element. */
    @Test
    public void testRemoveMax_populatedTree_returnsLargestElement()
    {
        populateTree();
        BSTreeNode removed = tree.removeMax();
        assertNotNull( removed );
        assertEquals( "W", removed.getElement() );
    }

    /** Removing max decrements the size by 1. */
    @Test
    public void testRemoveMax_populatedTree_sizeDecrementsBy1()
    {
        populateTree();
        tree.removeMax();
        assertEquals( 6, tree.size() );
    }

    /** Removed max element is no longer contained in the tree. */
    @Test
    public void testRemoveMax_populatedTree_removedElementNotInTree()
    {
        populateTree();
        BSTreeNode removed = tree.removeMax();
        assertFalse( tree.contains( removed.getElement() ) );
    }

    /** Calling removeMax twice removes the two largest elements in order. */
    @Test
    public void testRemoveMax_calledTwice_removesTwoLargestElements()
    {
        populateTree();
        BSTreeNode first = tree.removeMax();    // removes "W"
        BSTreeNode second = tree.removeMax();   // removes "T"
        assertEquals( "W", first.getElement() );
        assertEquals( "T", second.getElement() );
        assertEquals( 5, tree.size() );
    }

    /** Removing max from a two-element tree leaves only the smaller element. */
    @Test
    public void testRemoveMax_twoElements_leavesSmallerElement()
    {
        tree.add( "M" );
        tree.add( "T" );
        tree.removeMax();                       // removes "T"
        assertTrue( tree.contains( "M" ) );
        assertFalse( tree.contains( "T" ) );
    }

    /** Removing max on an empty tree does not throw. */
    @Test
    public void testRemoveMax_emptyTree_noException()
    {
        assertNull( tree.removeMax() );
        assertEquals( 0, tree.size() );
    }


    // -----------------------------------------------------------------------
    // inorderIterator()
    // -----------------------------------------------------------------------

    /** inorderIterator() never returns null. */
    @Test
    public void testInorderIterator_alwaysReturnsNonNull()
    {
        assertNotNull( tree.inorderIterator() );
    }

    /** An in-order iterator over an empty tree has no elements. */
    @Test
    public void testInorderIterator_emptyTree_hasNextReturnsFalse()
    {
        Iterator it = tree.inorderIterator();
        assertFalse( it.hasNext() );
    }

    /** A single-element tree produces a single-step in-order iterator. */
    @Test
    public void testInorderIterator_singleElement_traversesOneElement()
    {
        tree.add( "M" );
        Iterator it = tree.inorderIterator();
        assertTrue( it.hasNext() );
        assertEquals( "M", it.next() );
        assertFalse( it.hasNext() );
    }

    /** In-order traversal visits all elements in ascending lexicographic order. */
    @Test
    public void testInorderIterator_populatedTree_traversesInAscendingOrder()
    {
        populateTree();
        Iterator it = tree.inorderIterator();

        assertEquals( "C", it.next() );
        assertEquals( "F", it.next() );
        assertEquals( "I", it.next() );
        assertEquals( "M", it.next() );
        assertEquals( "P", it.next() );
        assertEquals( "T", it.next() );
        assertEquals( "W", it.next() );
        assertFalse( it.hasNext() );
    }

    /** In-order traversal visits all 7 elements exactly once. */
    @Test
    public void testInorderIterator_populatedTree_traversesAllSevenElements()
    {
        populateTree();
        Iterator it = tree.inorderIterator();
        int count = 0;
        while( it.hasNext() )
        {
            it.next();
            count++;
        }
        assertEquals( 7, count );
    }

    /** Each call to inorderIterator() returns an independent cursor. */
    @Test
    public void testInorderIterator_calledTwice_returnsTwoIndependentIterators()
    {
        populateTree();
        Iterator it1 = tree.inorderIterator();
        Iterator it2 = tree.inorderIterator();

        it1.next();                             // advance it1 past "C"
        assertEquals( "C", it2.next() );        // it2 still at beginning
    }


    // -----------------------------------------------------------------------
    // preorderIterator()
    // -----------------------------------------------------------------------

    /** preorderIterator() never returns null. */
    @Test
    public void testPreorderIterator_alwaysReturnsNonNull()
    {
        assertNotNull( tree.preorderIterator() );
    }

    /** A pre-order iterator over an empty tree has no elements. */
    @Test
    public void testPreorderIterator_emptyTree_hasNextReturnsFalse()
    {
        Iterator it = tree.preorderIterator();
        assertFalse( it.hasNext() );
    }

    /** A single-element tree produces a single-step pre-order iterator. */
    @Test
    public void testPreorderIterator_singleElement_traversesOneElement()
    {
        tree.add( "M" );
        Iterator it = tree.preorderIterator();
        assertTrue( it.hasNext() );
        assertEquals( "M", it.next() );
        assertFalse( it.hasNext() );
    }

    /**
     * Pre-order traversal visits root first, then left subtree, then right.
     * Expected: M, F, C, I, T, P, W
     */
    @Test
    public void testPreorderIterator_populatedTree_traversesRootLeftRight()
    {
        populateTree();
        Iterator it = tree.preorderIterator();

        assertEquals( "M", it.next() );    // root
        assertEquals( "F", it.next() );    // left subtree root
        assertEquals( "C", it.next() );    // left-left leaf
        assertEquals( "I", it.next() );    // left-right leaf
        assertEquals( "T", it.next() );    // right subtree root
        assertEquals( "P", it.next() );    // right-left leaf
        assertEquals( "W", it.next() );    // right-right leaf
        assertFalse( it.hasNext() );
    }

    /** Pre-order traversal visits all 7 elements exactly once. */
    @Test
    public void testPreorderIterator_populatedTree_traversesAllSevenElements()
    {
        populateTree();
        Iterator it = tree.preorderIterator();
        int count = 0;
        while( it.hasNext() )
        {
            it.next();
            count++;
        }
        assertEquals( 7, count );
    }

    /** Each call to preorderIterator() returns an independent cursor. */
    @Test
    public void testPreorderIterator_calledTwice_returnsTwoIndependentIterators()
    {
        populateTree();
        Iterator it1 = tree.preorderIterator();
        Iterator it2 = tree.preorderIterator();

        it1.next();                             // advance it1 past "M"
        assertEquals( "M", it2.next() );        // it2 still at beginning
    }


    // -----------------------------------------------------------------------
    // postorderIterator()
    // -----------------------------------------------------------------------

    /** postorderIterator() never returns null. */
    @Test
    public void testPostorderIterator_alwaysReturnsNonNull()
    {
        assertNotNull( tree.postorderIterator() );
    }

    /** A post-order iterator over an empty tree has no elements. */
    @Test
    public void testPostorderIterator_emptyTree_hasNextReturnsFalse()
    {
        Iterator it = tree.postorderIterator();
        assertFalse( it.hasNext() );
    }

    /** A single-element tree produces a single-step post-order iterator. */
    @Test
    public void testPostorderIterator_singleElement_traversesOneElement()
    {
        tree.add( "M" );
        Iterator it = tree.postorderIterator();
        assertTrue( it.hasNext() );
        assertEquals( "M", it.next() );
        assertFalse( it.hasNext() );
    }

    /**
     * Post-order traversal visits leaves first, root last.
     * Expected: C, I, F, P, W, T, M
     */
    @Test
    public void testPostorderIterator_populatedTree_traversesLeftRightRoot()
    {
        populateTree();
        Iterator it = tree.postorderIterator();

        assertEquals( "C", it.next() );    // left-left leaf
        assertEquals( "I", it.next() );    // left-right leaf
        assertEquals( "F", it.next() );    // left subtree root
        assertEquals( "P", it.next() );    // right-left leaf
        assertEquals( "W", it.next() );    // right-right leaf
        assertEquals( "T", it.next() );    // right subtree root
        assertEquals( "M", it.next() );    // root last
        assertFalse( it.hasNext() );
    }

    /** Post-order traversal visits all 7 elements exactly once. */
    @Test
    public void testPostorderIterator_populatedTree_traversesAllSevenElements()
    {
        populateTree();
        Iterator it = tree.postorderIterator();
        int count = 0;
        while( it.hasNext() )
        {
            it.next();
            count++;
        }
        assertEquals( 7, count );
    }

    /** Each call to postorderIterator() returns an independent cursor. */
    @Test
    public void testPostorderIterator_calledTwice_returnsTwoIndependentIterators()
    {
        populateTree();
        Iterator it1 = tree.postorderIterator();
        Iterator it2 = tree.postorderIterator();

        it1.next();                             // advance it1 past "C"
        assertEquals( "C", it2.next() );        // it2 still at beginning
    }


    // -----------------------------------------------------------------------
    // Interaction Tests
    // -----------------------------------------------------------------------

    /** Adding and searching for all elements finds every one. */
    @Test
    public void testInteraction_addThenSearch_allElementsFound()
    {
        populateTree();
        assertNotNull( tree.search( "M" ) );
        assertNotNull( tree.search( "F" ) );
        assertNotNull( tree.search( "T" ) );
        assertNotNull( tree.search( "C" ) );
        assertNotNull( tree.search( "I" ) );
        assertNotNull( tree.search( "P" ) );
        assertNotNull( tree.search( "W" ) );
    }

    /** Removing min and max leaves the five middle elements intact. */
    @Test
    public void testInteraction_removeMinAndMax_middleElementsRemain()
    {
        populateTree();
        tree.removeMin();                       // removes "C"
        tree.removeMax();                       // removes "W"
        assertEquals( 5, tree.size() );
        assertTrue( tree.contains( "M" ) );
        assertTrue( tree.contains( "F" ) );
        assertTrue( tree.contains( "T" ) );
        assertTrue( tree.contains( "I" ) );
        assertTrue( tree.contains( "P" ) );
        assertFalse( tree.contains( "C" ) );
        assertFalse( tree.contains( "W" ) );
    }

    /** findMin() and findMax() agree with removeMin() and removeMax() on the same tree. */
    @Test
    public void testInteraction_findMinAndFindMax_agreeWithRemoveMinAndRemoveMax()
    {
        populateTree();
        String foundMin = tree.findMin().getElement();
        String foundMax = tree.findMax().getElement();
        String removedMin = tree.removeMin().getElement();
        String removedMax = tree.removeMax().getElement();
        assertEquals( foundMin, removedMin );
        assertEquals( foundMax, removedMax );
    }

    /** findMin() after findMax() leaves the tree unchanged. */
    @Test
    public void testInteraction_findMinThenFindMax_treeSizeUnchanged()
    {
        populateTree();
        tree.findMin();
        tree.findMax();
        assertEquals( 7, tree.size() );
    }

    /** Clear and refill produces the same size, height, and root. */
    @Test
    public void testInteraction_clearAndRefill_consistentState()
    {
        populateTree();
        tree.clear();
        populateTree();
        assertEquals( 7, tree.size() );
        assertEquals( 3, tree.getHeight() );
        assertEquals( "M", tree.getRoot().getElement() );
    }

    /** In-order traversal after removeMin starts from the new minimum. */
    @Test
    public void testInteraction_inorderAfterRemoveMin_startsFromNewMin()
    {
        populateTree();
        tree.removeMin();                       // removes "C"; new min is "F"
        Iterator it = tree.inorderIterator();
        assertEquals( "F", it.next() );
    }

    /** In-order traversal after removeMax ends at the new maximum. */
    @Test
    public void testInteraction_inorderAfterRemoveMax_endsAtNewMax()
    {
        populateTree();
        tree.removeMax();                       // removes "W"; new max is "T"
        Iterator it = tree.inorderIterator();
        String last = null;
        while( it.hasNext() )
        {
            last = it.next();
        }
        assertEquals( "T", last );
    }

    /** All three iterators visit the same set of elements. */
    @Test
    public void testInteraction_allThreeIterators_visitSameElements()
    {
        populateTree();
        int inCount = 0, preCount = 0, postCount = 0;

        Iterator inIt = tree.inorderIterator();
        while( inIt.hasNext() ) { inIt.next(); inCount++; }

        Iterator preIt = tree.preorderIterator();
        while( preIt.hasNext() ) { preIt.next(); preCount++; }

        Iterator postIt = tree.postorderIterator();
        while( postIt.hasNext() ) { postIt.next(); postCount++; }

        assertEquals( 7, inCount );
        assertEquals( 7, preCount );
        assertEquals( 7, postCount );
    }
}