package external;

import java.util.Comparator;

import domain.Student;

/**
 * Comparator used to compare {@link Student} objects based on their grades.
 * <p>
 * This provides an alternative ordering to the natural ordering defined in
 * {@link Student#compareTo(Student)}.
 * </p>
 */
public class GradeCompare implements Comparator<Student>
{
	/**
	 * Compares two students based on their grade values.
	 *
	 * @param stud1 the first student
	 * @param stud2 the second student
	 * @return a positive value if stud1 has a higher grade,
	 *         a negative value if stud1 has a lower grade,
	 *         or zero if both grades are equal
	 */
	@Override
	public int compare( Student stud1, Student stud2 )
	{
		if( stud1.getGrade() > stud2.getGrade() )
		{
			return 100; // 100 is also a positive integer!
		}
		else if( stud1.getGrade() < stud2.getGrade() )
		{
			return -100; // -100 is also a negative integer!
		}
		else
		{
			return 0;
		}
	}
}