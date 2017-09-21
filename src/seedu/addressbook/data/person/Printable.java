package seedu.addressbook.data.person;

/**
 * An interface for producing a printable string representation of an object.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface Printable {

    /**
     * Returns a concatenated version of the printable strings of each object.
     */
    String getPrintableString();

}
