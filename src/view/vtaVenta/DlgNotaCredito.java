package view.vtaVenta;

import controller.grlGeneral.CCValores;
import controller.vtaVenta.CCComprobanteDevolucion;
import controller.vtaVenta.CCComprobanteVenta;
import controller.vtaVenta.CCConcepto;
import controller.vtaVenta.CCNotaCredito;
import controller.vtaVenta.CCSerie;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
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
import modelo.vtaVenta.entidad.CEComprobanteVentaDetalle;
import modelo.vtaVenta.entidad.CEComprobanteVentaMatriz;
import modelo.vtaVenta.entidad.CEConcepto;
import modelo.vtaVenta.entidad.CENotaCredito;
import modelo.vtaVenta.entidad.CENotaCreditoDetalle;
import modelo.vtaVenta.entidad.CESerie;
import util.clases.almAlmacen.JTableX;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLCheckBoxHeader;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.MiReloj;
import util.clases.grlGeneral.VerificadorDeTxt;
import util.clases.vtaVenta.CLCargarCombo;
import util.clases.vtaVenta.CLNotaCredito;
import view.FrmSistemaMenu;

/**
 *
 * @author Luiggi
 */
public class DlgNotaCredito extends DialogoPadre implements ActionListener{

    private  int IdEstadoComprobante=0;
    private MiReloj hilo;
    private CLNotaCredito oCLNotaCredito=new CLNotaCredito();
    private  int pAccionABM=0;
    private  long pIdComprobante=0;
    private int pIdConcepto=0;//0:Nota credito 1: anulacion 2 : devolucion
    CLBotonesABM oclBotonesABM= new CLBotonesABM();
    private boolean  iscompAnulado= false;
    private boolean sino_devolucion_dinero=false;
    private Frame oparent;

    /** Creates new form DlgGestionPedido */
    public DlgNotaCredito(java.awt.Frame parent, boolean modal,int pIdConcepto,long idComprobante,boolean sino_devolucion) {
        super(parent, modal); // accionDlg 1 : nuevo
        initComponents();
        this.setLocationRelativeTo(null);
        this.sino_devolucion_dinero=sino_devolucion;
        generarSerie();
        oparent=parent;
        TxtTipoCambio.setText("1");        
        this.pIdConcepto=pIdConcepto;
        if(!sino_devolucion){
            btnBuscarNotaCredito.setText("Buscar nota crédito");
            LblTituloComprobante.setText(("      Nota de Crédito    ").toUpperCase());
        }else{
            LblTituloComprobante.setText(("Comprobante Devolución de Dinero").toUpperCase());
            btnBuscarNotaCredito.setText("Buscar Dev de dinero");
            this.setTitle("DEVOLUCIONES DE DINERO");

        }
        cargarUtilidades();
        oclBotonesABM.controlBoton(false,true, false, false, true, false);
        cnsNumDoc=2;                        
        if(pIdConcepto>0)
        {
            CbxConcepto.setEnabled(false);
            this.pIdComprobante=idComprobante;
            cnsNumDoc=1;            
           oCEComprobanteVentaMatriz=CCComprobanteVenta.ListarComprobanteVentaPorId(pIdComprobante);
           buscarComprobanteVenta();
           calcularMontos();
           oclBotonesABM.controlBoton(false,true, false, false, true, false);
        }
        pAccionABM=1;
        CLCargarCombo.buscarIdConcepto(CbxConcepto, pIdConcepto);
        HabilitarcolumnasporConcepto();
        pintarTabla();
        btnEditar.setVisible(false);
        btnAnular.setVisible(false);
    }
private void generarSerie()
    {
    CESerie oCESerie=null;
    if(!sino_devolucion_dinero){
     oCESerie=CCSerie.consultarSeriePorIdPuntoVenta(1, 5);
        }else
        {
        oCESerie=CCSerie.consultarSeriePorIdPuntoVenta(1, 6);
        }
        //LblSerie.setText(oCESerie.getNumeroGeneradoString());
        LblNumNotaCred.setText("Nº "+oCESerie.getUltimoNumeroGeneradoString());
}
    private void cargarUtilidades()
    {
         jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero",0,jTextFieldNumber));
         TblNotaCreditoDetalle.setSurrendersFocusOnKeystroke(true);
        ((JTableX)TblNotaCreditoDetalle).setSelectAllForEdit(true);
        TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
        TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
        oCLNotaCredito = new CLNotaCredito(TblNotaCreditoDetalle,LblSubTotalNetoSinIgvNC, LblMontoAcreditar,LblIgvNC,LblISCNC,LblMontoTotalAcreditar,lblMontoPercepcion);
        jCheckBox1.setSelected(true);
        CbxConcepto.setModel(CLComboBox.cargarCombo(CCConcepto.getAll()));
        CLCargarCombo.buscarIdConcepto(CbxConcepto, pIdConcepto);
        TableColumn aColumn = (TableColumn)TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colCheck);
        aColumn.setHeaderRenderer(new CLCheckBoxHeader(jCheckBox1.getItemListeners()[0]));
        TblNotaCreditoDetalle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colDescuento).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colImporSinDesc).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colTipoDescuento).setMinWidth(0);
        hilo= new MiReloj(LblHoraSistema);
        hilo.start();
        TableColumn columnaCantidadNueva = TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colCantDevuelta);
        columnaCantidadNueva.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaPrecioNuevo = TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colPrecioNuevo);
        columnaPrecioNuevo.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaMontoNuevo = TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colImporNuevo);
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

             if(oCEConcepto.getIdConcepto()==1)// concepto devolucion
             {
                 
                 for( int i =0;i<TblNotaCreditoDetalle.getRowCount();i++)
                {

                        if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colCheck)!=null)
                        {
                           float cantidadNueva=0,PrecioNuevo=0;
                            if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colCantidad)!=null){
                            cantidadNueva=VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colCantidad));
                            PrecioNuevo=VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colPrecio));

                            TblNotaCreditoDetalle.setValueAt(""+cantidadNueva,i, oCLNotaCredito.colCantDevuelta);
                            TblNotaCreditoDetalle.setValueAt(""+PrecioNuevo,i, oCLNotaCredito.colPrecioNuevo);
                            oCLNotaCredito.CalcularCeldaImporteNuevo(i, oCLNotaCredito.colPrecioNuevo);
                            }
                           

                        }
                        TblNotaCreditoDetalle.setValueAt(true,i, oCLNotaCredito.colCheck);

                }

             } else // concepto devolucion
                 {

                     if(TblNotaCreditoDetalle.getRowCount()>0){
                        for( int i =0;i<TblNotaCreditoDetalle.getRowCount();i++)
                        {

                               TblNotaCreditoDetalle.setValueAt(false,i, oCLNotaCredito.colCheck);

                        }
                     }
                 }
                repaint();
            }
        }
    }

