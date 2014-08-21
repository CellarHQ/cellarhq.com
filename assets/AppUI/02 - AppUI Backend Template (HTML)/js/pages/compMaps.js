/*
 *  Document   : compMaps.js
 *  Author     : pixelcave
 *  Description: Custom javascript code used in Maps page
 */

var CompMaps = function() {

    return {
        init: function() {
            /*
             * With Gmaps.js, Check out examples and documentation at http://hpneo.github.io/gmaps/examples.html
             */

            // Initialize terrain map
            new GMaps({
                div: '#gmap-terrain',
                lat: 59.3261419,
                lng: 17.9875456,
                zoom: 14,
                scrollwheel: false
            }).setMapTypeId(google.maps.MapTypeId.TERRAIN);

            // Initialize satellite map
            new GMaps({
                div: '#gmap-satellite',
                lat: 59.3261419,
                lng: 17.9875456,
                zoom: 13,
                scrollwheel: false
            }).setMapTypeId(google.maps.MapTypeId.SATELLITE);

            // Initialize map with markers
            new GMaps({
                div: '#gmap-markers',
                lat: 59.3261419,
                lng: 17.9875456,
                zoom: 11,
                scrollwheel: false
            }).addMarkers([
                {lat: 59.32, lng: 17.97, title: 'Marker #1', animation: google.maps.Animation.DROP, infoWindow: {content: '<strong>Marker #1</strong>'}},
                {lat: 59.35, lng: 17.90, title: 'Marker #2', animation: google.maps.Animation.DROP, infoWindow: {content: '<strong>Marker #2</strong>'}},
                {lat: 59.30, lng: 17.99, title: 'Marker #3', animation: google.maps.Animation.DROP, infoWindow: {content: '<strong>Marker #3</strong>'}},
                {lat: 59.37, lng: 17.95, title: 'Marker #4', animation: google.maps.Animation.DROP, infoWindow: {content: '<strong>Marker #4</strong>'}},
                {lat: 59.32, lng: 17.99, title: 'Marker #5', animation: google.maps.Animation.DROP, infoWindow: {content: '<strong>Marker #5</strong>'}}
            ]);

            // Initialize overlay map
            new GMaps({
                div: '#gmap-overlay',
                lat: 59.3261419,
                lng: 17.9875456,
                zoom: 13,
                scrollwheel: false
            }).drawOverlay({
                lat: 59.3261419,
                lng: 17.9875456,
                content: '<div class="alert alert-info"><h4><i class="fa fa-info-circle"></i> <strong>Information message</strong></h4><p>Some more info here!</p></div>'
            });

            // Initialize map geolocation
            var gmapGeolocation = new GMaps({
                div: '#gmap-geolocation',
                lat: 0,
                lng: 0,
                scrollwheel: false
            });

            GMaps.geolocate({
                success: function(position) {
                    gmapGeolocation.setCenter(position.coords.latitude, position.coords.longitude);
                    gmapGeolocation.addMarker({
                        lat: position.coords.latitude,
                        lng: position.coords.longitude,
                        animation: google.maps.Animation.DROP,
                        title: 'GeoLocation',
                        infoWindow: {
                            content: '<div class="text-success"><i class="fa fa-map-marker"></i> <strong>Your location!</strong></div>'
                        }
                    });
                },
                error: function(error) {
                    alert('Geolocation failed: ' + error.message);
                },
                not_supported: function() {
                    alert("Your browser does not support geolocation");
                },
                always: function() {
                    // Message when geolocation succeed
                }
            });

            // Initialize street view panorama
            new GMaps.createPanorama({
                el: '#gmap-street',
                lat: 36.458963,
                lng: 25.417926,
                pov: {heading: 0, pitch: 4},
                scrollwheel: false
            });
        }
    };
}();