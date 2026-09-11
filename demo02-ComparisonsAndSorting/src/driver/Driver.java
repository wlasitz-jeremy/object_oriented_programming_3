package driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import domain.Student;
import external.GradeCompare;
import external.NameCompare;
import utilities.KittySort;

/**
 * Driver class demonstrating multiple sorting techniques in Java.
 * <p>
 * This program illustrates how to sort {@link Student} objects using:
 * </p>
 * <ul>
 *   <li>Natural ordering via {@link Comparable}</li>
 *   <li>Custom ordering via {@link Comparator}</li>
 *   <li>{@code Collections.sort()} for lists</li>
 *   <li>{@code Arrays.sort()} for arrays</li>
 *   <li>A custom "bogosort"-style algorithm ({@link KittySort})</li>
 * </ul>
 * <p>
 * Students are sorted based on ID, name, and grade to demonstrate different
 * sorting strategies.
 * </p>
 */
public class Driver
{

    /**
     * Entry point of the program.
     * <p>
     * Creates a list of {@link Student} objects and demonstrates various
     * sorting approaches using both lists and arrays.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main( String[] args )
    {
        // List of students used for sorting demonstrations
        List<Student> studs = new ArrayList<>();

        studs.add( new Student( "Kitty", 111, 87.5 ) );
        studs.add( new Student( "Maryam", 222, 92.6 ) );
        studs.add( new Student( "Aaron", 333, 73.8 ) );
        studs.add( new Student( "John", 444, 35.2 ) );
        studs.add( new Student( "Nicole", 555, 99.9 ) );

        // Sort using Comparable (by ID)
        System.out.println( "Sort by ID: " );
        Collections.sort( studs );
        for( Student s : studs )
        {
            System.out.println( "\t" + s.getName() + " " + s.getID() + " " + s.getGrade() );
        }

        // Sort using Comparator (by name)
        System.out.println( "Sort by name: " );
        NameCompare nc = new NameCompare();
        Collections.sort( studs, nc );
        for( Student s : studs )
        {
            System.out.println( "\t" + s.getName() + " " + s.getID() + " " + s.getGrade() );
        }

        // Sort using Comparator (by grade)
        System.out.println( "Sort by grade: " );
        GradeCompare gc = new GradeCompare();
        Collections.sort( studs, gc );
        for( Student s : studs )
        {
            System.out.println( "\t" + s.getName() + " " + s.getID() + " " + s.getGrade() );
        }

        // Convert ArrayList to array and sort using Arrays.sort (Comparable)
        System.out.println( "Sort by ID via Array: " );
        Object[] objArray = studs.toArray();
        Student[] studsArray = Arrays.copyOf( objArray, objArray.length, Student[].class );

        Arrays.sort( studsArray );

        for( int i = 0; i < studsArray.length; i++ )
        {
            System.out.println(
                "\t" + studsArray[i].getName() + " " +
                studsArray[i].getID() + " " +
                studsArray[i].getGrade()
            );
        }

        // Custom sort using KittySort (Comparator by name)
        System.out.println( "Sort by name via Array with custom algorithm: " );
        KittySort.someSort( studsArray, nc );

        for( int i = 0; i < studsArray.length; i++ )
        {
            System.out.println(
                "\t" + studsArray[i].getName() + " " +
                studsArray[i].getID() + " " +
                studsArray[i].getGrade()
            );
        }

        // Custom sort using KittySort (Comparable by ID)
        System.out.println( "Sort by ID via Array with custom algorithm: " );
        KittySort.someSort( studsArray );

        for( int i = 0; i < studsArray.length; i++ )
        {
            System.out.println(
                "\t" + studsArray[i].getName() + " " +
                studsArray[i].getID() + " " +
                studsArray[i].getGrade()
            );
        }
    }
}