package view.almAlmacen.invInventarios;


import controller.almAlmacen.CCAlmacen;
import java.awt.CardLayout;
import java.net.URL;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.almAlmacen.entidad.CEAlmacen;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;
import view.cmrComercial.DlgGestionProveedor;


public class DlgReporteStockMinimo extends DialogoPadre
{
 
    public DlgReporteStockMinimo(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        LblCargando.setVisible(false);
       CbxAlmacen.setModel(CLComboBox.cargarCombo(CCAlmacen.listarAlmacenSucursal()));

        }
private void reportar(String url)
    {
    ClaseReporte oClaseReporte = new ClaseReporte();
    String oUrlC = null;
    CLReporte oCLReporte=new CLReporte();
    URL oUrlRC=getClass().getResource(url);
    oCLReporte.setUrlReport(oUrlRC.toString());
    oUrlC = oCLReporte.getUrlReport().replace('\'','/');
    if(oCLReporte!=null)
    {

   

    oClaseReporte.setUrl(oUrlC);


    int idAlmacen=((CEAlmacen)CbxAlmacen.getSelectedItem()).getIdAlmacen();
    ClaseReporte_Parametro oClaseParametroAlmacen = new ClaseReporte_Parametro();
    oClaseParametroAlmacen.setNombre("pidalmacen");
    oClaseParametroAlmacen.setPParametro(""+idAlmacen);

    ClaseReporte_Parametro oClaseParametroProv= new ClaseReporte_Parametro();
    oClaseParametroProv.setNombre("pidproveedor");
    oClaseParametroProv.setPParametro(""+idProveedor);

     ClaseReporte_Parametro oClaseParametroOpcion= new ClaseReporte_Parametro();
    oClaseParametroOpcion.setNombre("pidopcion");
    oClaseParametroOpcion.setPParametro(""+obtenerOpcion());

    Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
    oLstParametro.add(oClaseParametroAlmacen);
    oLstParametro.add(oClaseParametroProv);
    oLstParametro.add(oClaseParametroOpcion);


    oClaseReporte.setoLstParametros(oLstParametro);

    ClaseGestionDeReporte oGestionDeReporte = new ClaseGestionDeReporte();
    JRViewer oJRViewer=new JRViewer(oGestionDeReporte.ejecutarReporte(oClaseReporte));
    {
            PnlReporte.setViewportView(oJRViewer);
            oJRViewer.setVisible(true);
    }
    }
    else
    {
    JOptionPane.showMessageDialog(null,"El reporte no se encuentra");
    }

    }

private int obtenerOpcion()
    {
    if(RbnUnidadBase.isSelected())
        return 1;
    return 2;

}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        BtnGenerar = new javax.swing.JButton();
        PnlMostrar = new javax.swing.JPanel();
        PnlReporte = new javax.swing.JScrollPane();
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CbxAlmacen = new ComboBox.ComboBox();
        ChkProv = new Opcion.CheckBox();
        TxtProveedor1 = new TextField.JTxtLetra();
        RbnTodos = new javax.swing.JRadioButton();
        RbnUnidadBase = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Stock Mínimo");

        BtnGenerar.setText("Generar");
        BtnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerarActionPerformed(evt);
            }
        });

        PnlMostrar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlMostrar.setLayout(new java.awt.CardLayout());
        PnlMostrar.add(PnlReporte, "card2");

        PnlCargando.setLayout(new java.awt.BorderLayout());

        LblCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/retina_waiting.gif"))); // NOI18N
        PnlCargando.add(LblCargando, java.awt.BorderLayout.CENTER);

        PnlMostrar.add(PnlCargando, "card3");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Almacen");

        CbxAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxAlmacenActionPerformed(evt);
            }
        });

        ChkProv.setSelected(true);
        ChkProv.setText("Proveedor");
        ChkProv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkProvItemStateChanged(evt);
            }
        });

        TxtProveedor1.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtProveedor1.setTamanio(150);
        TxtProveedor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtProveedor1MouseClicked(evt);
            }
        });
        TxtProveedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtProveedor1ActionPerformed(evt);
            }
        });
        TxtProveedor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtProveedor1KeyPressed(evt);
            }
        });

        buttonGroup1.add(RbnTodos);
        RbnTodos.setFont(new java.awt.Font("Verdana", 1, 11));
        RbnTodos.setText("Todas las Unidades");
        RbnTodos.setOpaque(false);
        RbnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbnTodosActionPerformed(evt);
            }
        });

        buttonGroup1.add(RbnUnidadBase);
        RbnUnidadBase.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        RbnUnidadBase.setSelected(true);
        RbnUnidadBase.setText("Unidad Base");
        RbnUnidadBase.setOpaque(false);
        RbnUnidadBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbnUnidadBaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ChkProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RbnUnidadBase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RbnTodos)
                .addGap(14, 14, 14)
                .addComponent(BtnGenerar)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnGenerar)
                    .addComponent(jLabel3)
                    .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChkProv, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RbnUnidadBase)
                    .addComponent(RbnTodos))
                .addContainerGap(637, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(40, Short.MAX_VALUE)
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed


        BtnGenerar.setEnabled(false);
        CbxAlmacen.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {
                    

                        reportar("/view/almAlmacen/rptALMReportes/ALMRPTStockMinimo.jasper");
                         

                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    CbxAlmacen.setEnabled(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();
        

}//GEN-LAST:event_BtnGenerarActionPerformed


    private int idProveedor=0;
   private void buscarProveedor()
    {

        if(ChkProv.isSelected()){

                  DlgGestionProveedor odialogo= new DlgGestionProveedor(null, true,1,1,1);//consultar

                  odialogo.setCajaTexo(TxtProveedor1.getText());
                  odialogo.setVisible(true);
                  odialogo.setTitle("Busqueda de Proveedor");
                          if(odialogo.getoCEProveedor()!=null){
                            idProveedor=odialogo.getoCEProveedor().getIdProveedor();
                            TxtProveedor1.setText(odialogo.getoCEProveedor().getRazonSocial());
                            BtnGenerar.requestFocus();
                            }
        }
      }
    private void CbxAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxAlmacenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxAlmacenActionPerformed

    private void ChkProvItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkProvItemStateChanged
        if(!ChkProv.isSelected())
        {
            TxtProveedor1.setEnabled(false);
            idProveedor=0;
            TxtProveedor1.setText("");

        }
        else
        TxtProveedor1.setEnabled(true);
    }//GEN-LAST:event_ChkProvItemStateChanged

    private void TxtProveedor1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProveedor1KeyPressed

        if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarProveedor();

        }
}//GEN-LAST:event_TxtProveedor1KeyPressed

    private void TxtProveedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtProveedor1ActionPerformed
        
}//GEN-LAST:event_TxtProveedor1ActionPerformed

    private void TxtProveedor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtProveedor1MouseClicked
        if(evt.getClickCount()==2) {
            buscarProveedor();
        }
}//GEN-LAST:event_TxtProveedor1MouseClicked

    private void RbnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbnTodosActionPerformed
        
}//GEN-LAST:event_RbnTodosActionPerformed

    private void RbnUnidadBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbnUnidadBaseActionPerformed
        
}//GEN-LAST:event_RbnUnidadBaseActionPerformed
  
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private ComboBox.ComboBox CbxAlmacen;
    private Opcion.CheckBox ChkProv;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.JRadioButton RbnTodos;
    private javax.swing.JRadioButton RbnUnidadBase;
    private TextField.JTxtLetra TxtProveedor1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

}

