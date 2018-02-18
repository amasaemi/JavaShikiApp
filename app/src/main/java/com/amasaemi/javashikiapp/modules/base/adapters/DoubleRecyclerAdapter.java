package com.amasaemi.javashikiapp.modules.base.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amasaemi.javashikiapp.BR;
import com.amasaemi.javashikiapp.modules.base.adapters.DoubleRecyclerAdapter.ViewHolderListItem;
import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.RecyclerAdapter;
import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.ViewModel;
import com.amasaemi.javashikiapp.utils.ErrorReport;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 31.01.2018.
 */

public final class DoubleRecyclerAdapter extends RecyclerView.Adapter<ViewHolderListItem> implements RecyclerAdapter<ViewModel> {
    // тип элемента - заголовок или обычный элемент
    public static final int HEADER = 102;
    public static final int ITEM = 103;

    // id шаблона заголовочного элемента адаптера
    private int mHeaderLayout;
    // id шаблона обычного элемента адаптера
    private int mItemLayout;
    // список элементов адаптера
    private List<ViewModel> mList = new LinkedList<ViewModel>();

    public DoubleRecyclerAdapter(int headerLayout, int itemLayout) {
        mHeaderLayout = headerLayout;
        mItemLayout = itemLayout;
    }

    public DoubleRecyclerAdapter(int headerLayout, int itemLayout, List<ViewModel> list) {
        this(headerLayout, itemLayout);
        mList.addAll(list);
    }

    @Override
    public ViewHolderListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER: return new ViewHolderListItem(LayoutInflater.from(parent.getContext())
                    .inflate(mHeaderLayout, parent, false));

            case ITEM: return new ViewHolderListItem(LayoutInflater.from(parent.getContext())
                    .inflate(mItemLayout, parent, false));

            default: throw new NullPointerException(String.format("%d id is no viewType", viewType));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolderListItem holder, int position) {
        try {
            // заполняем модель карточки данными
            holder.getBinding().setVariable(BR.model, mList.get(position));
        } catch (Exception ex) {
            Log.e(this.getClass().getSimpleName(), ex.getMessage());
            ErrorReport.sendReport(String.format("%s %s", this.getClass().getSimpleName(), ex.getMessage()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (mList.get(position) instanceof HeaderModel) ? HEADER : ITEM;
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
    public List<ViewModel> getItems() {
        return mList;
    }

    @Override
    public void setItems(List<ViewModel> items) {
        mList.clear();
        mList.addAll(items);

        this.notifyDataSetChanged();
    }

    @Override
    public void addItems(List<ViewModel> items) {
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

    final class ViewHolderListItem extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        private ViewHolderListItem(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public interface HeaderModel extends ViewModel { }

    public interface ItemModel extends ViewModel { }
}
