package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.ui.WarningWindow;

/**
 * Warn the user with a prompt window before the actual clear command executed.
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    // new message added to indicate if the address book is already empty
    public static final String MESSAGE_UNNECESSARY = "Address book is already empty!";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_FAILURE = "Address book is not cleared!";

    /**
     * Executes will create a warning panel to give the user one more chance
     * to reconsider before clearing all the data.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Object CommandResult with the specific message
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.size() == 0) {
            return new CommandResult(MESSAGE_UNNECESSARY);
        } else {
            WarningWindow warning = new WarningWindow("Are you sure?\n All data will be cleared!");
            if (warning.isChoiceYes()) {
                model.setAddressBook(new AddressBook());
                return new CommandResult(MESSAGE_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_FAILURE);
            }
        }
    }

}
