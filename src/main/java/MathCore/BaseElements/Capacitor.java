package MathCore.BaseElements;

import lombok.NoArgsConstructor;
import org.apache.commons.math.complex.Complex;

@NoArgsConstructor
public class Capacitor extends Device{
    private String type = "Capacitor";
    public Capacitor(double resistance) {
        setResistance(new Complex(0, -resistance));
        setVoltage(Complex.ZERO);
        setCurrent(Complex.ZERO);
    }
}
