package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hreeman on 1/30/17.
 */

@Component
public class ApplicationContextMetrics implements PublicMetrics {
    private ApplicationContext context;

    @Autowired
    public ApplicationContextMetrics(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Collection<Metric<?>> metrics() {
        List<Metric<?>> metrics = new ArrayList<Metric<?>>();

        metrics.add(new Metric<Long>("spring.context.startup-date", context.getStartupDate())); //애플리케이션 시작 시간
        metrics.add(new Metric<Integer>("spring.beans.definitions", context.getBeanDefinitionCount())); //빈 정의 개수
        metrics.add(new Metric<Integer>("spring.beans", context.getBeanNamesForType(Object.class).length)); //빈 개수
        metrics.add(new Metric<Integer>("spring.controllers", context.getBeanNamesForAnnotation(Controller.class).length)); //컨트롤러 빈 개수

        return metrics;
    }
}
