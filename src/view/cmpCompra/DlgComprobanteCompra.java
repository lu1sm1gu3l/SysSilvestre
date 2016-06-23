package view.cmpCompra;

import controller.almAlmacen.CCGuiaRemisionRecibido;
import controller.cmpCompra.CCComprobanteCompra;
import controller.cmpCompra.CCOrdenCompra;
import controller.grlGeneral.CCEstado;
import controller.vtaVenta.CCCondicion;
import controller.vtaVenta.CCMoneda;
import controller.vtaVenta.CCTipoComprobante;
import controller.vtaVenta.CCTipoDescuento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.almAlmacen.entidad.CEEquivalenciaUnidad;
import modelo.almAlmacen.entidad.CEGuiaRemisionRecibido;
import modelo.almAlmacen.entidad.CEGuiaRemisionRecibidoDetalle;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.cmpCompra.entidad.CEComprobanteCompraDetalle;
import modelo.cmpCompra.entidad.CEComprobanteCompraMatriz;
import modelo.cmpCompra.entidad.CEOrdenCompraDetalle;
import modelo.cmpCompra.entidad.CEOrdenCompraMatriz;
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CECondicion;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CETipoComprobante;
import modelo.vtaVenta.entidad.CETipoDescuento;
import util.clases.almAlmacen.JTableX;
import util.clases.cmpCompra.CLComprobanteCompra;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.MiReloj;
import util.clases.grlGeneral.VerificadorDeTxt;
import util.clases.vtaVenta.CLCargarCombo;
import view.FrmSistemaMenu;
import view.almAlmacen.DlgBusquedaProductoCompra;
import view.almAlmacen.invInventarios.DlgBusquedaGuiaRemisionRecibido;
import view.almAlmacen.invInventarios.DlgIngresoProducto1;
import view.cmrComercial.DlgGestionProveedor;

/**
 *
 * @author Luiggi
 */
public class DlgComprobanteCompra extends javax.swing.JDialog implements ActionListener {

    private int idProveedor = 0;
    private long IdComprobanteCompra = 0;
    private int IdEstado = 0;
    private int IdEstadoInv = 0;
    private int IdGuiaRemision=0;
    private MiReloj hilo;
    private CLComprobanteCompra oCLComprobanteCompra;
    private int pAccionABM = 0;
    CLBotonesABM oclBotonesABM;
    private  List<CEComprobanteCompraDetalle> lstComprasEliminados = new ArrayList<CEComprobanteCompraDetalle>();
    private java.awt.Frame oparent;

    /** Creates new form DlgGestionComprobanteCompra */
    public DlgComprobanteCompra(java.awt.Frame parent, boolean modal) {
        super(parent, false);
        initComponents();
        this.oparent=parent;
        this.setLocationRelativeTo(null);
        TxtTipoCambio.setText("1.0");
        CbxUnidadMedida.setEditable(true);
        jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero", 0, jTextFieldNumber));
        TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
        TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
        cargarUtilidades();
        TblComprobanteCompraDetalle.setSurrendersFocusOnKeystroke(true);
        DtchFecha.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
    }

