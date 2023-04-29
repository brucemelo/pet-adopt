package com.github.brucemelo.petadopt.web;

import com.github.brucemelo.petadopt.domain.Pet;
import com.github.brucemelo.petadopt.service.PetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PetHandler {

    private final PetService petService;

    public PetHandler(PetService petService) {
        this.petService = petService;
    }

    public Mono<ServerResponse> indexPets() {
        return ServerResponse.ok().build(petService.indexPets());
    }

    public Mono<ServerResponse> available(final ServerRequest req) {
        final var id = req.pathVariable("id");
        return ServerResponse.noContent().build(petService.makeAvailable(Integer.valueOf(id)));
    }

    public Mono<ServerResponse> adopt(final ServerRequest req) {
        final var id = req.pathVariable("id");
        return ServerResponse.noContent().build(petService.adopt(Integer.valueOf(id)));
    }

    public Mono<ServerResponse> findAll(final ServerRequest req) {
        var term = req.queryParam("term");
        var category = req.queryParam("category");
        var status = req.queryParam("status");
        var date = req.queryParam("date");
        var sizeParam = req.queryParam("size").orElse("10");
        var pageParam = req.queryParam("page").orElse("0");
        var size = Integer.parseInt(sizeParam);
        var page = Integer.parseInt(pageParam);
        Page<Pet> result = petService.findAll(
                term,
                category,
                status,
                date,
                PageRequest.of(page, size));
        return ServerResponse.ok().bodyValue(result);
    }

}
