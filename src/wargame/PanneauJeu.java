package wargame;

import java.awt.*;

import javax.swing.*;

public class PanneauJeu extends JPanel
{
	/* Ceci est dégueulasse et ne fonctionnera probablement pas */
	static final long serialVersionUID=0; 
	private Carte c;
	public PanneauJeu(){/*
		setLayout(new GridLayout(IConfig.HAUTEUR_CARTE,IConfig.LARGEUR_CARTE));
		for(int i=0;i<IConfig.LARGEUR_CARTE;i++)
			for(int j=0;j<IConfig.HAUTEUR_CARTE;j++){
				JButton jb=new JButton("");
				jb.setPreferredSize(new Dimension(20,20));
				add(jb);
		}*/
		setBackground(new Color(200,200,200));
		setOpaque(true);
		setPreferredSize(new Dimension(IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE,IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE+50));

	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		c=new Carte();
		c.toutDessiner(g);
	}
}