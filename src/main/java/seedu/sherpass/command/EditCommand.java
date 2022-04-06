package seedu.sherpass.command;

import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static seedu.sherpass.constant.Message.EDIT_TASK_RESULT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.TAB_INDENT;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "Edit: Edit a task in the task list.\n"
            + "Usage: edit TASK_NUMBER [TASK_DESCRIPTION] [/do DATE /start START_TIME /end END_TIME]"
            + " [/by DEADLINE]\n\n"
            + "DATE & DEADLINE format: d/M/yyyy\n"
            + "START_TIME & END_TIME format: HH:mm";

    private int editIndex;
    private String taskDescription;
    private LocalDateTime doOnStartDateTime;
    private LocalDateTime doOnEndDateTime;
    private LocalDateTime byDate;
    private LocalDate doOnDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isRepeating;

    public EditCommand(int editIndex, String taskDescription,
                       LocalDate doOnDate, LocalTime startTime, LocalTime endTime) {
        this.editIndex = editIndex;
        this.taskDescription = taskDescription;
        this.doOnDate = doOnDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setRepeating(boolean repeating) {
        isRepeating = repeating;
    }

    public void setByDate(LocalDateTime byDate) {
        this.byDate = byDate;
    }

    private void setDoOnDateStartEndTime(Task taskToEdit) {
        doOnDate = (doOnDate == null) ? taskToEdit.getDoOnStartDateTime().toLocalDate() : doOnDate;
        startTime = (startTime == null) ? taskToEdit.getDoOnStartDateTime().toLocalTime() : startTime;
        endTime = (endTime == null) ? taskToEdit.getDoOnEndDateTime().toLocalTime() : endTime;
        doOnStartDateTime = LocalDateTime.of(doOnDate, startTime);
        doOnEndDateTime = LocalDateTime.of(doOnDate, endTime);
    }


    /**
     * Executes the editing of a task or multiple tasks.
     *
     * @param taskList Array representation of tasks.
     * @param ui       User Interface.
     * @param storage  Overwrites the save file data.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task taskToEdit = taskList.getTask(editIndex);
            setDoOnDateStartEndTime(taskToEdit);
            if (isRepeating) {
                taskList.editRepeatedTasks(editIndex, taskDescription,
                        doOnStartDateTime, doOnEndDateTime, byDate);
            } else {
                taskList.editSingleTaskContent(editIndex, taskDescription,
                        doOnStartDateTime, doOnEndDateTime, byDate);
            }
            storage.writeSaveData(taskList);
            ui.showToUser(EDIT_TASK_RESULT_MESSAGE);
            ui.showToUser(TAB_INDENT + taskToEdit);
        } catch (TimeClashException | InvalidInputException exception) {
            ui.showToUser(exception.getMessage());
        } catch (IndexOutOfBoundsException exception) {
            ui.showToUser(ERROR_INVALID_INDEX_MESSAGE);
        }
    }
}
