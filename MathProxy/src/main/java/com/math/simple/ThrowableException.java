
/**
 * ThrowableException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

package com.math.simple;

public class ThrowableException extends java.lang.Exception{
    
    private com.math.simple.MathSimpleBackendStub.Throwable faultMessage;

    
        public ThrowableException() {
            super("ThrowableException");
        }

        public ThrowableException(java.lang.String s) {
           super(s);
        }

        public ThrowableException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ThrowableException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.math.simple.MathSimpleBackendStub.Throwable msg){
       faultMessage = msg;
    }
    
    public com.math.simple.MathSimpleBackendStub.Throwable getFaultMessage(){
       return faultMessage;
    }
}
    