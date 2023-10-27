package com.example.challenge_4.repository;

import com.example.challenge_4.model.Merchant;
import com.example.challenge_4.model.dto.MerchantViewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    @Procedure("edit_merchant_status")
    void editStatusMerchant(@Param("Merchant_name") String name, @Param("is_open") boolean isOpen);

    @Query("SELECT m.name AS name, m.location AS location FROM Merchant m WHERE m.open = true")
    List<MerchantViewDto> findOpenMerchants();
}
