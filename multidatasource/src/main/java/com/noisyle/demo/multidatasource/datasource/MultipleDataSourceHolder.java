package com.noisyle.demo.multidatasource.datasource;

public class MultipleDataSourceHolder {
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
}
