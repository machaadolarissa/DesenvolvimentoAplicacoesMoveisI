package com.machado.trabalho2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.machado.trabalho2.databinding.FragmentCommandHistoryBinding

class CommandHistoryFragment : Fragment() {

    private var _binding: FragmentCommandHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var commandAdapter: CommandAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommandHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerViewCommands
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        commandAdapter = CommandAdapter(getCommandList()) { command ->
            sendMessageToRobot(command)
        }
        recyclerView.adapter = commandAdapter

        binding.buttonVoltar.setOnClickListener {
            findNavController().navigate(R.id.action_History_to_FirstFragment)
        }
    }

    private fun sendMessageToRobot(message: String) {
        val action = CommandHistoryFragmentDirections.actionHistoryToFirstFragment()
        val bundle = Bundle().apply {
            putString("userMessage", message)
        }
        if (message.isNotEmpty()){
            addCommand(message)
        }
        findNavController().navigate(action.actionId, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val commandList = mutableListOf<String>()

        fun addCommand(command: String) {
            commandList.add(command)
        }

        fun getCommandList(): List<String> {
            return commandList.toList()
        }
    }

}
