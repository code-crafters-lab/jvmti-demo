
package com.fr.license.cloud;

import com.fr.json.JSONObject;
import com.fr.log.FineLoggerFactory;
import com.fr.third.org.apache.http.HttpEntity;
import com.fr.third.org.apache.http.client.config.RequestConfig;
import com.fr.third.org.apache.http.client.methods.CloseableHttpResponse;
import com.fr.third.org.apache.http.client.methods.HttpPost;
import com.fr.third.org.apache.http.config.Registry;
import com.fr.third.org.apache.http.config.RegistryBuilder;
import com.fr.third.org.apache.http.conn.socket.PlainConnectionSocketFactory;
import com.fr.third.org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import com.fr.third.org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import com.fr.third.org.apache.http.entity.StringEntity;
import com.fr.third.org.apache.http.impl.client.CloseableHttpClient;
import com.fr.third.org.apache.http.impl.client.HttpClients;
import com.fr.third.org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import com.fr.third.org.apache.http.ssl.SSLContexts;
import com.fr.third.org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;

public abstract class CloudServerConnectorImpl {


    public static void main(String[] args) {
        JSONObject entries = requirePrivateServerInfo();
        System.out.println(entries);
    }


    private static JSONObject requirePrivateServerInfo() {
        try {
            CloseableHttpResponse var0 = send("https://localhost/license/capacity", JSONObject.create());
            HttpEntity var1 = var0.getEntity();
            JSONObject var2 = new JSONObject(EntityUtils.toString(var1));
            var0.close();
            return var2;
        } catch (Exception var3) {
            FineLoggerFactory.getLogger().error(var3.getMessage(), var3);
            return JSONObject.create();
        }
    }

    private static CloseableHttpResponse send(String var0, JSONObject var1) throws IOException {
        Registry var2 = RegistryBuilder.create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", new SSLConnectionSocketFactory(getSSLContext()))
                .build();
        PoolingHttpClientConnectionManager var3 = new PoolingHttpClientConnectionManager(var2);
        CloseableHttpClient var4 = HttpClients.custom().useSystemProperties().setConnectionManager(var3).build();
        RequestConfig var5 = RequestConfig.custom().setConnectTimeout(8000).setConnectionRequestTimeout(3000).setSocketTimeout(8000).build();
        HttpPost var6 = new HttpPost(var0);
        var6.setConfig(var5);
        var6.setEntity(new StringEntity(var1.toString()));
        var6.setHeader("Content-type", "application/octet-stream");
        return var4.execute(var6);
    }

    private static SSLContext getSSLContext() {
        SSLContext var0 = null;

        try {
            KeyStore var1 = KeyStore.getInstance(KeyStore.getDefaultType());
            var1.load(getKeyStoreIn(), "changeit".toCharArray());
            var0 = SSLContexts.custom().loadTrustMaterial(var1, new TrustSelfSignedStrategy()).build();
        } catch (Throwable var2) {
            FineLoggerFactory.getLogger().error(var2.getMessage(), var2);
        }

        return var0;
    }

