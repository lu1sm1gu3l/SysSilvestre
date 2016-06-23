/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.grlGeneral;

import javax.swing.*;	// librería swing
import java.util.*;	// para la clase Date;
import java.text.*;     // para la clase SimpleDateFormat;

public class MiRelojHora extends Thread{
	JLabel lblReloj;
     	boolean estado;
        private int tipo;
	public MiRelojHora(JLabel lblReloj)
        {
		this.lblReloj= lblReloj;
                tipo=0;
                this.estado=true;
           
	}
  
        public void detn (boolean estado)
        {
        this.estado=estado;
        }
    	public void run() {
    		while(estado)
                {
    			Date hoy= new Date();
                        SimpleDateFormat sdf;
                        if(tipo==0)
                        {
    	                sdf= new SimpleDateFormat("hh:mm:ss");
                        }
                        else
                        {
                        sdf= new SimpleDateFormat("dd/MM/yyyy");
                        }
                        lblReloj.setText(sdf.format(hoy));
			// demora de 1 segundo            
	        try { 
                     sleep(1000);
                    }
                catch (Exception ex) {}
    	}// fin del while
    }// fin del run

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}// fin de la clase MiReloj
