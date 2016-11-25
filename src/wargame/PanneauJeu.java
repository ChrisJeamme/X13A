package wargame;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;

public class PanneauJeu extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1877005263173998764L;
	
	private Carte c = new Carte();
	private JLabel labelInfo = new JLabel();
	private JLabel labelAlerte = new JLabel();
	
	public PanneauJeu()
	{	
		setLayout(new BorderLayout());
		
		setBackground(new Color(200,200,200));
		setOpaque(true);
		setPreferredSize(new Dimension(IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE,IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE+70));

		addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseMoved(MouseEvent e)
			{
				if (e.getX()/IConfig.NB_PIX_CASE<IConfig.LARGEUR_CARTE && e.getY()/IConfig.NB_PIX_CASE<IConfig.HAUTEUR_CARTE)	//TODO à commenter
					if(c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE].visible==true)
						labelInfo.setText(c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE].toString());
				else labelInfo.setText("");
			}
		});
		
		addMouseListener(new MouseAdapter()
		{
			Element h; //Héros selectionné
			int test;
			public void mousePressed(MouseEvent e)
			{
				//if (c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE] instanceof Heros) //La case où souris clic est un héros
				//{
					h =c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE];
					test=1;
					labelAlerte.setText("Mouvement d'un héros");
				//}
			}
			public void mouseReleased(MouseEvent e)
			{
				labelAlerte.setText("");
				
				/*Test, pas complet du tout aucune verif faite*/
				if (test==1)
				{
					Position p = h.getPosition();
			
					c.actionHeros(p, new Position(e.getX()/IConfig.NB_PIX_CASE,e.getY()/IConfig.NB_PIX_CASE));
					
					c.toutDessiner(getGraphics());
					
					repaint();
					test=0;
				}
				
				/*Inutile juste pour tester*/
				if(c.trouveHeros()==null){
					System.out.print("Perdu");
				}
			
			}
		});
		
		labelInfo.setOpaque(true);
		labelInfo.setBackground(Color.WHITE);
		labelInfo.setPreferredSize(new Dimension(500,70));	
		labelInfo.setHorizontalAlignment(JLabel.CENTER);
		labelInfo.setVerticalAlignment(JLabel.CENTER);
		Font font1 = new Font("Calibri",Font.BOLD,17);
		labelInfo.setFont(font1);
		add(labelInfo,BorderLayout.SOUTH);
		
		labelAlerte.setPreferredSize(new Dimension(500,70));	
		labelAlerte.setHorizontalAlignment(JLabel.CENTER);
		labelAlerte.setVerticalAlignment(JLabel.CENTER);
		Font font2 = new Font("Calibri",Font.BOLD,28);
		labelAlerte.setFont(font2);
		add(labelAlerte,BorderLayout.NORTH);
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		labelAlerte.setText(c.informations);
		c.toutDessiner(g);
	
	}
}