private void pintarTabla(){
    for (int i =0; i<TblNotaCreditoDetalle.getColumnCount();i++) {
        if(i!=oCLNotaCredito.colCheck){

    TblNotaCreditoDetalle.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if(table.getValueAt(row,oCLNotaCredito.colCheck)!=null)
                {
                    boolean isSelccionado=Boolean.parseBoolean(table.getValueAt(row,oCLNotaCredito.colCheck)+"");
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
        LblHoraRegistro2 = new Label.Label();
        LblHoraRegistro1 = new Label.Label();
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
        label13 = new Label.Label();
        LblMontoTotalComp = new Label.Label();
        label29 = new Label.Label();
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
        lblEtiqEstado = new Label.Label();
        LblEstado = new Label.Label();
        label10 = new Label.Label();
        CbxConcepto = new ComboBox.ComboBox();
        label8 = new Label.Label();
        LblNumCompr = new Label.Label();
        btnImportarCompra = new BotonesABM.BtnBuscar();
        btnBuscarNotaCredito = new BotonesABM.BtnBuscar();
        LblHoraSistema = new Label.Label();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtObservacion = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblNotaCreditoDetalle = new util.clases.almAlmacen.JTableX(){
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{
                    if(column==oCLNotaCredito.colCantDevuelta||
                        column==oCLNotaCredito.colImporNuevo)
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
                    else if(column==oCLNotaCredito.colPrecioNuevo)
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
        LblTituloComprobante = new javax.swing.JLabel();
        LblNumNotaCred = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnExportar1 = new BotonesABM.BtnExportar();
        jCheckBox2 = new javax.swing.JCheckBox();

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
        setTitle("Nota de Crédito");
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

        TxtCliente.setBackground(java.awt.SystemColor.controlHighlight);
        TxtCliente.setBorder(null);
        TxtCliente.setEditable(false);
        TxtCliente.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtCliente.setTamanio(200);

        LblHoraRegistro2.setText("Fecha ");
        LblHoraRegistro2.setFont(new java.awt.Font("Verdana", 0, 11));
        LblHoraRegistro2.setVisible(false);

        LblHoraRegistro1.setVisible(false);
        LblHoraRegistro1.setText("Hora Registro");
        LblHoraRegistro1.setFont(new java.awt.Font("Verdana", 0, 11));

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
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(TxtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)))
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
                        .addComponent(TxtTipoCambio, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblHoraRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(LblHoraRegistro1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))
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
                                .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LblEtiqMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEtiqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEtiqTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblHoraRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblEtiqTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblHoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        btnEditar.setVisible(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnAnular.setVisible(false);
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

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        label13.setText("TOTAL COBRADO :");
        label13.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(label13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 130, 20));

        LblMontoTotalComp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotalComp.setText("0");
        LblMontoTotalComp.setFont(new java.awt.Font("Verdana", 1, 11));
        LblMontoTotalComp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblMontoTotalComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 100, 20));

        label29.setText("PERCEPCION :");
        label29.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(label29, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 130, 20));

        LblMontoPercepcionComp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoPercepcionComp.setText("0");
        LblMontoPercepcionComp.setFont(new java.awt.Font("Verdana", 1, 11));
        LblMontoPercepcionComp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblMontoPercepcionComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 100, 20));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 150));

        jPanel2.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Montos de Nota de Credito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblEtiqMontoTotal.setText("MONTO TOTAL :");
        LblEtiqMontoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 160, -1));

        lblMontoPercepcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMontoPercepcion.setText("0");
        lblMontoPercepcion.setFont(new java.awt.Font("Verdana", 1, 12));
        lblMontoPercepcion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblMontoPercepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 150, -1));

        LblEtiqIgvNC.setText("I.G.V :");
        LblEtiqIgvNC.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqIgvNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 160, -1));

        LblISCNC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblISCNC.setText("0");
        LblISCNC.setFont(new java.awt.Font("Verdana", 1, 12));
        LblISCNC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(LblISCNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 150, -1));

        LblEtiqISCNC.setText("I.S.C :");
        LblEtiqISCNC.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqISCNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 160, -1));

        LblIgvNC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblIgvNC.setText("0");
        LblIgvNC.setFont(new java.awt.Font("Verdana", 1, 12));
        LblIgvNC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(LblIgvNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 150, -1));

        LblEtiqSubtotalNetoSinIgvNC.setText("SUBT. NETO SIN IGV:");
        LblEtiqSubtotalNetoSinIgvNC.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqSubtotalNetoSinIgvNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 160, 20));

        LblSubTotalNetoSinIgvNC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubTotalNetoSinIgvNC.setText("0");
        LblSubTotalNetoSinIgvNC.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubTotalNetoSinIgvNC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(LblSubTotalNetoSinIgvNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 150, 20));

        LblEtiqTotalAcreditar.setForeground(new java.awt.Color(0, 0, 102));
        LblEtiqTotalAcreditar.setText("MONTO ACREDITAR:");
        LblEtiqTotalAcreditar.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqTotalAcreditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 160, -1));

        LblMontoTotalAcreditar.setForeground(new java.awt.Color(0, 51, 153));
        LblMontoTotalAcreditar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotalAcreditar.setText("0");
        LblMontoTotalAcreditar.setFont(new java.awt.Font("Verdana", 1, 12));
        LblMontoTotalAcreditar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(LblMontoTotalAcreditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 150, -1));

        LblEtiqPercepcion.setText("PERCEPCION :");
        LblEtiqPercepcion.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel2.add(LblEtiqPercepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 160, -1));

        LblMontoAcreditar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoAcreditar.setText("0");
        LblMontoAcreditar.setFont(new java.awt.Font("Verdana", 1, 12));
        LblMontoAcreditar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(LblMontoAcreditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 150, -1));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 390, 150));

        jPanel7.setBackground(java.awt.SystemColor.info);
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS GENERALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11))); // NOI18N

        lblEtiqEstado.setText("Estado");
        lblEtiqEstado.setFont(new java.awt.Font("Verdana", 1, 11));

        LblEstado.setText("EMITIDO");
        LblEstado.setFont(new java.awt.Font("Verdana", 1, 11));

        label10.setText("Concepto :");
        label10.setFont(new java.awt.Font("Verdana", 1, 11));

        CbxConcepto.setFont(new java.awt.Font("Verdana", 1, 11));
        CbxConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxConceptoActionPerformed(evt);
            }
        });

        label8.setText("N° Comprob.");
        label8.setFont(new java.awt.Font("Verdana", 1, 11));

        LblNumCompr.setText("000-0000000000");
        LblNumCompr.setFont(new java.awt.Font("Verdana", 1, 11));

        btnImportarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        btnImportarCompra.setText("Import. Comp. Venta");
        btnImportarCompra.setToolTipText("Import. Comprobante");
        btnImportarCompra.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImportarCompra.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImportarCompra.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnImportarCompra.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImportarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarCompraActionPerformed(evt);
            }
        });

        btnBuscarNotaCredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        btnBuscarNotaCredito.setText("Buscar dev de dinero");
        btnBuscarNotaCredito.setToolTipText("Buscar Nota Crédito");
        btnBuscarNotaCredito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscarNotaCredito.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarNotaCredito.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscarNotaCredito.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarNotaCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarNotaCreditoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbxConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblNumCompr, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(407, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap(679, Short.MAX_VALUE)
                    .addComponent(btnImportarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnBuscarNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CbxConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblNumCompr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBuscarNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImportarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        LblHoraSistema.setText("hora");
        LblHoraSistema.setFont(new java.awt.Font("Verdana", 1, 12));

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
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
                false, false, false, false, false,
                false, false, false,false,false, false, false,
                false,true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {

                if(columnIndex==oCLNotaCredito.colCantDevuelta||
                    columnIndex==oCLNotaCredito.colImporNuevo||
                    columnIndex==oCLNotaCredito.colPrecioNuevo){
                    Boolean issel=false;
                    if(this.getValueAt(rowIndex, oCLNotaCredito.colCheck)!=null)
                    {
                        issel=Boolean.parseBoolean(this.getValueAt(rowIndex, oCLNotaCredito.colCheck)+"");
                    }
                    if(columnIndex==oCLNotaCredito.colCantDevuelta){

                        if(idConcepto==1){
                            issel=false;
                        }
                    }

                    return issel;
                }

                if(columnIndex==oCLNotaCredito.colCheck){
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
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colItem).setPreferredWidth(30);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colCodigo).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colCodigo).setPreferredWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colCodigo).setMaxWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colStock).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colStock).setPreferredWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colStock).setMaxWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colProducto).setPreferredWidth(320);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colPrecio).setPreferredWidth(60);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colUnidadMedida).setPreferredWidth(75);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colCantidad).setPreferredWidth(80);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colImporSinDesc).setPreferredWidth(95);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colTipoDescuento).setPreferredWidth(75);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colDescuento).setPreferredWidth(70);
        TblNotaCreditoDetalle.getColumnModel().getColumn(10).setPreferredWidth(95);
        TblNotaCreditoDetalle.getColumnModel().getColumn(11).setPreferredWidth(90);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colExonerado).setPreferredWidth(85);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colCantDevuelta).setPreferredWidth(85);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colPrecioNuevo).setPreferredWidth(80);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colImporNuevo).setPreferredWidth(95);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colCheck).setPreferredWidth(45);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colImporSinDesc).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colTipoDescuento).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colDescuento).setMinWidth(0);
        TblNotaCreditoDetalle.getColumnModel().getColumn(oCLNotaCredito.colProporcion).setMinWidth(0);

        label26.setText("DETALLE DE PRODUCTOS");
        label26.setFont(new java.awt.Font("Verdana", 1, 12));

        columnButton1.setTbl(TblNotaCreditoDetalle);

        BtnCalcularColumnas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/seleccionar.jpg"))); // NOI18N
        BtnCalcularColumnas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCalcularColumnasActionPerformed(evt);
            }
        });

        LblTituloComprobante.setBackground(new java.awt.Color(255, 255, 255));
        LblTituloComprobante.setFont(new java.awt.Font("Verdana", 1, 18));
        LblTituloComprobante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblTituloComprobante.setText("COMPROBANTE DEVOLUCION DE DINERO");
        LblTituloComprobante.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        LblTituloComprobante.setOpaque(true);

        LblNumNotaCred.setBackground(new java.awt.Color(255, 255, 255));
        LblNumNotaCred.setFont(new java.awt.Font("Verdana", 1, 18));
        LblNumNotaCred.setForeground(new java.awt.Color(0, 0, 102));
        LblNumNotaCred.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblNumNotaCred.setText("003-2126633");
        LblNumNotaCred.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        LblNumNotaCred.setOpaque(true);

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

        jCheckBox2.setText("IMPRIMIR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(label26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(308, 308, 308)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(BtnCalcularColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox2)
                        .addGap(128, 128, 128)
                        .addComponent(LblTituloComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblNumNotaCred, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblTituloComprobante)
                    .addComponent(LblNumNotaCred)
                    .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label26, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCalcularColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

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
    TxtObservacion.setEnabled(bol);

}

