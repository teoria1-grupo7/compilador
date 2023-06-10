package ast;

import static helper.AssemblerHelper.buildCodeHeader;
import static helper.AssemblerHelper.buildFooter;
import static helper.AssemblerHelper.buildHeader;
import static helper.AssemblerHelper.buildDataSection;

import compilador.SymbolTableEntry;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NodoPrograma extends Nodo {
    private final List<NodoSentencia> sentencias;

    public NodoPrograma(List<NodoSentencia> sentencias) {
        super("PGM");
        this.sentencias = sentencias;
    }

    public String graficar() {
        // Acá se dispara la invocación a los métodos graficar() de los nodos.
        // Como un NodoPrograma no tiene padre, se inicia pasando null.
        return this.graficar(null);
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = "nodo_programa";

        StringBuilder resultado = new StringBuilder();
        resultado.append("graph G {");

        resultado.append(miId + " [label=\"Programa\"]\n");
        for (NodoSentencia sentencia : this.sentencias) {
            resultado.append(sentencia.graficar(miId));
        }

        resultado.append("}");

        return resultado.toString();
    }

    public String assemble(HashMap<String, SymbolTableEntry> symbolTable) {
        StringBuilder codeAssembler = new StringBuilder();
        AtomicInteger auxCount = new AtomicInteger(0);
        for (NodoSentencia node : sentencias) {
            node.assemble(codeAssembler, symbolTable, auxCount);
        }

        return buildHeader()
            + "\n; vars from symbol table and aux vars\n"
            + buildDataSection(symbolTable, auxCount.get())
            + "\n; program instructions\n"
            + buildCodeHeader()
            + codeAssembler
            + "\n; end of execution\n"
            + buildFooter();
    }
}

