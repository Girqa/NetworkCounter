package Interfaces;

import MathCore.BaseElements.Device;

public interface PassiveElementFactory {
    Device newInstance (double resistance);
}
