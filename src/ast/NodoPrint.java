package ast;

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
}
