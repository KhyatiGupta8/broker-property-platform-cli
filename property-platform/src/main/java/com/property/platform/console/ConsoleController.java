package com.property.platform.console;

import com.property.platform.exception.CommandException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleController {
    private final CommandFactory factory;

    public void start() {
        System.out.println("Property Platform CLI (HELP for commands; EXIT to quit)");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("EXIT")) {
                System.out.println("Bye!");
                break;
            }
            if (line.isBlank()) continue;
            try {
                Command cmd = factory.get(line);
                cmd.execute();
            } catch (CommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}