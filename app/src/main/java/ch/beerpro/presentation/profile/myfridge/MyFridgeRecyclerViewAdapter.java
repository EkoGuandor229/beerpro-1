package ch.beerpro.presentation.profile.myfridge;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.GlideApp;
import ch.beerpro.R;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeItem;
import ch.beerpro.presentation.utils.EntityPairDiffItemCallback;

public class MyFridgeRecyclerViewAdapter extends ListAdapter<Pair<FridgeItem, Beer>, MyFridgeRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MyFridgeRecyclerViewAda";

    private static final DiffUtil.ItemCallback<Pair<FridgeItem, Beer>> DIFF_CALLBACK = new EntityPairDiffItemCallback<>();

    private final OnMyFridgeItemInteractionListener listener;

    public MyFridgeRecyclerViewAdapter(OnMyFridgeItemInteractionListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_my_fridge_listentry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Pair<FridgeItem, Beer> item = getItem(position);
        holder.bind(item.first, item.second, listener);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.manufacturer)
        TextView manufacturer;

        @BindView(R.id.category)
        TextView category;

        @BindView(R.id.photo)
        ImageView photo;

        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        @BindView(R.id.numRatings)
        TextView numRatings;

        @BindView(R.id.amountInFridge)
        EditText amountInFridge;

        @BindView(R.id.removeFromFridge)
        Button remove;

        @BindView(R.id.addAmountInFridge)
        Button increment;

        @BindView(R.id.removeAmountInFridge)
        Button decrement;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        void bind(FridgeItem fridgeBeer, Beer item, OnMyFridgeItemInteractionListener listener) {
            name.setText(item.getName());
            manufacturer.setText(item.getManufacturer());
            category.setText(item.getCategory());
            name.setText(item.getName());
            GlideApp.with(itemView).load(item.getPhoto()).apply(new RequestOptions().override(240, 240).centerInside())
                    .into(photo);
            ratingBar.setNumStars(5);
            ratingBar.setRating(item.getAvgRating());
            numRatings.setText(itemView.getResources().getString(R.string.fmt_num_ratings, item.getNumRatings()));
            itemView.setOnClickListener(v -> listener.onMoreClickedListener(photo, item));

            amountInFridge.setText(Integer.toString(fridgeBeer.getAmount()));
            amountInFridge.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if(!hasFocus){
                        listener.afterTextChanged(item, amountInFridge.getText());
                    }
                }
            });
            remove.setOnClickListener(v -> listener.onFridgeBeerDeleteClickedListener(item));
            increment.setOnClickListener(v -> listener.onIncrementClickedListener(item, itemView));
            decrement.setOnClickListener(v -> listener.onDecrementClickedListener(item, itemView));
        }

    }
}
