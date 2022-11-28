package Sokoban;

import java.io.*;

/**
 * @file Plateau.java
 * @brief Contient la déclaration de la classe Plateau qui hérite de la classe Coordonnees
 * @details Cette classe contient les informations sur le plateau de jeu
 * @author Justine Moulin
 */

public class Plateau extends Coordonnees {
	
	// liste des attributs
	/**
     * @brief m_largeur : entier correspondant à la largeur de la grille de jeu pour un niveau donné
     */
    private int m_largeur;
    /**
     * @brief m_longueur : entier correspondant à la longueur de la grille de jeu pour un niveau donné
     */
    private int m_longueur;
    /**
     * @brief m_niveau : entier correspondant au numéro du niveau en cours
     */
    private int m_niveau;
    /**
     * @brief m_nbCaisse : entier correspondant au nombre de caisse présente dans le niveau en cours
     */
    private int m_nbCaisse;
    /**
     * @brief m_perso : Case correspondant au personnage
     */
    private Case m_perso;
    /**
     * @brief m_GrillePlateau : Matrice d'éléments de type Case
     * @detail modélise la grille de jeu
     */
    private Case[][] m_GrillePlateau;
    /**
     * @brief m_listeCaisses : Tableau d'éléments de type Caisse
     * @detail contient les informations sur les caisses du niveau
     */
    private Caisse[] m_listeCaisses;
    /**
     * @brief m_listeCasesMarquees : Tableau d'éléments de type Case
     * @detail contient les informations sur les cases marquées du niveau
     */
    private Case[] m_listeCasesMarquees;

    /**
     * @brief m_listeFichiers : Tableaux contenant les chemins relatifs vers les fichiers de niveaux
     */
    private String[] m_listeFichiers;
    /**
     * @brief mvt : entier correspondant au nombre de mouvements effectués par le joueur
     */
    private int mvt;
    
