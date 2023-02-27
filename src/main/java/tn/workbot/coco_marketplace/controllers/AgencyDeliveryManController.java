package tn.workbot.coco_marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.workbot.coco_marketplace.entities.AgencyDeliveryMan;
import tn.workbot.coco_marketplace.services.interfaces.AgencyDeliveryManIService;

import java.util.List;
import java.util.Optional;

@RestController
public class AgencyDeliveryManController  {
    @Autowired
    AgencyDeliveryManIService ad;
    @PostMapping("addAgencyDeliveryMan")
    public AgencyDeliveryMan addAgencyDeliveryMan(@RequestBody AgencyDeliveryMan agencyDeliveryMan) {
        return ad.addAgencyDeliveryMan(agencyDeliveryMan);
    }

    @DeleteMapping("removeAgencyDeliveryMan")
    public void removeAgencyDeliveryMan(@RequestParam Long id) {
          ad.removeAgencyDeliveryMan(id);
    }

    @GetMapping("RetrieveAgencyDeliveryMan")
    public AgencyDeliveryMan RetrieveAgencyDeliveryMan(@RequestParam Long id) {
        return ad.RetrieveAgencyDeliveryMan(id);
    }

    @GetMapping("RetrieveAgencyDeliveryMen")
    public List<AgencyDeliveryMan> RetrieveAgencyDeliveryMen() {
        return ad.RetrieveAgencyDeliveryMen();
    }
    @PutMapping("UpdateAgencyDeliveryMan")
    public AgencyDeliveryMan UpdateAgencyDeliveryMan(@RequestBody AgencyDeliveryMan agencyDeliveryMan) {
        return ad.UpdateAgencyDeliveryMan(agencyDeliveryMan);
    }
    }