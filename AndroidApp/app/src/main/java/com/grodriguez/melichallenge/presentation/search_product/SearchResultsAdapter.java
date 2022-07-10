package com.grodriguez.melichallenge.presentation.search_product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grodriguez.melichallenge.R;
import com.grodriguez.melichallenge.databinding.FragmentSearchResultsItemBinding;
import com.grodriguez.melichallenge.framework.utils.AppConstants;
import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melisearchcore.model.domain.items.ItemDetail;
import com.grodriguez.melisearchcore.model.domain.search.SearchItem;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchResultItemViewHolder> {

    public interface IItemSelectedListener {
        void onItemSelected(SearchItem item);
    }

    List<SearchItem> resultItems = new ArrayList<>();
    IItemSelectedListener listener;

    public SearchResultsAdapter(IItemSelectedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchResultItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentSearchResultsItemBinding itemBinding = FragmentSearchResultsItemBinding.inflate(inflater, parent, false);

        return new SearchResultItemViewHolder(itemBinding.getRoot(), parent.getContext(), itemBinding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultItemViewHolder holder, int position) {
        holder.bind(resultItems.get(position));
    }

    @Override
    public int getItemCount() {
        return resultItems.size();
    }

    public void updateItems(List<SearchItem> newItems) {
        resultItems.clear();
        resultItems.addAll(newItems);
        notifyDataSetChanged();
    }

    static class SearchResultItemViewHolder extends RecyclerView.ViewHolder {
        SearchItem item;
        Context context;
        IItemSelectedListener listener;
        FragmentSearchResultsItemBinding binding;

        public SearchResultItemViewHolder(@NonNull View itemView,
                                          Context context,
                                          FragmentSearchResultsItemBinding binding,
                                          IItemSelectedListener listener) {
            super(itemView);
            this.context = context;
            this.binding = binding;
            this.listener = listener;
        }

        public void bind(SearchItem item) {
            this.item = item;
            binding.txtFragSearchResultsItemTitle.setText(item.getTitle());

            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setGroupingUsed(true);
            String price = formatter.format(item.getPrice());

            binding.txtFragSearchResultsItemPrice.setText(String.format("%s %s", item.getCurrency().getSymbol(), price));

            ImageView imgItemThumbnail  = binding.imgFragSearchResultsItemImage;
            Picasso.get().load(item.getThumbnailUrl()).into(imgItemThumbnail);

            if (item.getShipping().isFreeShipping())
                binding.txtFragSearchResultsItemFreeShipping.setVisibility(View.VISIBLE);

            if (item.isBestSeller())
                binding.txtFragSearchResultsItemBestSeller.setVisibility(View.VISIBLE);

            binding.imgFragSearchResultsItemFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView favoriteView = binding.imgFragSearchResultsItemFavorite;

                    if(!item.isFavorite()) {
                        favoriteView.setImageResource(R.drawable.ic_favorite_24);
                        item.setFavorite(true);
                    }
                    else
                    {
                        favoriteView.setImageResource(R.drawable.ic_favorite_border_24);
                        item.setFavorite(false);
                    }
                }
            });

            binding.cvFragSearchResultsItem.setOnClickListener(view -> {
                listener.onItemSelected(this.item);
            });
        }

    }// End ViewHolder Class

}// End Class
