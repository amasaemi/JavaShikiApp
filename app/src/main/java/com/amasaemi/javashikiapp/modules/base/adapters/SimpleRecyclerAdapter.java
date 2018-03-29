package com.amasaemi.javashikiapp.modules.base.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amasaemi.javashikiapp.BR;
import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.RecyclerAdapter;
import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.ViewModel;
import com.amasaemi.javashikiapp.utils.ErrorReport;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 31.01.2018.
 */

public final class SimpleRecyclerAdapter<T extends ViewModel> extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolderListItem> implements RecyclerAdapter<T> {
    // стили списка - длинные и короткие карточки
    public static final int STYLE_LONG = 100;
    public static final int STYLE_SHORT = 101;

    // id шаблона элементов адаптера
    private int mHolderLayout;
    // список элементов адаптера
    private List<T> mList = new LinkedList<T>();

    public SimpleRecyclerAdapter(int holderLayout) {
        mHolderLayout = holderLayout;
    }

    public SimpleRecyclerAdapter(int holderLayout, List<T> list) {
        this(holderLayout);
        mList.addAll(list);
    }

    @Override
    public ViewHolderListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderListItem(LayoutInflater.from(parent.getContext())
                .inflate(mHolderLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleRecyclerAdapter.ViewHolderListItem holder, int position) {
        try {
            // заполняем модель карточки данными
            holder.getBinding().setVariable(BR.model, mList.get(position));
        } catch (Exception ex) {
            Log.e(this.getClass().getSimpleName(), ex.getMessage());
            ErrorReport.sendReport(ex);
        }
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public List<T> getItems() {
        return mList;
    }

    @Override
    public void setItems(List<T> items) {
        mList.clear();
        mList.addAll(items);

        this.notifyDataSetChanged();
    }

    @Override
    public void addItems(List<T> items) {
        mList.addAll(items);

        this.notifyItemRangeInserted(getItemCount() - items.size(), getItemCount());
    }

    @Override
    public void clearItems() {
        mList.clear();

        this.notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty() {
        return mList.isEmpty();
    }

    class ViewHolderListItem extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        private ViewHolderListItem(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
