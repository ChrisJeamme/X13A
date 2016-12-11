package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monstre extends Soldat
{
	private static final long serialVersionUID = 1232616690858916564L;
	
	private final TypesM TYPE;
	private boolean enCombat;
	private transient Image imageCombat;
	public Monstre(int x, int y)
	{
		super(x,y);
		TYPE=TypesM.getTypeMAlea();
		points_de_vie=TYPE.getPoints();
		portee_visuelle=TYPE.getPortee();
		puissance=TYPE.getPuissance();
		tir=TYPE.getTir();
		couleur = Color.ORANGE;

		//On met l'image correspondate au type d'ennemi
		
		if(TYPE == TypesM.TROLL)		{typeImage = 5;changerImage();}	//Et on stock cet id (pour recharger l'image avec chargement partie)
		if(TYPE == TypesM.ORC)			{typeImage = 6;changerImage();}
		if(TYPE == TypesM.GOBELIN)		{typeImage = 7;changerImage();}
	}
	
	public Monstre(TypesM type,int x, int y)
	{
		super(x,y);
		TYPE=type;
		points_de_vie=TYPE.getPoints();
		portee_visuelle=TYPE.getPortee();
		puissance=TYPE.getPuissance();
		tir=TYPE.getTir();
		couleur = Color.ORANGE;
		
		if(TYPE == TypesM.TROLL)		{typeImage = 5;changerImage();}
		if(TYPE == TypesM.ORC)			{typeImage = 6;changerImage();}
		if(TYPE == TypesM.GOBELIN)		{typeImage = 7;changerImage();}
	}
	
	public String toString() 
	{ 
		return "("+this.getPosition().getX()+","+this.getPosition().getY()+") "+TYPE+" ("+points_de_vie+"PV/"+TYPE.getPoints()+")"; 
	}
	
	public void seDessiner(Graphics g)
	{
		g.setColor(couleur);
		//g.fillRect(getPosition().getX()*IConfig.NB_PIX_CASE+1,getPosition().getY()*IConfig.NB_PIX_CASE+1,IConfig.NB_PIX_CASE-2,IConfig.NB_PIX_CASE-2);
	    
	    g.drawImage(image, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE, null);
	    try
		{
		      imageCombat = ImageIO.read(new File("img/combat.png"));	//On charge l'image correspondante au type de case
		}
		catch (IOException e){e.printStackTrace();} 
	    if (enCombat)
	    	g.drawImage(imageCombat, getPosition().getX()*IConfig.NB_PIX_CASE+1, getPosition().getY()*IConfig.NB_PIX_CASE+1, NB_PIX_CASE, NB_PIX_CASE, null);

		//.... On dessine un carré à la position pos de 20x20 ?
	}
	public void heal(){ //Tour non joué
		if (points_de_vie>TYPE.getPoints()-3)
			points_de_vie=TYPE.getPoints();
		else points_de_vie=points_de_vie+3; //A définir de combien
	}
	
	public TypesM getTYPE(){
		return TYPE;
	}
	public void setEnCombat(boolean b){
		enCombat=b;
	}
}
