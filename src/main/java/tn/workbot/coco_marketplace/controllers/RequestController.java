package tn.workbot.coco_marketplace.controllers;

import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.workbot.coco_marketplace.entities.Request;
import tn.workbot.coco_marketplace.entities.User;
import tn.workbot.coco_marketplace.services.interfaces.RequestInterface;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("RequestController")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('SELLER') || hasAuthority('DELIVERYAGENCY') || hasAuthority('DELIVERYMEN')")
public class RequestController {
    @Autowired
    RequestInterface ri;

    @PostMapping("addRequest")
    public Request addRequest(@RequestBody Request request) {
        return ri.addRequest(request);
    }

    @DeleteMapping("deleteRequest")
    public void removeRequest(@RequestParam Long id) {
        ri.removeRequest(id);
    }

    @GetMapping("retrieveRequest")
    public Request RetrieveRequest(@RequestParam Long id) {
        return ri.RetrieveRequest(id);
    }

    @GetMapping("retriveRequests")
    public List<Request> RetrieveRequests() {
        return ri.RetrieveRequests();
    }

    @PutMapping("updateRequest")
    public Request updateRequest(@RequestBody Request request) {
        return ri.updateRequest(request);
    }

    @PostMapping("assignRequestDeliveryAgencyandPickup")
    public Request assignRequestDeliveryAgencyandPickup(@RequestBody Request request, @RequestParam Long idPickup, @RequestParam Long idDeliveryMenAgency) {
        return ri.assignRequestDeliveryAgencyandDeliverymenandPickup(request, idPickup, idDeliveryMenAgency);
    }

    @PostMapping("assignRequestDeliveryMenFreelancerandPickup")
    public Request assignRequestDeliveryMenFreelancerandPickup(@RequestBody Request request, @RequestParam Long idPickup) {
        return ri.assignRequestDeliveryMenFreelancerandPickup(request, idPickup);
    }

    @PostMapping("assignRequesttoseller")
    public Request assignRequesttoseller(@RequestParam Long idRequest, @RequestParam String status, @RequestParam Long idPickup) throws IOException, InterruptedException, ApiException {
        return ri.assignRequesttoseller(idRequest, status, idPickup);

    }

    @GetMapping("retrieveRequestBySeller")
    public List<Request> retrieveRequestBySeller() {
        return ri.retrieveRequestBySeller();
    }

    @GetMapping("retrieveRequestByPickup")
    public List<Request> retrieveRequestByPickup(Long idPickup) {
        return ri.retrieveRequestByPickup(idPickup);
    }

    @GetMapping("RetrieveRequestByAgency")

    public List<Request> RetrieveRequestByAgency() {
        return ri.RetrieveRequestByAgency();
    }

    @GetMapping("RetrieveRequestByFreelancer")
    public List<Request> RetrieveRequestByFreelancer() {
        return ri.RetrieveRequestByFreelancer();
    }

    @GetMapping("RetrieveFreelancerDeliveryrByRequest")
    public User RetrieveFreelancerDeliveryrByRequest(@RequestParam Long idRequest) {
        return ri.RetrieveFreelancerDeliveryrByRequest(idRequest);
    }

    @GetMapping("countRequestTotalForAgencyPending")
    public int countRequestTotalForAgencyPending() {
        return ri.countRequestTotalForAgencyPending();
    }

    @GetMapping("countRequestApprovedForAgency")
    public int countRequestApprovedForAgency() {
        return ri.countRequestApprovedForAgency();
    }

    @GetMapping("countRequestRejectForAgency")
    public int countRequestRejectForAgency() {
        return ri.countRequestRejectForAgency();
    }

    @GetMapping("retrieveRequestApprovedOfPickupFreelancer")
    public List<Request> retrieveRequestApprovedOfPickupFreelancer() {
        return ri.retrieveRequestApprovedOfPickupFreelancer();
    }

    @GetMapping("retrieveRequestApprovedOfPickupAgency")
    public List<Request> retrieveRequestApprovedOfPickupAgency() {
        return ri.retrieveRequestApprovedOfPickupAgency();
    }

    @GetMapping("countRequestByPickup")
    public int countRequestByPickup(@RequestParam Long idPickup) {
        return ri.countRequestByPickup(idPickup);
    }

    @GetMapping("LastRequestCreatedForSeller")
    public List<Request> LastRequestCreatedForSeller() {
        return ri.LastRequestCreatedForSeller();
    }
    @GetMapping("LastRequestAssignedToFreelancer")
    public List<Request> LastRequestAssignedToFreelancer() {
        return ri.LastRequestAssignedToFreelancer();
    }
    @GetMapping("RetrieveRequestOfFreelancer")
    public List<Request> RetrieveRequestOfFreelancer() {
        return ri.RetrieveRequestOfFreelancer();
    }
    }
