package wargame;

import java.awt.Graphics;
import java.io.Serializable;

import wargame.ISoldat.TypesH;
import wargame.ISoldat.TypesM;
import wargame.Obstacle.TypeObstacle;

public class Carte implements ICarte, IConfig, Serializable
{
	private static final long serialVersionUID = 3933938513841317615L;
	
	private Element[][] caseCarte; //On peut (il faut) passer private et utiliser getElement
	private String informations = "";
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
		
	}

	public Element getElement(Position pos)
	{
		int x = pos.getX();
		int y = pos.getY();
		
		return caseCarte[x][y];
	}
	
	public Element getElement(int x,int y)
	{
		return caseCarte[x][y];
	}
	
	/** Trouve aléatoirement une position vide sur la carte */
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
	
	/** Trouve une position vide choisie aléatoirement parmi les 8 positions adjacentes de pos */
	public Position trouvePositionVide(Position pos)
	{
		int x = pos.getX();
		int y = pos.getY();
		
		for(int i=x-1; i<=x+1; i++)
		{
			if (i<IConfig.LARGEUR_CARTE && i>=0)
				for(int j=y-1; j<=y+1; j++)
					if (j<IConfig.HAUTEUR_CARTE && j>=0)
						if (caseCarte[i][j].estVide())
							return new Position(i,j);
		}
		//Non trouvé
		System.out.println("Il n'y a aucune case vide autour");

		return new Position(-1,-1); //On renvoi une position invalide dans l'attente de trouver une meilleure solution
	}
	
	public Position trouvePositionVideAlea(Position pos) // Pour IA Random
	{
		int x = pos.getX();
		int y = pos.getY();
		int rd=(int) (Math.random()*4);
		int i=0;
		while(i<2){
			switch(rd){
			case (0):
				if ((new Position(x-1,y)).estValide() && estVide(new Position(x-1,y)))
					return new Position(x-1,y);
				
				else if((new Position(x+1,y)).estValide() && estVide(new Position(x+1,y)))
					return new Position(x+1,y);
				rd=1;
			case(1):
				if((new Position(x+1,y)).estValide() && estVide(new Position(x+1,y)))
					return new Position(x+1,y);
				else if ((new Position(x-1,y)).estValide() && estVide(new Position(x-1,y)))
					return new Position(x-1,y);
				rd=2;
			case(2):
				if ((new Position(x,y-1)).estValide() && estVide(new Position(x,y-1)))
					return new Position(x,y-1);
				else if((new Position(x,y+1)).estValide() && estVide(new Position(x,y+1)))
					return new Position(x,y+1);
				rd=3;
			case(3):
				if ((new Position(x,y+1)).estValide() && estVide(new Position(x,y+1)))
					return new Position(x,y+1);
				else if((new Position(x,y-1)).estValide() && estVide(new Position(x,y-1)))
					return new Position(x,y-1);
				rd=1;
			}
			i++;
		}
		return pos;
	}
	
	/** Trouve aléatoirement un héros sur la carte */
	public Heros trouveHeros()
	{
		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
			{
				if (caseCarte[i][j] instanceof Heros)
				{
					return (Heros)caseCarte[i][j];
				}
			}
		}
		System.out.println("Aucun héros trouvé sur la carte");
		return null;
	}
	
	/** Trouve aléatoirement un monstre sur la carte */
	public Monstre trouveMonstre()
	{
		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
			{
				if (caseCarte[i][j] instanceof Monstre)
				{
					return (Monstre)caseCarte[i][j];
				}
			}
		}
		System.out.println("Aucun monstre trouvé sur la carte");
		return null;
	}
	
	/** Trouve tous les monstres de la carte */
	public Monstre[] trouveToutMonstre()
	{
		Monstre[] tab = new Monstre[15];
		int indice=0;
		
		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
			{
				if (caseCarte[i][j] instanceof Monstre)
				{
					tab[indice] = (Monstre)caseCarte[i][j];
					indice++;
				}
			}
		}
		return tab;
	}
	
	/** Trouve un héros choisi aléatoirement parmi les 8 positions adjacentes de pos */
	public Heros trouveHeros(Position pos)
	{
		int x = pos.getX();
		int y = pos.getY();
		
		//Vérification que le cadre des positions adjacentes ne dépassent pas une limite
		int limiteHaut = (y-1>=0)? y-1 : 0;
		int limiteBas = (y+1<=IConfig.HAUTEUR_CARTE)? y+1 : IConfig.HAUTEUR_CARTE;
		int limiteGauche = (x-1>=0)? x-1 : 0;
		int limiteDroite = (x+1<=IConfig.LARGEUR_CARTE)? x+1 : IConfig.LARGEUR_CARTE;
		
		for(int i=limiteHaut; i<limiteBas; i++)
		{
			for(int j=limiteGauche; j<limiteDroite; j++)
			{
				//System.out.println(caseCarte[i][j]);
				if (caseCarte[j][i] instanceof Heros)
				{
					//System.out.println("Trouvé !");
					return (Heros) caseCarte[j][i];
				}
			}
		}
		
		return null;
	}
	
	/** Effectue le déplacement du soldat, pas de vérification ! (voir actionHeros) */
	public boolean deplaceSoldat(Position pos, Soldat soldat,int affichage)
	{	
		/* affichage 1 > informations pour le Heros sinon IA, pas d'affichage */
		/* On peut se deplace que d'une case */
		if (soldat.getPortee()==1){ /* Cas du nain */
			if( Math.abs((pos.getX()-soldat.getPosition().getX()))>1 || Math.abs((pos.getY()-soldat.getPosition().getY()))>1 || pos.distance(soldat.getPosition())>2){
				if (affichage==1) informations = "Hors de portée";
				return false;
			}
		}
		else if (pos.distance(soldat.getPosition())!=1)
		{
			if (affichage==1) informations = "Hors de portée";
			return false;
		}
		int x = soldat.getPosition().getX();
		int y = soldat.getPosition().getY();
		int newX = pos.getX();
		int newY = pos.getY();
		
		if( pos.estValide() && estVide(pos) )
		{
			caseCarte[newX][newY] = soldat;	//On place le soldat à sa nouvelle position (carte)
			caseCarte[x][y] = new Element(x,y);	//On remplace la case laissé par le soldat par un Element simple
			
			soldat.seDeplace(pos); //On place le soldat à sa nouvelle position (dans son objet)
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
		if (perso.getPoints()<=0)
			caseCarte[perso.getPosition().getX()][perso.getPosition().getY()] = new Element(perso.getPosition());
	}
	
	/** Déplacement d'un soldat, renvoi vrai si effecuté et faux sinon (Obstacle, allié, combat perdu)*/
	public boolean actionHeros(Position pos, Position pos2)
	{
		/* Futurement a verif si il a deja joué > false */
		
		if (!(caseCarte[pos.getX()][pos.getY()] instanceof Heros))
		{
			return false;
		}
		
		if (pos2.estValide())	//Case dans la carte
		{
			//Case vide
			
			if (estVide(pos2))	
			{
				informations = "";
				//informations = "Changement de position effectué";
				return deplaceSoldat(pos2,(Soldat)caseCarte[pos.getX()][pos.getY()],1);
			}
			
			//Case non vide		
			
				//Même position
			
			if(pos.distance(pos2) == 0)
			{
				informations = "";
				return false;
			}
			
				//Autre Héros
			
			if(getElement(pos2) instanceof Heros)
			{
				informations = "La position est un héros allié";
				return false;
			}
	
				//Ennemi
			
			if(getElement(pos2) instanceof Monstre)	
			{
				if (((Soldat)caseCarte[pos.getX()][pos.getY()]).getPortee()==1){ /* Cas du nain */
					if( Math.abs((pos2.getX()-pos.getX()))>1 || Math.abs((pos2.getY()-pos.getY()))>1 || pos2.distance(pos)>2){
						if (caseCarte[pos2.getX()][pos2.getY()].visible==true)
							informations= "Ennemi hors de portee";
						else informations="Hors de portee";
						return false;
					}
				}
				else if (pos.distance(pos2)>((Soldat)caseCarte[pos.getX()][pos.getY()]).getPortee())
				{
					/* Pour ne pas avoir des infos dans le brouillard de guerre */
					if (caseCarte[pos2.getX()][pos2.getY()].visible==true)
						informations= "Ennemi hors de portee";
					else informations="Hors de portee";
					return false;
				}
				informations = "Combat !";
				if (((Soldat)caseCarte[pos.getX()][pos.getY()]).combat((Soldat)caseCarte[pos2.getX()][pos2.getY()]))	//Si combat gagné
				{
					//return deplaceSoldat(pos2,(Soldat)caseCarte[pos.getX()][pos.getY()]);	//On déplace et on renvoit vrai
					/* Non on le deplace pas ?*/
					if (((Soldat)caseCarte[pos2.getX()][pos2.getY()]).getPoints()<=0)
						informations = "Monstre mort";
					mort((Soldat)caseCarte[pos2.getX()][pos2.getY()]); /* Si mort, ça le supprime */
				}
				else 
				{
					if (((Soldat)caseCarte[pos.getX()][pos.getY()]).getPoints()<=0)
						informations = "Heros mort";
					mort((Soldat)caseCarte[pos.getX()][pos.getY()]); /*Heros mort */ 	
				}
				return true;
			}
			
			//Obstacle
			
			if(getElement(pos2) instanceof Obstacle) 
			{					
				if (caseCarte[pos2.getX()][pos2.getY()].visible==true)
					informations = "La position est un obstacle";
				else informations="Hors de portee";
				return false;
			}
		
		}
		else
			informations="La position est en dehors de la carte";
		return false;		//Pas une position valide
	}
	
	public void jouerSoldats()
	{
		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
			{
				if (caseCarte[i][j] instanceof Heros)
				{
					if (((Heros)caseCarte[i][j]).getTourJoue()==false) ((Heros)caseCarte[i][j]).heal();
					else ((Heros)caseCarte[i][j]).setTourJoue(false);
				}
			}
		}
	}
	
	/** Déssine la carte avec ses éléments */
	public void toutDessiner(Graphics g)
	{
		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++){
				//caseCarte[i][j].seDessiner(g); //modif le temps de l'ia
				caseCarte[i][j].visible=false;
			}
		
		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
			{
				if (caseCarte[i][j] instanceof Heros)
				{
					if (((Soldat)caseCarte[i][j]).getPortee()==1){ /*Cas a part pour les nains, portee en carré */
						if (j+1<IConfig.HAUTEUR_CARTE && i+1<IConfig.LARGEUR_CARTE){
							caseCarte[i+1][j+1].visible=true;
							caseCarte[i+1][j+1].seDessiner(g);
						}
						if (j-1>=0 && i-1>=0){
							caseCarte[i-1][j-1].visible=true;
							caseCarte[i-1][j-1].seDessiner(g);
						}
						if (j-1>=0 && i+1<IConfig.LARGEUR_CARTE){
							caseCarte[i+1][j-1].visible=true;
							caseCarte[i+1][j-1].seDessiner(g);
						}
						if (i-1>=0 && j+1<IConfig.HAUTEUR_CARTE){
							caseCarte[i-1][j+1].visible=true;
							caseCarte[i-1][j+1].seDessiner(g);
						}
					}
					/* Jean-Code Degueux, pas fait un carré je trouve ça plus sympa*/
					for (int k=-((Soldat)caseCarte[i][j]).getPortee(); k<=((Soldat)caseCarte[i][j]).getPortee();k++)
					{
						for (int l=0; l<=((Soldat)caseCarte[i][j]).getPortee()-(Math.abs(k));l++)
						{
							if (j+k>=0 && j+k<IConfig.HAUTEUR_CARTE && i+l<IConfig.LARGEUR_CARTE)
							{
								caseCarte[i+l][j+k].visible=true;
								caseCarte[i+l][j+k].seDessiner(g);
							}
							if (j+k>=0 && j+k<IConfig.HAUTEUR_CARTE && i-l>=0)
							{
								caseCarte[i-l][j+k].visible=true;
								caseCarte[i-l][j+k].seDessiner(g);
							}
						}
					}
				}
			}
		}
	}
	
	public int verifFinJeu(){ /* 0 on continue 1 on gagne 2 on perd */
		if (trouveHeros()==null) return 2;
		else if (trouveMonstre()==null) return 1;
		else return 0;
	}
	public String getInfo(){
		return informations;
	}
}
