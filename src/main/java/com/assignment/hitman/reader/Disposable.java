package com.assignment.hitman.reader;

/**
 * A functional interface which must be implemented by any class which is using any resource/stream. This
 * method is an indication for implementors that they must close/dispose the resources after use.
 *
 * @author ashutoshp
 */
@FunctionalInterface
public interface Disposable {
    void dispose();
}
