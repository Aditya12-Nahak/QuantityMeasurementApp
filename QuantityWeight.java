import java.util.function.DoubleBinaryOperator;

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

    // ===== ENUM for operations =====
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator op;

        ArithmeticOperation(DoubleBinaryOperator op) {
            this.op = op;
        }

        public double apply(double a, double b) {
            return op.applyAsDouble(a, b);
        }
    }

    // ===== CENTRAL VALIDATION =====
    private void validate(Quantity<U> other, U target, boolean needTarget) {

        if (other == null) {
            throw new IllegalArgumentException("Other quantity is null");
        }

        if (this.unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException("Different measurement categories");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric values");
        }

        if (needTarget && target == null) {
            throw new IllegalArgumentException("Target unit is null");
        }
    }

    // ===== CORE HELPER =====
    private double performBaseOperation(Quantity<U> other, ArithmeticOperation op) {
        double base1 = this.toBase();
        double base2 = other.toBase();
        return op.apply(base1, base2);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // ===== ADD =====
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U target) {
        validate(other, target, true);
        double base = performBaseOperation(other, ArithmeticOperation.ADD);
        return new Quantity<>(round(target.fromBase(base)), target);
    }

    // ===== SUBTRACT =====
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U target) {
        validate(other, target, true);
        double base = performBaseOperation(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(round(target.fromBase(base)), target);
    }

    // ===== DIVIDE =====
    public double divide(Quantity<U> other) {
        validate(other, null, false);
        return performBaseOperation(other, ArithmeticOperation.DIVIDE);
    }

    // ===== CONVERSION =====
    public Quantity<U> convertTo(U target) {
        double base = this.toBase();
        return new Quantity<>(target.fromBase(base), target);
    }

    // ===== EQUALITY =====
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (this.unit.getClass() != other.unit.getClass()) return false;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}