    private void cargarUtilidades() {
        CbxTipoDescuentoComprobanteCompra.setModel(CLComboBox.cargarCombo(CCTipoDescuento.listaAll()));
        oCLComprobanteCompra = new CLComprobanteCompra(TblComprobanteCompraDetalle, LblSubtotalNeto, LblIgv,
                LblISC, LblMontoTotal, LblMontoTotalPagar, LblSubTotalNetoSinIgv, LblDescuentoValores,
                LblSubtotalBruto, LblDescuentoSubtotal, LblDescuentoTotal, TxtDescuento, CbxTipoDescuentoComprobanteCompra,lblPercepcion,ChkPercepcion);
        CbxTipoComprobante.setModel(CLComboBox.cargarCombo(CCTipoComprobante.listaAll()));
        CbxCondicion.setModel(CLComboBox.cargarCombo(CCCondicion.listaAll()));
        CbxMoneda.setModel(CLComboBox.cargarCombo(CCMoneda.listarMonedaConTipoCambio()));
        CbxTipoDescuento.setModel(CLComboBox.cargarCombo(CCTipoDescuento.listaAll()));
        CbxTipoComprobante.setSelectedIndex(1);
        TblComprobanteCompraDetalle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        CbxTipoDescuento.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jTextFieldNumber.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TableColumn columnaTxtProducto = TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colProducto);
        columnaTxtProducto.setCellEditor(new DefaultCellEditor(TxtProducto));
        TableColumn columnaTxtTipoDescuento = TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colTipoDescuento);
        columnaTxtTipoDescuento.setCellEditor(new DefaultCellEditor(CbxTipoDescuento));
        TableColumn columnaUnidadMedida = TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colUnidadMedida);
        columnaUnidadMedida.setCellEditor(new DefaultCellEditor(CbxUnidadMedida));
        DefaultCellEditor oDefaultCellEditor = new DefaultCellEditor(CbxUnidadMedida);
        columnaUnidadMedida.setCellEditor(oDefaultCellEditor);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colCantidad).setCellRenderer(tcr);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colPrecioConDesc).setCellRenderer(tcr);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colPrecioConIGV).setCellRenderer(tcr);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colImporConDesc).setCellRenderer(tcr);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colImporSinDesc).setCellRenderer(tcr);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colImporSinDescConIgv).setCellRenderer(tcr);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colExonerado).setCellRenderer(tcr);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colDescuento).setCellRenderer(tcr);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colDestValores).setCellRenderer(tcr);


        TableColumn columnaCantidad = TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colCantidad);
        columnaCantidad.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaImportbr = TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colImporSinDescConIgv);
        columnaImportbr.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaImportbrConIgv = TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colImporSinDesc);
        columnaImportbrConIgv.setCellEditor(new DefaultCellEditor(jTextFieldNumber));

        TableColumn columnaPrecioConIgv = TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colPrecioConIGV);
        columnaPrecioConIgv.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaDescuento = TblComprobanteCompraDetalle.getColumnModel().getColumn(oCLComprobanteCompra.colDescuento);
        columnaDescuento.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        hilo = new MiReloj(LblHoraSistema);
        hilo.start();
        CbxTipoDescuento.setSelectedIndex(0);
        CETipoDescuento oTipoDescuento = (CETipoDescuento) CbxTipoDescuento.getSelectedItem();
        TblComprobanteCompraDetalle.setValueAt(oTipoDescuento, 0, oCLComprobanteCompra.colTipoDescuento);
        TblComprobanteCompraDetalle.requestFocus();
        TblComprobanteCompraDetalle.changeSelection(0, oCLComprobanteCompra.colProducto, false, false);
        oclBotonesABM = new CLBotonesABM();
        oclBotonesABM.setBotones(btnNuevo, btnGuardar, btnRecepcion, btnEditar, btnCancelar, btnRecepcion,this);
        oclBotonesABM.controlBoton(false, true, false, false, true, false);
        pAccionABM = 1;
        ((JTableX) TblComprobanteCompraDetalle).setSelectAllForEdit(true);


    }
    List<ArrayList<CEEquivalenciaUnidad>> LstLstEquivalencia = new ArrayList<ArrayList<CEEquivalenciaUnidad>>();

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CbxTipoDescuento = new ComboBox.ComboBox();
        CbxUnidadMedida = new javax.swing.JComboBox();
        jTextFieldNumber = new javax.swing.JTextField();
        TxtProducto = new TextField.JTxtNinguno();
        PnlDatos2 = new javax.swing.JPanel();
        LblEtiqProveedor = new Label.Label();
        LblEtiqSucursal = new Label.Label();
        TxtVendedor = new Label.Label();
        LblEtiqCondicion = new Label.Label();
        CbxCondicion = new ComboBox.ComboBox();
        CbxMoneda = new ComboBox.ComboBox();
        LblEtiqMoneda = new Label.Label();
        LblEtiqTipoCambio = new Label.Label();
        LblEtiqRuc = new Label.Label();
        LblEtiqDireccion = new Label.Label();
        TxtProveedor = new TextField.JTxtLetra();
        LblUltimaFecha = new Label.Label();
        LblEtiqUltimaFEcha = new Label.Label();
        LblEtiqUsuario = new Label.Label();
        TxtSucursal = new Label.Label();
        TxtTipoCambio = new Label.Label();
        TxtDireccion = new Label.Label();
        TxtDNI = new Label.Label();
        jPanel1 = new javax.swing.JPanel();
        LblEtiqMontoTotal = new Label.Label();
        LblMontoTotal = new Label.Label();
        LblIgv = new Label.Label();
        LblEtiqIGV = new Label.Label();
        LblISC = new Label.Label();
        LblEtiqISC = new Label.Label();
        LblPorcentaje = new Label.Label();
        LblEtiqSubNTSinIvg = new Label.Label();
        LblSubTotalNetoSinIgv = new Label.Label();
        LblEtiqTipoDescuento = new Label.Label();
        CbxTipoDescuentoComprobanteCompra = new ComboBox.ComboBox();
        LblDescuentoValores = new Label.Label();
        LblEtiqDescValores = new Label.Label();
        LblEtiqDescuento = new Label.Label();
        LblEtiqPercepcion = new Label.Label();
        LblDescuentoTotal = new Label.Label();
        btnRecepcion = new BotonesABM.BtnEliminar();
        btnEditar = new BotonesABM.BtnEditar();
        btnGuardar = new BotonesABM.BtnGuardar();
        btnNuevo = new BotonesABM.BtnNuevo();
        LblEtiqTotalPagar = new Label.Label();
        LblMontoTotalPagar = new Label.Label();
        TxtDescuento = new javax.swing.JTextField();
        btnCancelar = new BotonesABM.BtnCancelar();
        btnExportar1 = new BotonesABM.BtnExportar();
        LblEtiqDescTotal = new Label.Label();
        lblPercepcion = new Label.Label();
        btnAnular1 = new BotonesABM.BtnEliminar();
        jPanel3 = new javax.swing.JPanel();
        CbxTipoComprobante = new ComboBox.ComboBox();
        LblEtiqComprob = new Label.Label();
        LblEtiqNum = new Label.Label();
        TxtNumeroComprob1 = new TextField.JTxtNinguno();
        btnBuscarCompComp = new BotonesABM.BtnBuscar();
        btnImportarOC = new javax.swing.JButton();
        btnImportarGR = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblComprobanteCompraDetalle = new util.clases.almAlmacen.JTableX(){
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{
                    if(column==oCLComprobanteCompra.colCantidad)
                    {
                        if(!value.toString().trim().equals("")||value!=null||!value.toString().isEmpty())
                        {
                            super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),2),row,column);
                        }
                        else
                        {
                            super.setValueAt(null,row,column);

                        }

                    }
                    else
                    {
                        super.setValueAt(value,row,column);
                    }
                    if(column==oCLComprobanteCompra.colDescuento)
                    {
                        if(!value.toString().trim().equals("")||value!=null||!value.toString().isEmpty())
                        {
                            super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),2),row,column);
                        }
                        else
                        {
                            super.setValueAt("0.00",row,column);
                        }

                    }
                    else
                    {
                        super.setValueAt(value,row,column);
                    }

                }
                catch(Exception e)
                {
                    if(this.getRowCount()>0){
                        super.setValueAt(null,row,column);
                    }
                }
            }

        };
        label20 = new Label.Label();
        BtnAgregar = new javax.swing.JButton();
        BtnQuitar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        columnButton1 = new util.clases.vtaVenta.ColumnButton();
        LblHoraSistema = new Label.Label();
        ChkCompraDirecta = new Opcion.CheckBox();
        LblEtiqEstado = new Label.Label();
        LblEstado = new Label.Label();
        label22 = new Label.Label();
        DtchFecha =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        LblEtiqSubtotalBruto = new Label.Label();
        LblSubtotalBruto = new Label.Label();
        LblEtiqDsctoSubtENValores = new Label.Label();
        LblDescuentoSubtotal = new Label.Label();
        LblEtiqSubtotalNeto = new Label.Label();
        LblSubtotalNeto = new Label.Label();
        ChkPercepcion = new Opcion.CheckBox();

        CbxTipoDescuento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CbxTipoDescuentoMouseClicked(evt);
            }
        });

        CbxUnidadMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextFieldNumber.setFont(new java.awt.Font("Verdana", 0, 12));
        jTextFieldNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldNumber.setBorder(null);

        TxtProducto.setBorder(null);
        TxtProducto.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtProducto.setTamanio(250);
        TxtProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtProductoMouseClicked(evt);
            }
        });
        TxtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtProductoKeyTyped(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Compras");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        PnlDatos2.setBackground(java.awt.SystemColor.controlHighlight);
        PnlDatos2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "[ DATOS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        LblEtiqProveedor.setText("Proveedor:");

        LblEtiqSucursal.setText("Sucursal   :");

        TxtVendedor.setText("---------");
        TxtVendedor.setFont(new java.awt.Font("Verdana", 1, 12));

        LblEtiqCondicion.setText("Condicion :");

        CbxCondicion.setEnabled(false);
        CbxCondicion.setFont(new java.awt.Font("Verdana", 0, 11));
        CbxCondicion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxCondicionItemStateChanged(evt);
            }
        });

        CbxMoneda.setFont(new java.awt.Font("Verdana", 0, 11));
        CbxMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxMonedaActionPerformed(evt);
            }
        });

        LblEtiqMoneda.setText("Moneda     :");

        LblEtiqTipoCambio.setText("Tipo Cambio:");

        LblEtiqRuc.setText("RUC             :");

        LblEtiqDireccion.setText("Direccion  :");

        TxtProveedor.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtProveedor.setTamanio(200);
        TxtProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtProveedorMouseClicked(evt);
            }
        });
        TxtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtProveedorKeyPressed(evt);
            }
        });

        LblUltimaFecha.setText("Hora Registro");
        LblUltimaFecha.setText("");

        LblEtiqUltimaFEcha.setText("Ultima Fecha:");

        LblEtiqUsuario.setText("Usuario   :");

        TxtSucursal.setText("-----------");
        TxtSucursal.setFont(new java.awt.Font("Verdana", 1, 12));

        javax.swing.GroupLayout PnlDatos2Layout = new javax.swing.GroupLayout(PnlDatos2);
        PnlDatos2.setLayout(PnlDatos2Layout);
        PnlDatos2Layout.setHorizontalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(TxtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(LblEtiqRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(CbxCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(LblEtiqMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(LblEtiqTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(LblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(LblEtiqUltimaFEcha, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(LblUltimaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        PnlDatos2Layout.setVerticalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(LblEtiqProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEtiqDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(LblEtiqCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CbxCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(LblEtiqMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(LblEtiqTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEtiqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqUltimaFEcha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblUltimaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MONTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel1.setLayout(null);

        LblEtiqMontoTotal.setText("MONTO TOTAL ");
        LblEtiqMontoTotal.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqMontoTotal);
        LblEtiqMontoTotal.setBounds(660, 100, 130, 20);

        LblMontoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotal.setText("0.00");
        LblMontoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblMontoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblMontoTotal);
        LblMontoTotal.setBounds(790, 100, 120, 16);

        LblIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblIgv.setText("0.00");
        LblIgv.setFont(new java.awt.Font("Verdana", 1, 12));
        LblIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblIgv);
        LblIgv.setBounds(790, 40, 120, 20);

        LblEtiqIGV.setText("I.G.V.");
        LblEtiqIGV.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqIGV);
        LblEtiqIGV.setBounds(660, 40, 130, 20);

        LblISC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblISC.setText("0.00");
        LblISC.setFont(new java.awt.Font("Verdana", 1, 12));
        LblISC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblISC);
        LblISC.setBounds(790, 60, 120, 20);

        LblEtiqISC.setText("I.S.C.");
        LblEtiqISC.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqISC);
        LblEtiqISC.setBounds(660, 60, 130, 20);

        LblPorcentaje.setText("V");
        LblPorcentaje.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(LblPorcentaje);
        LblPorcentaje.setBounds(460, 20, 20, 16);

        LblEtiqSubNTSinIvg.setText("SUB. NT. SIN IGV");
        LblEtiqSubNTSinIvg.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqSubNTSinIvg);
        LblEtiqSubNTSinIvg.setBounds(660, 20, 130, 20);

        LblSubTotalNetoSinIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubTotalNetoSinIgv.setText("0.00");
        LblSubTotalNetoSinIgv.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubTotalNetoSinIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblSubTotalNetoSinIgv);
        LblSubTotalNetoSinIgv.setBounds(790, 20, 120, 20);

        LblEtiqTipoDescuento.setText("Tipo Descuento :");
        LblEtiqTipoDescuento.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(LblEtiqTipoDescuento);
        LblEtiqTipoDescuento.setBounds(20, 20, 110, 16);

        CbxTipoDescuentoComprobanteCompra.setFont(new java.awt.Font("Verdana", 0, 11));
        CbxTipoDescuentoComprobanteCompra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxTipoDescuentoComprobanteCompraItemStateChanged(evt);
            }
        });
        CbxTipoDescuentoComprobanteCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoDescuentoComprobanteCompraActionPerformed(evt);
            }
        });
        jPanel1.add(CbxTipoDescuentoComprobanteCompra);
        CbxTipoDescuentoComprobanteCompra.setBounds(140, 20, 116, 21);

        LblDescuentoValores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoValores.setText("0.00");
        LblDescuentoValores.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoValores.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblDescuentoValores);
        LblDescuentoValores.setBounds(790, 80, 120, 20);

        LblEtiqDescValores.setText("DESC. EN VALORES");
        LblEtiqDescValores.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqDescValores);
        LblEtiqDescValores.setBounds(660, 80, 130, 20);

        LblEtiqDescuento.setText("Descuento :");
        LblEtiqDescuento.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(LblEtiqDescuento);
        LblEtiqDescuento.setBounds(260, 20, 78, 16);

        LblEtiqPercepcion.setText("PERCEPCIÓN");
        LblEtiqPercepcion.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqPercepcion);
        LblEtiqPercepcion.setBounds(660, 120, 130, 20);

        LblDescuentoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoTotal.setText("0");
        LblDescuentoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblDescuentoTotal);
        LblDescuentoTotal.setBounds(560, 20, 90, 19);

        btnRecepcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Entrada.png"))); // NOI18N
        btnRecepcion.setText("RECEP.");
        btnRecepcion.setToolTipText("Recepcionar Entrada");
        btnRecepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecepcionActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecepcion);
        btnRecepcion.setBounds(370, 100, 90, 70);

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar);
        btnEditar.setBounds(190, 100, 90, 70);

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed1(evt);
            }
        });
        jPanel1.add(btnGuardar);
        btnGuardar.setBounds(100, 100, 90, 70);

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo);
        btnNuevo.setBounds(10, 100, 90, 70);

        LblEtiqTotalPagar.setText("TOTAL A PAGAR ");
        LblEtiqTotalPagar.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(LblEtiqTotalPagar);
        LblEtiqTotalPagar.setBounds(660, 140, 130, 30);

        LblMontoTotalPagar.setForeground(new java.awt.Color(0, 0, 153));
        LblMontoTotalPagar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotalPagar.setText("0.00");
        LblMontoTotalPagar.setFont(new java.awt.Font("Verdana", 1, 15));
        LblMontoTotalPagar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblMontoTotalPagar);
        LblMontoTotalPagar.setBounds(790, 140, 120, 30);

        TxtDescuento.setFont(new java.awt.Font("Verdana", 1, 11));
        TxtDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtDescuento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        TxtDescuento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtDescuentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtDescuentoFocusLost(evt);
            }
        });
        TxtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtDescuentoKeyReleased(evt);
            }
        });
        jPanel1.add(TxtDescuento);
        TxtDescuento.setBounds(350, 20, 100, 21);

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(550, 100, 90, 70);

        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportar1);
        btnExportar1.setBounds(460, 100, 90, 70);

        LblEtiqDescTotal.setText("Desc. Total:");
        LblEtiqDescTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(LblEtiqDescTotal);
        LblEtiqDescTotal.setBounds(480, 20, 80, 16);

        lblPercepcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPercepcion.setText("0.00");
        lblPercepcion.setFont(new java.awt.Font("Verdana", 1, 12));
        lblPercepcion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(lblPercepcion);
        lblPercepcion.setBounds(790, 120, 120, 20);

        btnAnular1.setText("ANULAR");
        btnAnular1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnular1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnular1);
        btnAnular1.setBounds(280, 100, 90, 70);

        jPanel3.setBackground(java.awt.SystemColor.info);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        CbxTipoComprobante.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxTipoComprobanteItemStateChanged(evt);
            }
        });
        CbxTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoComprobanteActionPerformed(evt);
            }
        });

        LblEtiqComprob.setText("Comprob:");

        LblEtiqNum.setText("Num :");

        TxtNumeroComprob1.setText("003-123456");
        TxtNumeroComprob1.setTamanio(20);
        TxtNumeroComprob1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNumeroComprob1ActionPerformed(evt);
            }
        });
        TxtNumeroComprob1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNumeroComprob1KeyPressed(evt);
            }
        });

        btnBuscarCompComp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        btnBuscarCompComp.setText("Buscar Comprob.");
        btnBuscarCompComp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscarCompComp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarCompComp.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscarCompComp.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarCompComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCompCompActionPerformed(evt);
            }
        });

        btnImportarOC.setFont(new java.awt.Font("Verdana", 1, 12));
        btnImportarOC.setText("Import. Orden C.");
        btnImportarOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarOCActionPerformed(evt);
            }
        });

        btnImportarGR.setFont(new java.awt.Font("Verdana", 1, 12));
        btnImportarGR.setText("Import. Guia Rem.");
        btnImportarGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarGRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblEtiqComprob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblEtiqNum, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtNumeroComprob1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnImportarGR, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImportarOC, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCompComp, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(TxtNumeroComprob1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(LblEtiqNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(LblEtiqComprob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarCompComp, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnImportarOC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnImportarGR, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblComprobanteCompraDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblComprobanteCompraDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {new Integer(1), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N°", "Codigo", "Producto", "Stock", "Cantidad", "U. M. ", "Precio Con IGV", "Impor. Sin igv", "Impor. Con igv", "Tipo Desc.", "Descuento", "Dsct. Val.", "Precio c/ desc.", "Importe Neto", "Exonerado", "Almacen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, false, true, true, true, true, true, false, true, false, false, false
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
        TblComprobanteCompraDetalle.setRowHeight(21);
        TblComprobanteCompraDetalle.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblComprobanteCompraDetalle.getTableHeader().setReorderingAllowed(false);
        TblComprobanteCompraDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblComprobanteCompraDetalleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TblComprobanteCompraDetalleMousePressed(evt);
            }
        });
        TblComprobanteCompraDetalle.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblComprobanteCompraDetallePropertyChange(evt);
            }
        });
        TblComprobanteCompraDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblComprobanteCompraDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblComprobanteCompraDetalleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblComprobanteCompraDetalle);
        TblComprobanteCompraDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(0).setPreferredWidth(28);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(1).setMinWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(1).setPreferredWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(1).setMaxWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(2).setPreferredWidth(380);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(3).setMinWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(3).setPreferredWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(3).setMaxWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(5).setPreferredWidth(80);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(6).setPreferredWidth(85);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(7).setPreferredWidth(90);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(8).setPreferredWidth(95);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(9).setMinWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(9).setPreferredWidth(80);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(10).setMinWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(10).setPreferredWidth(70);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(11).setMinWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(12).setMinWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(13).setMinWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(13).setPreferredWidth(90);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(14).setMinWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(14).setPreferredWidth(95);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(15).setMinWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(15).setPreferredWidth(0);
        TblComprobanteCompraDetalle.getColumnModel().getColumn(15).setMaxWidth(0);

        label20.setText("ORDEN COMPRA DE PRODUCTOS");
        label20.setFont(new java.awt.Font("Verdana", 1, 12));

        BtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Agregar2.png"))); // NOI18N
        BtnAgregar.setToolTipText("Agregar Item");
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });

        BtnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/DeleteUser.png"))); // NOI18N
        BtnQuitar.setToolTipText("Quitar Item");
        BtnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQuitarActionPerformed(evt);
            }
        });

        columnButton1.setTbl(TblComprobanteCompraDetalle);

        LblHoraSistema.setText("hora");
        LblHoraSistema.setFont(new java.awt.Font("Verdana", 1, 11));

        ChkCompraDirecta.setText("COMPRA DIRECTA");
        ChkCompraDirecta.setFont(new java.awt.Font("Verdana", 1, 12));

        LblEtiqEstado.setText("Estado :");

        LblEstado.setText("EMITIDO Y SIN RECEPCIÓN");
        LblEstado.setFont(new java.awt.Font("Verdana", 1, 12));

        label22.setText("Fecha :");

        DtchFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtchFechaPropertyChange(evt);
            }
        });

        LblEtiqSubtotalBruto.setText("<html>SubTotal Bruto:</html>");
        LblEtiqSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalBruto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalBruto.setText("0.00");
        LblSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalBruto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqDsctoSubtENValores.setText("Dsct. Subt. en Valores:");
        LblEtiqDsctoSubtENValores.setFont(new java.awt.Font("Verdana", 1, 12));

        LblDescuentoSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoSubtotal.setText("0.00");
        LblDescuentoSubtotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoSubtotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqSubtotalNeto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblEtiqSubtotalNeto.setText("Subtotal Neto");
        LblEtiqSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblEtiqSubtotalNeto.setMinimumSize(new java.awt.Dimension(62, 20));
        LblEtiqSubtotalNeto.setPreferredSize(new java.awt.Dimension(102, 20));

        LblSubtotalNeto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalNeto.setText("0.00");
        LblSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalNeto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        ChkPercepcion.setText("CON PERCEPCION");
        ChkPercepcion.setFont(new java.awt.Font("Verdana", 1, 12));
        ChkPercepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPercepcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ChkCompraDirecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ChkPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DtchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(LblEtiqSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(LblEtiqDsctoSubtENValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(LblEtiqSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ChkCompraDirecta, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DtchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChkPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAgregar)
                    .addComponent(BtnQuitar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LblEtiqSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LblEtiqDsctoSubtENValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LblEtiqSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarProveedor() {
        if (TxtProveedor.isEnabled()) {
            DlgGestionProveedor odialogo = new DlgGestionProveedor(oparent, true, 1, 1, 1);//consultar

            odialogo.setCajaTexo(TxtProveedor.getText());
            odialogo.setVisible(true);
            odialogo.setTitle("Busqueda de Proveedor");
            if (odialogo.getoCEProveedor() != null) {
                idProveedor = odialogo.getoCEProveedor().getIdProveedor();
                TxtProveedor.setText(odialogo.getoCEProveedor().getRazonSocial());
                TxtDNI.setText(odialogo.getoCEProveedor().getRUC());
                TxtDireccion.setText(odialogo.getoCEProveedor().getDireccion());
                if(TblComprobanteCompraDetalle.isEnabled())
                {
                TblComprobanteCompraDetalle.requestFocus();
                TblComprobanteCompraDetalle.changeSelection(0, oCLComprobanteCompra.colProducto, false, false);
                }
            }
        }

    }

    private void habilitarControles(boolean bol) {
        DtchFecha.setEnabled(bol);
        btnImportarOC.setEnabled(bol);
        btnImportarGR.setEnabled(bol);
        TxtProveedor.setEnabled(bol);
        TxtNumeroComprob1.setEditable(bol);
        TblComprobanteCompraDetalle.setEnabled(bol);
        BtnAgregar.setEnabled(bol);
        BtnQuitar.setEnabled(bol);
        CbxTipoDescuentoComprobanteCompra.setEnabled(bol);
        TxtDescuento.setEnabled(bol);
        CbxTipoComprobante.setEnabled(bol);
        TxtProducto.setEnabled(bol);
        CbxMoneda.setEnabled(bol);
        ChkCompraDirecta.setEnabled(bol);
        ChkPercepcion.setEnabled(bol);
        TxtProducto.setEnabled(bol);
        jTextFieldNumber.setEnabled(bol);
//        ChkIsImpuestoAntes.setEnabled(bol);
    }

    private int ValidarRegistro(int fila) {
        if (TblComprobanteCompraDetalle.getRowCount() > 0) {

            int numColumna = 0;
            CEComprobanteCompraDetalle oCEComprobanteCompraDetalle = (CEComprobanteCompraDetalle) TblComprobanteCompraDetalle.getValueAt(fila - 1, oCLComprobanteCompra.colCodigo);
            String mensaje = "Seleccione un producto";
            if (oCEComprobanteCompraDetalle != null) {
                if (TblComprobanteCompraDetalle.getValueAt(fila - 1, oCLComprobanteCompra.colCantidad) != null) {
                    float cantidad = Float.parseFloat(TblComprobanteCompraDetalle.getValueAt(fila - 1, oCLComprobanteCompra.colCantidad).toString());
                    if (cantidad < 0) {
                        numColumna = oCLComprobanteCompra.colCantidad;
                        colValidada = oCLComprobanteCompra.colCantidad;
                        mensaje = "Ingrese una cantidad mayor a cero";
                    } else {
                        float precio = 0;
                        if (TblComprobanteCompraDetalle.getValueAt(fila - 1, oCLComprobanteCompra.colPrecioConIGV) != null) {
                            precio = VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(fila - 1, oCLComprobanteCompra.colPrecioConIGV).toString());
                        }
//                        if (precio == 0) {
//                            numColumna = oCLComprobanteCompra.colPrecioConIGV;
//                            colValidada = oCLComprobanteCompra.colPrecioConIGV;
//                            mensaje = "Ingrese un Precio";
//                        } else
                            if (TblComprobanteCompraDetalle.getValueAt(fila - 1, oCLComprobanteCompra.colDescuento) != null) {
                            float descuentodet = Float.parseFloat(TblComprobanteCompraDetalle.getValueAt(fila - 1, oCLComprobanteCompra.colDescuento).toString());
                            float subTotalSindesc = 0;
                            if (TblComprobanteCompraDetalle.getValueAt(fila - 1, oCLComprobanteCompra.colImporSinDesc) != null) {
                                subTotalSindesc = Float.parseFloat(TblComprobanteCompraDetalle.getValueAt(fila - 1, oCLComprobanteCompra.colImporSinDesc).toString());
                            }

                            if (descuentodet > subTotalSindesc) {
                                mensaje = "El descuento no puede ser mayor al Subtotal";
                                numColumna = oCLComprobanteCompra.colDescuento;
                                colValidada = oCLComprobanteCompra.colDescuento;
                            }
                        }
                    }
                } else {
                    numColumna = oCLComprobanteCompra.colCantidad;
                    colValidada = oCLComprobanteCompra.colCantidad;
                    mensaje = "Ingrese una cantidad";
                }
            } else {
                numColumna = oCLComprobanteCompra.colProducto;
                colValidada = oCLComprobanteCompra.colProducto;

            }
            if (numColumna != 0) {
//            if(numColumna!=oCLComprobanteCompra.colPrecio){
                JOptionPane.showMessageDialog(this, mensaje);
//            }else
//                { int filat=TblComprobanteCompraDetalle.getSelectedRow();
//                  oCLComprobanteCompra.calcularPrecio(filat);
//                }
            }

            return numColumna;

        }
        return 0;
    }

    private void BuscarProducto() {

        if(TblComprobanteCompraDetalle.isEnabled()){
        DlgBusquedaProductoCompra odialogo = new DlgBusquedaProductoCompra(oparent, true,0,0);
        String NombreProd = TxtProducto.getText().trim();
        btnGuardar.requestFocus();
        if (NombreProd.equals("")) {
            if (TblComprobanteCompraDetalle.getValueAt(TblComprobanteCompraDetalle.getSelectedRow(), oCLComprobanteCompra.colProducto) != null) {
                NombreProd = TblComprobanteCompraDetalle.getValueAt(TblComprobanteCompraDetalle.getSelectedRow(), oCLComprobanteCompra.colProducto) + "";
            }
        }
        odialogo.setCajaTexto(NombreProd);
        TxtProducto.setText("");
        odialogo.setVisible(true);
        int fila = TblComprobanteCompraDetalle.getSelectedRow();
        CEProducto oCEProducto = odialogo.getProducto();
        if (!odialogo.getisagregarCantidadOrdenCompra()) {
             agregarComprobanteCompraDetalle(oCEProducto);
             oCLComprobanteCompra.CalcularPrecioDesdeImporteConIGV(fila, oCLComprobanteCompra.colImporSinDescConIgv);            
             TblComprobanteCompraDetalle.requestFocus();
             TblComprobanteCompraDetalle.changeSelection(fila, oCLComprobanteCompra.colCantidad, false, false);

        } else {
            TblComprobanteCompraDetalle.requestFocus();
            SeleccionarComprobanteCompraExistente(oCEProducto);
        }
      }

    }

    private void SeleccionarComprobanteCompraExistente(CEProducto oCEProducto) {
        CEComprobanteCompraDetalle oCEComprobanteCompraDetalle;
        for (int i = 0; i < TblComprobanteCompraDetalle.getRowCount(); i++) {
//cx
            oCEComprobanteCompraDetalle = (CEComprobanteCompraDetalle) TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colCodigo);
            if (oCEComprobanteCompraDetalle != null) {

                if (oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra() == oCEProducto.getIdUnidadBase()
                        && oCEComprobanteCompraDetalle.getIdProducto() == oCEProducto.getIdProducto()) {
                    TblComprobanteCompraDetalle.requestFocus();
                    TblComprobanteCompraDetalle.changeSelection(i, oCLComprobanteCompra.colUnidadMedida, false, false);
                }

            }
        }
    }

    private List<CEComprobanteCompraDetalle> ObtenerRegistrosAcuales() {
        List<CEComprobanteCompraDetalle> lstComprobanteCompraDetalleTemp = new ArrayList<CEComprobanteCompraDetalle>();
        CEComprobanteCompraDetalle oCEComprobanteCompraDetalle;
        for (int i = 0; i < TblComprobanteCompraDetalle.getRowCount(); i++) {
            oCEComprobanteCompraDetalle = (CEComprobanteCompraDetalle) TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colCodigo);
            if (oCEComprobanteCompraDetalle != null) {
                if (i != TblComprobanteCompraDetalle.getSelectedRow()) {
                    oCEComprobanteCompraDetalle.setPrecio(VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colPrecioConIGV).toString()));
                    oCEComprobanteCompraDetalle.setCantidad(0);
                    if (TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colCantidad) != null) {
                        oCEComprobanteCompraDetalle.setCantidad(VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colCantidad).toString()));
                    }
                    CEComprobanteCompraDetalle oCEComprobanteCompraDetalleTemp = (CEComprobanteCompraDetalle) TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colCodigo);
                    oCEComprobanteCompraDetalle.setIdProducto(oCEComprobanteCompraDetalleTemp.getIdProducto());
                    lstComprobanteCompraDetalleTemp.add(oCEComprobanteCompraDetalle);
                }
            }
        }
        return lstComprobanteCompraDetalleTemp;
    }

    private boolean ValidarTablaComprobanteCompra() {


        for (int i = 0; i < TblComprobanteCompraDetalle.getRowCount(); i++) {
            verificarDatos(i);

            if (colValidada != -1) {
                TblComprobanteCompraDetalle.requestFocus();
                TblComprobanteCompraDetalle.changeSelection(i, colValidada, false, false);
                colValidada = -1;
                return false;
            }
        }
        eventoGuardar = false;
        return true;
    }

    private String ValidarRegistrosDeCostoCero() {

       String Mensaje="";
        for (int i = 0; i < TblComprobanteCompraDetalle.getRowCount(); i++) {
          float precio=VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colPrecioConIGV));
          if(precio ==0)
          {
              Mensaje=Mensaje+VerificadorDeTxt.convertString(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colProducto))+"\n";
          }
        }
        return Mensaje;
    }

    boolean eventoGuardar = false;
    private void GuardarCompra()
    {
      if(btnGuardar.isEnabled()){
      oCLComprobanteCompra.CalcularSubtotales();
      oCLComprobanteCompra.calcularImportes();
      oCLComprobanteCompra.eventoDescuento();
      int fil=TblComprobanteCompraDetalle.getRowCount();
      if(fil>0){
              int row=fil-1;
              CEComprobanteCompraDetalle oCEComprobanteCompraDetalle= (CEComprobanteCompraDetalle)TblComprobanteCompraDetalle.getValueAt(row, oCLComprobanteCompra.colCodigo);
              if(oCEComprobanteCompraDetalle==null){
                oCLComprobanteCompra.QuitarFilaSel(row);
              }
        }
      fil=TblComprobanteCompraDetalle.getRowCount();
      if(fil>0){

        eventoGuardar = true;
        int columnaValidada = ValidarRegistro(TblComprobanteCompraDetalle.getRowCount());
        if (columnaValidada == 0) {

            GuardarRegistro();

        } else {
            TblComprobanteCompraDetalle.requestFocus();
            TblComprobanteCompraDetalle.changeSelection(TblComprobanteCompraDetalle.getRowCount() - 1, columnaValidada, false, false);
        }

        }
        else
        {
               JOptionPane.showMessageDialog(this,"Ingrese un Item","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                 agregarNuevaFila(0);
                 TblComprobanteCompraDetalle.requestFocus();
                 TblComprobanteCompraDetalle.changeSelection(0, oCLComprobanteCompra.colProducto, false, false);
        }

      }
        
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private boolean validar() {
        
        
        if (idProveedor == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese un Proveedor", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            buscarProveedor();
            return false;
        }
        if (TxtNumeroComprob1.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this,"Ingrese el número del comprobante", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            TxtNumeroComprob1.requestFocus();
            return false;
        }
        if (TblComprobanteCompraDetalle.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese ComprobanteCompra", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            agregarNuevaFila(0);
            TblComprobanteCompraDetalle.requestFocus();
            TblComprobanteCompraDetalle.changeSelection(0, oCLComprobanteCompra.colProducto, false, false);
            return false;
        }
        if (DtchFecha.getCalendar()==null) {
            JOptionPane.showMessageDialog(this, "Ingrese una Fecha", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            DtchFecha.requestFocus();
            return false;
        }

        String Mensaje=ValidarRegistrosDeCostoCero();
        if(!Mensaje.equals(""))
        {
         int resul=JOptionPane.showConfirmDialog(this,"Adevertencia,Los Siguientes Productos tienen costo Cero :\n"+ Mensaje+"\n ¿Desea Continuar de todos modos?", "ADVERTENCIA",JOptionPane.YES_NO_OPTION);
            if (resul==JOptionPane.NO_OPTION||resul==JOptionPane.CLOSED_OPTION) {
               return false;
            }
        }
        
        int resul=JOptionPane.showConfirmDialog(this,"<html><h2> Monto  a  Pagar  : "+LblMontoTotalPagar.getText()+" S/.<BR>Nº Comprobante: "
                                                                    + TxtNumeroComprob1.getText() + " <BR><BR>ESTÁ SEGURO DE GUARDAR LA COMPRA</h2></html>" , "",JOptionPane.YES_NO_OPTION);
        if (resul==JOptionPane.NO_OPTION||resul==JOptionPane.CLOSED_OPTION) {
                   return false;
        }
        if(pAccionABM==2)
        {
            CEEstado oCEEstadoContab=CCEstado.consultarUltimoestadoCompra_contable(IdComprobanteCompra);
            IdEstado=oCEEstadoContab.getmIntIdEstado();
            if(oCEEstadoContab.getmIntIdEstado()!=14)
            {
             JOptionPane.showMessageDialog(this,"El Registro ya fué "+oCEEstadoContab.getmStrDescripcion()+", No se Puede Modificar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
            CEEstado oCEEstadoInvent=CCEstado.consultarUltimoestadoCompra_inventario(IdComprobanteCompra);
            IdEstadoInv=oCEEstadoInvent.getmIntIdEstado();
           // if(oCEEstadoInvent.getmIntIdEstado()!=13)
//            {
//             JOptionPane.showMessageDialog(this,"El Registro ya fué "+oCEEstadoInvent.getmStrDescripcion()+", No se Puede Modificar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
//             return false;
//            }

        }

        return true;
    }

    private void GuardarRegistro() {
        if (ValidarTablaComprobanteCompra()) {
            if (validar()) {
                if (pAccionABM == 1) {
                    CEComprobanteCompraMatriz oCEComprobanteCompraInsert = CCComprobanteCompra.InsComprobanteCompra(this.getComprobanteCompra());
                    if (oCEComprobanteCompraInsert != null) {

                        if(oCEComprobanteCompraInsert.getIdEstadoInventario()==13){
                            int resul=JOptionPane.showConfirmDialog(this,"<html><h3>Se guardó con exito, <BR> ¿DESEA GENERAR LA ENTRADA A ALMACEN?<h3></html>" , "",JOptionPane.YES_NO_OPTION);
                            if (resul==JOptionPane.YES_OPTION) {

                                DlgIngresoProducto1 oDlgIngresoProducto=new DlgIngresoProducto1(oparent, true,true);

                                oDlgIngresoProducto.SetIngresoProductoPorCompra(oCEComprobanteCompraInsert);
                                oDlgIngresoProducto.setVisible(true);

                            }
                            }
                        limpiarFormulario();
                        LblUltimaFecha.setText("");
                        TblComprobanteCompraDetalle.requestFocus();
                        TblComprobanteCompraDetalle.changeSelection(0, oCLComprobanteCompra.colProducto, false, false);

                    } else {

                        JOptionPane.showMessageDialog(this, "Operación Fallida");
                    }
                } else {
                    int iscorrecto = ActualizarSegunEstado();
                    if (iscorrecto == 1) {
                        JOptionPane.showMessageDialog(this, "<html><CENTER>Operación exitosa</CENTER></html>");
                        limpiarFormulario();
                        eventoNuevo();
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Operación Fallida");
                    }
                }
            }
        }
    }

    private int ActualizarSegunEstado()
    {
        if(IdEstado==14&&IdEstadoInv==13)
        return CCComprobanteCompra.UPDComprobanteCompra(this.getComprobanteCompra());

       return CCComprobanteCompra.UPDComprobanteCompraSinDet(this.getComprobanteCompra());
    }

    private CEComprobanteCompraMatriz getComprobanteCompra() {
        CEComprobanteCompraMatriz oCEComprobanteCompra = new CEComprobanteCompraMatriz();
        int idEstadoGuiaRem=IdEstadoInv;
        oCEComprobanteCompra.setIdComprobanteCompra(IdComprobanteCompra);
        oCEComprobanteCompra.setIdOrdenCompra(1);
        CETipoComprobante oCETipoComprobante = (CETipoComprobante) CbxTipoComprobante.getSelectedItem();
        oCEComprobanteCompra.setIdTipoComprobanteCompra(oCETipoComprobante.getIdTipoComprobante());
        CEMoneda oCEMoneda = (CEMoneda) CbxMoneda.getSelectedItem();
        oCEComprobanteCompra.setIdMoneda(oCEMoneda.getId_moneda());
        CECondicion oCECondcion = (CECondicion) CbxCondicion.getSelectedItem();
        oCEComprobanteCompra.setIdCondicion(oCECondcion.getIdCondicion());
        oCEComprobanteCompra.setTipoCambio(Float.parseFloat(TxtTipoCambio.getText()));
        oCEComprobanteCompra.setProveedor(TxtProveedor.getText());
        oCEComprobanteCompra.setRUC(TxtDNI.getText());
        oCEComprobanteCompra.setDireccion(TxtDireccion.getText());
        CETipoDescuento oCETipoDescuento = (CETipoDescuento) CbxTipoDescuentoComprobanteCompra.getSelectedItem();

        oCEComprobanteCompra.setIdSucursal(FrmSistemaMenu.getIdSucursal());
        oCEComprobanteCompra.setUltimoIdEstado(1);
        oCEComprobanteCompra.setIdEstadoInventario(13);
        if(IdGuiaRemision!=0){
        oCEComprobanteCompra.setIdEstadoInventario(idEstadoGuiaRem);
        }
        oCEComprobanteCompra.setIdTipoDescuento(oCETipoDescuento.getIdTipoDescuento());
        oCEComprobanteCompra.setIdProveedor(idProveedor);
        oCEComprobanteCompra.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCEComprobanteCompra.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCEComprobanteCompra.setSubtotalBruto(Float.parseFloat(LblSubtotalBruto.getText()));
        oCEComprobanteCompra.setDescuentoEnSubtotal(Float.parseFloat(LblDescuentoSubtotal.getText()));
        oCEComprobanteCompra.setSubtotalNeto(Float.parseFloat(LblSubtotalNeto.getText()));
        oCEComprobanteCompra.setDescuento(oCLComprobanteCompra.getDescuento());
        oCEComprobanteCompra.setDescuentoEnValores(Float.parseFloat(LblDescuentoValores.getText()));
        oCEComprobanteCompra.setSubTotalNetoSinIGV(Float.parseFloat(LblSubTotalNetoSinIgv.getText()));
        oCEComprobanteCompra.setIGV(Float.parseFloat(LblIgv.getText()));
        oCEComprobanteCompra.setISC(Float.parseFloat(LblISC.getText()));
        oCEComprobanteCompra.setIGVActual(oCLComprobanteCompra.getIgvActual());
        oCEComprobanteCompra.setDescuentoTotal(Float.parseFloat(LblDescuentoTotal.getText()));
        oCEComprobanteCompra.setMontoTotal(Float.parseFloat(LblMontoTotal.getText()));
        oCEComprobanteCompra.setTotalPagar(Float.parseFloat(LblMontoTotalPagar.getText()));
        oCEComprobanteCompra.setCompraDirecta(ChkCompraDirecta.isSelected());
        oCEComprobanteCompra.setNumComprobante(TxtNumeroComprob1.getText());
        oCEComprobanteCompra.setConPercepcion(ChkPercepcion.isSelected());
        oCEComprobanteCompra.setMontoPercepcion(Float.parseFloat(lblPercepcion.getText()));
        ConvertidorFecha oConvertidorFecha = new ConvertidorFecha();
        if (DtchFecha.getCalendar() != null)
        {
            oConvertidorFecha.setFecha(DtchFecha.getCalendar());
            oCEComprobanteCompra.setFecha(oConvertidorFecha.getFechaConvertida());
        }
        List<CEComprobanteCompraDetalle> lstComprobanteCompraDetalle = new ArrayList<CEComprobanteCompraDetalle>();
        CEComprobanteCompraDetalle oCEComprobanteCompraDetalle;
        for (int i = 0; i < TblComprobanteCompraDetalle.getRowCount(); i++) {

            oCEComprobanteCompraDetalle = (CEComprobanteCompraDetalle) TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colCodigo);
            if (oCEComprobanteCompraDetalle != null) {
                CETipoDescuento oCETipoDescuentoDet = (CETipoDescuento) TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colTipoDescuento);
                oCEComprobanteCompraDetalle.setIdTipoDescuento(oCETipoDescuentoDet.IdTipoDescuento);
                if(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colPrecioConIGV)==null)
                {TblComprobanteCompraDetalle.setValueAt(0, i, oCLComprobanteCompra.colPrecioConIGV);}
                oCEComprobanteCompraDetalle.setPrecio(VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colPrecioConIGV)));
                oCEComprobanteCompraDetalle.setPrecioConDesc(VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colPrecioConDesc)));
                oCEComprobanteCompraDetalle.setCantidad(VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colCantidad)));
                oCEComprobanteCompraDetalle.setSaldoCantidad(VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colCantidad)));
                oCEComprobanteCompraDetalle.setImporteSinDescuento(VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colImporSinDesc)));
                oCEComprobanteCompraDetalle.setImporteSinDescuentoConIgv(VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colImporSinDescConIgv)));
                oCEComprobanteCompraDetalle.setUnidadMedida(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colUnidadMedida).toString());
                oCEComprobanteCompraDetalle.setImporteConDescuento(VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colImporConDesc)));
                    oCEComprobanteCompraDetalle.setExonerado(VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colExonerado)));
                if (TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colDescuento) == null) {
                    TblComprobanteCompraDetalle.setValueAt("0", i, oCLComprobanteCompra.colDescuento);
                }
                oCEComprobanteCompraDetalle.setDescuento(VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colDescuento)));
                float descuentoValores =VerificadorDeTxt.convertFloat(TblComprobanteCompraDetalle.getValueAt(i, oCLComprobanteCompra.colDestValores));
                oCEComprobanteCompraDetalle.setDescuentoEnValores(descuentoValores);
                lstComprobanteCompraDetalle.add(oCEComprobanteCompraDetalle);
            }
        }

        if(pAccionABM==2)
                {
                    lstComprobanteCompraDetalle.addAll(lstComprasEliminados);
                }
        
        oCEComprobanteCompra.setoLstComprobanteDetalle(lstComprobanteCompraDetalle);

        return oCEComprobanteCompra;
    }

    private void agregarNuevaFila(int fila) {

        Vector oVector = new Vector();
        ((DefaultTableModel) TblComprobanteCompraDetalle.getModel()).addRow(oVector);

        if(fila!=0){
            if(TblComprobanteCompraDetalle.getValueAt(fila-1, oCLComprobanteCompra.colPrecioConIGV)==null)
                    {TblComprobanteCompraDetalle.setValueAt(0, fila-1, oCLComprobanteCompra.colPrecioConIGV);
            }
        }

        CbxTipoDescuento.setSelectedIndex(0);
        CETipoDescuento oTipoDescuento = (CETipoDescuento) CbxTipoDescuento.getSelectedItem();

        TblComprobanteCompraDetalle.requestFocus();

        TblComprobanteCompraDetalle.setValueAt(oTipoDescuento, fila, oCLComprobanteCompra.colTipoDescuento);


    }
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        eventoAgregar();

    }//GEN-LAST:event_BtnAgregarActionPerformed
    private void eventoAgregar() {
        int columnaValidada = ValidarRegistro(TblComprobanteCompraDetalle.getRowCount());
        if (columnaValidada == 0) {
            agregarNuevaFila(TblComprobanteCompraDetalle.getRowCount());
            TblComprobanteCompraDetalle.requestFocus();
            TblComprobanteCompraDetalle.changeSelection(TblComprobanteCompraDetalle.getRowCount()-1, oCLComprobanteCompra.colProducto, false, false);


        } else {
            TblComprobanteCompraDetalle.requestFocus();
            TblComprobanteCompraDetalle.changeSelection(TblComprobanteCompraDetalle.getRowCount() - 1, columnaValidada, false, false);
        }
    }
    private void BtnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarActionPerformed

        try{
        this.QuitarFila();
        }
        catch(Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(this,"Operacion Fallida");
        }

    }//GEN-LAST:event_BtnQuitarActionPerformed

    private void QuitarFila()
{

        int rowsel=TblComprobanteCompraDetalle.getSelectedRow();
        if(rowsel!=-1){
                       CEComprobanteCompraDetalle oCEComprobanteCompraDetalle= (CEComprobanteCompraDetalle)TblComprobanteCompraDetalle.getValueAt(rowsel, oCLComprobanteCompra.colCodigo);
                    if(oCEComprobanteCompraDetalle!=null){
                        if(oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle()>0)
                        {
                            oCEComprobanteCompraDetalle.setIdAbm(3);
                            lstComprasEliminados.add(oCEComprobanteCompraDetalle);
                        }
                    }
                    oCLComprobanteCompra.QuitarFila();
                }
        else JOptionPane.showMessageDialog(this,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);

}

    private void CbxCondicionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxCondicionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxCondicionItemStateChanged

    private void CbxTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteActionPerformed
    }//GEN-LAST:event_CbxTipoComprobanteActionPerformed

    private void CbxTipoComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteItemStateChanged
        CETipoComprobante oCETipoComprobante = (CETipoComprobante) CbxTipoComprobante.getSelectedItem();
        if (oCETipoComprobante != null) {
            oCLComprobanteCompra.CalcularSubtotales();
            oCLComprobanteCompra.eventoDescuento();
            oCLComprobanteCompra.calcularImportes();
            
        }
    }//GEN-LAST:event_CbxTipoComprobanteItemStateChanged
    private int colValidada = -1;
    private int cont = 0;// contador utilizado como artificio

    private void verificarDatos(int fila) {
        try {
            colValidada = -1;

            float cantidad = 1;
            float descuento = 0;
            float precio = 1;
            float stock = 1;
            if (TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colCantidad) != null) {
                cantidad = Float.parseFloat(TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colCantidad).toString());
            }
            if (TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colPrecioConIGV) != null) {
                precio = Float.parseFloat(TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colPrecioConIGV).toString());
            }
            if (TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colDescuento) != null) {
                descuento = Float.parseFloat(TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colDescuento).toString());
            }

            if (precio < 0) {
                JOptionPane.showMessageDialog(this, "el precio debe ser mayor a cero");
                TblComprobanteCompraDetalle.setValueAt(null, fila, oCLComprobanteCompra.colPrecioConIGV);
                TblComprobanteCompraDetalle.setValueAt(null, fila, oCLComprobanteCompra.colImporSinDesc);
                TblComprobanteCompraDetalle.setValueAt(null, fila, oCLComprobanteCompra.colImporSinDescConIgv);
                colValidada = oCLComprobanteCompra.colPrecioConIGV;
                return;
            }

            if (descuento > cantidad * precio) {
                JOptionPane.showMessageDialog(this, "El descuento no puede ser mayor al Importe Bruto");
                TblComprobanteCompraDetalle.setValueAt(null, fila, oCLComprobanteCompra.colDescuento);
                descuento = 0;
                TblComprobanteCompraDetalle.setValueAt(CLRedondear.Redondear((cantidad * precio) - descuento, 2), fila, oCLComprobanteCompra.colImporConDesc);//con
                oCLComprobanteCompra.CalcularSubtotales();
                oCLComprobanteCompra.calcularImportes();

                colValidada = oCLComprobanteCompra.colDescuento;
                return;
            }

            if (descuento < 0) {
                JOptionPane.showMessageDialog(this, "El descuento no puede ser menor a cero");
                TblComprobanteCompraDetalle.setValueAt(null, fila, oCLComprobanteCompra.colDescuento);
                descuento = 0;
                TblComprobanteCompraDetalle.setValueAt(CLRedondear.Redondear((cantidad * precio) - descuento, 2), fila, oCLComprobanteCompra.colImporConDesc);//con
                colValidada = oCLComprobanteCompra.colDescuento;
                return;
            }

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero");
                TblComprobanteCompraDetalle.setValueAt(null, fila, oCLComprobanteCompra.colCantidad);
                colValidada = oCLComprobanteCompra.colCantidad;
                return;
            }
            /*      if(precio<=0)
            {
            JOptionPane.showMessageDialog(null,"El precio tiene q ser mayor a cero");
            colValidada=oCLComprobanteCompra.colPrecio;
            return;
            }*/
            if (TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colStock) != null) {
                stock = Float.parseFloat(TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colStock).toString());
            }
            if (cantidad > stock) {
                /* JOptionPane.showMessageDialog(null,"La cantidad no debe ser mayor al stock disponible");
                TblComprobanteCompraDetalle.setValueAt(null, fila,oCLComprobanteCompra.colCantidad);
                colValidada=oCLComprobanteCompra.colCantidad;
                return;*/
            }

            int col = TblComprobanteCompraDetalle.getSelectedColumn();
            if (!eventoGuardar) {
                if (col == oCLComprobanteCompra.colCantidad) {
                    oCLComprobanteCompra.calcularPrecio(fila);
                }
            }

        } catch (Exception e) {
            cont++;
            System.out.println("dlgGestionComprobanteCompra-metodo Verificar datos: "+e);
        }
    }

    private void CbxTipoDescuentoComprobanteCompraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxTipoDescuentoComprobanteCompraItemStateChanged
}//GEN-LAST:event_CbxTipoDescuentoComprobanteCompraItemStateChanged

    private void CbxTipoDescuentoComprobanteCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoDescuentoComprobanteCompraActionPerformed
        oCLComprobanteCompra.CalcularSubtotales();
        oCLComprobanteCompra.calcularImportes();
        //     oCLComprobanteCompra.eventoDescuento();
        CETipoDescuento oDescuento = (CETipoDescuento) CbxTipoDescuentoComprobanteCompra.getSelectedItem();
        if (oDescuento != null) {
            if (oDescuento.getIdTipoDescuento() == 2) {

                LblPorcentaje.setText("%");

            } else {

                LblPorcentaje.setText(" V");
            }
        }
         oCLComprobanteCompra.eventoDescuento();
    }//GEN-LAST:event_CbxTipoDescuentoComprobanteCompraActionPerformed
    private String NumComprobante;
    
    private void SetComprobanteCompra( CEComprobanteCompraMatriz oCEComprobanteCompraMatriz ) {

        oCLComprobanteCompra.limpiarTabla();
        if (oCEComprobanteCompraMatriz != null) {

            oCLComprobanteCompra.setCalcularImport(false);
            oCLComprobanteCompra.setSinoDescuento(false);
            ChkPercepcion.setSelected(oCEComprobanteCompraMatriz.isConPercepcion());
            DtchFecha.setDate(oCEComprobanteCompraMatriz.getDate());
            LblEstado.setText(oCEComprobanteCompraMatriz.getUltimoEstado()+" y " +oCEComprobanteCompraMatriz.getEstadoInventario());
            IdEstado = oCEComprobanteCompraMatriz.getUltimoIdEstado();
            IdEstadoInv=oCEComprobanteCompraMatriz.getIdEstadoInventario();
            LblSubtotalBruto.setText("" + oCEComprobanteCompraMatriz.getSubtotalBruto());
            LblDescuentoSubtotal.setText("" + oCEComprobanteCompraMatriz.getDescuentoEnSubtotal());
            TxtDescuento.setText("" + oCEComprobanteCompraMatriz.getDescuento());
            LblSubtotalNeto.setText(oCEComprobanteCompraMatriz.getSubtotalNeto() + "");
            LblSubTotalNetoSinIgv.setText(oCEComprobanteCompraMatriz.getSubTotalNetoSinIGV() + "");
            LblDescuentoValores.setText(oCEComprobanteCompraMatriz.getDescuentoEnValores() + "");
            LblIgv.setText(oCEComprobanteCompraMatriz.getIGV() + "");
            LblISC.setText(oCEComprobanteCompraMatriz.getISC() + "");
            LblMontoTotal.setText(oCEComprobanteCompraMatriz.getMontoTotal()+"");
            lblPercepcion.setText(CLRedondear.RedondearString(oCEComprobanteCompraMatriz.getMontoPercepcion(),2));
            LblMontoTotalPagar.setText(CLRedondear.RedondearString(oCEComprobanteCompraMatriz.getTotalPagar(),2));
            IdComprobanteCompra = oCEComprobanteCompraMatriz.getIdComprobanteCompra();
            idProveedor = oCEComprobanteCompraMatriz.getIdProveedor();
            TxtProveedor.setText(oCEComprobanteCompraMatriz.getProveedor());
            TxtDNI.setText(oCEComprobanteCompraMatriz.getRUC());
            TxtDireccion.setText(oCEComprobanteCompraMatriz.getDireccion());
            TxtProducto.setText("");
            TxtSucursal.setText(oCEComprobanteCompraMatriz.getSucursal());
            TxtVendedor.setText(oCEComprobanteCompraMatriz.getEmpleado());
            LblUltimaFecha.setText(oCEComprobanteCompraMatriz.getUltimaFecha());
            ChkCompraDirecta.setSelected(oCEComprobanteCompraMatriz.isCompraDirecta());
            TxtNumeroComprob1.setText(oCEComprobanteCompraMatriz.getNumComprobante());
            CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuentoComprobanteCompra, oCEComprobanteCompraMatriz.getIdDescuento());
            CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante, oCEComprobanteCompraMatriz.getIdTipoComprobanteCompra());
            CLCargarCombo.buscarIdMoneda(CbxMoneda,oCEComprobanteCompraMatriz.getIdMoneda());
            if (oCEComprobanteCompraMatriz.getoLstComprobanteDetalle() != null) {
                for (int i = 0; i < oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().size(); i++) {

                    Vector oVector = new Vector();

                    ((DefaultTableModel) TblComprobanteCompraDetalle.getModel()).addRow(oVector);

                    CEComprobanteCompraDetalle oCEComprobanteCompraDetalle = oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().get(i);

                    oCEComprobanteCompraDetalle.setIdAbm(2);
                    CEUnidadMedidaProducto oCEUnidadMedidaProducto = new CEUnidadMedidaProducto();
                    oCEUnidadMedidaProducto.setTipoUnidad(oCEComprobanteCompraDetalle.getUnidadMedida());
                    oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra());
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle, i, oCLComprobanteCompra.colCodigo);
                    TxtProducto.setText(oCEComprobanteCompraDetalle.getProducto());
