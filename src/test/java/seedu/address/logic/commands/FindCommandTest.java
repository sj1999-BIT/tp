package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDate.getTypicalCountdown;
import static seedu.address.testutil.TypicalPersons.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Shortcut;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Price;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PriceEqualsNumberPredicate;
import seedu.address.model.person.predicates.PriceGreaterThanNumberPredicate;
import seedu.address.model.person.predicates.PriceLessThanNumberPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
            new UserPrefs(), new Shortcut());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
            new UserPrefs(), new Shortcut());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate, "");
        FindCommand findSecondCommand = new FindCommand(secondPredicate, "");

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, "");
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = "0 person(s) listed where ";
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                preparePredicate(" "));
        FindCommand command = new FindCommand(predicate, "");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = "3 person(s) listed where name is in [Kurz, Elle, Kunz] ";
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                preparePredicate("Kurz Elle Kunz"));
        FindCommand command = new FindCommand(predicate, "name is in [Kurz, Elle, Kunz] ");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroTags_noPersonFound() {
        String expectedMessage = "0 person(s) listed where ";
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(
                preparePredicate(" ")
        );
        FindCommand command = new FindCommand(predicate, "");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTags_multiplePersonsFound() {
        String expectedMessage = "3 person(s) listed where tag is in [friends] ";
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(
                preparePredicate("friends")
        );
        FindCommand command = new FindCommand(predicate, "tag is in [friends] ");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_priceEqual_noPersonFound() {
        String expectedMessage = "0 person(s) listed where ";
        PriceEqualsNumberPredicate predicate = new PriceEqualsNumberPredicate(new Price("10.00"));
        FindCommand command = new FindCommand(predicate, "");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_priceEqual_multiplePersonsFound() {
        String expectedMessage = "7 person(s) listed where ";
        PriceEqualsNumberPredicate predicate = new PriceEqualsNumberPredicate(new Price("0.00"));
        FindCommand command = new FindCommand(predicate, "");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_priceGreaterThan_noPersonFound() {
        String expectedMessage = "0 person(s) listed where ";
        PriceGreaterThanNumberPredicate predicate = new PriceGreaterThanNumberPredicate(new Price("10.00"));
        FindCommand command = new FindCommand(predicate, "");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_priceLessThan_noPersonFound() {
        String expectedMessage = "0 person(s) listed where ";
        PriceLessThanNumberPredicate predicate = new PriceLessThanNumberPredicate(new Price("0.00"));
        FindCommand command = new FindCommand(predicate, "");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_priceLessThan_multiplePersonsFound() {
        String expectedMessage = "7 person(s) listed where ";
        PriceLessThanNumberPredicate predicate = new PriceLessThanNumberPredicate(new Price("10.00"));
        FindCommand command = new FindCommand(predicate, "");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a ArrayList
     */
    private List preparePredicate(String userInput) {
        return Arrays.asList(userInput.split("\\s+"));
    }
}
