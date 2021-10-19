package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Price;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

public class PriceCommand extends Command {

    public static final String COMMAND_WORD = "price";

    public static final String MESSAGE_ARGUMENT_USAGE = COMMAND_WORD
            + ": Checks the total price under specified {@code targetTag} in the displayed person list.\n"
            + "Parameters: Either empty or t/TAG\n"
            + "Example 1: " + COMMAND_WORD + "\n"
            + "Example 2: " + COMMAND_WORD + " t/photographer";

    public static final String MESSAGE_TAG_USAGE = COMMAND_WORD
            + ": Checks the total price under specified {@code targetTag} in the displayed person list.\n"
            + "Parameters: TAG (must be a non-empty and non-blank string)\n"
            + "Example: " + COMMAND_WORD + " t/photographer";

    public static final String MESSAGE_UNKNOWN_PERSON_TAG = "Persons with tag %s are not found.";
    public static final String MESSAGE_TOTAL_PRICE_SUCCESS = "Total price of your wedding is %.2f";
    public static final String MESSAGE_TOTAL_PRICE_UNDER_TAG_SUCCESS = "Total price under tag: %s is %.2f";

    private static final Predicate<Person> PREDICATE_IS_CONFIRMED = (person)
        -> person.getStatus().value.matches("[Cc]onfirmed");

    private final Tag targetTag;

    public PriceCommand() {
        this.targetTag = null;
    }

    public PriceCommand(Tag targetTag) {
        this.targetTag = targetTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String zeroSumMessage = String.format(MESSAGE_TOTAL_PRICE_UNDER_TAG_SUCCESS, targetTag, 0.00);
        updateFilteredList(model, PREDICATE_IS_CONFIRMED, zeroSumMessage);
        if (targetTag == null) {
            double totalPrice = sumPriceInTheList(model.getFilteredPersonList());
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_TOTAL_PRICE_SUCCESS, totalPrice));
        } else {
            return executeSumByTag(model);
        }
    }

    /** Finds the price sum under the target tag. */
    private CommandResult executeSumByTag(Model model) throws CommandException {
        List<String> tags = new ArrayList<>();
        assert targetTag != null : "targetTag should not be null";
        tags.add(targetTag.tagName);
        Predicate<Person> hasTag = new TagContainsKeywordsPredicate(tags);

        String unknownPersonMessage = String.format(MESSAGE_UNKNOWN_PERSON_TAG, targetTag);
        updateFilteredList(model, hasTag, unknownPersonMessage);

        Predicate<Person> hasTagAndConfirmed = (person) -> hasTag.test(person) && PREDICATE_IS_CONFIRMED.test(person);
        String zeroSumMessage = String.format(MESSAGE_TOTAL_PRICE_UNDER_TAG_SUCCESS, targetTag, 0.00);
        List<Person> hasTagAndConfirmedFilteredList = updateFilteredList(model, hasTagAndConfirmed, zeroSumMessage);

        double totalPrice = sumPriceInTheList(hasTagAndConfirmedFilteredList);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_TOTAL_PRICE_UNDER_TAG_SUCCESS, targetTag, totalPrice));
    }

    private double sumPriceInTheList(List<Person> listToSum) {
        double totalPrice = 0.00;
        for (int i = 0; i < listToSum.size(); i++) {
            Price priceToAdd = listToSum.get(i).getPrice();
            double amountToAdd = priceToAdd.toDouble();
            totalPrice += amountToAdd;
        }

        return totalPrice;
    }

    private List<Person> updateFilteredList(Model model, Predicate<Person> predicate, String message)
            throws CommandException {
        model.updateFilteredPersonList(predicate);
        List<Person> newFilteredList = model.getFilteredPersonList();
        if (newFilteredList.isEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            throw new CommandException(message);
        }
        return newFilteredList;
    }
}
