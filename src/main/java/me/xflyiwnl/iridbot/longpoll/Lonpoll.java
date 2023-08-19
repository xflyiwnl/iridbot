package me.xflyiwnl.iridbot.longpoll;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;
import me.xflyiwnl.iridbot.Main;
import me.xflyiwnl.iridbot.object.User;
import me.xflyiwnl.iridbot.object.VkMessage;
import me.xflyiwnl.iridbot.util.MessageUtil;

public class Lonpoll extends LongPollBot {

    private String token;

    public Lonpoll(String token) {
        this.token = token;
    }

    @Override
    public void onMessageNew(MessageNew messageNew) {

        long afterMillis = System.currentTimeMillis();

        String text = messageNew.getMessage().getText();
        String[] args = MessageUtil.splitMessage(messageNew.getMessage().getText());
        VkMessage message = Main.getInstance().getMessages().get(args[0]);

        if (message == null) {
            message = Main.getInstance().getMessages().get("");
            if (message == null) {
                return;
            }
        }

        if (message.getStartWith() != null && !text.startsWith(message.getStartWith()) ||
                message.getEndWith() != null && !text.endsWith(message.getEndWith())) {
            return;
        }

        try {
            vk.messages.setActivity().setType("typing").setPeerId(messageNew.getMessage().getPeerId())
                    .execute();
        } catch (VkApiException e) {
            throw new RuntimeException(e);
        }

        User user = getUserOrCreate(messageNew.getMessage());

        message.messageNew(vk, args, user, messageNew.getMessage());

        long beforeMillis = System.currentTimeMillis();

        System.out.println("Обработано сообщение за " + (beforeMillis - afterMillis) + "ms / " + messageNew.getMessage().getFromId());

    }

    public User getUserOrCreate(Message message) {
        User user = Main.getInstance().getUser(message.getPeerId());
        if (user == null) {
            user = new User(message.getFromId(), 0);
            user.create(true);
        }
        return user;
    }

    @Override
    public String getAccessToken() {
        return token;
    }

}
