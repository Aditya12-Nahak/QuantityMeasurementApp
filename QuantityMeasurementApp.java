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

        // ===== UC6 METHOD (default) =====
        public Quantity add(Quantity other) {
            return add(other, this.unit);
        }

        // ===== UC7 METHOD (target unit) =====
        public Quantity add(Quantity other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            // Convert both → base (feet)
            double sum = this.toBase() + other.toBase();

            // Convert → target unit
            double result = targetUnit.fromFeet(sum);

            return new Quantity(result, targetUnit);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        Quantity a = new Quantity(1.0, LengthUnit.FEET);
        Quantity b = new Quantity(12.0, LengthUnit.INCHES);

        System.out.println(a.add(b, LengthUnit.FEET));       // 2 feet
        System.out.println(a.add(b, LengthUnit.INCHES));     // 24 inches
        System.out.println(a.add(b, LengthUnit.YARDS));      // ~0.667 yards

        System.out.println(
            new Quantity(36.0, LengthUnit.INCHES)
            .add(new Quantity(1.0, LengthUnit.YARDS), LengthUnit.FEET)
        );
    }
}