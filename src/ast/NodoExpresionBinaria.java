package ast;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class NodoExpresionBinaria extends NodoExpresion {
    private final NodoExpresion izquierda;
    private final NodoExpresion derecha;

    public NodoExpresionBinaria(String nombre, NodoExpresion izquierda, NodoExpresion derecha) {
        super(nombre);
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                izquierda.graficar(miId) +
                derecha.graficar(miId);
    }

    public NodoExpresion getIzquierda() {
        return izquierda;
    }

    public NodoExpresion getDerecha() {
        return derecha;
    }

    @Override
    public abstract String assemble(StringBuilder asm, AtomicInteger auxCount);
}
