module eus.ehu.bum4fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens eus.ehu.bum4fx to javafx.fxml;
    exports eus.ehu.bum4fx;
}