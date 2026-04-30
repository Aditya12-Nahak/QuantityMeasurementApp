public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        // Equality
        System.out.println(w1.equals(w2)); // true

        // Conversion
        System.out.println(w1.convertTo(WeightUnit.GRAM)); // 1000 g

        // Addition
        System.out.println(w1.add(w2)); // 2 kg

        // Target unit addition
        System.out.println(w1.add(w2, WeightUnit.GRAM)); // 2000 g
    }
}