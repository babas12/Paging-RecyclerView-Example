package com.ertohru.pagingrecyclerview;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Pagination
 * Created by Suleiman19 on 10/15/16.
 * Copyright (c) 2016. Suleiman Ali Shakir. All rights reserved.
 */
public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;

    /**
     * Supporting only LinearLayoutManager for now.
     *
     * @param layoutManager
     */
    protected PaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }

    }

    protected abstract void loadMoreItems();

    public abstract int getTotalPageCount();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
    
    
    object DateUtils {
    const val DATE_FORMAT_YYYY = "yyyy-MM-dd"
    const val DATE_FORMAT_FULL = "EEEE dd MMM yyyy"
    const val DATE_FORMAT_HISTORY = "dd MMMM yyyy"

    fun formatStringToDate(date: String, format: String) : Date{
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.parse(date)
    }

    fun formatDateLong(date: Long, destinationFormat: String) : String{
        val sdfDest = SimpleDateFormat(destinationFormat, Locale.getDefault())
        return sdfDest.format(Date(date))
    }

    fun formatDateLongLocale(date: Long, destinationFormat: String) : String{
        val sdfDest = SimpleDateFormat(destinationFormat, Locale("in","ID"))
        return sdfDest.format(Date(date))
    }
    fun formatDateLocale(date: Date, destinationFormat: String) : String{
        val sdfDest = SimpleDateFormat(destinationFormat, Locale("in","ID"))
        return sdfDest.format(date)
    }

}

}
