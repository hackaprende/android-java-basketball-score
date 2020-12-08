package com.hackaprende.basketballscore;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.hackaprende.basketballscore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setupButtons();
    }

    private void setupButtons() {
        binding.localMinusButton.setOnClickListener(v -> {
            viewModel.decreaseLocal();
            binding.localScoreText.setText(String.valueOf(viewModel.getLocalScore()));
        });

        binding.localPlusButton.setOnClickListener(v -> {
            addPointsToScore(1, true);
        });

        binding.localTwoPointsButton.setOnClickListener(v -> {
            addPointsToScore(2, true);
        });

        binding.visitorMinusButton.setOnClickListener(v -> {
            viewModel.decreaseVisitor();
            binding.visitorScoreText.setText(String.valueOf(viewModel.getVisitorScore()));
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
        viewModel.resetScores();
        binding.visitorScoreText.setText(String.valueOf(viewModel.getLocalScore()));
        binding.localScoreText.setText(String.valueOf(viewModel.getVisitorScore()));
    }

    private void addPointsToScore(int points, Boolean isLocal) {
        viewModel.addPointsToScore(points, isLocal);
        if (isLocal) {
            binding.localScoreText.setText(String.valueOf(viewModel.getLocalScore()));
        } else {
            binding.visitorScoreText.setText(String.valueOf(viewModel.getVisitorScore()));
        }
    }

    private void startScoreActivity() {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra(ScoreActivity.LOCAL_SCORE_KEY, viewModel.getLocalScore());
        intent.putExtra(ScoreActivity.VISITOR_SCORE_KEY, viewModel.getVisitorScore());
        startActivity(intent);
    }
}