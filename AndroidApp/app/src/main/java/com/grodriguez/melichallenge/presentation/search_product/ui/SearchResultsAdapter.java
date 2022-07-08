package com.grodriguez.melichallenge.presentation.search_product.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grodriguez.melichallenge.R;
import com.grodriguez.melichallenge.databinding.FragmentSearchResultsItemBinding;
import com.grodriguez.melisearchcore.model.dtos.SearchItemDTO;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchResultItemViewHolder> {

    List<SearchItemDTO> resultItems = new ArrayList<>();

    @NonNull
    @Override
    public SearchResultItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentSearchResultsItemBinding itemBinding = FragmentSearchResultsItemBinding.inflate(inflater, parent, false);

        return new SearchResultItemViewHolder(itemBinding.getRoot(), parent.getContext(), itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultItemViewHolder holder, int position) {
        holder.bind(resultItems.get(position));
    }

    @Override
    public int getItemCount() {
        return resultItems.size();
    }

    public void updateItems(List<SearchItemDTO> newItems) {
        resultItems.clear();
        resultItems.addAll(newItems);
        notifyDataSetChanged();
    }

    static class SearchResultItemViewHolder extends RecyclerView.ViewHolder {

        Context context;
        FragmentSearchResultsItemBinding binding;
        boolean isFavorite = false;

        public SearchResultItemViewHolder(@NonNull View itemView,
                                          Context context,
                                          FragmentSearchResultsItemBinding binding) {
            super(itemView);
            this.context = context;
            this.binding = binding;
        }

        public void bind(SearchItemDTO item) {

            binding.txtFragSearchResultsItemTitle.setText(item.getTitle());

            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setGroupingUsed(true);
            String price = formatter.format(item.getPrice());

            binding.txtFragSearchResultsItemPrice.setText(String.format("%s %s", item.getCurrencyId(), price));

            ImageView imgItemThumbnail  = binding.imgFragSearchResultsItemImage;
            Picasso.get().load(item.getThumbnailUrl()).into(imgItemThumbnail);

            if (item.getShipping().isFreeShipping())
                binding.txtFragSearchResultsItemFreeShipping.setVisibility(View.VISIBLE);

            binding.imgFragSearchResultsItemFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView favoriteView = binding.imgFragSearchResultsItemFavorite;

                    if(!isFavorite) {
                        favoriteView.setImageResource(R.drawable.ic_favorite_24);
                        isFavorite = true;
                    }
                    else
                    {
                        favoriteView.setImageResource(R.drawable.ic_favorite_border_24);
                        isFavorite = false;
                    }
                }
            });
        }

    }// End ViewHolder Class

}// End Class
