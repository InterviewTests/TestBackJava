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
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.text.ParseException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


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
            try { categoryRepo.save(c); }
            // do nothing if the category already exists
            catch (DuplicateKeyException ignore) { }
            
        }

        Page<Spend> storedSpends = spendRepo.findByDescriptionAndUserCodeAndCategoryNotNull(spend.getDescription(), 
                                                                                            spend.getUserCode(), 
                                                                                            PageRequest.of(0, 1));
        if (storedSpends.getNumberOfElements() > 0) 
            spend.setCategory(storedSpends.getContent().get(0).getCategory());
        return CompletableFuture.completedFuture(spendRepo.save(spend));  
    }


    @Async("ThreadPoolExecutor")
    public CompletableFuture<?> updateCategory(ObjectId spendId, String userId, Category newCategory) {
        Spend spend = spendRepo.findBy_id(spendId);
        if (spend != null) {
            if (spend.getUserCode().equals(userId)) {
                try { categoryRepo.save(newCategory); } 
                // do nothing if the category already exists
                catch (DuplicateKeyException ignore) { }

                spend.setCategory(newCategory.getCategory());
                spend = spendRepo.save(spend);
                return CompletableFuture.completedFuture(spend);  
            }
            else {
                return CompletableFuture.completedFuture(new Message("this spend does not belong to this user", userId, "forbidden"));
            }
        }
        else {
            return CompletableFuture.completedFuture(new Message("no spend with the provided spendId", userId, "failed"));
        }
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