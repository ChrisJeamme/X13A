package wargame;

import java.awt.Color;

import wargame.ISoldat.TypesH;
import wargame.ISoldat.TypesM;
import wargame.Obstacle.TypeObstacle;

public abstract class Soldat extends Element implements ISoldat, IConfig
{
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
	
	/** Renvoi vrai si le combat est gagn� par le soldat courant */
	public boolean combat(Soldat soldat)
	{
		if (this.getPosition().estVoisine(soldat.getPosition()))	//TODO A commenter
		{
			soldat.points_de_vie -= Math.random()*this.puissance;
			if (soldat.points_de_vie>0)
				this.points_de_vie -= Math.random()*soldat.puissance;
			/*Apres appel fonction : verifier si un des deux mort */
		}
		else															//TODO A commenter
		{
			soldat.points_de_vie -= Math.random()*this.tir;
			if (soldat.points_de_vie>0)
				this.points_de_vie -= Math.random()*soldat.tir;
			/*Apres appel fonction : verifier si un des deux mort */
		}
		
		return true; //On retourne oui si le courant a gagn�, il faudra le faire comme il faut l� c'est pour test
	}
	public void seDeplace(Position newPos)
	{
		this.setPosition(newPos);
	}

}