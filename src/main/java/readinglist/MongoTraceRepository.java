package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.Trace;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hreeman on 1/30/17.
 */

@Service
public class MongoTraceRepository implements TraceRepository {
    private MongoOperations mongoOps;

    @Autowired
    public MongoTraceRepository(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    @Override
    public List<Trace> findAll() {
        return mongoOps.findAll(Trace.class);
    }

    @Override
    public void add(Map<String, Object> traceInfo) {
        mongoOps.save(new Trace(new Date(), traceInfo));
    }
}
