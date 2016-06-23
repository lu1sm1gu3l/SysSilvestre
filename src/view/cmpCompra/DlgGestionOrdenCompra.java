package view.cmpCompra;

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
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.cmpCompra.entidad.CEOrdenCompraDetalle;
import modelo.cmpCompra.entidad.CEOrdenCompraMatriz;
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CECondicion;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CETipoComprobante;
import modelo.vtaVenta.entidad.CETipoDescuento;
import util.clases.almAlmacen.JTableX;
import util.clases.cmpCompra.CLOrdenCompra;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.MiReloj;
import util.clases.grlGeneral.VerificadorDeTxt;
import util.clases.vtaVenta.CLCargarCombo;
import view.FrmSistemaMenu;
import view.almAlmacen.DlgBusquedaProductoCompra;
import view.cmrComercial.DlgGestionProveedor;

/**
 *
 * @author Luiggi
 */
public class DlgGestionOrdenCompra extends DialogoPadre implements ActionListener {

    private int idProveedor = 0;
    private long IdOrdenCompra = 0;
    private int IdEstado = 0;
    private MiReloj hilo;
    private CLOrdenCompra oCLOrdenCompra;
    private int pAccionABM = 0;
    CLBotonesABM oclBotonesABM;
    private  List<CEOrdenCompraDetalle> lstComprasEliminados = new ArrayList<CEOrdenCompraDetalle>();
    private java.awt.Frame oparent;

    /** Creates new form DlgGestionOrdenCompra */
    public DlgGestionOrdenCompra(java.awt.Frame parent, boolean modal) {
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
        TblOrdenCompraDetalle.setSurrendersFocusOnKeystroke(true);

    }

