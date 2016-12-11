# **Rapport de projet** 
# POO: Wargame

## Introduction

But du projet et plan du rapport

## Techniques de POO mises en oeuvre

Héritage, encapsulation, polymorphisme, exceptions etc.

+ **Exceptions**
Nous avons utilisé des ```try catch``` pour tout ce qui est entrée/sortie de fichiers et pour le chargement des images

## Présentation synthétique des résultat

Fonctionnalités / Interface

+ **Menu**  
Un menu permet de choisir de lancer une partie ou de charger

+ **Gestion des sauvegardes multiples**     
Quand on sauvegarde on crée dans le dossier ```save``` un nouveau fichier sauvegarde défini par la date.    
Quand on charge (géré dans ```ChargementPartie.java```):
    - Si il n'y a pas de sauvegarde on fait une alerte
    - Si il y a des sauvegarde on extrait la liste des sauvegardes et on donne le choix avec un ```JOptionPane```

+ **Image des obstacles aléatoires**    
(Je sais pas si c'est assez important pour y mettre ici)

+ **Gestion de raccourcis claviers basiques**   
On peut revenir en menu depuis la partie avec le bouton ```Echap```     
On peut également depuis le menu choisir de charger ou lancer une partie avec les touches 1 et 2 ou de quitter avec ```Echap```

+ **Selection des unités**  
On peut selectionner une unité avec le clic gauche, et soit donner un ordre avec le clic droit, soit changer de selectionner
On affiche lors d'une selection les cases visibles de l'unité d'une couleur, et les cases de déplacements possibles d'une autre couleur.

+ **Label alerte**  
Un label alerte informe d'information tel que l'action effectué par l'ennemi ou le héros (combat, déplacement) mais aussi une action incorrecte ou la fin d'un tour

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
Temps:  
Travaillé majoritairement sur:
+ Gestion des interfaces et dispositions
+ Intelligence artificielle
+ Généralités

**Dimitri BRUYERE**  
Temps:  
Travaillé majoritairement sur:
+ UML
+ Généralités

## Liste exhaustive des ressources utilisées (lol)

Outils, livres, tutoriels, site web
(putain mais lol !)

+ Google.fr

+ Images
    - Hobbit: Image basée sur http://www.otakia.com/wp-content/uploads/V_1/article_3723/8484.jpg
