package view.cmpCompra;

import com.toedter.calendar.JCalendar;
import controller.cmpCompra.CCComprobanteCompra;
import controller.ctbContabilidad.CCCuentaCorrienteProveedor;
import controller.vtaVenta.CCCuentaEmpresa;
import controller.vtaVenta.CCInstituicionFinanciera;
import controller.vtaVenta.CCMoneda;
import controller.vtaVenta.CCTipoIngreso;
import fecha.CLFecha;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import modelo.cmpCompra.entidad.CEComprobanteCompraMatriz;
import modelo.ctbContabilidad.entidad.CECuentaCorrienteProveedor;
import modelo.vtaVenta.entidad.CECuentaEmpresa;
import modelo.cmpCompra.entidad.CEEgresoFinaciero;
import modelo.cmpCompra.entidad.CEEgresoFinancieroDetalle;
import modelo.vtaVenta.entidad.CEInstitucionFinanciera;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CETipoPago;
import util.clases.grlGeneral.CLABM;
import util.clases.grlGeneral.CLConsultarFechaSistema;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.SetterGetter;
import util.clases.grlGeneral.VerificadorDeTxt;
import view.FrmSistemaMenu;
import view.ctbContabilidad.DlgConsultaCuentaCorrienteProveedor;

public class DlgPagosPorCompra extends DialogoPadre
{
    private CEEgresoFinaciero oCEEgresoFinanciero;
    private int colnum=0;
    private int coltipoPago=1;
    private int colmonto=2;
    private int colfecha=3;
    private int colUsuario=4;
    private int colNumPago=5;
    private int colInstitu=6;
    private int colFechaCheq=7;
    private int colCodAutor=8;
    List<CEEgresoFinancieroDetalle> lstEliminarEgresoDet=new ArrayList<CEEgresoFinancieroDetalle>();

