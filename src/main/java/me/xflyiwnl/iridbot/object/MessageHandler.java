package me.xflyiwnl.iridbot.object;

import api.longpoll.bots.methods.VkBotsMethods;
import api.longpoll.bots.model.objects.basic.Message;

public interface MessageHandler {

    void messageNew(VkBotsMethods vk, String[] args, User user, Message message);

}
