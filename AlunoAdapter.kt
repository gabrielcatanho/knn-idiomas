package com.example.knnidiomas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlunoAdapter(private val alunos: List<Aluno>) : RecyclerView.Adapter<AlunoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.nomeTextView)
        val notaTextView: TextView = itemView.findViewById(R.id.notaTextView)
        val frequenciaTextView: TextView = itemView.findViewById(R.id.frequenciaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_aluno, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aluno = alunos[position]
        holder.nomeTextView.text = aluno.nome
        holder.notaTextView.text = "Nota: ${aluno.nota}"
        holder.frequenciaTextView.text = "Frequência: ${aluno.frequencia}%"
    }

    override fun getItemCount() = alunos.size
}
