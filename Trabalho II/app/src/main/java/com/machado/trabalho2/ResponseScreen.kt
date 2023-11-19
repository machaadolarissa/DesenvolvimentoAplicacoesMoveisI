package com.machado.trabalho2

import MarcianoCustomActions
import MarcianoPremium
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.machado.trabalho2.databinding.ResponseScreenBinding


class ResponseScreen : Fragment() {

    private var _binding: ResponseScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ResponseScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userMessage = arguments?.getString("userMessage")

        val robotResponse = getRobotResponse(userMessage!!)

        binding.textViewResponse.text = robotResponse

        binding.buttonVoltar.setOnClickListener {
            findNavController().navigate(R.id.action_ResponseScreen_to_homeScreen)
        }

        binding.buttonViewHistory.setOnClickListener {
            findNavController().navigate(R.id.action_ResponseScreen_to_CommandHistoryScreen)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRobotResponse(message: String): String {
        val acoesPersonalizadas = mapOf(
            "agir contar" to object : MarcianoCustomActions {
                override fun execute(): String {
                    val contador = StringBuilder("Conto até 10: ")
                    for (i in 1..10) {
                        contador.append(i)
                        if (i < 10) {
                            contador.append(", ")
                        }
                    }
                    return contador.toString()
                }
            },
            "agir alfabeto" to object : MarcianoCustomActions {
                override fun execute(): String {
                    return "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"
                }
            },
            "agir fato" to object : MarcianoCustomActions {
                override fun execute(): String {
                    return "Aqui está um fato interessante do Kotlin: Kotlin é uma linguagem de programação oficialmente " +
                            "suportada para o desenvolvimento Android."
                }
            }
        )

        val robopremium = MarcianoPremium(acoesPersonalizadas)
        return robopremium.reply(message)
    }
}
