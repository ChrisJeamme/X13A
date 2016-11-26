package wargame;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;

public class PanneauJeu extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1877005263173998764L;
	
	private Carte c;
	private JLabel labelInfo = new JLabel();
	private JLabel labelAlerte = new JLabel();
	private JButton sauvegarde;
	private JButton menu;
	private JMenuBar menuBar;
	
	public PanneauJeu(boolean chargement)
	{	
		if(chargement)
			c = chargementCarte();
		else
			c = new Carte();
		
		setLayout(new BorderLayout());
		
		setBackground(new Color(200,200,200));
		setOpaque(true);
		setPreferredSize(new Dimension(IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE,IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE+70));

		addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseMoved(MouseEvent e)
			{
				if (e.getX()/IConfig.NB_PIX_CASE<IConfig.LARGEUR_CARTE && e.getY()/IConfig.NB_PIX_CASE<IConfig.HAUTEUR_CARTE)	//Si position souris correcte
					if(c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE].visible==true)	//Et sur une case visible
						labelInfo.setText(c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE].toString());	//On met à jour le labelInfo
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
					h = c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE];
					if (h instanceof Heros)
						((Heros) h).estSelection(getGraphics(), c);
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
				if(c.trouveHeros()==null)
				{
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
		
		//Menu du haut
		
		menuBar=new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setPreferredSize(new Dimension(200,50));
		menuBar.setBackground(new Color(125,125,125));
		add(menuBar, BorderLayout.NORTH);
		
		//Bouton Menu dans barre du haut 
		
		menu = new Boutton("Menu", "img/BouttonF.png", "img/BouttonB.png");
		menu.setPreferredSize(new Dimension(300,30));
		menu.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//TODO
				repaint();
				revalidate();
			}
		});
		menuBar.add(menu);
		
		sauvegarde = new Boutton("Sauvegarder une partie", "img/BouttonF.png", "img/BouttonB.png");
		sauvegarde.setPreferredSize(new Dimension(300,30));
		sauvegarde.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				sauvegardeCarte();
				labelAlerte.setText("Partie sauvegardé");
			}
		});
		menuBar.add(sauvegarde);
	}


	protected Carte chargementCarte()
	{
		ObjectInputStream ois = null;
		Carte c = null;

	    try
	    {
	      final FileInputStream fichier = new FileInputStream("save");
	      ois = new ObjectInputStream(fichier);
	      c = (Carte) ois.readObject();
	    }
	    catch (final java.io.IOException e)
	    {
	      e.printStackTrace();
	    }
	    catch (final ClassNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    finally
	    {
	    	try
	    	{
	    		if (ois != null)
	    			{
	    				ois.close();
	    			}
	    	}
	    	catch (final IOException ex)
	    	{
	    		ex.printStackTrace();
	    	}
	    }
	    return c;
	}

	protected void sauvegardeCarte()
	{
	    ObjectOutputStream oos = null;

	    try
	    {
	      final FileOutputStream fichier = new FileOutputStream("save");
	      oos = new ObjectOutputStream(fichier);
	      oos.writeObject(c);
	      oos.flush();
	    }
	    catch (final java.io.IOException e)
	    {
	      e.printStackTrace();
	    }
	    finally
	    {
	    	try
	    	{
	    		if (oos != null)
				{
	    			oos.flush();
	    			oos.close();
				}
			}
	      catch (final IOException ex)
	      {
	        ex.printStackTrace();
	      }
	    }
		
	}
	
	public void paintComponent(Graphics g)
	{
		//super.paintComponent(g);
		
		labelAlerte.setText(c.informations);
		c.toutDessiner(g);
		
	}
}