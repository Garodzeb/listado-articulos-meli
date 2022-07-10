package com.grodriguez.melichallenge.presentation.search_product.filters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grodriguez.melichallenge.R;
import com.grodriguez.melichallenge.databinding.FragmentFilterTypeCheckboxBinding;
import com.grodriguez.melichallenge.databinding.FragmentFilterTypeRadiobuttonBinding;
import com.grodriguez.melichallenge.databinding.FragmentFilterTypeSwitchBinding;
import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melisearchcore.model.domain.search.Filter;
import com.grodriguez.melisearchcore.model.domain.search.FilterValue;
import com.grodriguez.melisearchcore.model.enums.FilterType;

import java.util.ArrayList;
import java.util.List;

public class SearchFiltersAdapter extends RecyclerView.Adapter<SearchFiltersAdapter.SearchFiltersItemViewHolder> {

    enum SearchFilterViewHolderType {
        RADIO_BUTTON(0),
        CHECKBOX(1),
        SWITCH(2);

        private final int value;

        SearchFilterViewHolderType(int val) {
            this.value = val;
        }

        public int getValue() {
            return value;
        }
    }

    ISearchFiltersListener listener;
    List<Filter> filters = new ArrayList<>();

    public SearchFiltersAdapter(ISearchFiltersListener listener) {
        this.listener = listener;
    }

    // region Public Methods

    @NonNull
    @Override
    public SearchFiltersItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        SearchFiltersItemViewHolder viewHolder;

        // Determina el tipo de ViewHolder a renderizar en base al tipo de filtro
        if (viewType == SearchFilterViewHolderType.CHECKBOX.getValue()) {
            FragmentFilterTypeCheckboxBinding binding = FragmentFilterTypeCheckboxBinding.inflate(inflater, parent, false);
            viewHolder = new SearchFiltersCheckboxViewHolder(binding.getRoot(),
                    parent.getContext(),
                    listener,
                    binding);

        } else if (viewType == SearchFilterViewHolderType.SWITCH.getValue()) {
            FragmentFilterTypeSwitchBinding binding = FragmentFilterTypeSwitchBinding.inflate(inflater, parent, false);
            viewHolder = new SearchFiltersSwitchViewHolder(binding.getRoot(),
                    parent.getContext(),
                    listener,
                    binding);

        } else {
            FragmentFilterTypeRadiobuttonBinding binding = FragmentFilterTypeRadiobuttonBinding.inflate(inflater, parent, false);
            viewHolder = new SearchFiltersRadioButtonViewHolder(binding.getRoot(),
                    parent.getContext(),
                    listener,
                    binding);
        }

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        // El filtro por defecto para mostrar es de tipo RadioButton
        SearchFilterViewHolderType itemViewType = SearchFilterViewHolderType.RADIO_BUTTON;

        if (filters.size() > 0) {
            Filter filter = filters.get(position);
            itemViewType = getFilterViewType(filter);
        }

