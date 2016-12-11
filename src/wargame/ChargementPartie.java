package wargame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.*;
import javax.swing.JOptionPane;

/** Boite de dialogue permettant de choisir une sauvegarde parmi celles existante */
public class ChargementPartie extends JPanel implements IConfig
{
	private static final long serialVersionUID = 18770052638764L;
	
	/** Carte choisie par l'utilisateur */
	private Carte c;
	
	public ChargementPartie()
	{	
		//On cherche tous les choix de sauvegarde possible
	    String[] choixSave = chercheSave();
	    
	    if (choixSave.length != 0)	//Si on a trouvé au moins une sauvegarde
	    {
	    	String choix = (String)JOptionPane.showInputDialog
	    
		    		(
			    		null,
			    		"Quelle partie charger?",
			    		"Chargement",
			    		JOptionPane.QUESTION_MESSAGE,
			    		null,
			    		choixSave,
			    		choixSave[choixSave.length-1]
		    		);
	    
		    //if (choix == null) throw ErreurDialog ??
		    
		    if (choix == null)	//Si jamais le dialog était annulé
		    {
		    	c = null;
		    }
		    else
		    	c = chargementCarte(choix);
	    }
	    else	//On a pas trouvé de sauvegarde
	    {
		    JOptionPane.showMessageDialog(null,"Erreur: Aucune sauvegarde trouvé");
	    }
	}
	
	/**
	 *  Permet de retourner la carte chargé
	 * @return La carte chargée
	 */
	public Carte getCarte()
	{
		return c;
	}
	
	/** Charge la carte du fichier save/(nom) et la place dans la carte de l'objet */
	private Carte chargementCarte(String nom)
	{
		ObjectInputStream ois = null;
		Carte c = null;

	    try
	    {
	      final FileInputStream fichier = new FileInputStream("save/"+nom);
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
				c.getElement(i,j).changerImage();
			}
		}
				
	    
	    return c;
	}
	
	/** Cherche dans le dossier save tout les fichiers avec extension .sac et renvoi le tableau */
	private String[] chercheSave()
	{
	    FilenameFilter filtre = new FilenameFilter()	//Filtre de nom
	    {
			public boolean accept(File dir, String name)
			{
				return name.endsWith(".sav");
			}
		};
		
		String[] choixSave = new File("save/").list(filtre);
		
		return choixSave;
	}
}
