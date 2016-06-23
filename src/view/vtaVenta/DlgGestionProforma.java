package view.vtaVenta;

import controller.almAlmacen.CCProductoPrecio;
import controller.grlGeneral.CCAtencionSistema;
import controller.grlGeneral.CCEstado;
import controller.vtaVenta.CCCondicion;
import controller.vtaVenta.CCMoneda;
import controller.vtaVenta.CCProforma;
import controller.vtaVenta.CCTipoComprobante;
import controller.vtaVenta.CCTipoDescuento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
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
import modelo.almAlmacen.entidad.CEAlmacenProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.cmrComercial.entidad.CECliente;
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CECondicion;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CEProformaDetalle;
import modelo.vtaVenta.entidad.CEProformaMatriz;
import modelo.vtaVenta.entidad.CETipoComprobante;
import modelo.vtaVenta.entidad.CETipoDescuento;
import util.clases.almAlmacen.JTableX;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.MiReloj;
import util.clases.grlGeneral.SetterGetter;
import util.clases.grlGeneral.VerificadorDeTxt;
import util.clases.vtaVenta.CLCargarCombo;
import util.clases.vtaVenta.CLPedido;
import view.FrmSistemaMenu;
import view.almAlmacen.DlgBusquedaProducto;
import view.cmrComercial.DlgGestionCliente;
import view.vtaVenta.rptVTAReportes.view.DlgReporteProforma;

/**
 *
 * @author Luiggi
 */
public class DlgGestionProforma extends DialogoPadre implements  ActionListener{

   private  long idCliente=0;
   private String Cliente="";
   private  int IdProforma=0;
   private  int IdEstado=0;
   private MiReloj hilo;
   private  CLPedido oCLPedido;
   private  int pAccionABM=0;
   private  CLBotonesABM oclBotonesABM;
   private  List<CEProformaDetalle> lstProformaEliminados = new ArrayList<CEProformaDetalle>();
   private java.awt.Frame oparent;
   private boolean guardarConFecha;

    /** Creates new form DlgGestionProforma */
    public DlgGestionProforma(java.awt.Frame parent, boolean modal) {
        super(parent, false);
        initComponents();
        guardarConFecha=false;
        this.setLocationRelativeTo(null);
        oparent=parent;
        TxtTipoCambio.setText("1.0");
        LblUltimoNumeroProforma.setText("");
        CbxUnidadMedida.setEditable(true);
        jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero",0,jTextFieldNumber));
        TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
        TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
        cargarUtilidades();
        TblProformaDetalle.setSurrendersFocusOnKeystroke(true);
//        TblProformaDetalle.setAutoCreateRowSorter(true);
        DtchFecha.setDate(new Date());
        DtchFecha.setEnabled(false);
        DtchFecha.setVisible(false);
        chkFecha.setVisible(false);

    }

    

    private void cargarUtilidades()
    {
        CbxTipoDescuentoProforma.setModel(CLComboBox.cargarCombo(CCTipoDescuento.listaAll()));
        oCLPedido = new CLPedido(TblProformaDetalle,LblSubtotalNeto,LblIgv,
                                LblISC,LblMontoTotal,LblMontoTotalPagar,LblSubTotalNetoSinIgv,LblDescuentoValores,
                                LblSubtotalBruto,LblDescuentoSubtotal,LblDescuentoTotal,TxtDescuento,CbxTipoDescuentoProforma,lblMontoPercepcion,chkPercepcion);
        CbxTipoComprobante.setModel(CLComboBox.cargarCombo(CCTipoComprobante.listaProforma()));
        CbxCondicion.setModel(CLComboBox.cargarCombo(CCCondicion.listaAll()));
        CbxMoneda.setModel(CLComboBox.cargarCombo(CCMoneda.listarMonedaConTipoCambio()));
        CbxTipoDescuento.setModel(CLComboBox.cargarCombo(CCTipoDescuento.listaAll()));
        TblProformaDetalle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        CbxTipoDescuento.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jTextFieldNumber.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TableColumn columnaTxtProducto = TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colProducto);
        columnaTxtProducto.setCellEditor(new DefaultCellEditor(TxtProducto));
        TableColumn columnaTxtTipoDescuento = TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colTipoDescuento);
        columnaTxtTipoDescuento.setCellEditor(new DefaultCellEditor(CbxTipoDescuento));
        TableColumn columnaUnidadMedida = TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colUnidadMedida);
        columnaUnidadMedida.setCellEditor(new DefaultCellEditor(CbxUnidadMedida));
        DefaultCellEditor oDefaultCellEditor=new DefaultCellEditor(CbxUnidadMedida);
        columnaUnidadMedida.setCellEditor(oDefaultCellEditor);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colCantidad).setCellRenderer(tcr);
        TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colPrecio).setCellRenderer(tcr);
        TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colImporConDesc).setCellRenderer(tcr);
        TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colImporSinDesc).setCellRenderer(tcr);
        TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colExonerado).setCellRenderer(tcr);
        TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colDescuento).setCellRenderer(tcr);
        TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colDestValores).setCellRenderer(tcr);
        TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colPrecioConDesc).setCellRenderer(tcr);
        TableColumn columnaCantidad = TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colCantidad);
        columnaCantidad.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaDescuento = TblProformaDetalle.getColumnModel().getColumn(oCLPedido.colDescuento);
        columnaDescuento.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        hilo= new MiReloj(LblHoraSistema);
        hilo.start();
        CbxTipoDescuento.setSelectedIndex(0);
        CETipoDescuento oTipoDescuento=(CETipoDescuento)CbxTipoDescuento.getSelectedItem();
        TblProformaDetalle.setValueAt(oTipoDescuento, 0, oCLPedido.colTipoDescuento);
        TblProformaDetalle.requestFocus();
        TblProformaDetalle.changeSelection(0,oCLPedido.colProducto, false, false);
        oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(btnNuevo, btnGuardar, btnAnular, null, btnCancelar, null,this);
        pAccionABM=1;
//        if(FrmSistemaMenu.isSiNoAtencion())
//        {
        oclBotonesABM.controlBoton(false, true, false, false, true, false);
