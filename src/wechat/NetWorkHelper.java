package wechat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NetWorkHelper {
    X509TrustManager xtm = new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }
    };

    public NetWorkHelper() {
    }

    public String getHttpsResponsePostBody(String hsUrl, String requestMethod, String body) {
        InputStream is = null;
        String resultData = "";

        try {
            URL url = new URL(hsUrl);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            TrustManager[] tm = new TrustManager[]{this.xtm};
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init((KeyManager[])null, tm, (SecureRandom)null);
            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            if (requestMethod != null && !requestMethod.equals("")) {
                con.setRequestMethod(requestMethod);
            } else {
                con.setRequestMethod("GET");
            }

            con.setRequestProperty("Content-Type", "application/json");
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            byte[] outputInBytes = body.getBytes("UTF-8");
            out.write(outputInBytes);
            out.flush();
            out.close();
            is = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);

            for(String inputLine = ""; (inputLine = bufferReader.readLine()) != null; resultData = resultData + inputLine + "\n") {
                ;
            }

            System.out.println(resultData);
            Certificate[] certs = con.getServerCertificates();
            int certNum = 1;
            Certificate[] var21 = certs;
            int var20 = certs.length;

            for(int var19 = 0; var19 < var20; ++var19) {
                Certificate cert = var21[var19];
                X509Certificate var22 = (X509Certificate)cert;
            }
        } catch (Exception var23) {
            var23.printStackTrace();
        }

        return resultData;
    }

    public String getHttpsResponse(String hsUrl, String requestMethod) {
        InputStream is = null;
        String resultData = "";

        try {
            URL url = new URL(hsUrl);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            TrustManager[] tm = new TrustManager[]{this.xtm};
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init((KeyManager[])null, tm, (SecureRandom)null);
            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
            con.setDoInput(true);
            con.setDoOutput(false);
            con.setUseCaches(false);
            if (requestMethod != null && !requestMethod.equals("")) {
                con.setRequestMethod(requestMethod);
            } else {
                con.setRequestMethod("GET");
            }

            is = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);

            for(String inputLine = ""; (inputLine = bufferReader.readLine()) != null; resultData = resultData + inputLine + "\n") {
                ;
            }

            System.out.println(resultData);
            Certificate[] certs = con.getServerCertificates();
            int certNum = 1;
            Certificate[] var17 = certs;
            int var16 = certs.length;

            for(int var15 = 0; var15 < var16; ++var15) {
                Certificate cert = var17[var15];
                X509Certificate var18 = (X509Certificate)cert;
            }
        } catch (Exception var19) {
            var19.printStackTrace();
        }

        return resultData;
    }
}