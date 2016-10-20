package ru.ecmephi.client.desktop.gui.tool;

import javafx.geometry.Rectangle2D;

public class GUISizer {

    private double width;
    private double height;

    public GUISizer(Rectangle2D visualBounds) {
        width = visualBounds.getWidth();
        height = visualBounds.getHeight();
    }

    public PercentSizeUnit getWidthPercent() {
        return new PercentSizeUnit(width / 100);
    }

    public PercentSizeUnit getHeightPercent() {
        return new PercentSizeUnit(height / 100);
    }

}
