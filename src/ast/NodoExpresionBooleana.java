package ast;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class NodoExpresionBooleana extends Nodo {
     
     public NodoExpresionBooleana(String nombre) {
        super(nombre);
    }

  protected abstract String assemble(AtomicInteger auxCount, Boolean doubleComp, Boolean inverse);

}

    
    
    
