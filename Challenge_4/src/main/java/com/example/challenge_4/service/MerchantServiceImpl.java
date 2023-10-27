package com.example.challenge_4.service;

import com.example.challenge_4.model.Merchant;
import com.example.challenge_4.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService{

    @Autowired
    MerchantRepository merchantRepository;
    @Override
    public List<Merchant> getAll() {
        return null;
    }

    @Override
    public Merchant create(Merchant merchant) {
        return null;
    }

    @Override
    public Merchant update(UUID id, Merchant merchant) {
        return null;
    }

    @Override
    public void editStatusMerchantByName(String name, boolean isOpen) {

    }

    @Override
    public void delete(UUID id) {

    }
}
