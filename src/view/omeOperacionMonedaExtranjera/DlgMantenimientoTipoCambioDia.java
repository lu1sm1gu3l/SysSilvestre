/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJDialog.java
 *
 * Created on 03/09/2011, 12:32:33 AM
 */

package view.omeOperacionMonedaExtranjera;

import controller.omeOperacionMonedaExtranjera.CCCambioDia;
import controller.vtaVenta.CCMoneda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.omeOperacionMonedaExtranjera.entidad.CECambioDia;
import modelo.vtaVenta.entidad.CEMoneda;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.VerificadorDeTxt;
import view.FrmSistemaMenu;

/**
 *
 * @author Katya
 */
public class DlgMantenimientoTipoCambioDia extends DialogoPadre implements ActionListener{

int AccionABM=0; //(0) cancelado (1) nuevo (2) editar
int AccionDialogo=0; // pacccopdialogo 1= agregar desde otro formulario 0 = mantenimiento
 CECambioDia vCETipoCambioDia=new CECambioDia();
 List<CECambioDia> LstTipoCambioDia;
//  private CLValidarControles ovalidar;
    /** Creates new form NewJDialog */
    public DlgMantenimientoTipoCambioDia(java.awt.Frame parent, boolean modal,int paccioDialogo)
    {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
//        ovalidar= new CLValidarControles();
        AccionDialogo=paccioDialogo;
        CbxMoneda.setModel(CLComboBox.cargarCombo(CCMoneda.listaMonedaOperacionCambioDia()));
        TxtCompraDolar.setDocument(new VerificadorDeTxt("Numero",0,TxtCompraDolar));
        TxtCompraMN.setDocument(new VerificadorDeTxt("Numero",0,TxtCompraMN));
        TxtVentaDolar.setDocument(new VerificadorDeTxt("Numero",0,TxtVentaDolar));
        TxtVentaMN.setDocument(new VerificadorDeTxt("Numero",0,TxtVentaMN));
        DtChFecha.setDate(new Date());
     //   ovalidar.SetListCompnente(new Component[]{TxtCompraDolar,TxtCompraMN,TxtVentaDolar,TxtVentaMN,JDtChFecha}, PnlDatos);
        eventocbxMoneda();
         BtnNuevo.requestFocus();
        if(paccioDialogo==1){
        controladorDeEventosBotonesABM(1);
        AccionABM=1;}
        else{ controladorDeEventosBotonesABM(0);}

    }

    private void eventocbxMoneda()
    {
        if(AccionABM!=0){
            CEMoneda oCEMoneda =(CEMoneda)CbxMoneda.getSelectedItem();
            if(oCEMoneda!=null)
            {
                if(oCEMoneda.getId_moneda()==2)
                {
                    TxtCompraDolar.setEnabled(false);
                    TxtVentaDolar.setEnabled(false);
                }else
                {
                    TxtCompraDolar.setEnabled(true);
                    TxtVentaDolar.setEnabled(true);
                }
            }
        }

    }
    private void limpiarCajas()
    {
        TxtCompraDolar.setText("");
        TxtCompraMN.setText("");
        TxtVentaDolar.setText("");
        TxtVentaMN.setText("");
    }
    private void habilitarCajas(boolean bol)
    {
        TxtCompraDolar.setEnabled(bol);
        TxtCompraMN.setEnabled(bol);
        TxtVentaDolar.setEnabled(bol);
        TxtVentaMN.setEnabled(bol);
    }
     private void habilitarBotonesABM(boolean cNuevo,boolean cGuardar,boolean cEditar,boolean cEliminar,boolean cCancelar)
    {
        BtnNuevo.setEnabled(cNuevo);
        BtnGuardar.setEnabled(cGuardar);
        BtnEditar.setEnabled(cEditar);
        BtnEliminar.setEnabled(cEliminar);
        BtnCancelar.setEnabled(cCancelar);
    }
    private void controladorDeEventosBotonesABM(int operacion)
      { if(operacion==0) // iniio de aplicacion
          {
            habilitarCajas(false);
            habilitarBotonesABM(true,false,true,true,true);


          }
        else
          { if(operacion==1) // boton nuevo
                {

                TxtCompraMN.requestFocus();
                TblTipoCambioDia.clearSelection();
                limpiarCajas();
                habilitarBotonesABM(false,true,false,false,true);
                 habilitarCajas(true);
                TblTipoCambioDia.setEnabled(false);
                }
                else
                  {
                    if(operacion==2) // boton consultar
                    {
                        habilitarBotonesABM(false,true,true,true,true);
                    }
                  }
          }
    }


