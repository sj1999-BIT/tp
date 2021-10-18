package seedu.address.logic.parser;

import seedu.address.logic.commands.AddShortcutCommand;
import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ShortcutCommandParser implements Parser<ShortcutCommand> {

    @Override
    public ShortcutCommand parse(String userInput) throws ParseException {
        return new ShortcutCommand("");
    }
}
