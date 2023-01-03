package org.chatapp.infrastructure.impl;

import org.chatapp.core.contracts.ICommand;
import org.chatapp.core.contracts.ICommandExecutor;
import org.chatapp.core.contracts.boundary.IInputBoundary;
import org.chatapp.core.contracts.boundary.IOutputBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
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