     private void cargarTabla()
    {
        limpiarTabla();

        CEMoneda oCEMoneda= (CEMoneda)CbxMoneda.getSelectedItem();
        ConvertidorFecha ocConvertidorFecha= new ConvertidorFecha();
        ocConvertidorFecha.setFecha(DtChFecha.getCalendar());
        ocConvertidorFecha.setFechaSimpleYMD();
        String pFecha=ocConvertidorFecha.getFechaSimple2();

      LstTipoCambioDia=CCCambioDia.consultarListaCambioDiasPorDescripcion(1, pFecha,oCEMoneda.getId_moneda());

        if(LstTipoCambioDia!=null)
            {

             for(int i=0;i<LstTipoCambioDia.size();i++)
                 {
                 CECambioDia oCETipoCambioDia=LstTipoCambioDia.get(i);
                     ArrayList oArrayList =new ArrayList();
                     oArrayList.add(i+1);
                     oArrayList.add(oCETipoCambioDia.getFecha());
                     oArrayList.add(oCETipoCambioDia);
                     oArrayList.add(oCETipoCambioDia.getMontoCompraMN());
                     oArrayList.add(oCETipoCambioDia.getMontoVentaMN());
                     oArrayList.add(oCETipoCambioDia.getMontoCompraDolar());
                     oArrayList.add(oCETipoCambioDia.getMontoVentaDolar());
                      ((ArrayListTableModel)TblTipoCambioDia.getModel()).addRow(oArrayList);
                }
             if(TblTipoCambioDia.getRowCount()>0){                 
                 TblTipoCambioDia.requestFocus();
                 TblTipoCambioDia.changeSelection(0,0, false, false);
                 selecionarRegistro();

             }
            }

        }

 private void selecionarRegistro()
    {

     if(TblTipoCambioDia.getSelectedRow()!=-1)
     {
         if(AccionABM!=1){
         vCETipoCambioDia=(CECambioDia)TblTipoCambioDia.getValueAt(TblTipoCambioDia.getSelectedRow(), 2);
         TxtCompraDolar.setText(""+vCETipoCambioDia.getMontoCompraDolar());
         TxtCompraMN.setText(""+vCETipoCambioDia.getMontoCompraMN());
         TxtVentaDolar.setText(""+vCETipoCambioDia.getMontoVentaDolar());
         TxtVentaMN.setText(""+vCETipoCambioDia.getMontoVentaMN());
         
         controladorDeEventosBotonesABM(0);
         }
     }
 }
     private void limpiarTabla()
{
        // ArrayListTableModel oModelo=(ArrayListTableModel)TblCambioDia.getModel();
         int size =TblTipoCambioDia.getRowCount();
         if(size!=0){
            for(int i=0;i<size;i++)
            {
              ((ArrayListTableModel)TblTipoCambioDia.getModel()).removeRow(0);
            }
         }
     }


private void eventoGuardar()
    {

//        ovalidar.validarContorles();
      //  if(ovalidar.getisVacio()){
        CEMoneda oCEMoneda =(CEMoneda)CbxMoneda.getSelectedItem();

        CECambioDia oCETipoCambioDia= new CECambioDia();
        oCETipoCambioDia.setIdMoneda(oCEMoneda.getId_moneda());
        oCETipoCambioDia.setIdCambioDia(vCETipoCambioDia.getIdCambioDia());
        oCETipoCambioDia.setMontoCompraDolar(0);
        oCETipoCambioDia.setMontoVentaDolar(0);
        if(!TxtCompraDolar.getText().equals("")){
        oCETipoCambioDia.setMontoCompraDolar(Float.parseFloat(TxtCompraDolar.getText()));
        }
        if(!TxtVentaDolar.getText().equals("")){
        oCETipoCambioDia.setMontoVentaDolar(Float.parseFloat(TxtVentaDolar.getText()));
        }
        oCETipoCambioDia.setMontoCompraMN(Float.parseFloat(TxtCompraMN.getText()));
        
        oCETipoCambioDia.setMontoVentaMN(Float.parseFloat(TxtVentaMN.getText()));
        oCETipoCambioDia.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());

