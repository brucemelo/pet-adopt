package com.github.brucemelo.petadopt.integration;

import com.github.brucemelo.petadopt.domain.Category;
import com.github.brucemelo.petadopt.domain.Pet;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class IntegrationDogApi {

    protected final UriConfigDogApi uriConfigDogApi;

    public IntegrationDogApi(UriConfigDogApi uriConfigDogApi) {
        this.uriConfigDogApi = uriConfigDogApi;
    }

    public final Flux<Pet> getDogs() {
        var catApis = getDogsApi();
        return catApis.map(dogApi -> {
            var pet = new Pet();
            pet.setName(dogApi.getBreed());
            pet.setCategory(Category.Dog);
            return pet;
        });
    }

    protected Flux<DogApi> getDogsApi() {
        var uri = uriConfigDogApi.getUri();
        return WebClient.create(uri).get().retrieve().bodyToFlux(DogApi.class);
    }
}
