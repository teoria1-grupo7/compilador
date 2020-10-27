package compilador;

public class JavaSymbol extends java_cup.runtime.Symbol {
  private int line;
  private int column;

  public void setLine(int line) {
    this.line = line;
  }

  public int getLine() {
    return line;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getColumn() {
    return column;
  }

  public JavaSymbol(int type, int line, int column) {
    this(type, line, column, -1, -1, null);
  }

  public JavaSymbol(int type, int line, int column, Object value) {
    this(type, line, column, -1, -1, value);
  }

  public JavaSymbol(int type, int line, int column, int left, int right, Object value) {
    super(type, left, right, value);
    this.line = line;
    this.column = column;
  }

  public String toString() {
    return "Simbolo: "+sym+(value == null ? "" : (", Valor: '"+value+"'"))+"\n";
  }
}
