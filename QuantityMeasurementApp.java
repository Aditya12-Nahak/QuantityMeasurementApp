public class QuantityMeasurementApp {

    // ===== ENUM =====
    enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeet;
        }
    }

    // ===== CONVERSION API =====
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        // Validation
        if (source == null || target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        // Step 1: Convert to base (feet)
        double base = source.toFeet(value);

        // Step 2: Convert to target
        return target.fromFeet(base);
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        System.out.println("1 ft → inch: " +
                convert(1.0, LengthUnit.FEET, LengthUnit.INCHES));

        System.out.println("3 yards → feet: " +
                convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));

        System.out.println("36 inch → yard: " +
                convert(36.0, LengthUnit.INCHES, LengthUnit.YARDS));

        System.out.println("1 cm → inch: " +
                convert(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES));
    }
}