package seedu.address.logic.parser;

import static seedu.address.logic.commands.PriceCommand.MESSAGE_ARGUMENT_USAGE;
import static seedu.address.logic.commands.PriceCommand.MESSAGE_TAG_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.PriceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new PriceCommand object
 */
public class PriceCommandParser implements Parser<PriceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PriceCommand
     * and returns a PriceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PriceCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new PriceCommand();
        }

        boolean isNotTag = !StringUtil.isTag(args);
        if (isNotTag) {
            throw new ParseException(MESSAGE_ARGUMENT_USAGE);
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        String tagName = argMultimap.getValue(PREFIX_TAG).get();
        String trimmedTag = tagName.trim();
        if (trimmedTag.isEmpty()) {
            throw new ParseException(MESSAGE_TAG_USAGE);
        }

        Tag tag = ParserUtil.parseTag(tagName);
        return new PriceCommand(tag);
    }
}
