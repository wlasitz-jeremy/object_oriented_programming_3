package testSLL;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import linkedListImplementation.MySLLNode;

/**
 * JUnit 4 test suite for the {@link MySLLNode} class.
 * <p>
 * This test suite verifies the correct behaviour of singly linked list nodes,
 * including construction via both available constructors, element access and
 * mutation via getters and setters, next-pointer access and mutation, and
 * basic {@link Object#toString()} output.
 * </p>
 *
 * <p>The following two-node linked structure is established before each test:</p>
 * <pre>
 *   one (element="Alpha") --next--&gt; two (element="Beta") --next--&gt; null
 * </pre>
 *
 * @author kitty
 */
public class MySLLNodeTest
{
    // -----------------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------------

    /**
     * A node holding element {@code "Alpha"} with its {@code next} reference
     * pointing to {@link #two}. Created fresh before each test.
     */
    private MySLLNode one;

    /**
     * A node holding element {@code "Beta"} with no {@code next} reference
     * ({@code null}). Created fresh before each test.
     */
    private MySLLNode two;


    // -----------------------------------------------------------------------
    // Fixtures
    // -----------------------------------------------------------------------

    /**
     * Creates a fresh two-node chain before each test method.
     * <p>
     * {@link #two} is constructed first (element {@code "Beta"}, no successor),
     * then {@link #one} is constructed with element {@code "Alpha"} and
     * {@link #two} as its successor, forming the chain:
     * {@code one -> two -> null}.
     * </p>
     *
     * @throws Exception if an unexpected error occurs during setup
     */
    @Before
    public void setUp() throws Exception
    {
        two = new MySLLNode( "Beta" );
        one = new MySLLNode( "Alpha", two );
    }

    /**
     * Nullifies both node references after each test method to release
     * resources and prevent state leakage between tests.
     *
     * @throws Exception if an unexpected error occurs during teardown
     */
    @After
    public void tearDown() throws Exception
    {
        one = null;
        two = null;
    }


    // -----------------------------------------------------------------------
    // Constructor Tests
    // -----------------------------------------------------------------------

    /**
     * Tests the two-argument constructor {@code MySLLNode(String element, MySLLNode next)}.
     * <p>
     * Verifies that a node constructed with both a {@link String} element and
     * an explicit successor reference is successfully created and is not
     * {@code null}.
     * </p>
     */
    @Test
    public void testMySLLNode_twoArgConstructor_notNull()
    {
        assertNotNull( "Node constructed with element and next reference should not be null.",
                one );
    }

    /**
     * Tests the single-argument constructor {@code MySLLNode(String element)}.
     * <p>
     * Verifies that a node constructed with only a {@link String} element
     * (no successor reference) is successfully created and is not {@code null}.
     * </p>
     */
    @Test
    public void testMySLLNode_singleArgConstructor_notNull()
    {
        assertNotNull( "Node constructed with element only should not be null.",
                two );
    }

    /**
     * Tests that the single-argument constructor initializes {@code next}
     * to {@code null}.
     * <p>
     * Verifies that a node constructed without a successor has no forward
     * reference, confirming it is a tail node upon creation.
     * </p>
     */
    @Test
    public void testMySLLNode_singleArgConstructor_nextIsNull()
    {
        assertNull( "Node constructed with element only should have a null next reference.",
                two.getNext() );
    }


    // -----------------------------------------------------------------------
    // getElement() Tests
    // -----------------------------------------------------------------------

    /**
     * Tests {@link MySLLNode#getElement()} on {@link #one}, which was
     * constructed with the two-argument constructor.
     * <p>
     * Verifies that the {@link String} element stored at construction time
     * ({@code "Alpha"}) is returned correctly and unchanged.
     * </p>
     */
    @Test
    public void testGetElement_twoArgNode_returnsCorrectElement()
    {
        assertEquals( "getElement() should return the element set at construction.",
                "Alpha", one.getElement() );
    }

    /**
     * Tests {@link MySLLNode#getElement()} on {@link #two}, which was
     * constructed with the single-argument constructor.
     * <p>
     * Verifies that the {@link String} element stored at construction time
     * ({@code "Beta"}) is returned correctly and unchanged.
     * </p>
     */
    @Test
    public void testGetElement_singleArgNode_returnsCorrectElement()
    {
        assertEquals( "getElement() should return the element set at construction.",
                "Beta", two.getElement() );
    }


    // -----------------------------------------------------------------------
    // setElement() Tests
    // -----------------------------------------------------------------------

