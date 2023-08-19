package me.xflyiwnl.iridbot.message;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.VkBotsMethods;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.model.objects.basic.Message;
import me.xflyiwnl.iridbot.object.User;
import me.xflyiwnl.iridbot.object.VkMessage;

public class TestMessage extends VkMessage {

    public TestMessage(String startWith, String endWith) {
        super(startWith, endWith);
    }

    @Override
    public void messageNew(VkBotsMethods vk, String[] args, User user, Message message) {

            Send send = vk.messages.send()
                    .setPeerId(message.getPeerId())
                    .setMessage("Бот работает");

        try {
            send.execute();
        } catch (VkApiException e) {
            throw new RuntimeException(e);
        }

    }

}
