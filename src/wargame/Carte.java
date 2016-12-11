package wargame;

import java.awt.Graphics;
import java.io.Serializable;

import wargame.ISoldat.TypesH;
import wargame.ISoldat.TypesM;
import wargame.Obstacle.TypeObstacle;
/**
 * Classe de gestion de la Carte
 */
public class Carte implements ICarte, IConfig, Serializable
{
	private static final long serialVersionUID = 3933938513841317615L;
	/** Tableau des elements de la carte */
	private Element[][] caseCarte;
	/** Informations relatives aux actions */
	private String informations = "";
	/**
	 * Constructeur de la Carte
	 * Place aleatoirement les elements suivant IConfig
	 */
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
	/**
	 * Retourne l'element a la position pos
	 * @param pos Position de l'Element dans la carte
	 * @return Element dans la carte actuelle a pos
	 */
	public Element getElement(Position pos)
	{
		int x = pos.getX();
		int y = pos.getY();
		
		return caseCarte[x][y];
	}
	/**
	 * Retourne l'element a la position x,y
	 * @param x Position horizontale
	 * @param y Position verticale
	 * @return Element dans la carte actuelle a pos(x,y)
	 */
	public Element getElement(int x,int y)
	{
		return caseCarte[x][y];
	}
	
	/** Trouve aleatoirement une position vide sur la carte */
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
	
	/** Trouve une position vide choisie parmi les 8 positions adjacentes de pos */
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
		//Non trouve
		System.out.println("Il n'y a aucune case vide autour");

