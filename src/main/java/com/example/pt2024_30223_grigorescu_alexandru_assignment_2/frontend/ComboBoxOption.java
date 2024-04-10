package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend;

public class ComboBoxOption {
    private final String label;
    private final String value;

    public ComboBoxOption(String label, String value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() { return label; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
}
