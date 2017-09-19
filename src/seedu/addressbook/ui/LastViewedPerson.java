package seedu.addressbook.ui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

/**
 *  Representation of a person shown in the last view list
 *  Used as a model for the table view
 */

public class LastViewedPerson {

    private static String DATA_IS_PRIVATE = "(private)";

    public final StringProperty name;
    public final StringProperty phone;
    public final StringProperty email;
    public final StringProperty address;
    public final StringProperty tags;
    public final ObservableValue<Integer> index;

    LastViewedPerson(ReadOnlyPerson rop, int indexInList) {

        String phoneToDisplay =
                (rop.getPhone().isPrivate()) ? DATA_IS_PRIVATE : rop.getPhone().toString();
        String emailToDisplay =
                (rop.getEmail().isPrivate()) ? DATA_IS_PRIVATE : rop.getEmail().toString();
        String addressToDisplay =
                (rop.getAddress().isPrivate()) ? DATA_IS_PRIVATE : rop.getAddress().toString();

        String tagsToDisplay = "";

        for (Tag t : rop.getTags()) {
            tagsToDisplay += t.toString() + " ";
        }

        name = new SimpleStringProperty(rop.getName().toString());
        phone = new SimpleStringProperty(phoneToDisplay);
        email = new SimpleStringProperty(emailToDisplay);
        address = new SimpleStringProperty(addressToDisplay);
        index = new ReadOnlyObjectWrapper<>(indexInList);
        tags = new SimpleStringProperty(tagsToDisplay);
    }
}
