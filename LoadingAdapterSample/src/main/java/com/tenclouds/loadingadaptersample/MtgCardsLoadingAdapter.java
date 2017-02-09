package com.tenclouds.loadingadaptersample;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.tenclouds.loadingadapter.AbstractItemsLoader;
import com.tenclouds.loadingadapter.AbstractLoadingAdapter;
import com.tenclouds.loadingadaptersample.databinding.ViewCardBinding;

import io.magicthegathering.javasdk.resource.Card;


public class MtgCardsLoadingAdapter extends AbstractLoadingAdapter<Card> {
    private ItemSelectedListener itemSelectedListener;

    public MtgCardsLoadingAdapter(Context context, AbstractItemsLoader<Card> itemsLoader, ItemSelectedListener itemSelectedListener) {
        super(context, R.layout.view_empty, itemsLoader);
        this.itemSelectedListener = itemSelectedListener;
    }

    @Override
    public long getYourItemId(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getYourItemViewHolder(ViewGroup parent, int viewType) {
        ViewCardBinding binding = DataBindingUtil.inflate(getInflater(), R.layout.view_card, parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void bindYourViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Card item = getItem(position);
        CardViewHolder cardViewHolder = (CardViewHolder) holder;
        cardViewHolder.setCard(item);
        if (itemSelectedListener != null) {
            cardViewHolder.binding.getRoot().setOnClickListener((View view) -> itemSelectedListener.onItemSelected(item));
        }
    }

    @Override
    public int getYourItemViewType(int position) {
        return 0;
    }

    private class CardViewHolder extends RecyclerView.ViewHolder {
        private ViewCardBinding binding;

        public CardViewHolder(ViewCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setCard(Card card) {
            binding.setCard(card);
        }
    }

    public interface ItemSelectedListener {
        void onItemSelected(Card card);
    }
}
