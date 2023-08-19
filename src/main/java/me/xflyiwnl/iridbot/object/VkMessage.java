package me.xflyiwnl.iridbot.object;

import api.longpoll.bots.methods.VkBotsMethods;
import api.longpoll.bots.model.objects.basic.Message;

public abstract class VkMessage implements MessageHandler {

    private String startWith;
    private String endWith;

    public VkMessage(String startWith, String endWith) {
        this.startWith = startWith;
        this.endWith = endWith;
    }

    @Override
    public void messageNew(VkBotsMethods vk, String[] args, User user, Message message) {

    }

    public String getStartWith() {
        return startWith;
    }

    public String getEndWith() {
        return endWith;
    }
}
