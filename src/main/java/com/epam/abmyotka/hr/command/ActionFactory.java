package com.epam.abmyotka.hr.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ActionFactory {
    private final static Logger LOGGER = LogManager.getLogger(ActionFactory.class);

    public static Optional<Command> defineCommand(String commandName) {
        Optional<Command> current = Optional.empty();
        if (commandName == null) {
            return current;
        }
        try {
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = Optional.of(type.getCommand());
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.ERROR, "Illegal argument type of \"Command\"! Detail: " + e.getMessage());
        }
        return current;
    }
}
