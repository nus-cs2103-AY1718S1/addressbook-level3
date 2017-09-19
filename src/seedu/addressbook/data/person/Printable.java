package seedu.addressbook.data.person;

import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

/**
 * An immutable interface
 * Implementations should guarantee: provide at least one entry of information field, e.g., phone number; and at most
 * four entries, namely, Address, Email, Name and Phone
 */
public interface Printable {

    default String getPrintableString() {
        return "";
    }

    default String getPrintableString(Printable item1, Printable item2) {
        return item1.getPrintableString() + item2.getPrintableString();
    }

    default String getPrintableString(Printable item1, Printable item2, Printable item3) {
        return getPrintableString(item1, item2) + item3.getPrintableString();
    }

    default String getPrintableString(Printable item1, Printable item2, Printable item3, Printable item4) {
        return getPrintableString(item1, item2) + getPrintableString(item3, item4);
    }

}
