package seedu.sherpass.task;

import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class TaskList {
    private ArrayList<Task> tasks;
    private HashSet<Integer> identifierList;

    /**
     * Creates a constructor for the class TaskList.
     *
     * @param savedTasks Representation of an array of tasks.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        tasks = savedTasks;
        identifierList = new HashSet<>();
        refreshIdentifier();
    }

    public TaskList() {
        identifierList = new HashSet<>();
        tasks = new ArrayList<>();
    }

    /**
     * Returns the array of tasks in the class TaskList.
     *
     * @return the array of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the current array of tasks.
     *
     * @param newTask The new task to be added to the array.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        identifierList.add(newTask.getIdentifier());
    }

    /**
     * Prints all available tasks in the task list.
     *
     * @param ui Ui class for printing of messages.
     */
    public void printAllTasks(Ui ui) {
        int printIndex = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(printIndex + ". " + task);
            printIndex++;
        }
        ui.showLine();
        System.out.println("You have " + (printIndex - 1) + " task(s) in your list.");
    }


    /**
     * Returns a boolean value denoting the task status, i.e.
     * whether the task has been marked or not.
     *
     * @param markIndex Index of a task to check for its mark status.
     * @return Returns true if task has been marked. False otherwise.
     */
    public boolean isTaskDone(int markIndex) {
        return tasks.get(markIndex).isDone();
    }


    /**
     * Marks a task given the index of the task.
     * Index corresponds to its placement within the task array.
     *
     * @param markIndex Index of the task to mark as done.
     */
    public void markTask(int markIndex) {
        tasks.get(markIndex).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(markIndex));
    }


    /**
     * Unmarks a task given the index of the task.
     * Index corresponds to its placement within the task array.
     *
     * @param markIndex Index of the task to mark as undone.
     */
    public void unmarkTask(int markIndex) {
        tasks.get(markIndex).markAsUndone();
        System.out.println("Ok, I've marked this task as" + " not done yet:\n  " + tasks.get(markIndex));
    }


    /**
     * Returns a boolean value to denote if the task has already
     * been added to the task array. References task description
     * when checking against each task.
     *
     * @param taskDescription Description of the task to match when searching for task.
     * @return Returns true if task has been added. False otherwise.
     */
    public boolean isTaskAlreadyAdded(String taskDescription) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(taskDescription)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a boolean value denoting the existence of a task
     * within the task array.
     *
     * @param index Index of a task. Corresponds to its placement in task array.
     * @return Returns true if task exists in task array. False otherwise.
     */
    public boolean isTaskExist(int index) {
        try {
            tasks.get(index);
            return true;
        } catch (IndexOutOfBoundsException exception) {
            return false;
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes a task given its index. Index corresponds to its placement
     * in task array.
     *
     * @param deleteIndex Index of a task to search for.
     */
    public void removeTask(int deleteIndex) {
        Task taskToBeRemoved = tasks.get(deleteIndex);
        tasks.remove(deleteIndex);
        System.out.println("Okay. I've removed this task:\n  " + taskToBeRemoved
                + "\nNow you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Deletes all tasks saved within the task array.
     *
     * @param ui Ui for printing the completion of the deletion.
     */
    public void deleteAllTasks(Ui ui) {
        while (tasks.size() > 0) {
            tasks.remove(0);
        }
        ui.showLine();
        ui.showToUser("Done! Now you have " + tasks.size() + " task(s) in the list.");
    }

    private void refreshIdentifier() {
        for (Task t : tasks) {
            identifierList.add(t.getIdentifier());
        }
    }

    public int generateIdentifier() {
        Random generator = new Random();
        int candidate;
        do {
            candidate = generator.nextInt(65536);
        } while (identifierList.contains(candidate));
        return candidate;
    }

    /**
     * Return a filtered ArrayList of task according to the date specified.
     *
     * @param dateInput The specific date.
     * @return The filtered ArrayList.
     */
    public ArrayList<Task> getFilteredTasksByDate(LocalDate dateInput) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (hasDoOnDate(task) && task.getDoOnStartDateTime().toLocalDate().isEqual(dateInput)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    private boolean hasDoOnDate(Task task) {
        return task.getDoOnStartDateTime() != null;
    }

    /**
     * Prints tasks that are yet to be completed, i.e. marked as done.
     * Printed tasks applies to non-recurring tasks.
     *
     * @param ui User interface
     */
    public void printPendingTasks(Ui ui) {
        int printIndex = 1;
        for (Task task : tasks) {
            if (!task.isDone()) {
                ui.showToUser(printIndex + ". " + task);
                printIndex++;
            }
        }
    }

    public int getPendingTasksCount() {
        int count = 0;
        for (Task task : tasks) {
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }
}
