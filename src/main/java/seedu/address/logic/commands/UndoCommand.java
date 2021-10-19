package seedu.address.logic.commands;

import java.util.List;

import static java.util.Objects.requireNonNull;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;

/**
 * Undoes the previous task.
 */
public class UndoCommand extends Command {

    private static Command prevCommand;

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "The task has been undone";

    /**
     * Sets the previous command.
     *
     * @param prevCommand
     */
    public void setPrevCommand(Command prevCommand) {
        this.prevCommand = prevCommand;
    }

    /**
     * Checks whether the command can be done
     * and performs the undo operation accordingly.
     *
     * @param model the model in use
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (prevCommand == null || isInstanceOf(prevCommand)) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_CANNOT_UNDO_COMMAND, model.getFilteredPersonList().size()));
        }
        List<Person> currentList = model.getFilteredPersonList();
        if (prevCommand instanceof AddCommand) {
            model.deletePerson(currentList.get(currentList.size() - 1));
        } else if (prevCommand instanceof DeleteCommand) {
            DeleteCommand deleted = (DeleteCommand) prevCommand;
            Person deletedPerson = deleted.getPersonToDelete();
            if (deleted.getTargetIndex() != null) {
                Index index = deleted.getTargetIndex();
                shuffleByIndex(currentList, index.getZeroBased(), deletedPerson,
                        model);
            } else if (deleted.getTargetName() != null) {
                Index index = deleted.getIndexName();
                shuffleByIndex(currentList, index.getZeroBased(), deletedPerson,
                        model);
            }
        } else if (prevCommand instanceof ClearCommand) {
            ClearCommand cleared = (ClearCommand) prevCommand;
            model.setAddressBook(cleared.getPrevBook());
        } else if (prevCommand instanceof EditCommand) {
            EditCommand edited = (EditCommand) prevCommand;
            model.setPerson(edited.getEditedPerson(), edited.getOriginalPerson());
        } else if (prevCommand instanceof GroupCommand) {
            ClearCommand cleared = (ClearCommand) prevCommand;
        }
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().size()));
    }

    /**
     * Checks whether the command is an instance
     * of any of the following classes.
     *
     * @param command the command to check
     */
    public boolean isInstanceOf(Command command) {
        if ((prevCommand instanceof ExitCommand)||
                (prevCommand instanceof FindCommand)
                || (prevCommand instanceof HelpCommand)
                || (prevCommand instanceof ListCommand)
                || (prevCommand instanceof CountdownCommand)) {
            return true;
        }
        return false;
    }

    /**
     * If a delete by index has occurred, reinstates
     * the deleted person at the correct index
     *
     *
     * @param personList
     * @param index
     * @param newPerson
     * @param model
     */
    public void shuffleByIndex(List<Person> personList, int index, Person newPerson, Model model) {
        Person temp1 = newPerson;
        for (int count = 0; count < personList.size(); count++) {
             if (count == index) {
                temp1 = personList.get(count);
                model.setPerson(temp1, newPerson);
            } else if (count > index) {
                Person temp2 = personList.get(count);
                model.setPerson(temp2, temp1);
                temp1 = temp2;
            }
        }
        model.addPerson(temp1);
    }

}

