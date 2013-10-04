package org.mcvly.algo.dynamic.apsp.graph;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 04.10.13
 */
public class NegativeCycleException extends Exception {

    public NegativeCycleException() {
        super();
    }

    public NegativeCycleException(Throwable e) {
        super(e);
    }

    public NegativeCycleException(final String message) {
        super(message);
    }

    public NegativeCycleException(final String message, final Throwable cause) {
        super(message, cause);
    }

}