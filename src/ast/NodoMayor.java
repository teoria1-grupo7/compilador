
package ast;


import java.util.concurrent.atomic.AtomicInteger;

public class NodoMayor extends NodoComparacion {

        public NodoMayor(NodoExpresion izquierda, NodoExpresion derecha) {
        super(">", izquierda, derecha);
    }

  @Override
  protected String assemble(AtomicInteger auxCount, Boolean doubleComp, Boolean inverse) {
    String comp = inverse ? "JBE" : "JA";
    return "fld " + getIzquierda().assemble(auxCount)
        + "\nfld " + getDerecha().assemble(auxCount)
        + "\nfcomp "
        + "\n" + comp;
  }
}
