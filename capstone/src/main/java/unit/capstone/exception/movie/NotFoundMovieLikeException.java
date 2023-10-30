package unit.capstone.exception.movie;

public class NotFoundMovieLikeException extends RuntimeException{
    public NotFoundMovieLikeException() {
    }

    public NotFoundMovieLikeException(String message) {
        super(message);
    }

    public NotFoundMovieLikeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundMovieLikeException(Throwable cause) {
        super(cause);
    }

    public NotFoundMovieLikeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
