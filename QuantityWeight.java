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
// ===== SUBTRACTION (default unit) =====
public Quantity<U> subtract(Quantity<U> other) {
    return subtract(other, this.unit);
}

// ===== SUBTRACTION (target unit) =====
public Quantity<U> subtract(Quantity<U> other, U target) {

    if (other == null || target == null) {
        throw new IllegalArgumentException("Invalid input");
    }

    // Prevent cross-category
    if (this.unit.getClass() != other.unit.getClass()) {
        throw new IllegalArgumentException("Different measurement categories");
    }

    double resultBase = this.toBase() - other.toBase();

    double result = target.fromBase(resultBase);

    // Round to 2 decimal places
    result = Math.round(result * 100.0) / 100.0;

    return new Quantity<>(result, target);
}
// ===== DIVISION =====
public double divide(Quantity<U> other) {

    if (other == null) {
        throw new IllegalArgumentException("Invalid input");
    }

    // Prevent cross-category
    if (this.unit.getClass() != other.unit.getClass()) {
        throw new IllegalArgumentException("Different measurement categories");
    }

    double divisor = other.toBase();

    if (divisor == 0) {
        throw new ArithmeticException("Division by zero");
    }

    return this.toBase() / divisor;
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