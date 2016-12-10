package wargame;

import wargame.ISoldat.TypesH;

public class Test1
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