package wargame;
import java.awt.Color;
import java.awt.Graphics;
public class Obstacle extends Element implements IConfig
{
	public enum TypeObstacle
	{
		ROCHER (COULEUR_ROCHER), FORET (COULEUR_FORET), EAU (COULEUR_EAU);
		private final Color COULEUR;
		TypeObstacle(Color couleur) { COULEUR = couleur; }
		public static TypeObstacle getObstacleAlea()
		{
			return values()[(int)(Math.random()*values().length)];
		}
	}
	private TypeObstacle TYPE;
	Obstacle(TypeObstacle type, Position pos) 
	{ 
		super(pos);
		TYPE = type; 
		vide=false;
	}
	public String toString() { return ""+TYPE; }
	public void seDessiner(Graphics g){
		g.setColor(couleur);
		g.fillRect(getPosition().getX()*IConfig.NB_PIX_CASE+1,getPosition().getY()*IConfig.NB_PIX_CASE+1,18,18);
	}
}