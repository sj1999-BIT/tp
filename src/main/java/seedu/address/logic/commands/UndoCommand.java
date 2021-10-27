package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Undoes the previous task.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "The task has been undone";

    private static Command prevCommand;

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
                    String.format(Messages.MESSAGE_CANNOT_UNDO_COMMAND,
                            model.getFilteredPersonList().size()));
        }
        List<Person> currentList = model.getFilteredPersonList();
        if (prevCommand instanceof AddCommand) {
            model.deletePerson(currentList.get(currentList.size() - 1));
        } else if (prevCommand instanceof DeleteCommand) {
            DeleteCommand deleted = (DeleteCommand) prevCommand;
            Person deletedPerson = deleted.getPersonToDelete();
            if (deleted.getTargetIndex() != null) {
                Index index = deleted.getTargetIndex();
                shuffle(currentList, index.getZeroBased(), deletedPerson,
                        model);
            } else if (deleted.getTargetName() != null) {
                Index index = deleted.getIndexName();
                shuffle(currentList, index.getZeroBased(), deletedPerson,
                        model);
            } else if (deleted.getTargetTag() != null) {
                ArrayList<Person> deletedList = deleted.getDeletedList();
                for (Person p : deletedList) {
                    shuffle(currentList, 0, p,
                            model);
                }
            }
        } else if (prevCommand instanceof ClearCommand) {
            ClearCommand cleared = (ClearCommand) prevCommand;
            model.setAddressBook(new AddressBook(cleared.getPrevBook()));
        } else if (prevCommand instanceof EditCommand) {
            EditCommand edited = (EditCommand) prevCommand;
            model.setPerson(edited.getEditedPerson(), edited.getOriginalPerson());
        } else if (prevCommand instanceof GroupCommand) {
            GroupCommand group = (GroupCommand) prevCommand;
            String tagName = group.getTagName();
            List<String> nameList = group.getNameList();
            removePeople(currentList, nameList, tagName, model);
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
        return (command instanceof ExitCommand)
                || (command instanceof FindCommand)
                || (command instanceof HelpCommand)
                || (command instanceof ListCommand)
                || (command instanceof CountdownCommand)
                || (command instanceof UndoCommand);
    }

    /**
     * If a person has been deleted, reinstates
     * the deleted person at the correct index
     *
     * @param personList the list of all people
     * @param index      the former index of the deleted person
     * @param newPerson  the deleted person
     * @param model      the model in use
     */
    public void shuffle(List<Person> personList, int index, Person newPerson, Model model) {
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

    /**
     * Undoes the adding of people to a new group
     *
     * @param personList the list of all people
     * @param names      the names of those who were added to a group
     * @param tag        the group name
     * @param model      the model in use
     */
    public void removePeople(List<Person> personList, List<String> names, String tag, Model model) {
        for (Person p : personList) {
            for (String name : names) {
                if (p.getName().fullName.equals(name)) {
                    HashSet<Tag> tags = new HashSet<>(p.getTags());
                    tags.removeIf(t -> t.toString().contains(tag));
                    Person editedPerson = new Person(p.getName(), p.getPhone(),
                            p.getEmail(), p.getAddress(), p.getPrice(),
                            p.getInfo(), p.getStatus(), tags);
                    model.setPerson(p, editedPerson);
                    break;
                }
            }
        }
    }
}