//                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getIdAlmacen(), i, oCLComprobanteCompra.colAlm);
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getProducto(), i, oCLComprobanteCompra.colProducto);
                    TblComprobanteCompraDetalle.setValueAt(oCEUnidadMedidaProducto, i, oCLComprobanteCompra.colUnidadMedida);
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getPrecio(), i, oCLComprobanteCompra.colPrecioConIGV);
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getPrecioConDesc(), i, oCLComprobanteCompra.colPrecioConDesc);
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getCantidad(), i, oCLComprobanteCompra.colCantidad);
                    TblComprobanteCompraDetalle.setValueAt(CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuento.getModel(), oCEComprobanteCompraDetalle.getIdTipoDescuento()), i, oCLComprobanteCompra.colTipoDescuento);
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteSinDescuentoConIgv(), i, oCLComprobanteCompra.colImporSinDescConIgv);
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteSinDescuento(), i, oCLComprobanteCompra.colImporSinDesc);
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteConDescuento(), i, oCLComprobanteCompra.colImporConDesc);
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getDescuentoEnValores(), i, oCLComprobanteCompra.colDestValores);
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getExonerado(), i, oCLComprobanteCompra.colExonerado);
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getDescuento(), i,oCLComprobanteCompra.colDescuento);
 
                }
            }
           
            habilitarControles(false);
            oclBotonesABM.controlBoton(false, false, true, true, true, false);
            habilitarBotonPorEstado();
            pAccionABM = 2;
            btnEditar.requestFocus();
            oCLComprobanteCompra.setSinoDescuento(true);
            oCLComprobanteCompra.setCalcularImport(true);
        } else {
            JOptionPane.showMessageDialog(this, "El codigo no Existe");
        }
    }


    private void habilitarBotonPorEstado()
    {
        if (IdEstadoInv != 13) {
                btnRecepcion.setEnabled(false);
                btnEditar.setEnabled(false);
                btnRecepcion.setEnabled(false);
            }
       if (IdEstadoInv == 13||IdEstadoInv == 9) 
            {
                btnRecepcion.setEnabled(true);
            }
        if (IdEstado != 14) {
                btnEditar.setEnabled(false);
            }else
            {
              btnEditar.setEnabled(true);
            }
            
        
    }
    private void SetComprobanteCompraPorOc( CEOrdenCompraMatriz oCEOrdenCompraMatriz ) {

        oCLComprobanteCompra.limpiarTabla();
       // CEOrdenCompraMatriz oCEComprobanteCompraMatriz=new
        if (oCEOrdenCompraMatriz != null) {
            oCLComprobanteCompra.setCalcularImport(false);
            oCLComprobanteCompra.setSinoDescuento(false);
            LblEstado.setText("EMITIDO Y SIN RECEPCIÓN");
            IdEstado = oCEOrdenCompraMatriz.getIdUltimoEstado();
            IdEstadoInv=13;
            LblSubtotalBruto.setText("" + oCEOrdenCompraMatriz.getSubtotalBruto());
            LblDescuentoSubtotal.setText("" + oCEOrdenCompraMatriz.getDescuentoEnSubtotal());
            TxtDescuento.setText("" + oCEOrdenCompraMatriz.getDescuento());
            LblSubtotalNeto.setText(oCEOrdenCompraMatriz.getSubtotalNeto() + "");
            LblSubTotalNetoSinIgv.setText(oCEOrdenCompraMatriz.getSubtotalNetoSinIGV() + "");
            LblDescuentoValores.setText(oCEOrdenCompraMatriz.getDescuentoValores() + "");
            LblIgv.setText(oCEOrdenCompraMatriz.getIGV() + "");
            LblISC.setText(oCEOrdenCompraMatriz.getISC() + "");
            LblMontoTotal.setText(oCEOrdenCompraMatriz.getMontoTotal()+"");
            LblMontoTotalPagar.setText(CLRedondear.RedondearString(oCEOrdenCompraMatriz.getTotalPagar(),1)+"0");
            IdComprobanteCompra =-2;
            idProveedor = oCEOrdenCompraMatriz.getIdProveedor();
            TxtProveedor.setText(oCEOrdenCompraMatriz.getProveedor());
            TxtDNI.setText(oCEOrdenCompraMatriz.getRUC());
            TxtDireccion.setText(oCEOrdenCompraMatriz.getDireccion());
            TxtProducto.setText("");
            TxtSucursal.setText(oCEOrdenCompraMatriz.getSucursal());
            TxtVendedor.setText(oCEOrdenCompraMatriz.getEmpleado());
            LblUltimaFecha.setText(oCEOrdenCompraMatriz.getFecha());
           // ChkCompraDirecta.setSelected(oCEComprobanteCompraMatriz.is);
            TxtNumeroComprob1.setText(NumComprobante);
            CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuentoComprobanteCompra, oCEOrdenCompraMatriz.getIdDescuento());
            CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante, oCEOrdenCompraMatriz.getIdTipoComprobante());
            CLCargarCombo.buscarIdMoneda(CbxMoneda,oCEOrdenCompraMatriz.getIdMoneda());
            if (oCEOrdenCompraMatriz.getLstOrdenCompraDetalle() != null) {
                for (int i = 0; i < oCEOrdenCompraMatriz.getLstOrdenCompraDetalle().size(); i++) {
                    Vector oVector = new Vector();
                    ((DefaultTableModel) TblComprobanteCompraDetalle.getModel()).addRow(oVector);

                    CEComprobanteCompraDetalle oCEComprobanteCompraDetalle = new CEComprobanteCompraDetalle();
                    CEOrdenCompraDetalle oCEOrdenCompraDetalle = oCEOrdenCompraMatriz.getLstOrdenCompraDetalle().get(i);

                   oCEComprobanteCompraDetalle.setIdProducto(oCEOrdenCompraDetalle.getIdProducto());
                   oCEComprobanteCompraDetalle.setProducto(oCEOrdenCompraDetalle.getProducto());
                   oCEComprobanteCompraDetalle.setIdUnidadMedidaCompra(oCEOrdenCompraDetalle.getIdUnidadMedidaVenta());
                   oCEComprobanteCompraDetalle.setSiNoImpuesto(oCEOrdenCompraDetalle.isSinoImpuesto());

                    oCEComprobanteCompraDetalle.setIdAbm(1);
                    CEUnidadMedidaProducto oCEUnidadMedidaProducto = new CEUnidadMedidaProducto();
                    oCEUnidadMedidaProducto.setTipoUnidad(oCEOrdenCompraDetalle.getUnidadMedida());
                    oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEOrdenCompraDetalle.getIdUnidadMedidaVenta());
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle, i, oCLComprobanteCompra.colCodigo);
                    TxtProducto.setText(oCEOrdenCompraDetalle.getProducto());
