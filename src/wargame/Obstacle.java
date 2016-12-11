package wargame;

import java.awt.Graphics;

/**
 * Classe des Elements Obstacle
 */

public class Obstacle extends Element implements IConfig
{
	private static final long serialVersionUID = -4591273601582318390L;
	/** Type des Obstacles possibles */
	public enum TypeObstacle
	{
		ROCHER (), FORET (), EAU ();
		public static TypeObstacle getObstacleAlea()
		{
			return values()[(int)(Math.random()*values().length)];
		}
	}
	/** Type de l'Obstacle */
	private TypeObstacle TYPE;
	/**
	 * Constructeur Obstacle
	 * @param type Type de l'Obstacle
	 * @param pos Position de l'Obstacle
	 */
	Obstacle(TypeObstacle type, Position pos) 
	{ 
		super(pos);
		TYPE = type; 
		setVide(false);
		
		if(TYPE.name() == "ROCHER")			{typeImage = 3;changerImage();}
		if(TYPE.name() == "FORET")			{typeImage = 1;changerImage();}
		if(TYPE.name() == "EAU")			{typeImage = 2;changerImage();}
	}
	/** Redefinition de toString pour Obstacle */
	public String toString() { return ""+TYPE; }
	/** Dessine l'image de l'Obstacle
	 * @param g Graphics
	 */
	public void seDessiner(Graphics g)
	{
	    g.drawImage(image, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE, null);
	}
}