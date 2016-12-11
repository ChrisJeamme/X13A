package wargame;

import wargame.ISoldat.TypesH;

public class Main
{
	public static void main(String[] args)
	{
		new Fenetre();

		for(TypesH h: TypesH.values())
			System.out.println(h+" "+h.getPoints()+" "+h.getPortee()+" "+h.getPuissance()+" "+h.getTir());
	}
}