//        }
//        else
//        {
    //    oclBotonesABM.controlBoton(false, false, false, false, false, false);
        
        ((JTableX)TblProformaDetalle).setSelectAllForEdit(true);
    }
        List<ArrayList<CEEquivalenciaUnidad>> LstLstEquivalencia= new ArrayList<ArrayList<CEEquivalenciaUnidad>>();

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CbxTipoDescuento = new ComboBox.ComboBox();
        CbxUnidadMedida = new javax.swing.JComboBox();
        JmMenuTablaProforma = new javax.swing.JPopupMenu();
        JMAgregar = new javax.swing.JMenuItem();
        JMEliminar = new javax.swing.JMenuItem();
        JMVerPrecios = new javax.swing.JMenuItem();
        JMModificaPrecio = new javax.swing.JMenuItem();
        jTextFieldNumber = new javax.swing.JTextField();
        TxtProducto = new TextField.JTxtNinguno();
        PnlDatos2 = new javax.swing.JPanel();
        LblEtiqCliente = new Label.Label();
        LblEtiqSucursal = new Label.Label();
        TxtVendedor = new Label.Label();
        LblEtiqCondicion = new Label.Label();
        CbxCondicion = new ComboBox.ComboBox();
        CbxMoneda = new ComboBox.ComboBox();
        LblEtiqMoneda = new Label.Label();
        LblEtiqTipoCambio = new Label.Label();
        LblEtiqDNI = new Label.Label();
        LblEtiqDireccion = new Label.Label();
        TxtCliente = new TextField.JTxtLetra();
        TxtlHoraRegistro1 = new Label.Label();
        LblEtiqHoraRegistro = new Label.Label();
        lblEtiqVendedor = new Label.Label();
        TxtSucursal = new Label.Label();
        TxtTipoCambio = new Label.Label();
        TxtDireccion = new Label.Label();
        TxtDNI = new Label.Label();
        jPanel1 = new javax.swing.JPanel();
        LblPorcentaje = new Label.Label();
        LblEtiqTipoDescuento = new Label.Label();
        CbxTipoDescuentoProforma = new ComboBox.ComboBox();
        LblEtiqDescuento = new Label.Label();
        LblEtiqDescTotal = new Label.Label();
        LblDescuentoTotal = new Label.Label();
        btnCancelar = new BotonesABM.BtnCancelar();
        btnAnular = new BotonesABM.BtnEliminar();
        btnEditar = new BotonesABM.BtnEditar();
        btnGuardar = new BotonesABM.BtnGuardar();
        btnNuevo = new BotonesABM.BtnNuevo();
        TxtDescuento = new javax.swing.JTextField();
        LblEtiqMontoTotal = new Label.Label();
        LblMontoTotal = new Label.Label();
        LblIgv = new Label.Label();
        LblEtiqIgv = new Label.Label();
        LblISC = new Label.Label();
        LblEtiqISC = new Label.Label();
        LblEtiqSubNetoSinIgv = new Label.Label();
        LblSubTotalNetoSinIgv = new Label.Label();
        LblDescuentoValores = new Label.Label();
        LblEtiqDescValores = new Label.Label();
        LblEtiqPercepcion = new Label.Label();
        LblEtiqTotalPagar = new Label.Label();
        LblMontoTotalPagar = new Label.Label();
        lblMontoPercepcion = new Label.Label();
        btnExportar1 = new BotonesABM.BtnExportar();
        BtnImprimir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        CbxTipoComprobante = new ComboBox.ComboBox();
        lblEtiqTipoComp = new Label.Label();
        TxtEstado = new Label.Label();
        lblEtiqEstado = new Label.Label();
        btnBuscarPedido = new BotonesABM.BtnBuscar();
        DtchFechaVencimiento =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        lblEtiqEstado1 = new Label.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblProformaDetalle = new util.clases.almAlmacen.JTableX(){
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{

                    if(column==oCLPedido.colPrecio||column==oCLPedido.colPrecioConDesc)
                    {
                        if(value!=null)
                        {
                            if(!value.toString().trim().equals("")||!value.toString().isEmpty())
                            {
                                super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),4),row,column);
                            }
                            else{
                                super.setValueAt(null,row,column);
                            }
                        }
                        else
                        {
                            super.setValueAt(null,row,column);

                        }

                    }
                    else if(column==oCLPedido.colCantidad)
                    {
                        if(value!=null)
                        {
                            if(!value.toString().trim().equals("")||!value.toString().isEmpty())
                            {
                                super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),2),row,column);
                            }
                            else{
                                super.setValueAt(null,row,column);
                            }
                        }
                        else
                        {
                            super.setValueAt(null,row,column);

                        }

                    }
                    else if(column==oCLPedido.colDescuento||column==oCLPedido.colImporConDesc||column==oCLPedido.colImporSinDesc||column==oCLPedido.colExonerado||column==oCLPedido.colDestValores)
                    {
                        if(value!=null){
                            if(!value.toString().trim().equals("")||!value.toString().isEmpty())
                            {
                                super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),2),row,column);
                            }
                            else
                            {
                                super.setValueAt("0.00",row,column);
                            }
                        }else
                        {
                            super.setValueAt(null,row,column);
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
                    System.out.println("*Constructor tabla - CLPedido : "+ e);
                }
            }

        };
        label20 = new Label.Label();
        BtnAgregar = new javax.swing.JButton();
        BtnQuitar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        columnButton1 = new util.clases.vtaVenta.ColumnButton();
        LblHoraSistema = new Label.Label();
        LblEtiqNum = new Label.Label();
        BtnActualizar = new javax.swing.JButton();
        LblUltimoNumeroProforma = new javax.swing.JTextField();
        chkFecha = new Opcion.CheckBox();
        DtchFecha =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        LblEtiqSubtotalBruto = new Label.Label();
        LblSubtotalBruto = new Label.Label();
        LblEtiqDsctSubtValores = new Label.Label();
        LblDescuentoSubtotal = new Label.Label();
        LblEtiqSubtotalNeto = new Label.Label();
        LblSubtotalNeto = new Label.Label();
        chkPercepcion = new Opcion.CheckBox();

        CbxTipoDescuento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CbxTipoDescuentoMouseClicked(evt);
            }
        });

        CbxUnidadMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        JMAgregar.setFont(new java.awt.Font("Verdana", 0, 12));
        JMAgregar.setText("Agregar Nuevo Registro");
        JMAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMAgregarActionPerformed(evt);
            }
        });
        JmMenuTablaProforma.add(JMAgregar);

        JMEliminar.setFont(new java.awt.Font("Verdana", 0, 12));
        JMEliminar.setText("Eliminar Registro");
        JMEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMEliminarActionPerformed(evt);
            }
        });
        JmMenuTablaProforma.add(JMEliminar);

        JMVerPrecios.setFont(new java.awt.Font("Verdana", 0, 12));
        JMVerPrecios.setText("Ver Precios");
        JMVerPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMVerPreciosActionPerformed(evt);
            }
        });
        JmMenuTablaProforma.add(JMVerPrecios);

        JMModificaPrecio.setFont(new java.awt.Font("Verdana", 0, 12));
        JMModificaPrecio.setLabel("Modificar Precio");
        JMModificaPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMModificaPrecioActionPerformed(evt);
            }
        });
        JmMenuTablaProforma.add(JMModificaPrecio);

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
        setTitle("Proformas");
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

        LblEtiqCliente.setText("Cliente     :");

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

        LblEtiqTipoCambio.setText("Tipo Cambio    :");

        LblEtiqDNI.setText("DNI             :");

        LblEtiqDireccion.setText("Direccion  :");

        TxtCliente.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtCliente.setTamanio(200);
        TxtCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtClienteMouseClicked(evt);
            }
        });
        TxtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtClienteKeyPressed(evt);
            }
        });

        TxtlHoraRegistro1.setText("00/00/00");
        TxtlHoraRegistro1.setText("");

        LblEtiqHoraRegistro.setText("Fecha Registro:");

        lblEtiqVendedor.setText("Vendedor :");

        TxtSucursal.setText(" ");
        TxtSucursal.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtTipoCambio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TxtTipoCambio.setText("1.0");

        TxtDireccion.setText("---");

        javax.swing.GroupLayout PnlDatos2Layout = new javax.swing.GroupLayout(PnlDatos2);
        PnlDatos2.setLayout(PnlDatos2Layout);
        PnlDatos2Layout.setHorizontalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(LblEtiqDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(LblEtiqCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(CbxCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(lblEtiqVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(LblEtiqMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(LblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(LblEtiqHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtlHoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(LblEtiqTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        PnlDatos2Layout.setVerticalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(LblEtiqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEtiqDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(LblEtiqCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(LblEtiqMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LblEtiqTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(CbxCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEtiqVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtlHoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LblEtiqHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MONTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel1.setLayout(null);

        LblPorcentaje.setText("V");
        LblPorcentaje.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(LblPorcentaje);
        LblPorcentaje.setBounds(440, 20, 20, 16);

        LblEtiqTipoDescuento.setText("TIPO DESCUENTO");
        LblEtiqTipoDescuento.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqTipoDescuento);
        LblEtiqTipoDescuento.setBounds(10, 20, 111, 15);

        CbxTipoDescuentoProforma.setFont(new java.awt.Font("Verdana", 0, 11));
        CbxTipoDescuentoProforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoDescuentoProformaActionPerformed(evt);
            }
        });
        jPanel1.add(CbxTipoDescuentoProforma);
        CbxTipoDescuentoProforma.setBounds(130, 20, 100, 21);

        LblEtiqDescuento.setText("DESCUENTO :");
        LblEtiqDescuento.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqDescuento);
        LblEtiqDescuento.setBounds(240, 20, 84, 15);

        LblEtiqDescTotal.setText("DESC. TOTAL:");
        LblEtiqDescTotal.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqDescTotal);
        LblEtiqDescTotal.setBounds(460, 20, 86, 15);

        LblDescuentoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoTotal.setText("0.00");
        LblDescuentoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblDescuentoTotal);
        LblDescuentoTotal.setBounds(560, 20, 90, 19);

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(550, 100, 90, 70);

        btnAnular.setText("ANULAR");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnular);
        btnAnular.setBounds(280, 100, 90, 70);

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar);
        btnEditar.setBounds(190, 100, 90, 70);

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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
        TxtDescuento.setBounds(330, 20, 100, 21);

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

        LblEtiqIgv.setText("I.G.V.");
        LblEtiqIgv.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqIgv);
        LblEtiqIgv.setBounds(660, 40, 130, 20);

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

        LblEtiqSubNetoSinIgv.setText("SUB. NT. SIN IGV");
        LblEtiqSubNetoSinIgv.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqSubNetoSinIgv);
        LblEtiqSubNetoSinIgv.setBounds(660, 20, 130, 20);

        LblSubTotalNetoSinIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubTotalNetoSinIgv.setText("0.00");
        LblSubTotalNetoSinIgv.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubTotalNetoSinIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblSubTotalNetoSinIgv);
        LblSubTotalNetoSinIgv.setBounds(790, 20, 120, 20);

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

        LblEtiqPercepcion.setText("PERCEPCIÓN");
        LblEtiqPercepcion.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqPercepcion);
        LblEtiqPercepcion.setBounds(660, 120, 130, 20);

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

        lblMontoPercepcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMontoPercepcion.setText("0.00");
        lblMontoPercepcion.setFont(new java.awt.Font("Verdana", 1, 12));
        lblMontoPercepcion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(lblMontoPercepcion);
        lblMontoPercepcion.setBounds(790, 120, 120, 20);

        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportar1);
        btnExportar1.setBounds(370, 100, 90, 70);

        BtnImprimir.setFont(new java.awt.Font("Verdana", 1, 12));
        BtnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/agt_print.png"))); // NOI18N
        BtnImprimir.setText("Imprimir ");
        BtnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnImprimir.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(BtnImprimir);
        BtnImprimir.setBounds(460, 100, 90, 70);

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

        lblEtiqTipoComp.setText("Comprobante :");

        TxtEstado.setText("EMITIDO");
        TxtEstado.setFont(new java.awt.Font("Verdana", 1, 12));

        lblEtiqEstado.setText("Estado :");

        btnBuscarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        btnBuscarPedido.setText("Buscar Proforma");
        btnBuscarPedido.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarPedido.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscarPedido.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPedidoActionPerformed(evt);
            }
        });

        DtchFechaVencimiento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtchFechaVencimientoPropertyChange(evt);
            }
        });

        lblEtiqEstado1.setText("Fecha Vencimiento:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEtiqTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(DtchFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(511, 511, 511)
                    .addComponent(lblEtiqEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(298, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DtchFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEtiqTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblEtiqEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblProformaDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblProformaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {new Integer(1), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N°", "Codigo", "Producto", "Stock", "Cantidad", "U. M. ", "precio_sin_redon", "Precio", "Importe Bruto", "Tipo Desc.", "Descuento", "Dsct. Val.", "Precio/ Desc.", "Importe Neto", "Exonerado", "Almacen", "ListaPrecio", "sinPrecio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, false, false, false, false, true, true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblProformaDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblProformaDetalle.setColumnSelectionAllowed(true);
        TblProformaDetalle.setRowHeight(21);
        TblProformaDetalle.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblProformaDetalle.getTableHeader().setReorderingAllowed(false);
        TblProformaDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblProformaDetalleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TblProformaDetalleMousePressed(evt);
            }
        });
        TblProformaDetalle.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblProformaDetallePropertyChange(evt);
            }
        });
        TblProformaDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblProformaDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblProformaDetalleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblProformaDetalle);
        TblProformaDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TblProformaDetalle.getColumnModel().getColumn(0).setPreferredWidth(28);
        TblProformaDetalle.getColumnModel().getColumn(1).setMinWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(1).setPreferredWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(1).setMaxWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(2).setPreferredWidth(380);
        TblProformaDetalle.getColumnModel().getColumn(3).setMinWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(3).setPreferredWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(3).setMaxWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
        TblProformaDetalle.getColumnModel().getColumn(5).setPreferredWidth(80);
        TblProformaDetalle.getColumnModel().getColumn(6).setMinWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(6).setPreferredWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(6).setMaxWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(7).setPreferredWidth(85);
        TblProformaDetalle.getColumnModel().getColumn(8).setMinWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(8).setPreferredWidth(90);
        TblProformaDetalle.getColumnModel().getColumn(9).setMinWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(9).setPreferredWidth(80);
        TblProformaDetalle.getColumnModel().getColumn(10).setMinWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(10).setPreferredWidth(70);
        TblProformaDetalle.getColumnModel().getColumn(11).setMinWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(12).setMinWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(12).setPreferredWidth(80);
        TblProformaDetalle.getColumnModel().getColumn(13).setResizable(false);
        TblProformaDetalle.getColumnModel().getColumn(13).setPreferredWidth(95);
        TblProformaDetalle.getColumnModel().getColumn(14).setPreferredWidth(95);
        TblProformaDetalle.getColumnModel().getColumn(15).setMinWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(15).setPreferredWidth(70);
        TblProformaDetalle.getColumnModel().getColumn(16).setMinWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(16).setPreferredWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(16).setMaxWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(17).setMinWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(17).setPreferredWidth(0);
        TblProformaDetalle.getColumnModel().getColumn(17).setMaxWidth(0);

        label20.setText("PEDIDO DE PRODUCTOS");
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

        columnButton1.setToolTipText("Ajustar Columnas");
        columnButton1.setTbl(TblProformaDetalle);

        LblHoraSistema.setText("hora");
        LblHoraSistema.setFont(new java.awt.Font("Verdana", 1, 11));

        LblEtiqNum.setText("Numero  :");
        LblEtiqNum.setFont(new java.awt.Font("Verdana", 1, 11));

        BtnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Refresh.gif"))); // NOI18N
        BtnActualizar.setToolTipText("Refrescar");
        BtnActualizar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarActionPerformed(evt);
            }
        });

        LblUltimoNumeroProforma.setEditable(false);

        chkFecha.setText("Fecha");
        chkFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFechaActionPerformed(evt);
            }
        });

        DtchFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtchFechaPropertyChange(evt);
            }
        });

        LblEtiqSubtotalBruto.setText("SubTotal Bruto:");
        LblEtiqSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalBruto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalBruto.setText("0.00");
        LblSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalBruto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqDsctSubtValores.setText("Dsct. Subt. en Valores:");
        LblEtiqDsctSubtValores.setFont(new java.awt.Font("Verdana", 1, 12));

        LblDescuentoSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoSubtotal.setText("0.00");
        LblDescuentoSubtotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoSubtotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqSubtotalNeto.setText("SubTotal Neto");
        LblEtiqSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalNeto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalNeto.setText("0.00");
        LblSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalNeto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        chkPercepcion.setText("PERCEPCIÓN");
        chkPercepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPercepcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE)
                    .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                        .addComponent(chkFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(DtchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(LblEtiqNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(LblUltimoNumeroProforma, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblEtiqSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LblEtiqDsctSubtValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(LblEtiqSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(chkFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(DtchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(LblEtiqNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(LblUltimoNumeroProforma, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAgregar)
                    .addComponent(BtnQuitar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LblEtiqSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblEtiqDsctSubtValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarCliente()
    {
        if(TxtCliente.isEnabled()){
          DlgGestionCliente odialogo= new DlgGestionCliente(oparent, true,1,1,1);//consultar
       
          odialogo.setCajaTexo(TxtCliente.getText());
          odialogo.setVisible(true);
          odialogo.setTitle("Busqueda de Cliente");
          CECliente oCEClienteAux=odialogo.getoCECliente();
                  if(oCEClienteAux!=null){
                    idCliente=oCEClienteAux.getIdCliente();
                    Cliente=oCEClienteAux.getNombreCompleto();
                    TxtCliente.setText(oCEClienteAux.getNombreCompleto());
                    if(oCEClienteAux.getIdTipoPersona()==1){
                    TxtDNI.setText(oCEClienteAux.getDNI());
                    LblEtiqDNI.setText("DNI");
                    }else
                    {
                        TxtDNI.setText(oCEClienteAux.getRUC());
                        LblEtiqDNI.setText("RUC");
                    }
                    TxtDireccion.setText(oCEClienteAux.getDireccion());
                    TblProformaDetalle.requestFocus();
                    TblProformaDetalle.changeSelection(0,oCLPedido.colProducto, false, false);
                    }
        }

      }
private void habilitarControles(boolean bol)
    {
    TxtCliente.setEnabled(bol);
    TblProformaDetalle.setEnabled(bol);
    BtnAgregar.setEnabled(bol);
    BtnQuitar.setEnabled(bol);
    CbxTipoDescuentoProforma.setEnabled(bol);
    TxtDescuento.setEnabled(bol);
    CbxTipoComprobante.setEnabled(bol);
    CbxMoneda.setEnabled(bol);
    TxtProducto.setEnabled(bol);
    CbxMoneda.setEnabled(bol);
    chkPercepcion.setEnabled(bol);
    DtchFechaVencimiento.setEnabled(bol);
}


private int ValidarRegistro(int fila)
    {

    try {
     if(TblProformaDetalle.getRowCount()>0){
    
     int numColumna=0;
        CEProformaDetalle oCEProformaDetalle=(CEProformaDetalle)TblProformaDetalle.getValueAt(fila-1, oCLPedido.colCodigo);
        String mensaje="Seleccione un producto";
        if(oCEProformaDetalle!=null){
            if(TblProformaDetalle.getValueAt(fila-1, oCLPedido.colCantidad)!=null)
            {
                float cantidad=Float.parseFloat(TblProformaDetalle.getValueAt(fila-1, oCLPedido.colCantidad).toString());
                if(cantidad<0){
                        numColumna=oCLPedido.colCantidad;
                        colValidada=oCLPedido.colCantidad;
                        mensaje="Ingrese una cantidad mayor a cero";
                }else
                {
                    float precio=0;
                        if(TblProformaDetalle.getValueAt(fila-1, oCLPedido.colPrecio)!=null)
                        {
                        precio=Float.parseFloat(TblProformaDetalle.getValueAt(fila-1, oCLPedido.colPrecio).toString());
                        }
                        if(precio==0){
                        numColumna = oCLPedido.colPrecio;
                        colValidada = oCLPedido.colCantidad;
                        mensaje="Ingrese un Precio";
                        }
                        if(TblProformaDetalle.getValueAt(fila-1, oCLPedido.colDescuento)!=null)
                        {                           
                            
                            float importesinDesc=VerificadorDeTxt.convertFloat(TblProformaDetalle.getValueAt(fila-1, oCLPedido.colImporSinDesc));
                            float descuento=VerificadorDeTxt.convertFloat(TblProformaDetalle.getValueAt(fila-1, oCLPedido.colDestValores));
                            if(descuento>importesinDesc)
                                {
                                    mensaje="El descuento no puede ser mayor al importe.";
                                    numColumna = oCLPedido.colDescuento;
                                    colValidada = oCLPedido.colDescuento;
                                }
                        }
                }
            }else{
                        numColumna = oCLPedido.colCantidad;
                        colValidada = oCLPedido.colCantidad;
                        mensaje="Ingrese una cantidad";                     
                 }
        }
        else{
            numColumna=oCLPedido.colProducto;
            colValidada=oCLPedido.colProducto;

        }
        if(numColumna!=0){
            if(numColumna!=oCLPedido.colPrecio){
            JOptionPane.showMessageDialog(this,mensaje);
            }else
                { int filat=TblProformaDetalle.getSelectedRow();
                  oCLPedido.calcularPrecio(filat);
                }
            }

        return numColumna;
        
        }
    return 0;
        }
    catch (Exception e)
    {
        System.out.println("ValidarRegistro - DlgGestion Proforma : "+ e);
        return 0;
    }
}
private boolean validarbusqProd()
{
    if(TblProformaDetalle.getRowCount()<=0)
        return false;
    if(!TblProformaDetalle.isEnabled())
        return false;

    return true;

}
    private void BuscarProducto()
    {
        if(validarbusqProd()){
        CEMoneda oCEMoneda=(CEMoneda)CbxMoneda.getSelectedItem();
        if(oCEMoneda!=null)
        {
        DlgBusquedaProducto odialogo= new DlgBusquedaProducto(oparent, true,oCEMoneda,Float.parseFloat(TxtTipoCambio.getText()));
                String NombreProd=TxtProducto.getText().trim();
               if(NombreProd.equals(""))
               {
               if(TblProformaDetalle.getValueAt(TblProformaDetalle.getSelectedRow(), oCLPedido.colProducto)!=null)
                    {
                    NombreProd=TblProformaDetalle.getValueAt(TblProformaDetalle.getSelectedRow(), oCLPedido.colProducto)+"";
                    }
                }
               TxtProducto.setText("");
               odialogo.setConPercepcion(chkPercepcion.isSelected());
               odialogo.setCajaTexto(NombreProd);
           //    odialogo.setListaProforma(this.ObtenerRegistrosAcuales());
               odialogo.setVisible(true);
               CEAlmacenProducto oCEAlmacenProducto=odialogo.getProductoAlmacen();
               if(!odialogo.getisagregarCantidadPedido()){
               agregarProformaDetalle(oCEAlmacenProducto,odialogo.getListaPrecio());
               
               int fila=TblProformaDetalle.getSelectedRow();
               TblProformaDetalle.requestFocus();
               TblProformaDetalle.changeSelection(fila, oCLPedido.colStock, false, false);
                }else{
                   SeleccionarProformaExistente(oCEAlmacenProducto);
                }

            }
        }
    }
    private void SeleccionarProformaExistente(CEAlmacenProducto oCEAlmacenProducto)
    {
        CEProformaDetalle oCEProformaDetalle ;
        for( int i =0;i<TblProformaDetalle.getRowCount();i++)
            {
//cx
                oCEProformaDetalle= (CEProformaDetalle)TblProformaDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEProformaDetalle!=null){
                    
                        if(oCEProformaDetalle.getIdAlmacen()==oCEAlmacenProducto.getIdAlmacen()&&
                           oCEProformaDetalle.getIdUnidadMedidaVenta()==oCEAlmacenProducto.getIdUnidadBase()&&
                           oCEProformaDetalle.getIdProducto()==oCEAlmacenProducto.getIdProducto())
                        {
                            TblProformaDetalle.requestFocus();
                            TblProformaDetalle.changeSelection(i, oCLPedido.colUnidadMedida, false, false);
                        }

                }
            }
    }
    private List<CEProformaDetalle> ObtenerRegistrosAcuales()
    {
        List<CEProformaDetalle> lstProformaDetalleTemp = new ArrayList<CEProformaDetalle>();
        CEProformaDetalle oCEProformaDetalle ;
        for( int i =0;i<TblProformaDetalle.getRowCount();i++)
            {
                oCEProformaDetalle= (CEProformaDetalle)TblProformaDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEProformaDetalle!=null){
                    if(i!=TblProformaDetalle.getSelectedRow()){
                oCEProformaDetalle.setPrecio(VerificadorDeTxt.convertFloat(TblProformaDetalle.getValueAt(i, oCLPedido.colPrecio).toString()));
                oCEProformaDetalle.setCantidad(0);
                if(TblProformaDetalle.getValueAt(i, oCLPedido.colCantidad)!=null){
                oCEProformaDetalle.setCantidad(VerificadorDeTxt.convertFloat(TblProformaDetalle.getValueAt(i, oCLPedido.colCantidad).toString()));
                }
                CEProformaDetalle oCEProformaDetalleTemp=(CEProformaDetalle)TblProformaDetalle.getValueAt(i, oCLPedido.colCodigo);
                oCEProformaDetalle.setIdProducto(oCEProformaDetalleTemp.getIdProducto());
                lstProformaDetalleTemp.add(oCEProformaDetalle);
                    }
                }
            }
        return lstProformaDetalleTemp;
    }
    private boolean  ValidarTablaProforma()
     {

        for(int i=0; i<TblProformaDetalle.getRowCount();i++)
        {
            verificarDatos(i);

            if(colValidada!=-1){
                TblProformaDetalle.requestFocus();
                TblProformaDetalle.changeSelection(i, colValidada, false, false);
                colValidada=-1;
                return false;
            }
        }
            eventoGuardar=false;
        return true;
    }
    boolean eventoGuardar=false;

    private void eventoGuardarProforma()
    {
        if(btnGuardar.isEnabled()){
      //  this.setEnabled(false);
        verificarPercepcion();
        oCLPedido.CalcularSubtotales();
        oCLPedido.calcularImportes();
        oCLPedido.eventoDescuento();
        
        int fil=TblProformaDetalle.getRowCount();
        if(fil>0){
          int row=fil-1;
              CEProformaDetalle oCEProformaDetalle= (CEProformaDetalle)TblProformaDetalle.getValueAt(row, oCLPedido.colCodigo);
              if(oCEProformaDetalle==null){

                oCLPedido.QuitarFilaSel(row);

              }
         }
      fil=TblProformaDetalle.getRowCount();
      if(fil>0){
      int columnaValidada=ValidarRegistro(TblProformaDetalle.getRowCount());
      if(columnaValidada==0)
      {
         eventoGuardar=true;
          GuardarProforma();

      }else
      {
          TblProformaDetalle.requestFocus();
          TblProformaDetalle.changeSelection(TblProformaDetalle.getRowCount()-1, columnaValidada, false, false);
      }
    }else
    {
           JOptionPane.showMessageDialog(this,"Ingrese un Item","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             agregarNuevaFila(0);
             TblProformaDetalle.requestFocus();
             TblProformaDetalle.changeSelection(0, oCLPedido.colProducto, false, false);
    }
   }
  //  this.setEnabled(true);
    }

    private boolean EstaImpreso=true;
    private boolean validar()
    {

        if(idCliente==0)
        {
             JOptionPane.showMessageDialog(this,"Ingrese un Cliente","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             TxtCliente.requestFocus();
             return false;
        }
        if(TblProformaDetalle.getRowCount()==0)
        {
             JOptionPane.showMessageDialog(this,"Ingrese Proforma","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             agregarNuevaFila(0);
             TblProformaDetalle.requestFocus();
             TblProformaDetalle.changeSelection(0, oCLPedido.colProducto, false, false);
             return false;
        }
        if(DtchFechaVencimiento.getCalendar()==null)
        {
             JOptionPane.showMessageDialog(this,"Ingrese la Fecha de Vencimiento","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             DtchFechaVencimiento.requestFocus();
             return false;
        }

        int res = JOptionPane.showConfirmDialog(this, "¿Desea Imprimir La Proforma?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
        if(res==JOptionPane.NO_OPTION||res==JOptionPane.CANCEL_OPTION)
        {
            EstaImpreso=false;
        }

        if(pAccionABM==2)
        {
            CEEstado oCEEstado=CCEstado.consultarUltimoestadoProforma(IdProforma);
            if(oCEEstado.getmIntIdEstado()!=1)
            {
             JOptionPane.showMessageDialog(this,"El Proforma ya fue "+oCEEstado.getmStrDescripcion()+", No se Puede Modificar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
        }



        return true;
    }

    private boolean verificarPercepcion()
    {
        String ProductosConPercepcion="";
        String ProductosSinPercepcion="";

        CEProformaDetalle oCEProformaDetalle=null;
        for(int i=0; i<TblProformaDetalle.getRowCount();i++)
            {
                oCEProformaDetalle= (CEProformaDetalle)TblProformaDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEProformaDetalle!=null){
                    if(oCEProformaDetalle.isConPercepcion())
                    {
                        ProductosConPercepcion=ProductosConPercepcion+oCEProformaDetalle.getProducto()+"\n";
                    }
                    else
                    {
                        if(chkPercepcion.isSelected()){
                            ProductosSinPercepcion=ProductosSinPercepcion+"\n"+oCEProformaDetalle.getProducto();
                            this.QuitarFila(i);
                        }
                    }

                }
            }

        if(!ProductosSinPercepcion.equals("")){
            JOptionPane.showMessageDialog(this,
                    "<html><h3>Los Siguientes Productos seran borrados por que no tienen Percepción: "+ProductosSinPercepcion);
            
        }

        return true;
    }
    private void GuardarProforma()
    {
        try {
//        if(FrmSistemaMenu.isSiNoAtencion())
//        {
        if(ValidarTablaProforma()){
        if(validar()){
                    if(pAccionABM==1){
                        CEProformaMatriz oCEProforma=this.getProforma();
                        String CodigoProforma=CCProforma.InsProforma(oCEProforma);
                        if(CodigoProforma!=null)
                        {

                        oCEProforma.setCodigo(CodigoProforma);
                        //CLImprimirProforma oCLImprimirProforma=new CLImprimirProforma(oCEProforma,"");
                        JOptionPane.showMessageDialog(this,"<html><h3>El código de Proforma es: </h3><h2><center>"+CodigoProforma+
                                    "</center></h2><h3> El monto a pagar es   : </h3><h2><center>"+LblMontoTotalPagar.getText()+"S/.</center><h2></html>");
                        if(EstaImpreso){
                            DlgReporteProforma oDlgDetalleComprobante = new DlgReporteProforma(oparent, true, oCEProforma);
                            oDlgDetalleComprobante.setLocationRelativeTo(null);
                            oDlgDetalleComprobante.setVisible(true);
                        }
                        limpiarFormulario();        
                        lstProformaEliminados = new ArrayList<CEProformaDetalle>();
                        TxtlHoraRegistro1.setText("");
                        TblProformaDetalle.requestFocus();
                        TblProformaDetalle.changeSelection(0,oCLPedido.colProducto, false, false);
                        }
                        else{

                        JOptionPane.showMessageDialog(this,"Operación Fallida");
                        }
                    }else{

                        CEProformaMatriz oCEProforma=this.getProforma();
                        int iscorrecto=CCProforma.UPDProforma(oCEProforma);
                        if(iscorrecto==1)
                        {
                        JOptionPane.showMessageDialog(this,"<html><CENTER><h3>Operación exitosa,<BR>el Nuevo Monto es: "+LblMontoTotalPagar.getText()+"S/.</h3></CENTER></html>");
                        if(EstaImpreso){
                            DlgReporteProforma oDlgDetalleComprobante = new DlgReporteProforma(oparent, true, oCEProforma);
                            oDlgDetalleComprobante.setLocationRelativeTo(null);
                            oDlgDetalleComprobante.setVisible(true);
                        }
                        limpiarFormulario();
                        lstProformaEliminados = new ArrayList<CEProformaDetalle>();
                        pAccionABM=1;
                        }
                    else{
                        JOptionPane.showMessageDialog(this,"Operación Fallida");
                        }
               }
      }
     }

    }
        catch (Exception e)
    {
        System.out.println("EventoGuardar - DlgGestion Proforma : "+ e);
       JOptionPane.showMessageDialog(this,"Operación Fallida");
    }
//        }
// else
//        {
//        JOptionPane.showMessageDialog(null,"El sistema se encuentra cerrado, no se puede realizar Proformas","Mensaje", JOptionPane.ERROR_MESSAGE);
//        BtnActualizar.requestFocus();
// }
    }


    private CEProformaMatriz getProforma(){
        try {
        CEProformaMatriz oCEProforma= new CEProformaMatriz();

        oCEProforma.setIdProforma(IdProforma);
        CETipoComprobante oCETipoComprobante=(CETipoComprobante)CbxTipoComprobante.getSelectedItem();
        oCEProforma.setIdTipoComprobante(oCETipoComprobante.getIdTipoComprobante());
        CEMoneda oCEMoneda=(CEMoneda)CbxMoneda.getSelectedItem();
        oCEProforma.setIdMoneda(oCEMoneda.getId_moneda());
        CECondicion oCECondcion=(CECondicion)CbxCondicion.getSelectedItem();
        oCEProforma.setIdCondicion(oCECondcion.getIdCondicion());
        oCEProforma.setTipoCambio(Float.parseFloat(TxtTipoCambio.getText()));
        CETipoDescuento oCETipoDescuento=(CETipoDescuento)CbxTipoDescuentoProforma.getSelectedItem();
        oCEProforma.setIdUltimoEstado(1);
        if(IdEstado==15)
           oCEProforma.setIdUltimoEstado(15);
        if(EstaImpreso)
        {
            oCEProforma.setIdUltimoEstado(15);
        }
        oCEProforma.setIdSucursal(FrmSistemaMenu.getIdSucursal());
        oCEProforma.setIdTipoDescuento(oCETipoDescuento.getIdTipoDescuento());
        oCEProforma.setIdCliente(idCliente);
        oCEProforma.setCliente(Cliente);
        oCEProforma.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCEProforma.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCEProforma.setSubtotalBruto(Float.parseFloat(LblSubtotalBruto.getText()));
        oCEProforma.setDescuentoEnSubtotal(Float.parseFloat(LblDescuentoSubtotal.getText()));
        oCEProforma.setSubTotalNeto(Float.parseFloat(LblSubtotalNeto.getText()));
        oCEProforma.setDescuento(oCLPedido.getDescuento());
        oCEProforma.setDescuentoEnValores(Float.parseFloat(LblDescuentoValores.getText()));
        oCEProforma.setSubTotalNetoSinIGV(Float.parseFloat(LblSubTotalNetoSinIgv.getText()));
        oCEProforma.setIGV(Float.parseFloat(LblIgv.getText()));
        oCEProforma.setISC(Float.parseFloat(LblISC.getText()));
        oCEProforma.setIGVActual(oCLPedido.getIgvActual());
        oCEProforma.setDescuentoTotal(Float.parseFloat(LblDescuentoTotal.getText()));
        oCEProforma.setMontoTotal(Float.parseFloat(LblMontoTotal.getText()));
        oCEProforma.setMontoPercepcion(Float.parseFloat(lblMontoPercepcion.getText()));
        oCEProforma.setTotalPagar(Float.parseFloat(LblMontoTotalPagar.getText()));
        oCEProforma.setTasaPercepcion(0);
        if(chkPercepcion.isSelected())
        {
            oCEProforma.setTasaPercepcion(oCLPedido.getTasaPercepcion());
        }
            SetterGetter oSetterGetter = new SetterGetter();
            oSetterGetter.setmIntTipo(1);
            ConvertidorFecha oConvertidorFecha = new ConvertidorFecha();
            if (DtchFecha.getCalendar() != null)
            {
                oConvertidorFecha.setFecha(DtchFecha.getCalendar());
                oCEProforma.setFecha(oSetterGetter.convertir(oConvertidorFecha.getFechaConvertida()));
            }
            if (DtchFechaVencimiento.getCalendar() != null)
            {
                oConvertidorFecha.setFecha(DtchFechaVencimiento.getCalendar());
                oCEProforma.setFechaVencimiento(oSetterGetter.convertir(oConvertidorFecha.getFechaConvertida()));
            }

        List<CEProformaDetalle> lstProformaDetalle = new ArrayList<CEProformaDetalle>();
        CEProformaDetalle oCEProformaDetalle ;
        for( int i =0;i<TblProformaDetalle.getRowCount();i++)
            {

                oCEProformaDetalle= (CEProformaDetalle)TblProformaDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEProformaDetalle!=null){

                CEUnidadMedidaProducto oCEUnidadMedidaProducto=(CEUnidadMedidaProducto)TblProformaDetalle.getValueAt(i, oCLPedido.colUnidadMedida);
                oCEProformaDetalle.setUnidadMedida(oCEUnidadMedidaProducto.toString());
                CETipoDescuento oCETipoDescuentoDet=(CETipoDescuento)TblProformaDetalle.getValueAt(i, oCLPedido.colTipoDescuento);
                oCEProformaDetalle.setIdTipoDescuento(oCETipoDescuentoDet.IdTipoDescuento);
                oCEProformaDetalle.setPrecio(Float.parseFloat(TblProformaDetalle.getValueAt(i, oCLPedido.colPrecio).toString()));
                oCEProformaDetalle.setPrecioSinRedon(Float.parseFloat(TblProformaDetalle.getValueAt(i, oCLPedido.colprecio_sin_redon).toString()));
                oCEProformaDetalle.setCantidad(Float.parseFloat(TblProformaDetalle.getValueAt(i, oCLPedido.colCantidad).toString()));
                oCEProformaDetalle.setImporteSinDescuento(Float.parseFloat(TblProformaDetalle.getValueAt(i, oCLPedido.colImporSinDesc).toString()));
                if(TblProformaDetalle.getValueAt(i, oCLPedido.colImporConDesc)!=null){
                    oCEProformaDetalle.setImporteConDescuento(Float.parseFloat(TblProformaDetalle.getValueAt(i, oCLPedido.colImporConDesc).toString()));
                    }
                if(TblProformaDetalle.getValueAt(i, oCLPedido.colExonerado)!=null){
                    oCEProformaDetalle.setExonerado(Float.parseFloat(TblProformaDetalle.getValueAt(i, oCLPedido.colExonerado).toString()));
                    }
                if(TblProformaDetalle.getValueAt(i, oCLPedido.colDescuento)==null){
                    TblProformaDetalle.setValueAt("0",i, oCLPedido.colDescuento);
                    }
                    oCEProformaDetalle.setDescuento(Float.parseFloat(TblProformaDetalle.getValueAt(i, oCLPedido.colDescuento).toString()));
                    float descuentoValores=0;
                    if(TblProformaDetalle.getValueAt(i, oCLPedido.colDestValores)!=null){
                        descuentoValores=Float.parseFloat(TblProformaDetalle.getValueAt(i, oCLPedido.colDestValores).toString());
                }
                oCEProformaDetalle.setDescuentoEnValores(descuentoValores);
                oCEProformaDetalle.setPrecioConDesc(VerificadorDeTxt.convertFloat(TblProformaDetalle.getValueAt(i, oCLPedido.colPrecioConDesc)));
                lstProformaDetalle.add(oCEProformaDetalle);}
            }
                if(pAccionABM==2)
                {
                    lstProformaDetalle.addAll(lstProformaEliminados);
                }


            oCEProforma.setLstProformaDetalle(lstProformaDetalle);

            
            return oCEProforma;

            }
        catch (Exception e)
    {
        System.out.println("getProforma - DlgGestion Proforma : "+ e);
       return null;
    }

}
    private boolean agregarNuevaFila(int fila)
    {

        try {
        if(TblProformaDetalle.getRowCount()<12){

         Vector oVector = new Vector();

                ((DefaultTableModel)TblProformaDetalle.getModel()).addRow(oVector);

                    CbxTipoDescuento.setSelectedIndex(0);
                    CETipoDescuento oTipoDescuento=(CETipoDescuento)CbxTipoDescuento.getSelectedItem();

                    TblProformaDetalle.requestFocus();
     
                    TblProformaDetalle.setValueAt(oTipoDescuento, fila, oCLPedido.colTipoDescuento);

                   
        }
         else
        {
        
        JOptionPane.showMessageDialog(this,"No se puede agregar,a sobrepasado el límite");
        return  false;
        }
        
        return true;
        }
        catch (Exception e)
    {
        System.out.println("agregarNruvaFila - DlgGestion Proforma : "+ e);
       return false;
    }

    }
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
    eventoAgregar();
        
    }//GEN-LAST:event_BtnAgregarActionPerformed
private void eventoAgregar()
{

try {
    if(TblProformaDetalle.isEnabled()){
      int columnaValidada=ValidarRegistro(TblProformaDetalle.getRowCount());
      if(columnaValidada==0)
      {
          int fila =TblProformaDetalle.getRowCount();
          boolean isAgrega=agregarNuevaFila(fila);
                    if(isAgrega){
                    TblProformaDetalle.requestFocus();
                    TblProformaDetalle.changeSelection(fila, oCLPedido.colProducto, false, false);
                    }
                    else{ TxtCliente.requestFocus();}
      }else
      {
          TblProformaDetalle.requestFocus();
          TblProformaDetalle.changeSelection(TblProformaDetalle.getRowCount()-1, columnaValidada, false, false);
      }
    }

    }
    catch (Exception e)
    {
        System.out.println("EventoAgregar - DlgGestion Proforma : "+ e);
       JOptionPane.showMessageDialog(this,"Operación Fallida");
    }
}

private void QuitarFila(int rowsel)
{

    try{        
        if(rowsel!=-1){
                       CEProformaDetalle oCEProformaDetalle= (CEProformaDetalle)TblProformaDetalle.getValueAt(rowsel, oCLPedido.colCodigo);
                    if(oCEProformaDetalle!=null){
                        if(oCEProformaDetalle.getIdProformaDetalle()>0)
                        {
                            oCEProformaDetalle.setIdAbm(3);
                            lstProformaEliminados.add(oCEProformaDetalle);
                        }
                    }
                    oCLPedido.QuitarFilaSel(rowsel);
                }
        else JOptionPane.showMessageDialog(this,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);

        }
        catch (Exception e)
    {
        System.out.println("Quitar Fila - DlgGestion Proforma : "+ e);

    }

}
    private void BtnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarActionPerformed

        try{
        this.QuitarFila(TblProformaDetalle.getSelectedRow());
        }
        catch(Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(this,"Operacion Fallida");
        }
    }//GEN-LAST:event_BtnQuitarActionPerformed

    private void CbxCondicionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxCondicionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxCondicionItemStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        eventoCancelar();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void eventoCancelar()
    {
         if(pAccionABM==4||pAccionABM==1||pAccionABM==2)
       {
            habilitarControles(false);
            oclBotonesABM.controlBoton(true, false, false, false, true, true);
            TxtlHoraRegistro1.setText("");
            pAccionABM=0;
            limpiarFormulario();
            BtnImprimir.setEnabled(false);
       }else{
           iscerrando=true;
             dispose();
            }

    }
    private void CbxTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteActionPerformed
     
    }//GEN-LAST:event_CbxTipoComprobanteActionPerformed

    private void CbxTipoComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteItemStateChanged

        try{
        CETipoComprobante oCETipoComprobante=(CETipoComprobante)CbxTipoComprobante.getSelectedItem();
      if(oCETipoComprobante!=null)
      {
           
          oCLPedido.setConigv(true);
          if(oCETipoComprobante.getIdTipoComprobante()==3)
          {
              oCLPedido.setConigv(false);
          }
          oCLPedido.CalcularSubtotales();
          oCLPedido.calcularImportes();
           oCLPedido.eventoDescuento();
      }
      }
        catch (Exception e)
    {
        System.out.println("CbxTipoComprobanteItemStateChanged - DlgGestion Proforma : "+ e);

    }
    }//GEN-LAST:event_CbxTipoComprobanteItemStateChanged

    private int colValidada=-1;
    private int cont=0;// contador utilizado como artificio
    private void verificarDatos(int fila){
         try{
             
            colValidada=-1;

            float cantidad=1;
            float descuento=0;
            float precio=1;
            float stock=1;
            if(TblProformaDetalle.getValueAt(fila, oCLPedido.colCantidad)!=null){
            cantidad=Float.parseFloat(TblProformaDetalle.getValueAt(fila, oCLPedido.colCantidad).toString());}
            if(TblProformaDetalle.getValueAt(fila, oCLPedido.colPrecio)!=null){
                precio=Float.parseFloat(TblProformaDetalle.getValueAt(fila, oCLPedido.colPrecio).toString());
            }
             if(TblProformaDetalle.getValueAt(fila, oCLPedido.colDescuento)!=null){
             descuento=Float.parseFloat(TblProformaDetalle.getValueAt(fila, oCLPedido.colDescuento).toString());
            }

              float  desc=VerificadorDeTxt.convertFloat(TblProformaDetalle.getValueAt(fila, oCLPedido.colDestValores));
              float  importSindesc=VerificadorDeTxt.convertFloat(TblProformaDetalle.getValueAt(fila, oCLPedido.colImporSinDesc));


            if(desc>importSindesc)
            {
                JOptionPane.showMessageDialog(this,"El descuento no puede ser mayor al importe");
                TblProformaDetalle.setValueAt(0, fila,oCLPedido.colDescuento);
                descuento=0;
                
               oCLPedido.CalcularSubtotales();
               oCLPedido.eventoDescuento();
               oCLPedido.calcularImportes();

                colValidada=oCLPedido.colDescuento;
                 return;
            }

        if(descuento<0)
            {
                JOptionPane.showMessageDialog(this,"El descuento no puede ser menor a cero");
                TblProformaDetalle.setValueAt(null, fila,oCLPedido.colDescuento);
                descuento=0;
                TblProformaDetalle.setValueAt(CLRedondear.Redondear((cantidad*precio)-descuento,2), fila, oCLPedido.colImporConDesc);//con
                colValidada=oCLPedido.colDescuento;
                return;
            }

        if(cantidad<=0)
            {
                JOptionPane.showMessageDialog(null,"La cantidad debe ser mayor a cero");
                TblProformaDetalle.setValueAt(null,fila,oCLPedido.colCantidad);
                colValidada=oCLPedido.colCantidad;
                 return;
            }
          /*      if(precio<=0)
            {
                JOptionPane.showMessageDialog(null,"El precio tiene q ser mayor a cero");
                colValidada=oCLProforma.colPrecio;
                 return;
            }*/
        stock=VerificadorDeTxt.convertFloat(TblProformaDetalle.getValueAt(fila, oCLPedido.colStock));
        if(TblProformaDetalle.isEnabled()){
//            if(cantidad>stock)
//            {
//                if(TblProformaDetalle.getValueAt(fila, oCLPedido.colStock)!=null){
//               JOptionPane.showMessageDialog(this,"La cantidad no debe ser mayor al stock disponible");
//                TblProformaDetalle.setValueAt(null, fila,oCLPedido.colCantidad);
//                colValidada=oCLPedido.colCantidad;
//                 return;
//                }
//
//            }
        }

        int col=TblProformaDetalle.getSelectedColumn();
                if(!eventoGuardar){
                if(col==oCLPedido.colCantidad){
                    oCLPedido.calcularPrecio(fila);
                }
             }
            
            }catch(Exception e)
            {
                cont++;
               // System.out.println("metodo Verificar datos");
            }
    }



    private void CbxTipoDescuentoProformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoDescuentoProformaActionPerformed
        
        oCLPedido.CalcularSubtotales();
        oCLPedido.calcularImportes();
             CETipoDescuento oDescuento=(CETipoDescuento)CbxTipoDescuentoProforma.getSelectedItem();
        if(oDescuento!=null)
      {
          if(oDescuento.getIdTipoDescuento()==2)
          {

          LblPorcentaje.setText("%");

          }
        else{

         LblPorcentaje.setText(" V");
        }
      }
     oCLPedido.eventoDescuento();
    }//GEN-LAST:event_CbxTipoDescuentoProformaActionPerformed
private String Codigo;
    private void buscarProforma()
    {
        try{
        
       DlgBusquedaProforma odialogo= new DlgBusquedaProforma(oparent, true);
       odialogo.setVisible(true);
       CEProformaMatriz oCEProformaaux=odialogo.getCEProformaMatriz();
       oCLPedido.setCalcular(false);
       if(oCEProformaaux!=null)
        {

            CEProformaMatriz oCEProformaMatriz= CCProforma.ConsultarProformasPorId(oCEProformaaux.getIdProforma());
            oCLPedido.limpiarTabla();
            Codigo=oCEProformaMatriz.getCodigo();
            LblUltimoNumeroProforma.setText(Codigo);
            TxtEstado.setText(oCEProformaMatriz.getUltimoEstado());
            IdEstado=oCEProformaMatriz.getIdUltimoEstado();
            LblSubtotalBruto.setText(""+oCEProformaMatriz.getSubtotalBruto());
            LblDescuentoSubtotal.setText(""+oCEProformaMatriz.getDescuentoEnSubtotal());
            TxtDescuento.setText(""+oCEProformaMatriz.getDescuento());
            LblSubtotalNeto.setText(oCEProformaMatriz.getSubTotalNeto()+"");
            LblSubTotalNetoSinIgv.setText(oCEProformaMatriz.getSubtotalNetoSinIGV()+"");
            LblDescuentoValores.setText(oCEProformaMatriz.getDescuentoValores()+"");
            LblIgv.setText(oCEProformaMatriz.getIGV()+"");
            LblISC.setText(oCEProformaMatriz.getISC()+"");
            IdProforma=oCEProformaMatriz.getIdProforma();
            LblMontoTotal.setText(""+oCEProformaMatriz.getMontoTotal());
            lblMontoPercepcion.setText(""+oCEProformaMatriz.getMontoPercepcion());
            LblMontoTotalPagar.setText(""+oCEProformaMatriz.getTotalPagar());
            LblDescuentoTotal.setText(""+oCEProformaMatriz.getDescuentoEnSubtotal());
            DtchFechaVencimiento.setDate(oCEProformaMatriz.getDate());
            if(oCEProformaMatriz.getMontoPercepcion()>0)
            {chkPercepcion.setSelected(true);}
            else{chkPercepcion.setSelected(false);}
           
            idCliente=oCEProformaMatriz.getIdCliente();
            Cliente=oCEProformaMatriz.getCliente();
            TxtCliente.setText(oCEProformaMatriz.getCliente());
            TxtDNI.setText(oCEProformaMatriz.getDNI());
            TxtDireccion.setText(oCEProformaMatriz.getDireccion());
            TxtProducto.setText("");
            TxtSucursal.setText(oCEProformaMatriz.getSucursal());
            TxtVendedor.setText(oCEProformaMatriz.getEmpleado());
            TxtlHoraRegistro1.setText(oCEProformaMatriz.getFecha());
            CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuentoProforma,oCEProformaMatriz.getIdDescuento());
            CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante,oCEProformaMatriz.getIdTipoComprobante());
            CLCargarCombo.buscarIdMoneda(CbxMoneda,oCEProformaMatriz.getIdMoneda());
            oCLPedido.setTipoCambioAnterior(oCEProformaMatriz.getTipoCambio());
            if(oCEProformaMatriz.getLstProformaDetalle()!=null)
            {
               for(int i=0; i<oCEProformaMatriz.getLstProformaDetalle().size();i++){

                   Vector oVector = new Vector();

                ((DefaultTableModel)TblProformaDetalle.getModel()).addRow(oVector);
                
                CEProformaDetalle oCEProformaDetalle=oCEProformaMatriz.getLstProformaDetalle().get(i) ;
                oCEProformaDetalle.setIdAbm(2);
              //  oCEProformaDetalle.setEqivalenciaUMBAntenior(oCEProformaDetalle.getEqivalenciaUMB());
                CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                oCEUnidadMedidaProducto.setTipoUnidad(oCEProformaDetalle.getUnidadMedida());
                oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEProformaDetalle.getIdUnidadMedidaVenta());

                TblProformaDetalle.setValueAt(oCEProformaDetalle,i,oCLPedido.colCodigo);
                TxtProducto.setText(oCEProformaDetalle.getProducto());
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getIdAlmacen(),i,oCLPedido.colAlm);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getProducto(),i,oCLPedido.colProducto);
                TblProformaDetalle.setValueAt(oCEUnidadMedidaProducto,i,oCLPedido.colUnidadMedida);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getPrecio(),i,oCLPedido.colPrecio);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getPrecioSinRedon(),i,oCLPedido.colprecio_sin_redon);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getCantidad(),i,oCLPedido.colCantidad);
                TblProformaDetalle.setValueAt(CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuento.getModel(), oCEProformaDetalle.getIdTipoDescuento()),i,oCLPedido.colTipoDescuento);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getImportelSinDescuento(),i,oCLPedido.colImporSinDesc);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getImporteConDescuento(),i,oCLPedido.colImporConDesc);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getDescuentoEnValores(), i, oCLPedido.colDestValores);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getPrecioConDesc(), i, oCLPedido.colPrecioConDesc);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getExonerado(), i, oCLPedido.colExonerado);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getDescuento(),i,oCLPedido.colDescuento);
                }
            }
     

            habilitarControles(false);
            BtnImprimir.setEnabled(true);
            oclBotonesABM.controlBoton(false, false, true, true, true, false);
            btnEditar.setEnabled(true);
            if(IdEstado!=1)
            {
                btnAnular.setEnabled(false);
                btnEditar.setEnabled(false);
            }
            pAccionABM=4;// consulta
            BtnImprimir.requestFocus();
        }
            oCLPedido.setCalcular(true);
       }
        catch (Exception e)
    {
        System.out.println("buscarProforma - DlgGestion Proforma : "+ e);
        JOptionPane.showMessageDialog(this,"operación fallida");

    }
    }
    private void ObtenerListadePrecios()
    {
        CEProformaDetalle oCEProformaDetalle;
        List<CEProductoPrecio> listaPrecioProducto;
       for( int i =0;i<TblProformaDetalle.getRowCount();i++)
            {

                oCEProformaDetalle= (CEProformaDetalle)TblProformaDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEProformaDetalle!=null){
                    listaPrecioProducto = new ArrayList<CEProductoPrecio>();
                    listaPrecioProducto=CCProductoPrecio.consultarPrecioProducto(oCEProformaDetalle.getIdProducto());
                    TblProformaDetalle.setValueAt(listaPrecioProducto,i, oCLPedido.colListaPrecio);
                }
        }
    }
    private void TxtClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtClienteMouseClicked

        if(evt.getClickCount()==2) {
            buscarCliente();
        }
}//GEN-LAST:event_TxtClienteMouseClicked

    private void TxtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarCliente();
            
        }
}//GEN-LAST:event_TxtClienteKeyPressed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
     pAccionABM=1;
        oclBotonesABM.controlBoton(false, true, false, false, true, false);
       habilitarControles(true);
       LblUltimoNumeroProforma.setText("");
       TblProformaDetalle.requestFocus();
        TblProformaDetalle.changeSelection(0,oCLPedido.colProducto, false, false);
        BtnImprimir.setEnabled(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed


      oclBotonesABM.controlBoton(true, false, false, false, true, false);

         
       CEProformaMatriz oCEProforma= new CEProformaMatriz();
        oCEProforma.setIdProforma(IdProforma);
        oCEProforma.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCEProforma.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCEProforma.setCliente(TxtCliente.getText());
        oCEProforma.setCodigo(Codigo);
        
           
        DlgAnularProforma oDlgAnularProforma= new DlgAnularProforma(oparent,true , oCEProforma);
        oDlgAnularProforma.setLocationRelativeTo(null);
        oDlgAnularProforma.setVisible(true);
        limpiarFormulario();
        habilitarControles(false);
                
    }//GEN-LAST:event_btnAnularActionPerformed

  
    private void EventoEditar()
    {

      try {

        if(pAccionABM!=2)
        {
            oclBotonesABM.controlBoton(false, true, false, false, true, false);
             habilitarControles(true);
             ObtenerListadePrecios();
             CEMoneda oCEMoneda=(CEMoneda)CbxMoneda.getSelectedItem();
            if(oCEMoneda!=null)
                {
                    if(oCEMoneda.getId_moneda()!=1)
                    {
                        if(oCLPedido.getTipoCambioAnterior()!=Float.parseFloat(TxtTipoCambio.getText())){
                        JOptionPane.showMessageDialog(this,"<HTML>El Proforma se registro con el tipo de Cambio: "+oCLPedido.getTipoCambioAnterior()+
                                                            "<BR>se actualizaran los importes con el Tipo Cambio Actual: "+TxtTipoCambio.getText()+"</HTML>");
                        oCLPedido.calcularEquivalenciaMoneda();
                        }
                    }
                }
             pAccionABM=2;
            }
        }
        catch (Exception e)
        {
            System.out.println("btnEditarActionPerformed - DlgGestion Proforma : "+ e);

        }
    }
    private void CbxTipoDescuentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxTipoDescuentoMouseClicked

    }//GEN-LAST:event_CbxTipoDescuentoMouseClicked

    private void JMAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMAgregarActionPerformed

        eventoAgregar();
}//GEN-LAST:event_JMAgregarActionPerformed

    private void JMEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMEliminarActionPerformed

        this.QuitarFila(TblProformaDetalle.getSelectedRow());
    }//GEN-LAST:event_JMEliminarActionPerformed

    private void JMVerPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMVerPreciosActionPerformed
       
    verPrecios(0);
    }//GEN-LAST:event_JMVerPreciosActionPerformed


    private void verPrecios(int accion)// accion =0 ver , accion =3 modificar
    {
        try{
        int fila=TblProformaDetalle.getSelectedRow();

        if(fila!=-1){
            boolean ismodificable=false;
            float precioNuevo=0;
            CEProformaDetalle oCEProformaDetalle= (CEProformaDetalle)TblProformaDetalle.getValueAt(fila, oCLPedido.colCodigo);
             if(oCEProformaDetalle!=null){
                    if(TblProformaDetalle.getValueAt( fila, oCLPedido.colPrecio)!=null)
                    {
                        precioNuevo=Float.parseFloat(TblProformaDetalle.getValueAt(fila, oCLPedido.colPrecio).toString());
                    }
                    if(TblProformaDetalle.getValueAt(fila, oCLPedido.colisnPrecio)!=null){
                        ismodificable=Boolean.parseBoolean(TblProformaDetalle.getValueAt(fila, oCLPedido.colisnPrecio).toString());
                    }
                    float cantidad=0;
                    if(TblProformaDetalle.getValueAt(fila, oCLPedido.colCantidad)!=null){
                       cantidad=Float.parseFloat(TblProformaDetalle.getValueAt(fila, oCLPedido.colCantidad).toString());
                       }
                     CEUnidadMedidaProducto oCEUnidadMedidaProducto=(CEUnidadMedidaProducto)TblProformaDetalle.getValueAt(fila, oCLPedido.colUnidadMedida);
                     DlgConsultarPrecio odialogo=new DlgConsultarPrecio(null, true,oCLPedido.obtenerListaPorUnidad(),oCEProformaDetalle.getProducto(),
                                                    oCEUnidadMedidaProducto.getTipoUnidad(),accion,ismodificable,precioNuevo,cantidad);
                     odialogo.setTitle("Consulta de Precios");
                     if(accion==2){odialogo.setTitle("Modificar de Precio");}     
                     odialogo.setLocationRelativeTo(null);
                     odialogo.setVisible(true);
                     if(accion==2){
                                 precioNuevo=odialogo.getPrecio();
                                 if(precioNuevo!=0){
                                     TblProformaDetalle.setValueAt(CLRedondear.RedondearString(precioNuevo,4)+"", fila, oCLPedido.colPrecio);
                                     oCLPedido.CalcularCelda(fila, oCLPedido.colCantidad);
                                     }
                                }
             }
             else{
                 JOptionPane.showMessageDialog(this,"Aun no seleccionado un producto");
             }
        }
        }
        catch (Exception e)
    {
        System.out.println("ver precios - DlgGestion Proforma : "+ e);
        JOptionPane.showMessageDialog(this,"operación fallida");

    }

    }
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseReleased

    private void JMModificaPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMModificaPrecioActionPerformed
        verPrecios(2);
    }//GEN-LAST:event_JMModificaPrecioActionPerformed

    private void TxtProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtProductoMouseClicked

      if(evt.getClickCount()==2)
        {
            BuscarProducto();
        }
    }//GEN-LAST:event_TxtProductoMouseClicked

    private void TxtProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyPressed

      if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER)
        {
        evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);

            BuscarProducto();

        }
    

    }//GEN-LAST:event_TxtProductoKeyPressed

    private void TxtProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyReleased

  
        
    }//GEN-LAST:event_TxtProductoKeyReleased

    private void TxtProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyTyped
         
    }//GEN-LAST:event_TxtProductoKeyTyped

    boolean iscerrando=false;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       iscerrando=true;
    }//GEN-LAST:event_formWindowClosing

    private void TblProformaDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblProformaDetalleKeyReleased

        try {
        if(TblProformaDetalle.getSelectedColumn()==oCLPedido.colImporSinDesc&&(evt.getKeyCode()==evt.VK_ENTER||evt.getKeyCode()==evt.VK_TAB)) {
            if(TblProformaDetalle.getSelectedRow()+1==TblProformaDetalle.getRowCount()){
                int fila=TblProformaDetalle.getSelectedRow()+1;
                int columnaValidada=ValidarRegistro(fila);
                if(columnaValidada==0) {
                    boolean isAgrega=agregarNuevaFila(fila);
                    if(isAgrega){
                    TblProformaDetalle.requestFocus();
                    TblProformaDetalle.changeSelection(fila, oCLPedido.colProducto, false, false);
                    }
                    else{ TxtCliente.requestFocus();}
                }else{
                    TblProformaDetalle.requestFocus();
                    TblProformaDetalle.changeSelection(TblProformaDetalle.getSelectedRow(), columnaValidada, false, false);
                }
                TxtProducto.setText("");

                return;
            }

        }

        if(colValidada!=-1){

            TblProformaDetalle.requestFocus();
            TblProformaDetalle.changeSelection(TblProformaDetalle.getSelectedRow(), colValidada, false, false);
            colValidada=-1;
        }

        if(TblProformaDetalle.getSelectedColumn()==oCLPedido.colPrecio&&(evt.getKeyCode()==evt.VK_F2||evt.getKeyCode()==evt.VK_INSERT)){
            verPrecios(2);
        }

        }
        catch (Exception e)
            {
                System.out.println("TblProformaDetalleKeyReleased - DlgGestion Proforma : "+ e);

            }

    }//GEN-LAST:event_TblProformaDetalleKeyReleased

    private void TblProformaDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblProformaDetalleKeyPressed


        if(evt.getKeyCode()==evt.VK_ENTER&&TblProformaDetalle.getSelectedColumn()==oCLPedido.colProducto) {
            BuscarProducto();

        }
        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {
            evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);
            if(TblProformaDetalle.getSelectedColumn()==oCLPedido.colCantidad){
                TblProformaDetalle.changeSelection(TblProformaDetalle.getSelectedRow(), oCLPedido.colImporSinDesc-1, false, false);
            }
            if(TblProformaDetalle.getSelectedColumn()==oCLPedido.colprecio_sin_redon){
                TblProformaDetalle.changeSelection(TblProformaDetalle.getSelectedRow(), oCLPedido.colPrecio, false, false);
            }

        }


    }//GEN-LAST:event_TblProformaDetalleKeyPressed

    private void verficarTextoVacio(int fila){

        try{
                if(fila!=-1)
                   {
                   if(TblProformaDetalle.getValueAt(fila, oCLPedido.colProducto)!=null){
                       String producto=TblProformaDetalle.getValueAt(fila, oCLPedido.colProducto).toString();
                       if(producto.trim().equals("")){
                           if(TblProformaDetalle.getValueAt(fila, oCLPedido.colCodigo)!=null){

                               TblProformaDetalle.setValueAt(((CEProformaDetalle)TblProformaDetalle.getValueAt(fila, oCLPedido.colCodigo)).getProducto(),fila, oCLPedido.colProducto);
                           }

                       }
                    }
                   }

                }
        catch (Exception e)
            {
                System.out.println("verificartextoVacio - DlgGestion Proforma : "+ e);

            }
    }
    private void TblProformaDetallePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblProformaDetallePropertyChange
        if(!iscerrando){
            if(cont==1){
                int fila=TblProformaDetalle.getSelectedRow();
                verficarTextoVacio(fila);
                if(fila!=-1){
                    verificarDatos(fila);}
            }else{
                cont=0;
            }
            cont++;
        }



}//GEN-LAST:event_TblProformaDetallePropertyChange

    private void TblProformaDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblProformaDetalleMousePressed
        if(evt.isMetaDown()) // isMetaDown es el click derecho
            if (!evt.isPopupTrigger()) {
                JmMenuTablaProforma.show( evt.getComponent(),evt.getX() , evt.getY()) ;

            }
}//GEN-LAST:event_TblProformaDetalleMousePressed

    private void TblProformaDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblProformaDetalleMouseClicked

    }//GEN-LAST:event_TblProformaDetalleMouseClicked

    private void TxtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDescuentoKeyReleased
      oCLPedido.eventoDescuento();
    }//GEN-LAST:event_TxtDescuentoKeyReleased

    private void TxtDescuentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtDescuentoFocusLost
       if(!TxtDescuento.getText().equals("")&&TxtDescuento.getText()!=null&&!TxtDescuento.getText().isEmpty())
       {
          TxtDescuento.setText(CLRedondear.RedondearString(Float.parseFloat(TxtDescuento.getText()), 2));

       }
    }//GEN-LAST:event_TxtDescuentoFocusLost

    private void TxtDescuentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtDescuentoFocusGained
      TxtDescuento.selectAll();
    }//GEN-LAST:event_TxtDescuentoFocusGained

    private void CbxMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxMonedaActionPerformed
        // TODO add your handling code here


        TxtTipoCambio.setText(""+oCLPedido.ObtenerTipoCambioMoneda(CbxMoneda));
        obtenerAbrevMoneda();
        oCLPedido.calcularEquivalenciaMoneda();
    }//GEN-LAST:event_CbxMonedaActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed

        FrmSistemaMenu.setSiNoAtencion(CCAtencionSistema.ConsultarAtencionSistema());
        oCLPedido.CalcularSubtotales();
        oCLPedido.calcularImportes();
        oCLPedido.eventoDescuento();
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void DtchFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtchFechaPropertyChange

       
}//GEN-LAST:event_DtchFechaPropertyChange

    private void chkPercepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPercepcionActionPerformed

        oCLPedido.CalcularSubtotales();
        oCLPedido.calcularImportes();
        oCLPedido.eventoDescuento();

    }//GEN-LAST:event_chkPercepcionActionPerformed

    private void btnBuscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPedidoActionPerformed
