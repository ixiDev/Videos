/*
 * Created on 2019/3/23 3:12 PM.
 */

package com.liuzhenlin.videos.view.adapter;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.util.Synthetic;

import java.util.List;

public class HeaderAndFooterWrapper<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    @Synthetic final SparseArray<View> mHeaderViews = new SparseArray<>();
    @Synthetic final SparseArray<View> mFootViews = new SparseArray<>();

    private final RecyclerView.Adapter<VH> mInnerAdapter;

    public HeaderAndFooterWrapper(@NonNull RecyclerView.Adapter<VH> adapter) {
        mInnerAdapter = adapter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int index = mHeaderViews.indexOfKey(viewType);
        if (index >= 0) {
            return new RecyclerView.ViewHolder(mHeaderViews.valueAt(index)) {
            };
        } else {
            index = mFootViews.indexOfKey(viewType);
            if (index >= 0) {
                return new RecyclerView.ViewHolder(mFootViews.valueAt(index)) {
                };
            }
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (isHeaderViewPos(position)) {
            return;
        }
        if (isFooterViewPos(position)) {
            return;
        }
        //noinspection unchecked
        mInnerAdapter.onBindViewHolder((VH) holder, position - getHeadersCount(), payloads);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    final int viewType = getItemViewType(position);
                    if (mHeaderViews.indexOfKey(viewType) >= 0) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (mFootViews.indexOfKey(viewType) >= 0) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (spanSizeLookup != null) {
                        return spanSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        //noinspection unchecked
        mInnerAdapter.onViewAttachedToWindow((VH) holder);
        final int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
            }
        }
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    public void addHeaderView(@NonNull View view) {
        mHeaderViews.put(getHeadersCount() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFootView(@NonNull View view) {
        mFootViews.put(getFootersCount() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }
}