package wargame;

import java.awt.Graphics;

import wargame.ISoldat.TypesH;
import wargame.Obstacle.TypeObstacle;

public class Carte implements ICarte, IConfig
{
	public Element[][] caseCarte;

	public Carte()
	{
		System.out.println("TEST");
		
		caseCarte = new Element[IConfig.LARGEUR_CARTE][IConfig.HAUTEUR_CARTE];

		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
			{
				caseCarte[i][j] = new Element(i,j);
			}
		}
		
		for(int i=0; i<25; i++)
		{
			Position p=trouvePositionVide();
			//caseCarte[p.getX()][p.getY()]=new Obstacle(TypeObstacle.getObstacleAlea(),p);
			caseCarte[p.getX()][p.getY()]=new Heros(TypesH.getTypeHAlea(),p.getX(),p.getY());
			System.out.println(caseCarte[p.getX()][p.getY()].couleur);
		}
	}
	
	public Element getElement(Position pos)
	{
		int x = pos.getX();
		int y = pos.getY();
		
		return caseCarte[x][y];
	}
	
	public Position trouvePositionVide() // Trouve al�atoirement une position vide sur la carte
	{
		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
			{
				if (caseCarte[i][j].estVide())
				{
					return new Position(i,j);
				}
			}
		}
		
		System.out.println("Aucune position vide sur la carte");
		return null;
	}
	
	public Position trouvePositionVide(Position pos) // Trouve une position vide choisie al�atoirement parmi les 8 positions adjacentes de pos
	{
		int x = pos.getX();
		int y = pos.getY();
		
		//V�rification que le cadre des positions adjacentes ne d�passent pas une limite
		int limiteHaut = (y+1>=0)? y+1 : 0;
		int limiteBas = (y-1<=IConfig.HAUTEUR_CARTE)? y-1 : IConfig.HAUTEUR_CARTE;
		int limiteGauche = (x+1>=0)? x+1 : 0;
		int limiteDroite = (x-1<=IConfig.LARGEUR_CARTE)? x-1 : IConfig.LARGEUR_CARTE;
		
		for(int i=limiteHaut; i<=limiteBas; i++)
		{
			for(int j=limiteGauche; j<=limiteDroite; j++)
			{
				if (caseCarte[i][j].estVide())
				{
					return new Position(i,j);
				}
			}
		}
		
		//Non trouv�
		System.out.println("Il n'y a aucune case vide autour");

		return new Position(-1,-1); //On renvoi une position invalide dans l'attente de trouver une meilleure solution
	}
	
	public Heros trouveHeros() // Trouve al�atoirement un h�ros sur la carte
	{
		for(int i=0; i<=IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<=IConfig.HAUTEUR_CARTE; j++)
			{
				if (caseCarte[i][j] instanceof Heros)
				{
					return (Heros) caseCarte[i][j];
				}
			}
		}
		System.out.println("Aucun h�ros trouv� sur la carte");
		return null;
	}
	
	public Heros trouveHeros(Position pos) // Trouve un h�ros choisi al�atoirement parmi les 8 positions adjacentes de pos
	{
		int x = pos.getX();
		int y = pos.getY();
		
		//V�rification que le cadre des positions adjacentes ne d�passent pas une limite
		int limiteHaut = (y+1>=0)? y+1 : 0;
		int limiteBas = (y-1<=IConfig.HAUTEUR_CARTE)? y-1 : IConfig.HAUTEUR_CARTE;
		int limiteGauche = (x+1>=0)? x+1 : 0;
		int limiteDroite = (x-1<=IConfig.LARGEUR_CARTE)? x-1 : IConfig.LARGEUR_CARTE;
		
		for(int i=limiteHaut; i<=limiteBas; i++)
		{
			for(int j=limiteGauche; j<=limiteDroite; j++)
			{
				if (caseCarte[i][j] instanceof Heros)
				{
					return (Heros) caseCarte[i][j];
				}
			}
		}
		
		return null;
	}
	
	public boolean deplaceSoldat(Position pos, Soldat soldat)
	{	
		int x = soldat.getPosition().getX();
		int y = soldat.getPosition().getY();
		int newX = pos.getX();
		int newY = pos.getY();
		
		if( pos.estValide() && estVide(pos) )
		{
			caseCarte[newX][newY] = soldat;	//On place le soldat � sa nouvelle position (carte)
			caseCarte[x][y] = new Element(x,y);	//On remplace la case laiss� par le soldat par un Element simple
			
			soldat.seDeplace(pos); //On place le soldat � sa nouvelle position (dans son objet)
			return true;
		}
		
		return false;
	}
	
	private boolean estVide(Position pos)
	{
		return (caseCarte[pos.getX()][pos.getY()].estVide());
	}

	public void mort(Soldat perso)
	{
		caseCarte[perso.getPosition().getX()][perso.getPosition().getY()]=new Element(perso.getPosition());
	}
	
	public boolean actionHeros(Position pos, Position pos2)
	{
		if (pos2.estValide())
		{
			if (estVide(pos2))
				return deplaceSoldat(pos2,(Soldat)caseCarte[pos.getX()][pos.getY()]);
			else 
			{   /* J'ai le droit sans verifier la portee ? */
				((Soldat)caseCarte[pos.getX()][pos.getY()]).combat((Soldat)caseCarte[pos2.getX()][pos2.getY()]);
				return true;
			}
		}
		else return false;
	}
	
	public void jouerSoldats(PanneauJeu pj)
	{
		
	}
	
	public void toutDessiner(Graphics g)
	{
		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
			{
				caseCarte[i][j].seDessiner(g);
			}
		}
	}
	
}
