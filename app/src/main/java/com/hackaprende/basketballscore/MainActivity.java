package com.hackaprende.basketballscore;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hackaprende.basketballscore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int localScore = 0;
    private int visitorScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupButtons();
    }

    private void setupButtons() {
        binding.localMinusButton.setOnClickListener(v -> {
            if (localScore > 0) {
                localScore--;
                binding.localScoreText.setText(String.valueOf(localScore));
            }
        });

        binding.localPlusButton.setOnClickListener(v -> {
            addPointsToScore(1, true);
        });

        binding.localTwoPointsButton.setOnClickListener(v -> {
            addPointsToScore(2, true);
        });

        binding.visitorMinusButton.setOnClickListener(v -> {
            if (visitorScore > 0) {
                visitorScore--;
                binding.visitorScoreText.setText(String.valueOf(visitorScore));
            }
        });

        binding.visitorPlusButton.setOnClickListener(v -> {
            addPointsToScore(1, false);
        });

        binding.visitorTwoPointsButton.setOnClickListener(v -> {
            addPointsToScore(2, false);
        });

        binding.restartButton.setOnClickListener(v -> {
            resetScores();
        });

        binding.resultsButton.setOnClickListener(v -> {
            startScoreActivity();
        });
    }

    private void resetScores() {
        localScore = 0;
        visitorScore = 0;
        binding.visitorScoreText.setText(String.valueOf(localScore));
        binding.localScoreText.setText(String.valueOf(visitorScore));
    }

    private void addPointsToScore(int points, Boolean isLocal) {
        if (isLocal) {
            localScore += points;
            binding.localScoreText.setText(String.valueOf(localScore));
        } else {
            visitorScore += points;
            binding.visitorScoreText.setText(String.valueOf(visitorScore));
        }
    }

    private void startScoreActivity() {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra(ScoreActivity.LOCAL_SCORE_KEY, localScore);
        intent.putExtra(ScoreActivity.VISITOR_SCORE_KEY, visitorScore);
        startActivity(intent);
    }
}