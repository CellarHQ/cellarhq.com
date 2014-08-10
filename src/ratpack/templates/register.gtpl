layout 'layout.gtpl',
title: title,
error: error,
loggedIn: loggedIn,
pageId: 'register',
bodyContents: contents {
    h1('Register')
    includeGroovy '_register-form.gtpl'
}
