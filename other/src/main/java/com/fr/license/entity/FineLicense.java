package com.fr.license.entity;

import com.fr.stable.Hidden;

@Hidden
public class FineLicense extends AbstractLicense {
    public FineLicense(String var1) {
        super(var1);
    }

    public native String getString(String var1, String var2);

    public native boolean isOvertime();

    public native boolean isOnTrial();

    public native boolean isTemp();

    public native int maxConcurrencyLevel();

    public native int maxDataConnectionLevel();

    public native int maxReportletLevel();

    public native int maxDecisionUserLevel();

    public native int maxMobileUserLevel();

    public native boolean isVersionMatch();

    public native boolean isAppNameMatch();

    public native boolean isAppContentMatch();

    public native boolean isDongleSerialNumMatch();

    public native boolean isMultiServer();

    public native boolean isUUIDMatch();

    public native boolean isMacAddressMatch();

    public native boolean isClusterMatch();

    public native boolean isNodeMatch();

    public native String templateEncryptionKey(String var1);
}
