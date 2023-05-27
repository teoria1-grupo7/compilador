
package ast;


import java.util.concurrent.atomic.AtomicInteger;

public class NodoNot extends NodoExpresionBooleana {
  private final NodoExpresionBooleana izquierda;

  public NodoNot (NodoExpresionBooleana izquierda) {
        super("NOT");
        this.izquierda = izquierda;
    }

  @Override
   protected String graficar(String idPadre) {
      final String miId = this.getIdNodo();
      return super.graficar(idPadre) +
              izquierda.graficar(miId) ;
  }

  @Override
  protected String assemble(AtomicInteger auxCount, Boolean doubleComp, Boolean inverse) {
    return null;
  }
}

