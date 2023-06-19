package onlinebookstore.bookstoreservice.common.exceptions.custom;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(String message) {
        super(message);
    }
}
