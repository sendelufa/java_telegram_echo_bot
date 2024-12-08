# Пример Telegram Echo Bot

Пример создания бота в трех шагах. Каждый шаг это отдельный коммит.

Для работа с серверами телеграм используется библиотека
[rubenlagus/TelegramBots](https://github.com/rubenlagus/TelegramBots)

## Установка Bot Token

Для передачи в приложение значение токена используйте переменную окружения.

Установить переменную с токеном для текущей сессии:

- Linux/macOS

```bash
export BOT_TOKEN=123
```

- Windows (PowerShell)

```powershell
$env:BOT_TOKEN = "123"
```

- Среда разработки Intellij IDEA

В настройках запуска приложения включите возможность задать переменные среды.
Modify Options → Env Variables. В поле **Environment variables** введите
`BOT_TOKEN=123`. Сохраните конфигурацию.