package seedu.addressbook.data.person;

/**
 * An interface for a person details in the addressbook.
 * Implementations should guarantee: returned string to be printable.
 */
public interface Printable {
    String getPrintableString();
}
