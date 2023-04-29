package com.github.brucemelo.petadopt.service;

import com.github.brucemelo.petadopt.domain.Pet;
import com.github.brucemelo.petadopt.domain.PetRepository;
import com.github.brucemelo.petadopt.domain.Pet_;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PetService {

    private final ProviderDogsService providerDogsService;
    private final ProviderCatsService providerCatsService;
    private final PetRepository petRepository;

    public PetService(ProviderDogsService providerDogsService, ProviderCatsService providerCatsService, PetRepository petRepository) {
        this.providerDogsService = providerDogsService;
        this.providerCatsService = providerCatsService;
        this.petRepository = petRepository;
    }

    @Transactional
    public Mono<Void> indexPets() {
        var dogs = providerDogsService.getDogs();
        var cats = providerCatsService.getCats();

        petRepository.saveAll(dogs.toIterable());
        petRepository.saveAll(cats.toIterable());
        return Mono.empty();
    }

    @Transactional
    public Mono<Void> makeAvailable(Integer id) {
        var pet = petRepository.findById(id).orElseThrow(PetNotFoundException::new);
        pet.makeAvailable();
        petRepository.save(pet);
        return Mono.empty();
    }

    @Transactional
    public Mono<Void> adopt(Integer id) {
        var pet = petRepository.findById(id).orElseThrow(PetNotFoundException::new);
        pet.toAdopt();
        petRepository.save(pet);
        return Mono.empty();
    }

    public Page<Pet> findAll(Optional<String> term, Optional<String> category, Optional<String> status, Optional<String> date, PageRequest pageRequest) {
        Specification<Pet> filterSpecs = (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();
            if (term.isPresent()) {
                predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get(Pet_.name), term), criteriaBuilder.equal(root.get(Pet_.description), term)));
            }
            if (category.isPresent()) {
                predicates.add(criteriaBuilder.equal(root.get(Pet_.category), category));
            }
            if (status.isPresent()) {
                predicates.add(criteriaBuilder.equal(root.get(Pet_.status), status));
            }
            if (date.isPresent()) {
                predicates.add(criteriaBuilder.equal(root.get(Pet_.date), date));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return petRepository.findAll(filterSpecs, pageRequest);
    }

}
