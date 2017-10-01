package seedu.addressbook.data.person;

/**
 * An interface to provide data into printable format for Person's details in the addressbook.
 * Implementation should return the details of a person with a prefixed title of the information
 * Good: return "DataTitle: " + value;
 * Bad:  return value;
 */
public interface Printable {
    String getPrintableString();
}
