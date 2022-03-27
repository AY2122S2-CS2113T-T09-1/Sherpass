package seedu.sherpass.util.parser;

import seedu.sherpass.command.Command;
import seedu.sherpass.command.AddCommand;
import seedu.sherpass.command.ClearCommand;
import seedu.sherpass.command.DeleteCommand;
import seedu.sherpass.command.EditCommand;
import seedu.sherpass.command.ExitCommand;
import seedu.sherpass.command.HelpCommand;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.command.ShowCommand;
import seedu.sherpass.command.StudyCommand;
import seedu.sherpass.command.UnmarkCommand;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import static seedu.sherpass.constant.CommandParameter.FREQUENCY_DELIMITER;
import static seedu.sherpass.constant.Index.COMMAND_CONTENT_INDEX;
import static seedu.sherpass.constant.Index.HELP_OPTIONS_INDEX;
import static seedu.sherpass.constant.Index.OPTIONS_INDEX;

import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.WHITESPACE;


public class Parser {

    private static Command prepareHelp(String userInput) {
        try {
            String[] parsedInput = userInput.split(" ", 2);
            return new HelpCommand(parsedInput[HELP_OPTIONS_INDEX]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return new HelpCommand("show help list");
        }
    }

    /**
     * Parses the user command input.
     *
     * @param userInput User command.
     * @param taskList  Array of tasks.
     * @return Command type matching the user command.
     */
    public static Command parseCommand(String userInput, TaskList taskList, Ui ui) {
        String[] splitInput = userInput.split(WHITESPACE, 2);
        String commandWord = splitInput[OPTIONS_INDEX].toLowerCase().trim();
        String argument = (splitInput.length > 1)
                ? splitInput[COMMAND_CONTENT_INDEX].trim() : EMPTY_STRING;
        switch (commandWord) {
        case MarkCommand.COMMAND_WORD:
            return TaskParser.prepareMarkOrUnmark(argument, MarkCommand.COMMAND_WORD, taskList);
        case UnmarkCommand.COMMAND_WORD:
            return TaskParser.prepareMarkOrUnmark(argument, UnmarkCommand.COMMAND_WORD, taskList);
        case AddCommand.COMMAND_WORD:
            return TaskParser.prepareAdd(argument, ui);
        case EditCommand.COMMAND_WORD:
            return TaskParser.prepareEdit(argument, taskList, ui);
        case DeleteCommand.COMMAND_WORD:
            return TaskParser.prepareDelete(argument, taskList, ui);
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case StudyCommand.COMMAND_WORD:
            return new StudyCommand();
        case ShowCommand.COMMAND_WORD:
            return TaskParser.prepareShow(splitInput);
        case HelpCommand.COMMAND_WORD:
            return prepareHelp(userInput);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            ui.showToUser(ERROR_INVALID_INPUT_MESSAGE);
            return null;
        }
    }
}
