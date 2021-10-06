package seedu.address.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * This class create a warning window. Future upgrade include adding an extension to
 * UIPart to utilize fxml and standardise to the rest of the class
 * under ui package.
 */
public class WarningWindow {
    private JFrame frame = new JFrame("");;
    private String message = "";

    public WarningWindow() {
    }

    public WarningWindow(String presetMessage) {
        message = presetMessage;
    }

    /**
     * Creates the pop up warning window with the indicated frame and message.
     * @return boolean value: return true if yes to chose, else return false
     */
    public boolean isChoiceYes() {
        int result = JOptionPane.showConfirmDialog(frame, message, "WARNING!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        // if yes is picked, the address book data in model will be reset
        return result == JOptionPane.YES_OPTION;
    }
}
