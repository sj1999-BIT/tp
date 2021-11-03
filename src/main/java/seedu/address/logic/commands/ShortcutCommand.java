package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ShortcutCommand extends Command {

    public static final String COMMAND_WORD = "sc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Calls the command tied to the keyphrase added by "
            + "addsc\n"
            + "Parameters: KEYPHRASE\n"
            + "Example: " + COMMAND_WORD + " a";

    public static final String COMMAND_INVALID = "Command invalid form: ";

    public static final String COMMAND_NOT_FOUND = "Command not found";

    public static final String COMMAND_EXECUTE_ERROR = "Command execute error: ";

    private final String shortcut;

    private UndoCommand commandToUndo;

    /**
     * Creates a new @code ShortcutCommand}
     *
     * @param shortcut the user's shortcut term
     */
    public ShortcutCommand(String shortcut) {
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        this.shortcut = shortcut;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String commandString = model.getShortcutFromKey(shortcut);
        try {
            if (commandString == null) {
                commandToUndo.setPrevCommand(null);
                return new CommandResult(COMMAND_NOT_FOUND);
            }
            Command command = (new AddressBookParser()).parseCommand(commandString);
            try {
                return command.execute(model);
            } catch (CommandException ce) {
                commandToUndo.setPrevCommand(null);
                return new CommandResult(COMMAND_EXECUTE_ERROR + commandString);
            }
        } catch (ParseException e) {
            commandToUndo.setPrevCommand(null);
            return new CommandResult(COMMAND_INVALID + commandString);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShortcutCommand) // instanceof handles nulls
                || (shortcut.equals(((ShortcutCommand) other).shortcut)); // state check
    }
}
