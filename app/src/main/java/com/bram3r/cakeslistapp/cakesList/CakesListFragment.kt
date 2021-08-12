package com.bram3r.cakeslistapp.cakesList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bram3r.cakeslistapp.common.isOnline
import com.bram3r.cakeslistapp.databinding.CakesListFragmentBinding

class CakesListFragment : Fragment() {

    private var _binding: CakesListFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CakesListFragment()
    }

    private lateinit var viewModel: CakesListViewModel

    private lateinit var adapter: CakeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CakesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CakesListViewModel::class.java)

        binding.progressBar.visibility = View.VISIBLE
        binding.swipeTextView.visibility = View.GONE

        adapter = CakeAdapter(emptyList(), requireContext())

        binding.swipeRefreshLayout.setOnRefreshListener {
            if (isOnline(requireContext())) {
                viewModel.updateCakesList()
                binding.swipeTextView.visibility = View.GONE
            }
            else {
                Toast.makeText(requireContext(), "Error en la conexión", Toast.LENGTH_LONG).show()
                binding.swipeRefreshLayout.isRefreshing = false
                binding.swipeTextView.visibility = View.VISIBLE
            }
        }

        viewModel.cakeList.observe(viewLifecycleOwner, Observer {
            try {
                adapter.cakes = it
                binding.cakesRecyclerView.adapter = adapter
                binding.progressBar.visibility = View.GONE
                binding.swipeRefreshLayout.isRefreshing = false
            }catch (e: Error) {
                Log.e("TAG", "Error al actualizar cakes")
            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                viewModel.status.value = null
                binding.swipeTextView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })

        if (isOnline(requireContext()))
            viewModel.updateCakesList()
        else {
            Toast.makeText(requireContext(), "Error en la conexión", Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.cakesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.cakesRecyclerView.adapter = adapter
    }
}