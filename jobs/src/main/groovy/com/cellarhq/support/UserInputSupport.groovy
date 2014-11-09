package com.cellarhq.support

import com.cellarhq.support.userinput.PromptType

trait UserInputSupport {

    String promptForInput(PromptType type, String message) {
        renderPrompt(type, message)

        String input = readInput()
        while (!isInputValid(type, input)) {
            println("Invalid input, must be one of: ${type.allowedValues.keySet().join(',')}")
            input = readInput()
        }
        return input
    }

    void renderSeparator() {
        println('###################################################')
    }

    private String readInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        return br.readLine()
    }

    private void renderPrompt(PromptType type, String prompt) {
        String message = '> '

        if (prompt) {
            message += "${prompt} "
        }

        if (type.hint) {
            message += "(${type.hint})"
        }

        println('')
        println(message.trim() + ":")
    }

    private boolean isInputValid(PromptType promptType, String input) {
        Set<String> allowValues = promptType.allowedValues.keySet()
        return allowValues.size() == 0 || allowValues.contains(input)
    }
}
