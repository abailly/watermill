package fr.adbonnin.watermill.glassfish.parser;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TerminalParserTest {

    @Test
    public void canParseASingleComponent() throws Exception {
        assertThat(LogParser.component.parse(" oracle-glassfish3.1.2"),is(" oracle-glassfish3.1.2"));
    }

    @Test
    public void canParseSeveralComponents() throws Exception {
        assertThat(LogParser.components.parse(" oracle-glassfish3.1.2|com.entreprise.ejbservice.AuthenticationServiceImpl|_ThreadID=24;_ThreadName=Thread-2;")
                , Matchers.<List<String>>is(Lists.newArrayList(" oracle-glassfish3.1.2", "com.entreprise.ejbservice.AuthenticationServiceImpl", "_ThreadID=24;_ThreadName=Thread-2;")));
    }

    @Test
    public void canParseASingleEvent() throws Exception {
        final String str =
                "[#|2014-09-04T11:10:15.964+0200|INFO|oracle-glassfish3.1.2|com.entreprise.ejbservice.AuthenticationServiceImpl|_ThreadID=24;_ThreadName=Thread-2;|Connexion autorisée (toto) depuis adresse : 127.0.0.1|#]";

        assertThat(LogParser.event.parse(str), is(new Event("2014-09-04T11:10:15.964+0200", "INFO", "oracle-glassfish3.1.2", "com.entreprise.ejbservice.AuthenticationServiceImpl", "_ThreadID=24;_ThreadName=Thread-2;", "Connexion autorisée (toto) depuis adresse : 127.0.0.1")));

    }

    @Test
    public void canParseLogAsAListOfEvents() throws IOException {
        final String str =
                "[#|2014-09-04T11:10:15.964+0200|INFO|oracle-glassfish3.1.2|com.entreprise.ejbservice.AuthenticationServiceImpl|_ThreadID=24;_ThreadName=Thread-2;|Connexion autorisée (toto) depuis adresse : 127.0.0.1|#]\n\n" +
                        "[#|2014-09-04T11:10:26.558+0200|WARNING|oracle-glassfish3.1.2|PERFORMANCES|_ThreadID=39;_ThreadName=Thread-2;|La méthode service.method s'est executée en 22582.0 ms |#]";

        List<Event> events = new LogParser().parse(new StringReader(str));

        assertThat(events, Matchers.<List<Event>>is(Lists.newArrayList(new Event("2014-09-04T11:10:15.964+0200", "INFO", "oracle-glassfish3.1.2", "com.entreprise.ejbservice.AuthenticationServiceImpl", "_ThreadID=24;_ThreadName=Thread-2;", "Connexion autorisée (toto) depuis adresse : 127.0.0.1")
                , new Event("2014-09-04T11:10:26.558+0200", "WARNING", "oracle-glassfish3.1.2", "PERFORMANCES", "_ThreadID=39;_ThreadName=Thread-2;", "La méthode service.method s'est executée en 22582.0 ms "))));
    }
}
