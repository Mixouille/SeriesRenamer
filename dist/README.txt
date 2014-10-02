#################################################################
#   Series Renamer 1.0.0, par Mixouille (mixouille@gmail.com)   #
#################################################################

Fonctionnalit�s
---------------

Series Renamer vous permet de renommer par lots les fichiers vid�o de s�ries TV, si comme moi vous aimez conserver vos s�ries pr�f�r�es sous un format propre et explicite.

- Recherche de s�ries � partir de tout ou partie du titre.
- Utilisation, au choix, des sites Allocine, BetaSeries ou TMDB comme fournisseur de donn�es.
- R�cup�ration de donn�es associ�es aux s�ries : titres, posters, dates, saisons, �pisodes.
- Acc�s aux titres en VO et en VF (seulement sur Allocine pour la VF).
- S�lection des fichiers � renommer par fen�tres de s�lection ou par drag&drop.
- Possibilit� "d'extraction" des fichiers pr�sents dans des sous-r�pertoires.
- R�ordonnancement de la liste de fichiers.
- 2 patterns utilisables, enti�rement personnalisables.


Configuration requise
---------------------

- Th�oriquement fonctionne sous n'importe quel OS graphique 32 ou 64 bits (Windows, Unix, Linux, Mac), test� sous Windows XP et Windows 7 64 bits.
- JavaSE 32 bits JRE 6+ (le programme proposera de le t�l�charger au besoin).
- Connexion Internet.


Installation / Lancement
------------------------

Aucune installation n'est n�cessaire. Il suffit de copier le r�pertoire SeriesRenamer sur votre disque.
Pour lancer l'application :
	- Sous Windows : vous pouvez ex�cuter le fichier SeriesRenamer.exe.
	- Sur tout OS : lancez le JAR ex�cutable SeriesRenamer.jar.

	
R�glages
--------

Le bouton "R�glages" ouvre une fen�tre permettant de param�trer l'application.
Deux patterns sont disponibles et enti�rement personnalisables selon les besoins. Par d�faut il s'agit des patterns VF et VO.

- Nom pattern 1 / 2 : Nom du pattern (1 ou 2) affich� dans l'interface.
- Pattern 1 / 2 : Le pattern (1 ou 2) en lui-m�me, constitu� de caract�res fixes et de mots-cl�s (voir ci-dessous pour les mots-cl�s).
- Remplacer les espaces par les points : A cocher si l'on ne souhaite pas de caract�res espace dans le nom des fichiers renomm�s.
- Extraire des sous-r�pertoires : Si non coch� Series Renamer parcourra les sous-r�pertoires et renommera les fichiers contenus, mais les laissera � leur emplacement d'origine. Si coch� les fichiers seront en plus d�plac�s dans le r�pertoire racine (utile quand chaque �pisode a son propre r�pertoire).
- Test pattern : En cliquant sur l'ic�ne repr�sentant un oeil vous pouvez tester un pattern pour visualiser le rendu obtenu.


Mots-cl�s pour la d�finition des patterns
-----------------------------------------

Utilisez les mots-cl�s suivants pour ins�rer du contenu dynamique dans le nom des fichiers renomm�s :

%svf% : Titre de la s�rie en VF (uniquement Allocinbe)
%svo% : Titre de la s�rie en VO
%sai% : N� de la saison
%esa% : N� d'�pisode dans la saison
%ese% : N� global de l'�pisode dans la s�rie (non disponible sur TMDB)
%evf% : Titre de l'�pisode en VF (uniquement Allocinbe)
%evo% : Titre de l'�pisode en VO

Lorsqque les titres en VF ne sont pas disponibles ils sont automatiquement remplac�s par les titres en VO.

Attention : les caract�res suivants �tant interdits dans les noms de fichiers ils seront remplac�s par un espace (ou un point) s'ils sont utilis�s :
'/', '\', ':', '*', '?', '"', '<', '>', '|'


Renommage des �pisodes d'une saison
-----------------------------------

- Choisissez un fournisseur de donn�es : Allocine, BetaSeries ou TMDB.
- Entrez tout ou partie du nom de la s�rie, en VO ou VF.
- Cliquez sur la s�rie d�sir�e.
- Cliquez sur la saison d�sir�e.
- Choisissez les fichiers � renommer selon l'une de ces 3 m�thodes :
	* Cliquer sur "Choisir les fichiers � renommer", parcourez vos disques et s�lectionnez tous les fichiers et r�pertoires voulus en utilisant la multi-s�lection (touches CTRL et MAJ).
	* Cliquez sur "Choisir un r�pertoire", parcourez vos disques et s�lectionnez le r�pertoire racine de vos fichiers � renommer.
	* Utilisez le drag&drop en faisant glisser les fichiers / r�pertoires d�sir�s sur la fen�tre de l'application.
- Vous pouvez supprimer un ou plusieurs fichiers de la s�lection en utilisant le bouton "Supprimer les fichiers s�lectionn�s de la liste".
- Vous pouvez r�ordonner les fichiers de la liste en utilisant les boutons fl�ch�s afin que l'ordre corresponde bien � l'ordre des �pisodes tel qu'il est dans la saison.
- Le renommage n'est possible que si autant d'�pisodes sont s�lectionn�s dans la partie gauche que dans la partie droite. Ne s�lectionner aucun �l�ment est �quivalent � tous les s�lectionner. Par exemple si vous ne poss�dez pas tous les �pisodes de la saison, s�lectionnez � l'aide de la multi-s�lection (touches CTRL et MAJ) uniquement les �pisodes pr�sents dans la liste de fichiers.
- Si autant d'�pisodes que de fichiers sont s�lectionn�s les boutons de renommage s'activent.
- Cliquez sur le bouton de renommage d�sir�, les fichiers sont renomm�s dans la liste et sur le disque.


Change log
----------

Version 1.0.0
* Version initiale






















