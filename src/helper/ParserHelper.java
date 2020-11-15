package helper;

import compilador.Lexico;
import compilador.SymbolTableEntry;
import compilador.sym;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ParserHelper {

    private final Lexico lexico;
    private HashMap<String, SymbolTableEntry> symbolTable = new HashMap();

    private Stack<Integer> types = new Stack<>();

    private List<String> varNames = new ArrayList<>();

    public ParserHelper (Lexico lexico) {
        this.lexico = lexico;
    }

    public void setSymbolTable(HashMap<String, SymbolTableEntry> symbolTable){
        this.symbolTable = symbolTable;
    }

    public HashMap getSymbolTable() {
        return this.symbolTable;
    }

    public void typeDef(Integer type){
        types.push(type);
    }

    public void varDef(String name){
        varNames.add(name);
    }

    public void loadVarsAndTypes(){
        varNames.forEach(var -> {
            Integer dataType = types.pop();
            SymbolTableEntry symbolTableEntry = new SymbolTableEntry(lexico.getNombreToken(sym.IDENTIFIER),
                    lexico.getNombreToken(dataType), null, null);
            symbolTable.put(var, symbolTableEntry);
        });

        varNames = new ArrayList<>();
    }

    public void addCteInt(Number cte) {
        String cteNumString = Integer.toString(cte.intValue());
        String string_literal_aux = "_"  + cteNumString;
        SymbolTableEntry symbolTableEntry =
                new SymbolTableEntry(lexico.getNombreToken(sym.INTEGER_LITERAL), lexico.getNombreToken(sym.INT), cteNumString, null);
        symbolTable.put(string_literal_aux, symbolTableEntry);
    }

    public void addCteFloat(Number cte) {
        String cteNumString = Double.toString(cte.doubleValue());
        String string_literal_aux = "_"  + cteNumString;
        SymbolTableEntry symbolTableEntry =
                new SymbolTableEntry(lexico.getNombreToken(sym.FLOATING_POINT_LITERAL), null, cteNumString, null);
        symbolTable.put(string_literal_aux, symbolTableEntry);
    }

    public void addCteString(String cte){
        String string_literal_aux = "_"  + cte;
        SymbolTableEntry symbolTableEntry =
                new SymbolTableEntry(lexico.getNombreToken(sym.STRING_LITERAL), null, cte, cte.length());
        symbolTable.put(string_literal_aux, symbolTableEntry);
    }

}
