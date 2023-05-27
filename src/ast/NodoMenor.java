
package ast;


import java.util.concurrent.atomic.AtomicInteger;

public class NodoMenor extends NodoComparacion {
        
        public NodoMenor (NodoExpresion izquierda, NodoExpresion derecha) {
        super("<", izquierda, derecha);
    }

  @Override
  protected String assemble(AtomicInteger auxCount, Boolean doubleComp, Boolean inverse) {
    String comp = inverse ? "JAE" : "JB";
    return "fld " + getIzquierda().assemble(auxCount)
        + "\nfld " + getDerecha().assemble(auxCount)
        + "\nfcomp "
        + "\n" + comp;
  }
}
