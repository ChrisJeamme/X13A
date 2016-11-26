package wargame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Heros extends Soldat implements ISoldat
{
	private static final long serialVersionUID = 3756416095361088558L;
	
	private final TypesH TYPE;
	
	public Heros(int x, int y)
	{
		super(x,y);
		TYPE=TypesH.getTypeHAlea();
		points_de_vie=TYPE.getPoints();
		portee_visuelle=TYPE.getPortee();
		puissance=TYPE.getPuissance();
		tir=TYPE.getTir();
		couleur=Color.YELLOW;
		
		if(TYPE == TypesH.NAIN)		{TypeImage = 10;changerImage(10);}
		if(TYPE == TypesH.HUMAIN)	{TypeImage = 8;changerImage(8);}
		if(TYPE == TypesH.ELF)		{TypeImage = 9;changerImage(9);}
		if(TYPE == TypesH.HOBBIT)	{TypeImage = 11;changerImage(11);}
	}
	
	public Heros(TypesH type,int x, int y)
	{
		super(x,y);
		TYPE=type;
		points_de_vie=TYPE.getPoints();
		portee_visuelle=TYPE.getPortee();
		puissance=TYPE.getPuissance();
		tir=TYPE.getTir();
		couleur=Color.YELLOW;

		if(TYPE == TypesH.NAIN)		{TypeImage = 10;changerImage(10);}
		if(TYPE == TypesH.HUMAIN)	{TypeImage = 8;changerImage(8);}
		if(TYPE == TypesH.ELF)		{TypeImage = 9;changerImage(9);}
		if(TYPE == TypesH.HOBBIT)	{TypeImage = 11;changerImage(11);}
		
		
	}
	
	public String toString() 
	{ 
		return "("+this.getPosition().getX()+","+this.getPosition().getY()+") "+TYPE+" ("+points_de_vie+"PV/"+TYPE.getPoints()+")"; 
	}
	
	public void seDessiner(Graphics g)
	{
		g.setColor(couleur);
		//g.fillRect(getPosition().getX()*IConfig.NB_PIX_CASE+1,getPosition().getY()*IConfig.NB_PIX_CASE+1,IConfig.NB_PIX_CASE-2,IConfig.NB_PIX_CASE-2);
		
		//System.out.println(getPosition().getX());
		
	    g.drawImage(image, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE, null);
		
		//.... On dessine un carré à la position pos de 20x20 ?
	}
	
	public void estSelection(Graphics g,Carte c){
		
		int i=this.getPosition().getX();
		int j=this.getPosition().getY();

		for (int v=-this.getPortee(); v<=this.getPortee();v++)
		{
			for (int l=0; l<=this.getPortee()-(Math.abs(v));l++)
			{
				if (j+v>=0 && j+v<IConfig.HAUTEUR_CARTE && i+l<IConfig.LARGEUR_CARTE)
				{
					if (c.caseCarte[i+l][j+v] instanceof Obstacle)
						/* Rien */ ;
					else if (c.caseCarte[i+l][j+v] instanceof Heros){
						g.setColor(Color.GREEN);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
					}
					else if (c.caseCarte[i+l][j+v] instanceof Monstre){
						g.setColor(Color.RED);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
					}
					else
					{
						g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */
						if (this.getPosition().distance(new Position(i+l,j+v))==1)
							g.fillRect((i+l)*IConfig.NB_PIX_CASE+2,(j+v)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
						g.fillRect((i+l)*IConfig.NB_PIX_CASE+2,(j+v)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
					}
					
				}
				if (j+v>=0 && j+v<IConfig.HAUTEUR_CARTE && i-l>=0)
				{
					//caseCarte[i-l][j+v].visible=true;
					if (c.caseCarte[i-l][j+v] instanceof Obstacle)
						/*Rien*/ ;
					else if (c.caseCarte[i-l][j+v] instanceof Heros){
						g.setColor(Color.GREEN);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
					}
					else if (c.caseCarte[i-l][j+v] instanceof Monstre){
						g.setColor(Color.RED);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
					}
					else if (l!=0)
					{				
						g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */
						if (this.getPosition().distance(new Position(i-l,j+v))==1)
							g.fillRect((i-l)*IConfig.NB_PIX_CASE+2,(j+v)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
						g.fillRect((i-l)*IConfig.NB_PIX_CASE+2,(j+v)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
					}
				}
			}

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2)); /* Pour augmenter la largeur des traits ? */
            g.setColor(new Color(125,0,250));
			g.drawLine(i*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE);
			g.drawLine(i*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE,i*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
			g.drawLine((i+1)*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
			g.drawLine(i*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
		}
	}
}