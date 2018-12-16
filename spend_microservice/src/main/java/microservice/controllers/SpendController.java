package microservice.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import microservice.models.Message;
import microservice.models.Spend;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import org.springframework.web.util.UriComponentsBuilder;
import microservice.services.SpendService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;


@RestController
public class SpendController {


    @Autowired
    private SpendService spendService;


    @RequestMapping(value = "/spend", 
                    method = RequestMethod.POST, 
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Spend> register(UriComponentsBuilder builder,
        @Valid @RequestBody Spend spend) throws URISyntaxException, InterruptedException, ExecutionException {

        CompletableFuture<Spend> spendFuture = spendService.insert(spend);
        Spend storedSpend = spendFuture.get();
        return ResponseEntity
                    .created(new URI(builder.toUriString() + spend.get_id()))
                    .body(storedSpend);
        
    }


    @RequestMapping(value = "/user/spends", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getUserSpends(HttpServletRequest request,
        @RequestParam(value="startDate", defaultValue="") String startDateStr,
        @RequestParam(value="endDate", defaultValue="") String endDateStr) 
        throws ValidationException, InterruptedException, ExecutionException, ParseException {
        
        CompletableFuture<?> spendFuture = spendService.filterBetweenDates(startDateStr, endDateStr, request.getAttribute("userId").toString());
        Object result = spendFuture.get();
        if (result.getClass() == Message.class) 
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        else
            return ResponseEntity.ok(result);
    }

}
