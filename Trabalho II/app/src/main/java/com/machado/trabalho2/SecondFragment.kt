package com.machado.trabalho2

import MarcianoAcaoPersonalizada
import MarcianoPremium
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.machado.trabalho2.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userMessage = arguments?.getString("userMessage")

        val robotResponse = getRobotResponse(userMessage!!)

        binding.textViewResponse.text = robotResponse

        binding.buttonVoltar.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRobotResponse(message: String): String {
        val acoesPersonalizadas = mapOf(
            "agir contar" to object : MarcianoAcaoPersonalizada {
                override fun executar(): String {
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
            "agir alfabeto" to object : MarcianoAcaoPersonalizada {
                override fun executar(): String {
                    return "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"
                }
            },
            "agir fato" to object : MarcianoAcaoPersonalizada {
                override fun executar(): String {
                    return "Aqui está um fato interessante do Kotlin: Kotlin é uma linguagem de programação oficialmente " +
                            "suportada para o desenvolvimento Android."
                }
            }
        )

        val robopremium = MarcianoPremium(acoesPersonalizadas)
        return robopremium.responda(message)
    }
}