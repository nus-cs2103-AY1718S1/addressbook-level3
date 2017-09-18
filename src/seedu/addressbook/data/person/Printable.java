package seedu.addressbook.data.person;

public interface Printable {

    String getPrintableString();

    /**
     * converts multiple Printables into a single string, separated by spaces
     * @param printables is a varargs collection of Printables
     * @return a string representing the arguments, in order, separated by spaces
     */
    static String getPrintableString(Printable... printables) {
        String output = "";
        for (Printable p : printables) {
            output += p.getPrintableString() + " ";
        }
        return output;
    }
}
