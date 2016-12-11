package wargame;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.*;

public class PanneauJeu extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1877005263173998764L;
	private JPanel menuBar;
	private JButton sauvegarde;
	private JButton menu;
	private JButton fintour;
	private JLabel labelAlerte = new JLabel();
	private JPanel hautfenetre = new JPanel();
	private JLabel labelInfo = new JLabel();
	private JLabel labelfin=new JLabel();
	private JPanel p2;
	private int numeroTour = 1;
	private int affichagefin=0;
	private int affichagetour=0;
	
	public PanneauJeu(Carte c, final JFrame f)
	{	
		/*On ajoute la JMenuBar */
		hautfenetre.setLayout(new GridLayout(2,1));
		menuBar(f);
		/*Classe imbriquée pour separer la JMenuBar du JPanel */
		class PanneauJeuImbric extends JPanel implements Serializable
		{
			private static final long serialVersionUID = 1L;
			/** Carte du panneau */
			private Carte c;
			/** Label où il y aura les informations supplémentaires */
			private JLabel labelInfoTours = new JLabel();
			/**  */
			private int selection=0;
			/** Le héros selectionné */
			private Element h;
			/** Choix de l'IA: Par défaut à 1, il faudra faire un menu pour choisir */
			private int choixIA = 2;	//
			
			
			public PanneauJeuImbric(Carte carte)
			{				
				c = carte;
				
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
						if (e.getX()/IConfig.NB_PIX_CASE<IConfig.LARGEUR_CARTE && e.getY()/IConfig.NB_PIX_CASE<IConfig.HAUTEUR_CARTE && affichagefin==0) //Si position souris correcte
							if(c.getElement(e.getX()/IConfig.NB_PIX_CASE,e.getY()/IConfig.NB_PIX_CASE).visible==true)	//Et sur une case visible
								labelInfo.setText(c.getElement(e.getX()/IConfig.NB_PIX_CASE,e.getY()/IConfig.NB_PIX_CASE).toString());	//On met à jour le labelInfo
						else labelInfo.setText("");
					}
				});
				
				addMouseListener(new MouseAdapter()	//Gestion des héros
				{
					private boolean selected;
					public void mousePressed(MouseEvent e)
					{
						affichagetour=0;
						if (affichagefin==0){ /* Si on a pas fini */
						
							//Clic Gauche
							labelAlerte.setText("");
							if(e.getButton() == 1)	
							{			
								c.toutDessiner(getGraphics());
								if ( (e.getX()/IConfig.NB_PIX_CASE<IConfig.LARGEUR_CARTE) && (e.getY()/IConfig.NB_PIX_CASE<IConfig.HAUTEUR_CARTE))
								{
									//System.out.println("test :"+e.getX()/IConfig.NB_PIX_CASE+" "+e.getY()/IConfig.NB_PIX_CASE);
									h = c.getElement(e.getX()/IConfig.NB_PIX_CASE,e.getY()/IConfig.NB_PIX_CASE);	// h = Case cliqué
								}
								if (h instanceof Heros)	//Case cliqué est un héros
								{
									selection = 1;
									if (((Heros)h).getTourJoue()==true){
										labelAlerte.setText("Ce Heros a déjà joué");
									}
									else{
										((Heros) h).estSelection(getGraphics(), c);
										labelAlerte.setText("Mouvement d'un héros");
										selected=true;
									}
								}
										
									
							}
					
							//Clic droit
							if(e.getButton() == 3 && selected==true)
							{
								labelAlerte.setText("");
								Position p = h.getPosition();
								if (c.actionHeros(p, new Position(e.getX()/IConfig.NB_PIX_CASE,e.getY()/IConfig.NB_PIX_CASE))==true)
								{
									((Heros) h).setTourJoue(true);
									if (c.verifFinJeu()!=0) finJeu(c.verifFinJeu());
								}
								if (h instanceof Heros)
								{
									c.toutDessiner(getGraphics());
									repaint();
									selection=0;
								}
								selected=false;
							}
						}/*fin du if */
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
				
				/*
				labelInfoTours.setOpaque(true);
				labelInfoTours.setBackground(Color.WHITE);
				labelInfoTours.setPreferredSize(new Dimension(500,40));	
				labelInfoTours.setHorizontalAlignment(JLabel.CENTER);
				//labelInfo.setVerticalAlignment(JLabel.CENTER);
				labelInfoTours.setFont(font1);
				*/
				//add(labelInfoTours,BorderLayout.SOUTH);				/////////////////////////////////////// A REMETTRE POUR LES TOURS
				
				fintour= new Boutton("Fin de Tour", "img/BouttonF.png", "img/BouttonB.png");
				fintour.setOpaque(true);
				fintour.addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						finirTour(c);				
						numeroTour++;				
						
					}
				});
				
				menuBar.add(fintour);
				
				/*Ajouté ici pour reconnaitre la fonction sauvegarde */
				sauvegarde = new Boutton("Sauvegarder une partie", "img/BouttonF.png", "img/BouttonB.png");
				sauvegarde.setPreferredSize(new Dimension(300,30));
				sauvegarde.addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						sauvegardeCarte();
						labelAlerte.setText("Partie sauvegardé");
					}
				});
				menuBar.add(sauvegarde);
				
				
			}
			
			/** Selon le choix de l'ia on va appeler la méthode correspondante */
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
				if (c.verifFinJeu()!=0) finJeu(c.verifFinJeu());
			}
	
			/** IA avec actions simples */
			protected void iaLvl1()
			{
				// Mieux que random mais non fonctionnelle a 100 %
				 Heros h;
				 Monstre[] m = c.trouveToutMonstre();
				 Heros[] tabheros=new Heros[IConfig.NB_HEROS];
				 int indicetab=0;
				 boolean verif;
				 // On recupere tous les Heros repérés
				 for(int b=0; b<m.length;b++)	
				 {	
				 	if (m[b]==null) break; // Les autres sont morts
				 	h=null;
				 	m[b].setEnCombat(false);
				 	int i=m[b].getPosition().getX(),j=m[b].getPosition().getY();
				 		
				 	for (int v=-m[b].getPortee(); v<=m[b].getPortee();v++)
				 	{
				 		for (int l=0; l<=m[b].getPortee()-(Math.abs(v));l++)
				 		{
				 			if ((new Position(i+l,j+v)).estValide())
				 			{
				 				if (c.getElement(i+l,j+v) instanceof Heros)
				 				{
				 					h=(Heros)(c.getElement(i+l,j+v));
				 					verif=false;
				 					// Je verif si il y était pas déjà
				 					for (int z=0; z<indicetab;z++)
				 						if (h.getPosition().distance(tabheros[z].getPosition())==0)
				 							verif=true;
				 					if (!verif){
				 						tabheros[indicetab]=h;
				 						indicetab++;
				 					}
				 				}
				 							
				 			}
				 			if ((new Position(i-l,j+v)).estValide())
				 			{
				 				if (c.getElement(i-l,j+v) instanceof Heros)
				 				{
				 					h=(Heros)(c.getElement(i-l,j+v));
				 					verif=false;
				 					// Je verif si il y était pas déjà
				 					for (int z=0; z<indicetab;z++)
				 						if (h.getPosition().distance(tabheros[z].getPosition())==0)
				 							verif=true;
				 					if (!verif){
				 						tabheros[indicetab]=h;
				 						indicetab++;
				 					}
				 				}
				 			}
				 		}
					}	
				 	if(h==null)	//Sinon on se déplace
				 	{
				 		c.deplaceSoldat(c.trouvePositionVideAlea(m[b].getPosition()),m[b],0);
				 	}
				 }
				 // Recuperation faite
				 for(int b=0; b<m.length;b++)	
				 {							
				 	if (m[b]==null) break; // Les autres sont morts
				 	h=null;
				 	int[] distance=new int[indicetab];
				 	int distancemin=1000,indicemin=0;
				 	if (indicetab>0){
				 
				 		for (int z=0; z<indicetab;z++){
				 			distance[z]=tabheros[z].getPosition().distance(m[b].getPosition());
				 			if (distance[z]<distancemin){
				 				distancemin=distance[z];
				 				indicemin=z;
				 			}
				 		}
				 		// Si n'a pas tout sa vie et est hors d'atteinte, se regenere 
				 		if (tabheros[indicemin].getPortee()<distancemin && m[b].getPoints()<m[b].getTYPE().getPoints()){
				 			m[b].heal();
				 		}
				 		else{
				 			if ( (tabheros[indicemin].getPosition()).distance( m[b].getPosition())  <=m[b].getPortee() ){
				 				if (m[b].combat(tabheros[indicemin])) //Heros mort
				 					c.mort(tabheros[indicemin]);
				 				c.mort(m[b]);
				 			}
				 			else c.deplaceSoldat(c.avoirPositionVers(m[b],tabheros[indicemin].getPosition()),m[b],0);
				 		}
				 	}else{ // Sinon on va de gauche a droite, on balaye la carte
				 		if (numeroTour%40>20)
				 			c.deplaceSoldat(c.avoirPositionVers(m[b],new Position(0,(m[b].getPosition().getY()+5)%IConfig.HAUTEUR_CARTE)),m[b], 0);
				 		else c.deplaceSoldat(c.avoirPositionVers(m[b],new Position(IConfig.LARGEUR_CARTE-1,(m[b].getPosition().getY()+5)%IConfig.HAUTEUR_CARTE)),m[b], 0);
				 	}
				 }
			}

			/** IA avec actions random */
			protected void iaRandom()
			{		
				Heros h;
				Monstre[] m = c.trouveToutMonstre();
				//Si un ennemi à proximité, on l'attaque
				// Reviens a faire une action Monstre avec choix aleatoire
				for(int b=0; b<m.length;b++)	//Pour tous les monstres trouvés
				{	
		
					if (m[b]==null) break; // Les autres sont morts
					h=null;
					m[b].setEnCombat(false);
					//System.out.println("bl "+b);
					int i=m[b].getPosition().getX(),j=m[b].getPosition().getY();
					//System.out.println("test: "+i+" "+j);
					for (int v=-m[b].getPortee(); v<=m[b].getPortee();v++)
					{
						for (int l=0; l<=m[b].getPortee()-(Math.abs(v));l++)
						{
							if ((new Position(i+l,j+v)).estValide())
							{
								if (c.getElement(i+l,j+v) instanceof Heros)
								{
									
									h=(Heros)(c.getElement(i+l,j+v));
									if (m[b].combat(h)) //Heros mort
										c.mort(h);
									c.mort(m[b]);
									//Je casse la boucle for l et v pour ne pas avoir 2 actions pour le meme Monstre
									l=1000;
									v=1000;
								}
								
							}
							if ((new Position(i-l,j+v)).estValide())
							{
								if (c.getElement(i-l,j+v) instanceof Heros)
								{
									h=(Heros)(c.getElement(i-l,j+v));
									if (m[b].combat(h)) //Heros mort
										c.mort(h);
									c.mort(m[b]); // SI Points de vie <=0 > l'enleve)
									//Je casse la boucle for l et v
									l=1000;
									v=1000;
								}
							}
						}
					}	
					if(h==null)	//Sinon on se déplace
					{
						c.deplaceSoldat(c.trouvePositionVideAlea(m[b].getPosition()),m[b],0);
					}
				}
			}
			
			/**
			 * 	Fini le tour: Fait les actions et appelle l'ia
			 * @param c La carte
			 */
			public void finirTour(Carte c)
			{
				affichagetour=1;
				c.jouerSoldats();
				ia();
				labelAlerte.setText("Fin du tour "+numeroTour+" (L'IA a jouée)");
				repaint();
			}
			
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				/* On dessine un fond */
				g.setColor(new Color(50,90,100));
				g.fillRect(0,0,IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE,IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE);
				c.toutDessiner(g);
				if (selection!=1 && affichagetour==0)
				{
					labelAlerte.setText(c.getInfo());
				}
			}

			/** Sauvegarde la carte de l'objet dans le fichier save */
			protected void sauvegardeCarte()
			{
			    ObjectOutputStream oos = null;

			    try
			    {			 
			    	Calendar calendrier = Calendar.getInstance(new Locale("FRANCE"));	//La date à mettre dans le nom
			    	
					final FileOutputStream fichier = new FileOutputStream("save/"+calendrier.getTime().toString().replaceAll(":", "-")+".sav");			//On l'ouvre
					
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
		
			public void finJeu(int fin)
			{
				affichagefin=1; /*sert a ne pas supprimer le message de fin vu qu'on peut encore cliquer */
				if (fin==1){ /* a ne pas laisser  bien sur ! */
					labelInfo.setText("Quel génie !");
				}
				else{
					labelInfo.setText("Pauvre merde");
				}
				repaint();
				sauvegarde.setVisible(false);
				fintour.setVisible(false);
				/*Si quand on gagne on veut plus pouvoir cliquer > balayage carte et visible=false; */

				labelfin.setVisible(true);
				labelfin.setHorizontalAlignment(JLabel.CENTER);
				labelfin.setVerticalAlignment(JLabel.CENTER);
				labelfin.setOpaque(false);
				Font font3 = new Font("Calibri",Font.BOLD,130);
				labelfin.setFont(font3);
				add(labelfin,BorderLayout.CENTER);
				if (fin==1)
					labelfin.setText("<html><center><font color = #ED7700 >Gagné !</font><br><font size=120 color = #ECFD00 >[En "+numeroTour+" tours]</font></center</html>");
				else labelfin.setText("<html><center><font color = #00C6ED >Perdu !</font><br><font size=120 color = #7F00ED > [En "+numeroTour+" tours]</font></center></html>");
				repaint();
			}
			

		}
		
		PanneauJeuImbric p2 = new PanneauJeuImbric(c);	
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
			public void mousePressed(MouseEvent e)
			{
				((Fenetre) f).retourMenu();
				repaint();
				revalidate();
			}
		});
		menuBar.add(menu);
		
		hautfenetre.add(menuBar);
	}

}