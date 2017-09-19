package seedu.addressbook.data.person;

/**
 * An interface for data that requires the ability to be printed
 * Guarantees : Existence of method to print data as string
 */
public interface Printable {
    String getPrintableString();
}
