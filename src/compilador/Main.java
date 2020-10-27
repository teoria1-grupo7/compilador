package compilador;
import java_cup.runtime.Symbol;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import java.io.IOException;

public class Main {
    private final static Path PRUEBA_PATH = Paths.get("prueba.txt");
    private final static Path TS_PATH = Paths.get("ts.txt");

    private static HashMap<String, SymbolTableEntry> symbolTable = new HashMap<>();

    public static void outputSymbolTable() {
        try {
            PrintWriter pw =  new PrintWriter(Files.newOutputStream(TS_PATH));
            String fmt = "%31s%31s%10s%31s%31s\n";
            pw.printf(fmt, "NOMBRE", "TOKEN", "TIPO", "VALOR", "LONGITUD");
            symbolTable.entrySet().forEach( entry -> {
                pw.printf(fmt, entry.getKey(), entry.getValue().getToken(), entry.getValue().getType(), entry.getValue().getVal(), entry.getValue().getLen());
            });
            pw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        try {
            BufferedReader br = Files.newBufferedReader(PRUEBA_PATH);
            Lexico sc = new Lexico(br);
            try {
                SymbolWrapper sw = sc.imprimirProximoToken();
                Symbol s = sw.getSymbol();
                while (s.sym != sym.EOF) {
                    System.out.println("Linea: " + sw.getLine() + ", Columna: " + sw.getColumn() + ", Token: " + sc.getNombreToken(s.sym) + ", Lexema: "+ sc.yytext() + "\n");
                    switch(s.sym) {
                        case sym.IDENTIFIER:
                            if (!symbolTable.containsKey(s.value.toString())) {
                                symbolTable.put(s.value.toString(), new SymbolTableEntry(sc.getNombreToken(s.sym), null));
                            };
                            break;
                        case sym.STRING_LITERAL:
                            String string_literal_aux = "_" + s.value.toString();
                            if (!symbolTable.containsKey(string_literal_aux)) {
                                symbolTable.put(string_literal_aux, new SymbolTableEntry(sc.getNombreToken(s.sym), null, s.value.toString(), s.value.toString().length()));
                            };
                            break;
                        case sym.FLOATING_POINT_LITERAL:
                        case sym.INTEGER_LITERAL:
                            String numeric_literal_aux = "_" + s.value.toString();
                            if (!symbolTable.containsKey(numeric_literal_aux)) {
                                symbolTable.put(numeric_literal_aux, new SymbolTableEntry(sc.getNombreToken(s.sym), null, s.value.toString(), null));
                            };
                            break;
                        default:
                            // code block
                    }
                    sw = sc.imprimirProximoToken();
                    s = sw.getSymbol();
                }
                outputSymbolTable();
            }
            catch (RuntimeException ex) {
                ex.printStackTrace();
            }
        }
        catch (IOException ex) {
            System.err.println("No se pudo abrir el archivo de prueba " + PRUEBA_PATH.toString() + ".");
        }
    }
}
