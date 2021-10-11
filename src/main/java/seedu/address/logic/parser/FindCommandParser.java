package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.person.predicates.*;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private final String OPERATOR_VALIDATION_REGEX = "^(<=|>=|<|>|=)";
    private final Pattern OPERATOR_VALIDATION_PATTERN = Pattern.compile(OPERATOR_VALIDATION_REGEX + "(.*)");
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
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_PRICE);
        Predicate<Person> predicate = new TruePredicate();
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            List<String> tagList = argMultimap.getAllValues(PREFIX_TAG);
            if (tagList.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicate = predicate.and(new TagContainsKeywordsPredicate(tagList));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            List<String> priceList = argMultimap.getAllValues(PREFIX_PRICE);
            if (priceList.size() != 1 || !isValidPrice(priceList.get(0))) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String priceParam = priceList.get(0);
            Matcher m = OPERATOR_VALIDATION_PATTERN.matcher(priceParam);
            if (m.matches()) {
                String operator = m.group(1);
                System.out.println(operator);
                Price price = new Price(priceParam.split(OPERATOR_VALIDATION_REGEX)[1]);
                predicate = predicate.and(parsePricePredicate(operator, price));
            } else {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }
        if (!argMultimap.getPreamble().isBlank()) {
            String[] nameKeywords = argMultimap.getPreamble().split("\\s+");
            predicate = predicate.and(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        return new FindCommand(predicate);
    }

    private Predicate<Person> parsePricePredicate(String operator, Price price) {
        Predicate<Person> pricePredicate = new FalsePredicate();
        for(char c : operator.toCharArray()) {
            switch (c) {
                case ('>'):
                    pricePredicate = pricePredicate.or(new PriceGreaterThanNumberPredicate(price));
                    break;
                case ('<'):
                    pricePredicate = pricePredicate.or(new PriceLessThanNumberPredicate(price));
                    break;
                case ('='):
                    pricePredicate = pricePredicate.or(new PriceEqualsNumberPredicate(price));
                    break;
            }
        }
        return pricePredicate;
    }

    private boolean isValidPrice(String input) {
        boolean validOperator = input.matches(OPERATOR_VALIDATION_REGEX+"(.*)");
        String pricePortion = input.split(OPERATOR_VALIDATION_REGEX)[1];
        return validOperator && Price.isValidPrice(pricePortion);
    }

}
