package linkedListImplementation;

import java.util.Arrays;
import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

/**
 * A singly linked list (SLL) implementation of the {@link ListADT} interface,
 * for use in CPRG 304 at SAIT.
 * <p>
 * This class stores {@link String} elements in a chain of {@link MySLLNode}
 * objects. The list maintains explicit {@code head} and {@code tail} references
 * for efficient access to both ends of the chain. Unlike a doubly linked list,
 * each node holds only a forward ({@code next}) reference, so traversal is
 * forward-only and insertions or removals at arbitrary positions require O(n)
 * traversal from the head. All index parameters are zero-based unless otherwise
 * noted.
 * </p>
 *
 * <p><strong>Design Note – String-Specific Implementation:</strong><br>
 * This class is intentionally designed to store {@link String} elements only.
 * A fully reusable, type-safe design would use Java generics (e.g.,
 * {@code SLL<E>}), allowing any object type to be stored in the list.
 * Generic programming is outside the scope of CPRG 304 and has therefore
 * been omitted. All element fields, parameters, and return types are fixed
 * as {@link String} throughout this class and its implementations.
 * </p>
 */
public class MySLL implements ListADT
{
    // -----------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------

    /**
     * Reference to the first {@link MySLLNode} in the list, or {@code null}
     * if the list is empty.
     */
    private MySLLNode head;

    /**
     * Reference to the last {@link MySLLNode} in the list, or {@code null}
     * if the list is empty.
     */
    private MySLLNode tail;

    /** The number of {@link String} elements currently stored in the list. */
    private int size;


    // -----------------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------------

    /**
     * Constructs a new, empty {@link MySLL}.
     * <p>
     * After construction, {@link #isEmpty()} returns {@code true},
     * {@link #size()} returns {@code 0}, and both {@code head} and
     * {@code tail} references are {@code null}.
     * </p>
     */
    public MySLL()
    {
        head = tail = null;
        size = 0;
    }


