package view.cmrComercial;
import abm.CLABM;
import abm.CLBotonesABM;
import controller.cmrComercial.CCCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.cmrComercial.entidad.CECliente;
import util.clases.cmrComercial.CLCliente;
import util.clases.grlGeneral.CLMgs;
import util.clases.grlGeneral.DialogoPadre;

public class DlgGestionCliente extends DialogoPadre implements ActionListener {
private CECliente CECliente;
private int OpcionDialogo=0;// 0=amb 1=busqueda
private int IdEstado;// 1=habilitado
CLBotonesABM oCLBoton;
CLCliente oCLogica;
int acc=1;
 
    public DlgGestionCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
         oCLogica=new CLCliente(TblListaCliente);
       oCLogica.armarModelo();
        TxtCliente.requestFocus();
        
      }
    public DlgGestionCliente(java.awt.Frame parent, boolean modal,int Opcion,int TipoCliente,int Estado){
        super(parent, modal);
        initComponents();
        oCLogica=new CLCliente(TblListaCliente);
        oCLogica.armarModelo();
        OpcionDialogo=Opcion;
        // IdEstado=Estado;       
        this.setLocationRelativeTo(null);
        TxtCliente.requestFocus();
        
        oCLBoton = new CLBotonesABM();
        if(OpcionDialogo==0)
        {
         opcionDialogo(true);
         buscarCliente();
        }
        else
        {
            opcionDialogo(false);
        }
  
       
     }
    public void setCajaTexo(String texto)
    {
        TxtCliente.setText(texto);
        buscarCliente();
    }
  /*  public void cargarUtilidades()
    {
        
      //  oCLBoton.setBotones(btnNuevo, btnSeleccionar, btnDardeBaja, btnEditar,
                      //       null, null);
                           
        
        if(OpcionDialogo==0)
             {
         oCLogica.llenarDatosATabla(TblListaCliente,CCCliente.ListaABMCliente(IdTipoCliente,null));
           
             }
            else
            {
         oCLogica.llenarDatosATabla(TblListaCliente,CCCliente.ListarCNSCliente(IdTipoCliente,IdEstado, null));
          
            }
       *
    }*/

      private void opcionDialogo(boolean  p)
      {
          btnEditar.setVisible(p);
       //   btnNuevo.setVisible(p);
          btnDardeBaja.setVisible(p);
      }

      public CECliente getoCECliente() {
        return CECliente;
     }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtgrDatos = new javax.swing.ButtonGroup();
        PnlDatos = new javax.swing.JPanel();
        rbtDNIOpcion = new javax.swing.JRadioButton();
        rbtApellidoNombreOpcion = new javax.swing.JRadioButton();
        TxtCliente = new TextField.JTxtNinguno();
        PnBotonesABM = new javax.swing.JPanel();
        btnDardeBaja = new javax.swing.JButton();
        btnEditar = new BotonesABM.BtnEditar();
        btnNuevo = new BotonesABM.BtnNuevo();
        btnAceptar1 = new BotonesABM.BtnAceptar();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblListaCliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        PnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "[ DATOS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        BtgrDatos.add(rbtDNIOpcion);
        rbtDNIOpcion.setFont(new java.awt.Font("Verdana", 1, 12));
        rbtDNIOpcion.setText("DNI");
        rbtDNIOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtDNIOpcionActionPerformed(evt);
            }
        });

        BtgrDatos.add(rbtApellidoNombreOpcion);
        rbtApellidoNombreOpcion.setFont(new java.awt.Font("Verdana", 1, 12));
        rbtApellidoNombreOpcion.setSelected(true);
        rbtApellidoNombreOpcion.setText("Apellidos y Nombres");
        rbtApellidoNombreOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtApellidoNombreOpcionActionPerformed(evt);
            }
        });

        TxtCliente.setTamanio(250);
        TxtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtClienteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout PnlDatosLayout = new javax.swing.GroupLayout(PnlDatos);
        PnlDatos.setLayout(PnlDatosLayout);
        PnlDatosLayout.setHorizontalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addComponent(rbtApellidoNombreOpcion)
                        .addGap(18, 18, 18)
                        .addComponent(rbtDNIOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(TxtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PnlDatosLayout.setVerticalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtApellidoNombreOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(rbtDNIOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnDardeBaja.setFont(new java.awt.Font("Verdana", 1, 12));
        btnDardeBaja.setText("<html><CENTER>Dar de<BR>Baja</html>");
        btnDardeBaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDardeBaja.setPreferredSize(new java.awt.Dimension(90, 70));
        btnDardeBaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDardeBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDardeBajaActionPerformed(evt);
            }
        });

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnBotonesABMLayout = new javax.swing.GroupLayout(PnBotonesABM);
        PnBotonesABM.setLayout(PnBotonesABMLayout);
        PnBotonesABMLayout.setHorizontalGroup(
            PnBotonesABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnBotonesABMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDardeBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PnBotonesABMLayout.setVerticalGroup(
            PnBotonesABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnBotonesABMLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(PnBotonesABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDardeBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        TblListaCliente.setFont(new java.awt.Font("Verdana", 0, 12));
        TblListaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblListaCliente.setRowHeight(22);
        TblListaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblListaClienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TblListaClienteMouseEntered(evt);
            }
        });
        TblListaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblListaClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblListaClienteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblListaCliente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PnBotonesABM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PnBotonesABM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents




    private void btnDardeBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDardeBajaActionPerformed

        if(TblListaCliente.getSelectedRow()!=-1){
        acc= CLABM.DELETE;
      int rspt= JOptionPane.showConfirmDialog(null,"Desea dar de baja al cliente seleccionado?","Aviso",
                                                    JOptionPane.YES_NO_OPTION);
        try
        {       CECliente oClienteAux= new CECliente();
                ArrayList<CECliente> olista=new ArrayList<CECliente>();
                
                if (rspt == CLMgs.OK())
                {   sacarDatosTabla();
                    oClienteAux.setIdCliente(CECliente.getIdCliente());
                   olista.add(oClienteAux);
                  int resul=CCCliente.DELCliente(olista);
                    if(resul!=0)
                    {
                    JOptionPane.showMessageDialog(null,"Operación exitosa");
                    buscarCliente();
//                    cargarUtilidades();
//                    TxtCliente.setText("");
                    acc = CLABM.INSERT;
                    }
                    else
                    {
                    CLMgs.mgsNOTransaccion(acc);
                    }
                }
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(null,"Escriba los datos correctamente");
        }
        }else
        {
             JOptionPane.showMessageDialog(null,"Seleccione una fila");
        }

}//GEN-LAST:event_btnDardeBajaActionPerformed
    private void rbtDNIOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtDNIOpcionActionPerformed
        // TODO add your handling code here:
        TxtCliente.setText("");
        TxtCliente.requestFocus();
