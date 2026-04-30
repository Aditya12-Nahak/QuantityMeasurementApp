public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        // Equality
        System.out.println(v1.equals(v2)); // true

        // Conversion
        System.out.println(v1.convertTo(VolumeUnit.MILLILITRE));

        // Addition
        System.out.println(v1.add(v2)); // 2 litre

        // Cross unit addition
        System.out.println(v1.add(v3, VolumeUnit.MILLILITRE));
    }
}