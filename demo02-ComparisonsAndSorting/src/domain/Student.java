package domain;

/**
 * Represents a student with a name, ID, and grade.
 * <p>
 * This class implements {@link Comparable} to provide a natural ordering
 * of Student objects based on their ID values.
 * </p>
 */
public class Student implements Comparable<Student>
{
	/** Student's name. */
	private String name;

	/** Student's unique ID. */
	private int ID;

	/** Student's grade value. */
	private double grade;

	/**
	 * Constructs a Student object with the specified details.
	 *
	 * @param name the student's name
	 * @param ID the student's ID
	 * @param grade the student's grade
	 */
	public Student( String name, int ID, double grade )
	{
		this.name = name;
		this.ID = ID;
		this.grade = grade;
	}

	/**
	 * Returns the student's name.
	 *
	 * @return the name of the student
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Returns the student's ID.
	 *
	 * @return the student ID
	 */
	public int getID()
	{
		return ID;
	}

	/**
	 * Returns the student's grade.
	 *
	 * @return the grade value
	 */
	public double getGrade()
	{
		return grade;
	}

	/**
	 * Compares this student to another student based on ID.
	 *
	 * @param that the student to compare against
	 * @return a negative integer, zero, or a positive integer
	 *         as this student's ID is less than, equal to,
	 *         or greater than the other student's ID
	 */
	@Override
	public int compareTo( Student that )
	{
		if( this.ID > that.ID )
		{
			return 1; // 1 is a fine positive integer!
		}
		else if( this.ID < that.ID )
		{
			return -1; // -1 is also a fine negative integer!
		}
		else
		{
			return 0;
		}
	}
}