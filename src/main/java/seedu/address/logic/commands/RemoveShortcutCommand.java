package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Removes a shortcut to the shortcut list stored.
 */
public class RemoveShortcutCommand extends Command {

    public static final String COMMAND_WORD = "removesc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a shortcut with a specified call"
            + "Parameters: KEYWORDS c/[Command to be executed]\n"
            + "Example: " + COMMAND_WORD + " a";

    private final String keyword;

    /**
     * Creates new {@code RemoveShortcutCommand}
     * @param keyword Key to call the command
     */
    public RemoveShortcutCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String commandString = model.removeShortcut(keyword);
        if (commandString == null) {
            return new CommandResult("Command with keyword " + keyword + " not found");
        }
        return new CommandResult("Removed " + keyword + ": " + commandString);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveShortcutCommand); // instanceof handles nulls
    }
}
