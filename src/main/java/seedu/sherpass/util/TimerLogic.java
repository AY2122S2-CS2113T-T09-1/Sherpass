package seedu.sherpass.util;

import seedu.sherpass.command.Command;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.exception.InvalidTimeException;
import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.Message.ERROR_INVALID_TIMER_INPUT_MESSAGE;

public class TimerLogic {

    private static Ui ui;
    private static Timer timer;
    private static TaskList taskList;
    protected boolean isTimerInitialised = false;

    /**
     * Creates a constructor for TimerLogic.
     *
     * @param ui UI
     * @param taskList taskList
     */
    public TimerLogic(TaskList taskList, Ui ui) {
        TimerLogic.taskList = taskList;
        TimerLogic.ui = ui;
    }

    /**
     * Marks a task as done, as specified in parsedInput.
     * @param storage Storage.
     * @param parsedInput parsedInput.
     */
    public void markTask(Storage storage, String[] parsedInput) {
        if (isTimerPausedOrStopped()) {
            executeMark(storage, parsedInput);
        } else {
            ui.showToUser("You can't mark a task as done while timer is running!");
        }
    }

    private void executeMark(Storage storage, String[] parsedInput) {
        Command c = Parser.prepareMarkOrUnmark(parsedInput, MarkCommand.COMMAND_WORD, taskList);
        if (c != null) {
            c.execute(taskList, ui, storage);
            printAvailableCommands();
        }
    }

    private void printAvailableCommands() {
        if (!isTimerInitialised) {
            ui.showToUser("Would you like to start another timer, mark a task as done, "
                    + "or leave the study session?");
        } else {
            ui.showToUser("Would you like to resume the timer, mark a task as done, "
                    + "or leave the study session?");
        }
    }

    public void showTasks(Storage storage, String[] parsedInput) {
        if (!isTimerInitialised) {
            executeShow(storage, parsedInput);
        } else {
            ui.showToUser("You can't show tasks while timer is running!");
        }
    }

    private void executeShow(Storage storage, String[] parsedInput) {
        Command c = Parser.prepareShow(parsedInput);
        if (c != null) {
            c.execute(taskList, ui, storage);
            printAvailableCommands();
        }
    }

    /**
     * Creates a thread using timer.start() to start the timer with the user's specified duration.
     *
     * @param parsedInput Parsed input of the user
     */
    public void callStartTimer(String[] parsedInput) {
        if (isTimerInitialised) {
            ui.showToUser("You already have a timer running!");
            return;
        }
        try {
            callResetTimer(parsedInput);
            if (timer instanceof Countdown) {
                int duration = Parser.parseTimerInput(parsedInput);
                assert (duration > 0);
                ((Countdown) timer).setDuration(duration);
            }
            isTimerInitialised = true;
            timer.start();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | InvalidTimeException e) {
            ui.showToUser(ERROR_INVALID_TIMER_INPUT_MESSAGE);
        }
    }

    public void callPauseTimer() {
        if (!isTimerInitialised) {
            ui.showToUser("There is no timer running.");
            return;
        }
        if (timer.isTimerPaused()) {
            ui.showToUser("The timer is already paused!");
            return;
        }
        if (timer.getIsStopped()) {
            ui.showToUser("The timer has already finished!");
            return;
        }
        assert timer.isTimerRunning();
        timer.pauseTimer();
    }

    public void callResumeTimer() {
        if (!isTimerInitialised) {
            ui.showToUser("There is no timer running.");
            return;
        }
        if (timer.isTimerPaused()) {
            timer.resumeTimer();
        } else if (timer.isTimerRunning()) {
            ui.showToUser("The timer is still running!");
        } else {
            ui.showToUser("There is no timer running currently!");
        }
    }

    public void callStopTimer() {
        if (isTimerInitialised) {
            timer.stopTimer();
            isTimerInitialised = updateIsTimerRunning();
            taskList.printAllTasks(ui);
            ui.showToUser("Would you like to start another timer, mark a task as done, "
                    + "or leave the study session?");
            return;
        }
        ui.showToUser("You don't have a timer running!");
    }

    private boolean updateIsTimerRunning() {
        return timer.isTimerRunning();
    }

    /**
     * Resets the timer by creating a new timer object, which can then be started by the user.
     *
     */
    public void callResetTimer(String[] parsedInput) {
        String parameter = Parser.parseStudyParameter(parsedInput);
        if (parameter.equals("stopwatch")) {
            timer = new Stopwatch(taskList, ui);
            return;
        }
        timer = new Countdown(taskList, ui);
    }

    private boolean isTimerPausedOrStopped() {
        if (!isTimerInitialised) {
            return true;
        }
        if (timer.isTimerPaused()) {
            return true;
        }
        return false;
    }

    public boolean getIsTimerRunning() {
        if (!isTimerInitialised) {
            return false;
        }
        if (timer.isTimerRunning) {
            return true;
        }
        return false;
    }
}
