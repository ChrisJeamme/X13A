package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	
	public void seDessiner(Graphics g)
	{
		g.setColor(couleur);
		 try
		 {

		      Image img = ImageIO.read(new File("img/"+this.getClass().getSimpleName()+".png"));

		      g.drawImage(img, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1,IConfig.NB_PIX_CASE-2, this);

		      //Pour une image de fond

		      //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		 }
		 catch (IOException e)
		 {
		      e.printStackTrace();
		 } 
		 
		g.fillRect(getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1,IConfig.NB_PIX_CASE-2,IConfig.NB_PIX_CASE-2);
		//.... On dessine un carré à la position pos de 20x20 ?
	}
	
	public String toString() 
	{ 
		return "("+this.getPosition().getX()+","+this.getPosition().getY()+") "+" VIDE"; 
	}
}
