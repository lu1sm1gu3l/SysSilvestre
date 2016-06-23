/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgMantenimientoCategoriao.java
 *
 * Created on 17/07/2011, 04:31:21 PM
 */

package view.almAlmacen;

import controller.almAlmacen.CCCategoria;
import java.awt.Component;
import javax.swing.JOptionPane;
import modelo.almAlmacen.entidad.CECategoria;
import modelo.almAlmacen.entidad.CERubro;
import util.clases.grlGeneral.CLValidarControles;
import util.clases.grlGeneral.DialogoPadre;

/**
 *
 * @author Katya
 */
public class DlgMantenimientoCategoria extends DialogoPadre{

    private int opcionABM=1; //1= Guardar ,2 =Actuañlizar
//private int IdCategoria=0;
private CERubro oCERubro;
private CECategoria oCECategoria;
    private CLValidarControles ovalidar;
    /** Creates new form DlgMantenimientoCategoriao */
    public DlgMantenimientoCategoria(java.awt.Frame parent, boolean modal,CERubro oCERubro)//nuevo
    {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.opcionABM=1;
        this.oCERubro=oCERubro;
        TxtRubro.setText(oCERubro.getDescripcion());
        TxtDescripcion.requestFocus();
         CargarUtilidades();
        
        
    }
    public DlgMantenimientoCategoria(java.awt.Frame parent, boolean modal,CECategoria oCECategoria,String DescRubro)//Actualizar
    {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.opcionABM=2;
        this.oCECategoria=oCECategoria;        
        TxtCodigo.setText(oCECategoria.getCodigo());
        TxtDescripcion.setText(oCECategoria.getDescripcion());
        TxtRubro.setText(DescRubro);
        TxtDescripcion.requestFocus();
        CargarUtilidades();
    }
private void CargarUtilidades()
    {
        ovalidar=new CLValidarControles();
      ovalidar.SetListCompnente(new Component[]{ TxtDescripcion},PnlFormulario);
     
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnlFormulario = new javax.swing.JPanel();
        TxtCodigo = new TextField.JTxtNumero();
        TxtDescripcion = new TextField.JTxtLetra();
        label1 = new Label.Label();
        label2 = new Label.Label();
        TxtRubro = new TextField.JTxtLetra();
        label3 = new Label.Label();
        btnGuardar1 = new BotonesABM.BtnGuardar();
        btnCancelar1 = new BotonesABM.BtnCancelar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Categoria");

        PnlFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Datos Generales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TxtCodigo.setEditable(false);
        TxtCodigo.setTamanio(5);
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
        label3.setText("Rubro :");
        label3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout PnlFormularioLayout = new javax.swing.GroupLayout(PnlFormulario);
        PnlFormulario.setLayout(PnlFormularioLayout);
        PnlFormularioLayout.setHorizontalGroup(
            PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlFormularioLayout.createSequentialGroup()
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(TxtRubro, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlFormularioLayout.createSequentialGroup()
                        .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                            .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42))
        );
        PnlFormularioLayout.setVerticalGroup(
            PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioLayout.createSequentialGroup()
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtRubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PnlFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(PnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
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

                    CECategoria oCECategoriaTmp=new CECategoria();
                    
                    
                    oCECategoriaTmp.setDescripcion(TxtDescripcion.getText());

   
                     
                    if(opcionABM==1){
                        
                        oCECategoriaTmp.setIdRubro(oCERubro.getIdRubro());
                        oCECategoriaTmp.setCodigo(oCERubro.getCodigo()+TxtCodigo.getText());

                    }else{oCECategoriaTmp.setIdCategoria(this.oCECategoria.getIdCategoria());}


                        if(CCCategoria.ABMCategoria(opcionABM, oCECategoriaTmp)==1){
                       // JOptionPane.showMessageDialog(null,"se Guardo Con exito");
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

    
    /**
    * @param args the command line arguments
    */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlFormulario;
    private TextField.JTxtNumero TxtCodigo;
    private TextField.JTxtLetra TxtDescripcion;
    private TextField.JTxtLetra TxtRubro;
    private BotonesABM.BtnCancelar btnCancelar1;
    private BotonesABM.BtnGuardar btnGuardar1;
    private Label.Label label1;
    private Label.Label label2;
    private Label.Label label3;
    // End of variables declaration//GEN-END:variables

  
}
