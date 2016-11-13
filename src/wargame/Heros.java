package wargame;

import java.awt.Color;

public class Heros extends Element implements IConfig
{
	public java.awt.Color couleur = Color.YELLOW;
	public boolean vide = false;
	
	public Heros(int x, int y)
	{
		super(x,y);
	}
}