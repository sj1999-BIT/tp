package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a warning window
 */
public class WarningWindow extends UiPart<Stage> {
    public static final String WARNING_MESSAGE = "Are you sure you want to clear all data?";

    private static final Logger logger = LogsCenter.getLogger(WarningWindow.class);
    private static final String FXML = "WarningWindow.fxml";

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    private Label warningMessage;

    /**
     * Creates a new WarningWindow.
     *
     * @param root Stage to use as the root of the WarningWindow.
     */
    public WarningWindow(Stage root) {
        super(FXML, root);
        warningMessage.setText(WARNING_MESSAGE);
    }

    /**
     * Creates a new WarningWindow.
     */
    public WarningWindow() {
        this(new Stage());
    }

    /**
     * Shows the warning window.
     */
    public void show() {
        logger.fine("Showing warning window about the clearing.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Obtains the button for yes.
     */
    public Button getYesButton() {
        return this.yesButton;
    }

    /**
     * Obtains the button for no.
     */
    public Button getNoButton() {
        return this.noButton;
    }
}

