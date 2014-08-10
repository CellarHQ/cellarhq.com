layout 'layout.gtpl',
title: title,
loggedIn: loggedIn,
pageId: 'home',
bodyContents: contents {
    header {
        h1('Ratpack')
        p('a micro web framework for Java &amp; Groovy')
    }
    section {
        h1(model.title)
        p('This is the main page for your Ratpack app.')
    }
    table(class: 'table table-striped table-bordered') {
        thead {
            tr {
                th('ID')
                th('Screen Name')
            }
        }
        tbody { cellars.each { cellar ->
            tr {
                td(cellar.id)
                td(cellar.displayName)
            }
        }}
    }
}
