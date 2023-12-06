package algonquin.cst2335.cst2335graphicalinterfaceprogramming;

import static algonquin.cst2335.cst2335graphicalinterfaceprogramming.R.id.Sunrise_Sunset_Page;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Find buttons by ID
        Button buttonPage1 = findViewById(Sunrise_Sunset_Page);
        Button buttonPage2 = findViewById(R.id.Recipe_Search_Page);
        Button buttonPage3 = findViewById(R.id.Dictionary_API_Page);
        Button buttonPage4 = findViewById(R.id.Deezer_Song_Search_API_Page);

        // Set click listeners for each button
        buttonPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, WelcomeActivity.class));
            }
        });

        buttonPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, WelcomeActivity.class));
            }
        });

        buttonPage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, WelcomeActivity.class));
            }
        });

        buttonPage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, WelcomeActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                Intent backToMain = new Intent(this, WelcomeActivity.class);
                startActivity(backToMain);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}