    private void cargarUtilidades() {
        CbxTipoDescuentoOrdenCompra.setModel(CLComboBox.cargarCombo(CCTipoDescuento.listaAll()));
        oCLOrdenCompra = new CLOrdenCompra(TblOrdenCompraDetalle, LblSubtotalNeto, LblIgv,
                LblISC, LblMontoTotal, LblMontoTotalPagar, LblSubTotalNetoSinIgv, LblDescuentoValores,
                LblSubtotalBruto, LblDescuentoSubtotal, LblDescuentoTotal, TxtDescuento, CbxTipoDescuentoOrdenCompra,lblPercepcion,ChkPercepcion);
        CbxTipoComprobante.setModel(CLComboBox.cargarCombo(CCTipoComprobante.listaAll()));
        CbxCondicion.setModel(CLComboBox.cargarCombo(CCCondicion.listaAll()));
        CbxMoneda.setModel(CLComboBox.cargarCombo(CCMoneda.listarMonedaConTipoCambio()));
        CbxTipoDescuento.setModel(CLComboBox.cargarCombo(CCTipoDescuento.listaAll()));
        CbxTipoComprobante.setSelectedIndex(1);
        TblOrdenCompraDetalle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        CbxTipoDescuento.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jTextFieldNumber.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TableColumn columnaTxtProducto = TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colProducto);
        columnaTxtProducto.setCellEditor(new DefaultCellEditor(TxtProducto));
        TableColumn columnaTxtTipoDescuento = TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colTipoDescuento);
        columnaTxtTipoDescuento.setCellEditor(new DefaultCellEditor(CbxTipoDescuento));
        TableColumn columnaUnidadMedida = TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colUnidadMedida);
        columnaUnidadMedida.setCellEditor(new DefaultCellEditor(CbxUnidadMedida));
        DefaultCellEditor oDefaultCellEditor = new DefaultCellEditor(CbxUnidadMedida);
        columnaUnidadMedida.setCellEditor(oDefaultCellEditor);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colCantidad).setCellRenderer(tcr);
        TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colPrecioConDesc).setCellRenderer(tcr);
        TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colPrecioConIGV).setCellRenderer(tcr);
        TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colImporConDesc).setCellRenderer(tcr);
        TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colImporSinDesc).setCellRenderer(tcr);
        TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colImporSinDescConIgv).setCellRenderer(tcr);
        TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colExonerado).setCellRenderer(tcr);
        TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colDescuento).setCellRenderer(tcr);
        TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colDestValores).setCellRenderer(tcr);


        TableColumn columnaCantidad = TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colCantidad);
        columnaCantidad.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaImportbr = TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colImporSinDescConIgv);
        columnaImportbr.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaImportbrConIgv = TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colImporSinDesc);
        columnaImportbrConIgv.setCellEditor(new DefaultCellEditor(jTextFieldNumber));

        TableColumn columnaPrecioConIgv = TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colPrecioConIGV);
        columnaPrecioConIgv.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaDescuento = TblOrdenCompraDetalle.getColumnModel().getColumn(oCLOrdenCompra.colDescuento);
        columnaDescuento.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        hilo = new MiReloj(LblHoraSistema);
        hilo.start();
        CbxTipoDescuento.setSelectedIndex(0);
        CETipoDescuento oTipoDescuento = (CETipoDescuento) CbxTipoDescuento.getSelectedItem();
        TblOrdenCompraDetalle.setValueAt(oTipoDescuento, 0, oCLOrdenCompra.colTipoDescuento);
        TblOrdenCompraDetalle.requestFocus();
        TblOrdenCompraDetalle.changeSelection(0, oCLOrdenCompra.colProducto, false, false);
        oclBotonesABM = new CLBotonesABM();
        oclBotonesABM.setBotones(btnNuevo, btnGuardar, btnAnular, btnEditar, btnCancelar, null,this);
        oclBotonesABM.controlBoton(false, true, false, false, true, false);
        pAccionABM = 1;
        ((JTableX) TblOrdenCompraDetalle).setSelectAllForEdit(true);


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
        LblHoraRegistro1 = new Label.Label();
        LblEtiqFechaRegistro = new Label.Label();
        LblEtiqUsuario = new Label.Label();
        TxtSucursal = new Label.Label();
        TxtTipoCambio = new Label.Label();
        TxtDireccion = new Label.Label();
        TxtDNI = new Label.Label();
        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new BotonesABM.BtnCancelar();
        btnAnular = new BotonesABM.BtnEliminar();
        btnEditar = new BotonesABM.BtnEditar();
        btnGuardar = new BotonesABM.BtnGuardar();
        btnNuevo = new BotonesABM.BtnNuevo();
        LblEtiqTotalPagar = new Label.Label();
        LblMontoTotalPagar = new Label.Label();
        lblPercepcion = new Label.Label();
        LblEtiqPercepcion = new Label.Label();
        LblMontoTotal = new Label.Label();
        LblDescuentoValores = new Label.Label();
        LblEtiqMontoTotal = new Label.Label();
        LblEtiqDescEnValores = new Label.Label();
        LblEtiqIGV = new Label.Label();
        LblEtiqISC = new Label.Label();
        LblISC = new Label.Label();
        LblIgv = new Label.Label();
        LblSubTotalNetoSinIgv = new Label.Label();
        LblEtiqSubNTSinIgv = new Label.Label();
        LblDescuentoTotal = new Label.Label();
        LblEtiqDescTotal = new Label.Label();
        TxtDescuento = new javax.swing.JTextField();
        LblEtiqDescuento = new Label.Label();
        CbxTipoDescuentoOrdenCompra = new ComboBox.ComboBox();
        LblEtiqTipoDescuento = new Label.Label();
        btnExportar1 = new BotonesABM.BtnExportar();
        jPanel3 = new javax.swing.JPanel();
        CbxTipoComprobante = new ComboBox.ComboBox();
        LblEtiqComp = new Label.Label();
        btnBuscarCodigo1 = new BotonesABM.BtnBuscar();
        TxtNumOrdenCompra = new TextField.JTxtNinguno();
        LblEtiqNum = new Label.Label();
        LblEstado = new Label.Label();
        LblEtiqEstado = new Label.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblOrdenCompraDetalle = new util.clases.almAlmacen.JTableX(){
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{
                    if(column==oCLOrdenCompra.colCantidad)
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
                    if(column==oCLOrdenCompra.colDescuento)
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
                    if(this.getRowCount()>row){
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
        ChkPercepcion = new Opcion.CheckBox();
        LblDescuentoSubtotal = new Label.Label();
        LblEtiqSubtotalNeto = new Label.Label();
        LblSubtotalNeto = new Label.Label();
        LblEtiqSubtotalBruto = new Label.Label();
        LblSubtotalBruto = new Label.Label();
        LblEtiqDsctSubtValores = new Label.Label();

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
        setTitle("Orden de Compra");
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

        LblEtiqProveedor.setText("Proovedor:");

        LblEtiqSucursal.setText("Sucursal   :");

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

        LblHoraRegistro1.setText("Hora Registro");
        LblHoraRegistro1.setText("");

        LblEtiqFechaRegistro.setText("Fecha Registro:");

        LblEtiqUsuario.setText("Usuario    :");

        TxtSucursal.setText(" ");
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
                        .addComponent(LblEtiqFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(LblHoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                    .addComponent(LblEtiqFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblHoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MONTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAnular.setText("ANULAR");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        LblEtiqTotalPagar.setText("TOTAL A PAGAR ");
        LblEtiqTotalPagar.setFont(new java.awt.Font("Verdana", 1, 12));

        LblMontoTotalPagar.setForeground(new java.awt.Color(0, 0, 153));
        LblMontoTotalPagar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotalPagar.setText("0.00");
        LblMontoTotalPagar.setFont(new java.awt.Font("Verdana", 1, 15));
        LblMontoTotalPagar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblPercepcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPercepcion.setText("0.00");
        lblPercepcion.setFont(new java.awt.Font("Verdana", 1, 12));
        lblPercepcion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqPercepcion.setText("PERCEPCIÓN");
        LblEtiqPercepcion.setFont(new java.awt.Font("Verdana", 1, 11));

        LblMontoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotal.setText("0.00");
        LblMontoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblMontoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblDescuentoValores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoValores.setText("0.00");
        LblDescuentoValores.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoValores.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqMontoTotal.setText("MONTO TOTAL ");
        LblEtiqMontoTotal.setFont(new java.awt.Font("Verdana", 1, 11));

        LblEtiqDescEnValores.setText("DESC. EN VALORES");
        LblEtiqDescEnValores.setFont(new java.awt.Font("Verdana", 1, 11));

        LblEtiqIGV.setText("I.G.V.");
        LblEtiqIGV.setFont(new java.awt.Font("Verdana", 1, 11));

        LblEtiqISC.setText("I.S.C.");
        LblEtiqISC.setFont(new java.awt.Font("Verdana", 1, 11));

        LblISC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblISC.setText("0.00");
        LblISC.setFont(new java.awt.Font("Verdana", 1, 12));
        LblISC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblIgv.setText("0.00");
        LblIgv.setFont(new java.awt.Font("Verdana", 1, 12));
        LblIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblSubTotalNetoSinIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubTotalNetoSinIgv.setText("0.00");
        LblSubTotalNetoSinIgv.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubTotalNetoSinIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqSubNTSinIgv.setText("SUB. NT. SIN IGV");
        LblEtiqSubNTSinIgv.setFont(new java.awt.Font("Verdana", 1, 11));

        LblDescuentoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoTotal.setText("0");
        LblDescuentoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqDescTotal.setText("Desc. Total:");
        LblEtiqDescTotal.setFont(new java.awt.Font("Verdana", 1, 12));

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

        LblEtiqDescuento.setText("Descuento :");
        LblEtiqDescuento.setFont(new java.awt.Font("Verdana", 1, 12));

        CbxTipoDescuentoOrdenCompra.setFont(new java.awt.Font("Verdana", 0, 11));
        CbxTipoDescuentoOrdenCompra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxTipoDescuentoOrdenCompraItemStateChanged(evt);
            }
        });
        CbxTipoDescuentoOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoDescuentoOrdenCompraActionPerformed(evt);
            }
        });

        LblEtiqTipoDescuento.setText("Tipo Descuento :");
        LblEtiqTipoDescuento.setFont(new java.awt.Font("Verdana", 1, 12));

        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(LblEtiqTipoDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(CbxTipoDescuentoOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(LblEtiqDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(TxtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(LblEtiqDescTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(LblDescuentoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEtiqSubNTSinIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblSubTotalNetoSinIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(650, 650, 650)
                .addComponent(LblEtiqISC, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(LblISC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEtiqDescEnValores, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblDescuentoValores, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblMontoTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEtiqTipoDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxTipoDescuentoOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqDescTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblDescuentoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LblEtiqSubNTSinIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEtiqIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LblSubTotalNetoSinIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEtiqISC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblISC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LblEtiqDescEnValores, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEtiqMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEtiqPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEtiqTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LblDescuentoValores, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lblPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblMontoTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

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

        LblEtiqComp.setText("Comprobante :");

        btnBuscarCodigo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        btnBuscarCodigo1.setText("Buscar Orden Compra");
        btnBuscarCodigo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscarCodigo1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarCodigo1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscarCodigo1.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarCodigo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCodigo1ActionPerformed(evt);
            }
        });

        TxtNumOrdenCompra.setEditable(false);
        TxtNumOrdenCompra.setText("003-123456");
        TxtNumOrdenCompra.setTamanio(20);
        TxtNumOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNumOrdenCompraActionPerformed(evt);
            }
        });
        TxtNumOrdenCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNumOrdenCompraKeyPressed(evt);
            }
        });

        LblEtiqNum.setText("Num :");

        LblEstado.setText("EMITIDO");
        LblEstado.setFont(new java.awt.Font("Verdana", 1, 12));

        LblEtiqEstado.setText("Estado :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(LblEtiqComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblEtiqNum, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtNumOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblEtiqEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNumOrdenCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnBuscarCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(LblEtiqNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(LblEtiqComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblOrdenCompraDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblOrdenCompraDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
                false, false, true, false, true, false, true, true, true, true, true, false, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblOrdenCompraDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblOrdenCompraDetalle.setColumnSelectionAllowed(true);
        TblOrdenCompraDetalle.setRowHeight(21);
        TblOrdenCompraDetalle.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblOrdenCompraDetalle.getTableHeader().setReorderingAllowed(false);
        TblOrdenCompraDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblOrdenCompraDetalleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TblOrdenCompraDetalleMousePressed(evt);
            }
        });
        TblOrdenCompraDetalle.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblOrdenCompraDetallePropertyChange(evt);
            }
        });
        TblOrdenCompraDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblOrdenCompraDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblOrdenCompraDetalleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblOrdenCompraDetalle);
        TblOrdenCompraDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TblOrdenCompraDetalle.getColumnModel().getColumn(0).setPreferredWidth(28);
        TblOrdenCompraDetalle.getColumnModel().getColumn(1).setMinWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(1).setPreferredWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(1).setMaxWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(2).setPreferredWidth(380);
        TblOrdenCompraDetalle.getColumnModel().getColumn(3).setMinWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(3).setPreferredWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(3).setMaxWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
        TblOrdenCompraDetalle.getColumnModel().getColumn(5).setPreferredWidth(80);
        TblOrdenCompraDetalle.getColumnModel().getColumn(6).setPreferredWidth(85);
        TblOrdenCompraDetalle.getColumnModel().getColumn(7).setPreferredWidth(90);
        TblOrdenCompraDetalle.getColumnModel().getColumn(8).setPreferredWidth(95);
        TblOrdenCompraDetalle.getColumnModel().getColumn(9).setMinWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(9).setPreferredWidth(80);
        TblOrdenCompraDetalle.getColumnModel().getColumn(10).setMinWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(10).setPreferredWidth(70);
        TblOrdenCompraDetalle.getColumnModel().getColumn(11).setMinWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(12).setMinWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(13).setMinWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(13).setPreferredWidth(90);
        TblOrdenCompraDetalle.getColumnModel().getColumn(14).setMinWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(14).setPreferredWidth(95);
        TblOrdenCompraDetalle.getColumnModel().getColumn(15).setMinWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(15).setPreferredWidth(0);
        TblOrdenCompraDetalle.getColumnModel().getColumn(15).setMaxWidth(0);

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

        columnButton1.setTbl(TblOrdenCompraDetalle);

        LblHoraSistema.setText("hora");
        LblHoraSistema.setFont(new java.awt.Font("Verdana", 1, 11));

        ChkPercepcion.setText("CON PERCEPCION");
        ChkPercepcion.setFont(new java.awt.Font("Verdana", 1, 12));
        ChkPercepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPercepcionActionPerformed(evt);
            }
        });

        LblDescuentoSubtotal.setForeground(new java.awt.Color(0, 0, 102));
        LblDescuentoSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoSubtotal.setText("0.00");
        LblDescuentoSubtotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoSubtotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqSubtotalNeto.setText("SubTotal Neto");
        LblEtiqSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalNeto.setForeground(new java.awt.Color(0, 0, 102));
        LblSubtotalNeto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalNeto.setText("0.00");
        LblSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalNeto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqSubtotalBruto.setText("SubTotal Bruto:");
        LblEtiqSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalBruto.setForeground(new java.awt.Color(0, 0, 102));
        LblSubtotalBruto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalBruto.setText("0.00");
        LblSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalBruto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqDsctSubtValores.setText("Dsct. Subt. en Valores:");
        LblEtiqDsctSubtValores.setFont(new java.awt.Font("Verdana", 1, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ChkPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(592, 592, 592)
                        .addComponent(LblHoraSistema, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140)
                                .addComponent(LblEtiqSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(LblEtiqDsctSubtValores, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(LblEtiqSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChkPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnAgregar)
                            .addComponent(BtnQuitar)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(LblEtiqSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(LblEtiqDsctSubtValores, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(LblEtiqSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarProveedor() {
        if (TxtProveedor.isEnabled()) {
            DlgGestionProveedor odialogo = new DlgGestionProveedor(null, true, 1, 1, 1);//consultar

            odialogo.setCajaTexo(TxtProveedor.getText());
            odialogo.setVisible(true);
            odialogo.setTitle("Busqueda de Proveedor");
            if (odialogo.getoCEProveedor() != null) {
                idProveedor = odialogo.getoCEProveedor().getIdProveedor();
                TxtProveedor.setText(odialogo.getoCEProveedor().getRazonSocial());
                TxtDNI.setText(odialogo.getoCEProveedor().getRUC());
                TxtDireccion.setText(odialogo.getoCEProveedor().getDireccion());
                TblOrdenCompraDetalle.requestFocus();
                TblOrdenCompraDetalle.changeSelection(0, oCLOrdenCompra.colProducto, false, false);
            }
        }

    }

    private void habilitarControles(boolean bol) {
        TxtProveedor.setEnabled(bol);
        TblOrdenCompraDetalle.setEnabled(bol);
        BtnAgregar.setEnabled(bol);
        BtnQuitar.setEnabled(bol);
        CbxTipoDescuentoOrdenCompra.setEnabled(bol);
        TxtDescuento.setEnabled(bol);
        CbxTipoComprobante.setEnabled(bol);
        TxtProducto.setEnabled(bol);
        CbxMoneda.setEnabled(bol);
        ChkPercepcion.setEnabled(bol);
//        ChkIsImpuestoAntes.setEnabled(bol);
    }

    private int ValidarRegistro(int fila) {
        if (TblOrdenCompraDetalle.getRowCount() > 0) {

            int numColumna = 0;
            CEOrdenCompraDetalle oCEOrdenCompraDetalle = (CEOrdenCompraDetalle) TblOrdenCompraDetalle.getValueAt(fila - 1, oCLOrdenCompra.colCodigo);
            String mensaje = "Seleccione un producto";
            if (oCEOrdenCompraDetalle != null) {
                if (TblOrdenCompraDetalle.getValueAt(fila - 1, oCLOrdenCompra.colCantidad) != null) {
                    float cantidad = Float.parseFloat(TblOrdenCompraDetalle.getValueAt(fila - 1, oCLOrdenCompra.colCantidad).toString());
                    if (cantidad < 0) {
                        numColumna = oCLOrdenCompra.colCantidad;
                        colValidada = oCLOrdenCompra.colCantidad;
                        mensaje = "Ingrese una cantidad mayor a cero";
                    } else {
                        float precio = 0;
                        if (TblOrdenCompraDetalle.getValueAt(fila - 1, oCLOrdenCompra.colPrecioConIGV) != null) {
                            precio = VerificadorDeTxt.convertFloat(TblOrdenCompraDetalle.getValueAt(fila - 1, oCLOrdenCompra.colPrecioConIGV).toString());
                        }
//                        if (precio == 0) {
//                            numColumna = oCLOrdenCompra.colPrecioConIGV;
//                            colValidada = oCLOrdenCompra.colPrecioConIGV;
//                            mensaje = "Ingrese un Precio";
//                        } else
                            if (TblOrdenCompraDetalle.getValueAt(fila - 1, oCLOrdenCompra.colDescuento) != null) {
                            float descuentodet = Float.parseFloat(TblOrdenCompraDetalle.getValueAt(fila - 1, oCLOrdenCompra.colDescuento).toString());
                            float subTotalSindesc = 0;
                            if (TblOrdenCompraDetalle.getValueAt(fila - 1, oCLOrdenCompra.colImporSinDesc) != null) {
                                subTotalSindesc = Float.parseFloat(TblOrdenCompraDetalle.getValueAt(fila - 1, oCLOrdenCompra.colImporSinDesc).toString());
                            }

                            if (descuentodet > subTotalSindesc) {
                                mensaje = "El descuento no puede ser mayor al Subtotal";
                                numColumna = oCLOrdenCompra.colDescuento;
                                colValidada = oCLOrdenCompra.colDescuento;
                            }
                        }
                    }
                } else {
                    numColumna = oCLOrdenCompra.colCantidad;
                    colValidada = oCLOrdenCompra.colCantidad;
                    mensaje = "Ingrese una cantidad";
                }
            } else {
                numColumna = oCLOrdenCompra.colProducto;
                colValidada = oCLOrdenCompra.colProducto;

            }
            if (numColumna != 0) {
//            if(numColumna!=oCLOrdenCompra.colPrecio){
                JOptionPane.showMessageDialog(this, mensaje);
//            }else
//                { int filat=TblOrdenCompraDetalle.getSelectedRow();
//                  oCLOrdenCompra.calcularPrecio(filat);
//                }
            }

            return numColumna;

        }
        return 0;
    }

    private void BuscarProducto() {
        DlgBusquedaProductoCompra odialogo = new DlgBusquedaProductoCompra(null, true,0,0);
        String NombreProd = TxtProducto.getText().trim();
        btnGuardar.requestFocus();
        if (NombreProd.equals("")) {
            if (TblOrdenCompraDetalle.getValueAt(TblOrdenCompraDetalle.getSelectedRow(), oCLOrdenCompra.colProducto) != null) {
                NombreProd = TblOrdenCompraDetalle.getValueAt(TblOrdenCompraDetalle.getSelectedRow(), oCLOrdenCompra.colProducto) + "";
            }
        }
        odialogo.setCajaTexto(NombreProd);
        TxtProducto.setText("");
//        odialogo.setListaOrdenCompra(this.ObtenerRegistrosAcuales());
        odialogo.setVisible(true);
        CEProducto oCEProducto = odialogo.getProducto();
        if (!odialogo.getisagregarCantidadOrdenCompra()) {
            agregarOrdenCompraDetalle(oCEProducto, odialogo.getListaPrecio());

            int fila = TblOrdenCompraDetalle.getSelectedRow();
            TblOrdenCompraDetalle.requestFocus();
            TblOrdenCompraDetalle.changeSelection(fila, oCLOrdenCompra.colCantidad, false, false);

        } else {
            TblOrdenCompraDetalle.requestFocus();
            SeleccionarOrdenCompraExistente(oCEProducto);
        }


    }

    private void SeleccionarOrdenCompraExistente(CEProducto oCEProducto) {
        CEOrdenCompraDetalle oCEOrdenCompraDetalle;
        for (int i = 0; i < TblOrdenCompraDetalle.getRowCount(); i++) {
//cx
            oCEOrdenCompraDetalle = (CEOrdenCompraDetalle) TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colCodigo);
            if (oCEOrdenCompraDetalle != null) {

                if (oCEOrdenCompraDetalle.getIdUnidadMedidaVenta() == oCEProducto.getIdUnidadBase()
                        && oCEOrdenCompraDetalle.getIdProducto() == oCEProducto.getIdProducto()) {
                    TblOrdenCompraDetalle.requestFocus();
                    TblOrdenCompraDetalle.changeSelection(i, oCLOrdenCompra.colUnidadMedida, false, false);
                }

            }
        }
    }

    private List<CEOrdenCompraDetalle> ObtenerRegistrosAcuales() {
        List<CEOrdenCompraDetalle> lstOrdenCompraDetalleTemp = new ArrayList<CEOrdenCompraDetalle>();
        CEOrdenCompraDetalle oCEOrdenCompraDetalle;
        for (int i = 0; i < TblOrdenCompraDetalle.getRowCount(); i++) {
            oCEOrdenCompraDetalle = (CEOrdenCompraDetalle) TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colCodigo);
            if (oCEOrdenCompraDetalle != null) {
                if (i != TblOrdenCompraDetalle.getSelectedRow()) {
                    oCEOrdenCompraDetalle.setPrecio(VerificadorDeTxt.convertFloat(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colPrecioConIGV).toString()));
                    oCEOrdenCompraDetalle.setCantidad(0);
                    if (TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colCantidad) != null) {
                        oCEOrdenCompraDetalle.setCantidad(VerificadorDeTxt.convertFloat(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colCantidad).toString()));
                    }
                    CEOrdenCompraDetalle oCEOrdenCompraDetalleTemp = (CEOrdenCompraDetalle) TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colCodigo);
                    oCEOrdenCompraDetalle.setIdProducto(oCEOrdenCompraDetalleTemp.getIdProducto());
                    lstOrdenCompraDetalleTemp.add(oCEOrdenCompraDetalle);
                }
            }
        }
        return lstOrdenCompraDetalleTemp;
    }

    private boolean ValidarTablaOrdenCompra() {


        for (int i = 0; i < TblOrdenCompraDetalle.getRowCount(); i++) {
            verificarDatos(i);

            if (colValidada != -1) {
                TblOrdenCompraDetalle.requestFocus();
                TblOrdenCompraDetalle.changeSelection(i, colValidada, false, false);
                colValidada = -1;
                return false;
            }
        }
        eventoGuardar = false;
        return true;
    }
    boolean eventoGuardar = false;
    private void eventoGuardarCompra()
    {
        oCLOrdenCompra.CalcularSubtotales();
        oCLOrdenCompra.calcularImportes();
        oCLOrdenCompra.eventoDescuento();
        
        int fil=TblOrdenCompraDetalle.getRowCount();
      if(fil>0){
          int row=fil-1;
              CEOrdenCompraDetalle oCEOrdenCompraDetalle= (CEOrdenCompraDetalle)TblOrdenCompraDetalle.getValueAt(row, oCLOrdenCompra.colCodigo);
              if(oCEOrdenCompraDetalle==null){

                oCLOrdenCompra.QuitarFilaSel(row);

              }
        }
        fil=TblOrdenCompraDetalle.getRowCount();
      if(fil>0){

        eventoGuardar = true;
        int columnaValidada = ValidarRegistro(TblOrdenCompraDetalle.getRowCount());
        if (columnaValidada == 0) {

            GuardarRegistro();

        } else {
            TblOrdenCompraDetalle.requestFocus();
            TblOrdenCompraDetalle.changeSelection(TblOrdenCompraDetalle.getRowCount() - 1, columnaValidada, false, false);
        }

        }
        else
        {
               JOptionPane.showMessageDialog(this,"Ingrese un Item","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                 agregarNuevaFila(0);
                 TblOrdenCompraDetalle.requestFocus();
                 TblOrdenCompraDetalle.changeSelection(0, oCLOrdenCompra.colProducto, false, false);
        }
        
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        eventoGuardarCompra();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private boolean validar() {
        if (idProveedor == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese un Proveedor", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            buscarProveedor();
            return false;
        }
        if (TblOrdenCompraDetalle.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese OrdenCompra", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            agregarNuevaFila(0);
            TblOrdenCompraDetalle.requestFocus();
            TblOrdenCompraDetalle.changeSelection(0, oCLOrdenCompra.colProducto, false, false);
            return false;
        }

        if(pAccionABM==2)
        {
            CEEstado oCEEstadoContab=CCEstado.consultarUltimoestadoOrdenCompra(IdOrdenCompra);
            
            if(oCEEstadoContab.getmIntIdEstado()!=1)
            {
             JOptionPane.showMessageDialog(this,"La orden ya fué "+oCEEstadoContab.getmStrDescripcion()+", No se Puede Modificar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
            
        }
        return true;
    }

    private void GuardarRegistro() {
        if (ValidarTablaOrdenCompra()) {
            if (validar()) {
                if (pAccionABM == 1) {
                    CEOrdenCompraMatriz oCEOrdenCompraInsert = CCOrdenCompra.InsOrdenCompra(this.getOrdenCompra());
                    if (oCEOrdenCompraInsert != null) {

                        
                        JOptionPane.showMessageDialog(this,"<html><h3>El código de OrdenCompra es: " + oCEOrdenCompraInsert.getCodigo()
                                                                    + " <br> El monto a pagar es   : "
                                                                    + LblMontoTotalPagar.getText() + "S/.<h3></html>");                                               
                        limpiarFormulario();
                        LblHoraRegistro1.setText("");
                        TblOrdenCompraDetalle.requestFocus();
                        TblOrdenCompraDetalle.changeSelection(0, oCLOrdenCompra.colProducto, false, false);

                    } else {

                        JOptionPane.showMessageDialog(this, "Operación Fallida");
                    }
                } else {
                    int iscorrecto = CCOrdenCompra.UPDOrdenCompra(this.getOrdenCompra());
                    if (iscorrecto == 1) {
                        JOptionPane.showMessageDialog(this, "<html><CENTER>Operación exitosa</CENTER></html>");
                        limpiarFormulario();
                        pAccionABM = 1;
                    } else {
                        JOptionPane.showMessageDialog(this, "Operación Fallida");
                    }
                }
            }
        }
    }

    private CEOrdenCompraMatriz getOrdenCompra() {
        CEOrdenCompraMatriz oCEOrdenCompra = new CEOrdenCompraMatriz();

        oCEOrdenCompra.setIdOrdenCompra(IdOrdenCompra);
        CETipoComprobante oCETipoComprobante = (CETipoComprobante) CbxTipoComprobante.getSelectedItem();
        oCEOrdenCompra.setIdTipoComprobante(oCETipoComprobante.getIdTipoComprobante());
        CEMoneda oCEMoneda = (CEMoneda) CbxMoneda.getSelectedItem();
        oCEOrdenCompra.setIdMoneda(oCEMoneda.getId_moneda());
        CECondicion oCECondcion = (CECondicion) CbxCondicion.getSelectedItem();
        oCEOrdenCompra.setIdCondicion(oCECondcion.getIdCondicion());
        oCEOrdenCompra.setTipoCambio(Float.parseFloat(TxtTipoCambio.getText()));
        oCEOrdenCompra.setProveedor(TxtProveedor.getText());
        oCEOrdenCompra.setRUC(TxtDNI.getText());
        oCEOrdenCompra.setDireccion(TxtDireccion.getText());
        CETipoDescuento oCETipoDescuento = (CETipoDescuento) CbxTipoDescuentoOrdenCompra.getSelectedItem();

        oCEOrdenCompra.setIdSucursal(FrmSistemaMenu.getIdSucursal());
        oCEOrdenCompra.setIdUltimoEstado(1);
        oCEOrdenCompra.setIdTipoDescuento(oCETipoDescuento.getIdTipoDescuento());
        oCEOrdenCompra.setIdProveedor(idProveedor);
        oCEOrdenCompra.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCEOrdenCompra.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCEOrdenCompra.setSubtotalBruto(Float.parseFloat(LblSubtotalBruto.getText()));
        oCEOrdenCompra.setDescuentoEnSubtotal(Float.parseFloat(LblDescuentoSubtotal.getText()));
        oCEOrdenCompra.setSubtotalNeto(Float.parseFloat(LblSubtotalNeto.getText()));
        oCEOrdenCompra.setDescuento(oCLOrdenCompra.getDescuento());
        oCEOrdenCompra.setDescuentoEnValores(Float.parseFloat(LblDescuentoValores.getText()));
        oCEOrdenCompra.setSubTotalNetoSinIGV(Float.parseFloat(LblSubTotalNetoSinIgv.getText()));
        oCEOrdenCompra.setIGV(Float.parseFloat(LblIgv.getText()));
        oCEOrdenCompra.setISC(Float.parseFloat(LblISC.getText()));
        oCEOrdenCompra.setIGVActual(oCLOrdenCompra.getIgvActual());
        oCEOrdenCompra.setDescuentoTotal(Float.parseFloat(LblDescuentoTotal.getText()));
        oCEOrdenCompra.setMontoTotal(Float.parseFloat(LblMontoTotal.getText()));
        oCEOrdenCompra.setTotalPagar(Float.parseFloat(LblMontoTotalPagar.getText()));
        oCEOrdenCompra.setMontoPercepcion(Float.parseFloat(lblPercepcion.getText()));
        oCEOrdenCompra.setConPercepcion(ChkPercepcion.isSelected());
//        oCEOrdenCompra.setImpuestoDespues(ChkIsImpuestoAntes.isSelected());
        List<CEOrdenCompraDetalle> lstOrdenCompraDetalle = new ArrayList<CEOrdenCompraDetalle>();
        CEOrdenCompraDetalle oCEOrdenCompraDetalle;
        for (int i = 0; i < TblOrdenCompraDetalle.getRowCount(); i++) {

            oCEOrdenCompraDetalle = (CEOrdenCompraDetalle) TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colCodigo);
            if (oCEOrdenCompraDetalle != null) {
                CETipoDescuento oCETipoDescuentoDet = (CETipoDescuento) TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colTipoDescuento);
                oCEOrdenCompraDetalle.setIdTipoDescuento(oCETipoDescuentoDet.IdTipoDescuento);
                if(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colPrecioConIGV)==null)
                {TblOrdenCompraDetalle.setValueAt(0, i, oCLOrdenCompra.colPrecioConIGV);}
                oCEOrdenCompraDetalle.setPrecio(Float.parseFloat(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colPrecioConIGV).toString()));
                oCEOrdenCompraDetalle.setPrecioConDesc(Float.parseFloat(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colPrecioConDesc).toString()));
                oCEOrdenCompraDetalle.setCantidad(Float.parseFloat(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colCantidad).toString()));              
                oCEOrdenCompraDetalle.setImporteSinDescuento(Float.parseFloat(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colImporSinDesc).toString()));
                oCEOrdenCompraDetalle.setImporteSinDescuentoConIgv(Float.parseFloat(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colImporSinDescConIgv).toString()));
                oCEOrdenCompraDetalle.setUnidadMedida(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colUnidadMedida).toString());
                if (TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colImporConDesc) != null) {
                    oCEOrdenCompraDetalle.setImporteConDescuento(Float.parseFloat(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colImporConDesc).toString()));
                }
                if (TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colExonerado) != null) {
                    oCEOrdenCompraDetalle.setExonerado(Float.parseFloat(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colExonerado).toString()));
                }
                if (TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colDescuento) == null) {
                    TblOrdenCompraDetalle.setValueAt("0", i, oCLOrdenCompra.colDescuento);
                }
                oCEOrdenCompraDetalle.setDescuento(Float.parseFloat(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colDescuento).toString()));
                float descuentoValores = 0;
                if (TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colDestValores) != null) {
                    descuentoValores = Float.parseFloat(TblOrdenCompraDetalle.getValueAt(i, oCLOrdenCompra.colDestValores).toString());
                }
                oCEOrdenCompraDetalle.setDescuentoEnValores(descuentoValores);
                lstOrdenCompraDetalle.add(oCEOrdenCompraDetalle);
            }
        }

        if(pAccionABM==2)
                {
                    lstOrdenCompraDetalle.addAll(lstComprasEliminados);
                }
        
        oCEOrdenCompra.setLstOrdenCompraDetalle(lstOrdenCompraDetalle);

        return oCEOrdenCompra;
    }

    private void agregarNuevaFila(int fila) {

        Vector oVector = new Vector();
        ((DefaultTableModel) TblOrdenCompraDetalle.getModel()).addRow(oVector);

        if(fila!=0){
            if(TblOrdenCompraDetalle.getValueAt(fila-1, oCLOrdenCompra.colPrecioConIGV)==null)
                    {TblOrdenCompraDetalle.setValueAt(0, fila-1, oCLOrdenCompra.colPrecioConIGV);
            }
        }

        CbxTipoDescuento.setSelectedIndex(0);
        CETipoDescuento oTipoDescuento = (CETipoDescuento) CbxTipoDescuento.getSelectedItem();

        TblOrdenCompraDetalle.requestFocus();

        TblOrdenCompraDetalle.setValueAt(oTipoDescuento, fila, oCLOrdenCompra.colTipoDescuento);


    }
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        eventoAgregar();

    }//GEN-LAST:event_BtnAgregarActionPerformed
    private void eventoAgregar() {
        int columnaValidada = ValidarRegistro(TblOrdenCompraDetalle.getRowCount());
        if (columnaValidada == 0) {
            agregarNuevaFila(TblOrdenCompraDetalle.getRowCount());
            TblOrdenCompraDetalle.requestFocus();
            TblOrdenCompraDetalle.changeSelection(TblOrdenCompraDetalle.getRowCount()-1, oCLOrdenCompra.colProducto, false, false);


        } else {
            TblOrdenCompraDetalle.requestFocus();
            TblOrdenCompraDetalle.changeSelection(TblOrdenCompraDetalle.getRowCount() - 1, columnaValidada, false, false);
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

        int rowsel=TblOrdenCompraDetalle.getSelectedRow();
        if(rowsel!=-1){
                       CEOrdenCompraDetalle oCEOrdenCompraDetalle= (CEOrdenCompraDetalle)TblOrdenCompraDetalle.getValueAt(rowsel, oCLOrdenCompra.colCodigo);
                    if(oCEOrdenCompraDetalle!=null){
                        if(oCEOrdenCompraDetalle.getIdOrdenCompraDetalle()>0)
                        {
                            oCEOrdenCompraDetalle.setIdAbm(3);
                            lstComprasEliminados.add(oCEOrdenCompraDetalle);
                        }
                    }
                    oCLOrdenCompra.QuitarFila();
                }
        else JOptionPane.showMessageDialog(this,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);

}

    private void CbxCondicionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxCondicionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxCondicionItemStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (pAccionABM == 1 || pAccionABM == 2) {
            habilitarControles(false);
            oclBotonesABM.controlBoton(true, false, false, false, true, true);
            LblHoraRegistro1.setText("");
            pAccionABM = 0;
            limpiarFormulario();
        } else {
            iscerrando = true;
            dispose();
        }


    }//GEN-LAST:event_btnCancelarActionPerformed

    private void CbxTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteActionPerformed
    }//GEN-LAST:event_CbxTipoComprobanteActionPerformed

    private void CbxTipoComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteItemStateChanged
        CETipoComprobante oCETipoComprobante = (CETipoComprobante) CbxTipoComprobante.getSelectedItem();
        if (oCETipoComprobante != null) {
            oCLOrdenCompra.CalcularSubtotales();
            oCLOrdenCompra.eventoDescuento();
            oCLOrdenCompra.calcularImportes();

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
            if (TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colCantidad) != null) {
                cantidad = Float.parseFloat(TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colCantidad).toString());
            }
            if (TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colPrecioConIGV) != null) {
                precio = Float.parseFloat(TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colPrecioConIGV).toString());
            }
            if (TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colDescuento) != null) {
                descuento = Float.parseFloat(TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colDescuento).toString());
            }

            if (precio < 0) {
                JOptionPane.showMessageDialog(this, "el precio debe ser mayor a cero");
                TblOrdenCompraDetalle.setValueAt(null, fila, oCLOrdenCompra.colPrecioConIGV);
                TblOrdenCompraDetalle.setValueAt(null, fila, oCLOrdenCompra.colImporSinDesc);
                TblOrdenCompraDetalle.setValueAt(null, fila, oCLOrdenCompra.colImporSinDescConIgv);
                colValidada = oCLOrdenCompra.colPrecioConIGV;
                return;
            }

            if (descuento > cantidad * precio) {
                JOptionPane.showMessageDialog(this, "El descuento no puede ser mayor al Importe Bruto");
                TblOrdenCompraDetalle.setValueAt(null, fila, oCLOrdenCompra.colDescuento);
                descuento = 0;
                TblOrdenCompraDetalle.setValueAt(CLRedondear.Redondear((cantidad * precio) - descuento, 2), fila, oCLOrdenCompra.colImporConDesc);//con
                oCLOrdenCompra.CalcularSubtotales();
                oCLOrdenCompra.calcularImportes();

                colValidada = oCLOrdenCompra.colDescuento;
                return;
            }

            if (descuento < 0) {
                JOptionPane.showMessageDialog(this, "El descuento no puede ser menor a cero");
                TblOrdenCompraDetalle.setValueAt(null, fila, oCLOrdenCompra.colDescuento);
                descuento = 0;
                TblOrdenCompraDetalle.setValueAt(CLRedondear.Redondear((cantidad * precio) - descuento, 2), fila, oCLOrdenCompra.colImporConDesc);//con
                colValidada = oCLOrdenCompra.colDescuento;
                return;
            }

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero");
                TblOrdenCompraDetalle.setValueAt(null, fila, oCLOrdenCompra.colCantidad);
                colValidada = oCLOrdenCompra.colCantidad;
                return;
            }
            /*      if(precio<=0)
            {
            JOptionPane.showMessageDialog(null,"El precio tiene q ser mayor a cero");
            colValidada=oCLOrdenCompra.colPrecio;
            return;
            }*/
            if (TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colStock) != null) {
                stock = Float.parseFloat(TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colStock).toString());
            }
            if (cantidad > stock) {
                /* JOptionPane.showMessageDialog(null,"La cantidad no debe ser mayor al stock disponible");
                TblOrdenCompraDetalle.setValueAt(null, fila,oCLOrdenCompra.colCantidad);
                colValidada=oCLOrdenCompra.colCantidad;
                return;*/
            }

            int col = TblOrdenCompraDetalle.getSelectedColumn();
            if (!eventoGuardar) {
                if (col == oCLOrdenCompra.colCantidad) {
                    oCLOrdenCompra.calcularPrecio(fila);
                }
            }

        } catch (Exception e) {
            cont++;
            System.out.println("dlgGestionOrdenCompra-metodo Verificar datos: "+e);
        }
    }
    private String Codigo;
    
    private void SetOrdenCompra( CEOrdenCompraMatriz oCEOrdenCompraMatriz ) {

        oCLOrdenCompra.limpiarTabla();
        if (oCEOrdenCompraMatriz != null) {
            oCLOrdenCompra.setCalcularImport(false);
            oCLOrdenCompra.setSinoDescuento(false);

            LblEstado.setText(oCEOrdenCompraMatriz.getUltimoEstado());
            IdEstado = oCEOrdenCompraMatriz.getIdUltimoEstado();
            ChkPercepcion.setSelected(oCEOrdenCompraMatriz.isConPercepcion());
            TxtNumOrdenCompra.setText(oCEOrdenCompraMatriz.getCodigo());
            LblSubtotalBruto.setText("" + oCEOrdenCompraMatriz.getSubtotalBruto());
            LblDescuentoSubtotal.setText("" + oCEOrdenCompraMatriz.getDescuentoEnSubtotal());
            TxtDescuento.setText("" + oCEOrdenCompraMatriz.getDescuento());
            LblSubtotalNeto.setText(oCEOrdenCompraMatriz.getSubtotalNeto() + "");
            LblSubTotalNetoSinIgv.setText(oCEOrdenCompraMatriz.getSubtotalNetoSinIGV() + "");
            LblDescuentoValores.setText(oCEOrdenCompraMatriz.getDescuentoValores() + "");
            LblDescuentoTotal.setText(oCEOrdenCompraMatriz.getDescuentoTotal()+"");
            LblIgv.setText(oCEOrdenCompraMatriz.getIGV() + "");
            LblISC.setText(oCEOrdenCompraMatriz.getISC() + "");
            LblMontoTotal.setText(oCEOrdenCompraMatriz.getMontoTotal()+"");
            lblPercepcion.setText(CLRedondear.RedondearString(oCEOrdenCompraMatriz.getMontoPercepcion(),2));
            LblMontoTotalPagar.setText(CLRedondear.RedondearString(oCEOrdenCompraMatriz.getTotalPagar(),2));
            IdOrdenCompra = oCEOrdenCompraMatriz.getIdOrdenCompra();
            idProveedor = oCEOrdenCompraMatriz.getIdProveedor();
            TxtProveedor.setText(oCEOrdenCompraMatriz.getProveedor());
            TxtDNI.setText(oCEOrdenCompraMatriz.getRUC());
            TxtDireccion.setText(oCEOrdenCompraMatriz.getDireccion());
            TxtProducto.setText("");
            TxtSucursal.setText(oCEOrdenCompraMatriz.getSucursal());
            TxtVendedor.setText(oCEOrdenCompraMatriz.getEmpleado());
            LblHoraRegistro1.setText(oCEOrdenCompraMatriz.getFecha());
            CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuentoOrdenCompra, oCEOrdenCompraMatriz.getIdDescuento());
            CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante, oCEOrdenCompraMatriz.getIdTipoComprobante());
            CLCargarCombo.buscarIdMoneda(CbxMoneda,oCEOrdenCompraMatriz.getIdMoneda());
            
            if (oCEOrdenCompraMatriz.getLstOrdenCompraDetalle() != null) {
                for (int i = 0; i < oCEOrdenCompraMatriz.getLstOrdenCompraDetalle().size(); i++) {

                    Vector oVector = new Vector();

                    ((DefaultTableModel) TblOrdenCompraDetalle.getModel()).addRow(oVector);

                    CEOrdenCompraDetalle oCEOrdenCompraDetalle = oCEOrdenCompraMatriz.getLstOrdenCompraDetalle().get(i);

                    oCEOrdenCompraDetalle.setIdAbm(2);
                    CEUnidadMedidaProducto oCEUnidadMedidaProducto = new CEUnidadMedidaProducto();
                    oCEUnidadMedidaProducto.setTipoUnidad(oCEOrdenCompraDetalle.getUnidadMedida());
                    oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEOrdenCompraDetalle.getIdUnidadMedidaVenta());
                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle, i, oCLOrdenCompra.colCodigo);
                    TxtProducto.setText(oCEOrdenCompraDetalle.getProducto());
