package wargame;

import java.awt.Color;

public class Element
{
	public java.awt.Color couleur = Color.WHITE;
	private Position pos;
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
}
