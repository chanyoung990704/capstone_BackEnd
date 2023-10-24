package unit.capstone.exception.movie;

public class NotFoundMovieException extends RuntimeException{
    public NotFoundMovieException() {
    }

    public NotFoundMovieException(String message) {
        super(message);
    }

    public NotFoundMovieException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundMovieException(Throwable cause) {
        super(cause);
    }

    public NotFoundMovieException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
