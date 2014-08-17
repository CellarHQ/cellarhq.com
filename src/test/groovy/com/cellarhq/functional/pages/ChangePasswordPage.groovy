package com.cellarhq.functional.pages

class ChangePasswordPage extends BasePage {

    static at = { pageId ==~ /change-password/ }
    static content = {
        changePasswordForm(wait: true) { $('#change-password-form') }
        submitButton(wait: true) { $('#change-password-form button[type=submit]') }
    }

    void fillForm(String newPassword, String confirmPassword = null) {
        changePasswordForm.password = newPassword
        changePasswordForm.passwordConfirm = confirmPassword ?: newPassword
    }

    void submit() {
        submitButton.click()
    }
}
