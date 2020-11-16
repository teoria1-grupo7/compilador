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

    public static void outputSymbolTable(HashMap<String, SymbolTableEntry> symbolTable) {
        try {
            PrintWriter pw =  new PrintWriter(Files.newOutputStream(TS_PATH));
            String fmt = "%31s%31s%10s%31s%31s\n";
            pw.printf(fmt, "NOMBRE", "TOKEN", "TIPO", "VALOR", "LONGITUD");
            symbolTable.entrySet().forEach( entry -> {
                pw.printf(fmt, entry.getKey(), entry.getValue().getToken(),
                        entry.getValue().getType() != null ? entry.getValue().getType() : "-",
                        entry.getValue().getVal() != null ? entry.getValue().getVal() : "-",
                        entry.getValue().getLen() != null ? entry.getValue().getLen() : "-");
            });
            pw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        try {
            BufferedReader br = Files.newBufferedReader(PRUEBA_PATH);
            Lexico lexico = new Lexico(br);
            parser par = new parser(lexico);
            try {
                Symbol s = par.parse();
                outputSymbolTable(par.helper.getSymbolTable());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        catch (IOException ex) {
            System.err.println("No se pudo abrir el archivo de prueba " + PRUEBA_PATH.toString() + ".");
        }
    }
}
