if (error) {
    div(class: 'alert alert-danger alert-dismissible') {
        button(type: "button", class: "close", 'data-dismiss': "alert", 'aria-hidden': "true", '&times')
        yield error
    }
}

div {
    a(href: '/auth-twitter', id: 'twitter-login-btn') {
        img(src: '/images/sign-in-with-twitter-gray.png', alt: 'Sign in with Twitter'){}
    }
}

def formGroup = [class: 'form-group']
def column = [class: 'col-sm-10']
def columnOffset = [class: 'col-sm-offset-2 col-sm-10']
def controlLabel(id) { [for: id, class: 'col-sm-2 control label'] }
def inputAttr(id, Map opts = [:]) { [name: id, class: 'form-control', id: id, value: ''] + opts }

form(class: 'form-horizontal', role: 'form', method: method, action: action) {
    div(formGroup) {
        label(controlLabel('username'), 'Username')
        div(column) {
            input(inputAttr('username', [type: 'text'])) {}
        }
    }
    div(formGroup) {
        label(controlLabel('password'), 'Password')
        div(column) {
            input(inputAttr('password', [type: 'password'])) {}
        }
    }
    div(formGroup) {
        div(columnOffset) {
            button(type: 'submit', class: 'btn btn-primary', buttonText)
            a(href: '/', class: 'btn btn-link', 'cancel')
        }
    }
    input(inputAttr('client_name', [type: 'hidden', value: 'FormClient'])) {}
}
