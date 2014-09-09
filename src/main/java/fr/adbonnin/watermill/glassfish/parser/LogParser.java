package fr.adbonnin.watermill.glassfish.parser;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.functors.Map;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static org.codehaus.jparsec.Scanners.*;

public class LogParser {

    /**
     * A parser for a single part of a log event, something between two | or event terminator
     */
    public static final Parser<String> component = 
            notAmong("|#][")   /* any char not among the given chars (those are special chars used for tokenizing ... */
                    .many1()   /* ... 1 or more times */ 
                    .source(); /* ... represented as a String */

    /**
     * A parser for a list of components
     */
    public static final Parser<List<String>> components = 
            component                    /* a component ... */ 
                .sepBy(isChar('|'));    /* ... zero or more times separated by a single '|' */

    /**
     * A parser for a single event
     */
    public static final Parser<Event> event = 
            components                                 /* a list of components ... */
            .between(string("[#|"), string("|#]"))     /* ... between the given strings */
            .map(new Map<List<String>, Event>() {      /* ... transformed into an event. map allows creation of syntax
                                                          tree objects from component parsers */

                @Override
                public Event map(List<String> strings) {
                    return new Event(strings);
                }
            });

    /**
     * A parser for a list of events.
     */
    public static final Parser<List<Event>> events = 
            event                                         /* a single event ... */
            .sepEndBy(Scanners.WHITESPACES.many());       /* ... repeated zero or more times and separated by any amount of whitespace */

    public List<Event> parse(Reader reader) throws IOException {
        return events.parse(reader);
    }
}
