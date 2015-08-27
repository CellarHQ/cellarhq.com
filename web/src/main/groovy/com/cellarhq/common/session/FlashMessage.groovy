package com.cellarhq.common.session

import groovy.transform.CompileStatic

@CompileStatic
class FlashMessage implements Serializable {

    static enum Type {
        ERROR('error'),
        WARNING('warning'),
        INFO('info'),
        SUCCESS('success')

        private final String value

        Type(String value) {
            this.value = value
        }

        String getName() {
            return value
        }

        String getMessagesName() {
            return "${value}Messages"
        }
    }

    static class EmbeddedMessage {
        String message

        EmbeddedMessage(String message) {
            this.message = message
        }
    }

    static class SocialButton {
        String message
        String url

        SocialButton(String message, String url) {
            this.message = message
            this.url = url
        }
    }

    static FlashMessage error(String message, List<String> embedded = null) {
        return new FlashMessage(Type.ERROR, message, embedded)
    }

    static FlashMessage warning(String message, List<String> embedded = null) {
        return new FlashMessage(Type.WARNING, message, embedded)
    }

    static FlashMessage info(String message, List<String> embedded = null) {
        return new FlashMessage(Type.INFO, message, embedded)
    }

    static FlashMessage success(String message, List<String> embedded = null) {
        return new FlashMessage(Type.SUCCESS, message, embedded)
    }

    static FlashMessage success(String message, SocialButton socialButton) {
        FlashMessage flash = new FlashMessage(Type.SUCCESS, message, null)
        flash.socialButton = socialButton
        return flash
    }

    Type type
    String message
    List<EmbeddedMessage> embeddedMessages = []
    SocialButton socialButton

    FlashMessage(Type type, String message, List<String> messages) {
        this.type = type
        this.message = message
        setMessages(messages)
    }

    void setMessages(List<String> messages) {
        if (messages) {
            embeddedMessages = messages.collect { String msg -> new EmbeddedMessage(msg) }
        }
    }

    String getSessionKey() {
        return type.name
    }
}
