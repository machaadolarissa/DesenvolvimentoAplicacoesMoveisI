package com.machado.trabalho2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.machado.trabalho2.databinding.HomeScreenBinding

class HomeScreen : Fragment() {

    private var _binding: HomeScreenBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = HomeScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSendMessage.setOnClickListener {
            val message = binding.editTextMessage.text.toString()

            val action = HomeScreenDirections.actionHomeScreenToResponseScreen()
            val bundle = Bundle().apply {
                putString("userMessage", message)
            }
            if (message.isNotEmpty()){
                CommandHistoryScreen.addCommand(message)
            }
            findNavController().navigate(action.actionId, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}