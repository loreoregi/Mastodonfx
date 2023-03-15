module eus.ehu.bum4fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.kordamp.bootstrapfx.core;
    requires okhttp3;
    requires com.google.gson;
    requires jdk.xml.dom;
    requires java.datatransfer;
    requires java.desktop;

    opens eus.ehu.bum4fx to javafx.fxml;
    exports eus.ehu.bum4fx;
    exports eus.ehu.bum4fx.domain;
    opens eus.ehu.bum4fx.domain to javafx.fxml, com.google.gson;

}