package es.netrunners.databinding;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class DatabindingActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ListView list = (ListView) findViewById(R.id.list);

		// Con estas variables asignamos las claves del hashamps con las ids de
		// sus correspondientes contenedores en el layout de la fila
		String[] from = new String[] { "Nombre", "Hora" };
		int[] to = new int[] { R.id.text1, R.id.text2 };

		// Esta lista contendrá la información en bruto. Este modo puede ser
		// reemplazado por cualquier otra forma de obtener datos.
		ArrayList<String[]> data = new ArrayList<String[]>();

		String[] evento1 = { "Android Training", "11:30" };
		data.add(evento1);

		String[] evento2 = { "Java Course", "12:00" };
		data.add(evento2);

		String[] evento3 = { "Lunch", "14:00" };
		data.add(evento3);

		// Esta lista contendrá los datos que aparecerán en el ListView. Cada
		// elemento del ArrayList será una fila en el ListView. La información
		// del mismo podrá ser accesible mediante la forma clave/valor
		ArrayList<HashMap<String, String>> Events = new ArrayList<HashMap<String, String>>();
		for (String[] event : data) {

			HashMap<String, String> eventData = new HashMap<String, String>();

			// Aquí utilizamos las claves almacenadas en la variable 'from' para
			// asignar los valores de los datos
			eventData.put(from[0], event[0]);
			eventData.put(from[1], event[1]);

			Events.add(eventData);
		}
		// Una vez tengamos almacenada toda la información necesaria para
		// rellenar el Listview estaremos preparados para crear el Adaptador:
		// SimpleAdapter(Contexto, HasMap con los elementos, Layout de la Fila,
		// Claves, IDs de los elementos del layout de la fila)
		SimpleAdapter ListAdapter = new SimpleAdapter(this, Events,
				R.layout.row, from, to);
		list.setAdapter(ListAdapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				// Leemos de esta forma la información del ListView. Es
				// importante destacar que no tiene porque corresponderse con la
				// información que se muestra, es decir, podemos mostrar nombre
				// y tiempo en la lista, pero en el adaptador podemos contener
				// más información extra como el número de teléfono para
				// utilizarlos momentos específicos como éste. 
				@SuppressWarnings("unchecked")
				HashMap<String, String> item = (HashMap<String, String>) arg0
						.getAdapter().getItem(position);
				Toast.makeText(getApplicationContext(),
						item.get("Nombre") + " " + item.get("Hora"),
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}