package compilador;
import java_cup.runtime.*;

%%

%public
%class Lexico
%implements sym
%unicode
%line
%column
%cup
%cupdebug

%{
  final int MAX_STRING = 30;
  final int MAX_INT = Short.MAX_VALUE;
  final float MAX_FLOAT = Float.MAX_VALUE;

  private Float verifyReal(String x) throws Exception {
      Float f = Float.parseFloat(x);
      if (f < -MAX_FLOAT || f > MAX_FLOAT) {
          throw new Exception("Real out of range");
      }
      return f;
  }
  private Integer verifyInt(String x) throws Exception {
      Integer i = Integer.parseInt(x);
      if (i < -MAX_INT || i > MAX_INT) {
          throw new Exception("Integer out of range");
      }
      return i;
  }
  private String verifyString(String x) throws Exception {
      x = x.substring(1, x.length()-1);
      if (x.length() > MAX_STRING) {
          throw new Exception("Maximum string length exceeded");
      }
      return x;
  }

  private Symbol symbol(int type) {
    return new JavaSymbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new JavaSymbol(type, yyline+1, yycolumn+1, value);
  }

  /**
   * Parecido a debug_next_token(), pero devuelve también la línea y la columna, la salida es más estética
   */
  public SymbolWrapper imprimirProximoToken() throws java.io.IOException {
    java_cup.runtime.Symbol s = next_token();
    System.out.println( "Linea: " + (yyline+1) + ", Columna: " + (yycolumn+1) + ", Token: " + getTokenName(s.sym) + ", Lexema: "+ yytext());
    return new SymbolWrapper(s, yyline+1, yycolumn+1);
  }

  /**
   * Igual getTokenName(), pero la salida es más estética
   */
  public String getNombreToken(int token) {
    return getTokenName(token);
  }
%}

InputCharacter = [^\r\n]

LineTerminator = \r|\n|\r\n

WhiteSpace = {LineTerminator} | [ \t\f]

/* COMENTARIOS */
Comment = "</" ~"/>"

Identifier = [a-zA-Z_][a-zA-Z_0-9]*

/* CONSTANTES ENTERAS */
DecIntegerLiteral = 0 | [1-9][0-9]*

/* CONSTANTES DECIMALES */
FloatLiteral = ({FLit1}|{FLit2})
FLit1    = [0-9]+ \. [0-9]*
FLit2    = \. [0-9]+

%%

<YYINITIAL> {

  /* COMENTARIOS */
  {Comment}                      {/**/}

  /* KEYWORDS */
  "BEGIN.PROGRAM"                { return symbol(BEGINPROGRAM); }
  "END.PROGRAM"                  { return symbol(ENDPROGRAM); }
  "DECLARE"                      { return symbol(DECLARE); }
  "ENDDECLARE"                   { return symbol(ENDDECLARE); }
  ([iI][nN][tT])                 { return symbol(INT); }
  ([fF][lL][oO][aA][tT])         { return symbol(FLOAT); }
  ([sS][tT][rR][iI][nN][gG])     { return symbol(STRING); }
  ([wW][hH][iI][lL][eE])         { return symbol(WHILE); }
  ([iI][fF])                     { return symbol(IF); }
  ([eE][lL][sS][eE])             { return symbol(ELSE); }
  ([pP][rR][iI][nN][tT])         { return symbol(PRINT); }
  ([bB][eE][tT][wW][eE][eE][nN]) { return symbol(BETWEEN); }
  "("                            { return symbol(LPAREN); }
  ")"                            { return symbol(RPAREN); }
  "{"                            { return symbol(LBRACE); }
  "}"                            { return symbol(RBRACE); }
  "["                            { return symbol(LBRACK); }
  "]"                            { return symbol(RBRACK); }
  ";"                            { return symbol(SEMICOLON); }
  ","                            { return symbol(COMMA); }

  /* OPERADORES */
  ([aA][nN][dD])                 { return symbol(AND); }
  ([oO][rR])                     { return symbol(OR); }
  "<"                            { return symbol(LT); }
  "<="                           { return symbol(LTEQ); }
  "=="                           { return symbol(EQEQ); }
  "<>"                           { return symbol(NOTEQ); }
  ">="                           { return symbol(GTEQ); }
  ">"                            { return symbol(GT); }
  "!"                            { return symbol(NOT); }
  ":="                           { return symbol(VARASSIGN); }
  "="                            { return symbol(ASSIGN); }
  "+"                            { return symbol(PLUS); }
  "-"                            { return symbol(MINUS); }
  "*"                            { return symbol(MULT); }
  "/"                            { return symbol(DIV); }

  /* CONSTANTES STRING */
  "\"" [^\"\n\r]* "\""           {
                                    try {
                                      return symbol(STRING_LITERAL, verifyString(yytext()));
                                    } catch (Exception e) {
                                      e.printStackTrace();
                                      return null;
                                    }
                                 }

  /* CONSTANTES ENTERAS */
  {DecIntegerLiteral}            {
                                    try {
                                      return symbol(INTEGER_LITERAL, verifyInt(yytext()));
                                    } catch (Exception e) {
                                      e.printStackTrace();
                                      return null;
                                    }
                                 }

  /* CONSTANTES DECIMALES */
  {FloatLiteral}                 {
                                    try {
                                      return symbol(FLOATING_POINT_LITERAL, verifyReal(yytext()));
                                    } catch (Exception e) {
                                      e.printStackTrace();
                                      return null;
                                    }
                                 }

  /* IDENTIFICADORES */
  {Identifier}                   { return symbol(IDENTIFIER, yytext()); }

  /* ESPACIOS */
  {WhiteSpace}                   { /**/ }
}

/* ERRORES */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+"\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(EOF); }
