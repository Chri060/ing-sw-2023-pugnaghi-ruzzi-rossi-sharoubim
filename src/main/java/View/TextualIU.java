package View;

import Distributed.Messages.clientMessages.ClientMessage;
import Model.entities.Card;
import Model.viewEntities.PlayerView;
import util.Observable;

import java.util.List;

public class TextualIU extends View {

    @Override
    public void printDashboard() {
        Card[][] dashboard = this.model.getDashboard();
        printMatrix(dashboard);
    }

    @Override
    public void printMyShelf() {
        List<PlayerView> playerViewList = model.getPlayerViews();
        System.out.println("My Shelf");
        playerViewList.stream().filter(x -> x.getName().equals(name)).forEach(x -> printMatrix(x.getShelf()));
    }

    @Override
    public void printAllShelfs() {
        List<PlayerView> playerViewList = model.getPlayerViews();
        playerViewList.stream().filter(x -> !x.getName().equals(name)).forEach(x -> {
            System.out.println(x.getName() +"'s Shelf");
            printMatrix(x.getShelf());
        });
    }

    @Override
    public void printCommonObjectives() {

    }

    @Override
    public void update() {
        printDashboard();
        printMyShelf();
        printAllShelfs();
    }

    private void printMatrix(Card[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        System.out.print(" ");
        for (int i = 0; i < columns; i++) {
            System.out.print(" " + i + "");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i);
            for (int j = 0; j < columns; j++) {
                System.out.print("|");
                if (matrix[i][j] == null) {
                    System.out.print(" ");
                }
                else {
                    switch (matrix[i][j].getType()) {
                        case PLANT -> System.out.print("P");
                        case CAT -> System.out.print("C");
                        case TROPHY -> System.out.print("T");
                        case BOOK -> System.out.print("B");
                        case FRAME -> System.out.print("F");
                        case GAME -> System.out.print("G");
                    }
                }
            }
            System.out.println("|");
        }
    }
}
