package utilities;

import java.util.Comparator;
import domain.Student;

/**
 * A deliberately inefficient and humorous sorting utility.
 * <p>
 * This class implements a randomized sorting approach that repeatedly shuffles
 * the array until it becomes sorted. It is commonly referred to as a 
 * "bogosort"-style algorithm due to its extreme inefficiency.
 * </p>
 * <p>
 * Sorting can be performed using either the natural ordering defined in
 * {@link Student#compareTo(Student)} or a custom {@link Comparator}.
 * </p>
 */
public final class KittySort
{
    /**
     * Continuously randomizes the array until it is sorted
     * using the natural ordering of {@code Student}.
     *
     * @param array the array of students to sort
     * @throws NullPointerException if the array is null
     */
    public static void someSort( Student[] array )
    {
        while( !isSorted( array, null ) )
        {
            randomize( array );
        }
    }
    
    /**
     * Continuously randomizes the array until it is sorted
     * using the provided comparator.
     *
     * @param array the array of students to sort
     * @param comp the comparator defining the desired order
     * @throws NullPointerException if the array or comparator is null
     */
    public static void someSort( Student[] array, Comparator<Student> comp )
    {
        while( !isSorted( array, comp ) )
        {
            randomize( array );
        }
    }

    /**
     * Randomly swaps elements within the array to produce a new arrangement.
     *
     * @param array the array to randomize
     */
    private static void randomize( Student[] array )
    {
        for( int i = 0; i < array.length; i++ )
        {
            swapElements( array, i, (int) Math.floor( Math.random() * ( i + 1 ) ) );
        }
    }

    /**
     * Swaps two elements in the array.
     *
     * @param array the array containing the elements
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private static void swapElements( Student[] array, int i, int j )
    {
        Student temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Determines whether the array is sorted.
     * <p>
     * If a comparator is provided, it is used to determine ordering.
     * Otherwise, the natural ordering of {@code Student} is used.
     * </p>
     *
     * @param array the array to check
     * @param comp the comparator to use, or {@code null} for natural ordering
     * @return {@code true} if the array is sorted; {@code false} otherwise
     */
    private static boolean isSorted( Student[] array, Comparator<Student> comp )
    {
        for( int i = 0; i < array.length - 1; i++ )
        {
            if( comp == null )
            {
                if( array[i].compareTo( array[i + 1] ) > 0 )
                {
                    return false;
                }
            }
            else
            {
                if( comp.compare( array[i], array[i + 1] ) > 0 )
                {
                    return false;
                }
            }
        }
        return true;
    }
}