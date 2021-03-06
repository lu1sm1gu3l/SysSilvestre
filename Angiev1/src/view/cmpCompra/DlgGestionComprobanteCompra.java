package view.cmpCompra;

import controller.cmpCompra.CCComprobanteCompra;
import controller.grlGeneral.CCEstado;
import controller.vtaVenta.CCTipoComprobante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import modelo.cmpCompra.entidad.CEComprobanteCompraMatriz;
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CETipoComprobante;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import view.FrmSistemaMenu;
import view.almAlmacen.invInventarios.DlgIngresoProducto1;
import view.cmrComercial.DlgGestionProveedor;


public class DlgGestionComprobanteCompra extends DialogoPadre implements ActionListener
{

    private List<CEComprobanteCompraMatriz> oListaComprobanteCompra;
    private java.awt.Frame oparent;
    private boolean cargar;
    public DlgGestionComprobanteCompra(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        cargar=false;
        CLBotonesABM oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(null, null, null, null, null, null,this);
        this.setLocationRelativeTo(null);
        configurarTabla();
        DtChFecha.setDate(FrmSistemaMenu.FechaSistema);
        DtChFechaFinal.setDate(FrmSistemaMenu.FechaSistema);
        DtChFechaFinal.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        DtChFecha.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        CbxTipoComp.setModel(CLComboBox.cargarCombo(CCTipoComprobante.listarCompCompraMasTodos()));
        CbxEstadoInventario.setModel(CLComboBox.cargarCombo(CCEstado.ListadoEstadoCompras_Mas_Todos()));
        CbxEstadoContable.setModel(CLComboBox.cargarCombo(CCEstado.ListadoEstadoContableMasTodos()));
        CbxTipoComp.setSelectedIndex(1);
        this.oparent=parent;
        cargar=true;
        filtrarTabla();
        BtnNotaCredito.setEnabled(false);
    }
    private void configurarTabla()
    {
        TableColumnModel oTableColumnModel=TblDato.getColumnModel();
        oTableColumnModel.getColumn(0).setPreferredWidth(90);
        oTableColumnModel.getColumn(1).setPreferredWidth(90);
        oTableColumnModel.getColumn(2).setPreferredWidth(380);
        oTableColumnModel.getColumn(3).setPreferredWidth(90);
        oTableColumnModel.getColumn(4).setPreferredWidth(60);
        oTableColumnModel.getColumn(5).setPreferredWidth(95);
        oTableColumnModel.getColumn(6).setPreferredWidth(95);
        oTableColumnModel.getColumn(7).setPreferredWidth(120);
        oTableColumnModel.getColumn(8).setPreferredWidth(120);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);

        DefaultTableCellRenderer tccent = new DefaultTableCellRenderer();
        tccent.setHorizontalAlignment(SwingConstants.CENTER);

        TblDato.getColumnModel().getColumn(1).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(6).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(4).setCellRenderer(tccent);

        TblDato.getColumnModel().getColumn(5).setCellRenderer(tcr);
  
    }

