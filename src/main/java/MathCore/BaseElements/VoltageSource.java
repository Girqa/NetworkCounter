package MathCore.BaseElements;

import lombok.NoArgsConstructor;
import org.apache.commons.math.complex.Complex;

@NoArgsConstructor
public class VoltageSource extends Device{
    private static final Complex ALMOST_ZERO = new Complex(TOLERANCE, 0);
    private String type = "VoltageSource";
    /**
     * Creates Complex Voltage Source
     * @param voltage - voltage magnitude
     * @param phase - voltage phase in radians
     */
    public VoltageSource(double voltage, double phase) {
        setVoltage(new Complex(
                voltage*Math.cos(phase),
                voltage*Math.sin(phase))
        );
        setResistance(ALMOST_ZERO);
        setCurrent(Complex.ZERO);
    }
}
