import menu.Menu;
import menu.MenuGym;

public class Main {
    public static void main(String[] args) {
        Menu menu = new MenuGym(); // Полиморфизм через интерфейс
        menu.displayMenu();
    }
}