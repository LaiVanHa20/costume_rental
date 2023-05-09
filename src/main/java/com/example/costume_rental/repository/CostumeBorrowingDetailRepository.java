package com.example.costume_rental.repository;

import com.example.costume_rental.model.CostumeBorrowingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostumeBorrowingDetailRepository extends JpaRepository<CostumeBorrowingDetail, Integer> {
    @Query(value ="SELECT kh.id, kh.name, tp.name, tp.type, tp.price, pm.time, tpm.rent_cost, pm.total_deposit, tpm.quantity, tp.id, " +
            "DATEDIFF(CURDATE(), pm.time) AS borrowedDays " +
            "FROM tbl_customer kh " +
            "JOIN tbl_borrowing_slip pm ON kh.id = pm.customer_id " +
            "JOIN tbl_costume_borrowing_detail tpm ON pm.id = tpm.borrowing_slip_id " +
            "JOIN tbl_costume tp ON tpm.costume_id = tp.id " +
            "WHERE kh.id = :idCustomer " +
            "GROUP BY kh.id, kh.name, tp.name, tp.type, tp.price, pm.time, tpm.rent_cost, pm.total_deposit, tpm.quantity, tp.id ", nativeQuery = true)
    List<Object[]> getListCostumes(@Param("idCustomer") Integer idCustomer);

}
