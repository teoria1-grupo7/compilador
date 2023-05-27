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
  protected String assemble(AtomicInteger auxCount, Boolean doubleComp, Boolean inverse) {
    return
        "\n " + izquierda.assemble(auxCount, Boolean.TRUE, Boolean.FALSE) + " then_part"
     +  "\n " + derecha.assemble(auxCount, Boolean.TRUE, Boolean.TRUE) + " else_part";
  }
}

