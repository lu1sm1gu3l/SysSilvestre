package view.vtaVenta;

import controller.vtaVenta.CCPedido;
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
import modelo.vtaVenta.entidad.CEPedidoDetalle;
import modelo.vtaVenta.entidad.CEPedidoMatriz;
import util.clases.grlGeneral.DialogoPadre;



public class DlgDetallePedido extends DialogoPadre implements ActionListener
{

    public List<CEPedidoMatriz> oListaPedidos;
    public DlgDetallePedido(java.awt.Frame parent, boolean modal,CEPedidoMatriz oCEPedidoMatriz)
    {
        super(parent, modal);
        initComponents();
        configurarTablaPedidoDetalle();
        cargarPedido(oCEPedidoMatriz);
    }
   
    private void configurarTablaPedidoDetalle()
    {
        TblPedidoDetalle.setDefaultRenderer(java.lang.Float.class,new DefaultTableCellRenderer()
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
        jPanel2 = new javax.swing.JPanel();
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
        label5 = new Label.Label();
        label4 = new Label.Label();
        TxtCodigo = new TextField.JTxtNinguno();
        LblHora = new Label.Label();
        TxtTipoComprobante = new TextField.JTxtNinguno();
        jPanel5 = new javax.swing.JPanel();
        label6 = new Label.Label();
        LblMontoTotal = new Label.Label();
        LblIgv = new Label.Label();
        label9 = new Label.Label();
        label11 = new Label.Label();
        LblSubtotalNeto = new Label.Label();
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
        label15 = new Label.Label();
        LblSubtotalBruto = new Label.Label();
        label19 = new Label.Label();
        LblDescuentoSubtotal = new Label.Label();
        label25 = new Label.Label();
        LblDescuentoTotal = new Label.Label();
        TxtTipoDescuento = new TextField.JTxtNumero();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblPedidoDetalle = new javax.swing.JTable();

        jMenuItemCancelarPedido.setText("Cancelar Pedido");
        jPopupMenuPedido.add(jMenuItemCancelarPedido);

        jMenuItemAnularPedido.setText("Anular Pedido");
        jPopupMenuPedido.add(jMenuItemAnularPedido);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setAutoscrolls(true);

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

        javax.swing.GroupLayout PnlDatos2Layout = new javax.swing.GroupLayout(PnlDatos2);
        PnlDatos2.setLayout(PnlDatos2Layout);
        PnlDatos2Layout.setHorizontalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
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
                        .addGap(41, 41, 41)
                        .addComponent(TxtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
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
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label5.setText("Tipo Comprobante :");
        label5.setFont(new java.awt.Font("Arial", 1, 12));

        label4.setText("Código :");
        label4.setFont(new java.awt.Font("Arial", 1, 12));

        TxtCodigo.setEditable(false);
        TxtCodigo.setText("jTxtNinguno1");
        TxtCodigo.setTamanio(250);

        LblHora.setText("hora");

        TxtTipoComprobante.setEditable(false);
        TxtTipoComprobante.setText("jTxtNinguno1");
        TxtTipoComprobante.setTamanio(250);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MONTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        label6.setText("TOTAL A PAGAR :");
        label6.setFont(new java.awt.Font("Verdana", 1, 18));

        LblMontoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotal.setText("0");
        LblMontoTotal.setFont(new java.awt.Font("Verdana", 1, 18));
        LblMontoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblIgv.setText("0");
        LblIgv.setFont(new java.awt.Font("Verdana", 1, 12));
        LblIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label9.setText("I.G.V                                 :");
        label9.setFont(new java.awt.Font("Verdana", 1, 12));

        label11.setText("SubTotal Neto                   :");
        label11.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalNeto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalNeto.setText("0");
        LblSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalNeto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblISC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblISC.setText("0");
        LblISC.setFont(new java.awt.Font("Verdana", 1, 12));
        LblISC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label16.setText("I.S.C                                 :");
        label16.setFont(new java.awt.Font("Verdana", 1, 12));

        LblPorcentaje.setVisible(false);
        LblPorcentaje.setText("%");
        LblPorcentaje.setFont(new java.awt.Font("Verdana", 1, 12));

        label7.setText("SubTotal Neto sin Igv      :");
        label7.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubTotalNetoSinIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubTotalNetoSinIgv.setText("0");
        LblSubTotalNetoSinIgv.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubTotalNetoSinIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label21.setText("Tipo Descuento :");
        label21.setFont(new java.awt.Font("Verdana", 1, 12));

        LblDescuentoEnValores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoEnValores.setText("0");
        LblDescuentoEnValores.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoEnValores.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        TxtDescuentoPorcentual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtDescuentoPorcentual.setTamanio(15);

        label23.setText("Desc. en Val.:");
        label23.setFont(new java.awt.Font("Verdana", 1, 12));

        label24.setText("Descuento :");
        label24.setFont(new java.awt.Font("Verdana", 1, 12));

        label15.setText("<html>SubTotal Bruto:</html>");
        label15.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalBruto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalBruto.setText("0");
        LblSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalBruto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label19.setText("<html>Desc. en SubT.:</html>");
        label19.setFont(new java.awt.Font("Verdana", 1, 12));

        LblDescuentoSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoSubtotal.setText("0");
        LblDescuentoSubtotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoSubtotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label25.setText("Descuento Total               :");
        label25.setFont(new java.awt.Font("Verdana", 1, 12));

        LblDescuentoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoTotal.setText("0");
        LblDescuentoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        TxtTipoDescuento.setEditable(false);
        TxtTipoDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtTipoDescuento.setTamanio(15);
        TxtTipoDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtTipoDescuentoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTipoDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblDescuentoEnValores, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtDescuentoPorcentual, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(LblPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LblDescuentoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblISC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblSubTotalNetoSinIgv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(LblIgv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblMontoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(label21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtDescuentoPorcentual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtTipoDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LblPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LblDescuentoEnValores, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9)
                            .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(87, 87, 87)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(LblSubTotalNetoSinIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LblIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LblISC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LblDescuentoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(21, 21, 21)
                            .addComponent(LblMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PEDIDO DE PRODUCTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblPedidoDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblPedidoDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Producto", "Almacen", "Precio", "U. M. ", "Cantidad", "Impor. sin Desc.", "Tipo Desc.", "Descuento", "Desc. en Valo.", "Impor. con Desc."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblPedidoDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblPedidoDetalle.setColumnSelectionAllowed(true);
        TblPedidoDetalle.setRowHeight(22);
        jScrollPane3.setViewportView(TblPedidoDetalle);
        TblPedidoDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TblPedidoDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
        TblPedidoDetalle.getColumnModel().getColumn(1).setPreferredWidth(320);
        TblPedidoDetalle.getColumnModel().getColumn(2).setPreferredWidth(100);
        TblPedidoDetalle.getColumnModel().getColumn(3).setPreferredWidth(80);
        TblPedidoDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
        TblPedidoDetalle.getColumnModel().getColumn(5).setPreferredWidth(75);
        TblPedidoDetalle.getColumnModel().getColumn(6).setPreferredWidth(90);
        TblPedidoDetalle.getColumnModel().getColumn(7).setPreferredWidth(80);
        TblPedidoDetalle.getColumnModel().getColumn(8).setPreferredWidth(75);
        TblPedidoDetalle.getColumnModel().getColumn(10).setPreferredWidth(90);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 810, 160));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(LblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(212, 212, 212)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(199, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cargarPedido(CEPedidoMatriz oCEPedidoMatriz)
    {
        
        TxtTipoComprobante.setText(oCEPedidoMatriz.getTipoComprobante());
        TxtCodigo.setText(oCEPedidoMatriz.getCodigo());
        TxtCliente.setText(oCEPedidoMatriz.getCliente());
        TxtDNI.setText(oCEPedidoMatriz.getDNI());
        TxtDireccion.setText(oCEPedidoMatriz.getDireccion());
        TxtCondicion.setText(oCEPedidoMatriz.getCondicion());
        TxtMoneda.setText(oCEPedidoMatriz.getMoneda());
        TxtVendedor.setText(oCEPedidoMatriz.getEmpleado());
        TxtSucursal.setText(oCEPedidoMatriz.getSucursal());
        TxtTipoCambio.setText(oCEPedidoMatriz.getTipoCambio()+"");
        TxtTipoDescuento.setText(oCEPedidoMatriz.getTipoDescuento());
        TxtDescuentoPorcentual.setText(oCEPedidoMatriz.getDescuento()+"");
        LblSubtotalNeto.setText(oCEPedidoMatriz.getSubTotalNeto()+"");
        LblSubTotalNetoSinIgv.setText(oCEPedidoMatriz.getSubtotalNetoSinIGV()+"");
        LblDescuentoSubtotal.setText(oCEPedidoMatriz.getDescuentoEnSubtotal()+"");
        LblSubtotalBruto.setText(oCEPedidoMatriz.getSubtotalBruto()+"");
        LblDescuentoEnValores.setText(oCEPedidoMatriz.getDescuentoValores()+"");
        LblIgv.setText(oCEPedidoMatriz.getIGV()+"");
        LblISC.setText(oCEPedidoMatriz.getISC()+"");
        LblMontoTotal.setText(oCEPedidoMatriz.getMontoTotal()+"");
        oCEPedidoMatriz.setLstPedidoDetalle(CCPedido.ConsultarPedidoDetalle(oCEPedidoMatriz.getIdPedido()));
        limpiarTablarPedidoDetalle();
        for(int i=0; i<oCEPedidoMatriz.getLstPedidoDetalle().size();i++)
        {
                Vector oArrayList = new Vector();
               ((DefaultTableModel)TblPedidoDetalle.getModel()).addRow(oArrayList);
                CEPedidoDetalle oCEPedidoDetalle=oCEPedidoMatriz.getLstPedidoDetalle().get(i) ;
                TblPedidoDetalle.setValueAt(i+1,i,0);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getProducto(),i,1);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getAlmacen(),i,2);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getPrecio(),i,3);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getUnidadMedida(),i,4);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getCantidad(),i,5);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getImportelSinDescuento(),i,6);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getTipoDescuento(),i,7);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getDescuento(),i,8);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getDescuentoEnValores(),i,9);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getImporteConDescuento(),i,10);
          }
    }    private void limpiarTablarPedidoDetalle()
    {
        int rowCount=TblPedidoDetalle.getRowCount();
        for(int i=0;i<rowCount;i++)
        {
            ((DefaultTableModel)TblPedidoDetalle.getModel()).removeRow(0);
        }
    }
    private void TxtTipoDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTipoDescuentoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTipoDescuentoKeyReleased
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Label.Label LblDescuentoEnValores;
    private Label.Label LblDescuentoSubtotal;
    private Label.Label LblDescuentoTotal;
    private Label.Label LblHora;
    private Label.Label LblISC;
    private Label.Label LblIgv;
    private Label.Label LblMontoTotal;
    private Label.Label LblPorcentaje;
    private Label.Label LblSubTotalNetoSinIgv;
    private Label.Label LblSubtotalBruto;
    private Label.Label LblSubtotalNeto;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JTable TblPedidoDetalle;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenuPedido;
    private javax.swing.JScrollPane jScrollPane3;
    private Label.Label label1;
    private Label.Label label10;
    private Label.Label label11;
    private Label.Label label12;
    private Label.Label label15;
    private Label.Label label16;
    private Label.Label label17;
    private Label.Label label18;
    private Label.Label label19;
    private Label.Label label2;
    private Label.Label label21;
    private Label.Label label23;
    private Label.Label label24;
    private Label.Label label25;
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
