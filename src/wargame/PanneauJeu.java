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
	private JPanel menuBar;
	private JButton sauvegarde;
	private JButton menu;
	private JLabel labelAlerte = new JLabel();
	private JPanel hautfenetre = new JPanel();
	protected int numeroTour = 0;
	
	public PanneauJeu(boolean chargement, final JFrame f)
	{	
		/*On ajoute la JMenuBar */
		hautfenetre.setLayout(new GridLayout(2,1));
		menuBar(f);
		/*Classe imbriquée pour separer la JMenuBar du JPanel */
		class PanneauJeuImbric extends JPanel implements Serializable
		{
			private static final long serialVersionUID = 1L;
			
			private Carte c;
			private JLabel labelInfo = new JLabel();
			private JLabel labelInfoTours = new JLabel();
			private int selection=0;
			private Element h; //Héros selectionné
			private boolean aDejaJoue = false;
			private int choixIA = 1;	//Par défaut à 1, il faudra faire un menu pour choisir
			
			public PanneauJeuImbric(boolean chargement)
			{
				//Si chargement
				if(chargement)
					c = chargementCarte();
				else
					c = new Carte();
				
				//Configuration d'affichage
				
				setLayout(new BorderLayout());
				
				setBackground(new Color(200,200,200));
				setOpaque(true);
				setPreferredSize(new Dimension(IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE,IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE+40));
				
				//Déclaration des JElements & Ecouteurs

				addMouseMotionListener(new MouseMotionAdapter() //Gestion des héros
				{
					public void mouseMoved(MouseEvent e)
					{
						if (e.getX()/IConfig.NB_PIX_CASE<IConfig.LARGEUR_CARTE && e.getY()/IConfig.NB_PIX_CASE<IConfig.HAUTEUR_CARTE)	//Si position souris correcte
							if(c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE].visible==true)	//Et sur une case visible
								labelInfo.setText(c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE].toString());	//On met à jour le labelInfo
						else labelInfo.setText("");
					}
				});
				
				addMouseListener(new MouseAdapter()	//Gestion des héros
				{
					boolean selected = false;
					
					public void mouseClicked(MouseEvent e)
					{
						if (aDejaJoue)
						{
							labelAlerte.setText("Vous avez déjà joué ce tour !");
						}
						else
						{					
						//Clic Gauche
						
							if(e.getButton() == 1)	
							{			
								if(selected)	//Clic Gauche sur un héros EN ayant choisi un héros
								{
									c.toutDessiner(getGraphics());
									selection=0;
									
									if ( (e.getX()/IConfig.NB_PIX_CASE<IConfig.LARGEUR_CARTE) && (e.getY()/IConfig.NB_PIX_CASE<IConfig.HAUTEUR_CARTE))
									{
										h = c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE];	// h = Case cliqué
									}
									if (h instanceof Heros)	//Case cliqué est un héros
									{
										selection = 1;
										((Heros) h).estSelection(getGraphics(), c);
										selected = true;
									}
									labelAlerte.setText("Mouvement d'un héros");
								}
								else	//Clic Gauche sur un héros SANS avoir choisi un héros
								{
									if ( (e.getX()/IConfig.NB_PIX_CASE<IConfig.LARGEUR_CARTE) && (e.getY()/IConfig.NB_PIX_CASE<IConfig.HAUTEUR_CARTE))
									{
										//System.out.println("test :"+e.getX()/IConfig.NB_PIX_CASE+" "+e.getY()/IConfig.NB_PIX_CASE);
										h = c.caseCarte[e.getX()/IConfig.NB_PIX_CASE][e.getY()/IConfig.NB_PIX_CASE];	// h = Case cliqué
									}
									if (h instanceof Heros)	//Case cliqué est un héros
									{
										selection = 1;
										((Heros) h).estSelection(getGraphics(), c);
										selected = true;
									}
									labelAlerte.setText("Mouvement d'un héros");
								}
							}
				
						//Clic droit
						
							if(e.getButton() == 3)
							{
								labelAlerte.setText("");
								
								if (selected)	//Si un héros à été précédemment selectionné
								{
									selected = false;
									Position p = h.getPosition();
							
									c.actionHeros(p, new Position(e.getX()/IConfig.NB_PIX_CASE,e.getY()/IConfig.NB_PIX_CASE));
									
									if (h instanceof Heros)
									{
										c.toutDessiner(getGraphics());
										repaint();
										selection=0;
										numeroTour++;
										aDejaJoue = true;			// Sert à terminer un tour
									}
								}
							}
						}
					}
				});
				
				//Ajout des JElements
				
				labelInfo.setOpaque(true);
				labelInfo.setBackground(Color.WHITE);
				labelInfo.setPreferredSize(new Dimension(500,40));	
				labelInfo.setHorizontalAlignment(JLabel.CENTER);
				//labelInfo.setVerticalAlignment(JLabel.CENTER);
				Font font1 = new Font("Calibri",Font.BOLD,17);
				labelInfo.setFont(font1);
				add(labelInfo,BorderLayout.SOUTH);
				
				labelInfoTours.setOpaque(true);
				labelInfoTours.setBackground(Color.WHITE);
				labelInfoTours.setPreferredSize(new Dimension(500,40));	
				labelInfoTours.setHorizontalAlignment(JLabel.CENTER);
				//labelInfo.setVerticalAlignment(JLabel.CENTER);
				labelInfoTours.setFont(font1);
				add(labelInfoTours,BorderLayout.SOUTH);
				
				/*Ajouté ici pour reconnaitre la fonction sauvegarde */
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
			
			public void ia()
			{
				
				///////////////  Gérer ici l'IA  ///////////////////////
				//
				//			II		A
				//			II	   AAA
				//			II	  AA AA
				//			II	 AA   AA
				//
				////////////////////////////////////////////////////////
				
				switch(choixIA)
				{
					case 1:
						iaRandom();
						break;
					case 2:
						iaLvl1();
						break;
				}
				
				labelAlerte.setText("L'adversaire a joué");
				aDejaJoue = false;
				numeroTour++;
			}
			
			/** IA avec actions simples */
			protected void iaLvl1()
			{
				//Explorer tous les monstres
				//Cherche ceux qui ont des ennemies à attaquer
				//Choisir parmi eux avec une fonction qui attribue une valeur à chaque combat possible
			}

			/** IA avec actions random */
			protected void iaRandom()
			{
				Monstre m = c.trouveMonstre();
				
				m.seDeplace(new Position(m.getPosition().getX()+1, m.getPosition().getY()+1));	//A l'arrache pour qu'il fasse quelque chose
				
				repaint();
				
				//Si un ennemi à proximité, on l'attaque
				//Sinon on se déplace
			}

			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				/* On dessine un fond */
				g.setColor(new Color(50,90,100));
				g.fillRect(0,0,IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE,IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE);
				c.toutDessiner(g);
				if (selection!=1)
				{
					labelAlerte.setText(c.informations);
				}
				labelInfoTours.setText("Tour numéro: "+numeroTour);
				
				if(aDejaJoue)
				{
					ia();
				}
			}

			/** Charge la carte du fichier save et la place dans la carte de l'objet */
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
			    
			    for(int i=0; i<IConfig.LARGEUR_CARTE; i++)
				{
					for(int j=0; j<IConfig.HAUTEUR_CARTE; j++)
					{
						c.caseCarte[i][j].changerImage();
					}
				}
						
			    
			    return c;
			}

			/** Sauvegarde la carte de l'objet dans le fichier save */
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
			
		}
		
		PanneauJeuImbric p2=new PanneauJeuImbric(chargement);		
		
		labelAlerte.setPreferredSize(new Dimension(500,40));	
		labelAlerte.setHorizontalAlignment(JLabel.CENTER);
		labelAlerte.setVerticalAlignment(JLabel.CENTER);
		labelAlerte.setOpaque(true);
		Font font2 = new Font("Calibri",Font.BOLD,20);
		labelAlerte.setFont(font2);
		labelAlerte.setBackground(Color.WHITE);
		hautfenetre.add(labelAlerte);
		add(hautfenetre);
		add(p2,BorderLayout.CENTER);

	}

	public void paintComponent(Graphics g)
	{
		//super.paintComponent(g);
	}

	/** Menu du haut */
	public void menuBar(final JFrame f)
	{
		menuBar=new JPanel();
		//menuBar.setBackground(new Color(0,0,0));
		menuBar.setLayout(new GridLayout(1,3));
		menuBar.setOpaque(true);
		menuBar.setPreferredSize(new Dimension(IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE,50));
		//menuBar.setBackground(new Color(0,0,0));
		//Bouton Menu dans barre du haut 
		menu = new Boutton("Menu", "img/BouttonF.png", "img/BouttonB.png");
		menu.setOpaque(true);
		menu.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				//TODO
				((Fenetre) f).retourMenu();
				repaint();
				revalidate();
			}
		});
		menuBar.add(menu);

		hautfenetre.add(menuBar);
	}
}