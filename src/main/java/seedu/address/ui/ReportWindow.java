package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a report page
 */
public class ReportWindow extends UiPart<Stage> {

    private static String REPORT_MESSAGE;

    private static final Logger logger = LogsCenter.getLogger(ReportWindow.class);
    private static final String FXML = "ReportWindow.fxml";

    @FXML
    private Label reportMessage;

    public static void setReportMessage(String message) {
        REPORT_MESSAGE = message;
    }

    /**
     * Creates a new ReportWindow.
     *
     * @param root Stage to use as the root of the ReportWindow.
     */
    public ReportWindow(Stage root) {
        super(FXML, root);
        reportMessage.setText(REPORT_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public ReportWindow() {
        this(new Stage());
    }

    /**
     * Shows the report window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing report page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

}