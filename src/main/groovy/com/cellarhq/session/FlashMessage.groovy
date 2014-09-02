package com.cellarhq.session

import groovy.transform.CompileStatic

@CompileStatic
class FlashMessage {

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

    Type type
    String message
    List<EmbeddedMessage> embeddedMessages = []

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
