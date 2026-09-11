package geometric;

import java.util.Scanner;

public class IO {
    private final Scanner scanner = new Scanner(System.in);
    private final CommandParser parser = new CommandParser();
    private final CommandInterpreter interpreter = new CommandInterpreter(new Functions());

    public void start() {
        printOptions();
        boolean running = true;
        while (running && scanner.hasNextLine()) {
            System.out.print("Input command: ");
            CommandParser.Command command = parser.parse(scanner.nextLine());
            if (command == null) {
                System.out.println("Invalid command. Type help to see the available commands.");
                continue;
            }
            if (command.getName().equals("help")) {
                printOptions();
                continue;
            }
            if (command.getArguments().length == 0 && needsArguments(command.getName())) {
                printPrompt(command.getName());
                command = parser.parse(command.getName() + " " + scanner.nextLine());
            }
            running = !command.getName().equals("quit");
            String result = interpreter.execute(command);
            if (result != null) {
                System.out.println(result);
            }
        }
    }

    private boolean needsArguments(String command) {
        return command.equals("circle") || command.equals("rectangle")
                || command.equals("move") || command.equals("remove") || command.equals("sort");
    }

    private void printPrompt(String command) {
        switch (command) {
            case "circle":
                System.out.println("Enter x, y, and radius separated by spaces:");
                break;
            case "rectangle":
                System.out.println("Enter x, y, height, and length separated by spaces:");
                break;
            case "move":
                System.out.println("Enter index, dx, and dy separated by spaces:");
                break;
            case "remove":
                System.out.println("Enter the object index:");
                break;
            case "sort":
                System.out.println("Enter x, y, or area:");
                break;
            default:
                break;
        }
    }

    private void printOptions() {
        System.out.println("Available commands:");
        System.out.println("circle x y radius");
        System.out.println("rectangle x y height length");
        System.out.println("move index dx dy");
        System.out.println("remove index");
        System.out.println("sort x|y|area");
        System.out.println("show");
        System.out.println("help");
        System.out.println("quit");
    }
}
