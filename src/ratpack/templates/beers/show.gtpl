layout 'layout.gtpl',
title: 'CellarHQ | ' + drink.name,
bodyContents: contents {
	div(class: 'row') {
		div(class: 'col-md-4') {
			img(src:'https://s3.amazonaws.com/brewerydbapi/beer/qRuupG/upload_ei0JGN-medium.png', class: 'img-responsive img-thumbnail')
		}
		div(class: 'cold-md-8') {
			h1(drink.organization.name + ' ' + drink.name)
			div(id: 'basic-info') {
				span(id: 'style') {
					strong('Style :')
					span(drink.style.name)
				}
				span(id: 'availability') {
					strong('Available: ')
					span(drink.availability)
				}
				span(id: 'abv') {
						span(drink.abv + '% ')
						attr(title: 'Alcohol By Volume', 'ABV')
				}
			}
			div(id: 'description') {
				p(drink.description)
			} 
		}
	}
}