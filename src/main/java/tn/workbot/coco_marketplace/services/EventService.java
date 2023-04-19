package tn.workbot.coco_marketplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.workbot.coco_marketplace.configuration.SessionService;
import tn.workbot.coco_marketplace.entities.Event;
import tn.workbot.coco_marketplace.entities.KeyWords;
import tn.workbot.coco_marketplace.entities.Product;
import tn.workbot.coco_marketplace.entities.User;
import tn.workbot.coco_marketplace.entities.enmus.ProductFiltre;
import tn.workbot.coco_marketplace.repositories.EventRepository;
import tn.workbot.coco_marketplace.repositories.KeyWordsRepository;
import tn.workbot.coco_marketplace.repositories.OrderRepository;
import tn.workbot.coco_marketplace.repositories.UserrRepository;
import tn.workbot.coco_marketplace.services.interfaces.EventInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EventService implements EventInterface {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    KeyWordsRepository keyWordsRepository;

    @Autowired
    UserrRepository userrRepository;

    @Autowired
    OrderServices orderServices;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SessionService sessionService;


    @Override
    public void addEvent(Event event) {
        //session Manager id
        User user=userrRepository.findById(sessionService.getUserBySession().getId()).get();
        event.setUser(user);
        eventRepository.save(event);
    }

    @Override
    public Event findEventByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    @Override
    public Event findEventById(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public List<Event> displayRunningEvents() {
        List<Event> eventList= eventRepository.findAll();
        List<Event> eventList1=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        for (Event e:eventList)
        {
            if(currentDate.after(e.getStartDate()) && currentDate.before(e.getLastDate()))
                eventList1.add(e);
        }
        return eventList1;
    }

    @Override
    public List<Event> displayAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void updateEvent(Event event) {
        //session Manager id
        User user=userrRepository.findById(sessionService.getUserBySession().getId()).get();
        event.setUser(user);
        Event event1=findEventByTitle(event.getTitle());
        if (event1!=null)
        event.setId(event1.getId());
        eventRepository.save(event);
    }

    @Override
    public void addKeyWordToEvent(Long id, KeyWords keyWords) {
    Event event=eventRepository.findById(id).get();
    event.getListkeyWords().add(keyWordsRepository.save(keyWords));
    event.setProductList(displayProductForEvent(event.getId()));
    eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
    eventRepository.deleteById(id);
    }

    @Override
    public List<Product> displayProductForEvent(Long id)
    {
        List<Product> productList=new ArrayList<>();
        Event event=eventRepository.findById(id).get();
        String key;
        for (KeyWords k:event.getListkeyWords())
        {
            key='%'+k.getWord()+'%';
            productList.addAll(orderRepository.productsByNameLike(key));
        }
        if(productList.size()>0)
        return productList;
        return new ArrayList<>();
    }

    private static final String FILE_DIRECTORY = "C:/xampp/htdocs/MarketPlace-Frontend/src/assets/uploads";
    @Override
    public void storeFile(MultipartFile file) throws IOException {
        Path filePath = Paths.get(FILE_DIRECTORY + "/" + file.getOriginalFilename());

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }
}
