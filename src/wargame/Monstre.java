package wargame;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Classe des Elements Monstres
 */
public class Monstre extends Soldat
{
	private static final long serialVersionUID = 1232616690858916564L;
	/** Type du Monstre */
	private final TypesM TYPE;
	/** Vrai si en Combat a ce tour, faux sinon : pour affichage */
	private boolean enCombat;
	/** Image du combat */
	private transient Image imageCombat;
	
	/**
	 * Constructeur Monstre avec type aleatoire
	 * @param x Position horizontale du monstre
	 * @param y Position verticale du monstre
	 */ 
	public Monstre(int x, int y)
	{
		super(x,y);
		TYPE=TypesM.getTypeMAlea();
		points_de_vie=TYPE.getPoints();
		portee_visuelle=TYPE.getPortee();
		puissance=TYPE.getPuissance();
		tir=TYPE.getTir();

		//On met l'image correspondate au type d'ennemi
		
		if(TYPE == TypesM.TROLL)		{typeImage = 5;changerImage();}	//Et on stock cet id (pour recharger l'image avec chargement partie)
		if(TYPE == TypesM.ORC)			{typeImage = 6;changerImage();}
		if(TYPE == TypesM.GOBELIN)		{typeImage = 7;changerImage();}
	}
	
	/**
	 *  Constructeur Monstre avec type choisi
	 * @param type Type du monstre
	 * @param x Position horizontale du monstre
	 * @param y Position verticale du monstre
	 */
	public Monstre(TypesM type,int x, int y)
	{
		super(x,y);
		TYPE=type;
		points_de_vie=TYPE.getPoints();
		portee_visuelle=TYPE.getPortee();
		puissance=TYPE.getPuissance();
		tir=TYPE.getTir();
		
		if(TYPE == TypesM.TROLL)		{typeImage = 5;changerImage();}
		if(TYPE == TypesM.ORC)			{typeImage = 6;changerImage();}
		if(TYPE == TypesM.GOBELIN)		{typeImage = 7;changerImage();}
	}
	/** Redfinition de toString pour Monstre */
	public String toString() 
	{ 
		return "("+this.getPosition().getX()+","+this.getPosition().getY()+") "+TYPE+" ("+points_de_vie+"PV/"+TYPE.getPoints()+")"; 
	}
	/** 
	 * Dessine l'image du Monstre  sa position et si il est en combat  ce tour
	 * @param g Graphics
	 */
	public void seDessiner(Graphics g)
	{
	    g.drawImage(image, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE, null);
	    try
		{
		      imageCombat = ImageIO.read(new File("img/combat.png"));	//On charge l'image correspondante au type de case
		}
		catch (IOException e){e.printStackTrace();} 
	    if (enCombat)
	    	g.drawImage(imageCombat, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE, null);

	}
	/** Heal le monstre */
	public void heal(){ //Tour non joue
		if (points_de_vie>TYPE.getPoints()-3)
			points_de_vie=TYPE.getPoints();
		else points_de_vie=points_de_vie+3; //A definir de combien
	}
	/** Retourne le type du Monstre 
	 * @return Type du Monstre
	 */
	public TypesM getTYPE(){
		return TYPE;
	}
	/**
	 * Fixe la valeur de enCombat
	 * @param b Vrai si en combat, faux sinon
	 */
	public void setEnCombat(boolean b){
		enCombat=b;
	}
}
