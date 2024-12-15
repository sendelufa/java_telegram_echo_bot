package bot;

import bot.command.Command;
import bot.command.StartCommand;
import bot.command.UserIdCommand;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.HashMap;
import java.util.Map;

public class CommandDispatcher {
    private final Map<String, Command> commandMap = new HashMap<>();

    public CommandDispatcher(TelegramClient telegramClient) {
        commandMap.put("/start", new StartCommand(telegramClient));
        commandMap.put("/myid", new UserIdCommand(telegramClient));
    }

    public void dispatch(String commandText, Update update) {
        Command command = commandMap.get(commandText.split("\\s")[0].toLowerCase());
        System.out.printf("Обработка команды %s%n", commandText);
        if (command != null) {
            command.execute(update);
        } else {
            System.out.printf("Команда %s не найдена%n", commandText);
        }
    }
}