private int ValidarRegistro(int fila)
    {
return 0;
}

 private CEComprobanteVentaMatriz oCEComprobanteVentaMatrizAnulado= null;

    public void getoCEComprobanteVentaMatrizAnulado() {

         oCEComprobanteVentaMatrizAnulado= new CEComprobanteVentaMatriz();
         oCEComprobanteVentaMatrizAnulado.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
         oCEComprobanteVentaMatrizAnulado.setUltimoUsuario(FrmSistemaMenu.oCEUsuario.getUsuario());
         oCEComprobanteVentaMatrizAnulado.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
         oCEComprobanteVentaMatrizAnulado.setIdComprobanteVenta(oCEComprobanteVentaMatriz.getIdComprobanteVenta());
         oCEComprobanteVentaMatrizAnulado.setUltimaObservacion(TxtObservacion.getText());
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
              oCLNotaCredito.CalcularSubtotal();
              oCLNotaCredito.calcularImportes();
              GuardarRegistro();

          }else
          {
              TblNotaCreditoDetalle.requestFocus();
              TblNotaCreditoDetalle.changeSelection(TblNotaCreditoDetalle.getRowCount()-1, columnaValidada, false, false);
          }
     }
    }
    private boolean  validar()
    {
        boolean isSelccionado=false;
        for( int i =0;i<TblNotaCreditoDetalle.getRowCount();i++)
            {
                if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colCheck)!=null)
                {
                    if(!isSelccionado){
                    isSelccionado=Boolean.parseBoolean(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colCheck)+"");
                    }
                }
                if(!verificarDatos(i))
                    {return false;}
            }
        

          if(!isSelccionado)
        {
            JOptionPane.showMessageDialog(null,"Seleccione al menos un registro de la tabla Pedido de productos","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
           return false;
        }
        return true;
    }

    private void GuardarRegistro()
    {       
        int IdAlmacen=1;
        int IdCaja=3;
        if(validar()){

                    if(pAccionABM==1){
                        oCENotaCredito = new CENotaCredito();

                           
                        this.getoCEComprobanteVentaMatrizAnulado();
                        
                        String[] numeroNotaCredito=new String[1] ;
                        String mensaje="";
                        if(!sino_devolucion_dinero){
                        numeroNotaCredito=CCNotaCredito.InsNotaCredito(this.getNotaCreditoIns(),oCEComprobanteVentaMatrizAnulado,
                                                                       IdCaja,IdAlmacen);
                        mensaje="Nota Crédito";
                        }else{
                         
                            numeroNotaCredito=CCComprobanteDevolucion.InsComprobanteDevolucion(this.getNotaCreditoIns(),oCEComprobanteVentaMatrizAnulado
                                                                                               ,IdCaja,IdAlmacen);
                            
                            mensaje="Devolucion de Dinero";
                        }

                        if(numeroNotaCredito!=null){
                                    CEConcepto oCEConcepto=(CEConcepto)CbxConcepto.getSelectedItem();
                                    if(oCEConcepto.getIdConcepto()==1){
                                    JOptionPane.showMessageDialog(null,"<html>Se guardo con éxito: "+mensaje+" N° "+numeroNotaCredito[0]+
                                                                        "<br>Se anuló con éxito: Comprobante Venta N° "+LblNumCompr.getText()+"</html>");
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(null,"<html>Se guardo con éxito: "+mensaje+" N° "+numeroNotaCredito[0]
                                                                    +    "<br>Se genero el Numero de Ingreso: "+numeroNotaCredito[1]+"</html>"
                                                                      );
                                    }

                                if(pIdConcepto!=0)
                                {
                                    iscompAnulado=true;
                                    dispose();
                                }else{
                                generarSerie();
                                limpiarFormulario();
                                limpiarFormularioNotaCredito();
                                LblHoraRegistro1.setVisible(false);
                                oclBotonesABM.controlBoton(true, false, false, false, true, false);}

                     }
                        else{

                            JOptionPane.showMessageDialog(null,"Operación Fallida");
                            }
               }else{
                boolean iscorrecto=false;
                if(!sino_devolucion_dinero){
                       iscorrecto =CCNotaCredito.UPDNotaCredito(this.getNotaCreditoIns());
                        }else
                        {
                            iscorrecto =CCComprobanteDevolucion.UPDComprobanteDevolucion(this.getNotaCreditoIns());
                        }
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

    private CENotaCredito getNotaCreditoIns(){

        oCENotaCredito= new CENotaCredito();
        oCENotaCredito.setIdSucursal(FrmSistemaMenu.getIdSucursal());
        oCENotaCredito.setUltimoIdEstado(1);
        oCENotaCredito.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCENotaCredito.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        CEConcepto oCEConcepto=(CEConcepto)CbxConcepto.getSelectedItem();
        oCENotaCredito.setIdConcepto(oCEConcepto.getIdConcepto());
        oCENotaCredito.setIdCliente(oCEComprobanteVentaMatriz.getIdCliente());
        oCENotaCredito.setIdMoneda(oCEComprobanteVentaMatriz.getIdMoneda());
        oCENotaCredito.setIdCondicion(oCEComprobanteVentaMatriz.getIdCondicion());
        oCENotaCredito.setIdTipoComprobante(oCEComprobanteVentaMatriz.getIdTipoComprobanteVenta());
        oCENotaCredito.setIdComprobanteVentaRef(oCEComprobanteVentaMatriz.getIdComprobanteVenta());
        oCENotaCredito.setMontoAcreditar(Float.parseFloat(LblMontoAcreditar.getText()));
        oCENotaCredito.setMontoTotalAcreditar(Float.parseFloat(LblMontoTotalAcreditar.getText()));
        oCENotaCredito.setMontoPercepcion(Float.parseFloat(lblMontoPercepcion.getText()));
        oCENotaCredito.setSubtotalSinIGVNuevo(Float.parseFloat(LblSubTotalNetoSinIgvNC.getText()));
        oCENotaCredito.setIGVNuevo(Float.parseFloat(LblIgvNC.getText()));
        oCENotaCredito.setUltimaObservacion(TxtObservacion.getText());
        List<CENotaCreditoDetalle> lstCENotaCreditoDetalle = new ArrayList<CENotaCreditoDetalle>();
        CEComprobanteVentaDetalle oCEComprobanteVentaDetalle;
        CENotaCreditoDetalle oCENotaCreditoDetalle=new CENotaCreditoDetalle();
        for( int i =0;i<TblNotaCreditoDetalle.getRowCount();i++)
            {


                oCEComprobanteVentaDetalle= (CEComprobanteVentaDetalle)TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colCodigo);
                if(oCEComprobanteVentaDetalle!=null){
                    if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colCheck)!=null)
                    {
                        Boolean isSelccionado=Boolean.parseBoolean(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colCheck)+"");
                        if(isSelccionado)
                        {
                        oCENotaCreditoDetalle=new CENotaCreditoDetalle();
                        oCENotaCreditoDetalle.setIdComprobanteDetalleRef(oCEComprobanteVentaDetalle.getIdComprobanteVentaDetalle());
                        oCENotaCreditoDetalle.setSinoImpuesto(oCEComprobanteVentaDetalle.isSiNoImpuesto());
                        oCENotaCreditoDetalle.setIdProducto(oCEComprobanteVentaDetalle.getIdProducto());
                        oCENotaCreditoDetalle.setIdAlmacen(oCEComprobanteVentaDetalle.getIdAlmacen());
                        oCENotaCreditoDetalle.setIdUnidadMedidaVenta(oCEComprobanteVentaDetalle.getIdUnidadMedidaVenta());

                        if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colImporConDesc)!=null){
                                oCENotaCreditoDetalle.setImporteAnterior(Float.parseFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colImporConDesc).toString()));
                            }
                        if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colExonerado)!=null){
                                oCENotaCreditoDetalle.setExonerado(Float.parseFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colExonerado).toString()));
                            }
                        oCENotaCreditoDetalle.setProporcionDescuentoTotal(0);
                        if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colProporcion)!=null){
                            oCENotaCreditoDetalle.setProporcionDescuentoTotal(Float.parseFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colProporcion).toString()));
                        }
                        if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colImporNuevo)!=null){
                        oCENotaCreditoDetalle.setImporteNuevo(Float.parseFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colImporNuevo).toString()));}
                        if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colPrecioNuevo)!=null){
                        oCENotaCreditoDetalle.setPrecioNuevo(Float.parseFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colPrecioNuevo).toString()));}
                        if(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colCantDevuelta)!=null){
                        oCENotaCreditoDetalle.setCantidadNueva(Float.parseFloat(TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colCantDevuelta).toString()));}

                        lstCENotaCreditoDetalle.add(oCENotaCreditoDetalle);}

                    }
                }
            }

            oCENotaCredito.setLstCENotaCreditoDetalle(lstCENotaCreditoDetalle);

            return oCENotaCredito;
}

    private boolean  ValidarFecha(String pFecha)
    {
     int isFechaActual=CCValores.obenerFechaDelSistemaDMY(pFecha);
     if(isFechaActual==1)
     {
         if(sino_devolucion_dinero)
             return true;
         else
            return false;
     }else
         if (sino_devolucion_dinero)
         return false;
         else
             return true;

    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        if(pIdConcepto==0){
                if(pAccionABM==1||pAccionABM==2)
               {
                   habilitarControles(false);
                 oclBotonesABM.controlBoton(true, false, false, false, true, true);
                    LblHoraRegistro1.setVisible(false);
                 pAccionABM=0;
                 cnsNumDoc=2;
                 limpiarFormulario();
                 limpiarFormularioNotaCredito();
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

    Boolean isCorrecto=false;     private int cnsNumDoc=1;// 1 : comprobante
    private CEComprobanteVentaMatriz oCEComprobanteVentaMatriz= null;
    private CENotaCredito oCENotaCredito;
    private void buscarComprobanteVenta()
    {
        oCLNotaCredito.limpiarTabla();
        
              
            if(oCEComprobanteVentaMatriz!=null)
            {if(oCEComprobanteVentaMatriz.getUltimoIdEstado()!=4){
                 if(ValidarFecha(oCEComprobanteVentaMatriz.getFecha()))
                 {
                        limpiarFormulario();
                        limpiarFormularioNotaCredito();
                        if(oCEComprobanteVentaMatriz.getMontoPercepcion()==0)
                        {
                            oCLNotaCredito.setConPercepcion(false);
                        }
                        else
                        {
                            oCLNotaCredito.setConPercepcion(true);
                        }
                        LblEstado.setText(oCEComprobanteVentaMatriz.getUltimoEstado());
                        IdEstadoComprobante=oCEComprobanteVentaMatriz.getUltimoIdEstado();
                        LblEstado.setText(oCEComprobanteVentaMatriz.getUltimoEstado());
                        TxtCliente.setText(oCEComprobanteVentaMatriz.getCliente());
                        TxtTipoDescuento.setText(oCEComprobanteVentaMatriz.getTipoDescuento());
                        TxtMoneda.setText(oCEComprobanteVentaMatriz.getMoneda());
                        TxtTipoComprobante.setText(oCEComprobanteVentaMatriz.getTipoComprobante());
                        TxtDescuento.setText(oCEComprobanteVentaMatriz.getDescuento()+"");
                        LblHoraRegistro2.setVisible(true);
                        LblHoraRegistro1.setText(oCEComprobanteVentaMatriz.getFecha());
                        LblSubtotalNeto.setText(oCEComprobanteVentaMatriz.getSubtotalNeto()+"");
                        LblSubTotaNetolSinIgv.setText(oCEComprobanteVentaMatriz.getSubTotalNetoSinIGV()+"");
                        LblIgv.setText(oCEComprobanteVentaMatriz.getIGV()+"");
                        LblMontoTotalComp.setText(oCEComprobanteVentaMatriz.getMontoTotal()+"");
                        LblMontoPercepcionComp.setText(oCEComprobanteVentaMatriz.getMontoPercepcion()+"");
                        LblMontoTotalCobrado.setText(oCEComprobanteVentaMatriz.getTotalPagar()+"");
                        LblNumCompr.setText(oCEComprobanteVentaMatriz.getNumComprobante());

                        if(oCEComprobanteVentaMatriz.getoLstComprobanteDetalle()!=null)
                        {
                            int Items=oCEComprobanteVentaMatriz.getoLstComprobanteDetalle().size();
                            double Proporcion=CLRedondear.Redondear(oCEComprobanteVentaMatriz.getDescuento()/Items,2);
                           for(int i=0; i<oCEComprobanteVentaMatriz.getoLstComprobanteDetalle().size();i++){
                              Vector oVector = new Vector();

                            ((DefaultTableModel)TblNotaCreditoDetalle.getModel()).addRow(oVector);

                            CEComprobanteVentaDetalle oCEComprobanteVentaDetalle=oCEComprobanteVentaMatriz.getoLstComprobanteDetalle().get(i);

                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle,i,oCLNotaCredito.colCodigo);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getProducto(),i,oCLNotaCredito.colProducto);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getUnidadMedida(),i,oCLNotaCredito.colUnidadMedida);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getPrecioconDesc(),i,oCLNotaCredito.colPrecio);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getCantidad(),i,oCLNotaCredito.colCantidad);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getTipoDescuento(),i,oCLNotaCredito.colTipoDescuento);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getDescuento(),i,oCLNotaCredito.colDescuento);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getImporteSinDescuento(),i,oCLNotaCredito.colImporSinDesc);
                            float importeNuevo=0;
                            if(!oCEComprobanteVentaDetalle.isSiNoImpuesto())
                            {
                                importeNuevo=oCEComprobanteVentaDetalle.getExonerado()-(float)Proporcion;
                                TblNotaCreditoDetalle.setValueAt(importeNuevo,i,oCLNotaCredito.colExonerado);
                            }else {
                                importeNuevo=(oCEComprobanteVentaDetalle.getImporteConDescuento()-(float)Proporcion);
                               TblNotaCreditoDetalle.setValueAt(importeNuevo,i,oCLNotaCredito.colImporConDesc);
                            }


                            TblNotaCreditoDetalle.setValueAt(Proporcion, i, oCLNotaCredito.colProporcion);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getCantidad(), i, oCLNotaCredito.colCantDevuelta);


                            }
                         }

                        TblNotaCreditoDetalle.requestFocus();
                        TblNotaCreditoDetalle.changeSelection(0,oCLNotaCredito.colImporNuevo, false, false);
                        if(cnsNumDoc==1){
                        oclBotonesABM.controlBoton(false, true, false, true, true, false);}
                         else{
                            oclBotonesABM.controlBoton(true, true, true, true, true, false);}

                        if(IdEstadoComprobante!=1)
                        {
                            btnAnular.setEnabled(false);
                            btnEditar.setEnabled(false);
                        }
                        HabilitarcolumnasporConcepto();
                    }else
                            {
                             JOptionPane.showMessageDialog(null,"No se puede Generar el documento,El Comprobante es del dia");
                             return ;
                            }
               }
                else{
                JOptionPane.showMessageDialog(null,"El Comprobante ya está anulado,No se puede generar el Documento");
                }
           }
        else
            {
              JOptionPane.showMessageDialog(null,"El codigo no Existe");
            }

        
        
    }

    private void setNotaCreditoDetalle(int i,long idComprobanteDetRef,float Proporcion)
    {

        if(oCEComprobanteVentaMatriz.getoLstComprobanteDetalle()!=null)
            {

            for(int j=0; j<oCEComprobanteVentaMatriz.getoLstComprobanteDetalle().size();j++){
                    CEComprobanteVentaDetalle oCEComprobanteVentaDetalle=oCEComprobanteVentaMatriz.getoLstComprobanteDetalle().get(j);

                    if(oCEComprobanteVentaDetalle.getIdComprobanteVentaDetalle()==idComprobanteDetRef){

                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle,i,oCLNotaCredito.colCodigo);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getProducto(),i,oCLNotaCredito.colProducto);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getUnidadMedida(),i,oCLNotaCredito.colUnidadMedida);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getPrecioconDesc(),i,oCLNotaCredito.colPrecio);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getCantidad(),i,oCLNotaCredito.colCantidad);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getTipoDescuento(),i,oCLNotaCredito.colTipoDescuento);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getDescuento(),i,oCLNotaCredito.colDescuento);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getImporteSinDescuento(),i,oCLNotaCredito.colImporSinDesc);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getImporteConDescuento()-Proporcion,i,oCLNotaCredito.colImporConDesc);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getExonerado()-Proporcion,i,oCLNotaCredito.colExonerado);
                        TblNotaCreditoDetalle.setValueAt(Proporcion, i, oCLNotaCredito.colProporcion);
                        TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getCantidad(), i, oCLNotaCredito.colCantDevuelta);
                        break;
                    }
                }
        }
    }
    private void SetComprobanteDetalleFaltante()
    {
        if(oCEComprobanteVentaMatriz.getoLstComprobanteDetalle()!=null)
            {
                int Items=oCEComprobanteVentaMatriz.getoLstComprobanteDetalle().size();
                float Proporcion=(float)CLRedondear.Redondear(oCEComprobanteVentaMatriz.getDescuento()/Items,2);
               for(int i=0; i<oCEComprobanteVentaMatriz.getoLstComprobanteDetalle().size();i++){
                    CEComprobanteVentaDetalle oCEComprobanteVentaDetalle=oCEComprobanteVentaMatriz.getoLstComprobanteDetalle().get(i);
                    boolean existeCompDet=this.getExisteIdNotaCreditoDetalle(oCEComprobanteVentaDetalle.getIdComprobanteVentaDetalle());
                    if(!existeCompDet)
                        {

                             Vector oVector = new Vector();
                            ((DefaultTableModel)TblNotaCreditoDetalle.getModel()).addRow(oVector);
                             int countfil=TblNotaCreditoDetalle.getRowCount()-1;
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle,countfil,oCLNotaCredito.colCodigo);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getProducto(),countfil,oCLNotaCredito.colProducto);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getUnidadMedida(),countfil,oCLNotaCredito.colUnidadMedida);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getPrecioconDesc(),countfil,oCLNotaCredito.colPrecio);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getCantidad(),countfil,oCLNotaCredito.colCantidad);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getTipoDescuento(),countfil,oCLNotaCredito.colTipoDescuento);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getDescuento(),countfil,oCLNotaCredito.colDescuento);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getImporteSinDescuento(),countfil,oCLNotaCredito.colImporSinDesc);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getImporteConDescuento()-Proporcion,countfil,oCLNotaCredito.colImporConDesc);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getExonerado()-Proporcion,countfil,oCLNotaCredito.colExonerado);
                            TblNotaCreditoDetalle.setValueAt(Proporcion, countfil, oCLNotaCredito.colProporcion);
                            TblNotaCreditoDetalle.setValueAt(oCEComprobanteVentaDetalle.getCantidad(), countfil, oCLNotaCredito.colCantDevuelta);
                         }
                   
                }
        }

    }
    private boolean getExisteIdNotaCreditoDetalle(long pIdCompVentDealle)
    {
        for( int i =0;i<TblNotaCreditoDetalle.getRowCount();i++)
            {

             CEComprobanteVentaDetalle   oCEComprobanteVentaDetalle= (CEComprobanteVentaDetalle)TblNotaCreditoDetalle.getValueAt(i, oCLNotaCredito.colCodigo);
                if(oCEComprobanteVentaDetalle!=null){
                    if(oCEComprobanteVentaDetalle.getIdComprobanteVentaDetalle()==pIdCompVentDealle)
                    {
                        return true;
                    }
                }
        }
        return false;
    }

    private void buscarNotaCredito(int id)
    {
        oCLNotaCredito.setCalcular(false);
        oCLNotaCredito.limpiarTabla();
        if(!sino_devolucion_dinero){
       oCENotaCredito= CCNotaCredito.ListarNotaCredito(id);
        }else
        {
            oCENotaCredito=CCComprobanteDevolucion.ListarComprobanteDevolucionPorId(id);
        }
        if(oCENotaCredito!=null)
        {
            oCEComprobanteVentaMatriz= CCComprobanteVenta.ListarComprobanteVentaPorId(oCENotaCredito.getIdComprobanteVentaRef());
            limpiarFormulario();
            limpiarFormularioNotaCredito();
            LblNumCompr.setText(oCEComprobanteVentaMatriz.getNumComprobante());
            LblEstado.setText(oCENotaCredito.getUltimoEstado());
            IdEstadoComprobante=oCENotaCredito.getUltimoIdEstado();
            TxtCliente.setText(oCENotaCredito.getCliente());
            TxtMoneda.setText(oCENotaCredito.getMoneda());
            TxtTipoComprobante.setText(oCENotaCredito.getTipoComprobante());
            LblSubTotalNetoSinIgvNC.setText(""+oCENotaCredito.getSubtotalSinIGVNuevo());
            LblIgvNC.setText(""+oCENotaCredito.getIGVNuevo());
            LblISCNC.setText(""+oCENotaCredito.getISCNuevo());
            LblMontoAcreditar.setText(""+oCENotaCredito.getMontoAcreditar());
            LblMontoTotalAcreditar.setText(""+oCENotaCredito.getMontoTotalAcreditar());
            lblMontoPercepcion.setText(""+oCENotaCredito.getMontoPercepcion());
            LblNumNotaCred.setText(oCENotaCredito.getNumeroNotaCredito());
            TxtObservacion.setText(oCENotaCredito.getUltimaObservacion());
            sino_accionHabilitarColumnas=false;
            CLCargarCombo.buscarIdConcepto(CbxConcepto, oCENotaCredito.getIdConcepto());
            sino_accionHabilitarColumnas=true;
                if(oCENotaCredito.getLstCENotaCreditoDetalle()!=null)
                {
                    for(int i=0; i<oCENotaCredito.getLstCENotaCreditoDetalle().size();i++){
                    CENotaCreditoDetalle oCENotaCreditoDetalle=oCENotaCredito.getLstCENotaCreditoDetalle().get(i);
                       Vector oVector = new Vector();

                    ((DefaultTableModel)TblNotaCreditoDetalle.getModel()).addRow(oVector);


                    TblNotaCreditoDetalle.setValueAt(oCENotaCreditoDetalle.getCantidadNueva(), i, oCLNotaCredito.colCantDevuelta);
                    TblNotaCreditoDetalle.setValueAt(oCENotaCreditoDetalle.getPrecioNuevo(), i, oCLNotaCredito.colPrecioNuevo);
                    TblNotaCreditoDetalle.setValueAt(oCENotaCreditoDetalle.getImporteNuevo()+oCENotaCreditoDetalle.getExonerado(), i, oCLNotaCredito.colImporNuevo);
                    TblNotaCreditoDetalle.setValueAt(true, i, oCLNotaCredito.colCheck);
                    setNotaCreditoDetalle(i,oCENotaCreditoDetalle.getIdComprobanteDetalleRef(), oCENotaCreditoDetalle.getProporcionDescuentoTotal());
                }


                habilitarControles(false);
                btnEditar.requestFocus();
                if(cnsNumDoc==1){
                oclBotonesABM.controlBoton(false, false, false, true, true, false);}
                 else{
                    oclBotonesABM.controlBoton(true, false, true, true, true, false);}
              CEConcepto oCEConcepto=(CEConcepto)CbxConcepto.getSelectedItem();
              idConcepto=oCEConcepto.getIdConcepto();
            }
       }
       else
        {
          JOptionPane.showMessageDialog(null,"El codigo no Existe");
        }
        oCLNotaCredito.setCalcular(true);
    }
    private void verDatosCompobante()
    {
            LblSubtotalBruto.setText(oCEComprobanteVentaMatriz.getSubtotalBruto()+"");
            LblDescuentoSubtotal.setText(oCEComprobanteVentaMatriz.getDescuentoEnSubtotal()+"");
            TxtDescuento.setText(oCEComprobanteVentaMatriz.getDescuento()+"");
            LblDescuentoTotal.setText(oCEComprobanteVentaMatriz.getDescuentoTotal()+"");
            LblSubtotalNeto.setText(oCEComprobanteVentaMatriz.getSubtotalNeto()+"");
            LblSubTotaNetolSinIgv.setText(oCEComprobanteVentaMatriz.getSubTotalNetoSinIGV()+"");
            LblIgv.setText(oCEComprobanteVentaMatriz.getIGV()+"");
            LblISC.setText(oCEComprobanteVentaMatriz.getISC()+"");
            LblMontoTotalCobrado.setText(oCEComprobanteVentaMatriz.getMontoTotal()+"");
            TxtTipoDescuento.setText(oCEComprobanteVentaMatriz.getTipoDescuento());
        
    }

   

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
     pAccionABM=1;
        oclBotonesABM.controlBoton(false, true, false, false, true, false);
       habilitarControles(true);
        cnsNumDoc=1;
        limpiarFormulario();
        oCLNotaCredito.limpiarTabla();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed

        try {

             if(oCENotaCredito.getUltimoIdEstado()!=4){
                    oclBotonesABM.controlBoton(true, false, false, false, true, false);


                    oCENotaCredito.setIdNotaCredito(oCENotaCredito.getIdNotaCredito());
                    oCENotaCredito.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
                    oCENotaCredito.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());


                       DlgAnularNotaCredito odlgAnularNotaCredito=new DlgAnularNotaCredito(null, true, oCENotaCredito,sino_devolucion_dinero);
                       odlgAnularNotaCredito.setVisible(true);
                       if(odlgAnularNotaCredito.IsAnulado())
                       {
                           JOptionPane.showMessageDialog(null,"Se anulo con exito");
                           limpiarFormulario();
                       }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"No se puede Realizar cambios,El documento ya está anulado");
                }
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Ne se puede Editar");
            }

        
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

       // try {
                if(oCENotaCredito.getUltimoIdEstado()!=4){
                    oclBotonesABM.controlBoton(false, true, false, false, true, false);
                     habilitarControles(true);
                     CbxConcepto.setEnabled(false);
                     pAccionABM=2;
                     SetComprobanteDetalleFaltante();
                     verDatosCompobante();
                     if(TblNotaCreditoDetalle.getRowCount()>0)
                     {
                         TblNotaCreditoDetalle.requestFocus();
                         TblNotaCreditoDetalle.changeSelection(0,oCLNotaCredito.colImporNuevo, false, false);
                     }
                }else
                {
                    JOptionPane.showMessageDialog(null,"No se puede Realizar cambios, el documento ya está anulado");
                }
