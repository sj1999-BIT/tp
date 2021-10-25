package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ReportElement;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.ui.ReportWindow;

public class ReportCommand extends Command {

    public static final String COMMAND_WORD = "report";
    public static final String SHOWING_REPORT_MESSAGE = "Opened report window.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows status report for contacts.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Checks for tag in the existing report arraylist. If it is present, the corresponding report element is
     * updated with incremented status count and true is returned. Else, false is returned.
     * @param reportArray is the arraylist with report elements
     * @param tag is the tag associated with element
     * @param status is the status associated with element
     * @return true if tag exists and so status can be updated
     */
    public static boolean canUpdateExistingElement(ArrayList<ReportElement> reportArray, String tag, String status) {
        for (ReportElement e : reportArray) {
            if (e.hasSameTag(tag)) {
                e.incrementStatusCount(status);
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a report arraylist that keeps track of all the tags and the corresponding
     * status count for each tag.
     * @param model is the source from which the list of contacts can be obtained
     * @return report created
     */
    public static String createReport(Model model) {
        ArrayList<ReportElement> reportArray = new ArrayList<ReportElement>();
        ObservableList<Person> listOfPeople = model.getFilteredPersonList();
        for (Person currPerson : listOfPeople) {
            Set<Tag> currPersonTags = currPerson.getTags();
            currPersonTags.toArray();
            String statusString = currPerson.getStatus().toString();
            for (Tag tagUsed : currPersonTags) {
                String tagString = tagUsed.toString();
                if (!canUpdateExistingElement(reportArray, tagString, statusString)) {
                    createNewElement(reportArray, tagString, statusString);
                }
            }
        }
        return provideTextReport(reportArray);
    }

    /**
     * Creates and adds a new report element to report array.
     * @param reportArray is the arraylist storing report elements
     * @param currTag is the tag being added
     * @param currStatus is the status being used to keep count
     */
    public static void createNewElement(ArrayList<ReportElement> reportArray, String currTag, String currStatus) {
        if (currStatus.matches("[Cc]onfirmed|c")) {
            reportArray.add(new ReportElement(currTag, 1, 0, 0));
        } else if (currStatus.matches("[Pp]ending|p")) {
            reportArray.add(new ReportElement(currTag, 0, 1, 0));
        } else if (currStatus.matches("[Dd]eclined|d")) {
            reportArray.add(new ReportElement(currTag, 0, 0, 1));
        } else { }
    }

    /**
     * Creates a report in an organised format.
     * @param fullReportArray is the arraylist with all report elements
     * @return final report as a string
     */
    public static String provideTextReport(ArrayList<ReportElement> fullReportArray) {
        String reportAsString = "Current status for tags:" + "\n";
        for (ReportElement currElement : fullReportArray) {
            reportAsString = reportAsString + currElement + "\n";
        }
        return reportAsString;
    }

    @Override
    public CommandResult execute(Model model) {
        ReportWindow.setReportMessage(createReport(model));
        ReportWindow window = new ReportWindow();
        window.show();
        return new CommandResult(SHOWING_REPORT_MESSAGE);
    }
}
