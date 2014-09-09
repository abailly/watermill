package fr.adbonnin.watermill.glassfish.parser;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.misc.Mapper;

public class TerminalParser {

	private static final Terminals SEPARATOR = Terminals.operators("|");

	static Parser<?> term(String name) {
		return Mapper._(SEPARATOR.token(name));
	}
}
