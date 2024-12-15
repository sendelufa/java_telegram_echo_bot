package bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

/**
 * Действие при /start команды
 */
public class StartCommand implements Command {

    private final String startText = """
            Этот бот позволяет узнать свой ID в мессенджере Telegram.
            Для это используйте команду /myid""";

    private final TelegramClient client;

    public StartCommand(TelegramClient client) {
        this.client = client;
    }

    @Override
    public void execute(Update update) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(startText)
                .build();

        try {
            client.execute(sendMessage);
        } catch (TelegramApiException e) {
            System.err.println("Не удалось отправить сообщение в ответ на команду /start");
            e.printStackTrace();
        }
    }
}