public void ocultarBotones()
{
 
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
        CbxTipoComp = new ComboBox.ComboBox();
        CbxEstadoContable = new ComboBox.ComboBox();
        LblEstado = new Label.Label();
        ChkCompDir = new javax.swing.JCheckBox();
        CbxEstadoInventario = new ComboBox.ComboBox();
        LblEstado1 = new Label.Label();
        chkFecha1 = new Label.Label();
        DtChFechaFinal =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jPanel5 = new javax.swing.JPanel();
        LblCodigo = new Label.Label();
        TxtCodigo = new TextField.JTxtNinguno();
        btnBuscar2 = new BotonesABM.BtnBuscar();
        jPanel4 = new javax.swing.JPanel();
        LblCodigo1 = new Label.Label();
        TxtBusqCliente = new TextField.JTxtNinguno();
        btnBuscar1 = new BotonesABM.BtnBuscar();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblDato = new javax.swing.JTable()
        ;
        LblColaPedidos = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnRecepcion = new BotonesABM.BtnEliminar();
        BtnVerDetalles1 = new javax.swing.JButton();
        BtnNotaCredito = new javax.swing.JButton();
        btnPagar = new BotonesABM.BtnGuardar();
        btnAnular = new BotonesABM.BtnEliminar();
        BtnRefrescar = new javax.swing.JButton();
        btnExportar1 = new BotonesABM.BtnExportar();

        jMenuItemCancelarPedido.setText("Cancelar Pedido");
        jPopupMenuPedido.add(jMenuItemCancelarPedido);

        jMenuItemAnularPedido.setText("Anular Pedido");
        jPopupMenuPedido.add(jMenuItemAnularPedido);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Compras");
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
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkFecha.setText("DEL:");
        jPanel3.add(chkFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        DtChFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaPropertyChange(evt);
            }
        });
        jPanel3.add(DtChFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 120, -1));

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
        jPanel3.add(TxtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 340, -1));

        lblCliente.setText("Proveedor            :");
        jPanel3.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 120, -1));

        lblTipocomp.setText("Comprobante      :");
        jPanel3.add(lblTipocomp, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 10, 120, -1));

        CbxTipoComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoCompActionPerformed(evt);
            }
        });
        jPanel3.add(CbxTipoComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 140, 20));

        CbxEstadoContable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxEstadoContableActionPerformed(evt);
            }
        });
        jPanel3.add(CbxEstadoContable, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 140, 20));

        LblEstado.setText("Estado Contable:");
        jPanel3.add(LblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 110, -1));

        ChkCompDir.setBackground(new java.awt.Color(204, 204, 204));
        ChkCompDir.setFont(new java.awt.Font("Verdana", 0, 11));
        ChkCompDir.setText("Solo Compra Directa");
        ChkCompDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkCompDirActionPerformed(evt);
            }
        });
        jPanel3.add(ChkCompDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, -1, 20));

        CbxEstadoInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxEstadoInventarioActionPerformed(evt);
            }
        });
        jPanel3.add(CbxEstadoInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 160, 20));

        LblEstado1.setText("Estado Inventario:");
        jPanel3.add(LblEstado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, -1));

        chkFecha1.setText("AL  :");
        jPanel3.add(chkFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        DtChFechaFinal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaFinalPropertyChange(evt);
            }
        });
        jPanel3.add(DtChFechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 120, -1));

        tbBusqueda.addTab("Busqueda Avanzada", jPanel3);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        LblCodigo.setText("Numero :");

        TxtCodigo.setTamanio(150);
        TxtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtCodigoKeyReleased(evt);
            }
        });

        btnBuscar2.setText("Buscar Numero");
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
                .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tbBusqueda.addTab("Busqueda Por Numero", jPanel5);

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
                .addContainerGap(266, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtBusqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tbBusqueda.addTab("Busqueda por Proveedor", jPanel4);

        TblDato.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TblDato.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha",
                "Código",
                "Proveedor",
                "Tip.Comprob.",
                "Moneda",
                "Total",
                "Deuda",
                "Estado Contab",
                "Estado Invent"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.Object.class,
                java.lang.String.class,
                java.lang.Object.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false,false,false,false,false,false,false,false,false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblDato.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblDatoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblDato);

        LblColaPedidos.setFont(new java.awt.Font("Arial", 0, 14));
        LblColaPedidos.setForeground(new java.awt.Color(153, 0, 0));
        LblColaPedidos.setText("Lista Comprobante Venta");

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRecepcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Entrada.png"))); // NOI18N
        btnRecepcion.setText("RECEP.");
        btnRecepcion.setToolTipText("Recepcionar Entrada");
        btnRecepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecepcionActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, -1, -1));

        BtnVerDetalles1.setFont(new java.awt.Font("Verdana", 1, 12));
        BtnVerDetalles1.setText("<html><center>Ver<br>Detalles</center></html>");
        BtnVerDetalles1.setToolTipText("Ver Detalles");
        BtnVerDetalles1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnVerDetalles1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnVerDetalles1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        BtnVerDetalles1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerDetalles1ActionPerformed(evt);
            }
        });
        jPanel1.add(BtnVerDetalles1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 90, 70));

        BtnNotaCredito.setFont(new java.awt.Font("Verdana", 1, 12));
        BtnNotaCredito.setText("<html><center>Nota de<br>Crédito</center></html>");
        BtnNotaCredito.setToolTipText("Nota de Crédito");
        BtnNotaCredito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnNotaCredito.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnNotaCredito.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        BtnNotaCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotaCreditoActionPerformed(evt);
            }
        });
        jPanel1.add(BtnNotaCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 90, 70));

        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Pago.png"))); // NOI18N
        btnPagar.setText("Pagar");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });
        jPanel1.add(btnPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, -1, -1));

        btnAnular.setText("Anular");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnular, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, -1));

        BtnRefrescar.setFont(new java.awt.Font("Verdana", 1, 12));
        BtnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Refrescar.png"))); // NOI18N
        BtnRefrescar.setText("Refrescar");
        BtnRefrescar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnRefrescar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnRefrescar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefrescarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 70));
        BtnRefrescar.getAccessibleContext().setAccessibleName("0");
        BtnRefrescar.getAccessibleContext().setAccessibleDescription("Refrescar");

        btnExportar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/icono-excel.png"))); // NOI18N
        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblColaPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tbBusqueda, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblColaPedidos)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void BtnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefrescarActionPerformed

       BtnRefrescar.setEnabled(false);        
        filtrarTabla();        
        BtnRefrescar.setEnabled(true);
    }//GEN-LAST:event_BtnRefrescarActionPerformed

  
    private void TblDatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TblDatoFocusLost

}//GEN-LAST:event_TblDatoFocusLost

    private void TblDatoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblDatoMouseClicked

        HabilitarBotonesdeCompra();
        if(evt.getClickCount()==2)
            pagar();
}//GEN-LAST:event_TblDatoMouseClicked

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
          odialogo.setTitle("Busqueda de Cliente");
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

    private void filtarPorsujeto()
    {
    limpiarTabla();
         for(CEComprobanteCompraMatriz pCEComprobanteCompraMatriz:oListaComprobanteCompra )
                {
                    if(pCEComprobanteCompraMatriz.toString().contains(TxtCliente.getText().toUpperCase()))
                    {
                         ArrayList oArrayList=new ArrayList();
                        oArrayList.add(pCEComprobanteCompraMatriz.getFecha());
                        oArrayList.add(pCEComprobanteCompraMatriz.getNumComprobante());
                        oArrayList.add(pCEComprobanteCompraMatriz);
                        oArrayList.add(pCEComprobanteCompraMatriz.getTipoComprobante());
                        oArrayList.add(pCEComprobanteCompraMatriz.getMoneda());
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteCompraMatriz.getTotalPagar()));
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteCompraMatriz.getSaldoPagar()));
                        oArrayList.add(pCEComprobanteCompraMatriz.getUltimoEstado());
                        oArrayList.add(pCEComprobanteCompraMatriz.getEstadoInventario());
                        ((ArrayListTableModel)TblDato.getModel()).addRow(oArrayList);
                    }
                }

    }
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

                pagar();


            }
        });
     

    }//GEN-LAST:event_TblDatoKeyPressed

    private void BtnVerDetalles1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerDetalles1ActionPerformed

        int fila = TblDato.getSelectedRow();

    if(fila!=-1){

    CEComprobanteCompraMatriz vCEComprobanteCompraMatriz=(CEComprobanteCompraMatriz)TblDato.getValueAt(fila,2);
    DlgDetalleComprobanteCompra oDlgCompra= new DlgDetalleComprobanteCompra(oparent,true , vCEComprobanteCompraMatriz.getIdComprobanteCompra());
    oDlgCompra.setLocationRelativeTo(null);
    oDlgCompra.setVisible(true);
    }
    else{
            JOptionPane.showMessageDialog(null,"Seleccione una fila","Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_BtnVerDetalles1ActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed

        pagar();
    }//GEN-LAST:event_btnPagarActionPerformed

    private boolean validarAnulacion(long IdComprobanteCompra) {


            CEEstado oCEEstadoContab=CCEstado.consultarUltimoestadoCompra_contable(IdComprobanteCompra);
            if(oCEEstadoContab.getmIntIdEstado()!=14)
            {
             JOptionPane.showMessageDialog(this,"El Registro ya fué "+oCEEstadoContab.getmStrDescripcion()+", No se Puede Anular","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
            CEEstado oCEEstadoInvent=CCEstado.consultarUltimoestadoCompra_inventario(IdComprobanteCompra);
            if(oCEEstadoInvent.getmIntIdEstado()!=13)
            {
             JOptionPane.showMessageDialog(this,"El Registro ya fué "+oCEEstadoInvent.getmStrDescripcion()+", No se Puede Anular","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }



        return true;
    }

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed

        
    int fila = TblDato.getSelectedRow();

    if(fila!=-1){

    CEComprobanteCompraMatriz vCEComprobanteCompraMatriz=(CEComprobanteCompraMatriz)TblDato.getValueAt(fila,2);
    
    DlgAnularComprobanteCompra oDlgAnularCompra= new DlgAnularComprobanteCompra(null,true , vCEComprobanteCompraMatriz);
    oDlgAnularCompra.setLocationRelativeTo(null);
    oDlgAnularCompra.setVisible(true);
        if(oDlgAnularCompra.IsAnulado()){
            if(validarAnulacion(vCEComprobanteCompraMatriz.getIdComprobanteCompra()))
            {
                if(CCComprobanteCompra.AnularComprobanteCompra(vCEComprobanteCompraMatriz)==1)
                     {
                        JOptionPane.showMessageDialog(null, "operación exitosa");
                        filtrarTabla();
                     }
                    else
                     {
                        JOptionPane.showMessageDialog(null, "No se pudo completar la operación");
                     }

            }
          }
    }
    else{
            JOptionPane.showMessageDialog(null,"Seleccione una fila","Mensaje", JOptionPane.ERROR_MESSAGE);
        }    



    }//GEN-LAST:event_btnAnularActionPerformed

    private void BtnNotaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotaCreditoActionPerformed
       
      int fila = TblDato.getSelectedRow();
      if(fila!=-1){
       CEComprobanteCompraMatriz vCEComprobanteCompraMatriz=(CEComprobanteCompraMatriz)TblDato.getValueAt(fila,2);
       DlgNotaCreditoProveedor dialogo= new DlgNotaCreditoProveedor(null, false,1,vCEComprobanteCompraMatriz.getIdComprobanteCompra());
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
        }
    else{
             JOptionPane.showMessageDialog(null,"Seleccione una fila","Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_BtnNotaCreditoActionPerformed

    private void DtChFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaPropertyChange

        if(cargar)
            if(!iscerrando){
                if(DtChFecha.getDate()!=null){
                    filtrarTabla();

                }
                if(evt.getPropertyName().equals("date")) {
                DtChFechaFinal.setMinSelectableDate(DtChFecha.getDate());
            }
            }
}//GEN-LAST:event_DtChFechaPropertyChange

    private void TxtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtClienteActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_TxtClienteActionPerformed

    private void TxtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyPressed

        if(evt.getKeyCode()==evt.VK_ENTER) {
            filtarPorsujeto();
        }
}//GEN-LAST:event_TxtClienteKeyPressed

    private void TxtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyReleased


        if(evt.getKeyCode()==evt.VK_DOWN) {
            if(TblDato.getRowCount()>0) {
                TblDato.requestFocus();
                TblDato.changeSelection(0, 0, false, false);
            }
        }
    }//GEN-LAST:event_TxtClienteKeyReleased

    private void CbxTipoCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoCompActionPerformed

        if(cargar)
            filtrarTabla();
}//GEN-LAST:event_CbxTipoCompActionPerformed

    private void CbxEstadoContableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxEstadoContableActionPerformed
        if(cargar)
            filtrarTabla();
}//GEN-LAST:event_CbxEstadoContableActionPerformed

    private void ChkCompDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkCompDirActionPerformed
        filtrarTabla();
}//GEN-LAST:event_ChkCompDirActionPerformed

    private void CbxEstadoInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxEstadoInventarioActionPerformed
        if(cargar)
            filtrarTabla();
}//GEN-LAST:event_CbxEstadoInventarioActionPerformed

    private void DtChFechaFinalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaFinalPropertyChange

        if(cargar)
            if(!iscerrando){
                if(DtChFechaFinal.getDate()!=null){
                    filtrarTabla();

                }
            }
}//GEN-LAST:event_DtChFechaFinalPropertyChange

    private void btnRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecepcionActionPerformed

        int fila = TblDato.getSelectedRow();

    if(fila!=-1){

        CEComprobanteCompraMatriz vCEComprobanteCompraMatriz=(CEComprobanteCompraMatriz)TblDato.getValueAt(fila,2);
        DlgIngresoProducto1 oDlgIngresoProducto=new DlgIngresoProducto1(oparent, true,true);

        oDlgIngresoProducto.SetIngresoProductoPorCompra(CCComprobanteCompra.ConsultarComprasPorId(vCEComprobanteCompraMatriz.getIdComprobanteCompra()));
        oDlgIngresoProducto.setVisible(true);
        filtrarTabla();

        }
        else{
            JOptionPane.showMessageDialog(null,"Seleccione una fila","Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnRecepcionActionPerformed

    private void HabilitarBotonesdeCompra()
   {
        try {
        int fila = TblDato.getSelectedRow();
            if(fila>=0){
            CEComprobanteCompraMatriz vCEComprobanteCompraMatriz=(CEComprobanteCompraMatriz)TblDato.getValueAt(fila,2);
                if(vCEComprobanteCompraMatriz.getUltimoIdEstado()==4)
                {
                    BtnNotaCredito.setEnabled(false);
                    btnRecepcion.setEnabled(false);
                }else
                {
                    BtnNotaCredito.setEnabled(true);
                    btnRecepcion.setEnabled(true);
                }
            }
       }
        catch(Exception e){

        }
   }

    private void TblDatoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblDatoKeyReleased
       
    HabilitarBotonesdeCompra();
    }//GEN-LAST:event_TblDatoKeyReleased

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed

        CLExportarExcel oExportar=new CLExportarExcel(TblDato,
                new String[]{},new String[]{},new String[]{}
        ,this.getTitle());
        oExportar.GuardarArchivoExcel(this);
}//GEN-LAST:event_btnExportar1ActionPerformed


    private void pagar()
    {

    int fila = TblDato.getSelectedRow();

    if(fila!=-1){

    CEComprobanteCompraMatriz vCEComprobanteCompraMatriz=(CEComprobanteCompraMatriz)TblDato.getValueAt(fila,2);
    DlgPagosPorCompra oDlgPagoPorOC=new DlgPagosPorCompra(oparent,true,vCEComprobanteCompraMatriz.getIdComprobanteCompra());
    oDlgPagoPorOC.setLocationRelativeTo(null);
    oDlgPagoPorOC.setVisible(true);
    filtrarTabla();
    }
    else{
            JOptionPane.showMessageDialog(null,"Seleccione una fila","Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    CEComprobanteCompraMatriz oCEComprobanteCompraMatriz=null;

public CEComprobanteCompraMatriz getCEComprobanteCompraMatriz()
{
    return oCEComprobanteCompraMatriz;
}
private void SelecionarRegistro()
    {
        int filSel=TblDato.getSelectedRow();
        if(filSel!=-1){
        oCEComprobanteCompraMatriz=(CEComprobanteCompraMatriz)TblDato.getValueAt(filSel, 2);
        dispose();

        }else
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    private List<CEComprobanteCompraMatriz> listarRegistros()
    {
        String dato=TxtCodigo.getText();
        int id_TipoComp=1;
        int id_Estado=0;
        int id_EstadoInv=0;
        CETipoComprobante oCETipoComprobante=(CETipoComprobante)CbxTipoComp.getSelectedItem();
        if(oCETipoComprobante!=null){
            id_TipoComp=oCETipoComprobante.getIdTipoComprobante();
        }
        CEEstado oCEEstado=(CEEstado)CbxEstadoContable.getSelectedItem();
        if(oCEEstado!=null){
            id_Estado=oCEEstado.getmIntIdEstado();
        }
        oCEEstado=(CEEstado)CbxEstadoInventario.getSelectedItem();
        if(oCEEstado!=null){
            id_EstadoInv=oCEEstado.getmIntIdEstado();
        }
        if(!dato.equals("")){
        if(tbBusqueda.getSelectedIndex()==1){
            return CCComprobanteCompra.BuscarPorCodigo(dato);
                }

        }
        if(tbBusqueda.getSelectedIndex()==2){

            if(id_proveedor!=-1)
            {
                return CCComprobanteCompra.BuscarOCPorProveedor(id_proveedor);
            }
        }

        if(tbBusqueda.getSelectedIndex()==0)
        {
            if(DtChFecha.getCalendar()!=null){
             ConvertidorFecha ocConvertidorFecha= new ConvertidorFecha();
            ocConvertidorFecha.setFecha(DtChFecha.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFecha=ocConvertidorFecha.getFechaSimple2();

            ocConvertidorFecha= new ConvertidorFecha();
            ocConvertidorFecha.setFecha(DtChFechaFinal.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFechaFin=ocConvertidorFecha.getFechaSimple2();

           
            return CCComprobanteCompra.BusqAvanzadaCompra(pFecha,id_Estado,id_TipoComp,id_EstadoInv,pFechaFin,ChkCompDir.isSelected());
           }
        }
        return null;


    }

    private void filtrarTabla()
    {
        oListaComprobanteCompra=listarRegistros();
       limpiarTabla();
       if(oListaComprobanteCompra!=null)
       {

                for(CEComprobanteCompraMatriz pCEComprobanteCompraMatriz:oListaComprobanteCompra )
                {
                        ArrayList oArrayList=new ArrayList();
                        oArrayList.add(pCEComprobanteCompraMatriz.getFecha());
                        oArrayList.add(pCEComprobanteCompraMatriz.getNumComprobante());
                        oArrayList.add(pCEComprobanteCompraMatriz);
                        oArrayList.add(pCEComprobanteCompraMatriz.getTipoComprobante());
                        oArrayList.add(pCEComprobanteCompraMatriz.getMoneda());
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteCompraMatriz.getTotalPagar()));
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteCompraMatriz.getSaldoPagar()));
                        oArrayList.add(pCEComprobanteCompraMatriz.getUltimoEstado());
                        oArrayList.add(pCEComprobanteCompraMatriz.getEstadoInventario());
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
    private javax.swing.JButton BtnNotaCredito;
    private javax.swing.JButton BtnRefrescar;
    private javax.swing.JButton BtnVerDetalles1;
    private ComboBox.ComboBox CbxEstadoContable;
    private ComboBox.ComboBox CbxEstadoInventario;
    private ComboBox.ComboBox CbxTipoComp;
    private javax.swing.JCheckBox ChkCompDir;
    private com.toedter.calendar.JDateChooser DtChFecha;
    private com.toedter.calendar.JDateChooser DtChFechaFinal;
    private Label.Label LblCodigo;
    private Label.Label LblCodigo1;
    private javax.swing.JLabel LblColaPedidos;
    private Label.Label LblEstado;
    private Label.Label LblEstado1;
    private javax.swing.JTable TblDato;
    private TextField.JTxtNinguno TxtBusqCliente;
    private TextField.JTxtNinguno TxtCliente;
    private TextField.JTxtNinguno TxtCodigo;
    private BotonesABM.BtnEliminar btnAnular;
    private BotonesABM.BtnBuscar btnBuscar1;
    private BotonesABM.BtnBuscar btnBuscar2;
    private BotonesABM.BtnExportar btnExportar1;
    private BotonesABM.BtnGuardar btnPagar;
    private BotonesABM.BtnEliminar btnRecepcion;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private Label.Label chkFecha;
    private Label.Label chkFecha1;
    private javax.swing.JMenuItem jMenuItemAnularPedido;
    private javax.swing.JMenuItem jMenuItemCancelarPedido;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenuPedido;
    private javax.swing.JScrollPane jScrollPane1;
    private Label.Label lblCliente;
    private Label.Label lblTipocomp;
    private javax.swing.JTabbedPane tbBusqueda;
    // End of variables declaration//GEN-END:variables
    private static final String ACTION_CLOSE = "ACTION_CLOSE";
    private static final String ACTION_REFRESCAR = "ACTION_REFRESCAR";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke nuevo = KeyStroke.getKeyStroke(KeyEvent.VK_R,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_REFRESCAR, nuevo, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            dispose();
        }
        else
        {
            if(e.getActionCommand().equals(ACTION_REFRESCAR))
            {
               oListaComprobanteCompra=listarRegistros();
               TxtCodigo.requestFocus();
            }
        }
    }
}
