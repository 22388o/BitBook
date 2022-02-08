package de.cotto.bitbook.backend.persistence;

import de.cotto.bitbook.backend.AddressWithDescriptionDao;
import de.cotto.bitbook.backend.model.Address;
import de.cotto.bitbook.backend.model.AddressWithDescription;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
@Transactional
public class AddressWithDescriptionDaoImpl implements AddressWithDescriptionDao {
    private final AddressWithDescriptionRepository repository;

    public AddressWithDescriptionDaoImpl(AddressWithDescriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public AddressWithDescription get(Address address) {
        return repository.findById(address.toString())
                .map(AddressWithDescriptionJpaDto::toModel)
                .orElseGet(() -> new AddressWithDescription(address));
    }

    @Override
    public void save(AddressWithDescription addressWithDescription) {
        repository.save(AddressWithDescriptionJpaDto.fromModel(addressWithDescription));
    }

    @Override
    public void remove(Address address) {
        repository.deleteById(address.toString());
    }

    @Override
    public Set<AddressWithDescription> findWithDescriptionInfix(String infix) {
        return repository.findByDescriptionContaining(infix).stream()
                .map(AddressWithDescriptionJpaDto::toModel)
                .collect(toSet());
    }
}
