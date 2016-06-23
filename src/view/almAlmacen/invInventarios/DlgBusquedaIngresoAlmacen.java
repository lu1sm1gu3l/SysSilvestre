package view.almAlmacen.invInventarios;


import controller.almAlmacen.CCIngresoProducto;
import controller.almAlmacen.CCTipoIngresoSalida;
import controller.grlGeneral.CCEstado;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import modelo.almAlmacen.entidad.CEIngresoProducto;
import modelo.almAlmacen.entidad.CETipoIngresoSalida;
import modelo.grlGeneral.entidad.CEEstado;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import view.FrmSistemaMenu;
import view.cmrComercial.DlgGestionProveedor;


public class DlgBusquedaIngresoAlmacen extends DialogoPadre
{

    private List<CEIngresoProducto> oListaIngresoProducto;
    private java.awt.Frame oparent;
    private boolean cargar;
    public DlgBusquedaIngresoAlmacen(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        cargar=false;
        this.setLocationRelativeTo(null);
        configurarTabla();
        DtChFecha.setDate(FrmSistemaMenu.FechaSistema);
        DtChFechaFin.setDate(FrmSistemaMenu.FechaSistema);
        DtChFecha.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        DtChFechaFin.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        CbxEstado.setModel(CLComboBox.cargarCombo(CCEstado.ListadoEstado(0)));
        CbxTipoIngreso.setModel(CLComboBox.cargarCombo(CCTipoIngresoSalida.consultarTipoIngresosProductosMasTodos()));
        this.oparent=parent;
        cargar=true;
        CLBotonesABM oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(null, null, null, null, null, null,this);
        filtrarTabla();
    }
    private void configurarTabla()
    {
        TableColumnModel oTableColumnModel=TblDato.getColumnModel();
        oTableColumnModel.getColumn(0).setPreferredWidth(150);
        oTableColumnModel.getColumn(1).setPreferredWidth(100);
        oTableColumnModel.getColumn(2).setPreferredWidth(280);
        oTableColumnModel.getColumn(3).setPreferredWidth(100);
        oTableColumnModel.getColumn(4).setPreferredWidth(95);
        oTableColumnModel.getColumn(5).setPreferredWidth(95);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);

        DefaultTableCellRenderer tccent = new DefaultTableCellRenderer();
        tccent.setHorizontalAlignment(SwingConstants.CENTER);

     //   TblPedidosPendientes.getColumnModel().getColumn(0).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(1).setCellRenderer(tccent);
        //TblPedidosPendientes.getColumnModel().getColumn(2).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(6).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(4).setCellRenderer(tccent);

        TblDato.getColumnModel().getColumn(5).setCellRenderer(tcr);
  ;
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPopupMenuPedido = new javax.swing.JPopupMenu();
        jMenuItemCancelarPedido = new javax.swing.JMenuItem();
        jMenuItemAnularPedido = new javax.swing.JMenuItem();
        tbBusqueda = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        chkFecha = new Label.Label();
        DtChFecha =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        TxtCliente = new TextField.JTxtNinguno();
        lblCliente = new Label.Label();
        lblTipocomp = new Label.Label();
        CbxTipoIngreso = new ComboBox.ComboBox();
        CbxEstado = new ComboBox.ComboBox();
        LblEstado = new Label.Label();
        DtChFechaFin =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        chkFecha1 = new Label.Label();
        jPanel5 = new javax.swing.JPanel();
        LblCodigo = new Label.Label();
        TxtCodigo = new TextField.JTxtNinguno();
        btnBuscar2 = new BotonesABM.BtnBuscar();
        jPanel4 = new javax.swing.JPanel();
        LblCodigo1 = new Label.Label();
        TxtBusqCliente = new TextField.JTxtNinguno();
        btnBuscar1 = new BotonesABM.BtnBuscar();
        LblColaPedidos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblDato = new javax.swing.JTable()
        ;
        jPanel7 = new javax.swing.JPanel();
        btnExportar4 = new BotonesABM.BtnExportar();
        BtnRefrescar = new javax.swing.JButton();
        btnAceptar1 = new BotonesABM.BtnAceptar();

        jMenuItemCancelarPedido.setText("Cancelar Pedido");
        jPopupMenuPedido.add(jMenuItemCancelarPedido);

        jMenuItemAnularPedido.setText("Anular Pedido");
        jPopupMenuPedido.add(jMenuItemAnularPedido);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busqueda Ingreso a Almacén");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tbBusqueda.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbBusquedaStateChanged(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(153, 0, 0))); // NOI18N

        chkFecha.setText("DEL :");

        DtChFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaPropertyChange(evt);
            }
        });

        TxtCliente.setTamanio(250);
        TxtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtClienteActionPerformed(evt);
            }
        });
        TxtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtClienteKeyReleased(evt);
            }
        });

        lblCliente.setText("Proveed.");

        lblTipocomp.setText("Tipo Ingreso :");

        CbxTipoIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoIngresoActionPerformed(evt);
            }
        });

        CbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxEstadoActionPerformed(evt);
            }
        });

        LblEstado.setText("Estado          :");

        DtChFechaFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaFinPropertyChange(evt);
            }
        });

        chkFecha1.setText("AL  :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DtChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DtChFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblTipocomp, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(CbxTipoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                    .addComponent(CbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTipocomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CbxTipoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(DtChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkFecha1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(DtChFechaFin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tbBusqueda.addTab("Busqueda Avanzada", jPanel3);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        LblCodigo.setText("Numero Doc:");

        TxtCodigo.setTamanio(150);
        TxtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtCodigoKeyReleased(evt);
            }
        });

        btnBuscar2.setText("Buscar Numero Doc");
        btnBuscar2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscar2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscar2.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tbBusqueda.addTab("Busqueda Por Codigo", jPanel5);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        LblCodigo1.setText("Proveedor :");

        TxtBusqCliente.setTamanio(150);
        TxtBusqCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtBusqClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtBusqClienteKeyReleased(evt);
            }
        });

        btnBuscar1.setText("Buscar Proveedor");
        btnBuscar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscar1.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtBusqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtBusqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tbBusqueda.addTab("Busqueda por Proveedor", jPanel4);

        LblColaPedidos.setFont(new java.awt.Font("Arial", 0, 14));
        LblColaPedidos.setForeground(new java.awt.Color(153, 0, 0));
        LblColaPedidos.setText("Lista Comprobante Venta");

        TblDato.setFont(new java.awt.Font("Arial", 0, 14));
        TblDato.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha",
                "Numero Doc.",
                "Proveedor",
                "Tip.Comprob.",
                "Tipo Ingreso",
                "Nº Ingreso",
                "Estado"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.Object.class,
                java.lang.String.class,
                java.lang.Object.class,
                java.lang.String.class,
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false,false,false,false,false,false,false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblDato.setRowHeight(20);
        TblDato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblDatoMouseClicked(evt);
            }
        });
        TblDato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TblDatoFocusLost(evt);
            }
        });
        TblDato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblDatoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TblDato);

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        btnExportar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/icono-excel.png"))); // NOI18N
        btnExportar4.setText("");
        btnExportar4.setToolTipText("Exportar");
        btnExportar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar4ActionPerformed(evt);
            }
        });
        jPanel7.add(btnExportar4);

        BtnRefrescar.setFont(new java.awt.Font("Verdana", 1, 12));
        BtnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Refrescar.png"))); // NOI18N
        BtnRefrescar.setToolTipText("Refrescar");
        BtnRefrescar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnRefrescar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnRefrescar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel7.add(BtnRefrescar);
        BtnRefrescar.getAccessibleContext().setAccessibleName("0");
        BtnRefrescar.getAccessibleContext().setAccessibleDescription("");

        btnAceptar1.setText("");
        btnAceptar1.setToolTipText("Seleccionar");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });
        jPanel7.add(btnAceptar1);
        btnAceptar1.getAccessibleContext().setAccessibleName("0");
        btnAceptar1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(tbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblColaPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(808, 808, 808)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(29, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblColaPedidos)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(556, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


  
    private void TblDatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TblDatoFocusLost

}//GEN-LAST:event_TblDatoFocusLost

    private void TblDatoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblDatoMouseClicked

        if (evt.getClickCount()==2) {
            SelecionarRegistro();
        }
}//GEN-LAST:event_TblDatoMouseClicked

    
    private void DtChFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaPropertyChange

        if(cargar)
        if(!iscerrando){
            if(DtChFecha.getDate()!=null){
            filtrarTabla();

            }
             if(evt.getPropertyName().equals("date")) {
                DtChFechaFin.setMinSelectableDate(DtChFecha.getDate());
            }
        }
    }//GEN-LAST:event_DtChFechaPropertyChange
 boolean iscerrando=false;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        iscerrando=true;
    }//GEN-LAST:event_formWindowClosing

    private void TxtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCodigoKeyPressed

        if(evt.getKeyCode()==evt.VK_ENTER) {
            filtrarTabla();

        }

    }//GEN-LAST:event_TxtCodigoKeyPressed

    private long id_proveedor;
    private void buscarProveedor()
    {
          DlgGestionProveedor odialogo= new DlgGestionProveedor(oparent, true,1,1,1);//consultar

          odialogo.setCajaTexo(TxtBusqCliente.getText().trim());
          odialogo.setVisible(true);
          odialogo.setTitle("Busqueda de Proveedor");
                  if(odialogo.getoCEProveedor()!=null){
                    id_proveedor=odialogo.getoCEProveedor().getIdProveedor();
                    TxtBusqCliente.setText(odialogo.getoCEProveedor().getRazonSocial());
                    }

      }
    private void TxtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCodigoKeyReleased
        if(evt.getKeyCode()==evt.VK_DOWN) {

        if(TblDato.getRowCount()>0)
        {
          TblDato.requestFocus();
          TblDato.changeSelection(0, 0, false, false);
        }
        }
    }//GEN-LAST:event_TxtCodigoKeyReleased

    private void TxtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtClienteActionPerformed

    private void TxtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyPressed

        if(evt.getKeyCode()==evt.VK_ENTER) {
         filtarPorProveedor();
        }
        
    }//GEN-LAST:event_TxtClienteKeyPressed

    private void filtarPorProveedor()
    {
    limpiarTabla();
         for(CEIngresoProducto vCEIngresoProducto:oListaIngresoProducto )
                {
                    if(vCEIngresoProducto.toString().contains(TxtCliente.getText().toUpperCase()))
                    {
                         ArrayList oArrayList=new ArrayList();
                        oArrayList.add(vCEIngresoProducto.getFecha());
                        oArrayList.add(vCEIngresoProducto.getNumeroDocumento());
                        oArrayList.add(vCEIngresoProducto);
                        oArrayList.add(vCEIngresoProducto.getTipoComprobante());
                        oArrayList.add(vCEIngresoProducto.getTipoIngreso());
                        oArrayList.add(vCEIngresoProducto.getNumeroIngreso());
                        oArrayList.add(vCEIngresoProducto.getUltimoEstado());
                        ((ArrayListTableModel)TblDato.getModel()).addRow(oArrayList);
                    }
                }

    }
    private void TxtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyReleased


        if(evt.getKeyCode()==evt.VK_DOWN) {
            if(TblDato.getRowCount()>0)
        {
          TblDato.requestFocus();
          TblDato.changeSelection(0, 0, false, false);
        }
        }
        

    }//GEN-LAST:event_TxtClienteKeyReleased

    private void CbxTipoIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoIngresoActionPerformed

        if(cargar)
        filtrarTabla();
    }//GEN-LAST:event_CbxTipoIngresoActionPerformed

    private void CbxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxEstadoActionPerformed
        if(cargar)
        filtrarTabla();
    }//GEN-LAST:event_CbxEstadoActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        buscarProveedor();
        filtrarTabla();
}//GEN-LAST:event_btnBuscar1ActionPerformed

    private void TxtBusqClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtBusqClienteKeyPressed
         if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarProveedor();
             filtrarTabla();

        }

    }//GEN-LAST:event_TxtBusqClienteKeyPressed

    private void TxtBusqClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtBusqClienteKeyReleased
         if(evt.getKeyCode()==evt.VK_ENTER) {            

            filtrarTabla();
        }

    }//GEN-LAST:event_TxtBusqClienteKeyReleased

    private void tbBusquedaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbBusquedaStateChanged

        if(cargar){
        id_proveedor=-1;
        if(tbBusqueda.getSelectedIndex()==0){
                TxtCliente.setText("");
            }else if (tbBusqueda.getSelectedIndex()==1){
                TxtCodigo.setText("");
            }else if (tbBusqueda.getSelectedIndex()==2){
                TxtBusqCliente.setText("");
            }
            filtrarTabla();
        }
    }//GEN-LAST:event_tbBusquedaStateChanged

    private void btnBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar2ActionPerformed
        filtrarTabla();
    }//GEN-LAST:event_btnBuscar2ActionPerformed

    private void TblDatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblDatoKeyPressed

        TblDato.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
        TblDato.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                SelecionarRegistro();


            }
        });

    }//GEN-LAST:event_TblDatoKeyPressed

    private void DtChFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaFinPropertyChange

        if(cargar)
        if(!iscerrando){
            if(DtChFecha.getDate()!=null){
            filtrarTabla();

            }
        }
    }//GEN-LAST:event_DtChFechaFinPropertyChange

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
          SelecionarRegistro();
    }//GEN-LAST:event_btnAceptar1ActionPerformed

    private void btnExportar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar4ActionPerformed
        CLExportarExcel oExportar=new CLExportarExcel(TblDato,
                new String[]{},new String[]{},new String[]{}
        ,this.getTitle());
        oExportar.GuardarArchivoExcel(this);
    }//GEN-LAST:event_btnExportar4ActionPerformed

    CEIngresoProducto oCEIngresoProducto=null;

