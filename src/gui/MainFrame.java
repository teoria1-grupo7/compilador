package gui;

import ast.NodoPrograma;
import compilador.*;
import java_cup.runtime.Symbol;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends javax.swing.JFrame {
    private final static Path TS_PATH = Paths.get("ts.txt");
    private String dotPath;

    public String outputSymbolTable(HashMap<String, SymbolTableEntry> symbolTable) {
        String salida = "";
        try {
            PrintWriter pw =  new PrintWriter(Files.newOutputStream(TS_PATH));
            String fmt = "%31s%31s%10s%31s%31s\n";
            salida += String.format(fmt, "NOMBRE", "TOKEN", "TIPO", "VALOR", "LONGITUD");
            for (Map.Entry<String, SymbolTableEntry> entry : symbolTable.entrySet()) {
                pw.printf(fmt, entry.getKey(), entry.getValue().getToken(),
                        entry.getValue().getType() != null ? entry.getValue().getType() : "-",
                        entry.getValue().getVal() != null ? entry.getValue().getVal() : "-",
                        entry.getValue().getLen() != null ? entry.getValue().getLen() : "-");
                salida += String.format(fmt, entry.getKey(), entry.getValue().getToken(),
                        entry.getValue().getType() != null ? entry.getValue().getType() : "-",
                        entry.getValue().getVal() != null ? entry.getValue().getVal() : "-",
                        entry.getValue().getLen() != null ? entry.getValue().getLen() : "-");
            };
            pw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return salida;
    }

    File currentEditingFile = null;
    int fontSize = 10;

    public MainFrame() {
        initComponents();

        //Filter Files to display
        //Set JFileChooser to accept only text files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt", "text");
        fileOpener.setFileFilter(filter);

        //Launch the application in the middle of Screen
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e); //To change body of generated methods, choose Tools | Templates.
                if (currentEditingFile != null) {
                    int ans = JOptionPane.showConfirmDialog(rootPane, "¿Guardar cambios?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (ans == JOptionPane.YES_OPTION) {
                        saveChanges();
                    }
                }
            }

        });
        try {
            this.setIconImage(ImageIO.read(MainFrame.class.getResource("/gui/unlu.png")));
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Custom button text

    }

    public MainFrame(File file) {
        initComponents();
        this.setLocationRelativeTo(null);

        currentEditingFile = file;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt", "text");
        fileOpener.setFileFilter(filter);
        readTheParamFile(file);
    }

    public void readTheParamFile(File file) {
        try {
            Scanner scn = new Scanner(file);
            String buffer = "";
            while (scn.hasNext()) {
                buffer += scn.nextLine() + "\n";
            }
            display.setText(buffer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveChanges() {
        try {
            PrintWriter printWriter = new PrintWriter(currentEditingFile);
            printWriter.write(display.getText());
            printWriter.close();
//            JOptionPane.showMessageDialog(rootPane, "Saved", "Done", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileOpener = new javax.swing.JFileChooser();
        execFileOpener = new javax.swing.JFileChooser();
        saveDialog = new javax.swing.JFileChooser();

        jPanel1 = new javax.swing.JPanel();

        jToolBar1 = new javax.swing.JToolBar();
        jButtonAbrir = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonAgrandarTexto = new javax.swing.JButton();
        jButtonAchicarTexto = new javax.swing.JButton();

        jTabbedPaneCodigo = new javax.swing.JTabbedPane();
        jScrollPaneCodigo = new javax.swing.JScrollPane();
        display = new javax.swing.JTextArea();

        jTabbedPaneSalida = new javax.swing.JTabbedPane();
        jScrollPaneSalida = new javax.swing.JScrollPane();
        jTextPaneSalida = new javax.swing.JTextPane();
        jScrollPaneTS = new javax.swing.JScrollPane();
        jTextAreaTS = new javax.swing.JTextArea();

        jLabel1 = new javax.swing.JLabel();

        saveDialog.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        saveDialog.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compilador");
        setResizable(true);

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setRollover(true);

        jButtonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/open_new_file.png"))); // NOI18N
        jButtonAbrir.setText("Abrir");
        jButtonAbrir.setFocusable(false);
        jButtonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonAbrir);

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Save_icon.png"))); // NOI18N
        jButtonGuardar.setText("Compilar");
        jButtonGuardar.setFocusable(false);
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompilarActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonGuardar);

        jButtonAgrandarTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/increase_font.png"))); // NOI18N
        jButtonAgrandarTexto.setText("Agrandar texto");
        jButtonAgrandarTexto.setFocusable(false);
        jButtonAgrandarTexto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAgrandarTexto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAgrandarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgrandarTextoActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonAgrandarTexto);

        jButtonAchicarTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/decrease_font.png"))); // NOI18N
        jButtonAchicarTexto.setText("Achicar texto");
        jButtonAchicarTexto.setFocusable(false);
        jButtonAchicarTexto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAchicarTexto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAchicarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAchicarTextoActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonAchicarTexto);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Copyright ©2020, UNLu - César Ferrarotti, Mariano Rapaport");

        display.setColumns(20);
        display.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        display.setLineWrap(false);
        display.setRows(5);
        jScrollPaneCodigo.setViewportView(display);

        jTabbedPaneCodigo.addTab("CODIGO", jScrollPaneCodigo);

        //jTextPaneSalida.setColumns(20);
        jTextPaneSalida.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        //jTextPaneSalida.setLineWrap(false);
        //jTextPaneSalida.setRows(5);
        jScrollPaneSalida.setViewportView(jTextPaneSalida);

        jTabbedPaneSalida.addTab("SALIDA", jScrollPaneSalida);

        jTextAreaTS.setColumns(20);
        jTextAreaTS.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextAreaTS.setLineWrap(false);
        jTextAreaTS.setRows(5);
        jScrollPaneTS.setViewportView(jTextAreaTS);

        jTabbedPaneSalida.addTab("TS", jScrollPaneTS);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTabbedPaneCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPaneSalida))
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTabbedPaneSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                        .addComponent(jTabbedPaneCodigo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        //setDotExecutablePath();
    }// </editor-fold>//GEN-END:initComponents

    private void setDotExecutablePath() {
        Object[] options = {"Si",
            "No"};
        String message = "<html><p style=\"font-size: 13px; text-align:center;\">"
            + "¿Desea seleccionar el path al ejecutable de <b>dot</b> en su sistema?</p><br>"
            + "<p style=\"font-size: 11px; text-align:center;\">En caso de no seleccionarlo, "
            + "no se construirá la imagen del AST.</p></html>";
        int option = JOptionPane.showOptionDialog(new Frame(),
            message,
            "Path ejecutable de dot",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[1]);

        if (option == JOptionPane.YES_OPTION) {
            int status = execFileOpener.showOpenDialog(rootPane);
            if (status == JFileChooser.APPROVE_OPTION) {
                File selectedFile = execFileOpener.getSelectedFile();
                this.dotPath = selectedFile.getAbsolutePath();
            }
        }
    }

    private String chooseAsmFileLocation() {
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();
        //In response to a button click:
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            return file.getAbsolutePath();
        }
        return "";
    }
    private void jButtonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirActionPerformed
        //Show File Open dialouge here
        int status = fileOpener.showOpenDialog(rootPane);
        if (status == JFileChooser.APPROVE_OPTION) {
            if (currentEditingFile != null) {
                // A file is opened and is being edited. Open the new file in new window
                MainFrame newWindow = new MainFrame(fileOpener.getSelectedFile());
                newWindow.setVisible(true);
                newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                newWindow.pack();
                return;
            }
            currentEditingFile = fileOpener.getSelectedFile();
            System.out.println("Se seleccióno el archivo " + fileOpener.getSelectedFile().getName() + ".");

            try {
                //Now read the contents of file
                Scanner scn = new Scanner(new FileInputStream(currentEditingFile));
                String buffer = "";
                while (scn.hasNext()) {
                    buffer += scn.nextLine() + "\n";
                }
                display.setText(buffer);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("No se seleccióno un archivo.");
        }
    }//GEN-LAST:event_jButtonAbrirActionPerformed

    private void jButtonCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompilarActionPerformed
        jTextPaneSalida.setText("");
        MessageConsole mc = new MessageConsole(jTextPaneSalida);
        mc.redirectOut();
        mc.redirectErr(Color.RED, null);
        mc.setMessageLines(10000);
        Reader reader;
        try {
            if (currentEditingFile != null) {
                saveChanges();
                reader = Files.newBufferedReader(Paths.get(currentEditingFile.getPath()));
            } else {
                reader = new StringReader(display.getText());
            }
            Lexico lexico = new Lexico(reader);
            parser par = new parser(lexico);
            try {
                // Parse input
                Symbol s = par.parse();
                HashMap<String, SymbolTableEntry> symbolTable = par.helper.getSymbolTable();

                // Output symbol table
                jTextAreaTS.setText(outputSymbolTable(symbolTable));
                NodoPrograma program = (NodoPrograma) s.value;

                // Generate ASM
                String assembler = program.assemble(symbolTable);
                JOptionPane.showMessageDialog(this, "Assembler generado correctamente. Elija la ubicación", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                String asmFilePath = chooseAsmFileLocation();
                String asmOutputPath =
                    !asmFilePath.equals("") ? asmFilePath + ".asm" : "program.asm";
                FileWriter asmFile = new FileWriter(asmOutputPath);
                PrintWriter asmWriter = new PrintWriter(asmFile);

                asmWriter.println(assembler);
                asmFile.close();
                JOptionPane.showMessageDialog(this, "Archivo assembler guardado en " + asmOutputPath);

                // Generate DOT file
                FileWriter dotFile = new FileWriter("arbol.dot");
                PrintWriter pw = new PrintWriter(dotFile);
                pw.println(program.graficar());
                dotFile.close();
                generateAstImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonCompilarActionPerformed


    private void generateAstImage() {
        if (this.dotPath != null) {
            String cmd = this.dotPath + " -Tpng arbol.dot -o arbol.png";
            try {
                Process exec = Runtime.getRuntime().exec(cmd);
                exec.waitFor();
                if (exec.exitValue() == 0) {
                    JOptionPane.showMessageDialog(new Frame(), "Árbol generado en arbol.png");
                }
                else {
                    JOptionPane.showMessageDialog(rootPane, "No se pudo generar la imagen del árbol.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, "No se pudo generar la imagen del árbol.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void jButtonAgrandarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgrandarTextoActionPerformed
        display.setFont(new java.awt.Font("Monospaced", 0, ++fontSize));
        jTextPaneSalida.setFont(new java.awt.Font("Monospaced", 0, ++fontSize));
        jTextAreaTS.setFont(new java.awt.Font("Monospaced", 0, ++fontSize));
    }//GEN-LAST:event_jButtonAgrandarTextoActionPerformed

    private void jButtonAchicarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAchicarTextoActionPerformed
        display.setFont(new java.awt.Font("Monospaced", 0, --fontSize));
        jTextPaneSalida.setFont(new java.awt.Font("Monospaced", 0, --fontSize));
        jTextAreaTS.setFont(new java.awt.Font("Monospaced", 0, --fontSize));
    }//GEN-LAST:event_jButtonAchicarTextoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea display;
    private javax.swing.JTextPane jTextPaneSalida;
    private javax.swing.JTextArea jTextAreaTS;
    private javax.swing.JFileChooser fileOpener;
    private javax.swing.JFileChooser execFileOpener;
    private javax.swing.JButton jButtonAbrir;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonAgrandarTexto;
    private javax.swing.JButton jButtonAchicarTexto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneCodigo;
    private javax.swing.JScrollPane jScrollPaneSalida;
    private javax.swing.JScrollPane jScrollPaneTS;
    private javax.swing.JTabbedPane jTabbedPaneSalida;
    private javax.swing.JTabbedPane jTabbedPaneCodigo;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JFileChooser saveDialog;
    // End of variables declaration//GEN-END:variables
}
