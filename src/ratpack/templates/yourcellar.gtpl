layout 'layout.gtpl',
title: title,
error: error,
loggedIn: loggedIn,
pageId: 'yourcellar',
bodyContents: contents {
    h1('Your Cellar')
    h2("YOU ARE DEFINITELY PROBABLY @${username}")
}
