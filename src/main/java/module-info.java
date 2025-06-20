module by.custom_paint {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.compiler;

    opens by.custom_paint to javafx.fxml;
    opens by.custom_paint.controllers to javafx.fxml;
    exports by.custom_paint;

    exports by.custom_paint.models.shapes.base;
    exports by.custom_paint.dto.shapes;
    exports by.custom_paint.services.plugins;
}