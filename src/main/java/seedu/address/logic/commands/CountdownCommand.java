package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class CountdownCommand extends Command {

    public static final String COMMAND_WORD = "countdown";
    public static final String MESSAGE_DATE_USAGE = COMMAND_WORD
            + ": Set the wedding date so countdown can be tracked later.\n"
            + "Parameters: YYYY-MM-DD (must be a valid date)\n"
            + "Example: " + COMMAND_WORD + " 2021-10-24";
    public static final String MESSAGE_FUTURE_DAY_COUNT = "%d day(s) left until your wedding on %s.";
    public static final String MESSAGE_PAST_DAY_COUNT = "%d day(s) has passed since your wedding day on %s.";
    public static final String MESSAGE_TODAY_COUNT = "Today is your wedding.\n"
            + "Congratulations and WedFast wish you all of the love and happiness!! \uD83D\uDE00";

    public static final String MESSAGE_FUTURE_SUCCESS = "Wedding date has been set on %s.\n"
            + "%d day(s) left from now.";
    public static final String MESSAGE_PAST_SUCCESS = "Wedding date has been set on %s.\n"
            + "%d day(s) has passed since your wedding day.";
    public static final String MESSAGE_TODAY_SUCCESS = "You have set your wedding to be on today.\n"
            + "Hope it is not too late to organize your wedding now and WedFast wish you all the best! \uD83D\uDE00";

    private final LocalDate dateSetByUser;
    private UndoCommand commandToUndo;

    /**
     * Creates an CountdownCommand to track the day count until the wedding date set.
     */
    public CountdownCommand() {
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        this.dateSetByUser = null;
    }

    /**
     * Creates an CountdownCommand to set the wedding date.
     */
    public CountdownCommand(LocalDate dateSetByUser) {
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        this.dateSetByUser = dateSetByUser;
    }

    /**
     * Returns the day count based on the wedding date set by user.
     *
     * @param model The app model used to retrieve the wedding date set by user.
     * @return the day count based on the wedding date set by user.
     */
    public static int getCountdown(Model model) {
        LocalDate weddingDate = model.getCountdown().getDate();
        return (int) LocalDate.now().until(weddingDate, ChronoUnit.DAYS);
    }

    /** Calculates and shows the day count before the wedding day. */
    private CommandResult executeShowCountDown(Model model) {
        LocalDate weddingDate = model.getCountdown().getDate();
        int numDays = (int) LocalDate.now().until(weddingDate, ChronoUnit.DAYS);

        if (numDays > 0) {
            return new CommandResult(String.format(MESSAGE_FUTURE_DAY_COUNT, numDays, weddingDate));
        }

        if (numDays < 0) {
            return new CommandResult(String.format(MESSAGE_PAST_DAY_COUNT, Math.abs(numDays), weddingDate));
        }

        return new CommandResult(MESSAGE_TODAY_COUNT);
    }

    /** Set the wedding day then shows the day count before the day. */
    private CommandResult executeSetWeddingDate(Model model) {
        model.setDate(dateSetByUser);
        assert dateSetByUser != null : "The wedding set by user should not be null.";
        int numDays = (int) LocalDate.now().until(dateSetByUser, ChronoUnit.DAYS);

        if (numDays > 0) {
            return new CommandResult(String.format(MESSAGE_FUTURE_SUCCESS, dateSetByUser, numDays));
        }

        if (numDays < 0) {
            return new CommandResult(String.format(MESSAGE_PAST_SUCCESS, dateSetByUser, Math.abs(numDays)));
        }

        return new CommandResult(MESSAGE_TODAY_SUCCESS);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (dateSetByUser == null) {
            return executeShowCountDown(model);
        }

        return executeSetWeddingDate(model);
    }
}