    // liste des méthodes
    /**
     * @brief Plateau : constructeur
     * @param x : entier correspondant à l'abscisse du coin haut gauche du plateau
     * @param y : entier correspondant à l'ordonnée du coin haut gauche du plateau
     * @param n : entier correspondant au numéro du niveau à afficher
     */
    public Plateau(int x, int y, int n) {
    	super(x, y); // appel du constructeur de Coordonnées
    	
    	// déclaration des variables 
    	int type;
        int nbCaseLargeur;
        int nbCaseHauteur;
        int k = 0;
        int l = 0;
        mvt = 0;
        // référence + allocation mémoire pour m_listeFichiers
     	m_listeFichiers = new String[3];
     	// nom du fichier du niveau à jouer :
        m_listeFichiers[0] = "/Users/justinemoulin/Sokoban2/niveaux/niveau1.txt";
        m_listeFichiers[1] = "/Users/justinemoulin/Sokoban2/niveaux/niveau2.txt";
        m_listeFichiers[2] = "/Users/justinemoulin/Sokoban2/niveaux/niveau3.txt";
    	
        // lecture du fichier :
        try
        {
          File file = new File(m_listeFichiers[n]);    
          FileReader fr = new FileReader(file); // Création de l'objet FileReader        
          BufferedReader br = new BufferedReader(fr); // Création l'objet BufferedReader   
          int c=0;
          // lecture des informations du débuts de fichier
          m_niveau = Integer.parseInt(br.readLine());
          m_nbCaisse = Integer.parseInt(br.readLine());  
          m_largeur = Integer.parseInt(br.readLine());
          m_longueur = Integer.parseInt(br.readLine());
          // calcul du nombre de cases par ligne et par colonne
          nbCaseLargeur = m_largeur/60;
          nbCaseHauteur = m_longueur/60;
            
          // allocation mémoire pour la liste des caisses
          m_listeCaisses = new Caisse[m_nbCaisse];
          // allocation mémoire pour la liste des cases
          m_listeCasesMarquees = new Case[m_nbCaisse];
          // allocation mémoire pour la grille
          m_GrillePlateau = new Case [nbCaseHauteur][nbCaseLargeur];
            
          // lecture de la composition de la grille de jeu
            for(int i=0; i<nbCaseHauteur; i++)
            {
                for(int j=0; j<nbCaseLargeur; j++)
                {
                    // lecture du type de la case
                    type = Integer.parseInt(br.readLine());
                    // on l'enregistre dans la grille
                    m_GrillePlateau[i][j] = new Case(i,j,type);
                    // si c'est une caisse, il faut l'ajouter à la liste de caisses
                    if (m_GrillePlateau[i][j].getType() == 3)
                    {
                    	m_listeCaisses[k] = new Caisse(i, j, false);
                        k = k + 1;
                    }
                    // si c'est une case marquée, il faut l'ajouter à la liste de cases marquées
                    if (m_GrillePlateau[i][j].getType() == 2)
                    {
                    	m_listeCasesMarquees[l] = new Case(i, j, 2);
                        l = l + 1;
                    }
                    // si c'est le personnage, il faut l'initialiser dans m_perso
                    if (m_GrillePlateau[i][j].getType() == 4)
                    {
                    	m_perso = new Case(i,j,4);
                    }
                }
            }
          // une fois la lecture terminée, on ferme le fichier
          fr.close();     
        }
        // Si la lecture du fichier échoue, on lance un exception
        catch(IOException e)
        {
          e.printStackTrace();
        }
    }
    /**
     * @brief getLongueur : Ascesseur de longueur
     * @return entier correpsondant à la longueur du plateau
     */
    public int getLongueur() {
    	return m_longueur;
    }
    /**
     * @brief getLargeur : Ascesseur de largeur
     * @return entier correpsondant à la largeur du plateau
     */
    public int getLargeur() {
    	return m_largeur;
    }
    /**
     * @brief getNiveau : Ascesseur de niveau
     * @return entier correspondant au numéro du niveau
     */
    public int getNiveau() {
    	return m_niveau;
    }
    /**
     * @brief getNbCaisse : Ascesseur de nbCaisse
     * @return entier correspondant au nombre de caisses
     */
    public int getNbCaisse() {
    	return m_nbCaisse;
    }
    /**
     * @brief getMvt : Ascesseur de mvt
     * @return entier correspondant au nombre de mouvement
     */
    public int getMvt() {
    	return mvt;
    }
    /**
     * @brief getGrillePlateau() : Ascesseur de grillePlateau
     * @return Case[][]
     */
    public Case[][] getGrillePlateau() {
        return m_GrillePlateau;
    }
    /**
     * @brief getListeCaisses() : Ascesseur de listeCaisses
     * @return Caisse[]
     */
    public Caisse[] getListeCaisses() {
        return m_listeCaisses;
    }
    /**
     * @brief getPersonnage() : Ascesseur de m_perso
     * @return Case
     */
    public Case getPersonnage() {
        return m_perso;
    }
    /**
     * @brief getListeCasesMarquees() : Ascesseur de listeCasesMarquees
     * @return Case[]
     */
    public Case[] getListeCasesMarquees() {
        return m_listeCasesMarquees;
    }
    /**
     * @brief setMvt() : Mutateur de mvt
     * @param m : entier
     */
    public void setMvt(int m) {
    	mvt = m;
    }
    /**
     * @brief setGrillePlateau() : Mutateur de grillePlateau
     * @param grille : Case[][]
     */
    public void setGrillePlateau(Case[][] grille) {
    	int nbCaseHauteur = m_longueur/60;
        int nbCaseLargeur = m_largeur/60;
        // recopie
        for (int i=0; i<nbCaseHauteur; i++)
        {
            for(int j=0; j<nbCaseLargeur; j++)
            {
                m_GrillePlateau[i][j].setType(grille[i][j].getType());
            }
        }
    }
    /**
     * @brief setListeCaisses() : Mutateur de listeCaisses
     * @param listeCaisses : Caisse[]
     */
    public void setListeCasesMarquees(Caisse[] listeCaisses) {
    	for (int i=0; i<m_nbCaisse; i++)
        {
            m_listeCaisses[i].setX(listeCaisses[i].getX());
            m_listeCaisses[i].setY(listeCaisses[i].getY());
            m_listeCaisses[i].setEtatCaisse(listeCaisses[i].getEtatCaisse());
        }
    }
    
