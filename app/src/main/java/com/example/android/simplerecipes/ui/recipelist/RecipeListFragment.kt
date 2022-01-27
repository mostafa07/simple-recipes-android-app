package com.example.android.simplerecipes.ui.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.simplerecipes.R
import com.example.android.simplerecipes.databinding.FragmentRecipeListBinding
import com.example.android.simplerecipes.ui.adapter.RecipeAdapter

class RecipeListFragment : Fragment() {

    private lateinit var binding: FragmentRecipeListBinding

    private val viewModel: RecipeListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "ViewModel can only be accessed after OnViewCreated() ic called"
        }
        ViewModelProvider(this, RecipeListViewModel.Factory(activity.application))
            .get(RecipeListViewModel::class.java)
    }

    private var recipeAdapter: RecipeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_recipe_list,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        recipeAdapter = RecipeAdapter { recipe, _ ->
            // TODO remove toast and navigate to detail screen
            Toast.makeText(requireContext(), "Recipe Clicked: " + recipe.name, Toast.LENGTH_SHORT)
                .show()
        }
        binding.recipesRecyclerView.adapter = recipeAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recipes.observe(viewLifecycleOwner, {
            it?.apply {
                recipeAdapter?.dataList = it
            }
        })
    }
}