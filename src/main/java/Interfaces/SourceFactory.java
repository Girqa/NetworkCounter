package Interfaces;

import MathCore.BaseElements.Device;

public interface SourceFactory {
    Device newInstance(double magnitude, double phase, double tolerance);
}
