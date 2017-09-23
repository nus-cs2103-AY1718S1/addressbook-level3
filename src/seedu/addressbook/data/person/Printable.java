package seedu.addressbook.data.person;

public interface Printable {
    String detailIsPrivate = "(private) ";
    /**
     * Returns a printable string representation of the data type of a person
     */
    String getPrintableString();
}
