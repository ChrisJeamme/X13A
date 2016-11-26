package wargame;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class LancementJeu extends JPanel implements IConfig
{
	private static final long serialVersionUID = 1877005263173998764L;
	
	Image titre;
	Image fond;
	
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
		setPreferredSize(new Dimension(IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE, IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE+70));		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
	    g.drawImage(fond, 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
	    g.drawImage(titre, (g.getClipBounds().width-768)/2, (g.getClipBounds().height-880)/2, 768, 432, null);
	}
}