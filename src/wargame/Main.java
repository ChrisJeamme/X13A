package wargame;

import wargame.ISoldat.TypesH;

/**
 * Classe Main: Lance l'interface
 */
public class Main
{
	public static void main(String[] args)
	{
		new Fenetre();

		for(TypesH h: TypesH.values())
			System.out.println(h+" "+h.getPoints()+" "+h.getPortee()+" "+h.getPuissance()+" "+h.getTir());
		
		//TODO Des try catch, qui peuvent ouvrir des fenetres JOptionPane()
		//TODO Tout commenter
	}
}