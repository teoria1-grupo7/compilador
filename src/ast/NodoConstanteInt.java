package ast;

import java.util.concurrent.atomic.AtomicInteger;

public class NodoConstanteInt extends NodoExpresion {
    private final int valor;

    public NodoConstanteInt(Number valor) {
        super("CTE");
        this.valor = valor.intValue();
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE: " + Integer.toString(valor);
    }

    @Override
    public String assemble(AtomicInteger auxCount) {
        return "_" + valor;
    }
}
