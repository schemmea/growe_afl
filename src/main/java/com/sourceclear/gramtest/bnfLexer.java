// Generated from com\sourceclear\gramtest\bnf.g4 by ANTLR 4.12.0
package com.sourceclear.gramtest;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class bnfLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ID=1, ASSIGN=2, LPAREN=3, RPAREN=4, LBRACE=5, RBRACE=6, LEND=7, REND=8, 
		BAR=9, CAPTEXT=10, TEXT=11, STRINGLITERAL=12, WS=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ID", "ASSIGN", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LEND", "REND", 
			"BAR", "CAPTEXT", "TEXT", "STRINGLITERAL", "UPPERCASE_LETTER", "LOWERCASE_LETTER", 
			"DIGIT", "SYMBOL", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'::='", "'('", "')'", "'{'", "'}'", "'['", "']'", "'|'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ID", "ASSIGN", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LEND", 
			"REND", "BAR", "CAPTEXT", "TEXT", "STRINGLITERAL", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public bnfLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "bnf.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\r^\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0005"+
		"\u0000&\b\u0000\n\u0000\f\u0000)\t\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0004\nF\b\n\u000b\n\f\nG\u0001"+
		"\u000b\u0001\u000b\u0005\u000bL\b\u000b\n\u000b\f\u000bO\t\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0002\'M\u0000\u0011\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017"+
		"\f\u0019\u0000\u001b\u0000\u001d\u0000\u001f\u0000!\r\u0001\u0000\u0002"+
		"\b\u0000!\'*/:@^`\u00a1\u00ff\u0152\u0192\u2013\u2122\u2190\u22ff\u0003"+
		"\u0000\t\n\r\r  _\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000"+
		"\u0000\u0000!\u0001\u0000\u0000\u0000\u0001#\u0001\u0000\u0000\u0000\u0003"+
		",\u0001\u0000\u0000\u0000\u00050\u0001\u0000\u0000\u0000\u00072\u0001"+
		"\u0000\u0000\u0000\t4\u0001\u0000\u0000\u0000\u000b6\u0001\u0000\u0000"+
		"\u0000\r8\u0001\u0000\u0000\u0000\u000f:\u0001\u0000\u0000\u0000\u0011"+
		"<\u0001\u0000\u0000\u0000\u0013>\u0001\u0000\u0000\u0000\u0015E\u0001"+
		"\u0000\u0000\u0000\u0017I\u0001\u0000\u0000\u0000\u0019R\u0001\u0000\u0000"+
		"\u0000\u001bT\u0001\u0000\u0000\u0000\u001dV\u0001\u0000\u0000\u0000\u001f"+
		"X\u0001\u0000\u0000\u0000!Z\u0001\u0000\u0000\u0000#\'\u0005<\u0000\u0000"+
		"$&\t\u0000\u0000\u0000%$\u0001\u0000\u0000\u0000&)\u0001\u0000\u0000\u0000"+
		"\'(\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000(*\u0001\u0000\u0000"+
		"\u0000)\'\u0001\u0000\u0000\u0000*+\u0005>\u0000\u0000+\u0002\u0001\u0000"+
		"\u0000\u0000,-\u0005:\u0000\u0000-.\u0005:\u0000\u0000./\u0005=\u0000"+
		"\u0000/\u0004\u0001\u0000\u0000\u000001\u0005(\u0000\u00001\u0006\u0001"+
		"\u0000\u0000\u000023\u0005)\u0000\u00003\b\u0001\u0000\u0000\u000045\u0005"+
		"{\u0000\u00005\n\u0001\u0000\u0000\u000067\u0005}\u0000\u00007\f\u0001"+
		"\u0000\u0000\u000089\u0005[\u0000\u00009\u000e\u0001\u0000\u0000\u0000"+
		":;\u0005]\u0000\u0000;\u0010\u0001\u0000\u0000\u0000<=\u0005|\u0000\u0000"+
		"=\u0012\u0001\u0000\u0000\u0000>?\u0003\u0019\f\u0000?@\u0003\u0015\n"+
		"\u0000@\u0014\u0001\u0000\u0000\u0000AF\u0003\u0019\f\u0000BF\u0003\u001b"+
		"\r\u0000CF\u0003\u001d\u000e\u0000DF\u0003\u001f\u000f\u0000EA\u0001\u0000"+
		"\u0000\u0000EB\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000ED\u0001"+
		"\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000"+
		"GH\u0001\u0000\u0000\u0000H\u0016\u0001\u0000\u0000\u0000IM\u0005\"\u0000"+
		"\u0000JL\t\u0000\u0000\u0000KJ\u0001\u0000\u0000\u0000LO\u0001\u0000\u0000"+
		"\u0000MN\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000NP\u0001\u0000"+
		"\u0000\u0000OM\u0001\u0000\u0000\u0000PQ\u0005\"\u0000\u0000Q\u0018\u0001"+
		"\u0000\u0000\u0000RS\u0002AZ\u0000S\u001a\u0001\u0000\u0000\u0000TU\u0002"+
		"az\u0000U\u001c\u0001\u0000\u0000\u0000VW\u000209\u0000W\u001e\u0001\u0000"+
		"\u0000\u0000XY\u0007\u0000\u0000\u0000Y \u0001\u0000\u0000\u0000Z[\u0007"+
		"\u0001\u0000\u0000[\\\u0001\u0000\u0000\u0000\\]\u0006\u0010\u0000\u0000"+
		"]\"\u0001\u0000\u0000\u0000\u0005\u0000\'EGM\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}