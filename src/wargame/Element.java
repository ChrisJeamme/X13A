package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**  */
public class Element implements IConfig, Serializable
{
	private static final long serialVersionUID = -5790544026462153601L;
	
	public java.awt.Color couleur = Color.WHITE;
	public transient Image image;
	public int typeImage;
	private Position pos;
	public boolean visible = false;
	public boolean vide = true;
	
	/**
	 * Constructeur principal d'un Element avec x et y séparés
	 * @param x La coordonnée x de la position voulu
	 * @param y La coordonnée y de la position voulu
	 */
	public Element(int x, int y)
	{
		pos = new Position(x,y);
		typeImage = 4;
		changerImage();
	}
	
	/**
	 *  Constructeur d'un Element avec une position
	 *  @param p Position voulu
	 */
	public Element(Position p)
	{
		this(p.getX(),p.getY());
	}
	
	/**
	 * Evalue si l'élément est vide (N'est pas une instance de Soldat / Monstre)
	 * @return Vrai si l'élément est vide 
	 */
	public boolean estVide()
	{
		return vide;
	}
	
	
	/**
	 * @return La position de l'élement
	 */
	public Position getPosition()
	{
		return pos;
	}
	
	/**
	 * Changer la position de l'Element par newpos
	 * @param newPos Nouvelle position voulu
	 */
	public void setPosition(Position newPos)
	{
		pos=newPos;
	}
	
	/** Défini l'image stocké dans l'élément avec le numéro typeImage de l'élément */
	public void changerImage()
	{
		int choixAlea;
		String nomFichier;
		
		switch(typeImage)
		{
			case 1 : //FORET
				choixAlea =(int)(Math.random() * (NB_SPRITE_FORET));	//On choisi aléatoirement un sprite de foret
				nomFichier = "Foret"+choixAlea;
				break;
			case 2: //EAU
				choixAlea =(int)(Math.random() * (NB_SPRITE_EAU));		//On choisi aléatoirement un sprite d'eau
				nomFichier = "Eau"+choixAlea;
				break;
			case 3: //ROCHER
				choixAlea =(int)(Math.random() * (NB_SPRITE_ROCHER));		//On choisi aléatoirement un sprite de rocher
				nomFichier = "Rocher"+choixAlea;
				break;
			case 4: //ELEMENT
				nomFichier = "Herbe0";
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
				System.out.println("Erreur: Choix du type de sprite inccorrecte");
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
