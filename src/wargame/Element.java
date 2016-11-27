package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Element implements IConfig, Serializable
{
	private static final long serialVersionUID = -5790544026462153601L;
	
	public java.awt.Color couleur = Color.WHITE;
	public transient Image image;
	public int TypeImage;
	private Position pos;
	public boolean visible = false;
	public boolean vide = true;
	
	public Element(int x, int y)
	{
		pos = new Position(x,y);
		TypeImage = 4;
		changerImage();
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
	
	public void changerImage()
	{
		int choixAlea;
		String nomFichier;
		
		switch(TypeImage)
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
				nomFichier = "Orc0";
				break;
			case 7: //GOBELIN
				nomFichier = "Gobelin0";
				break;
			case 8: //HUMAIN
				nomFichier = "Humain0";
				break;
			case 9: //ELF
				nomFichier = "Elf0";
				break;
			case 10: //NAIN
				nomFichier = "Nain0";
				break;
			case 11: //HOBBIT
				nomFichier = "Hobbit0";
				break;
			default:
				nomFichier = "Erreur";
				System.out.println("Horreur !");
				break;
		}
		
		try
		{
		      image = ImageIO.read(new File("img/"+nomFichier+".png"));	//On charge l'image correspondante au type de case
		}
		catch (IOException e){e.printStackTrace();} 
	}
	
	public void seDessiner(Graphics g)
	{
		g.setColor(couleur);
		//g.fillRect(getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1,IConfig.NB_PIX_CASE-2,IConfig.NB_PIX_CASE-2);
	    g.drawImage(image, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE, null);
	}
	
	public String toString() 
	{ 
		return "("+this.getPosition().getX()+","+this.getPosition().getY()+") "+" VIDE"; 
	}
	
}
