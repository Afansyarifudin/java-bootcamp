package com.example.springwebrest_ch5.service;

import com.example.springwebrest_ch5.model.Merchant;
import com.example.springwebrest_ch5.model.dto.MerchantEditNameDto;
import com.example.springwebrest_ch5.repository.MerchantRepository;
import com.example.springwebrest_ch5.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService{

    @Autowired
    MerchantRepository merchantRepository;

    private final static Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Override
    public Merchant create(Merchant merchant) {
        if (merchant.getName().isEmpty()) return null;
        merchant = merchantRepository.save(merchant);
        return merchant;
    }

    @Override
    public List<Merchant> getAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Optional<Merchant> getById(UUID id) {
        Optional<Merchant> merchantOptional = merchantRepository.findById(id);
        if (merchantOptional.isEmpty()){
            logger.info("There's no Merchant yet");
            throw new RuntimeException();
        }
        return merchantOptional;
    }

    @Override
    public Merchant update(Merchant merchant, UUID id) {
        Optional<Merchant> existingMerchant = merchantRepository.findById(id);

        if (existingMerchant.isEmpty()) {
            logger.info("Merchant not found");
            throw new RuntimeException("Merchant not found");
        }

        Merchant updatedMerchant = existingMerchant.get();

        if (!updatedMerchant.isDeleted()) {
            updatedMerchant.setId(id);
            updatedMerchant.setName(merchant.getName());
            updatedMerchant.setLocation(merchant.getLocation());
            updatedMerchant.setOpen(merchant.isOpen());
            return merchantRepository.save(updatedMerchant);
        } else {
            logger.info("Merchant has been deleted");
            throw new RuntimeException("Merchant has been deleted and cannot be updated");
        }
    }

    @Override
    public Merchant updateByName(MerchantEditNameDto merchantEditNameDto, UUID id) {
        Optional<Merchant> existingMerchant = merchantRepository.findById(id);

        if (existingMerchant.isEmpty()) {
            logger.info("Merchant not found");
            throw new RuntimeException("Merchant not found");
        }

        Merchant updatedMerchant = existingMerchant.get();

        if (!updatedMerchant.isDeleted()) {
            updatedMerchant.setName(merchantEditNameDto.getName());
            return merchantRepository.save(updatedMerchant);
        } else {
            logger.info("Merchant has been deleted");
            throw new RuntimeException("Merchant has been deleted and cannot be updated");
        }
    }

    @Override
    public void delete(UUID id) {
//        Merchant merchant = getById(id);
//        LocalDateTime currentDate = LocalDateTime.now();
//        merchant.setDeletedDate(currentDate);
//        merchantRepository.save(merchant);
////        merchantRepository.deleteById(id);
        Optional<Merchant> merchantOptional = getById(id);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            LocalDateTime currentDate = LocalDateTime.now();
            merchant.setDeletedDate(currentDate);
            merchantRepository.save(merchant);
            merchantRepository.deleteById(id);
        } else {
            ResponseUtil.notFoundResponse("Merchant Not Found");
        }
    }
}
