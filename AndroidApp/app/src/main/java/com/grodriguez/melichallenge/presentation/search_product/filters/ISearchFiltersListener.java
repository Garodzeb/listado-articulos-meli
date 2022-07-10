package com.grodriguez.melichallenge.presentation.search_product.filters;

import com.grodriguez.melisearchcore.model.domain.search.Filter;
import com.grodriguez.melisearchcore.model.domain.search.FilterValue;

public interface ISearchFiltersListener {

    void filterSelected(Filter filter, FilterValue value);
    void removeSelected(Filter filter, FilterValue value);

}// End Interface
