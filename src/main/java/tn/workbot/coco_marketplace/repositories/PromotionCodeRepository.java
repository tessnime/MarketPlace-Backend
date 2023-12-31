package tn.workbot.coco_marketplace.repositories;

import tn.workbot.coco_marketplace.entities.Product;
import tn.workbot.coco_marketplace.entities.PromotionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PromotionCodeRepository extends JpaRepository<PromotionCode, Long> {

    PromotionCode findByVoucherAndStartDateAndDiscountValue(String voucher, Date startDate,int discountValue);

   PromotionCode findByProductIdAndVoucher(Long id, String voucher);

}
