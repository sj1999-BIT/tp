package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Hashtable;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.ui.ReportWindow;

public class TagInfoCommand extends Command {

    public static final String COMMAND_WORD = "tagInfo";

    public static final String MESSAGE_NO_INPUT = "No valid tag value is inputted";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Provides the number of contacts with the respective tags"
            + " Methods of usage :"
            + "1. tagInfo list : provide a list for every unique tag in the current model\n"
            + "2. tagInfo t/[TAG1], [TAG2] ... : provide a list for every existing tag inputted\n "
            + "   if the input tag is not in the model, an error message will be shown while the\n "
            + "   existing tags will still be in the list";

    private HashSet<Tag> listOfTags = new HashSet<>();


    public TagInfoCommand(String all) {
        assert all == "list";
    }

    /**
     * Returns a new TagInfoCommand Object with the inputs converted into
     * a HashSet Object
     * @param tagListInput is an array of strings containing the tag names.
     * @throws CommandException if invalid tag name is inputted
     */
    public TagInfoCommand(String[] tagListInput) throws CommandException {
        for (String tagName : tagListInput) {
            if (tagName.equals("")) {
                throw new CommandException("Empty tag is inputted!");
            }
            try {
                this.listOfTags.add(new Tag(tagName));
            } catch (Exception e) {
                throw new CommandException(e.getMessage());
            }
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String resultMessage = "tags Info:\n";
        String errorTags = "";
        Hashtable<Tag, Integer> tagInfoTable = model.getUniqueTagTable();

        // empty, assumed that we are looking for every tag in the model
        if (listOfTags.isEmpty()) {
            listOfTags.addAll(tagInfoTable.keySet());
        }

        for (Tag tagsToBeChecked : listOfTags) {
            if (tagInfoTable.containsKey(tagsToBeChecked)) {
                resultMessage += tagsToBeChecked.tagName
                        + " used by "
                        + tagInfoTable.get(tagsToBeChecked)
                        + " people\n";
            } else {
                errorTags += tagsToBeChecked.tagName + "\n";
            }
        }

        if (!errorTags.equals("")) {
            resultMessage += "following tags cannot be found:\n" + errorTags;
        }

        ReportWindow.setReportMessage(resultMessage);
        ReportWindow window = new ReportWindow();
        window.show();

        return new CommandResult("Tag Information is generated in a report window.");
    }
}
