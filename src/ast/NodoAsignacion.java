package ast;

import java.util.concurrent.atomic.AtomicInteger;

public class NodoAsignacion extends NodoSentencia {
    private final NodoIdentificador identificador;
    private final NodoExpresion expresion;

    public NodoAsignacion(NodoIdentificador identificador, NodoExpresion expresion) {
        super(":=");
        this.identificador = identificador;
        this.expresion = expresion;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                identificador.graficar(miId) +
                expresion.graficar(miId);
    }

    @Override
    public String assemble(StringBuilder asm, AtomicInteger auxCount) {
        String expResult = expresion.assemble(asm, auxCount);
        String idResult = identificador.assemble(asm, auxCount);
        asm.append("\n");
        asm.append("fld ").append(expResult).append("\n")
            .append("fstp ").append(idResult)
            .append("\n");
        return "";
    }
}
