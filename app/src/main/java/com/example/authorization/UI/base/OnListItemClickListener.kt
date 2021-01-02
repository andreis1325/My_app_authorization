package com.delivery.ui.base.baseadapter

import android.view.View

interface OnListItemClickListener<in T> {
    fun onItemClick(item: T, view: View)
}