buscarProforma();
     
}//GEN-LAST:event_btnBuscarPedidoActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed

        CLExportarExcel oExportar=new CLExportarExcel(TblProformaDetalle,
                new String[]{lblEtiqTipoComp.getText(),((CETipoComprobante)CbxTipoComprobante.getSelectedItem()).getDescripcion(),
                LblEtiqNum.getText(),LblUltimoNumeroProforma.getText(),lblEtiqEstado.getText(),TxtEstado.getText(),
                LblEtiqCliente.getText(),TxtCliente.getText(),
                LblEtiqDireccion.getText(),TxtDireccion.getText(),LblEtiqDNI.getText(),TxtDNI.getText(),
                LblEtiqCondicion.getText(),((CECondicion)CbxCondicion.getSelectedItem()).getDescripcion(),
                LblEtiqMoneda.getText(),((CEMoneda)CbxMoneda.getSelectedItem()).getDescripcion(),
                LblEtiqTipoCambio.getText(),TxtTipoCambio.getText(),LblEtiqSucursal.getText(),TxtSucursal.getText(),
                lblEtiqVendedor.getText(),TxtVendedor.getText(),LblEtiqHoraRegistro.getText(),TxtlHoraRegistro1.getText()}
                ,new String[]{LblEtiqSubtotalBruto.getText(),LblSubtotalBruto.getText(),LblEtiqDescValores.getText(),LblDescuentoSubtotal.getText(),
                LblEtiqSubtotalNeto.getText(),LblSubtotalNeto.getText(),
                LblEtiqTipoDescuento.getText(),((CETipoDescuento)CbxTipoDescuento.getSelectedItem()).getDescripcion(),
                LblEtiqDescuento.getText(),TxtDescuento.getText(),LblEtiqDescTotal.getText(),LblDescuentoTotal.getText()},
                new String[]{LblEtiqSubNetoSinIgv.getText(),LblSubTotalNetoSinIgv.getText(),LblEtiqIgv.getText(),LblIgv.getText(),
                LblEtiqISC.getText(),LblISC.getText(),LblEtiqDescValores.getText(),LblDescuentoValores.getText(),
                LblEtiqMontoTotal.getText(),LblMontoTotal.getText(),LblEtiqPercepcion.getText(),lblMontoPercepcion.getText(),
                LblEtiqTotalPagar.getText(),LblMontoTotalPagar.getText()
                },"Registro de Proforma");
        oExportar.GuardarArchivoExcel(this);
}//GEN-LAST:event_btnExportar1ActionPerformed

    private void chkFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFechaActionPerformed

        if(chkFecha.isSelected())DtchFecha.setEnabled(true);
        else DtchFecha.setEnabled(false);
    }//GEN-LAST:event_chkFechaActionPerformed

    private void DtchFechaVencimientoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtchFechaVencimientoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_DtchFechaVencimientoPropertyChange

    private void BtnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirActionPerformed

        CEProformaMatriz oCEProformaMatrizAux= new  CEProformaMatriz();

        if(IdEstado==1){
       
         oCEProformaMatrizAux.setIdUltimoEstado(15);
         oCEProformaMatrizAux.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
         oCEProformaMatrizAux.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
         oCEProformaMatrizAux.setIdProforma(IdProforma);
         oCEProformaMatrizAux.setTotalPagar(VerificadorDeTxt.convertFloat(LblMontoTotalPagar.getText()));
         if(CCProforma.ImprimirProforma(oCEProformaMatrizAux)==1)
         {            
            DlgReporteProforma oDlgDetalleComprobante = new DlgReporteProforma(oparent, true, oCEProformaMatrizAux);
            oDlgDetalleComprobante.setLocationRelativeTo(null);
            oDlgDetalleComprobante.setVisible(true);
         }
        else
         {
            JOptionPane.showMessageDialog(null, "No se pudo completar la operacion");
         }
        }else
        {
            oCEProformaMatrizAux.setIdProforma(IdProforma);
            oCEProformaMatrizAux.setTotalPagar(VerificadorDeTxt.convertFloat(LblMontoTotalPagar.getText()));
            DlgReporteProforma oDlgDetalleComprobante = new DlgReporteProforma(oparent, true, oCEProformaMatrizAux);
            oDlgDetalleComprobante.setLocationRelativeTo(null);
            oDlgDetalleComprobante.setVisible(true);
        }
        



}//GEN-LAST:event_BtnImprimirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        try {

            pAccionABM=2;
            oclBotonesABM.controlBoton(false, true, false, false, true, false);
            BtnImprimir.setEnabled(false);
             habilitarControles(true);
             ObtenerListadePrecios();
             CEMoneda oCEMoneda=(CEMoneda)CbxMoneda.getSelectedItem();
            if(oCEMoneda!=null)
                {
                    if(oCEMoneda.getId_moneda()!=1)
                    {
                        if(oCLPedido.getTipoCambioAnterior()!=Float.parseFloat(TxtTipoCambio.getText())){
                        JOptionPane.showMessageDialog(this,"<HTML>El pedido se registro con el tipo de Cambio: "+oCLPedido.getTipoCambioAnterior()+
                                                            "<BR>se actualizaran los importes con el Tipo Cambio Actual: "+TxtTipoCambio.getText()+"</HTML>");
                        oCLPedido.calcularEquivalenciaMoneda();
                        }
                    }
                }


    }
        catch (Exception e)
    {
        System.out.println("btnEditarActionPerformed - DlgGestion Pedido : "+ e);

    }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        eventoGuardarProforma();
    }//GEN-LAST:event_btnGuardarActionPerformed

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

    private void agregarProformaDetalle(CEAlmacenProducto oCEAlmacenProducto,List<CEProductoPrecio> olistaPrecio)
    {
        try{
        if(oCEAlmacenProducto!=null){


        int fila=TblProformaDetalle.getSelectedRow();

        CEProformaDetalle  oCEProformaDetalleExist= (CEProformaDetalle)TblProformaDetalle.getValueAt(fila, oCLPedido.colCodigo);
        CEProformaDetalle oCEProformaDetalle= new CEProformaDetalle();
        oCEProformaDetalle.setIdAbm(1);
        if(oCEProformaDetalleExist!=null)
        {
            if(oCEProformaDetalleExist.getIdProformaDetalle()>0)
            {
                oCEProformaDetalle.setIdAbm(2);
                oCEProformaDetalle.setIdProformaDetalle(oCEProformaDetalleExist.getIdProformaDetalle());
            }
        }

        oCEProformaDetalle.setProducto(oCEAlmacenProducto.getDescripcion());
        oCEProformaDetalle.setIdProducto(oCEAlmacenProducto.getIdProducto());
        oCEProformaDetalle.setIdAlmacen(oCEAlmacenProducto.getIdAlmacen());
        oCEProformaDetalle.setIdUnidadMedidaVenta(oCEAlmacenProducto.getIdUnidadBase());
        oCEProformaDetalle.setSinoImpuesto(oCEAlmacenProducto.isSiNoImpuesto());
        oCEProformaDetalle.setConPercepcion(oCEAlmacenProducto.isPercepcion());
        CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
        oCEUnidadMedidaProducto.setTipoUnidad(oCEAlmacenProducto.getUnidad_medida());
        oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEAlmacenProducto.getIdUnidadBase());
        oCEProformaDetalle.setCodigoProducto(oCEAlmacenProducto.getCodigo());
        oCEProformaDetalle.setCantidad(oCEAlmacenProducto.getCantidad());
        oCEProformaDetalle.setEqivalenciaUMB(oCEAlmacenProducto.getEquivalenciaUMB());
        CETipoDescuento oTipoDescuento=(CETipoDescuento)CbxTipoDescuento.getSelectedItem();
        oCLPedido.setActionPrecio(false);
                TblProformaDetalle.setValueAt(oCEProformaDetalle,fila,oCLPedido.colCodigo);
                TblProformaDetalle.setValueAt(oCEAlmacenProducto.getStockReal(),fila, oCLPedido.colStock);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getProducto(),fila,oCLPedido.colProducto);
                TblProformaDetalle.setValueAt(oCEUnidadMedidaProducto,fila,oCLPedido.colUnidadMedida);
                TblProformaDetalle.setValueAt(0,fila,oCLPedido.colDescuento);
                TblProformaDetalle.setValueAt(oTipoDescuento, fila, oCLPedido.colTipoDescuento);
                TblProformaDetalle.setValueAt(olistaPrecio,fila , oCLPedido.colListaPrecio);
                TblProformaDetalle.setValueAt(0,fila , oCLPedido.colPrecio);
                TblProformaDetalle.setValueAt(oCEProformaDetalle.getIdAlmacen(),fila , oCLPedido.colAlm);
                float cant=oCEAlmacenProducto.getCantidad();
                if(cant==0)
                TblProformaDetalle.setValueAt(null, fila, oCLPedido.colCantidad);
                else{
                    TblProformaDetalle.setValueAt(oCEAlmacenProducto.getCantidad(), fila, oCLPedido.colCantidad);
                }
                TblProformaDetalle.setValueAt(CLRedondear.RedondearString(oCEAlmacenProducto.getPrecio(),4), fila, oCLPedido.colPrecio);
                TblProformaDetalle.setValueAt(CLRedondear.RedondearString(oCEAlmacenProducto.getPrecio_sin_redon(),4), fila, oCLPedido.colprecio_sin_redon);
                float cantidad=0;
                if(TblProformaDetalle.getValueAt(fila, oCLPedido.colCantidad)!=null){
                cantidad=Float.parseFloat(TblProformaDetalle.getValueAt(fila, oCLPedido.colCantidad).toString());
                }
                if(cantidad>0){
                    verificarDatos(fila);

                    oCLPedido.calcularPrecio(fila);
                    oCLPedido.CalcularCelda(fila,oCLPedido.colCantidad);
                    
                }

        }
        }
        catch (Exception e)
            {
                System.out.println("agregarpProformaDetalle - DlgGestion Proforma : "+ e);

            }
                
    }
    private void limpiarFormulario()
    {
      IdEstado=1;
      chkFecha.setSelected(false);
      DtchFechaVencimiento.setDate(null);
      DtchFecha.setEnabled(false);
      DtchFecha.setDate(new Date());
      chkPercepcion.setSelected(false);
      CbxTipoComprobante.setEnabled(true);
      CbxTipoComprobante.setSelectedIndex(0);
      TxtEstado.setText("EMITIDO");
      CbxCondicion.setSelectedIndex(0);
      CbxMoneda.setSelectedIndex(0);
      CbxTipoComprobante.setSelectedIndex(0);
      jTextFieldNumber.setText("");
      LblUltimoNumeroProforma.setText("");
      TxtProducto.setText("");
      TxtCliente.setText("");
      TxtDireccion.setText("");
      TxtDNI.setText("");
      TxtDescuento.setText("0");
      idCliente=0;
      Cliente="";
      oCLPedido.limpiarTabla();
      LblDescuentoValores.setText("0");
      LblSubtotalNeto.setText("0");
      LblSubTotalNetoSinIgv.setText("0");
      LblIgv.setText("0");
      LblISC.setText("0");
      LblMontoTotal.setText("0");
      LblDescuentoTotal.setText("0");
      TxtDescuento.setText("0");
      CbxTipoDescuentoProforma.setSelectedIndex(0);
      TblProformaDetalle.setEnabled(false);
      agregarNuevaFila(0);
      TblProformaDetalle.setEnabled(true);
      TblProformaDetalle.requestFocus();
      TblProformaDetalle.changeSelection(0, oCLPedido.colProducto, false, false);
      TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
      TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
    }
    /**
    * @param args the command line arguments
    */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnImprimir;
    private javax.swing.JButton BtnQuitar;
    private ComboBox.ComboBox CbxCondicion;
    private ComboBox.ComboBox CbxMoneda;
    private ComboBox.ComboBox CbxTipoComprobante;
    private ComboBox.ComboBox CbxTipoDescuento;
    private ComboBox.ComboBox CbxTipoDescuentoProforma;
    private javax.swing.JComboBox CbxUnidadMedida;
    private com.toedter.calendar.JDateChooser DtchFecha;
    private com.toedter.calendar.JDateChooser DtchFechaVencimiento;
    private javax.swing.JMenuItem JMAgregar;
    private javax.swing.JMenuItem JMEliminar;
    private javax.swing.JMenuItem JMModificaPrecio;
    private javax.swing.JMenuItem JMVerPrecios;
    private javax.swing.JPopupMenu JmMenuTablaProforma;
    private Label.Label LblDescuentoSubtotal;
    private Label.Label LblDescuentoTotal;
    private Label.Label LblDescuentoValores;
    private Label.Label LblEtiqCliente;
    private Label.Label LblEtiqCondicion;
    private Label.Label LblEtiqDNI;
    private Label.Label LblEtiqDescTotal;
    private Label.Label LblEtiqDescValores;
    private Label.Label LblEtiqDescuento;
    private Label.Label LblEtiqDireccion;
    private Label.Label LblEtiqDsctSubtValores;
    private Label.Label LblEtiqHoraRegistro;
    private Label.Label LblEtiqISC;
    private Label.Label LblEtiqIgv;
    private Label.Label LblEtiqMoneda;
    private Label.Label LblEtiqMontoTotal;
    private Label.Label LblEtiqNum;
    private Label.Label LblEtiqPercepcion;
    private Label.Label LblEtiqSubNetoSinIgv;
    private Label.Label LblEtiqSubtotalBruto;
    private Label.Label LblEtiqSubtotalNeto;
    private Label.Label LblEtiqSucursal;
    private Label.Label LblEtiqTipoCambio;
    private Label.Label LblEtiqTipoDescuento;
    private Label.Label LblEtiqTotalPagar;
    private Label.Label LblHoraSistema;
    private Label.Label LblISC;
    private Label.Label LblIgv;
    private Label.Label LblMontoTotal;
    private Label.Label LblMontoTotalPagar;
    private Label.Label LblPorcentaje;
    private Label.Label LblSubTotalNetoSinIgv;
    private Label.Label LblSubtotalBruto;
    private Label.Label LblSubtotalNeto;
    private javax.swing.JTextField LblUltimoNumeroProforma;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JTable TblProformaDetalle;
    private TextField.JTxtLetra TxtCliente;
    private Label.Label TxtDNI;
    private javax.swing.JTextField TxtDescuento;
    private Label.Label TxtDireccion;
    private Label.Label TxtEstado;
    private TextField.JTxtNinguno TxtProducto;
    private Label.Label TxtSucursal;
    private Label.Label TxtTipoCambio;
    private Label.Label TxtVendedor;
    private Label.Label TxtlHoraRegistro1;
    private BotonesABM.BtnEliminar btnAnular;
    private BotonesABM.BtnBuscar btnBuscarPedido;
    private BotonesABM.BtnCancelar btnCancelar;
    private BotonesABM.BtnEditar btnEditar;
    private BotonesABM.BtnExportar btnExportar1;
    private BotonesABM.BtnGuardar btnGuardar;
    private BotonesABM.BtnNuevo btnNuevo;
    private Opcion.CheckBox chkFecha;
    private Opcion.CheckBox chkPercepcion;
    private util.clases.vtaVenta.ColumnButton columnButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldNumber;
    private Label.Label label20;
    private Label.Label lblEtiqEstado;
    private Label.Label lblEtiqEstado1;
    private Label.Label lblEtiqTipoComp;
    private Label.Label lblEtiqVendedor;
    private Label.Label lblMontoPercepcion;
    // End of variables declaration//GEN-END:variables

    private static final String ACTION_CLOSE = "ACTION_CLOSE";
    private static final String ACTION_GUARDAR = "ACTION_NUEVO";
    private static final String ACTION_VER_PRECIOS= "ACTION_VER_PRECIOS";
    private static final String ACTION_QUITAR= "ACTION_QUITAR";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke Guardad = KeyStroke.getKeyStroke(KeyEvent.VK_G,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        KeyStroke VerPrecios = KeyStroke.getKeyStroke(KeyEvent.VK_P,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_GUARDAR, Guardad, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_VER_PRECIOS, VerPrecios, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            eventoCancelar();
        }

         if(e.getActionCommand().equals(ACTION_GUARDAR))
        {
              eventoGuardarProforma();
              return;
        }
        if(e.getActionCommand().equals(ACTION_VER_PRECIOS))
        {
            verPrecios(0);return;
        }
  
    }

}
