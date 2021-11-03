package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) or tags and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]... t/TAG [MORE_TAGS]\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie t/colleague";

    private final Predicate<Person> predicate;

    private UndoCommand commandToUndo;

    /**
     * Creates a new @code FindCommand}
     *
     * @param predicate the check used in the search
     */
    public FindCommand(Predicate<Person> predicate) {
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
