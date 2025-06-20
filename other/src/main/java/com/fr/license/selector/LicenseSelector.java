package com.fr.license.selector;

import com.fr.regist.License;
import com.fr.stable.Hidden;

@Hidden
public interface LicenseSelector {
    License get();

    void destroy();

    boolean validate();
}
