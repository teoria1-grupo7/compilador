package ast;

import java.util.concurrent.atomic.AtomicInteger;

public class NodoPrint extends NodoSentencia {
    private final NodoConstanteString valor;

    public NodoPrint(NodoConstanteString valor) {
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
    public String assemble(AtomicInteger auxCount) {
        return "displayString " + valor.assemble(auxCount);
    }
}
