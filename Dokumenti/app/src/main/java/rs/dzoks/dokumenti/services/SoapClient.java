package rs.dzoks.dokumenti.services;


import android.content.Context;

import com.google.gson.Gson;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpsTransportSE;

import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import rs.dzoks.dokumenti.R;
import rs.dzoks.dokumenti.model.DocumentsResponse;
import rs.dzoks.dokumenti.util.HTTPSSetup;

public class SoapClient {

    private String NAMESPACE = "http://dzoks.rs/service/soap";
    private String REQUEST = "GetDocumentsRequest";
    private String host;
    private int port;
    private String file;
    private int timeOut;
    private IWsdl2CodeEvents eventHandler;
    private Context context;

    public SoapClient(Context context) {
        this.context = context;
        this.host = context.getResources().getString(R.string.ip);
        this.port = Integer.parseInt(context.getResources().getString(R.string.port));
        this.file = context.getResources().getString(R.string.soap_path);
        this.timeOut = 36000;
    }


    public DocumentsResponse getDocuments(String username, String password, String token, long time) {
        return getDocuments(username, password, token, time, null);
    }

    public DocumentsResponse getDocuments(String username, String password, String token, long time, List<HeaderProperty> headers) {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, REQUEST);
        soapReq.addProperty("date", time);
        soapReq.addProperty("username", username);
        soapReq.addProperty("password", password);
        soapReq.addProperty("token", token);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpsTransportSE httpsTransportSE = new HttpsTransportSE(host, port, file, timeOut);
        HttpsURLConnection.setDefaultSSLSocketFactory(HTTPSSetup.getSslSocketFactory(context));
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return HTTPSSetup.verifyCert(context, sslSession);
            }
        });
        try {
            if (headers != null) {
                httpsTransportSE.call(NAMESPACE + "/" + REQUEST, soapEnvelope, headers);
            } else {
                httpsTransportSE.call(NAMESPACE + "/" + REQUEST, soapEnvelope);
            }
            Object retObj = soapEnvelope.bodyIn;
            if (retObj instanceof SoapFault) {
                SoapFault fault = (SoapFault) retObj;
                Exception ex = new Exception(fault.faultstring);
                if (eventHandler != null)
                    eventHandler.Wsdl2CodeFinishedWithException(ex);
            } else {
                SoapObject result = (SoapObject) retObj;
                if (result.getPropertyCount() > 0) {
                    Object obj = result.getProperty(0);
                    if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                        SoapPrimitive j = (SoapPrimitive) obj;
                        String resultVariable = j.toString();
                        Gson gson = new Gson();
                        return gson.fromJson(resultVariable, DocumentsResponse.class);
                    }/*else if (obj!= null && obj instanceof String){
                        String resultVariable = (String) obj;
                        return resultVariable;
                    }*/
                }
            }
        } catch (Exception e) {
            if (eventHandler != null)
                eventHandler.Wsdl2CodeFinishedWithException(e);
            e.printStackTrace();
        }
        return null;
    }
}
