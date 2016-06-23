package view.vtaVenta;

import controller.acceso.ConexionBD;
import controller.ctbContabilidad.CCCuentaCorrienteCliente;
import controller.vtaVenta.CCComprobanteVenta;
import controller.vtaVenta.CCCuentaEmpresa;
import controller.vtaVenta.CCInstituicionFinanciera;
import controller.vtaVenta.CCMoneda;
import controller.vtaVenta.CCPedido;
import controller.vtaVenta.CCSerie;
import controller.vtaVenta.CCTipoIngreso;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import modelo.vtaVenta.entidad.CEComprobanteVenta;
import modelo.vtaVenta.entidad.CEComprobanteVentaDetalle;
import modelo.ctbContabilidad.entidad.CECuentaCorrienteCliente;
import modelo.vtaVenta.entidad.CECuentaEmpresa;
import modelo.vtaVenta.entidad.CEFormaPago;
import modelo.vtaVenta.entidad.CEInstitucionFinanciera;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CEPedidoDetalle;
import modelo.vtaVenta.entidad.CEPedidoMatriz;
import modelo.vtaVenta.entidad.CESerie;
import modelo.vtaVenta.entidad.CEFormaPagoDetalle;
import modelo.vtaVenta.entidad.CETipoPago;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.SetterGetter;
import util.clases.grlGeneral.VerificadorDeTxt;
import util.clases.vtaVenta.CLImprimirComprobante;
import util.clases.vtaVenta.CLImprimirComprobante1;
import view.FrmSistemaMenu;
import view.ctbContabilidad.DlgConsultaCuentaCorrienteCliente;

