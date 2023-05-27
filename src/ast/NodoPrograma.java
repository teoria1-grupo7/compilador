package ast;

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

    public String assemble() {
        StringBuilder resultado = new StringBuilder();
        // Agregar header y tabla de simbolos?
        AtomicInteger auxCount = new AtomicInteger(0);
        for (NodoSentencia sentencia : sentencias) {
            resultado.append(sentencia.assemble(auxCount)).append("\n");
        }
        // Agregar footer?
        return resultado.toString();
    }
}

