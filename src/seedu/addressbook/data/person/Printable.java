package seedu.addressbook.data.person;

/**
 * An interface for data that requires the ability to be printed
 * Guarantees : Existence of method to print data as string
 */
public interface Printable {
    String getPrintableString();
    
    /** Returns a concatenated version, separated by a separator, of the printable strings of each object **/
    static String getPrintableString(Printable... printables) {
        String separator = " ";
        String resultString = "";
        for (Printable p : printables) {
            resultString += p.getPrintableString() + separator;
        }
        return resultString;
    }
}
