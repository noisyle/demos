package com.noisyle.demo.multidatasource.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {
    final static private Logger logger = LoggerFactory.getLogger(MultipleDataSource.class);
    final static private ThreadLocal<String> DATA_SOURCE_TYPE_HOLDER = new ThreadLocal<String>();

    public static void setDataSourceType(String dataSourceType) {
        DATA_SOURCE_TYPE_HOLDER.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return DATA_SOURCE_TYPE_HOLDER.get();
    }

    public static void clearDataSourceType() {
        DATA_SOURCE_TYPE_HOLDER.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType = MultipleDataSource.getDataSourceType();
        logger.debug("Use DataSource: {}", dataSourceType == null ? "default" : dataSourceType);
        return dataSourceType;
    }

}
