package ast;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class NodoSentencia extends Nodo {

    public NodoSentencia(String nombre) {
        super(nombre);
    }

    public abstract String assemble(AtomicInteger auxCount);
}
