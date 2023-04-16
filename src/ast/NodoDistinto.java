package ast;

public class NodoDistinto extends NodoComparacion {

  public NodoDistinto(NodoExpresion left, NodoExpresion right) {
    super("<>", left, right);
  }
}
