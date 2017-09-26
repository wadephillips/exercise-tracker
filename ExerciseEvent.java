import java.util.Date;

/**
 * Created by wadelp on 12/4/16.
 */
public class ExerciseEvent implements ExerciseEventInterface {

    private Date date;
    private Double length;
    private ExerciseInterface exerciseType;



    public ExerciseEvent() {
    }

    /**
     * Set the date of the exercise event
     *
     * @param date
     */
    public void setDate(Date date) {

    }

    /**
     * Set the lenght of the exercise event
     *
     * @param length
     */
    public void setLength(double length) {

    }

    /**
     * Set the type of exercise for this event
     *
     * @param exerciseType The exercise type id
     */
    public void setExerciseType(ExerciseInterface exerciseType) {

    }

    /**
     * Get the date of the exercise event
     *
     * @return the date of event
     */
    public Date getDate() {
        return null;
    }

    /**
     * Get the lenght of the exercise event
     *
     * @return the lenght of exercise
     */
    public double getLength() {
        return 0;
    }

    /**
     * Get the type of exercise for this event
     *
     * @return id of the exercise type for this event
     */
    public ExerciseInterface getExerciseType() {
        return null;
    }

    /**
     * Save the exercise event
     *
     * @return
     */
    public boolean save() {
        return false;
    }
}
