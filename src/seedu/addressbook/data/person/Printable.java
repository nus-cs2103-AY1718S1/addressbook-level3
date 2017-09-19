package seedu.addressbook.data.person;

public interface Printable {

    /**
     * Returns a printable string representation of the object.
     */
    String getPrintableString();

    /**
     * Returns if printable is private
     */
    default boolean isPrivate() { return false; }
}
