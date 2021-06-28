package vistas.modulos;

import controlador.Controlador;
import java.awt.event.KeyListener;
import utilidades.TextPrompt;

public class VistaFactura extends javax.swing.JPanel {

    public VistaFactura() {
        initComponents();       
    }
    
    public void setControlador(Controlador control){
        this.btnNuevo.addMouseListener(control);
        this.tablaFactura.addMouseListener(control);
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaFactura = new rojerusan.RSTableMetro();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnNuevo.setBackground(new java.awt.Color(8, 89, 165));
        btnNuevo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add_22px.png"))); // NOI18N
        btnNuevo.setText("Nueva factura");
        btnNuevo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(8, 89, 165), 1, true));
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
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_group_22px.png"))); // NOI18N
        jLabel3.setText("Facturas / historial de facturas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 15, 0);
        jPanel3.add(jLabel3, gridBagConstraints);

        add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(210, 214, 222)));
        jPanel1.setLayout(new java.awt.CardLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setOpaque(false);

        tablaFactura.setBackground(new java.awt.Color(255, 255, 255));
        tablaFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(249, 249, 249)));
        tablaFactura.setForeground(new java.awt.Color(255, 255, 255));
        tablaFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No. de Factura", "Cliente", "Fecha", "Vendedor", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaFactura.setAltoHead(30);
        tablaFactura.setColorBackgoundHead(new java.awt.Color(249, 249, 249));
        tablaFactura.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tablaFactura.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tablaFactura.setColorFilasBackgound2(new java.awt.Color(249, 249, 249));
        tablaFactura.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        tablaFactura.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        tablaFactura.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tablaFactura.setColorSelBackgound(new java.awt.Color(240, 240, 240));
        tablaFactura.setColorSelForeground(new java.awt.Color(51, 51, 51));
        tablaFactura.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaFactura.setFuenteFilas(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaFactura.setFuenteFilasSelect(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaFactura.setFuenteHead(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tablaFactura.setGridColor(new java.awt.Color(255, 255, 255));
        tablaFactura.setGrosorBordeFilas(0);
        tablaFactura.setGrosorBordeHead(0);
        tablaFactura.setMultipleSeleccion(false);
        tablaFactura.setRowHeight(40);
        tablaFactura.getTableHeader().setResizingAllowed(false);
        tablaFactura.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaFactura);
        if (tablaFactura.getColumnModel().getColumnCount() > 0) {
            tablaFactura.getColumnModel().getColumn(0).setResizable(false);
            tablaFactura.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaFactura.getColumnModel().getColumn(1).setResizable(false);
            tablaFactura.getColumnModel().getColumn(2).setResizable(false);
            tablaFactura.getColumnModel().getColumn(3).setResizable(false);
            tablaFactura.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.add(jScrollPane2, "card2");

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btnNuevo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    public rojerusan.RSTableMetro tablaFactura;
    // End of variables declaration//GEN-END:variables
}
