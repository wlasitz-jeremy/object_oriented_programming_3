package external;

import java.util.Comparator;

import domain.Student;

/**
 * Comparator used to compare {@link Student} objects based on their names.
 * <p>
 * This provides an alternative ordering to the natural ordering defined in
 * {@link Student#compareTo(Student)}, which compares students by ID.
 * </p>
 */
public class NameCompare implements Comparator<Student>
{
	/**
	 * Compares two students based on their names in lexicographical order.
	 *
	 * @param stud1 the first student
	 * @param stud2 the second student
	 * @return a negative integer, zero, or a positive integer
	 *         as the first student's name is less than, equal to,
	 *         or greater than the second student's name
	 */
	@Override
	public int compare( Student stud1, Student stud2 )
	{
		// no need to reinvent the wheel, the String class already implements
		// the Comparable interface with the expected return values!
		return stud1.getName().compareTo( stud2.getName() );
	}
}