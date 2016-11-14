package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Heros extends Soldat implements IConfig,ISoldat
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
		
		System.out.println("BIP "+couleur);
	}
	public String toString() 
	{ 
		return "("+this.getPosition().getX()+","+this.getPosition().getY()+") "+TYPE+" ("+points_de_vie+"PV/"+TYPE.getPoints()+")"; 
	}
	
	public void seDessiner(Graphics g){
		g.setColor(couleur);
		g.fillRect(getPosition().getX()*IConfig.NB_PIX_CASE+1,getPosition().getY()*IConfig.NB_PIX_CASE+1,18,18);
	}
}