         int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea Guardar?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION)
        {

            if(CCCambioDia.INSUPDCambioDia(oCETipoCambioDia,AccionABM)==1)
            {
             JOptionPane.showMessageDialog(null,"Se Guardo con exito");
             cargarTabla();
             selecionarRegistro();
             if(AccionDialogo==1&&AccionABM==1)
                 {dispose();}
             controladorDeEventosBotonesABM(0);
             AccionABM=0;
             TblTipoCambioDia.setEnabled(true);
             BtnNuevo.requestFocus();
            }
            else
            {
            JOptionPane.showMessageDialog(null,"Se Canceló la operación");
            }
        }
      //  }
 else{
         JOptionPane.showMessageDialog(null,"Completar datos");
 }
}
    private void Eventocancelar()
    {
          if(AccionABM==1||AccionABM==2)
       {
           AccionABM=0;
           controladorDeEventosBotonesABM(0);
           TblTipoCambioDia.setEnabled(true);
           TblTipoCambioDia.clearSelection();
           BtnNuevo.requestFocus();
       }
 else
       {
           dispose();
 }
    }
   private void eventoEditar()
    {
         if(TblTipoCambioDia.getSelectedRow()!=-1) {
            AccionABM=2;
            TxtCompraMN.requestFocus();
            habilitarCajas(true);
            BtnGuardar.setEnabled(true);
            BtnNuevo.setEnabled(false);


        }
            else {
                JOptionPane.showMessageDialog(null,"seleccione una fila");
            }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnlDatos = new javax.swing.JPanel();
        JLblCompraDolar = new javax.swing.JLabel();
        JLblVentaDolar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TxtCompraMN = new javax.swing.JTextField();
        TxtVentaMN = new javax.swing.JTextField();
        TxtCompraDolar = new javax.swing.JTextField();
        TxtVentaDolar = new javax.swing.JTextField();
        DtChFecha =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jLabel6 = new javax.swing.JLabel();
        JLblCompraDolar1 = new javax.swing.JLabel();
        CbxMoneda = new ComboBox.ComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblTipoCambioDia = new javax.swing.JTable();
        BtnCancelar = new BotonesABM.BtnCancelar();
        BtnEliminar = new BotonesABM.BtnEliminar();
        BtnEditar = new BotonesABM.BtnEditar();
        BtnGuardar = new BotonesABM.BtnGuardar();
        BtnNuevo = new BotonesABM.BtnNuevo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulario Tipo Cambio");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        PnlDatos.setBackground(new java.awt.Color(254, 254, 239));
        PnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 1, true), "Datos Generales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        JLblCompraDolar.setFont(new java.awt.Font("Verdana", 1, 12));
        JLblCompraDolar.setText("Compra Dolar :");

        JLblVentaDolar.setFont(new java.awt.Font("Verdana", 1, 12));
        JLblVentaDolar.setText("Venta Dolar :");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12));
        jLabel3.setText("Compra MN :");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12));
        jLabel4.setText("Venta MN : ");

        TxtCompraMN.setFont(new java.awt.Font("Verdana", 0, 12));
        TxtCompraMN.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtCompraMN.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        TxtVentaMN.setFont(new java.awt.Font("Verdana", 0, 12));
        TxtVentaMN.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtVentaMN.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        TxtCompraDolar.setFont(new java.awt.Font("Verdana", 0, 12));
        TxtCompraDolar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtCompraDolar.setText("0");
        TxtCompraDolar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        TxtVentaDolar.setFont(new java.awt.Font("Verdana", 0, 12));
        TxtVentaDolar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtVentaDolar.setText("0");
        TxtVentaDolar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        DtChFecha.setAutoscrolls(true);
        DtChFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DtChFecha.setDate(new Date());
        DtChFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaPropertyChange(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12));
        jLabel6.setText("Fecha :");

        JLblCompraDolar1.setFont(new java.awt.Font("Verdana", 1, 12));
        JLblCompraDolar1.setText("Moneda :");

        CbxMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxMonedaActionPerformed(evt);
            }
        });

        TblTipoCambioDia.setFont(new java.awt.Font("Verdana", 0, 12));
        TblTipoCambioDia.setModel( new  ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Fecha", "Moneda", "Compra MN",
                "Venta MN", "Compra Dolar", "Venta Dolar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class,
                java.lang.Object.class,java.lang.Float.class,
                java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblTipoCambioDia.setRowHeight(22);
        TblTipoCambioDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblTipoCambioDiaMouseClicked(evt);
            }
        });
        TblTipoCambioDia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblTipoCambioDiaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblTipoCambioDia);
        TblTipoCambioDia.getColumnModel().getColumn(0).setPreferredWidth(20);
        TblTipoCambioDia.getColumnModel().getColumn(1).setPreferredWidth(150);
        TblTipoCambioDia.getColumnModel().getColumn(2).setPreferredWidth(80);
        TblTipoCambioDia.getColumnModel().getColumn(3).setPreferredWidth(60);
        TblTipoCambioDia.getColumnModel().getColumn(4).setPreferredWidth(60);
        TblTipoCambioDia.getColumnModel().getColumn(5).setPreferredWidth(60);

        javax.swing.GroupLayout PnlDatosLayout = new javax.swing.GroupLayout(PnlDatos);
        PnlDatos.setLayout(PnlDatosLayout);
        PnlDatosLayout.setHorizontalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlDatosLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(13, 13, 13)
                                .addComponent(TxtVentaMN, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(JLblVentaDolar)
                                .addGap(23, 23, 23)
                                .addComponent(TxtVentaDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlDatosLayout.createSequentialGroup()
                                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(JLblCompraDolar1))
                                .addGap(4, 4, 4)
                                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CbxMoneda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TxtCompraMN, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JLblCompraDolar)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(DtChFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TxtCompraDolar, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))))
                        .addGap(66, 66, 66))
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PnlDatosLayout.setVerticalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JLblCompraDolar1)
                        .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(DtChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(TxtCompraMN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtCompraDolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JLblCompraDolar)))
                .addGap(3, 3, 3)
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4))
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(TxtVentaMN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(JLblVentaDolar))
                    .addComponent(TxtVentaDolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );

        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });

        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(343, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(PnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(91, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        Eventocancelar();
}//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea Eliminar?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {

            if(CCCambioDia.DELCambioDia(vCETipoCambioDia.getIdCambioDia())==1) {
                JOptionPane.showMessageDialog(null,"Se Eliminó con exito");
                AccionABM=0;
                cargarTabla();
                controladorDeEventosBotonesABM(0);
                TblTipoCambioDia.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null,"Se Canceló la operación");
            }
        }
}//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        eventoEditar();
}//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        eventoGuardar();
}//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        AccionABM=1;//(1)nuevo
        limpiarCajas();
        
        controladorDeEventosBotonesABM(1);
        eventocbxMoneda();
}//GEN-LAST:event_BtnNuevoActionPerformed

    int cont=0;
    private void DtChFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaPropertyChange
        //  jTextField2.setText(oFecha.ObtenerFecha(jDateChooser1));

        CEMoneda oCEMoneda = (CEMoneda) CbxMoneda.getSelectedItem();
        if(oCEMoneda!=null) {

            if(!iscerrando){

            cargarTabla();
            
        }
           
        }
        
    }//GEN-LAST:event_DtChFechaPropertyChange

    private void CbxMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxMonedaActionPerformed
        eventocbxMoneda();
        cargarTabla();
    }//GEN-LAST:event_CbxMonedaActionPerformed

    private void TblTipoCambioDiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblTipoCambioDiaKeyReleased

        if(evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_ENTER||evt.getKeyCode()==evt.VK_UP) 
        {
            selecionarRegistro();
        }
    }//GEN-LAST:event_TblTipoCambioDiaKeyReleased

    private void TblTipoCambioDiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblTipoCambioDiaMouseClicked
        selecionarRegistro();
    }//GEN-LAST:event_TblTipoCambioDiaMouseClicked

    boolean iscerrando=false;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        iscerrando=true;
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BotonesABM.BtnCancelar BtnCancelar;
    private BotonesABM.BtnEditar BtnEditar;
    private BotonesABM.BtnEliminar BtnEliminar;
    private BotonesABM.BtnGuardar BtnGuardar;
    private BotonesABM.BtnNuevo BtnNuevo;
    private ComboBox.ComboBox CbxMoneda;
    private com.toedter.calendar.JDateChooser DtChFecha;
    private javax.swing.JLabel JLblCompraDolar;
    private javax.swing.JLabel JLblCompraDolar1;
    private javax.swing.JLabel JLblVentaDolar;
    private javax.swing.JPanel PnlDatos;
    private javax.swing.JTable TblTipoCambioDia;
    private javax.swing.JTextField TxtCompraDolar;
    private javax.swing.JTextField TxtCompraMN;
    private javax.swing.JTextField TxtVentaDolar;
    private javax.swing.JTextField TxtVentaMN;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
private static final String ACTION_CLOSE = "ACTION_CLOSE";
 private static final String ACTION_GUARDAR = "ACTION_NUEVO";


    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke Guardad = KeyStroke.getKeyStroke(KeyEvent.VK_G,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_GUARDAR, Guardad, JComponent.WHEN_IN_FOCUSED_WINDOW);

        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            Eventocancelar();return;
        }
        if(e.getActionCommand().equals(ACTION_GUARDAR))
        {
            if(BtnGuardar.isEnabled()){
              eventoGuardar();
              return;}
        }

    }
}
