package org.mathws;

public class MathWebServiceSoapProxy implements org.mathws.MathWebServiceSoap {
  private String _endpoint = null;
  private org.mathws.MathWebServiceSoap mathWebServiceSoap = null;
  
  public MathWebServiceSoapProxy() {
    _initMathWebServiceSoapProxy();
  }
  
  public MathWebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initMathWebServiceSoapProxy();
  }
  
  private void _initMathWebServiceSoapProxy() {
    try {
      mathWebServiceSoap = (new org.mathws.MathWebServiceLocator()).getMathWebServiceSoap();
      if (mathWebServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mathWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mathWebServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mathWebServiceSoap != null)
      ((javax.xml.rpc.Stub)mathWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.mathws.MathWebServiceSoap getMathWebServiceSoap() {
    if (mathWebServiceSoap == null)
      _initMathWebServiceSoapProxy();
    return mathWebServiceSoap;
  }
  
  public long add(long num1, long num2) throws java.rmi.RemoteException{
    if (mathWebServiceSoap == null)
      _initMathWebServiceSoapProxy();
    return mathWebServiceSoap.add(num1, num2);
  }
  
  public long multiply(long num1, long num2) throws java.rmi.RemoteException{
    if (mathWebServiceSoap == null)
      _initMathWebServiceSoapProxy();
    return mathWebServiceSoap.multiply(num1, num2);
  }
  
  
}