package unit.capstone.exception.movie;

public class DuplicateLikedMovieException extends RuntimeException{
    public DuplicateLikedMovieException() {
    }

    public DuplicateLikedMovieException(String message) {
        super(message);
    }

    public DuplicateLikedMovieException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateLikedMovieException(Throwable cause) {
        super(cause);
    }

    public DuplicateLikedMovieException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
