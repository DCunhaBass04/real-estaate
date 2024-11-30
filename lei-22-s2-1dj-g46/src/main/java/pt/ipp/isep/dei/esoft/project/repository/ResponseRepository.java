package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Response repository.
 */
public class ResponseRepository implements Serializable {
    private List<Response> responses = new ArrayList<>();

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty(){
        return responses.isEmpty();
    }

    /**
     * Add optional.
     *
     * @param response the response
     * @return the optional
     */
    public Optional<Response> add(Response response) {

        Optional<Response> newResponse = Optional.empty();
        boolean operationSuccess = false;

        if (validateResponse(response)) {
            newResponse = Optional.of(response.clone());
            operationSuccess = responses.add(newResponse.get());
        }

        if (!operationSuccess) {
            newResponse = Optional.empty();
        }

        return newResponse;
    }
    private boolean validateResponse(Response response) {return !responses.contains(response);}

    /**
     * Remove.
     *
     * @param o the o
     */
    public void remove(Object o){responses.remove(o);}

    /**
     * Get responses list.
     *
     * @return the list
     */
    public List<Response> getResponses(){return List.copyOf(responses);}

}
