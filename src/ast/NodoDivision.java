package ast;

import java.util.concurrent.atomic.AtomicInteger;

public class NodoDivision extends NodoExpresionBinaria {

    public NodoDivision(NodoExpresion izquierda, NodoExpresion derecha) {
        super("/", izquierda, derecha);
    }

    @Override
    public String assemble(AtomicInteger auxCount) {
        return getIzquierda().assemble(auxCount)
            + "\nfdiv " + getDerecha().assemble(auxCount)
            + "\nfstp @aux" + auxCount.get()
            + "\nfld @aux" + auxCount.getAndIncrement() + "\n";
    }
}