    /**
     * Tests {@link MySLLNode#setElement(String)} by replacing the existing
     * {@link String} element in {@link #one} with a new value.
     * <p>
     * Verifies that after calling {@code setElement("Gamma")}, a subsequent
     * call to {@link MySLLNode#getElement()} returns {@code "Gamma"} rather
     * than the original value {@code "Alpha"}.
     * </p>
     */
    @Test
    public void testSetElement_updatesElement()
    {
        one.setElement( "Gamma" );
        assertEquals( "getElement() should return the new value after setElement() is called.",
                "Gamma", one.getElement() );
    }

    /**
     * Tests that {@link MySLLNode#setElement(String)} does not affect the
     * {@code next} reference of the modified node.
     * <p>
     * Verifies that after updating the element of {@link #one}, its
     * successor reference still points to {@link #two}.
     * </p>
     */
    @Test
    public void testSetElement_doesNotAffectNextReference()
    {
        one.setElement( "Delta" );
        assertEquals( "setElement() should not modify the next reference of the node.",
                two, one.getNext() );
    }


    // -----------------------------------------------------------------------
    // getNext() Tests
    // -----------------------------------------------------------------------

    /**
     * Tests {@link MySLLNode#getNext()} on {@link #one}, which was constructed
     * with {@link #two} as its explicit successor.
     * <p>
     * Verifies that the reference returned by {@code getNext()} is the same
     * object as {@link #two}.
     * </p>
     */
    @Test
    public void testGetNext_returnsCorrectSuccessor()
    {
        assertEquals( "getNext() should return the successor node set at construction.",
                two, one.getNext() );
    }

    /**
     * Tests {@link MySLLNode#getNext()} on {@link #two}, which was constructed
     * with no successor reference.
     * <p>
     * Verifies that {@code getNext()} returns {@code null} for a tail node.
     * </p>
     */
    @Test
    public void testGetNext_tailNode_returnsNull()
    {
        assertNull( "getNext() should return null for a node with no successor.",
                two.getNext() );
    }


    // -----------------------------------------------------------------------
    // setNext() Tests
    // -----------------------------------------------------------------------

    /**
     * Tests {@link MySLLNode#setNext(MySLLNode)} by assigning a new successor
     * node to {@link #two}.
     * <p>
     * Verifies that after calling {@code setNext(three)}, a subsequent call
     * to {@link MySLLNode#getNext()} on {@link #two} returns the newly
     * assigned node rather than {@code null}.
     * </p>
     */
    @Test
    public void testSetNext_updatesSuccessor()
    {
        MySLLNode three = new MySLLNode( "Gamma" );
        two.setNext( three );
        assertEquals( "getNext() should return the new successor after setNext() is called.",
                three, two.getNext() );
    }

    /**
     * Tests {@link MySLLNode#setNext(MySLLNode)} by setting the successor of
     * {@link #one} to {@code null}, effectively making it a tail node.
     * <p>
     * Verifies that after calling {@code setNext(null)}, a subsequent call
     * to {@link MySLLNode#getNext()} returns {@code null}.
     * </p>
     */
    @Test
    public void testSetNext_toNull_makesNodeATailNode()
    {
        one.setNext( null );
        assertNull( "getNext() should return null after setNext(null) is called.",
                one.getNext() );
    }

    /**
     * Tests that {@link MySLLNode#setNext(MySLLNode)} does not affect the
     * {@link String} element stored in the modified node.
     * <p>
     * Verifies that after updating the successor of {@link #two}, its
     * element remains {@code "Beta"}.
     * </p>
     */
    @Test
    public void testSetNext_doesNotAffectElement()
    {
        two.setNext( new MySLLNode( "Gamma" ) );
        assertEquals( "setNext() should not modify the element stored in the node.",
                "Beta", two.getElement() );
    }


    // -----------------------------------------------------------------------
    // toString() Tests
    // -----------------------------------------------------------------------

    /**
     * Tests {@link Object#toString()} on {@link #one} to verify it produces
     * a non-{@code null}, non-empty {@link String} representation.
     * <p>
     * The exact format of the output is implementation-dependent; this test
     * confirms only that a valid string is produced. The result is also
     * printed to standard output for manual inspection.
     * </p>
     */
    @Test
    public void testToString_returnsNonNullNonEmptyString()
    {
        String result = one.toString();
        assertNotNull( "toString() should not return null.", result );
        assertFalse( "toString() should not return an empty string.", result.isEmpty() );
        System.out.println( one.toString() );
    }
}