package ast;

import java.util.concurrent.atomic.AtomicInteger;

public class NodoConstanteString extends NodoExpresion {
    private final String valor;

    public NodoConstanteString(String valor) {
        super("CTE");
        this.valor = valor;
    }

    @Override
    public String assemble(StringBuilder asm, AtomicInteger auxCount) {
        return "_" + valor;
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE: " + valor;
    }
}
