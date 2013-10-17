package org.mcvly.algo.dynamic.csp;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 17.10.13
 */
public class NotSatisfiableSolutionException extends Exception {

    public NotSatisfiableSolutionException() {
        super();
    }

    public NotSatisfiableSolutionException(Throwable e) {
        super(e);
    }

    public NotSatisfiableSolutionException(final String message) {
        super(message);
    }

    public NotSatisfiableSolutionException(final String message, final Throwable cause) {
        super(message, cause);
    }

}