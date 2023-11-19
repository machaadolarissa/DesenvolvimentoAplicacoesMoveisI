package com.machado.trabalho2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommandAdapter(
    private val commands: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<CommandAdapter.CommandViewHolder>() {

    inner class CommandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commandText: TextView = itemView.findViewById(R.id.textViewCommand)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_command, parent, false)
        return CommandViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommandViewHolder, position: Int) {
        val command = commands[position]
        holder.commandText.text = command

        holder.itemView.setOnClickListener {
            onItemClick(command)
        }
    }

    override fun getItemCount(): Int {
        return commands.size
    }
}