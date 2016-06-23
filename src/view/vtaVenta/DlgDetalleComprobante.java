package view.vtaVenta;

import controller.vtaVenta.CCComprobanteVenta;
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
import modelo.vtaVenta.entidad.CEComprobanteVentaDetalle;
import modelo.vtaVenta.entidad.CEComprobanteVentaMatriz;
import util.clases.grlGeneral.DialogoPadre;



public class DlgDetalleComprobante extends DialogoPadre implements ActionListener
{

    public List<CEComprobanteVentaMatriz> oListaComprobanteVentas;
    public DlgDetalleComprobante(java.awt.Frame parent, boolean modal,long id)
    {
        super(parent, modal);
        initComponents();
        configurarTablaComprobanteVentaDetalle();
        cargarComprobanteVenta(id);
    }
   
    private void configurarTablaComprobanteVentaDetalle()
    {
        TblComprobanteVentaDetalle.setDefaultRenderer(java.lang.Float.class,new DefaultTableCellRenderer()
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
        jScrollPane3 = new javax.swing.JScrollPane();
        TblComprobanteVentaDetalle = new javax.swing.JTable();
        PnlDatos2 = new javax.swing.JPanel();
        TxtCliente = new TextField.JTxtLetra();
        label1 = new Label.Label();
        label3 = new Label.Label();
        TxtSucursal = new TextField.JTxtLetra();
        label2 = new Label.Label();
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
        TxtCobrador = new TextField.JTxtLetra();
        label22 = new Label.Label();
        LblUltimaFecha = new Label.Label();
        label27 = new Label.Label();
        LblFechaEmision = new Label.Label();
        label5 = new Label.Label();
        TxtTipoComprobante = new TextField.JTxtNinguno();
        TxtCodigo = new TextField.JTxtNinguno();
        label13 = new Label.Label();
        TxtNumPedido = new TextField.JTxtNinguno();
        label4 = new Label.Label();
        label15 = new Label.Label();
        label19 = new Label.Label();
        LblSubtotalBruto = new Label.Label();
        label11 = new Label.Label();
        LblDescuentoSubtotal = new Label.Label();
        LblSubtotalNeto = new Label.Label();
        jPanel5 = new javax.swing.JPanel();
        LblPorcentaje = new Label.Label();
        label21 = new Label.Label();
        LblDescuentoEnValores = new Label.Label();
        TxtDescuentoPorcentual = new TextField.JTxtNumero();
        label23 = new Label.Label();
        label24 = new Label.Label();
        TxtTipoDescuento = new TextField.JTxtNumero();
        label6 = new Label.Label();
        LblMontoTotal = new Label.Label();
        LblIgv = new Label.Label();
        label9 = new Label.Label();
        LblISC = new Label.Label();
        label16 = new Label.Label();
        label7 = new Label.Label();
        LblSubTotalNetoSinIgv = new Label.Label();
        LblDescuentototal = new Label.Label();
        label25 = new Label.Label();
        label26 = new Label.Label();
        label34 = new Label.Label();
        LblMontoTotalPagar = new Label.Label();
        lblPercepcion = new Label.Label();
        label14 = new Label.Label();
        LblEstado = new Label.Label();

        jMenuItemCancelarPedido.setText("Cancelar Pedido");
        jPopupMenuPedido.add(jMenuItemCancelarPedido);

        jMenuItemAnularPedido.setText("Anular Pedido");
        jPopupMenuPedido.add(jMenuItemAnularPedido);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblComprobanteVentaDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblComprobanteVentaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Producto", "Precio", "U. M. ", "Cantidad", "Importe Bruto", "Tipo Desc.", "Descuento", "Desc. en Valo.", "Precio/ Dsto", "Impote Neto", "Exonerado", "Almacen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblComprobanteVentaDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblComprobanteVentaDetalle.setColumnSelectionAllowed(true);
        TblComprobanteVentaDetalle.setRowHeight(22);
        jScrollPane3.setViewportView(TblComprobanteVentaDetalle);
        TblComprobanteVentaDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(1).setPreferredWidth(320);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(2).setPreferredWidth(80);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(3).setPreferredWidth(80);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(4).setPreferredWidth(75);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(5).setPreferredWidth(90);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(6).setPreferredWidth(80);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(7).setPreferredWidth(75);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(10).setPreferredWidth(90);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(12).setMinWidth(0);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(12).setPreferredWidth(0);
        TblComprobanteVentaDetalle.getColumnModel().getColumn(12).setMaxWidth(0);

        PnlDatos2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "[ DATOS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        TxtCliente.setEditable(false);
        TxtCliente.setTamanio(200);

        label1.setText("Cliente :");
        label1.setFont(new java.awt.Font("Verdana", 1, 12));

        label3.setText("Sucursal :");
        label3.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtSucursal.setEditable(false);
        TxtSucursal.setText("CENTRAL");
        TxtSucursal.setTamanio(50);

        label2.setText("Vendedor :");
        label2.setFont(new java.awt.Font("Verdana", 1, 12));

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

        label8.setText("DNI :");
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

        TxtCobrador.setEditable(false);
        TxtCobrador.setTamanio(50);

        label22.setText("Ultima Fecha :");
        label22.setFont(new java.awt.Font("Verdana", 1, 12));

        LblUltimaFecha.setText("hora");

        label27.setText("Fecha Emision:");
        label27.setFont(new java.awt.Font("Verdana", 1, 12));

        LblFechaEmision.setText("hora");

        javax.swing.GroupLayout PnlDatos2Layout = new javax.swing.GroupLayout(PnlDatos2);
        PnlDatos2.setLayout(PnlDatos2Layout);
        PnlDatos2Layout.setHorizontalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(179, Short.MAX_VALUE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(TxtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(TxtCondicion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(TxtCobrador, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label22, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LblUltimaFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxtSucursal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxtMoneda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblFechaEmision, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
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
                .addGap(7, 7, 7)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtCobrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblUltimaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnlDatos2Layout.createSequentialGroup()
                            .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(label27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LblFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnlDatos2Layout.createSequentialGroup()
                            .addComponent(TxtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label5.setText("Comprobante :");
        label5.setFont(new java.awt.Font("Arial", 1, 12));

        TxtTipoComprobante.setEditable(false);
        TxtTipoComprobante.setText("jTxtNinguno1");
        TxtTipoComprobante.setTamanio(250);

        TxtCodigo.setEditable(false);
        TxtCodigo.setTamanio(250);

        label13.setText("Num .Pedido :");
        label13.setFont(new java.awt.Font("Arial", 1, 12));

        TxtNumPedido.setEditable(false);
        TxtNumPedido.setText("123-12345678");
        TxtNumPedido.setTamanio(250);

        label4.setText("Num: ");
        label4.setFont(new java.awt.Font("Arial", 1, 12));

        label15.setText("<html>SubTotal Bruto:</html>");
        label15.setFont(new java.awt.Font("Verdana", 1, 12));

        label19.setText("<html>Desc. en SubT.:</html>");
        label19.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalBruto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalBruto.setText("0");
        LblSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalBruto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label11.setText("SubTotal Neto                   :");
        label11.setFont(new java.awt.Font("Verdana", 1, 12));

        LblDescuentoSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoSubtotal.setText("0");
        LblDescuentoSubtotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoSubtotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblSubtotalNeto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalNeto.setText("0");
        LblSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalNeto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel5.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MONTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        LblPorcentaje.setVisible(false);
        LblPorcentaje.setText("%");
        LblPorcentaje.setFont(new java.awt.Font("Verdana", 1, 11));

        label21.setText("TIPO DSTO. :");
        label21.setFont(new java.awt.Font("Verdana", 1, 11));

        LblDescuentoEnValores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoEnValores.setText("0");
        LblDescuentoEnValores.setFont(new java.awt.Font("Verdana", 1, 11));
        LblDescuentoEnValores.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        TxtDescuentoPorcentual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtDescuentoPorcentual.setFont(new java.awt.Font("Verdana", 1, 11));
        TxtDescuentoPorcentual.setTamanio(15);

        label23.setText("DST0. EN VALOR.:");
        label23.setFont(new java.awt.Font("Verdana", 1, 11));

        label24.setText("DSTO :");
        label24.setFont(new java.awt.Font("Verdana", 1, 11));

        TxtTipoDescuento.setEditable(false);
        TxtTipoDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtTipoDescuento.setFont(new java.awt.Font("Verdana", 1, 11));
        TxtTipoDescuento.setTamanio(15);
        TxtTipoDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtTipoDescuentoKeyReleased(evt);
            }
        });

        label6.setText("MONTO TOTAL ");
        label6.setFont(new java.awt.Font("Verdana", 1, 11));

        LblMontoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotal.setText("0.00");
        LblMontoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblMontoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblIgv.setText("0.00");
        LblIgv.setFont(new java.awt.Font("Verdana", 1, 12));
        LblIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label9.setText("I.G.V.");
        label9.setFont(new java.awt.Font("Verdana", 1, 11));

        LblISC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblISC.setText("0.00");
        LblISC.setFont(new java.awt.Font("Verdana", 1, 12));
        LblISC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label16.setText("I.S.C.");
        label16.setFont(new java.awt.Font("Verdana", 1, 11));

        label7.setText("SUB. NT. SIN IGV");
        label7.setFont(new java.awt.Font("Verdana", 1, 11));

        LblSubTotalNetoSinIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubTotalNetoSinIgv.setText("0.00");
        LblSubTotalNetoSinIgv.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubTotalNetoSinIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblDescuentototal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentototal.setText("0.00");
        LblDescuentototal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentototal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label25.setText("DESC. TOTAL");
        label25.setFont(new java.awt.Font("Verdana", 1, 11));

        label26.setText("PERCEPCIÓN");
        label26.setFont(new java.awt.Font("Verdana", 1, 11));

        label34.setText("TOTAL A PAGAR ");
        label34.setFont(new java.awt.Font("Verdana", 1, 12));

        LblMontoTotalPagar.setForeground(new java.awt.Color(0, 0, 153));
        LblMontoTotalPagar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotalPagar.setText("0.00");
        LblMontoTotalPagar.setFont(new java.awt.Font("Verdana", 1, 15));
        LblMontoTotalPagar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblPercepcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPercepcion.setText("0.00");
        lblPercepcion.setFont(new java.awt.Font("Verdana", 1, 12));
        lblPercepcion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtTipoDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtDescuentoPorcentual, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblDescuentoEnValores, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblSubTotalNetoSinIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblISC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label25, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblDescuentototal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label26, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label34, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblMontoTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtTipoDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtDescuentoPorcentual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblDescuentoEnValores, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblSubTotalNetoSinIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblISC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblDescuentototal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblMontoTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        label14.setText("Estado :");
        label14.setFont(new java.awt.Font("Verdana", 1, 12));

        LblEstado.setText("nn");
        LblEstado.setFont(new java.awt.Font("Verdana", 1, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(TxtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(TxtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtTipoDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTipoDescuentoKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_TxtTipoDescuentoKeyReleased
    private void cargarComprobanteVenta(long id)
    {

        CEComprobanteVentaMatriz oCEComprobanteVentaMatriz=CCComprobanteVenta.ListarComprobanteVentaPorId_Y_Pedido(id);
        TxtTipoComprobante.setText(oCEComprobanteVentaMatriz.getTipoComprobante());
        TxtCodigo.setText(oCEComprobanteVentaMatriz.getNumComprobante());
        TxtCliente.setText(oCEComprobanteVentaMatriz.getCliente());
        TxtDNI.setText(oCEComprobanteVentaMatriz.getDNI());
        TxtDireccion.setText(oCEComprobanteVentaMatriz.getDireccion());
        TxtCondicion.setText(oCEComprobanteVentaMatriz.getCondicion());
        TxtMoneda.setText(oCEComprobanteVentaMatriz.getMoneda());
        TxtVendedor.setText(oCEComprobanteVentaMatriz.getEmpleado());
        TxtSucursal.setText(oCEComprobanteVentaMatriz.getSucursal());
        TxtTipoCambio.setText(oCEComprobanteVentaMatriz.getTipoCambio()+"");
        TxtTipoDescuento.setText(oCEComprobanteVentaMatriz.getTipoDescuento());
        TxtDescuentoPorcentual.setText(oCEComprobanteVentaMatriz.getDescuento()+"");
        LblSubtotalNeto.setText(oCEComprobanteVentaMatriz.getSubtotalNeto()+"");
        LblSubTotalNetoSinIgv.setText(oCEComprobanteVentaMatriz.getSubTotalNetoSinIGV()+"");
        LblDescuentoSubtotal.setText(oCEComprobanteVentaMatriz.getDescuentoEnSubtotal()+"");
        LblSubtotalBruto.setText(oCEComprobanteVentaMatriz.getSubtotalBruto()+"");
        LblDescuentoEnValores.setText(oCEComprobanteVentaMatriz.getDescuentoEnValores()+"");
        LblDescuentototal.setText(oCEComprobanteVentaMatriz.getDescuentoTotal()+"");
        LblIgv.setText(oCEComprobanteVentaMatriz.getIGV()+"");
        LblISC.setText(oCEComprobanteVentaMatriz.getISC()+"");
        LblMontoTotal.setText(oCEComprobanteVentaMatriz.getMontoTotal()+"");
        lblPercepcion.setText(oCEComprobanteVentaMatriz.getMontoPercepcion()+"");
        LblMontoTotalPagar.setText(oCEComprobanteVentaMatriz.getTotalPagar()+"");
        TxtNumPedido.setText(oCEComprobanteVentaMatriz.getNumPedido());
        LblEstado.setText(oCEComprobanteVentaMatriz.getUltimoEstado());
        TxtCobrador.setText(oCEComprobanteVentaMatriz.getCobrador());
        LblFechaEmision.setText(oCEComprobanteVentaMatriz.getFecha());
        LblUltimaFecha.setText(oCEComprobanteVentaMatriz.getUltimaFecha());
      //  oCEComprobanteVentaMatriz.setLstComprobanteVentaDetalle(CCComprobanteVenta.ConsultarComprobanteVentaDetalle(oCEComprobanteVentaMatriz.getIdComprobanteVenta()));
        limpiarTablarComprobanteVentaDetalle();
        for(int i=0; i<oCEComprobanteVentaMatriz.getoLstComprobanteDetalle().size();i++)
        {
                Vector oArrayList = new Vector();
                // "N°", "Producto", "Precio", "U. M. ", "Cantidad", "Importe Bruto",
                //"Tipo Desc.", "Descuento", "Desc. en Valo.", "Precio/ Dsto", "Impote Neto", "Exonerado", "Almacen"
               ((DefaultTableModel)TblComprobanteVentaDetalle.getModel()).addRow(oArrayList);
                CEComprobanteVentaDetalle oCEComprobanteVentaDetalle=oCEComprobanteVentaMatriz.getoLstComprobanteDetalle().get(i) ;
                TblComprobanteVentaDetalle.setValueAt(i+1,i,0);
                TblComprobanteVentaDetalle.setValueAt(oCEComprobanteVentaDetalle.getProducto(),i,1);
                TblComprobanteVentaDetalle.setValueAt(oCEComprobanteVentaDetalle.getPrecio(),i,2);
                TblComprobanteVentaDetalle.setValueAt(oCEComprobanteVentaDetalle.getUnidadMedida(),i,3);
                TblComprobanteVentaDetalle.setValueAt(oCEComprobanteVentaDetalle.getCantidad(),i,4);
                TblComprobanteVentaDetalle.setValueAt(oCEComprobanteVentaDetalle.getImporteSinDescuento(),i,5);
                TblComprobanteVentaDetalle.setValueAt(oCEComprobanteVentaDetalle.getTipoDescuento(),i,6);
                TblComprobanteVentaDetalle.setValueAt(oCEComprobanteVentaDetalle.getDescuento(),i,7);
                TblComprobanteVentaDetalle.setValueAt(oCEComprobanteVentaDetalle.getDescuentoEnValores(),i,8);
                TblComprobanteVentaDetalle.setValueAt(oCEComprobanteVentaDetalle.getPrecioconDesc(),i,9);
                TblComprobanteVentaDetalle.setValueAt(oCEComprobanteVentaDetalle.getImporteConDescuento(),i,10);
                TblComprobanteVentaDetalle.setValueAt(oCEComprobanteVentaDetalle.getExonerado(),i,11);
          }
    }    private void limpiarTablarComprobanteVentaDetalle()
    {
        int rowCount=TblComprobanteVentaDetalle.getRowCount();
        for(int i=0;i<rowCount;i++)
        {
            ((DefaultTableModel)TblComprobanteVentaDetalle.getModel()).removeRow(0);
        }
    }    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Label.Label LblDescuentoEnValores;
    private Label.Label LblDescuentoSubtotal;
    private Label.Label LblDescuentototal;
    private Label.Label LblEstado;
    private Label.Label LblFechaEmision;
    private Label.Label LblISC;
    private Label.Label LblIgv;
    private Label.Label LblMontoTotal;
    private Label.Label LblMontoTotalPagar;
    private Label.Label LblPorcentaje;
    private Label.Label LblSubTotalNetoSinIgv;
    private Label.Label LblSubtotalBruto;
    private Label.Label LblSubtotalNeto;
    private Label.Label LblUltimaFecha;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JTable TblComprobanteVentaDetalle;
    private TextField.JTxtLetra TxtCliente;
    private TextField.JTxtLetra TxtCobrador;
    private TextField.JTxtNinguno TxtCodigo;
    private TextField.JTxtLetra TxtCondicion;
    private TextField.JTxtLetra TxtDNI;
    private TextField.JTxtNumero TxtDescuentoPorcentual;
    private TextField.JTxtLetra TxtDireccion;
    private TextField.JTxtLetra TxtMoneda;
    private TextField.JTxtNinguno TxtNumPedido;
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
    private Label.Label label13;
    private Label.Label label14;
    private Label.Label label15;
    private Label.Label label16;
    private Label.Label label17;
    private Label.Label label18;
    private Label.Label label19;
    private Label.Label label2;
    private Label.Label label20;
    private Label.Label label21;
    private Label.Label label22;
    private Label.Label label23;
    private Label.Label label24;
    private Label.Label label25;
    private Label.Label label26;
    private Label.Label label27;
    private Label.Label label3;
    private Label.Label label34;
    private Label.Label label4;
    private Label.Label label5;
    private Label.Label label6;
    private Label.Label label7;
    private Label.Label label8;
    private Label.Label label9;
    private Label.Label lblPercepcion;
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
