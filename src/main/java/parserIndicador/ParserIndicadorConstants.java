/* Generated By:JavaCC: Do not edit this line. ParserIndicadorConstants.java */
package parserIndicador;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface ParserIndicadorConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SUMA = 5;
  /** RegularExpression Id. */
  int RESTA = 6;
  /** RegularExpression Id. */
  int MULTIPLICACION = 7;
  /** RegularExpression Id. */
  int DIVISION = 8;
  /** RegularExpression Id. */
  int PARENTIZQ = 9;
  /** RegularExpression Id. */
  int PARENTDER = 10;
  /** RegularExpression Id. */
  int FINALIZACION = 11;
  /** RegularExpression Id. */
  int NUMERO = 12;
  /** RegularExpression Id. */
  int PALABRA = 13;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"(\"",
    "\")\"",
    "\";\"",
    "<NUMERO>",
    "<PALABRA>",
  };

}
