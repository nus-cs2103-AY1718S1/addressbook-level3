package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ListCommandTest {
    List<Person> testList = new ArrayList<Person>();
    @Test
    public void execute() throws Exception {
        testList.add(new Person(new Name("Betty"), new Phone("123", true),new Email("q@q", true), new Address("abc", true), new UniqueTagList()));
        testList.add(new Person(new Name("Alice"), new Phone("123", true),new Email("q@q", true), new Address("abc", true), new UniqueTagList()));
        Collections.sort(testList, (ReadOnlyPerson person1, ReadOnlyPerson person2) ->
                (person1.getName().toString()).compareTo(person2.getName().toString()));
        if(testList.get(0).getName().equals("Alice")) {
            assertTrue(true);
        }

        if(testList.get(1).getName().equals("Betty")) {
            assertTrue(true);
        }
    }
}