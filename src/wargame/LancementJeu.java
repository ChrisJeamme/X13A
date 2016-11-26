package wargame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class LancementJeu extends JPanel
{
	private static final long serialVersionUID = 1877005263173998764L;
	
	public LancementJeu()
	{	
		setLayout(new BorderLayout());
		setBackground(new Color(200,200,200));
		setOpaque(true);
		setPreferredSize(new Dimension(IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE,IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE+70));		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	
	}
}