    private static InputStream getKeyStoreIn() {
        return new ByteArrayInputStream(new byte[]{-2, -19, -2, -19, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 2, 0, 17, 102, 114, 45, 108, 105, 99, 101, 110, 115, 101, 45, 115, 101, 114, 118, 101, 114, 0, 0, 1, 94, -98, 64, -128, -98, 0, 5, 88, 46, 53, 48, 57, 0, 0, 3, 9, 48, -126, 3, 5, 48, -126, 1, -19, -96, 3, 2, 1, 2, 2, 9, 0, -38, 10, -122, 11, -54, -40, -5, 114, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 11, 5, 0, 48, 25, 49, 23, 48, 21, 6, 3, 85, 4, 3, 12, 14, 102, 105, 110, 101, 114, 101, 112, 111, 114, 116, 46, 99, 111, 109, 48, 30, 23, 13, 49, 55, 48, 57, 49, 50, 48, 56, 49, 49, 53, 49, 90, 23, 13, 51, 49, 48, 53, 50, 50, 48, 56, 49, 49, 53, 49, 90, 48, 25, 49, 23, 48, 21, 6, 3, 85, 4, 3, 12, 14, 102, 105, 110, 101, 114, 101, 112, 111, 114, 116, 46, 99, 111, 109, 48, -126, 1, 34, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -126, 1, 15, 0, 48, -126, 1, 10, 2, -126, 1, 1, 0, -12, 84, 84, -9, 110, -100, 19, 30, 124, 121, -124, -14, 106, -124, 106, 102, 24, 42, -115, -127, -127, 33, 39, 112, 84, -96, -2, 37, -26, 89, -23, -95, 114, -24, -26, 44, -21, -33, -19, 6, -125, -37, 35, 120, 123, 6, -114, 47, 82, 79, 48, 32, -26, 68, -127, 124, 79, 127, -23, -50, -19, -8, -17, 5, -63, -50, 70, 53, 10, 83, -28, 27, 94, 99, -70, 109, 91, 11, 15, -66, -17, 41, -36, -48, -43, 20, -13, -116, -35, -44, -125, -65, -14, -111, -86, -13, -5, -31, 10, 69, -67, -25, 83, 11, -121, 110, 121, -5, -12, -50, 20, -71, 48, -119, -44, -100, -37, 12, 42, 34, 101, -4, 88, 84, 14, 93, 70, -2, 127, 8, 103, 90, -15, -6, 121, -81, -23, -97, -6, 80, 109, 25, 52, -10, -115, -55, 79, 22, -89, -117, -111, -89, 93, 110, 86, -15, -24, -62, -52, -77, -11, -28, 56, -13, 73, 118, 38, -114, -15, -80, 45, -128, 0, -84, 2, 0, -52, 99, 83, -59, -113, -98, 93, 33, 58, -77, -16, -50, 85, 127, 13, 111, -71, 21, 120, 46, -67, -8, 76, -10, -41, 55, -12, -83, -15, -87, -81, -101, -83, -50, 61, 108, -10, 82, 27, -2, 31, -61, 26, 27, 110, 44, 109, 101, 90, 19, 115, -46, -92, -61, 24, -108, -104, 18, 115, -43, 44, -17, 71, -58, -5, 65, 119, 125, -128, -75, -11, -34, 120, 99, 19, 14, -121, -85, -8, -59, 2, 3, 1, 0, 1, -93, 80, 48, 78, 48, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, 84, -123, -90, 88, 97, -87, -83, 115, -97, 84, 19, -32, 90, -64, -67, -14, -124, -99, 54, 37, 48, 31, 6, 3, 85, 29, 35, 4, 24, 48, 22, -128, 20, 84, -123, -90, 88, 97, -87, -83, 115, -97, 84, 19, -32, 90, -64, -67, -14, -124, -99, 54, 37, 48, 12, 6, 3, 85, 29, 19, 4, 5, 48, 3, 1, 1, -1, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 11, 5, 0, 3, -126, 1, 1, 0, -36, -99, -15, 72, 45, -32, -8, -114, -20, -37, 91, 62, 48, -123, 120, -3, 10, 116, 45, 45, 24, 19, 93, 60, -75, -92, -31, 123, 50, 106, 53, 110, 6, -33, -115, 2, 39, 66, 2, 25, 41, 27, 5, -95, -36, -2, 70, 127, -83, -60, 116, 62, 69, -53, -95, -103, -92, 65, 41, -49, 25, 2, -117, -117, 99, 53, -4, -1, 63, -96, -67, -22, 11, -92, 76, 2, -102, -16, -59, -82, 84, -8, 72, 75, -119, 74, 82, -85, -22, -32, -94, -88, 71, 116, 69, 5, -8, 122, -76, -124, 65, -45, -18, -14, 5, 66, 119, -43, -70, 23, 89, -44, -5, -117, 17, -91, -17, -128, 104, -64, -43, -16, 19, 114, -44, 11, -99, 96, -68, -83, 97, -113, 83, -54, 89, 73, -61, -41, -51, -73, -34, -84, -108, -99, -127, 51, 81, 30, 14, -79, -1, 33, -23, -18, -87, -91, -28, -8, 37, -38, -79, 72, 5, 94, 34, 67, -80, 65, 103, -60, 96, -10, 94, -124, 15, -27, -123, 69, -34, 70, 42, -40, 58, -99, 80, -118, 33, -51, -94, 97, 8, -1, -32, 15, 0, 89, -85, 27, 32, -123, 117, -123, -21, -88, -100, 17, 54, -117, 96, 45, 58, -3, -18, -51, 10, 76, 46, 118, 24, 82, 89, 0, -91, -83, 125, -89, 7, -57, -112, -61, 30, -29, 61, 46, -95, 81, -25, -68, 84, 70, -114, 78, 13, 116, 109, 127, -46, 67, -81, 88, 48, 105, -8, -117, 53, 45, -65, 79, -128, -67, 118, -107, 7, -84, 51, 53, -86, -65, 47, -95, 50, 32, -43, -26, 12, -22});
    }
}
