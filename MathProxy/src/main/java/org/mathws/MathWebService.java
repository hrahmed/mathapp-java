/**
 * MathWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mathws;

public interface MathWebService extends javax.xml.rpc.Service {
    public java.lang.String getMathWebServiceSoapAddress();

    public org.mathws.MathWebServiceSoap getMathWebServiceSoap() throws javax.xml.rpc.ServiceException;

    public org.mathws.MathWebServiceSoap getMathWebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
