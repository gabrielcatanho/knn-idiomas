package com.example.knnidiomas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "knn_idiomas.db"
        private const val TABLE_ALUNOS = "alunos"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_ALUNOS (id INTEGER PRIMARY KEY, nome TEXT, nota REAL, frequencia INTEGER)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ALUNOS")
        onCreate(db)
    }

    fun addAluno(aluno: Aluno) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("nome", aluno.nome)
        values.put("nota", aluno.nota)
        values.put("frequencia", aluno.frequencia)

        db.insert(TABLE_ALUNOS, null, values)
        db.close()
    }

    fun getAllAlunos(): List<Aluno> {
        val alunos = mutableListOf<Aluno>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ALUNOS", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nome = cursor.getString(1)
                val nota = cursor.getDouble(2)
                val frequencia = cursor.getInt(3)
                alunos.add(Aluno(id, nome, nota, frequencia))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return alunos
    }
}
