package br.com.etecia.localizausuarioapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LocalizacaoActivity extends AppCompatActivity {
    private Button m_botao;
    private EditText m_campo_latitude, m_campo_longitude;
    private double m_latitude, m_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao);

        m_botao = findViewById(R.id.botao_local);
        m_campo_latitude = findViewById(R.id.latitude);
        m_campo_longitude = findViewById(R.id.longitude);

        m_botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_latitude = Double.parseDouble(m_campo_latitude.getText().toString());
                m_longitude = Double.parseDouble(m_campo_longitude.getText().toString());

                Intent intent = new Intent(LocalizacaoActivity.this, MapsActivity.class);

                intent.putExtra("m_valor_latitude", m_latitude);
                intent.putExtra("m_valor_longitude", m_longitude);

                startActivity(intent);
            }
        });
    }

}