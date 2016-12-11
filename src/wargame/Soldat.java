package wargame;
/**
 * Classe des Soldats (Heros et Monstres)
 */
public abstract class Soldat extends Element implements ISoldat, IConfig
{
	private static final long serialVersionUID = -3756577513065161512L;
	
	/**
	 * Points de vie du Soldat
	 */
	protected int points_de_vie;
	/**
	 * Portee visuelle (d'attaque) du soldat
	 */
	protected int portee_visuelle;
	/**
	 * Puissance (corps a corps) du soldat
	 */
	protected int puissance;
	/**
	 * Puissance de tir (distance) du soldat
	 */
	protected int tir;
	
	/**
	 * Constructeur d'un Soldat
	 * 
	 * @param x Position horizontale sur la map
	 * @param y Position verticale sur la map
	 */
	public Soldat(int x,int y)
	{
		super(x,y);
		vide=false;
	}
	
	/** 
	 * Renvoie les points de vie du soldat
	 * @return points_de_vie
	 */
	public int getPoints()
	{
		return points_de_vie;
	}
	/**
	 * Renvoie le Tour (non utilisé) du soldat
	 */
	public int getTour()
	{
		return this.getTour();
	}
	/**
	 * Renvoie la portée du soldat
	 * @return portee_visuelle
	 */
	public int getPortee()
	{
		return portee_visuelle;
	}
	/**
	 * Renvoie la puissance du soldat
	 * @return puissance
	 */
	public int getPuissance()
	{
		return puissance;
	}
	/**
	 * Renvoie la puissance de tir du soldat
	 * @return tir
	 */
	public int getTir()
	{
		return tir;
	}
	/**
	 * Joue le tour
	 * (Non utilisé)
	 */
	public void joueTour(int tour)
	{
		
	}
	/**
	 * 	Methode de combat en le soldat actuel et celui passé en parametre
	 * @param soldat le soldat attaqué
	 * @return vrai si le combat est gagné par le soldat courant
	 */
	public boolean combat(Soldat soldat)
	{
		if (this.getPosition().estVoisine(soldat.getPosition()))	//A coté de l'ennemi (8 cases)
		{
			soldat.points_de_vie -= Math.random()*this.puissance;
			if (soldat.points_de_vie>0){
					this.points_de_vie -= Math.random()*soldat.puissance;	
			}
		}
		else															//A distance de l'ennemi
		{
			soldat.points_de_vie -= Math.random()*this.tir;
			if (soldat.points_de_vie>0 && getPosition().distance(soldat.getPosition())<=soldat.getPortee()) // On verifie la portee de l'ennemi
				this.points_de_vie -= Math.random()*soldat.tir;
		}
		
		return (getPoints() > 0 && soldat.getPoints() <= 0);	//Vrai si combat gagné
	}
	/**
	 * Deplace le soldat a la nouvelle position
	 * @param newPos Nouvelle position de deplacement
	 */
	public void seDeplace(Position newPos)
	{
		this.setPosition(newPos);
	}

}