package org.chatapp.infrastructure.impl;


import org.chatapp.core.contracts.ICommand;
import org.chatapp.core.contracts.ICommandExecutor;
import org.chatapp.core.contracts.boundary.IInputBoundary;
import org.chatapp.core.contracts.boundary.IOutputBoundary;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class CommandExecutorImpl implements ICommandExecutor {

    @Override
    public <T, I extends IInputBoundary, O extends IOutputBoundary>
    CompletableFuture<T> execute(I input, ICommand<I, O> command, Function<O, T> outMapper) {
        return CompletableFuture
                .supplyAsync(() -> input)
                .thenApplyAsync(command::execute)
                .thenApplyAsync(outMapper);
    }
}
