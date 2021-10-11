package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.person.Price;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class PriceLessThanNumberPredicate implements Predicate<Person> {

    private Price price;

    PriceLessThanNumberPredicate(Price price) {
        this.price = price;
    }

    @Override
    public boolean test(Person person) {
        return price.greaterThan(person.getPrice());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceLessThanNumberPredicate); // state check
    }
}
