package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.model.person.Price;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PriceEqualsNumberPredicate;
import seedu.address.model.person.predicates.PriceGreaterThanNumberPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.*;

public class ShortcutCommandParserTest {

    private ShortcutCommandParser parser = new ShortcutCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", ShortcutCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_validShortArgs_returnsShortcutCommand() {
        // no leading and trailing whitespaces
        ShortcutCommand expectedShortcutCommand = new ShortcutCommand("a");
        assertParseSuccess(parser, "a", expectedShortcutCommand);
    }

}
