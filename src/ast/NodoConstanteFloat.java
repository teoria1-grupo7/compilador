package ast;

public class NodoConstanteFloat extends NodoExpresion {
    private final float valor;

    public NodoConstanteFloat(Number valor) {
        super("CTE");
        this.valor = valor.floatValue();
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE: " + valor;
    }
}
