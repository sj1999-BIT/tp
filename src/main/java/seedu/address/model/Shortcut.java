package seedu.address.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Hashtable;

import static java.util.Objects.requireNonNull;

/**
 * Represents shortcuts stored by the user
 */
public class Shortcut implements ReadOnlyShortcut {

    private HashMap<String, String> shortcutMap;

    public Shortcut() {
        shortcutMap = new HashMap<>();
    }

    /**
     * Creates a Shortcut using the data in the {@code toBeCopied}
     */
    public Shortcut(ReadOnlyShortcut toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code Shortcut} with {@code newData}.
     */
    public void resetData(ReadOnlyShortcut newData) {
        requireNonNull(newData);

        setShortcutMap(newData.getShortcutMap());
    }

    /**
     * Replaces the contents of the shortcut map with {@code shorcutMap}.
     */
    public void setShortcutMap(HashMap<String, String> shortcutMap) {
        this.shortcutMap = shortcutMap;
    }

    /**
     * Adds a shortcut to the shortcut map with a {@code keyword} and {@code commandString} key-pair value.
     *
     * @param keyword Key to call a command
     * @param commandString Command to be called
     */
    public void addShortcut(String keyword, String commandString) {
        shortcutMap.put(keyword, commandString);
    }

    /**
     * Fetches the command by a key.
     *
     * @param keyword Key to call a command
     * @return Command to be called
     */
    public String getCommandFromKey(String keyword) {
        return shortcutMap.get(keyword);
    }

    /**
     * Returns an unmodifiable view of the shortcut map.
     */
    @Override
    public HashMap<String, String> getShortcutMap() {
        return shortcutMap;
    }
}
