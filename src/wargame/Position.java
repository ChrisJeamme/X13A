package wargame;

import java.io.Serializable;

/**
 * Classe Position (dans la carte)
 */
public class Position implements IConfig, Serializable
{
	private static final long serialVersionUID = -4856873962311882663L;
	/** Variables de position cartésiennes */
	private int x, y;
	/**
	 * Constructeur de position
	 * @param x Position horizontale	
	 * @param y Position verticale
	 */
	Position(int x, int y) { this.x = x; this.y = y; }
	/**
	 * @return x Retourne la position horizontale
	 */
	public int getX() { return x; }
	/**
	 * @return y Retourne la position verticale
	 */
	public int getY() { return y; }
	/**
	 *  Change la position horizontale
	 * @param x Coordonnée X
	 */
	public void setX(int x) { this.x = x; }
	/**
	 * Change la position verticale
	 * @param y Coordonnée Y
	 */
	public void setY(int y) { this.y = y; }
	/**
	 * Verifie que la position est dans la carte
	 * @return vrai si oui, faux sinon
	 */
	public boolean estValide()
	{
		return !(x<0 || x>=LARGEUR_CARTE || y<0 || y>=HAUTEUR_CARTE);
	}
	/**
	 * Redefinition de toString
	 */
	public String toString() { return "("+x+","+y+")"; }
	/** 
	 * Cherche si la position courante est voisine de pos (8 cases autour)
	 * @param pos Position de recherche
	 * @return Vrai si position voisine faux sinon
	 */
	public boolean estVoisine(Position pos)
	{
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
	/**
	 * Retourne la distance entre la position courante et le parametre
	 * @param pos l'autre position
	 * @return Valeur de la distance
	 */
	public int distance(Position pos)
	{
		return (Math.abs(this.x-pos.x))+(Math.abs(this.y-pos.y));
	}
}