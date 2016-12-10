package wargame;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class LancementJeu extends JPanel implements IConfig
{
	private static final long serialVersionUID = 1877005263173998764L;
	
	/** L'image du titre y sera stocké lors de la construction */
	Image titre;
	/** L'image de fond du menu y sera stocké lors de la construction */
	Image fond;
	
	/** Constructeur qui fixe le titre et le fond et les caractéristiques principales de la fenêtre */
	public LancementJeu()
	{	
		try
		{
			titre = ImageIO.read(new File("img/titre.png"));
			fond = ImageIO.read(new File("img/menu.png"));
			
		}catch (IOException e){System.out.println("Impossible d'ouvrir l'image "+e.getMessage());e.printStackTrace();}
		
		setLayout(new BorderLayout());
		setBackground(new Color(200,200,200));
		setOpaque(true);
		setPreferredSize(new Dimension(IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE, IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE+150));		
	}
	
	/** Affiche le fond et le titre */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
	    g.drawImage(fond, 0, 0, g.getClipBounds().width, g.getClipBounds().width/16*9+150, null);
	    g.drawImage(titre, (g.getClipBounds().width-768)/2, (g.getClipBounds().height-880)/2, 768, 432, null);
	}
}