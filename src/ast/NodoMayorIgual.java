
package ast;


import java.util.concurrent.atomic.AtomicInteger;

public class NodoMayorIgual extends NodoComparacion {

        public NodoMayorIgual(NodoExpresion izquierda, NodoExpresion derecha) {
        super(">=", izquierda, derecha);
    }

  @Override
  protected String assemble(AtomicInteger auxCount, Boolean doubleComp, Boolean inverse) {
    String comp = inverse ? "JB" : "JAE";
    return "fld " + getIzquierda().assemble(auxCount)
        + "\nfld " + getDerecha().assemble(auxCount)
        + "\nfcomp "
        + "\n" + comp;
  }
}
