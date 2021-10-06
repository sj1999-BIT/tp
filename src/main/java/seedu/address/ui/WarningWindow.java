package seedu.address.ui;


import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;

import javax.swing.*;
import java.net.URL;


/**
 * This class create a warning window. Future upgrade include adding an extension to
 * UIPart to utilize fxml and standardise to the rest of the class
 * under ui package.
 */
public class WarningWindow {
    JFrame frame = new JFrame("");;
    String message = "";

    public WarningWindow() {
    }

    public WarningWindow(JFrame presetFrame) {
        frame = presetFrame;
    }

    public WarningWindow(String presetMessage) {
        message = presetMessage;
    }

    public WarningWindow(JFrame presetFrame, String presetMessage) {
        frame = presetFrame;
        message = presetMessage;
    }

    public boolean isChoiceYes() {
        int result = JOptionPane.showConfirmDialog(frame, message, "WARNING!", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        // if yes is picked, the address book data in model will be reset
        return result == JOptionPane.YES_OPTION;
    }
}
