package driver;

import java.util.Date;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import testDictionary.DictionaryTest;

/**
 * Test driver used to execute JUnit test cases for the Dictionary implementation.
 * <p>
 * This class runs the {@link DictionaryUnitTest} suite using JUnitCore,
 * prints any test failures, and reports the overall test results.
 * </p>
 */
public class Driver 
{
	/**
	 * Main entry point for running unit tests.
	 *
	 * @param args command-line arguments (not used)
	 */
    public static void main(String[] args) 
    {
        // Run specific test classes
        Result result = JUnitCore.runClasses( DictionaryTest.class );

		System.out.println( "Jeremy Wlasitz! " + new Date() );
		
        // Print details of any failed tests
        for( Failure failure : result.getFailures() ) 
        {
            System.out.println( failure.toString() );
        }
        
        // Print summary if all tests passed
        if( result.wasSuccessful() )
        {
        	System.out.println( "Tests passed: " + result.getRunCount() );
        }
    }
}