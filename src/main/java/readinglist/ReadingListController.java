package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by hreeman on 12/31/16.
 */

@Controller
@RequestMapping("/")
public class ReadingListController {
    private ReadingListRepository readingListRepository;
    private AmazonProperties amazonProperties;
    private CounterService counterService;
    private GaugeService gaugeService;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository, AmazonProperties amazonProperties,
                                 CounterService counterService, GaugeService gaugeService) {
        this.readingListRepository = readingListRepository;
        this.amazonProperties = amazonProperties;
        this.counterService = counterService;
        this.gaugeService = gaugeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String readersBooks(Reader reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);

        if(readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
        }

        return "readingList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToReadingList(Reader reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);

        counterService.increment("books.saved"); //book.saved 메트릭 증가
        gaugeService.submit("books.last.saved", System.currentTimeMillis()); //book.last.saved 기록

        return "redirect:/";
    }
}
