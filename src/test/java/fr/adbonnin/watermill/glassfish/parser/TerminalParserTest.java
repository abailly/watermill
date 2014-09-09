package fr.adbonnin.watermill.glassfish.parser;

import org.junit.Test;

public class TerminalParserTest {

	@Test
	public void testTerminalParser() {
		final String str =
				"[#|2014-09-04T11:10:15.964+0200|INFO|oracle-glassfish3.1.2|com.entreprise.ejbservice.AuthenticationServiceImpl|_ThreadID=24;_ThreadName=Thread-2;|Connexion autorisée (toto) depuis adresse : 127.0.0.1|#]\n\n" +
				"[#|2014-09-04T11:10:26.558+0200|WARNING|oracle-glassfish3.1.2|PERFORMANCES|_ThreadID=39;_ThreadName=Thread-2;|La méthode service.method s'est executée en 22582.0 ms |#]";
	}
}
