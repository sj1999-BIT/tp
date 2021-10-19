package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedShortcut {

    private final String keyword;
    private final String commandString;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedShortcut(@JsonProperty("keyword") String keyword, @JsonProperty("command") String commandString) {
        this.keyword = keyword;
        this.commandString = commandString;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getCommandString() {
        return commandString;
    }

}
