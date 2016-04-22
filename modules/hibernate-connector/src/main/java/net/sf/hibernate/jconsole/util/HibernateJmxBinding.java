/*
 * Copyright (c) 2010
 *
 * This file is part of HibernateJConsole.
 *
 *     HibernateJConsole is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     HibernateJConsole is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with HibernateJConsole.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.hibernate.jconsole.util;

import net.sf.hibernate.jconsole.jmx.StatisticsService;
import org.hibernate.SessionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Exposes Hibernate to JMX.
 */
public class HibernateJmxBinding {

	public static final ObjectName HIBERNATE_STATISTICS;

	static {
		try {
			HIBERNATE_STATISTICS = new ObjectName(
					System.getProperty("hibernate.mbean", "Hibernate:application=Statistics"));
		} catch (MalformedObjectNameException e) {
			throw new RuntimeException(e);
		}
	}

	@Resource
	protected MBeanServer mBeanServer;

	protected SessionFactory sessionFactory;
	protected ObjectName statisticsBeanName;

	/**
	 * Constructor to use by Spring when annotation configuration is enabled.
	 */
	public HibernateJmxBinding() {
	}

	/**
	 * Creates a new instance of the HibernateJmxBinding using the given SessionFactory
	 * as source to publis the statistics via JMX.
	 *
	 * @param sessionFactory The SessionFactory to monitor.
	 */
	public HibernateJmxBinding(SessionFactory sessionFactory) {
		this(ManagementFactory.getPlatformMBeanServer(), sessionFactory);
	}

	/**
	 * Creates a new instance of the HibernateJmxBinding using the given SessionFactory
	 * as source to publis the statistics via JMX.
	 *
	 * @param mBeanServer	The MBeanServer to publish the JMX bean to.
	 * @param sessionFactory The SessionFactory to monitor.
	 */
	public HibernateJmxBinding(MBeanServer mBeanServer, SessionFactory sessionFactory) {
		this.mBeanServer = mBeanServer;
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Returns the object name for the published MBean.
	 *
	 * @return the object name for the published MBean.
	 * @throws Exception In case of the object name is invalid.
	 */
	public ObjectName getStatisticsBeanName() throws Exception {
		if (statisticsBeanName == null)
			statisticsBeanName = HIBERNATE_STATISTICS;
		return statisticsBeanName;
	}

	/**
	 * Registers the JMX binding.
	 *
	 * @throws Exception In case of the operation failed.
	 */
	@PostConstruct
	public void registerJmxBinding() throws Exception {
		// Enable Hibernate JMX Statistics
		StatisticsService statsMBean = new StatisticsService();
		statsMBean.setSessionFactory(sessionFactory);
		statsMBean.setStatisticsEnabled(true);
		mBeanServer.registerMBean(statsMBean, getStatisticsBeanName());
	}

	/**
	 * Removes the JMX binding.
	 *
	 * @throws Exception In case of the operation failed.
	 */
	@PreDestroy
	public void unregisterJmxBinding() throws Exception {
		if (statisticsBeanName != null)
			mBeanServer.unregisterMBean(statisticsBeanName);
	}
}
