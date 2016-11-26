package wargame;

public abstract class Soldat extends Element implements ISoldat, IConfig
{
	private static final long serialVersionUID = -3756577513065161512L;
	
	protected int points_de_vie;
	protected int portee_visuelle;
	protected int puissance;
	protected int tir;
	
	public java.awt.Color couleur;
	
	public Soldat(int x,int y)
	{
		super(x,y);
		vide=false;
	}
	
	/** Renvoi les points de vie */
	public int getPoints()
	{
		return points_de_vie;
	}
	
	public int getTour()
	{
		return this.getTour();
	}
	
	public int getPortee()
	{
		return portee_visuelle;
	}
	
	public int getPuissance()
	{
		return puissance;
	}
	
	public int getTir()
	{
		return tir;
	}
	
	public void joueTour(int tour)
	{
		
	}
	
	/** Renvoi vrai si le combat est gagné par le soldat courant */
	public boolean combat(Soldat soldat)
	{
		if (this.getPosition().estVoisine(soldat.getPosition()))	//A coté de l'ennemi
		{
			soldat.points_de_vie -= Math.random()*this.puissance;
			if (soldat.points_de_vie>0)
				this.points_de_vie -= Math.random()*soldat.puissance;			
		}
		else															//A distance de l'ennemi
		{
			soldat.points_de_vie -= Math.random()*this.tir;
			if (soldat.points_de_vie>0)
				this.points_de_vie -= Math.random()*soldat.tir;
		}
		
		return (getPoints() > 0 && soldat.getPoints() <= 0);	//Vrai si combat gagné
	}
	
	public void seDeplace(Position newPos)
	{
		this.setPosition(newPos);
	}

}