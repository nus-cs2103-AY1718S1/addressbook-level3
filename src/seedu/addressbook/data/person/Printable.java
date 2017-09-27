package seedu.addressbook.data.person;

/**
 * A read-only immutable interface only allows object to be printed.
 * Implementations should guarantee: only the necessary details for the object are returned for the printing.
 */
public interface Printable {
    public String getPrintableString();
}
