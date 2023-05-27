package ast;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class NodoExpresion extends Nodo {

    public NodoExpresion(String nombre) {
        super(nombre);
    }

  public abstract String assemble(AtomicInteger auxCount);

}
