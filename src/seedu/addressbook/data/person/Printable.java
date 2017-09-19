package seedu.addressbook.data.person;

public interface Printable {

    public default String getPrintableString() {
        return null;
    }

}