public class DlgCobroPedido extends DialogoPadre
{
    private CEPedidoMatriz oCEPedidoMatriz;
    private float TotalPagar;
    private float TotalPagarRedondeado;
    public DlgCobroPedido(java.awt.Frame parent, boolean modal,CEPedidoMatriz pCEPedidoMatriz)
    {
        super(parent, modal);
        initComponents();
        Container con = this.getContentPane();
        con.setBackground(new Color(204, 204, 204));
        TxtAmortizacion.setDocument(new VerificadorDeTxt("Numero", 0, TxtAmortizacion));
        DtchFecha.setDate(FrmSistemaMenu.FechaSistema);
        DtchFecha.setEnabled(false);
        //DtchFecha.setVisible(false);
        

        this.oCEPedidoMatriz = pCEPedidoMatriz;
        TotalPagar=oCEPedidoMatriz.getTotalPagar();
        TotalPagarRedondeado=VerificadorDeTxt.convertFloat(CLRedondear.Redondear(oCEPedidoMatriz.getTotalPagar(),1)+"");
        TxtMontoTotal.setText(CLRedondear.FormatearDosDigitos(TotalPagarRedondeado));
        TxtMontoRestante.setText(CLRedondear.FormatearDosDigitos(TotalPagarRedondeado));
        LblTipoComprobante.setText(oCEPedidoMatriz.getTipoComprobante());
        CESerie oCESerie = CCSerie.consultarSeriePorPuntoVenta(FrmSistemaMenu.oCEUsuario.getIp(), oCEPedidoMatriz.getIdTipoComprobante());
        LblSerie.setText(oCESerie.getNumeroGeneradoString());
        LblNumeroComprobante.setText(oCESerie.getUltimoNumeroGeneradoString());
        cargarInstituicionFinaciera();
        cargarTipoIngresoFinanciero();
        CbxInstitucionFinanciera.setSelectedIndex(0);
        cargarCuentaEmpresa(((CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem()).getIdInstitucionFinanciera());
        CbxCuentaEmpresa.setSelectedIndex(0);
        cargarTipoMoneda();
        definirMoneda();        
        CbxTipoPago.setSelectedIndex(0);
        TxtAmortizacion.setText(0 + "");
        TxtVueltoAmor.setText(0 + "");
        TxtAmortizacion.selectAll();
        TxtAmortizacion.requestFocus();
        TxtVueltoTotal.setText(CLRedondear.RedondearString(sumarVueltos(), 2) + "");
        TblPagosDetalle.getModel().addTableModelListener(new TableModelListener()
        {

            public void tableChanged(TableModelEvent e)
            {
                if (e.getType() == e.INSERT || e.getType() == e.DELETE)
                {
                    float pagado = VerificadorDeTxt.convertFloat(CLRedondear.Redondear(sumarIngresosLiquido(), 2)+"");
                    float montoTotal=VerificadorDeTxt.convertFloat(TxtMontoTotal.getText());
                    TxtMontoRestante.setText(CLRedondear.RedondearString(montoTotal - pagado, 2));
                    if (CLRedondear.Redondear(montoTotal - pagado, 2) < 0)
                    {
                        TxtMontoRestante.setText("00.00");
                    }
                    TxtVueltoTotal.setText(CLRedondear.RedondearString(sumarVueltos(), 2) + "");
                }

            }
        });
        LblCliente.setText(oCEPedidoMatriz.getCliente());
        

    }

    public void limpiarTabla()
    {
        int filas=TblPagosDetalle.getRowCount();

        if(filas>0)
            for(int i=0;i<filas;i++)
            {
                ((DefaultTableModel)TblPagosDetalle.getModel()).removeRow(0);
            }
    }
    private boolean ValidarPedido()
    {
        long idPedido=oCEPedidoMatriz.getIdPedido();
        oCEPedidoMatriz=CCPedido.ConsultarPedidosPorId(idPedido);
        if(oCEPedidoMatriz.getIdUltimoEstado()!=1)
        {
            JOptionPane.showMessageDialog(this,"No se puede Realizar la operación, el pedido se encuentra "+ oCEPedidoMatriz.getUltimoEstado(),"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Properties props = new Properties();
        try {
                props.load(ConexionBD.class.getResourceAsStream("PropiedadesDelSistema.properties"));
            } catch (IOException ex) {
                Logger.getLogger(CLImprimirComprobante.class.getName()).log(Level.SEVERE, null, ex);
            }
             int validar = Integer.parseInt(props.getProperty("ayn.validar"));
         if(validar==1){
        if(TotalPagar!=oCEPedidoMatriz.getTotalPagar()){
        TotalPagarRedondeado=VerificadorDeTxt.convertFloat(CLRedondear.Redondear(oCEPedidoMatriz.getTotalPagar(),1)+"");
        TxtMontoTotal.setText(CLRedondear.FormatearDosDigitos(TotalPagarRedondeado));
        TxtMontoRestante.setText(CLRedondear.FormatearDosDigitos(TotalPagarRedondeado));
        LblTipoComprobante.setText(oCEPedidoMatriz.getTipoComprobante());
        JOptionPane.showMessageDialog(this,"Se actualizo el Monto "+CLRedondear.FormatearDosDigitos(TotalPagar)+" con el Siguiente:\n"+
                CLRedondear.FormatearDosDigitos(oCEPedidoMatriz.getTotalPagar())
                + "\n vuelva a ingresar el pago","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        TotalPagar=oCEPedidoMatriz.getTotalPagar();
        TxtAmortizacion.requestFocus();
        limpiarTabla();
        return false;
        }
        }

       return true;

    }
    private float sumarIngresosLiquido()
    {
        int size = TblPagosDetalle.getRowCount();
        float montoTotal = 0;
        for (int i = 0; i < size; i++)
        {
            float monto = Float.parseFloat(TblPagosDetalle.getValueAt(i, 6).toString());
            montoTotal = montoTotal + monto;
        }
        return montoTotal;
    }

    private float sumarPagos()
    {
        int size = TblPagosDetalle.getRowCount();
        float montoTotal = 0;
        for (int i = 0; i < size; i++)
        {
            float monto = Float.parseFloat(TblPagosDetalle.getValueAt(i, 4).toString());
            montoTotal = montoTotal + monto;
        }
        return montoTotal;
    }

    private int contarEfectivos()
    {
        int size = TblPagosDetalle.getRowCount();
        int count = 0;
        for (int i = 0; i < size; i++)
        {
            CEFormaPagoDetalle oCEFormaPagoDetalle = (CEFormaPagoDetalle) TblPagosDetalle.getValueAt(i, 1);
            if (oCEFormaPagoDetalle.getIdTipoPago() == 1)
            {
                count++;
            }
        }
        return count;
    }

    private float sumarDescargasCuentasCorrientes()
    {
        int size = TblPagosDetalle.getRowCount();
        float montoTotal = 0;
        for (int i = 0; i < size; i++)
        {
            CEFormaPagoDetalle oCEFormaPagoDetalle = (CEFormaPagoDetalle) TblPagosDetalle.getValueAt(i, 1);
            if (oCEFormaPagoDetalle.getIdTipoPago() == 4)
            {
                float monto = Float.parseFloat(TblPagosDetalle.getValueAt(i, 4).toString());
                montoTotal = montoTotal + monto;
            }
        }
        return montoTotal;
    }

    private float sumarVueltos()
    {
        int size = TblPagosDetalle.getRowCount();
        float montoTotal = 0;
        for (int i = 0; i < size; i++)
        {
            float monto = Float.parseFloat(TblPagosDetalle.getValueAt(i, 5).toString());
            montoTotal = montoTotal + monto;
        }
        return montoTotal;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        BtnAgregar1 = new javax.swing.JButton();
        LblTipoComprobante = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblPagosDetalle = new javax.swing.JTable();
        BtnEliminar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        CbxTipoPago = new javax.swing.JComboBox();
        TxtNumTipoPago = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        CbxInstitucionFinanciera = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        DtcFechaCheque = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        CbxCuentaEmpresa = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        TxtCodigoAutorizacion = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        LblMonedaMontoTotal = new javax.swing.JLabel();
        LblMonedaMontoRestante = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        TxtMontoTotal = new javax.swing.JLabel();
        TxtMontoRestante = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        TxtEquivalenteVuelto = new javax.swing.JTextField();
        LblMonedaVuelto = new javax.swing.JLabel();
        LblTipoCambioAmortiza = new javax.swing.JLabel();
        TxtAmortizacion = new javax.swing.JTextField();
        CbxAmortizaMoneda = new javax.swing.JComboBox();
        TxtEquivalenteAmortizacion = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        TxtVueltoAmor = new javax.swing.JTextField();
        CbxVueltoMoneda = new javax.swing.JComboBox();
        LblMonedaAmortiza = new javax.swing.JLabel();
        LblTipoCambioVuelto = new javax.swing.JLabel();
        BtnAgregar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        BtnCuentaCorriente = new javax.swing.JButton();
        BtnVerDetalles = new javax.swing.JButton();
        BtnImprimirComprobante = new javax.swing.JButton();
        BtnAgregar2 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        LblMonedaVueltoTotal = new javax.swing.JLabel();
        LblCliente = new javax.swing.JLabel();
        TxtVueltoTotal = new javax.swing.JLabel();
        TxtTipoCambio = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        DtchFecha =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        chkFecha = new Opcion.CheckBox();
        jPanel1 = new javax.swing.JPanel();
        LblNumeroComprobante = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        LblSerie = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        chkImprimir = new Opcion.CheckBox();

        BtnAgregar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        BtnAgregar1.setText("Agregar");
        BtnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregar1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cobros de Pedido");

        LblTipoComprobante.setBackground(new java.awt.Color(255, 255, 255));
        LblTipoComprobante.setFont(new java.awt.Font("Arial", 1, 24));
        LblTipoComprobante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblTipoComprobante.setText("NOTA DE PEDIDO");
        LblTipoComprobante.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        LblTipoComprobante.setOpaque(true);

        TblPagosDetalle.setFont(new java.awt.Font("Tahoma", 0, 12));
        TblPagosDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Tipo Pago", "Moneda", "Tipo Cambio", "Monto", "Vuelto", "Liquido", "Num. Pago", "Inst. Finan.", "Fecha Cheque", "Cod. Autor."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        TblPagosDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblPagosDetalle.setRowHeight(20);
        jScrollPane2.setViewportView(TblPagosDetalle);
        TblPagosDetalle.getColumnModel().getColumn(0).setMinWidth(30);
        TblPagosDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
        TblPagosDetalle.getColumnModel().getColumn(0).setMaxWidth(30);
        TblPagosDetalle.getColumnModel().getColumn(1).setMinWidth(150);
        TblPagosDetalle.getColumnModel().getColumn(1).setPreferredWidth(150);
        TblPagosDetalle.getColumnModel().getColumn(1).setMaxWidth(150);
        TblPagosDetalle.getColumnModel().getColumn(2).setMinWidth(60);
        TblPagosDetalle.getColumnModel().getColumn(2).setPreferredWidth(60);
        TblPagosDetalle.getColumnModel().getColumn(2).setMaxWidth(60);
        TblPagosDetalle.getColumnModel().getColumn(3).setMinWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(3).setPreferredWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(3).setMaxWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(4).setMinWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(4).setMaxWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(5).setMinWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(5).setPreferredWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(5).setMaxWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(7).setMinWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(7).setPreferredWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(7).setMaxWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(8).setMinWidth(100);
        TblPagosDetalle.getColumnModel().getColumn(8).setMaxWidth(100);
        TblPagosDetalle.getColumnModel().getColumn(9).setMinWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(9).setMaxWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(10).setMinWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(10).setPreferredWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(10).setMaxWidth(80);

        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(255, 255, 204));
        jLabel14.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel14.setForeground(new java.awt.Color(153, 0, 0));
        jLabel14.setText("Vuelto Total :");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setText("Tipo de Pago ");

        CbxTipoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoPagoActionPerformed(evt);
            }
        });

        TxtNumTipoPago.setEditable(false);
        TxtNumTipoPago.setFont(new java.awt.Font("Arial", 0, 14));
        TxtNumTipoPago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel26.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel26.setForeground(new java.awt.Color(0, 0, 102));
        jLabel26.setText("Nº Tipo Pago   ");

        CbxInstitucionFinanciera.setEnabled(false);
        CbxInstitucionFinanciera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxInstitucionFinancieraActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel28.setForeground(new java.awt.Color(0, 0, 102));
        jLabel28.setText("Institución Financiera");

        jLabel29.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel29.setForeground(new java.awt.Color(0, 0, 102));
        jLabel29.setText("Fecha Cheque");

        DtcFechaCheque.setEnabled(false);

        CbxCuentaEmpresa.setEnabled(false);

        jLabel30.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel30.setForeground(new java.awt.Color(0, 0, 102));
        jLabel30.setText("Cuenta de Empresa");

        TxtCodigoAutorizacion.setEditable(false);
        TxtCodigoAutorizacion.setFont(new java.awt.Font("Arial", 0, 14));
        TxtCodigoAutorizacion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel15.setForeground(new java.awt.Color(0, 0, 102));
        jLabel15.setText("Código Autorización");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(177, 177, 177))
                    .addComponent(CbxTipoPago, javax.swing.GroupLayout.Alignment.TRAILING, 0, 320, Short.MAX_VALUE)
                    .addComponent(jLabel28)
                    .addComponent(CbxInstitucionFinanciera, javax.swing.GroupLayout.Alignment.TRAILING, 0, 320, Short.MAX_VALUE)
                    .addComponent(jLabel30)
                    .addComponent(CbxCuentaEmpresa, 0, 320, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(31, 31, 31))
                    .addComponent(TxtNumTipoPago, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(DtcFechaCheque, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(TxtCodigoAutorizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CbxTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNumTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CbxInstitucionFinanciera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DtcFechaCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CbxCuentaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtCodigoAutorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LblMonedaMontoTotal.setFont(new java.awt.Font("Arial", 1, 14));
        LblMonedaMontoTotal.setText("S/.");
        LblMonedaMontoTotal.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        LblMonedaMontoRestante.setFont(new java.awt.Font("Arial", 1, 14));
        LblMonedaMontoRestante.setText("S/.");
        LblMonedaMontoRestante.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Monto Restante:");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel27.setForeground(new java.awt.Color(0, 0, 102));
        jLabel27.setText("Monto Total :");
        jLabel27.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        TxtMontoTotal.setBackground(new java.awt.Color(255, 255, 255));
        TxtMontoTotal.setFont(new java.awt.Font("Arial", 1, 26));
        TxtMontoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TxtMontoTotal.setText("0000000.00");
        TxtMontoTotal.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        TxtMontoTotal.setOpaque(true);

        TxtMontoRestante.setBackground(new java.awt.Color(255, 255, 255));
        TxtMontoRestante.setFont(new java.awt.Font("Arial", 1, 26));
        TxtMontoRestante.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TxtMontoRestante.setText("000000.00");
        TxtMontoRestante.setOpaque(true);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtMontoTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblMonedaMontoTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtMontoRestante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblMonedaMontoRestante)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(TxtMontoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblMonedaMontoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(TxtMontoRestante)
                    .addComponent(LblMonedaMontoRestante))
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel31.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel31.setForeground(new java.awt.Color(0, 0, 51));
        jLabel31.setText("Amortiza:");

        TxtEquivalenteVuelto.setEditable(false);
        TxtEquivalenteVuelto.setFont(new java.awt.Font("Arial", 0, 14));
        TxtEquivalenteVuelto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        LblMonedaVuelto.setFont(new java.awt.Font("Verdana", 0, 12));
        LblMonedaVuelto.setText("S/.");

        LblTipoCambioAmortiza.setFont(new java.awt.Font("Verdana", 0, 12));
        LblTipoCambioAmortiza.setForeground(new java.awt.Color(153, 0, 0));
        LblTipoCambioAmortiza.setText("x 1.000 =");

        TxtAmortizacion.setFont(new java.awt.Font("Arial", 0, 14));
        TxtAmortizacion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtAmortizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtAmortizacionActionPerformed(evt);
            }
        });
        TxtAmortizacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtAmortizacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtAmortizacionFocusLost(evt);
            }
        });
        TxtAmortizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtAmortizacionKeyReleased(evt);
            }
        });

        CbxAmortizaMoneda.setEnabled(false);

        TxtEquivalenteAmortizacion.setEditable(false);
        TxtEquivalenteAmortizacion.setFont(new java.awt.Font("Arial", 0, 14));
        TxtEquivalenteAmortizacion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel36.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel36.setForeground(new java.awt.Color(0, 0, 51));
        jLabel36.setText("Vuelto   :");

        TxtVueltoAmor.setEditable(false);
        TxtVueltoAmor.setFont(new java.awt.Font("Arial", 0, 14));
        TxtVueltoAmor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        CbxVueltoMoneda.setEnabled(false);

        LblMonedaAmortiza.setFont(new java.awt.Font("Verdana", 0, 12));
        LblMonedaAmortiza.setText("S/.");

        LblTipoCambioVuelto.setFont(new java.awt.Font("Verdana", 0, 12));
        LblTipoCambioVuelto.setForeground(new java.awt.Color(153, 0, 0));
        LblTipoCambioVuelto.setText("x 1.000 =");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CbxVueltoMoneda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CbxAmortizaMoneda, 0, 66, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtVueltoAmor, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(TxtAmortizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LblTipoCambioAmortiza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblTipoCambioVuelto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(TxtEquivalenteVuelto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblMonedaVuelto))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(TxtEquivalenteAmortizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblMonedaAmortiza)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtEquivalenteVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LblMonedaVuelto, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtEquivalenteAmortizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LblMonedaAmortiza, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(LblTipoCambioAmortiza, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LblTipoCambioVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                    .addComponent(CbxAmortizaMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                    .addComponent(CbxVueltoMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(TxtAmortizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtVueltoAmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19))))
        );

        BtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        BtnAgregar.setText("Agregar");
        BtnAgregar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new java.awt.GridLayout(0, 1, 1, 1));

        BtnCuentaCorriente.setFont(new java.awt.Font("Arial", 0, 14));
        BtnCuentaCorriente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/tb_preview.png"))); // NOI18N
        BtnCuentaCorriente.setText("CCorriente");
        BtnCuentaCorriente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCuentaCorriente.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnCuentaCorriente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCuentaCorriente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCuentaCorrienteActionPerformed(evt);
            }
        });
        jPanel6.add(BtnCuentaCorriente);

        BtnVerDetalles.setFont(new java.awt.Font("Arial", 0, 14));
        BtnVerDetalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/LettersDetail.png"))); // NOI18N
        BtnVerDetalles.setText("Ver Detalles");
        BtnVerDetalles.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnVerDetalles.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnVerDetalles.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnVerDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerDetallesActionPerformed(evt);
            }
        });
        jPanel6.add(BtnVerDetalles);

        BtnImprimirComprobante.setFont(new java.awt.Font("Arial", 0, 14));
        BtnImprimirComprobante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/agt_print.png"))); // NOI18N
        BtnImprimirComprobante.setText("Imprimir ");
        BtnImprimirComprobante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnImprimirComprobante.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnImprimirComprobante.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnImprimirComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImprimirComprobanteActionPerformed(evt);
            }
        });
        jPanel6.add(BtnImprimirComprobante);

        BtnAgregar2.setFont(new java.awt.Font("Arial", 0, 14));
        BtnAgregar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Refresh.gif"))); // NOI18N
        BtnAgregar2.setText("Reiniciar");
        BtnAgregar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAgregar2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnAgregar2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAgregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregar2ActionPerformed(evt);
            }
        });
        jPanel6.add(BtnAgregar2);

        jLabel35.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel35.setForeground(new java.awt.Color(153, 0, 0));
        jLabel35.setText("Tipo Cambio:");

        LblMonedaVueltoTotal.setBackground(new java.awt.Color(255, 255, 204));
        LblMonedaVueltoTotal.setFont(new java.awt.Font("Arial", 1, 20));
        LblMonedaVueltoTotal.setText("S/.");

        LblCliente.setBackground(new java.awt.Color(255, 255, 204));
        LblCliente.setFont(new java.awt.Font("Arial", 1, 14));
        LblCliente.setText("MANRIQUE LEZAMETA ELIZABETH CRISTINA");
        LblCliente.setOpaque(true);

        TxtVueltoTotal.setBackground(new java.awt.Color(255, 255, 204));
        TxtVueltoTotal.setFont(new java.awt.Font("Arial", 1, 30));
        TxtVueltoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TxtVueltoTotal.setText("00");

        TxtTipoCambio.setFont(new java.awt.Font("Arial", 1, 14));
        TxtTipoCambio.setText("1.00");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Update.gif"))); // NOI18N
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel17.setForeground(new java.awt.Color(0, 0, 102));
        jLabel17.setText("Cliente   :");

        DtchFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtchFechaPropertyChange(evt);
            }
        });

        chkFecha.setBackground(new java.awt.Color(204, 204, 204));
        chkFecha.setForeground(new java.awt.Color(0, 0, 102));
        chkFecha.setText("Fecha ");
        chkFecha.setEnabled(false);
        chkFecha.setOpaque(false);
        chkFecha.setVisible(false);
        chkFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFechaActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LblNumeroComprobante.setBackground(new java.awt.Color(255, 255, 255));
        LblNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 14));
        LblNumeroComprobante.setForeground(new java.awt.Color(0, 0, 102));
        LblNumeroComprobante.setText("003-2126633");
        LblNumeroComprobante.setOpaque(true);

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel12.setText("Nº");
        jLabel12.setOpaque(true);

        LblSerie.setBackground(new java.awt.Color(255, 255, 255));
        LblSerie.setFont(new java.awt.Font("Arial", 1, 14));
        LblSerie.setForeground(new java.awt.Color(0, 0, 102));
        LblSerie.setText("003");
        LblSerie.setOpaque(true);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel10.setText("Serie:");
        jLabel10.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblSerie)
                        .addContainerGap())
                    .addComponent(LblNumeroComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(LblSerie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblNumeroComprobante)
                    .addComponent(jLabel12)))
        );

        chkImprimir.setBackground(new java.awt.Color(204, 204, 204));
        chkImprimir.setForeground(new java.awt.Color(0, 0, 102));
        chkImprimir.setSelected(true);
        chkImprimir.setText("Imprimir al Guardar");
        chkImprimir.setEnabled(false);
        chkImprimir.setOpaque(false);
        chkImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtVueltoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(LblMonedaVueltoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(LblTipoComprobante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtTipoCambio, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton1))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(chkImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                                .addComponent(chkFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DtchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblTipoComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LblCliente)
                        .addComponent(jLabel17))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtTipoCambio)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkImprimir, 0, 0, Short.MAX_VALUE))
                    .addComponent(DtchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblMonedaVueltoTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtVueltoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void definirMoneda()
    {
        LblMonedaAmortiza.setText(oCEPedidoMatriz.getMoneda());
        LblMonedaMontoRestante.setText(oCEPedidoMatriz.getMoneda());
        LblMonedaMontoTotal.setText(oCEPedidoMatriz.getMoneda());
        TxtTipoCambio.setText(oCEPedidoMatriz.getTipoCambio() + "");
        LblTipoCambioAmortiza.setText("x " + oCEPedidoMatriz.getTipoCambio() + " =");
        LblTipoCambioVuelto.setText("x " + oCEPedidoMatriz.getTipoCambio() + " =");
        for (int i = 0; i < CbxAmortizaMoneda.getItemCount(); i++)
        {
            CEMoneda oCEMoneda = (CEMoneda) CbxAmortizaMoneda.getItemAt(i);
            if (oCEMoneda.getId_moneda() == oCEPedidoMatriz.getIdMoneda())
            {
                CbxAmortizaMoneda.setSelectedIndex(i);
                CbxVueltoMoneda.setSelectedIndex(i);
            }

        }
    }

    private void cargarTipoIngresoFinanciero()
    {
        List<CETipoPago> oListTipoIngreso = CCTipoIngreso.consultarTipoIngresoFinaciero();
        construirModeloCombo(CbxTipoPago, (ArrayList) oListTipoIngreso);
    }

    private void cargarInstituicionFinaciera()
    {
        List<CEInstitucionFinanciera> oListTipoIngreso = CCInstituicionFinanciera.consultarInstituicionFinanciera();
        construirModeloCombo(CbxInstitucionFinanciera, (ArrayList) oListTipoIngreso);
    }

    private void cargarCuentaEmpresa(int IdInstituicionFinanciera)
    {
        List<CECuentaEmpresa> oListTipoIngreso = CCCuentaEmpresa.consultarCuentaEmpresaPorInstitucionFinancieraMoneda(IdInstituicionFinanciera, oCEPedidoMatriz.getIdMoneda());
        construirModeloCombo(CbxCuentaEmpresa, (ArrayList) oListTipoIngreso);
    }

    private void cargarTipoMoneda()
    {
        List<CEMoneda> oListTipoIngreso = CCMoneda.listaAll();
        construirModeloCombo(CbxAmortizaMoneda, (ArrayList) oListTipoIngreso);
        construirModeloCombo(CbxVueltoMoneda, (ArrayList) oListTipoIngreso);
    }

    public void construirModeloCombo(JComboBox oBox, ArrayList oLista)
    {
        oBox.removeAllItems();
        if (oLista != null)
        {
            for (Object objeto : oLista)
            {
                oBox.addItem(objeto);
            }
        }
    }
    private void BtnImprimirComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirComprobanteActionPerformed

        if(ValidarPedido()){
        eventoImprimir();
        }
    }//GEN-LAST:event_BtnImprimirComprobanteActionPerformed


    private void eventoImprimir()
    {
        try {
        Float txt = Float.parseFloat(TxtMontoRestante.getText());
        if (txt == 0)
        {
            CEComprobanteVenta oCEComprobanteVenta = new CEComprobanteVenta();
            oCEComprobanteVenta.setIdComprobanteVenta(0);
            oCEComprobanteVenta.setIdComprobanteVentaRef(0);
            oCEComprobanteVenta.setCliente(oCEPedidoMatriz.getCliente());
            oCEComprobanteVenta.setEmpleado(oCEPedidoMatriz.getEmpleado());
            oCEComprobanteVenta.setDireccion(oCEPedidoMatriz.getDireccion());
            oCEComprobanteVenta.setIdCliente(oCEPedidoMatriz.getIdCliente());
            oCEComprobanteVenta.setIdCondicion(oCEPedidoMatriz.getIdCondicion());

            oCEComprobanteVenta.setMontoTotal(oCEPedidoMatriz.getMontoTotal());
            oCEComprobanteVenta.setSubtotalBruto(oCEPedidoMatriz.getSubtotalBruto());
            oCEComprobanteVenta.setSubtotalNeto(oCEPedidoMatriz.getSubTotalNeto());
            oCEComprobanteVenta.setIGVActual(oCEPedidoMatriz.getIGVActual());
            oCEComprobanteVenta.setSubTotalNetoSinIGV(oCEPedidoMatriz.getSubtotalNetoSinIGV());
            oCEComprobanteVenta.setIdTipoComprobanteVenta(oCEPedidoMatriz.getIdTipoComprobante());
            oCEComprobanteVenta.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
            oCEComprobanteVenta.setIGV(oCEPedidoMatriz.getIGV());
            oCEComprobanteVenta.setISC(oCEPedidoMatriz.getISC());
            oCEComprobanteVenta.setIdPedidoRef(oCEPedidoMatriz.getIdPedido());
            oCEComprobanteVenta.setDescuento(oCEPedidoMatriz.getDescuento());
            oCEComprobanteVenta.setDescuentoEnSubtotal(oCEPedidoMatriz.getDescuentoEnSubtotal());
            oCEComprobanteVenta.setDescuentoTotal(oCEPedidoMatriz.getDescuentoTotal());
            oCEComprobanteVenta.setDescuentoEnValores(oCEPedidoMatriz.getDescuentoValores());
            oCEComprobanteVenta.setIdDescuento(oCEPedidoMatriz.getIdDescuento());
            oCEComprobanteVenta.setIdSucursal(oCEPedidoMatriz.getIdSucursal());
            oCEComprobanteVenta.setIdMoneda(oCEPedidoMatriz.getIdMoneda());
            oCEComprobanteVenta.setTipoCambio(oCEPedidoMatriz.getTipoCambio());
            oCEComprobanteVenta.setMontoPercepcion(oCEPedidoMatriz.getMontoPercepcion());
            oCEComprobanteVenta.setTotalPagar(oCEPedidoMatriz.getTotalPagar());
            oCEComprobanteVenta.setDNI(oCEPedidoMatriz.getDNI());
            oCEComprobanteVenta.setRUC(oCEPedidoMatriz.getRUC());
            oCEComprobanteVenta.setMontoExonerado(oCEPedidoMatriz.getMontoExonerado());
            oCEComprobanteVenta.setTasaPercepcion(oCEPedidoMatriz.getTasaPercepcion());
            SetterGetter oSetterGetter = new SetterGetter();
            oSetterGetter.setmIntTipo(1);
            ConvertidorFecha oConvertidorFecha = new ConvertidorFecha();
                                    if (DtchFecha.getCalendar() != null)
                                    {
                                        oConvertidorFecha.setFecha(DtchFecha.getCalendar());
                                        oCEComprobanteVenta.setFecha(oSetterGetter.convertir(oConvertidorFecha.getFechaConvertida()));
                                    }
            oCEComprobanteVenta.setNumPedido(oCEPedidoMatriz.getCodigo());
            oCEComprobanteVenta.setNumComprobante(LblNumeroComprobante.getText());
            oCEComprobanteVenta.setTipoComprobante(oCEPedidoMatriz.getTipoComprobante());
            oCEComprobanteVenta.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
            List<CEComprobanteVentaDetalle> oLstComprobanteDetalle = new ArrayList<CEComprobanteVentaDetalle>();
            //oCEPedidoMatriz.setLstPedidoDetalle(CCPedido.ConsultarPedidoDetalle(oCEPedidoMatriz.getIdPedido()));

            float importe=0;
            for (CEPedidoDetalle oCEPedidoDetalle : oCEPedidoMatriz.getLstPedidoDetalle())
            {
                CEComprobanteVentaDetalle oCEComprobanteVentaDetalle = new CEComprobanteVentaDetalle();
                oCEComprobanteVentaDetalle.setIdPedidoDetalleRef(oCEPedidoDetalle.getIdPedidoDetalle());
                oCEComprobanteVentaDetalle.setIdProducto((int) oCEPedidoDetalle.getIdProducto());
                oCEComprobanteVentaDetalle.setIdAlmacen(oCEPedidoDetalle.getIdAlmacen());
                oCEComprobanteVentaDetalle.setIdUnidadMedidaVenta(oCEPedidoDetalle.getIdUnidadMedidaVenta());
                oCEComprobanteVentaDetalle.setCantidad(oCEPedidoDetalle.getCantidad());
                oCEComprobanteVentaDetalle.setEquivalenciaBase(oCEPedidoDetalle.getEqivalenciaUMB()*oCEPedidoDetalle.getCantidad());
                oCEComprobanteVentaDetalle.setUnidadMedida(oCEPedidoDetalle.getUnidadMedida());
                oCEComprobanteVentaDetalle.setProducto(oCEPedidoDetalle.getProducto());
                oCEComprobanteVentaDetalle.setPrecio(oCEPedidoDetalle.getPrecio());
                oCEComprobanteVentaDetalle.setImporteSinDescuento(oCEPedidoDetalle.getImportelSinDescuento());
                oCEComprobanteVentaDetalle.setIdTipoDescuento(oCEPedidoDetalle.getIdTipoDescuento());
                oCEComprobanteVentaDetalle.setDescuento(oCEPedidoDetalle.getDescuento());
                oCEComprobanteVentaDetalle.setDescuentoEnValores(oCEPedidoDetalle.getDescuentoEnValores());
                importe=importe+oCEPedidoDetalle.getImporteConDescuento()+oCEPedidoDetalle.getExonerado();
                oCEComprobanteVentaDetalle.setImporteConDescuento(oCEPedidoDetalle.getImporteConDescuento());
                oCEComprobanteVentaDetalle.setExonerado(oCEPedidoDetalle.getExonerado());
                oCEComprobanteVentaDetalle.setSiNoImpuesto(oCEPedidoDetalle.isSinoImpuesto());
                oCEComprobanteVentaDetalle.setPrecioconDesc(oCEPedidoDetalle.getPrecioConDesc());
                oLstComprobanteDetalle.add(oCEComprobanteVentaDetalle);
            }

            CEFormaPago oCEFormaPago = new CEFormaPago();

            oCEFormaPago.setIdSucursal(oCEPedidoMatriz.getIdSucursal());
            oCEFormaPago.setIdTipoComprobanteVenta(oCEPedidoMatriz.getIdTipoComprobante());
            // NumDoc
            oCEFormaPago.setIdCliente(oCEPedidoMatriz.getIdCliente());
            oCEFormaPago.setIdMoneda(oCEPedidoMatriz.getIdMoneda());
            oCEFormaPago.setTipoCambio(oCEPedidoMatriz.getTipoCambio());
            // IdPuntoVenta
            oCEFormaPago.setFecha(null);
            // Observacion
            oCEFormaPago.setSubTotalNeto(oCEPedidoMatriz.getSubTotalNeto());
            oCEFormaPago.setSubTotalNetoSinIGV(oCEPedidoMatriz.getSubtotalNetoSinIGV());
            oCEFormaPago.setIGVActual(oCEPedidoMatriz.getIGVActual());
            oCEFormaPago.setIGV(oCEPedidoMatriz.getIGV());
            oCEFormaPago.setISC(oCEPedidoMatriz.getISC());
            oCEFormaPago.setIngresoTotalLiquido(TotalPagarRedondeado);
            float DescargasCuentasCorrientes=VerificadorDeTxt.convertFloat(""+sumarDescargasCuentasCorrientes());
            float totalRedondeado=TotalPagarRedondeado;
            oCEFormaPago.setIngresoTotalLiquidoSinCuentaCorriente(VerificadorDeTxt.convertFloat(CLRedondear.Redondear(totalRedondeado-DescargasCuentasCorrientes,2)+""));
            oCEFormaPago.setVueltoTotal(sumarVueltos());
            oCEFormaPago.setPagoTotal(VerificadorDeTxt.convertFloat(""+CLRedondear.Redondear(sumarPagos(), 2)));
            oCEFormaPago.setPagoTotalSinCuentaCorriente(VerificadorDeTxt.convertFloat(CLRedondear.Redondear(sumarPagos()- DescargasCuentasCorrientes, 2)+""));
            oCEFormaPago.setMontoTotal(oCEPedidoMatriz.getMontoTotal());
            oCEFormaPago.setMontoSinCuentaCorriente(VerificadorDeTxt.convertFloat(CLRedondear.Redondear(totalRedondeado-DescargasCuentasCorrientes,2)+""));
            oCEFormaPago.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
            oCEFormaPago.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
            List<CEFormaPagoDetalle> oLstIngresoFinancieroDetalle = new ArrayList<CEFormaPagoDetalle>();
            for (int i = 0; i < TblPagosDetalle.getRowCount(); i++)
            {
                CEFormaPagoDetalle oCEFormaPagoDetalle = (CEFormaPagoDetalle) TblPagosDetalle.getValueAt(i, 1);
                oCEFormaPagoDetalle.setMonto(VerificadorDeTxt.convertFloat(TblPagosDetalle.getValueAt(i, 4)));
                oCEFormaPagoDetalle.setVuelto(VerificadorDeTxt.convertFloat(TblPagosDetalle.getValueAt(i, 5)));
                
                oLstIngresoFinancieroDetalle.add(oCEFormaPagoDetalle);
            }
            oCEFormaPago.setoLstFormaPagoDetalle(oLstIngresoFinancieroDetalle);
            oCEComprobanteVenta.setoLstComprobanteDetalle(oLstComprobanteDetalle);
            oCEComprobanteVenta.setFormaPago(oCEFormaPago);
            
            
             String mensaje=CCComprobanteVenta.RegistrarComprobanteVenta(oCEComprobanteVenta);

           if (mensaje.equals("OK"))
            {
               try {
               BtnImprimirComprobante.setEnabled(false);
                   if(chkImprimir.isSelected()){
                       CLImprimirComprobante1 oCLImprimirComprobantePago=new CLImprimirComprobante1(oCEComprobanteVenta);
                       }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(this, "Se guardo con exito, pero ocurio un Error Inesperado, Impresion Cancelada");
                    e.printStackTrace();
                }
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No se pudo completar la operación: \nStock Menor a la cantidad de pedido\nDetalles:\n"+mensaje);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "El pago no puede ser menor que el MONTO A PAGAR");

        }

      }  catch(Exception e)
        {
          JOptionPane.showMessageDialog(this, "Operación Fallida");
      }
        
    }

    private void BtnCuentaCorrienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCuentaCorrienteActionPerformed
        CECuentaCorrienteCliente oCECuentaCorriente = CCCuentaCorrienteCliente.consultarCuentaCorrientePorClienteConDetalle((int) oCEPedidoMatriz.getIdCliente(), oCEPedidoMatriz.getIdMoneda());
        if (oCECuentaCorriente != null)
        {
            DlgConsultaCuentaCorrienteCliente oDlgConsultaCuentaCorriente = new DlgConsultaCuentaCorrienteCliente(null, true, oCECuentaCorriente);
            oDlgConsultaCuentaCorriente.setLocationRelativeTo(null);
            oDlgConsultaCuentaCorriente.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "El cliente no tiene cuenta corriente registrada en " + oCEPedidoMatriz.getMoneda());
        }


    }//GEN-LAST:event_BtnCuentaCorrienteActionPerformed

    private void BtnVerDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerDetallesActionPerformed

        DlgDetallePedido oDlgDetallePedido = new DlgDetallePedido(null, true, oCEPedidoMatriz);
        oDlgDetallePedido.setLocationRelativeTo(null);
        oDlgDetallePedido.setVisible(true);
    }//GEN-LAST:event_BtnVerDetallesActionPerformed

    private void TxtAmortizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtAmortizacionKeyReleased
        CalcularVuelto();
    }//GEN-LAST:event_TxtAmortizacionKeyReleased


    private void CalcularVuelto()
    {
        CETipoPago oCEFormaPagoDetalle = (CETipoPago) CbxTipoPago.getSelectedItem();
        if (oCEFormaPagoDetalle != null)
        {
            if (oCEFormaPagoDetalle.getIdTipoPago() == 1)
            {
                if (!TxtAmortizacion.getText().equals(""))
                {
                    float value1 =VerificadorDeTxt.convertFloat(TxtAmortizacion.getText());
                    float valuemontorestante1 = VerificadorDeTxt.convertFloat(TxtMontoRestante.getText());
                    if(value1>valuemontorestante1){
                    TxtVueltoAmor.setText(CLRedondear.Redondear(value1-valuemontorestante1 , 2)+"");
                    TxtEquivalenteAmortizacion.setText(CLRedondear.Redondear(value1 * oCEPedidoMatriz.getTipoCambio(), 2) + "");
                    TxtEquivalenteVuelto.setText(CLRedondear.Redondear(Float.parseFloat(TxtVueltoAmor.getText()) * oCEPedidoMatriz.getTipoCambio(), 2) + "");
                    }else
                    {
                        TxtVueltoAmor.setText(CLRedondear.Redondear(0 , 2)+"");
                        TxtEquivalenteAmortizacion.setText(CLRedondear.Redondear(value1 * oCEPedidoMatriz.getTipoCambio(), 2) + "");
                        TxtEquivalenteVuelto.setText(CLRedondear.Redondear(Float.parseFloat(TxtVueltoAmor.getText()) * oCEPedidoMatriz.getTipoCambio(), 2) + "");
                    }
                }
                else
                {
                    TxtAmortizacion.setText("");
                    TxtVueltoAmor.setText("" + 0);
                    TxtEquivalenteAmortizacion.setText(CLRedondear.Redondear(0 * oCEPedidoMatriz.getTipoCambio(), 2) + "");
                    TxtEquivalenteVuelto.setText(CLRedondear.Redondear(0 * oCEPedidoMatriz.getTipoCambio(), 2) + "");
                }
            }
            if (oCEFormaPagoDetalle.getIdTipoPago() != 1)
            {
                if (!TxtAmortizacion.getText().equals(""))
                {

                    float value1 = VerificadorDeTxt.convertFloat(TxtAmortizacion.getText());
                    TxtVueltoAmor.setText("" );
                    TxtEquivalenteAmortizacion.setText(CLRedondear.Redondear(value1 * oCEPedidoMatriz.getTipoCambio(), 2) + "");
                    TxtEquivalenteVuelto.setText("" + 0);
                }
                else
                {
                    TxtAmortizacion.setText("");
                    TxtVueltoAmor.setText("" + 0);
                    TxtEquivalenteAmortizacion.setText(CLRedondear.Redondear(0 * oCEPedidoMatriz.getTipoCambio(), 2) + "");
                    TxtEquivalenteVuelto.setText(CLRedondear.Redondear(0 * oCEPedidoMatriz.getTipoCambio(), 2) + "");

                }
            }
        }
    }
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed

        CalcularVuelto();
        CETipoPago oCEFormaPagoDetalle = (CETipoPago) CbxTipoPago.getSelectedItem();
        if (oCEFormaPagoDetalle != null)
        {
            if (oCEFormaPagoDetalle.getIdTipoPago() == 1)
            {
                if (contarEfectivos() == 0)
                {
                    float montoPagado = sumarIngresosLiquido();
                    if (montoPagado < oCEPedidoMatriz.getTotalPagar())
                    {
                        if(VerificadorDeTxt.convertFloat(TxtAmortizacion.getText())>0){
                        if (validarCajas(oCEFormaPagoDetalle))
                        {
                            agregarPago();
                            if(Double.parseDouble(TxtMontoRestante.getText())==0)
                            {
                                BtnImprimirComprobante.requestFocus();
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "Debe completar los campos señalados");
                        }
                        }else
                        {
                            JOptionPane.showMessageDialog(this, "El monto no pueder menor o igual a cero");
                            TxtAmortizacion.requestFocus();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "El monto ha sido cubrido");
                        Nuevo();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Solo puede hace un pago en efectivo");
                    Nuevo();
                }
            }
            else
            {
                float montoPagado = sumarIngresosLiquido();
                if (montoPagado <= oCEPedidoMatriz.getTotalPagar())
                {
                    if (validarCajas(oCEFormaPagoDetalle))
                    {
                        agregarPago();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Debe completar los campos señalados");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "El monto ha sido cubrido");
                    Nuevo();
                }
            }
        }

    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void CbxInstitucionFinancieraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxInstitucionFinancieraActionPerformed
        CEInstitucionFinanciera oCEInstitucionFinanciera = (CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem();
        if (oCEInstitucionFinanciera != null)
        {
            cargarCuentaEmpresa(oCEInstitucionFinanciera.getIdInstitucionFinanciera());
        }
    }//GEN-LAST:event_CbxInstitucionFinancieraActionPerformed

    private void CbxTipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoPagoActionPerformed
        CETipoPago oCEFormaPagoDetalle = (CETipoPago) CbxTipoPago.getSelectedItem();
        if (oCEFormaPagoDetalle != null)
        {
            habilitarPanelPorTipoIngreso(oCEFormaPagoDetalle);
        }
    }//GEN-LAST:event_CbxTipoPagoActionPerformed
    public void Nuevo()
    {
        TxtCodigoAutorizacion.setText("");
        TxtNumTipoPago.setText("");
        DtcFechaCheque.setCalendar(null);
        CbxInstitucionFinanciera.setSelectedIndex(0);
        CbxTipoPago.setSelectedIndex(0);
        CbxInstitucionFinanciera.setEnabled(false);
        CbxCuentaEmpresa.setEnabled(false);
        DtcFechaCheque.setEnabled(false);
        TxtCodigoAutorizacion.setEditable(false);
        TxtNumTipoPago.setEditable(false);
        TxtAmortizacion.setText(0 + "");
        TxtEquivalenteAmortizacion.setText(0 + "");
        TxtEquivalenteVuelto.setText(0 + "");
        TxtVueltoAmor.setText(0 + "");
        TxtAmortizacion.setText(0 + "");
        TxtVueltoAmor.requestFocus();
    }

    private void BtnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregar1ActionPerformed
    }//GEN-LAST:event_BtnAgregar1ActionPerformed

    private void BtnAgregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregar2ActionPerformed

