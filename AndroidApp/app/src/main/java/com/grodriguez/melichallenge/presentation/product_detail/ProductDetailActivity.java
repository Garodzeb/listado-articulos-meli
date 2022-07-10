package com.grodriguez.melichallenge.presentation.product_detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.grodriguez.melichallenge.R;
import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.databinding.ActivityProductDetailBinding;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.framework.utils.AppConstants;
import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melichallenge.presentation.ViewModelsFactory;
import com.grodriguez.melichallenge.presentation.ui.UIState;
import com.grodriguez.melisearchcore.model.domain.items.ItemDetail;
import com.grodriguez.melisearchcore.model.domain.items.ItemPicture;
import com.grodriguez.melisearchcore.model.domain.items.ItemRating;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

public class ProductDetailActivity extends AppCompatActivity {

    ProductDetailViewModel productViewModel;
    ActivityProductDetailBinding binding;

    // region Lifecycle Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            Context context = getApplicationContext();
            AppDependenciesContainer appDependencies = AppDependenciesContainer.getInstance(context, AppRoomDatabase.getInstance(context));
            ViewModelsFactory factory = new ViewModelsFactory(appDependencies.getItemsRepository(), appDependencies.getSiteRepository());

            productViewModel = new ViewModelProvider(this, factory).get(ProductDetailViewModel.class);

