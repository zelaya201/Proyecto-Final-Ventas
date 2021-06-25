/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modulos;


import controlador.Controlador;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ComponentListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import utilidades.MyComboBoxUI;
import utilidades.TextPrompt;


/**
 *
 * @author Sarai
 */
public class ModalEmpleado extends javax.swing.JPanel {
    
 VistaEmpleado vistaEmpleado;


 
    public ModalEmpleado(java.awt.Frame parent, boolean modal, VistaEmpleado vistaEmpleado) {
        super(modal);
        initComponents();
        comboBoxInit();
       setLocationRelativeTo(null);
        this.vistaEmpleado = vistaEmpleado;
        new TextPrompt("Nombre", jtNombre);
        new TextPrompt("Apellido", jtApellido);
        new TextPrompt("Dui", jtDui);
        new TextPrompt("Cargo", jtCargo);
        new TextPrompt("Estado", jtEstado);
        new TextPrompt("Renta", jtRenta);
        new TextPrompt("Edad", jtEdad); 
         new TextPrompt("Salario", jtSalario);
          new TextPrompt("Gmail", jtGmail);
           new TextPrompt("ISSS", jtIsss);
            new TextPrompt("AFP", jtAfp);
      
        
        

    }

    public ModalEmpleado(VistaEmpleado vistaEmpleado, JLabel btnGuardar, JComboBox<String> cbResponsable, JComboBox<String> cbSucursal, JComboBox<String> cbUsuario, JLabel header, JLabel jLabel1, JLabel jLabel11, JLabel jLabel12, JLabel jLabel13, JLabel jLabel14, JLabel jLabel15, JLabel jLabel16, JLabel jLabel17, JLabel jLabel18, JLabel jLabel19, JLabel jLabel20, JLabel jLabel21, JLabel jLabel22, JLabel jLabel23, JLabel jLabel24, JLabel jLabel25, JPanel jPanel1, JPanel jPanel2, JRadioButton jrF, JRadioButton jrM, JTextField jtAfp, JTextField jtApellido, JTextField jtCargo, JTextField jtDui, JTextField jtEdad, JTextField jtEstado, JTextField jtGmail, JTextField jtIsss, JTextField jtNombre, JTextField jtRenta, JTextField jtSalario, LayoutManager lm, boolean bln) {
        super(lm, bln);
        this.vistaEmpleado = vistaEmpleado;
        this.btnGuardar = btnGuardar;
        this.cbResponsable = cbResponsable;
        this.cbSucursal = cbSucursal;
        this.cbUsuario = cbUsuario;
        this.header = header;
        this.jLabel1 = jLabel1;
        this.jLabel11 = jLabel11;
        this.jLabel12 = jLabel12;
        this.jLabel13 = jLabel13;
        this.jLabel14 = jLabel14;
        this.jLabel15 = jLabel15;
        this.jLabel16 = jLabel16;
        this.jLabel17 = jLabel17;
        this.jLabel18 = jLabel18;
        this.jLabel19 = jLabel19;
        this.jLabel20 = jLabel20;
        this.jLabel21 = jLabel21;
        this.jLabel22 = jLabel22;
        this.jLabel23 = jLabel23;
        this.jLabel24 = jLabel24;
        this.jLabel25 = jLabel25;
        this.jPanel1 = jPanel1;
        this.jPanel2 = jPanel2;
        this.jrF = jrF;
        this.jrM = jrM;
        this.jtAfp = jtAfp;
        this.jtApellido = jtApellido;
        this.jtCargo = jtCargo;
        this.jtDui = jtDui;
        this.jtEdad = jtEdad;
        this.jtEstado = jtEstado;
        this.jtGmail = jtGmail;
        this.jtIsss = jtIsss;
        this.jtNombre = jtNombre;
        this.jtRenta = jtRenta;
        this.jtSalario = jtSalario;
    }

