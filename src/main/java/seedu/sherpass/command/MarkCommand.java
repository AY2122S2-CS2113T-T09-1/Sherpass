package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = "Mark: Marks a task as done. "
            + "\nTo mark a specific task, enter 'mark <task_number>'.\n\n Here, "
            + "'task_number' denotes the index of a task \n based on the task list under the command 'show all'.\n"
            + "\nE.g., 'mark 1' marks the first task in the task list as done.\n\n"
            + "Note: You can only mark one task per command input.";

    private int markIndex;

    /**
     * Creates a constructor for the mark command.
     * Saves index of task to mark.
     *
     * @param markIndex Task index to mark.
     * @param taskList  Task array.
     */
    public MarkCommand(int markIndex, TaskList taskList) throws IndexOutOfBoundsException {
        if (taskList.isTaskNotExist(markIndex)) {
            throw new IndexOutOfBoundsException();
        }
        this.markIndex = markIndex;
    }


    /**
     * Executes mark command. Marks task as Done.
     *
     * @param taskList Task array.
     * @param ui       Ui for printing
     * @param storage  Overwrite save file for newly marked task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isTaskDone(markIndex)) {
            ui.showToUser("This task has already been marked!");
            return;
        }
        taskList.markTask(markIndex, ui);
        storage.writeSaveData(taskList);
    }
}