    // -----------------------------------------------------------------------
    // ListADT Interface Methods
    // -----------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        return size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Sets both {@code head} and {@code tail} to {@code null} and resets
     * the size counter to {@code 0}. All previously held nodes become
     * eligible for garbage collection.
     * </p>
     */
    @Override
    public void clear()
    {
        head = tail = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Handles five structural cases to maintain correct {@code head},
     * {@code tail}, and {@code next} references after insertion:
     * </p>
     * <ul>
     *   <li><strong>Empty list:</strong> the new node becomes both head and
     *       tail.</li>
     *   <li><strong>Single-element list, index 0:</strong> new node becomes
     *       head with the existing node as its successor.</li>
     *   <li><strong>index 0 (multi-element list):</strong> new node prepends
     *       before the current head; current head becomes its successor.</li>
     *   <li><strong>index == size:</strong> new node appends after the current
     *       tail; current tail's next is updated.</li>
     *   <li><strong>Middle position:</strong> the new node is linked between
     *       the nodes at {@code index - 1} and {@code index} by updating the
     *       predecessor's {@code next} reference.</li>
     * </ul>
     */
    @Override
    public boolean add( int index, String toAdd )
            throws NullPointerException, IndexOutOfBoundsException
    {
        boolean status = false;

        if( toAdd == null )
        {
            throw new NullPointerException( "Cannot store a null element." );
        }
        if( index < 0 || index > size )
        {
            throw new IndexOutOfBoundsException( "Index outside bounds: " + index );
        }

        if( !isEmpty() )
        {
            if( size == 1 && index == 0 )
            {
                head = new MySLLNode( toAdd, tail );
                size++;
                status = true;
            }
            else if( index == 0 )
            {
                MySLLNode next = head;
                head = new MySLLNode( toAdd, next );
                size++;
                status = true;
            }
            else if( index == size )
            {
                MySLLNode before = tail;
                tail = new MySLLNode( toAdd, null );
                before.setNext( tail );
                size++;
                status = true;
            }
            else
            {
                MySLLNode before = head;
                MySLLNode after = head.getNext();

                for( int i = 0; i < index - 1; i++ )
                {
                    before = before.getNext();
                    after = after.getNext();
                }

                MySLLNode insert = new MySLLNode( toAdd, after );
                before.setNext( insert );
                size++;
                status = true;
            }
        }
        else
        {
            head = tail = new MySLLNode( toAdd, null );
            size++;
            status = true;
        }

        return status;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Delegates to {@link #add(int, String)} using {@link #size()} as the
     * index, effectively appending the element to the end of the list.
     * </p>
     */
    @Override
    public boolean add( String toAdd ) throws NullPointerException
    {
        return add( size, toAdd );
    }

    /**
     * {@inheritDoc}
     * <p>
     * Iterates over the provided list using index-based access and appends
     * each element to the end of this list via {@link #add(String)}.
     * </p>
     */
    @Override
    public boolean addAll( ListADT toAdd ) throws NullPointerException
    {
        if( toAdd == null )
        {
            throw new NullPointerException( "Cannot add a null list." );
        }
        for( int i = 0; i < toAdd.size(); i++ )
        {
            this.add( toAdd.get( i ) );
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Traverses the list from {@code head}, following {@code next} references
     * until the node at {@code index} is reached. Access is O(n) due to the
     * linked structure.
     * </p>
     */
    @Override
    public String get( int index ) throws IndexOutOfBoundsException
    {
        if( index < 0 || index >= size() || size() == 0 )
        {
            throw new IndexOutOfBoundsException( "Index out of bounds: " + index );
        }

        MySLLNode curr = head;
        for( int i = 0; i < index; i++ )
        {
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Handles two structural cases after removal:
     * </p>
     * <ul>
     *   <li><strong>index 0:</strong> head advances to its successor; the
     *       removed node is returned.</li>
     *   <li><strong>Any other index:</strong> the list is traversed to the
     *       predecessor of the target node; the predecessor's {@code next}
     *       reference is updated to bypass the removed node.</li>
     * </ul>
     */
    @Override
    public String remove( int index ) throws IndexOutOfBoundsException
    {
        if( index < 0 || index >= size() )
        {
            throw new IndexOutOfBoundsException( "Index out of bounds: " + index );
        }

        if( index == 0 )
        {
            MySLLNode del = head;
            head = head.getNext();
            if( head == null )
            {
                tail = null;
            }
            size--;
            return del.getElement();
        }

        MySLLNode del = head.getNext();
        MySLLNode prev = head;

        for( int i = 1; i < index; i++ )
        {
            del = del.getNext();
            prev = prev.getNext();
        }

        String removed = del.getElement();
        prev.setNext( del.getNext() );

        if( del.getNext() == null )
        {
            tail = prev;
        }

        size--;
        return removed;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Traverses the list from {@code head} using {@link String#equals(Object)}
     * to locate the first matching node, records its index, then delegates to
     * {@link #remove(int)} to perform the actual removal. Returns {@code null}
     * if the element is not found.
     * </p>
     */
    @Override
    public String remove( String toRemove ) throws NullPointerException
    {
        if( toRemove == null )
        {
            throw new NullPointerException( "Cannot remove a null element." );
        }

        MySLLNode curr = head;
        int index = -1;

        for( int i = 0; i < size; i++ )
        {
            if( curr.getElement().equals( toRemove ) )
            {
                index = i;
                break;
            }
            curr = curr.getNext();
        }

        if( index > -1 )
        {
            return remove( index );
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Traverses the list from {@code head} to the node at {@code index},
     * stores the existing element, replaces it with {@code toChange}, and
     * returns the previous value. The size and structure of the list are
     * unaffected.
     * </p>
     */
    @Override
    public String set( int index, String toChange )
            throws NullPointerException, IndexOutOfBoundsException
    {
        if( toChange == null )
        {
            throw new NullPointerException( "Cannot store a null element." );
        }
        if( index < 0 || index >= size() )
        {
            throw new IndexOutOfBoundsException( "Index out of bounds: " + index );
        }

        MySLLNode curr = head;
        for( int i = 0; i < index; i++ )
        {
            curr = curr.getNext();
        }

        String old = curr.getElement();
        curr.setElement( toChange );
        return old;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Performs a linear scan of the list from {@code head} to {@code tail}
     * using {@link String#equals(Object)} for comparison. Returns
     * {@code false} immediately if the list is empty.
     * </p>
     */
    @Override
    public boolean contains( String toFind ) throws NullPointerException
    {
        if( toFind == null )
        {
            throw new NullPointerException( "Cannot search for a null element." );
        }
        if( isEmpty() )
        {
            return false;
        }

        MySLLNode curr = head;
        while( curr != null )
        {
            if( toFind.equals( curr.getElement() ) )
            {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Performs a linear scan of the list from {@code head} to {@code tail}
     * using {@link String#equals(Object)}, returning the zero-based index of
     * the first matching element. Returns {@code -1} if the element is not
     * found or if the list is empty.
     * </p>
     */
    @Override
    public int indexOf( String toFind ) throws NullPointerException
    {
        if( toFind == null )
        {
            throw new NullPointerException( "Cannot search for a null element." );
        }
        if( isEmpty() )
        {
            return -1;
        }

        MySLLNode curr = head;
        int index = 0;

        while( curr != null )
        {
            if( toFind.equals( curr.getElement() ) )
            {
                return index;
            }
            curr = curr.getNext();
            index++;
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Traverses the list from {@code head} to {@code tail}, copying each
     * node's {@link String} element into the provided array in order. If
     * {@code toHold} is smaller than {@link #size()}, a new {@link String}
     * array of the required size is allocated using
     * {@link Arrays#copyOf(Object[], int)} before elements are copied.
     * </p>
     */
    @Override
    public String[] toArray( String[] toHold ) throws NullPointerException
    {
        if( toHold == null )
        {
            throw new NullPointerException( "Cannot copy to a null array." );
        }
        if( toHold.length < size )
        {
            toHold = Arrays.copyOf( toHold, size );
        }

        MySLLNode curr = head;
        for( int i = 0; i < size; i++ )
        {
            toHold[ i ] = curr.getElement();
            curr = curr.getNext();
        }
        return toHold;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a new {@link SLLIterator} that operates on a deep copy of
     * the list's elements captured at the moment of this call. Subsequent
     * modifications to this list do not affect the returned iterator.
     * </p>
     */
    @Override
    public Iterator iterator()
    {
        return new SLLIterator();
    }


    // -----------------------------------------------------------------------
    // Inner Classes
    // -----------------------------------------------------------------------

    /**
     * A private inner class that implements the {@link Iterator} interface
     * for traversing the {@link String} elements of an {@link MySLL}.
     * <p>
     * On construction, a deep copy of the list's current elements is captured
     * into an internal {@link String} array by traversing the linked chain
     * from {@code head} to {@code tail}. All subsequent calls to
     * {@link #hasNext()} and {@link #next()} operate exclusively on this
     * snapshot, making the iterator independent of any structural changes
     * made to the original list after the iterator was created.
     * </p>
     */
    private class SLLIterator implements Iterator
    {
        /** Deep copy of the list's elements at the time this iterator was created. */
        private String[] copyOfElements;

        /** Current position of the iterator cursor within {@code copyOfElements}. */
        private int pos;

        /**
         * Constructs an {@link SLLIterator} by capturing a deep copy of the
         * current elements in the enclosing {@link MySLL}.
         * <p>
         * Traverses the list from {@code head} to {@code tail}, storing each
         * node's {@link String} element into the snapshot array in order.
         * </p>
         */
        public SLLIterator()
        {
            copyOfElements = new String[ size ];
            MySLLNode curr = head;

            for( int i = 0; i < size; i++ )
            {
                copyOfElements[ i ] = curr.getElement();
                curr = curr.getNext();
            }
            pos = 0;
        }

        /**
         * Returns {@code true} if the iterator has more {@link String} elements
         * remaining in the snapshot.
         *
         * @return {@code true} if at least one more element exists in the
         *         snapshot; {@code false} otherwise
         */
        @Override
        public boolean hasNext()
        {
            return pos < copyOfElements.length;
        }

        /**
         * Returns the next {@link String} element in the traversal sequence
         * and advances the iterator position by one.
         *
         * @return the next {@link String} element in the snapshot
         * @throws NoSuchElementException if no more elements remain in the
         *                                snapshot
         */
        @Override
        public String next() throws NoSuchElementException
        {
            if( !hasNext() )
            {
                throw new NoSuchElementException( "No more elements in the iteration." );
            }
            return copyOfElements[ pos++ ];
        }
    }
}