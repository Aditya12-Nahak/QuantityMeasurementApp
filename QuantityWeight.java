public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.toBase(value);
    }

    // ===== EQUALITY =====
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Prevent cross-category comparison
        if (this.unit.getClass() != other.unit.getClass()) {
            return false;
        }

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    // ===== CONVERSION =====
    public Quantity<U> convertTo(U target) {
        double base = this.toBase();
        double result = target.fromBase(base);
        return new Quantity<>(result, target);
    }

    // ===== ADDITION =====
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U target) {
        if (other == null || target == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double sum = this.toBase() + other.toBase();
        double result = target.fromBase(sum);

        return new Quantity<>(result, target);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}