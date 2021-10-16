package seedu.address.logic.parser;

import java.time.LocalDate;

import seedu.address.logic.commands.CountdownCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class CountdownCommandParser implements Parser<CountdownCommand> {

    @Override
    public CountdownCommand parse(String userInput) throws ParseException {

        if (userInput.trim().equals("countdown")) {
            return new CountdownCommand();
        } else {
            String dateString = userInput.substring(10);
            LocalDate dateSetByUser = LocalDate.parse(dateString);
            return new CountdownCommand(dateSetByUser);
        }
    }
}
