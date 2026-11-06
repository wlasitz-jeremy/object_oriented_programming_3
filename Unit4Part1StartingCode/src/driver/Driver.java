package driver;

import java.util.Date;

import implementation.MyAList;

/**
 * Demonstration driver for the {@link MyAList} class.
 * <p>
 * This program exercises the core operations provided by {@code MyAList}
 * in order to verify basic functionality and illustrate expected behavior.
 * It demonstrates:
 * </p>
 * <ul>
 *   <li>Adding elements to the end of the list</li>
 *   <li>Inserting elements at the front of the list</li>
 *   <li>Iterating over list contents using the enhanced for-loop</li>
 *   <li>Accessing elements by index</li>
 *   <li>Removing elements using index-based and convenience methods</li>
 *   <li>Updating existing elements</li>
 *   <li>Querying list size and valid index bounds</li>
 * </ul>
 * <p>
 * This class is intended for manual execution and observation of output,
 * not for automated unit testing.
 * </p>
 */
public class Driver
{

    /**
     * Program entry point.
     * <p>
     * Creates a {@code MyAList<String>} instance and performs a sequence of
     * list operations while printing results to standard output. The output
     * allows the developer to confirm correctness of each operation and
     * observe how the list state changes over time.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        // Create a generic list of strings for demonstration
        MyAList aList = new MyAList();

        System.out.println("Jeremy Wlasitz " + new Date());

        System.out.println("\nSize Before Add: " + aList.size());

        // Add elements to the list
        aList.addFirst("Charlie");
        aList.addFirst("Bob");
        aList.addFirst("Alice");

        // Add elements to the end of  the list
        aList.addLast("David");
        aList.addLast("Eve");

        // Display updated list
        for (String name : aList)
        {
            System.out.println("List after addLast: " + name);
        }

        // Access the first element
        System.out.println("Get 2: " + aList.get(2));
        
        // Access the first element
        System.out.println("GetFirst: " + aList.getFirst());

        // Access the last element
        System.out.println("GetLast: " + aList.getLast());

        // Remove the first element by index
        System.out.println("Remove 1: " + aList.remove(2));

        // Remove the first element using the convenience method
        System.out.println("RemoveFirst: " + aList.removeFirst());
        
        // Remove the last element using the convenience method
        System.out.println("RemoveLast: " + aList.removeLast());

        // Update an element at a specific index
        System.out.println("Set: " + aList.set(0, "Bub"));

        // Display the current size of the list
        System.out.println("Size: " + aList.size());

        // Display the last valid index in the list
        System.out.println("LastValidIndex: " + aList.lastValidIndex());
        
        // Display list contents
        for (String name : aList)
        {
        	System.out.println("List: " + name);
        }
    }
}