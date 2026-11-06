package implementation;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A lightweight wrapper around {@link ArrayList} that provides a simplified
 * list abstraction and supports iteration.
 * <p>
 * This class exposes common list operations such as adding, removing,
 * accessing, and updating elements while delegating all storage, indexing,
 * and resizing behavior to an internal {@code ArrayList}.
 * </p>
 * <p>
 * Unless otherwise stated, index-based operations follow the same rules
 * and exception behavior as {@link ArrayList}.
 * </p>
 */
public class MyAList implements Iterable<String>
{
	
    /** Internal storage for list elements. */
    private ArrayList<String> list;
    
    /**
     * Constructs an empty list with an initial size of zero.
     */
    public MyAList()
    {
        this.list = new ArrayList<>();
    }

    /**
     * Returns the element at the specified index.
     *
     * @param index the position of the element to retrieve
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public String get(int index)
    {
        return list.get(index);
    }
    
    /**
     * Returns an iterator over the elements in this list.
     * <p>
     * The returned iterator reflects the same ordering and fail-fast behavior
     * as an {@link ArrayList} iterator.
     * </p>
     *
     * @return an iterator over the elements in iteration order
     */
    @Override
    public Iterator<String> iterator()
    {
        return list.iterator();
    }
    
    public void addFirst(String item) {
    	list.add(0, item);
    }
    
    public void addLast(String item) {
    	list.add(item);
    	
    }
    
    public String getFirst() {
		return list.get(0);
    }
    
    public String getLast() {
		return list.get(size() - 1);
    }
    
    public String remove(int index) {
		return list.remove(index);
    }
    
    public String removeFirst() {
		return list.remove(0);
    }
    
    public String removeLast() {
		return list.remove(size() - 1);
    }
    
    public String set(int index, String item) {
		return list.set(index, item);
    }
    
    public int size() {
		return list.size();
    	
    }
    
    public int lastValidIndex() {
		return size() -1;
    	
    }
}