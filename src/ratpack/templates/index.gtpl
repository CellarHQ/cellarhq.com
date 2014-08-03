layout 'layout.gtpl',
title: title,
bodyContents: contents {
    header {
        h1('Ratpack')
        p('a micro web framework for Java &amp; Groovy')
    }
    section {
        h1(model.title)
        p('This is the main page for your Ratpack app.')
    }
}
