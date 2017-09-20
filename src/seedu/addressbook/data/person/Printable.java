package seedu.addressbook.data.person;

/**
 * A read-only immutable interface for a Person's details in the addressbook.
 * Implementations should guarantee: field values are validated.
 */
public interface Printable {
     String getPrintableString();
}
