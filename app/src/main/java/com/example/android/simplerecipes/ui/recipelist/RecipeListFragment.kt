package com.example.android.simplerecipes.ui.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.simplerecipes.R
import com.example.android.simplerecipes.databinding.FragmentRecipeListBinding
import com.example.android.simplerecipes.ui.adapter.RecipeAdapter
import com.example.android.simplerecipes.util.disableUserInteraction
import com.example.android.simplerecipes.util.reEnableUserInteraction
import com.example.android.simplerecipes.util.showSnackbar

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
            val directions =
                RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(recipe)
            findNavController().navigate(directions)
        }
        binding.recipesRecyclerView.adapter = recipeAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservations()
    }

    private fun setupViewModelObservations() {
        viewModel.successMessage.observe(
            viewLifecycleOwner,
            { showSnackbar(binding.root, it, true) })
        viewModel.errorMessage.observe(
            viewLifecycleOwner,
            { showSnackbar(binding.root, it, false) })
        viewModel.isContentLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.shimmerLayout.shimmerFrameLayout.showShimmer(isLoading)

            if (isLoading) {
                disableUserInteraction()
            } else {
                reEnableUserInteraction()
            }
        }

        viewModel.recipes.observe(viewLifecycleOwner, {
            it?.apply {
                recipeAdapter?.dataList = it
                binding.recipesRecyclerView.smoothScrollToPosition(0)
            }
        })
    }
}