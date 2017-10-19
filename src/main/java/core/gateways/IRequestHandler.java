package core.gateways;

/**
 * Created by ryan on 10/16/17.
 */
public interface IRequestHandler<I, O> {

    O handleRequest(I request);


}

