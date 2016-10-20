package ru.ecmephi.client.desktop.gui.tool;

public class PercentSizeUnit {

    private double value;

    PercentSizeUnit(double value) {
        this.value = value;
    }

    public PercentSizeUnit multiply(double multiplier) {
        value *= multiplier;
        return this;
    }

    public double getDoubleValue() {
        return value;
    }

}
