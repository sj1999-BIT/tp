package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


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
     * Function execute will create a warning panel to give the user one more chance
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
            // confirm that the address book is not empty
            assert model.size() > 0;
            //create GUI will the choice of clearing the data
            JFrame frame = new JFrame();
            int result = JOptionPane.showConfirmDialog(frame, "Are you sure?\n All data will be cleared!", "WARNING",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            // if yes is picked, the address book data in model will be reset
            if(result ==JOptionPane.YES_OPTION) {
                model.setAddressBook(new AddressBook());
                return new CommandResult(MESSAGE_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_FAILURE);
            }
        }
    }

}
