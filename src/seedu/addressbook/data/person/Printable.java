package seedu.addressbook.data.person;

public interface Printable {

    default String getPrintableString(){
        return "this is the default interface string for Printable";
    }
}
