package ast;


import java.util.concurrent.atomic.AtomicInteger;

public class NodoAnd extends NodoExpresionBooleana {
        
       private final NodoExpresionBooleana izquierda;
       private final NodoExpresionBooleana derecha;

public NodoAnd (NodoExpresionBooleana izquierda, NodoExpresionBooleana derecha) {
        super("AND");
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
        "\n" + izquierda.assemble(auxCount, Boolean.TRUE, Boolean.TRUE) + " else_part"
     +  "\n" + derecha.assemble(auxCount, Boolean.TRUE, Boolean.FALSE) + " else_part";
  }
}

