package wargame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * Classe des Elements Heros
 */
public class Heros extends Soldat implements ISoldat
{
	private static final long serialVersionUID = 3756416095361088558L;
	/** Type du Heros */
	private final TypesH TYPE;
	/** Vrai: le Heros a déjà joué, Faux sinon */
	private boolean tourJoue=false;
	/**
	 * Constructeur Heros avec type aléatoire
	 * @param x Position horizontale
	 * @param y Position verticale
	 */
	public Heros(int x, int y)
	{
		super(x,y);
		TYPE=TypesH.getTypeHAlea();
		points_de_vie=TYPE.getPoints();
		portee_visuelle=TYPE.getPortee();
		puissance=TYPE.getPuissance();
		tir=TYPE.getTir();
		
		if(TYPE == TypesH.NAIN)		{typeImage = 10;changerImage();}
		if(TYPE == TypesH.HUMAIN)	{typeImage = 8;changerImage();}
		if(TYPE == TypesH.ELF)		{typeImage = 9;changerImage();}
		if(TYPE == TypesH.HOBBIT)	{typeImage = 11;changerImage();}
	}
	/**
	 * Constructeur Heros avec type choisi
	 * @param type Type du Heros
	 * @param x Position horizontale
	 * @param y Position verticale
	 */
	public Heros(TypesH type,int x, int y)
	{
		super(x,y);
		TYPE=type;
		points_de_vie=TYPE.getPoints();
		portee_visuelle=TYPE.getPortee();
		puissance=TYPE.getPuissance();
		tir=TYPE.getTir();

		if(TYPE == TypesH.NAIN)		{typeImage = 10;changerImage();}
		if(TYPE == TypesH.HUMAIN)	{typeImage = 8;changerImage();}
		if(TYPE == TypesH.ELF)		{typeImage = 9;changerImage();}
		if(TYPE == TypesH.HOBBIT)	{typeImage = 11;changerImage();}
		
		
	}
	/** Redefinition de toString() pour Heros */
	public String toString() 
	{ 
		return "("+this.getPosition().getX()+","+this.getPosition().getY()+") "+TYPE+" ("+points_de_vie+"PV/"+TYPE.getPoints()+")"; 
	}
	/**
	 * Dessine l'element (image et si il a déjà joué)
	 * @param g Graphics
	 */
	public void seDessiner(Graphics g)
	{
	    g.drawImage(image, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE, null);
		
		if (tourJoue){
			g.setColor(new Color(50,50,50,100)); /* Opacité en 4en (pas une %) */
			g.fillRect(getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE);
		}
	}
	/**
	 * Dessine la selection (affichage de la portée d'attaque, de déplacement et les héros alliés et ennemis) du Heros
	 * @param g Graphics
	 * @param c Carte actuelle
	 */
	public void estSelection(Graphics g,Carte c){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2)); /* Pour augmenter la largeur des traits ? */
        
		int i=this.getPosition().getX();
		int j=this.getPosition().getY();
		
		if (((Soldat)c.getElement(i,j)).getPortee()==1){ /*Cas a part pour les nains, portee en carré */
			if ((new Position(i+1,j+1)).estValide()){
				if (c.getElement(i+1,j+1) instanceof Obstacle)
					;
				else if (c.getElement(i+1,j+1) instanceof Heros){
					g.setColor(Color.GREEN);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
				}
				else if (c.getElement(i+1,j+1) instanceof Monstre){
					g.setColor(Color.RED);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
				}
				else{
					g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */
					g.fillRect((i+1)*IConfig.NB_PIX_CASE+2,(j+1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
					g.fillRect((i+1)*IConfig.NB_PIX_CASE+2,(j+1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
				}
			}
			if ((new Position(i-1,j-1)).estValide()){
				if (c.getElement(i-1,j-1) instanceof Obstacle)
					;
				else if (c.getElement(i-1,j-1) instanceof Heros){
					g.setColor(Color.GREEN);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
				}
				else if (c.getElement(i-1,j-1) instanceof Monstre){
					g.setColor(Color.RED);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
				}
				else{
					g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */
					
					g.fillRect((i-1)*IConfig.NB_PIX_CASE+2,(j-1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
						
					g.fillRect((i-1)*IConfig.NB_PIX_CASE+2,(j-1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
				}
			}
			if ((new Position(i+1,j-1)).estValide()){
				if (c.getElement(i+1,j-1) instanceof Obstacle)
					;
				else if (c.getElement(i+1,j-1) instanceof Heros){
					g.setColor(Color.GREEN);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
				}
				else if (c.getElement(i+1,j-1) instanceof Monstre){
					g.setColor(Color.RED);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1+1)*IConfig.NB_PIX_CASE,(j-1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE,(i+1+1)*IConfig.NB_PIX_CASE,(j-1+1)*IConfig.NB_PIX_CASE);
				}
				else{
					g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */				
					g.fillRect((i+1)*IConfig.NB_PIX_CASE+2,(j-1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
					g.fillRect((i+1)*IConfig.NB_PIX_CASE+2,(j-1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
				}
			}
			if ((new Position(i-1,j+1)).estValide()){
				if (c.getElement(i-1,j+1) instanceof Obstacle)
					;
				else if (c.getElement(i-1,j+1) instanceof Heros){
					g.setColor(Color.GREEN);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
				}
				else if (c.getElement(i-1,j+1) instanceof Monstre){
					g.setColor(Color.RED);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
					g.drawLine((i-1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE,(i-1+1)*IConfig.NB_PIX_CASE,(j+1+1)*IConfig.NB_PIX_CASE);
				}
				else{
					g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */
					g.fillRect((i-1)*IConfig.NB_PIX_CASE+2,(j+1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
					g.fillRect((i-1)*IConfig.NB_PIX_CASE+2,(j+1)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
				}
			}
		}
		
		for (int v=-this.getPortee(); v<=this.getPortee();v++)
		{
			for (int l=0; l<=this.getPortee()-(Math.abs(v));l++)
			{
				if ((new Position(i+l,j+v)).estValide())
				{
					if (c.getElement(i+l,j+v) instanceof Obstacle)
						/* Rien */ ;
					else if (c.getElement(i+l,j+v) instanceof Heros){
						g.setColor(Color.GREEN);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
					}
					else if (c.getElement(i+l,j+v) instanceof Monstre){
						g.setColor(Color.RED);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i+l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE,(i+l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
					}
					else
					{
						g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */
						if (this.getPosition().distance(new Position(i+l,j+v))==1)
							g.fillRect((i+l)*IConfig.NB_PIX_CASE+2,(j+v)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
						g.fillRect((i+l)*IConfig.NB_PIX_CASE+2,(j+v)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
					}
					
				}
				if ((new Position(i-l,j+v)).estValide())
				{
					//caseCarte[i-l][j+v].visible=true;
					if (c.getElement(i-l,j+v) instanceof Obstacle)
						/*Rien*/ ;
					else if (c.getElement(i-l,j+v) instanceof Heros){
						g.setColor(Color.GREEN);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
					}
					else if (c.getElement(i-l,j+v) instanceof Monstre){
						g.setColor(Color.RED);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l+1)*IConfig.NB_PIX_CASE,(j+v)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
						g.drawLine((i-l)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE,(i-l+1)*IConfig.NB_PIX_CASE,(j+v+1)*IConfig.NB_PIX_CASE);
					}
					else if (l!=0)
					{				
						g.setColor(new Color(255,0,0,50)); /* Opacité en 4en (pas une %) */
						if (this.getPosition().distance(new Position(i-l,j+v))==1)
							g.fillRect((i-l)*IConfig.NB_PIX_CASE+2,(j+v)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
						g.fillRect((i-l)*IConfig.NB_PIX_CASE+2,(j+v)*IConfig.NB_PIX_CASE+2,IConfig.NB_PIX_CASE-1,IConfig.NB_PIX_CASE-1);
					}
				}
			}
		    g.setColor(new Color(125,0,250));
			g.drawLine(i*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE);
			g.drawLine(i*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE,i*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
			g.drawLine((i+1)*IConfig.NB_PIX_CASE,j*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
			g.drawLine(i*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE,(i+1)*IConfig.NB_PIX_CASE,(j+1)*IConfig.NB_PIX_CASE);
		}
	}
	/**
	 * Fixe TourJoue 
	 * @param ajoue vrai: a joué, faux sinon
	 */
	public void setTourJoue(boolean ajoue){
		tourJoue=ajoue;
	}
	/**
	 * Retourne la valeur de tourJoue
	 * @return tourJoue
	 */
	public boolean getTourJoue(){
		return tourJoue;
	}
	/**
	 * Heal le Heros
	 */
	public void heal(){ //Tour non joué
		if (points_de_vie>TYPE.getPoints()-3)
			points_de_vie=TYPE.getPoints();
		else points_de_vie=points_de_vie+3; //A définir de combien
	}
}