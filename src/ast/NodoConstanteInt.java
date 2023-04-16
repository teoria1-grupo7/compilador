package ast;

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
}
