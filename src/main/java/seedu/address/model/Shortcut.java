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

    public Shortcut() {
        shortcutMap = new HashMap<>();
    }

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

        setShortcutMap(newData.getShortcutMap());
    }

    /**
     * Replaces the contents of the date with {@code weddingDate}.
     */
    public void setShortcutMap(HashMap<String, String> shortcutMap) {
        this.shortcutMap = shortcutMap;
    }

    public void addShortcut(String keyword, String commandString) {
        shortcutMap.put(keyword, commandString);
    }

    public String getCommandFromKey(String keyword) {
        return shortcutMap.get(keyword);
    }

    /**
     * Returns an unmodifiable view of the wedding date.
     */
    @Override
    public HashMap<String, String> getShortcutMap() {
        return shortcutMap;
    }
}
