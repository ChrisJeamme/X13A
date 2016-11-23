package wargame;

import java.awt.Color;
import java.awt.Graphics;

import wargame.ISoldat.TypesH;
import wargame.ISoldat.TypesM;
import wargame.Obstacle.TypeObstacle;

public class Carte implements ICarte, IConfig
{
	public Element[][] caseCarte;
	public Carte()
	{
		caseCarte = new Element[IConfig.LARGEUR_CARTE][IConfig.HAUTEUR_CARTE];

		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
			{
				caseCarte[i][j] = new Element(i,j);
			}
		}
		
		for(int i=0; i<IConfig.NB_OBSTACLES; i++)
		{
			Position p=trouvePositionVide();
			caseCarte[p.getX()][p.getY()]=new Obstacle(TypeObstacle.getObstacleAlea(),p);
		}
		for(int i=0; i<IConfig.NB_HEROS; i++)
		{
			Position p=trouvePositionVide();
			if (p.getX()<IConfig.LARGEUR_CARTE/2)
				i--;
			else
			{
				Position test=trouvePositionVide(p);
				if (test.getX()==-1 && test.getY()==-1)
					i--;
				else caseCarte[p.getX()][p.getY()]=new Heros(TypesH.getTypeHAlea(),p.getX(),p.getY());
			}
		}
		for(int i=0; i<IConfig.NB_MONSTRES; i++)
		{
			Position p=trouvePositionVide();
			if (p.getX()>IConfig.LARGEUR_CARTE/2)
				i--;
			else
			{
				Position test=trouvePositionVide(p);
				if (test.getX()==-1 && test.getY()==-1)
					i--;
				else caseCarte[p.getX()][p.getY()]=new Monstre(TypesM.getTypeMAlea(),p.getX(),p.getY());
			}
		}
		
		//caseCarte[8][8]=new Heros(TypesH.getTypeHAlea(),8,8);
	}

	public Element getElement(Position pos)
	{
		int x = pos.getX();
		int y = pos.getY();
		
		return caseCarte[x][y];
	}
	
	/** Trouve al�atoirement une position vide sur la carte */
	public Position trouvePositionVide()
	{  
		int x=(int)(Math.random()*IConfig.LARGEUR_CARTE), y=(int)(Math.random()*IConfig.HAUTEUR_CARTE);
		while (!(caseCarte[x][y].estVide()))
		{
			x=(int)(Math.random()*IConfig.LARGEUR_CARTE);
			y=(int)(Math.random()*IConfig.HAUTEUR_CARTE);
		}
		return new Position(x,y);
	}
	
	/** Trouve une position vide choisie al�atoirement parmi les 8 positions adjacentes de pos */
	public Position trouvePositionVide(Position pos)
	{
		int x = pos.getX();
		int y = pos.getY();
		
		//V�rification que le cadre des positions adjacentes ne d�passent pas une limite
		
		/* J'ai rien compris a �a mais erreur donc j'en fais une autre en attendant 
		 
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
		*/
		for(int i=x-1; i<=x+1; i++)
		{
			if (i<IConfig.LARGEUR_CARTE && i>=0)
				for(int j=y-1; j<=y+1; j++)
					if (j<IConfig.HAUTEUR_CARTE && j>=0)
						if (caseCarte[i][j].estVide())
							return new Position(i,j);
		}
		//Non trouv�
		System.out.println("Il n'y a aucune case vide autour");

		return new Position(-1,-1); //On renvoi une position invalide dans l'attente de trouver une meilleure solution
	}
	
	/** Trouve al�atoirement un h�ros sur la carte */
	public Heros trouveHeros()
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
	
	/** Trouve un h�ros choisi al�atoirement parmi les 8 positions adjacentes de pos */
	public Heros trouveHeros(Position pos)
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
	
	/** Effectue le d�placement du soldat, pas de v�rification ! (voir actionHeros) */
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
	
	/** Renvoi true si la position pos est vide */
	private boolean estVide(Position pos)
	{
		return (caseCarte[pos.getX()][pos.getY()].estVide());
	}

	/** Rend mort le soldat */
	public void mort(Soldat perso)
	{
		caseCarte[perso.getPosition().getX()][perso.getPosition().getY()] = new Element(perso.getPosition());
	}
	
	/** Demande de d�placer un h�ros en v�rifiant si obstacle / ennemi */
	public boolean actionHeros(Position pos, Position pos2)
	{
		if (pos2.estValide())	//Case dans la carte
		{
			if (estVide(pos2))	//Case vide
			{
				System.out.println("La position 2 est vide");
				return deplaceSoldat(pos2,(Soldat)caseCarte[pos.getX()][pos.getY()]);
			}
			else 				
			{   /* J'ai le droit sans verifier la portee ? */System.out.println("La position 2 est un ennemi");
				if(getElement(pos2) instanceof Soldat)	//Soldat ennemi
				{
					if (((Soldat)caseCarte[pos.getX()][pos.getY()]).combat((Soldat)caseCarte[pos2.getX()][pos2.getY()]))	//Si combat gagn�
					{
						return deplaceSoldat(pos2,(Soldat)caseCarte[pos.getX()][pos.getY()]);	//On d�place et on renvoi vrai
					}
				}
				else	//Obstacle
				{
					System.out.println("La position 2 est un obstacle");
					return false;
				}
			}
		}
		else
			System.out.println("La position 2 est en dehors de la carte");return false;		//Pas une position valide
	}
	
	public void jouerSoldats(PanneauJeu pj)
	{
		
	}
	
	/** D�ssine la carte avec ses �l�ments */
	public void toutDessiner(Graphics g)
	{
		g.setColor(new Color(50,90,100));
		g.fillRect(0,0,IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE,IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE);

		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
				caseCarte[i][j].visible=false;
		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
			{
				if (caseCarte[i][j] instanceof Heros){
					/* Jean-Code Degueux, pas fait un carr� je trouve �a plus sympa*/
					for (int k=-((Soldat)caseCarte[i][j]).getPortee(); k<=((Soldat)caseCarte[i][j]).getPortee();k++){
						for (int l=0; l<=((Soldat)caseCarte[i][j]).getPortee()-(Math.abs(k));l++){
							if (j+k>=0 && j+k<IConfig.HAUTEUR_CARTE && i+l<IConfig.LARGEUR_CARTE){
								caseCarte[i+l][j+k].visible=true;
								caseCarte[i+l][j+k].seDessiner(g);
							}
							if (j+k>=0 && j+k<IConfig.HAUTEUR_CARTE && i-l>=0){
								caseCarte[i-l][j+k].visible=true;
								caseCarte[i-l][j+k].seDessiner(g);
							}
						}
					}
				}
			}
		}
	}
	
}
