/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.grlGeneral;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JPanel;
import java.awt.Font;

/**
 *
 * @author Morales
 */
public class CLPanelImpresion extends JPanel{

    int tipoComp=0;

    private List<CLImprimir> listImprimir;
	public void SetLstImprimir(List<CLImprimir> plistImprimir,int tipocomp)
    {
        this.listImprimir=plistImprimir;
        this.tipoComp=tipocomp;
       

    }
        /*Metodo para iniciar la graficacion*/
	public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
		update(g);
	}

    //Metodo para actualizar los graficos
	public void update(Graphics g)
    {
             Graphics2D g2=(Graphics2D)g;

        int fuente=9;
        String format="Roman";
        g2.setColor(Color.BLACK);
        g2.setFont(new Font(format,0,fuente));

        if(tipoComp==1){
        g2.drawString(listImprimir.get(0).getDescripcion(),listImprimir.get(0).getX(),listImprimir.get(0).getY());
        g2.drawString(listImprimir.get(1).getDescripcion(),listImprimir.get(1).getX(),listImprimir.get(1).getY());
        g2.drawString(listImprimir.get(2).getDescripcion(),listImprimir.get(2).getX(),listImprimir.get(2).getY());
        g2.drawString(listImprimir.get(3).getDescripcion(),listImprimir.get(3).getX(),listImprimir.get(3).getY());
        g2.drawString(listImprimir.get(4).getDescripcion(),listImprimir.get(4).getX(),listImprimir.get(4).getY());
        g2.drawString(listImprimir.get(16).getDescripcion(),listImprimir.get(16).getX(),listImprimir.get(16).getY());
        g2.drawString(listImprimir.get(5).getDescripcion(),listImprimir.get(5).getX(),listImprimir.get(5).getY());
        int y=40;
       
        for(int i=0,x=listImprimir.get(6).getY(); i<5;i++,x=x+listImprimir.get(7).getX())
                {
                  g2.drawString(listImprimir.get(8).getDescripcion(),listImprimir.get(8).getX(),x);
                  g2.drawString(listImprimir.get(9).getDescripcion(),listImprimir.get(9).getX(),x);
                  g2.drawString(listImprimir.get(10).getDescripcion(),listImprimir.get(10).getX(),x);
                  g2.drawString(listImprimir.get(11).getDescripcion(),listImprimir.get(11).getX(),x);
                  g2.drawString(listImprimir.get(12).getDescripcion(),listImprimir.get(12).getX(),x);
                  g2.drawString(listImprimir.get(13).getDescripcion(),listImprimir.get(13).getX(),x);
                }
                g2.drawString(listImprimir.get(14).getDescripcion(),listImprimir.get(14).getX(),listImprimir.get(14).getY());
                g2.drawString(listImprimir.get(15).getDescripcion(),listImprimir.get(15).getX(),listImprimir.get(15).getY());

	}
 else if(tipoComp==2)
        {

            g2.drawString(listImprimir.get(0).getDescripcion(),listImprimir.get(0).getX(),listImprimir.get(0).getY());
            g2.drawString(listImprimir.get(1).getDescripcion(),listImprimir.get(1).getX(),listImprimir.get(1).getY());
            g2.drawString(listImprimir.get(2).getDescripcion(),listImprimir.get(2).getX(),listImprimir.get(2).getY());
            g2.drawString(listImprimir.get(3).getDescripcion(),listImprimir.get(3).getX(),listImprimir.get(3).getY());
            g2.drawString(listImprimir.get(4).getDescripcion(),listImprimir.get(4).getX(),listImprimir.get(4).getY());
            g2.drawString(listImprimir.get(5).getDescripcion(),listImprimir.get(5).getX(),listImprimir.get(5).getY());
            g2.drawString(listImprimir.get(6).getDescripcion(),listImprimir.get(6).getX(),listImprimir.get(6).getY());
            int y=40;

        for(int i=0,x=listImprimir.get(7).getX(); i<5;i++,x=x+listImprimir.get(8).getX())
                {
                  g2.drawString(listImprimir.get(9).getDescripcion(),listImprimir.get(9).getX(),x);
                  g2.drawString(listImprimir.get(10).getDescripcion(),listImprimir.get(10).getX(),x);
                  g2.drawString(listImprimir.get(11).getDescripcion(),listImprimir.get(11).getX(),x);
                  g2.drawString(listImprimir.get(12).getDescripcion(),listImprimir.get(12).getX(),x);
                  g2.drawString(listImprimir.get(13).getDescripcion(),listImprimir.get(13).getX(),x);
                  g2.drawString(listImprimir.get(14).getDescripcion(),listImprimir.get(14).getX(),x);
                }
                
                g2.drawString(listImprimir.get(15).getDescripcion(),listImprimir.get(15).getX(),listImprimir.get(15).getY());
                g2.drawString(listImprimir.get(16).getDescripcion(),listImprimir.get(16).getX(),listImprimir.get(16).getY());
                g2.drawString(listImprimir.get(17).getDescripcion(),listImprimir.get(17).getX(),listImprimir.get(17).getY());
                g2.drawString(listImprimir.get(18).getDescripcion(),listImprimir.get(18).getX(),listImprimir.get(18).getY());
                g2.drawString(listImprimir.get(19).getDescripcion(),listImprimir.get(19).getX(),listImprimir.get(19).getY());
                g2.drawString(listImprimir.get(20).getDescripcion(),listImprimir.get(20).getX(),listImprimir.get(20).getY());


        }

        else if(tipoComp == 4)
        {
        g2.drawString(listImprimir.get(0).getDescripcion(),listImprimir.get(0).getX(),listImprimir.get(0).getY());
        g2.drawString(listImprimir.get(1).getDescripcion(),listImprimir.get(1).getX(),listImprimir.get(1).getY());
        g2.drawString(listImprimir.get(2).getDescripcion(),listImprimir.get(2).getX(),listImprimir.get(2).getY());
        g2.drawString(listImprimir.get(3).getDescripcion(),listImprimir.get(3).getX(),listImprimir.get(3).getY());
        g2.drawString(listImprimir.get(4).getDescripcion(),listImprimir.get(4).getX(),listImprimir.get(4).getY());
        g2.drawString(listImprimir.get(16).getDescripcion(),listImprimir.get(16).getX(),listImprimir.get(16).getY());
        g2.drawString(listImprimir.get(5).getDescripcion(),listImprimir.get(5).getX(),listImprimir.get(5).getY());
        int y=40;

        for(int i=0,x=listImprimir.get(6).getY(); i<5;i++,x=x+listImprimir.get(7).getX())
                {
                  g2.drawString(listImprimir.get(8).getDescripcion(),listImprimir.get(8).getX(),x);
                  g2.drawString(listImprimir.get(9).getDescripcion(),listImprimir.get(9).getX(),x);
                  g2.drawString(listImprimir.get(10).getDescripcion(),listImprimir.get(10).getX(),x);
                  g2.drawString(listImprimir.get(11).getDescripcion(),listImprimir.get(11).getX(),x);
                  g2.drawString(listImprimir.get(12).getDescripcion(),listImprimir.get(12).getX(),x);
                  g2.drawString(listImprimir.get(13).getDescripcion(),listImprimir.get(13).getX(),x);
                }
                g2.drawString(listImprimir.get(14).getDescripcion(),listImprimir.get(14).getX(),listImprimir.get(14).getY());
                g2.drawString(listImprimir.get(15).getDescripcion(),listImprimir.get(15).getX(),listImprimir.get(15).getY());

	}
    }
}