//      cargarUtilidades();
    }//GEN-LAST:event_rbtDNIOpcionActionPerformed

    private void rbtApellidoNombreOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtApellidoNombreOpcionActionPerformed
        // TODO add your handling code here:
        TxtCliente.setText("");
         TxtCliente.requestFocus();
  //    cargarUtilidades();
    }//GEN-LAST:event_rbtApellidoNombreOpcionActionPerformed

    private void TblListaClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListaClienteMouseEntered
        // TODO add your handling code here:
}//GEN-LAST:event_TblListaClienteMouseEntered

    private void TblListaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListaClienteMouseClicked
        
        
        if (evt.getClickCount()==2) {
            SelecionarRegistro();
          //  cargarUtilidades();
        }
}//GEN-LAST:event_TblListaClienteMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       SelecionarRegistro();
//    cargarUtilidades();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
     EventoNuevo();
    //    cargarUtilidades();
    }//GEN-LAST:event_btnNuevoActionPerformed
private void EventoNuevo()
    {
    acc = CLABM.INSERT;
        dlgRegistroCliente oDialogoCliente=new dlgRegistroCliente(null,true,null);
        oDialogoCliente.setABM(1);
        oDialogoCliente.setVisible(true);
        buscarCliente();

        if(TblListaCliente.getRowCount()>0) {
            TblListaCliente.requestFocus();
            TblListaCliente.setRowSelectionInterval(0,0);
        }
}
    private void TblListaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblListaClienteKeyReleased

