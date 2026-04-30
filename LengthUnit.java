public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    private final double toFeet;

    LengthUnit(double toFeet) {
        this.toFeet = toFeet;
    }

    // Convert → base unit (feet)
    public double toBase(double value) {
        return value * toFeet;
    }

    // Convert ← base unit (feet)
    public double fromBase(double baseValue) {
        return baseValue / toFeet;
    }
}