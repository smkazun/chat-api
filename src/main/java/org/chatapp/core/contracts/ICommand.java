package org.chatapp.core.contracts;

import org.chatapp.core.contracts.boundary.IInputBoundary;
import org.chatapp.core.contracts.boundary.IOutputBoundary;

public interface ICommand<I extends IInputBoundary, O extends IOutputBoundary> {

    public O execute(I input);
}
