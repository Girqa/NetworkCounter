package MathCore.BaseElements;

import lombok.NoArgsConstructor;
import org.apache.commons.math.complex.Complex;

@NoArgsConstructor
public class Inductance extends Device{
    private String type = "Inductance";
    public Inductance(double resistance) {
        setResistance(new Complex(0.0, resistance));
        setVoltage(Complex.ZERO);
        setCurrent(Complex.ZERO);
    }
}