    public DlgPagosPorCompra(java.awt.Frame parent, boolean modal,long idComprobanteCompra)
    {
        super(parent, modal);
        initComponents();
        Container con = this.getContentPane();
        con.setBackground(new Color(204, 204, 204));
        TxtAmortizacion.setDocument(new VerificadorDeTxt("Numero", 0, TxtAmortizacion));
        CEComprobanteCompraMatriz oCEComprobanteCompraMatriz =CCComprobanteCompra.ConsultarCompraSinDetallePorId(idComprobanteCompra);
       // DtchFechaPago.setDate(CLConsultarFechaSistema.consultarFecha_Date());
        this.oCEEgresoFinanciero = CCComprobanteCompra.ConsultarEgresoPorId(idComprobanteCompra);
        DtchFechaPago.setDate(oCEEgresoFinanciero.getDate());
        oCEEgresoFinanciero.setSaldoPagar(oCEComprobanteCompraMatriz.getSaldoPagar());
        TxtMontoTotal.setText(CLRedondear.Redondear(oCEComprobanteCompraMatriz.getTotalPagar(),1)+ "0");
        oCEEgresoFinanciero.setMontoTotal((float)CLRedondear.Redondear(oCEComprobanteCompraMatriz.getTotalPagar(),1));
        TxtMontoRestante.setText(CLRedondear.Redondear(oCEComprobanteCompraMatriz.getSaldoPagar(),1)+ "0");
        LblTipoComprobante.setText(oCEComprobanteCompraMatriz.getTipoComprobante());            
        LblNumeroComprobante.setText("Nº "+oCEComprobanteCompraMatriz.getNumComprobante());
        cargarInstituicionFinaciera();
        cargarTipoIngresoFinanciero();
        CbxInstitucionFinanciera.setSelectedIndex(0);
        cargarCuentaEmpresa(((CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem()).getIdInstitucionFinanciera());
        CbxCuentaEmpresa.setSelectedIndex(0);
        cargarTipoMoneda();
        definirMoneda();        
        CbxTipoPago.setSelectedIndex(0);
        TxtAmortizacion.setText(0 + "");
        TxtAmortizacion.selectAll();
        TxtAmortizacion.requestFocus();
        List<CEEgresoFinancieroDetalle> list= oCEEgresoFinanciero.getLstEgresoFinancieroDetalle();

        for (CEEgresoFinancieroDetalle vCEEgresoFinancieroDetalle : list) {

            agregarDetalle(vCEEgresoFinancieroDetalle);
        }
        oCEEgresoFinanciero.setMontoCuentaCorrienteAnte(sumarDescargasCuentasCorrientes());
        eventoNuevo();
        TblPagosDetalle.getModel().addTableModelListener(new TableModelListener()
        {

            public void tableChanged(TableModelEvent e)
            {
                if (e.getType() == e.INSERT || e.getType() == e.DELETE)
                {
                    float pagado = (float) CLRedondear.Redondear(sumarPagos(), 2);
                    TxtMontoRestante.setText(CLRedondear.Redondear(oCEEgresoFinanciero.getMontoTotal() - pagado, 2) + "");
                    if (CLRedondear.Redondear(oCEEgresoFinanciero.getMontoTotal() - pagado, 2) < 0)
                    {
                        TxtMontoRestante.setText(0 + "");
                    }
                }

            }
        });
        LblProveedor.setText(oCEComprobanteCompraMatriz.getProveedor());
        TxtAmortizacion.setText(CLRedondear.Redondear(oCEComprobanteCompraMatriz.getTotalPagar(),1)+ "0");

    }

    private float sumarPagos()
    {
        int size = TblPagosDetalle.getRowCount();
        float montoTotal = 0;
        for (int i = 0; i < size; i++)
        {
            float monto = VerificadorDeTxt.convertFloat(TblPagosDetalle.getValueAt(i, colmonto));
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
                CEEgresoFinancieroDetalle oCEFormaPagoEgresoDetalle = (CEEgresoFinancieroDetalle) TblPagosDetalle.getValueAt(i, coltipoPago);
                if (oCEFormaPagoEgresoDetalle.getIdTipoPago() == 1)
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
            float monto=0;
            CEEgresoFinancieroDetalle oCEFormaPagoEgresoDetalle = (CEEgresoFinancieroDetalle) TblPagosDetalle.getValueAt(i, coltipoPago);
            if (oCEFormaPagoEgresoDetalle.getIdTipoPago() == 4)
            {
                monto = Float.parseFloat(TblPagosDetalle.getValueAt(i, colmonto).toString());
                montoTotal = montoTotal + monto;
            }
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
        jLabel16 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        LblTipoCambioAmortiza = new javax.swing.JLabel();
        TxtAmortizacion = new javax.swing.JTextField();
        CbxAmortizaMoneda = new javax.swing.JComboBox();
        TxtEquivalenteAmortizacion = new javax.swing.JTextField();
        LblMonedaAmortiza = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        BtnCuentaCorriente = new javax.swing.JButton();
        BtnPagarComprobante = new BotonesABM.BtnGuardar();
        btnCancelar1 = new BotonesABM.BtnCancelar();
        jLabel35 = new javax.swing.JLabel();
        LblProveedor = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        LblMonedaMontoTotal = new javax.swing.JLabel();
        LblMonedaMontoRestante = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        TxtMontoTotal = new javax.swing.JLabel();
        TxtMontoRestante = new javax.swing.JLabel();
        TxtTipoCambio = new javax.swing.JLabel();
        LblNumeroComprobante = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        BtnAgregar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        DtchFechaPago =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');

        BtnAgregar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        BtnAgregar1.setText("Agregar");
        BtnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregar1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Pagos");

        LblTipoComprobante.setBackground(new java.awt.Color(255, 255, 255));
        LblTipoComprobante.setFont(new java.awt.Font("Arial", 1, 24));
        LblTipoComprobante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblTipoComprobante.setText("EGRESO POR COMPRA");
        LblTipoComprobante.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        LblTipoComprobante.setOpaque(true);

        TblPagosDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblPagosDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Tipo Pago", "Monto", "Fecha", "Usuario", "Num. Pago", "Inst. Finan.", "Fecha Cheque", "Cod. Autor."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblPagosDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblPagosDetalle.setRowHeight(22);
        TblPagosDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblPagosDetalleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TblPagosDetalle);
        TblPagosDetalle.getColumnModel().getColumn(0).setMinWidth(30);
        TblPagosDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
        TblPagosDetalle.getColumnModel().getColumn(0).setMaxWidth(30);
        TblPagosDetalle.getColumnModel().getColumn(1).setPreferredWidth(120);
        TblPagosDetalle.getColumnModel().getColumn(2).setMinWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(2).setPreferredWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(2).setMaxWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(3).setPreferredWidth(110);
        TblPagosDetalle.getColumnModel().getColumn(5).setMinWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(5).setPreferredWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(5).setMaxWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(6).setMinWidth(100);
        TblPagosDetalle.getColumnModel().getColumn(6).setMaxWidth(100);
        TblPagosDetalle.getColumnModel().getColumn(7).setMinWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(7).setMaxWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(8).setMinWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(8).setPreferredWidth(80);
        TblPagosDetalle.getColumnModel().getColumn(8).setMaxWidth(80);

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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(177, 177, 177))
                    .addComponent(jLabel28)
                    .addComponent(jLabel30)
                    .addComponent(CbxInstitucionFinanciera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CbxTipoPago, 0, 276, Short.MAX_VALUE)
                    .addComponent(CbxCuentaEmpresa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(31, 31, 31))
                    .addComponent(DtcFechaCheque, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(TxtCodigoAutorizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtNumTipoPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
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
                    .addComponent(DtcFechaCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxInstitucionFinanciera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CbxCuentaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtCodigoAutorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel16.setForeground(new java.awt.Color(0, 0, 102));
        jLabel16.setText("Ultima Fecha Pago:");

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel31.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel31.setForeground(new java.awt.Color(0, 0, 51));
        jLabel31.setText("Amortiza:");

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

        LblMonedaAmortiza.setFont(new java.awt.Font("Verdana", 0, 12));
        LblMonedaAmortiza.setText("S/.");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbxAmortizaMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtAmortizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblTipoCambioAmortiza)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtEquivalenteAmortizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblMonedaAmortiza)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtEquivalenteAmortizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblMonedaAmortiza, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(LblTipoCambioAmortiza, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                    .addComponent(CbxAmortizaMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31))
                            .addComponent(TxtAmortizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new java.awt.GridLayout(0, 1, 1, 1));

        BtnCuentaCorriente.setFont(new java.awt.Font("Verdana", 1, 12));
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

        BtnPagarComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPagarComprobanteActionPerformed(evt);
            }
        });
        jPanel6.add(BtnPagarComprobante);

        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnCancelar1);

        jLabel35.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel35.setForeground(new java.awt.Color(153, 0, 0));
        jLabel35.setText("Tipo Cambio:");

        LblProveedor.setFont(new java.awt.Font("Verdana", 0, 12));
        LblProveedor.setText("MANRIQUE LEZAMETA ELIZABETH CRISTINA");

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
        jLabel27.setText("Saldo Total :");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        TxtTipoCambio.setFont(new java.awt.Font("Arial", 1, 14));
        TxtTipoCambio.setText("1.00");

        LblNumeroComprobante.setBackground(new java.awt.Color(255, 255, 255));
        LblNumeroComprobante.setFont(new java.awt.Font("Arial", 1, 18));
        LblNumeroComprobante.setForeground(new java.awt.Color(0, 0, 102));
        LblNumeroComprobante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblNumeroComprobante.setText("003-2126633");
        LblNumeroComprobante.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        LblNumeroComprobante.setOpaque(true);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        BtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Agregar2.png"))); // NOI18N
        BtnAgregar.setText("Agregar");
        BtnAgregar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnAgregar);

        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnEliminar);

        DtchFechaPago.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(LblProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LblTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DtchFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtTipoCambio))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(LblNumeroComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblNumeroComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(LblTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DtchFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblProveedor)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTipoCambio))
                        .addGap(2, 2, 2)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void definirMoneda()
    {
        LblMonedaAmortiza.setText(oCEEgresoFinanciero.getMoneda());
        LblMonedaMontoRestante.setText(oCEEgresoFinanciero.getMoneda());
        LblMonedaMontoTotal.setText(oCEEgresoFinanciero.getMoneda());
        TxtTipoCambio.setText(oCEEgresoFinanciero.getTipoCambio() + "");
        LblTipoCambioAmortiza.setText("x " + oCEEgresoFinanciero.getTipoCambio() + " =");
        for (int i = 0; i < CbxAmortizaMoneda.getItemCount(); i++)
        {
            CEMoneda oCEMoneda = (CEMoneda) CbxAmortizaMoneda.getItemAt(i);
            if (oCEMoneda.getId_moneda() == oCEEgresoFinanciero.getIdMoneda())
            {
                CbxAmortizaMoneda.setSelectedIndex(i);
            }

        }
    }

    private void cargarTipoIngresoFinanciero()
    {
        List<CETipoPago> oListTipoIngreso = CCTipoIngreso.consultarTipoEgresoFinaciero();
        construirModeloCombo(CbxTipoPago, (ArrayList) oListTipoIngreso);
    }

    private void cargarInstituicionFinaciera()
    {
        List<CEInstitucionFinanciera> oListTipoIngreso = CCInstituicionFinanciera.consultarInstituicionFinanciera();
        construirModeloCombo(CbxInstitucionFinanciera, (ArrayList) oListTipoIngreso);
    }

    private void cargarCuentaEmpresa(int IdInstituicionFinanciera)
    {
        List<CECuentaEmpresa> oListTipoIngreso = CCCuentaEmpresa.consultarCuentaEmpresaPorInstitucionFinancieraMoneda(IdInstituicionFinanciera, oCEEgresoFinanciero.getIdMoneda());
        construirModeloCombo(CbxCuentaEmpresa, (ArrayList) oListTipoIngreso);
    }

    private void cargarTipoMoneda()
    {
        List<CEMoneda> oListTipoIngreso = CCMoneda.listaAll();
        construirModeloCombo(CbxAmortizaMoneda, (ArrayList) oListTipoIngreso);
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

    private boolean validar()
    {


        if (sumarPagos()<=0) {
           // JOptionPane.showMessageDialog(this, "Debe Agregar al menos un registro");
          // return false;
        }
        int resul=JOptionPane.showConfirmDialog(this,"<html><h2>ESTÁ SEGURO DE GUARDAR EL PAGO</h2></html>" , "",JOptionPane.YES_NO_OPTION);
        if (resul==JOptionPane.NO_OPTION) {
                   return false;
        }

        if(oCEEgresoFinanciero.getUltimoIdEstado()==12){
            int diasDif=CLConsultarFechaSistema.verificarDiferenciaFecha(oCEEgresoFinanciero.getFecha());
            if(diasDif>7)
            {
                JOptionPane.showMessageDialog(this, "Han Pasado "+diasDif+", No se Puede Guardar");
                return false;
            }
        }


        CEEgresoFinaciero oCEEgresoFinancieroAux = CCComprobanteCompra.ConsultarEgresoPorId(oCEEgresoFinanciero.getIdComprobanteCompra());
        if(!oCEEgresoFinancieroAux.getFecha().equals(oCEEgresoFinanciero.getFecha()))
        {
            JOptionPane.showMessageDialog(this, "Se ha actulizado el pago desde otro registro,vuelva a intentarlo");
            this.dispose();
            return false;

        }

        return true;
    }

    private void BtnCuentaCorrienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCuentaCorrienteActionPerformed
        CECuentaCorrienteProveedor oCECuentaCorriente = CCCuentaCorrienteProveedor.consultarCuentaCorrientePorProveedorConDetalle((int) oCEEgresoFinanciero.getIdProveedor(), oCEEgresoFinanciero.getIdMoneda());
        if (oCECuentaCorriente != null)
        {
            DlgConsultaCuentaCorrienteProveedor oDlgConsultaCuentaCorriente = new DlgConsultaCuentaCorrienteProveedor(null, true, oCECuentaCorriente);
            oDlgConsultaCuentaCorriente.setLocationRelativeTo(null);
            oDlgConsultaCuentaCorriente.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "El Proveedor no tiene cuenta corriente registrada en " + oCEEgresoFinanciero.getMoneda());
        }


    }//GEN-LAST:event_BtnCuentaCorrienteActionPerformed

    private void TxtAmortizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtAmortizacionKeyReleased
        caluclarVuelto();
    }//GEN-LAST:event_TxtAmortizacionKeyReleased


    private void caluclarVuelto()
    {
        CETipoPago oCEFormaPagoEgresoDetalle = (CETipoPago) CbxTipoPago.getSelectedItem();
        if (oCEFormaPagoEgresoDetalle != null)
        {
            if (oCEFormaPagoEgresoDetalle.getIdTipoPago() == 1)
            {
                if (!TxtAmortizacion.getText().equals(""))
                {
                    float value1 = VerificadorDeTxt.convertFloat(TxtAmortizacion.getText());
                    TxtEquivalenteAmortizacion.setText(CLRedondear.Redondear(value1 * oCEEgresoFinanciero.getTipoCambio(), 2) + "");
                }
                else
                {
                    TxtAmortizacion.setText("");
                    TxtEquivalenteAmortizacion.setText(CLRedondear.Redondear(0 * oCEEgresoFinanciero.getTipoCambio(), 2) + "");

                }
            }
            if (oCEFormaPagoEgresoDetalle.getIdTipoPago() != 1)
            {
                if (!TxtAmortizacion.getText().equals(""))
                {

                    float value1 = Float.parseFloat(!TxtAmortizacion.getText().equals("") ? TxtAmortizacion.getText() : 0 + "");
                    TxtEquivalenteAmortizacion.setText(CLRedondear.Redondear(value1 * oCEEgresoFinanciero.getTipoCambio(), 2) + "");
                }
                else
                {
                    TxtAmortizacion.setText("");
                    TxtEquivalenteAmortizacion.setText(CLRedondear.Redondear(0 * oCEEgresoFinanciero.getTipoCambio(), 2) + "");

                }
            }
        }
    }

    private void CbxInstitucionFinancieraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxInstitucionFinancieraActionPerformed
        CEInstitucionFinanciera oCEInstitucionFinanciera = (CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem();
        if (oCEInstitucionFinanciera != null)
        {
            cargarCuentaEmpresa(oCEInstitucionFinanciera.getIdInstitucionFinanciera());
        }
    }//GEN-LAST:event_CbxInstitucionFinancieraActionPerformed

    private void CbxTipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoPagoActionPerformed
        CETipoPago oCEFormaPagoEgresoDetalle = (CETipoPago) CbxTipoPago.getSelectedItem();
        if (oCEFormaPagoEgresoDetalle != null)
        {
            habilitarPanelPorTipoIngreso(oCEFormaPagoEgresoDetalle);
        }
    }//GEN-LAST:event_CbxTipoPagoActionPerformed
   

    private void BtnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregar1ActionPerformed
    }//GEN-LAST:event_BtnAgregar1ActionPerformed

    private void TxtAmortizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtAmortizacionActionPerformed
        BtnAgregar.requestFocus();
    }//GEN-LAST:event_TxtAmortizacionActionPerformed


    private void TxtAmortizacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtAmortizacionFocusLost
    }//GEN-LAST:event_TxtAmortizacionFocusLost

    private boolean validarPago()
    {
        CETipoPago vCETipoPago = (CETipoPago) CbxTipoPago.getSelectedItem();
        if(vCETipoPago!=null){
//            for(int i=0;i<TblPagosDetalle.getRowCount();i++)
//            {
//                CEEgresoFinancieroDetalle vCEFormaPagoEgresoDetalle = (CEEgresoFinancieroDetalle) TblPagosDetalle.getValueAt(i, coltipoPago);
//                if(vCEFormaPagoEgresoDetalle.getIdTipoPago()==vCETipoPago.getIdTipoPago())
//                {
//                    JOptionPane.showMessageDialog(null, "El tipo Pago: "+vCETipoPago.getDescripcion()+", ya está agregado");
//                    return false;
//                }
//            }
        }
        if(VerificadorDeTxt.convertFloat(TxtAmortizacion.getText())>VerificadorDeTxt.convertFloat(TxtMontoRestante  .getText()))
        {
            JOptionPane.showMessageDialog(null, "La amortización no puede ser mayor al MONTO A RESTANTE ");
            TxtAmortizacion.requestFocus();
            return false;
        }

        return true;
    }
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed

        caluclarVuelto();
        if(VerificadorDeTxt.convertFloat(TxtAmortizacion.getText())!=0){
        if(validarPago()){
        CETipoPago vCETipoPago = (CETipoPago) CbxTipoPago.getSelectedItem();
            if (vCETipoPago.getIdTipoPago() == 1) {
                    float montoPagado = sumarPagos();
                    if (montoPagado < oCEEgresoFinanciero.getMontoTotal()) {
                        if (validarCajas(vCETipoPago)) {
                            agregarPago();
                            eventoNuevo();
                        } else {
                            JOptionPane.showMessageDialog(null, "Debe completar los campos señalados");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El monto ha sido cubrido");
                        eventoNuevo();
                    }

            } else {
                float montoPagado = sumarPagos();
                if (montoPagado <= oCEEgresoFinanciero.getMontoTotal()) {
                    if (validarCajas(vCETipoPago)) {
                        agregarPago();
                    } else {
                            JOptionPane.showMessageDialog(null, "Debe completar los campos señalados");
                        }
                } else {
                    JOptionPane.showMessageDialog(null, "El monto ha sido cubrido");
                    eventoNuevo();
                    }
                }
            if(Double.parseDouble(TxtMontoRestante.getText())==0) {
                                    BtnPagarComprobante.requestFocus();
              }
        }
      }
 else
        {
            JOptionPane.showMessageDialog(null, "la amortizacion debe ser mayor a cero");
            TxtAmortizacion.requestFocus();
 }

    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void TxtAmortizacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtAmortizacionFocusGained

        TxtAmortizacion.selectAll();
    }//GEN-LAST:event_TxtAmortizacionFocusGained

    private CETipoPago oCETipoPago=null;

    private int accion=CLABM.NUEVO;

    private void eventoNuevo()
    {
        TblPagosDetalle.clearSelection();
        accion=CLABM.NUEVO;
        habilitarBotones(false, true, false, false, true);
        CbxTipoPago.setSelectedIndex(0);
        habilitarPanelPorTipoIngreso((CETipoPago)CbxTipoPago.getSelectedItem());
        TxtAmortizacion.setText("0");
        TxtAmortizacion.setEnabled(true);
        CbxTipoPago.setEnabled(true);
        BtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Agregar2.png"))); // NOI18N
        BtnAgregar.setText("Agregar");
    }

   
    private void BtnPagarComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPagarComprobanteActionPerformed

        if(validar()){
            
            oCEEgresoFinanciero.setIdSucursal(FrmSistemaMenu.getIdSucursal());
            oCEEgresoFinanciero.setIdTipoComprobanteCompra(oCEEgresoFinanciero.getIdTipoComprobanteCompra());
            oCEEgresoFinanciero.setFecha(null);
            oCEEgresoFinanciero.setIGVActual(oCEEgresoFinanciero.getIGVActual());;
            oCEEgresoFinanciero.setEgresoTotalLiquido((float) CLRedondear.Redondear(sumarPagos()-sumarDescargasCuentasCorrientes(), 2));
            oCEEgresoFinanciero.setMontoCuentaCorriente((float) CLRedondear.Redondear(sumarDescargasCuentasCorrientes(), 2));
            oCEEgresoFinanciero.setMontoPago(sumarPagos());
            oCEEgresoFinanciero.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
            oCEEgresoFinanciero.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
            oCEEgresoFinanciero.setIdPuntoVenta(1);
            oCEEgresoFinanciero.setSaldoPagar(Float.parseFloat(TxtMontoRestante.getText()));
             ConvertidorFecha oConvertidorFecha = new ConvertidorFecha();
                if (DtchFechaPago.getCalendar() != null)
                {
                    oConvertidorFecha.setFecha(DtchFechaPago.getCalendar());
                    oCEEgresoFinanciero.setFecha(oConvertidorFecha.getFechaConvertida());
                }
            List<CEEgresoFinancieroDetalle> oLstIngresoFinancieroDetalle = new ArrayList<CEEgresoFinancieroDetalle>();
            oLstIngresoFinancieroDetalle.addAll(lstEliminarEgresoDet);
            for (int i = 0; i < TblPagosDetalle.getRowCount(); i++)
            {
                CEEgresoFinancieroDetalle CEFormaPagoEgresoDetalle = (CEEgresoFinancieroDetalle) TblPagosDetalle.getValueAt(i, coltipoPago);
                oLstIngresoFinancieroDetalle.add(CEFormaPagoEgresoDetalle);
            }
            oCEEgresoFinanciero.setLstEgresoFinancieroDetalle(oLstIngresoFinancieroDetalle);
            if (CCComprobanteCompra.pagarCompra(oCEEgresoFinanciero))
            {
                JOptionPane.showMessageDialog(null, "Se Guardo con exito");
                dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No se pudo completar la operaciÃ³n");
            }


      }

    }//GEN-LAST:event_BtnPagarComprobanteActionPerformed

    private void habilitarBotones(boolean bolnuevo,boolean bolagregar,boolean boleditar,boolean boleliminar,boolean bolcancelar)
    {
        BtnAgregar.setEnabled(bolagregar);
        BtnEliminar.setEnabled(bolagregar);
        
    }
    private  void habilitarControles(boolean bol)
    {
            TxtAmortizacion.setEnabled(bol);
            CbxInstitucionFinanciera.setEnabled(bol);
            CbxCuentaEmpresa.setEnabled(bol);
            CbxTipoPago.setEnabled(bol);
            DtcFechaCheque.setEnabled(bol);
            TxtCodigoAutorizacion.setEditable(bol);
            TxtNumTipoPago.setEditable(bol);
            TxtAmortizacion.requestFocus();
            TxtAmortizacion.setBackground(Color.white);
            TxtNumTipoPago.setBackground(Color.white);
            ((JTextField) DtcFechaCheque.getDateEditor()).setBackground(Color.white);
            CbxInstitucionFinanciera.setBackground(Color.white);
            CbxCuentaEmpresa.setBackground(Color.white);
    }


    private long IdFrmPagoDet=0;
   
    private void TblPagosDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblPagosDetalleMouseClicked

        

    }//GEN-LAST:event_TblPagosDetalleMouseClicked

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        
        int rowsel=TblPagosDetalle.getSelectedRow();
        if (rowsel != -1) {

            CEEgresoFinancieroDetalle oCEFormaPagoEgresoDet =(CEEgresoFinancieroDetalle) TblPagosDetalle.getValueAt(rowsel, coltipoPago);
            if(oCEFormaPagoEgresoDet.getIdEgresoFinancieroDetalle()!=0)
            {
            oCEFormaPagoEgresoDet.setIdCuentaEmpresa(-1);
            lstEliminarEgresoDet.add(oCEFormaPagoEgresoDet);
            }

            ((DefaultTableModel) TblPagosDetalle.getModel()).removeRow(rowsel);
        } else {
            JOptionPane.showMessageDialog(null, "Debe elegir una fila");
        }
}//GEN-LAST:event_BtnEliminarActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed
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

                        CECuentaCorrienteProveedor oCECuentaCorriente = CCCuentaCorrienteProveedor.consultarSaldoCuentaCorrientePorProveedor((int) oCEEgresoFinanciero.getIdProveedor(), oCEEgresoFinanciero.getIdMoneda());
                        if (oCECuentaCorriente != null)
                        {
                            if (oCECuentaCorriente.getSaldo() > 0)
                            {
                                DlgDescuentoCuentaCorrienteProveedor oDlgDescuentoCuentaCorriente = new DlgDescuentoCuentaCorrienteProveedor(null, true, oCECuentaCorriente, sumarDescargasCuentasCorrientes(), (float) CLRedondear.Redondear(oCEEgresoFinanciero.getMontoTotal() - sumarPagos(), 2));
                                oDlgDescuentoCuentaCorriente.setLocationRelativeTo(null);
                                oDlgDescuentoCuentaCorriente.setVisible(true);
                                if(validarPago()){
                                    float monto = oDlgDescuentoCuentaCorriente.descargarCuentaCorriente();
                                    if (monto > 0)
                                    {
                                        SetterGetter oSetterGetter = new SetterGetter();
                                        oSetterGetter.setmIntTipo(1);
                                        CEEgresoFinancieroDetalle oCEFormaPagoEgresoDetalle = new CEEgresoFinancieroDetalle();
                                        oCEFormaPagoEgresoDetalle.setIdTipoPago(oCETipoPago.getIdTipoPago());
                                        oCEFormaPagoEgresoDetalle.setEgresoLiquido(monto);
                                        ConvertidorFecha oConvertidorFecha = new ConvertidorFecha();
                                        if (DtcFechaCheque.getCalendar() != null)
                                        {
                                            oConvertidorFecha.setFecha(DtcFechaCheque.getCalendar());
                                            oCEFormaPagoEgresoDetalle.setFechaCheque(oSetterGetter.convertir(oConvertidorFecha.getFechaConvertida()));
                                        }
                                        else
                                        {
                                            oCEFormaPagoEgresoDetalle.setFechaCheque(null);
                                        }

                                        oCEFormaPagoEgresoDetalle.setIdCuentaEmpresa(((CECuentaEmpresa) CbxCuentaEmpresa.getSelectedItem()).getIdCuentaEmpresa());
                                        oCEFormaPagoEgresoDetalle.setIdInstituicionFinanciera(((CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem()).getIdInstitucionFinanciera());
                                        oCEFormaPagoEgresoDetalle.setCodigoAutorizacion(TxtCodigoAutorizacion.getText());
                                        oCEFormaPagoEgresoDetalle.setTipoPago(oCETipoPago.getDescripcion());
                                        oCEFormaPagoEgresoDetalle.setNumeroPago(TxtNumTipoPago.getText());
                                        oCEFormaPagoEgresoDetalle.setTipoCambio(oCEEgresoFinanciero.getTipoCambio());
                                        oCEFormaPagoEgresoDetalle.setInstitucionFinanciera(((CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem()).getDescripcion());
                                        agregarDetalle(oCEFormaPagoEgresoDetalle);
                                        eventoNuevo();
                                    }else{CbxTipoPago.setSelectedIndex(0);}
                                }
                                else
                                {
                                    CbxTipoPago.setSelectedIndex(0);
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "El Saldo del Proveedor es 0");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "El Saldo del Proveedor es 0");
                            CbxTipoPago.setSelectedIndex(0);
                        }
                    }
//                    else
//                    {
//                        CbxTipoPago.setSelectedIndex(0);
//                    }
                }
            }
        }

    }

    private boolean validarCajas(CETipoPago oCEFormaPagoEgresoDetalle)
    {
        boolean evaluar = true;
        if (oCEFormaPagoEgresoDetalle.getIdTipoPago() == 2)
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
            if (oCEFormaPagoEgresoDetalle.getIdTipoPago() == 3)
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

        if (TxtEquivalenteAmortizacion.getText().equals(""))
        {
            evaluar = false;
            TxtAmortizacion.setBackground(new Color(255, 204, 204));
        }
        else
        {
            if (oCEFormaPagoEgresoDetalle.getIdTipoPago() != 1)
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
        CETipoPago vCETipoPago = (CETipoPago) CbxTipoPago.getSelectedItem();

        CEEgresoFinancieroDetalle oCEEgresoFinancieroDetalle = new CEEgresoFinancieroDetalle();
        oCEEgresoFinancieroDetalle.setIdTipoPago(vCETipoPago.getIdTipoPago());
        oCEEgresoFinancieroDetalle.setIdEgresoFinancieroDetalle(0);
        oCEEgresoFinancieroDetalle.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCEEgresoFinancieroDetalle.setUsuario(FrmSistemaMenu.oCEUsuario.getUsuario());
        oCEEgresoFinancieroDetalle.setEgresoLiquido(VerificadorDeTxt.convertFloat(TxtAmortizacion.getText()));
        ConvertidorFecha oConvertidorFecha = new ConvertidorFecha();
        if (DtcFechaCheque.getCalendar() != null)
        {
            oConvertidorFecha.setFecha(DtcFechaCheque.getCalendar());
            oCEEgresoFinancieroDetalle.setFechaCheque(oSetterGetter.convertir(oConvertidorFecha.getFechaConvertida()));
        }
        else
        {
            oCEEgresoFinancieroDetalle.setFechaCheque(null);
        }
        oCEEgresoFinancieroDetalle.setIdCuentaEmpresa(((CECuentaEmpresa) CbxCuentaEmpresa.getSelectedItem()).getIdCuentaEmpresa());
        oCEEgresoFinancieroDetalle.setIdInstituicionFinanciera(((CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem()).getIdInstitucionFinanciera());
        oCEEgresoFinancieroDetalle.setCodigoAutorizacion(TxtCodigoAutorizacion.getText());
        oCEEgresoFinancieroDetalle.setTipoPago(vCETipoPago.getDescripcion());
        oCEEgresoFinancieroDetalle.setNumeroPago(TxtNumTipoPago.getText());
        oCEEgresoFinancieroDetalle.setTipoCambio(oCEEgresoFinanciero.getTipoCambio());
        oCEEgresoFinancieroDetalle.setInstitucionFinanciera(((CEInstitucionFinanciera) CbxInstitucionFinanciera.getSelectedItem()).getDescripcion());
        oCEEgresoFinancieroDetalle.setIdEgresoFinancieroDetalle(IdFrmPagoDet);

        agregarDetalle(oCEEgresoFinancieroDetalle);
        eventoNuevo();
        
    }
    private void agregarDetalle(CEEgresoFinancieroDetalle oCEEgresoEgresoDetalle)
    {
        Vector oVector = new Vector();
        oVector.add(TblPagosDetalle.getRowCount() + 1);
        oVector.add(oCEEgresoEgresoDetalle);
        oVector.add(oCEEgresoEgresoDetalle.getEgresoLiquido());
        oVector.add(oCEEgresoEgresoDetalle.getFecha());
        oVector.add(oCEEgresoEgresoDetalle.getUsuario());
        oVector.add(oCEEgresoEgresoDetalle.getNumeroPago());
        oVector.add(oCEEgresoEgresoDetalle.getInstitucionFinanciera());
        oVector.add(oCEEgresoEgresoDetalle.getFechaCheque());
        oVector.add(oCEEgresoEgresoDetalle.getCodigoAutorizacion());
        ((DefaultTableModel) TblPagosDetalle.getModel()).addRow(oVector);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnAgregar1;
    private javax.swing.JButton BtnCuentaCorriente;
    private javax.swing.JButton BtnEliminar;
    private BotonesABM.BtnGuardar BtnPagarComprobante;
    private javax.swing.JComboBox CbxAmortizaMoneda;
    private javax.swing.JComboBox CbxCuentaEmpresa;
    private javax.swing.JComboBox CbxInstitucionFinanciera;
    private javax.swing.JComboBox CbxTipoPago;
    private com.toedter.calendar.JDateChooser DtcFechaCheque;
    private com.toedter.calendar.JDateChooser DtchFechaPago;
    private javax.swing.JLabel LblMonedaAmortiza;
    private javax.swing.JLabel LblMonedaMontoRestante;
    private javax.swing.JLabel LblMonedaMontoTotal;
    private javax.swing.JLabel LblNumeroComprobante;
    private javax.swing.JLabel LblProveedor;
    private javax.swing.JLabel LblTipoCambioAmortiza;
    private javax.swing.JLabel LblTipoComprobante;
    private javax.swing.JTable TblPagosDetalle;
    private javax.swing.JTextField TxtAmortizacion;
    private javax.swing.JTextField TxtCodigoAutorizacion;
    private javax.swing.JTextField TxtEquivalenteAmortizacion;
    private javax.swing.JLabel TxtMontoRestante;
    private javax.swing.JLabel TxtMontoTotal;
    private javax.swing.JTextField TxtNumTipoPago;
    private javax.swing.JLabel TxtTipoCambio;
    private BotonesABM.BtnCancelar btnCancelar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

