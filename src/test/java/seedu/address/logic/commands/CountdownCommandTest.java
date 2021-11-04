package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDate.getTypicalCountdown;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Shortcut;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CountdownCommand}.
 */
public class CountdownCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
            new UserPrefs(), new Shortcut());

    @Test
    public void execute_validFutureDate_success() {
        LocalDate dateToSet = getTypicalCountdown().getDate();
        CountdownCommand countdownCommand = new CountdownCommand(dateToSet);
        int numDays = (int) LocalDate.now().until(dateToSet, ChronoUnit.DAYS);

        String expectedMessage = String.format(CountdownCommand.MESSAGE_FUTURE_SUCCESS, dateToSet, numDays);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getCountdown(),
                new UserPrefs(), new Shortcut());
        expectedModel.setDate(dateToSet);

        assertCommandSuccess(countdownCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTodayDate_success() {
        LocalDate dateToSet = LocalDate.now();
        CountdownCommand countdownCommand = new CountdownCommand(dateToSet);
        int numDays = (int) LocalDate.now().until(dateToSet, ChronoUnit.DAYS);

        String expectedMessage = String.format(CountdownCommand.MESSAGE_TODAY_SUCCESS, dateToSet, numDays);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getCountdown(),
                new UserPrefs(), new Shortcut());
        expectedModel.setDate(dateToSet);

        assertCommandSuccess(countdownCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPastDate_success() {
        LocalDate pastDate = LocalDate.of(2000, Month.APRIL, 29);
        CountdownCommand countdownCommand = new CountdownCommand(pastDate);

        String expectedMessage = CountdownCommand.MESSAGE_PAST_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getCountdown(),
                new UserPrefs(), new Shortcut());

        assertCommandSuccess(countdownCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_checkFutureCountdown_success() {
        LocalDate dateToSet = getTypicalCountdown().getDate();
        CountdownCommand countdownCommand = new CountdownCommand();
        int numDays = (int) LocalDate.now().until(dateToSet, ChronoUnit.DAYS);

        String expectedMessage = String.format(CountdownCommand.MESSAGE_FUTURE_DAY_COUNT, numDays, dateToSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getCountdown(),
                new UserPrefs(), new Shortcut());
        expectedModel.setDate(dateToSet);

        assertCommandSuccess(countdownCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_checkTodayCountdown_success() {
        LocalDate dateToSet = LocalDate.now();
        model.setDate(dateToSet);
        CountdownCommand countdownCommand = new CountdownCommand();

        String expectedMessage = CountdownCommand.MESSAGE_TODAY_COUNT;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getCountdown(),
                new UserPrefs(), new Shortcut());
        expectedModel.setDate(dateToSet);

        assertCommandSuccess(countdownCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_checkPastCountdown_success() {
        LocalDate dateToSet = LocalDate.of(2000, Month.APRIL, 29);
        model.setDate(dateToSet);
        CountdownCommand countdownCommand = new CountdownCommand();
        int numDays = (int) LocalDate.now().until(dateToSet, ChronoUnit.DAYS);

        String expectedMessage = String.format(CountdownCommand.MESSAGE_PAST_DAY_COUNT, Math.abs(numDays), dateToSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getCountdown(),
                new UserPrefs(), new Shortcut());
        expectedModel.setDate(dateToSet);

        assertCommandSuccess(countdownCommand, model, expectedMessage, expectedModel);
    }
}
