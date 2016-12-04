/**
 * Created by wadelp on 12/4/16.
 */
public interface ExerciseInterface {

    /**
     * Adds an exercise type
     * @return
     */
    public boolean save();


    /**
     * Make a change to the exercise type
     * @return
     */
    public ExerciseInterface update();

    /**
     * Get the exercise type
     * @return
     */
    public  ExerciseInterface show();

    /**
     * Remove an exercise type
     * @return
     */
    public ExerciseInterface delete();

}
