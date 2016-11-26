package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Monstre extends Soldat
{
	private final TypesM TYPE;
	
	public Monstre(int x, int y)
	{
		super(x,y);
		TYPE=TypesM.getTypeMAlea();
		points_de_vie=TYPE.getPoints();
		portee_visuelle=TYPE.getPortee();
		puissance=TYPE.getPuissance();
		tir=TYPE.getTir();
		couleur = Color.ORANGE;

		if(TYPE == TypesM.TROLL)
			changerImage(5);
		if(TYPE == TypesM.ORC)
			changerImage(6);
		if(TYPE == TypesM.GOBELIN)
			changerImage(7);
	}
	
	public Monstre(TypesM type,int x, int y)
	{
		super(x,y);
		TYPE=type;
		points_de_vie=TYPE.getPoints();
		portee_visuelle=TYPE.getPortee();
		puissance=TYPE.getPuissance();
		tir=TYPE.getTir();
		couleur = Color.ORANGE;
		
		if(type == TypesM.TROLL)
			changerImage(5);
		if(type == TypesM.ORC)
			changerImage(6);
		if(type == TypesM.GOBELIN)
			changerImage(7);
	}
	
	public String toString() 
	{ 
		return "("+this.getPosition().getX()+","+this.getPosition().getY()+") "+TYPE+" ("+points_de_vie+"PV/"+TYPE.getPoints()+")"; 
	}
	
	public void seDessiner(Graphics g)
	{
		g.setColor(couleur);
		//g.fillRect(getPosition().getX()*IConfig.NB_PIX_CASE+1,getPosition().getY()*IConfig.NB_PIX_CASE+1,IConfig.NB_PIX_CASE-2,IConfig.NB_PIX_CASE-2);
	    
	    g.drawImage(image, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE, null);

		//.... On dessine un carré à la position pos de 20x20 ?
	}
}
