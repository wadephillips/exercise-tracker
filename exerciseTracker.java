/**
 * main class for exercise tracker application
 */
import java.util.Date;
import java.util.Scanner;
import java.text.*;
import java.sql.*;

class exerciseTracker {

	public static void main(String[] args) {
		// what info do we need to collect?
		// date
		// type of exercise
		// lenght of time
		// System.out.println(args[0]);
		// System.exit(0);
		if (args.length > 0) {
			for (String s : args) {
				if ( s.equals("f")){
					System.out.println("Fuck it I sat on the couch.");
					System.exit(0);
				} else if (s.equals("n")) {
					addExerciseType();
					System.exit(0);
				} else if (s.equals("r")) {
					int[] report = new int[3];
					report = getReports();
					System.out.println(report[2]);
					System.exit(0);
				}

				/*if (s.equals("d")) {
					Date date = new Date();
				} else {
					Date date = new Date();
				}*/

			}
		} 
		
		int exerciseType = 0;
		Date date = new Date();
		SimpleDateFormat fdate = new SimpleDateFormat ("yyyy-MM-dd");
		double exerciseTime = 0;

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Select exercise type:");

		Connection c = null;
		
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:exerciseTracker.db");
	      c.setAutoCommit(false);
	      // System.out.println("Opened database successfully");

	      Statement stmt = c.createStatement();
	      
	      ResultSet countResult = stmt.executeQuery("select count(`id`) as id FROM exercise;");
	      int exerciseCount = countResult.getInt("id");
	      String[] exerciseNames;
	      exerciseNames = new String[exerciseCount + 1];

	      ResultSet rs = stmt.executeQuery("SELECT * FROM exercise;");
	      while ( rs.next() ) {
	        int id = rs.getInt("id");
	        String exerciseName = rs.getString("exercise");
	        exerciseNames[id] = exerciseName;
	        System.out.println(id + ") " + exerciseName);
	      }
	    exerciseType = keyboard.nextInt();

		System.out.println("How many minutes did you " + exerciseNames[exerciseType] + "?");
		exerciseTime = keyboard.nextDouble();

		String insertSql = "INSERT INTO exercise_log ('exercise_id', 'date', 'duration') VALUES (" + exerciseType + ", '" + fdate.format(date) + "', " + exerciseTime + ");"; 
		stmt.executeUpdate(insertSql);
		stmt.close();
		c.commit();
		c.close();

		String activity = exerciseNames[exerciseType];
		if (exerciseType == 3) {
			activity += " ride" ;
		}

		System.out.println("On " + fdate.format(date) + " you went for a " + activity + 
			", lasting for " + exerciseTime + " minutes");

	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(1);
	    }
	    int[] reports;
	    reports = new int[3];
	    reports = getReports();
		
	}

	private static void addExerciseType(){
		// System.out.println("You called the addExerciseType method.");
		Connection c = null;
		try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:exerciseTracker.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      String exercise = "";
	      Scanner keyboard = new Scanner(System.in);

	      System.out.println("What exercise activity would you like to add?");
	      exercise = keyboard.next();

	      String sql = "INSERT INTO exercise ('exercise') VALUES ('" + exercise + "');";

	      Statement stmt = c.createStatement();
	      stmt.executeUpdate(sql);
		  stmt.close();
		  c.commit();
		  c.close();

	  	} catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(1);
	    }



	}

	private static Connection getDbConnection() {
		// need to refactor other connection requests to use this.
		Connection c = null;
		try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:exerciseTracker.db");
	      c.setAutoCommit(false);
	      // System.out.println("REOpened database successfully");

	      } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(1);
	    }
	    return c;
	}

	private static int[] getReports() {
		//Does this even need to return the report?  Could it just print the info?
		//
		// what do I want to know
		// Total minutes of exercise
		// per exercise - you have gone on x activity name for a total of y minutes
		int[] report;
		report = new int[3];
		Connection c = null;
		c = getDbConnection();
		try { 
		  Statement stmt = c.createStatement();
		  String totalTimeSql = "SELECT SUM(duration) as total_minutes FROM exercise_log;";

		  ResultSet totalTime = stmt.executeQuery(totalTimeSql);
		  report[0] = totalTime.getInt("total_minutes");
		  System.out.println("You have exercised for a total of " + report[0] + " minutes.");
		  /*report[1] = 2;
		  report[2] = 42;*/
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      	System.exit(1);
		}
		return report;
	}
}