package tn.workbot.coco_marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.workbot.coco_marketplace.entities.PromotionCode;
import tn.workbot.coco_marketplace.services.interfaces.PromotionCodeInterface;

import java.util.List;

@RestController
@RequestMapping("PromotionCode")
public class PromortionCodeController {

    @Autowired
    private PromotionCodeInterface promotionCodeInterface;

    @PostMapping("savePromotionCode")
    public PromotionCode create(@RequestBody PromotionCode p) {
        return promotionCodeInterface.create(p);
    }

    @PutMapping("UpdatePromotionCode")
    public PromotionCode update(PromotionCode p) {
        return promotionCodeInterface.update(p);
    }

    @GetMapping("RetrieveAllPromotionCodes")
    public List<PromotionCode> retrieveAll() {
        return promotionCodeInterface.retrieveAll();
    }

    @GetMapping("GetPCById")
    public PromotionCode getById(Long id) {
        return promotionCodeInterface.getById(id);
    }

    @DeleteMapping("DeletePromotionCode")
    public void delete(PromotionCode p) {
        promotionCodeInterface.delete(p);
    }

}
