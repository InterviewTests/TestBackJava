package microservice.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import microservice.models.Spend;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.web.util.UriComponentsBuilder;
import microservice.services.SpendService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.http.MediaType;


// import java.text.SimpleDateFormat;
// import java.util.Date;
// import java.util.List;
// import java.util.Calendar;
// import java.util.TimeZone;
// import org.bson.types.ObjectId;
// import java.text.ParseException;
// import microservice.models.Category;
// import microservice.repositories.SpendRepository;
// import microservice.repositories.CategoryRepository;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SpendController {

    // private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    
    @Autowired
    private SpendService spendService;


    @RequestMapping(value = "/spend", 
                    method = RequestMethod.POST, 
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Spend> insertNewSpend(UriComponentsBuilder builder,
                                    @Valid @RequestBody Spend spend) 
                                    throws URISyntaxException, InterruptedException, ExecutionException {

            CompletableFuture<Spend> spendFuture = spendService.insertNewSpend(spend);
            Spend storedSpend = spendFuture.get();
            return ResponseEntity
                        .created(new URI(builder.toUriString() + spend.get_id()))
                        .body(storedSpend);
        
    }


    // @RequestMapping(value = "/spends", method = RequestMethod.GET)
    // public Callable<ResponseEntity<List<Spend>>> getSpendListByDate(
    //     @RequestParam(value="startDate", defaultValue="") String startDateString,
    //     @RequestParam(value="endDate", defaultValue="") String endDateString) {
        
    //     return () -> { 
    //         Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    //         Date now = calendar.getTime();
    //         calendar.add(Calendar.DATE, -1);

    //         Date startDate = null;
    //         Date endDate = null;
    //         try {
    //             startDate = startDateString.trim().isEmpty() || startDateString.equals(null) ? 
    //                             calendar.getTime() : dateFormat.parse(startDateString);
    //             endDate = startDateString.trim().isEmpty() || startDateString.equals(null) ? 
    //                             now : dateFormat.parse(endDateString);
    //         }
    //         catch (ParseException e) {
    //             // Implement the exception handling strategy here...
    //             e.printStackTrace();
    //         } 
            
    //         return ResponseEntity.ok(spendRepo.findByDate(startDate, endDate));
    //     };
    // }

}
