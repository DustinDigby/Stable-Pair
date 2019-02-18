public class StackOverflowException extends Exception {

    public StackOverflowException(){

    }

    public StackOverflowException(String message){
        super(message);
    }

    public StackOverflowException(Throwable cause){
        super(cause);
    }

    public StackOverflowException(String message, Throwable cause){
        super(message,cause);
    }

    public StackOverflowException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
        super(message,cause,enableSuppression,writeableStackTrace);
    }
}
