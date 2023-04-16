
package ast;


public class NodoMayorIgual extends NodoComparacion {

        public NodoMayorIgual(NodoExpresion izquierda, NodoExpresion derecha) {
        super(">=", izquierda, derecha);
    }
}
