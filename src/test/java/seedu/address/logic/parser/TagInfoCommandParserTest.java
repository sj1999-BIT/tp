package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.TagInfoCommand;
import seedu.address.logic.commands.exceptions.CommandException;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class TagInfoCommandParserTest {

    private TagInfoCommandParser parser = new TagInfoCommandParser();

    @Test
    public void assert_all_values_correct() throws CommandException {
        assertParseSuccess(parser, "tagInfo t/Ben Tan test yall",
                new TagInfoCommand("Ben Tan test yall".split(" ")));
    }

    @Test
    public void assert_empty_tag_values() throws CommandException {
        assertParseFailure(parser, "tagInfo t/", "Empty tag is inputted!");
    }

    @Test
    public void assert_invalid__tag_values() throws CommandException {
        assertParseFailure(parser, "tagInfo t/Photographer, test",
                "Tags names should be alphanumeric");
    }

}
