# **Rapport de projet** 
# POO: Wargame
**Groupe**:
* BRUYERE Dimitri
* JEAMME Christopher
* PEURIERE Romain

## Introduction

Le projet comme présenté par le sujet nous a demandé de créer un jeu de wargame en Java (Basé sur une guerre dans le monde imaginaire du seigneur des anneaux).


Celui-ci tout d'abord devait implémenter le fonctionnement de base demandé par le sujet et par la suite des améliorations libres (dont certaines étaient proposées).
Il s'agit donc d'un jeu tour par tour d'un joueur contre le général-ordinateur, le but étant à chaque tour de donner un ordre à ses différentes unités et de par une stratégie, réussir à éliminer tous les ennemis avant que ceux-ci n'y parviennent. Tout ceci en utilisant à bon escient les principes de la programmation orientée object (Java) que nous avons pu étudier en cours.

Nous allons donc présenter en plusieurs parties la manière dont nous sommes arrivés à ce résultat.

**Plan**
+ Analyse du projet
    <br>Cette partie décrira la structure de notre code par le biai d'un diagramme UML ainsi que l'explication de clui-ci.
+ Techniques de POO mises en oeuvre
    <br>Nous déciront ici quellques principes de la Programmation Orientée Objet nous avons utilisé.
+ Présentation des résultats
    <br>Il sera ici expliqué nos principales fonctionnalités.
+ Temps de travail et répartition
    <br>Contient le détail de notre organisation du travail.
+ Bilan
+ Annexes

## Analyse du projet

blabla uml ici + commentaire de pq comment

### Diagramme de classes

![UML](JavaSaignerUML.png)

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

## Présentation des résultat

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

## Bilan

## Annexes

###Images ?

###Liste exhaustive des ressources utilisées (lol)

Outils, livres, tutoriels, site web
(putain mais lol !)

+ Google.fr

+ Images
    - Hobbit: Image basée sur http://www.otakia.com/wp-content/uploads/V_1/article_3723/8484.jpg
    
+ https://openclassroom.com

+ https://developpez.net/forums/

+ https://docs.oracle.com
