package com.noisyle.demo.multidatasource.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {
    final static private Logger logger = LoggerFactory.getLogger(MultipleDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType = MultipleDataSourceHolder.getDataSourceType();
        logger.debug("Select DataSource: {}", dataSourceType);
        return dataSourceType;
    }

}
