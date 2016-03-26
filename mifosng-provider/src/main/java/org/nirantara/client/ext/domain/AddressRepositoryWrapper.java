package org.nirantara.client.ext.domain;

import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.portfolio.client.domain.Client;
import org.nirantara.client.ext.exception.AddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressRepositoryWrapper {

    private final AddressRepository repository;

    @Autowired
    public AddressRepositoryWrapper(final AddressRepository repository) {
        this.repository = repository;
    }

    public Address findOneWithNotFoundDetection(final Long id) {
        final Address address = this.repository.findOne(id);
        if (address == null) { throw new AddressNotFoundException(id); }
        return address;
    }

    public void save(final Address address) {
        this.repository.save(address);
    }

    public void saveAndFlush(final Address address) {
        this.repository.saveAndFlush(address);
    }

    public void delete(final Address address) {
        this.repository.delete(address);
    }

    public Address findByClientAndAddressType(final Client client, final CodeValue addressType) {
        return this.repository.findByClientAndAddressType(client, addressType);
    }
}
