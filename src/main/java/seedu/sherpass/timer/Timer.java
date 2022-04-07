package seedu.sherpass.timer;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.TimerConstant.ONE_HOUR;
import static seedu.sherpass.constant.TimerConstant.ONE_MINUTE;

public abstract class Timer extends Thread {
    protected volatile boolean isTimerRunning = false;
    protected volatile boolean isTimerPaused = false;
    protected boolean forcedStop = false;
    protected static Ui ui;
    protected static TaskList taskList;

    /**
     * Creates a constructor for timer. Initialises the parameters needed.
     *
     * @param ui UI
     */
    public Timer(TaskList taskList, Ui ui) {
        Timer.ui = ui;
        Timer.taskList = taskList;
    }

    protected static String convertTimeToString(int time) {
        long hour;
        long minute;
        long second;
        if ((time / ONE_HOUR) > 0) {
            hour = time / ONE_HOUR;
            minute = (time - (hour * ONE_HOUR)) / ONE_MINUTE;
            second = time - (hour * ONE_HOUR) - (minute * ONE_MINUTE);
            String zeroStringHour = (hour > 9) ? EMPTY_STRING : "0";
            String zeroStringMinute = (minute > 9) ? EMPTY_STRING : "0";
            String zeroStringSecond = (second > 9) ? EMPTY_STRING : "0";
            return zeroStringHour + hour + " hour(s) " + zeroStringMinute + minute
                    + " minute(s) " + zeroStringSecond + second + " second(s)";
        } else if ((time / ONE_MINUTE) > 0) {
            minute = time / ONE_MINUTE;
            second = time - (minute * ONE_MINUTE);
            String zeroStringMinute = (minute > 9) ? EMPTY_STRING : "0";
            String zeroStringSecond = (second > 9) ? EMPTY_STRING : "0";
            return zeroStringMinute + minute + " minute(s) " + zeroStringSecond + second + " second(s)";
        } else {
            second = time;
            String zeroStringSecond = (second > 9) ? EMPTY_STRING : "0";
            return zeroStringSecond + second + " second(s)";
        }
    }

    protected abstract void update();

    protected abstract void printTimerStart();

    public abstract void resumeTimer();

    public abstract void stopTimer();

    public boolean getIsStopped() {
        return forcedStop;
    }

    public abstract void pauseTimer();

    /**
     * Method causes the thread which the timer is running on to wait when it is paused, until the user resumes the
     * timer.
     */
    protected void waitForTimerToResume() throws InterruptedException {
        synchronized (this) {
            while (isTimerPaused) {
                wait();
            }
        }
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public boolean isTimerPaused() {
        return isTimerPaused;
    }


}
