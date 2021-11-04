package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCountdown;
import seedu.address.model.ReadOnlyShortcut;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Shortcut;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDate.getTypicalCountdown;
import static seedu.address.testutil.TypicalPersons.*;

public class ShortcutCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
            new UserPrefs(), getTypicalShortcut());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
            new UserPrefs(), getTypicalShortcut());
    @Test
    public void constructor_nullKey_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShortcutCommand(null));
    }

    @Test
    public void execute_shortcutValid_success() {
        assertCommandSuccess(new ShortcutCommand(VALID_KEY), model, VALID_RESPONSE, expectedModel);
    }

    @Test
    public void execute_shortcutDoesNotExist_failure() {
        assertCommandFailure(new ShortcutCommand("9"), model, ShortcutCommand.COMMAND_NOT_FOUND);
    }

    @Test
    public void execute_shortcutInvalid_failure() {
        assertCommandFailure(new ShortcutCommand(INVALID_KEY), model, String.format(ShortcutCommand.COMMAND_EXECUTE_ERROR,
                INVALID_COMMAND, INVALID_RESPONSE));
    }

    @Test
    public void execute_shortcutUnknown_failure() {
        assertCommandFailure(new ShortcutCommand(UNKNOWN_KEY), model, String.format(ShortcutCommand.COMMAND_UNKNOWN,
                UNKNOWN_COMMAND, MESSAGE_UNKNOWN_COMMAND));
    }

    @Test
    public void equals() {
        ShortcutCommand scA = new ShortcutCommand("A");
        ShortcutCommand scB = new ShortcutCommand("B");

        // same object -> returns true
        assertTrue(scA.equals(scA));

        // same values -> returns true
        ShortcutCommand scACopy = new ShortcutCommand("A");
        assertTrue(scA.equals(scACopy));

        // different types -> returns false
        assertFalse(scA.equals(1));

        // null -> returns false
        assertFalse(scA.equals(null));

        // different person -> returns false
        assertFalse(scA.equals(scB));
    }
}