//                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getIdAlmacen(), i, oCLComprobanteCompra.colAlm);
                    TblComprobanteCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getProducto(), i, oCLComprobanteCompra.colProducto);
                    TblComprobanteCompraDetalle.setValueAt(oCEUnidadMedidaProducto, i, oCLComprobanteCompra.colUnidadMedida);
                    TblComprobanteCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getPrecio(), i, oCLComprobanteCompra.colPrecioConIGV);
                    TblComprobanteCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getPrecioConDesc(), i, oCLComprobanteCompra.colPrecioConDesc);
                    TblComprobanteCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getCantidad(), i, oCLComprobanteCompra.colCantidad);
                    TblComprobanteCompraDetalle.setValueAt(CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuento.getModel(), oCEOrdenCompraDetalle.getIdTipoDescuento()), i, oCLComprobanteCompra.colTipoDescuento);
                    TblComprobanteCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getImporteSinDescuentoConIgv(), i, oCLComprobanteCompra.colImporSinDescConIgv);
                    TblComprobanteCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getImportelSinDescuento(), i, oCLComprobanteCompra.colImporSinDesc);
                    TblComprobanteCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getImporteConDescuento(), i, oCLComprobanteCompra.colImporConDesc);
                    TblComprobanteCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getDescuentoEnValores(), i, oCLComprobanteCompra.colDestValores);
                    TblComprobanteCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getExonerado(), i, oCLComprobanteCompra.colExonerado);
                    TblComprobanteCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getDescuento(), i,oCLComprobanteCompra.colDescuento);

                }
            }

            oCLComprobanteCompra.setCalcularImport(true);
            oCLComprobanteCompra.setSinoDescuento(true);
         //   oCLComprobanteCompra.CalcularSubtotales();
          //  oCLComprobanteCompra.eventoDescuento();

        } else {
            JOptionPane.showMessageDialog(this, "El codigo no Existe");
        }
    }

    private void SetComprobanteCompraPorGR( CEGuiaRemisionRecibido oCEGuiaRemisionRecibido ) {

        oCLComprobanteCompra.limpiarTabla();
       // CEOrdenCompraMatriz oCEComprobanteCompraMatriz=new
        if (oCEGuiaRemisionRecibido != null) {
            LblEstado.setText("EMITIDO Y "+oCEGuiaRemisionRecibido.getUltimoEstado());
        //    IdEstado = oCEGuiaRemisionRecibido.getUltimoIdEstado();
            IdEstadoInv=oCEGuiaRemisionRecibido.getUltimoIdEstado();
            IdGuiaRemision=oCEGuiaRemisionRecibido.getIdGuiaRemisionRecibido();
            idProveedor = oCEGuiaRemisionRecibido.getIdProveedor();
            TxtProveedor.setText(oCEGuiaRemisionRecibido.getProveedor());
            TxtDNI.setText(oCEGuiaRemisionRecibido.getRUC());
            TxtDireccion.setText(oCEGuiaRemisionRecibido.getDireccion());
            TxtProducto.setText("");
            TxtSucursal.setText(oCEGuiaRemisionRecibido.getSucursal());
            TxtVendedor.setText(oCEGuiaRemisionRecibido.getEmpleado());
            LblUltimaFecha.setText(oCEGuiaRemisionRecibido.getFecha());
           // ChkCompraDirecta.setSelected(oCEComprobanteCompraMatriz.is);
            TxtNumeroComprob1.setText(NumComprobante);
            if (oCEGuiaRemisionRecibido.getLstGuiaDetalle() != null) {
                for (int i = 0; i < oCEGuiaRemisionRecibido.getLstGuiaDetalle().size(); i++) {
                    Vector oVector = new Vector();
                    ((DefaultTableModel) TblComprobanteCompraDetalle.getModel()).addRow(oVector);

                    CEComprobanteCompraDetalle oCEComprobanteCompraDetalle = new CEComprobanteCompraDetalle();
                    CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle = oCEGuiaRemisionRecibido.getLstGuiaDetalle().get(i);

                   oCEComprobanteCompraDetalle.setIdProducto(oCEGuiaRemisionRecibidoDetalle.getIdProducto());
                   oCEComprobanteCompraDetalle.setProducto(oCEGuiaRemisionRecibidoDetalle.getProducto());
                   oCEComprobanteCompraDetalle.setIdUnidadMedidaCompra(oCEGuiaRemisionRecibidoDetalle.getIdUnidadMedida());

                    oCEComprobanteCompraDetalle.setIdAbm(1);
                    CEUnidadMedidaProducto oCEUnidadMedidaProducto = new CEUnidadMedidaProducto();
                    oCEUnidadMedidaProducto.setTipoUnidad(oCEGuiaRemisionRecibidoDetalle.getUnidadMedida());
                    oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEGuiaRemisionRecibidoDetalle.getIdUnidadMedida());
                    TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle, i, oCLComprobanteCompra.colCodigo);
                    TblComprobanteCompraDetalle.setValueAt(0, i, oCLComprobanteCompra.colPrecioConIGV);
                    TblComprobanteCompraDetalle.setValueAt(CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuento.getModel(), 1), i, oCLComprobanteCompra.colTipoDescuento);
                    TxtProducto.setText(oCEGuiaRemisionRecibidoDetalle.getProducto());
                    TblComprobanteCompraDetalle.setValueAt(oCEGuiaRemisionRecibidoDetalle.getProducto(), i, oCLComprobanteCompra.colProducto);
                    TblComprobanteCompraDetalle.setValueAt(oCEUnidadMedidaProducto, i, oCLComprobanteCompra.colUnidadMedida);
                    TblComprobanteCompraDetalle.setValueAt(oCEGuiaRemisionRecibidoDetalle.getCantidad(), i, oCLComprobanteCompra.colCantidad);                    

                }
            }


        } else {
            JOptionPane.showMessageDialog(this, "El codigo no Existe");
        }
    }
    private void TxtProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtProveedorMouseClicked

        if (evt.getClickCount() == 2) {
            buscarProveedor();
        }
}//GEN-LAST:event_TxtProveedorMouseClicked

    private void TxtProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProveedorKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            buscarProveedor();

        }
}//GEN-LAST:event_TxtProveedorKeyPressed

    private void eventoNuevo()
    {
        limpiarFormulario();
        oCLComprobanteCompra.limpiarTabla();
          pAccionABM = 1;
        oclBotonesABM.controlBoton(false, true, false, false, true, false);
        habilitarControles(true);
        TblComprobanteCompraDetalle.requestFocus();
        TblComprobanteCompraDetalle.changeSelection(0, oCLComprobanteCompra.colProducto, false, false);
    }
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
      eventoNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecepcionActionPerformed


         DlgIngresoProducto1 oDlgIngresoProducto=new DlgIngresoProducto1(oparent, true,true);

                            oDlgIngresoProducto.SetIngresoProductoPorCompra(CCComprobanteCompra.ConsultarComprasPorId(IdComprobanteCompra));
                            oDlgIngresoProducto.setVisible(true);
                            SetComprobanteCompra(CCComprobanteCompra.ConsultarComprasPorId(IdComprobanteCompra));
                            habilitarBotonPorEstado();

    }//GEN-LAST:event_btnRecepcionActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        if(IdEstado==14){
        oclBotonesABM.controlBoton(false, true, false, true, true, false);
        habilitarControles(true);
            if (IdEstadoInv != 13) {
                TblComprobanteCompraDetalle.setEnabled(false);
                BtnQuitar.setEnabled(false);
                BtnAgregar.setEnabled(false);
            }

        pAccionABM = 2;}
        else
        {
            JOptionPane.showMessageDialog(this, "El estado "+LblEstado.getText()+" no se puede Editar");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void CbxTipoDescuentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxTipoDescuentoMouseClicked
    }//GEN-LAST:event_CbxTipoDescuentoMouseClicked

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseReleased

    private void TxtProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtProductoMouseClicked

        if (evt.getClickCount() == 2) {
            BuscarProducto();
        }
    }//GEN-LAST:event_TxtProductoMouseClicked

    private void TxtProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyPressed

        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);

            BuscarProducto();

        }




    }//GEN-LAST:event_TxtProductoKeyPressed

    private void TxtProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyReleased
    }//GEN-LAST:event_TxtProductoKeyReleased

    private void TxtProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyTyped
    }//GEN-LAST:event_TxtProductoKeyTyped
    boolean iscerrando = false;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        iscerrando = true;
    }//GEN-LAST:event_formWindowClosing

    private void TblComprobanteCompraDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblComprobanteCompraDetalleKeyReleased

        if (TblComprobanteCompraDetalle.getSelectedColumn() == oCLComprobanteCompra.colImporSinDescConIgv+1 && (evt.getKeyCode() == evt.VK_ENTER || evt.getKeyCode() == evt.VK_TAB)) {
            if (TblComprobanteCompraDetalle.getSelectedRow() + 1 == TblComprobanteCompraDetalle.getRowCount()) {
                int fila = TblComprobanteCompraDetalle.getSelectedRow() + 1;
                int columnaValidada = ValidarRegistro(fila);
                if (columnaValidada == 0) {
                    agregarNuevaFila(fila);
                    TblComprobanteCompraDetalle.requestFocus();
                    TblComprobanteCompraDetalle.changeSelection(fila, oCLComprobanteCompra.colProducto, false, false);
                } else {
                    TblComprobanteCompraDetalle.requestFocus();
                    TblComprobanteCompraDetalle.changeSelection(TblComprobanteCompraDetalle.getSelectedRow(), columnaValidada, false, false);
                }
                TxtProducto.setText("");

                return;
            }

        }


        if (colValidada != -1) {

            TblComprobanteCompraDetalle.requestFocus();
            TblComprobanteCompraDetalle.changeSelection(TblComprobanteCompraDetalle.getSelectedRow(), colValidada, false, false);
            colValidada = -1;
        }



    }//GEN-LAST:event_TblComprobanteCompraDetalleKeyReleased

    private void TblComprobanteCompraDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblComprobanteCompraDetalleKeyPressed


        if (evt.getKeyCode() == evt.VK_ENTER && TblComprobanteCompraDetalle.getSelectedColumn() == oCLComprobanteCompra.colProducto) {
            BuscarProducto();

        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {


            evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);

            if (TblComprobanteCompraDetalle.getSelectedColumn() == oCLComprobanteCompra.colCantidad-1) {
                TblComprobanteCompraDetalle.changeSelection(TblComprobanteCompraDetalle.getSelectedRow(), oCLComprobanteCompra.colCantidad+1, false, false);
            }

            if (TblComprobanteCompraDetalle.getSelectedColumn() == oCLComprobanteCompra.colCantidad) {
                TblComprobanteCompraDetalle.changeSelection(TblComprobanteCompraDetalle.getSelectedRow(), oCLComprobanteCompra.colImporSinDescConIgv - 1, false, false);
            }

            
           
        }


    }//GEN-LAST:event_TblComprobanteCompraDetalleKeyPressed

    private void verficarTextoVacio(int fila) {
        int col = TblComprobanteCompraDetalle.getSelectedColumn();
        if (fila != -1) {
            if (TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colProducto) != null) {
                String producto = TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colProducto).toString();
                if (producto.trim().equals("")) {
                    if (TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colCodigo) != null) {

                        TblComprobanteCompraDetalle.setValueAt(((CEComprobanteCompraDetalle) TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colCodigo)).getProducto(), fila, oCLComprobanteCompra.colProducto);
                    }

                }
            }
        }
    }
    private void TblComprobanteCompraDetallePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblComprobanteCompraDetallePropertyChange
        if (!iscerrando) {
            if (cont == 1) {

                int fila = TblComprobanteCompraDetalle.getSelectedRow();
                verficarTextoVacio(fila);
                if (fila != -1) {
                    verificarDatos(fila);
                }
            } else {
                cont = 0;
            }
            cont++;
        }
}//GEN-LAST:event_TblComprobanteCompraDetallePropertyChange

    private void TblComprobanteCompraDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblComprobanteCompraDetalleMousePressed
        if (evt.isMetaDown()) // isMetaDown es el click derecho
        {
            if (!evt.isPopupTrigger()) {
            }
        }
}//GEN-LAST:event_TblComprobanteCompraDetalleMousePressed

    private void TblComprobanteCompraDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblComprobanteCompraDetalleMouseClicked
    }//GEN-LAST:event_TblComprobanteCompraDetalleMouseClicked

    private void TxtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDescuentoKeyReleased
        oCLComprobanteCompra.eventoDescuento();
    }//GEN-LAST:event_TxtDescuentoKeyReleased

    private void TxtDescuentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtDescuentoFocusLost
        if (!TxtDescuento.getText().equals("") && TxtDescuento.getText() != null && !TxtDescuento.getText().isEmpty()) {
            TxtDescuento.setText(CLRedondear.RedondearString(Float.parseFloat(TxtDescuento.getText()), 2));
        }
    }//GEN-LAST:event_TxtDescuentoFocusLost

    private void TxtDescuentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtDescuentoFocusGained
        TxtDescuento.selectAll();
    }//GEN-LAST:event_TxtDescuentoFocusGained

    private void CbxMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxMonedaActionPerformed
        TxtTipoCambio.setText("" + oCLComprobanteCompra.ObtenerTipoCambioMoneda(CbxMoneda));
        obtenerAbrevMoneda();
        oCLComprobanteCompra.calcularEquivalenciaMoneda();
    }//GEN-LAST:event_CbxMonedaActionPerformed

    private void TxtNumeroComprob1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNumeroComprob1ActionPerformed

        
    }//GEN-LAST:event_TxtNumeroComprob1ActionPerformed

    private void TxtNumeroComprob1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNumeroComprob1KeyPressed

        if (evt.getKeyCode() == evt.VK_ENTER) {
            GuardarCompra();
        }
    }//GEN-LAST:event_TxtNumeroComprob1KeyPressed

    private void btnBuscarCompCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCompCompActionPerformed

        DlgBusquedaComprobanteCompra odialogo=new DlgBusquedaComprobanteCompra(oparent, true);
        odialogo.setVisible(true);
         CEComprobanteCompraMatriz oCEComprobanteCompraMatriz= odialogo.getCEComprobanteCompraMatriz();

        if(oCEComprobanteCompraMatriz!=null)
        {

            SetComprobanteCompra(CCComprobanteCompra.ConsultarComprasPorId(oCEComprobanteCompraMatriz.getIdComprobanteCompra()));
        }

    }//GEN-LAST:event_btnBuscarCompCompActionPerformed

    private void DtchFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtchFechaPropertyChange

    }//GEN-LAST:event_DtchFechaPropertyChange

    private void btnGuardarActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed1

        GuardarCompra();
}//GEN-LAST:event_btnGuardarActionPerformed1

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
          eventoCancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void ChkPercepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPercepcionActionPerformed

      oCLComprobanteCompra.CalcularSubtotales();
      oCLComprobanteCompra.calcularImportes();
      oCLComprobanteCompra.eventoDescuento();

    }//GEN-LAST:event_ChkPercepcionActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed


        CLExportarExcel oExportar=new CLExportarExcel(TblComprobanteCompraDetalle,
                new String[]{LblEtiqComprob.getText(),((CETipoComprobante)CbxTipoComprobante.getSelectedItem()).getDescripcion(),
                LblEtiqNum.getText(),TxtNumeroComprob1.getText(),LblEtiqEstado.getText(),LblEstado.getText(),
                LblEtiqProveedor.getText(),TxtProveedor.getText(),
                LblEtiqMoneda.getText(),((CEMoneda)CbxMoneda.getSelectedItem()).getDescripcion(),
                LblEtiqTipoCambio.getText(),TxtTipoCambio.getText(),LblEtiqSucursal.getText(),TxtSucursal.getText(),
                LblEtiqUsuario.getText(),TxtVendedor.getText()},
                new String[]{LblEtiqSubtotalBruto.getText(),LblSubtotalBruto.getText(),
                LblEtiqDsctoSubtENValores.getText(),LblDescuentoSubtotal.getText(),
                LblEtiqSubtotalNeto.getText(),LblSubtotalNeto.getText(),
                LblEtiqTipoDescuento.getText(),((CETipoDescuento)CbxTipoDescuento.getSelectedItem()).getDescripcion(),
                LblEtiqDescuento.getText(),TxtDescuento.getText(),LblEtiqDescTotal.getText(),LblDescuentoTotal.getText()
                }
                ,new String[]{LblEtiqSubNTSinIvg.getText(),LblSubTotalNetoSinIgv.getText(),LblEtiqIGV.getText(),LblIgv.getText(),
                LblEtiqISC.getText(),LblISC.getText(),LblEtiqDescValores.getText(),LblDescuentoValores.getText(),
                LblEtiqMontoTotal.getText(),LblMontoTotal.getText(),LblEtiqPercepcion.getText(),lblPercepcion.getText(),
                LblEtiqTotalPagar.getText(),LblMontoTotalPagar.getText()
                }
        ,"Comprobante Compra");
        oExportar.GuardarArchivoExcel(this);

    }//GEN-LAST:event_btnExportar1ActionPerformed

    private void btnImportarOCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarOCActionPerformed
        DlgBusquedaOrdenCompra odialogo=new DlgBusquedaOrdenCompra(oparent, true);
        odialogo.setVisible(true);
         CEOrdenCompraMatriz oCEOrdenCompraMatriz= odialogo.getCEOrdenCompraMatriz();

        if(oCEOrdenCompraMatriz!=null)
        {

            SetComprobanteCompraPorOc(CCOrdenCompra.ConsultarOrdenComprasPorId(oCEOrdenCompraMatriz.getIdOrdenCompra()));
        }
    }//GEN-LAST:event_btnImportarOCActionPerformed

    private void btnImportarGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarGRActionPerformed

         DlgBusquedaGuiaRemisionRecibido odialogo=new DlgBusquedaGuiaRemisionRecibido(oparent, true);
        odialogo.setVisible(true);
         CEGuiaRemisionRecibido oCEGuiaRemisionRecibidoMatriz= odialogo.getCEGuiaRemisionRecibido();

        if(oCEGuiaRemisionRecibidoMatriz!=null)
        {

            SetComprobanteCompraPorGR(CCGuiaRemisionRecibido.ConsultarPorId(oCEGuiaRemisionRecibidoMatriz.getIdGuiaRemisionRecibido()));

        }

    }//GEN-LAST:event_btnImportarGRActionPerformed

    private boolean validarAnulacion() {


            CEEstado oCEEstadoContab=CCEstado.consultarUltimoestadoCompra_contable(IdComprobanteCompra);
            IdEstado=oCEEstadoContab.getmIntIdEstado();
            if(oCEEstadoContab.getmIntIdEstado()!=14)
            {
             JOptionPane.showMessageDialog(this,"El Registro ya fué "+oCEEstadoContab.getmStrDescripcion()+", No se Puede Anular","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
            CEEstado oCEEstadoInvent=CCEstado.consultarUltimoestadoCompra_inventario(IdComprobanteCompra);
            IdEstadoInv=oCEEstadoInvent.getmIntIdEstado();
            if(oCEEstadoInvent.getmIntIdEstado()!=13)
            {
             JOptionPane.showMessageDialog(this,"El Registro ya fué "+oCEEstadoInvent.getmStrDescripcion()+", No se Puede Anular","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }



        return true;
    }
    private void btnAnular1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnular1ActionPerformed
         oclBotonesABM.controlBoton(true, false, false, false, true, false);


        CEComprobanteCompraMatriz oCEComprobanteCompra = new CEComprobanteCompraMatriz();
        oCEComprobanteCompra.setIdComprobanteCompra(IdComprobanteCompra);
        oCEComprobanteCompra.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCEComprobanteCompra.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCEComprobanteCompra.setProveedor(TxtProveedor.getText());
        oCEComprobanteCompra.setNumComprobante(NumComprobante);

        DlgAnularComprobanteCompra oDlgAnularCompra= new DlgAnularComprobanteCompra(oparent,true , oCEComprobanteCompra);
        oDlgAnularCompra.setLocationRelativeTo(null);
        oDlgAnularCompra.setVisible(true);
        if(oDlgAnularCompra.IsAnulado()){
            if(validarAnulacion()){
            if(CCComprobanteCompra.AnularComprobanteCompra(oCEComprobanteCompra)==1)
                 {
                    JOptionPane.showMessageDialog(null, "operación exitosa");
                 }
                else
                 {
                    JOptionPane.showMessageDialog(null, "No se pudo completar la operación");
                 }
            limpiarFormulario();
            habilitarControles(false);
            }
        }
    }//GEN-LAST:event_btnAnular1ActionPerformed

    private void eventoCancelar()
    {
        if (pAccionABM == 1 || pAccionABM == 2) {
            habilitarControles(false);
            oclBotonesABM.controlBoton(true, false, false, false, true, true);
            btnRecepcion.setEnabled(false);
            LblUltimaFecha.setText("");
            pAccionABM = 0;
            limpiarFormulario();
        } else {
            iscerrando = true;
            dispose();
        }
    }

    private void obtenerAbrevMoneda()
{
    CEMoneda oCEMoneda=(CEMoneda)CbxMoneda.getSelectedItem();
        if(oCEMoneda!=null)
        {
//            lblAbrevMon1.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon2.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon3.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon4.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon5.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon6.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon7.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon8.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon9.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon10.setText(oCEMoneda.getAbreviatura());

        }
}

    private void agregarComprobanteCompraDetalle(CEProducto oCEProducto) {
        int fila = TblComprobanteCompraDetalle.getSelectedRow();
        if (oCEProducto != null) {
         CEComprobanteCompraDetalle oCEComprobanteCompraDetalleExist= (CEComprobanteCompraDetalle)TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colCodigo);
         CEComprobanteCompraDetalle oCEComprobanteCompraDetalle = new CEComprobanteCompraDetalle();
        oCEComprobanteCompraDetalle.setIdAbm(1);
        if(oCEComprobanteCompraDetalleExist!=null)
        {
            if(oCEComprobanteCompraDetalleExist.getIdComprobanteCompraDetalle()>0)
            {
                oCEComprobanteCompraDetalle.setIdAbm(2);
                oCEComprobanteCompraDetalle.setIdComprobanteCompraDetalle(oCEComprobanteCompraDetalleExist.getIdComprobanteCompraDetalle());
            }
        }

            oCEComprobanteCompraDetalle.setProducto(oCEProducto.getDescripcion());
            oCEComprobanteCompraDetalle.setIdProducto(oCEProducto.getIdProducto());
          //  oCEComprobanteCompraDetalle.setIdAlmacen(oCEProducto.getIdAlmacen());
            oCEComprobanteCompraDetalle.setIdUnidadMedidaCompra(oCEProducto.getIdUnidadBase());
            oCEComprobanteCompraDetalle.setSiNoImpuesto(oCEProducto.isSiNoImpuesto());
            CEUnidadMedidaProducto oCEUnidadMedidaProducto = new CEUnidadMedidaProducto();
            oCEUnidadMedidaProducto.setTipoUnidad(oCEProducto.getUMP());
            oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEProducto.getIdUnidadBase());
            oCEComprobanteCompraDetalle.setCodigoProducto(oCEProducto.getCodigo());
            CETipoDescuento oTipoDescuento = (CETipoDescuento) CbxTipoDescuento.getSelectedItem();

            TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle, fila, oCLComprobanteCompra.colCodigo);
//            TblComprobanteCompraDetalle.setValueAt(oCEProducto.getStockReal(), fila, oCLComprobanteCompra.colStock);
            TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getProducto(), fila, oCLComprobanteCompra.colProducto);
            TblComprobanteCompraDetalle.setValueAt(oCEUnidadMedidaProducto, fila, oCLComprobanteCompra.colUnidadMedida);
            TblComprobanteCompraDetalle.setValueAt(0, fila, oCLComprobanteCompra.colDescuento);
            TblComprobanteCompraDetalle.setValueAt(oTipoDescuento, fila, oCLComprobanteCompra.colTipoDescuento);
          
       //     TblComprobanteCompraDetalle.setValueAt(oCEComprobanteCompraDetalle.getIdAlmacen(), fila, oCLComprobanteCompra.colAlm);
            float cantidad = 0;
            if (TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colCantidad) != null) {
                cantidad = Float.parseFloat(TblComprobanteCompraDetalle.getValueAt(fila, oCLComprobanteCompra.colCantidad).toString());
            }
            if (cantidad > 0) {
                verificarDatos(fila);
            }

        }

    }

    private void limpiarFormulario() {

        DtchFecha.setDate(null);
        LblEstado.setText("EMITIDO");
        CbxCondicion.setSelectedIndex(0);
        CbxMoneda.setSelectedIndex(0);
        CbxTipoComprobante.setSelectedIndex(1);
        jTextFieldNumber.setText("");
        TxtNumeroComprob1.setText("");
        TxtProducto.setText("");
        TxtProveedor.setText("");
        TxtDireccion.setText("");
        TxtDNI.setText("");
        TxtDescuento.setText("");
        idProveedor = 0;
        oCLComprobanteCompra.limpiarTabla();
        LblDescuentoValores.setText("0");
        LblSubtotalNeto.setText("0");
        LblSubTotalNetoSinIgv.setText("0");
        LblIgv.setText("0");
        LblISC.setText("0");
        LblMontoTotal.setText("0");
        TxtDescuento.setText("0");
        CbxTipoDescuentoComprobanteCompra.setSelectedIndex(0);
        agregarNuevaFila(0);
        ChkPercepcion.setSelected(false);
        TblComprobanteCompraDetalle.requestFocus();
        TblComprobanteCompraDetalle.changeSelection(0, oCLComprobanteCompra.colProducto, false, false);
        TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
        TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
        ChkCompraDirecta.setSelected(false);
        TblComprobanteCompraDetalle.clearSelection();

    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnQuitar;
    private ComboBox.ComboBox CbxCondicion;
    private ComboBox.ComboBox CbxMoneda;
    private ComboBox.ComboBox CbxTipoComprobante;
    private ComboBox.ComboBox CbxTipoDescuento;
    private ComboBox.ComboBox CbxTipoDescuentoComprobanteCompra;
    private javax.swing.JComboBox CbxUnidadMedida;
    private Opcion.CheckBox ChkCompraDirecta;
    private Opcion.CheckBox ChkPercepcion;
    private com.toedter.calendar.JDateChooser DtchFecha;
    private Label.Label LblDescuentoSubtotal;
    private Label.Label LblDescuentoTotal;
    private Label.Label LblDescuentoValores;
    private Label.Label LblEstado;
    private Label.Label LblEtiqComprob;
    private Label.Label LblEtiqCondicion;
    private Label.Label LblEtiqDescTotal;
    private Label.Label LblEtiqDescValores;
    private Label.Label LblEtiqDescuento;
    private Label.Label LblEtiqDireccion;
    private Label.Label LblEtiqDsctoSubtENValores;
    private Label.Label LblEtiqEstado;
    private Label.Label LblEtiqIGV;
    private Label.Label LblEtiqISC;
    private Label.Label LblEtiqMoneda;
    private Label.Label LblEtiqMontoTotal;
    private Label.Label LblEtiqNum;
    private Label.Label LblEtiqPercepcion;
    private Label.Label LblEtiqProveedor;
    private Label.Label LblEtiqRuc;
    private Label.Label LblEtiqSubNTSinIvg;
    private Label.Label LblEtiqSubtotalBruto;
    private Label.Label LblEtiqSubtotalNeto;
    private Label.Label LblEtiqSucursal;
    private Label.Label LblEtiqTipoCambio;
    private Label.Label LblEtiqTipoDescuento;
    private Label.Label LblEtiqTotalPagar;
    private Label.Label LblEtiqUltimaFEcha;
    private Label.Label LblEtiqUsuario;
    private Label.Label LblHoraSistema;
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
    private javax.swing.JTable TblComprobanteCompraDetalle;
    private Label.Label TxtDNI;
    private javax.swing.JTextField TxtDescuento;
    private Label.Label TxtDireccion;
    private TextField.JTxtNinguno TxtNumeroComprob1;
    private TextField.JTxtNinguno TxtProducto;
    private TextField.JTxtLetra TxtProveedor;
    private Label.Label TxtSucursal;
    private Label.Label TxtTipoCambio;
    private Label.Label TxtVendedor;
    private BotonesABM.BtnEliminar btnAnular1;
    private BotonesABM.BtnBuscar btnBuscarCompComp;
    private BotonesABM.BtnCancelar btnCancelar;
    private BotonesABM.BtnEditar btnEditar;
    private BotonesABM.BtnExportar btnExportar1;
    private BotonesABM.BtnGuardar btnGuardar;
    private javax.swing.JButton btnImportarGR;
    private javax.swing.JButton btnImportarOC;
    private BotonesABM.BtnNuevo btnNuevo;
    private BotonesABM.BtnEliminar btnRecepcion;
    private util.clases.vtaVenta.ColumnButton columnButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldNumber;
    private Label.Label label20;
    private Label.Label label22;
    private Label.Label lblPercepcion;
    // End of variables declaration//GEN-END:variables
    private static final String ACTION_CLOSE = "ACTION_CLOSE";
    private static final String ACTION_GUARDAR = "ACTION_NUEVO";

    protected JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke Guardad = KeyStroke.getKeyStroke(KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK);
        KeyStroke VerPrecios = KeyStroke.getKeyStroke(KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK);
        rootPane.registerKeyboardAction(this, ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this, ACTION_GUARDAR, Guardad, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ACTION_CLOSE)) {
            iscerrando = true;
            dispose();
            return;
        }

        if (e.getActionCommand().equals(ACTION_GUARDAR)) {
            GuardarCompra();
            return;
        }


    }
}

