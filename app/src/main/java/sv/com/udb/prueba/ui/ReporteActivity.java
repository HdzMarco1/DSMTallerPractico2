package sv.com.udb.prueba.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.databinding.ActivityCitaBinding;
import sv.com.udb.prueba.databinding.ActivityReporteBinding;
import sv.com.udb.prueba.model.Cita;
import sv.com.udb.prueba.model.Tratamiento;
import sv.com.udb.prueba.repositories.CitaRepository;
import sv.com.udb.prueba.repositories.TratamientoRepository;

public class ReporteActivity extends AppCompatActivity {


    private CitaRepository citaRepository;
    private ActivityReporteBinding binding;
    private List<Cita> citaList = new ArrayList<>();
    private List<String> citaListStr = new ArrayList<>();
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReporteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        citaRepository = new CitaRepository(getApplicationContext());
        try {
            /*for (Tratamiento t : tratamientoRepository.findAll()) {
                tratamientoList.add(t.getNombre());
                System.err.println(t.getNombre());
            }*/
            citaList = citaRepository.findAll();
            for (Cita cita : citaList) {
                citaListStr.add(
                        "Cliente: " + cita.getCliente() + "\n" +
                                "Tratamiento: " + cita.getTratamiento().getNombre() + "\n" +
                                "Fecha: " + cita.getFecha() + "\n" +
                                "Horario: " + cita.getHorario().getHoraInicio() + " - " + cita.getHorario().getHoraFin() + "\n" +
                                "Precio: " + cita.getTratamiento().getPrecio()
                );
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, citaListStr);

            binding.LstCitas.setAdapter(adapter);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}