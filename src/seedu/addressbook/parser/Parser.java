package seedu.addressbook.parser;

import seedu.addressbook.commands.*;
import seedu.addressbook.data.exception.IllegalValueException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.addressbook.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.addressbook.common.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

/**
 * Parses user input.
 */
public class Parser {

    public static final Pattern PERSON_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    public static final Pattern PERSON_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<name>[^/]+)"
                    + " (?<isPhonePrivate>p?)p/(?<phone>[^/]+)"
                    + " (?<isEmailPrivate>p?)e/(?<email>[^/]+)"
                    + " (?<isAddressPrivate>p?)a/(?<address>[^/]+)"
                    + "(?<tagArguments>(?: t/[^/]+)*)"); // variable number of tags

    public static final String PERSON_NAME_ARGS_FORMAT = " (?<name>[^/]+)";

    public static final String PERSON_PHONE_ARGS_FORMAT = " (?<isPhonePrivate>p?)p/(?<phone>[^/]+)";

    public static final String PERSON_EMAIL_ARGS_FORMAT = " (?<isEmailPrivate>p?)e/(?<email>[^/]+)";

    public static final String PERSON_ADDRESS_ARGS_FORMAT = " (?<isAddressPrivate>p?)a/(?<address>[^/]+)";

    public static final String PERSON_TAGS_ARGS_FORMAT = "(?<tagArguments>(?: t/[^/]+)*)";

    public static final int FIRST = 0;
    public static final int SECOND = 1;

    public static final String FIELDTYPE = "NPEAT";
    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

            case AddCommand.COMMAND_WORD:
                return prepareAdd(arguments);

            case UpdateCommand.COMMAND_WORD:
                return prepareUpdate(arguments);

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);

            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();

            case FindCommand.COMMAND_WORD:
                return prepareFind(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case ViewCommand.COMMAND_WORD:
                return prepareView(arguments);

            case ViewAllCommand.COMMAND_WORD:
                return prepareViewAll(arguments);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD: // Fallthrough
            default:
                return new HelpCommand();
        }
    }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAdd(String args){
        final Matcher matcher = PERSON_DATA_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        try {
            return new AddCommand(
                    matcher.group("name"),

                    matcher.group("phone"),
                    isPrivatePrefixPresent(matcher.group("isPhonePrivate")),

                    matcher.group("email"),
                    isPrivatePrefixPresent(matcher.group("isEmailPrivate")),

                    matcher.group("address"),
                    isPrivatePrefixPresent(matcher.group("isAddressPrivate")),

                    getTagsFromArgs(matcher.group("tagArguments"))
            );
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

    /**
     * Checks whether the private prefix of a contact detail in the add command's arguments string is present.
     */
    private static boolean isPrivatePrefixPresent(String matchedPrefix) {
        return matchedPrefix.equals("p");
    }

    /**
     * Extracts the new person's tags from the add command's tag arguments string.
     * Merges duplicate tag strings.
     */
    private static Set<String> getTagsFromArgs(String tagArguments) throws IllegalValueException {
        // no tags
        if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }
        // replace first delimiter prefix, then split
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst(" t/", "").split(" t/"));
        return new HashSet<>(tagStrings);
    }

    /**
     * Parses arguments in the context of the update person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareUpdate(String args) {

        String[] inputValue = args.trim().split(" ");
        String index = inputValue[FIRST];
        String field = inputValue[SECOND];
        System.out.println("Index " + index);
        System.out.println("Field " + field);

        try {

            final int targetIndex = parseArgsAsDisplayedIndex(index);
            final Pattern targetField = parseArgsAsDisplayedField(field);

            int startValue = (index + " " + field).length();

            String updateInput = args.trim().substring(startValue);
            final Matcher allMatcher = targetField.matcher(updateInput);

            // Validate arg string format
            if (!allMatcher.matches()) {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
            }

            String fieldNotUpdated = parseArgsAsNonDisplayedField(field);
            System.out.println(field.indexOf('E'));
            String name="",
                    phone="123123",
                    email="12312@123.com",
                    address = "123 sadas 123123" ;

            Boolean isEmailPrivate=false,
                    isAddressPrivate=false,
                    isPhonePrivate=false;

            Set<String> tags = new HashSet<String>();;
            if(field.indexOf('N') >= 0){
                name = allMatcher.group("name");
            }

            if(field.indexOf('P') >= 0) {
                phone = allMatcher.group("phone");
                isPhonePrivate = isPrivatePrefixPresent(allMatcher.group("isPhonePrivate"));
            }

            if(field.indexOf('E') >= 0) {
                email = allMatcher.group("email");
                isEmailPrivate = isPrivatePrefixPresent(allMatcher.group("isEmailPrivate"));
            }

            if(field.indexOf('A') >= 0) {
                address = allMatcher.group("address");
                isAddressPrivate = isPrivatePrefixPresent(allMatcher.group("isAddressPrivate"));
            }

            if(field.indexOf('T') >= 0) {
                tags = getTagsFromArgs(allMatcher.group("tagArguments"));
            }



            return new UpdateCommand(
                    targetIndex, fieldNotUpdated, name, phone, isPhonePrivate, email, isEmailPrivate, address, isAddressPrivate, tags
            );

        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }


    /**
     * Parses arguments in the context of the delete person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException | NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the view command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareView(String args) {

        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new ViewCommand(targetIndex);
        } catch (ParseException | NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the view all command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareViewAll(String args) {

        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new ViewAllCommand(targetIndex);
        } catch (ParseException | NumberFormatException e) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewAllCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = PERSON_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }

    /**
     * Parses the given arguments string
     *
     * @param args arguments string to parse as string value
     * @return the person data args format
     * @throws ParseException if no region of the args string could be found for the relevant indicate field
     */
    private Pattern parseArgsAsDisplayedField(String args) throws ParseException {
        String regex = "";
        for (int i=0; i<args.length(); i++){
            switch (args.charAt(i)) {

                case UpdateCommand.NAME:
                    regex += PERSON_NAME_ARGS_FORMAT;
                    continue;

                case UpdateCommand.PHONE:
                    regex += PERSON_PHONE_ARGS_FORMAT;
                    continue;

                case UpdateCommand.EMAIL:
                    regex += PERSON_EMAIL_ARGS_FORMAT;
                    continue;

                case UpdateCommand.ADDRESS:
                    regex += PERSON_ADDRESS_ARGS_FORMAT;
                    continue;

                case UpdateCommand.TAG:
                    regex += PERSON_TAGS_ARGS_FORMAT;
                    continue;
                default:
                    throw new ParseException("Could not find relevant indicated field to parse");
            }
        }

        Pattern pattern = Pattern.compile(regex);


        return pattern;
    }

    /**
     * Parses the given arguments string
     *
     * @param args arguments string to parse as string value
     * @return the field not updated
     */
    private String parseArgsAsNonDisplayedField(String args) {
        String fieldNotExist = "";

        if(FIELDTYPE.length() != args.length()){
            for (int i=0; i<FIELDTYPE.length(); i++) {
                if(args.indexOf(FIELDTYPE.charAt(i)) == -1){
                    fieldNotExist += FIELDTYPE.charAt(i);
                }
            }
        }
        else {
            return FIELDTYPE;
        }

        System.out.println("parseArgsAsNonDisplayedField " + fieldNotExist);

        return fieldNotExist;
    }


    /**
     * Parses arguments in the context of the find person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareFind(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }


}
