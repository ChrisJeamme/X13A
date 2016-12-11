package wargame;
import java.awt.Graphics;
public interface ICarte
{
	Element getElement(Position pos);
	Position trouvePositionVide(); // Trouve aleatoirement une position vide sur la carte
	Position trouvePositionVide(Position pos); // Trouve une position vide choisie aleatoirement parmi les 8 positions adjacentes de pos
	Heros trouveHeros(); // Trouve aleatoirement un heros sur la carte
	Heros trouveHeros(Position pos); // Trouve un heros choisi aleatoirement parmi les 8 positions adjacentes de pos
	public Monstre trouveMonstre();
	public Monstre[] trouveToutMonstre();
	boolean deplaceSoldat(Position pos, Soldat soldat, int affichage);
	void mort(Soldat perso);
	boolean actionHeros(Position pos, Position pos2);
	void jouerSoldats();
	void toutDessiner(Graphics g);
}