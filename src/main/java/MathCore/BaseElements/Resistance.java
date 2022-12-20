package MathCore.BaseElements;

import lombok.NoArgsConstructor;
import org.apache.commons.math.complex.Complex;

@NoArgsConstructor
public class Resistance extends Device{
    private String type = "Resistance";
    public Resistance(double resistance) {
        setResistance(new Complex(resistance, 0));
        setVoltage(Complex.ZERO);
        setCurrent(Complex.ZERO);
    }
}
