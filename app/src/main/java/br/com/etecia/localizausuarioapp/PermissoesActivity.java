package br.com.etecia.localizausuarioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PermissoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static boolean validar_permissoes(String[] m_permissoes, Activity m_activity, int m_request_code) {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> m_lista_permissoes = new ArrayList<>();

            for (String m_permissao : m_permissoes) {
                Boolean existe_permissao = ContextCompat
                        .checkSelfPermission(m_activity, m_permissao)
                        == PackageManager.PERMISSION_GRANTED;
                if (!existe_permissao)
                    m_lista_permissoes.add(m_permissao);
            }

            if (m_lista_permissoes.isEmpty())
                return true;
            String[] m_novas_permissoes = new String[m_lista_permissoes.size()];
            m_lista_permissoes.toArray(m_novas_permissoes);

            ActivityCompat.requestPermissions(m_activity, m_novas_permissoes, m_request_code);
        }
        return true;
    }
}