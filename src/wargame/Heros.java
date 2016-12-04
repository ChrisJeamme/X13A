package wargame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Heros extends Soldat implements ISoldat
{
	private static final long serialVersionUID = 3756416095361088558L;
	
	private final TypesH TYPE;
	private boolean tourJoue=false;
	
	public Heros(int x, int y)
	{
		super(x,y);
		TYPE=TypesH.getTypeHAlea();
		points_de_vie=TYPE.getPoints();
		portee_visuelle=TYPE.getPortee();
		puissance=TYPE.getPuissance();
		tir=TYPE.getTir();
		couleur=Color.YELLOW;
		
		if(TYPE == TypesH.NAIN)		{typeImage = 10;changerImage();}
		if(TYPE == TypesH.HUMAIN)	{typeImage = 8;changerImage();}
		if(TYPE == TypesH.ELF)		{typeImage = 9;changerImage();}
		if(TYPE == TypesH.HOBBIT)	{typeImage = 11;changerImage();}
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

		if(TYPE == TypesH.NAIN)		{typeImage = 10;changerImage();}
		if(TYPE == TypesH.HUMAIN)	{typeImage = 8;changerImage();}
		if(TYPE == TypesH.ELF)		{typeImage = 9;changerImage();}
		if(TYPE == TypesH.HOBBIT)	{typeImage = 11;changerImage();}
		
		
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
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2)); /* Pour augmenter la largeur des traits ? */
        
		int i=this.getPosition().getX();
		int j=this.getPosition().getY();
		
		if (((Soldat)c.getElement(i,j)).getPortee()==1){ /*Cas a part pour les nains, portee en carré */
			if (j+1<IConfig.HAUTEUR_CARTE && i+1<IConfig.LARGEUR_CARTE){
				if (c.getElement(i+1,j+1) instanceof Obstacle)
					;
				else if (c.getElement(i+1,j+1) instanceof Heros){
					g.setColor(Color.GREEN);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
				}
				else if (c.getElement(i+1,j+1) instanceof Monstre){
					g.setColor(Color.RED);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
				}
				else{
					g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */
					g.fillRect((i+1)*IConfig.NB_PIX_CASE+2,(j+1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
					g.fillRect((i+1)*IConfig.NB_PIX_CASE+2,(j+1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
				}
			}
			if (j-1>=0 && i-1>=0){
				if (c.getElement(i-1,j-1) instanceof Obstacle)
					;
				else if (c.getElement(i-1,j-1) instanceof Heros){
					g.setColor(Color.GREEN);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
				}
				else if (c.getElement(i-1,j-1) instanceof Monstre){
					g.setColor(Color.RED);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
				}
				else{
					g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */
					
					g.fillRect((i-1)*IConfig.NB_PIX_CASE+2,(j-1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
						
					g.fillRect((i-1)*IConfig.NB_PIX_CASE+2,(j-1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
				}
			}
			if (j-1>=0 && i+1<IConfig.LARGEUR_CARTE){
				if (c.getElement(i+1,j-1) instanceof Obstacle)
					;
				else if (c.getElement(i+1,j-1) instanceof Heros){
					g.setColor(Color.GREEN);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
				}
				else if (c.getElement(i+1,j-1) instanceof Monstre){
					g.setColor(Color.RED);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
				}
				else{
					g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */				
					g.fillRect((i+1)*IConfig.NB_PIX_CASE+2,(j-1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
					g.fillRect((i+1)*IConfig.NB_PIX_CASE+2,(j-1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
				}
			}
			if (i-1>=0 && j+1<IConfig.HAUTEUR_CARTE){
				if (c.getElement(i-1,j+1) instanceof Obstacle)
					;
				else if (c.getElement(i-1,j+1) instanceof Heros){
					g.setColor(Color.GREEN);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
				}
				else if (c.getElement(i-1,j+1) instanceof Monstre){
					g.setColor(Color.RED);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
				}
				else{
					g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */
					g.fillRect((i-1)*IConfig.NB_PIX_CASE+2,(j+1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
					g.fillRect((i-1)*IConfig.NB_PIX_CASE+2,(j+1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
				}
			}
		}
		
		for (int v=-this.getPortee(); v<=this.getPortee();v++)
		{
			for (int l=0; l<=this.getPortee()-(Math.abs(v));l++)
			{
				if (j+v>=0 && j+v<IConfig.HAUTEUR_CARTE && i+l<IConfig.LARGEUR_CARTE)
				{
					if (c.getElement(i+l,j+v) instanceof Obstacle)
						/* Rien */ ;
					else if (c.getElement(i+l,j+v) instanceof Heros){
						g.setColor(Color.GREEN);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
					}
					else if (c.getElement(i+l,j+v) instanceof Monstre){
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
					if (c.getElement(i-l,j+v) instanceof Obstacle)
						/*Rien*/ ;
					else if (c.getElement(i-l,j+v) instanceof Heros){
						g.setColor(Color.GREEN);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
					}
					else if (c.getElement(i-l,j+v) instanceof Monstre){
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
		    g.setColor(new Color(125,0,250));
			g.drawLine(i*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE);
			g.drawLine(i*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE,i*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
			g.drawLine((i+1)*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
			g.drawLine(i*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
		}
	}

	public void setTourJoue(boolean ajoue){
		tourJoue=ajoue;
	}
	
	public boolean getTourJoue(){
		return tourJoue;
	}

	public void heal(){ //Tour non joué
		if (points_de_vie>TYPE.getPoints()-3)
			points_de_vie=TYPE.getPoints();
		else points_de_vie=points_de_vie+3; //A définir de combien
	}
}