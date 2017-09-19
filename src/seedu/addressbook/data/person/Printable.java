package seedu.addressbook.data.person;

/**
 * An interface for a Person's details in the addressbook.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface Printable {
    String getPrintableString();
}
