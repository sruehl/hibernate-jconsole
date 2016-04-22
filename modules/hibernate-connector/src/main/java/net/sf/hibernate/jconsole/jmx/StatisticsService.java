package net.sf.hibernate.jconsole.jmx;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

/**
 * Own implementation of hibernate service as it got removed.
 *
 * @author Sebastian R&uml;hl, 2016-04-22
 * @version 1.0
 */
public class StatisticsService implements StatisticsServiceMBean {

    @Delegate
    @Getter
    @Setter
    private Statistics stats;

    @Getter
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        if (null != sessionFactory) {
            stats = sessionFactory.getStatistics();
        }
    }

}
