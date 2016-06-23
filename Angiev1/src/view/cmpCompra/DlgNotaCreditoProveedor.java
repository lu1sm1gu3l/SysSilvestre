package view.cmpCompra;

import controller.almAlmacen.CCSalidaProducto;
import controller.cmpCompra.CCComprobanteCompra;
import controller.cmpCompra.CCNotaCreditoProveedor;
import controller.grlGeneral.CCEstado;
import controller.vtaVenta.CCConcepto;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.cmpCompra.entidad.CEComprobanteCompraDetalle;
import modelo.cmpCompra.entidad.CEComprobanteCompraMatriz;
import modelo.cmpCompra.entidad.CENotaCreditoProveedor;
import modelo.cmpCompra.entidad.CENotaCreditoProveedorDetalle;
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CEConcepto;
import util.clases.almAlmacen.JTableX;
import util.clases.cmpCompra.CLNotaCreditoProveedor;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLCheckBoxHeader;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.MiReloj;
import util.clases.grlGeneral.VerificadorDeTxt;
import util.clases.vtaVenta.CLCargarCombo;
import view.FrmSistemaMenu;

/**
 *
 * @author Luiggi
 */
public class DlgNotaCreditoProveedor extends DialogoPadre implements ActionListener{

    private  int IdEstadoComprobante=0;
    private MiReloj hilo;
    private CLNotaCreditoProveedor oCLNotaCreditoProveedor;
    private  int pAccionABM=0;
    private  long pIdComprobante=0;
    private int pAccionDlg=0;//0:Nota credito 1: anulacion 2 : devolucion
    CLBotonesABM oclBotonesABM= new CLBotonesABM();
    private boolean  iscompAnulado= false;
    private java.awt.Frame oparent;

    /** Creates new form DlgGestionPedido */
    public DlgNotaCreditoProveedor(java.awt.Frame parent, boolean modal,int accionDlg,long idComprobante){
        super(parent, modal);
        initComponents();
        oparent=parent;
        this.setLocationRelativeTo(null);
        TxtTipoCambio.setText("1");        
        TxtNumNC.requestFocus();
        this.pAccionDlg=accionDlg;
        cargarUtilidades();
        oclBotonesABM.controlBoton(false,true, false, false, true, false);
        pAccionABM=1;
        if(pAccionDlg>0)
        {
            this.pIdComprobante=idComprobante;            
            buscarComprobanteCompra();
            oclBotonesABM.controlBoton(false,true, false, false, true, false);
        }
        HabilitarcolumnasporConcepto();
        pintarTabla();
        btnEditar.setVisible(false);
        btnAnular.setVisible(false);
    }

    private void cargarUtilidades()
    {
         jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero",0,jTextFieldNumber));
         TblNotaCreditoDetalle.setSurrendersFocusOnKeystroke(true);
        ((JTableX)TblNotaCreditoDetalle).setSelectAllForEdit(true);
        TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
        TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
        oCLNotaCreditoProveedor = new CLNotaCreditoProveedor(TblNotaCreditoDetalle,LblSubTotalNetoSinIgvNC, LblMontoAcreditar,LblIgvNC,
                                        LblISCNC,LblMontoTotalAcreditar,lblMontoPercepcion);
        jCheckBox1.setSelected(true);
        CbxConcepto.setModel(CLComboBox.cargarCombo(CCConcepto.getNotaCerditoProveedorMasTodos()));
        CLCargarCombo.buscarIdConcepto(CbxConcepto, pAccionDlg);
        TableColumn aColumn = (TableColumn)TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCreditoProveedor.colCheck);
        aColumn.setHeaderRenderer(new CLCheckBoxHeader(jCheckBox1.getItemListeners()[0]));
        TblNotaCreditoDetalle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCreditoProveedor.colDescuento).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCreditoProveedor.colImporSinDesc).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCreditoProveedor.colTipoDescuento).setMinWidth(0);
        hilo= new MiReloj(LblHoraSistema);
        hilo.start();
        TableColumn columnaCantidadNueva = TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCreditoProveedor.colCantDevuelta);
        columnaCantidadNueva.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaPrecioNuevo = TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCreditoProveedor.colPrecioNuevo);
        columnaPrecioNuevo.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaMontoNuevo = TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCreditoProveedor.colImporNuevo);
        columnaMontoNuevo.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        oclBotonesABM.setBotones(btnNuevo, btnGuardar, btnEditar,btnAnular, btnCancelar, null,this);
    }
  private int idConcepto=1;
  private boolean sino_accionHabilitarColumnas=true;
  private void HabilitarcolumnasporConcepto()
    {
        if(sino_accionHabilitarColumnas){
            CEConcepto oCEConcepto=(CEConcepto)CbxConcepto.getSelectedItem();
            idConcepto=oCEConcepto.getIdConcepto();
            if(oCEConcepto!=null)
            {

                 for( int i =0;i<TblNotaCreditoDetalle.getRowCount();i++)
                {

                        if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCheck)!=null)
                        {
                           float cantidadNueva=0;
                            cantidadNueva=VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCantidad));
                 //           TblNotaCreditoDetalle.setValueAt(""+cantidadNueva,i, oCLNotaCreditoProveedor.colCantDevuelta);
                        }
              //    TblNotaCreditoDetalle.setValueAt(false,i, oCLNotaCreditoProveedor.colCheck);

                }

                repaint();
            }
        }
    }

