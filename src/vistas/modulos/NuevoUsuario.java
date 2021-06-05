package vistas.modulos;

import controlador.Controlador;
import javax.swing.JFrame;
import javax.swing.UIManager;
import utilidades.TextPrompt;

public class NuevoUsuario extends javax.swing.JDialog {

    VistaUsuario vistaUsuario;
    
    public NuevoUsuario(java.awt.Frame parent, boolean modal, VistaUsuario vistaUsuario) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.vistaUsuario = vistaUsuario;
        new TextPrompt("Usuario", jtUser);
        new TextPrompt("Contraseña", jtPass);
        new TextPrompt("Repetir contraseña", jtPassRepet);
        
    }
    
    public void setControlador(Controlador control){
        
    }
    
    public void iniciar(){
        this.setVisible(true);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jtUser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtPass = new javax.swing.JPasswordField();
        jtPassRepet = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.BorderLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Nuevo Usuario");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel1.add(jLabel2, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 15, 0};
        jPanel2Layout.rowHeights = new int[] {0};
        jPanel2.setLayout(jPanel2Layout);

        jLabel3.setBackground(new java.awt.Color(0, 102, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Guardar");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 204), 1, true));
        jLabel3.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cancelar");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 0, 0};
        jPanel3Layout.rowHeights = new int[] {0, 12, 0, 12, 0, 12, 0, 12, 0};
        jPanel3.setLayout(jPanel3Layout);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_22px.png"))); // NOI18N
        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 0, 0);
        jPanel3.add(jLabel4, gridBagConstraints);

        jtUser.setBackground(new java.awt.Color(255, 255, 255));
        jtUser.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 30);
        jPanel3.add(jtUser, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/access_22px.png"))); // NOI18N
        jLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 20, 0);
        jPanel3.add(jLabel5, gridBagConstraints);

        jtPass.setBackground(new java.awt.Color(255, 255, 255));
        jtPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel3.add(jtPass, gridBagConstraints);

        jtPassRepet.setBackground(new java.awt.Color(255, 255, 255));
        jtPassRepet.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel3.add(jtPassRepet, gridBagConstraints);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/key_22px.png"))); // NOI18N
        jLabel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel3.add(jLabel6, gridBagConstraints);

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(102, 102, 102));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione tipo de Rol", "Empleado", "Gerente", "Propietario" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jComboBox1.setFocusable(false);
        jComboBox1.setLightWeightPopupEnabled(false);
        jComboBox1.setRequestFocusEnabled(false);
        jComboBox1.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 152;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 30);
        jPanel3.add(jComboBox1, gridBagConstraints);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add_key_22px.png"))); // NOI18N
        jLabel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel3.add(jLabel7, gridBagConstraints);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        this.dispose();
    }//GEN-LAST:event_jLabel1MousePressed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        
//        
//        
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                NuevoUsuario dialog = new NuevoUsuario(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jtPass;
    private javax.swing.JPasswordField jtPassRepet;
    private javax.swing.JTextField jtUser;
    // End of variables declaration//GEN-END:variables
}
