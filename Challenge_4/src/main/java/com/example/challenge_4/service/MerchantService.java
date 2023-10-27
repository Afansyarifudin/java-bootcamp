package com.example.challenge_4.service;

import com.example.challenge_4.model.Merchant;
import com.example.challenge_4.model.dto.MerchantViewDto;

import java.util.List;
import java.util.UUID;

public interface MerchantService {
    List<Merchant> getAll();
    List<MerchantViewDto> getOpenMerchant();

    Merchant create(Merchant merchant);

    Merchant update(UUID id, Merchant merchant);

    void editStatusMerchantByName(String name, boolean isOpen);

    void delete(UUID id);
}
