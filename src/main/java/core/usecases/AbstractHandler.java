package core.usecases;

import core.gateways.IRequestHandler;

/**
 * Created by ryan on 11/20/17.
 */
public abstract class AbstractHandler<I, O> implements IRequestHandler<I, O> {

    protected abstract O errorResponse(String errorMessage);

}