    /**
     * @brief setPersonnage() : Mutateur de m_perso
     * @param p : Case
     */
    public void setPersonnage(Case p) {
        m_perso.setX(p.getX());
        m_perso.setY(p.getY());
    }
    
    /**
     * @brief updateCoordonnees : Permet de récupérer les nouvelles coordonnées après le déplacement
     * @param coord : Coordonnées de l'objet avant le déplacement
     * @param d : entier correspondant à la direction de déplacement
     * @return Coordonnees de l'objet après le déplacement
     */
    public Coordonnees updateCoordonnees(Coordonnees coordP, int d) {
    	Coordonnees coor = new Coordonnees(0,0);
        switch(d) {
        	// vers le haut : x - 1 et y ne bouge pas
            case 1 :
                coor.setX(coordP.getX() - 1);
                coor.setY(coordP.getY());
            break;
            // vers le bas : x + 1 et y ne bouge pas
            case 2 :
                coor.setX(coordP.getX() + 1);
                coor.setY(coordP.getY());
            break;
            // vers la droite : x ne bouge pas et y + 1
            case 3 :
                coor.setY(coordP.getY() + 1);
                coor.setX(coordP.getX());
            break;
            // vers la gauche : x ne bouge pas et y - 1
            case 4 :
                coor.setY(coordP.getY() - 1);
                coor.setX(coordP.getX());
            break;
        }
        return coor;
    }
    