//                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getIdAlmacen(), i, oCLOrdenCompra.colAlm);
                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getProducto(), i, oCLOrdenCompra.colProducto);
                    TblOrdenCompraDetalle.setValueAt(oCEUnidadMedidaProducto, i, oCLOrdenCompra.colUnidadMedida);
                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getPrecio(), i, oCLOrdenCompra.colPrecioConIGV);                    
                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getPrecioConDesc(), i, oCLOrdenCompra.colPrecioConDesc);
                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getCantidad(), i, oCLOrdenCompra.colCantidad);
                    TblOrdenCompraDetalle.setValueAt(CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuento.getModel(), oCEOrdenCompraDetalle.getIdTipoDescuento()), i, oCLOrdenCompra.colTipoDescuento);
                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getImporteSinDescuentoConIgv(), i, oCLOrdenCompra.colImporSinDescConIgv);
                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getImportelSinDescuento(), i, oCLOrdenCompra.colImporSinDesc);
                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getImporteConDescuento(), i, oCLOrdenCompra.colImporConDesc);
                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getDescuentoEnValores(), i, oCLOrdenCompra.colDestValores);
                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getExonerado(), i, oCLOrdenCompra.colExonerado);
                    TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getDescuento(), i,oCLOrdenCompra.colDescuento);
 
                }

            }


           // oCLOrdenCompra.CalcularSubtotales();
           // oCLOrdenCompra.eventoDescuento();



            habilitarControles(false);
            oclBotonesABM.controlBoton(false, false, true, true, true, false);
            if (IdEstado != 1) {
                btnAnular.setEnabled(false);
                btnEditar.setEnabled(false);
            }

            pAccionABM = 2;
            btnEditar.requestFocus();
            oCLOrdenCompra.setSinoDescuento(true);
            oCLOrdenCompra.setCalcularImport(true);
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

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        pAccionABM = 1;
        oclBotonesABM.controlBoton(false, true, false, false, true, false);
        habilitarControles(true);
        limpiarFormulario();
        oCLOrdenCompra.limpiarTabla();
        TblOrdenCompraDetalle.requestFocus();
        TblOrdenCompraDetalle.changeSelection(0, oCLOrdenCompra.colProducto, false, false);
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed

        oclBotonesABM.controlBoton(true, false, false, false, true, false);
        CEOrdenCompraMatriz oCEOrdenCompra = new CEOrdenCompraMatriz();
        oCEOrdenCompra.setIdOrdenCompra(IdOrdenCompra);
        oCEOrdenCompra.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCEOrdenCompra.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCEOrdenCompra.setProveedor(TxtProveedor.getText());
        oCEOrdenCompra.setCodigo(Codigo);


        DlgAnularOrdenCompra oDlgAnularOrdenCompra= new DlgAnularOrdenCompra(null,true , oCEOrdenCompra);
        oDlgAnularOrdenCompra.setLocationRelativeTo(null);
        oDlgAnularOrdenCompra.setVisible(true);
        limpiarFormulario();
        habilitarControles(false);

    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        if(IdEstado==1){
        oclBotonesABM.controlBoton(false, true, false, true, true, false);
        habilitarControles(true);
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

    private void TblOrdenCompraDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblOrdenCompraDetalleKeyReleased

        if (TblOrdenCompraDetalle.getSelectedColumn() == oCLOrdenCompra.colImporSinDescConIgv+1 && (evt.getKeyCode() == evt.VK_ENTER || evt.getKeyCode() == evt.VK_TAB)) {
            if (TblOrdenCompraDetalle.getSelectedRow() + 1 == TblOrdenCompraDetalle.getRowCount()) {
                int fila = TblOrdenCompraDetalle.getSelectedRow() + 1;
                int columnaValidada = ValidarRegistro(fila);
                if (columnaValidada == 0) {
                    agregarNuevaFila(fila);
                    TblOrdenCompraDetalle.requestFocus();
                    TblOrdenCompraDetalle.changeSelection(fila, oCLOrdenCompra.colProducto, false, false);
                } else {
                    TblOrdenCompraDetalle.requestFocus();
                    TblOrdenCompraDetalle.changeSelection(TblOrdenCompraDetalle.getSelectedRow(), columnaValidada, false, false);
                }
                TxtProducto.setText("");

                return;
            }

        }


        if (colValidada != -1) {

            TblOrdenCompraDetalle.requestFocus();
            TblOrdenCompraDetalle.changeSelection(TblOrdenCompraDetalle.getSelectedRow(), colValidada, false, false);
            colValidada = -1;
        }



    }//GEN-LAST:event_TblOrdenCompraDetalleKeyReleased

    private void TblOrdenCompraDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblOrdenCompraDetalleKeyPressed


        if (evt.getKeyCode() == evt.VK_ENTER && TblOrdenCompraDetalle.getSelectedColumn() == oCLOrdenCompra.colProducto) {
            BuscarProducto();

        }
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {


            evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);

            if (TblOrdenCompraDetalle.getSelectedColumn() == oCLOrdenCompra.colCantidad-1) {
                TblOrdenCompraDetalle.changeSelection(TblOrdenCompraDetalle.getSelectedRow(), oCLOrdenCompra.colCantidad+1, false, false);
            }

            if (TblOrdenCompraDetalle.getSelectedColumn() == oCLOrdenCompra.colCantidad) {
                TblOrdenCompraDetalle.changeSelection(TblOrdenCompraDetalle.getSelectedRow(), oCLOrdenCompra.colImporSinDescConIgv - 1, false, false);
            }

            
           
        }


    }//GEN-LAST:event_TblOrdenCompraDetalleKeyPressed

    private void verficarTextoVacio(int fila) {
        int col = TblOrdenCompraDetalle.getSelectedColumn();
        if (fila != -1) {
            if (TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colProducto) != null) {
                String producto = TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colProducto).toString();
                if (producto.trim().equals("")) {
                    if (TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colCodigo) != null) {

                        TblOrdenCompraDetalle.setValueAt(((CEOrdenCompraDetalle) TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colCodigo)).getProducto(), fila, oCLOrdenCompra.colProducto);
                    }

                }
            }
        }
    }
    private void TblOrdenCompraDetallePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblOrdenCompraDetallePropertyChange
        if (!iscerrando) {
            if (cont == 1) {

                int fila = TblOrdenCompraDetalle.getSelectedRow();
                verficarTextoVacio(fila);
                if (fila != -1) {
                    verificarDatos(fila);
                }
            } else {
                cont = 0;
            }
            cont++;
        }
}//GEN-LAST:event_TblOrdenCompraDetallePropertyChange

    private void TblOrdenCompraDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblOrdenCompraDetalleMousePressed
        if (evt.isMetaDown()) // isMetaDown es el click derecho
        {
            if (!evt.isPopupTrigger()) {
            }
        }
}//GEN-LAST:event_TblOrdenCompraDetalleMousePressed

    private void TblOrdenCompraDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblOrdenCompraDetalleMouseClicked
    }//GEN-LAST:event_TblOrdenCompraDetalleMouseClicked

    private void CbxMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxMonedaActionPerformed
        TxtTipoCambio.setText("" + oCLOrdenCompra.ObtenerTipoCambioMoneda(CbxMoneda));
        obtenerAbrevMoneda();
        oCLOrdenCompra.calcularEquivalenciaMoneda();
    }//GEN-LAST:event_CbxMonedaActionPerformed

    private void btnBuscarCodigo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCodigo1ActionPerformed

        DlgBusquedaOrdenCompra odialogo=new DlgBusquedaOrdenCompra(oparent, true);
        odialogo.setVisible(true);
         CEOrdenCompraMatriz oCEOrdenCompraMatriz= odialogo.getCEOrdenCompraMatriz();

        if(oCEOrdenCompraMatriz!=null)
        {

            SetOrdenCompra(CCOrdenCompra.ConsultarOrdenComprasPorId(oCEOrdenCompraMatriz.getIdOrdenCompra()));
        }
        

       
        

    }//GEN-LAST:event_btnBuscarCodigo1ActionPerformed

    private void ChkPercepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPercepcionActionPerformed

        oCLOrdenCompra.CalcularSubtotales();
        oCLOrdenCompra.calcularImportes();
        oCLOrdenCompra.eventoDescuento();
    }//GEN-LAST:event_ChkPercepcionActionPerformed

    private void TxtNumOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNumOrdenCompraActionPerformed

    }//GEN-LAST:event_TxtNumOrdenCompraActionPerformed

    private void TxtNumOrdenCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNumOrdenCompraKeyPressed

        if (evt.getKeyCode() == evt.VK_ENTER) {
            eventoGuardarCompra();
        }
}//GEN-LAST:event_TxtNumOrdenCompraKeyPressed

    private void TxtDescuentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtDescuentoFocusGained
        TxtDescuento.selectAll();
}//GEN-LAST:event_TxtDescuentoFocusGained

    private void TxtDescuentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtDescuentoFocusLost
        if (!TxtDescuento.getText().equals("") && TxtDescuento.getText() != null && !TxtDescuento.getText().isEmpty()) {
            TxtDescuento.setText(CLRedondear.RedondearString(Float.parseFloat(TxtDescuento.getText()), 2));
        }
}//GEN-LAST:event_TxtDescuentoFocusLost

    private void TxtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDescuentoKeyReleased
        oCLOrdenCompra.eventoDescuento();
}//GEN-LAST:event_TxtDescuentoKeyReleased

    private void CbxTipoDescuentoOrdenCompraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxTipoDescuentoOrdenCompraItemStateChanged

}//GEN-LAST:event_CbxTipoDescuentoOrdenCompraItemStateChanged

    private void CbxTipoDescuentoOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoDescuentoOrdenCompraActionPerformed
        oCLOrdenCompra.CalcularSubtotales();
        oCLOrdenCompra.calcularImportes();
        oCLOrdenCompra.eventoDescuento();
}//GEN-LAST:event_CbxTipoDescuentoOrdenCompraActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed


        CLExportarExcel oExportar=new CLExportarExcel(TblOrdenCompraDetalle,
                new String[]{LblEtiqComp.getText(),((CETipoComprobante)CbxTipoComprobante.getSelectedItem()).getDescripcion(),
                LblEtiqNum.getText(),TxtNumOrdenCompra.getText(),LblEtiqEstado.getText(),LblEstado.getText(),
                LblEtiqProveedor.getText(),TxtProveedor.getText(),
                LblEtiqMoneda.getText(),((CEMoneda)CbxMoneda.getSelectedItem()).getDescripcion(),
                LblEtiqTipoCambio.getText(),TxtTipoCambio.getText(),LblEtiqSucursal.getText(),TxtSucursal.getText(),
                LblEtiqUsuario.getText(),TxtVendedor.getText()},
                new String[]{LblEtiqSubtotalBruto.getText(),LblSubtotalBruto.getText(),
                LblEtiqDsctSubtValores.getText(),LblDescuentoSubtotal.getText(),
                LblEtiqSubtotalNeto.getText(),LblSubtotalNeto.getText(),
                LblEtiqTipoDescuento.getText(),((CETipoDescuento)CbxTipoDescuento.getSelectedItem()).getDescripcion(),
                LblEtiqDescuento.getText(),TxtDescuento.getText(),LblEtiqDescTotal.getText(),LblDescuentoTotal.getText()
                }
                ,new String[]{LblEtiqSubNTSinIgv.getText(),LblSubTotalNetoSinIgv.getText(),LblEtiqIGV.getText(),LblIgv.getText(),
                LblEtiqISC.getText(),LblISC.getText(),LblEtiqDescEnValores.getText(),LblDescuentoValores.getText(),
                LblEtiqMontoTotal.getText(),LblMontoTotal.getText(),LblEtiqPercepcion.getText(),lblPercepcion.getText(),
                LblEtiqTotalPagar.getText(),LblMontoTotalPagar.getText()
                }
        ,"Comprobante Compra");
        oExportar.GuardarArchivoExcel(this);
        
}//GEN-LAST:event_btnExportar1ActionPerformed

    private void obtenerAbrevMoneda()
{
    
}

    private void agregarOrdenCompraDetalle(CEProducto oCEProducto, List<CEProductoPrecio> olistaPrecio) {
        int fila = TblOrdenCompraDetalle.getSelectedRow();
        if (oCEProducto != null) {

         CEOrdenCompraDetalle oCEOrdenCompraDetalleExist= (CEOrdenCompraDetalle)TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colCodigo);
         CEOrdenCompraDetalle oCEOrdenCompraDetalle = new CEOrdenCompraDetalle();
        oCEOrdenCompraDetalle.setIdAbm(1);
        if(oCEOrdenCompraDetalleExist!=null)
        {
            if(oCEOrdenCompraDetalleExist.getIdOrdenCompraDetalle()>0)
            {
                oCEOrdenCompraDetalle.setIdAbm(2);
                oCEOrdenCompraDetalle.setIdOrdenCompraDetalle(oCEOrdenCompraDetalleExist.getIdOrdenCompraDetalle());
            }
        }

            oCEOrdenCompraDetalle.setProducto(oCEProducto.getDescripcion());
            oCEOrdenCompraDetalle.setIdProducto(oCEProducto.getIdProducto());
          //  oCEOrdenCompraDetalle.setIdAlmacen(oCEProducto.getIdAlmacen());
            oCEOrdenCompraDetalle.setIdUnidadMedidaVenta(oCEProducto.getIdUnidadBase());
            oCEOrdenCompraDetalle.setSinoImpuesto(oCEProducto.isSiNoImpuesto());
            CEUnidadMedidaProducto oCEUnidadMedidaProducto = new CEUnidadMedidaProducto();
            oCEUnidadMedidaProducto.setTipoUnidad(oCEProducto.getUMP());
            oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEProducto.getIdUnidadBase());
            oCEOrdenCompraDetalle.setCodigoProducto(oCEProducto.getCodigo());
            CETipoDescuento oTipoDescuento = (CETipoDescuento) CbxTipoDescuento.getSelectedItem();

            TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle, fila, oCLOrdenCompra.colCodigo);
