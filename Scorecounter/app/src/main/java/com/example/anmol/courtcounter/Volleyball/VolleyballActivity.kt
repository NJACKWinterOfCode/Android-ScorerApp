package com.example.anmol.courtcounter.Volleyball

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import com.example.anmol.courtcounter.R
import kotlinx.android.synthetic.main.activity_volleyball.*

class VolleyballActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volleyball)
        supportActionBar?.title = "Volleyball"

        resetButton.setOnClickListener { View->
            reset()
        }

        switchButton.setOnClickListener { View->
            switchSides()
        }

        teamAname.setOnClickListener { View->
            val alertBuilder = android.app.AlertDialog.Builder(this)
            alertBuilder.setTitle("Edit Team Name")

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT
            alertBuilder.setView(input)

            alertBuilder.setPositiveButton("Ok") { dialogInterface, i -> teamAname.setText(input.text) }

            alertBuilder.setNegativeButton("cancel") { dialogInterface, i -> }

            alertBuilder.show()
        }

        teamA_ScoreTextView.setOnClickListener { View->
            var score = Integer.parseInt(teamA_ScoreTextView.text.toString()) + 1
            if(score == 25) {
                teamA_setScoreTextView.text = (Integer.parseInt(teamA_setScoreTextView.text.toString()) + 1).toString()
                score = 0
                teamB_ScoreTextView.text = score.toString()
                Toast.makeText(this, "Set Completed", Toast.LENGTH_SHORT).show()
                isWinner()
            }
            teamA_ScoreTextView.text = score.toString()
        }

        teamB_ScoreTextView.setOnClickListener { View->
            var score = Integer.parseInt(teamB_ScoreTextView.text.toString()) + 1
            if(score == 25) {
                teamB_setScoreTextView.text = (Integer.parseInt(teamB_setScoreTextView.text.toString()) + 1).toString()
                score = 0
                teamA_ScoreTextView.text = score.toString()
                Toast.makeText(this, "Set Completed", Toast.LENGTH_SHORT).show()
                isWinner()
            }
            teamB_ScoreTextView.text = score.toString()
        }
    }

    fun isWinner() {
        if(teamA_setScoreTextView.text.toString().equals("3")) {
            val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
            dialog.setTitle("Winner")
            dialog.setMessage("Team A WON")
            dialog.show()
            reset()
        } else if (teamB_setScoreTextView.text.toString().equals("3")) {
            val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
            dialog.setTitle("Winner")
            dialog.setMessage("Team B WON")
            dialog.show()
            reset()
        }
    }

    fun reset() {
        teamA_ScoreTextView.text = "0"
        teamB_ScoreTextView.text = "0"
        teamA_setScoreTextView.text = "0"
        teamB_setScoreTextView.text = "0"
        teamAname.text = "Team A"
        teamBname.text = "Team B"
    }

    fun switchSides() {
        val nameA = teamAname.text.toString()
        val colorA = teamAname.currentTextColor
        val scoreA = teamA_ScoreTextView.text.toString()
        val setA = teamA_setScoreTextView.text.toString()
        val nameB = teamBname.text.toString()
        val colorB = teamBname.currentTextColor
        val scoreB = teamB_ScoreTextView.text.toString()
        val setB = teamB_setScoreTextView.text.toString()

        teamBname.text = nameA
        teamBname.setTextColor(colorA)
        teamB_ScoreTextView.text = scoreA
        teamB_ScoreTextView.setTextColor(colorA)
        teamB_setScoreTextView.text = setA
        teamB_setScoreTextView.setTextColor(colorA)

        teamAname.text = nameB
        teamAname.setTextColor(colorB)
        teamA_ScoreTextView.text = scoreB
        teamA_ScoreTextView.setTextColor(colorB)
        teamA_setScoreTextView.text = setB
        teamA_setScoreTextView.setTextColor(colorB)
    }
}