    /**
     * @brief deplacerPersonnage : Permet de déplacer le personnage dans une direction donnée
     * @param coordP : Coordonnees correspondant aux coordonnées actuelles du personnage
     * @param d : entier correspondant à la direction dans laquelle le personnage doit se déplacer
     */
    public void deplacerPersonnage(Coordonnees coordP, int d) {
    	// vérification de la case suivante :
        int suivanteP = verifierPosition(coordP, d);
        int marquee = 0;
        Coordonnees newc = new Coordonnees(0,0);
        // switch pour personnage :
        switch (suivanteP) {
            // la case est vide, on peut se déplacer
            case 0 :
            {
                // type de la case où se trouvait le perso (vide ou marquée)
                for(int k=0; k < m_nbCaisse; k++) {
                    if ((m_listeCasesMarquees[k].getX() == coordP.getX())&&(m_listeCasesMarquees[k].getY() == coordP.getY()))
                        marquee = 1;
                }

                // mise à jour de la grille (position ou se trouvait le personnage)
                if (marquee == 1)
                    m_GrillePlateau[coordP.getX()][coordP.getY()].setType(2);
                else
                    m_GrillePlateau[coordP.getX()][coordP.getY()].setType(0);

                // mise à jour des coordonnées du personnage
                newc.setX(updateCoordonnees(coordP, d).getX());
                newc.setY(updateCoordonnees(coordP, d).getY());

                // mise à jour de la grille (position ou arrive le personnage)
                m_GrillePlateau[newc.getX()][newc.getY()].setType(4);

                // mise à jour des coordonnées du personnage dans m_perso
                m_perso.setX(newc.getX());
                m_perso.setY(newc.getY());

                // mise à jour du nombre de mouvements :
                mvt = mvt + 1;
                setMvt(mvt);
            }
            break;

            // la case est un mur, on ne fait rien
            case 1 :

            break;

            // la case est marquée, on peut se déplacer
            case 2 :
            {
                // type de la case où se trouvait le perso (vide ou marquée)
                for(int k=0; k < m_nbCaisse; k++) {
                    if ((m_listeCasesMarquees[k].getX() == coordP.getX())&&(m_listeCasesMarquees[k].getY() == coordP.getY()))
                        marquee = 1;
                }
                // mise à jour de la grille (position ou se trouvait le personnage)
                if (marquee == 1)
                    m_GrillePlateau[coordP.getX()][coordP.getY()].setType(2);
                else
                    m_GrillePlateau[coordP.getX()][coordP.getY()].setType(0);

                // mise à jour des coordonnées du personnage
                newc.setX(updateCoordonnees(coordP, d).getX());
                newc.setY(updateCoordonnees(coordP, d).getY());

                // mise à jour de la grille (position ou arrive le personnage)
                m_GrillePlateau[newc.getX()][newc.getY()].setType(4);

                // mise à jour des coordonnées du personnage dans m_perso
                m_perso.setX(newc.getX());
                m_perso.setY(newc.getY());

                // mise à jour du nombre de mouvements :
                mvt = mvt + 1;
                setMvt(mvt);
            }
            break;

            // le joueur veut pousser une caisse
            case 3 :
            {
                // calcule des coordonnées de la caisse
                Coordonnees coordC = new Coordonnees (0,0);
                coordC.setX(updateCoordonnees(coordP, d).getX());
                coordC.setY(updateCoordonnees(coordP, d).getY());

                // si la caisse a été déplacée, on déplace le personne, sinon on ne fait rien
                if (deplacerCaisse(coordC, d) == true ) {

                    // type de la case où se trouvait le perso (vide ou marquée)
                    for(int k=0; k < m_nbCaisse; k++) {
                        if ((m_listeCasesMarquees[k].getX() == coordP.getX())&&(m_listeCasesMarquees[k].getY() == coordP.getY()))
                            marquee = 1;
                    }

                    // mise à jour de la grille (position ou se trouvait le personnage)
                    if (marquee == 1)
                        m_GrillePlateau[coordP.getX()][coordP.getY()].setType(2);
                    else
                        m_GrillePlateau[coordP.getX()][coordP.getY()].setType(0);

                    // mise à jour des coordonnées du personnage
                    newc.setX(updateCoordonnees(coordP, d).getX());
                    newc.setY(updateCoordonnees(coordP, d).getY());

                    // mise à jour de la grille (position ou arrive le personnage)
                    m_GrillePlateau[newc.getX()][newc.getY()].setType(4);

                    // mise à jour des coordonnées du personnage dans m_perso
                    m_perso.setX(newc.getX());
                    m_perso.setY(newc.getY());

                    // mise à jour du nombre de mouvements :
                    mvt = mvt + 1;
                    setMvt(mvt);
                }
            }
            break;
        }
    }
    /**
     * @brief deplacerCaisse : Permet de déplacer une caisse dans une direction donnée
     * @param coordC : Coordonnees correspondant aux coordonnées actuelles de la caisse
     * @param d : entier correspondant à la direction dans laquelle la caisse doit se déplacer
     * @return reussi: booléen valant vrai si la caisse à été déplacée et faux sinon
     */
    public boolean deplacerCaisse(Coordonnees coordC, int d) {
    	// vérification de la case suivante :
        int suivante = verifierPosition(coordC, d);
        int marquee = 0;
        Coordonnees newc = new Coordonnees(0,0);
        boolean isMoved = false;

        // en fonction du type de la case suivante, on a plusieurs cas possibles :
        switch (suivante) {

            // la case est vide, on peut se déplacer
            case 0 :
            {
                // type de la case où se trouvait la caisse (vide ou marquée)
                for(int k=0; k < m_nbCaisse; k++) {
                    if ((m_listeCasesMarquees[k].getX() == coordC.getX())&&(m_listeCasesMarquees[k].getY() == coordC.getY()))
                        marquee = 1;
                }
                // mise à jour de la grille (position ou se trouvait la caisse)
                if (marquee == 1)
                    m_GrillePlateau[coordC.getX()][coordC.getY()].setType(2);
                else
                    m_GrillePlateau[coordC.getX()][coordC.getY()].setType(0);

                // mise à jour des coordonnées dans la liste des caisses
                int k = 0;
                while ((m_listeCaisses[k].getX() != coordC.getX())||(m_listeCaisses[k].getY() != coordC.getY()))
                    k = k + 1;
                newc = updateCoordonnees(coordC,d);
                m_listeCaisses[k].setX(newc.getX());
                m_listeCaisses[k].setY(newc.getY());

                // mise à jour de la grille (position ou arrive la caisses)
                m_GrillePlateau[newc.getX()][newc.getY()].setType(3);

                // changement de couleur
                m_listeCaisses[k].setEtatCaisse(false);

                // la méthode renvoie true car la caisse a été déplacée
                isMoved =  true;
            }
            break;

            // la case est un mur, on ne fait rien
            case 1 :
                isMoved = false;
            break;

            // la case est marquée, on peut se déplacer et il faut changer la couleur
            case 2 :
            {
                // type de la case où se trouvait la caisse (vide ou marquée)
                for(int k=0; k < m_nbCaisse; k++) {
                    if ((m_listeCasesMarquees[k].getX() == coordC.getX())&&(m_listeCasesMarquees[k].getY() == coordC.getY()))
                        marquee = 1;
                }
                // mise à jour de la grille (position ou se trouvait la caisse)
                if (marquee == 1)
                    m_GrillePlateau[coordC.getX()][coordC.getY()].setType(2);
                else
                    m_GrillePlateau[coordC.getX()][coordC.getY()].setType(0);

                // mise à jour des coordonnées dans la liste des caisses
                int k = 0;
                while ((m_listeCaisses[k].getX() != coordC.getX())||(m_listeCaisses[k].getY() != coordC.getY()))
                    k = k + 1;
                newc = updateCoordonnees(coordC,d);
                m_listeCaisses[k].setX(newc.getX());
                m_listeCaisses[k].setY(newc.getY());

                // mise à jour de la grille (position ou arrive la caisses)
                m_GrillePlateau[newc.getX()][newc.getY()].setType(3);

                // on regarde si un changement de couleur est nécessaire
                m_listeCaisses[k].setEtatCaisse(true);

                // la méthode renvoie true car la caisse a été déplacée
                isMoved = true;
            }
            break;

            // il y a une autre caisse dernière la caisse => on ne fait rien
            case 3 :
                isMoved = false;
            break;

            // la case suivant est le personne => le joueur veut tirer la caisse => on ne fait rien
            case 4 :
                isMoved = false;
            break;
        }
        return isMoved;
    	
    }
    /**
     * @brief verifierPosition : Permet de vérifier le type de case sur laquelle le joueur veut se déplacer
     * @param Coord : Coordonnees actuelles de l'objet considéré
     * @param d : entier correspondant à la direction de déplacement
     * @return un entier correspondant à au type de la case d'arrivée du déplacement
     */
    public int verifierPosition(Coordonnees Coord, int d) {
    	Coordonnees coord_dep = new Coordonnees(0,0);
        switch(d) {
            // on regarde la case du dessus
            case 1 :
            	coord_dep.setX(Coord.getX() - 1);
            	coord_dep.setY(Coord.getY());
            break;
            // on regarde la case du dessous
            case 2 :
            	coord_dep.setX(Coord.getX() + 1);
            	coord_dep.setY(Coord.getY());
            break;
            // on regarde la case de droite
            case 3 :
            	coord_dep.setX(Coord.getX());
            	coord_dep.setY(Coord.getY() + 1);

            break;
            // on regarde la case de gauche
            case 4 :
            	coord_dep.setX(Coord.getX());
            	coord_dep.setY(Coord.getY() - 1);
            break;
        }
        return m_GrillePlateau[coord_dep.getX()][coord_dep.getY()].getType();
    }
    
    /**
     * @brief NiveauTermine : Permet de savoir si un niveau est terminé ou non, en comptant le nombre de caisses bien placées
     * @return booléen valant vrai si le niveau est terminé et faux sinon
     */
    public boolean niveauTermine() {
        int compteur = 0;
        for(int k=0; k<m_nbCaisse; k++)
        {
            if (m_listeCaisses[k].getEtatCaisse() == true)
                compteur = compteur + 1;
        }
        if (compteur == m_nbCaisse)
            return true;
        else
            return false;
    }
    
    
}
