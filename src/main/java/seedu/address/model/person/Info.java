package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Info {

    public static final String MESSAGE_CONSTRAINTS =
            "Important information should only contain alphanumeric "
                    + "characters and spaces";


    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String info;

    /**
     * Constructs a {@code Info}.
     *
     * @param info valid information.
     */
    public Info(String info) {
        requireNonNull(info);
        checkArgument(isValidInfo(info), MESSAGE_CONSTRAINTS);
        this.info = info;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return info;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Info // instanceof handles nulls
                && info.equals(((Info) other).info)); // state check
    }

    @Override
    public int hashCode() {
        return info.hashCode();
    }

}


