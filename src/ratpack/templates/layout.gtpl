yieldUnescaped '<!DOCTYPE html>'
html(lang: 'en') {
    head {
        meta(charset: 'utf-8')
        title(title ?: 'CellarHQ')
        meta('http-equiv': "Content-Type", content:"text/html; charset=utf-8")
        meta(name: 'viewport', content: 'width=device-width, initial-scale=1.0')
        link(href: '//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css', rel: 'stylesheet')
        link(href: '/styles/cellarhq.css', rel: 'stylesheet')
    }
    body {
        div(class: 'container') {
            div(id: 'header') {
                div(id: 'header-logo') {
                    h1('CellarHQ')
                }
            
                div(id: 'header-ad') {
                    script(type: 'text/javascript',
                        '''<!--
                        google_ad_client = "ca-pub-0146558179277695";
                        /* Top Banner */
                        google_ad_slot = "9274883423";
                        google_ad_width = 728;
                        google_ad_height = 90;
                        //-->''')
                    //script(type: 'text/javascript', src: 'http://pagead2.googlesyndication.com/pagead/show_ads.js')
                }
            }
        }
        div(class: 'container') {
            div(class: 'navbar navbar-inverse', role: 'navigation') {
                div(class: 'navbar-header') {
                    button(type: 'button', class: 'navbar-toggle', 'data-toggle':'collapse', 'data-target': '#main-menu') {
                        span(class: 'sr-only', 'Toggle navigation')
                        span(class: 'icon-bar')
                        span(class: 'icon-bar')
                        span(class: 'icon-bar')
                    }
                }
                div(class: 'navbar-collapse collapse', id:'main-menu') {
                    ul(class: 'nav navbar-nav', role: 'menu') {
                        li { 
                            a(href:'#', 'Cellars')
                        }
                        li { 
                            a(href:'#', 'Breweries')
                        }
                        li { 
                            a(href:'#', 'Beers')
                        }
                    }
                }
            }
        }
        div(class: 'container') {
            if (msg) {
                div(class: 'alert alert-info alert-dismissible') {
                    button(type: 'button', class: 'close', 'data-dismiss': 'alert', 'aria-hidden': 'true', '&times;')
                    yield msg
                }
            }
            bodyContents()
        }

        div(class: 'container navbar-fixed-bottom', id: 'footer') {
            ul {
                li(class: 'footernav') {
                    a(href='#', 'About')
                }
                li(class: 'footernav') {
                    a(href='http://blog.cellarhq.com/', 'Blog')
                }
                li(class: 'footernav') {
                    a(href='https://twitter.com/cellarhq', 'Twitter')  
                }
                li(class: 'footernav noborder', '&copy; CellarHQ 2014')
            }
        }
    }
}
