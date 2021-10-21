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
    public static boolean canUpdateExistingTagStatus(ArrayList<ReportElement> reportList, String tag, String status) {
        for (ReportElement e : reportList) {
            if (e.hasSameTag(tag)) {
                if (status.equalsIgnoreCase("confirmed")
                        || status.equalsIgnoreCase("c")) {
                    e.incrementConfirmed();
                } else if (status.equalsIgnoreCase("pending")
                        || status.equalsIgnoreCase("p")) {
                    e.incrementPending();
                } else if (status.equalsIgnoreCase("declined")
                        || status.equalsIgnoreCase("d")) {
                    e.incrementDeclined();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a report arraylist that keeps track of all the tags and the corresponding
     * status count for each tag.
     * @param model is the list with report elements
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
                if (!canUpdateExistingTagStatus(reportArray, tagString, statusString)) {
                    if (statusString.equalsIgnoreCase("confirmed")
                            || statusString.equalsIgnoreCase("c")) {
                        reportArray.add(new ReportElement(tagString, 1, 0, 0));
                    } else if (statusString.equalsIgnoreCase("pending")
                            || statusString.equalsIgnoreCase("p")) {
                        reportArray.add(new ReportElement(tagString, 0, 1, 0));
                    } else if (statusString.equalsIgnoreCase("declined")
                            || statusString.equalsIgnoreCase("d")) {
                        reportArray.add(new ReportElement(tagString, 0, 0, 1));
                    } else { }
                }
            }
        }
        return provideTextReport(reportArray);
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


