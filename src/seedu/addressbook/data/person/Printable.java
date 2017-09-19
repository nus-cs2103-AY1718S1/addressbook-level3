package seedu.addressbook.data.person;

public interface Printable {
    default void print() {
        System.out.println("Person is here");
    }
}
