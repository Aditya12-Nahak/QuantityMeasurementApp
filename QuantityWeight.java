public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.toBase(value);
    }

    // ===== Equality =====
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    // ===== Conversion =====
    public QuantityWeight convertTo(WeightUnit target) {
        double base = this.toBase();
        double result = target.fromBase(base);
        return new QuantityWeight(result, target);
    }

    // ===== Addition (default) =====
    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    // ===== Addition (target unit) =====
    public QuantityWeight add(QuantityWeight other, WeightUnit target) {
        if (other == null || target == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double sum = this.toBase() + other.toBase();
        double result = target.fromBase(sum);

        return new QuantityWeight(result, target);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}