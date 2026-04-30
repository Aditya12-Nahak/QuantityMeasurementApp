public class QuantityMeasurementApp {

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

        // Convert to another unit
        public Quantity convertTo(LengthUnit target) {
            double base = unit.toBase(value);
            double result = target.fromBase(base);
            return new Quantity(result, target);
        }

        // Add with target unit (UC7 logic reused)
        public Quantity add(Quantity other, LengthUnit target) {
            if (other == null || target == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sum = this.unit.toBase(this.value)
                        + other.unit.toBase(other.value);

            double result = target.fromBase(sum);

            return new Quantity(result, target);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(
                this.unit.toBase(this.value),
                other.unit.toBase(other.value)
            ) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCHES);

        // Conversion
        System.out.println(q1.convertTo(LengthUnit.INCHES));

        // Addition
        System.out.println(q1.add(q2, LengthUnit.FEET));

        // Equality
        System.out.println(q2.equals(new Quantity(1.0, LengthUnit.YARDS)));
    }
}