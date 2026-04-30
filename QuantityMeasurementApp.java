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

    // ===== QUANTITY CLASS =====
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toFeet(value);
        }

        // ===== ADD METHOD =====
        public Quantity add(Quantity other) {
            if (other == null) {
                throw new IllegalArgumentException("Second operand is null");
            }

            // Convert both to base (feet)
            double sumInFeet = this.toBase() + other.toBase();

            // Convert back to unit of FIRST operand
            double resultValue = unit.fromFeet(sumInFeet);

            return new Quantity(resultValue, this.unit);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        // Same unit
        System.out.println(
            new Quantity(1.0, LengthUnit.FEET)
            .add(new Quantity(2.0, LengthUnit.FEET))
        );

        // Cross unit
        System.out.println(
            new Quantity(1.0, LengthUnit.FEET)
            .add(new Quantity(12.0, LengthUnit.INCHES))
        );

        // Reverse (result in inches)
        System.out.println(
            new Quantity(12.0, LengthUnit.INCHES)
            .add(new Quantity(1.0, LengthUnit.FEET))
        );

        // Yards example
        System.out.println(
            new Quantity(1.0, LengthUnit.YARDS)
            .add(new Quantity(3.0, LengthUnit.FEET))
        );

        // CM example
        System.out.println(
            new Quantity(2.54, LengthUnit.CENTIMETERS)
            .add(new Quantity(1.0, LengthUnit.INCHES))
        );
    }
}