            initializeUI();
            initializeObservers();
        }
        catch (Exception ex)
        {
            showErrorScreen(ex);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            String itemId = AppUtils.getCustomStringPreference(getApplicationContext(), AppConstants.CURRENT_ITEM_ID_SHARED_PREFERENCE_KEY);
            productViewModel.loadItemDetails(itemId);
        }
        catch (Exception ex) {
            showErrorScreen(ex);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            if (isFinishing()) {
                AppUtils.removeCustomPreference(getApplicationContext(), AppConstants.CURRENT_ITEM_ID_SHARED_PREFERENCE_KEY);
                productViewModel.dispose();
            }
        }
        catch (Exception ex) {
            AppUtils.logError(ex);
        }
    }

    // endregion

    // region Initialization Methods

    private void initializeUI() {
        binding.btnActivityProductDetailPreviousImage.setOnClickListener(view -> {
            productViewModel.getPreviousPicture();
        });

        binding.btnActivityProductDetailNextImage.setOnClickListener(view -> {
            productViewModel.getNextPicture();
        });

        binding.btnActivityProductDetailAddToCart.setOnClickListener(view -> {
            ItemDetail itemDetail = productViewModel.getCurrentItem().getValue();

            if (itemDetail != null) {
                String addToCartMsg = String.format("El artículo %s fue agregado al carrito", itemDetail.getTitle());
                Toast.makeText(getApplicationContext(), addToCartMsg, Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnActivityProductDetailBuy.setOnClickListener(view -> {
            ItemDetail itemDetail = productViewModel.getCurrentItem().getValue();

            if (itemDetail != null) {
                String buyMsg = String.format("Se realizó la compra del artículo %s", itemDetail.getTitle());
                Toast.makeText(getApplicationContext(), buyMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeObservers() {
        productViewModel.getUIState().observe(this, this::updateUIState);
        productViewModel.getCurrentItem().observe(this, this::showItem);
        productViewModel.getcurrentPicture().observe(this, this::showItemPicture);
    }

    // endregion

    // region Private Methods

    private void updateUIState(UIState status) {
        binding.fragActivityProductDetailErrorMessage.getRoot().setVisibility(View.GONE);

        switch (status.getCurrentStatus()) {
            case UI_IDLE: {
                binding.svActivityProductDetailContentView.setVisibility(View.VISIBLE);
                binding.pbActivityProductDetailLoading.setVisibility(View.GONE);
                break;
            }
            case UI_ON_BACKGROUND_WORK: {
                binding.svActivityProductDetailContentView.setVisibility(View.VISIBLE);
                binding.pbActivityProductDetailLoading.setVisibility(View.VISIBLE);
                break;
            }
            case UI_ON_ERROR: {
                binding.pbActivityProductDetailLoading.setVisibility(View.GONE);

                // Valida si el mensaje de error ocurrió debido a una excepción no controlada
                if (status.getError() != null) {
                    showErrorScreen(status.getError());
                }
                // Valida si el mensaje de error es un mensaje controlado
                else if (!status.getMessage().isEmpty()) {
                    // En el caso de que sea un mensaje controlado muestra un toast en pantalla
                    Toast.makeText(getApplicationContext(), status.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void showItem(ItemDetail item) {
        try {
            if (item != null) {
                // Titulo del producto
                binding.lblActivityDetailProductTitle.setText(item.getTitle());

                NumberFormat formatter = NumberFormat.getNumberInstance();
                formatter.setGroupingUsed(true);

                // Precio del producto
                String price = formatter.format(item.getPrice());
                binding.lblActivityDetailPrice.setText(String.format("%s %s", item.getCurrency().getSymbol(), price));

                // Reseñas
                updateItemRating(item.getItemRating());

                // Garantía
                binding.lblActivityProductDetailWarranty.setText(item.getWarranty());

                // Condición
                String itemCondition = getItemConditionMsg(item.getCondition());
                binding.lblActivityProductDetailCondition.setText(itemCondition);

                // Valida si el artículo tiene cantidad vendida
                if (item.getSoldQty() > 0) {
                    String soldQty = formatter.format(item.getSoldQty());
                    binding.lblActivityProductDetailCondition.setText(String.format("%s | %s vendidos", itemCondition, soldQty));
                }

                // Stock
                if (item.getAvailableQty() > 0) {
                    // TODO: verificar de donde se sacan las unidades de stock
                    String stockQty = formatter.format(item.getAvailableQty());
                    binding.lblActivityProductDetailAvailableStock.setText(String.format("%s unidades", stockQty));
                } else
                    binding.lblActivityProductDetailAvailableStock.setText(getString(R.string.no_stock_message));

                // Valida si debe mostrar el control de imágenes
                if (item.getPictures() == null) {
                    binding.imgActivityProductDetailProductImage.setVisibility(View.GONE);
                    binding.btnActivityProductDetailNextImage.setVisibility(View.GONE);
                    binding.btnActivityProductDetailPreviousImage.setVisibility(View.GONE);

                } else if (item.getPictures().size() == 0) {
                    binding.imgActivityProductDetailProductImage.setVisibility(View.GONE);
                    binding.btnActivityProductDetailNextImage.setVisibility(View.GONE);
                    binding.btnActivityProductDetailPreviousImage.setVisibility(View.GONE);

                } else if (item.getPictures().size() == 1) {
                    // Si el artículo solo tiene una imágen no muestra los bótones de cambiar imágen
                    binding.btnActivityProductDetailNextImage.setVisibility(View.GONE);
                    binding.btnActivityProductDetailPreviousImage.setVisibility(View.GONE);
                } else {
                    binding.imgActivityProductDetailProductImage.setVisibility(View.VISIBLE);
                    binding.btnActivityProductDetailNextImage.setVisibility(View.VISIBLE);
                    binding.btnActivityProductDetailPreviousImage.setVisibility(View.VISIBLE);
                }
            }
        }
        catch (Exception ex) {
            showErrorScreen(ex);
        }
    }

    private String getItemConditionMsg(String conditionId) {
        String conditionMsg = "";

        if(conditionId.equalsIgnoreCase(AppConstants.ITEM_CONDITION_USED))
            conditionMsg = "Usado";
        else
            conditionMsg = "Nuevo";

        return conditionMsg;
    }

    private void updateItemRating(ItemRating rating) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setGroupingUsed(true);

        if (rating.getPaging().getTotal() > 0) {
            String reviewQty = formatter.format(rating.getPaging().getTotal());
            binding.lblActivityProductDetailReviewQty.setText(String.format("(%s)", reviewQty));

            updateRatingStars(rating.getRatingAvg());
        } else {
            binding.imgActivityProductDetailStar1.setVisibility(View.GONE);
            binding.imgActivityProductDetailStar2.setVisibility(View.GONE);
            binding.imgActivityProductDetailStar3.setVisibility(View.GONE);
            binding.imgActivityProductDetailStar4.setVisibility(View.GONE);
            binding.imgActivityProductDetailStar5.setVisibility(View.GONE);
            binding.lblActivityProductDetailReviewQty.setVisibility(View.GONE);
        }
    }

    // Modifica la cantidad de estrellas mostradas a partir del promedio de calificaciones que
    // tiene el artículo
    private void updateRatingStars(float avg) {
        if (avg < 0.5) {
            binding.imgActivityProductDetailStar1.setVisibility(View.GONE);
            binding.imgActivityProductDetailStar2.setVisibility(View.GONE);
            binding.imgActivityProductDetailStar3.setVisibility(View.GONE);
            binding.imgActivityProductDetailStar4.setVisibility(View.GONE);
            binding.imgActivityProductDetailStar5.setVisibility(View.GONE);

        } else if (avg >= 0.5 && avg < 1) {
            binding.imgActivityProductDetailStar1.setImageResource(R.drawable.ic_rating_half_star_24);
            binding.imgActivityProductDetailStar2.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar3.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar4.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar5.setImageResource(R.drawable.ic_rating_empty_star_24);

        } else if (avg < 1.5) {
            binding.imgActivityProductDetailStar1.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar2.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar3.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar4.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar5.setImageResource(R.drawable.ic_rating_empty_star_24);

        } else if (avg >= 1.5 && avg < 2) {
            binding.imgActivityProductDetailStar1.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar2.setImageResource(R.drawable.ic_rating_half_star_24);
            binding.imgActivityProductDetailStar3.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar4.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar5.setImageResource(R.drawable.ic_rating_empty_star_24);

        } else if (avg < 2.5) {
            binding.imgActivityProductDetailStar1.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar2.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar3.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar4.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar5.setImageResource(R.drawable.ic_rating_empty_star_24);

        } else if (avg >= 2.5 && avg < 3) {
            binding.imgActivityProductDetailStar1.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar2.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar3.setImageResource(R.drawable.ic_rating_half_star_24);
            binding.imgActivityProductDetailStar4.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar5.setImageResource(R.drawable.ic_rating_empty_star_24);

        } else if (avg < 3.5) {
            binding.imgActivityProductDetailStar1.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar2.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar3.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar4.setImageResource(R.drawable.ic_rating_empty_star_24);
            binding.imgActivityProductDetailStar5.setImageResource(R.drawable.ic_rating_empty_star_24);

        } else if (avg >= 3.5 && avg < 4) {
            binding.imgActivityProductDetailStar1.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar2.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar3.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar4.setImageResource(R.drawable.ic_rating_half_star_24);
            binding.imgActivityProductDetailStar5.setImageResource(R.drawable.ic_rating_empty_star_24);

        } else if (avg < 4.5) {
            binding.imgActivityProductDetailStar1.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar2.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar3.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar4.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar5.setImageResource(R.drawable.ic_rating_empty_star_24);

        } else if (avg >= 4.5 && avg < 5) {
            binding.imgActivityProductDetailStar1.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar2.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar3.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar4.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar5.setImageResource(R.drawable.ic_rating_half_star_24);

        } else {
            binding.imgActivityProductDetailStar1.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar2.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar3.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar4.setImageResource(R.drawable.ic_rating_full_star_24);
            binding.imgActivityProductDetailStar5.setImageResource(R.drawable.ic_rating_full_star_24);
        }
    }

    private void showItemPicture(ItemPicture picture) {
        try {
            if (!picture.getUrl().isEmpty()) {
                Picasso.get().load(picture.getUrl()).into(binding.imgActivityProductDetailProductImage);
            }
        }
        catch (Exception ex) {
            showErrorScreen(ex);
        }
    }

    // Muestara una pantalla de error y logea el error en la consola
    private void showErrorScreen(Throwable error) {
        AppUtils.logError(error);
        binding.fragActivityProductDetailErrorMessage.getRoot().setVisibility(View.VISIBLE);
        binding.svActivityProductDetailContentView.setVisibility(View.GONE);
    }

    // endregion
}// End Class