package wargame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanneauJeu extends JPanel
{
	/* Ceci est dégueulasse et ne fonctionnera probablement pas */
	static final long serialVersionUID=0; 
	private Carte c=new Carte();
	private JLabel label=new JLabel();
	public PanneauJeu(){	
		setLayout(new BorderLayout());
		
		setBackground(new Color(200,200,200));
		setOpaque(true);
		setPreferredSize(new Dimension(IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE,IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE+70));

		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e){
				if (e.getX()/IConfig.NB_PIX_CASE<IConfig.LARGEUR_CARTE && e.getY()/IConfig.NB_PIX_CASE<IConfig.HAUTEUR_CARTE)
					if(c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE].visible==true)
						label.setText(c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE].toString());
				else label.setText("");
			}
		});
		
		addMouseListener(new MouseAdapter(){
			Heros h;
			int test;
			public void mousePressed(MouseEvent e){
				if (c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE] instanceof Heros){
					h=(Heros)c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE];
					test=1;
				}
			}
			public void mouseReleased(MouseEvent e){
				/*Test, pas complet du tout aucune verif faite*/
				if (test==1){
					Position p=h.getPosition();
					System.out.println(h.getPosition().getX()+" "+h.getPosition().getY());
					h.setPosition(new Position(e.getX()/IConfig.NB_PIX_CASE,e.getY()/IConfig.NB_PIX_CASE));
					System.out.println(h.getPosition().getX()+" "+h.getPosition().getY());
					c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE]=h;
					c.caseCarte[p.getX()][p.getY()]=new Element(p.getX(),p.getY());
					c.toutDessiner(getGraphics());
					if (c.caseCarte[p.getX()][p.getY()] instanceof Heros)
						System.out.print("BLBLBLBBLBLBL");
					repaint();
					test=0;
				}
			}
		});
		
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setPreferredSize(new Dimension(500,70));	
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		Font font = new Font("Arial",Font.BOLD,17);
		label.setFont(font);
		add(label,BorderLayout.SOUTH);
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		c.toutDessiner(g);
	}
}