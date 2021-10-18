package seedu.address.model;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyShortcut {

    /**
     * Returns an unmodifiable view of the wedding date.
     */
    HashMap<String, String> getShortcutMap();
}
