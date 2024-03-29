package vistas.modulos;

import controlador.Controlador;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import utilidades.MyComboBoxUI;
import utilidades.TextPrompt;
import vistas.modulos.VistaUsuario;

public class ModalUsuario extends javax.swing.JDialog {

    VistaUsuario vistaUsuario;
    
    public ModalUsuario(java.awt.Frame parent, boolean modal, VistaUsuario vistaUsuario) {
        super(parent, modal);
        initComponents();
        comboBoxInit();
        setLocationRelativeTo(null);
        this.vistaUsuario = vistaUsuario;
        new TextPrompt("Usuario", jtUser);
        new TextPrompt("Contraseña", jtPass);
        new TextPrompt("Repetir contraseña", jtPassRepet);   

    }
    
    public void setControlador(Controlador control){
        this.btnGuardar.addMouseListener(control);
        this.cbRol.addItemListener(control);
    }
    
    public void iniciar(){
        this.setVisible(true);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        form = new javax.swing.JPanel();
        iconUser = new javax.swing.JLabel();
        jtUser = new javax.swing.JTextField();
        iconEmpleado = new javax.swing.JLabel();
        jtPass = new javax.swing.JPasswordField();
        jtPassRepet = new javax.swing.JPasswordField();
        iconPass = new javax.swing.JLabel();
        cbEmpleado = new javax.swing.JComboBox<>();
        cbRol = new javax.swing.JComboBox<>();
        iconRol = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        header.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        header.setForeground(new java.awt.Color(51, 51, 51));
        header.setText("Nuevo Usuario");
        header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel1.add(header, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 15, 0};
        jPanel2Layout.rowHeights = new int[] {0};
        jPanel2.setLayout(jPanel2Layout);

        btnGuardar.setBackground(new java.awt.Color(0, 102, 204));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 204), 1, true));
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setOpaque(true);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGuardarMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(btnGuardar, gridBagConstraints);

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
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel2, gridBagConstraints);

        form.setBackground(new java.awt.Color(255, 255, 255));
        form.setLayout(new java.awt.GridBagLayout());

        iconUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_22px.png"))); // NOI18N
        iconUser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 12, 0);
        form.add(iconUser, gridBagConstraints);

        jtUser.setBackground(new java.awt.Color(255, 255, 255));
        jtUser.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        jtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtUserActionPerformed(evt);
            }
        });
        jtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtUserKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 360;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 12, 30);
        form.add(jtUser, gridBagConstraints);

        iconEmpleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add_user_male_22px.png"))); // NOI18N
        iconEmpleado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 20, 0);
        form.add(iconEmpleado, gridBagConstraints);

        jtPass.setBackground(new java.awt.Color(255, 255, 255));
        jtPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        form.add(jtPass, gridBagConstraints);

        jtPassRepet.setBackground(new java.awt.Color(255, 255, 255));
        jtPassRepet.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 12, 30);
        form.add(jtPassRepet, gridBagConstraints);

        iconPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/key_22px.png"))); // NOI18N
        iconPass.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 12, 0);
        form.add(iconPass, gridBagConstraints);

        cbEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        cbEmpleado.setForeground(new java.awt.Color(102, 102, 102));
        cbEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asignar empleado" }));
        cbEmpleado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbEmpleado.setEnabled(false);
        cbEmpleado.setFocusable(false);
        cbEmpleado.setLightWeightPopupEnabled(false);
        cbEmpleado.setRequestFocusEnabled(false);
        cbEmpleado.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 30);
        form.add(cbEmpleado, gridBagConstraints);

        cbRol.setBackground(new java.awt.Color(255, 255, 255));
        cbRol.setForeground(new java.awt.Color(102, 102, 102));
        cbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione tipo de Rol", "Administrador", "Empleado" }));
        cbRol.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbRol.setFocusable(false);
        cbRol.setLightWeightPopupEnabled(false);
        cbRol.setRequestFocusEnabled(false);
        cbRol.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 30);
        form.add(cbRol, gridBagConstraints);

        iconRol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconRol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/control_panel_22px.png"))); // NOI18N
        iconRol.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 12, 0);
        form.add(iconRol, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(form, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        this.dispose();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtUserActionPerformed

    private void jtUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtUserKeyTyped
        char val = evt.getKeyChar();
        
        if((val<'a' || val>'z') && (val<'A' || val>'Z') && (val < '0' || val > '9')) evt.consume();
    }//GEN-LAST:event_jtUserKeyTyped

    private void btnGuardarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarMousePressed

    public void comboBoxInit(){
        this.cbEmpleado.setUI(new MyComboBoxUI());
        this.cbRol.setUI(new MyComboBoxUI());
        
        this.cbRol.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204,204,204)), javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        this.cbEmpleado.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204,204,204)), javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)));
    }
    
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
//            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        
//        
//        
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ModalUsuario dialog = new ModalUsuario(new javax.swing.JFrame(), true);
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
    public javax.swing.JLabel btnGuardar;
    public javax.swing.JComboBox<String> cbEmpleado;
    public javax.swing.JComboBox<String> cbRol;
    public javax.swing.JPanel form;
    public javax.swing.JLabel header;
    public javax.swing.JLabel iconEmpleado;
    public javax.swing.JLabel iconPass;
    public javax.swing.JLabel iconRol;
    private javax.swing.JLabel iconUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPasswordField jtPass;
    public javax.swing.JPasswordField jtPassRepet;
    public javax.swing.JTextField jtUser;
    // End of variables declaration//GEN-END:variables
}
