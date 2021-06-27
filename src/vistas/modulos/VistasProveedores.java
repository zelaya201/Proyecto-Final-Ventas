package vistas.modulos;

import controlador.Controlador;
import java.awt.event.KeyListener;
import utilidades.TextPrompt;

public class VistasProveedores extends javax.swing.JPanel {

    public VistasProveedores() {
        initComponents();
        new TextPrompt("Buscar Proveedor", tfBusqueda);
        
    }
    
    public void setControlador(Controlador control){
        this.btnNuevo.addMouseListener(control);
        this.tfBusqueda.addKeyListener(control);
        this.tablaProveedor.addMouseListener(control);
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

        jPanel3 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfBusqueda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProveedor = new rojerusan.RSTableMetro();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnNuevo.setBackground(new java.awt.Color(236, 240, 245));
        btnNuevo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(236, 240, 245));
        btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNuevo.setText("Nuevo Proveedor");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNuevo.setIconTextGap(1);
        btnNuevo.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 25.0;
        jPanel3.add(btnNuevo, gridBagConstraints);
        btnNuevo.getAccessibleContext().setAccessibleName("btnNuevo");

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(42, 53, 66));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/truckb_22px.png"))); // NOI18N
        jLabel3.setText("Proveedor / lista de proveedores");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 15, 0);
        jPanel3.add(jLabel3, gridBagConstraints);

        tfBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        tfBusqueda.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tfBusqueda.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        tfBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfBusquedaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfBusquedaKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.weightx = 60.0;
        jPanel3.add(tfBusqueda, gridBagConstraints);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search_contacts_25px.png"))); // NOI18N
        jLabel1.setLabelFor(tfBusqueda);
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jLabel1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 11;
        jPanel3.add(jLabel1, gridBagConstraints);

        add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(210, 214, 222)));
        jPanel1.setLayout(new java.awt.CardLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setOpaque(false);

        tablaProveedor.setBackground(new java.awt.Color(255, 255, 255));
        tablaProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(249, 249, 249)));
        tablaProveedor.setForeground(new java.awt.Color(255, 255, 255));
        tablaProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cod. Proveedor", "Nom. Proveedor", "Tel. Proveedor", "Dir. Proveedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProveedor.setAltoHead(30);
        tablaProveedor.setColorBackgoundHead(new java.awt.Color(249, 249, 249));
        tablaProveedor.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tablaProveedor.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tablaProveedor.setColorFilasBackgound2(new java.awt.Color(249, 249, 249));
        tablaProveedor.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        tablaProveedor.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        tablaProveedor.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tablaProveedor.setColorSelBackgound(new java.awt.Color(240, 240, 240));
        tablaProveedor.setColorSelForeground(new java.awt.Color(51, 51, 51));
        tablaProveedor.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaProveedor.setFuenteFilas(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaProveedor.setFuenteFilasSelect(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaProveedor.setFuenteHead(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tablaProveedor.setGridColor(new java.awt.Color(255, 255, 255));
        tablaProveedor.setGrosorBordeFilas(0);
        tablaProveedor.setGrosorBordeHead(0);
        tablaProveedor.setMultipleSeleccion(false);
        tablaProveedor.setRowHeight(40);
        tablaProveedor.getTableHeader().setResizingAllowed(false);
        tablaProveedor.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaProveedor);
        if (tablaProveedor.getColumnModel().getColumnCount() > 0) {
            tablaProveedor.getColumnModel().getColumn(0).setResizable(false);
            tablaProveedor.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaProveedor.getColumnModel().getColumn(1).setResizable(false);
            tablaProveedor.getColumnModel().getColumn(1).setPreferredWidth(150);
            tablaProveedor.getColumnModel().getColumn(2).setResizable(false);
            tablaProveedor.getColumnModel().getColumn(2).setPreferredWidth(40);
            tablaProveedor.getColumnModel().getColumn(3).setResizable(false);
            tablaProveedor.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        jPanel1.add(jScrollPane2, "card2");

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tfBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfBusquedaKeyPressed

    private void tfBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfBusquedaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    public rojerusan.RSTableMetro tablaProveedor;
    public javax.swing.JTextField tfBusqueda;
    // End of variables declaration//GEN-END:variables
}