//            }catch(Exception e)
//            {
//                JOptionPane.showMessageDialog(null,"No se puede Editar");
//            }
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
                    TblNotaCreditoDetalle.setValueAt(state,i,oCLNotaCredito.colCheck);

                }
                
            }
        }
}//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void CbxConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxConceptoActionPerformed
      HabilitarcolumnasporConcepto();
      oCLNotaCredito.CalcularSubtotal();
      oCLNotaCredito.calcularImportes();
    }//GEN-LAST:event_CbxConceptoActionPerformed
 private int cont=0;// contador utilizado como artificio
    private void TblNotaCreditoDetallePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblNotaCreditoDetallePropertyChange
if(!iscerrando){
int fila=TblNotaCreditoDetalle.getSelectedRow();
int col=TblNotaCreditoDetalle.getSelectedColumn();

if(TblNotaCreditoDetalle.getSelectedRow()!=-1){
        if(TblNotaCreditoDetalle.getSelectedColumn()==oCLNotaCredito.colCheck)
        {
            boolean chek=false;
            repaint();
            
            
             if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colCheck)!=null){
                chek= Boolean.parseBoolean(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colCheck).toString());
              }

            
              if(chek){

             CEComprobanteVentaDetalle oCEComprobanteVentaDetalle=(CEComprobanteVentaDetalle)TblNotaCreditoDetalle.getValueAt(fila,oCLNotaCredito.colCodigo);


            float importeNuevo=0;
            float Proporcion=0;

            if(!oCEComprobanteVentaDetalle.isSiNoImpuesto())
            {
                   Proporcion=Float.parseFloat(TblNotaCreditoDetalle.getValueAt( fila, oCLNotaCredito.colProporcion).toString());
            }
            if(!oCEComprobanteVentaDetalle.isSiNoImpuesto())
            {
                importeNuevo=oCEComprobanteVentaDetalle.getExonerado()-(float)Proporcion;

            }else {
                importeNuevo=(oCEComprobanteVentaDetalle.getImporteConDescuento()-(float)Proporcion);

            }
             

             float cantidad=VerificadorDeTxt.convertFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colCantidad));
             String precioNuevo=CLRedondear.RedondearString(importeNuevo/cantidad,4);
            TblNotaCreditoDetalle.setValueAt(cantidad,fila, oCLNotaCredito.colCantDevuelta);
            TblNotaCreditoDetalle.setValueAt(precioNuevo, fila, oCLNotaCredito.colPrecioNuevo);
            TblNotaCreditoDetalle.setValueAt(importeNuevo, fila, oCLNotaCredito.colImporNuevo);
            
            

            }else
            {
            TblNotaCreditoDetalle.setValueAt(null, fila, oCLNotaCredito.colCantDevuelta);
            TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCredito.colPrecioNuevo);
            TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCredito.colImporNuevo);
            }
            
        }


        
        }
        if(cont==1){
           
           if(fila!=-1){
               if(col==oCLNotaCredito.colImporNuevo||col==oCLNotaCredito.colCantDevuelta||col==oCLNotaCredito.colPrecioNuevo){
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
          if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colCheck)!=null)
           {
            Boolean isSelccionado=Boolean.parseBoolean(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colCheck)+"");
            if(isSelccionado)
            {
            float cantidadNueva=0; float precioNuevo=0; float ImporteNuevo=0; float ImporteConDes=0; float Cantidad=0;
            if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colCantDevuelta)!=null){
            cantidadNueva=Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colCantDevuelta).toString());}
            if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colPrecioNuevo)!=null){
            precioNuevo=Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colPrecioNuevo).toString());}

             if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colImporNuevo)!=null){
             ImporteNuevo=Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colImporNuevo).toString());}

       //    CEComprobanteVentaDetalle oCEComprobanteVentaDetalle= (CEComprobanteVentaDetalle)TblPedidoDetalle.getValueAt(fila, oCLNotaCredito.colCodigo);
         //  if(oCEComprobanteVentaDetalle!=null){
          //  if(oCEComprobanteVentaDetalle.isSiNoImpuesto())
            //    {
                if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colImporConDesc)!=null){
                   ImporteConDes=Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colImporConDesc).toString());}
             //   }
              //  else{
                     if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colExonerado)!=null){
                     ImporteConDes=ImporteConDes+Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colExonerado).toString());}
                //     }
           // }
            if(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colCantidad)!=null){
             Cantidad=Float.parseFloat(TblNotaCreditoDetalle.getValueAt(fila, oCLNotaCredito.colCantidad).toString());}
            
                if(cantidadNueva>Cantidad)
                {
                    JOptionPane.showMessageDialog(null,"La cantidad Nueva no puede ser mayor a la cantidad registrada","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    TblNotaCreditoDetalle.setValueAt(Cantidad,fila, oCLNotaCredito.colCantDevuelta);
                    TblNotaCreditoDetalle.requestFocus();
                    TblNotaCreditoDetalle.changeSelection(fila,oCLNotaCredito.colCantDevuelta, false, false);
                    return false;
                }
                if(cantidadNueva<=0)
                {
                    JOptionPane.showMessageDialog(null,"La cantidad Nueva no puede ser menor a cero","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    TblNotaCreditoDetalle.setValueAt(Cantidad,fila, oCLNotaCredito.colCantDevuelta);
                    TblNotaCreditoDetalle.requestFocus();
                    TblNotaCreditoDetalle.changeSelection(fila,oCLNotaCredito.colCantDevuelta, false, false);
                    return false;
                }
                    if(ImporteNuevo>ImporteConDes)
                    {
                    JOptionPane.showMessageDialog(null,"El importe nuevo no puede ser mayor al Importe con descuento o Exonerado","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCredito.colPrecioNuevo);
                    TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCredito.colImporNuevo);
                    TblNotaCreditoDetalle.requestFocus();
                    TblNotaCreditoDetalle.changeSelection(fila,oCLNotaCredito.colPrecioNuevo, false, false);
                    return false;
                    }
                    if(ImporteNuevo<=0)
                    {
                        JOptionPane.showMessageDialog(null,"El importe nuevo no puede ser menor a cero","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                        TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCredito.colPrecioNuevo);
                        TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCredito.colImporNuevo);
                        TblNotaCreditoDetalle.requestFocus();
                        TblNotaCreditoDetalle.changeSelection(fila,oCLNotaCredito.colImporNuevo, false, false);
                        return false;
                    }
                    if(precioNuevo<=0)
                    {
                        JOptionPane.showMessageDialog(null,"El precio nuevo no puede ser menor a cero","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                        TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCredito.colPrecioNuevo);
                        TblNotaCreditoDetalle.setValueAt(null,fila, oCLNotaCredito.colImporNuevo);
                        TblNotaCreditoDetalle.requestFocus();
                        TblNotaCreditoDetalle.changeSelection(fila,oCLNotaCredito.colPrecioNuevo, false, false);
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

    private void calcularMontos()
    {

    if(TblNotaCreditoDetalle.getRowCount()>0){
        for(int i=0;i<TblNotaCreditoDetalle.getRowCount();i++){
            if(TblNotaCreditoDetalle.getValueAt(i,oCLNotaCredito.colCodigo)!=null){
             

              boolean check=false;
                    if(TblNotaCreditoDetalle.getValueAt(i,oCLNotaCredito.colCheck)!=null){
                      check=Boolean.parseBoolean(TblNotaCreditoDetalle.getValueAt(i,oCLNotaCredito.colCheck).toString());}
                    if(check){
                        
                          TblNotaCreditoDetalle.setValueAt(TblNotaCreditoDetalle.getValueAt(i,oCLNotaCredito.colPrecio),i,oCLNotaCredito.colPrecioNuevo);
                        
                        oCLNotaCredito.CalcularCeldaImporteNuevo(i, oCLNotaCredito.colPrecioNuevo);
                    }
            }
         }
     }
    }
    private void BtnCalcularColumnasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCalcularColumnasActionPerformed

calcularMontos();
    }//GEN-LAST:event_BtnCalcularColumnasActionPerformed

    private void btnImportarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarCompraActionPerformed

        DlgGestionComprobanteVenta odialogo=new DlgGestionComprobanteVenta(oparent, true,false,false);
        odialogo.ocultarBotones();
        odialogo.setVisibleBtnAceptar(true);
        odialogo.setVisible(true);
        CEComprobanteVentaMatriz vCEComprobanteVentaMatriz= odialogo.getCEComprobanteVenta();

        if(vCEComprobanteVentaMatriz!=null) {

            oCEComprobanteVentaMatriz=CCComprobanteVenta.ListarComprobanteVentaPorId(vCEComprobanteVentaMatriz.getIdComprobanteVenta());
            buscarComprobanteVenta();
            HabilitarcolumnasporConcepto();

        }
}//GEN-LAST:event_btnImportarCompraActionPerformed

    private void btnBuscarNotaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNotaCreditoActionPerformed

        DlgBusquedaNotaCredito odialogo=new DlgBusquedaNotaCredito(oparent, true,sino_devolucion_dinero);
        odialogo.setVisible(true);
        CENotaCredito oCENotaCreditoAux= odialogo.getCENotaCredito();

        if(oCENotaCreditoAux!=null) {

            buscarNotaCredito(oCENotaCreditoAux.getIdNotaCredito());
        }
    }//GEN-LAST:event_btnBuscarNotaCreditoActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed

        CLExportarExcel oExportar=new CLExportarExcel(TblNotaCreditoDetalle,
                new String[]{lblEtiqTipoComp.getText(),TxtTipoComprobante.getText(),
                "Nº Documento",LblNumNotaCred.getText(),lblEtiqEstado.getText(),LblEstado.getText(),
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

    private void limpiarFormularioNotaCredito()
    {
     TxtObservacion.setText("");
     LblISCNC.setText("0");
     LblIgvNC.setText("0");
     LblSubTotalNetoSinIgvNC.setText("0");
     LblMontoAcreditar.setText("0");
     lblMontoPercepcion.setText("0");
     LblMontoTotalAcreditar.setText("0");
    }
    private void limpiarFormulario()
    {

        TxtObservacion.setText("");
        TxtTipoComprobante.setText("");
        TxtTipoDescuento.setText("");
        TxtMoneda.setText("");
        CbxConcepto.setSelectedIndex(0);
        LblNumCompr.setText("");
//      TxtTipoCambio.setText("");
        TxtCliente.setText("");
        TxtDescuento.setText("");
        oCLNotaCredito.limpiarTabla();
        TxtDescuento.setText("0");
        LblSubtotalNeto.setText("0");
        LblSubTotaNetolSinIgv.setText("0");
        LblIgv.setText("0");
        LblISC.setText("0");
        LblMontoTotalCobrado.setText("0");
        LblMontoPercepcionComp.setText("0");
        LblMontoTotalComp.setText("0");
        TxtDescuento.setText("0");
        

    }
    /**
    * @param args the command line arguments
    */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCalcularColumnas;
    private ComboBox.ComboBox CbxConcepto;
    private Label.Label LblDescuentoSubtotal;
    private Label.Label LblDescuentoTotal;
    private Label.Label LblEstado;
    private Label.Label LblEtiqCliente;
    private Label.Label LblEtiqISCNC;
    private Label.Label LblEtiqIgvNC;
    private Label.Label LblEtiqMoneda;
    private Label.Label LblEtiqMontoTotal;
    private Label.Label LblEtiqPercepcion;
    private Label.Label LblEtiqSubtotalNetoSinIgvNC;
    private Label.Label LblEtiqSucursal;
    private Label.Label LblEtiqTipoCambio;
    private Label.Label LblEtiqTotalAcreditar;
    private Label.Label LblHoraRegistro1;
    private Label.Label LblHoraRegistro2;
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
    private javax.swing.JLabel LblNumNotaCred;
    private Label.Label LblSubTotaNetolSinIgv;
    private Label.Label LblSubTotalNetoSinIgvNC;
    private Label.Label LblSubtotalBruto;
    private Label.Label LblSubtotalNeto;
    private javax.swing.JLabel LblTituloComprobante;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JTable TblNotaCreditoDetalle;
    private TextField.JTxtLetra TxtCliente;
    private Label.Label TxtDescuento;
    private TextField.JTxtLetra TxtMoneda;
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
    private javax.swing.JCheckBox jCheckBox2;
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
    private Label.Label label13;
    private Label.Label label16;
    private Label.Label label21;
    private Label.Label label22;
    private Label.Label label23;
    private Label.Label label24;
    private Label.Label label25;
    private Label.Label label26;
    private Label.Label label29;
    private Label.Label label6;
    private Label.Label label7;
    private Label.Label label8;
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
    else{
             if(e.getActionCommand().equals(ACTION_GUARDAR))
            {
                eventoGuardar();
            }
        }
    }
}
