package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
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
    public static String createReport(Model model) throws CommandException {
        ArrayList<ReportElement> reportArray = new ArrayList<>();
        ObservableList<Person> listOfPeople = model.getFilteredPersonList();
        for (Person currPerson : listOfPeople) {
            Set<Tag> currPersonTags = currPerson.getTags();
            String statusString = currPerson.getStatus().toString();
            for (Tag tagUsed : currPersonTags) {
                String tagString = tagUsed.toString();
                if (!canUpdateExistingElement(reportArray, tagString, statusString)) {
                    createNewElement(reportArray, tagString, statusString);
                }
            }
        }

        String priceOverallSum = new PriceCommand().execute(model).getFeedbackToUser();
        StringBuilder priceSumByTag = new StringBuilder();
        for (String tagString : getListOfTag(listOfPeople)) {
            priceSumByTag.append(new PriceCommand(tagString).execute(model).getFeedbackToUser())
                    .append("\n");
        }

        String priceSumReport = String.format("\n%s\n%s", priceOverallSum, priceSumByTag);
        return provideTextReport(reportArray) + priceSumReport;
    }

    /**
     * Creates and includes a new report element to report array
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

    /** Get a list of unique existing tags */
    private static List<String> getListOfTag(ObservableList<Person> listOfPeople) {
        Set<String> setOfTagString = new HashSet<>();
        for (Person person : listOfPeople) {
            Set<Tag> tagsOfCurrPerson = person.getTags();
            addTagsToSet(tagsOfCurrPerson, setOfTagString);
        }
        return new ArrayList<>(setOfTagString);
    }

    /** Add each tag from {@code tags} to {@code setOfTagString} */
    private static void addTagsToSet(Set<Tag> tags, Set<String> setOfTagString) {
        for (Tag tag : tags) {
            setOfTagString.add(tag.tagName);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(createReport(model));
    }
}
