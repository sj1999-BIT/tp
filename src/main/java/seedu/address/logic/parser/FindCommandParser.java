package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;

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
        Predicate<Person> predicate = new TruePredicate();
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            List<String> tagList = argMultimap.getAllValues(PREFIX_TAG);
            trimmedArgs = trimmedArgs.split(PREFIX_TAG.toString())[0];
            if (tagList.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicate = predicate.and(new TagContainsKeywordsPredicate(tagList));
        }
        if (!trimmedArgs.isEmpty()) {
            String[] nameKeywords = trimmedArgs.split("\\s+");
            predicate = predicate.and(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        return new FindCommand(predicate);
    }

}
