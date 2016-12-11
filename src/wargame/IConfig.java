package wargame;
import java.awt.Color;
public interface IConfig
{
	int LARGEUR_CARTE = 18; int HAUTEUR_CARTE = 10; // en nombre de cases
	int NB_PIX_CASE = 70;
	int POSITION_X = 50; int POSITION_Y = 25; // Position de la fenetre
	int NB_HEROS = 4; int NB_MONSTRES = 13; int NB_OBSTACLES = 18;
	Color COULEUR_VIDE = Color.white, COULEUR_INCONNU = Color.lightGray;
	Color COULEUR_TEXTE = Color.black, COULEUR_MONSTRES = Color.black;
	Color COULEUR_HEROS = Color.red, COULEUR_HEROS_DEJA_JOUE = Color.pink;
	Color COULEUR_EAU = Color.blue, COULEUR_FORET = Color.green, COULEUR_ROCHER = Color.gray;
	int NB_SPRITE_FORET = 2;
	int NB_SPRITE_EAU = 1;
	int NB_SPRITE_ROCHER = 2;	
	int NB_SPRITE_HERBE = 1;
}