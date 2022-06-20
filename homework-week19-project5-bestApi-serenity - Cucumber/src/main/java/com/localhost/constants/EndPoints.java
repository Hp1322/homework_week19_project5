package com.localhost.constants;

/**
 * Created by Harsh Patel
 */
public class EndPoints {

    /**
     * This is Endpoints of product api
     */
    public static final String GET_ALL_PRODUCT = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/products/{productID}";
    public static final String CREATE_PRODUCT_BY_ID = "/products";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{productID}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{productID}";

    /**
     * This is Endpoints of store api
     */
    public static final String GET_ALL_STORE = "/stores";
    public static final String GET_SINGLE_STORE_BY_ID = "/stores/{storeID}";
    public static final String CREATE_STORE_BY_ID = "/stores";
    public static final String UPDATE_STORE_BY_ID = "/stores/{storeID}";
    public static final String DELETE_STORE_BY_ID = "/stores/{storeID}";

    /**
     * This is Endpoints of service api
     */
    public static final String GET_ALL_SERVICE = "/services";
    public static final String GET_SINGLE_SERVICE_BY_ID = "/services/{serviceID}";
    public static final String CREATE_SERVICE_BY_ID = "/services";
    public static final String UPDATE_SERVICE_BY_ID = "/services/{serviceID}";
    public static final String DELETE_SERVICE_BY_ID = "/services/{serviceID}";

    /**
     * This is Endpoints of category api
     */
    public static final String GET_ALL_CATEGORY = "/categories";
    public static final String GET_SINGLE_CATEGORY_BY_ID = "/categories/{categoryID}";
    public static final String CREATE_CATEGORY_BY_ID = "/categories";
    public static final String UPDATE_CATEGORY_BY_ID = "/categories/{categoryID}";
    public static final String DELETE_CATEGORY_BY_ID = "/categories/{categoryID}";

    /**
     * This is Endpoints of utility api
     */
    public static final String GET_ALL_HEALTHCHECK = "/healthcheck";
    public static final String GET_ALL_VERSION = "/version";
}