    public ModalEmpleado(VistaEmpleado vistaEmpleado, JLabel btnGuardar, JComboBox<String> cbResponsable, JComboBox<String> cbSucursal, JComboBox<String> cbUsuario, JLabel header, JLabel jLabel1, JLabel jLabel11, JLabel jLabel12, JLabel jLabel13, JLabel jLabel14, JLabel jLabel15, JLabel jLabel16, JLabel jLabel17, JLabel jLabel18, JLabel jLabel19, JLabel jLabel20, JLabel jLabel21, JLabel jLabel22, JLabel jLabel23, JLabel jLabel24, JLabel jLabel25, JPanel jPanel1, JPanel jPanel2, JRadioButton jrF, JRadioButton jrM, JTextField jtAfp, JTextField jtApellido, JTextField jtCargo, JTextField jtDui, JTextField jtEdad, JTextField jtEstado, JTextField jtGmail, JTextField jtIsss, JTextField jtNombre, JTextField jtRenta, JTextField jtSalario, LayoutManager lm) {
        super(lm);
        this.vistaEmpleado = vistaEmpleado;
        this.btnGuardar = btnGuardar;
        this.cbResponsable = cbResponsable;
        this.cbSucursal = cbSucursal;
        this.cbUsuario = cbUsuario;
        this.header = header;
        this.jLabel1 = jLabel1;
        this.jLabel11 = jLabel11;
        this.jLabel12 = jLabel12;
        this.jLabel13 = jLabel13;
        this.jLabel14 = jLabel14;
        this.jLabel15 = jLabel15;
        this.jLabel16 = jLabel16;
        this.jLabel17 = jLabel17;
        this.jLabel18 = jLabel18;
        this.jLabel19 = jLabel19;
        this.jLabel20 = jLabel20;
        this.jLabel21 = jLabel21;
        this.jLabel22 = jLabel22;
        this.jLabel23 = jLabel23;
        this.jLabel24 = jLabel24;
        this.jLabel25 = jLabel25;
        this.jPanel1 = jPanel1;
        this.jPanel2 = jPanel2;
        this.jrF = jrF;
        this.jrM = jrM;
        this.jtAfp = jtAfp;
        this.jtApellido = jtApellido;
        this.jtCargo = jtCargo;
        this.jtDui = jtDui;
        this.jtEdad = jtEdad;
        this.jtEstado = jtEstado;
        this.jtGmail = jtGmail;
        this.jtIsss = jtIsss;
        this.jtNombre = jtNombre;
        this.jtRenta = jtRenta;
        this.jtSalario = jtSalario;
    }

    public ModalEmpleado(VistaEmpleado vistaEmpleado, JLabel btnGuardar, JComboBox<String> cbResponsable, JComboBox<String> cbSucursal, JComboBox<String> cbUsuario, JLabel header, JLabel jLabel1, JLabel jLabel11, JLabel jLabel12, JLabel jLabel13, JLabel jLabel14, JLabel jLabel15, JLabel jLabel16, JLabel jLabel17, JLabel jLabel18, JLabel jLabel19, JLabel jLabel20, JLabel jLabel21, JLabel jLabel22, JLabel jLabel23, JLabel jLabel24, JLabel jLabel25, JPanel jPanel1, JPanel jPanel2, JRadioButton jrF, JRadioButton jrM, JTextField jtAfp, JTextField jtApellido, JTextField jtCargo, JTextField jtDui, JTextField jtEdad, JTextField jtEstado, JTextField jtGmail, JTextField jtIsss, JTextField jtNombre, JTextField jtRenta, JTextField jtSalario, boolean bln) {
        super(bln);
        this.vistaEmpleado = vistaEmpleado;
        this.btnGuardar = btnGuardar;
        this.cbResponsable = cbResponsable;
        this.cbSucursal = cbSucursal;
        this.cbUsuario = cbUsuario;
        this.header = header;
        this.jLabel1 = jLabel1;
        this.jLabel11 = jLabel11;
        this.jLabel12 = jLabel12;
        this.jLabel13 = jLabel13;
        this.jLabel14 = jLabel14;
        this.jLabel15 = jLabel15;
        this.jLabel16 = jLabel16;
        this.jLabel17 = jLabel17;
        this.jLabel18 = jLabel18;
        this.jLabel19 = jLabel19;
        this.jLabel20 = jLabel20;
        this.jLabel21 = jLabel21;
        this.jLabel22 = jLabel22;
        this.jLabel23 = jLabel23;
        this.jLabel24 = jLabel24;
        this.jLabel25 = jLabel25;
        this.jPanel1 = jPanel1;
        this.jPanel2 = jPanel2;
        this.jrF = jrF;
        this.jrM = jrM;
        this.jtAfp = jtAfp;
        this.jtApellido = jtApellido;
        this.jtCargo = jtCargo;
        this.jtDui = jtDui;
        this.jtEdad = jtEdad;
        this.jtEstado = jtEstado;
        this.jtGmail = jtGmail;
        this.jtIsss = jtIsss;
        this.jtNombre = jtNombre;
        this.jtRenta = jtRenta;
        this.jtSalario = jtSalario;
    }

