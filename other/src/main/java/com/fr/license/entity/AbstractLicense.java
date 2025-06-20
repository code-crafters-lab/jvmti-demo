package com.fr.license.entity;

import com.fr.json.JSON;
import com.fr.json.JSONArray;
import com.fr.json.JSONFactory;
import com.fr.json.JSONObject;
import com.fr.log.FineLoggerFactory;
import com.fr.plugin.context.PluginMarker;
import com.fr.regist.DataBaseTypePoint;
import com.fr.regist.FunctionPoint;
import com.fr.regist.License;
import com.fr.regist.LicenseItem;
import com.fr.stable.Hidden;
import com.fr.stable.StringUtils;
import java.math.BigInteger;

@Hidden
public abstract class AbstractLicense implements License {
    public AbstractLicense() {
        this.init("");
    }

    public AbstractLicense(String var1) {
        this.init(var1);
    }

    public native long signature();

    private native void init(String var1);

    public native long deadline();

    public native int getInt(String var1, int var2);

    public native long getLong(String var1, long var2);

    public native boolean getBoolean(String var1, boolean var2);

    public native String companyName(String var1);

    public native String projectName(String var1);

    public native String getVersion();

    public native String getAppName();

    public native String getAppContent();

    public native String getDongleSerialNum();

    public native String getUUID();

    public native String getMacAddress();

    private native String getContent();

    public boolean support(FunctionPoint var1) {
        String var2 = this.getString(LicenseItem.Function.getKey(), "");
        if (StringUtils.isNotBlank(var2)) {
            BigInteger var3 = new BigInteger(var2);
            return var3.and(BigInteger.ONE.shiftLeft(var1.getMarker())).compareTo(BigInteger.ZERO) == 1;
        } else {
            return true;
        }
    }

    public boolean support(DataBaseTypePoint var1) {
        if (var1 == null) {
            return false;
        } else {
            JSONObject var2 = this.getJSONObject();
            if (var2.get(LicenseItem.DataBaseType.getKey()) == null) {
                return true;
            } else {
                JSONArray var3 = var2.getJSONArray(LicenseItem.DataBaseType.getKey());
                if (!var3.isEmpty() && (var3.length() != 1 || !StringUtils.isEmpty(var3.getString(0)))) {
                    for(int var4 = 0; var4 < var3.length(); ++var4) {
                        if (var3.getInt(var4) == var1.getMark()) {
                            return true;
                        }
                    }

                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    public boolean limitDatabaseType() {
        JSONObject var1 = this.getJSONObject();
        if (var1.get(LicenseItem.DataBaseType.getKey()) == null) {
            return false;
        } else {
            JSONArray var2 = var1.getJSONArray(LicenseItem.DataBaseType.getKey());
            return !var2.isEmpty() && (var2.length() != 1 || !StringUtils.isEmpty(var2.getString(0)));
        }
    }

    public boolean support(PluginMarker var1) {
        if (var1 == null) {
            return false;
        } else {
            try {
                JSONObject var2 = this.getJSONObject();
                if (var2.has(LicenseItem.Plugin.getKey())) {
                    JSONArray var3 = var2.getJSONArray(LicenseItem.Plugin.getKey());

                    for(int var5 = 0; var5 < var3.length(); ++var5) {
                        String[] var4 = var3.getString(var5).split(",");
                        if (var4.length >= 2 && var1.getPluginID().equals(var4[0])) {
                            return true;
                        }
                    }
                }
            } catch (Exception var6) {
                FineLoggerFactory.getLogger().error(var6.getMessage(), var6);
            }

            return false;
        }
    }

    public JSONObject getJSONObject() {
        return (JSONObject)JSONFactory.createJSON(JSON.OBJECT, this.getContent());
    }
}