public CEIngresoProducto getCEIngresoProducto()
{
    return oCEIngresoProducto;
}
private void SelecionarRegistro()
    {
        int filSel=TblDato.getSelectedRow();
        if(filSel!=-1){
        oCEIngresoProducto=(CEIngresoProducto)TblDato.getValueAt(filSel, 2);
        dispose();
        }else
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    private List<CEIngresoProducto> listarRegistros()
    {
        String dato=TxtCodigo.getText();
        int id_TipoComp=1;
        int id_Estado=2;
        CETipoIngresoSalida oCETipIngreso=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
        if(oCETipIngreso!=null){
            id_TipoComp=oCETipIngreso.getIdTipoIngresoSalida();
        }
        CEEstado oCEEstado=(CEEstado)CbxEstado.getSelectedItem();
        if(oCEEstado!=null){
            id_Estado=oCEEstado.getmIntIdEstado();
        }
        if(!dato.equals("")){
//        if(RbtCliente.isSelected())
//        {
//            return CCIngresoProducto.BuscarIngresoProducto(3, dato,1);
//        }
        if(tbBusqueda.getSelectedIndex()==1){
            return CCIngresoProducto.BuscarPorCodigo(dato);
                }
        
        }
        if(tbBusqueda.getSelectedIndex()==2){
            
            if(id_proveedor!=-1)
            {
                return CCIngresoProducto.BuscarPorProveedor(id_proveedor);
            }
        }

        if(tbBusqueda.getSelectedIndex()==0)
        {
            if(DtChFecha.getCalendar()!=null){
             ConvertidorFecha ocConvertidorFecha= new ConvertidorFecha();
            ocConvertidorFecha.setFecha(DtChFecha.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFecha=ocConvertidorFecha.getFechaSimple2();
            ocConvertidorFecha.setFecha(DtChFechaFin.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFechaFin=ocConvertidorFecha.getFechaSimple2();
           return CCIngresoProducto.BusqAvanzadaIngreso(pFecha,id_Estado,id_TipoComp,pFechaFin);
            }
        }
        return null;
      

    }

    private void filtrarTabla()
    {
        oListaIngresoProducto=listarRegistros();
       limpiarTabla();
       if(oListaIngresoProducto!=null)
       {

                for(CEIngresoProducto pCEIngresoProducto:oListaIngresoProducto )
                {
                        ArrayList oArrayList=new ArrayList();
                        oArrayList.add(pCEIngresoProducto.getFecha());
                        oArrayList.add(pCEIngresoProducto.getNumeroDocumento());
                        oArrayList.add(pCEIngresoProducto);
                        oArrayList.add(pCEIngresoProducto.getTipoComprobante());
                        oArrayList.add(pCEIngresoProducto.getTipoIngreso());
                        oArrayList.add(pCEIngresoProducto.getNumeroIngreso());
                        oArrayList.add(pCEIngresoProducto.getUltimoEstado());
                        ((ArrayListTableModel)TblDato.getModel()).addRow(oArrayList);
                    
                }
                LblColaPedidos.setText("Cola de Registros: "+TblDato.getRowCount()+" registros(s) Encontrados");
            
        }
       if(TblDato.getRowCount()>0)
        {
          TblDato.requestFocus();
          TblDato.changeSelection(0, 0, false, false);
        }

    }
    private void limpiarTabla()
    {
        int size=TblDato.getRowCount();
        for(int i=0;i<size;i++ )
        {
        ((ArrayListTableModel)TblDato.getModel()).removeRow(0);
        }
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRefrescar;
    private ComboBox.ComboBox CbxEstado;
    private ComboBox.ComboBox CbxTipoIngreso;
    private com.toedter.calendar.JDateChooser DtChFecha;
    private com.toedter.calendar.JDateChooser DtChFechaFin;
    private Label.Label LblCodigo;
    private Label.Label LblCodigo1;
    private javax.swing.JLabel LblColaPedidos;
    private Label.Label LblEstado;
    private javax.swing.JTable TblDato;
    private TextField.JTxtNinguno TxtBusqCliente;
    private TextField.JTxtNinguno TxtCliente;
    private TextField.JTxtNinguno TxtCodigo;
    private BotonesABM.BtnAceptar btnAceptar1;
    private BotonesABM.BtnBuscar btnBuscar1;
    private BotonesABM.BtnBuscar btnBuscar2;
    private BotonesABM.BtnExportar btnExportar4;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private Label.Label chkFecha;
    private Label.Label chkFecha1;
    private javax.swing.JMenuItem jMenuItemAnularPedido;
    private javax.swing.JMenuItem jMenuItemCancelarPedido;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenuPedido;
    private javax.swing.JScrollPane jScrollPane1;
    private Label.Label lblCliente;
    private Label.Label lblTipocomp;
    private javax.swing.JTabbedPane tbBusqueda;
    // End of variables declaration//GEN-END:variables
   
}
