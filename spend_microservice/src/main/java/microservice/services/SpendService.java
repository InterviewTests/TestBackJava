package microservice.services;


import microservice.repositories.SpendRepository;
import microservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import microservice.models.Spend;
import microservice.models.Category;
import org.bson.types.ObjectId;
import microservice.models.Message;
import java.util.concurrent.CompletableFuture;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.text.ParseException;


@Service
public class SpendService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    
    @Autowired
    private SpendRepository spendRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Async("ThreadPoolExecutor")
    public CompletableFuture<Spend> insert(Spend spend) {
        spend.set_id(ObjectId.get());
        if (spend.getCategory() != null) {
            Category c = new Category(spend.getCategory());
            categoryRepo.save(c);
        }
        return CompletableFuture.completedFuture(spendRepo.save(spend));  
    }


    @Async("ThreadPoolExecutor")
    public CompletableFuture<List<Spend>> getByUserId(String userId) {
        return CompletableFuture.completedFuture(spendRepo.findByUserCode(userId));  
    }

    @Async("ThreadPoolExecutor")
    public CompletableFuture<?> filterBetweenDates(String startDateStr, String endDateStr, String userId)
        throws ParseException, ValidationException {
        
        startDateStr = startDateStr.replace("Z", "+0000").replace(" ", "+");
        endDateStr = endDateStr.replace("Z", "+0000").replace(" ", "+");
        
        // getting the current Date as a UTC aware object
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Date now = calendar.getTime();
        
        Date startDate = startDateStr.trim().isEmpty() ? addDays(now, -7) : dateFormat.parse(startDateStr);
        Date endDate = startDateStr.trim().isEmpty() ? now : dateFormat.parse(endDateStr);

        if (startDate.after(endDate)) 
            return CompletableFuture.completedFuture(new Message("startDate cannot be after endDate", userId, "failed"));
        else if (addDays(startDate, 21).before(endDate)) 
            return CompletableFuture.completedFuture(new Message("the time range between startDate and endDate have to be smaller than 21 days", userId, "failed"));
     
        return CompletableFuture.completedFuture(spendRepo.findByStartAndEndDate(startDate, endDate, userId));  
    }


    public static Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}