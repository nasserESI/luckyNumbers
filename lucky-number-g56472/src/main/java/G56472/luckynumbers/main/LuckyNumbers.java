package G56472.luckynumbers.main;
import G56472.luckynumbers.controller.Controller;
import G56472.luckynumbers.model.*;
import G56472.luckynumbers.view.MyView;
/**
 * this class is going to run the game trough a methode (in another class
 * especially set for the purpose of controle) which has the whole sequence of
 * the game and keep going as loang as the expression of the "while in braket is
 * true. so that we can trully play as much round as required.
 *
 * @author Nasse
 */
public class LuckyNumbers {
    public static void main(String[] args) {
        Game game = new Game();
        Controller controller = new Controller(game, new MyView(game));
        controller.play();
    }
}
