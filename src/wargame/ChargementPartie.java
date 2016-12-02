package wargame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	    String[] choixSave = {"Sauvegarde 3: 02/12/2016 16h23m10s", "Sauvegarde 2", "Sauvegarde 1"};	//Sera rempli avec les save trouvés
	    
	    //String[] choixSave = chercheSave();
	    
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
	
	
	/** Retourne la carte chargée */
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
				c.caseCarte[i][j].changerImage();
			}
		}
				
	    
	    return c;
	}
	
	private String[] chercheSave()		//Pas testé !!!!!!!!!
	{
		String[] s = new File("save").list();
		
		return s;
	}
}
