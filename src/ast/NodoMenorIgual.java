
package ast;


import java.util.concurrent.atomic.AtomicInteger;

public class NodoMenorIgual extends NodoComparacion {

        public NodoMenorIgual(NodoExpresion izquierda, NodoExpresion derecha) {
        super("<=", izquierda, derecha);
    }

  @Override
  protected String assemble(AtomicInteger auxCount, Boolean doubleComp, Boolean inverse) {
    String comp = inverse ? "JA" : "JBE";
    return "fld " + getIzquierda().assemble(auxCount)
        + "\nfld " + getDerecha().assemble(auxCount)
        + "\nfcomp "
        + "\n" + comp;
  }
}
