package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddShortcutCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Info;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

public class AddShortcutCommandParserTest {
    private AddShortcutCommandParser parser = new AddShortcutCommandParser();

    private final String KEY = "k";
    private final String COMMAND_STRING = "list";

    @Test
    public void parse_allFieldsPresent_success() {

        assertParseSuccess(parser, KEY + " c/" + COMMAND_STRING, new AddShortcutCommand(KEY, COMMAND_STRING));
    }

    @Test
    public void parse_emptyEntry_failure() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noCommand_failure() {
        assertParseFailure(parser, KEY, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noKey_failure() {
        assertParseFailure(parser, " c/" + COMMAND_STRING, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_blankCommand_failure() {
        assertParseFailure(parser, KEY + " c/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
    }
}
