package MathCore.BaseElements;

import lombok.NoArgsConstructor;
import org.apache.commons.math.complex.Complex;

@NoArgsConstructor
public class CurrentSource extends Device{
    private String type = "CurrentSource";
    private static final Complex ALMOST_INFINITE = new Complex(1 / TOLERANCE, 0.0);
    /**
     * Creates Complex Current Source
     * @param current - current magnitude
     * @param phase - current phase in radians
     */
    public CurrentSource(double current, double phase) {
        setCurrent(new Complex(
                current * Math.cos(phase),
                current * Math.sin(phase)
        ));
        setVoltage(Complex.ZERO);
        setResistance(ALMOST_INFINITE);
    }
}
