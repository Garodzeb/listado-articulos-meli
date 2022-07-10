package com.grodriguez.melichallenge.framework.utils;

public class AppConstants {

    // region DB ROOM

    public static final String SEARCH_QUERIES_ROOM_TABLE_NAME = "t_search_queries";
    public static final String QUERY_PARAMETERS_ROOM_TABLE_NAME = "t_query_parameters";
    public static final String SITE_CATEGORIES_ROOM_TABLE_NAME = "t_site_categories";
    public static final String SITE_CURRENCIES_ROOM_TABLE_NAME = "t_site_currencies";
    public static final String SITE_METADATA_ROOM_TABLE_NAME = "t_sites_metadata";

    // endregion

    // region Shared Preferences

    public static final String SHARED_PREFERENCE_FILE_ID = "MELI_CHALLENGE";
    public static final String CURRENT_ITEM_ID_SHARED_PREFERENCE_KEY = "CURRENT_ITEM";

    // endregion

    // region Log

    public static final String APP_LOG_TAG = "MELI_CHALLENGE";

    // endregion

    // region Main Activity

    public static final int MAX_SEARCH_RESULTS_DISPLAY_QTY = 2000;
    public static final String SORT_FILTER_ID = "sort";
    public static final String SORT_FILTER_NAME = "Ordenar por:";

    // endregion

    // region Item Tags

    public static final String BEST_SELLER_TAG = "best_seller_candidate";

    // endregion

    // region others

    public static final String ITEM_CONDITION_NEW = "new";
    public static final String ITEM_CONDITION_USED = "used";
    public static final String OFFSET_FILTER_KEY = "offset";
    public static final int SCROLL_DIRECTION_UP = -1;
    public static final int SCROLL_DIRECTION_DOWN = 1;

    // endregion

}// End Class
