package org.chatapp.core.contracts;

import org.chatapp.core.contracts.boundary.IInputBoundary;
import org.chatapp.core.contracts.boundary.IOutputBoundary;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface ICommandExecutor {

    <T, I extends IInputBoundary, O extends IOutputBoundary>   //TODO: or here, interesting...
    CompletableFuture<T> execute(I input, ICommand<I, O> command, Function<O, T> outMapper);

}