//           PrinterJob printJob = PrinterJob.getPrinterJob();
//   // printJob.setPrintable( null );
//    // Diálogo de conción de impresora
//
//    if( printJob.printDialog() ) {
//      try {
//        printJob.print();
//      } catch( Exception e ) {
//        e.printStackTrace();
//        }
//      }
       Nuevo();
    }//GEN-LAST:event_BtnAgregar2ActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        if (TblPagosDetalle.getSelectedRow() != -1)
        {
            ((DefaultTableModel) TblPagosDetalle.getModel()).removeRow(TblPagosDetalle.getSelectedRow());
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Debe elegir una fila");
        }

    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void TxtAmortizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtAmortizacionActionPerformed
        BtnAgregar.requestFocus();
    }//GEN-LAST:event_TxtAmortizacionActionPerformed

    private void TxtAmortizacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtAmortizacionFocusLost
    }//GEN-LAST:event_TxtAmortizacionFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TxtAmortizacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtAmortizacionFocusGained

        TxtAmortizacion.selectAll();
    }//GEN-LAST:event_TxtAmortizacionFocusGained

    private void DtchFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtchFechaPropertyChange

    }//GEN-LAST:event_DtchFechaPropertyChange

    private void chkFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFechaActionPerformed

        if(chkFecha.isSelected())
        {
            DtchFecha.setEnabled(true);
        }
        else 
        {
            DtchFecha.setEnabled(false);
        }
    }//GEN-LAST:event_chkFechaActionPerformed

    private void chkImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkImprimirActionPerformed
        
    }//GEN-LAST:event_chkImprimirActionPerformed
    private void habilitarPanelPorTipoIngreso(CETipoPago oCETipoPago)
    {
        if (oCETipoPago.getIdTipoPago() == 1)
        {
            TxtCodigoAutorizacion.setText("");
            TxtNumTipoPago.setText("");
            DtcFechaCheque.setCalendar(null);
            CbxInstitucionFinanciera.setSelectedIndex(0);
            CbxCuentaEmpresa.setSelectedIndex(0);
            CbxTipoPago.setSelectedIndex(0);
            CbxInstitucionFinanciera.setEnabled(false);
            CbxCuentaEmpresa.setEnabled(false);
            DtcFechaCheque.setEnabled(false);
            TxtCodigoAutorizacion.setEditable(false);
            TxtNumTipoPago.setEditable(false);
            TxtAmortizacion.requestFocus();
            TxtAmortizacion.setBackground(Color.white);
            TxtNumTipoPago.setBackground(Color.white);
            ((JTextField) DtcFechaCheque.getDateEditor()).setBackground(Color.white);
            CbxInstitucionFinanciera.setBackground(Color.white);
            CbxCuentaEmpresa.setBackground(Color.white);
        }
        else
        {
            if (oCETipoPago.getIdTipoPago() == 2)
            {
                TxtCodigoAutorizacion.setText("");
                TxtNumTipoPago.setText("");
                DtcFechaCheque.setCalendar(null);
                CbxInstitucionFinanciera.setSelectedIndex(0);
                CbxInstitucionFinanciera.setEnabled(true);
                CbxCuentaEmpresa.setEnabled(true);
                DtcFechaCheque.setEnabled(false);
                TxtCodigoAutorizacion.setEditable(true);
                TxtNumTipoPago.setEditable(true);
                TxtNumTipoPago.requestFocus();
                TxtAmortizacion.setBackground(Color.white);
                TxtNumTipoPago.setBackground(Color.white);
                ((JTextField) DtcFechaCheque.getDateEditor()).setBackground(Color.white);
                CbxInstitucionFinanciera.setBackground(Color.white);
                CbxCuentaEmpresa.setBackground(Color.white);
            }
            else
            {

                if (oCETipoPago.getIdTipoPago() == 3)
                {
                    TxtCodigoAutorizacion.setText("");
                    TxtNumTipoPago.setText("");
                    DtcFechaCheque.setCalendar(null);
                    CbxInstitucionFinanciera.setSelectedIndex(0);
                    CbxInstitucionFinanciera.setEnabled(false);
                    CbxCuentaEmpresa.setEnabled(false);
                    DtcFechaCheque.setEnabled(true);
                    TxtCodigoAutorizacion.setEditable(false);
                    TxtNumTipoPago.setEditable(true);
                    TxtNumTipoPago.requestFocus();
                    TxtAmortizacion.setBackground(Color.white);
                    TxtNumTipoPago.setBackground(Color.white);
                    ((JTextField) DtcFechaCheque.getDateEditor()).setBackground(Color.white);
                    CbxInstitucionFinanciera.setBackground(Color.white);
                    CbxCuentaEmpresa.setBackground(Color.white);
                }
                else
                {
                    if (oCETipoPago.getIdTipoPago() == 4)
                    {

                        TxtAmortizacion.setBackground(Color.white);
                        TxtNumTipoPago.setBackground(Color.white);
                        ((JTextField) DtcFechaCheque.getDateEditor()).setBackground(Color.white);
                        CbxInstitucionFinanciera.setBackground(Color.white);
                        CbxCuentaEmpresa.setBackground(Color.white);

                        CECuentaCorrienteCliente oCECuentaCorriente = CCCuentaCorrienteCliente.consultarSaldoCuentaCorrientePorCliente((int) oCEPedidoMatriz.getIdCliente(), oCEPedidoMatriz.getIdMoneda());
                        if (oCECuentaCorriente != null)
                        {
                            if (oCECuentaCorriente.getSaldo() > 0)
                            {
                                DlgDescuentoCuentaCorrienteCliente oDlgDescuentoCuentaCorriente = new DlgDescuentoCuentaCorrienteCliente(null, true, oCECuentaCorriente, sumarDescargasCuentasCorrientes(), VerificadorDeTxt.convertFloat(CLRedondear.Redondear(oCEPedidoMatriz.getTotalPagar() - sumarIngresosLiquido(), 2)+""));
                                oDlgDescuentoCuentaCorriente.setLocationRelativeTo(null);
                                oDlgDescuentoCuentaCorriente.setVisible(true);
                                float monto = oDlgDescuentoCuentaCorriente.descargarCuentaCorriente();
                                if (monto > 0)
                                {
                                    SetterGetter oSetterGetter = new SetterGetter();
                                    oSetterGetter.setmIntTipo(1);
                                    CEFormaPagoDetalle oCEFormaPagoDetalle = new CEFormaPagoDetalle();
                                    oCEFormaPagoDetalle.setIdTipoPago(oCETipoPago.getIdTipoPago());
                                    oCEFormaPagoDetalle.setMonto(monto);
                                    oCEFormaPagoDetalle.setVuelto(0);
                                    ConvertidorFecha oConvertidorFecha = new ConvertidorFecha();
                                    if (DtcFechaCheque.getCalendar() != null)
                                    {
                                        oConvertidorFecha.setFecha(DtcFechaCheque.getCalendar());
                                        oCEFormaPagoDetalle.setFechaCheque(oSetterGetter.convertir(oConvertidorFecha.getFechaConvertida()));
                                    }
                                    else
                                    {
                                        oCEFormaPagoDetalle.setFechaCheque(null);
                                    }
                                    oCEFormaPagoDetalle.setIdCuentaEmpresa(((CECuentaEmpresa) CbxCuentaEmpresa.getSelectedItem()).getIdCuentaEmpresa());
                                    oCEFormaPagoDetalle.setIdInstituicionFinanciera(((CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem()).getIdInstitucionFinanciera());
                                    oCEFormaPagoDetalle.setCodigoAutorizacion(TxtCodigoAutorizacion.getText());
                                    oCEFormaPagoDetalle.setFormaDePago(oCETipoPago.getDescripcion());
                                    oCEFormaPagoDetalle.setNumeroPago(TxtNumTipoPago.getText());
                                    oCEFormaPagoDetalle.setTipoCambio(oCEPedidoMatriz.getTipoCambio());
                                    oCEFormaPagoDetalle.setInstitucionFinanciera(((CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem()).getDescripcion());
                                    Vector oVector = new Vector();
                                    oVector.add(TblPagosDetalle.getRowCount() + 1);
                                    oVector.add(oCEFormaPagoDetalle);
                                    oVector.add(oCEPedidoMatriz.getMoneda());
                                    oVector.add(oCEFormaPagoDetalle.getTipoCambio());
                                    oVector.add(oCEFormaPagoDetalle.getMonto());
                                    oVector.add(oCEFormaPagoDetalle.getVuelto());
                                    oVector.add(oCEFormaPagoDetalle.getMonto());
                                    oVector.add(oCEFormaPagoDetalle.getInstitucionFinanciera());
                                    oVector.add(oCEFormaPagoDetalle.getNumeroPago());
                                    oVector.add(oCEFormaPagoDetalle.getFechaCheque());
                                    oVector.add(oCEFormaPagoDetalle.getCodigoAutorizacion());
                                    ((DefaultTableModel) TblPagosDetalle.getModel()).addRow(oVector);
                                    Nuevo();
                                }
                                else
                                {
                                    CbxTipoPago.setSelectedIndex(0);
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(this, "El Saldo del cliente es 0");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "El Saldo del cliente es 0");
                            CbxTipoPago.setSelectedIndex(0);
                        }
                    }
                    else
                    {
                        CbxTipoPago.setSelectedIndex(0);
                    }
                }
            }
        }

    }

    private boolean validarCajas(CETipoPago oCEFormaPagoDetalle)
    {
        boolean evaluar = true;
        if (oCEFormaPagoDetalle.getIdTipoPago() == 2)
        {
            if (TxtCodigoAutorizacion.getText().equals(""))
            {
                evaluar = false;
                TxtAmortizacion.setBackground(new Color(255, 204, 204));
            }
            else
            {
                TxtCodigoAutorizacion.setBackground(Color.white);
            }
            if (TxtNumTipoPago.getText().equals(""))
            {
                evaluar = false;
                TxtNumTipoPago.setBackground(new Color(255, 204, 204));
            }
            else
            {
                TxtNumTipoPago.setBackground(Color.white);
            }
            if (CbxInstitucionFinanciera.getSelectedIndex() == 0)
            {
                evaluar = false;
                CbxInstitucionFinanciera.setBackground(new Color(255, 204, 204));
            }
            else
            {
                CbxInstitucionFinanciera.setBackground(Color.white);
            }
            if (CbxCuentaEmpresa.getSelectedIndex() == 0)
            {
                evaluar = false;
                CbxCuentaEmpresa.setBackground(new Color(255, 204, 204));
            }
            else
            {
                CbxCuentaEmpresa.setBackground(Color.white);
            }
        }
        else
        {
            if (oCEFormaPagoDetalle.getIdTipoPago() == 3)
            {

                if (TxtNumTipoPago.getText().equals(""))
                {
                    evaluar = false;
                    TxtNumTipoPago.setBackground(new Color(255, 204, 204));
                }
                else
                {
                    TxtNumTipoPago.setBackground(Color.white);
                }
                if (CbxInstitucionFinanciera.getSelectedIndex() != 0)
                {
                    evaluar = false;
                    CbxInstitucionFinanciera.setBackground(new Color(255, 204, 204));
                }
                else
                {
                    CbxInstitucionFinanciera.setBackground(Color.white);
                }
                if (DtcFechaCheque.getDate() == null)
                {
                    evaluar = false;
                    ((JTextField) DtcFechaCheque.getDateEditor()).setBackground(new Color(255, 204, 204));
                }
                else
                {
                    ((JTextField) DtcFechaCheque.getDateEditor()).setBackground(Color.white);
                }
            }
        }

        if (TxtEquivalenteAmortizacion.getText().trim().equals(""))
        {
            evaluar = false;
            TxtAmortizacion.setBackground(new Color(255, 204, 204));
        }
        else
        {
            if (oCEFormaPagoDetalle.getIdTipoPago() != 1)
            {
                if (Float.parseFloat(TxtEquivalenteAmortizacion.getText()) <= 0)
                {
                    evaluar = false;
                    TxtAmortizacion.setBackground(new Color(255, 204, 204));
                }
                else
                {
                    TxtAmortizacion.setBackground(Color.white);
                }
            }
        }
        return evaluar;
    }

    private void agregarPago()
    {
        SetterGetter oSetterGetter = new SetterGetter();
        oSetterGetter.setmIntTipo(1);
        CETipoPago oCETipoPago = (CETipoPago) CbxTipoPago.getSelectedItem();
        CEFormaPagoDetalle oCEFormaPagoDetalle = new CEFormaPagoDetalle();
        oCEFormaPagoDetalle.setIdTipoPago(oCETipoPago.getIdTipoPago());
        oCEFormaPagoDetalle.setMonto(VerificadorDeTxt.convertFloat(TxtAmortizacion.getText()));
        oCEFormaPagoDetalle.setVuelto(VerificadorDeTxt.convertFloat(TxtVueltoAmor.getText()));
        oCEFormaPagoDetalle.setIngresoLiquido((float) CLRedondear.Redondear(oCEFormaPagoDetalle.getMonto() - oCEFormaPagoDetalle.getVuelto(), 2));
        ConvertidorFecha oConvertidorFecha = new ConvertidorFecha();
        if (DtcFechaCheque.getCalendar() != null)
        {
            oConvertidorFecha.setFecha(DtcFechaCheque.getCalendar());
            oCEFormaPagoDetalle.setFechaCheque(oSetterGetter.convertir(oConvertidorFecha.getFechaConvertida()));
        }
        else
        {
            oCEFormaPagoDetalle.setFechaCheque(null);
        }
        oCEFormaPagoDetalle.setIdCuentaEmpresa(((CECuentaEmpresa) CbxCuentaEmpresa.getSelectedItem()).getIdCuentaEmpresa());
        oCEFormaPagoDetalle.setIdInstituicionFinanciera(((CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem()).getIdInstitucionFinanciera());
        oCEFormaPagoDetalle.setCodigoAutorizacion(TxtCodigoAutorizacion.getText());
        oCEFormaPagoDetalle.setFormaDePago(oCETipoPago.getDescripcion());
        oCEFormaPagoDetalle.setNumeroPago(TxtNumTipoPago.getText());
        oCEFormaPagoDetalle.setTipoCambio(oCEPedidoMatriz.getTipoCambio());
        oCEFormaPagoDetalle.setInstitucionFinanciera(((CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem()).getDescripcion());
        Vector oVector = new Vector();
        oVector.add(TblPagosDetalle.getRowCount() + 1);
        oVector.add(oCEFormaPagoDetalle);
        oVector.add(oCEPedidoMatriz.getMoneda());
        oVector.add(oCEFormaPagoDetalle.getTipoCambio());
        oVector.add(oCEFormaPagoDetalle.getMonto());
        oVector.add(oCEFormaPagoDetalle.getVuelto());
        oVector.add(oCEFormaPagoDetalle.getIngresoLiquido());
        oVector.add(oCEFormaPagoDetalle.getNumeroPago());
        oVector.add(oCEFormaPagoDetalle.getInstitucionFinanciera());
        oVector.add(oCEFormaPagoDetalle.getFechaCheque());
        oVector.add(oCEFormaPagoDetalle.getCodigoAutorizacion());
        ((DefaultTableModel) TblPagosDetalle.getModel()).addRow(oVector);
        Nuevo();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnAgregar1;
    private javax.swing.JButton BtnAgregar2;
    private javax.swing.JButton BtnCuentaCorriente;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnImprimirComprobante;
    private javax.swing.JButton BtnVerDetalles;
    private javax.swing.JComboBox CbxAmortizaMoneda;
    private javax.swing.JComboBox CbxCuentaEmpresa;
    private javax.swing.JComboBox CbxInstitucionFinanciera;
    private javax.swing.JComboBox CbxTipoPago;
    private javax.swing.JComboBox CbxVueltoMoneda;
    private com.toedter.calendar.JDateChooser DtcFechaCheque;
    private com.toedter.calendar.JDateChooser DtchFecha;
    private javax.swing.JLabel LblCliente;
    private javax.swing.JLabel LblMonedaAmortiza;
    private javax.swing.JLabel LblMonedaMontoRestante;
    private javax.swing.JLabel LblMonedaMontoTotal;
    private javax.swing.JLabel LblMonedaVuelto;
    private javax.swing.JLabel LblMonedaVueltoTotal;
    private javax.swing.JLabel LblNumeroComprobante;
    private javax.swing.JLabel LblSerie;
    private javax.swing.JLabel LblTipoCambioAmortiza;
    private javax.swing.JLabel LblTipoCambioVuelto;
    private javax.swing.JLabel LblTipoComprobante;
    private javax.swing.JTable TblPagosDetalle;
    private javax.swing.JTextField TxtAmortizacion;
    private javax.swing.JTextField TxtCodigoAutorizacion;
    private javax.swing.JTextField TxtEquivalenteAmortizacion;
    private javax.swing.JTextField TxtEquivalenteVuelto;
    private javax.swing.JLabel TxtMontoRestante;
    private javax.swing.JLabel TxtMontoTotal;
    private javax.swing.JTextField TxtNumTipoPago;
    private javax.swing.JLabel TxtTipoCambio;
    private javax.swing.JTextField TxtVueltoAmor;
    private javax.swing.JLabel TxtVueltoTotal;
    private javax.swing.ButtonGroup buttonGroup1;
    private Opcion.CheckBox chkFecha;
    private Opcion.CheckBox chkImprimir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables


 
    private static final String ACTION_IMPRIMIR = "ACTION_IMPRIMIR";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();


        KeyStroke imprimir = KeyStroke.getKeyStroke(KeyEvent.VK_P,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_IMPRIMIR, imprimir, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_IMPRIMIR))
        {
            eventoImprimir();
        }

    }
}
