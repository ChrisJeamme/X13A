# **Rapport de projet** 
# POO: Wargame

## Introduction

But du projet et plan du rapport

## Techniques de POO mises en oeuvre

Héritage, encapsulation, polymorphisme, exceptions etc.

+ **Exceptions**
Nous avons utilisé des ```try catch``` pour tout ce qui est entrée/sortie de fichiers et pour le chargement des images
+ **Cascade**
Certains constructeurs sont créés en cascades afin de permettre différents ajouts de paramètres
+ **Héritage**
L'héritage est principalement utilisé via la structure de base donnée de base:
    + Heros/Monstre qui héritent de Soldat
    + Soldat/Obstacle héritent de Element
    + De nombreuses classes implementent les interfaces (IConfig, ISoldat, ICarte)
    + etc...
+ **Polymorphisme**
Nottament utilisé pour les interfaces et la classe imbriquée, n'existant pas au niveau d'au dessus, un upcasting a lieu par la suite. De la même façon pour les Elements qui sont souvent cast dans leur classe par la suite.

## Présentation synthétique des résultat

Fonctionnalités / Interface

+ **Menu**  
Un menu permet de choisir de lancer une partie ou de charger (par clic ou raccourcis)

+ **Gestion des sauvegardes multiples**     
Quand on sauvegarde on crée dans le dossier ```save``` un nouveau fichier sauvegarde défini par la date.    
Quand on charge (géré dans ```ChargementPartie.java```):
    - Si il n'y a pas de sauvegarde on fait une alerte
    - Si il y a des sauvegarde on extrait la liste des sauvegardes et on donne le choix avec un ```JOptionPane```

+ **Image des élements**    
Les Elements de la carte ne sont plus représentés par des couleurs mais par des images, les obstacles quant-à-eux, ont une image choisie aléatoirement parmis un pannel correspondant à leur type (eau,rocher,foret...). (Images créées)


+ **Gestion de raccourcis claviers basiques**   
On peut revenir en menu depuis la partie avec le bouton ```Echap```     
On peut également depuis le menu choisir de charger ou lancer une partie avec les touches 1 et 2 ou de quitter avec ```Echap```.


+ **Selection des unités**  
On peut selectionner une unité avec le clic gauche, et soit donner un ordre avec le clic droit, soit changer de selection.
On affiche lors d'une selection les cases visibles de l'unité d'une couleur, et les cases de déplacements possibles d'une autre couleur.
(Les Héros alliés, Monstres ennemis, et Obstacles sont également différenciés).


+ **Label alerte**  
Un label alerte informe l'utilisateur (sous la barre de menu) tel que celui-ci affiche l'action effectuée par l'ennemi ou le héros (combat, déplacement) mais aussi une action incorrecte ou la fin d'un tour.

+ **IA** 
Deux IA possibles:
    + Une IA random comme demandée par le sujet (choixIA=1)
    + Une IA améliorée: Celle-ci repère tous les Héros à portée du groupe de Monstres et chaque Monstre essayera de rejoindre ou attaquer l'ennemi le plus près. Un Monstre peut également se régénerer si celui-ci est hors de portée d'attaque et n'a pas tout sa vie.

## Diagramme de classes

![UML](JavaSaignerUML.png)

## Estimation temps de travail

**Christopher JEAMME**  
Temps: Environ 50h
Travaillé majoritairement sur:
+ Partie graphique
+ Système de sauvegarde
+ Ecouteurs
+ Généralités


+ son ordi
+ une table

**Romain PEURIERE**  
Temps: 40h+
Travaillé majoritairement sur: 
+ Gestion des interfaces et dispositions
+ Intelligence artificielle
+ Généralités

**Dimitri BRUYERE**  
Temps: Environ 30 heures 
Travaillé majoritairement sur:
+ Fonctions de jeu
+ Organisation des classes
+ UML

## Liste exhaustive des ressources utilisées (lol)

Outils, livres, tutoriels, site web
(putain mais lol !)

+ Google.fr

+ Images
    - Hobbit: Image basée sur http://www.otakia.com/wp-content/uploads/V_1/article_3723/8484.jpg
    
+ https://openclassroom.com

+ https://developpez.net/forums/

+ https://docs.oracle.com

## Bilan
