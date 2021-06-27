package vistas.modulos;

import controlador.Controlador;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import javax.swing.UIManager;
import utilidades.MyComboBoxUI;
import utilidades.TextPrompt;
import vistas.modulos.VistaUsuario;

public class ModalFactura extends javax.swing.JDialog {

    VistaUsuario vistaUsuario;
    
    public ModalFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        comboBoxInit();
        setLocationRelativeTo(null);
        this.vistaUsuario = vistaUsuario;
        new TextPrompt("Email", jtImail);
        new TextPrompt("Nombre", jtNomClien);
        new TextPrompt("DUI", jFDui);
        new TextPrompt("Dirección", jtDirec);
        new TextPrompt("Telefono", jFTelef);
        new TextPrompt("Apellido", jtApeClien);
       
  
      
        
        

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
        jtImail = new javax.swing.JTextField();
        iconPass = new javax.swing.JLabel();
        cbRol = new javax.swing.JComboBox<>();
        iconRol = new javax.swing.JLabel();
        jtApeClien = new javax.swing.JTextField();
        jtNomClien = new javax.swing.JTextField();
        iconUser1 = new javax.swing.JLabel();
        jtDirec = new javax.swing.JTextField();
        iconPass1 = new javax.swing.JLabel();
        jFTelef = new javax.swing.JFormattedTextField();
        jFDui = new javax.swing.JFormattedTextField();
        iconPass3 = new javax.swing.JLabel();
        iconPass2 = new javax.swing.JLabel();
        jFCalenda = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProducto = new rojerusan.RSTableMetro();
        SeparadorW = new javax.swing.JLabel();
        SeparadorW2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        header.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        header.setForeground(new java.awt.Color(51, 51, 51));
        header.setText("Nueva Factura");
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
        jPanel2Layout.columnWidths = new int[] {0, 40, 0};
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
        iconUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/map_marker_22px.png"))); // NOI18N
        iconUser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 12, 0);
        form.add(iconUser, gridBagConstraints);

        jtImail.setBackground(new java.awt.Color(255, 255, 255));
        jtImail.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        jtImail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtImailActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 175;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 30);
        form.add(jtImail, gridBagConstraints);

        iconPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mail_22px.png"))); // NOI18N
        iconPass.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 12, 0);
        form.add(iconPass, gridBagConstraints);

        cbRol.setBackground(new java.awt.Color(255, 255, 255));
        cbRol.setForeground(new java.awt.Color(102, 102, 102));
        cbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vendedor" }));
        cbRol.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbRol.setFocusable(false);
        cbRol.setLightWeightPopupEnabled(false);
        cbRol.setRequestFocusEnabled(false);
        cbRol.setVerifyInputWhenFocusTarget(false);
        cbRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRolActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        form.add(cbRol, gridBagConstraints);

        iconRol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconRol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reseller_22px.png"))); // NOI18N
        iconRol.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 12, 0);
        form.add(iconRol, gridBagConstraints);

        jtApeClien.setBackground(new java.awt.Color(255, 255, 255));
        jtApeClien.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        jtApeClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtApeClienActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 216;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 12, 30);
        form.add(jtApeClien, gridBagConstraints);

        jtNomClien.setBackground(new java.awt.Color(255, 255, 255));
        jtNomClien.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        jtNomClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNomClienActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 12, 0);
        form.add(jtNomClien, gridBagConstraints);

        iconUser1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconUser1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_22px.png"))); // NOI18N
        iconUser1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 12, 0);
        form.add(iconUser1, gridBagConstraints);

        jtDirec.setBackground(new java.awt.Color(255, 255, 255));
        jtDirec.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        jtDirec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDirecActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 360;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 12, 30);
        form.add(jtDirec, gridBagConstraints);

        iconPass1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconPass1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add_phone_22px.png"))); // NOI18N
        iconPass1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        form.add(iconPass1, gridBagConstraints);

        jFTelef.setBackground(new java.awt.Color(255, 255, 255));
        jFTelef.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        try {
            jFTelef.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 175;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 30);
        form.add(jFTelef, gridBagConstraints);

        jFDui.setBackground(new java.awt.Color(255, 255, 255));
        jFDui.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        try {
            jFDui.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 175;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 12, 30);
        form.add(jFDui, gridBagConstraints);

        iconPass3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconPass3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/identity_theft_22px.png"))); // NOI18N
        iconPass3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 12, 0);
        form.add(iconPass3, gridBagConstraints);

        iconPass2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconPass2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/event_22px.png"))); // NOI18N
        iconPass2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        form.add(iconPass2, gridBagConstraints);

        jFCalenda.setBackground(new java.awt.Color(255, 255, 255));
        jFCalenda.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        try {
            jFCalenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 175;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 30);
        form.add(jFCalenda, gridBagConstraints);

        jLabel2.setText("N° de Factura: 0001");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        form.add(jLabel2, gridBagConstraints);

        tablaProducto.setBackground(new java.awt.Color(255, 255, 255));
        tablaProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(249, 249, 249)));
        tablaProducto.setForeground(new java.awt.Color(255, 255, 255));
        tablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Cantidad", "Descripción", "Precio Unitario", "Precio Total", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProducto.setAltoHead(30);
        tablaProducto.setColorBackgoundHead(new java.awt.Color(249, 249, 249));
        tablaProducto.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tablaProducto.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tablaProducto.setColorFilasBackgound2(new java.awt.Color(249, 249, 249));
        tablaProducto.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        tablaProducto.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        tablaProducto.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tablaProducto.setColorSelBackgound(new java.awt.Color(240, 240, 240));
        tablaProducto.setColorSelForeground(new java.awt.Color(51, 51, 51));
        tablaProducto.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaProducto.setFuenteFilas(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaProducto.setFuenteFilasSelect(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaProducto.setFuenteHead(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tablaProducto.setGridColor(new java.awt.Color(255, 255, 255));
        tablaProducto.setGrosorBordeFilas(0);
        tablaProducto.setGrosorBordeHead(0);
        tablaProducto.setMultipleSeleccion(false);
        tablaProducto.setRowHeight(40);
        tablaProducto.getTableHeader().setResizingAllowed(false);
        tablaProducto.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaProducto);
        if (tablaProducto.getColumnModel().getColumnCount() > 0) {
            tablaProducto.getColumnModel().getColumn(2).setPreferredWidth(300);
            tablaProducto.getColumnModel().getColumn(5).setPreferredWidth(10);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 30);
        form.add(jScrollPane1, gridBagConstraints);

        SeparadorW.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorW.setText("jLabel4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        form.add(SeparadorW, gridBagConstraints);

        SeparadorW2.setForeground(new java.awt.Color(255, 255, 255));
        SeparadorW2.setText("jLabel4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        form.add(SeparadorW2, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("SUBTOTAL $ 0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        form.add(jLabel3, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("iVA(%)$ 0.00 ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        form.add(jLabel4, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("TOTAL $ 0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        form.add(jLabel5, gridBagConstraints);

        btnNuevo.setBackground(new java.awt.Color(8, 89, 165));
        btnNuevo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/package_searchw_22px.png"))); // NOI18N
        btnNuevo.setText("Agregar Producto");
        btnNuevo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(8, 89, 165), 1, true));
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNuevo.setIconTextGap(1);
        btnNuevo.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 25.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 30);
        form.add(btnNuevo, gridBagConstraints);

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

    private void jtImailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtImailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtImailActionPerformed

    private void jtApeClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtApeClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtApeClienActionPerformed

    private void jtNomClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNomClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNomClienActionPerformed

    private void jtDirecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDirecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtDirecActionPerformed

    private void cbRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRolActionPerformed

    public void comboBoxInit(){
        this.cbRol.setUI(new MyComboBoxUI());
        this.cbRol.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204,204,204)), javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        
        
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModalFactura dialog = new ModalFactura(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SeparadorW;
    private javax.swing.JLabel SeparadorW2;
    public javax.swing.JLabel btnGuardar;
    public javax.swing.JLabel btnNuevo;
    public javax.swing.JComboBox<String> cbRol;
    public javax.swing.JPanel form;
    public javax.swing.JLabel header;
    public javax.swing.JLabel iconPass;
    public javax.swing.JLabel iconPass1;
    public javax.swing.JLabel iconPass2;
    public javax.swing.JLabel iconPass3;
    public javax.swing.JLabel iconRol;
    private javax.swing.JLabel iconUser;
    private javax.swing.JLabel iconUser1;
    private javax.swing.JFormattedTextField jFCalenda;
    private javax.swing.JFormattedTextField jFDui;
    private javax.swing.JFormattedTextField jFTelef;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField jtApeClien;
    public javax.swing.JTextField jtDirec;
    public javax.swing.JTextField jtImail;
    public javax.swing.JTextField jtNomClien;
    public rojerusan.RSTableMetro tablaProducto;
    // End of variables declaration//GEN-END:variables
}
