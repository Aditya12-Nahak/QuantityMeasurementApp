public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKg;

    WeightUnit(double toKg) {
        this.toKg = toKg;
    }

    // Convert → base (kg)
    public double toBase(double value) {
        return value * toKg;
    }

    // Convert ← base (kg)
    public double fromBase(double baseValue) {
        return baseValue / toKg;
    }
}