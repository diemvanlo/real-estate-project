var panorama, panorama2, viewer, container, infospot;

container = document.querySelector( '#container' );

panorama = new PANOLENS.ImagePanorama( 'https://scontent.fdad3-2.fna.fbcdn.net/v/t1.15752-9/72215259_246797759583160_4109525988436606976_n.jpg?_nc_cat=105&_nc_oc=AQlZsp7tq5URFAWsF4SRSGwIapcwzkiMp49aByJVFaQ_B_I7PpzNss6r3gsxuXrtqrw&_nc_ht=scontent.fdad3-2.fna&oh=3a7c7b43f9a1eb27bbe979714a7c239c&oe=5E34ACA0' );
panorama2 = new PANOLENS.ImagePanorama( 'https://scontent.fdad3-3.fna.fbcdn.net/v/t1.15752-9/s2048x2048/72544926_2255205761457919_596811157755396096_n.jpg?_nc_cat=109&_nc_oc=AQkJE2dPE73HP4RTkWtt9II4SXZ2aL8_pMpouG5u0d4QdRtOWLeLQZo__Y5aVwshDPw&_nc_ht=scontent.fdad3-3.fna&oh=fa6b0d4157d4519b3841df86e5cf8ab2&oe=5DF111DF' );

infospot = new PANOLENS.Infospot( 500, PANOLENS.DataImage.Info );
infospot.position.set( -100, -500, -5000 );
infospot.addHoverText( "The Story" );
infospot.addEventListener( 'click', function(){
  viewer.setPanorama( panorama2 );
} );

panorama.add( infospot );

viewer = new PANOLENS.Viewer( { container: container } );
viewer.add( panorama, panorama2 );

viewer.addUpdateCallback(function(){
  
});