package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ReportElement;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class ReportCommand extends Command {

    public static final String COMMAND_WORD = "report";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows status report for contacts.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Checks for tag in the existing report arraylist. If it is present, the corresponding report element is
     * updated with incremented status count and true is returned. Else, false is returned.
     * @param reportList is the list with report elements
     * @param tag is the tag associated with element
     * @param status is the status associated with element
     * @return true if tag exists and so status can be updated
     */
    public static boolean canUpdateExistingElement(ArrayList<ReportElement> reportList, String tag, String status) {
        for (ReportElement e : reportList) {
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
     * @param model is the source from which list of contacts can be obtained
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
     * Creates and includes a new report element to report array
     * @param reportElements is the arraylist storing report elements
     * @param currTag is the tag being added
     * @param currStatus is the status being used to keep count
     */
    public static void createNewElement(ArrayList<ReportElement> reportElements, String currTag, String currStatus) {
        if (currStatus.matches("[Cc]onfirmed|c")) {
            reportElements.add(new ReportElement(currTag, 1, 0, 0));
        } else if (currStatus.matches("[Pp]ending|p")) {
            reportElements.add(new ReportElement(currTag, 0, 1, 0));
        } else if (currStatus.matches("[Dd]eclined|d")) {
            reportElements.add(new ReportElement(currTag, 0, 0, 1));
        } else { }
    }

    /**
     * Creates a report in an organised format.
     * @param currReportArray is the list with report elements
     * @return final report as a string
     */
    public static String provideTextReport(ArrayList<ReportElement> currReportArray) {
        String reportAsString = "Current status for tags:" + "\n";
        for (ReportElement currElement : currReportArray) {
            reportAsString = reportAsString + currElement + "\n";
        }
        return reportAsString;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(createReport(model), false, true, false);
    }
}
