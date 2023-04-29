package com.github.brucemelo.petadopt.integration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Profile({"local"})
@Component
public class IntegrationCatFakeApi extends IntegrationCatApi{

    Flux<CatApi> cats = Flux.just();

    {
        cats = cats.concatWithValues(new CatApi("cat 1"));
        cats = cats.concatWithValues(new CatApi("cat 2"));
    }

    public IntegrationCatFakeApi(UriConfigCatApi uriConfigCatApi) {
        super(uriConfigCatApi);
    }


    @Override
    protected Flux<CatApi> getCatsApi() {
        return cats;
    }

}
