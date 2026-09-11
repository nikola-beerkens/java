package geometric;

import java.util.Arrays;

public class CommandParser {
    public Command parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        String[] tokens = input.trim().split("\\s+");
        return new Command(tokens[0].toLowerCase(), Arrays.copyOfRange(tokens, 1, tokens.length));
    }

    public static class Command {
        private final String name;
        private final String[] arguments;

        private Command(String name, String[] arguments) {
            this.name = name;
            this.arguments = arguments;
        }

        public String getName() {
            return name;
        }

        public String[] getArguments() {
            return arguments;
        }
    }
}
