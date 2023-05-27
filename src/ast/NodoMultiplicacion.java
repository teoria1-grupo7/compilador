package ast;

import java.util.concurrent.atomic.AtomicInteger;

public class NodoMultiplicacion extends NodoExpresionBinaria {

    public NodoMultiplicacion(NodoExpresion izquierda, NodoExpresion derecha) {
        super("*", izquierda, derecha);
    }

    @Override
    public String assemble(AtomicInteger auxCount) {
        return getIzquierda().assemble(auxCount) +
            "\nfld " + getDerecha().assemble(auxCount) +
            "\nfmul" +
            "\nfstp @aux" + auxCount.get() +
            "\nfld @aux" + auxCount.getAndIncrement();
    }
}
