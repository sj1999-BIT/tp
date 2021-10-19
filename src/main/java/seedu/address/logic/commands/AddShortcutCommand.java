package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class AddShortcutCommand extends Command {

    public static final String COMMAND_WORD = "addshortcut";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final String keyword;
    private final String commandString;

    public AddShortcutCommand(String keyword, String commandString) {
        this.keyword = keyword;
        this.commandString = commandString;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.addShortcut(keyword, commandString);
        return new CommandResult("Added");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddShortcutCommand // instanceof handles nulls
                ); // state check
    }
}
