package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Element implements IConfig
{
	public java.awt.Color couleur = Color.WHITE;
	public Image image;
	private Position pos;
	public boolean visible = false;
	public boolean vide = true;
	
	public Element(int x, int y)
	{
		pos = new Position(x,y);
		changerImage(4);
	}
	
	public Element(Position p)
	{
		this(p.getX(),p.getY());
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
	
	public void changerImage(int typeCase)
	{
		int choixAlea;
		String nomFichier;
		
		switch(typeCase)
		{
			case 1 : //FORET
				choixAlea =(int)(Math.random() * (NB_SPRITE_FORET));
				nomFichier = "Foret"+choixAlea;
				break;
			case 2: //EAU
				choixAlea =(int)(Math.random() * (NB_SPRITE_EAU));
				nomFichier = "Eau"+choixAlea;
				break;
			case 3: //ROCHER
				choixAlea =(int)(Math.random() * (NB_SPRITE_ROCHER));
				nomFichier = "Rocher"+choixAlea;
				break;
			case 4: //ELEMENT
				nomFichier = "Herbe0"/*+choixAlea*/;
				break;
			case 5: //TROLL
				nomFichier = "Troll0";
				break;
			case 6: //ORC
				nomFichier = "Troll0";
				break;
			case 7: //GOBELIN
				nomFichier = "Troll0";
				break;
			case 8: //HUMAIN
				nomFichier = "Heros0";
				break;
			case 9: //ELF
				nomFichier = "Heros0";
				break;
			case 10: //NAIN
				nomFichier = "Nain0";
				break;
			case 11: //HOBBIT
				nomFichier = "Nain0";
				break;
			default:
				nomFichier = "Erreur";
				System.out.println("Horreur !");
				break;
		}
		
		try
		{
		      image = ImageIO.read(new File("img/"+nomFichier+".png"));
		}
		catch (IOException e){e.printStackTrace();} 
	}
	
	public void seDessiner(Graphics g)
	{
		g.setColor(couleur);
		//g.fillRect(getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1,IConfig.NB_PIX_CASE-2,IConfig.NB_PIX_CASE-2);
		
	    g.drawImage(image, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE, null);
		
		//.... On dessine un carré à la position pos de 20x20 ?
	}
	
	public String toString() 
	{ 
		return "("+this.getPosition().getX()+","+this.getPosition().getY()+") "+" VIDE"; 
	}
	
}
