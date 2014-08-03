yieldUnescaped '<!DOCTYPE html>'
html(lang: 'en') {
    head {
        meta(charset: 'utf-8')
        title(title ?: 'CellarHQ')
        meta('http-equiv': "Content-Type", content:"text/html; charset=utf-8")
        meta(name: 'viewport', content: 'width=device-width, initial-scale=1.0')
        link(href: '/lib/normalize/noramlize.css', rel: 'stylesheet')
        link(href: '/styles/ratpack.css', rel: 'stylesheet')
        script(href: '/lib/modernizr/modernizr.js') {}
    }
    body {
        div(class: 'container') {
            if (msg) {
                div(class: 'alert alert-info alert-dismissible') {
                    button(type: 'button', class: 'close', 'data-dismiss': 'alert', 'aria-hidden': 'true', '&times;')
                    yield msg
                }
            }
            bodyContents()
        }
    }
}
