// Generated from com\sourceclear\gramtest\bnf.g4 by ANTLR 4.12.0
package com.sourceclear.gramtest;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class bnfParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ID=1, ASSIGN=2, LPAREN=3, RPAREN=4, LBRACE=5, RBRACE=6, LEND=7, REND=8, 
		BAR=9, CAPTEXT=10, TEXT=11, STRINGLITERAL=12, WS=13;
	public static final int
		RULE_rulelist = 0, RULE_rule_ = 1, RULE_lhs = 2, RULE_rhs = 3, RULE_alternatives = 4, 
		RULE_alternative = 5, RULE_element = 6, RULE_optional = 7, RULE_zeroormore = 8, 
		RULE_oneormore = 9, RULE_captext = 10, RULE_text = 11, RULE_id = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"rulelist", "rule_", "lhs", "rhs", "alternatives", "alternative", "element", 
			"optional", "zeroormore", "oneormore", "captext", "text", "id"
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

	@Override
	public String getGrammarFileName() { return "bnf.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public bnfParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RulelistContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(bnfParser.EOF, 0); }
		public List<Rule_Context> rule_() {
			return getRuleContexts(Rule_Context.class);
		}
		public Rule_Context rule_(int i) {
			return getRuleContext(Rule_Context.class,i);
		}
		public RulelistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rulelist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterRulelist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitRulelist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitRulelist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulelistContext rulelist() throws RecognitionException {
		RulelistContext _localctx = new RulelistContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_rulelist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(26);
				rule_();
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(32);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Rule_Context extends ParserRuleContext {
		public LhsContext lhs() {
			return getRuleContext(LhsContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(bnfParser.ASSIGN, 0); }
		public RhsContext rhs() {
			return getRuleContext(RhsContext.class,0);
		}
		public Rule_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterRule_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitRule_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitRule_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rule_Context rule_() throws RecognitionException {
		Rule_Context _localctx = new Rule_Context(_ctx, getState());
		enterRule(_localctx, 2, RULE_rule_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			lhs();
			setState(35);
			match(ASSIGN);
			setState(36);
			rhs();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LhsContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public LhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lhs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterLhs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitLhs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitLhs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LhsContext lhs() throws RecognitionException {
		LhsContext _localctx = new LhsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_lhs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RhsContext extends ParserRuleContext {
		public AlternativesContext alternatives() {
			return getRuleContext(AlternativesContext.class,0);
		}
		public RhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rhs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterRhs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitRhs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitRhs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RhsContext rhs() throws RecognitionException {
		RhsContext _localctx = new RhsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_rhs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			alternatives();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlternativesContext extends ParserRuleContext {
		public List<AlternativeContext> alternative() {
			return getRuleContexts(AlternativeContext.class);
		}
		public AlternativeContext alternative(int i) {
			return getRuleContext(AlternativeContext.class,i);
		}
		public List<TerminalNode> BAR() { return getTokens(bnfParser.BAR); }
		public TerminalNode BAR(int i) {
			return getToken(bnfParser.BAR, i);
		}
		public AlternativesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternatives; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterAlternatives(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitAlternatives(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitAlternatives(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlternativesContext alternatives() throws RecognitionException {
		AlternativesContext _localctx = new AlternativesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_alternatives);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			alternative();
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BAR) {
				{
				{
				setState(43);
				match(BAR);
				setState(44);
				alternative();
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlternativeContext extends ParserRuleContext {
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public AlternativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternative; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterAlternative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitAlternative(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitAlternative(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlternativeContext alternative() throws RecognitionException {
		AlternativeContext _localctx = new AlternativeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_alternative);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(50);
					element();
					}
					} 
				}
				setState(55);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementContext extends ParserRuleContext {
		public OptionalContext optional() {
			return getRuleContext(OptionalContext.class,0);
		}
		public ZeroormoreContext zeroormore() {
			return getRuleContext(ZeroormoreContext.class,0);
		}
		public OneormoreContext oneormore() {
			return getRuleContext(OneormoreContext.class,0);
		}
		public TextContext text() {
			return getRuleContext(TextContext.class,0);
		}
		public CaptextContext captext() {
			return getRuleContext(CaptextContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_element);
		try {
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEND:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				optional();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				zeroormore();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				oneormore();
				}
				break;
			case TEXT:
			case STRINGLITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(59);
				text();
				}
				break;
			case CAPTEXT:
				enterOuterAlt(_localctx, 5);
				{
				setState(60);
				captext();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 6);
				{
				setState(61);
				id();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OptionalContext extends ParserRuleContext {
		public TerminalNode LEND() { return getToken(bnfParser.LEND, 0); }
		public AlternativesContext alternatives() {
			return getRuleContext(AlternativesContext.class,0);
		}
		public TerminalNode REND() { return getToken(bnfParser.REND, 0); }
		public OptionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterOptional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitOptional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitOptional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionalContext optional() throws RecognitionException {
		OptionalContext _localctx = new OptionalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_optional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(LEND);
			setState(65);
			alternatives();
			setState(66);
			match(REND);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ZeroormoreContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(bnfParser.LBRACE, 0); }
		public AlternativesContext alternatives() {
			return getRuleContext(AlternativesContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(bnfParser.RBRACE, 0); }
		public ZeroormoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_zeroormore; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterZeroormore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitZeroormore(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitZeroormore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ZeroormoreContext zeroormore() throws RecognitionException {
		ZeroormoreContext _localctx = new ZeroormoreContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_zeroormore);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(LBRACE);
			setState(69);
			alternatives();
			setState(70);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OneormoreContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(bnfParser.LPAREN, 0); }
		public AlternativesContext alternatives() {
			return getRuleContext(AlternativesContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(bnfParser.RPAREN, 0); }
		public OneormoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oneormore; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterOneormore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitOneormore(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitOneormore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OneormoreContext oneormore() throws RecognitionException {
		OneormoreContext _localctx = new OneormoreContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_oneormore);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(LPAREN);
			setState(73);
			alternatives();
			setState(74);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaptextContext extends ParserRuleContext {
		public TerminalNode CAPTEXT() { return getToken(bnfParser.CAPTEXT, 0); }
		public CaptextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_captext; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterCaptext(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitCaptext(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitCaptext(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaptextContext captext() throws RecognitionException {
		CaptextContext _localctx = new CaptextContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_captext);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(CAPTEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TextContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(bnfParser.TEXT, 0); }
		public TerminalNode STRINGLITERAL() { return getToken(bnfParser.STRINGLITERAL, 0); }
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_text);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_la = _input.LA(1);
			if ( !(_la==TEXT || _la==STRINGLITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(bnfParser.ID, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bnfListener ) ((bnfListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bnfVisitor ) return ((bnfVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\rS\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0005\u0000\u001c\b\u0000\n\u0000\f\u0000\u001f"+
		"\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0005\u0004.\b\u0004\n\u0004\f\u00041\t\u0004\u0001"+
		"\u0005\u0005\u00054\b\u0005\n\u0005\f\u00057\t\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006?\b"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0000\u0000\r\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000\u0001\u0001\u0000"+
		"\u000b\fM\u0000\u001d\u0001\u0000\u0000\u0000\u0002\"\u0001\u0000\u0000"+
		"\u0000\u0004&\u0001\u0000\u0000\u0000\u0006(\u0001\u0000\u0000\u0000\b"+
		"*\u0001\u0000\u0000\u0000\n5\u0001\u0000\u0000\u0000\f>\u0001\u0000\u0000"+
		"\u0000\u000e@\u0001\u0000\u0000\u0000\u0010D\u0001\u0000\u0000\u0000\u0012"+
		"H\u0001\u0000\u0000\u0000\u0014L\u0001\u0000\u0000\u0000\u0016N\u0001"+
		"\u0000\u0000\u0000\u0018P\u0001\u0000\u0000\u0000\u001a\u001c\u0003\u0002"+
		"\u0001\u0000\u001b\u001a\u0001\u0000\u0000\u0000\u001c\u001f\u0001\u0000"+
		"\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001d\u001e\u0001\u0000"+
		"\u0000\u0000\u001e \u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000\u0000"+
		"\u0000 !\u0005\u0000\u0000\u0001!\u0001\u0001\u0000\u0000\u0000\"#\u0003"+
		"\u0004\u0002\u0000#$\u0005\u0002\u0000\u0000$%\u0003\u0006\u0003\u0000"+
		"%\u0003\u0001\u0000\u0000\u0000&\'\u0003\u0018\f\u0000\'\u0005\u0001\u0000"+
		"\u0000\u0000()\u0003\b\u0004\u0000)\u0007\u0001\u0000\u0000\u0000*/\u0003"+
		"\n\u0005\u0000+,\u0005\t\u0000\u0000,.\u0003\n\u0005\u0000-+\u0001\u0000"+
		"\u0000\u0000.1\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000/0\u0001"+
		"\u0000\u0000\u00000\t\u0001\u0000\u0000\u00001/\u0001\u0000\u0000\u0000"+
		"24\u0003\f\u0006\u000032\u0001\u0000\u0000\u000047\u0001\u0000\u0000\u0000"+
		"53\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u00006\u000b\u0001\u0000"+
		"\u0000\u000075\u0001\u0000\u0000\u00008?\u0003\u000e\u0007\u00009?\u0003"+
		"\u0010\b\u0000:?\u0003\u0012\t\u0000;?\u0003\u0016\u000b\u0000<?\u0003"+
		"\u0014\n\u0000=?\u0003\u0018\f\u0000>8\u0001\u0000\u0000\u0000>9\u0001"+
		"\u0000\u0000\u0000>:\u0001\u0000\u0000\u0000>;\u0001\u0000\u0000\u0000"+
		"><\u0001\u0000\u0000\u0000>=\u0001\u0000\u0000\u0000?\r\u0001\u0000\u0000"+
		"\u0000@A\u0005\u0007\u0000\u0000AB\u0003\b\u0004\u0000BC\u0005\b\u0000"+
		"\u0000C\u000f\u0001\u0000\u0000\u0000DE\u0005\u0005\u0000\u0000EF\u0003"+
		"\b\u0004\u0000FG\u0005\u0006\u0000\u0000G\u0011\u0001\u0000\u0000\u0000"+
		"HI\u0005\u0003\u0000\u0000IJ\u0003\b\u0004\u0000JK\u0005\u0004\u0000\u0000"+
		"K\u0013\u0001\u0000\u0000\u0000LM\u0005\n\u0000\u0000M\u0015\u0001\u0000"+
		"\u0000\u0000NO\u0007\u0000\u0000\u0000O\u0017\u0001\u0000\u0000\u0000"+
		"PQ\u0005\u0001\u0000\u0000Q\u0019\u0001\u0000\u0000\u0000\u0004\u001d"+
		"/5>";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}