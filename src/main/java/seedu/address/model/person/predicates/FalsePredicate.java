package seedu.address.model.person.predicates;

import seedu.address.model.person.Person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class FalsePredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FalsePredicate); // state check
    }
}
