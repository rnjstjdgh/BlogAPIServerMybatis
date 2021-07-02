package nk.demo.BlogAPIServer.CustomException;

public class PostValidationException extends RuntimeException{
    public PostValidationException(String msg, Throwable t) {
        super(msg, t);
    }

    public PostValidationException(String msg) {
        super(msg);
    }

    public PostValidationException() {
        super();
    }
}