package org.dominokit.domino.ui.config;

import org.dominokit.domino.ui.dialogs.DefaultZIndexManager;
import org.dominokit.domino.ui.dialogs.ZIndexManager;

public interface ZIndexConfig extends ComponentConfig{

    default int getInitialZIndex() {
        return 8388635;
    }

    default int getzIndexIncrement() {
        return 1;
    }


    default ZIndexManager getZindexManager() {
        return DefaultZIndexManager.INSTANCE;
    }

}
