package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Element implements IConfig
{
	public java.awt.Color couleur = Color.WHITE;
	public Image image;
	private Position pos;
	public boolean visible = false;
	public boolean vide = true;
	public ImageObserver i;
	
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
				choixAlea =(int) (Math.random()*1000 % (int)NB_SPRITE_FORET-1)+1;
				nomFichier = "Foret"+choixAlea;
				break;
			case 2: //EAU
				choixAlea =(int) (Math.random()*1000 % (int)NB_SPRITE_EAU-1)+1;
				nomFichier = "Eau"+choixAlea;
				break;
			case 3: //ROCHER
				choixAlea =(int) (Math.random()*1000 % (int)NB_SPRITE_ROCHER-1)+1;
				nomFichier = "Rocher"+choixAlea;	System.out.println("Rocher numéro "+choixAlea);
				break;
			case 4: //ELEMENT
				//choixAlea =(int) (Math.random()*1000 % (int)NB_SPRITE_HERBE-1)+1;	Désactivé car il faut stocker quel type c'est à chaque fois
				nomFichier = "Herbe1"/*+choixAlea*/;
				break;
			case 5: //MONSTRE
				nomFichier = "Troll1";
				break;
			case 6: //HEROS
				nomFichier = "Heros1";
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
