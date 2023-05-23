package com.example.costume_rental.service;

import com.example.costume_rental.dto.CostumeDTO;
import com.example.costume_rental.model.CostumeBorrowingDetail;
import com.example.costume_rental.repository.CostumeBorrowingDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CostumeBorrowingDetailService {
    private final CostumeBorrowingDetailRepository repository;
    public List<CostumeDTO> getListCostumes(Integer idCustomer) {
        List<Object[]> objects = repository.getListCostumes(idCustomer);
        List<CostumeDTO> res = new ArrayList<>();
        for (Object[] obj:objects) {
            CostumeDTO costumeDTO = CostumeDTO.builder()
                    .customerId((Integer) obj[0]).customerName((String) obj[1]).costumeName((String) obj[2])
                    .costumeType((String) obj[3]).costumePrice((Double) obj[4]).borrowedDate((Date) obj[5])
                    .rentCost((Double) obj[6]).totalDeposit((Double) obj[7]).quantity((Integer) obj[8])
                    .costumeId((Integer) obj[9]).costumeBorrowingDetailId((Integer) obj[10]).borrowedDays((Integer) obj[11])
                    .build();
            res.add(costumeDTO);
        }
        return res;
    }

    public CostumeBorrowingDetail findById(Integer costumeBorrowingDetailId) {
        Optional<CostumeBorrowingDetail> costume = repository.findById(costumeBorrowingDetailId);
        return costume.orElse(null);
    }
}
