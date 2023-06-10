package ast;

import compilador.SymbolTableEntry;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class NodoSentencia extends Nodo {

    public NodoSentencia(String nombre) {
        super(nombre);
    }

    public abstract String assemble(StringBuilder resultado,  HashMap<String, SymbolTableEntry> symbolTable, AtomicInteger auxCount);
}
