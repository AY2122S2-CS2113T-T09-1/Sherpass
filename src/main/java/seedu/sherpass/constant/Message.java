package seedu.sherpass.constant;

public class Message {
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE = " ";
    public static final String WELCOME_MESSAGE_ONE = "Welcome to\n";
    public static final String WELCOME_MESSAGE_TWO = "Here is your schedule for today:";
    public static final String WELCOME_MESSAGE_STUDY = "Gotcha! Entering study mode...\n"
            + "Done! To get started, enter one of the three default timers\n"
            + "using 'start <mode_number>':\n"
            + "1) 30 minutes\n"
            + "2) 1 hour\n"
            + "3) 1.5 hours\n\n"
            + "For testing purposes, you may start a 30s timer\nwith mode number 0.\n"
            + "Feel free to choose your own timer with\n'start /custom <timer_duration>'.\n"
            + "Otherwise, you can start a stopwatch with 'start stopwatch'.";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon! :)";
    public static final String GOODBYE_MESSAGE_STUDY = "Leaving study session...\n"
            + "Welcome back to the main session! How can I help you?";

    private static final String HELP_MESSAGE = "For more information, please enter the 'help' command.";
    public static final String HELP_MESSAGE_SPECIFIC_COMMAND = "\n\nFor more information on "
            + "the command you wish to execute,\nenter 'help <command>' e.g. help add";
    public static final String HELP_MESSAGE_QUICK_START_COMMAND = "Command input quick start guide:\n"
            + "1) Show: show today/week/<date>/all/todo\n"
            + "2) Mark: mark <list_index>\n"
            + "3) Unmark: unmark <list_index>\n"
            + "4) Add: add <task_description> /by <task_due_date> /do_on <date_to_work_on_task>\n"
            + "5) Delete: delete <list_index>\n"
            + "6) Find: find <keyword>\n"
            + "7) Clear: clear\n"
            + "8) Bye: bye";
    public static final String HELP_MESSAGE_STUDY = "Timer command input quick start guide:\n"
            + "1) Start default timer: start <mode_number>\n"
            + "2) Start custom timer: start /custom <timer_duration>\n"
            + "3) Stop timer: stop\n"
            + "4) Pause timer: pause\n"
            + "5) Resume timer: resume\n"
            + "6) Exit study mode: leave\n"
            + "7) Mark task as done: mark <task_index>";

    public static final String ERROR_START_AFTER_END_TIME_MESSAGE = "Start time cannot be after end time!";
    public static final String ERROR_EMPTY_DESCRIPTION_MESSAGE = "Description cannot be blank!";
    public static final String ERROR_EMPTY_TASKLIST_MESSAGE = "The task list is empty!";
    public static final String ERROR_MISSING_EDIT_ARGUMENT_MESSAGE = "Missing task description or date argument!";
    public static final String ERROR_INVALID_FREQUENCY_MESSAGE = "Invalid frequency!";
    public static final String ERROR_INVALID_INDEX_MESSAGE = "Invalid index!";
    public static final String ERROR_INVALID_DATETIME_MESSAGE = "Invalid date time format!";

    public static final String ERROR_INVALID_INPUT_MESSAGE = "Please key in an appropriate command.\n"
            + HELP_MESSAGE;
    public static final String ERROR_INVALID_STUDY_INPUT_MESSAGE = "Please key in an appropriate command.\n"
            + HELP_MESSAGE_STUDY;
    public static final String ERROR_INVALID_DELETE_INDEX_MESSAGE = "Oops! It seems that you've given\n"
            + "an invalid index to delete the task.";
    public static final String ERROR_IO_FAILURE_MESSAGE = "Oh no! We've encountered an error \nwhile "
            + "trying to processing the system.\n"
            + "Please reboot and execute the application again.";
    public static final String ERROR_CORRUPT_SAVED_FILE_MESSAGE_1 = "Oops! It seems that your saved file "
            + "is corrupted.";
    public static final String ERROR_CORRUPT_SAVED_FILE_MESSAGE_2 = "Would you like to start with a new save "
            + "file? (Y/N):";
    public static final String ERROR_CORRUPT_SAVED_FILE_MESSAGE_3 = "We're sorry this happened. "
            + "Please refer to the troubleshooting section in the user guide "
            + "or contact the developers for help.";
    public static final String ERROR_INVALID_MARKING_INDEX_MESSAGE = "Bzzt!\nPlease"
            + " key in a valid task number to mark/unmark your task."
            + HELP_MESSAGE_SPECIFIC_COMMAND;
    public static final String ERROR_INVALID_TIMER_INPUT_MESSAGE = "Oops! Your timer input "
            + "does not seem to be correct.\n\n"
            + "Please select one of the three default modes with\n"
            + "\t'start <mode_number>'\n\n"
            + "or choose your own custom timer with\n"
            + "\t'start /custom <timer_duration>'";
}
