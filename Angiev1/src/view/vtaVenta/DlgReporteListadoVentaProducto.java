package view.vtaVenta;

import util.clases.grlGeneral.CLBotonesABM;
import controller.grlGeneral.CCEstado;
import controller.grlGeneral.CCValores;
import controller.vtaVenta.CCComprobanteVenta;
import controller.vtaVenta.CCTipoComprobante;
import java.awt.CardLayout;
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
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CEComprobanteVentaDetalle;
import modelo.vtaVenta.entidad.CEComprobanteVentaMatriz;
import modelo.vtaVenta.entidad.CETipoComprobante;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import view.FrmSistemaMenu;
import view.cmrComercial.DlgGestionCliente;
import view.vtaVenta.rptVTAReportes.view.DlgReporteComprobante;


public class DlgReporteListadoVentaProducto extends DialogoPadre implements ActionListener
{

    private List<CEComprobanteVentaDetalle> oListaComprobanteVenta;
    private java.awt.Frame oparent;
    private boolean cargar;

    public DlgReporteListadoVentaProducto(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        cargar=false;
        configurarTabla();
        DtChFecha.setDate(FrmSistemaMenu.FechaSistema);
        DtChFechaFin.setDate(FrmSistemaMenu.FechaSistema);
        DtChFechaFin.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        DtChFecha.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        //BtnDevolucion.setVisible(bolDev);
        CbxTipoComp.setModel(CLComboBox.cargarCombo(CCTipoComprobante.listarCompVentasMasTodos()));
        CbxEstado.setModel(CLComboBox.cargarCombo(CCEstado.ListadoEstado(0)));
        this.oparent=parent;
        cargar=true;
        filtrarTabla();
        CLBotonesABM oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(null, null, null, null, null, null,this);
    }
    public void ocultarBotones()
    {
//        BtnDevolucion.setVisible(false);        
        btnExportar1.setVisible(false);
        CLBotonesABM oclBotonesABM= new CLBotonesABM();
        this.setTitle("Busqueda Comprobante de Venta");
        oclBotonesABM.setBotones(null, null, null, null, null, null,this);
    }
    public void setVisibleBtnAceptar(boolean bol)
     {
        
    }
    private void configurarTabla()
    {
        TableColumnModel oTableColumnModel=tblComprobantes.getColumnModel();
        oTableColumnModel.getColumn(0).setPreferredWidth(80);
        oTableColumnModel.getColumn(1).setPreferredWidth(90);
        oTableColumnModel.getColumn(2).setPreferredWidth(70);
        oTableColumnModel.getColumn(3).setPreferredWidth(70);
        oTableColumnModel.getColumn(4).setPreferredWidth(290);
        oTableColumnModel.getColumn(5).setPreferredWidth(135);
        oTableColumnModel.getColumn(6).setPreferredWidth(85);
        oTableColumnModel.getColumn(7).setPreferredWidth(80);
        oTableColumnModel.getColumn(8).setPreferredWidth(80);
        oTableColumnModel.getColumn(9).setPreferredWidth(95);
        oTableColumnModel.getColumn(10).setPreferredWidth(100);
        oTableColumnModel.getColumn(11).setPreferredWidth(100);
        oTableColumnModel.getColumn(12).setPreferredWidth(100);
        oTableColumnModel.getColumn(13).setPreferredWidth(100);
        oTableColumnModel.getColumn(14).setPreferredWidth(100);
        oTableColumnModel.getColumn(15).setPreferredWidth(150);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);

        DefaultTableCellRenderer tccent = new DefaultTableCellRenderer();
        tccent.setHorizontalAlignment(SwingConstants.CENTER);

     //   TblPedidosPendientes.getColumnModel().getColumn(0).setCellRenderer(tccent);
        tblComprobantes.getColumnModel().getColumn(1).setCellRenderer(tccent);
        tblComprobantes.getColumnModel().getColumn(2).setCellRenderer(tccent);
        tblComprobantes.getColumnModel().getColumn(3).setCellRenderer(tccent);
        //TblPedidosPendientes.getColumnModel().getColumn(2).setCellRenderer(tccent);
       // tblComprobantes.getColumnModel().getColumn(5).setCellRenderer(tccent);
        tblComprobantes.getColumnModel().getColumn(6).setCellRenderer(tccent);

