package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Element
{
	public java.awt.Color couleur = Color.WHITE;
	private Position pos;
	public boolean visible = false;
	public boolean vide = true;
	
	public Element(int x, int y)
	{
		pos = new Position(x,y);
	}
	
	public Element(Position p)
	{
		pos = new Position(p.getX(),p.getY());
	}
	
	public boolean estVide()
	{
		return vide;
	}
	
	public Position getPosition()
	{
		return pos;
	}
	
	public void setPosition(Position newpos)
	{
		pos=newpos;
	}
	public void seDessiner(Graphics g){
		g.setColor(couleur);
		g.fillRect(getPosition().getX()*IConfig.NB_PIX_CASE+1,getPosition().getY()*IConfig.NB_PIX_CASE+1,IConfig.NB_PIX_CASE-2,IConfig.NB_PIX_CASE-2);
		//.... On dessine un carré à la position pos de 20x20 ?
	}
	public String toString() 
	{ 
		return "("+this.getPosition().getX()+","+this.getPosition().getY()+") "+" VIDE"; 
	}
}
