package com.example.springwebrest_ch5.service;


import com.example.springwebrest_ch5.model.Merchant;
import com.example.springwebrest_ch5.model.dto.MerchantEditNameDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MerchantService {
    Merchant create(Merchant merchant);

    List<Merchant> getAll();

    Optional<Merchant> getById(UUID id);

    Merchant update(Merchant merchant, UUID id);

    Merchant updateByName(MerchantEditNameDto merchantEditNameDto, UUID id);

    void delete(UUID id);


}
