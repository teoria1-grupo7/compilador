package compilador;

import java_cup.runtime.Symbol;

public class SymbolWrapper {
    private Symbol symbol;
    private int line;
    private int column;

    public SymbolWrapper(Symbol symbol, int line, int column) {
        this.symbol = symbol;
        this.line = line;
        this.column = column;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getLine() {
        return line;
    }
    public int getColumn() {
        return column;
    }
}
