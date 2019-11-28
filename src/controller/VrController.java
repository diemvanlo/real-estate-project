package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class VrController extends Application {
    private static String readLineByLineJava(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    @Override
    public void start(final Stage stage) {

        Button buttonURL = new Button("Load Page https://eclipse.org");
        Button buttonHtmlString = new Button("Load HTML String");
        Button buttonHtmlFile = new Button("Load File C:/test/a.html");

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        buttonURL.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String url = "https://eclipse.org";

                // Tải một trang HTML từ url.
                webEngine.load(url);
            }
        });

        buttonHtmlString.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    System.out.println(getClass().getResource("full.js").toURI());
                    System.out.println(readLineByLineJava("C:/Users/TechCare/Desktop/sample/realEstate/out/production/realEstate/controller/kaleidoscope.min.js"));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                String html = null;
                try {
                    html = "<!DOCTYPE HTML>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "    <meta charset=\"utf-8\">\n" +
                            "    <title>Kaleidoscope image example</title>\n" +
                            "<script src=" + getClass().getResource("full.js").toURI() + " type=\"text/javascript\"" + "></script>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<h1>gggg</h1>\n" +
                            "        <div id=\"container360\"></div>" +
                            "<script src=\"" + getClass().getResource("test.js") + "\"" + " type=\"text/javascript\"" + "></script>\n" +
                            "</body>\n" +
                            "</html>";
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                // Tải HTML String
                webEngine.setJavaScriptEnabled(true);
                //webEngine.executeScript(readLineByLineJava("C:/Users/TechCare/Desktop/sample/realEstate/out/production/realEstate/controller/kaleidoscope.min.js"));
                webEngine.executeScript("var imported = document.createElement('script');\n" +
                        "imported.src = '/kaleidoscope.min.js';\n" +
                        "document.head.appendChild(imported);" +
                        "(function () {\n" +
                        "    var viewer = new Kaleidoscope.Image({\n" +
                        "        source: 'http://thiago.me/image-360/Polie_Academy_53.JPG',\n" +
                        "        containerId: '#container360',\n" +
                        "        height: window.innerHeight,\n" +
                        "        width: window.innerWidth,\n" +
                        "    });\n" +
                        "    viewer.render();\n" +
                        "\n" +
                        "    window.onresize = function () {\n" +
                        "        viewer.setSize({height: window.innerHeight, width: window.innerWidth});\n" +
                        "    };\n" +
                        "})();");
                webEngine.loadContent(html);
            }
        });
        buttonHtmlFile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    File file = new File("image.html");
                    URL url = file.toURI().toURL();
                    // file:/C:/test/a.html
                    System.out.println("Local URL: " + url.toString());
//                    url = getClass().getResource("C:/Users/TechCare/Desktop/sample/realEstate/mage.html");
                    webEngine.load(url.toExternalForm());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        });

        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(5);
        root.getChildren().addAll(buttonURL, buttonHtmlString, buttonHtmlFile, browser);

        Scene scene = new Scene(root);

        stage.setTitle("JavaFX WebView (o7planning.org)");
        stage.setScene(scene);
        stage.setWidth(450);
        stage.setHeight(300);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}