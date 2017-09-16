package seedu.addressbook.data.person;

/**
 * A Printable interface for a Person's particular (e.g. email, phone, address, name) in the addressbook.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface Printable {

    String getPrintableString();
}
