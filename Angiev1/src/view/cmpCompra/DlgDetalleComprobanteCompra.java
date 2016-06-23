package view.cmpCompra;

import controller.cmpCompra.CCComprobanteCompra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;

import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.cmpCompra.entidad.CEComprobanteCompraDetalle;
import modelo.cmpCompra.entidad.CEComprobanteCompraMatriz;
import util.clases.grlGeneral.DialogoPadre;


public class DlgDetalleComprobanteCompra extends DialogoPadre implements ActionListener
{

    public List<CEComprobanteCompraMatriz> oListaComprobanteCompras;
    public DlgDetalleComprobanteCompra(java.awt.Frame parent, boolean modal,long id)
    {
        super(parent, modal);
        initComponents();
        configurarTablaComprobanteCompraDetalle();
        cargarComprobanteCompra(id);
    }
   
    private void configurarTablaComprobanteCompraDetalle()
    {
        TblComprobanteCompraDetalle.setDefaultRenderer(java.lang.Float.class,new DefaultTableCellRenderer()
        {
            @Override
            public void setHorizontalAlignment(int alignment) {
                super.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            }

        });
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPopupMenuPedido = new javax.swing.JPopupMenu();
        jMenuItemCancelarPedido = new javax.swing.JMenuItem();
        jMenuItemAnularPedido = new javax.swing.JMenuItem();
        TxtCodigo = new TextField.JTxtNinguno();
        label4 = new Label.Label();
        TxtTipoComprobante = new TextField.JTxtNinguno();
        label5 = new Label.Label();
        PnlDatos2 = new javax.swing.JPanel();
        TxtCliente = new TextField.JTxtLetra();
        label1 = new Label.Label();
        label3 = new Label.Label();
        TxtSucursal = new TextField.JTxtLetra();
        TxtVendedor = new TextField.JTxtLetra();
        label10 = new Label.Label();
        label17 = new Label.Label();
        label18 = new Label.Label();
        TxtTipoCambio = new TextField.JTxtNinguno();
        label8 = new Label.Label();
        TxtDNI = new TextField.JTxtLetra();
        TxtDireccion = new TextField.JTxtLetra();
        label12 = new Label.Label();
        TxtMoneda = new TextField.JTxtLetra();
        TxtCondicion = new TextField.JTxtLetra();
        label20 = new Label.Label();
        label22 = new Label.Label();
        LblUltimaFecha = new Label.Label();
        jPanel5 = new javax.swing.JPanel();
        label6 = new Label.Label();
        LblMontoTotalPagar = new Label.Label();
        LblIgv = new Label.Label();
        label9 = new Label.Label();
        LblISC = new Label.Label();
        label16 = new Label.Label();
        LblPorcentaje = new Label.Label();
        label7 = new Label.Label();
        LblSubTotalNetoSinIgv = new Label.Label();
        label21 = new Label.Label();
        LblDescuentoEnValores = new Label.Label();
        TxtDescuentoPorcentual = new TextField.JTxtNumero();
        label23 = new Label.Label();
        label24 = new Label.Label();
        label25 = new Label.Label();
        LblDescuentoTotal = new Label.Label();
        TxtTipoDescuento = new TextField.JTxtNumero();
        label26 = new Label.Label();
        LblMontoTotal = new Label.Label();
        label27 = new Label.Label();
        LblPercepcion = new Label.Label();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblComprobanteCompraDetalle = new javax.swing.JTable();
        label15 = new Label.Label();
        LblSubtotalBruto = new Label.Label();
        label19 = new Label.Label();
        LblDescuentoSubtotal = new Label.Label();
        label11 = new Label.Label();
        LblSubtotalNeto = new Label.Label();
        label14 = new Label.Label();
        LblEstado = new Label.Label();

        jMenuItemCancelarPedido.setText("Cancelar Pedido");
        jPopupMenuPedido.add(jMenuItemCancelarPedido);

        jMenuItemAnularPedido.setText("Anular Pedido");
        jPopupMenuPedido.add(jMenuItemAnularPedido);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TxtCodigo.setEditable(false);
        TxtCodigo.setTamanio(250);

        label4.setText("Numero:");
        label4.setFont(new java.awt.Font("Arial", 1, 12));

        TxtTipoComprobante.setEditable(false);
        TxtTipoComprobante.setText("jTxtNinguno1");
        TxtTipoComprobante.setTamanio(250);

        label5.setText("Comprobante :");
        label5.setFont(new java.awt.Font("Arial", 1, 12));

        PnlDatos2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "[ DATOS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        TxtCliente.setEditable(false);
        TxtCliente.setTamanio(200);

        label1.setText("Razon social :");
        label1.setFont(new java.awt.Font("Verdana", 1, 12));

        label3.setText("Sucursal :");
        label3.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtSucursal.setEditable(false);
        TxtSucursal.setText("CENTRAL");
        TxtSucursal.setTamanio(50);

        TxtVendedor.setEditable(false);
        TxtVendedor.setTamanio(50);

        label10.setText("Condicion :");
        label10.setFont(new java.awt.Font("Verdana", 1, 12));

        label17.setText("Moneda :");
        label17.setFont(new java.awt.Font("Verdana", 1, 12));

        label18.setText("Tipo Cambio :");
        label18.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtTipoCambio.setEditable(false);
        TxtTipoCambio.setTamanio(10);

        label8.setText("RUC :");
        label8.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtDNI.setEditable(false);
        TxtDNI.setTamanio(200);

        TxtDireccion.setEditable(false);
        TxtDireccion.setTamanio(200);

        label12.setText("Direccion :");
        label12.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtMoneda.setEditable(false);
        TxtMoneda.setTamanio(50);

        TxtCondicion.setEditable(false);
        TxtCondicion.setTamanio(50);

        label20.setText("Ultimo Usuar.");
        label20.setFont(new java.awt.Font("Verdana", 1, 12));

        label22.setText("Ultimo Fecha:");
        label22.setFont(new java.awt.Font("Verdana", 1, 12));

        LblUltimaFecha.setText("hora");

        javax.swing.GroupLayout PnlDatos2Layout = new javax.swing.GroupLayout(PnlDatos2);
        PnlDatos2.setLayout(PnlDatos2Layout);
        PnlDatos2Layout.setHorizontalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24))
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(TxtCondicion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtMoneda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TxtSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))
                            .addComponent(TxtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TxtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(label22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblUltimaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TxtTipoCambio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtDNI, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        PnlDatos2Layout.setVerticalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblUltimaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MONTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel5.setLayout(null);

        label6.setForeground(new java.awt.Color(0, 51, 153));
        label6.setText("TOTAL PAGAR");
        label6.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel5.add(label6);
        label6.setBounds(600, 141, 150, 15);

        LblMontoTotalPagar.setForeground(new java.awt.Color(0, 51, 153));
        LblMontoTotalPagar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotalPagar.setText("0");
        LblMontoTotalPagar.setFont(new java.awt.Font("Verdana", 1, 11));
        LblMontoTotalPagar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel5.add(LblMontoTotalPagar);
        LblMontoTotalPagar.setBounds(750, 141, 120, 15);

        LblIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblIgv.setText("0");
        LblIgv.setFont(new java.awt.Font("Verdana", 1, 11));
        LblIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel5.add(LblIgv);
        LblIgv.setBounds(750, 41, 120, 15);

        label9.setText("I.G.V                        ");
        label9.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel5.add(label9);
        label9.setBounds(600, 41, 150, 15);

        LblISC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblISC.setText("0");
        LblISC.setFont(new java.awt.Font("Verdana", 1, 11));
        LblISC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel5.add(LblISC);
        LblISC.setBounds(750, 61, 120, 15);

        label16.setText("I.S.C                           ");
        label16.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel5.add(label16);
        label16.setBounds(600, 61, 150, 15);

        LblPorcentaje.setVisible(false);
        LblPorcentaje.setText("%");
        LblPorcentaje.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel5.add(LblPorcentaje);
        LblPorcentaje.setBounds(390, 21, 14, 20);

        label7.setText("SubTotal Neto sin Igv ");
        label7.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel5.add(label7);
        label7.setBounds(600, 21, 144, 15);

        LblSubTotalNetoSinIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubTotalNetoSinIgv.setText("0");
        LblSubTotalNetoSinIgv.setFont(new java.awt.Font("Verdana", 1, 11));
        LblSubTotalNetoSinIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel5.add(LblSubTotalNetoSinIgv);
        LblSubTotalNetoSinIgv.setBounds(750, 21, 120, 15);

        label21.setText("TIPO DESCUENTO");
        label21.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel5.add(label21);
        label21.setBounds(10, 21, 111, 15);

        LblDescuentoEnValores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoEnValores.setText("0");
        LblDescuentoEnValores.setFont(new java.awt.Font("Verdana", 1, 11));
        LblDescuentoEnValores.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel5.add(LblDescuentoEnValores);
        LblDescuentoEnValores.setBounds(502, 21, 80, 19);

        TxtDescuentoPorcentual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtDescuentoPorcentual.setFont(new java.awt.Font("Verdana", 1, 11));
        TxtDescuentoPorcentual.setTamanio(15);
        jPanel5.add(TxtDescuentoPorcentual);
        TxtDescuentoPorcentual.setBounds(310, 21, 80, 21);

        label23.setText("DESC. VALOR.");
        label23.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel5.add(label23);
        label23.setBounds(410, 21, 86, 15);

        label24.setText("DESCUENTO");
        label24.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel5.add(label24);
        label24.setBounds(230, 21, 76, 15);

        label25.setText("Descuento Total       ");
        label25.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel5.add(label25);
        label25.setBounds(600, 81, 150, 20);

        LblDescuentoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoTotal.setText("0");
        LblDescuentoTotal.setFont(new java.awt.Font("Verdana", 1, 11));
        LblDescuentoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel5.add(LblDescuentoTotal);
        LblDescuentoTotal.setBounds(750, 81, 120, 19);

        TxtTipoDescuento.setEditable(false);
        TxtTipoDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtTipoDescuento.setFont(new java.awt.Font("Verdana", 1, 11));
        TxtTipoDescuento.setTamanio(15);
        TxtTipoDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtTipoDescuentoKeyReleased(evt);
            }
        });
        jPanel5.add(TxtTipoDescuento);
        TxtTipoDescuento.setBounds(123, 21, 100, 21);

        label26.setText("Monto Total");
        label26.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel5.add(label26);
        label26.setBounds(600, 101, 150, 15);

        LblMontoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotal.setText("0");
        LblMontoTotal.setFont(new java.awt.Font("Verdana", 1, 11));
        LblMontoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel5.add(LblMontoTotal);
        LblMontoTotal.setBounds(750, 101, 120, 19);

        label27.setText("Percepcion                ");
        label27.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel5.add(label27);
        label27.setBounds(600, 121, 150, 15);

        LblPercepcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblPercepcion.setText("0");
        LblPercepcion.setFont(new java.awt.Font("Verdana", 1, 11));
        LblPercepcion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel5.add(LblPercepcion);
        LblPercepcion.setBounds(750, 121, 120, 20);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblComprobanteCompraDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblComprobanteCompraDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Producto", "Almacen", "Precio", "U. M. ", "Cantidad", "Importe Bruto Sin IGV", "Importe Bruto Con IGv", "Tipo Desc.", "Descuento", "Desc. en Valo.", "Impote Neto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblComprobanteCompraDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblComprobanteCompraDetalle.setColumnSelectionAllowed(true);
        TblComprobanteCompraDetalle.setRowHeight(22);
        jScrollPane3.setViewportView(TblComprobanteCompraDetalle);
        TblComprobanteCompraDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(1).setPreferredWidth(320);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(2).setMinWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(2).setPreferredWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(2).setMaxWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(3).setPreferredWidth(80);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(5).setPreferredWidth(75);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(6).setPreferredWidth(120);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(7).setResizable(false);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(7).setPreferredWidth(120);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(8).setPreferredWidth(80);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(9).setPreferredWidth(75);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(10).setPreferredWidth(90);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(11).setPreferredWidth(90);

        label15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label15.setText("<html>SubTotal Bruto:</html>");
        label15.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalBruto.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        LblSubtotalBruto.setText("0");
        LblSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalBruto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label19.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label19.setText("<html>Desc. en SubT.:</html>");
        label19.setFont(new java.awt.Font("Verdana", 1, 12));

        LblDescuentoSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        LblDescuentoSubtotal.setText("0");
        LblDescuentoSubtotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoSubtotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label11.setText("SubTotal Neto                   :");
        label11.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalNeto.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        LblSubtotalNeto.setText("0");
        LblSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalNeto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label14.setText("Estado :");
        label14.setFont(new java.awt.Font("Verdana", 1, 12));

        LblEstado.setText("nn");
        LblEstado.setFont(new java.awt.Font("Verdana", 1, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(11, 11, 11)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cargarComprobanteCompra(long id)
    {

        CEComprobanteCompraMatriz oCEComprobanteCompraMatriz=CCComprobanteCompra.ConsultarComprasPorId(id);
        TxtTipoComprobante.setText(oCEComprobanteCompraMatriz.getTipoComprobante());
        TxtCodigo.setText(oCEComprobanteCompraMatriz.getNumComprobante());
        TxtCliente.setText(oCEComprobanteCompraMatriz.getProveedor());
        TxtDNI.setText(oCEComprobanteCompraMatriz.getRUC());
        TxtDireccion.setText(oCEComprobanteCompraMatriz.getDireccion());
        TxtCondicion.setText(oCEComprobanteCompraMatriz.getCondicion());
        TxtMoneda.setText(oCEComprobanteCompraMatriz.getMoneda());
        TxtVendedor.setText(oCEComprobanteCompraMatriz.getEmpleado());
        TxtSucursal.setText(oCEComprobanteCompraMatriz.getSucursal());
        TxtTipoCambio.setText(oCEComprobanteCompraMatriz.getTipoCambio()+"");
        TxtTipoDescuento.setText(oCEComprobanteCompraMatriz.getTipoDescuento());
        TxtDescuentoPorcentual.setText(oCEComprobanteCompraMatriz.getDescuento()+"");
        LblSubtotalNeto.setText(oCEComprobanteCompraMatriz.getSubtotalNeto()+"");
        LblSubTotalNetoSinIgv.setText(oCEComprobanteCompraMatriz.getSubTotalNetoSinIGV()+"");
        LblDescuentoSubtotal.setText(oCEComprobanteCompraMatriz.getDescuentoEnSubtotal()+"");
        LblSubtotalBruto.setText(oCEComprobanteCompraMatriz.getSubtotalBruto()+"");
        LblDescuentoEnValores.setText(oCEComprobanteCompraMatriz.getDescuentoEnValores()+"");
        LblIgv.setText(oCEComprobanteCompraMatriz.getIGV()+"");
        LblISC.setText(oCEComprobanteCompraMatriz.getISC()+"");
        LblMontoTotalPagar.setText(oCEComprobanteCompraMatriz.getTotalPagar()+"");
        LblMontoTotal.setText(oCEComprobanteCompraMatriz.getMontoTotal()+"");
        LblPercepcion.setText(oCEComprobanteCompraMatriz.getMontoPercepcion()+"");
        LblEstado.setText(oCEComprobanteCompraMatriz.getUltimoEstado());
        LblUltimaFecha.setText(oCEComprobanteCompraMatriz.getUltimaFecha());
        limpiarTablarComprobanteCompraDetalle();
        for(int i=0; i<oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().size();i++)
        {
                Vector oArrayList = new Vector();
               ((DefaultTableModel)TblComprobanteCompraDetalle.getModel()).addRow(oArrayList);
                CEComprobanteCompraDetalle oCEComprobanteCompraDetalle=oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().get(i) ;
                TblComprobanteCompraDetalle.setValueAt(i+1,i,0);
                TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getProducto(),i,1);
                //TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.get,i,2);
                TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getPrecio(),i,3);
                TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getUnidadMedida(),i,4);
                TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getCantidad(),i,5);
                TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteSinDescuento(),i,6);
                TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteSinDescuentoConIgv(),i,7);
                TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getTipoDescuento(),i,8);
                TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getDescuento(),i,9);
                TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getDescuentoEnValores(),i,10);
                TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteConDescuento(),i,11);
          }
    }    private void limpiarTablarComprobanteCompraDetalle()
    {
        int rowCount=TblComprobanteCompraDetalle.getRowCount();
        for(int i=0;i<rowCount;i++)
        {
            ((DefaultTableModel)TblComprobanteCompraDetalle.getModel()).removeRow(0);
        }
    }
    private void TxtTipoDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTipoDescuentoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTipoDescuentoKeyReleased
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Label.Label LblDescuentoEnValores;
    private Label.Label LblDescuentoSubtotal;
    private Label.Label LblDescuentoTotal;
    private Label.Label LblEstado;
    private Label.Label LblISC;
    private Label.Label LblIgv;
    private Label.Label LblMontoTotal;
    private Label.Label LblMontoTotalPagar;
    private Label.Label LblPercepcion;
    private Label.Label LblPorcentaje;
    private Label.Label LblSubTotalNetoSinIgv;
    private Label.Label LblSubtotalBruto;
    private Label.Label LblSubtotalNeto;
    private Label.Label LblUltimaFecha;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JTable TblComprobanteCompraDetalle;
    private TextField.JTxtLetra TxtCliente;
    private TextField.JTxtNinguno TxtCodigo;
    private TextField.JTxtLetra TxtCondicion;
    private TextField.JTxtLetra TxtDNI;
    private TextField.JTxtNumero TxtDescuentoPorcentual;
    private TextField.JTxtLetra TxtDireccion;
    private TextField.JTxtLetra TxtMoneda;
    private TextField.JTxtLetra TxtSucursal;
    private TextField.JTxtNinguno TxtTipoCambio;
    private TextField.JTxtNinguno TxtTipoComprobante;
    private TextField.JTxtNumero TxtTipoDescuento;
    private TextField.JTxtLetra TxtVendedor;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JMenuItem jMenuItemAnularPedido;
    private javax.swing.JMenuItem jMenuItemCancelarPedido;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenuPedido;
    private javax.swing.JScrollPane jScrollPane3;
    private Label.Label label1;
    private Label.Label label10;
    private Label.Label label11;
    private Label.Label label12;
    private Label.Label label14;
    private Label.Label label15;
    private Label.Label label16;
    private Label.Label label17;
    private Label.Label label18;
    private Label.Label label19;
    private Label.Label label20;
    private Label.Label label21;
    private Label.Label label22;
    private Label.Label label23;
    private Label.Label label24;
    private Label.Label label25;
    private Label.Label label26;
    private Label.Label label27;
    private Label.Label label3;
    private Label.Label label4;
    private Label.Label label5;
    private Label.Label label6;
    private Label.Label label7;
    private Label.Label label8;
    private Label.Label label9;
    // End of variables declaration//GEN-END:variables
    private static final String ACTION_CLOSE = "ACTION_CLOSE";
   
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke nuevo = KeyStroke.getKeyStroke(KeyEvent.VK_R,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
   
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            dispose();
        }       
    }
}
