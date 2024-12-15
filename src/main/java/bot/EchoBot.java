package bot;

import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class EchoBot implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;
    private final CommandDispatcher commandDispatcher;

    public EchoBot(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
        this.commandDispatcher = new CommandDispatcher(telegramClient);
    }

    @Override
    public void consume(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            System.out.println("There is no any text in Update" + update.getUpdateId());
            return;
        }

        String messageText = update.getMessage().getText().stripLeading();

        if(messageText.startsWith("/")) {
            commandDispatcher.dispatch(messageText, update);
            return;
        }

        long chatId = update.getMessage().getChatId();

        System.out.println(messageText + "from chatId=" + chatId);

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(messageText)
                .build();

        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
