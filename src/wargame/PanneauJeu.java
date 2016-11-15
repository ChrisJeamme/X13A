package wargame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanneauJeu extends JPanel implements ActionListener
{
	/* Ceci est dégueulasse et ne fonctionnera probablement pas */
	static final long serialVersionUID=0; 
	
	private Carte c;
	JLabel j;
	JButton b;
	int nombreFois=0;
	
	public PanneauJeu()
	{/*
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
		j = new JLabel("NoClic");	
		b = new JButton("Clic");
		setVisible(true);
		b.addActionListener(this);

	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		add(j);
		add(b);
		c = new Carte();
		
		//c.toutDessiner(g);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		nombreFois++;
		j.setText("Mes boules ! T'as cliqué "+nombreFois+" fois salope");
		repaint();
	}
}