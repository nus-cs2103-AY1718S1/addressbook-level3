package seedu.addressbook.data.person;

import seedu.addressbook.common.Utils;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.ArrayList;

/**
 * A read-only immutable interface for a Person in the addressbook.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyPerson {

    Name getName();
    Phone getPhone();
    Email getEmail();
    Address getAddress();

    /**
     * The returned TagList is a deep copy of the internal TagList,
     * changes on the returned list will not affect the person's internal tags.
     */
    UniqueTagList getTags();

    /**
     * Returns true if the values inside this object is same as those of the other (Note: interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyPerson other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getPhone().equals(this.getPhone())
                && other.getEmail().equals(this.getEmail())
                && other.getAddress().equals(this.getAddress()));
    }

    /**
     * Formats the person as text, showing all contact details.
     */
    default String getAsTextShowAll() {
        Printable[] details = { getName(), getPhone(), getEmail(), getAddress() };
        
        final StringBuilder tags = new StringBuilder(", Tags: ");
        for (Tag tag : getTags()) {
            tags.append(tag);
        }
        return Utils.getPrintableString(details) + tags.toString();
    }

    /**
     * Formats a person as text, showing only non-private contact details.
     */
    default String getAsTextHidePrivate() {
        ArrayList<Printable> detailList = new ArrayList<>();
        
        if(!getName().isPrivate()){
            detailList.add(getName());
        }
        
        if(!getPhone().isPrivate()){
            detailList.add(getPhone());
        }

        if(!getEmail().isPrivate()){
            detailList.add(getEmail());
        }

        if(!getAddress().isPrivate()){
            detailList.add(getAddress());
        }

        final StringBuilder tags = new StringBuilder(", Tags: ");
        for (Tag tag : getTags()) {
            tags.append(tag);
        }

        return Utils.getPrintableString(detailList.toArray(new Printable[detailList.size()])) + tags.toString();
    }

    
}
