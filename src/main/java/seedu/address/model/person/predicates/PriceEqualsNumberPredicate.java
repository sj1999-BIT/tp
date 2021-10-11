package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.person.Price;

/**
 * Tests that a {@code Person}'s {@code Price} is equal to a certain price
 */
public class PriceEqualsNumberPredicate implements Predicate<Person> {
    private Price price;

    public PriceEqualsNumberPredicate(Price price) {
        this.price = price;
    }

    @Override
    public boolean test(Person person) {
        return person.getPrice().equals(price);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceEqualsNumberPredicate); // state check
    }
}
