public interface IMeasurable {

    double toBase(double value);

    double fromBase(double baseValue);

    String getUnitName();
}