package com.example.android.simplerecipes.ui.adapter

import com.example.android.simplerecipes.BR
import com.example.android.simplerecipes.R
import com.example.android.simplerecipes.data.model.domain.Recipe
import com.example.android.simplerecipes.databinding.ItemRecipeBinding
import com.example.android.simplerecipes.ui.adapter.base.BaseRecyclerViewAdapter

class RecipeAdapter(onItemClickListener: OnItemClickListener<Recipe>) :
    BaseRecyclerViewAdapter<Recipe, ItemRecipeBinding>(onItemClickListener) {

    override fun getItemLayoutId(): Int {
        return R.layout.item_recipe
    }

    override fun getViewBindingVariableId(): Int {
        return BR.recipe
    }

    override fun onViewHolderBinding(
        viewDataBinding: ItemRecipeBinding,
        item: Recipe?,
        position: Int
    ) {
    }
}