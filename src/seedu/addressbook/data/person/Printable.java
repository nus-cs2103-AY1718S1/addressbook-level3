package seedu.addressbook.data.person;

/**
 * A read-only immutable interface for a person data object.
 * There should be a printable string representation of the object.
 */
public interface Printable {
    String getPrintableString();
}