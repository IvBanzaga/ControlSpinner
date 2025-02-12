package com.vanzaga.controlspinner;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNum1, etNum2;
    private TextView tvResultado;
    private Spinner spOperaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciar los elementos de la interfaz
        etNum1 = findViewById(R.id.txt_num1);
        etNum2 = findViewById(R.id.txt_num2);
        tvResultado = findViewById(R.id.textView);
        spOperaciones = findViewById(R.id.spinner);

        // Configurar el Spinner con las opciones de operaciones
        String[] operaciones = {"Suma", "Resta", "Multiplicación", "División"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operaciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOperaciones.setAdapter(adapter);
    }

    // Creamos la función calcular
    public void calcular(View view) {

        // Obtenemos los valores de los EditText y Spinner
        String num1 = etNum1.getText().toString();
        String num2 = etNum2.getText().toString();
        String elegir = spOperaciones.getSelectedItem().toString();

        // Compromabos que los campos no estén vacíos
        if (!num1.isEmpty() && !num2.isEmpty()) {

            // Convertimos los valores a double
            double n1 = Double.parseDouble(num1);
            double n2 = Double.parseDouble(num2);

            // Creamos una variable para el resultado
            double resultado = 0;

            // Con el switch evaluamos la operación seleccionada
            switch (elegir) {
                case "Suma":
                    resultado = n1 + n2;
                    break;
                case "Resta":
                    resultado = n1 - n2;
                    if (n2 > n1) {
                        resultado = n2 - n1;
                    }
                    break;
                case "Multiplicación":
                    resultado = n1 * n2;
                    break;
                case "División":
                    if (n2 != 0) {
                        resultado = n1 / n2;
                    } else {
                        tvResultado.setText("No se puede dividir por cero");
                        return;
                    }
                    break;
                default:
                    tvResultado.setText("Selecciona una operación válida");
                    return;
            }
            tvResultado.setText(String.format("%.2f", resultado));
        } else {
            tvResultado.setText(HtmlCompat.fromHtml(getString(R.string.mensaje), HtmlCompat.FROM_HTML_MODE_LEGACY));
        }
    }
}