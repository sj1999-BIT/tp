package seedu.address.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Hashtable;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Countdown of the wedding day.
 * Guarantees: details are present and not null.
 */
public class Shortcut implements ReadOnlyShortcut {

    private HashMap<String, String> shortcutMap;

    public Shortcut() {}

    /**
     * Creates a Countdown using the LocalDate in the {@code toBeCopied}
     */
    public Shortcut(ReadOnlyShortcut toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code Countdown} with {@code newData}.
     */
    public void resetData(ReadOnlyShortcut newData) {
        requireNonNull(newData);

        setDate(newData.getShortcutMap());
    }

    /**
     * Replaces the contents of the date with {@code weddingDate}.
     */
    public void setDate(HashMap<String, String> shortcutMap) {
        this.shortcutMap = shortcutMap;
    }

    /**
     * Returns an unmodifiable view of the wedding date.
     */
    @Override
    public HashMap<String, String> getShortcutMap() {
        return shortcutMap;
    }
}
