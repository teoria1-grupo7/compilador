package ast;

import java.util.concurrent.atomic.AtomicInteger;

public class NodoSuma extends NodoExpresionBinaria {

    public NodoSuma(NodoExpresion izquierda, NodoExpresion derecha) {
        super("+", izquierda, derecha);
    }

    @Override
    public String assemble(AtomicInteger auxCount) {
        return getIzquierda().assemble(auxCount) +
             "\nfld " + getDerecha().assemble(auxCount) +
             "\nfadd" +
             "\nfstp @aux" + auxCount.get() +
             "\nfld @aux" + auxCount.getAndIncrement();
    }
}