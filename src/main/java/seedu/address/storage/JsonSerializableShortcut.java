package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyShortcut;
import seedu.address.model.Shortcut;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "shortcut")
class JsonSerializableShortcut {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final HashMap<String, String> shortcuts = new HashMap<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableShortcut(@JsonProperty("shortcuts") List<JsonAdaptedShortcut> shortcuts) {
        this.shortcuts.clear();
        for (JsonAdaptedShortcut shortcut: shortcuts) {
            this.shortcuts.put(shortcut.getKeyword(), shortcut.getCommandString());
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableShortcut(ReadOnlyShortcut source) {
        shortcuts.clear();
        shortcuts.putAll(source.getShortcutMap());
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Shortcut toModelType() throws IllegalValueException {
        Shortcut shortcut = new Shortcut();
        shortcut.setShortcutMap(shortcuts);
        return shortcut;
    }

}
