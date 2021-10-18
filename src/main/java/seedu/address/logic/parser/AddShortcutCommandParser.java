package seedu.address.logic.parser;

import seedu.address.logic.commands.AddShortcutCommand;
import seedu.address.logic.commands.CountdownCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDate;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class AddShortcutCommandParser implements Parser<AddShortcutCommand> {

    @Override
    public AddShortcutCommand parse(String userInput) throws ParseException {
        return new AddShortcutCommand("");
    }
}
