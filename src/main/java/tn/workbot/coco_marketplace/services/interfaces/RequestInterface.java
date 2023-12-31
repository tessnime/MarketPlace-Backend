package tn.workbot.coco_marketplace.services.interfaces;

import com.google.maps.errors.ApiException;
import tn.workbot.coco_marketplace.entities.Pickup;
import tn.workbot.coco_marketplace.entities.Request;
import tn.workbot.coco_marketplace.entities.User;

import java.io.IOException;
import java.util.List;

public interface RequestInterface {
    public Request addRequest(Request request);
    public void removeRequest(Long id);
    public Request RetrieveRequest(Long id);
    public List<Request> RetrieveRequests();
    public Request updateRequest(Request request);
    public Request assignRequestDeliveryAgencyandDeliverymenandPickup(Request request, Long idPickup,Long idDeliveryMenAgency);
    public Request assignRequestDeliveryMenFreelancerandPickup(Request request, Long idPickup);

    public Request assignRequesttoseller(Long idRequest,String status,Long idPickup) throws IOException, InterruptedException, ApiException;
    public List<Request> retrieveRequestBySeller();
    public List<Request> retrieveRequestByPickup(Long idPickup);
    public List<Request> RetrieveRequestByAgency();
    public List<Request> RetrieveRequestByFreelancer();
    public User RetrieveFreelancerDeliveryrByRequest(Long idRequest);
    public int countRequestTotalForAgencyPending();
    public int countRequestApprovedForAgency();
    public int countRequestRejectForAgency();
    public List<Request> retrieveRequestApprovedOfPickupFreelancer();
    public List<Request> retrieveRequestApprovedOfPickupAgency();
    public int countRequestByPickup(Long idPickup);
    public List<Request> LastRequestCreatedForSeller();
    public List<Request> LastRequestAssignedToFreelancer();
    public List<Request> RetrieveRequestOfFreelancer();

}