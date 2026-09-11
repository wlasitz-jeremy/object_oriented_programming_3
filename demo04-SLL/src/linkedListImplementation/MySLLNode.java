package linkedListImplementation;

/**
 * A single node in a singly linked list, used as the building block for
 * singly linked list data structures in CPRG 304 at SAIT.
 * <p>
 * Each node stores a {@link String} element and a unidirectional {@code next}
 * reference pointing to the node immediately after it in the chain. Unlike a
 * doubly linked node, there is no predecessor reference — traversal is
 * forward-only. A {@code null} successor indicates that this node is the tail
 * of the list.
 * </p>
 *
 * <p>Example of a three-node chain:</p>
 * <pre>
 *   [A] --next--> [B] --next--> [C] --next--> null
 * </pre>
 *
 * <p><strong>Design Note – String-Specific Implementation:</strong><br>
 * This class is intentionally designed to store {@link String} elements only.
 * A fully reusable, type-safe design would use Java generics (e.g.,
 * {@code SLLNode<E>}), allowing any object type to be stored in the node.
 * Generic programming is outside the scope of CPRG 304 and has therefore
 * been omitted. All element fields, parameters, and return types are fixed
 * as {@link String} throughout this class and its usages.
 * </p>
 */
public class MySLLNode
{
    // -----------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------

    /** The {@link String} element stored in this node. */
    private String element;

    /**
     * Reference to the successor node immediately after this node in the
     * chain, or {@code null} if this node is the tail.
     */
    private MySLLNode next;


    // -----------------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------------

    /**
     * Constructs a new {@link MySLLNode} holding the specified {@link String}
     * element with an explicit successor reference.
     * <p>
     * Use this constructor when inserting a node into an existing chain.
     * The {@code next} parameter may be {@code null} to indicate that this
     * node is the tail of the list.
     * </p>
     *
     * @param element  the {@link String} element to store in this node
     * @param next     the {@link MySLLNode} immediately after this node in the
     *                 chain, or {@code null} if this node is the tail
     */
    public MySLLNode( String element, MySLLNode next )
    {
        this.element = element;
        this.next = next;
    }

    /**
     * Constructs a new {@link MySLLNode} holding the specified {@link String}
     * element with no successor.
     * <p>
     * The {@code next} reference is initialized to {@code null}, making
     * this node a tail node upon creation. Use this constructor when
     * appending a new node to the end of a list.
     * </p>
     *
     * @param element  the {@link String} element to store in this node
     */
    public MySLLNode( String element )
    {
        this.element = element;
        this.next = null;
    }


    // -----------------------------------------------------------------------
    // Getters and Setters
    // -----------------------------------------------------------------------

    /**
     * Returns the {@link String} element stored in this node.
     *
     * @return the {@link String} element held by this node; may be
     *         {@code null} if {@code null} was explicitly stored
     */
    public String getElement()
    {
        return this.element;
    }

    /**
     * Replaces the {@link String} element stored in this node with the
     * specified value.
     * <p>
     * The {@code next} reference of this node is unaffected by this call.
     * </p>
     *
     * @param element  the new {@link String} element to store in this node
     */
    public void setElement( String element )
    {
        this.element = element;
    }

    /**
     * Returns the successor of this node — the {@link MySLLNode} immediately
     * after this node in the singly linked chain.
     *
     * @return the successor {@link MySLLNode}, or {@code null} if this node
     *         is the tail of the list
     */
    public MySLLNode getNext()
    {
        return this.next;
    }

    /**
     * Sets the successor of this node to the specified {@link MySLLNode}.
     * <p>
     * Pass {@code null} to make this node the new tail of the list.
     * The {@link String} element stored in this node is unaffected by
     * this call.
     * </p>
     *
     * @param next  the {@link MySLLNode} to set as this node's successor,
     *              or {@code null} to indicate no successor
     */
    public void setNext( MySLLNode next )
    {
        this.next = next;
    }


    // -----------------------------------------------------------------------
    // Overridden Methods
    // -----------------------------------------------------------------------

    /**
     * Returns a {@link String} representation of this node.
     * <p>
     * The output includes the {@link String} element stored in this node
     * and a representation of the successor node. If the successor is
     * {@code null}, the string {@code "null"} is shown in its place.
     * Note that because the successor's {@code toString()} is called
     * recursively, this method will print the entire remaining chain
     * from this node to the tail.
     * </p>
     *
     * <p>Example output for a node holding {@code "A"} followed by a node
     * holding {@code "B"}:</p>
     * <pre>
     *   Node element: A, next: Node element: B, next: null
     * </pre>
     *
     * @return a {@link String} describing this node's element and its
     *         successor reference
     */
    @Override
    public String toString()
    {
        return "Node element: " + this.element + ", next: " + this.next;
    }
}