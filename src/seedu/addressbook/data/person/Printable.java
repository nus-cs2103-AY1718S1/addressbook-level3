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
        int count = 1;
        for (Printable p : printables) {
            resultString.concat(p.getPrintableString());
            if (count < printables.length) {
                resultString.concat(separator);
            }
            count++;
        }
        return resultString;
    }
}
