#################################################################
#   Series Renamer 1.0.0, par Mixouille (mixouille@gmail.com)   #
#################################################################

Fonctionnalités
---------------

Series Renamer vous permet de renommer par lots les fichiers vidéo de séries TV, si comme moi vous aimez conserver vos séries préférées sous un format propre et explicite.

- Recherche de séries à partir de tout ou partie du titre.
- Utilisation, au choix, des sites Allocine, BetaSeries ou TMDB comme fournisseur de données.
- Récupération de données associées aux séries : titres, posters, dates, saisons, épisodes.
- Accès aux titres en VO et en VF (seulement sur Allocine pour la VF).
- Sélection des fichiers à renommer par fenêtres de sélection ou par drag&drop.
- Possibilité "d'extraction" des fichiers présents dans des sous-répertoires.
- Réordonnancement de la liste de fichiers.
- 2 patterns utilisables, entièrement personnalisables.


Configuration requise
---------------------

- Théoriquement fonctionne sous n'importe quel OS graphique 32 ou 64 bits (Windows, Unix, Linux, Mac), testé sous Windows XP et Windows 7 64 bits.
- JavaSE 32 bits JRE 6+ (le programme proposera de le télécharger au besoin).
- Connexion Internet.


Installation / Lancement
------------------------

Aucune installation n'est nécessaire. Il suffit de copier le répertoire SeriesRenamer sur votre disque.
Pour lancer l'application :
	- Sous Windows : vous pouvez exécuter le fichier SeriesRenamer.exe.
	- Sur tout OS : lancez le JAR exécutable SeriesRenamer.jar.

	
Réglages
--------

Le bouton "Réglages" ouvre une fenêtre permettant de paramétrer l'application.
Deux patterns sont disponibles et entièrement personnalisables selon les besoins. Par défaut il s'agit des patterns VF et VO.

- Nom pattern 1 / 2 : Nom du pattern (1 ou 2) affiché dans l'interface.
- Pattern 1 / 2 : Le pattern (1 ou 2) en lui-même, constitué de caractères fixes et de mots-clés (voir ci-dessous pour les mots-clés).
- Remplacer les espaces par les points : A cocher si l'on ne souhaite pas de caractères espace dans le nom des fichiers renommés.
- Extraire des sous-répertoires : Si non coché Series Renamer parcourra les sous-répertoires et renommera les fichiers contenus, mais les laissera à leur emplacement d'origine. Si coché les fichiers seront en plus déplacés dans le répertoire racine (utile quand chaque épisode a son propre répertoire).
- Test pattern : En cliquant sur l'icône représentant un oeil vous pouvez tester un pattern pour visualiser le rendu obtenu.


Mots-clés pour la définition des patterns
-----------------------------------------

Utilisez les mots-clés suivants pour insérer du contenu dynamique dans le nom des fichiers renommés :

%svf% : Titre de la série en VF (uniquement Allocinbe)
%svo% : Titre de la série en VO
%sai% : N° de la saison
%esa% : N° d'épisode dans la saison
%ese% : N° global de l'épisode dans la série (non disponible sur TMDB)
%evf% : Titre de l'épisode en VF (uniquement Allocinbe)
%evo% : Titre de l'épisode en VO

Lorsqque les titres en VF ne sont pas disponibles ils sont automatiquement remplacés par les titres en VO.

Attention : les caractères suivants étant interdits dans les noms de fichiers ils seront remplacés par un espace (ou un point) s'ils sont utilisés :
'/', '\', ':', '*', '?', '"', '<', '>', '|'


Renommage des épisodes d'une saison
-----------------------------------

- Choisissez un fournisseur de données : Allocine, BetaSeries ou TMDB.
- Entrez tout ou partie du nom de la série, en VO ou VF.
- Cliquez sur la série désirée.
- Cliquez sur la saison désirée.
- Choisissez les fichiers à renommer selon l'une de ces 3 méthodes :
	* Cliquer sur "Choisir les fichiers à renommer", parcourez vos disques et sélectionnez tous les fichiers et répertoires voulus en utilisant la multi-sélection (touches CTRL et MAJ).
	* Cliquez sur "Choisir un répertoire", parcourez vos disques et sélectionnez le répertoire racine de vos fichiers à renommer.
	* Utilisez le drag&drop en faisant glisser les fichiers / répertoires désirés sur la fenêtre de l'application.
- Vous pouvez supprimer un ou plusieurs fichiers de la sélection en utilisant le bouton "Supprimer les fichiers sélectionnés de la liste".
- Vous pouvez réordonner les fichiers de la liste en utilisant les boutons fléchés afin que l'ordre corresponde bien à l'ordre des épisodes tel qu'il est dans la saison.
- Le renommage n'est possible que si autant d'épisodes sont sélectionnés dans la partie gauche que dans la partie droite. Ne sélectionner aucun élément est équivalent à tous les sélectionner. Par exemple si vous ne possédez pas tous les épisodes de la saison, sélectionnez à l'aide de la multi-sélection (touches CTRL et MAJ) uniquement les épisodes présents dans la liste de fichiers.
- Si autant d'épisodes que de fichiers sont sélectionnés les boutons de renommage s'activent.
- Cliquez sur le bouton de renommage désiré, les fichiers sont renommés dans la liste et sur le disque.


Change log
----------

Version 1.0.0
* Version initiale






















