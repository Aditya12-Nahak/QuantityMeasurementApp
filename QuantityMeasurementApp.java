public class QuantityMeasurementApp {

    // ===== ENUM (All units converted to FEET as base) =====
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETER(0.0328084); // 1 cm = 0.0328084 feet

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }
    }

    // ===== GENERIC CLASS =====
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        // Yard ↔ Feet
        System.out.println(
            new Quantity(1.0, LengthUnit.YARDS)
            .equals(new Quantity(3.0, LengthUnit.FEET))
        );

        // Yard ↔ Inches
        System.out.println(
            new Quantity(1.0, LengthUnit.YARDS)
            .equals(new Quantity(36.0, LengthUnit.INCH))
        );

        // CM ↔ Inches
        System.out.println(
            new Quantity(1.0, LengthUnit.CENTIMETER)
            .equals(new Quantity(0.393701, LengthUnit.INCH))

        System.out.println(
            new Quantity(2.0, LengthUnit.YARDS)
            .equals(new Quantity(2.0, LengthUnit.YARDS))
        );
    }
}