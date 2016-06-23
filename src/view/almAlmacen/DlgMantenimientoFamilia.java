/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgMantenimientoFamiliao.java
 *
 * Created on 17/07/2011, 04:31:21 PM
 */

package view.almAlmacen;

import controller.almAlmacen.CCFamilia;
import java.awt.Component;
import javax.swing.JOptionPane;
import modelo.almAlmacen.entidad.CEFamilia;
import modelo.almAlmacen.entidad.CECategoria;
import util.clases.grlGeneral.CLValidarControles;
import util.clases.grlGeneral.DialogoPadre;

/**
 *
 * @author Katya
 */
public class DlgMantenimientoFamilia extends DialogoPadre{

    private int opcionABM=1; //1= Guardar ,2 =Actuañlizar
private CECategoria oCECategoria;

private CEFamilia oCEFamilia;
    private CLValidarControles ovalidar;
    /** Creates new form DlgMantenimientoFamiliao */
    public DlgMantenimientoFamilia(java.awt.Frame parent, boolean modal,CECategoria oCECategoria,String descRubro)//nuevo
    {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.oCECategoria=oCECategoria;
         TxtCategoria.setText(oCECategoria.getDescripcion());
        TxtRubro.setText(descRubro);
        
       TxtDescripcion.requestFocus();
         
        this.opcionABM=1;
        CargarUtilidades();
    }
    public DlgMantenimientoFamilia(java.awt.Frame parent, boolean modal,CEFamilia oCEFamilia,String descCategoria,String descRubro)//actualizar
    {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.oCEFamilia=oCEFamilia;

        TxtCategoria.setText(descCategoria);
        TxtRubro.setText(descRubro);
        TxtCodigo.setText(oCEFamilia.getCodigo());
        TxtDescripcion.setText(oCEFamilia.getDescripcion());
        TxtDescripcion.requestFocus();
         
        this.opcionABM=2;
        CargarUtilidades();
    }
    private void CargarUtilidades()
    {
        ovalidar=new CLValidarControles();
      ovalidar.SetListCompnente(new Component[]{ TxtDescripcion},PnlFormulario);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnlFormulario = new javax.swing.JPanel();
        TxtCodigo = new TextField.JTxtNumero();
        TxtDescripcion = new TextField.JTxtLetra();
        label1 = new Label.Label();
        label2 = new Label.Label();
        TxtRubro = new TextField.JTxtLetra();
        label3 = new Label.Label();
        TxtCategoria = new TextField.JTxtLetra();
        label5 = new Label.Label();
        btnGuardar1 = new BotonesABM.BtnGuardar();
        btnCancelar1 = new BotonesABM.BtnCancelar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro Familia");

        PnlFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Datos Generales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TxtCodigo.setEditable(false);
        TxtCodigo.setTamanio(8);
        TxtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCodigoKeyPressed(evt);
            }
        });

        TxtDescripcion.setTamanio(50);
        TxtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtDescripcionKeyPressed(evt);
            }
        });

        label1.setForeground(new java.awt.Color(153, 0, 0));
        label1.setText("Codigo :");
        label1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        label2.setForeground(new java.awt.Color(153, 0, 0));
        label2.setText("Descripcion :");
        label2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        TxtRubro.setEditable(false);
        TxtRubro.setTamanio(50);
        TxtRubro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtRubroKeyPressed(evt);
            }
        });

        label3.setForeground(new java.awt.Color(153, 0, 0));
        label3.setText("Categoria :");
        label3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        TxtCategoria.setEditable(false);
        TxtCategoria.setTamanio(50);
        TxtCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCategoriaKeyPressed(evt);
            }
        });

        label5.setForeground(new java.awt.Color(153, 0, 0));
        label5.setText("Rubro :");
        label5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout PnlFormularioLayout = new javax.swing.GroupLayout(PnlFormulario);
        PnlFormulario.setLayout(PnlFormularioLayout);
        PnlFormularioLayout.setHorizontalGroup(
            PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtRubro, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE))
                .addContainerGap())
        );
        PnlFormularioLayout.setVerticalGroup(
            PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtRubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        btnGuardar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGuardar1KeyPressed(evt);
            }
        });

        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(305, Short.MAX_VALUE)
                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(PnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
dispose();
}//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed

        guardar();

   
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void guardar()

    {
        ovalidar.validarContorles();
        if(ovalidar.getisVacio()){

            int resul=JOptionPane.showConfirmDialog(this, "¿Esta seguro de Guardar? ", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                if (resul==JOptionPane.YES_OPTION) {


                    CEFamilia oCEFamiliaTmp=new CEFamilia();
                    oCEFamiliaTmp.setDescripcion(TxtDescripcion.getText());

                    if(opcionABM==1){

                        oCEFamiliaTmp.setIdCategoria(oCECategoria.getIdCategoria());
                        oCEFamiliaTmp.setCodigo(oCECategoria.getCodigo()+TxtCodigo.getText());

                    }else{oCEFamiliaTmp.setIdFamilia(this.oCEFamilia.getIdFamilia());}

                    


                        if(CCFamilia.ABMFamilia(opcionABM, oCEFamiliaTmp)==1){
                        //JOptionPane.showMessageDialog(null,"se Guardo Con exito");
                            dispose();
                        }else{
                        JOptionPane.showMessageDialog(null, "Operación Fallida");
                        }
            }

            } else{
            TxtCodigo.requestFocus();
            JOptionPane.showMessageDialog(null,"Verificar Datos");

            }



    }
    private void TxtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCodigoKeyPressed
        if(evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_ENTER) {
            TxtDescripcion.requestFocus();
        }

    }//GEN-LAST:event_TxtCodigoKeyPressed

    private void TxtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDescripcionKeyPressed
        if(evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_ENTER) {
            btnGuardar1.requestFocus();
        }
        if(evt.getKeyCode()==evt.VK_UP) {
            TxtCodigo.requestFocus();
        }
    }//GEN-LAST:event_TxtDescripcionKeyPressed

    private void btnGuardar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardar1KeyPressed
       if(evt.getKeyCode()==evt.VK_UP||evt.getKeyCode()==evt.VK_LEFT||evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_RIGHT) {
          TxtDescripcion.requestFocus();
      }
       if(evt.getKeyCode()==evt.VK_ENTER) {
          guardar();
      }
    }//GEN-LAST:event_btnGuardar1KeyPressed

    private void TxtRubroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtRubroKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtRubroKeyPressed

    private void TxtCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCategoriaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCategoriaKeyPressed

    
    /**
    * @param args the command line arguments
    */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlFormulario;
    private TextField.JTxtLetra TxtCategoria;
    private TextField.JTxtNumero TxtCodigo;
    private TextField.JTxtLetra TxtDescripcion;
    private TextField.JTxtLetra TxtRubro;
    private BotonesABM.BtnCancelar btnCancelar1;
    private BotonesABM.BtnGuardar btnGuardar1;
    private Label.Label label1;
    private Label.Label label2;
    private Label.Label label3;
    private Label.Label label5;
    // End of variables declaration//GEN-END:variables

   

}
