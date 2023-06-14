package helper;

import compilador.SymbolTableEntry;
import java.util.HashMap;

public class AssemblerHelper {

  public static String buildHeader() {
    return "include macros2.asm\n"
        + "include number.asm\n"
        + ".MODEL LARGE \n"
        + ".386\n"
        + ".STACK  200h \n";
  }



  public static String buildDataSection(HashMap<String, SymbolTableEntry> symbolTable, int auxCount) {
    StringBuilder output = new StringBuilder();
    output.append(".DATA\n");
    symbolTable.forEach((symbol, entry) -> {
      String dataType = translateDataType(entry.getType());
      Object constVal = getSymbolTableEntryValue(entry);
      output.append(symbol).append(" ").append(dataType).append(" ").append(constVal).append("\n");
    });
    for (int i = 0; i < auxCount; i++) {
      output.append("_@aux").append(i).append(" dd ?\n");
    }
    return output.toString();
  }

  private static Object getSymbolTableEntryValue(SymbolTableEntry entry) {
    if (entry.getVal() != null) {
      if (entry.getType().equals("STRING")) {
        return String.format("\"%s$\"", entry.getVal());
      }
      if (entry.getType().equals("INT")) {
        return entry.getVal() + ".0";
      }
      return entry.getVal();
    }
    return "?";
  }

  public static String buildCodeHeader() {
    return ".CODE\n"
        + "START:\n"
        + "MOV EAX,@DATA\n"
        + "MOV DS,EAX\n"
        + "MOV ES,EAX\n";
  }

  private static String translateDataType(String type) {
    switch (type) {
      case "INT": return "dd";
      case "FLOAT": return "dd";
      case "STRING": return "db";
      default: return "";
    }
  }

  public static String buildFooter() {
    return "MOV EAX, 4C00h\n"
        + "INT 21h\n"
        + "END START\n"
        + ";\n";
  }



}
