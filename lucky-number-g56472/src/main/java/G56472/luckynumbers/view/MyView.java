package G56472.luckynumbers.view;

import G56472.luckynumbers.model.*;
import java.util.Scanner;

/**
 * this class is set to get something on the screen for the player.
 *
 * @author Nasse
 */
public class MyView implements View {
    /*attribut which is related to the interface named "model" which has many 
    methods implemented in the class game*/
    private Model model;

    /**
     * Myview constructor build the object that display the game this contructor
     * require model as an attribut in order to get the method mandatory to
     * display the information for the player
     *
     * @param model the model is related to the interface in the model package
     */
    public MyView(Model model) {
        this.model = model;
    }
    @Override
    public void displayWelcome() {
        System.out.println("Bienvenue dans le jeu du LuckyNumber");
        System.out.println("La version java écrite par Nasser Abattouy");
    }
    /*this private methode is a piece of displaygame() is to display the current player*/
    private void playerAndCardInfo() {
        System.out.println("tour du joueur n° : " + model.getCurrentPlayerNumber());
        System.out.println("nombre de cartes cachée : " + model.faceDownTileCount());
        System.out.print("liste des cartes visible : ");
        System.out.print("[");
        for (int i = 0; i < model.getAllfaceUpTiles().size(); i++) {
            /*System.out.print(model.getAllfaceUpTiles().get(i).getValue() + " ; ");*/
            System.out.print("  "+ String.format("%02d", model.getAllfaceUpTiles().get(i).getValue()) + " ");
        }
        System.out.println("]");
    }
    /*this piece of displaygame() is to display the number of the column*/
    private void layout() {
        System.out.print("    ");
        for (int i = 1; i < model.getBoardSize() + 1; i++) {
            System.out.print(i + "   ");
        }
        System.out.println("");
        System.out.println("--------------------");
    }
    /*this piece of displaygame() is to display the number of the numbers of the rows and the grid*/
    private void displayBoard() {
       
         for (int i = 0; i < model.getBoardSize(); i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < model.getBoardSize(); j++) {
                if (model.getTile(model.getCurrentPlayerNumber(), new Position(i, j)) != null) {
                    System.out.print("  " + String.format("%02d", model.getTile(model.getCurrentPlayerNumber(), new Position(i, j)).getValue()) + " ");
                } else {
                    System.out.print("  .  ");
                }
            }
            System.out.println("");
        }
    }
    /*this piece of displaygame() is to display the chosen tile*/
    private void tileChosen() {
        System.out.println("tuile choisi : " + model.getPickedTile().getValue());
    }
    @Override
    public void displayGame() {

        playerAndCardInfo();
        layout();
        displayBoard();
        tileChosen();
    }
    @Override
    public void displayWinner() {
        System.out.println("******************************************");
        System.out.println("le(s) gagnant(s) est/sont : " + model.getWinners().toString());
        System.out.println("Partie terminée ! félicitation !");
    }
    @Override
    public int askPlayerCount() {
        Scanner clavier = new Scanner(System.in);
        System.out.println("combient de personnes vont jouer ?");

        return reliableInput(2, 4);
    }
    /**
     * this method is set to exhort to user to use an integer which doesn't go
     * beyond the limits
     *
     * @param a : minimum bound
     * @param b : maximum bound
     * @return the value only if it is an integer and respect the limit value
     */
    private int reliableInput(int a, int b) {
        Scanner clavier = new Scanner(System.in);
        int num;
        do {
            while (!clavier.hasNextInt()) {
                clavier.next();
                displayError("ce que vous avez entrée n'est pas un nombre "
                        + "entier");
            }
            num = clavier.nextInt();
            if (num < a || num > b) {
                displayError("mettez un nombre qui respecte les régles svp");
            }
        } while (num < a || num > b);
        return num;
    }
    private int rowGiven() {
        System.out.println("donnez une position horizontal");
        return reliableInput(1, model.getBoardSize()) - 1;
    }
    private int columnGiven() {
        System.out.println("donnez une position verticale");
        return reliableInput(1, model.getBoardSize()) - 1;
    }
    @Override
    public Position askPosition() {
        System.out.println("donnez une position sur le plateau");
        Position pos = new Position(rowGiven(), columnGiven());

        while (!model.canTileBePut(pos) || !model.isInside(pos)) {
            displayError("la tuile ne peut pas être mise ...proposez autre chose");
            pos = new Position(rowGiven(), columnGiven());
        }
        return pos;
    }
    @Override
    public void displayError(String message) {
        System.out.println("erreur détectée : " + message);
    }
    @Override
    public int askUpOrDown() {
        System.out.println("joueur n° " + model.getCurrentPlayerNumber() + " : voulez-vous :");
        System.out.println("1 : une carte face cachée ?");
        System.out.println("2 : une carte face visible ?");
        return reliableInput(1, 2);
    }
    @Override
    public Tile whichVisibleTile() {
        System.out.print("[");
        for (int i = 0; i < model.getAllfaceUpTiles().size(); i++) {
            System.out.print("  "+ String.format("%02d", model.getAllfaceUpTiles().get(i).getValue()) + " ");
        }
        System.out.println("]");
        System.out.println("entrez la position ordinal  de la tuile dans le paquet : ");

        Tile tile = model.getAllfaceUpTiles().get(reliableInput(0, model.getAllfaceUpTiles().size()) - 1);
        while (!tile.isFaceUp()) {
            tile = whichVisibleTile();
        }
        return tile;
    }
    /**
     * this method is set to exhort to user to use an integer which doesn't go
     * beyond the limits
     *
     * @param a : minimum bound
     * @param b : maximum bound
     * @return the value only if it is an integer and respect the limit value
     */
    private int reliableInput2() {
        Scanner clavier = new Scanner(System.in);
        while (!clavier.hasNextInt()) {
            clavier.next();
            displayError("ce que vous avez entrée n'est pas un nombre "
                    + "entier");
        }
        return clavier.nextInt();
    }
    @Override
    public int askPutOrDrop() {
        System.out.println("voulez-vous : ");
        System.out.println(" 1 : mettre la tuile");
        System.out.println(" 2 : redéposer la tuile");
        return reliableInput(1, 2);
    }
}
