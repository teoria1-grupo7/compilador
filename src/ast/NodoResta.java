package ast;

import java.util.concurrent.atomic.AtomicInteger;

public class NodoResta extends NodoExpresionBinaria {

    public NodoResta(NodoExpresion izquierda, NodoExpresion derecha) {
        super("-", izquierda, derecha);
    }

    @Override
    public String assemble(AtomicInteger auxCount) {
        return getIzquierda().assemble(auxCount) +
            "\nfld " + getDerecha().assemble(auxCount) +
            "\nfsub" +
            "\nfstp @aux" + auxCount.get() +
            "\nfld @aux" + auxCount.getAndIncrement();
    }
}