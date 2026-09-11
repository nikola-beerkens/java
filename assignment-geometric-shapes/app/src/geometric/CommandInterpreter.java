package geometric;

public class CommandInterpreter {
    private final Functions functions;
    private int nextId = 1;

    public CommandInterpreter(Functions functions) {
        this.functions = functions;
    }

    public String execute(CommandParser.Command command) {
        if (command == null) {
            return "Invalid command.";
        }

        try {
            String[] arguments = command.getArguments();
            switch (command.getName()) {
                case "quit":
                    return null;
                case "show":
                    requireArguments(arguments, 0);
                    return functions.show();
                case "circle":
                    requireArguments(arguments, 3);
                    functions.createCircle(number(arguments[0]), number(arguments[1]), number(arguments[2]), nextId++);
                    return "Circle added.";
                case "rectangle":
                    requireArguments(arguments, 4);
                    functions.createRectangle(number(arguments[0]), number(arguments[1]), number(arguments[2]), number(arguments[3]), nextId++);
                    return "Rectangle added.";
                case "move":
                    requireArguments(arguments, 3);
                    int moveIndex = integer(arguments[0]) - 1;
                    checkIndex(moveIndex);
                    functions.moveObject(moveIndex, number(arguments[1]), number(arguments[2]));
                    return "Object has been moved.";
                case "remove":
                    requireArguments(arguments, 1);
                    int removeIndex = integer(arguments[0]) - 1;
                    checkIndex(removeIndex);
                    functions.remove(removeIndex);
                    return "Object removed.";
                case "sort":
                    requireArguments(arguments, 1);
                    sort(arguments[0]);
                    return functions.show();
                default:
                    return "Invalid command. Type help to see the available commands.";
            }
        } catch (IllegalArgumentException exception) {
            return "Error: " + exception.getMessage();
        }
    }

    private void sort(String choice) {
        switch (choice.toLowerCase()) {
            case "x":
                functions.sortByX();
                break;
            case "y":
                functions.sortByY();
                break;
            case "area":
                functions.sortByArea();
                break;
            default:
                throw new IllegalArgumentException("sort expects x, y, or area.");
        }
    }

    private void requireArguments(String[] arguments, int expected) {
        if (arguments.length != expected) {
            throw new IllegalArgumentException("expected " + expected + " argument(s).");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= functions.showNumber()) {
            throw new IllegalArgumentException("object index is out of bounds.");
        }
    }

    private double number(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("expected numbers.");
        }
    }

    private int integer(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("expected an integer index.");
        }
    }
}
