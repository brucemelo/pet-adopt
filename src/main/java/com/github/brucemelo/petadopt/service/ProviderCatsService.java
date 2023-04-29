package com.github.brucemelo.petadopt.service;

import com.github.brucemelo.petadopt.domain.Pet;
import com.github.brucemelo.petadopt.integration.IntegrationCatApi;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ProviderCatsService {

    private final IntegrationCatApi integrationCatApi;

    public ProviderCatsService(IntegrationCatApi integrationCatApi) {
        this.integrationCatApi = integrationCatApi;
    }

    public Flux<Pet> getCats() {
        return integrationCatApi.getCats();
    }

}
