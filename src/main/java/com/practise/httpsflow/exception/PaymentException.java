package com.practise.httpsflow.exception;

public class PaymentException  extends ApiException{

    public PaymentException (ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static PaymentException notFound(long id){
        return new PaymentException(ErrorCode.PAYMENT_NOT_FOUND,"Payment not found: "+ id);
    }

    public static PaymentException missingField(String field){
        return new PaymentException(ErrorCode.INVALID_REQUEST, "Missing required field:"+field);
    }

    public static PaymentException invalidAmount(){
        return new PaymentException(ErrorCode.INVALID_AMOUNT,"Amount must be greater than zero");
    }

}
