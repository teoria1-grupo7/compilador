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
            SymbolTableEntry symbolTableEntry = new SymbolTableEntry(lexico.getNombreToken(sym.IDENTIFIER), dataType, null, null);
            symbolTable.put(var, symbolTableEntry);
        });
        varNames = new ArrayList<>();
    }

}
