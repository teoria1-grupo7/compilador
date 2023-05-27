package ast;

import java.util.concurrent.atomic.AtomicInteger;

public class NodoDistinto extends NodoComparacion {

  public NodoDistinto(NodoExpresion left, NodoExpresion right) {
    super("<>", left, right);
  }

  @Override
  protected String assemble(AtomicInteger auxCount, Boolean doubleComp, Boolean inverse) {
    String comp = inverse ? "JNE" : "JE";
    return "fld " + getIzquierda().assemble(auxCount)
        + "\nfld " + getDerecha().assemble(auxCount)
        + "\nfcomp "
        + "\n" + comp;
  }
}
