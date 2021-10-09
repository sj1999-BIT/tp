package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CombiningPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String[] tags = trimmedArgs.split(PREFIX_TAG.toString());
            if (tags.length < 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String names = tags[0];
            String[] tagKeywords = tags[1].trim().split("\\s+");
            if (!names.trim().isEmpty()) {
                String[] nameKeywords = names.trim().split("\\s+");
                return new FindCommand(new CombiningPredicate(
                        new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)),
                        new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords))
                ));
            }
            return new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");
        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
