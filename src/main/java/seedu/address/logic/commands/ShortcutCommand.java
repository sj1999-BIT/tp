package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ShortcutCommand extends Command {

    public static final String COMMAND_WORD = "shortcut";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final String shortcut;


    public ShortcutCommand(String shortcut) {
        this.shortcut = shortcut;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        try {
            String commandString = model.getShortcutFromKey(shortcut);
            Command command = (new AddressBookParser()).parseCommand(commandString);
            return command.execute(model);
        } catch (ParseException | CommandException e) {
            return new CommandResult("Command not found");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShortcutCommand // instanceof handles nulls
                ); // state check
    }
}