        tblComprobantes.getColumnModel().getColumn(8).setCellRenderer(tcr);
  
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
        CbxEstado = new ComboBox.ComboBox();
        LblEstado = new Label.Label();
        chkDsto = new Opcion.CheckBox();
        chkFecha1 = new Label.Label();
        DtChFechaFin =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jPanel5 = new javax.swing.JPanel();
        LblCodigo = new Label.Label();
        TxtCodigo = new TextField.JTxtNinguno();
        jPanel4 = new javax.swing.JPanel();
        LblCodigo1 = new Label.Label();
        TxtBusqCliente = new TextField.JTxtNinguno();
        btnBuscar1 = new BotonesABM.BtnBuscar();
        jPanel1 = new javax.swing.JPanel();
        btnExportar1 = new BotonesABM.BtnExportar();
        BtnRefrescar = new javax.swing.JButton();
        LblColaPedidos = new javax.swing.JLabel();
        PnlMostrar = new javax.swing.JPanel();
        PnlReporte = new javax.swing.JScrollPane();
        tblComprobantes = new javax.swing.JTable()
        ;
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();

        jMenuItemCancelarPedido.setText("Cancelar Pedido");
        jPopupMenuPedido.add(jMenuItemCancelarPedido);

        jMenuItemAnularPedido.setText("Anular Pedido");
        jPopupMenuPedido.add(jMenuItemAnularPedido);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comprobantes de Venta");
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

        chkFecha.setText("DEL:");

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

        lblCliente.setText("Cliente :");

        lblTipocomp.setText("Comprob:");

        CbxTipoComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoCompActionPerformed(evt);
            }
        });

        CbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxEstadoActionPerformed(evt);
            }
        });

        LblEstado.setText("Estado:");

        chkDsto.setBackground(new java.awt.Color(204, 204, 204));
        chkDsto.setText("Solo Con Dsto.");
        chkDsto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDstoActionPerformed(evt);
            }
        });

        chkFecha1.setText("AL :");

        DtChFechaFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaFinPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DtChFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(DtChFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblTipocomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbxTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CbxEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(chkDsto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chkFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(DtChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTipocomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CbxTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkDsto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DtChFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(chkFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tbBusqueda.addTab("Busqueda Avanzada", jPanel3);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        LblCodigo.setText("Serie - Numero :");

        TxtCodigo.setTamanio(150);
        TxtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtCodigoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(487, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tbBusqueda.addTab("Busqueda Por Numero", jPanel5);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        LblCodigo1.setText("Cliente :");

        TxtBusqCliente.setTamanio(150);
        TxtBusqCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtBusqClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtBusqClienteKeyReleased(evt);
            }
        });

        btnBuscar1.setText("Buscar Cliente");
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
                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(298, Short.MAX_VALUE))
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

        tbBusqueda.addTab("Busqueda por  Cliente", jPanel4);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExportar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/icono-excel.png"))); // NOI18N
        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, 70));

        BtnRefrescar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        BtnRefrescar.setText("Reportar");
        BtnRefrescar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnRefrescar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnRefrescar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefrescarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 70));
        BtnRefrescar.getAccessibleContext().setAccessibleName("0");

        LblColaPedidos.setFont(new java.awt.Font("Arial", 0, 14));
        LblColaPedidos.setForeground(new java.awt.Color(153, 0, 0));
        LblColaPedidos.setText("Lista Comprobante Venta");

        PnlMostrar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlMostrar.setLayout(new java.awt.CardLayout());

        tblComprobantes.setFont(new java.awt.Font("Arial", 0, 14));
        tblComprobantes.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha Emisión",
                "Hora",
                "Serie",
                "Número",
                "Cliente",
                "Nº Documento",
                "Tip.Comprob.",
                "Producto",
                "UnidadMedida",
                "Cantidad",
                "Precio",
                "Importe",
                "Total Cobrado",
                "Exonerado",
                "Subt. sin I.G.V",
                "I.G.V.",
                "Dsto Total",
                "Moneda",
                "Ultimo Estado",
                "Ultimo Usuario",
                "Ultima Fecha"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.Object.class,
                java.lang.String.class,
                java.lang.Object.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false,false,false,false,false,false,
                false,false,false,false,false,false,
                false,false,false,false,false,false
                ,false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblComprobantes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblComprobantes.setRowHeight(20);
        tblComprobantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblComprobantesMouseClicked(evt);
            }
        });
        tblComprobantes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblComprobantesFocusLost(evt);
            }
        });
        tblComprobantes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblComprobantesKeyPressed(evt);
            }
        });
        PnlReporte.setViewportView(tblComprobantes);

        PnlMostrar.add(PnlReporte, "card2");

        PnlCargando.setLayout(new java.awt.BorderLayout());

        LblCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/retina_waiting.gif"))); // NOI18N
        PnlCargando.add(LblCargando, java.awt.BorderLayout.CENTER);

        PnlMostrar.add(PnlCargando, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tbBusqueda, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblColaPedidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(107, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(538, 538, 538)
                .addComponent(LblColaPedidos))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(93, 93, 93)
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(31, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean  ValidarFecha(String pFecha)
    {
     int isFechaActual=CCValores.obenerFechaDelSistemaDMY(pFecha);
     if(isFechaActual==1)
     {
         return true;
     }
         return false;

    }
    
    
    private void DtChFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaPropertyChange

        if(cargar)
        if(!iscerrando){
            if(DtChFecha.getDate()!=null){
            //filtrarTabla();
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

    private long id_cliente;
    private void buscarCliente()
    {
          DlgGestionCliente odialogo= new DlgGestionCliente(oparent, true,1,1,1);//consultar

          odialogo.setCajaTexo(TxtBusqCliente.getText().trim());
          odialogo.setVisible(true);
          odialogo.setTitle("Busqueda de Cliente");
                  if(odialogo.getoCECliente()!=null){
                    id_cliente=odialogo.getoCECliente().getIdCliente();
                    TxtBusqCliente.setText(odialogo.getoCECliente().getNombreCompleto());
                    }

      }
    private void TxtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCodigoKeyReleased
        if(evt.getKeyCode()==evt.VK_DOWN) {

        if(tblComprobantes.getRowCount()>0)
        {
          tblComprobantes.requestFocus();
          tblComprobantes.changeSelection(0, 0, false, false);
        }
        }
    }//GEN-LAST:event_TxtCodigoKeyReleased

    private void TxtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtClienteActionPerformed

    private void TxtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyPressed

        if(evt.getKeyCode()==evt.VK_ENTER) {
         filtarPorcliente();
        }
        
    }//GEN-LAST:event_TxtClienteKeyPressed

    private void filtarPorcliente()
    {
    limpiarTabla();

         for(CEComprobanteVentaMatriz pCEComprobanteVentaMatriz:oListaComprobanteVenta )
                {
                    if(pCEComprobanteVentaMatriz!=null){
                    if(pCEComprobanteVentaMatriz.toString().contains(TxtCliente.getText().toUpperCase()))
                    {
                         ArrayList oArrayList=new ArrayList();
                        oArrayList.add(pCEComprobanteVentaMatriz.getFecha());
                        oArrayList.add(pCEComprobanteVentaMatriz.getHora());
                        oArrayList.add(pCEComprobanteVentaMatriz.getSerie());
                        oArrayList.add(pCEComprobanteVentaMatriz.getNumComprobante());                        
                        oArrayList.add(pCEComprobanteVentaMatriz);
                        oArrayList.add(pCEComprobanteVentaMatriz.getDNI());
                        oArrayList.add(pCEComprobanteVentaMatriz.getTipoComprobante());                        
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteVentaMatriz.getMontoTotal()));
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteVentaMatriz.getMontoExonerado()));
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteVentaMatriz.getSubTotalNetoSinIGV()));
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteVentaMatriz.getIGV()));
                        oArrayList.add(pCEComprobanteVentaMatriz.getDescuentoTotal());
                        oArrayList.add(pCEComprobanteVentaMatriz.getMoneda());
                        oArrayList.add(pCEComprobanteVentaMatriz.getUltimoEstado());
                        oArrayList.add(pCEComprobanteVentaMatriz.getUltimoUsuario());
                        oArrayList.add(pCEComprobanteVentaMatriz.getUltimaFecha());
                        ((ArrayListTableModel)tblComprobantes.getModel()).addRow(oArrayList);
                    }
                 }
           }

    }
    private void TxtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyReleased


        if(evt.getKeyCode()==evt.VK_DOWN) {
            if(tblComprobantes.getRowCount()>0)
        {
          tblComprobantes.requestFocus();
          tblComprobantes.changeSelection(0, 0, false, false);
        }
        }
        

    }//GEN-LAST:event_TxtClienteKeyReleased

    private void CbxTipoCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoCompActionPerformed
       // if(cargar)
       // filtrarTabla();
    }//GEN-LAST:event_CbxTipoCompActionPerformed

    private void CbxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxEstadoActionPerformed
       // if(cargar)
       // filtrarTabla();
        
    }//GEN-LAST:event_CbxEstadoActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        buscarCliente();
        filtrarTabla();
}//GEN-LAST:event_btnBuscar1ActionPerformed

    private void TxtBusqClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtBusqClienteKeyPressed
         if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarCliente();
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
        id_cliente=-1;
        if(tbBusqueda.getSelectedIndex()==0){
                TxtCliente.setText("");
                filtrarTabla();
            }else if (tbBusqueda.getSelectedIndex()==1){
                TxtCodigo.setText("");
                limpiarTabla();
            }else if (tbBusqueda.getSelectedIndex()==2){
                TxtBusqCliente.setText("");
                limpiarTabla();
            }
        
        }
    }//GEN-LAST:event_tbBusquedaStateChanged

    private void chkDstoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDstoActionPerformed

      //  filtrarTabla();
         
}//GEN-LAST:event_chkDstoActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed

        CLExportarExcel oExportar=new CLExportarExcel(tblComprobantes,
                new String[]{},new String[]{},new String[]{}
                ,this.getTitle());
        oExportar.GuardarArchivoExcel(this);
}//GEN-LAST:event_btnExportar1ActionPerformed

    private void DtChFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaFinPropertyChange
        if(cargar)
        if(!iscerrando){
            if(DtChFecha.getDate()!=null){
            //filtrarTabla();
            }
        }
    }//GEN-LAST:event_DtChFechaFinPropertyChange

    private void tblComprobantesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblComprobantesKeyPressed

    }//GEN-LAST:event_tblComprobantesKeyPressed

    private void tblComprobantesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblComprobantesFocusLost

}//GEN-LAST:event_tblComprobantesFocusLost

    private void tblComprobantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprobantesMouseClicked
   
    }//GEN-LAST:event_tblComprobantesMouseClicked

    private void BtnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefrescarActionPerformed

        BtnRefrescar.setEnabled(false);
        filtrarTabla();
        BtnRefrescar.setEnabled(true);
}//GEN-LAST:event_BtnRefrescarActionPerformed

