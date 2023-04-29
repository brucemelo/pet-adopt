package com.github.brucemelo.petadopt.service;

import com.github.brucemelo.petadopt.domain.Pet;
import com.github.brucemelo.petadopt.integration.IntegrationDogApi;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ProviderDogsService {

    private final IntegrationDogApi integrationDogApi;

    public ProviderDogsService(IntegrationDogApi integrationDogApi) {
        this.integrationDogApi = integrationDogApi;
    }

    public Flux<Pet> getDogs() {
        return integrationDogApi.getDogs();
    }
}
