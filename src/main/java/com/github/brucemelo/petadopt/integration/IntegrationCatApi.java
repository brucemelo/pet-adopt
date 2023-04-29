package com.github.brucemelo.petadopt.integration;

import com.github.brucemelo.petadopt.domain.Category;
import com.github.brucemelo.petadopt.domain.Pet;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Profile({"dev", "prod"})
@Component
public class IntegrationCatApi {

    protected final UriConfigCatApi uriConfigCatApi;

    public IntegrationCatApi(UriConfigCatApi uriConfigCatApi) {
        this.uriConfigCatApi = uriConfigCatApi;
    }

    public final Flux<Pet> getCats() {
        var catApis = getCatsApi();
        return catApis.map(dogApi -> {
            var pet = new Pet();
            pet.setName(dogApi.getBreed());
            pet.setCategory(Category.Cat);
            return pet;
        });
    }

    protected Flux<CatApi> getCatsApi() {
        var uri = uriConfigCatApi.getUri();
        return WebClient.create(uri).get().retrieve().bodyToFlux(CatApi.class);
    }

}
