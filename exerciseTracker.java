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
				}

			}
		}
		
		// else {
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
		// }
		/*System.out.println("1) run");
		System.out.println("2) hike");
		System.out.println("3) bike");*/
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
}