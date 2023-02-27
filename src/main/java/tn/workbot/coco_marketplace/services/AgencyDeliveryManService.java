package tn.workbot.coco_marketplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.workbot.coco_marketplace.entities.AgencyDeliveryMan;
import tn.workbot.coco_marketplace.repositories.AgencyDeliveryManRepository;
import tn.workbot.coco_marketplace.services.interfaces.AgencyDeliveryManIService;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyDeliveryManService implements AgencyDeliveryManIService {
    @Autowired
    AgencyDeliveryManRepository admr;

    @Override
    public AgencyDeliveryMan addAgencyDeliveryMan(AgencyDeliveryMan agencyDeliveryMan) {
        return admr.save(agencyDeliveryMan);
    }

    @Override
    public AgencyDeliveryMan UpdateAgencyDeliveryMan(AgencyDeliveryMan agencyDeliveryMan) {
        return admr.save(agencyDeliveryMan );
    }

    @Override
    public void removeAgencyDeliveryMan(Long id) {
        admr.deleteById(id);
    }

    @Override
    public AgencyDeliveryMan RetrieveAgencyDeliveryMan(Long id) {
        if(admr.findById(id).isPresent())
        return admr.findById(id).get();
        return new AgencyDeliveryMan();

    }

    @Override
    public List<AgencyDeliveryMan> RetrieveAgencyDeliveryMen() {
        return (List<AgencyDeliveryMan>) admr.findAll();
    }
}