//            TblOrdenCompraDetalle.setValueAt(oCEProducto.getStockReal(), fila, oCLOrdenCompra.colStock);
            TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getProducto(), fila, oCLOrdenCompra.colProducto);
            TblOrdenCompraDetalle.setValueAt(oCEUnidadMedidaProducto, fila, oCLOrdenCompra.colUnidadMedida);
            TblOrdenCompraDetalle.setValueAt(0, fila, oCLOrdenCompra.colDescuento);
            TblOrdenCompraDetalle.setValueAt(oTipoDescuento, fila, oCLOrdenCompra.colTipoDescuento);
       //     TblOrdenCompraDetalle.setValueAt(oCEOrdenCompraDetalle.getIdAlmacen(), fila, oCLOrdenCompra.colAlm);
            float cantidad = 0;
            if (TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colCantidad) != null) {
                cantidad = Float.parseFloat(TblOrdenCompraDetalle.getValueAt(fila, oCLOrdenCompra.colCantidad).toString());
            }
            if (cantidad > 0) {
                verificarDatos(fila);
            }

        }

    }

    private void limpiarFormulario() {

        LblEstado.setText("EMITIDO");
        CbxCondicion.setSelectedIndex(0);
        CbxMoneda.setSelectedIndex(0);
        CbxTipoComprobante.setSelectedIndex(1);
        jTextFieldNumber.setText("");
        TxtProducto.setText("");
        TxtProveedor.setText("");
        TxtDireccion.setText("");
        TxtDNI.setText("");
        TxtDescuento.setText("");
        idProveedor = 0;
        oCLOrdenCompra.limpiarTabla();
        LblDescuentoValores.setText("0");
        LblSubtotalNeto.setText("0");
        LblSubTotalNetoSinIgv.setText("0");
        LblIgv.setText("0");
        LblISC.setText("0");
        LblMontoTotal.setText("0");
        TxtDescuento.setText("0");
        CbxTipoDescuentoOrdenCompra.setSelectedIndex(0);
        agregarNuevaFila(0);
        TblOrdenCompraDetalle.requestFocus();
        TblOrdenCompraDetalle.changeSelection(0, oCLOrdenCompra.colProducto, false, false);
        TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
        TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
        TblOrdenCompraDetalle.clearSelection();
        ChkPercepcion.setSelected(false);
        TxtNumOrdenCompra.setText("");

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
    private ComboBox.ComboBox CbxTipoDescuentoOrdenCompra;
    private javax.swing.JComboBox CbxUnidadMedida;
    private Opcion.CheckBox ChkPercepcion;
    private Label.Label LblDescuentoSubtotal;
    private Label.Label LblDescuentoTotal;
    private Label.Label LblDescuentoValores;
    private Label.Label LblEstado;
    private Label.Label LblEtiqComp;
    private Label.Label LblEtiqCondicion;
    private Label.Label LblEtiqDescEnValores;
    private Label.Label LblEtiqDescTotal;
    private Label.Label LblEtiqDescuento;
    private Label.Label LblEtiqDireccion;
    private Label.Label LblEtiqDsctSubtValores;
    private Label.Label LblEtiqEstado;
    private Label.Label LblEtiqFechaRegistro;
    private Label.Label LblEtiqIGV;
    private Label.Label LblEtiqISC;
    private Label.Label LblEtiqMoneda;
    private Label.Label LblEtiqMontoTotal;
    private Label.Label LblEtiqNum;
    private Label.Label LblEtiqPercepcion;
    private Label.Label LblEtiqProveedor;
    private Label.Label LblEtiqRuc;
    private Label.Label LblEtiqSubNTSinIgv;
    private Label.Label LblEtiqSubtotalBruto;
    private Label.Label LblEtiqSubtotalNeto;
    private Label.Label LblEtiqSucursal;
    private Label.Label LblEtiqTipoCambio;
    private Label.Label LblEtiqTipoDescuento;
    private Label.Label LblEtiqTotalPagar;
    private Label.Label LblEtiqUsuario;
    private Label.Label LblHoraRegistro1;
    private Label.Label LblHoraSistema;
    private Label.Label LblISC;
    private Label.Label LblIgv;
    private Label.Label LblMontoTotal;
    private Label.Label LblMontoTotalPagar;
    private Label.Label LblSubTotalNetoSinIgv;
    private Label.Label LblSubtotalBruto;
    private Label.Label LblSubtotalNeto;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JTable TblOrdenCompraDetalle;
    private Label.Label TxtDNI;
    private javax.swing.JTextField TxtDescuento;
    private Label.Label TxtDireccion;
    private TextField.JTxtNinguno TxtNumOrdenCompra;
    private TextField.JTxtNinguno TxtProducto;
    private TextField.JTxtLetra TxtProveedor;
    private Label.Label TxtSucursal;
    private Label.Label TxtTipoCambio;
    private Label.Label TxtVendedor;
    private BotonesABM.BtnEliminar btnAnular;
    private BotonesABM.BtnBuscar btnBuscarCodigo1;
    private BotonesABM.BtnCancelar btnCancelar;
    private BotonesABM.BtnEditar btnEditar;
    private BotonesABM.BtnExportar btnExportar1;
    private BotonesABM.BtnGuardar btnGuardar;
    private BotonesABM.BtnNuevo btnNuevo;
    private util.clases.vtaVenta.ColumnButton columnButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldNumber;
    private Label.Label label20;
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
            eventoGuardarCompra();
            return;
        }


    }
}
