package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Heros extends Soldat implements ISoldat
{
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
		
		if(TYPE == TypesH.NAIN)
			changerImage(10);
		if(TYPE == TypesH.HUMAIN)
			changerImage(8);
		if(TYPE == TypesH.ELF)
			changerImage(9);
		if(TYPE == TypesH.HOBBIT)
			changerImage(11);
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
		
		if(type == TypesH.NAIN)
			changerImage(10);
		if(type == TypesH.HUMAIN)
			changerImage(8);
		if(type == TypesH.ELF)
			changerImage(9);
		if(type == TypesH.HOBBIT)
			changerImage(11);
		
		
	}
	
	public String toString() 
	{ 
		return "("+this.getPosition().getX()+","+this.getPosition().getY()+") "+TYPE+" ("+points_de_vie+"PV/"+TYPE.getPoints()+")"; 
	}
	
	public void seDessiner(Graphics g)
	{
		g.setColor(couleur);
		//g.fillRect(getPosition().getX()*IConfig.NB_PIX_CASE+1,getPosition().getY()*IConfig.NB_PIX_CASE+1,IConfig.NB_PIX_CASE-2,IConfig.NB_PIX_CASE-2);
		
		System.out.println(getPosition().getX());
		
	    g.drawImage(image, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE, null);
		
		//.... On dessine un carré à la position pos de 20x20 ?
	}
}