private CEComprobanteVentaMatriz oCEComprobanteVentaMatriz=null;
   
    public CEComprobanteVentaMatriz getCEComprobanteVenta()
{
    return oCEComprobanteVentaMatriz;
}
    
    
    private List<CEComprobanteVentaDetalle> listarregistros()
    {
        //JOptionPane.showMessageDialog(null, "cargando", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        String dato=TxtCodigo.getText();
        int id_TipoComp=1;
        int id_Estado=2;
        CETipoComprobante oCETipoComprobante=(CETipoComprobante)CbxTipoComp.getSelectedItem();
        if(oCETipoComprobante!=null){
            id_TipoComp=oCETipoComprobante.getIdTipoComprobante();
        }
        CEEstado oCEEstado=(CEEstado)CbxEstado.getSelectedItem();
        if(oCEEstado!=null){
            id_Estado=oCEEstado.getmIntIdEstado();
        }
        if(!dato.equals("")){
//        if(RbtCliente.isSelected())
//        {
//            return CCComprobanteVenta.BuscarComprobanteVenta(3, dato,1);
//        }
        if(tbBusqueda.getSelectedIndex()==1){
            return CCComprobanteVenta.BuscarComprobantePorNumDet(dato);
                }
        
        }
        if(tbBusqueda.getSelectedIndex()==2){
            
            if(id_cliente!=-1)
            {
                return CCComprobanteVenta.BuscarComprobantePorClienteDet(id_cliente);
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
            ocConvertidorFecha.setFecha(DtChFechaFin.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFechaFin=ocConvertidorFecha.getFechaSimple2();
                
                return CCComprobanteVenta.BuscarAvanzadoComprobanteVentaDet(pFecha,id_Estado,id_TipoComp,pFechaFin,chkDsto.isSelected());
            
            }
        }
        return null;
      

    }

    private void filtrarTabla()
    {

        try {
        LblCargando.setVisible(true);
        DtChFecha.setEnabled(false);
        DtChFechaFin.setEnabled(false);
        CbxEstado.setEnabled(false);
        CbxTipoComp.setEnabled(false);
        chkDsto.setEnabled(false);
        BtnRefrescar.setEnabled(false);
        btnExportar1.setEnabled(false);
        TxtCliente.setEnabled(false);
                       oListaComprobanteVenta=listarregistros();
                       limpiarTabla();
                       if(oListaComprobanteVenta!=null)
                       {

                                for(CEComprobanteVentaDetalle pCEComprobanteVentaMatriz:oListaComprobanteVenta )
                                {
                                        ArrayList oArrayList=new ArrayList();
                                        oArrayList.add(pCEComprobanteVentaMatriz.getFecha());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getHora());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getSerie());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getNumComprobante());
                                        oArrayList.add(pCEComprobanteVentaMatriz);
                                        oArrayList.add(pCEComprobanteVentaMatriz.getDNI());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getTipoComprobante());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getProducto());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getUnidadMedida());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getCantidad());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getPrecioconDesc());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getImporteConDescuento());
                                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteVentaMatriz.getMontoTotal()));
                                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteVentaMatriz.getMontoExonerado()));
                                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteVentaMatriz.getSubTotalNetoSinIGV()));
                                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEComprobanteVentaMatriz.getIGV()));
                                        oArrayList.add(pCEComprobanteVentaMatriz.getDescuentoTotal());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getMoneda());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getUltimoEstado());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getUltimoUsuario());
                                        oArrayList.add(pCEComprobanteVentaMatriz.getUltimaFecha());
                                        ((ArrayListTableModel)tblComprobantes.getModel()).addRow(oArrayList);

                                }
                                LblColaPedidos.setText("Registros Encontrados: "+tblComprobantes.getRowCount()+"");

                        }


                    LblCargando.setVisible(false);
                    DtChFecha.setEnabled(true);
                    DtChFechaFin.setEnabled(true);
                    CbxEstado.setEnabled(true);
                    CbxTipoComp.setEnabled(true);
                    chkDsto.setEnabled(true);
                    BtnRefrescar.setEnabled(true);
                    btnExportar1.setEnabled(true);
                    TxtCliente.setEnabled(true);

                    if(tblComprobantes.getRowCount()>0)
                    {
                      tblComprobantes.requestFocus();
                      tblComprobantes.changeSelection(0, 0, false, false);
                    }

        }
        catch (OutOfMemoryError e) {
                JOptionPane.showMessageDialog(null,"No se puede generar el Reporte, la Información que desea procesar es de gran magnitud"
                                                    + " \n Por favor utilice intervalo de fechas cortas");
                   limpiarTabla();
                   LblCargando.setVisible(false);
                    DtChFecha.setEnabled(true);
                    DtChFechaFin.setEnabled(true);
                    CbxEstado.setEnabled(true);
                    CbxTipoComp.setEnabled(true);
                    chkDsto.setEnabled(true);
                    BtnRefrescar.setEnabled(true);
                    btnExportar1.setEnabled(true);
                    TxtCliente.setEnabled(true);
        }

    }
    private void limpiarTabla()
    {
        int size=tblComprobantes.getRowCount();
        for(int i=0;i<size;i++ )
        {
        ((ArrayListTableModel)tblComprobantes.getModel()).removeRow(0);
        }
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRefrescar;
    private ComboBox.ComboBox CbxEstado;
    private ComboBox.ComboBox CbxTipoComp;
    private com.toedter.calendar.JDateChooser DtChFecha;
    private com.toedter.calendar.JDateChooser DtChFechaFin;
    private javax.swing.JLabel LblCargando;
    private Label.Label LblCodigo;
    private Label.Label LblCodigo1;
    private javax.swing.JLabel LblColaPedidos;
    private Label.Label LblEstado;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private TextField.JTxtNinguno TxtBusqCliente;
    private TextField.JTxtNinguno TxtCliente;
    private TextField.JTxtNinguno TxtCodigo;
    private BotonesABM.BtnBuscar btnBuscar1;
    private BotonesABM.BtnExportar btnExportar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private Opcion.CheckBox chkDsto;
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
    private javax.swing.JTable tblComprobantes;
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
               filtrarTabla();
            }
        }
    }
}
