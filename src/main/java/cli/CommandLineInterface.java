package cli;
import java.util.Scanner;

public class CommandLineInterface {
    Scanner scanner = new Scanner(System.in);
    public void startUp() {
        System.out.println("Hallo!");
        mainMenu();
    }
    private void mainMenu() {
        while(true) {
            System.out.print(">");
            String input = scanner.nextLine();

        }
    }
    private void handleCommand(String input) {
        switch(input.toLowerCase()) {
            case "hallo":
                System.out.println("Hallo 2!");
                break;
        }
    }

}