    public ModalEmpleado(VistaEmpleado vistaEmpleado, JLabel btnGuardar, JComboBox<String> cbResponsable, JComboBox<String> cbSucursal, JComboBox<String> cbUsuario, JLabel header, JLabel jLabel1, JLabel jLabel11, JLabel jLabel12, JLabel jLabel13, JLabel jLabel14, JLabel jLabel15, JLabel jLabel16, JLabel jLabel17, JLabel jLabel18, JLabel jLabel19, JLabel jLabel20, JLabel jLabel21, JLabel jLabel22, JLabel jLabel23, JLabel jLabel24, JLabel jLabel25, JPanel jPanel1, JPanel jPanel2, JRadioButton jrF, JRadioButton jrM, JTextField jtAfp, JTextField jtApellido, JTextField jtCargo, JTextField jtDui, JTextField jtEdad, JTextField jtEstado, JTextField jtGmail, JTextField jtIsss, JTextField jtNombre, JTextField jtRenta, JTextField jtSalario) {
        this.vistaEmpleado = vistaEmpleado;
        this.btnGuardar = btnGuardar;
        this.cbResponsable = cbResponsable;
        this.cbSucursal = cbSucursal;
        this.cbUsuario = cbUsuario;
        this.header = header;
        this.jLabel1 = jLabel1;
        this.jLabel11 = jLabel11;
        this.jLabel12 = jLabel12;
        this.jLabel13 = jLabel13;
        this.jLabel14 = jLabel14;
        this.jLabel15 = jLabel15;
        this.jLabel16 = jLabel16;
        this.jLabel17 = jLabel17;
        this.jLabel18 = jLabel18;
        this.jLabel19 = jLabel19;
        this.jLabel20 = jLabel20;
        this.jLabel21 = jLabel21;
        this.jLabel22 = jLabel22;
        this.jLabel23 = jLabel23;
        this.jLabel24 = jLabel24;
        this.jLabel25 = jLabel25;
        this.jPanel1 = jPanel1;
        this.jPanel2 = jPanel2;
        this.jrF = jrF;
        this.jrM = jrM;
        this.jtAfp = jtAfp;
        this.jtApellido = jtApellido;
        this.jtCargo = jtCargo;
        this.jtDui = jtDui;
        this.jtEdad = jtEdad;
        this.jtEstado = jtEstado;
        this.jtGmail = jtGmail;
        this.jtIsss = jtIsss;
        this.jtNombre = jtNombre;
        this.jtRenta = jtRenta;
        this.jtSalario = jtSalario;
    }

   

    
     public void iniciar(){
        this.setVisible(true);
    }
    public ModalEmpleado() {
        initComponents();
    }
       public void setControlador(Controlador control){
        this.btnGuardar.addMouseListener(control);
       
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
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbResponsable = new javax.swing.JComboBox<>();
        jtEdad = new javax.swing.JTextField();
        header = new javax.swing.JLabel();
        jtRenta = new javax.swing.JTextField();
        jtCargo = new javax.swing.JTextField();
        jtSalario = new javax.swing.JTextField();
        jtGmail = new javax.swing.JTextField();
        jtEstado = new javax.swing.JTextField();
        jrM = new javax.swing.JRadioButton();
        jrF = new javax.swing.JRadioButton();
        jtIsss = new javax.swing.JTextField();
        jtAfp = new javax.swing.JTextField();
        cbUsuario = new javax.swing.JComboBox<>();
        cbSucursal = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jtNombre = new javax.swing.JTextField();
        jtDui = new javax.swing.JTextField();
        jtApellido = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnGuardar.setBackground(new java.awt.Color(0, 102, 204));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 204), 1, true));
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setOpaque(true);
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

        cbResponsable.setForeground(new java.awt.Color(102, 102, 102));
        cbResponsable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione tipo de Rol", "Empleado", "Gerente" }));
        cbResponsable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbResponsable.setFocusable(false);
        cbResponsable.setLightWeightPopupEnabled(false);
        cbResponsable.setRequestFocusEnabled(false);
        cbResponsable.setVerifyInputWhenFocusTarget(false);
        cbResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbResponsableActionPerformed(evt);
            }
        });

        header.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        header.setForeground(new java.awt.Color(51, 51, 51));
        header.setText("Nuevo Empleado");
        header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jtRenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtRentaActionPerformed(evt);
            }
        });

        jtCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCargoActionPerformed(evt);
            }
        });

        jtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtEstadoActionPerformed(evt);
            }
        });

        jrM.setText("M");

        jrF.setText("F");
        jrF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrFActionPerformed(evt);
            }
        });

        cbUsuario.setForeground(new java.awt.Color(102, 102, 102));
        cbUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione tipo de Rol", "Empleado", "Gerente" }));
        cbUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbUsuario.setFocusable(false);
        cbUsuario.setLightWeightPopupEnabled(false);
        cbUsuario.setRequestFocusEnabled(false);
        cbUsuario.setVerifyInputWhenFocusTarget(false);

        cbSucursal.setForeground(new java.awt.Color(102, 102, 102));
        cbSucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione tipo de Rol", "Empleado", "Gerente" }));
        cbSucursal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbSucursal.setFocusable(false);
        cbSucursal.setLightWeightPopupEnabled(false);
        cbSucursal.setRequestFocusEnabled(false);
        cbSucursal.setVerifyInputWhenFocusTarget(false);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_team_30px.png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add_user_male_22px.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add_user_male_22px.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add_user_male_22px.png"))); // NOI18N

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_supplier_30px_2.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_cheap_2_30px.png"))); // NOI18N

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_nas_30px.png"))); // NOI18N

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_caduceus_30px.png"))); // NOI18N

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_mail_30px.png"))); // NOI18N

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_dog_house_30px.png"))); // NOI18N

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_age_30px.png"))); // NOI18N

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_gender_30px.png"))); // NOI18N

        jtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNombreActionPerformed(evt);
            }
        });

        jtDui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDuiActionPerformed(evt);
            }
        });

        jtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtApellidoActionPerformed(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_22px.png"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_22px.png"))); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_nas_30px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(112, 112, 112)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18))))
                                .addGap(24, 24, 24)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbUsuario, 0, 282, Short.MAX_VALUE)
                                .addComponent(jtEstado)
                                .addComponent(jtCargo)
                                .addComponent(jtRenta)
                                .addComponent(jtEdad)
                                .addComponent(jtSalario)
                                .addComponent(jtGmail)
                                .addComponent(jtIsss)
                                .addComponent(jtAfp)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jrM)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jrF))
                                .addComponent(cbSucursal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(cbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtDui, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtDui, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtCargo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jtRenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jtEdad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jtGmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jtIsss, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jtAfp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jrM, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jrF, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
      //  this.dispose();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtEstadoActionPerformed

    private void jrFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrFActionPerformed

    private void jtCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtCargoActionPerformed

    private void jtRentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtRentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtRentaActionPerformed

    private void cbResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbResponsableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbResponsableActionPerformed

    private void jtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNombreActionPerformed

    private void jtDuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDuiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtDuiActionPerformed

    private void jtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtApellidoActionPerformed

  public void comboBoxInit(){
        this.cbResponsable.setUI(new MyComboBoxUI());
        this.cbUsuario.setUI(new MyComboBoxUI());
          this.cbSucursal.setUI(new MyComboBoxUI());
        this.cbSucursal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204,204,204)), javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)));
          this.cbUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204,204,204)), javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        this.cbResponsable.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204,204,204)), javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btnGuardar;
    public javax.swing.JComboBox<String> cbResponsable;
    public javax.swing.JComboBox<String> cbSucursal;
    public javax.swing.JComboBox<String> cbUsuario;
    public javax.swing.JLabel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JRadioButton jrF;
    public javax.swing.JRadioButton jrM;
    public javax.swing.JTextField jtAfp;
    public javax.swing.JTextField jtApellido;
    public javax.swing.JTextField jtCargo;
    public javax.swing.JTextField jtDui;
    public javax.swing.JTextField jtEdad;
    public javax.swing.JTextField jtEstado;
    public javax.swing.JTextField jtGmail;
    public javax.swing.JTextField jtIsss;
    public javax.swing.JTextField jtNombre;
    public javax.swing.JTextField jtRenta;
    public javax.swing.JTextField jtSalario;
    // End of variables declaration//GEN-END:variables

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  



}
