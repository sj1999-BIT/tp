package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.function.Predicate;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Price;
import seedu.address.model.tag.Tag;

public class PriceCommand extends Command {

    public static final String COMMAND_WORD = "price";

    public static final String MESSAGE_TAG_USAGE = COMMAND_WORD
            + ": Checks the total price under specified {@code targetTag} in the displayed person list.\n"
            + "Parameters: TAG (must be a non-empty and non-blank string)\n"
            + "Example: " + COMMAND_WORD + " t/photographer";

    public static final String MESSAGE_UNKNOWN_PERSON_TAG = "Persons with tag %s are not found.";
    public static final String MESSAGE_TOTAL_PRICE_SUCCESS = "Total price of your wedding is %.2f";
    public static final String MESSAGE_TOTAL_PRICE_UNDER_TAG_SUCCESS = "Total price under tag: %s is %.2f";

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
        if (targetTag == null) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            double totalPrice = sumPriceInTheList(model.getFilteredPersonList());
            return new CommandResult(String.format(MESSAGE_TOTAL_PRICE_SUCCESS, totalPrice));
        }

        Predicate<Person> hasExactSameTag = (person) -> person.getTags().contains(targetTag);

        model.updateFilteredPersonList(hasExactSameTag);
        List<Person> filteredList = model.getFilteredPersonList();
        if (filteredList.isEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            throw new CommandException(String.format(MESSAGE_UNKNOWN_PERSON_TAG, targetTag));
        }

        double totalPrice = sumPriceInTheList(filteredList);

        return new CommandResult(String.format(MESSAGE_TOTAL_PRICE_UNDER_TAG_SUCCESS, targetTag, totalPrice));
    }

    private double sumPriceInTheList(List<Person> listToSum) {
        double totalPrice = 0.00;
        for (int i = 0; i < listToSum.size(); i++) {
            Price priceToAdd = listToSum.get(i).getPrice();
            double amountToAdd = priceToAdd.toDouble(priceToAdd);
            totalPrice += amountToAdd;
        }

        return totalPrice;
    }
}
