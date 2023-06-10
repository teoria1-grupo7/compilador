package ast;


import java.util.concurrent.atomic.AtomicInteger;

public class NodoOr extends NodoExpresionBooleana {
private final NodoExpresionBooleana izquierda;
private final NodoExpresionBooleana derecha;

public NodoOr (NodoExpresionBooleana izquierda, NodoExpresionBooleana derecha) {
        super("OR");
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

  @Override
  protected String assemble(StringBuilder asm, AtomicInteger auxCount, Boolean inverse, String jumpToLeft, String jumpToRight) {
    izquierda.assemble(asm, auxCount, Boolean.FALSE, null, jumpToRight);
    derecha.assemble(asm, auxCount, Boolean.TRUE, jumpToLeft, null);
    return "";
  }
}