private void pintarTabla(){
    for (int i =0; i<TblNotaCreditoDetalle.getColumnCount();i++) {
        if(i!=oCLNotaCreditoProveedor.colCheck){

    TblNotaCreditoDetalle.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if(table.getValueAt(row,oCLNotaCreditoProveedor.colCheck)!=null)
                {
                    boolean isSelccionado=Boolean.parseBoolean(table.getValueAt(row,oCLNotaCreditoProveedor.colCheck)+"");
                    if(isSelccionado)
                    {
                        cell.setBackground(new Color(198,235,210));
                        cell.setForeground(new Color(0,0,0));
                        if(isSelected)
                        {
                         cell.setForeground(table.getSelectionForeground());
                         cell.setBackground(table.getSelectionBackground());
                        }
                        return cell;
                    }
                }
                if(isSelected)
                {
                    cell.setForeground(table.getSelectionForeground());
                    cell.setBackground(table.getSelectionBackground());
                }
                else
                {
                    cell.setBackground(table.getBackground());
                    cell.setForeground(table.getForeground());
                }
                return cell;
            }
        });

      }
      
   }
}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CbxTipoDescuento = new ComboBox.ComboBox();
        CbxUnidadMedida = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextFieldNumber = new javax.swing.JTextField();
        PnlDatos2 = new javax.swing.JPanel();
        LblEtiqCliente = new Label.Label();
        LblEtiqSucursal = new Label.Label();
        TxtSucursal = new TextField.JTxtLetra();
        lblEtiqUsuario = new Label.Label();
        TxtVendedor = new TextField.JTxtLetra();
        LblEtiqMoneda = new Label.Label();
        LblEtiqTipoCambio = new Label.Label();
        TxtTipoCambio = new TextField.JTxtNinguno();
        TxtCliente = new TextField.JTxtLetra();
        LblEtiqFecha = new Label.Label();
        LblFechaRegistro = new Label.Label();
        lblEtiqTipoComp = new Label.Label();
        TxtMoneda = new TextField.JTxtLetra();
        TxtTipoComprobante = new TextField.JTxtLetra();
        btnGuardar = new BotonesABM.BtnGuardar();
        btnCancelar = new BotonesABM.BtnCancelar();
        btnEditar = new BotonesABM.BtnEditar();
        btnAnular = new BotonesABM.BtnEliminar();
        btnNuevo = new BotonesABM.BtnNuevo();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        label6 = new Label.Label();
        LblMontoTotalCobrado = new Label.Label();
        LblIgv = new Label.Label();
        label9 = new Label.Label();
        label11 = new Label.Label();
        LblSubtotalNeto = new Label.Label();
        LblISC = new Label.Label();
        label16 = new Label.Label();
        label21 = new Label.Label();
        TxtDescuento = new Label.Label();
        label23 = new Label.Label();
        label22 = new Label.Label();
        LblSubtotalBruto = new Label.Label();
        label24 = new Label.Label();
        LblDescuentoSubtotal = new Label.Label();
        label25 = new Label.Label();
        LblDescuentoTotal = new Label.Label();
        label7 = new Label.Label();
        LblSubTotaNetolSinIgv = new Label.Label();
        TxtTipoDescuento = new Label.Label();
        label29 = new Label.Label();
        LblMontoTotalComp = new Label.Label();
        label30 = new Label.Label();
        LblMontoPercepcionComp = new Label.Label();
        jPanel2 = new javax.swing.JPanel();
        LblEtiqMontoTotal = new Label.Label();
        lblMontoPercepcion = new Label.Label();
        LblEtiqIgvNC = new Label.Label();
        LblISCNC = new Label.Label();
        LblEtiqISCNC = new Label.Label();
        LblIgvNC = new Label.Label();
        LblEtiqSubtotalNetoSinIgvNC = new Label.Label();
        LblSubTotalNetoSinIgvNC = new Label.Label();
        LblEtiqTotalAcreditar = new Label.Label();
        LblMontoTotalAcreditar = new Label.Label();
        LblEtiqPercepcion = new Label.Label();
        LblMontoAcreditar = new Label.Label();
        jPanel7 = new javax.swing.JPanel();
        LblEtiqNumNC = new Label.Label();
        TxtNumNC = new TextField.JTxtNinguno();
        label10 = new Label.Label();
        CbxConcepto = new ComboBox.ComboBox();
        btnImportarCompra = new BotonesABM.BtnBuscar();
        btnBuscarNotaCredito = new BotonesABM.BtnBuscar();
        DtchFecha =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        LblEtiqHoraRegistro = new Label.Label();
        LblHoraSistema = new Label.Label();
        LblEtiqHoraSistema = new Label.Label();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtObservacion = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblNotaCreditoDetalle = new util.clases.almAlmacen.JTableX(){
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{
                    if(column==oCLNotaCreditoProveedor.colImporNuevo ||
                        column==oCLNotaCreditoProveedor.colCantDevuelta)
                    {
                        if(!value.toString().trim().equals("")||value!=null||!value.toString().isEmpty())
                        {
                            super.setValueAt(CLRedondear.Redondear(Float.parseFloat(value.toString()),2),row,column);
                        }
                        else
                        {
                            super.setValueAt(null,row,column);
                        }

                    }
                    else if(column==oCLNotaCreditoProveedor.colPrecioNuevo)
                    {

                        if(!value.toString().trim().equals("")||value!=null||!value.toString().isEmpty())
                        {
                            super.setValueAt(CLRedondear.Redondear(Float.parseFloat(value.toString()),4),row,column);
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

                }
                catch(Exception e)
                {
                    super.setValueAt(null,row,column);
                }
            }
        }
        ;
        label26 = new Label.Label();
        columnButton1 = new util.clases.vtaVenta.ColumnButton();
        BtnCalcularColumnas = new javax.swing.JButton();
        lblEtiqEstado = new Label.Label();
        LblEstado = new Label.Label();
        label28 = new Label.Label();
        LblNumCompr = new Label.Label();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnExportar1 = new BotonesABM.BtnExportar();

        CbxUnidadMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCheckBox1.setText("jCheckBox1");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jTextFieldNumber.setFont(new java.awt.Font("Verdana", 0, 12));
        jTextFieldNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldNumber.setBorder(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nota Crédito Proveedor");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        PnlDatos2.setBackground(java.awt.SystemColor.controlHighlight);
        PnlDatos2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "[ DATOS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        LblEtiqCliente.setText("Cliente :");
        LblEtiqCliente.setFont(new java.awt.Font("Verdana", 0, 11));

        LblEtiqSucursal.setText("Sucursal :");
        LblEtiqSucursal.setFont(new java.awt.Font("Verdana", 0, 11));

        TxtSucursal.setEditable(false);
        TxtSucursal.setText("CENTRAL");
        TxtSucursal.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtSucursal.setTamanio(50);

        lblEtiqUsuario.setText("USUARIO :");
        lblEtiqUsuario.setFont(new java.awt.Font("Verdana", 0, 11));

        TxtVendedor.setEditable(false);
        TxtVendedor.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtVendedor.setTamanio(50);

        LblEtiqMoneda.setText("Moneda ");
        LblEtiqMoneda.setFont(new java.awt.Font("Verdana", 0, 11));

        LblEtiqTipoCambio.setText("Tipo Cambio");
        LblEtiqTipoCambio.setFont(new java.awt.Font("Verdana", 0, 11));

        TxtTipoCambio.setEditable(false);
        TxtTipoCambio.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtTipoCambio.setTamanio(10);

        TxtCliente.setEnabled(false);
        TxtCliente.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtCliente.setTamanio(200);

        LblEtiqFecha.setText("Fecha ");
        LblEtiqFecha.setFont(new java.awt.Font("Verdana", 0, 11));

        LblFechaRegistro.setText("-----");
        LblFechaRegistro.setFont(new java.awt.Font("Verdana", 0, 11));

        lblEtiqTipoComp.setText("T. Comprobante");
        lblEtiqTipoComp.setFont(new java.awt.Font("Verdana", 0, 11));

        TxtMoneda.setEditable(false);
        TxtMoneda.setText("CENTRAL");
        TxtMoneda.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtMoneda.setTamanio(50);

        TxtTipoComprobante.setEditable(false);
        TxtTipoComprobante.setText("CENTRAL");
        TxtTipoComprobante.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtTipoComprobante.setTamanio(50);

        javax.swing.GroupLayout PnlDatos2Layout = new javax.swing.GroupLayout(PnlDatos2);
        PnlDatos2.setLayout(PnlDatos2Layout);
        PnlDatos2Layout.setHorizontalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblEtiqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LblEtiqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(TxtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEtiqMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtTipoComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtMoneda, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtTipoCambio, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(LblFechaRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PnlDatos2Layout.setVerticalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(LblEtiqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LblEtiqMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEtiqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEtiqTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblEtiqTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblEtiqFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnAnular.setText("ANULAR");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jPanel1.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Montos de Comprobante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label6.setText("MONTO TOTAL :");
        label6.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 130, 20));

        LblMontoTotalCobrado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotalCobrado.setText("0");
        LblMontoTotalCobrado.setFont(new java.awt.Font("Verdana", 1, 11));
        LblMontoTotalCobrado.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblMontoTotalCobrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, 100, 20));

        LblIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblIgv.setText("0");
        LblIgv.setFont(new java.awt.Font("Verdana", 1, 11));
        LblIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblIgv, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 90, 20));

        label9.setText("I.G.V :");
        label9.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 100, 20));

        label11.setText("Subt. Neto:");
        label11.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(label11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 130, 20));

        LblSubtotalNeto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalNeto.setText("0");
        LblSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 11));
        LblSubtotalNeto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblSubtotalNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 100, 20));

        LblISC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblISC.setText("0");
        LblISC.setFont(new java.awt.Font("Verdana", 1, 11));
        LblISC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblISC, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 91, 20));

        label16.setText("I.S.C :");
        label16.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(label16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 20));

        label21.setText("Tipo Dsct. :");
        label21.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(label21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        TxtDescuento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TxtDescuento.setText("0");
        TxtDescuento.setFont(new java.awt.Font("Verdana", 1, 11));
        TxtDescuento.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(TxtDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 90, 20));

        label23.setText("Dsct. :");
        label23.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(label23, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 100, 20));

        label22.setText("Subt.Bruto:");
        label22.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(label22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        LblSubtotalBruto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalBruto.setText("0");
        LblSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 11));
        LblSubtotalBruto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblSubtotalBruto, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 91, 20));

        label24.setText("Dsct. en Subt.");
        label24.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(label24, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 100, 20));

        LblDescuentoSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoSubtotal.setText("0");
        LblDescuentoSubtotal.setFont(new java.awt.Font("Verdana", 1, 11));
        LblDescuentoSubtotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblDescuentoSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 90, 20));

        label25.setText("Dsct. Total :");
        label25.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(label25, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 130, 20));

        LblDescuentoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoTotal.setText("0");
        LblDescuentoTotal.setFont(new java.awt.Font("Verdana", 1, 11));
        LblDescuentoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblDescuentoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 100, 20));

        label7.setText("Subt. Neto sin Igv:");
        label7.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 130, 20));

        LblSubTotaNetolSinIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubTotaNetolSinIgv.setText("0");
        LblSubTotaNetolSinIgv.setFont(new java.awt.Font("Verdana", 1, 11));
        LblSubTotaNetolSinIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblSubTotaNetolSinIgv, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 100, 20));

        TxtTipoDescuento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TxtTipoDescuento.setFont(new java.awt.Font("Verdana", 1, 11));
        TxtTipoDescuento.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(TxtTipoDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 90, 20));

        label29.setText("TOTAL COBRADO :");
        label29.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(label29, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 130, 20));

        LblMontoTotalComp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotalComp.setText("0");
        LblMontoTotalComp.setFont(new java.awt.Font("Verdana", 1, 11));
        LblMontoTotalComp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblMontoTotalComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 100, 20));

        label30.setText("PERCEPCION :");
        label30.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(label30, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 130, 20));

        LblMontoPercepcionComp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoPercepcionComp.setText("0");
        LblMontoPercepcionComp.setFont(new java.awt.Font("Verdana", 1, 11));
        LblMontoPercepcionComp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblMontoPercepcionComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 100, 20));

        jPanel2.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Montos de Nota de Credito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblEtiqMontoTotal.setText("MONTO TOTAL :");
        LblEtiqMontoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 160, -1));

        lblMontoPercepcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMontoPercepcion.setText("0");
        lblMontoPercepcion.setFont(new java.awt.Font("Verdana", 1, 12));
        lblMontoPercepcion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblMontoPercepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 150, -1));

        LblEtiqIgvNC.setText("I.G.V :");
        LblEtiqIgvNC.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqIgvNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 160, -1));

        LblISCNC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblISCNC.setText("0");
        LblISCNC.setFont(new java.awt.Font("Verdana", 1, 12));
        LblISCNC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(LblISCNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 150, -1));

        LblEtiqISCNC.setText("I.S.C :");
        LblEtiqISCNC.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqISCNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 160, -1));

        LblIgvNC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblIgvNC.setText("0");
        LblIgvNC.setFont(new java.awt.Font("Verdana", 1, 12));
        LblIgvNC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(LblIgvNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 150, -1));

        LblEtiqSubtotalNetoSinIgvNC.setText("SUBT. NETO SIN IGV:");
        LblEtiqSubtotalNetoSinIgvNC.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqSubtotalNetoSinIgvNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 160, 20));

        LblSubTotalNetoSinIgvNC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubTotalNetoSinIgvNC.setText("0");
        LblSubTotalNetoSinIgvNC.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubTotalNetoSinIgvNC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(LblSubTotalNetoSinIgvNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 150, 20));

        LblEtiqTotalAcreditar.setForeground(new java.awt.Color(0, 0, 102));
        LblEtiqTotalAcreditar.setText("MONTO ACREDITAR:");
        LblEtiqTotalAcreditar.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqTotalAcreditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 160, -1));

        LblMontoTotalAcreditar.setForeground(new java.awt.Color(0, 51, 153));
        LblMontoTotalAcreditar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotalAcreditar.setText("0");
        LblMontoTotalAcreditar.setFont(new java.awt.Font("Verdana", 1, 12));
        LblMontoTotalAcreditar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(LblMontoTotalAcreditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 150, -1));

        LblEtiqPercepcion.setText("PERCEPCION :");
        LblEtiqPercepcion.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqPercepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 160, -1));

        LblMontoAcreditar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoAcreditar.setText("0");
        LblMontoAcreditar.setFont(new java.awt.Font("Verdana", 1, 12));
        LblMontoAcreditar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(LblMontoAcreditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 150, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );

        jPanel7.setBackground(java.awt.SystemColor.info);
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS GENERALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblEtiqNumNC.setText("N° Nota Credito :");
        LblEtiqNumNC.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel7.add(LblEtiqNumNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 21, 110, 20));

        TxtNumNC.setFont(new java.awt.Font("Verdana", 1, 11));
        TxtNumNC.setTamanio(15);
        TxtNumNC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNumNCKeyPressed(evt);
            }
        });
        jPanel7.add(TxtNumNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 20, 110, -1));

        label10.setText("Concepto :");
        label10.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel7.add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 21, 70, 20));

        CbxConcepto.setFont(new java.awt.Font("Verdana", 1, 11));
        CbxConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxConceptoActionPerformed(evt);
            }
        });
        jPanel7.add(CbxConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 210, -1));

        btnImportarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        btnImportarCompra.setText("Import. Comprobante");
        btnImportarCompra.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImportarCompra.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImportarCompra.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnImportarCompra.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImportarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarCompraActionPerformed(evt);
            }
        });
        jPanel7.add(btnImportarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 200, 24));

        btnBuscarNotaCredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        btnBuscarNotaCredito.setText("Buscar Nota Crédito");
        btnBuscarNotaCredito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscarNotaCredito.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarNotaCredito.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscarNotaCredito.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarNotaCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarNotaCreditoActionPerformed(evt);
            }
        });
        jPanel7.add(btnBuscarNotaCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(882, 20, 190, 24));

        DtchFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtchFechaPropertyChange(evt);
            }
        });
        jPanel7.add(DtchFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 90, 20));

        LblEtiqHoraRegistro.setText("Fecha :");
        LblEtiqHoraRegistro.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel7.add(LblEtiqHoraRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 50, 20));

        LblHoraSistema.setText("---");
        LblHoraSistema.setFont(new java.awt.Font("Verdana", 1, 12));

        LblEtiqHoraSistema.setText("Fecha:");

        jPanel8.setBackground(java.awt.SystemColor.info);
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)), "Observacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        TxtObservacion.setColumns(20);
        TxtObservacion.setRows(5);
        jScrollPane2.setViewportView(TxtObservacion);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblNotaCreditoDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblNotaCreditoDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Codigo", "Producto", "Stock", "Precio", "U. M. ", "Cantidad", "Importe Bruto.", "Tipo Desc.", "Descuento","Proporcion Dct.", "Importe Neto","Exonerado","Cant. Devuelta", "Precio Nuevo", "Monto Nuevo", "Sel."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Float.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Float.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false,false,false, false, false, false,true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {

                if(columnIndex==oCLNotaCreditoProveedor.colCantDevuelta||
                    columnIndex==oCLNotaCreditoProveedor.colImporNuevo||
                    columnIndex==oCLNotaCreditoProveedor.colPrecioNuevo){
                    Boolean issel=false;
                    if(this.getValueAt(rowIndex, oCLNotaCreditoProveedor.colCheck)!=null)
                    {
                        issel=Boolean.parseBoolean(this.getValueAt(rowIndex, oCLNotaCreditoProveedor.colCheck)+"");
                    }
                    if(columnIndex==oCLNotaCreditoProveedor.colCantDevuelta){

                        if(idConcepto==1){
                            issel=false;
                        }
                    }

                    return issel;
                }
                if(columnIndex==oCLNotaCreditoProveedor.colCheck){
                    if(idConcepto==1){
                        return false;
                    }else{
                        return true;
                    }
                }
                return canEdit [columnIndex];
            }
        });
        TblNotaCreditoDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblNotaCreditoDetalle.setColumnSelectionAllowed(true);
        TblNotaCreditoDetalle.setRowHeight(22);
        TblNotaCreditoDetalle.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblNotaCreditoDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblNotaCreditoDetalleMouseClicked(evt);
            }
        });
        TblNotaCreditoDetalle.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblNotaCreditoDetallePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(TblNotaCreditoDetalle);
        TblNotaCreditoDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TblNotaCreditoDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
        TblNotaCreditoDetalle.getColumnModel().getColumn(1).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(1).setPreferredWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(1).setMaxWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(3).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(3).setPreferredWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(3).setMaxWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(2).setPreferredWidth(320);
        TblNotaCreditoDetalle.getColumnModel().getColumn(4).setPreferredWidth(60);
        TblNotaCreditoDetalle.getColumnModel().getColumn(5).setPreferredWidth(85);
        TblNotaCreditoDetalle.getColumnModel().getColumn(6).setPreferredWidth(80);
        TblNotaCreditoDetalle.getColumnModel().getColumn(7).setPreferredWidth(95);
        TblNotaCreditoDetalle.getColumnModel().getColumn(8).setPreferredWidth(75);
        TblNotaCreditoDetalle.getColumnModel().getColumn(9).setPreferredWidth(75);
        TblNotaCreditoDetalle.getColumnModel().getColumn(10).setPreferredWidth(95);
        TblNotaCreditoDetalle.getColumnModel().getColumn(11).setPreferredWidth(90);
        TblNotaCreditoDetalle.getColumnModel().getColumn(12).setPreferredWidth(95);
        TblNotaCreditoDetalle.getColumnModel().getColumn(13).setPreferredWidth(95);
        TblNotaCreditoDetalle.getColumnModel().getColumn(14).setPreferredWidth(95);
        TblNotaCreditoDetalle.getColumnModel().getColumn(15).setPreferredWidth(95);
        TblNotaCreditoDetalle.getColumnModel().getColumn(16).setPreferredWidth(45);
        TblNotaCreditoDetalle.getColumnModel().getColumn(7).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(8).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(9).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(10).setMinWidth(0);

        label26.setText("DETALLE PRODUCTOS");
        label26.setFont(new java.awt.Font("Verdana", 1, 12));

        columnButton1.setTbl(TblNotaCreditoDetalle);

        BtnCalcularColumnas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/seleccionar.jpg"))); // NOI18N
        BtnCalcularColumnas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCalcularColumnasActionPerformed(evt);
            }
        });

        lblEtiqEstado.setText("Estado :");
        lblEtiqEstado.setFont(new java.awt.Font("Verdana", 1, 11));

        LblEstado.setText("EMITIDO");
        LblEstado.setFont(new java.awt.Font("Verdana", 1, 11));

        label28.setText("Nº Comprobrobante: ");
        label28.setFont(new java.awt.Font("Verdana", 1, 11));

        LblNumCompr.setText("----");
        LblNumCompr.setFont(new java.awt.Font("Verdana", 1, 11));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Registrado en N. C. ");

        jLabel6.setBackground(new java.awt.Color(198, 235, 210));
        jLabel6.setText("  ");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel6.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("  ");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel5.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Sin Registrar en N. C.");

        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(label26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(396, 396, 396)
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(BtnCalcularColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(366, 366, 366)
                                .addComponent(label28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(LblNumCompr, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(LblEtiqHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LblHoraSistema, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1073, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1073, Short.MAX_VALUE)
                            .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblNumCompr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEtiqHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label26, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BtnCalcularColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        columnButton1.getAccessibleContext().setAccessibleName("NO");
        BtnCalcularColumnas.getAccessibleContext().setAccessibleName("0");

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
private void habilitarControles(boolean bol)
    {
    TblNotaCreditoDetalle.clearSelection();
    TblNotaCreditoDetalle.setEnabled(bol);
    CbxConcepto.setEnabled(bol);
    BtnCalcularColumnas.setEnabled(bol);
    btnImportarCompra.setEnabled(bol);
    TxtNumNC.setEnabled(bol);
    TxtObservacion.setEnabled(bol);

}

private int ValidarRegistro(int fila)
    {
return 0;
}

private CEComprobanteCompraMatriz oCEComprobanteCompraMatrizAnulado= null;



    private void SetCEComprobanteCompraMatrizAnulado() {

         oCEComprobanteCompraMatrizAnulado= new CEComprobanteCompraMatriz();
         oCEComprobanteCompraMatrizAnulado.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
         oCEComprobanteCompraMatrizAnulado.setUltimoUsuario(FrmSistemaMenu.oCEUsuario.getUsuario());
         oCEComprobanteCompraMatrizAnulado.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
         oCEComprobanteCompraMatrizAnulado.setIdComprobanteCompra(oCEComprobanteCompraMatriz.getIdComprobanteCompra());
         oCEComprobanteCompraMatriz.setUltimaObservacion(TxtObservacion.getText());
    }


    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

      eventoGuardar();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void eventoGuardar()
    {
      if(btnGuardar.isEnabled()){
          int columnaValidada=ValidarRegistro(TblNotaCreditoDetalle.getRowCount());
          if(columnaValidada==0)
          {
              GuardarRegistro();

          }else
          {
              TblNotaCreditoDetalle.requestFocus();
              TblNotaCreditoDetalle.changeSelection(TblNotaCreditoDetalle.getRowCount()-1, columnaValidada, false, false);
          }
       }

    }
    private String VerificarStock()
    {
        String Mensaje="";
        int row=TblNotaCreditoDetalle.getRowCount();
        for (int i = 0; i < row; i++)
        {

            CEComprobanteCompraDetalle oCEComprobanteCompraDetalle= (CEComprobanteCompraDetalle)TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCodigo);
            float cantidad=VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCantDevuelta));

            String MensajeStock =CCSalidaProducto.veificarStockPorNC(oCEComprobanteCompraDetalle.getIdProducto(),
                                                                    1,cantidad,oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra());
            if(!MensajeStock.equals("")){
              oCEComprobanteCompraDetalle.setIdAlmacen(-1);
              Mensaje=Mensaje+oCEComprobanteCompraDetalle.getProducto()+" - "+oCEComprobanteCompraDetalle.getUnidadMedida()+"\n" + MensajeStock;

          }

        }


        return Mensaje;
    }

    private String VerificarUltimosCambiosDeMovimiento()
    {
        String Mensaje="";
        int row=TblNotaCreditoDetalle.getRowCount();
        for (int i = 0; i < row; i++)
        {

            CEComprobanteCompraDetalle oCEComprobanteCompraDetalle= (CEComprobanteCompraDetalle)TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCodigo);
            
            if(oCEComprobanteCompraDetalle.getIdNotaCreditoProvAux()!=0){
            boolean isEditable =CCSalidaProducto.VerificarUltimoMovimientoSalidaPorNC(oCEComprobanteCompraDetalle.getIdProducto(),
                                                                    1,oCEComprobanteCompraDetalle.getIdNotaCreditoProvAux());
                if(!isEditable){
                 //oCEComprobanteCompraDetalle.setIdNotaCreditoProvAux(0);
                    oCEComprobanteCompraDetalle.setIdAlmacen(-1);// artificio para no realizar ningun cambio
                //TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getCantidad(), i, oCLNotaCreditoProveedor.colCantidad);
                  Mensaje=Mensaje+oCEComprobanteCompraDetalle.getProducto()+" - "+oCEComprobanteCompraDetalle.getUnidadMedida()+"\n";
                 }
           }

        }


        return Mensaje;
    }
    private boolean  validar()
    {
        boolean isSelccionado=false;
        for( int i =0;i<TblNotaCreditoDetalle.getRowCount();i++)
            {
                if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCheck)!=null)
                {
                    if(!isSelccionado){
                    isSelccionado=Boolean.parseBoolean(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCheck)+"");
                    }
                }
                if(!verificarDatos(i))
                    {return false;}
            }
        

          if(!isSelccionado)
        {
            JOptionPane.showMessageDialog(null,"Seleccione al menos un registro de la tabla productos","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
           return false;
        }



        if(TxtNumNC.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Ingrese el Número de Nota de Crédito","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            TxtNumNC.requestFocus();
           return false;
        }

       
        if(((CEConcepto)CbxConcepto.getSelectedItem()).getIdConcepto()==0)
        {
            JOptionPane.showMessageDialog(this, "Ingrese un Concepto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            CbxConcepto.requestFocus();
            return false;
        }
        if (DtchFecha.getCalendar()==null) {
            JOptionPane.showMessageDialog(this, "Ingrese una Fecha", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            DtchFecha.requestFocus();
            return false;
        }
        int resul=JOptionPane.showConfirmDialog(this,"<html><h2> EL Monto  a  ACREDITAR es  : "+LblMontoTotalAcreditar.getText()+" S/."
                                                    + " <BR><BR>ESTÁ SEGURO DE GUARDAR LA NOTA DE CRÉDITO</h2></html>" , "",JOptionPane.YES_NO_OPTION);
        if (resul==JOptionPane.NO_OPTION) {
                   return false;
        }
     
        if(pAccionABM==2)
        {
            CEEstado oCEEstado=CCEstado.consultarUltimoestadoNCProv(oCENotaCreditoProveedor.getIdNotaCreditoProveedor());
            if(oCEEstado.getmIntIdEstado()!=1)
            {
             JOptionPane.showMessageDialog(this,"La Nota de Crédito ya fue "+oCEEstado.getmStrDescripcion()+", No se Puede realizar la Operación","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
            CEConcepto oCEConcepto =(CEConcepto)CbxConcepto.getSelectedItem();
            if(oCEConcepto.getIdConcepto()==2){
            String Mensaje=VerificarUltimosCambiosDeMovimiento();
            if(!Mensaje.equals(""))
            {
                resul=JOptionPane.showConfirmDialog(this,"Los Siguientes productos, no se pueden actualizar :\n"+Mensaje+",\n¿Desea Continuar de todos modos?" ,
                                                        "Mensaje",JOptionPane.YES_NO_OPTION);
                    if (resul==JOptionPane.NO_OPTION) {
                       return false;
                    }

            }
            String MensajeStock=VerificarStock();
            if(!MensajeStock.equals(""))
            {
                resul=JOptionPane.showConfirmDialog(this,"Los Siguientes productos, no se pueden actualizar,\n por que la cantidad de Salida es Mayor al Stock :\n"+MensajeStock+",\n¿Desea Continuar de todos modos?" ,
                                                        "Mensaje",JOptionPane.YES_NO_OPTION);
                    if (resul==JOptionPane.NO_OPTION) {
                       return false;
                    }

            }
         }

        }
        if(pAccionABM==1){

             CEEstado oCEEstadoContab=CCEstado.consultarUltimoestadoCompra_contable(oCEComprobanteCompraMatriz.getIdComprobanteCompra());

            if(oCEEstadoContab.getmIntIdEstado()!=12)
            {
             JOptionPane.showMessageDialog(this,"La Compra Esta "+oCEEstadoContab.getmStrDescripcion()+", No se Puede Guardar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }

            String NumNotaCredito=CCNotaCreditoProveedor.VerificarNotaCreditoPorCompra(oCEComprobanteCompraMatriz.getIdComprobanteCompra());
            if(NumNotaCredito!=null){
                    resul=JOptionPane.showConfirmDialog(this,"Se encontro que el Comprobante Nº "+oCEComprobanteCompraMatriz.getNumComprobante()+",\n"
                                                            + " se encuentra relacionado con la nota de Credito Nº"+NumNotaCredito+",\n¿Desea Continuar de todos modos?" ,
                                                        "Mensaje",JOptionPane.YES_NO_OPTION);
                    if (resul==JOptionPane.NO_OPTION) {
                       return false;
                    }
            }


            
        }
        return true;
    }

    private void GuardarRegistro()
    {
        oCLNotaCreditoProveedor.CalcularSubtotal();
        oCLNotaCreditoProveedor.calcularImportes();
        if(validar()){
                    if(pAccionABM==1){
                        oCENotaCreditoProveedor = new CENotaCreditoProveedor();                           
                        this.SetCEComprobanteCompraMatrizAnulado();                        
                        String Mensaje="";
                       
                        Mensaje=CCNotaCreditoProveedor.InsNotaCreditoProveedor(this.getNotaCreditoProveedorIns());
                       
                        if(Mensaje.equals("OK")){
                            
                        JOptionPane.showMessageDialog(this,"<html>Se guardo con éxito</html>");
                                if(pAccionDlg!=0)
                                {
                                    iscompAnulado=true;
                                    dispose();
                                }else{
                                limpiarFormulario();
                                LblFechaRegistro.setText("----");
                                oclBotonesABM.controlBoton(true, false, false, false, true, false);}

                     }
                        else{

                            JOptionPane.showMessageDialog(this,"No se puede realizar la operacion,\n La cantidad de Salida de los siguientes Productos es mayor al stock:\n"+Mensaje);
                            }
               }else{
                boolean iscorrecto=false;
                       iscorrecto =CCNotaCreditoProveedor.UPDNotaCreditoProveedor(this.getNotaCreditoProveedorIns());
                        
                if(iscorrecto)
                {

                        JOptionPane.showMessageDialog(null,"<html><CENTER>Operación exitosa </CENTER></html>");
                        limpiarFormulario();
                        pAccionABM=1;
                    }
                else{

                    JOptionPane.showMessageDialog(null,"Operación Fallida");
                    }

               }

        }

    }

    private CENotaCreditoProveedor getNotaCreditoProveedorIns(){
        
        oCENotaCreditoProveedor.setIdSucursal(FrmSistemaMenu.getIdSucursal());
        oCENotaCreditoProveedor.setUltimoIdEstado(1);
        oCENotaCreditoProveedor.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCENotaCreditoProveedor.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        CEConcepto oCEConcepto=(CEConcepto)CbxConcepto.getSelectedItem();
        oCENotaCreditoProveedor.setIdConcepto(oCEConcepto.getIdConcepto());
        oCENotaCreditoProveedor.setIdProveedor(oCEComprobanteCompraMatriz.getIdProveedor());
        oCENotaCreditoProveedor.setIdMoneda(oCEComprobanteCompraMatriz.getIdMoneda());
        oCENotaCreditoProveedor.setIdCondicion(oCEComprobanteCompraMatriz.getIdCondicion());
        oCENotaCreditoProveedor.setIdTipoComprobante(oCEComprobanteCompraMatriz.getIdTipoComprobanteCompra());
        oCENotaCreditoProveedor.setIdComprobanteCompraRef(oCEComprobanteCompraMatriz.getIdComprobanteCompra());
        oCENotaCreditoProveedor.setMontoAcreditar(Float.parseFloat(LblMontoAcreditar.getText()));
        oCENotaCreditoProveedor.setMontoPercepcion(Float.parseFloat(lblMontoPercepcion.getText()));
        oCENotaCreditoProveedor.setMontoTotalAcreditar(Float.parseFloat(LblMontoTotalAcreditar.getText()));
        oCENotaCreditoProveedor.setUltimaObservacion(TxtObservacion.getText());
        oCENotaCreditoProveedor.setSubtotalSinIGVNuevo(Float.parseFloat(LblSubTotalNetoSinIgvNC.getText()));
        oCENotaCreditoProveedor.setIGVNuevo(Float.parseFloat(LblIgvNC.getText()));
        oCENotaCreditoProveedor.setISCNuevo(Float.parseFloat(LblISCNC.getText()));
        oCENotaCreditoProveedor.setNumeroNotaCreditoProveedor(TxtNumNC.getText());
        oCENotaCreditoProveedor.setObservacion(TxtObservacion.getText());
        if (DtchFecha.getCalendar() != null)
        {
            ConvertidorFecha oConvertidorFecha = new ConvertidorFecha();
            oConvertidorFecha.setFecha(DtchFecha.getCalendar());
            oCENotaCreditoProveedor.setFecha(oConvertidorFecha.getFechaConvertida());
        }
        List<CENotaCreditoProveedorDetalle> lstCENotaCreditoProveedorDetalle = new ArrayList<CENotaCreditoProveedorDetalle>();
        CEComprobanteCompraDetalle oCEComprobanteCompraDetalle;
        CENotaCreditoProveedorDetalle oCENotaCreditoProveedorDetalle=new CENotaCreditoProveedorDetalle();
        for( int i =0;i<TblNotaCreditoDetalle.getRowCount();i++)
            {


                oCEComprobanteCompraDetalle= (CEComprobanteCompraDetalle)TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCodigo);
                if(oCEComprobanteCompraDetalle!=null){
                    if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCheck)!=null)
                    {
                        Boolean isSelccionado=Boolean.parseBoolean(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCheck)+"");
                        oCENotaCreditoProveedorDetalle=new CENotaCreditoProveedorDetalle();
                        oCENotaCreditoProveedorDetalle.setIdNotaCreditoProveedorDetalle(oCEComprobanteCompraDetalle.getIdNotaCreditoProvAux());
                        oCENotaCreditoProveedorDetalle.setIdComprobanteDetalleRef(oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle());
                        oCENotaCreditoProveedorDetalle.setSinoImpuesto(oCEComprobanteCompraDetalle.isSiNoImpuesto());
                        oCENotaCreditoProveedorDetalle.setIdProducto(oCEComprobanteCompraDetalle.getIdProducto());
                        oCENotaCreditoProveedorDetalle.setIdAlmacen(oCEComprobanteCompraDetalle.getIdAlmacen());
                        oCENotaCreditoProveedorDetalle.setIdUnidadMedidaVenta(oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra());
                        oCENotaCreditoProveedorDetalle.setImporteAnterior(VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colImporConDesc)));
                        oCENotaCreditoProveedorDetalle.setExonerado(VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colExonerado)));
                        oCENotaCreditoProveedorDetalle.setProporcionDescuentoTotal(0);
                        oCENotaCreditoProveedorDetalle.setProporcionDescuentoTotal(VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colProporcion)));
                        oCENotaCreditoProveedorDetalle.setImporteNuevo(VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colImporNuevo)));
                        oCENotaCreditoProveedorDetalle.setPrecioNuevo(VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colPrecioNuevo)));
                        oCENotaCreditoProveedorDetalle.setCantidadNueva(VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCantDevuelta)));

                        if(isSelccionado)
                        {
                            if(oCEComprobanteCompraDetalle.getIdAlmacen()!=-1){
                            lstCENotaCreditoProveedorDetalle.add(oCENotaCreditoProveedorDetalle);
                            }

                        }
                        else if(oCEComprobanteCompraDetalle.getIdNotaCreditoProvAux()!=0)
                        {
                            if(oCEComprobanteCompraDetalle.getIdAlmacen()!=-1){
                                oCENotaCreditoProveedorDetalle.setAccionAbm(3);
                                lstCENotaCreditoProveedorDetalle.add(oCENotaCreditoProveedorDetalle);
                            }
                        }


                    }
                }
            }

            oCENotaCreditoProveedor.setLstCENotaCreditoProveedorDetalle(lstCENotaCreditoProveedorDetalle);

            return oCENotaCreditoProveedor;
}
    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        if(pAccionDlg==0){
                if(pAccionABM==1||pAccionABM==2)
               {
                   habilitarControles(false);
                 oclBotonesABM.controlBoton(true, false, false, false, true, true);
                    
                 pAccionABM=0;
           
                 limpiarFormulario();
                 LblFechaRegistro.setText("----");
               }else{
                    iscerrando=true;
                    dispose();


                    }
        }else
        {
            iscerrando=true;
            dispose();
        }

    }//GEN-LAST:event_btnCancelarActionPerformed

    Boolean isCorrecto=false;     
    private CEComprobanteCompraMatriz oCEComprobanteCompraMatriz= null;
    private CENotaCreditoProveedor oCENotaCreditoProveedor;
    private void buscarComprobanteCompra()
    {
           oCLNotaCreditoProveedor.limpiarTabla();
           oCEComprobanteCompraMatriz= CCComprobanteCompra.ConsultarComprasPorId(pIdComprobante);

            if(oCEComprobanteCompraMatriz!=null)
            {
                CbxConcepto.setEnabled(true);
                CbxConcepto.setSelectedIndex(0);
                if(oCEComprobanteCompraMatriz.getUltimoIdEstado()!=4){
                oCENotaCreditoProveedor=new CENotaCreditoProveedor();
                LblEstado.setText(oCEComprobanteCompraMatriz.getUltimoEstado());
                LblNumCompr.setText(oCEComprobanteCompraMatriz.getNumComprobante());
                IdEstadoComprobante=oCEComprobanteCompraMatriz.getUltimoIdEstado();
                TxtCliente.setText(oCEComprobanteCompraMatriz.getProveedor());
                TxtTipoDescuento.setText(oCEComprobanteCompraMatriz.getTipoDescuento());
                TxtMoneda.setText(oCEComprobanteCompraMatriz.getMoneda());
                TxtTipoComprobante.setText(oCEComprobanteCompraMatriz.getTipoComprobante());
                TxtDescuento.setText(oCEComprobanteCompraMatriz.getDescuento()+"");
             //   LblFechaRegistro.setText(oCEComprobanteCompraMatriz.getFecha());
                LblSubtotalNeto.setText(oCEComprobanteCompraMatriz.getSubtotalNeto()+"");
                LblSubTotaNetolSinIgv.setText(oCEComprobanteCompraMatriz.getSubTotalNetoSinIGV()+"");
                LblIgv.setText(oCEComprobanteCompraMatriz.getIGV()+"");
                LblMontoTotalComp.setText(oCEComprobanteCompraMatriz.getMontoTotal()+"");
                LblMontoPercepcionComp.setText(""+oCEComprobanteCompraMatriz.getMontoPercepcion());
                LblMontoTotalCobrado.setText(oCEComprobanteCompraMatriz.getTotalPagar()+"");
                LblNumCompr.setText(oCEComprobanteCompraMatriz.getNumComprobante());

                if(oCEComprobanteCompraMatriz.getMontoPercepcion()==0)
                        {
                            oCLNotaCreditoProveedor.setConPercepcion(false);
                        }
                        else
                        {
                            oCLNotaCreditoProveedor.setConPercepcion(true);
                        }
                if(oCEComprobanteCompraMatriz.getoLstComprobanteDetalle()!=null)
                {
                    int Items=oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().size();
                    double Proporcion=CLRedondear.Redondear(oCEComprobanteCompraMatriz.getDescuento()/Items,2);
                   for(int i=0; i<oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().size();i++){
                      Vector oVector = new Vector();

                    ((DefaultTableModel)TblNotaCreditoDetalle.getModel()).addRow(oVector);

                    CEComprobanteCompraDetalle oCEComprobanteCompraDetalle=oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().get(i);

                    TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle,i,oCLNotaCreditoProveedor.colCodigo);
                    TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getProducto(),i,oCLNotaCreditoProveedor.colProducto);
                    TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getUnidadMedida(),i,oCLNotaCreditoProveedor.colUnidadMedida);
                    TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getPrecioConDesc(),i,oCLNotaCreditoProveedor.colPrecio);
                    TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getCantidad(),i,oCLNotaCreditoProveedor.colCantidad);
                    TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getTipoDescuento(),i,oCLNotaCreditoProveedor.colTipoDescuento);
                    TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getDescuento(),i,oCLNotaCreditoProveedor.colDescuento);
                    TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteSinDescuento(),i,oCLNotaCreditoProveedor.colImporSinDesc);
                    if(!oCEComprobanteCompraDetalle.isSiNoImpuesto())
                    {
                        TblNotaCreditoDetalle.setValueAt((oCEComprobanteCompraDetalle.getExonerado()-(float)Proporcion),i,oCLNotaCreditoProveedor.colExonerado);
                    }else {
                       TblNotaCreditoDetalle.setValueAt((oCEComprobanteCompraDetalle.getImporteConDescuento()-(float)Proporcion),i,oCLNotaCreditoProveedor.colImporConDesc);
                    }
                    TblNotaCreditoDetalle.setValueAt(Proporcion, i, oCLNotaCreditoProveedor.colProporcion);
                    TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getCantidad(), i, oCLNotaCreditoProveedor.colCantDevuelta);
                    }
                 }

                pAccionABM=1;
                TblNotaCreditoDetalle.requestFocus();
                TblNotaCreditoDetalle.changeSelection(0,oCLNotaCreditoProveedor.colCheck, false, false);
                oclBotonesABM.controlBoton(true, true, true, true, true, false);

                if(IdEstadoComprobante!=1)
                {
                    btnAnular.setEnabled(false);
                    btnEditar.setEnabled(false);
                }


               }
                else{
                JOptionPane.showMessageDialog(null,"El Comprobante ya está anulado,No se puede generar una nota de credito");
                TxtNumNC.setText("");
                }
           }
        else
            {
              JOptionPane.showMessageDialog(null,"El codigo no Existe");
              TxtNumNC.requestFocus();
            }

        
        
    }

    private void setNotaCreditoProveedorDetalle(int i,long idComprobanteDetRef,float Proporcion,int idNotaCreditoDet)
    {

        if(oCEComprobanteCompraMatriz.getoLstComprobanteDetalle()!=null)
            {

            for(int j=0; j<oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().size();j++){
                    CEComprobanteCompraDetalle oCEComprobanteCompraDetalle=oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().get(j);

                    if(oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle()==idComprobanteDetRef){

                        oCEComprobanteCompraDetalle.setIdNotaCreditoProvAux(idNotaCreditoDet);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle,i,oCLNotaCreditoProveedor.colCodigo);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getProducto(),i,oCLNotaCreditoProveedor.colProducto);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getUnidadMedida(),i,oCLNotaCreditoProveedor.colUnidadMedida);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getPrecio(),i,oCLNotaCreditoProveedor.colPrecio);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getCantidad(),i,oCLNotaCreditoProveedor.colCantidad);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getTipoDescuento(),i,oCLNotaCreditoProveedor.colTipoDescuento);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getDescuento(),i,oCLNotaCreditoProveedor.colDescuento);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteSinDescuento(),i,oCLNotaCreditoProveedor.colImporSinDesc);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteConDescuento()-Proporcion,i,oCLNotaCreditoProveedor.colImporConDesc);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getExonerado()-Proporcion,i,oCLNotaCreditoProveedor.colExonerado);
                        TblNotaCreditoDetalle.setValueAt(Proporcion, i, oCLNotaCreditoProveedor.colProporcion);
                       // TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getCantidad(), i, oCLNotaCreditoProveedor.colCantDevuelta);
                        break;
                    }
                }
        }
    }
    private void SetComprobanteDetalleFaltante()
    {
        if(oCEComprobanteCompraMatriz.getoLstComprobanteDetalle()!=null)
            {
                int Items=oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().size();
                float Proporcion=(float)CLRedondear.Redondear(oCEComprobanteCompraMatriz.getDescuento()/Items,2);
               for(int i=0; i<oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().size();i++){
                    CEComprobanteCompraDetalle oCEComprobanteCompraDetalle=oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().get(i);
                    boolean existeCompDet=this.getExisteIdNotaCreditoProveedorDetalle(oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle());
                    if(!existeCompDet)
                        {

                             Vector oVector = new Vector();
                            ((DefaultTableModel)TblNotaCreditoDetalle.getModel()).addRow(oVector);
                             int countfil=TblNotaCreditoDetalle.getRowCount()-1;
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle,countfil,oCLNotaCreditoProveedor.colCodigo);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getProducto(),countfil,oCLNotaCreditoProveedor.colProducto);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getUnidadMedida(),countfil,oCLNotaCreditoProveedor.colUnidadMedida);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getPrecio(),countfil,oCLNotaCreditoProveedor.colPrecio);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getCantidad(),countfil,oCLNotaCreditoProveedor.colCantidad);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getTipoDescuento(),countfil,oCLNotaCreditoProveedor.colTipoDescuento);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getDescuento(),countfil,oCLNotaCreditoProveedor.colDescuento);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteSinDescuento(),countfil,oCLNotaCreditoProveedor.colImporSinDesc);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteConDescuento()-Proporcion,countfil,oCLNotaCreditoProveedor.colImporConDesc);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getExonerado()-Proporcion,countfil,oCLNotaCreditoProveedor.colExonerado);
                            TblNotaCreditoDetalle.setValueAt(Proporcion, countfil, oCLNotaCreditoProveedor.colProporcion);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteCompraDetalle.getCantidad(), countfil, oCLNotaCreditoProveedor.colCantDevuelta);
                         }
                   
                }
        }

    }
    private boolean getExisteIdNotaCreditoProveedorDetalle(long pIdCompVentDealle)
    {
        for( int i =0;i<TblNotaCreditoDetalle.getRowCount();i++)
            {

             CEComprobanteCompraDetalle   oCEComprobanteCompraDetalle= (CEComprobanteCompraDetalle)TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCodigo);
                if(oCEComprobanteCompraDetalle!=null){
                    if(oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle()==pIdCompVentDealle)
                    {
                        return true;
                    }
                }
        }
        return false;
    }

    private void buscarNotaCreditoProveedor()
    {
        oCLNotaCreditoProveedor.limpiarTabla();      
        
        if(oCENotaCreditoProveedor!=null)
        {
            oCLNotaCreditoProveedor.setCalcular(false);
            oCEComprobanteCompraMatriz= CCComprobanteCompra.ConsultarComprasPorId(oCENotaCreditoProveedor.getIdComprobanteCompraRef());
            LblNumCompr.setText(oCEComprobanteCompraMatriz.getNumComprobante());
            LblEstado.setText(oCENotaCreditoProveedor.getUltimoEstado());
            IdEstadoComprobante=oCENotaCreditoProveedor.getUltimoIdEstado();
            TxtCliente.setText(oCENotaCreditoProveedor.getProveedor());
            TxtMoneda.setText(oCENotaCreditoProveedor.getMoneda());
            TxtTipoComprobante.setText(oCENotaCreditoProveedor.getTipoComprobante());
            LblSubTotalNetoSinIgvNC.setText(""+oCENotaCreditoProveedor.getSubtotalSinIGVNuevo());
            LblIgvNC.setText(""+oCENotaCreditoProveedor.getIGVNuevo());
            LblISCNC.setText(""+oCENotaCreditoProveedor.getISCNuevo());
            oCENotaCreditoProveedor.setMontoTotalAcreditarAnterior(oCENotaCreditoProveedor.getMontoTotalAcreditar());
            LblMontoAcreditar.setText(""+oCENotaCreditoProveedor.getMontoAcreditar());
            lblMontoPercepcion.setText(""+oCENotaCreditoProveedor.getMontoPercepcion());
            LblMontoTotalAcreditar.setText(""+oCENotaCreditoProveedor.getMontoTotalAcreditar());
            TxtNumNC.setText(oCENotaCreditoProveedor.getNumeroNotaCreditoProveedor());
            TxtObservacion.setText(oCENotaCreditoProveedor.getObservacion());
            LblFechaRegistro.setText(oCENotaCreditoProveedor.getFecha());
            DtchFecha.setDate(oCENotaCreditoProveedor.getDate());
            sino_accionHabilitarColumnas=false;
            CLCargarCombo.buscarIdConcepto(CbxConcepto, oCENotaCreditoProveedor.getIdConcepto());
            sino_accionHabilitarColumnas=true;
                if(oCENotaCreditoProveedor.getLstCENotaCreditoProveedorDetalle()!=null)
                {
                    for(int i=0; i<oCENotaCreditoProveedor.getLstCENotaCreditoProveedorDetalle().size();i++){
                    CENotaCreditoProveedorDetalle oCENotaCreditoProveedorDetalle=oCENotaCreditoProveedor.getLstCENotaCreditoProveedorDetalle().get(i);
                       Vector oVector = new Vector();

                    ((DefaultTableModel)TblNotaCreditoDetalle.getModel()).addRow(oVector);


                    TblNotaCreditoDetalle.setValueAt(oCENotaCreditoProveedorDetalle.getCantidadNueva(), i, oCLNotaCreditoProveedor.colCantDevuelta);
                    TblNotaCreditoDetalle.setValueAt(oCENotaCreditoProveedorDetalle.getPrecioNuevo(), i, oCLNotaCreditoProveedor.colPrecioNuevo);
                    TblNotaCreditoDetalle.setValueAt(oCENotaCreditoProveedorDetalle.getImporteNuevo(), i, oCLNotaCreditoProveedor.colImporNuevo);
                    TblNotaCreditoDetalle.setValueAt(true, i, oCLNotaCreditoProveedor.colCheck);
                    setNotaCreditoProveedorDetalle(i,oCENotaCreditoProveedorDetalle.getIdComprobanteDetalleRef(),
                                                   oCENotaCreditoProveedorDetalle.getProporcionDescuentoTotal(),
                                                   oCENotaCreditoProveedorDetalle.getIdNotaCreditoProveedorDetalle());
                }


              habilitarControles(false);
              btnEditar.requestFocus();

              oclBotonesABM.controlBoton(false, false, true, true, true, false);
              CEConcepto oCEConcepto=(CEConcepto)CbxConcepto.getSelectedItem();
              idConcepto=oCEConcepto.getIdConcepto();
            }
            oCLNotaCreditoProveedor.setCalcular(true);
       }
       else
        {
          JOptionPane.showMessageDialog(null,"El codigo no Existe");
          TxtNumNC.requestFocus();
        }
    }
    private void verDatosCompobante()
    {
            LblSubtotalBruto.setText(oCEComprobanteCompraMatriz.getSubtotalBruto()+"");
            LblDescuentoSubtotal.setText(oCEComprobanteCompraMatriz.getDescuentoEnSubtotal()+"");
            TxtDescuento.setText(oCEComprobanteCompraMatriz.getDescuento()+"");
            LblDescuentoTotal.setText(oCEComprobanteCompraMatriz.getDescuentoTotal()+"");
            LblSubtotalNeto.setText(oCEComprobanteCompraMatriz.getSubtotalNeto()+"");
            LblSubTotaNetolSinIgv.setText(oCEComprobanteCompraMatriz.getSubTotalNetoSinIGV()+"");
            LblIgv.setText(oCEComprobanteCompraMatriz.getIGV()+"");
            LblISC.setText(oCEComprobanteCompraMatriz.getISC()+"");
            LblMontoTotalComp.setText(oCEComprobanteCompraMatriz.getMontoTotal()+"");
            LblMontoTotalCobrado.setText(oCEComprobanteCompraMatriz.getTotalPagar()+"");
            LblMontoPercepcionComp.setText(""+oCEComprobanteCompraMatriz.getMontoPercepcion());
            TxtTipoDescuento.setText(oCEComprobanteCompraMatriz.getTipoDescuento());
        
    }

   

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
     pAccionABM=1;
        oclBotonesABM.controlBoton(false, true, false, false, true, false);
       habilitarControles(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private  boolean validarAnulacion()
    {


        CEEstado oCEEstado=CCEstado.consultarUltimoestadoNCProv(oCENotaCreditoProveedor.getIdNotaCreditoProveedor());
            if(oCEEstado.getmIntIdEstado()!=1)
            {
             JOptionPane.showMessageDialog(this,"La Nota de Crédito ya fue "+oCEEstado.getmStrDescripcion()+", No se Puede realizar la Operación","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
        
        if(!VerificarUltimosCambiosDeMovimientoParaAnulacion())
        {
            JOptionPane.showMessageDialog(null,"<html>Operación Fallida, solo se puede realizar esta operación,<br>"
                                                + "si todos los productos registrados son los ultimos movimientos en el almacen<html>");

            return false;
        }


        return true;

    }


private boolean VerificarUltimosCambiosDeMovimientoParaAnulacion()
    {
       CEConcepto oCEConcepto=(CEConcepto)CbxConcepto.getSelectedItem();
       if(oCEConcepto.getIdConcepto()==2){
            int row=TblNotaCreditoDetalle.getRowCount();
            for (int i = 0; i < row; i++)
            {

                CEComprobanteCompraDetalle oCEComprobanteCompraDetalle= (CEComprobanteCompraDetalle)TblNotaCreditoDetalle.getValueAt(i, oCLNotaCreditoProveedor.colCodigo);

                if(oCEComprobanteCompraDetalle.getIdNotaCreditoProvAux()!=0){
                boolean isEditable =CCSalidaProducto.VerificarUltimoMovimientoSalidaPorNC(oCEComprobanteCompraDetalle.getIdProducto(),
                                                                        1,oCEComprobanteCompraDetalle.getIdNotaCreditoProvAux());
                    if(!isEditable){
                     return false;
                     }
               }

            }
        }
        return true;

    }
   

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed

//        try {
//             if(oCENotaCreditoProveedor.getUltimoIdEstado()!=4){
//
//
//                    CENotaCreditoProveedor oCENotaCreditoProveedorAnular=getNotaCreditoProveedorIns();
//                    oCENotaCreditoProveedorAnular.setIdNotaCreditoProveedor(oCENotaCreditoProveedor.getIdNotaCreditoProveedor());
//                    oCENotaCreditoProveedorAnular.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
//                    oCENotaCreditoProveedorAnular.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
//
//
//                       DlgAnularNotaCreditoProveedor odlgAnularNotaCreditoProveedor=new DlgAnularNotaCreditoProveedor(null, true, oCENotaCreditoProveedor);
//                       odlgAnularNotaCreditoProveedor.setVisible(true);
//                       if(odlgAnularNotaCreditoProveedor.IsAnulado())
//                       {
//                           if(validarAnulacion()){
//                              CEConcepto oCEConcepto=(CEConcepto)CbxConcepto.getSelectedItem();
//                                if(oCEConcepto.getIdConcepto()==1){
//                                       if(CCNotaCreditoProveedor.AnularNotaCreditoProveedor(oCENotaCreditoProveedor)==1)
//                                       {
//                                       JOptionPane.showMessageDialog(this,"Se anulo con exito");
//                                       oclBotonesABM.controlBoton(true, false, false, false, true, false);
//                                       limpiarFormulario();
//                                       }else
//                                       {
//                                           JOptionPane.showMessageDialog(this,"Operación Fallida");
//                                       }
//                                }else
//                                {
//                                  if(CCNotaCreditoProveedor.AnularNotaCreditoProveedor(oCENotaCreditoProveedor)==1)
//                                       {
//                                       JOptionPane.showMessageDialog(this,"Se anulo con exito");
//                                       oclBotonesABM.controlBoton(true, false, false, false, true, false);
//                                       limpiarFormulario();
//                                       }else
//                                       {
//                                           JOptionPane.showMessageDialog(this,"Operación Fallida");
//                                       }
//                                }
//
//                           }
//                       }
//                }
//                else
//                {
//                    JOptionPane.showMessageDialog(this,"No se puede Realizar cambios,El documento ya está anulado");
//                }
//            }catch(Exception e)
//            {
//                JOptionPane.showMessageDialog(this,"Ne se puede Anular");
//            }
//
//
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

       
//                if(oCENotaCreditoProveedor.getUltimoIdEstado()!=4){
//                    oclBotonesABM.controlBoton(false, true, false, false, true, false);
//                     habilitarControles(true);
//                     CbxConcepto.setEnabled(false);
//                     pAccionABM=2;
//                     SetComprobanteDetalleFaltante();
//                     verDatosCompobante();
//                     if(TblNotaCreditoDetalle.getRowCount()>0)
//                     {
//                         TblNotaCreditoDetalle.requestFocus();
//                         TblNotaCreditoDetalle.changeSelection(0,oCLNotaCreditoProveedor.colImporNuevo, false, false);
//                     }
//                }else
//                {
//                    JOptionPane.showMessageDialog(null,"No se puede Realizar cambios, el documento ya está anulado");
//                }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
      CEConcepto oCEConcepto=(CEConcepto)CbxConcepto.getSelectedItem();
        if(oCEConcepto!=null)
        {
             if(oCEConcepto.getIdConcepto()==2)
             {
                int size =TblNotaCreditoDetalle.getRowCount();
                for(int i=0;i<size;i++) {
                    boolean state= evt.getStateChange()==evt.SELECTED?true:false;
                    TblNotaCreditoDetalle.setValueAt(state,i,oCLNotaCreditoProveedor.colCheck);
                }
            }
        }
}//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void CbxConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxConceptoActionPerformed
      HabilitarcolumnasporConcepto();
    }//GEN-LAST:event_CbxConceptoActionPerformed
 private int cont=0;// contador utilizado como artificio
    private void TblNotaCreditoDetallePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblNotaCreditoDetallePropertyChange
if(!iscerrando){
int fila=TblNotaCreditoDetalle.getSelectedRow();
int col=TblNotaCreditoDetalle.getSelectedColumn();
if(TblNotaCreditoDetalle.getSelectedRow()!=-1){
        if(TblNotaCreditoDetalle.getSelectedColumn()==oCLNotaCreditoProveedor.colCheck)
        {
            repaint();

            boolean chek=VerificadorDeTxt.convertBoolean(TblNotaCreditoDetalle.getValueAt(fila,oCLNotaCreditoProveedor.colCheck));

            
            if(chek){
                    float cantidadNueva=0,importNuevo=0,PrecioNuevo=0;
                    cantidadNueva=VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colCantidad));
                    importNuevo=VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colImporConDesc));
                    importNuevo=importNuevo+VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colExonerado));
                    PrecioNuevo=VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colPrecio));
                    TblNotaCreditoDetalle.setValueAt(""+PrecioNuevo,fila, oCLNotaCreditoProveedor.colPrecioNuevo);
                    TblNotaCreditoDetalle.setValueAt(""+cantidadNueva,fila, oCLNotaCreditoProveedor.colCantDevuelta);
                    TblNotaCreditoDetalle.setValueAt(""+importNuevo,fila, oCLNotaCreditoProveedor.colImporNuevo);
                    
                }else
                {
                    TblNotaCreditoDetalle.setValueAt(""+null,fila, oCLNotaCreditoProveedor.colImporNuevo);
                    TblNotaCreditoDetalle.setValueAt(""+null,fila, oCLNotaCreditoProveedor.colPrecioNuevo);
                }
            

        }
        
        }
        if(cont==1){
           
           if(fila!=-1){
               if(col==oCLNotaCreditoProveedor.colImporNuevo||col==oCLNotaCreditoProveedor.colCantDevuelta||col==oCLNotaCreditoProveedor.colPrecioNuevo){
                verificarDatos(fila);}
            }
        }else{
            cont=0;
        }
        cont++;
 }
    }//GEN-LAST:event_TblNotaCreditoDetallePropertyChange


    private boolean verificarDatos(int fila)
    {
          if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colCheck)!=null)
           {
            Boolean isSelccionado=Boolean.parseBoolean(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colCheck)+"");
            if(isSelccionado)
            {
            float cantidadNueva=0; float precioNuevo=0; float ImporteNuevo=0; float ImporteConDes=0; float Cantidad=0;
            if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colCantDevuelta)!=null){
            cantidadNueva=Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colCantDevuelta).toString());}
            if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colPrecioNuevo)!=null){
            precioNuevo=Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colPrecioNuevo).toString());}

             if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colImporNuevo)!=null){
             ImporteNuevo=Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colImporNuevo).toString());}

       //    CEComprobanteCompraDetalle oCEComprobanteCompraDetalle= (CEComprobanteCompraDetalle)TblPedidoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colCodigo);
         //  if(oCEComprobanteCompraDetalle!=null){
          //  if(oCEComprobanteCompraDetalle.isSiNoImpuesto())
            //    {
                if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colImporConDesc)!=null){
                   ImporteConDes=Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colImporConDesc).toString());}
             //   }
              //  else{
                     if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colExonerado)!=null){
                     ImporteConDes=ImporteConDes+Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colExonerado).toString());}
                //     }
           // }
            if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colCantidad)!=null){
             Cantidad=Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCreditoProveedor.colCantidad).toString());}
            
                if(cantidadNueva>Cantidad)
                {
                    JOptionPane.showMessageDialog(null,"La cantidad Nueva no puede ser mayor a la cantidad registrada","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    TblNotaCreditoDetalle.setValueAt(Cantidad,fila, oCLNotaCreditoProveedor.colCantDevuelta);
                    TblNotaCreditoDetalle.requestFocus();
                    TblNotaCreditoDetalle.changeSelection(fila,oCLNotaCreditoProveedor.colCantDevuelta, false, false);
                    return false;
                }
                if(cantidadNueva<=0)
                {
                    JOptionPane.showMessageDialog(null,"La cantidad Nueva no puede ser menor a cero","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    TblNotaCreditoDetalle.setValueAt(Cantidad,fila, oCLNotaCreditoProveedor.colCantDevuelta);
                    TblNotaCreditoDetalle.requestFocus();
                    TblNotaCreditoDetalle.changeSelection(fila,oCLNotaCreditoProveedor.colCantDevuelta, false, false);
                    return false;
                }
                    if(ImporteNuevo>ImporteConDes)
                    {
                    JOptionPane.showMessageDialog(null,"El importe nuevo no puede ser mayor al Importe con descuento o Exonerado","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCreditoProveedor.colPrecioNuevo);
                    TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCreditoProveedor.colImporNuevo);
                    TblNotaCreditoDetalle.requestFocus();
                    TblNotaCreditoDetalle.changeSelection(fila,oCLNotaCreditoProveedor.colPrecioNuevo, false, false);
                    return false;
                    }
                    if(ImporteNuevo<=0)
                    {
                        JOptionPane.showMessageDialog(null,"El importe nuevo no puede ser menor a cero","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                        TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCreditoProveedor.colPrecioNuevo);
                        TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCreditoProveedor.colImporNuevo);
                        TblNotaCreditoDetalle.requestFocus();
                        TblNotaCreditoDetalle.changeSelection(fila,oCLNotaCreditoProveedor.colImporNuevo, false, false);
                        return false;
                    }
                    if(precioNuevo<=0)
                    {
                        JOptionPane.showMessageDialog(null,"El precio nuevo no puede ser menor a cero","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                        TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCreditoProveedor.colPrecioNuevo);
                        TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCreditoProveedor.colImporNuevo);
                        TblNotaCreditoDetalle.requestFocus();
                        TblNotaCreditoDetalle.changeSelection(fila,oCLNotaCreditoProveedor.colPrecioNuevo, false, false);
                        return false;
                    }


                
           }
        

      }
        return true;
    }
    private void TblNotaCreditoDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblNotaCreditoDetalleMouseClicked

    }//GEN-LAST:event_TblNotaCreditoDetalleMouseClicked

    boolean iscerrando=false;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        iscerrando=true;
    }//GEN-LAST:event_formWindowClosing

    private void BtnCalcularColumnasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCalcularColumnasActionPerformed

    if(TblNotaCreditoDetalle.getRowCount()>0){
        for(int i=0;i<TblNotaCreditoDetalle.getRowCount();i++){
            if(TblNotaCreditoDetalle.getValueAt(i,oCLNotaCreditoProveedor.colCodigo)!=null){
              CEComprobanteCompraDetalle oCEComprobanteCompraDetalle=(CEComprobanteCompraDetalle)TblNotaCreditoDetalle.getValueAt(i,oCLNotaCreditoProveedor.colCodigo);

              boolean check=false;
                    if(TblNotaCreditoDetalle.getValueAt(i,oCLNotaCreditoProveedor.colCheck)!=null){
                      check=Boolean.parseBoolean(TblNotaCreditoDetalle.getValueAt(i,oCLNotaCreditoProveedor.colCheck).toString());}
                    if(check){
                            
                        TblNotaCreditoDetalle.setValueAt(TblNotaCreditoDetalle.getValueAt(i,oCLNotaCreditoProveedor.colPrecio),i,oCLNotaCreditoProveedor.colPrecioNuevo);
                        oCLNotaCreditoProveedor.CalcularCeldaImporteNuevo(i, oCLNotaCreditoProveedor.colPrecioNuevo);
                    }
            }
         }
     }

    }//GEN-LAST:event_BtnCalcularColumnasActionPerformed

    private void btnImportarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarCompraActionPerformed

        DlgBusquedaComprobanteCompra odialogo=new DlgBusquedaComprobanteCompra(oparent, true);
       // odialogo.habilitarImportDesdeNotaCredito();
        odialogo.setVisible(true);
        CEComprobanteCompraMatriz vCEComprobanteCompraMatriz= odialogo.getCEComprobanteCompraMatriz();

        if(vCEComprobanteCompraMatriz!=null) {
            
            pIdComprobante=vCEComprobanteCompraMatriz.getIdComprobanteCompra();
            buscarComprobanteCompra();
            
        }
}//GEN-LAST:event_btnImportarCompraActionPerformed

    private void btnBuscarNotaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNotaCreditoActionPerformed

         DlgBusquedaNotaCreditoProv odialogo=new DlgBusquedaNotaCreditoProv(oparent, true);
         odialogo.setVisible(true);
         CENotaCreditoProveedor oCENotaCreditoProveedorAux= odialogo.getCENotaCreditoProveedor();

        if(oCENotaCreditoProveedorAux!=null)
        {
           
           oCENotaCreditoProveedor=CCNotaCreditoProveedor.ListarNotaCreditoProveedor(oCENotaCreditoProveedorAux.getIdNotaCreditoProveedor());
           buscarNotaCreditoProveedor();
        }
       
    }//GEN-LAST:event_btnBuscarNotaCreditoActionPerformed

    private void TxtNumNCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNumNCKeyPressed

}//GEN-LAST:event_TxtNumNCKeyPressed

    private void DtchFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtchFechaPropertyChange

}//GEN-LAST:event_DtchFechaPropertyChange

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed

        CLExportarExcel oExportar=new CLExportarExcel(TblNotaCreditoDetalle,
                new String[]{lblEtiqTipoComp.getText(),TxtTipoComprobante.getText(),
                LblEtiqNumNC.getText(),TxtNumNC.getText(),lblEtiqEstado.getText(),LblEstado.getText(),
                LblEtiqCliente.getText(),TxtCliente.getText(),
                LblEtiqMoneda.getText(),TxtMoneda.getText(),
                LblEtiqTipoCambio.getText(),TxtTipoCambio.getText(),LblEtiqSucursal.getText(),TxtSucursal.getText(),
                lblEtiqUsuario.getText(),TxtVendedor.getText()},null
                ,new String[]{LblEtiqSubtotalNetoSinIgvNC.getText(),LblSubTotalNetoSinIgvNC.getText(),LblEtiqIgvNC.getText(),LblIgvNC.getText(),
                LblEtiqISCNC.getText(),LblISCNC.getText(),
                LblEtiqMontoTotal.getText(),LblMontoTotalAcreditar.getText(),LblEtiqPercepcion.getText(),lblMontoPercepcion.getText(),
                LblEtiqTotalAcreditar.getText(),LblMontoTotalAcreditar.getText()
                }
        ,"Nota de Credito");
        oExportar.GuardarArchivoExcel(this);
}//GEN-LAST:event_btnExportar1ActionPerformed


    private void limpiarFormulario()
    {

        TxtNumNC.setText("");
        DtchFecha.setDate(null);
        LblNumCompr.setText("");
        TxtTipoComprobante.setText("");
        TxtTipoDescuento.setText("");
        TxtMoneda.setText("");
        CbxConcepto.setSelectedIndex(0);
//      TxtTipoCambio.setText("");
        TxtCliente.setText("");
        TxtDescuento.setText("");
        oCLNotaCreditoProveedor.limpiarTabla();
        TxtDescuento.setText("0.00");
        LblSubtotalNeto.setText("0.00");
        LblSubTotaNetolSinIgv.setText("0.00");
        LblIgv.setText("0.00");
        LblISC.setText("0.00");
        LblMontoTotalComp.setText("0.00");
        LblMontoTotalCobrado.setText("0.00");
        LblMontoPercepcionComp.setText("0.00");
        TxtDescuento.setText("0");
        TxtObservacion.setText("");
        LblMontoAcreditar.setText("0.00");
        LblISCNC.setText("0.00");
        LblIgvNC.setText("0.00");
        LblMontoAcreditar.setText("0.00");
        lblMontoPercepcion.setText("0.00");
        LblMontoTotalAcreditar.setText("0.00");
        LblSubTotalNetoSinIgvNC.setText("0.00");

    }
    /**
    * @param args the command line arguments
    */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCalcularColumnas;
    private ComboBox.ComboBox CbxConcepto;
    private ComboBox.ComboBox CbxTipoDescuento;
    private javax.swing.JComboBox CbxUnidadMedida;
    private com.toedter.calendar.JDateChooser DtchFecha;
    private Label.Label LblDescuentoSubtotal;
    private Label.Label LblDescuentoTotal;
    private Label.Label LblEstado;
    private Label.Label LblEtiqCliente;
    private Label.Label LblEtiqFecha;
    private Label.Label LblEtiqHoraRegistro;
    private Label.Label LblEtiqHoraSistema;
    private Label.Label LblEtiqISCNC;
    private Label.Label LblEtiqIgvNC;
    private Label.Label LblEtiqMoneda;
    private Label.Label LblEtiqMontoTotal;
    private Label.Label LblEtiqNumNC;
    private Label.Label LblEtiqPercepcion;
    private Label.Label LblEtiqSubtotalNetoSinIgvNC;
    private Label.Label LblEtiqSucursal;
    private Label.Label LblEtiqTipoCambio;
    private Label.Label LblEtiqTotalAcreditar;
    private Label.Label LblFechaRegistro;
    private Label.Label LblHoraSistema;
    private Label.Label LblISC;
    private Label.Label LblISCNC;
    private Label.Label LblIgv;
    private Label.Label LblIgvNC;
    private Label.Label LblMontoAcreditar;
    private Label.Label LblMontoPercepcionComp;
    private Label.Label LblMontoTotalAcreditar;
    private Label.Label LblMontoTotalCobrado;
    private Label.Label LblMontoTotalComp;
    private Label.Label LblNumCompr;
    private Label.Label LblSubTotaNetolSinIgv;
    private Label.Label LblSubTotalNetoSinIgvNC;
    private Label.Label LblSubtotalBruto;
    private Label.Label LblSubtotalNeto;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JTable TblNotaCreditoDetalle;
    private TextField.JTxtLetra TxtCliente;
    private Label.Label TxtDescuento;
    private TextField.JTxtLetra TxtMoneda;
    private TextField.JTxtNinguno TxtNumNC;
    private javax.swing.JTextArea TxtObservacion;
    private TextField.JTxtLetra TxtSucursal;
    private TextField.JTxtNinguno TxtTipoCambio;
    private TextField.JTxtLetra TxtTipoComprobante;
    private Label.Label TxtTipoDescuento;
    private TextField.JTxtLetra TxtVendedor;
    private BotonesABM.BtnEliminar btnAnular;
    private BotonesABM.BtnBuscar btnBuscarNotaCredito;
    private BotonesABM.BtnCancelar btnCancelar;
    private BotonesABM.BtnEditar btnEditar;
    private BotonesABM.BtnExportar btnExportar1;
    private BotonesABM.BtnGuardar btnGuardar;
    private BotonesABM.BtnBuscar btnImportarCompra;
    private BotonesABM.BtnNuevo btnNuevo;
    private util.clases.vtaVenta.ColumnButton columnButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldNumber;
    private Label.Label label10;
    private Label.Label label11;
    private Label.Label label16;
    private Label.Label label21;
    private Label.Label label22;
    private Label.Label label23;
    private Label.Label label24;
    private Label.Label label25;
    private Label.Label label26;
    private Label.Label label28;
    private Label.Label label29;
    private Label.Label label30;
    private Label.Label label6;
    private Label.Label label7;
    private Label.Label label9;
    private Label.Label lblEtiqEstado;
    private Label.Label lblEtiqTipoComp;
    private Label.Label lblEtiqUsuario;
    private Label.Label lblMontoPercepcion;
    // End of variables declaration//GEN-END:variables

        private static final String ACTION_CLOSE = "ACTION_CLOSE";
    private static final String ACTION_GUARDAR = "ACTION_NUEVO";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke nuevo = KeyStroke.getKeyStroke(KeyEvent.VK_G,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_GUARDAR, nuevo, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            iscerrando=true;
            dispose();
        }
    else if(e.getActionCommand().equals(ACTION_GUARDAR))
        {
           eventoGuardar();
        }

  }

}
