var geocoder;
		var carte;
		
			function initialiser() {

				geocoder = new google.maps.Geocoder();				
				var latlng = new google.maps.LatLng(48.817221, 2.297347);
				//objet contenant des propriétés avec des identificateurs prédéfinis dans Google Maps permettant
				//de définir des options d'affichage de notre carte
				var options = {
					center: latlng,
					zoom: 13,
				};
				
				//constructeur de la carte qui prend en paramêtre le conteneur HTML
				//dans lequel la carte doit s'afficher et les options
				carte = new google.maps.Map(document.getElementById("carte"), options);
				
				//Création d'un marqueur
				var marqueur = new google.maps.Marker({
					position: new google.maps.LatLng(46.779331, 6.659431),
					map: carte,
					draggable: true,
					title: 'un marqueur',
					icon: "http://images.clipartpanda.com/google-map-icon-14165-google-maps-pin-blue-design.png"
				});
			}
				
				
			function codeAddress() {
				var address = "#{mbPublic.adressErp}";
				geocoder.geocode( { 'address': address}, function(results, status) {
					if (status == google.maps.GeocoderStatus.OK) {
						carte.setCenter(results[0].geometry.location);
						var marker = new google.maps.Marker({
							map: carte,
							position: results[0].geometry.location
						});
					} else {
						alert("Geocode was not successful for the following reason: " + status);
					}
				});
			}