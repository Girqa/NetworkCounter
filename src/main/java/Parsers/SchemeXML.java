package Parsers;


import MathCore.BaseElements.Device;
import MathCore.BaseElements.Node;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="scheme")
public class SchemeXML {
    @XmlElementWrapper(name = "nodes")
    @XmlElement(name = "node")
    private Set<Node> nodes;
    @XmlElementWrapper(name = "devices")
    @XmlElement
    private Set<Device> devices;
}
