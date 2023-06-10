package ast;

import compilador.SymbolTableEntry;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class NodoPrint extends NodoSentencia {
    private final NodoExpresion valor;

    public NodoPrint(NodoExpresion valor) {
        super("PRINT");
        this.valor = valor;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
            valor.graficar(miId);
    }

    @Override
    public String assemble(StringBuilder asm, HashMap<String, SymbolTableEntry> symbolTable,
        AtomicInteger auxCount) {
        asm.append("\n")
           .append("displayString ").append(valor.assemble(asm, auxCount))
           .append("\n")
           .append("newLine")
           .append("\n");
        return "";
    }
}