        return itemViewType.getValue();
    }

    @Override
    public void onBindViewHolder(@NonNull SearchFiltersItemViewHolder holder, int position) {
        try {
            if (filters.size() > 0) {
                Filter filter = filters.get(position);
                holder.bind(filter);
            }
        }
        catch (Exception ex)
        {
            AppUtils.logError(ex);
        }
    }

    @Override
    public int getItemCount() {
        return (filters != null) ? filters.size() : 0;
    }

    public void updateFilters(List<Filter> newFilters) {
        if (filters != null && newFilters != null) {
            filters.clear();
            filters.addAll(newFilters);
            notifyDataSetChanged();
        }
    }

    // endregion

    // region Private Methods

    // Determina el tipo de filtro a mostrar dependiendo del tipo de filtro aplicado
    public SearchFilterViewHolderType getFilterViewType(Filter filter) {
        SearchFilterViewHolderType filterViewType = SearchFilterViewHolderType.RADIO_BUTTON;

        if (filter.getType() == FilterType.BOOLEAN)
            filterViewType = SearchFilterViewHolderType.SWITCH;

        else if (filter.getType() == FilterType.TEXT)
            filterViewType = SearchFilterViewHolderType.CHECKBOX;

        return filterViewType;
    }

    // endregion

    // region ViewHolders

    public static abstract class SearchFiltersItemViewHolder extends RecyclerView.ViewHolder {
        protected Context context;
        protected Filter currentFilter;
        protected ISearchFiltersListener observer;

        public SearchFiltersItemViewHolder(@NonNull View itemView,
                                           Context context,
                                           ISearchFiltersListener observer) {
            super(itemView);
            this.context = context;
            this.observer = observer;
        }

        public abstract void bind(Filter filter);
    }

    public static class SearchFiltersRadioButtonViewHolder extends SearchFiltersItemViewHolder {

        boolean expanded = false;
        FragmentFilterTypeRadiobuttonBinding binding;

        public SearchFiltersRadioButtonViewHolder(@NonNull View itemView,
                                                  Context context,
                                                  ISearchFiltersListener observer,
                                                  FragmentFilterTypeRadiobuttonBinding binding) {
            super(itemView, context, observer);
            this.binding = binding;
        }

        @Override
        public void bind(Filter filter) {
            currentFilter = filter;
            binding.lblFragFilterTypeRadiobuttonTitle.setText(filter.getName());

            // Elimina los botones previos que puedan existir
            binding.rgFragFilterTypeRadiobuttonAvialableValues.removeAllViews();

            // Por cada valor posible del filtro crea un nuevo RadioButton
            for (FilterValue fValue : filter.getValues()) {
                RadioButton rb = createRadioButton(fValue);
                binding.rgFragFilterTypeRadiobuttonAvialableValues.addView(rb);
            }

            binding.btnFragFilterTypeRadiobuttonsClean.setOnClickListener(view -> {
                binding.txtFragFilterTypeRadiobuttonSelectedValue.setText("");
                binding.rgFragFilterTypeRadiobuttonAvialableValues.clearCheck();
            });

            // Agrega un evento onClick a la vista para expandir o ocular los filtros disponibles
            binding.cvFragFilterTypeRadioButtons.setOnClickListener(view -> {
                if (expanded)
                    hideValues();
                else
                    showValues();
            });
        }

        // region Private Methods

        private RadioButton createRadioButton(FilterValue value) {
            RadioButton rb = new RadioButton(context);
            rb.setId(View.generateViewId());
            rb.setChecked(value.isSelected());
            rb.setText(value.getName());
            rb.setTag(value.getId());
            rb.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT));

            // Si es el filtro que seleccionó el usuario cambia la visualización del mismo
            if (value.isSelected()) {
                rb.setTextColor(context.getColor(R.color.text_button_color));
                binding.txtFragFilterTypeRadiobuttonSelectedValue.setText(value.getName());
            }

            // Asigna el evento para agregar/eliminar el filtro
            rb.setOnCheckedChangeListener((button, checked) -> {
                if (checked) {
                    binding.txtFragFilterTypeRadiobuttonSelectedValue.setText(value.getName());
                    observer.filterSelected(currentFilter, value);
                }
                else
                    observer.removeSelected(currentFilter, value);
            });

            hideValues();

            return rb;
        }

        private void showValues() {
            expanded = true;
            binding.imgFragFilterTypeRadiobuttonExpand.setImageResource(R.drawable.ic_outline_expand_less_24);
            binding.btnFragFilterTypeRadiobuttonsClean.setVisibility(View.VISIBLE);
            binding.rgFragFilterTypeRadiobuttonAvialableValues.setVisibility(View.VISIBLE);
        }

        private void hideValues() {
            expanded = false;
            binding.imgFragFilterTypeRadiobuttonExpand.setImageResource(R.drawable.ic_outline_expand_more_24);
            binding.btnFragFilterTypeRadiobuttonsClean.setVisibility(View.GONE);
            binding.rgFragFilterTypeRadiobuttonAvialableValues.setVisibility(View.GONE);
        }

        // endregion

    }// End ViewHolder

    public static class SearchFiltersCheckboxViewHolder extends SearchFiltersItemViewHolder {

        boolean expanded = false;
        List<FilterValue> selectedValues = new ArrayList<>();
        FragmentFilterTypeCheckboxBinding binding;

        public SearchFiltersCheckboxViewHolder(@NonNull View itemView,
                                               Context context,
                                               ISearchFiltersListener observer,
                                               FragmentFilterTypeCheckboxBinding binding) {
            super(itemView, context, observer);
            this.binding = binding;
        }

        @Override
        public void bind(Filter filter) {
            currentFilter = filter;
            binding.lblFragTypeCheckboxTitle.setText(filter.getName());

            // Elimina los controles previos de la interfaz
            selectedValues = new ArrayList<>();
            binding.txtFragTypeCheckboxSelectedValues.setText("");
            binding.linearLayoutFragTypeCheckboxAvailableValues.removeAllViews();

            // Crea un checkbox por cada uno de los valores posibles del filtro
            for(FilterValue fValue : filter.getValues())
            {
                CheckBox cb = createCheckbox(fValue);
                binding.linearLayoutFragTypeCheckboxAvailableValues.addView(cb);
            }

            // Actualiza el texto mostrado en pantalla con los filtros seleccionados
            updateDisplayText();

            // Agrega un evento onClick a la vista para expandir o ocular los filtros disponibles
            binding.cvFragFilterTypeCheckbox.setOnClickListener(view -> {
                if (expanded)
                    hideValues();
                else
                    showValues();
            });

            hideValues();
        }

        // region Private Methods

        private CheckBox createCheckbox(FilterValue value) {
            CheckBox cb = new CheckBox(context);
            cb.setId(View.generateViewId());
            cb.setChecked(value.isSelected());
            cb.setText(value.getName());
            cb.setTag(value.getId());
            cb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            if (value.isSelected()) {
                selectedValues.add(value);
            }

            cb.setOnCheckedChangeListener((button, checked) -> {
                selectedValues.remove(value);

                if (checked) {
                    binding.txtFragTypeCheckboxSelectedValues.setText(value.getName());
                    observer.filterSelected(currentFilter, value);
                    selectedValues.add(value);
                }
                else
                    observer.removeSelected(currentFilter, value);

                updateDisplayText();
            });

            return cb;
        }

        private void updateDisplayText() {
            String displayText = "";

            if(selectedValues != null && selectedValues.size() > 0) {
                StringBuilder builder = new StringBuilder();

                for (FilterValue filter : selectedValues) {
                    if (builder.length() > 0)
                        builder.append(",");

                    builder.append(filter.getName());
                }

                displayText = builder.toString();
            }

            binding.txtFragTypeCheckboxSelectedValues.setText(displayText);
        }

        private void hideValues() {
            expanded = false;
            binding.imgFragTypeCheckboxExpand.setImageResource(R.drawable.ic_outline_expand_more_24);
            binding.linearLayoutFragTypeCheckboxAvailableValues.setVisibility(View.GONE);
        }

        private void showValues() {
            expanded = true;
            binding.imgFragTypeCheckboxExpand.setImageResource(R.drawable.ic_outline_expand_less_24);
            binding.linearLayoutFragTypeCheckboxAvailableValues.setVisibility(View.VISIBLE);
        }

        // endregion

    }// End ViewHolder

    public static class SearchFiltersSwitchViewHolder extends SearchFiltersItemViewHolder {

        FilterValue currentValue;
        FragmentFilterTypeSwitchBinding binding;

        public SearchFiltersSwitchViewHolder(@NonNull View itemView,
                                             Context context,
                                             ISearchFiltersListener observer,
                                             FragmentFilterTypeSwitchBinding binding) {
            super(itemView, context, observer);
            this.binding = binding;
        }

        @Override
        public void bind(Filter filter) {
            currentFilter = filter;
            currentValue = filter.getValues().get(0);

            if(currentValue.isSelected())
                binding.swFragFilterTypeSwitchValue.setChecked(true);

            binding.swFragFilterTypeSwitchValue.setText(currentValue.getName());
            binding.swFragFilterTypeSwitchValue.setChecked(currentValue.isSelected());

            binding.swFragFilterTypeSwitchValue.setOnCheckedChangeListener((button, checked) -> {
                if(checked) {
                    currentValue.setSelected(true);
                    observer.filterSelected(filter, currentValue);
                }
                else {
                    currentValue.setSelected(false);
                    observer.removeSelected(filter, currentValue);
                }
            });
        }
    }

    // endregion

}// End Class
