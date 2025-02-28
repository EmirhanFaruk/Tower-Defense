# Jeu de Tower Defense

--------------------------------------

## Présentation

Ce Tower Defense est un jeu programmé en Java 11 .
Le jeu est inspiré du jeu [Tower Defense](https://fr.wikipedia.org/wiki/Tower_defense), un jeu qui existait déja dans les années 1990.
Le principe du jeu est défendre un base contre une vagues succesives d'ennemis qui suivent l'itinéraire ou non, tout en améliorant ou en construisant des tours défensives.

## Règle du Jeu

Les monstres arrivent en marchant sur la route.
Pour les arrêter, le joueur doit construire un certain nombre de structures défensives ( les tours ) qui peuvent infliger des dégâts aux ennemis ( les monstres ).
Pour gagner la partie, il vous faudra réfléchir au placement des tours.

## Comment Jouer

Tout d'abord, il vous faudra choisir un personnage. Chaque personnage a des capacités différentes alors choisissez le personnage astucieusement pour gagner la partie.
Ensuite, placer la tour qui vous ai proposé pour tuer les monstres et gagner de l'argent.
Une fois l'argent gagné, c'est à vous d'acheter les tours défensives nécessaires pour améliorer votre défense.
Vous pouvez aussi passer au niveau supérieur vos tours défensives, il vous suffit de cliquer sur le bouton update et sur un tour déja placé et cela est fait. Attention, chaque amélioration vous coûtera des pièces.
Après chaque vagues de monstres le niveau sera de plus en plus difficiles alors dépensé judicieusement vos pièces.


## Les personnages
### 1. Villageois
Le villageois permet de diminuer les prix des tours par deux.

### 2. Archer 
L'archer permet d'augmenter les dégâts des tours d'archer de 20%.

### 3. Soldat
Le soldat permet d'augmenter les dégâts des tours soldat de 20%.

### 4. Commandant
Le commandant permet de doubler la vitesse d'attaque.

## Les monstres

Les monstres viennent en series pendant le jeu. Ils deviennent de plus en plus fort chaque vague. Il y a quatre types de resistance des monstres: balles, fleches, feu et pas de resistance.
Il y a aussi des niveaux des monstres, qui va jusqu'à 3.

* Les monstres de premier niveau sont assez faciles à tuer. Ils n'ont pas une type resistance, et ils sont la version le plus faible des monstres.
* Niveau deux est où les monstres deviennent un peu fort. Ils auront une chance d'avoir une resistance, de plus leur points de vie et vitesse sera plus que niveau 3. Ils auront aussi un batôn qui va indiquer qu'ils sont niveau deux.
* Le plus durs monstres sont de niveau trois. Ils sont plus difficile à tuer par rapport aux autres parce qu'ils ont presque tout le temps une resistance, et leur taux de resistance c'est-à-dire les dégâts qu'ils bloquent est beaucoup plus. Pas besoin de dire qu'ils ont plus de points de vie. Pour montrer leur puissance, ils portent une "veste" et une épée qui leur rendent bien stylées contre les niveaux deux.

Il existe des types des monstres. Il y a des monstres rapides, qui sont agiles mais facile à tuer. Contrairement aux rapides, il y a des lents qui sont, bon, lentes mais aussi plus difficiles à tuer. Finalement il y a un monstre normal qui a une equilibre de la vitesse et points de vie.


## Les tours
### 1. La tour soldat
Le Soldat est votre première ligne de défense. 
Il est rapide et équipé d'une arme légère, idéal pour éliminer les ennemis faibles en début de partie. 
Les balles du soldat infligent également des dégâts supplémentaires si le personnage est un soldat.

**Prix  et puissance d'attaques :**
- Niveau 1 : 10 pièces et 10 puissances d'attaques
- Niveau 2 : 17 pièces et 17 puissances d'attaques 
- Niveau 3 : 22 pièces et 22 puissances d'attaques

### 2. La tour archer
L'Archer excelle dans les attaques à distance. 
Il peut tirer des flèches rapides sur les ennemis. 
Les flèches de l'Archer infligent également des dégâts supplémentaires si le personnage est un archer.

**Prix et puissance d'attaques :**
- Niveau 1 : 15 pièces et 15 puissances d'attaques
- Niveau 2 : 26 pièces et 26 puissances d'attaques
- Niveau 3 : 33 pièces et 33 puissances d'attaques

### 3. La tour canon
Le Canon est une tour lourde qui tire des projectiles puissants.
Elle peut être améliorée pour augmenter sa puissance.

**Prix et puissance d'attaques :**
- Niveau 1 : 40 pièces et 250 puissances d'attaques
- Niveau 2 : 68 pièces et 425 puissances d'attaques
- Niveau 3 : 88 pièces et 550 puissances d'attaques

### 4. La tour catapulte
La Catapulte est une tour de siège puissante qui inflige des dégâts.
Elle attaque deux fois à chaque tir, et peut être améliorée pour augmenter sa puissance d'attaque.

**Prix et puissance d'attaques :**
- Niveau 1 : 80 pièces et 400 puissances d'attaques
- Niveau 2 : 136 pièces et 680 puissances d'attaques
- Niveau 3 : 176 pièces et 880 puissances d'attaques

## Les Modes de Jeu

Il y a deux modes du jeu: Normal et Marathon.
 - Dans la mode Normal, il y a un nombre limité de vagues des ennemis. Pendant ces vagues, les ennemis viennent en groupes de 5 et 15 qu'on peut appeler des series. Chaque vague consiste de deux series. Quand le nombre de vagues est fait, le jeu est gagné.
 - Par contre dans le mode Marathon, il y a un nombre illimité des vagues des ennemis. En plus la difficulté des vagues augmente avec chaque vague survecu, qui rend le mode Marathon un bon choix pour une experience "challenging".

## Les Difficultés

Les difficultés dans le je touchent aux certains points du jeu: le points de vie des ennemis, le temps entre les vagues, le temps d'apparition des monstres etc.
Il existe trois difficultés: Easy, Normal et Hard. La difficulté choisie change aussi le nombre des vagues, qui peut affecter les niveaux des monstres rencontrés.


## Lancer Le Jeu ##
Tout d'abord, il faut se mettre dans la repertoire `towerdefense`. Puis il faut produire une repertoire out pour les fichiers de compliation. On peut le faire en utilisant le command:

```bash
$ mkdir -p out
```

Maintenant on peut compiler les fichiers java du jeu. Pour compiler tous les fichiers .java:

```bash
$ find src/java/com/ -name "*.java" -print | xargs javac -d out
```

Enfin notre jeu est prêt a lancer. Une fois la compilation fait, on peut lancer le jeu comme on veut plusieurs fois. Le jeu est lancé avec la commande:

```bash
$ java -cp out com.gui.App
```

**A vous de jouer**



