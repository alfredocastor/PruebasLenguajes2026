package compilador;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;

public class Ventana extends javax.swing.JFrame {

    private Analizador analizador;
    private GestorArchivos gestor;

    private javax.swing.JScrollPane jScrollTxtCodigo;
    private javax.swing.JScrollPane jScrollTxtMensajes;
    private javax.swing.JTextArea txtCodigo;
    private javax.swing.JTextArea txtMensajes;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuProcesos;
    private javax.swing.JMenuItem itemAbrir;
    private javax.swing.JMenuItem itemEncontrar;

    public Ventana(Analizador analizador, GestorArchivos gestor) {
        this.analizador = analizador;
        this.gestor = gestor;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Analizador de Identificadores");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollTxtCodigo = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextArea();
        jScrollTxtMensajes = new javax.swing.JScrollPane();
        txtMensajes = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        itemAbrir = new javax.swing.JMenuItem();
        menuProcesos = new javax.swing.JMenu();
        itemEncontrar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtCodigo.setColumns(20);
        txtCodigo.setFont(new java.awt.Font("Monospaced", 0, 14));
        txtCodigo.setRows(5);
        jScrollTxtCodigo.setViewportView(txtCodigo);

        txtMensajes.setEditable(false);
        txtMensajes.setColumns(20);
        txtMensajes.setFont(new java.awt.Font("Segoe UI", 1, 14));
        txtMensajes.setRows(5);
        jScrollTxtMensajes.setViewportView(txtMensajes);

        menuArchivo.setText("Archivo");

        itemAbrir.setText("Abrir");
        itemAbrir.addActionListener(evt -> itemAbrirActionPerformed());
        menuArchivo.add(itemAbrir);
        jMenuBar1.add(menuArchivo);

        menuProcesos.setText("Procesos");

        itemEncontrar.setText("Encontrar");
        itemEncontrar.addActionListener(evt -> itemEncontrarActionPerformed());
        menuProcesos.add(itemEncontrar);
        jMenuBar1.add(menuProcesos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollTxtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jScrollTxtMensajes))
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollTxtMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void itemAbrirActionPerformed() {
        JFileChooser chooser = new JFileChooser();
        int opcion = chooser.showOpenDialog(this);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            try {
                String contenido = gestor.leerArchivo(archivo);
                txtCodigo.setText(contenido);
                txtMensajes.setText("Archivo cargado: " + archivo.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void itemEncontrarActionPerformed() {
        String codigoFuente = txtCodigo.getText();

        if (!codigoFuente.isEmpty()) {
            analizador.analizarCodigo(codigoFuente);
            txtCodigo.setText(analizador.getTextoProcesado());
            txtMensajes.setText("Total de identificadores: " + analizador.getContador());
        } else {
            JOptionPane.showMessageDialog(this, "No hay código para analizar");
        }
    }
}