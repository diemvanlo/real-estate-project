(function () {
    var viewer = new Kaleidoscope.Image({
        source: 'http://thiago.me/image-360/Polie_Academy_53.JPG',
        containerId: '#container360',
        height: window.innerHeight,
        width: window.innerWidth,
    });
    viewer.render();

    window.onresize = function () {
        viewer.setSize({height: window.innerHeight, width: window.innerWidth});
    };
})();