//        if(evt.getKeyCode()==evt.VK_UP) {
//           // buscarCliente();
//            int row=TblListaCliente.getSelectedRow();
//
//               if(row!=-1)
//               {
//                   if(row==0)
//                   {
//                       TxtCliente.requestFocus();
//                   }
//               }
//
//        }

    }//GEN-LAST:event_TblListaClienteKeyReleased

    private void TblListaClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblListaClienteKeyPressed

               TblListaCliente.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
        TblListaCliente.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                SelecionarRegistro();


            }
        });

        
    }//GEN-LAST:event_TblListaClienteKeyPressed

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
         SelecionarRegistro();
    }//GEN-LAST:event_btnAceptar1ActionPerformed

    private void TxtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyPressed

        if(evt.getKeyCode()==evt.VK_ENTER) {
            if(TblListaCliente.getRowCount()==0)
            {
               // EventoNuevo();
            }
        }
        
    }//GEN-LAST:event_TxtClienteKeyPressed

    private void TxtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyReleased

         if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarCliente();
        }

        if(evt.getKeyCode()==evt.VK_DOWN&&TblListaCliente.getRowCount()>0) {
            TblListaCliente.requestFocus();
            TblListaCliente.setRowSelectionInterval(0,0);
        }
         
    }//GEN-LAST:event_TxtClienteKeyReleased
    private void SelecionarRegistro()
    {
        sacarDatosTabla();
          if(TblListaCliente.getSelectedRow()!=-1){
            if(OpcionDialogo==0)
            {
               
                 dlgRegistroCliente oJDABMCliente=new dlgRegistroCliente(null, true, CECliente);
                 oJDABMCliente.setABM(2);
                  oJDABMCliente.setVisible(true);
                  buscarCliente();

            }else
            {
                
                dispose();
            }

        }
              else{

                JOptionPane.showMessageDialog(null,"Selecione una fila");

            }
    }   

   private void  sacarDatosTabla()
    {
        int fila=TblListaCliente.getSelectedRow();
             if (fila>-1) {
                   CECliente=(CECliente)TblListaCliente.getValueAt(fila ,1);
                   
               }
    }
   private void buscarCliente()
     {           
        try
        {
            String datoCliente=TxtCliente.getText().trim();
            
            
             if(rbtDNIOpcion.isSelected())
                {
                 
                ArrayList<CECliente> oLista=CCCliente.BuscarDNI(IdEstado,datoCliente);
                oCLogica.llenarDatosATabla(TblListaCliente,oLista);
                 
                }
                else
                {
                
                 ArrayList<CECliente> oLista=CCCliente.BuscarNombre(IdEstado, datoCliente);
                oCLogica.llenarDatosATabla(TblListaCliente, oLista);
                 
                }
           
         }
        catch(Exception e)
        {
            CLMgs.mgsNOTransaccion(acc);

        }
        
        }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BtgrDatos;
    private javax.swing.JPanel PnBotonesABM;
    private javax.swing.JPanel PnlDatos;
    private javax.swing.JTable TblListaCliente;
    private TextField.JTxtNinguno TxtCliente;
    private BotonesABM.BtnAceptar btnAceptar1;
    private javax.swing.JButton btnDardeBaja;
    private BotonesABM.BtnEditar btnEditar;
    private BotonesABM.BtnNuevo btnNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtApellidoNombreOpcion;
    private javax.swing.JRadioButton rbtDNIOpcion;
    // End of variables declaration//GEN-END:variables
private static final String ACTION_CLOSE = "ACTION_CLOSE";
    private static final String ACTION_NUEVO = "ACTION_NUEVO";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke nuevo = KeyStroke.getKeyStroke(KeyEvent.VK_N,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_NUEVO, nuevo, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            dispose();
        }
 else{
         if(e.getActionCommand().equals(ACTION_NUEVO))
        {
            EventoNuevo();
        }
    }
    }
}
