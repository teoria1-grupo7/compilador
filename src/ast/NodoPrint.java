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
        String assembled = valor.assemble(asm, auxCount);
        SymbolTableEntry symbolTableEntry = symbolTable.get(assembled);
        asm.append("\n");
        if (symbolTableEntry.getType().equals("STRING")) {
            asm.append("displayString ").append(assembled);
        }
        else {
            asm.append("displayFloat ").append(assembled).append(", 2");
        }
        asm.append("\n")
            .append("newLine")
            .append("\n");
        return "";
    }
}
