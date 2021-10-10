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

    public static final String MESSAGE_DAY_COUNT = "%d days left until your wedding on %s";
    public static final String MESSAGE_SUCCESS = "Wedding date has been set on %s.\n%d days left from now.";
    private final LocalDate dateSetByUser;

    public CountdownCommand() {
        this.dateSetByUser = null;
    }

    public CountdownCommand(LocalDate dateSetByUser) {
        this.dateSetByUser = dateSetByUser;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (dateSetByUser == null) {
            LocalDate weddingDate = model.getCountdown().getDate();
            int numDays = (int) LocalDate.now().until(weddingDate, ChronoUnit.DAYS);
            return new CommandResult(String.format(MESSAGE_DAY_COUNT, numDays, weddingDate));
        } else {
            model.setDate(dateSetByUser);
            int numDays = (int) LocalDate.now().until(dateSetByUser, ChronoUnit.DAYS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, dateSetByUser, numDays));
        }
    }
}
