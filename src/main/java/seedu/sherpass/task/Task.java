package seedu.sherpass.task;

import seedu.sherpass.enums.Frequency;

import java.time.LocalDateTime;

import static seedu.sherpass.constant.DateAndTimeFormat.outputDateOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.outputWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.outputWithoutTimeFormat;
import static seedu.sherpass.constant.Message.EMPTY_STRING;

public class Task {
    protected String description;
    protected int identifier;
    protected boolean isDone;
    protected LocalDateTime byDate;
    protected LocalDateTime doOnStartDateTime;
    protected LocalDateTime doOnEndDateTime;
    protected Frequency repeatFrequency;

    protected int index;

    /**
     * Creates a constructor for the parent class of tasks, 'Task'.
     * Accepts only task description
     *
     * @param identifier  Identity number of a repeated task.
     * @param description Description of task.
     */
    public Task(int identifier, String description, LocalDateTime byDate,
                LocalDateTime doOnStartDateTime, LocalDateTime doOnEndDateTime,
                Frequency repeatFrequency) {
        this.identifier = identifier;
        this.description = description;
        this.byDate = byDate;
        this.doOnStartDateTime = doOnStartDateTime;
        this.doOnEndDateTime = doOnEndDateTime;
        this.isDone = false;
        this.repeatFrequency = repeatFrequency;
        this.index = 0;
    }

    /**
     * Creates a constructor for the parent class of tasks, 'Task'.
     * Accepts only task description
     *
     * @param identifier  Identity number of a repeated task.
     * @param description Description of task.
     */
    public Task(int identifier, String description, LocalDateTime byDate,
                LocalDateTime doOnStartDateTime, LocalDateTime doOnEndDateTime,
                Frequency repeatFrequency, int index) {
        this.identifier = identifier;
        this.description = description;
        this.byDate = byDate;
        this.doOnStartDateTime = doOnStartDateTime;
        this.doOnEndDateTime = doOnEndDateTime;
        this.isDone = false;
        this.repeatFrequency = repeatFrequency;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Returns the time range for when the task is taking place.
     * Format of time is in 24 hours.
     *
     * @return Returns a range of time when the task occurs.
     */
    public String getTimePeriod() {
        return doOnStartDateTime.toLocalTime().toString()
                + " - " + doOnEndDateTime.toLocalTime().toString();
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns String version of mark status.
     *
     * @return X if task has been marked. White space otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns task date. Parent class is created
     * as a template for child classes to perform its own
     * respective functions, i.e. each task date returned is
     * different for each task type.
     *
     * @return White space.
     */
    public LocalDateTime getByDate() {
        return byDate;
    }

    public LocalDateTime getDoOnStartDateTime() {
        return doOnStartDateTime;
    }

    /**
     * Returns the by date in String format.
     *
     * @return Returns if byDate contains a parsed date. Otherwise, returns a blank string (no whitespace).
     */
    public String getByDateString() {
        if (byDate != null) {
            return byDate.format(outputWithoutTimeFormat);
        }
        return EMPTY_STRING;
    }

    /**
     * Returns the do on date in String format.
     *
     * @return Returns if doOnDate contains a parsed date. Otherwise, returns a blank string (no whitespace).
     */
    public String getDoOnDateString(boolean isDateOnly) {
        if (doOnStartDateTime != null) {
            return (isDateOnly) ? doOnStartDateTime.format(outputDateOnlyFormat)
                    : doOnStartDateTime.format(outputWithTimeFormat);
        }
        return EMPTY_STRING;
    }

    /**
     * Returns a string version of the task content.
     * Content includes mark status, task type and description.
     * For ease of printing the task.
     *
     * @return Task content.
     */
    @Override
    public String toString() {
        String result = index + ". [" + this.getStatusIcon() + "] " + this.getDescription();
        if (this.byDate != null) {
            result += " (by: " + getByDateString() + ")";
        }
        if (this.doOnStartDateTime != null) {
            result += " (to do on: " + getDoOnDateString(false) + " - "
                    + doOnEndDateTime.toLocalTime().toString() + ")";
        }
        return result;
    }

    public void setTaskDescription(String taskDescription) {
        this.description = taskDescription;
    }

    public void setByDate(LocalDateTime byDate) {
        this.byDate = byDate;
    }

    public void setDoOnStartDateTime(LocalDateTime doOnStartDateTime) {
        this.doOnStartDateTime = doOnStartDateTime;
    }

    public void setDoOnEndDateTime(LocalDateTime doOnEndDateTime) {
        this.doOnEndDateTime = doOnEndDateTime;
    }

    public LocalDateTime getDoOnEndDateTime() {
        return doOnEndDateTime;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public Frequency getRepeatFrequency() {
        return repeatFrequency;
    }

    public void setRepeatFrequency(Frequency repeatFrequency) {
        this.repeatFrequency = repeatFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task t = (Task) o;
        return description.equals(t.getDescription())
                && doOnStartDateTime.equals(t.getDoOnStartDateTime())
                && doOnEndDateTime.equals(t.getDoOnEndDateTime())
                && repeatFrequency.equals(t.getRepeatFrequency())
                && identifier == (t.getIdentifier())
                && byDate.equals(t.getByDate());
    }
}
