package wargame;

import java.awt.Color;

public class Soldat extends Element implements ISoldat, IConfig
{
	public java.awt.Color couleur = Color.BLUE;
	public boolean vide = false;
	
	public Soldat(int x, int y)
	{
		super(x,y);
	}
	
	/*public getPoints()
	{
		
	}
	public getTour()
	public getPortee()
	public joueTour()
	public combat()
	public seDeplace()*/
}