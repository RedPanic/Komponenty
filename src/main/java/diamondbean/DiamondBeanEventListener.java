package diamondbean;

import java.io.IOException;

public interface DiamondBeanEventListener {
    void diamondBeanEventOccured(DiamondBeanEvent event) throws IOException;
}
