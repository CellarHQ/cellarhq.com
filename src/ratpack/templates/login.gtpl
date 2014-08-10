layout 'layout.gtpl',
title: title,
error: error,
loggedIn: loggedIn,
pageId: 'login',
bodyContents: contents {
    h1('Login')
    div(class: 'alert alert-info') {
        yield 'A matching username and password will pass authentication'
    }
    includeGroovy '_login-form.gtpl'
}
