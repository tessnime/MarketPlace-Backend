package tn.workbot.coco_marketplace.services.interfaces;

import tn.workbot.coco_marketplace.entities.AgencyBranch;
import tn.workbot.coco_marketplace.entities.AgencyDeliveryMan;
import tn.workbot.coco_marketplace.entities.Pickup;

import java.util.List;
import java.util.Optional;

public interface AgencyDeliveryManIService {
    public AgencyDeliveryMan addAgencyDeliveryMan(AgencyDeliveryMan agencyDeliveryMan);
    public AgencyDeliveryMan UpdateAgencyDeliveryMan(AgencyDeliveryMan agencyDeliveryMan);
    public void removeAgencyDeliveryMan(Long id);
    public AgencyDeliveryMan RetrieveAgencyDeliveryMan(Long id);
    public List<AgencyDeliveryMan> RetrieveAgencyDeliveryMen();
    public AgencyDeliveryMan AssignAgencyDeliveryManByBranch(AgencyDeliveryMan agencyDeliveryMan,Long Id);
    public List<AgencyDeliveryMan> RetrieveDeliverymenByagencyWhenThegovernorateOfPickupisSomeGovernorateofdeliverymen(Long idPickup);
    public List<AgencyDeliveryMan> RetrieveDeliveryMenByBranch(Long idBranch);
    public int countDeliveryMenInBranch(Long idBranch);
    public List<AgencyDeliveryMan> TopDeliveryMenByPickupDelivered();
    public List<AgencyBranch> TopDeliveryAgencyByPickupDelivered();
}
