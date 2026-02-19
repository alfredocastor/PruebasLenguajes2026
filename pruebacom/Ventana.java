package pruebacom;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;

public class Ventana extends javax.swing.JFrame {

    // Instancias de nuestras otras clases
    private Analizador analizador = new Analizador();
    private GestorArchivos gestor = new GestorArchivos();
     private javax.swing.JScrollPane jScrollTxtCodigo;
    private javax.swing.JScrollPane jScrollTxtMensajes;
    private javax.swing.JTextArea txtCodigo;
    private javax.swing.JTextArea txtMensajes;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuProcesos;
    private javax.swing.JMenuItem itemAbrir;
    private javax.swing.JMenuItem itemEncontrar;

    public Ventana() {
        initComponents();
        this.setLocationRelativeTo(null); // Centrar
        this.setTitle("Analizador de Identificadores");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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

        // Configuración visual del área de código
        txtCodigo.setColumns(20);
        txtCodigo.setFont(new java.awt.Font("Monospaced", 0, 14)); 
        txtCodigo.setRows(5);
        jScrollTxtCodigo.setViewportView(txtCodigo);

        // Configuración visual del área de mensajes (abajo)
        txtMensajes.setEditable(false);
        txtMensajes.setColumns(20);
        txtMensajes.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        txtMensajes.setRows(5);
        jScrollTxtMensajes.setViewportView(txtMensajes);

        menuArchivo.setText("Archivo");

        itemAbrir.setText("Abrir");
        itemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAbrirActionPerformed(evt);
            }
        });
        menuArchivo.add(itemAbrir);
        jMenuBar1.add(menuArchivo);

        menuProcesos.setText("Procesos");

        itemEncontrar.setText("Encontrar");
        itemEncontrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEncontrarActionPerformed(evt);
            }
        });
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
                    .addComponent(jScrollTxtMensajes)) // El área de abajo
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollTxtMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE) // Altura reducida para solo mostrar el total
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    // ----------------------------------------------------------
    // LÓGICA DEL BOTÓN ABRIR
    // ----------------------------------------------------------
    private void itemAbrirActionPerformed(java.awt.event.ActionEvent evt) {                                          
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

    // ----------------------------------------------------------
    // LÓGICA DEL BOTÓN ENCONTRAR (AQUÍ OCURRE LA MAGIA)
    // ----------------------------------------------------------
    private void itemEncontrarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        String codigoFuente = txtCodigo.getText();
        
        if (!codigoFuente.isEmpty()) {
            // 1. Llamamos al analizador para que procese el texto
            analizador.analizarCodigo(codigoFuente);
            
            // 2. ACTUALIZAMOS EL ÁREA DE ARRIBA: Ponemos el texto con los corchetes []
            txtCodigo.setText(analizador.getTextoProcesado());
            
            // 3. ACTUALIZAMOS EL ÁREA DE ABAJO: Ponemos solo el número total
            txtMensajes.setText("Total de identificadores: " + analizador.getContador());
        } else {
            JOptionPane.showMessageDialog(this, "No hay código para analizar");
        }
    }                                            
}