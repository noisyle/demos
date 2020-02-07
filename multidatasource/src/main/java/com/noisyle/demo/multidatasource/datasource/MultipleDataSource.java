package com.noisyle.demo.multidatasource.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {
    public enum Target {
        DB1, DB2
    }
    
    final static private Logger logger = LoggerFactory.getLogger(MultipleDataSource.class);
    final static private ThreadLocal<Target> DATA_SOURCE_TYPE_HOLDER = new ThreadLocal<Target>();

    public static void setDataSourceType(Target dataSourceType) {
        DATA_SOURCE_TYPE_HOLDER.set(dataSourceType);
    }

    public static Target getDataSourceType() {
        return DATA_SOURCE_TYPE_HOLDER.get();
    }

    public static void clearDataSourceType() {
        DATA_SOURCE_TYPE_HOLDER.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Target dataSourceType = MultipleDataSource.getDataSourceType();
        logger.debug("Use DataSource: {}", dataSourceType == null ? "default" : dataSourceType);
        return dataSourceType;
    }
    
}
