package by.custom_paint.dto.graphics;

import java.io.Serial;
import java.io.Serializable;

import javafx.scene.paint.Color;

public class ColorDTO implements Serializable {
    private final double red, green, blue;

    private final double opacity;

    @Serial
    private final static long serialVersionUID = 1L;

    public ColorDTO(Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();

        this.opacity = color.getOpacity();
    }

    public Color getColor() {
        return new Color(this.red, this.green, this.blue, this.opacity);
    }
}
