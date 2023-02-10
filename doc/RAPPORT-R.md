
<br>

### 1 - Point d’avancement


>**→ Fonctionnalités réalisées :** Toutes le jeu á été réalisé mis à part :
>- les aménagements
>- les météos ( Storm  Clouds )
>- la manche finale aprés l'arrivée de l'empereur

<br>

>**→ Pour les logs :** Différents niveaux de logs ont été utilisés en fonction de la sévérité de l'information. 
	>- Warning = erreures try/catch
	>- Info        = annonce du début de la partie et de son numéro
	>- Fine       = issue de la partie ( gagnant / nombre de points )
	>- Finer     = objectifs choisis, validés et nombre de points obtenues
	>- Finest   = chaque actions réalisées par les joueurs

<br>

>**→ Pour les statistiques en CSV :** Nous avons collecté des statistiques sur le résultat des parties mais aussi sur les comportements des joueurs en vu d'améliorer les bots. ( à suivre ...)
Un manger de statistique est transmit au jeu. Il est appellé à chaque fois que l'on veut remonter une donnée. Il remonte ainsi le joueur en question ainsi que la catégorie de l'évènement réalisé.
>
>Le manager intègre une liste de profiles ou l'ont retrouve les information statistiques de chaqun des joueurs.

<br>

>**→ Pour le bot spécifique :** Il effectue les actions suivante en cascade si l'action précédente n'a pas aboutit ( Ex : action non autorisé ) : 
>-   Si météo donne “?” essaie de placer une irrigation pour résoudre un objectif parcelle
>-   Il récupère 1 objectif s'il n'en a pas 5
>-   Essaie de manger les bambou de quelqu'un en fonction des actions précédentes
>-  Essaie de manger un bambou pour compléter ses objectifs
>-  Essaie de remplir ses objectifs
>
  >Si un joueur avant lui a déplacé le panda ou le jardinier il essaiera de manger un bambou du même type pour empêcher ses adversaires d'en récupérer.

<br>
<br>

### 2-Architecture et qualité

<br>
<span style="color:red">**
❗POUR COMPRENDRE LE FONCTIONNEMENT DE NOTRE PROJET RAPIDEMENT DANS SA GLOBALITÉ, LISEZ CETTE PARTIE ❗ **

La description suivante a été inspirer de la célèbre citation : 
<br>

" Pourquoi, pourquoi,  pourquoi, pourquoi, pourquoi "
<center>-- <cite>Philippe Collet</cite>
<br>
<br>

>Alex et Tom passent l'aprés-midi ensemble, Tom propose á Alex de jouer au **Takenoko**. Tom
>amène alors la boite du jeu et en sort les éléments :
>- le plateau
>- les parcelles
>- les bambous
>- le panda
>- le jardinier
>- les objectifs
>- le dé météo
>- les plateaux individuelles
>
>Ils commencent alors á jouer.

<br>

>On remarque grâce à cette courte histoire que nous avons deux éléments bien distinct :
>- Le jeu ( Game )
>- Les joueurs ( bots )
>
>On retrouvera ensuite dans le jeu le moteur de jeu ( GameEngine ) qui vérifie la bonne application des règles qui lui même regroupe tous les éléments du jeu ci-dessus
>
>Parmis les joueurs on retrouve deux bots et des outils permettant au bots de choisir une action en fonction de leur objectif.
>
>**L'architecture suit donc ce principe :**
>
![[structure.png]]

<br>

>#### Game :
>Il récupère les joueurs, leur créer des profils (  les plateaux individuelles ) puis il orchestre les manches.
<br>
>Déroulement d'une manche :
>
>Le dé est joué et une météo est attribué au joueur.
>Le joueur joue le nombre de coup prévu
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;la méthode commune au bot intégré dans leur interface play() est appelé.
>elle retourne un objet de type Action qui a l'interface suivante :
><br>
>- play( Referee referee, Map map )
>>applique l'action au jeu Ex : referee.moveGardener( Position )
>- verifyObjectiveAfterAction( Referee referee, GameEngine gameEngine )
>>appel la fonction de vérification des objectifs en fonction du type d'action Ex : referee.verifyObjectifGardener()
>- incrementAction( StatisticManager statisticManager, Playable bot )
>>appel le manager de statistiques en fonction de l'action  Ex : statisticManager.incrementActionGardener()
>
>L'action récupéré, on appelle alors sa fonction play() puis verifyObjectifs()

<br>

>L'objet GameEngine nous permet d'intéragir avec le jeu et de connaître son état actuel. 
>
>- position du jardinier
>- position du panda
>- état de la carte du jeu
>- etc ...
>
>Il permet aussi au bot d'effectuer des opérations sur la map hexagonale
> Ex : isPossibleToPlacePattern()
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;getNeighbours()

Pour empêcher les joueurs de tricher les actions sont re-vérifiées par le game engine si l'action est invalide le joueur perd sont tour. 
La sécurité du jeu est aussi assuré par des interfaces adapté au leur utilisateurs. De cette manière, le joueurs ne peuvent accéder qu'au méthodes prévuent pour eux.


#### Bots :

Les bots représente un ensemble de règles respectant l'interface Playable pui permet d'être utilisé par le jeu.
Ces règles chosissent les actions à réaliser en fonction des action impossible ( faites au coup précédent ) et de leur liste d'objectifs.

>Notre MVP :
>> Il trie ses objectifs par ordre de points puis essaie de les réaliser un par un.
>
>Le MVP de Mr COLLET :
>>Il applique les règles vues précédement et en dernier essaie de réalisert ses objectifs
>
>Lorsqu'un bot veut réaliser un objectif il utilise le panel d'outil comun à tous qui offre 4 algorithme permettant de trouver le meilleur coup. on a : 
>
>- GardenerBotResolver
>- IrrigationBotResolver
>- PandaBotResolver
>- PatternBotResolver


<br>

### 3-Qualité

<br>

>Toutes les classes ont été testés de facon unitaire en essayant de provoquer des cas d'erreures.
1 test d'intégration est réalisé sur l'ensemble du jeu pour vérifier la bonne imbrication des éléments.
>
>Les seules parties non testés sont les class comportant des appels systèmes. Ces appels partent coté utilisateurs et ne font pas parties du jeu, nous ne pourront pas les tester de manière Unitaire. On y retrouve :
>- main
>- logger
>- csv

>Le déroulement de milliers de parties sans erreures nous montre que notre jeu est stable et qu'il finit par réponse attendue ( Gagnée, Perdue, Nul)

On fintit avec un code coverage de 80% et une dette technique de 45 min

Cette dette est du à certaines méthodes que nous n'avons pas pu refactorer. Ainsi qu'à des remarques trouvées non pertinentes : Remplacer "points" par une constante car réutilisé plusieurs fois dans les logs.

**Si nous avions à continuer ce projet, nous aimerions refactorer les intéractions bot / Game, le bot communique actuellent avec l'arbitre, le motreur de jeu et la map. Nous aimerions réduire ces intérations à 1 seul interlocuteur.**




<br>
<br>

### 4-Processus

<br>

Le travail à été répartit équitablement entre les membres du groupe en séparant le jeux en features indépendantes nous permettant de ne pas ce géner dans le développement

