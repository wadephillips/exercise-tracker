import java.util.Date;

/**
 * Created by wadelp on 12/4/16.
 */
public interface ExerciseEventInterface {

    /**
     * Set the date of the exercise event
     * @param date
     */
    public void setDate(Date date);

    /**
     * Set the lenght of the exercise event
     * @param length
     */
    public void setLength(double length);

    /**
     * Set the type of exercise for this event
     * @param typeId  The exercise type id
     */
    public void setExerciseType(int typeId);

    /**
     * Get the date of the exercise event
     * @return the date of event
     */
    public Date getDate();

    /**
     * Get the lenght of the exercise event
     * @return the lenght of exercise
     */
    public double getLength();

    /**
     * Get the type of exercise for this event
     * @return id of the exercise type for this event
     */
    public int getExerciseType();

    /**
     * Save the exercise event
     * @return
     */
    public boolean save();


}