		return new Position(-1,-1); //On renvoi une position invalide dans l'attente de trouver une meilleure solution
	}
	/**
	 * Trouve une position vide choisie aleatoirement parmi les 8 positions adjacentes de pos
	 * @param pos Position autour de laquelle la recherche est effectuee
	 * @return Position trouvee
	 */
	public Position trouvePositionVideAlea(Position pos) // Pour IA Random
	{
		int x = pos.getX();
		int y = pos.getY();
		int rd=(int) (Math.random()*4);
		int i=0;
		while(i<2){
			if (rd==0){
				if ((new Position(x-1,y)).estValide() && estVide(new Position(x-1,y)))
					return new Position(x-1,y);
				
				else if((new Position(x+1,y)).estValide() && estVide(new Position(x+1,y)))
					return new Position(x+1,y);
				rd=1;
			}
			if (rd==1){
				if((new Position(x+1,y)).estValide() && estVide(new Position(x+1,y)))
					return new Position(x+1,y);
				else if ((new Position(x-1,y)).estValide() && estVide(new Position(x-1,y)))
					return new Position(x-1,y);
				rd=2;
			}
			if (rd==2){
				if ((new Position(x,y-1)).estValide() && estVide(new Position(x,y-1)))
					return new Position(x,y-1);
				else if((new Position(x,y+1)).estValide() && estVide(new Position(x,y+1)))
					return new Position(x,y+1);
				rd=3;
			}
			if (rd==3){
				if ((new Position(x,y+1)).estValide() && estVide(new Position(x,y+1)))
					return new Position(x,y+1);
				else if((new Position(x,y-1)).estValide() && estVide(new Position(x,y-1)))
					return new Position(x,y-1);
				rd=0;
			}
			i++;
		}
		return pos; //Bloque
	}
	
	/** Retourne le premier Heros trouve sur la carte */
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
		System.out.println("Aucun heros trouve sur la carte");
		return null;
	}
	
	/** Retourne le premier Monstre trouve sur la carte */
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
		System.out.println("Aucun monstre trouve sur la carte");
		return null;
	}
	
	/**
	 * Trouve tous les monstres de la carte 
	 * @return Tableau de Monstre
	 */
	public Monstre[] trouveToutMonstre()
	{
		Monstre[] tab = new Monstre[IConfig.NB_MONSTRES];
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
	
	/** 
	 * Trouve un heros choisi aleatoirement parmi les 8 positions adjacentes de pos 
	 * @param pos Position autour de laquelle la recherche est effectuee
	 * @return Le Heros trouve ou null
	 */
	public Heros trouveHeros(Position pos)
	{
		int x = pos.getX();
		int y = pos.getY();
		
		//Verification que le cadre des positions adjacentes ne depassent pas une limite
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
					//System.out.println("Trouve !");
					return (Heros) caseCarte[j][i];
				}
			}
		}
		
		return null;
	}
	/**
	 * Cherche une position depuis la position actuelle allant vers la position pos
	 * @param m Monstre actuel
	 * @param pos Position vers laquelle il veut aller
	 * @return La Position adjacente  m accessible dans la direction de pos
	 */
	public Position avoirPositionVers(Monstre m, Position pos){ // Toujours appele correctement > void
		int x=m.getPosition().getX();
		int y=m.getPosition().getY();
		int x2=pos.getX();
		int y2=pos.getY();
		if (x<=x2){
			if (new Position(x+1,y).estValide() && estVide(new Position(x+1,y)))
				return(new Position(x+1,y));
			else if (y>y2){
				if (new Position(x,y-1).estValide() && estVide(new Position(x,y-1)))
					return (new Position(x,y-1));
				else return (trouvePositionVideAlea(m.getPosition())); // On recule dans tous les cas
			}
		}else{
			if (new Position(x-1,y).estValide() && estVide(new Position(x-1,y)))
				return (new Position(x-1,y));
			else if (y>y2){
				if (new Position(x,y-1).estValide() && estVide(new Position(x,y-1)))
					return (new Position(x,y-1));
				return (trouvePositionVideAlea(m.getPosition())); // On recule dans tous les cas
			}
		}
		return trouvePositionVideAlea(m.getPosition());
	}
	/**
	 * Effectue le deplacement du soldat, pas de verification ! (voir actionHeros)
	 * @param pos Position vers laquelle se deplace le soldat
	 * @param soldat Soldat que l'on deplace
	 * @param affichage Permet d'avoir les informations relatives au deplacement ou non
	 * @return Vrai si deplacement effectue (possible) non sinon
	 */
	public boolean deplaceSoldat(Position pos, Soldat soldat,int affichage)
	{	
		/* affichage 1 > informations pour le Heros sinon IA, pas d'affichage */
		/* On peut se deplace que d'une case */
		if (soldat.getPortee()==1){ /* Cas du nain */
			if( Math.abs((pos.getX()-soldat.getPosition().getX()))>1 || Math.abs((pos.getY()-soldat.getPosition().getY()))>1 || pos.distance(soldat.getPosition())>2){
				if (affichage==1) informations = "Hors de portee";
				return false;
			}
		}
		else if (pos.distance(soldat.getPosition())!=1)
		{
			if (affichage==1) informations = "Hors de portee";
			return false;
		}
		int x = soldat.getPosition().getX();
		int y = soldat.getPosition().getY();
		int newX = pos.getX();
		int newY = pos.getY();
		
		if( pos.estValide() && estVide(pos) )
		{
			caseCarte[newX][newY] = soldat;	//On place le soldat  sa nouvelle position (carte)
			caseCarte[x][y] = new Element(x,y);	//On remplace la case laisse par le soldat par un Element simple
			
			soldat.seDeplace(pos); //On place le soldat  sa nouvelle position (dans son objet)
			return true;
		}
		return false;
	}
	
	/** 
	 * Renvoi true si la position pos est vide 
	 * @param pos Position de recherche
	 * @return Vrai si pos vide Faux sinon
	 */
	private boolean estVide(Position pos)
	{
		return (caseCarte[pos.getX()][pos.getY()].estVide());
	}

	/** 
	 * Rend mort le soldat si il doit l'etre
	 * @param perso Soldat concerne 
	 */
	public void mort(Soldat perso)
	{
		if (perso.getPoints()<=0)
			caseCarte[perso.getPosition().getX()][perso.getPosition().getY()] = new Element(perso.getPosition());
	}
	
	/**
	 * Fait l'action associee du Heros a la position pos vers la position pos2 (attaque, deplacement ou rien si impossible)
	 * @param pos Position du Heros
	 * @param pos2 Position (destination) de l'action
	 * @return Vrai si action effectuee, Faux sinon
	 */
	public boolean actionHeros(Position pos, Position pos2)
	{
		/* Futurement a verif si il a deja joue > false */
		
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
				//informations = "Changement de position effectue";
				return deplaceSoldat(pos2,(Soldat)caseCarte[pos.getX()][pos.getY()],1);
			}
			
			//Case non vide		
			
				//Meme position
			
			if(pos.distance(pos2) == 0)
			{
				informations = "";
				return false;
			}
			
				//Autre Heros
			
			if(getElement(pos2) instanceof Heros)
			{
				informations = "La position est un heros allie";
				return false;
			}
	
				//Ennemi
			
			if(getElement(pos2) instanceof Monstre)	
			{
				((Monstre)getElement(pos2)).setEnCombat(true);
				if (((Soldat)caseCarte[pos.getX()][pos.getY()]).getPortee()==1){ /* Cas du nain */
					if( Math.abs((pos2.getX()-pos.getX()))>1 || Math.abs((pos2.getY()-pos.getY()))>1 || pos2.distance(pos)>2){
						if (caseCarte[pos2.getX()][pos2.getY()].estVisible())
							informations= "Ennemi hors de portee";
						else informations="Hors de portee";
						return false;
					}
				}
				else if (pos.distance(pos2)>((Soldat)caseCarte[pos.getX()][pos.getY()]).getPortee())
				{
					/* Pour ne pas avoir des infos dans le brouillard de guerre */
					if (caseCarte[pos2.getX()][pos2.getY()].estVisible())
						informations= "Ennemi hors de portee";
					else informations="Hors de portee";
					return false;
				}
				informations = "Combat !";
				if (((Soldat)caseCarte[pos.getX()][pos.getY()]).combat((Soldat)caseCarte[pos2.getX()][pos2.getY()]))	//Si combat gagne
				{
					//return deplaceSoldat(pos2,(Soldat)caseCarte[pos.getX()][pos.getY()]);	//On deplace et on renvoit vrai
					/* Non on le deplace pas ?*/
					if (((Soldat)caseCarte[pos2.getX()][pos2.getY()]).getPoints()<=0)
						informations = "Monstre mort";
					mort((Soldat)caseCarte[pos2.getX()][pos2.getY()]); /* Si mort, ca le supprime */
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
				if (caseCarte[pos2.getX()][pos2.getY()].estVisible())
					informations = "La position est un obstacle";
				else informations="Hors de portee";
				return false;
			}
		
		}
		else
			informations="La position est en dehors de la carte";
		return false;		//Pas une position valide
	}
	/** Joue le reste des soldats a la fin d'un tour */
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
	
	/** Dessine la carte avec ses elements 
	 * @param g Graphics
	 */
	public void toutDessiner(Graphics g)
	{
		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++){
				caseCarte[i][j].setVisible(false);
				//caseCarte[i][j].seDessiner(g); //modif le temps de l'ia

			}
	
		
		for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
		{
			for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
			{
				if (caseCarte[i][j] instanceof Heros)
				{
					if (((Soldat)caseCarte[i][j]).getPortee()==1){ /*Cas a part pour les nains, portee en carre */
						if (j+1<IConfig.HAUTEUR_CARTE && i+1<IConfig.LARGEUR_CARTE){
							caseCarte[i+1][j+1].setVisible(true);
							caseCarte[i+1][j+1].seDessiner(g);
						}
						if (j-1>=0 && i-1>=0){
							caseCarte[i-1][j-1].setVisible(true);
							caseCarte[i-1][j-1].seDessiner(g);
						}
						if (j-1>=0 && i+1<IConfig.LARGEUR_CARTE){
							caseCarte[i+1][j-1].setVisible(true);
							caseCarte[i+1][j-1].seDessiner(g);
						}
						if (i-1>=0 && j+1<IConfig.HAUTEUR_CARTE){
							caseCarte[i-1][j+1].setVisible(true);
							caseCarte[i-1][j+1].seDessiner(g);
						}
					}
					/* Jean-Code Degueux, pas fait un carre je trouve ca plus sympa*/
					for (int k=-((Soldat)caseCarte[i][j]).getPortee(); k<=((Soldat)caseCarte[i][j]).getPortee();k++)
					{
						for (int l=0; l<=((Soldat)caseCarte[i][j]).getPortee()-(Math.abs(k));l++)
						{
							if (j+k>=0 && j+k<IConfig.HAUTEUR_CARTE && i+l<IConfig.LARGEUR_CARTE)
							{
								caseCarte[i+l][j+k].setVisible(true);
								caseCarte[i+l][j+k].seDessiner(g);
							}
							if (j+k>=0 && j+k<IConfig.HAUTEUR_CARTE && i-l>=0)
							{
								caseCarte[i-l][j+k].setVisible(true);
								caseCarte[i-l][j+k].seDessiner(g);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Verifie si le Jeu est termine
	 * @return Valeur associe
	 */
	public int verifFinJeu(){ /* 0 on continue 1 on gagne 2 on perd */
		if (trouveHeros()==null) return 2;
		else if (trouveMonstre()==null) return 1;
		else return 0;
	}
	
	/**
	 * Permet d'obtenir informations (modifie par les actions)
	 * @return La variable qui stock les informations relatives aux actions
	 */
	public String getInfo()
	{
		return informations;
	}
}
